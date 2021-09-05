package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.authencationRepo.remote.response.loginModel.UserDetails;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BranchDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISCompanyDetailsResponse;
import com.project.supportClasses.CustomSpinner;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.AGENT_ID;
import static com.project.supportClasses.SharedPref.USER_DETAILS;

public class BranchWiseBusinessActivity extends AppCompatActivity implements SpinnerAdapterBranchName.OnItemSelectListener, SpinnerAdapterMISCompanyName.OnItemSelectListener, SpinnerAdapterBusinessType.OnItemSelectListener {
    private ImageView ivBack;
    private SpinnerAdapterBranchName spinnerAdapterBranchName;
    private SpinnerAdapterMISCompanyName spinnerAdapterCompanyName;
    private SpinnerAdapterBusinessType spinnerAdapterBusinessType;
    private CustomSpinner spBranchName,spinsuranceCompany,spBusinessType;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private AppCompatEditText editEnterCode;
    private AppCompatTextView tvBranchName,tvInsuranceCompany,tvBusinessType,tvStartDate,tvEndDate;
    private UserDetailsResponse userDetails;
    private List<String> businessTypeList = new ArrayList<>();
    private ProgressBar pb;
    private MaterialCardView cvSubmit;
    private RecyclerView rvBranchWiseBusiness;
    private HorizontalScrollView hsv;

    private String selectedBranchId = "";
    private String selectedBusinessType = "0";
    private String selectedInsuranceCompany = "0";
    private String codeType = "0";
    private String userId = "";
    private boolean code,insurance,type;

