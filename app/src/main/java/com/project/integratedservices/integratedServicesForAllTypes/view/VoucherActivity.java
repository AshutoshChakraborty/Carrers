package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
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
import com.google.gson.Gson;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PhaseDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.StatementDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint1Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint2Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint3Response;
import com.project.supportClasses.CustomSpinner;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.USER_DETAILS;

public class VoucherActivity extends AppCompatActivity implements SpinnerAdapterPhaseDetails.OnItemSelectListener, SpinnerAdapterStatementDetails.OnItemSelectListener {
    private ImageView ivBack;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private ProgressBar pb;
    private SpinnerAdapterPhaseDetails spinnerAdapterPhaseDetails;
    private SpinnerAdapterStatementDetails spinnerAdapterStatementDetails;
    private CustomSpinner spStatement, spPhaseId;
    private AppCompatTextView tvStatement, tvPhaseId;
    private String phaseId = "";
    private UserDetailsResponse userDetails;
    private PhaseDetailsResponse selectedPhase = null;
    private StatementDetailsResponse selectedStatement = null;
    private MaterialCardView cvView;
    private HorizontalScrollView svHorizontal;
    private AppCompatEditText editEnterCode;
    //    voucherPrint1
    private TextView tvStatementForTheMonth, tvBranch, tvCode, tvGrade, tvName, tvPan, tvPanEntryDate, tvTotalCollection,tvTotalCollectionTillDate,tvLifeHelDupAmount;

    //    voucherPrint2
//    private TextView tvCID, tvBranchCode, tvcdapl, tvdate, tvScheme, tvCollection, tvPercentage, tvComm, tvSpot, tvadjust, tvRtype, tvInvestorJunior;

    //    voucherPrint3
    private TextView tvCID2, tvGross, tvSpot2, tvtdsHeldup, tvTdsDeduct, tvTdsNopan, tvTdsCleaning, tvNegBalance, tvFinalComm;

