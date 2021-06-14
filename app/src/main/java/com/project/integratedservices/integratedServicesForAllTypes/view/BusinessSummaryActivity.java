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
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import cn.refactor.lib.colordialog.ColorDialog;

public class BusinessSummaryActivity extends AppCompatActivity {
    private ImageView ivBack;
        private IntegratedServicesViewModel integratedServicesViewModel;
    private RecyclerView rvBusinessSummary;
    private AppCompatTextView tvStartDate, tvEndDate;
    private MaterialCardView cvSubmit;
    private Date start;
    private Calendar calendar;
    private ProgressBar pb;
    private HorizontalScrollView hsv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_summary);

        init();
        handleClicks();
        apiResponses();
    }

    private void apiResponses() {
        integratedServicesViewModel.getMisBusinessSummaryResponseLiveData().observe(this,misBusinessSummaryResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if(misBusinessSummaryResponses.size()>0)
            {
                hsv.setVisibility(View.VISIBLE);
                rvBusinessSummary.setAdapter(new MISBusinessSummaryAdapter(this,misBusinessSummaryResponses));
            }
        });

        integratedServicesViewModel.getApiError().observe(this,s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
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
                    Misc.disableScreenTouch(this);
                    pb.setVisibility(View.VISIBLE);

                    integratedServicesViewModel.submitMISIndividualBusinessDetails(tvStartDate.getText().toString(),tvEndDate.getText().toString());
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

                String myFormat = "dd MMM, yyyy"; //In which date Format needed
                String apiFormat = "yyyy-MM-dd"; //In which date Format needed
                SimpleDateFormat sdf = new SimpleDateFormat(apiFormat, Locale.ENGLISH);
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
        rvBusinessSummary = findViewById(R.id.rvBusinessSummary);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        cvSubmit = findViewById(R.id.cvSubmit);
        pb = findViewById(R.id.pb);
        hsv = findViewById(R.id.hsv);

        rvBusinessSummary.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}