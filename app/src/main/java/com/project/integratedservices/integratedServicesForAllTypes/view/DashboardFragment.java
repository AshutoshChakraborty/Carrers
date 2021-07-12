package com.project.integratedservices.integratedServicesForAllTypes.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.authenticate.viewmodel.AuthenticationViewModel;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import cn.refactor.lib.colordialog.ColorDialog;
import cn.refactor.lib.colordialog.PromptDialog;

import static com.project.supportClasses.Constants.endAttendanceGiven;
import static com.project.supportClasses.Constants.isSalaried;
import static com.project.supportClasses.Constants.startAttendanceGiven;
import static com.project.supportClasses.SharedPref.AGENT_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    private MaterialCardView cvCustomer, availablePlans, attendance,cvTeam,cvBusinessReport,cvLeave,cvMISReport,agentJoiningCard,materialCardViewMessage;
    private AuthenticationViewModel authenticationViewModel;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private String agentId = "";
    private LinearLayoutCompat llAgent,llEmployee;
    private AppCompatTextView tvAgentName,tvBranchName,tvIntroducerName,tvAgentCode;
    private TextView tvAgentCode1;
    private TextView tvBranchName1;
    private TextView tvAttendance;
    private ImageView ivAttendance;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(getActivity(), "resume fragment", Toast.LENGTH_SHORT).show();
//        if(!startAttendanceGiven)
//            checkAttendance(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        init(view);

//        if(!((DashboardActivity)getActivity()).agentId.equals(""))
//        {
//            agentId = ((DashboardActivity)getActivity()).agentId;
//            if(Misc.isNetworkAvailable(getActivity())) {
//                callUserDetailsApi(agentId);
//
//            }
//            else
//            {
//                ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
//                colorDialog.setContentText("Please check your Internet connection and retry");
//                colorDialog.setPositiveListener("OK", ColorDialog->{
//                    ColorDialog.dismiss();
//                    getActivity().onBackPressed();
//                });
//                colorDialog.setCancelable(false);
//                colorDialog.setAnimationEnable(true);
//                colorDialog.show();
//            }
//        }


//        authenticationViewModel.getUserDetails().observe(getActivity(),userDetailsResponse -> {
//            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.GONE);
//            Misc.enableScreenTouch(getActivity());
//            fillAgentDetails(userDetailsResponse);
//        });


        if(((DashboardActivity)getActivity()).getUserDetails()!=null)
        {
            fillAgentDetails(((DashboardActivity)getActivity()).getUserDetails());
        }

        authenticationViewModel.attendanceCheckResponse().observe(this,attendanceCheckResponse -> {
            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());

            if(attendanceCheckResponse.getActiveAttendance().equalsIgnoreCase("1"))
                isSalaried = true;
            else
                isSalaried = false;

            if(!attendanceCheckResponse.getStartAttendance().equals("1"))
            {
//                disableOtherButtons();
                startAttendanceGiven = false;
            }
            else {
                startAttendanceGiven = true;
                enableOtherButtons();
            }

            if(attendanceCheckResponse.getEndAttendance().equals("1"))
            {
                endAttendanceGiven = true;
            }
            else
            {
                endAttendanceGiven = false;
            }
        });

        authenticationViewModel.getApiError().observe(getActivity(),s -> {
            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());
            MyColorDialog.getInstance(getActivity()).setContentText(s)
                    .setPositiveListener("RETRY",colorDialog -> {
//                        callUserDetailsApi(agentId);
                        colorDialog.dismiss();
                    })
                    .setNegativeListener("CANCEL", ColorDialog::dismiss)
                    .show();
        });

