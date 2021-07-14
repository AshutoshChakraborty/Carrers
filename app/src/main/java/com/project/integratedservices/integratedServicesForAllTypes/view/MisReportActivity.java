package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.project.agent_details.AgentDetails;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.menu_status_response.MenueStatusResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import java.util.List;

import static com.project.supportClasses.SharedPref.USER_DETAILS;

public class MisReportActivity extends AppCompatActivity {
    private IntegratedServicesViewModel integratedServicesViewModel;
    private ImageView ivBack;
    private MaterialCardView cvBranchWiseBusiness, cvIndividualBusiness, cvBusinessSummary, cvCollectionRegister, cvSuggestion, cvVoucher, cv_joining, cvAgent, cv_Commission, cvNoWisePay, cvAgentDetails;
    private UserDetailsResponse userDetails;
    private AppCompatTextView tvBusinessSummary, tvCollectionRegister, tvSuggestion, tvVoucher, tvBranchWiseBussiness, tvJoining;
    private TextView tvIndividualBusiness;
    private TextView tvAgent;
    private TextView tvCommission;
    private TextView tvNoWisePay;
    private TextView tvAgentDetails;
    private SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_report);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);


        init();
        handleClicks();

    }

    private void init() {
        ivBack = findViewById(R.id.ivBack);
        cvBranchWiseBusiness = findViewById(R.id.cvBranchWiseBusiness);
        cvIndividualBusiness = findViewById(R.id.cvIndividualBusiness);
        tvIndividualBusiness = findViewById(R.id.tvIndividualBusiness);
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
        tvAgent = findViewById(R.id.tvAgent);
        cv_Commission = findViewById(R.id.cv_Commission);
        tvCommission = findViewById(R.id.tvCommission);
        tvBusinessSummary = findViewById(R.id.tvBusinessSummary);
        cvNoWisePay = findViewById(R.id.cvNoWisePay);
        tvNoWisePay = findViewById(R.id.tvNoWisePay);
        cvAgentDetails = findViewById(R.id.cv_agent_details);
        tvAgentDetails = findViewById(R.id.tvAgentDetails);
        spinKitView = findViewById(R.id.spin_kit);

        userDetails = new Gson().fromJson(SharedPref.getInstance(this).getData(USER_DETAILS), UserDetailsResponse.class);


/*        cvBranchWiseBusiness.setClickable(false);
        cvBranchWiseBusiness.setEnabled(false);
        tvBranchWiseBussiness.setTextColor(getResources().getColor(R.color.grey));
        cvBranchWiseBusiness.setCardElevation(1f);
        cvBranchWiseBusiness.setAlpha(.5f);

        cv_joining.setClickable(false);
        cv_joining.setEnabled(false);
        tvJoining.setTextColor(getResources().getColor(R.color.grey));
        cv_joining.setCardElevation(1f);
        cv_joining.setAlpha(.5f);*/


        obsevMenueStatus();
        getMenuEnableOrDisable();


    /*    if (userDetails.getRoleId().equals("1")) {

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

        } else if (userDetails.getRoleId().equals("3")) {
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

        }*/


    }

    private void handleClicks() {
        ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
        cvBranchWiseBusiness.setOnClickListener(v -> {
            startActivity(new Intent(this, BranchWiseBusinessActivity.class));
        });
        cvIndividualBusiness.setOnClickListener(v -> {
            startActivity(new Intent(this, IndividualBusinessReportActivity.class));
        });
        cvBusinessSummary.setOnClickListener(v -> {
            startActivity(new Intent(this, BusinessSummaryActivity.class));
        });
        cvCollectionRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, CollectionReportActivity.class));
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
        cvAgentDetails.setOnClickListener(v -> {
            startActivity(new Intent(this, AgentDetails.class));
        });
    }

    private void obsevMenueStatus() {
        integratedServicesViewModel.getMenueStatusLiveData().observe(this, menueStatusResponses -> {
            spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (menueStatusResponses != null) {
                if (!menueStatusResponses.isEmpty()) {
                    checkAndSetStatus(cvBranchWiseBusiness, tvBranchWiseBussiness, menueStatusResponses);
                    checkAndSetStatus(cvIndividualBusiness, tvIndividualBusiness, menueStatusResponses);
                    checkAndSetStatus(cvBusinessSummary, tvBusinessSummary, menueStatusResponses);
                    checkAndSetStatus(cvCollectionRegister, tvCollectionRegister, menueStatusResponses);
                    checkAndSetStatus(cvSuggestion, tvSuggestion, menueStatusResponses);
                    checkAndSetStatus(cvVoucher, tvVoucher, menueStatusResponses);
                    checkAndSetStatus(cv_joining, tvJoining, menueStatusResponses);
                    checkAndSetStatus(cvAgent, tvAgent, menueStatusResponses);
                    checkAndSetStatus(cv_Commission, tvCommission, menueStatusResponses);
                    checkAndSetStatus(cvNoWisePay, tvNoWisePay, menueStatusResponses);
                    checkAndSetStatus(cvAgentDetails, tvAgentDetails, menueStatusResponses);
                }
            } else {

            }
        });
    }


    private void getMenuEnableOrDisable() {
        Misc.disableScreenTouch(this);
        UserDetailsResponse userDetailsResponse = new Gson().fromJson(SharedPref.getInstance(this).getData(USER_DETAILS), UserDetailsResponse.class);
        String roleId = userDetailsResponse.getRoleId();
        spinKitView.setVisibility(View.VISIBLE);
        integratedServicesViewModel.getMenueStatus(roleId);

    }

    private void disableMenue(MaterialCardView attendance, TextView tvAttendance) {
        attendance.setClickable(false);
        attendance.setEnabled(false);
        tvAttendance.setTextColor(getResources().getColor(R.color.grey));
        attendance.setCardElevation(1f);
        attendance.setAlpha(.5f);
        attendance.setCardBackgroundColor(ContextCompat.getColor(this, R.color.offgrey));

    }

    private void EnableMenue(MaterialCardView attendance, TextView tvAttendance) {
        attendance.setClickable(true);
        attendance.setEnabled(true);
        tvAttendance.setTextColor(getResources().getColor(R.color.textBlue));
        if (this != null) {
            attendance.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    private void checkAndSetStatus(MaterialCardView attendance, TextView tvAttendance, List<MenueStatusResponse> menueStatusResponses) {
        for (MenueStatusResponse menueStatusRespons : menueStatusResponses) {
            if (menueStatusRespons.getMenuName().equalsIgnoreCase(tvAttendance.getText().toString())) {
                String status = menueStatusRespons.getViewType();
                if (status.equalsIgnoreCase("False")) {
                    disableMenue(attendance, tvAttendance);
                } else {
                    EnableMenue(attendance, tvAttendance);
                }
            }
        }
    }


}