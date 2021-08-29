package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.VoiceInteractor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.message_response.SmsDetailsResposne;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;


import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.AGENT_ID;

public class MessageActivity extends AppCompatActivity implements SmsListAdapter.HandleClick {
    private IntegratedServicesViewModel integratedServicesViewModel;
    private SpinKitView spinKitView;
    private RecyclerView rvTeam;
    private SmsListAdapter adapter;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        spinKitView = findViewById(R.id.spin_kit);
        rvTeam = findViewById(R.id.rvTeam);
        ivBack = findViewById(R.id.ivBack);
        rvTeam.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
        observeDeleteLive();
        observeSmsLiveData();
        checkforSmsDetails();


    }

    private void observeSmsLiveData() {
        integratedServicesViewModel.getSmsDetailsLiveData().observe(this,alertMessageResponses -> {

            if (alertMessageResponses!=null) {
                if(alertMessageResponses.size()>0) {
                    spinKitView.setVisibility(View.GONE);
                    Misc.enableScreenTouch(this);

                    if (alertMessageResponses.get(0).getStatus().equals("Success") || alertMessageResponses.get(0).getStatus().equals("Successfull")) {
                        rvTeam.setVisibility(View.VISIBLE);
                        adapter = new SmsListAdapter(this,alertMessageResponses,this,true);
                        rvTeam.setAdapter(adapter);
                    } else {
//                    Toast.makeText(getActivity(), teamDetailsResponses.get(0).getStatus(), Toast.LENGTH_LONG).show();
                        Toast.makeText(this, R.string.no_items_found, Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }

    private void observeDeleteLive() {
        integratedServicesViewModel.getDeleteSmsResponseLiveData().observe(this,alertMessageResponses -> {

            if (alertMessageResponses!=null) {
                if(alertMessageResponses.size()>0) {
                    spinKitView.setVisibility(View.GONE);
                    Misc.enableScreenTouch(this);


                    if (alertMessageResponses.get(0).getStatus().equals("Success") || alertMessageResponses.get(0).getStatus().equals("Successfull")) {
                       checkforSmsDetails();
                    } else {
//                    Toast.makeText(getActivity(), teamDetailsResponses.get(0).getStatus(), Toast.LENGTH_LONG).show();
                        Toast.makeText(this, R.string.no_items_found, Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }

    private void checkforSmsDetails() {
        if(Misc.isNetworkAvailable(this)) {
            Misc.disableScreenTouch(this);
            spinKitView.setVisibility(View.VISIBLE);
            integratedServicesViewModel.getSmsDetails(SharedPref.getInstance(this).getData(AGENT_ID));
        }
        else
        {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog->{
                ColorDialog.dismiss();
                spinKitView.setVisibility(View.VISIBLE);
                integratedServicesViewModel.getSmsDetails(SharedPref.getInstance(this).getData(AGENT_ID));
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }

    @Override
    public void handleClick(SmsDetailsResposne team) {
        deleteSms(team);
        observeSmsLiveData();

    }

    private void deleteSms(SmsDetailsResposne team) {
        spinKitView.setVisibility(View.VISIBLE);
        integratedServicesViewModel.deleteSms(team.getSlNo());
    }
}