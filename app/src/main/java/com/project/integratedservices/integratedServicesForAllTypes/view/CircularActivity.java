package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.circular_response.CircularResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.message_response.SmsDetailsResposne;
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

public class CircularActivity extends AppCompatActivity implements CircularAdapter.HandleClick {
    private IntegratedServicesViewModel integratedServicesViewModel;
    private SpinKitView spinKitView;
    private RecyclerView rvTeam;
    private CircularAdapter adapter;
    private ImageView ivBack;
    private Calendar calendar;
    private AppCompatTextView tvStartDate;
    private AppCompatTextView tvEndDate;
    private Date start;
    private Date end;
    private MaterialCardView cvSubmit;

    String myFormat = "dd-MM-yyyy"; //In which date Format needed
    String apiFormat = "yyyy-MM-dd"; //In which date Format needed
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
    SimpleDateFormat sdfApiFormat = new SimpleDateFormat(apiFormat, Locale.ENGLISH);
    String startDateApi,endDateApi ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);
        spinKitView = findViewById(R.id.spin_kit);
        cvSubmit = findViewById(R.id.cvSubmit);
        rvTeam = findViewById(R.id.rvTeam);
        ivBack = findViewById(R.id.ivBack);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        rvTeam.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
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
        cvSubmit.setOnClickListener(v -> fetchData());

        observeDeleteLive();
        observeSmsLiveData();

        integratedServicesViewModel.getApiError().observe(this,s -> {
            spinKitView.setVisibility(View.GONE);
            Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
            Misc.enableScreenTouch(this);
        });


    }

    private void fetchData() {
        if (start==null) {
            Toast.makeText(this, "Please Enter Start Date", Toast.LENGTH_SHORT).show();

        } else if (end == null) {
            Toast.makeText(this, "Please Enter End Date", Toast.LENGTH_SHORT).show();
        } else {
            checkforSmsDetails();
        }
    }


    private void observeSmsLiveData() {
        integratedServicesViewModel.getCircularResponseLiveData().observe(this,alertMessageResponses -> {
            spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (alertMessageResponses != null) {
                if (alertMessageResponses.size() > 0) {



                    if (alertMessageResponses.get(0).getStatus().equals("Success") || alertMessageResponses.get(0).getStatus().equals("Successfull")) {
                        rvTeam.setVisibility(View.VISIBLE);
                        adapter = new CircularAdapter(this, alertMessageResponses, this, true);
                        rvTeam.setAdapter(adapter);
                    } else {
//                    Toast.makeText(getActivity(), teamDetailsResponses.get(0).getStatus(), Toast.LENGTH_LONG).show();
                        Toast.makeText(this, R.string.no_items_found, Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void observeDeleteLive() {
        integratedServicesViewModel.getDeleteSmsResponseLiveData().observe(this,alertMessageResponses -> {

            if (alertMessageResponses != null) {
                if (alertMessageResponses.size() > 0) {
                    spinKitView.setVisibility(View.GONE);
                    Misc.enableScreenTouch(this);


                    if (alertMessageResponses.get(0).getStatus().equals("Success") || alertMessageResponses.get(0).getStatus().equals("Successfull")) {
                        checkforSmsDetails();
                    } else {
//                    Toast.makeText(getActivity(), teamDetailsResponses.get(0).getStatus(), Toast.LENGTH_LONG).show();
                        Toast.makeText(this, R.string.no_items_found, Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, R.string.no_items_found, Toast.LENGTH_SHORT).show();
                Misc.enableScreenTouch(this);
            }

        });
    }

    private void checkforSmsDetails() {
        if(Misc.isNetworkAvailable(this)) {
            Misc.disableScreenTouch(this);
            spinKitView.setVisibility(View.VISIBLE);
            integratedServicesViewModel.getPrizeReport(SharedPref.getInstance(this).getData(AGENT_ID),startDateApi,endDateApi);
        }
        else
        {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog->{
                ColorDialog.dismiss();
                spinKitView.setVisibility(View.VISIBLE);
                integratedServicesViewModel.getPrizeReport(SharedPref.getInstance(this).getData(AGENT_ID),Misc.getApiFormattedDate(tvStartDate.getText().toString()),Misc.getApiFormattedDate(tvEndDate.getText().toString()));
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }

    @Override
    public void handleClick(CircularResponse team) {
        showPdf(team);
    }

    private void showPdf(CircularResponse team) {
        if (team.getFileName().contains(".pdf")) {
            String url = "https://docs.google.com/gview?embedded=true&url=http://www.webzeminiprint.in/CIRCULAR/" + team.getFileName();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else {
            String url = "http://www.webzeminiprint.in/CIRCULAR/" + team.getFileName();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

    }

    private void openDatePicker(AppCompatTextView textView) {
        calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener sdt = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                textView.setText(sdf.format(calendar.getTime()));

                if (textView == tvStartDate) {
                    start = calendar.getTime();
                    startDateApi = sdfApiFormat.format(start);
                } else {
                    end = calendar.getTime();
                    endDateApi = sdfApiFormat.format(end);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}