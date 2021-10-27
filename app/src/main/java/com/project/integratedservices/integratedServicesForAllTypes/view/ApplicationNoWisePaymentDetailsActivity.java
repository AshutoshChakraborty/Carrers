package com.project.integratedservices.integratedServicesForAllTypes.view;

import static com.project.supportClasses.SharedPref.AGENT_ID;

import android.os.Bundle;
import android.util.Log;
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
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.util.Calendar;
import java.util.Date;

import cn.refactor.lib.colordialog.ColorDialog;

public class ApplicationNoWisePaymentDetailsActivity extends AppCompatActivity {
    private ImageView ivBack;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private RecyclerView rvCollectionReport , rvPaymentCollectionReport;
    private EditText agentcodeValue;
    private MaterialCardView cvSubmit;
    private Date start;
    private Calendar calendar;
    private ProgressBar pb;
    private ApplicationNumberWisePaymentAdapter adapter;
    private ApplicationNumberWisePaymentNewAdapter adapterNew;
    private TextView totalFrmamt, totalFrmcomm;
    String agentId = SharedPref.getInstance(this).getData(AGENT_ID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_no_wise_payment_details);
        init();
        handleClicks();
        apiResponses();
    }

    private void apiResponses() {
        integratedServicesViewModel.getApplicationNoWisePaymentLiveData().observe(this, misCollectionRegisterResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if (misCollectionRegisterResponses.size() > 0 && misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success")) {
                adapter = new ApplicationNumberWisePaymentAdapter(this, misCollectionRegisterResponses);
                rvCollectionReport.setAdapter(adapter);
                rvCollectionReport.setVisibility(View.VISIBLE);
            } else if (!(misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("Success") || misCollectionRegisterResponses.get(0).getStatus().equalsIgnoreCase("UnSuccess"))) {
                ColorDialog colorDialog = MyColorDialog.getInstance(this);
                colorDialog.setContentText(misCollectionRegisterResponses.get(0).getStatus());
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();
                Log.i("Response: ",misCollectionRegisterResponses.get(0).getStatus());

                rvCollectionReport.setVisibility(View.INVISIBLE);
                rvPaymentCollectionReport.setVisibility(View.GONE);
            }
        });

        integratedServicesViewModel.getApplicationNoWisePaymentNewLiveData().observe(this, misCollectionRegisterNewResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if (misCollectionRegisterNewResponses.size() > 0 && misCollectionRegisterNewResponses.get(0).getStatus().equalsIgnoreCase("Success")) {
                rvPaymentCollectionReport.setVisibility(View.VISIBLE);
                rvPaymentCollectionReport.setLayoutManager(new LinearLayoutManager(this));
                adapterNew = new ApplicationNumberWisePaymentNewAdapter(this, misCollectionRegisterNewResponses);
                rvPaymentCollectionReport.setAdapter(adapterNew);
                rvPaymentCollectionReport.setVisibility(View.VISIBLE);
                Log.i("Response1: ",misCollectionRegisterNewResponses.get(0).getStatus());
            } else if (!(misCollectionRegisterNewResponses.get(0).getStatus().equalsIgnoreCase("Success") || misCollectionRegisterNewResponses.get(0).getStatus().equalsIgnoreCase("UnSuccess"))) {
                ColorDialog colorDialog = MyColorDialog.getInstance(this);
                colorDialog.setContentText(misCollectionRegisterNewResponses.get(0).getStatus());
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();

                rvPaymentCollectionReport.setVisibility(View.GONE);
            }
        });
        integratedServicesViewModel.getApplicationWisePremiumAmountLiveData().observe(this, misCollectionRegisterResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            totalFrmcomm.setText(misCollectionRegisterResponses.get(0).getPremiumAmt());
            Log.i("Response2: ",misCollectionRegisterResponses.toString());

        });

        integratedServicesViewModel.getApiError().observe(this, s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
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

                    integratedServicesViewModel.getApplicationNoWisePayment(agentcodeValue.getText().toString(),agentId);
                    integratedServicesViewModel.getApplicationNoWisePaymentNew(agentcodeValue.getText().toString(),agentId);
                    integratedServicesViewModel.getApplicationNoWisePremiumAmount(agentcodeValue.getText().toString(),agentId);
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

    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack = findViewById(R.id.ivBack);
        rvCollectionReport = findViewById(R.id.rvCollectionReport);
        rvPaymentCollectionReport = findViewById(R.id.rvPaymentReport);
        agentcodeValue = findViewById(R.id.agentcodeValue);
        cvSubmit = findViewById(R.id.cvSubmit);
        totalFrmamt = findViewById(R.id.totalFrmamt);
        totalFrmcomm = findViewById(R.id.totalFrmcomm);
        pb = findViewById(R.id.pb);

        rvCollectionReport.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvPaymentCollectionReport.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}