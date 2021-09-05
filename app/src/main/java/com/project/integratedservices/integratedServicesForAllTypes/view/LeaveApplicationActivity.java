package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.ImageViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.ApplyLeaveRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.VisitNotInterestedRequestPojo;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LeaveApplicationActivity extends AppCompatActivity {

    private AppCompatTextView tvStartDate,tvEndDate,tvNoOfDays;
    private AppCompatEditText etReason;
    private AppCompatButton bt_submit;
    private AppCompatImageView ivBack;
    private Calendar datecalendar;
    private Date start,end;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);

        init();

        integratedServicesViewModel.getApiError().observe(this,s -> {
            Misc.enableScreenTouch(this);
            pb.setVisibility(View.GONE);
            Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        });

        integratedServicesViewModel.getLeaveResponseLiveData().observe(this,applyLeaveResponses -> {
            Misc.enableScreenTouch(this);
            pb.setVisibility(View.GONE);
//            if(applyLeaveResponses.get(0).getStatus().equalsIgnoreCase(""))
            Toast.makeText(this, ""+applyLeaveResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
            finish();
        });

    }

    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        datecalendar = Calendar.getInstance();
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        etReason = findViewById(R.id.etReason);
        bt_submit = findViewById(R.id.bt_submit);
        ivBack = findViewById(R.id.ivBack);
        tvNoOfDays = findViewById(R.id.tvNoOfDays);
        pb = findViewById(R.id.pb);

        ivBack.setOnClickListener(v -> finish());

        tvStartDate.setOnClickListener(v -> {
            openDatePicker(tvStartDate,0);

        });

        tvEndDate.setOnClickListener(v -> {
            if(start!=null) {
                openDatePicker(tvEndDate, 1);
            }
            else
            {
                Toast.makeText(this, "Please select start date first", Toast.LENGTH_SHORT).show();
            }
        });

        etReason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==1000)
                {
                    Toast.makeText(LeaveApplicationActivity.this, "Max characters supported is 1000", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_submit.setOnClickListener(v -> {
            if(start==null)
            {
                Toast.makeText(this, "Please select start date", Toast.LENGTH_SHORT).show();
            }
            else if(end==null)
            {
                Toast.makeText(this, "Please select end date", Toast.LENGTH_SHORT).show();
            }
            else if(etReason.getText().toString().trim().isEmpty())
            {
                Toast.makeText(this, "Reason cannot be empty", Toast.LENGTH_SHORT).show();
            }
//            else if(etReason.getText().toString().trim().length()<1000)
//            {
//                Toast.makeText(this, "Reason cannot be less than 1000 characters", Toast.LENGTH_SHORT).show();
//            }
            else
                {
                    if (Misc.isNetworkAvailable(this)) {
                        Misc.disableScreenTouch(this);
                        pb.setVisibility(View.VISIBLE);

                        ApplyLeaveRequestPojo requestPojo = new ApplyLeaveRequestPojo();
                        requestPojo.setAgentCode(SharedPref.getInstance(LeaveApplicationActivity.this).getData(SharedPref.AGENT_ID));
                        requestPojo.setLeaveEndDate(Misc.getApiFormattedDate(tvEndDate.getText().toString()));
                        requestPojo.setLeaveStartDate(Misc.getApiFormattedDate(tvStartDate.getText().toString()));
                        requestPojo.setLeaveText(etReason.getText().toString());

                        integratedServicesViewModel.applyLeave(requestPojo);
                    } else {
                        Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
    private void openDatePicker(AppCompatTextView textView,int type) {

        final DatePickerDialog.OnDateSetListener sdt = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                datecalendar.set(Calendar.YEAR, year);
                datecalendar.set(Calendar.MONTH, month);
                datecalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy"; //In which date Format needed
                String apiFormat = "yyyy-MM-dd"; //In which date Format needed
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                SimpleDateFormat sdfApiFormat = new SimpleDateFormat(apiFormat, Locale.ENGLISH);

//                followUpDate = sdfApiFormat.format(datecalendar.getTime());

//            apiDateFormat = sdfApiFormat.format(datecalendar.getStartTime());
                textView.setText(sdf.format(datecalendar.getTime()));

                if(type==0)
                {
                    start = datecalendar.getTime();
                }
                else
                {
                    end = datecalendar.getTime();

                    if(start!=null)
                    {
                        long diff = end.getTime() - start.getTime();
                        tvNoOfDays.setText(""+(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1));

                    }
                }

            }
        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(this), sdt, datecalendar
                .get(java.util.Calendar.YEAR), datecalendar.get(java.util.Calendar.MONTH),
                datecalendar.get(java.util.Calendar.DAY_OF_MONTH));


        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);

        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();

    }
}
