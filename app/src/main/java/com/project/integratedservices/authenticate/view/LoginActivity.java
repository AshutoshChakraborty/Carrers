package com.project.integratedservices.authenticate.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.project.integratedservices.R;
import com.project.integratedservices.authenticate.viewmodel.AuthenticationViewModel;
import com.project.integratedservices.integratedServicesForAllTypes.view.DashboardActivity;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.supportClasses.Constants;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import static com.project.supportClasses.SharedPref.AGENT_ID;
import static com.project.supportClasses.SharedPref.COMPANY_NAME;
import static com.project.supportClasses.SharedPref.LOGIN_TYPE;
import static com.project.supportClasses.SharedPref.USER_DETAILS;

import cn.refactor.lib.colordialog.ColorDialog;

public class LoginActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 99;
    private TextInputEditText etUsername, etPassword;
    private View btSignIn;
    private AuthenticationViewModel viewModel;
    private SpinKitView spin_kit;
    private FusedLocationProviderClient mFusedLocationClient;
    private String lattitude = "0.0";
    private String longitude = "0.0";
    private String imei = "";
    private AppCompatTextView tv_forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            checkLocationPermission();
        } else {
//            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//            imei = telephonyManager.getDeviceId();
//            System.out.println("@@@IMEI "+ telephonyManager.getDeviceId());


            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    lattitude = String.valueOf(location.getLatitude());
                    longitude = String.valueOf(location.getLongitude());
                    System.out.println("@@@ lat -> " + lattitude);
                    System.out.println("@@@ long -> " + longitude);
                }
            });
        }
//
//                    btSignIn.setOnClickListener(v -> {
//
//                        SharedPref.getInstance(this).saveData(AGENT_ID,etUsername.getText().toString().trim());
//
//                        Intent mainIntent = new Intent(this, DashboardActivity.class).putExtra("id",etUsername.getText().toString().trim());
//                        startActivity(mainIntent);
//                        finish();
//                    });

//                    etUsername.setText("9100001");
//                    etPassword.setText("123456");

