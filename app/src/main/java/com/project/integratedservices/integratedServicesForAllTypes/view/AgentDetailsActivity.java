package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AgentDetail;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.mis_agent_joining_details.MisAgentJoiningDetails;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.AGENT_ID;

public class AgentDetailsActivity extends AppCompatActivity {
    private ImageView ivBack;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private List<AgentDetail> mainResponseList;
    private RecyclerView rvCollectionReport;
    private AppCompatTextView tvStartDate, tvEndDate;
    private AppCompatTextView dateFromTo;
    private AppCompatEditText editEnterCode;
    private MaterialCardView cvSubmit;
    private Date start;
    private Calendar calendar;
    private ProgressBar pb;
    private AgentDetailsAdapter adapter;
    private TextView dateFromTo4;
    private TextView dateFromTo5;
    private TextView dateFromTo6;
    private TextView dateFromTo7;
    private TextView dateFromTo8;
    private TextView dateFromTo9;
    private TextView dateFromTo10;
    private AppCompatTextView dateFromTo11;
    private String startApiDateFormat;
    private String endApiDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);
        init();
        handleClicks();
        apiResponses();
    }
    private void apiResponses() {
        integratedServicesViewModel.getAgentDetailsLiveData().observe(this,misCollectionRegisterResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if(misCollectionRegisterResponses.size()>0)
            {
                if (!(misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success") || misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("UnSuccess"))) {
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText(misCollectionRegisterResponses.get(0).getStatus());
                    colorDialog.setCancelable(true);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();

                    dateFromTo4.setText("");
                    dateFromTo5.setText("");
                    dateFromTo6.setText("");
                    dateFromTo7.setText("");
                    dateFromTo8.setText("");
                    dateFromTo9.setText("");
                    dateFromTo10.setText("");
                    rvCollectionReport.setVisibility(View.GONE);

                } else if (misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success")) {
                    int totalJoining = 0;
                    if (!misCollectionRegisterResponses.isEmpty()) {
                        totalJoining = misCollectionRegisterResponses.get(misCollectionRegisterResponses.size() - 1).getSlno();
                    }
                    rvCollectionReport.setVisibility(View.VISIBLE);
                    adapter = new AgentDetailsAdapter(this,misCollectionRegisterResponses);
                    rvCollectionReport.setAdapter(adapter);
                    ((AppCompatTextView)findViewById(R.id.dateFromTo11)).setText(String.valueOf(totalJoining));
                   }

            }
        });
        integratedServicesViewModel.getMisAgentJoiningDetails().observe(this,misCollectionRegisterResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if(misCollectionRegisterResponses.size()>0)
            {
                MisAgentJoiningDetails misAgentJoiningDetails = misCollectionRegisterResponses.get(0);
                    dateFromTo4.setText(misAgentJoiningDetails.getAgent());
                    dateFromTo5.setText(misAgentJoiningDetails.getBranchName());
                    dateFromTo6.setText(misAgentJoiningDetails.getDoj());
                    dateFromTo7.setText(misAgentJoiningDetails.getIntroducer());
                    dateFromTo8.setText(misAgentJoiningDetails.getRankId());
                    dateFromTo9.setText(misAgentJoiningDetails.getGradeName());
//                dateFromTo10.setText(tvStartDate.getText().toString()+" to "+tvEndDate.getText().toString());
                    if (tvStartDate.getText() != null && tvEndDate.getText() != null) {
                        dateFromTo10.setText(tvStartDate.getText().toString() + " to " + tvEndDate.getText().toString());
                    }
                }
        });


    /*    integratedServicesViewModel.getApiError().observe(this,s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            Toast.makeText(this, ""+s, Toast.LENGTH_LONG).show();
        });*/

        integratedServicesViewModel.getApiError().observe(this,s -> {
            pb.setVisibility(View.GONE);
            if (!(s.equalsIgnoreCase("Success") || s.equalsIgnoreCase("UnSuccess"))) {
                ColorDialog colorDialog = MyColorDialog.getInstance(this);
                colorDialog.setContentText(s);
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();
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
            openDatePicker(tvEndDate);
        });
        cvSubmit.setOnClickListener(v -> {
            if(validated())
            {
                if (Misc.isNetworkAvailable(this))
                {
                    Misc.disableScreenTouch(this);
                    pb.setVisibility(View.VISIBLE);
                    String loggedInAgentsId = SharedPref.getInstance(this).getData(AGENT_ID);
                    if (tvStartDate.getText() != null && tvEndDate.getText() != null && editEnterCode.getText()!=null) {
                        integratedServicesViewModel.getAgentDetails(startApiDateFormat, endApiDateFormat, editEnterCode.getText().toString(), loggedInAgentsId);
                        integratedServicesViewModel.getMisAgentJoiningDetils(startApiDateFormat, endApiDateFormat, editEnterCode.getText().toString(), loggedInAgentsId);
                    } else {
                        Toast.makeText(this, "Please enter all details first", Toast.LENGTH_SHORT).show();
                    }
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

                if (textView == tvStartDate) {
                    start = calendar.getTime();
                    startApiDateFormat = sdfApiFormat.format(calendar.getTime());

                } else {
                    endApiDateFormat = sdfApiFormat.format(calendar.getTime());
                }
                setDateFromTo();
            }
        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(this), sdt, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));


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
        editEnterCode = findViewById(R.id.editEnterCode);

        dateFromTo4 = findViewById(R.id.dateFromTo4);
        dateFromTo5 = findViewById(R.id.dateFromTo5);
        dateFromTo6 = findViewById(R.id.dateFromTo6);
        dateFromTo7 = findViewById(R.id.dateFromTo7);
        dateFromTo8 = findViewById(R.id.dateFromTo8);
        dateFromTo9 = findViewById(R.id.dateFromTo9);
        dateFromTo10 = findViewById(R.id.dateFromTo10);
        dateFromTo11 = findViewById(R.id.dateFromTo11);
        pb = findViewById(R.id.pb);

        rvCollectionReport.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
    private void setDateFromTo() {
        String startDate = tvStartDate.getText().toString();
        String toDate = tvEndDate.getText().toString();
//        dateFromTo.setText(startDate + " To "+toDate);
    }
}