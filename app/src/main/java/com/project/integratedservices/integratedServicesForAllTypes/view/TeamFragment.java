package com.project.integratedservices.integratedServicesForAllTypes.view;

import static com.project.supportClasses.SharedPref.AGENT_ID;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.TeamAllRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.TeamDetailsResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import cn.refactor.lib.colordialog.ColorDialog;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class TeamFragment extends Fragment implements TeamListAdapter.HandleClick {


    private RecyclerView rvTeam;
    private TeamListAdapter adapter = null;
    private IntegratedServicesViewModel integratedServicesViewModel;

    public TeamFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {

                ((DashboardActivity) getActivity()).tvCustomerText.setVisibility(View.VISIBLE);
                ((DashboardActivity) getActivity()).tvCustomerText.setText(getResources().getString(R.string.team));
        }
        catch (Exception e)
        {e.printStackTrace();}

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment, container, false);

        init(view);

        if (Misc.isNetworkAvailable(getActivity())) {
            callTeamDetailsApi();
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("OK", ColorDialog -> {
                ColorDialog.dismiss();
                getActivity().onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }

        integratedServicesViewModel.getTeamDetailsResponse().observe(this, teamDetailsResponses -> {
            try {

                ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(getActivity());


                if (teamDetailsResponses.get(0).getStatus().equals("Success") || teamDetailsResponses.get(0).getStatus().equals("Successfull")) {
                    rvTeam.setVisibility(View.VISIBLE);
                    adapter = new TeamListAdapter(getActivity(),teamDetailsResponses,this,true);
                    rvTeam.setAdapter(adapter);
                } else {
//                    Toast.makeText(getActivity(), teamDetailsResponses.get(0).getStatus(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(), R.string.no_items_found, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        integratedServicesViewModel.getApiError().observe(getActivity(), s -> {
            try {
                ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(getActivity());
                MyColorDialog.getInstance(getActivity()).setContentText(s)
                        .setPositiveListener("RETRY", colorDialog -> {
                            callTeamDetailsApi();
                            colorDialog.dismiss();
                        })
                        .setNegativeListener("CANCEL", ColorDialog::dismiss)
                        .show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return view;
    }

    private void callTeamDetailsApi() {
        rvTeam.setVisibility(View.INVISIBLE);
        ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
        Misc.disableScreenTouch(getActivity());
        TeamAllRequestPojo requestPojo = new TeamAllRequestPojo();
        requestPojo.setAgentCode(SharedPref.getInstance(getActivity()).getData(SharedPref.AGENT_ID));
//        requestPojo.setAgentCode("9100012");
        requestPojo.setTeamNo("2");// Static
        integratedServicesViewModel.callTeamDetails(requestPojo);
    }

    private void init(View view) {

        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        rvTeam = view.findViewById(R.id.rvTeam);
        rvTeam.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvTeam.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.GONE);
    }

    @Override
    public void handleClick(TeamDetailsResponse team) {
        startActivity(new Intent(getActivity(),TeamMembersHierarchy.class).putExtra("agentCode",team));
    }
}
