package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.SalesDetailsRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.SalesDetailsResponsePojo;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import java.util.ArrayList;
import java.util.List;

import static com.project.supportClasses.SharedPref.AGENT_ID;

public class SalesReportActivity extends AppCompatActivity {


    private AppCompatImageView ivBack;
    private AppCompatTextView subHeader;
    private PieChart chart;
    private List<PieEntry> entries = new ArrayList<>();
    private RecyclerView rvSales;
    private String agentCode;
    private SalesAdapter adapter = null;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private List<SalesDetailsResponsePojo> salesDetailsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);

        init();

        if(getIntent().hasExtra("Agent_Code"))
        {
            agentCode = getIntent().getStringExtra("Agent_Code");
        }

        ivBack.setOnClickListener(v -> onBackPressed());




        ///////////////////////////////////////////////////////
        if (Misc.isNetworkAvailable(this)) {
            SalesDetailsRequestPojo requestPojo = new SalesDetailsRequestPojo();
            requestPojo.setAgentCode(agentCode);
//            requestPojo.setAgentCode(SharedPref.getInstance(this).getData(AGENT_ID));
            integratedServicesViewModel.getSalesDetailsRequest(requestPojo);
        } else {
            Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
            finish();
        }



        integratedServicesViewModel.getSalesDetailsLiveData().observe(this,salesDetailsResponsePojos -> {
            if(salesDetailsResponsePojos.get(0).getStatus().equals("Successful"))
            {
                salesDetailsList.clear();
                salesDetailsList.addAll(salesDetailsResponsePojos);

                setupChart(Float.parseFloat(salesDetailsList.get(0).getNew()),Float.parseFloat(salesDetailsList.get(0).getFollowUp()),Float.parseFloat(salesDetailsList.get(0).getClosed()),0);
                adapter.notifyDataSetChanged();
            }
            else
            {
//                Toast.makeText(this, salesDetailsResponsePojos.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                setupChart(0,0,0,1);
//                finish();
            }
        });
    }

    private void setupChart(float NEW,float FOLLOW_UP,float CLOSED,float DUMMY) {
        entries.add(new PieEntry((NEW),"NEW "+NEW));
        entries.add(new PieEntry((FOLLOW_UP),"FOLLOW UP "+FOLLOW_UP));
        entries.add(new PieEntry((CLOSED),"CLOSED "+CLOSED));
        entries.add(new PieEntry((DUMMY),""));

        final int[] MY_COLORS = {Color.GREEN,Color.YELLOW,Color.RED};

        PieDataSet pieDataSet = new PieDataSet(entries," ");
        pieDataSet.setColors(MY_COLORS);
        pieDataSet.setValues(entries);

//        ArrayList<Integer> colors = new ArrayList<Integer>();
//        for(int c: MY_COLORS) colors.add(c);
//        pieDataSet.setColors(colors);

        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);
        pieData.setValueTextSize(0f);
        pieData.setValueTextColor(Color.DKGRAY);

        chart.setCenterText("TOTAL"+"\n"+(NEW+FOLLOW_UP+CLOSED));
        chart.setCenterTextColor(Color.BLUE);
        chart.setCenterTextRadiusPercent(75);
        chart.getDescription().setEnabled(false);
        chart.setNoDataText("Waiting for chart data");
        chart.setDrawSliceText(false);
        chart.setData(pieData);
        chart.invalidate();
    }

    private void init() {
        ivBack = findViewById(R.id.iv_drawer_menu);
        subHeader = findViewById(R.id.textView2);
        chart = findViewById(R.id.pieChart);
        rvSales = findViewById(R.id.rvSales);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);


        adapter = new SalesAdapter(this,salesDetailsList);
        rvSales.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSales.setAdapter(adapter);


        subHeader.setVisibility(View.VISIBLE);
        subHeader.setText(getResources().getString(R.string.sales_report));
        ivBack.setImageResource(R.drawable.ic_left_arrow);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        subHeader.setVisibility(View.GONE);
    }
}
