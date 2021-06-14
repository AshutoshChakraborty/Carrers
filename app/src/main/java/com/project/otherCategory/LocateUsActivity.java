package com.project.otherCategory;

import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.view.DashboardActivity;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AllBranchDetailsResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;

public class LocateUsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ImageViewCompat ivBack;
    private IntegratedServicesViewModel  integratedServicesViewModel;
    private SpinKitView spinKitView;
    LatLngBounds.Builder builder;
    CameraUpdate cu;
    Marker marker;
    boolean handleit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_us);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        spinKitView = findViewById(R.id.spin_kit);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.ivBack).setOnClickListener(v -> {
            finish();
        });


        if (Misc.isNetworkAvailable(this)) {
            callGetBranchDetailsApi();
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("OK", ColorDialog -> {
                ColorDialog.dismiss();
                onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }

        integratedServicesViewModel.getAllBranchData().observe(this,allBranchDetailsResponses -> {
            spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if(allBranchDetailsResponses!=null)
            {
                mSetUpMap(allBranchDetailsResponses);
            }
        });

        integratedServicesViewModel.getApiError().observe(this,s -> {
            spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            try {
            MyColorDialog.getInstance(this).setContentText(s)
                    .setPositiveListener("RETRY", colorDialog -> {
                        callGetBranchDetailsApi();
                        colorDialog.dismiss();
                    })
                    .setNegativeListener("CANCEL", ColorDialog::dismiss)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        });


    }

    private void mSetUpMap(List<AllBranchDetailsResponse> allBranchDetailsResponses) {

        mMap.clear();

        List<Marker> markersList = new ArrayList<Marker>();

        for (AllBranchDetailsResponse response: allBranchDetailsResponses) {
            if(!response.getLatitude().equals(""))
            markersList.add(mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(response.getLatitude()),Double.parseDouble(response.getLongitude()))).title(response.getBranchName())));
        }

        builder = new LatLngBounds.Builder();
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }

        //**initialize the padding for map boundary*//*
        int padding = 50;
        //**create the bounds from latlngBuilder to set into map camera*//*
        LatLngBounds bounds = builder.build();
        //**create the camera with bounds and padding to set into map*//*
        cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

        //**call the map call back to know map is loaded or not*//*
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                //**set animated zoom camera into map*//*
                mMap.animateCamera(cu);

            }
        });

    }

    private void callGetBranchDetailsApi() {
        spinKitView.setVisibility(View.VISIBLE);
        Misc.disableScreenTouch(this);
        integratedServicesViewModel.callAllBranchDetails();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng kolkata = new LatLng(	22.572645, 88.363892);
        marker = mMap.addMarker(new MarkerOptions().position(kolkata).title("Kolkata"));
        marker.setTag(0);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kolkata));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 6.0f ) );

        mMap.setOnMarkerClickListener(this);

        mMap.setOnCameraIdleListener(() -> {
            int zoomLevel = (int) mMap.getCameraPosition().zoom;

            if(zoomLevel<10) {
//                Toast.makeText(this, "less than 10", Toast.LENGTH_SHORT).show();
                if(handleit)
                {
                    handleit = false;
                    mMap.clear();
                    marker = mMap.addMarker(new MarkerOptions().position(kolkata).title("Kolkata"));


                    mMap.moveCamera(CameraUpdateFactory.newLatLng(kolkata));
                    mMap.animateCamera( CameraUpdateFactory.zoomTo( 6.0f ) );
                }
            }
        });
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        System.out.println("marker");
        if (marker.equals(this.marker))
        {

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mMap.clear();
                    LatLng chinarpark = new LatLng(	22.623617, 88.441095);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng( mMap.addMarker(new MarkerOptions().position(chinarpark).title("Chinarpark")).getPosition()));
                    mMap.animateCamera( CameraUpdateFactory.zoomTo( 18.0f ) );
                    handleit = true;
                }
            }, 250);


        }
        return false;
    }


}
