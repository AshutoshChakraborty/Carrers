package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.project.integratedservices.R;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.supportClasses.SharedPref;

import static com.project.supportClasses.SharedPref.USER_DETAILS;

public class MisReportActivity extends AppCompatActivity {

    private ImageView ivBack;
    private MaterialCardView cvBranchWiseBusiness, cvIndividualBusiness, cvBusinessSummary, cvCollectionRegister, cvSuggestion, cvVoucher,cv_joining,cvAgent,cv_Commission,cvNoWisePay;
    private UserDetailsResponse userDetails;
    private AppCompatTextView tvBusinessSummary,tvCollectionRegister,tvSuggestion,tvVoucher,tvBranchWiseBussiness,tvJoining;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_report);


        init();
        handleClicks();

    }

    private void init() {
        ivBack = findViewById(R.id.ivBack);
        cvBranchWiseBusiness = findViewById(R.id.cvBranchWiseBusiness);
        cvIndividualBusiness = findViewById(R.id.cvIndividualBusiness);
        cvBusinessSummary = findViewById(R.id.cvBusinessSummary);
        cvCollectionRegister = findViewById(R.id.cvCollectionRegister);
        cvSuggestion = findViewById(R.id.cvSuggestion);
        cvVoucher = findViewById(R.id.cvVoucher);
        tvBusinessSummary = findViewById(R.id.tvBusinessSummary);
        tvBranchWiseBussiness = findViewById(R.id.tvBranchWiseBusiness);
        tvCollectionRegister = findViewById(R.id.tvCollectionRegister);
        tvSuggestion = findViewById(R.id.tvSuggestion);
        tvVoucher = findViewById(R.id.tvVoucher);
        cv_joining = findViewById(R.id.cv_joining);
        tvJoining = findViewById(R.id.tvJoining);
        cvAgent = findViewById(R.id.cvAgent);
        cv_Commission = findViewById(R.id.cv_Commission);
        tvBusinessSummary = findViewById(R.id.tvBusinessSummary);
        cvNoWisePay = findViewById(R.id.cvNoWisePay);

        userDetails = new Gson().fromJson(SharedPref.getInstance(this).getData(USER_DETAILS), UserDetailsResponse.class);


            cvBranchWiseBusiness.setClickable(false);
        cvBranchWiseBusiness.setEnabled(false);
        tvBranchWiseBussiness.setTextColor(getResources().getColor(R.color.grey));
        cvBranchWiseBusiness.setCardElevation(1f);
        cvBranchWiseBusiness.setAlpha(.5f);

        cv_joining.setClickable(false);
        cv_joining.setEnabled(false);
        tvJoining.setTextColor(getResources().getColor(R.color.grey));
        cv_joining.setCardElevation(1f);
        cv_joining.setAlpha(.5f);


        if(userDetails.getRoleId().equals("1"))
        {

            cvSuggestion.setClickable(false);
            cvSuggestion.setEnabled(false);
            tvSuggestion.setTextColor(getResources().getColor(R.color.grey));
            cvSuggestion.setCardElevation(1f);
            cvSuggestion.setAlpha(.5f);


            cvVoucher.setClickable(false);
            cvVoucher.setEnabled(false);
            tvVoucher.setTextColor(getResources().getColor(R.color.grey));
            cvVoucher.setCardElevation(1f);
            cvVoucher.setAlpha(.5f);

        }
        else if(userDetails.getRoleId().equals("3"))
        {
            cvBusinessSummary.setClickable(false);
            cvBusinessSummary.setEnabled(false);
            tvBusinessSummary.setTextColor(getResources().getColor(R.color.grey));
            cvBusinessSummary.setCardElevation(1f);
            cvBusinessSummary.setAlpha(.5f);


            cvCollectionRegister.setClickable(false);
            cvCollectionRegister.setEnabled(false);
            tvCollectionRegister.setTextColor(getResources().getColor(R.color.grey));
            cvCollectionRegister.setCardElevation(1f);
            cvCollectionRegister.setAlpha(.5f);

        }




    }

    private void handleClicks() {
        ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
        cvBranchWiseBusiness.setOnClickListener(v -> {
            startActivity(new Intent(this,BranchWiseBusinessActivity.class));
        });
        cvIndividualBusiness.setOnClickListener(v -> {
            startActivity(new Intent(this,IndividualBusinessReportActivity.class));
        });
        cvBusinessSummary.setOnClickListener(v -> {
            startActivity(new Intent(this,BusinessSummaryActivity.class));
        });
        cvCollectionRegister.setOnClickListener(v -> {
            startActivity(new Intent(this,CollectionReportActivity.class));
        });
        cvSuggestion.setOnClickListener(v -> {
            startActivity(new Intent(this, SuggestionOrComplainsActivity.class));
        });
        cvVoucher.setOnClickListener(v -> {
            startActivity(new Intent(this, VoucherActivity.class));
        });
        cv_joining.setOnClickListener(v -> {
            startActivity(new Intent(this, BranchWiseJoiningActivity.class));
        });
        cvAgent.setOnClickListener(v -> {
            startActivity(new Intent(this, AgentDetailsActivity.class));
        });
        cv_Commission.setOnClickListener(v -> {
            startActivity(new Intent(this, AgentCommissionDetailsActivity.class));
        });
        cvNoWisePay.setOnClickListener(v -> {
            startActivity(new Intent(this, ApplicationNoWisePaymentDetailsActivity.class));
        });
    }


}