    private RecyclerView rvVoucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        init();
        handleClicks();
        handleViewModelStuff();
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectPhase();
    }

    private void handleViewModelStuff() {

        integratedServicesViewModel.getPhaseDetailsResponseLiveData().observe(this, phaseDetailsResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (phaseDetailsResponses.size() > 0) {
                spinnerAdapterPhaseDetails = new SpinnerAdapterPhaseDetails(this, phaseDetailsResponses, this);
                spPhaseId.setAdapter(spinnerAdapterPhaseDetails);
                spPhaseId.setDropDownWidth(900);
            }
        });

        integratedServicesViewModel.getStatementDetailsResponseLiveData().observe(this, statementDetailsResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (statementDetailsResponses.size() > 0) {
                spinnerAdapterStatementDetails = new SpinnerAdapterStatementDetails(this, statementDetailsResponses, this);
                spStatement.setAdapter(spinnerAdapterStatementDetails);
                spStatement.setDropDownWidth(900);
            }
        });

        integratedServicesViewModel.getVoucherPrint1ResponseLiveData().observe(this, voucherPrint1Responses -> {
            callSecondApi();

            if (voucherPrint1Responses.size() > 0) {
                populateDataInView(voucherPrint1Responses.get(0));
            }

        });

        integratedServicesViewModel.getVoucherPrint2ResponseLiveData().observe(this, voucherPrint2Responses -> {
            callThirdApi();
            if (voucherPrint2Responses.size() > 0) {
                rvVoucher.setAdapter(new VoucherAdapter(this,voucherPrint2Responses));
//                populateDataInView(voucherPrint2Responses.get(0));
            }

        });

        integratedServicesViewModel.getVoucherPrint3ResponseLiveData().observe(this, voucherPrint3Responses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (voucherPrint3Responses.size() > 0) {
                populateDataInView(voucherPrint3Responses.get(0));
            }

        });



        integratedServicesViewModel.getApiError().observe(this, s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText(s);
            colorDialog.setCancelable(true);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        });
    }

    private void populateDataInView(VoucherPrint3Response voucherPrint3Response) {
        tvCID2.setText(voucherPrint3Response.getID() + "");
        tvGross.setText(voucherPrint3Response.getGROSS());
        tvSpot2.setText(voucherPrint3Response.getSPOT1());
        tvtdsHeldup.setText(voucherPrint3Response.getTDSHELDUP());
        tvTdsDeduct.setText(voucherPrint3Response.getTDSDEDUCT());
        tvTdsNopan.setText(voucherPrint3Response.getTDSNOPAN());
        tvTdsCleaning.setText(voucherPrint3Response.getTDSCLEARING());
        tvNegBalance.setText(voucherPrint3Response.getNEGBALANCE());
        tvFinalComm.setText(voucherPrint3Response.getFINALCOMM());
    }

    private void callThirdApi() {
        String agentCodeValue =editEnterCode.getText().toString();
        String agentCode = "0";
        if (!agentCodeValue.equalsIgnoreCase("")) {
            agentCode = agentCodeValue;
        }
        integratedServicesViewModel.getVoucherPrint3(selectedStatement.getID().toString(),agentCode,SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
//        integratedServicesViewModel.getVoucherPrint3("14", "8000020"); // for testing
    }

    private void populateDataInView(VoucherPrint2Response voucherPrint2Response) {
//        tvCID.setText("" + voucherPrint2Response.getCID());
//        tvBranchCode.setText(voucherPrint2Response.getBRANCH());
//        tvcdapl.setText(voucherPrint2Response.getCDAPL());
//        tvdate.setText(voucherPrint2Response.getSDATE());
//        tvScheme.setText(voucherPrint2Response.getSCHEME());
//        tvCollection.setText(voucherPrint2Response.getCOLLECTION());
//        tvPercentage.setText(voucherPrint2Response.getPERCENTAGE());
//        tvComm.setText(voucherPrint2Response.getCOMM());
//        tvSpot.setText(voucherPrint2Response.getSPOT());
//        tvadjust.setText(voucherPrint2Response.getADJUST());
//        tvRtype.setText(voucherPrint2Response.getRTYPE());
//        tvInvestorJunior.setText(voucherPrint2Response.getINVESTORJUNIOR());
    }

    private void callSecondApi() {
        String agentCodeValue =editEnterCode.getText().toString();
        String agentCode = "0";
        if (!agentCodeValue.equalsIgnoreCase("")) {
            agentCode = agentCodeValue;
        }
        integratedServicesViewModel.getVoucherPrint2(selectedStatement.getID().toString(),agentCode,SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
//        integratedServicesViewModel.getVoucherPrint2("14", "8000020"); // for testing
    }

    private void populateDataInView(VoucherPrint1Response voucherPrint1Response) {
        svHorizontal.setVisibility(View.VISIBLE);
        tvStatementForTheMonth.setText(voucherPrint1Response.getDESCRIPTION());
        tvBranch.setText(voucherPrint1Response.getBRANCH1());
        tvCode.setText("" + voucherPrint1Response.getCODE());
        tvGrade.setText("" + voucherPrint1Response.getRANK());
        tvName.setText("" + voucherPrint1Response.getNAME());
        tvPan.setText(voucherPrint1Response.getPAN());
        tvPanEntryDate.setText(Misc.getFormattedDate(voucherPrint1Response.getPanEntryDate()));
        tvTotalCollection.setText("" + voucherPrint1Response.getTOTALCOLLECTION());
        tvTotalCollectionTillDate.setText("" + voucherPrint1Response.getTOTALTILLDATE());
        tvLifeHelDupAmount.setText("" + voucherPrint1Response.getLIFEHELDUPAMOUNT());
    }

    private void handleClicks() {
        ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

        tvPhaseId.setOnClickListener(v -> {
            spPhaseId.performClick();
        });

        tvStatement.setOnClickListener(v -> {
            spStatement.performClick();
        });

        cvView.setOnClickListener(v -> {
//            if (selectedPhase != null && selectedStatement != null) {
                callFirstApi();
//            }
        });
    }

    private void callFirstApi() {
        if (Misc.isNetworkAvailable(this)) {
            Misc.disableScreenTouch(this);
            pb.setVisibility(View.VISIBLE);
            String agentCodeValue =editEnterCode.getText().toString();
            String agentCode = "0";
            if (!agentCodeValue.equalsIgnoreCase("")) {
                agentCode = agentCodeValue;
            }

            integratedServicesViewModel.getVoucherPrint1(selectedStatement.getID().toString(),agentCode,SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
//            integratedServicesViewModel.getVoucherPrint1("14", "8000020"); // for testing
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                ColorDialog.dismiss();
                callFirstApi();
            });
            colorDialog.setNegativeListener("CANCEL", dialog -> {
                dialog.dismiss();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }

    private void init() {
        userDetails = new Gson().fromJson(SharedPref.getInstance(this).getData(USER_DETAILS), UserDetailsResponse.class);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack = findViewById(R.id.ivBack);
        pb = findViewById(R.id.pb);
        spStatement = findViewById(R.id.spStatement);
        spPhaseId = findViewById(R.id.spPhaseId);
        tvStatement = findViewById(R.id.tvStatement);
        tvPhaseId = findViewById(R.id.tvPhaseId);
        cvView = findViewById(R.id.cvView);
        svHorizontal = findViewById(R.id.svHorizontal);
        rvVoucher = findViewById(R.id.rvVoucher);
        rvVoucher.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        //    voucherPrint1
        tvStatementForTheMonth = findViewById(R.id.tvStatementForTheMonth);
        tvBranch = findViewById(R.id.tvBranch);
        tvCode = findViewById(R.id.tvCode);
        tvGrade = findViewById(R.id.tvGrade);
        tvName = findViewById(R.id.tvName);
        tvPan = findViewById(R.id.tvPan);
        tvPanEntryDate = findViewById(R.id.tvPanEntryDate);
        tvTotalCollection = findViewById(R.id.tvTotalCollection);
        tvTotalCollectionTillDate = findViewById(R.id.tvTotalCollectionTillDate);
        tvLifeHelDupAmount = findViewById(R.id.tvLifeHelDupAmount);
        editEnterCode = findViewById(R.id.editEnterCode);

        //    voucherPrint2
//        tvCID = findViewById(R.id.tvCID);
//        tvBranchCode = findViewById(R.id.tvBranchCode);
//        tvcdapl = findViewById(R.id.tvcdapl);
//        tvdate = findViewById(R.id.tvdate);
//        tvScheme = findViewById(R.id.tvScheme);
//        tvCollection = findViewById(R.id.tvCollection);
//        tvPercentage = findViewById(R.id.tvPercentage);
//        tvComm = findViewById(R.id.tvComm);
//        tvSpot = findViewById(R.id.tvSpot);
//        tvadjust = findViewById(R.id.tvadjust);
//        tvRtype = findViewById(R.id.tvRtype);
//        tvInvestorJunior = findViewById(R.id.tvInvestorJunior);

        //    voucherPrint3
        tvCID2 = findViewById(R.id.tvCID2);
        tvGross = findViewById(R.id.tvGross);
        tvSpot2 = findViewById(R.id.tvSpot2);
        tvtdsHeldup = findViewById(R.id.tvtdsHeldup);
        tvTdsDeduct = findViewById(R.id.tvTdsDeduct);
        tvTdsNopan = findViewById(R.id.tvTdsNopan);
        tvTdsCleaning = findViewById(R.id.tvTdsCleaning);
        tvNegBalance = findViewById(R.id.tvNegBalance);
        tvFinalComm = findViewById(R.id.tvFinalComm);


        callPhaseDetailsApi();
    }

    private void callPhaseDetailsApi() {
        if (Misc.isNetworkAvailable(this)) {
            Misc.disableScreenTouch(this);
            pb.setVisibility(View.VISIBLE);
            integratedServicesViewModel.getPhaseDetails();
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                ColorDialog.dismiss();
                callPhaseDetailsApi();
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

    private void callStatementDetailsApi(String phaseId) {
        String roleId = userDetails.getRoleId();
        if (Misc.isNetworkAvailable(this)) {
            Misc.disableScreenTouch(this);
            pb.setVisibility(View.VISIBLE);
            integratedServicesViewModel.getStatementDetails(phaseId, roleId);
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                ColorDialog.dismiss();
                callPhaseDetailsApi();
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

    @Override
    public <E> void selectedPhase(E selectedItem) {
        spPhaseId.onDetachedFromWindow();
        tvPhaseId.setText(((PhaseDetailsResponse) selectedItem).getPhaseName());
        phaseId = ((PhaseDetailsResponse) selectedItem).getPhaseId();
        selectedPhase = ((PhaseDetailsResponse) selectedItem);
        callStatementDetailsApi(phaseId);
    }

    public void selectPhase() {
//        callStatementDetailsApi("11");
    }

    @Override
    public <E> void selectedStatement(E selectedItem) {
        spStatement.onDetachedFromWindow();
        tvStatement.setText(((StatementDetailsResponse) selectedItem).getDESCRIPTION());
        selectedStatement = ((StatementDetailsResponse) selectedItem);


    }
}