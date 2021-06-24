package com.project.agent_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_bank_detail.BankDetailResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_field_work.FieldWorkResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import static com.project.supportClasses.SharedPref.AGENT_ID;


public class BankDetailsFragment extends Fragment {

    public static final String AGENT_CODE = "agentcode";
    private IntegratedServicesViewModel integratedServicesViewModel;
    private RecyclerView rvBankDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel .class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bank_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBankDetails = view.findViewById(R.id.rvBankDetails);
        Bundle args = getArguments();
        String agentCode = args.getString(AGENT_CODE);
        String loggedInAgentsId = SharedPref.getInstance(getActivity()).getData(AGENT_ID);
        integratedServicesViewModel.getBankDetailObserver().observe(this, bankDetailResponses -> {
            Misc.enableScreenTouch(getActivity());

            if (bankDetailResponses.size() > 0) {
                rvBankDetails.setAdapter(new BankDetailsAdapter(requireActivity(),bankDetailResponses));
            }
        });
        integratedServicesViewModel.fetchBankDetails(agentCode,loggedInAgentsId);

    }
}