//        integratedServicesViewModel.getAlertMsgResponseLiveData().observe(getActivity(),alertMessageResponses -> {
//
//            if(!alertMessageResponses.get(0).getLeaveStatus().equals("0"))
//            {
//                ColorDialog dialog = new ColorDialog(getActivity());
//                dialog.setContentText("You cannot use the app when your on leave");
//                dialog.setPositiveListener("OK",colorDialog -> {
//                    colorDialog.dismiss();
//                    SharedPref.getInstance(getActivity()).clearAllPref();
//                    Toast.makeText(getActivity().getApplicationContext(), "logged out", Toast.LENGTH_SHORT).show();
//                    getActivity().finishAffinity();
//                });
//                dialog.setCancelable(false);
//                dialog.show();
//            }
//            else if(!alertMessageResponses.get(0).getSMSTextDisplay().equals("0"))
//            {
//                new PromptDialog(getActivity())
//                        .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
//                        .setAnimationEnable(true)
////                        .setTitleText(getString(R.string.success))
//                        .setContentText(alertMessageResponses.get(0).getSMSTextDisplay())
//                        .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
//                            @Override
//                            public void onClick(PromptDialog dialog) {
//                                dialog.dismiss();
//                            }
//                        }).show();
//            }
//
//        });
//
//
//        integratedServicesViewModel.getApiError().observe(getActivity(),s -> {
//            Toast.makeText(getActivity(), ""+s, Toast.LENGTH_SHORT).show();
//        });

        cvCustomer.setOnClickListener(v -> {
            if(isSalaried)
            {
                if(startAttendanceGiven && endAttendanceGiven)
                {

                }
                else if(startAttendanceGiven) {
                    ((DashboardActivity) getActivity()).loadFragment(new CustomerFragment());
                }
                else
                    Toast.makeText(getActivity(), getActivity().getString(R.string.make_your_attendance), Toast.LENGTH_SHORT).show();
            }
            else
            {
                ((DashboardActivity) getActivity()).loadFragment(new CustomerFragment());
            }


        });

        availablePlans.setOnClickListener(v -> {
            if(isSalaried)
            {
                if(startAttendanceGiven && endAttendanceGiven)
                {

                }
                else if(startAttendanceGiven) {
                    ((DashboardActivity) getActivity()).loadFragment(new AvailablePlansFragment());
                }
                else
                    Toast.makeText(getActivity(), getActivity().getString(R.string.make_your_attendance), Toast.LENGTH_SHORT).show();
            }
            else
            {
                ((DashboardActivity) getActivity()).loadFragment(new AvailablePlansFragment());
            }



        });

        attendance.setOnClickListener(v -> {
            if(isSalaried)
            ((DashboardActivity) getActivity()).loadFragment(new AttendanceFragment());
            else
            {
                Toast.makeText(getActivity(), getActivity().getString(R.string.unauthorised), Toast.LENGTH_SHORT).show();
            }
        });

        cvTeam.setOnClickListener(v -> {
            if(isSalaried)
            {
                if(startAttendanceGiven && endAttendanceGiven)
                {

                }
                else if(startAttendanceGiven) {
//                    ((DashboardActivity) getActivity()).loadFragment(new TeamFragment());
                    startActivity(new Intent(getActivity(),TeamMembersHierarchy.class));
                }
                else
                    Toast.makeText(getActivity(), getActivity().getString(R.string.make_your_attendance), Toast.LENGTH_SHORT).show();
            }
            else
            {
//                ((DashboardActivity) getActivity()).loadFragment(new TeamFragment());
                startActivity(new Intent(getActivity(),TeamMembersHierarchy.class));
            }

        });

        cvBusinessReport.setOnClickListener(v -> {
            if(isSalaried)
            {
                if(startAttendanceGiven && endAttendanceGiven)
                {

                }
                else if(startAttendanceGiven) {
                    ((DashboardActivity) getActivity()).loadFragment(new BusinessReportFragment());
                }
                else
                    Toast.makeText(getActivity(), getActivity().getString(R.string.make_your_attendance), Toast.LENGTH_SHORT).show();
            }
            else
            {
                ((DashboardActivity) getActivity()).loadFragment(new BusinessReportFragment());
            }


        });

        cvLeave.setOnClickListener(v -> {
            if(isSalaried)
            {
                if(startAttendanceGiven && endAttendanceGiven)
                {

                }
                else if(startAttendanceGiven) {
                    startActivity(new Intent(getActivity(),LeaveApplicationActivity.class));
                }
                else
                    Toast.makeText(getActivity(), getActivity().getString(R.string.make_your_attendance), Toast.LENGTH_SHORT).show();
            }
            else
            {
                startActivity(new Intent(getActivity(),LeaveApplicationActivity.class));
            }


        });

        cvMISReport.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),MisReportActivity.class));
        });

        agentJoiningCard.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),NewAgentJoiningActivity.class));

        });
        materialCardViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MessageActivity.class));
            }
        });

        return view;
    }

    private void disableOtherButtons() {
        cvCustomer.setClickable(false);
        availablePlans.setClickable(false);
        cvTeam.setClickable(false);
        cvBusinessReport.setClickable(false);
    }

    private void enableOtherButtons() {
        cvCustomer.setClickable(true);
        availablePlans.setClickable(true);
        cvTeam.setClickable(true);
        cvBusinessReport.setClickable(true);
    }

    private void fillAgentDetails(UserDetailsResponse userDetails) {

//        llAgent.setVisibility(View.VISIBLE);
        tvAgentName.setText(userDetails.getUserName());
        tvBranchName.setText(userDetails.getBranchName());
//        llEmployee.setVisibility(View.VISIBLE);
        tvIntroducerName.setText(userDetails.getIntroName());
        tvAgentCode.setText("Agent Code #"+userDetails.getIntroCode());
        tvAgentCode1.setText("Agent Code #" + SharedPref.getInstance(getActivity()).getData(AGENT_ID));
        tvBranchName1.setText(userDetails.getIntroBranch());

//        if(SharedPref.getInstance(getActivity()).getData(SharedPref.LOGIN_TYPE).equals(Constants.LOGIN_TYPE_AGENT))
//        {
//            llAgent.setVisibility(View.VISIBLE);
//            tvAgentName.setText(userDetails.getUserName());
//            tvBranchName.setText(userDetails.getBranchName());
//        }
//        else
//        {
//            llEmployee.setVisibility(View.VISIBLE);
//            tvIntroducerName.setText(userDetails.getIntroName());
//            tvAgentCode.setText("Agent Code #"+SharedPref.getInstance(getActivity()).getData(AGENT_ID));
//        }

//        if(!startAttendanceGiven)
            checkAttendance(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
//            checkforAlertMsg(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
    }

    private void checkforAlertMsg(String data) {
        if(Misc.isNetworkAvailable(getActivity())) {
            Misc.disableScreenTouch(getActivity());
//            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.VISIBLE);
            integratedServicesViewModel.getAlertMessage(agentId);
        }
        else
        {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog->{
                ColorDialog.dismiss();
                checkforAlertMsg(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
            });
            colorDialog.setNegativeListener("CANCEL", colorDialog1 -> {
                colorDialog1.dismiss();
                getActivity().onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }

//    private void callUserDetailsApi(String agentId) {
////        Misc.disableScreenTouch(getActivity());
////        ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.VISIBLE);
////        authenticationViewModel.callUserDetails(agentId);
////    }

    private void checkAttendance(String agentId) {

        if(Misc.isNetworkAvailable(getActivity())) {
            Misc.disableScreenTouch(getActivity());
            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.VISIBLE);
            authenticationViewModel.checkAttendance(agentId);
        }
        else
        {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog->{
                ColorDialog.dismiss();
                checkAttendance(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
            });
            colorDialog.setNegativeListener("CANCEL", colorDialog1 -> {
                colorDialog1.dismiss();
                getActivity().onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }

    }

    private void init(View view) {
        authenticationViewModel = ViewModelProviders.of(this).get(AuthenticationViewModel.class);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        cvCustomer = view.findViewById(R.id.materialCardView2);
        materialCardViewMessage = view.findViewById(R.id.materialCardViewMessage);
        availablePlans = view.findViewById(R.id.materialCardView4);
        attendance = view.findViewById(R.id.materialCardView3);
        tvAgentName = view.findViewById(R.id.tvAgentName);
        tvBranchName = view.findViewById(R.id.tvBranchName);
        tvIntroducerName = view.findViewById(R.id.tvIntroducerName);
        tvAgentCode = view.findViewById(R.id.tvAgentCode);
        cvTeam = view.findViewById(R.id.materialCardView5);
        cvBusinessReport = view.findViewById(R.id.materialCardView6);
        agentJoiningCard = view.findViewById(R.id.agentJoiningCard);
        llAgent = view.findViewById(R.id.llAgent);
        llEmployee = view.findViewById(R.id.llEmployee);
        cvLeave = view.findViewById(R.id.materialCardView);
        cvMISReport = view.findViewById(R.id.materialCardViewMisReport);
        tvAgentCode1 = view.findViewById(R.id.tvAgentCode1);
        tvBranchName1 = view.findViewById(R.id.tv_branch_name1);
        tvAttendance = view.findViewById(R.id.tvAttendance);
        ivAttendance = view.findViewById(R.id.ivAttendance);


        attendance.setClickable(false);
        attendance.setEnabled(false);
        tvAttendance.setTextColor(getResources().getColor(R.color.grey));
        attendance.setCardElevation(1f);
        ivAttendance.setAlpha(.5f);
        if (getActivity() != null) {
            attendance.setCardBackgroundColor(ContextCompat.getColor(getActivity(),R.color.offgrey));
        }

    }

}
