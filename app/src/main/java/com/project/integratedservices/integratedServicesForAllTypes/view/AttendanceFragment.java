package com.project.integratedservices.integratedServicesForAllTypes.view;


import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.project.integratedservices.R;
import com.project.integratedservices.authenticate.viewmodel.AuthenticationViewModel;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AttendanceRequestModel;
import com.project.supportClasses.Constants;
import com.project.supportClasses.GpsUtils;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.AGENT_ID;
import static com.project.supportClasses.SharedPref.ATTENDACE_END;
import static com.project.supportClasses.SharedPref.ATTENDACE_START;
import static com.project.supportClasses.SharedPref.ATTENDANCE_DATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {


    private AppCompatImageView ivStopTime;
    private AppCompatTextView tvAttendanceStatus, tvCurrentDate, tvHr, tvMinSec, tvStartTime, tvEndTime;
    private LinearLayoutCompat linearLayoutCompat;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private AuthenticationViewModel authenticationViewModel;
    private FusedLocationProviderClient mFusedLocationClient;
    private String latitude = "0.0", longitude = "0.0";
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean isGPSEnabled = false;
    private Location latLng;


    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance2, container, false);
        ((DashboardActivity) getActivity()).tvCustomerText.setVisibility(View.VISIBLE);
        ((DashboardActivity) getActivity()).tvCustomerText.setText(getResources().getString(R.string.attendance));

        init(view);


        /**
         * FOR LOCATION UPDATE
         */
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds



        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), location -> {
            if (location != null) {
                latLng = location;
                System.out.println("@@getLastLocation1 "+latLng.getLatitude());
            }else {
                checkLocationPermission();
            }
        });


