package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AssignCustomerActivityRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AssignCustomerFragmentRequestPojo;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import static com.project.integratedservices.integratedServicesForAllTypes.view.NewAssignCustomerActivity.isEmailValid;
import static com.project.supportClasses.SharedPref.AGENT_ID;

public class NewAssignCustomerFragment extends Fragment {

    public NewAssignCustomerFragment() {
        // Required empty public constructor
    }

    private TextInputEditText etCustomerName,etAddress,etPinCode,etEmailAddress,etMobileNumber;
    private AppCompatTextView tvConfirm;
    private IntegratedServicesViewModel integratedServicesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_assign_customer, container, false);
        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.VISIBLE);
        ((DashboardActivity)getActivity()).tvCustomerText.setText(getResources().getString(R.string.new_assign_customer));


        init(view);

        integratedServicesViewModel.getAssignCustomerFromCustomerLiveData().observe(this,attendanceCheckResponses -> {
            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());

            if(attendanceCheckResponses.get(0).getStatus().equals("Successful"))
            {
                Toast.makeText(getActivity(), attendanceCheckResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
            else
            {
                Toast.makeText(getActivity(), attendanceCheckResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
            }
        });

        tvConfirm.setOnClickListener(v -> {
            if(Misc.isNetworkAvailable(getActivity()))
            {
                if(isValid())
                {
                    ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.VISIBLE);
                    Misc.disableScreenTouch(getActivity());
                    AssignCustomerFragmentRequestPojo requestPojo = new AssignCustomerFragmentRequestPojo();
                    requestPojo.setAgentCode(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
                    requestPojo.setAsignAgentCode(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
                    requestPojo.setCustomerName(etCustomerName.getText().toString().trim());
                    requestPojo.setAddress(etAddress.getText().toString().trim());
                    requestPojo.setContact(etMobileNumber.getText().toString().trim());
                    requestPojo.setPinCode(etPinCode.getText().toString().trim());
                    if(etEmailAddress.getText().toString().trim().length()==0)
                        requestPojo.setEmail(" ");
                    else
                        requestPojo.setEmail(etEmailAddress.getText().toString().trim());

                    integratedServicesViewModel.assignNewCustomerFromCustomerRequest(requestPojo);

                }
            }
            else
            {
                Toast.makeText(getActivity(), getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void init(View view) {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        etCustomerName = view.findViewById(R.id.etCustomerName);
        etAddress = view.findViewById(R.id.etAddress);
        etPinCode = view.findViewById(R.id.etPinCode);
        etEmailAddress = view.findViewById(R.id.etEmailAddress);
        etMobileNumber = view.findViewById(R.id.etMobileNumber);
        tvConfirm = view.findViewById(R.id.tvConfirm);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.GONE);
        ((DashboardActivity)getActivity()).iv_drawer_menu.setImageResource(R.drawable.menu);
    }

    private boolean isValid() {
        boolean isValid = true;

        if(etCustomerName.getText().toString().trim().isEmpty())
        {
            isValid = false;
            Toast.makeText(getActivity(), "Please enter customer name", Toast.LENGTH_SHORT).show();
        }
        else if(etAddress.getText().toString().trim().isEmpty())
        {
            isValid = false;
            Toast.makeText(getActivity(), "Please enter address", Toast.LENGTH_SHORT).show();
        }else if(etPinCode.getText().toString().trim().isEmpty())
        {
            isValid = false;
            Toast.makeText(getActivity(), "Please enter pincode", Toast.LENGTH_SHORT).show();
        }
        else if(etMobileNumber.getText().toString().trim().isEmpty())
        {
            isValid = false;
            Toast.makeText(getActivity(), "Please enter mobile number", Toast.LENGTH_SHORT).show();
        }
        else  if(!etEmailAddress.getText().toString().trim().isEmpty())
        {
            if(!isEmailValid(etEmailAddress.getText().toString().trim())) {
                isValid = false;
                Toast.makeText(getActivity(), "Please enter valid email", Toast.LENGTH_SHORT).show();
            }
        }

        return isValid;
    }
}