//                    etUsername.setText("9100623");
//                    etPassword.setText("oxalic");

        btSignIn.setOnClickListener(v -> {
            if (!etUsername.getText().toString().trim().isEmpty() && !etPassword.getText().toString().trim().isEmpty()) {

                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {

                    checkLocationPermission();
                } else {
                    callUserDetailsApi(etUsername.getText().toString().trim(), imei, Constants.TOKEN_ID);
//                                if (imei==null || imei.equals("")) {
//                                    MyColorDialog.getInstance(this)
//                                            .setContentText("IMEI no is needed to proceed")
//                                            .setPositiveListener("OK", ColorDialog -> {
//                                                Dexter.withActivity(LoginActivity.this).withPermission(Manifest.permission.READ_PHONE_STATE).withListener(new PermissionListener() {
//                                                    @SuppressLint("MissingPermission")
//                                                    @Override
//                                                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                                                        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//                                                        imei = telephonyManager.getDeviceId();
//                                                        System.out.println("@@@IMEI "+ telephonyManager.getDeviceId());
//                                                    }
//
//                                                    @Override
//                                                    public void onPermissionDenied(PermissionDeniedResponse response) {
//                                                        Toast.makeText(LoginActivity.this, "Please provide required permissions to proceed", Toast.LENGTH_SHORT).show();
//                                                    }
//
//                                                    @Override
//                                                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                                                        token.continuePermissionRequest();
//                                                    }
//                                                }).check();
////                                                ActivityCompat.requestPermissions(this,
////                                                        new String[]{Manifest.permission.READ_PHONE_STATE},
////                                                        MY_PERMISSIONS_REQUEST);
//                                                ColorDialog.dismiss();
//                                            })
//                                            .show();
//                                } else {
////                                LoginRequestModel loginRequestModel = new LoginRequestModel();
////                                loginRequestModel.setAgentCode(etUsername.getText().toString().trim());
////                                loginRequestModel.setUserid(etUsername.getText().toString().trim());
////                                loginRequestModel.setPassowrd(etPassword.getText().toString().trim());
////                                loginRequestModel.setImeiCode(imei);
////                                loginRequestModel.setTokenid(Constants.TOKEN_ID);
////                                viewModel.callLoginApi(loginRequestModel);
////                                spin_kit.setVisibility(View.VISIBLE);
////                                Misc.disableScreenTouch(LoginActivity.this);
//
//                                    callUserDetailsApi(etUsername.getText().toString().trim(), imei, Constants.TOKEN_ID);
//                                }
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

//                    viewModel.getLoginResponse().observe(this, loginResponseModel -> {
////                        Toast.makeText(this, "" + loginResponseModel.getStatus(), Toast.LENGTH_SHORT).show();
//                        spin_kit.setVisibility(View.GONE);
//                        Misc.enableScreenTouch(LoginActivity.this);
////                        if (loginResponseModel.getStatus().equalsIgnoreCase("Success")) {
//                        if (loginResponseModel.getMessage().equalsIgnoreCase("Welcome.")) {
//                           /* if(etPassword.getText().toString().trim().equals(loginResponseModel.getPassword())) {
//                                SharedPref.getInstance(this).saveData(AGENT_ID,etUsername.getText().toString().trim());
//                                Intent mainIntent = new Intent(this, DashboardActivity.class).putExtra("id", etUsername.getText().toString().trim());
//                                startActivity(mainIntent);
//                                finish();
//                            }
//                            else
//                            {
//                                viewModel.getApiError().setValue(getString(R.string.incorrect_password));
//                            }*/
//                                SharedPref.getInstance(this).saveData(AGENT_ID,etUsername.getText().toString().trim());
//                                Intent mainIntent = new Intent(this, DashboardActivity.class).putExtra("id", etUsername.getText().toString().trim());
//                                startActivity(mainIntent);
//                                finish();
//                        } else {
//                            viewModel.getApiError().setValue(loginResponseModel.getStatus());
//                        }
//                    });

        viewModel.getUserDetails().observe(this, userDetailsResponse -> {
            spin_kit.setVisibility(View.GONE);
            Misc.enableScreenTouch(LoginActivity.this);
            if (userDetailsResponse.getStatus().equalsIgnoreCase("Success")) {

                Gson gson = new Gson();
                String response = gson.toJson(userDetailsResponse);
                if (imei.equals(userDetailsResponse.getImeiCode())/*true*/) {
                    SharedPref.getInstance(this).saveData(USER_DETAILS, response);
                    if (userDetailsResponse.getPassword().equals(etPassword.getText().toString().trim())/* true*/) {
                        SharedPref.getInstance(this).saveData(AGENT_ID, etUsername.getText().toString().trim());
                        SharedPref.getInstance(this).saveData(COMPANY_NAME, userDetailsResponse.getCompanyName());

                        if (userDetailsResponse.getLoginType().equals("Agent"))
                            SharedPref.getInstance(this).saveData(LOGIN_TYPE, Constants.LOGIN_TYPE_AGENT);
                        else
                            SharedPref.getInstance(this).saveData(LOGIN_TYPE, Constants.LOGIN_TYPE_USER);

                        Intent mainIntent = new Intent(this, DashboardActivity.class).putExtra("userdata", userDetailsResponse);
                        startActivity(mainIntent);
                        finish();
                    } else {
                        viewModel.getApiError().setValue(getResources().getString(R.string.incorrect_username_or_password));
                    }
                } else {
//                    Toast.makeText(this, "This id is registered with a different device", Toast.LENGTH_SHORT).show();
                    ColorDialog colorDialog = MyColorDialog.getInstance(this);
                    colorDialog.setContentText("This id is registered with a different device");
                    colorDialog.setCancelable(true);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();
                }
            } else {
//                Toast.makeText(this, userDetailsResponse.getStatus(), Toast.LENGTH_SHORT).show();
                ColorDialog colorDialog = MyColorDialog.getInstance(this);
                colorDialog.setContentText(userDetailsResponse.getStatus());
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();
            }
        });

        viewModel.getApiError().observe(this, s -> {
            Misc.enableScreenTouch(LoginActivity.this);
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText(s);
            colorDialog.setCancelable(true);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
            spin_kit.setVisibility(View.GONE);
        });

        tv_forgot_password.setOnClickListener(v -> {
            startActivity(new Intent(this, ForgotPasswordActivity.class));
        });
    }

    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        viewModel = ViewModelProviders.of(this).get(AuthenticationViewModel.class);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btSignIn = findViewById(R.id.btSignIn);
        spin_kit = findViewById(R.id.spin_kit);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);

        imei = (SharedPref.getInstance(LoginActivity.this).getData(SharedPref.UUID));
        System.out.println("uuid login - " + imei);

        if (!SharedPref.getInstance(this).getData(AGENT_ID).equals("")) {
            Gson gson = new Gson();
            UserDetailsResponse response = gson.fromJson(SharedPref.getInstance(this).getData(USER_DETAILS), UserDetailsResponse.class);
            Intent mainIntent = new Intent(this, DashboardActivity.class).putExtra("userdata", response);
            startActivity(mainIntent);
            finish();
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST);

            /*// Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                MyColorDialog.getInstance(this)
                        .setContentText("Please grant permissions to proceed")
                        .setPositiveListener("OK",colorDialog -> {
                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST);
                            colorDialog.dismiss();
                        })
                        .setNegativeListener("CANCEL",colorDialog -> {
                            colorDialog.dismiss();
                        })
                        .show();


            }
            else if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE))
            {
                MyColorDialog.getInstance(this)
                        .setContentText("Please grant permissions to proceed")
                        .setPositiveListener("OK",colorDialog -> {
                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    MY_PERMISSIONS_REQUEST);
                            colorDialog.dismiss();
                        })
                        .setNegativeListener("CANCEL",colorDialog -> {
                            colorDialog.dismiss();
                        })
                        .show();
            }
            else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST);
            }*/
            return false;
        } else {

//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.READ_PHONE_STATE)
//                    == PackageManager.PERMISSION_GRANTED) {
//
//
//                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//                imei = telephonyManager.getDeviceId();
//                System.out.println("@@@IMEI "+ telephonyManager.getDeviceId());
//
//                if(imei==null)
//                    imei= (SharedPref.getInstance(LoginActivity.this).getData(SharedPref.UUID));
//            }

            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    lattitude = String.valueOf(location.getLatitude());
                    longitude = String.valueOf(location.getLongitude());
                    System.out.println("@@@ lat -> " + lattitude);
                    System.out.println("@@@ long -> " + longitude);
                }
            });
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                            if (location != null) {
                                lattitude = String.valueOf(location.getLatitude());
                                longitude = String.valueOf(location.getLongitude());
                                System.out.println("@@@ lat -> " + lattitude);
                                System.out.println("@@@ long -> " + longitude);
                            }
                        });
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST);
                    }

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_PHONE_STATE)
                            == PackageManager.PERMISSION_GRANTED) {

                        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

//                        System.out.println("@@@IMEI " + telephonyManager.getDeviceId());
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_PHONE_STATE},
                                MY_PERMISSIONS_REQUEST);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

    private void callUserDetailsApi(String agentCode, String iemiCode, String token) {
        Misc.disableScreenTouch(this);
        spin_kit.setVisibility(View.VISIBLE);
        viewModel.callUserDetails(agentCode, iemiCode, token);
    }
}
