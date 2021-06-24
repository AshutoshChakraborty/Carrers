package com.project.agent_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_field_work.FieldWorkResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.phas_master.PhaseMasterResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import static com.project.supportClasses.SharedPref.AGENT_ID;

public class FieldWorkDetailsFragment extends Fragment {


    public static final String AGENT_CODE = "agentcode";
    private IntegratedServicesViewModel integratedServicesViewModel;
    private AppCompatTextView name;
    private AppCompatTextView rank;
    private AppCompatTextView grade1;
    private AppCompatTextView introducer;
    private AppCompatTextView gender;
    private AppCompatTextView doj;
    private AppCompatTextView address;
    private AppCompatTextView state;
    private AppCompatTextView pin;
    private AppCompatTextView branch;
    private AppCompatTextView mobile;
    private AppCompatTextView pan;


    public FieldWorkDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel .class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_field_work_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.name);
        rank = view.findViewById(R.id.rank);
        grade1 = view.findViewById(R.id.grade1);
        introducer = view.findViewById(R.id.introducer);
        gender = view.findViewById(R.id.gender);
        doj = view.findViewById(R.id.doj);
        address = view.findViewById(R.id.address);
        state = view.findViewById(R.id.state);
        pin = view.findViewById(R.id.pin);
        branch = view.findViewById(R.id.branch);
        mobile = view.findViewById(R.id.mobile);
        pan = view.findViewById(R.id.pan);

        Bundle args = getArguments();
        String agentCode = args.getString(AGENT_CODE);
        String loggedInAgentsId = SharedPref.getInstance(getActivity()).getData(AGENT_ID);

        integratedServicesViewModel.getFieldWorkObserver().observe(this, fieldWorkResponses -> {
            Misc.enableScreenTouch(getActivity());

            if (fieldWorkResponses.size() > 0) {
                FieldWorkResponse fieldWorkResponse = fieldWorkResponses.get(0);
                name.setText(fieldWorkResponse.getName());
                        rank.setText(fieldWorkResponse.getAgRankId());
                grade1.setText(fieldWorkResponse.getGradeName());
                        introducer.setText(fieldWorkResponse.getIntroCode());
                gender.setText(fieldWorkResponse.getGender());
                        doj.setText(fieldWorkResponse.getDoj());
                address.setText(fieldWorkResponse.getAddress());
                        state.setText(fieldWorkResponse.getState());
                pin.setText(fieldWorkResponse.getPin());
                        branch.setText(fieldWorkResponse.getBranchName());
                mobile.setText(fieldWorkResponse.getMobileNo());
                        pan.setText(fieldWorkResponse.getPin());
            }
        });


        integratedServicesViewModel.fetchFieldWorkDetails(agentCode,loggedInAgentsId);
    }
}