    private Date start;
    private Calendar calendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_wise_business);

        init();
        handleClick();

        if (Misc.isNetworkAvailable(this))
        {
            apiCalls();
        }
        else
        {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                ColorDialog.dismiss();
                apiCalls();
            });
            colorDialog.setNegativeListener("CANCEL",dialog -> {
                dialog.dismiss();
                this.onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }

    }

    private void apiCalls() {
        Misc.disableScreenTouch(this);
        pb.setVisibility(View.VISIBLE);
        integratedServicesViewModel.getMISBranchDetails(userDetails.getRoleId(),userId);





        integratedServicesViewModel.getMISBranchDetailsResponseLiveData().observe(this,branchDetailsResponses -> {

            //call 2nd api on response from 1st api
            integratedServicesViewModel.getMISCompanyDetails();


            if (branchDetailsResponses.size() > 0) {
                spinnerAdapterBranchName = new SpinnerAdapterBranchName(this, branchDetailsResponses, this);
                spBranchName.setAdapter(spinnerAdapterBranchName);
                spBranchName.setDropDownWidth(900);
            }
        });

        integratedServicesViewModel.getMISCompanyDetailsResponseLiveData().observe(this,misCompanyDetailsResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if(misCompanyDetailsResponses.size() > 0)
            {
                spinnerAdapterCompanyName = new SpinnerAdapterMISCompanyName(this,misCompanyDetailsResponses,this);
                spinsuranceCompany.setAdapter(spinnerAdapterCompanyName);
                spinsuranceCompany.setDropDownWidth(900);
            }


            integratedServicesViewModel.getBranchWiseBusinessResponseLiveData().observe(this,branchWiseBusinessResponses -> {
                pb.setVisibility(View.GONE);
                Misc.enableScreenTouch(this);

                if(branchWiseBusinessResponses.size()>0)
                {
                    hsv.setVisibility(View.VISIBLE);
                    rvBranchWiseBusiness.setAdapter(new BranchWiseBusinessAdapter(this,branchWiseBusinessResponses));
                }
            });

        });

        integratedServicesViewModel.getApiError().observe(this,s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText(s);
            colorDialog.setCancelable(true);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        });
    }

    private void handleClick() {
        ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

        tvBranchName.setOnClickListener(v -> {
            spBranchName.performClick();
        });

        tvInsuranceCompany.setOnClickListener(v -> {
            spinsuranceCompany.performClick();
        });

        tvBusinessType.setOnClickListener(v -> {
            spBusinessType.performClick();
        });

        tvStartDate.setOnClickListener(v -> {
            openDatePicker(tvStartDate);
        });

        tvEndDate.setOnClickListener(v -> {
            if(start != null)
            openDatePicker(tvEndDate);
            else Toast.makeText(this, "First select start date", Toast.LENGTH_SHORT).show();
        });

        cvSubmit.setOnClickListener(v -> {
           /* if(tvBranchName.getText().toString().isEmpty())
            {
                selectedBranchId = "0";
            }*/
            if(editEnterCode.getText().toString().trim().isEmpty()) {
                code = false;
            }
            else {
                codeType = editEnterCode.getText().toString().trim();
                code = true;
            }

            if(tvInsuranceCompany.getText().toString().isEmpty())
                insurance = false;
            else
                insurance = true;

            if(tvBusinessType.getText().toString().isEmpty())
                type = false;
            else
                type = true;

            if(validated())
            {
                if (Misc.isNetworkAvailable(this))
                {
                    Misc.disableScreenTouch(this);
                    pb.setVisibility(View.VISIBLE);

                    integratedServicesViewModel.submitMISBRanchWiseBusinessDetails(selectedBranchId,Misc.getApiFormattedDate(tvStartDate.getText().toString()),Misc.getApiFormattedDate(tvEndDate.getText().toString()),String.valueOf(code),
                            String.valueOf(insurance),String.valueOf(type),codeType,selectedInsuranceCompany,selectedBusinessType);
                }
                else
                {
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText("Please check your Internet connection and retry");
                    colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                        ColorDialog.dismiss();
                         validated();
                    });
                    colorDialog.setNegativeListener("CANCEL",dialog -> {
                        dialog.dismiss();
                        this.onBackPressed();
                    });
                    colorDialog.setCancelable(false);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();
                }
            }
        });
    }

    private boolean validated() {
        if(tvBranchName.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please select branch name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(tvStartDate.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please select start date", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(tvEndDate.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please select end date", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack = findViewById(R.id.ivBack);
        spBranchName = findViewById(R.id.spBranchName);
        tvBranchName = findViewById(R.id.tvBranchName);
        tvInsuranceCompany = findViewById(R.id.tvinsuranceCompany);
        spinsuranceCompany = findViewById(R.id.spinsuranceCompany);
        tvBusinessType = findViewById(R.id.tvBusinessType);
        spBusinessType = findViewById(R.id.spBusinessType);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        editEnterCode = findViewById(R.id.editEnterCode);
        pb = findViewById(R.id.pb);
        cvSubmit = findViewById(R.id.cvSubmit);
        rvBranchWiseBusiness = findViewById(R.id.rvBranchWiseBusiness);
        hsv = findViewById(R.id.hsv);


        userDetails = new Gson().fromJson(SharedPref.getInstance(this).getData(USER_DETAILS), UserDetailsResponse.class);

        userId = SharedPref.getInstance(this).getData(AGENT_ID);
        //disable editEnterCode when login type -> agent
        if(userDetails.getLoginType().equals("Agent")) {
            editEnterCode.setEnabled(false);
            editEnterCode.setText(SharedPref.getInstance(this).getData(AGENT_ID));
        }

        //add static business types
        businessTypeList.add("FRESH");
        businessTypeList.add("RENEWAL");

        spinnerAdapterBusinessType = new SpinnerAdapterBusinessType(this,businessTypeList,this);
        spBusinessType.setAdapter(spinnerAdapterBusinessType);
        spBusinessType.setDropDownWidth(900);

        rvBranchWiseBusiness.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public <E> void selectedBranchId(E selectedItem) {
        spBranchName.onDetachedFromWindow();

        BranchDetailsResponse obj = (BranchDetailsResponse) selectedItem;
        selectedBranchId = obj.getBranchId();
        tvBranchName.setText(obj.getBranchName());

    }

    @Override
    public <E> void selectedCompany(E selectedItem) {
        spinsuranceCompany.onDetachedFromWindow();
        selectedInsuranceCompany = ((MISCompanyDetailsResponse)selectedItem).getCompanyName();
        tvInsuranceCompany.setText(selectedInsuranceCompany);
    }

    @Override
    public <E> void selectedBusinessType(E selectedItem) {
        spBusinessType.onDetachedFromWindow();
        selectedBusinessType = selectedItem.toString();
        tvBusinessType.setText(selectedBusinessType);
    }

    private void openDatePicker(AppCompatTextView textView) {
        calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener sdt = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy"; //In which date Format needed
                String apiFormat = "yyyy-MM-dd"; //In which date Format needed
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                SimpleDateFormat sdfApiFormat = new SimpleDateFormat(apiFormat, Locale.ENGLISH);

                textView.setText(sdf.format(calendar.getTime()));

                if(textView == tvStartDate)
                {
                    start = calendar.getTime();
                }

            }
        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(this), sdt, calendar
                .get(java.util.Calendar.YEAR), calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH));


//        Date dt = new Date();
//        Calendar c = Calendar.getInstance();
//        c.setTime(dt);
//        c.add(Calendar.DATE, 1);
//
        if(textView != tvStartDate)
        {
            datePickerDialog.getDatePicker().setMinDate(start.getTime());
        }

        datePickerDialog.show();

    }
}