package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.ContentLoadingProgressBar;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PlanDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PremiumCalculatorList;

public class PlanWebActivity extends AppCompatActivity {

    private WebView mWebview ;
    private AppCompatImageView ivBack ;
    private AppCompatTextView tvHeader ;
    private ContentLoadingProgressBar progressBar ;
    PlanDetailsResponse detailsResponse;
    PremiumCalculatorList premium;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_web);

        init();

        if(getIntent().hasExtra("product")) {
            detailsResponse = getIntent().getParcelableExtra("product");

            tvHeader.setText(detailsResponse.getProductName());
            mWebview .loadUrl(detailsResponse.getLink());
        }
        else if(getIntent().hasExtra("premium"))
        {

            premium = getIntent().getParcelableExtra("premium");
            tvHeader.setText(premium.getInsurerName());
            mWebview .loadUrl(premium.getPremiumLink());
        }

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(PlanWebActivity.this, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient(){
            /*
                public void onProgressChanged (WebView view, int newProgress)
                    Tell the host application the current progress of loading a page.

                Parameters
                    view : The WebView that initiated the callback.
                    newProgress : Current page loading progress, represented by an integer
                        between 0 and 100.
            */
            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                progressBar.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        ivBack.setOnClickListener(v -> onBackPressed());


    }

    private void init() {
        mWebview = findViewById(R.id.mWebview);
        progressBar = findViewById(R.id.progressBar);
        ivBack = findViewById(R.id.ivBack);
        tvHeader = findViewById(R.id.tvHeader);

        progressBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));

        mWebview.getSettings().setJavaScriptEnabled(true);
    }
}
