package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import cn.refactor.lib.colordialog.ColorDialog;

public class SuggestionOrComplainsActivity extends AppCompatActivity {
    private ImageView ivBack;
    private MaterialCardView cvSubmit;
    private ProgressBar pb;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private AppCompatEditText et_suggestion;
    private AppCompatTextView tvCharactersLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggesation_or_complains);


        init();
        handleClicks();
        apiResponses();
    }


    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack = findViewById(R.id.ivBack);
        cvSubmit = findViewById(R.id.cvSubmit);
        pb = findViewById(R.id.pb);
        et_suggestion = findViewById(R.id.et_suggestion);
        tvCharactersLeft = findViewById(R.id.tvCharactersLeft);

    }

    private void handleClicks() {

        et_suggestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCharactersLeft.setText("Characters Left -  "+s.length()+"/200");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
        cvSubmit.setOnClickListener(v -> {
            if(validated())
            {
                if (Misc.isNetworkAvailable(this))
                {
                    Misc.disableScreenTouch(this);
                    pb.setVisibility(View.VISIBLE);

                    integratedServicesViewModel.submitMISSuggestionComplain(et_suggestion.getText().toString().trim(), SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
                }
                else
                {
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText("Please check your Internet connection and retry");
                    colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                        ColorDialog.dismiss();
                        validated();
                    });
                    colorDialog.setNegativeListener("CANCEL",dialog -> {
                        dialog.dismiss();
                        this.onBackPressed();
                    });
                    colorDialog.setCancelable(false);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();
                }
            }
        });


    }

    private boolean validated() {
        if(et_suggestion.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, "Please write something", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void apiResponses() {
        integratedServicesViewModel.getMisSuggestionComplainResponseLiveData().observe(this,misSuggestionComplainResponses -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if(misSuggestionComplainResponses.size()>0)
            {
                et_suggestion.getText().clear();
                Toast.makeText(this, ""+misSuggestionComplainResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
            }
        });

        integratedServicesViewModel.getApiError().observe(this,s -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        });
    }
}