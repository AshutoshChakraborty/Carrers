package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.TeamDetailsResponse;
import com.project.supportClasses.SharedPref;

public class TeamMemberDetailsActivity extends AppCompatActivity {

    private TeamDetailsResponse teamDetailsResponse;
    private AppCompatImageView ivBack;
    private AppCompatTextView subHeader,tvName,tvAgentCode,tvAgentRankId;
    private LinearLayoutCompat llAttendance,llSales,llNewCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_member_details);

        init();

        if(getIntent().hasExtra("details"))
        {
            teamDetailsResponse = getIntent().getParcelableExtra("details");

            populateData(teamDetailsResponse);
        }

        ivBack.setOnClickListener(v -> onBackPressed());

        String attendance = SharedPref.getInstance(this).getData("ATTENDANCE");
        String customer = SharedPref.getInstance(this).getData("CUSTOMER");
//        attendance = "False";
        if (attendance!=null && attendance.equalsIgnoreCase("False")) {
            llAttendance.setClickable(false);
            llAttendance.setBackgroundColor(R.color.grey);
        }else {
            llAttendance.setOnClickListener(v -> {
                startActivity(new Intent(this,AttendanceReport.class).putExtra("Agent_Code",teamDetailsResponse.getAgentCode()));
            });
        }

//        customer = "False";
        if (customer != null && customer.equalsIgnoreCase("False")) {
            llNewCustomer.setClickable(false);
            llNewCustomer.setBackgroundColor(R.color.grey);
        } else {
            llNewCustomer.setOnClickListener(v -> {
                startActivity(new Intent(this,NewAssignCustomerActivity.class).putExtra("Agent_Code",teamDetailsResponse.getAgentCode()));
            });
        }



        llSales.setOnClickListener(v -> {
            startActivity(new Intent(this,SalesReportActivity.class).putExtra("Agent_Code",teamDetailsResponse.getAgentCode()));
        });


    }

    private void init() {



        ivBack = findViewById(R.id.iv_drawer_menu);
        subHeader = findViewById(R.id.textView2);
        tvName = findViewById(R.id.tvIName);
        tvAgentCode = findViewById(R.id.tvAddress);
        tvAgentRankId = findViewById(R.id.tvPhn);
        llAttendance = findViewById(R.id.llAttendance);
        llSales = findViewById(R.id.llSales);
        llNewCustomer = findViewById(R.id.llNewCustomer);

        subHeader.setVisibility(View.VISIBLE);
        subHeader.setText(getResources().getString(R.string.member_details));
        ivBack.setImageResource(R.drawable.ic_left_arrow);
    }

    private void populateData(TeamDetailsResponse teamDetailsResponse) {

        if (teamDetailsResponse.getName()!=null) {
            tvName.setText(teamDetailsResponse.getName().toUpperCase());
        }
        if (teamDetailsResponse.getType()!=null) {
            tvAgentCode.setText(teamDetailsResponse.getType().toUpperCase());
        }
//      tvAgentRankId.setText("Agent Rank ID - "+teamDetailsResponse.getAgRankID().toUpperCase());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        subHeader.setVisibility(View.GONE);
    }
}
