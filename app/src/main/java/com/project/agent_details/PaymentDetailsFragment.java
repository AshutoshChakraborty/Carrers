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
import android.widget.ProgressBar;

import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import static com.project.supportClasses.SharedPref.AGENT_ID;


public class PaymentDetailsFragment extends Fragment {

    public static final String AGENT_CODE = "agentcode";
    private IntegratedServicesViewModel integratedServicesViewModel;
    private RecyclerView promotionDetailsRv;
    private ProgressBar pb;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel .class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        promotionDetailsRv = view.findViewById(R.id.rvBankDetails);
        pb = view.findViewById(R.id.pb);
        Bundle args = getArguments();
        String agentCode = args.getString(AGENT_CODE,"");
        if (!agentCode.isEmpty()) {
            pb.setVisibility(View.VISIBLE);
        }
        String loggedInAgentsId = SharedPref.getInstance(getActivity()).getData(AGENT_ID);
        integratedServicesViewModel.getPaymentDetailsObserver().observe(this, bankDetailResponses -> {
            Misc.enableScreenTouch(getActivity());
            pb.setVisibility(View.GONE);
            if (bankDetailResponses.size() > 0) {
                promotionDetailsRv.setAdapter(new PaymentDetailsAdapter(requireActivity(),bankDetailResponses));
            }
        });
        integratedServicesViewModel.fetchPaymentDetails(agentCode,loggedInAgentsId);
    }
}