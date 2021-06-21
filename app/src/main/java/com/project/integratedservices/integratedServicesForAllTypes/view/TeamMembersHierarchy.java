package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.TeamAllRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.TeamDetailsResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.COMPANY_NAME;

public class TeamMembersHierarchy extends AppCompatActivity implements TeamListAdapter.HandleClick {

    private IntegratedServicesViewModel integratedServicesViewModel;
    private RecyclerView rvTeamHierarchy;
    private AppCompatImageView ivBack;
    private AppCompatTextView subHeader;
    private TeamListAdapter adapter = null;
    private SpinKitView spinKitView;
    private String agentCode = "";
    private List<String> agentCodes = new ArrayList<>();
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_members_hirarchy);

        init();
        if (SharedPref.getInstance(this).getData(COMPANY_NAME) == null ||SharedPref.getInstance(this).getData(COMPANY_NAME) == "") {
            header.setText(getResources().getString(R.string.integrated_services));
            header.setVisibility(View.GONE);
        } else{
            header.setVisibility(View.GONE);
            header.setText(SharedPref.getInstance(this).getData(COMPANY_NAME));
        }


        //when coming from fragment
        if(getIntent().hasExtra("agentCode"))
        {
            TeamDetailsResponse team = getIntent().getParcelableExtra("agentCode");
            agentCode = team.getAgentCode();
            agentCodes.add(agentCode);

        }
        else
        {
            agentCode = (SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
            agentCodes.add(agentCode);
        }

        if (Misc.isNetworkAvailable(this)) {
            callTeamDetailsApi();
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("OK", ColorDialog -> {
                ColorDialog.dismiss();
                this.onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }


        integratedServicesViewModel.getTeamDetailsResponse().observe(this, teamDetailsResponses -> {
            try {

                spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(this);


                if (teamDetailsResponses.get(0).getStatus().equals("Success") || teamDetailsResponses.get(0).getStatus().equals("Successfull")) {
                    rvTeamHierarchy.setVisibility(View.VISIBLE);
                    adapter = new TeamListAdapter(this,teamDetailsResponses,this::handleClick,true);
                    rvTeamHierarchy.setAdapter(adapter);
                } else {
//                    agentCodes.remove(agentCode);
                    ColorDialog dialog = MyColorDialog.getInstance(this);
                    dialog.setContentText("No Data Available")
                            .setPositiveListener("OK", colorDialog -> {
                                colorDialog.dismiss();
                                onBackPressed();
                            });
                    dialog.setCancelable(false);
                    dialog.show();

//                    Toast.makeText(this, teamDetailsResponses.get(0).getStatus(), Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        integratedServicesViewModel.getApiError().observe(this, s -> {
            try {
                spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(this);
                MyColorDialog.getInstance(this).setContentText(s)
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

        ivBack.setOnClickListener(v -> onBackPressed());
    }

    private void callTeamDetailsApi() {
        rvTeamHierarchy.setVisibility(View.GONE);
        spinKitView.setVisibility(View.VISIBLE);
        Misc.disableScreenTouch(this);
        TeamAllRequestPojo requestPojo = new TeamAllRequestPojo();
        requestPojo.setAgentCode(agentCode);
        requestPojo.setTeamNo("2");// Static

        integratedServicesViewModel.callTeamDetails(requestPojo);
    }

    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        rvTeamHierarchy = findViewById(R.id.rvTeamHierarchy);
        ivBack = findViewById(R.id.iv_drawer_menu);
        subHeader = findViewById(R.id.textView2);
        spinKitView = findViewById(R.id.spin_kit);
        header = findViewById(R.id.tv_header_text);
        rvTeamHierarchy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        subHeader.setVisibility(View.VISIBLE);
        subHeader.setText(getResources().getString(R.string.team));
        ivBack.setImageResource(R.drawable.ic_left_arrow);
    }

    @Override
    public void onBackPressed() {
        subHeader.setVisibility(View.GONE);
//
//        for (String s: agentCodes) {
//            System.out.println("### "+ s);
//        }

//        if(agentCodes.size()>1)
//        {
//                agentCodes.remove(agentCodes.size() - 1);
//                agentCode = agentCodes.get(agentCodes.size() - 1);
//
//            callTeamDetailsApi();
//        }
//        else
//        {
//           finish();
//        }

        finish();
    }

    @Override
    public void handleClick(TeamDetailsResponse team) {
//        agentCode = team.getAgentCode();
//        agentCodes.add(agentCode);
//
//        for (String s: agentCodes) {
//            System.out.println("### "+ s);
//        }

//        callTeamDetailsApi();

        startActivity(new Intent(this,TeamMembersHierarchy.class).putExtra("agentCode",team));
    }
}
