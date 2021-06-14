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
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;

import java.util.Calendar;
import java.util.Date;

import cn.refactor.lib.colordialog.ColorDialog;

public class ApplicationNoWisePaymentDetailsActivity extends AppCompatActivity {
    private ImageView ivBack;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private RecyclerView rvCollectionReport;
    private EditText agentcodeValue;
    private MaterialCardView cvSubmit;
    private Date start;
    private Calendar calendar;
    private ProgressBar pb;
    private ApplicationNumberWisePaymentAdapter adapter;
    private TextView totalFrmamt, totalFrmcomm;
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

            if (misCollectionRegisterResponses.size() > 0) {
                adapter = new ApplicationNumberWisePaymentAdapter(this, misCollectionRegisterResponses);
                rvCollectionReport.setAdapter(adapter);
            }
        });
        integratedServicesViewModel.getApplicationWisePremiumAmountLiveData().observe(this, misCollectionRegisterResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            totalFrmcomm.setText(misCollectionRegisterResponses.get(0).getPremiumAmt());

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

                    integratedServicesViewModel.getApplicationNoWisePayment(agentcodeValue.getText().toString());
                    integratedServicesViewModel.getApplicationNoWisePremiumAmount(agentcodeValue.getText().toString());
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
            Toast.makeText(this, "Please Enter Agent Code", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack = findViewById(R.id.ivBack);
        rvCollectionReport = findViewById(R.id.rvCollectionReport);
        agentcodeValue = findViewById(R.id.agentcodeValue);
        cvSubmit = findViewById(R.id.cvSubmit);
        totalFrmamt = findViewById(R.id.totalFrmamt);
        totalFrmcomm = findViewById(R.id.totalFrmcomm);
        pb = findViewById(R.id.pb);

        rvCollectionReport.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}