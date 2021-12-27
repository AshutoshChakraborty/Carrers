package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.os.Bundle;
import android.util.Log;
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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint4Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint5Response;
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
    private HorizontalScrollView svHorizontal1;
    private AppCompatEditText editEnterCode;
    //    voucherPrint1
    private TextView tvStatementForTheMonth, tvBranch, tvCode, tvGrade, tvName, tvPan, tvPanEntryDate, tvTotalCollection,tvTotalCollectionTillDate,tvLifeHelDupAmount,tvChainDetails,tvTotalCollectionTillDate1;

    //    voucherPrint2
//    private TextView tvCID, tvBranchCode, tvcdapl, tvdate, tvScheme, tvCollection, tvPercentage, tvComm, tvSpot, tvadjust, tvRtype, tvInvestorJunior;

    //    voucherPrint3
    private TextView tvCID2,consolidate, tvGross, tvSpot2, tvtdsHeldup, tvTdsDeduct, tvTdsNopan, tvTdsCleaning, tvNegBalance, tvFinalComm;

    private RecyclerView rvVoucher,rvVoucher1;
    private String currrentDate;

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


            if(voucherPrint1Responses.size() > 0) {
                if (!(voucherPrint1Responses.get(0).getStatus0().equalsIgnoreCase("Success") || voucherPrint1Responses.get(0).getStatus0().equalsIgnoreCase("UnSuccess"))) {
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText(voucherPrint1Responses.get(0).getStatus0());
                    colorDialog.setCancelable(true);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();
                    svHorizontal.setVisibility(View.GONE);
                    Log.d("Voucher", "Gone");
                } else if (voucherPrint1Responses.get(0).getStatus0().equalsIgnoreCase("Success")) {
                    populateDataInView(voucherPrint1Responses.get(0));
                    svHorizontal.setVisibility(View.VISIBLE);
                    Log.d("Voucher", "Success");
                } else if (voucherPrint1Responses.get(0).getStatus0().equalsIgnoreCase("UnSuccess")) {
                    svHorizontal.setVisibility(View.GONE);
                    Log.d("Voucher", "UnSuccess");
                }
                currrentDate = voucherPrint1Responses.get(0).getCurrrentDate();
                callFourthApi();
                callFifthApi();
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

        integratedServicesViewModel.getVoucherPrint4ResponseLiveData().observe(this, voucherPrint4Responses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if(voucherPrint4Responses.size() > 0) {
                if (!(voucherPrint4Responses.get(0).getStatus().equalsIgnoreCase("Success") || voucherPrint4Responses.get(0).getStatus().equalsIgnoreCase("UnSuccess"))) {
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText(voucherPrint4Responses.get(0).getStatus());
                    colorDialog.setCancelable(true);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();
                    svHorizontal1.setVisibility(View.GONE);
                    Log.d("Voucher", "Gone");
                } else if (voucherPrint4Responses.get(0).getStatus().equalsIgnoreCase("Success")) {
//                    populateDataInView(voucherPrint4Responses.get(0));
                    svHorizontal1.setVisibility(View.VISIBLE);
                    consolidate.setText(currrentDate);
                    rvVoucher1.setAdapter(new VoucherFourAdapter(this,voucherPrint4Responses));
                    int count1 = 0;
                    int count2 = 0;
                    for (VoucherPrint4Response voucherPrint4Respons : voucherPrint4Responses) {
                        count1 = count1 + voucherPrint4Respons.getTotalTillDate();
                        count2 = count2 + voucherPrint4Respons.getLifeHeldupAmt();
                    }
                    ((TextView)findViewById(R.id.tvTotalCollection1)).setText(String.valueOf(count1));
                    ((TextView)findViewById(R.id.tvTotalCollection2)).setText(String.valueOf(count2));
                    Log.d("Voucher", "Success");
                } else if (voucherPrint4Responses.get(0).getStatus().equalsIgnoreCase("UnSuccess")) {
                    svHorizontal1.setVisibility(View.GONE);
                    Log.d("Voucher", "UnSuccess");
                }
            }

        });

        integratedServicesViewModel.getVoucherPrint5ResponseLiveData().observe(this, voucherPrint5Responses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if(voucherPrint5Responses.size() > 0) {
                if (!(voucherPrint5Responses.get(0).getStatus().equalsIgnoreCase("Success") || voucherPrint5Responses.get(0).getStatus().equalsIgnoreCase("UnSuccess"))) {
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText(voucherPrint5Responses.get(0).getStatus());
                    colorDialog.setCancelable(true);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();
//                    svHorizontal.setVisibility(View.GONE);
                    Log.d("Voucher", "Gone");
                } else if (voucherPrint5Responses.get(0).getStatus().equalsIgnoreCase("Success")) {
//                    populateDataInView(voucherPrint5Responses.get(0));
                    setData(voucherPrint5Responses.get(0));
//                    svHorizontal.setVisibility(View.VISIBLE);
                    Log.d("Voucher", "Success");
                } else if (voucherPrint5Responses.get(0).getStatus().equalsIgnoreCase("UnSuccess")) {
//                    svHorizontal.setVisibility(View.GONE);
                    Log.d("Voucher", "UnSuccess");
                }
            }

        });




        integratedServicesViewModel.getApiError().observe(this, s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            Log.d("Voucher", "error");
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText(s);
            colorDialog.setCancelable(true);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        });
    }

    private void setData(VoucherPrint5Response response) {
        ((TextView)findViewById(R.id.sur)).setText(response.getSur().toString());
        ((TextView)findViewById(R.id.tma1)).setText(response.getTmaI().toString());
        ((TextView)findViewById(R.id.tma2)).setText(response.getTmaIi().toString());
        ((TextView)findViewById(R.id.jma)).setText(response.getJma().toString());
        ((TextView)findViewById(R.id.jma2)).setText(response.getJma2().toString());
        ((TextView)findViewById(R.id.dma)).setText(response.getDma().toString());
        ((TextView)findViewById(R.id.ma)).setText(response.getMa().toString());
        ((TextView)findViewById(R.id.sma)).setText(response.getSma().toString());
        ((TextView)findViewById(R.id.amo)).setText(response.getAmo().toString());
        ((TextView)findViewById(R.id.mo)).setText(response.getMo().toString());
        ((TextView)findViewById(R.id.smo)).setText(response.getSmo().toString());
        ((TextView)findViewById(R.id.pmo)).setText(response.getPmo().toString());
        ((TextView)findViewById(R.id.jrm)).setText(response.getJrm().toString());
        ((TextView)findViewById(R.id.arm)).setText(response.getArm().toString());
        ((TextView)findViewById(R.id.rm)).setText(response.getRm().toString());
        ((TextView)findViewById(R.id.srm)).setText(response.getSrm().toString());
        ((TextView)findViewById(R.id.crm)).setText(response.getCrm().toString());
    }


    private void callFourthApi() {
        // TODO: 10/21/2021
        String agentCodeValue =editEnterCode.getText().toString();
        String agentCode = "0";
        if (!agentCodeValue.equalsIgnoreCase("")) {
            agentCode = agentCodeValue;
        }
        integratedServicesViewModel.getVoucherPrint4(selectedStatement.getID().toString(),agentCode,SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
    }
    private void callFifthApi() {
        // TODO: 10/21/2021
        String agentCodeValue =editEnterCode.getText().toString();
        String agentCode = "0";
        if (!agentCodeValue.equalsIgnoreCase("")) {
            agentCode = agentCodeValue;
        }
        integratedServicesViewModel.getVoucherPrint5(selectedStatement.getID().toString(),agentCode,SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
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
        if(voucherPrint1Response.getDESCRIPTION().equalsIgnoreCase("Referee does not belongs to your group")) {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText(voucherPrint1Response.getDESCRIPTION());
            colorDialog.setCancelable(true);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
        if(voucherPrint1Response.getDESCRIPTION() != null) {
            tvStatementForTheMonth.setText(voucherPrint1Response.getDESCRIPTION());
        }
        tvBranch.setText(voucherPrint1Response.getBRANCH1());
        tvChainDetails.setText(voucherPrint1Response.getChainDetails());
        tvTotalCollectionTillDate1.setText(voucherPrint1Response.getTOTALTILLDATE());
        tvCode.setText("" + voucherPrint1Response.getCODE());
        tvGrade.setText("" + voucherPrint1Response.getRANK());
        tvName.setText("" + voucherPrint1Response.getNAME());
        tvPan.setText(voucherPrint1Response.getPAN());
        tvPanEntryDate.setText(Misc.getFormattedDate(voucherPrint1Response.getPanEntryDate()));
        tvTotalCollection.setText("" + voucherPrint1Response.getTOTALCOLLECTION());
        tvTotalCollectionTillDate.setText("" + voucherPrint1Response.getUptoDate());
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

        integratedServicesViewModel.getApiError().observe(this,s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (!(s.equalsIgnoreCase("Success") || s.equalsIgnoreCase("UnSuccess"))) {
                ColorDialog colorDialog = MyColorDialog.getInstance(this);
                colorDialog.setContentText(s);
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();
            } else if (s.equalsIgnoreCase("UnSuccess")) {
                svHorizontal.setVisibility(View.GONE);
            }
        });
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
        svHorizontal1 = findViewById(R.id.svHorizontal1);
        rvVoucher = findViewById(R.id.rvVoucher);
        rvVoucher1 = findViewById(R.id.rvVoucher1);
        consolidate = findViewById(R.id.consolidate);
        rvVoucher.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rvVoucher1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
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
        tvChainDetails = findViewById(R.id.tv_chain_details);
        tvTotalCollectionTillDate1 = findViewById(R.id.tvTotalCollectionTillDate1);
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