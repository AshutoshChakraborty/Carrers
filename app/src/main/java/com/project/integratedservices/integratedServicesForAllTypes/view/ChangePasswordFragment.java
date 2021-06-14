package com.project.integratedservices.integratedServicesForAllTypes.view;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.ChangePasswordRequestModel;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import cn.refactor.lib.colordialog.ColorDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {


    private AppCompatButton btn_login;
    private TextInputEditText et_current_password,et_new_password,et_reenter_password;
    private IntegratedServicesViewModel integratedServicesViewModel;
    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.VISIBLE);
        ((DashboardActivity)getActivity()).tvCustomerText.setText(getResources().getString(R.string.change_password));

        
        init(view);
        
        
        btn_login.setOnClickListener(v -> {
            if(isValid())
            {
                callChangePasswordApi();
            }
        });

        integratedServicesViewModel.getChangePasswordResponse().observe(this,changePasswordResponseModels -> {
            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());

            et_current_password.getText().clear();
            et_new_password.getText().clear();
            et_reenter_password.getText().clear();

            et_current_password.clearFocus();
            et_new_password.clearFocus();
            et_reenter_password.clearFocus();

            if(changePasswordResponseModels.get(0).getStatus().equals("Success.."))
            {
                MyColorDialog.getInstance(getActivity())
                        .setContentText("Password Changed Successfully")
                        .setAnimationEnable(true)
                        .setPositiveListener("OK",colorDialog -> {
                            colorDialog.dismiss();
                            getActivity().onBackPressed();
                        })
                        .show();

            }
            else
            {
                Toast.makeText(getActivity(), ""+changePasswordResponseModels.get(0).getStatus(), Toast.LENGTH_LONG).show();
            }
        });

        integratedServicesViewModel.getApiError().observe(getActivity(),s -> {
            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());
            MyColorDialog.getInstance(getActivity()).setContentText(s)
                    .setPositiveListener("RETRY",colorDialog -> {
                        callChangePasswordApi();
                        colorDialog.dismiss();
                    })
                    .setNegativeListener("CANCEL", ColorDialog::dismiss)
                    .show();
        });
        
        return view;
    }

    private void callChangePasswordApi() {

        if(Misc.isNetworkAvailable(getActivity())) {
            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.VISIBLE);
            Misc.disableScreenTouch(getActivity());

            ChangePasswordRequestModel requestModel = new ChangePasswordRequestModel();
            requestModel.setAgentCode(SharedPref.getInstance(getActivity()).getData(SharedPref.AGENT_ID));
            requestModel.setOldPassword(et_current_password.getText().toString().trim());
            requestModel.setNewPassword(et_new_password.getText().toString().trim());

            integratedServicesViewModel.callChangePassword(requestModel);
        }
        else
        {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("OK", ColorDialog->{
                ColorDialog.dismiss();
                getActivity().onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }

    private boolean isValid() {
        boolean isValid = true;
        if(et_current_password.getText().toString().trim().isEmpty())
        {
            isValid = false;
            MyColorDialog.getInstance(getActivity())
                    .setAnimationEnable(true)
                    .setContentText("Please enter your current password")
                    .setPositiveListener("OK", ColorDialog::dismiss)
                    .show();
        }
        else if(et_new_password.getText().toString().trim().isEmpty())
        {
            isValid = false;
            MyColorDialog.getInstance(getActivity())
                    .setAnimationEnable(true)
                    .setContentText("Please enter your new password")
                    .setPositiveListener("OK", ColorDialog::dismiss)
                    .show();
        }
        else if(et_reenter_password.getText().toString().trim().isEmpty())
        {
            isValid = false;
            MyColorDialog.getInstance(getActivity())
                    .setAnimationEnable(true)
                    .setContentText("Please re-enter your new password")
                    .setPositiveListener("OK", ColorDialog::dismiss)
                    .show();
        }
        else if(!et_new_password.getText().toString().trim().equals(et_reenter_password.getText().toString().trim()))
        {
            isValid = false;
            MyColorDialog.getInstance(getActivity())
                    .setAnimationEnable(true)
                    .setContentText("New password doesn't match please check and re-enter password")
                    .setPositiveListener("OK", ColorDialog::dismiss)
                    .show();
        }


        return isValid;
    }

    private void init(View view) {
        btn_login = view.findViewById(R.id.btn_login);
        et_current_password = view.findViewById(R.id.et_current_password);
        et_new_password = view.findViewById(R.id.et_new_password);
        et_reenter_password = view.findViewById(R.id.et_reenter_password);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.GONE);
    }

}
