package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.util.Calendar;
import java.util.Date;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.AGENT_ID;
import static com.project.supportClasses.SharedPref.USER_DETAILS;

public class AgentCommissionDetailsActivity extends AppCompatActivity {
    private ImageView ivBack;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private RecyclerView rvCollectionReport;
    private EditText agentcodeValue;
    private TextInputLayout agentCode;
    private MaterialCardView cvSubmit;
    private Date start;
    private Calendar calendar;
    private ProgressBar pb;
    private AgentCommisionDetailsAdapter adapter;
    private TextView totalFrmamt, totalFrmcomm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_commission_details);
        init();
        handleClicks();
        apiResponses();
    }

    private void apiResponses() {
        integratedServicesViewModel.getAgentCommisionDetailsLiveData().observe(this, misCollectionRegisterResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if(misCollectionRegisterResponses.size() > 0) {
                if (misCollectionRegisterResponses.size() > 0 && misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success")) {
                    adapter = new AgentCommisionDetailsAdapter(this, misCollectionRegisterResponses);
                    rvCollectionReport.setAdapter(adapter);
                    rvCollectionReport.setVisibility(View.VISIBLE);
                } else if(misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("UnSuccess")) {
                    rvCollectionReport.setVisibility(View.GONE);
                    totalFrmamt.setText("0");
                    totalFrmcomm.setText("0");
                } else if (!(misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success") || misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("UnSuccess"))) {
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText(misCollectionRegisterResponses.get(0).getStatus());
                    colorDialog.setCancelable(true);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();

                    rvCollectionReport.setVisibility(View.GONE);
                    totalFrmamt.setText("0");
                    totalFrmcomm.setText("0");
                }
            } else {
                rvCollectionReport.setVisibility(View.GONE);
                totalFrmamt.setText("0");
                totalFrmcomm.setText("0");
            }
        });
        integratedServicesViewModel.getmAgentCommisionTotalLiveData().observe(this, misCollectionRegisterResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if(misCollectionRegisterResponses.size() > 0 && misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success")) {
                totalFrmamt.setText(misCollectionRegisterResponses.get(0).getFRMAMT());
                totalFrmcomm.setText(misCollectionRegisterResponses.get(0).getFRMCOMM());
            } else {
                totalFrmamt.setText("0");
                totalFrmcomm.setText("0");
            }

        });

        integratedServicesViewModel.getApiError().observe(this, s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (!(s.equalsIgnoreCase("Success") || s.equalsIgnoreCase("UnSuccess"))) {
                ColorDialog colorDialog = MyColorDialog.getInstance(this);
                colorDialog.setContentText(s);
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();

                rvCollectionReport.setVisibility(View.GONE);
            }
        });
    }

    private void handleClicks() {
        ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
        cvSubmit.setOnClickListener(v -> {
            if (validated()) {
                if (Misc.isNetworkAvailable(this)) {
                    Misc.disableScreenTouch(this);
                    pb.setVisibility(View.VISIBLE);
                    String agentId = SharedPref.getInstance(this).getData(AGENT_ID);
                    integratedServicesViewModel.getAgentCommisionDetails(agentcodeValue.getText().toString(),agentId);
                    integratedServicesViewModel.getAgentCommisionTotal(agentcodeValue.getText().toString(),agentId);
                } else {
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText("Please check your Internet connection and retry");
                    colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                        ColorDialog.dismiss();
                        validated();
                    });
                    colorDialog.setNegativeListener("CANCEL", dialog -> {
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
        if (agentcodeValue.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Ref Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    private void openDatePicker(AppCompatTextView textView) {
//        calendar = Calendar.getInstance();
//
//        final DatePickerDialog.OnDateSetListener sdt = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                calendar.set(Calendar.YEAR, year);
//                calendar.set(Calendar.MONTH, month);
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                String myFormat = "dd MMM, yyyy"; //In which date Format needed
//                String apiFormat = "yyyy-MM-dd"; //In which date Format needed
//                SimpleDateFormat sdf = new SimpleDateFormat(apiFormat, Locale.ENGLISH);
//                SimpleDateFormat sdfApiFormat = new SimpleDateFormat(apiFormat, Locale.ENGLISH);
//
//                textView.setText(sdf.format(calendar.getTime()));
//
//                if(textView == tvStartDate)
//                {
//                    start = calendar.getTime();
//                }
//
//            }
//        };
//
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(this), sdt, calendar
//                .get(java.util.Calendar.YEAR), calendar.get(java.util.Calendar.MONTH),
//                calendar.get(java.util.Calendar.DAY_OF_MONTH));
//
//
////        Date dt = new Date();
////        Calendar c = Calendar.getInstance();
////        c.setTime(dt);
////        c.add(Calendar.DATE, 1);
////
//        if(textView != tvStartDate)
//        {
//            datePickerDialog.getDatePicker().setMinDate(start.getTime());
//        }
//
//        datePickerDialog.show();
//
//    }


    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack = findViewById(R.id.ivBack);
        rvCollectionReport = findViewById(R.id.rvCollectionReport);
        agentcodeValue = findViewById(R.id.agentcodeValue);
        agentCode = findViewById(R.id.agentCode);
        cvSubmit = findViewById(R.id.cvSubmit);
        totalFrmamt = findViewById(R.id.totalFrmamt);
        totalFrmcomm = findViewById(R.id.totalFrmcomm);
        pb = findViewById(R.id.pb);
        UserDetailsResponse userDetailsResponse = new Gson().fromJson(SharedPref.getInstance(this).getData(USER_DETAILS), UserDetailsResponse.class);
        String roleId = userDetailsResponse.getRoleId();
        /*if (roleId.equals("3")) {
            agentcodeValue.setText(SharedPref.getInstance(this).getData(AGENT_ID));
            agentcodeValue.setClickable(false);
            agentcodeValue.setEnabled(false);
        } else {
            agentcodeValue.setClickable(true);
            agentcodeValue.setEnabled(true);
        }*/
        agentcodeValue.setText(SharedPref.getInstance(this).getData(AGENT_ID));
        rvCollectionReport.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}