/**
 * FOR FETCHING LAT LONG IF GPS IS ALREADY TURNED ON
 */
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        latLng = location;
                        System.out.println("@@locationCallback "+latLng.getLatitude());
                    }
                }
            }
        };


        //Checking if Automatic datetime is disabled in device
        if (!Misc.isTimeAutomatic(getActivity())) {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Automatic date time should be enabled to proceed further");
            colorDialog.setPositiveListener("OK", colorDialog1 -> {
                colorDialog1.dismiss();
                getActivity().onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }


        //////////////////////call get attendance time api//////////////////////
        authenticationViewModel.attendanceTimeResponse().observe(this, dailyAttendanceTime -> {
            tvStartTime.setText("START TIME : " + dailyAttendanceTime.getStartTime());
            tvEndTime.setText("END TIME : " + dailyAttendanceTime.getEndTime());
        });

        authenticationViewModel.checkAttendanceTime(SharedPref.getInstance(getActivity()).getData(AGENT_ID));

        //Handling click on attendance submitting button
        linearLayoutCompat.setOnClickListener(v -> {

            if(latLng==null)
            {
                checkLocationPermission();
                return;
            }
            else
            {
                System.out.println("@@button "+latLng.getLatitude());

                latitude = ""+latLng.getLatitude();
                longitude = ""+latLng.getLongitude();

                if (tvAttendanceStatus.getText().toString().equals(getResources().getString(R.string.start_timer))) {

                ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
                colorDialog.setTitle("ATTENDANCE");
                colorDialog.setContentText("DO YOU WANT TO START THE ATTENDANCE?");
                colorDialog.setPositiveListener("YES", colorDialog1 -> {
                    colorDialog1.dismiss();
                    AttendanceRequestModel requestModel = new AttendanceRequestModel();
                    requestModel.setAgentCode(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
                    requestModel.setStartTime(new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));//pattern accepted by api
                    requestModel.setStart_latitude(latitude);
                    requestModel.setStart_longitude(longitude);
                    callStartAttendanceApi(requestModel);
                });
                colorDialog.setNegativeListener("NO", ColorDialog::dismiss);
                colorDialog.setCancelable(false);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();


            }
            else if (tvAttendanceStatus.getText().toString().equals(getResources().getString(R.string.end_timer))) {
                ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
                colorDialog.setTitle("ATTENDANCE");
                colorDialog.setContentText("DO YOU WANT TO END THE ATTENDANCE?");
                colorDialog.setPositiveListener("YES", colorDialog1 -> {
                    colorDialog1.dismiss();
                    AttendanceRequestModel requestModel = new AttendanceRequestModel();
                    requestModel.setAgentCode(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
                    requestModel.setEndTime(new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));//pattern accepted by api
                    requestModel.setEnd_latitude(latitude);
                    requestModel.setEnd_longitude(longitude);
                    callEndAttendanceApi(requestModel);
                });
                colorDialog.setNegativeListener("NO", ColorDialog::dismiss);
                colorDialog.setCancelable(false);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();

            } else {
                Toast.makeText(getActivity(), "Attendance taken for the day", Toast.LENGTH_SHORT).show();
            }
            }


        });


        //Response for start attendance api
        integratedServicesViewModel.getStartAttendanceResponse().observe(this, attendanceResponses -> {
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());
            if (attendanceResponses != null) {
                if (attendanceResponses.get(0).getStatus().equalsIgnoreCase("Successfull")) {
                    authenticationViewModel.checkAttendanceTime(SharedPref.getInstance(getActivity()).getData(AGENT_ID));

                    SharedPref.getInstance(getActivity()).saveData(ATTENDANCE_DATE, tvCurrentDate.getText().toString());
                    SharedPref.getInstance(getActivity()).saveData(ATTENDACE_START, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));//pattern for local calculations
                    tvAttendanceStatus.setText(getResources().getString(R.string.end_timer));
                } else {
                    Toast.makeText(getActivity(), "" + attendanceResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        //Response for end attendance api
        integratedServicesViewModel.getEndAttendanceResponse().observe(this, attendanceResponses -> {
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());
            if (attendanceResponses != null) {
                if (attendanceResponses.get(0).getStatus().equalsIgnoreCase("Successfull")) {
                    authenticationViewModel.checkAttendanceTime(SharedPref.getInstance(getActivity()).getData(AGENT_ID));

                    SharedPref.getInstance(getActivity()).saveData(ATTENDACE_END, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));//pattern for local calculations
                    ivStopTime.setVisibility(View.GONE);
                    tvAttendanceStatus.setText(getResources().getString(R.string.attendance_taken));
                } else {
                    Toast.makeText(getActivity(), "" + attendanceResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        //Handling errors
        integratedServicesViewModel.getApiError().observe(getActivity(), s -> {
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());
            MyColorDialog.getInstance(getActivity()).setContentText(s)
                    .setPositiveListener("RETRY", colorDialog -> {
                        if (SharedPref.getInstance(getActivity()).getData(ATTENDACE_START).equals("")) {
                            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
                            Misc.disableScreenTouch(getActivity());

                            AttendanceRequestModel requestModel = new AttendanceRequestModel();
                            requestModel.setAgentCode(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
                            requestModel.setStartTime(new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));//pattern accepted by API
                            requestModel.setStart_latitude(latitude);
                            requestModel.setStart_longitude(longitude);
                            callStartAttendanceApi(requestModel);
                        } else {
                            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
                            Misc.disableScreenTouch(getActivity());

                            AttendanceRequestModel requestModel = new AttendanceRequestModel();
                            requestModel.setAgentCode(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
                            requestModel.setStartTime(new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));//pattern accepted by API
                            requestModel.setStart_latitude(latitude);
                            requestModel.setStart_longitude(longitude);
                            callEndAttendanceApi(requestModel);
                        }

                        colorDialog.dismiss();
                    })
                    .setNegativeListener("CANCEL", ColorDialog::dismiss)
                    .show();
        });

        return view;
    }

    //Checking for network connectivity and calling api
    private void callEndAttendanceApi(AttendanceRequestModel requestModel) {
        if (Misc.isNetworkAvailable(getActivity())) {
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
            Misc.disableScreenTouch(getActivity());
            integratedServicesViewModel.callEndAttendance(requestModel);
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
    }


    //Checking for network connectivity and calling api
    private void callStartAttendanceApi(AttendanceRequestModel requestModel) {

        if (Misc.isNetworkAvailable(getActivity())) {
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
            Misc.disableScreenTouch(getActivity());
            integratedServicesViewModel.callStartAttendance(requestModel);
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

    }

    private void init(View view) {
        ivStopTime = view.findViewById(R.id.ivStopTime);
        tvAttendanceStatus = view.findViewById(R.id.tvAttendanceStatus);
        tvCurrentDate = view.findViewById(R.id.appCompatTextView);
        linearLayoutCompat = view.findViewById(R.id.linearLayoutCompat);
        tvHr = view.findViewById(R.id.tvHr);
        tvMinSec = view.findViewById(R.id.tvMinSec);
        tvStartTime = view.findViewById(R.id.tvStartTime);
        tvEndTime = view.findViewById(R.id.tvEndTime);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        authenticationViewModel = ViewModelProviders.of(this).get(AuthenticationViewModel.class);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

       /* DateFormatSymbols syms = new DateFormatSymbols();
        String[] myMonths = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept","Oct", "Nov", "Dec"};
        syms.setMonths(myMonths);*/

        SimpleDateFormat format = new SimpleDateFormat("EEEE, d MMM, yyyy", Locale.ENGLISH);//pattern for displaying in UI
        tvCurrentDate.setText(format.format(Calendar.getInstance().getTime()));

        //If previous attendance record doesn't exist
        if (!SharedPref.getInstance(getActivity()).getData(ATTENDANCE_DATE).equals("")) {
            //If previous attendance date is same as Current date
            if (SharedPref.getInstance(getActivity()).getData(ATTENDANCE_DATE).equals(tvCurrentDate.getText().toString())) {
                //If attendance start time is available
                if (!SharedPref.getInstance(getActivity()).getData(ATTENDACE_START).equals("") && !SharedPref.getInstance(getActivity()).getData(ATTENDACE_END).equals("")) {
                    ivStopTime.setVisibility(View.GONE);
                    tvAttendanceStatus.setText(getResources().getString(R.string.attendance_taken));
                } else if (!SharedPref.getInstance(getActivity()).getData(ATTENDACE_START).equals("") && SharedPref.getInstance(getActivity()).getData(ATTENDACE_END).equals("")) {
                    ivStopTime.setVisibility(View.VISIBLE);
                    tvAttendanceStatus.setText(getResources().getString(R.string.end_timer));
                } else {
                    ivStopTime.setVisibility(View.GONE);
                    tvAttendanceStatus.setText(getResources().getString(R.string.start_timer));
                }

                //Calculating duration if start and end time available
                if (!SharedPref.getInstance(getActivity()).getData(ATTENDACE_START).equals("") && !SharedPref.getInstance(getActivity()).getData(ATTENDACE_END).equals("")) {
                    try {
                        Date startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(SharedPref.getInstance(getActivity()).getData(ATTENDACE_START));//pattern for local calculations
                        Date endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(SharedPref.getInstance(getActivity()).getData(ATTENDACE_END));//pattern for local calculations

                        long duration = endDate.getTime() - startDate.getTime();


                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(duration),
                                TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
                        System.out.println(hms);

                        tvHr.setText(hms.substring(0, 2) + " Hrs");
                        tvMinSec.setText(hms.substring(3, 5) + ":" + hms.substring(6));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                //Calculating duration if only start time available
                else if (!SharedPref.getInstance(getActivity()).getData(ATTENDACE_START).equals("")) {
                    try {
                        Date startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(SharedPref.getInstance(getActivity()).getData(ATTENDACE_START));//pattern for local calculations
                        Date endDate = Calendar.getInstance().getTime();

                        long duration = endDate.getTime() - startDate.getTime();


                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(duration),
                                TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
//                        System.out.println(hms);

                        tvHr.setText(hms.substring(0, 2) + " Hrs");
                        tvMinSec.setText(hms.substring(3, 5) + ":" + hms.substring(6));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                SharedPref.getInstance(getActivity()).saveData(ATTENDACE_START, "");
                SharedPref.getInstance(getActivity()).saveData(ATTENDACE_END, "");
                ivStopTime.setVisibility(View.GONE);
                tvAttendanceStatus.setText(getResources().getString(R.string.start_timer));
            }
        } else {
            ivStopTime.setVisibility(View.GONE);
            tvAttendanceStatus.setText(getResources().getString(R.string.start_timer));
        }


        if (Constants.startAttendanceGiven && Constants.endAttendanceGiven) {
            tvAttendanceStatus.setText(getResources().getString(R.string.attendance_taken));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DashboardActivity) getActivity()).tvCustomerText.setVisibility(View.GONE);
    }


    /**
     * DISPLAY SYSTEM DIALOG TO TURN ON GPS
     */
    private void displayTurnOnGPSDialog() {

        new GpsUtils(getActivity()).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPSEnabled = isGPSEnable;
            }
        });
    }

    public void checkLocationPermission() {

        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                           fetchCurrentLocation();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void fetchCurrentLocation() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), location -> {
            if (location != null) {
                latLng = location;
                System.out.println("@@getLastLocation "+latLng.getLatitude());
            }else {
                displayTurnOnGPSDialog();
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            }
        });
    }

}
