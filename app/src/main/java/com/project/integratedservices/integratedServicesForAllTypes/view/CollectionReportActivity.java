package com.project.integratedservices.integratedServicesForAllTypes.view;

import static com.project.supportClasses.SharedPref.AGENT_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISCollectionRegisterResponse;
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

public class CollectionReportActivity extends AppCompatActivity {
    private ImageView ivBack;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private RecyclerView rvCollectionReport;
    private AppCompatTextView tvStartDate, tvEndDate;
    private MaterialCardView cvSubmit;
    private Date start;
    private Calendar calendar;
    private ProgressBar pb;
    private CollectionReportAdapter adapter;
    private HorizontalScrollView hsv;
    private AppCompatTextView freshBussinessAmount,renewalBussinessAmount,sumBussinessAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_report);

        init();
        handleClicks();
        apiResponses();
    }

    private void apiResponses() {
        integratedServicesViewModel.getMisCollectionRegisterResponseLiveData().observe(this,misCollectionRegisterResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if(misCollectionRegisterResponses.size()>0 && misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success"))
            {
                hsv.setVisibility(View.VISIBLE);
                adapter = new CollectionReportAdapter(this,misCollectionRegisterResponses);
                rvCollectionReport.setAdapter(adapter);

                Double sumWeightedPremiumFresh = 0.0;
                Double sumWeightedPremiumRenewal = 0.0;

                for (MISCollectionRegisterResponse misIndividualBusinessRespons : misCollectionRegisterResponses) {
                    if (misIndividualBusinessRespons.getFreshWeighted() != null) {

                                Double value = Double.parseDouble(misIndividualBusinessRespons.getFreshWeighted());
                                sumWeightedPremiumFresh = sumWeightedPremiumFresh + value;

                                Double value1 = Double.parseDouble(misIndividualBusinessRespons.getRenewalWeighted());
                                sumWeightedPremiumRenewal = sumWeightedPremiumRenewal + value1;
                        }
                    }
                renewalBussinessAmount.setText(String.valueOf(sumWeightedPremiumRenewal));
                freshBussinessAmount.setText(String.valueOf(sumWeightedPremiumFresh));
                sumBussinessAmount.setText(String.valueOf(sumWeightedPremiumRenewal+sumWeightedPremiumFresh));

            } else if(misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("UnSuccess")) {
                hsv.setVisibility(View.GONE);
                renewalBussinessAmount.setText("0");
                freshBussinessAmount.setText("0");
            } else if (!(misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success") || misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("UnSuccess"))) {
                ColorDialog colorDialog = MyColorDialog.getInstance(this);
                colorDialog.setContentText(misCollectionRegisterResponses.get(0).getStatus());
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();

                hsv.setVisibility(View.GONE);
                renewalBussinessAmount.setText("0");
                freshBussinessAmount.setText("0");
            }
        });


        integratedServicesViewModel.getApiError().observe(this,s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (!(s.equalsIgnoreCase("Success") || s.equalsIgnoreCase("UnSuccess"))) {
                ColorDialog colorDialog = MyColorDialog.getInstance(this);
                colorDialog.setContentText(s);
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();

                hsv.setVisibility(View.GONE);
            }
        });
    }

    private void handleClicks() {
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
                    String loggedInAgentsId = SharedPref.getInstance(this).getData(AGENT_ID);
                    Misc.disableScreenTouch(this);
                    pb.setVisibility(View.VISIBLE);

                    integratedServicesViewModel.submitMISCollectionRegisterResponse(Misc.getApiFormattedDate(tvStartDate.getText().toString()),Misc.getApiFormattedDate(tvEndDate.getText().toString()),loggedInAgentsId);
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
        if(tvStartDate.getText().toString().isEmpty())
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


    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack = findViewById(R.id.ivBack);
        rvCollectionReport = findViewById(R.id.rvCollectionReport);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        cvSubmit = findViewById(R.id.cvSubmit);
        pb = findViewById(R.id.pb);
        hsv = findViewById(R.id.hsv);
        freshBussinessAmount = findViewById(R.id.freshBussinessAmount);
        renewalBussinessAmount = findViewById(R.id.renewalBussinessAmount);
        sumBussinessAmount = findViewById(R.id.sumBussinessAmount);

        rvCollectionReport.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}