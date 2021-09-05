package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISIndividualBusinessResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.AGENT_ID;
import static com.project.supportClasses.SharedPref.USER_DETAILS;

public class IndividualBusinessReportActivity extends AppCompatActivity {
    private ImageView ivBack;
    private RecyclerView rvIndividualBusiness;
    private AppCompatEditText editEnterCode;
    private AppCompatTextView tvStartDate, tvEndDate,freshBussinessAmount,renewalBussinessAmount;
    private MaterialCardView cvSubmit;
    private UserDetailsResponse userDetails;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private ProgressBar pb;
    private HorizontalScrollView hsv;

    private Date start;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_business_report);

        init();
        handleClicks();
        apiResponse();
    }

    private void apiResponse() {

        integratedServicesViewModel.getMisIndividualBusinessResponseLiveData().observe(this,misIndividualBusinessResponses -> {
            Misc.enableScreenTouch(this);
            pb.setVisibility(View.GONE);

            if(misIndividualBusinessResponses.size()>0)
            {
                Integer sumWeightedPremiumFresh = 0;
                Integer sumWeightedPremiumRenewal = 0;
                hsv.setVisibility(View.VISIBLE);
                rvIndividualBusiness.setAdapter(new IndividualBusinessReportAdapter(this,misIndividualBusinessResponses));
                for (MISIndividualBusinessResponse misIndividualBusinessRespons : misIndividualBusinessResponses) {
                    if (misIndividualBusinessRespons.getBusinessType() != null) {
                        if (misIndividualBusinessRespons.getBusinessType().equalsIgnoreCase("FRESH")) {
                            if (misIndividualBusinessRespons.getWeightedPremium() != null || !misIndividualBusinessRespons.getWeightedPremium().equalsIgnoreCase("")) {
                                Integer value = Integer.parseInt(misIndividualBusinessRespons.getWeightedPremium());
                                sumWeightedPremiumFresh = sumWeightedPremiumFresh + value;
                            }
                        } else {
                            if (misIndividualBusinessRespons.getWeightedPremium() != null || !misIndividualBusinessRespons.getWeightedPremium().equalsIgnoreCase("")) {
                                Integer value = Integer.parseInt(misIndividualBusinessRespons.getWeightedPremium());
                                sumWeightedPremiumRenewal = sumWeightedPremiumRenewal + value;
                            }
                        }
                    }
                }
                renewalBussinessAmount.setText(String.valueOf(sumWeightedPremiumRenewal));
                freshBussinessAmount.setText(String.valueOf(sumWeightedPremiumFresh));

            }
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

    private void handleClicks() {
        rvIndividualBusiness = findViewById(R.id.rvIndividualBusiness);
        rvIndividualBusiness.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ivBack.setOnClickListener(v -> {
            onBackPressed();
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
            if(validated())
            {
                if (Misc.isNetworkAvailable(this))
                {
                    Misc.disableScreenTouch(this);
                    pb.setVisibility(View.VISIBLE);
                    String logiId = SharedPref.getInstance(this).getData(AGENT_ID);
                    integratedServicesViewModel.submitMISIndividualBusinessDetails(editEnterCode.getText().toString().trim(),Misc.getApiFormattedDate(tvStartDate.getText().toString()),Misc.getApiFormattedDate(tvEndDate.getText().toString()),userDetails.getRoleId(),logiId);
//                    integratedServicesViewModel.submitMISIndividualBusinessDetails("8001780","2016-01-01","2020-09-30","3");  // for testing
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
        if(editEnterCode.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please enter code", Toast.LENGTH_SHORT).show();
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
        rvIndividualBusiness = findViewById(R.id.rvIndividualBusiness);
        editEnterCode = findViewById(R.id.editEnterCode);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        cvSubmit = findViewById(R.id.cvSubmit);
        pb = findViewById(R.id.pb);
        hsv = findViewById(R.id.hsv);
        freshBussinessAmount = findViewById(R.id.freshBussinessAmount);
        renewalBussinessAmount = findViewById(R.id.renewalBussinessAmount);

        rvIndividualBusiness.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        userDetails = new Gson().fromJson(SharedPref.getInstance(this).getData(USER_DETAILS), UserDetailsResponse.class);
        if(userDetails.getLoginType().equals("Agent"))
       /* {
            editEnterCode.setEnabled(true);
            editEnterCode.setText(SharedPref.getInstance(this).getData(AGENT_ID));
        }*/
        editEnterCode.setText(SharedPref.getInstance(this).getData(AGENT_ID));

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