package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.StartVisitRequest;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.VisitInterestedRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.VisitNotInterestedRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.KycResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.NewCustomerListResponsePojo;
import com.project.supportClasses.CaptureImage;
import com.project.supportClasses.CustomSpinner;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;
import com.project.supportClasses.yalantisCroppingLibrary.ImagePickerActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import cn.refactor.lib.colordialog.ColorDialog;

public class CustomerDetailsActivity extends AppCompatActivity implements SpinnerAdapterCompanyName.OnItemSelectListener, SpinnerAdapterPolicyName.OnPolicySelectListener,
        SpinnerAdapterAddressProof.OnAddressSelectListener,
        SpinnerAdapterAgeProof.OnAgeSelectListener,
        SpinnerAdapterIDProof.OnIdSelectListener {

    private AppCompatImageView ivBack;
    private AppCompatTextView subHeader, tvEndVisit, tvCompany, tvPolicy,tvUploadImage,tvfilename;
    private NewCustomerListResponsePojo customerDetails;
    private AppCompatTextView tvName, tvAddress, tvPhn, tvStartVisit, tvSelectedDate, tvIdProof, tvAgeProof, tvAddressProof;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private ProgressBar pb, pbEnd;
    private FusedLocationProviderClient mFusedLocationClient;
    private String lattitude = "0.0";
    private String longitude = "0.0";
    private LinearLayoutCompat llparent, cbFollowUpSubLayout, cbInterestedSubLayout;
    private AppCompatCheckBox cbFollowUp, cbInterested, cbNonInterested;
    private RadioGroup rg;
    private AppCompatEditText etChequeNumber, etComments,etAmount,etApplicationNumber;
    private AppCompatImageView ivSelectDate;
    private Calendar datecalendar;
    private SpinnerAdapterCompanyName spinnerAdapterCompanyName;
    private SpinnerAdapterPolicyName spinnerAdapterPolicyName;
    private CustomSpinner spCompanyName, spPolicy, spAddressProof, spAgeProof, spIdProof;
    private List<String> addressProof = new ArrayList<>();
    private List<String> ageProof = new ArrayList<>();
    private List<String> IdProof = new ArrayList<>();
    private List<KycResponsePojo> kycList = new ArrayList<>();
    private SpinnerAdapterAddressProof spinnerAdapterAddressProof;
    private SpinnerAdapterAgeProof spinnerAdapterAgeProof;
    private SpinnerAdapterIDProof spinnerAdapterIDProof;
    private String addressId,ageId,id;
    /*
     * CUSTOMER STATUS
     * 1 -> FOLLOW UP
     * 2 -> INTERESTED
     * 3 -> NOT INTERESTED
     * */
    private String customerStatus = "";
    private String followUpDate = "";
    private final int REQUEST_IMAGE = 100;
    public static final int RESULT_CROP = 103;
    private final int REQUEST_CODE = 1;
    private final int PICK_IMAGE = 2;
    private Uri selectedImageUri = null;
    private String filePath="";
    private String TAG = getClass().getSimpleName();
    private RadioButton rbCheque,rbCash;
    private boolean isCash = true,isCheque = false,choiceMade = false;
    private String paymentMode ="";
    private String encodedImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        init();



        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                lattitude = String.valueOf(location.getLatitude());
                longitude = String.valueOf(location.getLongitude());
                System.out.println("@@@ lat -> " + lattitude);
                System.out.println("@@@ long -> " + longitude);
            }
        });

        if (getIntent().hasExtra("customerDetails")) {
            customerDetails = getIntent().getParcelableExtra("customerDetails");
            fillDetails(customerDetails);
        }

        integratedServicesViewModel.getApiError().observe(this, s -> {
            Misc.enableScreenTouch(this);
            pb.setVisibility(View.GONE);
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        });

        integratedServicesViewModel.getVisitStateCheckResponseLiveData().observe(this,visitStateCheckResponses -> {
            Misc.enableScreenTouch(this);
            pb.setVisibility(View.GONE);
            if(!visitStateCheckResponses.get(0).getStatus().equalsIgnoreCase("Unsuccessful")) {
                tvStartVisit.setClickable(true);
                tvStartVisit.setBackgroundResource(R.drawable.btn_gradient);
                tvStartVisit.setTextColor(Color.parseColor("#00FFEE"));

            }
            else
            {
                tvStartVisit.setClickable(false);
                tvStartVisit.setBackgroundResource(R.drawable.btn_disabled);
                tvStartVisit.setTextColor(Color.parseColor("#000000"));

                llparent.setVisibility(View.VISIBLE);
//                Toast.makeText(this, ""+visitStateCheckResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
            }
        });

        integratedServicesViewModel.getStartVisitCheckResponse().observe(this, startVisitCheckResponses -> {
            Misc.enableScreenTouch(this);
            pb.setVisibility(View.GONE);
            if (startVisitCheckResponses.get(0).getStatus().equals("Successful")) {

                Misc.disableScreenTouch(this);
                pb.setVisibility(View.VISIBLE);

                StartVisitRequest request = new StartVisitRequest();
                request.setAgentCode(SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
                request.setCustomerId(customerDetails.getCustomerId());
                request.setLat(lattitude);
                request.setLong(longitude);

                integratedServicesViewModel.startVisitRequest(request);
            } else {
                Toast.makeText(this, "" + startVisitCheckResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
            }
        });

        integratedServicesViewModel.getStartVisitResponse().observe(this, startVisitResponses -> {
            Misc.enableScreenTouch(this);
            pb.setVisibility(View.GONE);
            Toast.makeText(this, startVisitResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();

            if (startVisitResponses.get(0).getStatus().equals("Successful")/*true*/) {
                llparent.setVisibility(View.VISIBLE);
            }
        });

        integratedServicesViewModel.getEndFollowUpVisitLiveData().observe(this, endFollowUpVisitResponses -> {
            Misc.enableScreenTouch(this);
            pbEnd.setVisibility(View.GONE);

            if (endFollowUpVisitResponses.get(0).getStatus().equals("Successful")) {
                Toast.makeText(this, "" + endFollowUpVisitResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "" + endFollowUpVisitResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
            }
        });

        integratedServicesViewModel.getEndInterestedVisitResponseLiveData().observe(this,endInterestedVisitResponses -> {
            Misc.enableScreenTouch(this);
            pbEnd.setVisibility(View.GONE);

            if(endInterestedVisitResponses.get(0).getStatus().equals("Successful"))
            {
                Toast.makeText(this, "" + endInterestedVisitResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "" + endInterestedVisitResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
            }
        });

        integratedServicesViewModel.getEndNotInterestedVisitResponseLiveData().observe(this,endNotInterestedVisitResponses -> {
            Misc.enableScreenTouch(this);
            pbEnd.setVisibility(View.GONE);

            if(endNotInterestedVisitResponses.get(0).getStatus().equals("Successful"))
            {
                Toast.makeText(this, "" + endNotInterestedVisitResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "" + endNotInterestedVisitResponses.get(0).getStatus(), Toast.LENGTH_SHORT).show();
            }
        });


        integratedServicesViewModel.getCompanyNamesResponseLiveData().observe(this, companyNamesResponsePojos -> {
            Misc.enableScreenTouch(this);

            if (companyNamesResponsePojos.size() > 0) {
                spinnerAdapterCompanyName = new SpinnerAdapterCompanyName(this, companyNamesResponsePojos, this);
                spCompanyName.setAdapter(spinnerAdapterCompanyName);
                spCompanyName.setDropDownWidth(900);
            }

        });

        integratedServicesViewModel.getPolicyNamesResponseLiveData().observe(this, policyNamesResponsePojos -> {

            if (policyNamesResponsePojos.size() > 0) {
                spinnerAdapterPolicyName = new SpinnerAdapterPolicyName(this, policyNamesResponsePojos, this);
                spPolicy.setAdapter(spinnerAdapterPolicyName);
                spPolicy.setDropDownWidth(900);
            }
        });

        integratedServicesViewModel.getKycResponseLiveData().observe(this, kycResponsePojos -> {
            Misc.enableScreenTouch(this);

            if (kycResponsePojos.size() > 0) {
                kycList.clear();
                kycList.addAll(kycResponsePojos);
                for (KycResponsePojo pojo : kycResponsePojos) {
                    addressProof.add(pojo.getDocumentName());
                    ageProof.add(pojo.getProofType());
                    IdProof.add(pojo.getKycId());
                }
                spinnerAdapterAddressProof = new SpinnerAdapterAddressProof(this, addressProof, this);
                spAddressProof.setAdapter(spinnerAdapterAddressProof);
                spAddressProof.setDropDownWidth(900);

                spinnerAdapterAgeProof = new SpinnerAdapterAgeProof(this, addressProof, this);
                spAgeProof.setAdapter(spinnerAdapterAgeProof);
                spAgeProof.setDropDownWidth(900);

                spinnerAdapterIDProof = new SpinnerAdapterIDProof(this, addressProof, this);
                spIdProof.setAdapter(spinnerAdapterIDProof);
                spIdProof.setDropDownWidth(900);
            } else {
                Toast.makeText(this, "No KYC details available", Toast.LENGTH_SHORT).show();
            }
        });


        if (Misc.isNetworkAvailable(this)) {
            Misc.disableScreenTouch(this);
            pb.setVisibility(View.VISIBLE);

            integratedServicesViewModel.startVisitStateCheckRequest(SharedPref.getInstance(this).getData(SharedPref.AGENT_ID),customerDetails.getCustomerId());

        } else {
            ColorDialog colorDialog = new ColorDialog(this);
            colorDialog.setContentText(R.string.internet_unavailable);
            colorDialog.setPositiveListener("RETRY", new ColorDialog.OnPositiveListener() {
                @Override
                public void onClick(ColorDialog colorDialog) {
                    colorDialog.dismiss();
                    integratedServicesViewModel.startVisitStateCheckRequest(SharedPref.getInstance(CustomerDetailsActivity.this).getData(SharedPref.AGENT_ID),customerDetails.getCustomerId());
                }
            });
            colorDialog.setCancelable(false);
            colorDialog.show();
//            Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
        }


        cbFollowUp.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                customerStatus = "1";
                cbFollowUpSubLayout.setVisibility(View.VISIBLE);
                cbInterestedSubLayout.setVisibility(View.GONE);
                cbInterested.setChecked(false);
                cbNonInterested.setChecked(false);
            }
        });

        cbInterested.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                customerStatus = "2";
                cbFollowUpSubLayout.setVisibility(View.GONE);
                cbInterestedSubLayout.setVisibility(View.VISIBLE);
                cbFollowUp.setChecked(false);
                cbNonInterested.setChecked(false);
                rbCash.setChecked(true);

                if (Misc.isNetworkAvailable(this)) {
//                    Misc.disableScreenTouch(this);

                    integratedServicesViewModel.getComnayNamesRequest();
                    integratedServicesViewModel.getKycDetails();
                } else {
                    Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
                    cbInterested.setChecked(false);
                }
            }
        });

        rbCash.setOnCheckedChangeListener((buttonView, isChecked) -> {
                isCash = isChecked;

            if (isChecked)
                isCheque = false;

            choiceMade = true;
        });

        rbCheque.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            isCash = false;

            isCheque = isChecked;
            choiceMade = true;
        });

        cbNonInterested.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                customerStatus = "3";
                cbFollowUpSubLayout.setVisibility(View.GONE);
                cbInterestedSubLayout.setVisibility(View.GONE);
                cbFollowUp.setChecked(false);
                cbInterested.setChecked(false);
            }
        });

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbCash) {
                etChequeNumber.setVisibility(View.GONE);
            } else if (checkedId == R.id.rbCheque) {
                etChequeNumber.setVisibility(View.VISIBLE);
            }
        });


        ivBack.setOnClickListener(v -> onBackPressed());


        ///////////Follow up section/////////////////////

        ivSelectDate.setOnClickListener(v -> {
            openDatePicker(tvSelectedDate);
        });

        ////////////////////////////////////////////////

        tvCompany.setOnClickListener(v -> {
            spCompanyName.performClick();
            tvPolicy.setText("");
        });

        tvPolicy.setOnClickListener(v -> {
            spPolicy.performClick();
        });

        tvAddressProof.setOnClickListener(v -> {
            spAddressProof.performClick();
        });
        tvAgeProof.setOnClickListener(v -> {
            spAgeProof.performClick();
        });
        tvIdProof.setOnClickListener(v -> {
            spIdProof.performClick();
        });

        tvUploadImage.setOnClickListener(v -> {
            Dexter.withActivity(CustomerDetailsActivity.this)
                    .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {

                                showImagePickerOptions();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        });

        tvEndVisit.setOnClickListener(v -> {

            switch (customerStatus) {
                case "1":
                    if (tvSelectedDate.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please select a follow-up date", Toast.LENGTH_SHORT).show();
                    } else if (etComments.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please write a comment", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Misc.isNetworkAvailable(this)) {
                            Misc.disableScreenTouch(this);
                            pbEnd.setVisibility(View.VISIBLE);

                            integratedServicesViewModel.endFollowUpVisit(customerDetails.getCustomerId(), followUpDate,etComments.getText().toString().trim());
                        } else {
                            Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

                case "2":
                    if (tvCompany.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please select company", Toast.LENGTH_SHORT).show();
                    }
                    else if (tvPolicy.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please select policy", Toast.LENGTH_SHORT).show();
                    }
                    else if (tvPolicy.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please select policy", Toast.LENGTH_SHORT).show();
                    }
                    else if(!choiceMade)
                    {
                        Toast.makeText(this, "Please select payment option and enter details", Toast.LENGTH_SHORT).show();
                    }
//                    else if(choiceMade && isCash)
//                    {
//                        paymentMode = "cash";
//                        if (etAmount.getText().toString().trim().isEmpty()) {
//                            Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
//                        }
//                        else  if (etApplicationNumber.getText().toString().trim().isEmpty()) {
//                            Toast.makeText(this, "Please enter application number", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else if(choiceMade && isCheque)
//                    {
//                        paymentMode = "cheque";
//                        if (etChequeNumber.getText().toString().trim().isEmpty()) {
//                            Toast.makeText(this, "Please enter cheque number", Toast.LENGTH_SHORT).show();
//                        }
//                        else if (etAmount.getText().toString().trim().isEmpty()) {
//                            Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
//                        }
//                        else  if (etApplicationNumber.getText().toString().trim().isEmpty()) {
//                            Toast.makeText(this, "Please enter application number", Toast.LENGTH_SHORT).show();
//                        }
//                    }

                    else if (tvAddressProof.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please select address proof", Toast.LENGTH_SHORT).show();
                    }
                    else if (tvAgeProof.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please select age proof", Toast.LENGTH_SHORT).show();
                    }
                    else if (tvIdProof.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please select id proof", Toast.LENGTH_SHORT).show();
                    }
                    else if (encodedImage.isEmpty()) {
                        Toast.makeText(this, "Please select a picture to upload", Toast.LENGTH_SHORT).show();
                    }
                    else if (etComments.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "Please write a comment", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (Misc.isNetworkAvailable(this)) {
                            Misc.disableScreenTouch(this);
                            pbEnd.setVisibility(View.VISIBLE);

                            VisitInterestedRequestPojo requestPojo = new VisitInterestedRequestPojo();
                            requestPojo.setAgentCode(SharedPref.getInstance(CustomerDetailsActivity.this).getData(SharedPref.AGENT_ID));
                            requestPojo.setCustomerId(customerDetails.getCustomerId());
                            requestPojo.setApplicationNo(etApplicationNumber.getText().toString().trim());
                            requestPojo.setPolicyPlan(tvPolicy.getText().toString().trim());
                            requestPojo.setPaymentMode(paymentMode);
                            requestPojo.setAmount(etAmount.getText().toString().trim());
                            requestPojo.setAddressId(tvAddress.getText().toString().trim());
                            requestPojo.setAgeId(tvAgeProof.getText().toString().trim());
                            requestPojo.setId(tvIdProof.getText().toString().trim());
                            requestPojo.setChequeNo(etChequeNumber.getText().toString().trim());
                            requestPojo.setComments(etComments.getText().toString().trim());
                            requestPojo.setImageName("data:image/jpeg;base64,f"+encodedImage);

                            integratedServicesViewModel.endVisitForInterested(requestPojo);
                        } else {
                            Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;

                case "3":
                 if (etComments.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "Please write a comment", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Misc.isNetworkAvailable(this)) {
                        Misc.disableScreenTouch(this);
                        pbEnd.setVisibility(View.VISIBLE);

                        VisitNotInterestedRequestPojo requestPojo = new VisitNotInterestedRequestPojo();
                        requestPojo.setAgentCode(SharedPref.getInstance(CustomerDetailsActivity.this).getData(SharedPref.AGENT_ID));
                        requestPojo.setCustomerId(customerDetails.getCustomerId());
                        requestPojo.setCustomerId(customerDetails.getCustomerId());
                        requestPojo.setComments(etComments.getText().toString().trim());

                        integratedServicesViewModel.endVisitForNotInterested(requestPojo);
                    } else {
                        Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }

        });
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void fillDetails(NewCustomerListResponsePojo customerDetails) {
        tvName.setText(customerDetails.getCustomerName());
        tvAddress.setText(customerDetails.getAddress());
        tvPhn.setText(customerDetails.getContact());

        tvStartVisit.setOnClickListener(v -> {
            if (Misc.isNetworkAvailable(this)) {
                Misc.disableScreenTouch(this);
                pb.setVisibility(View.VISIBLE);
                integratedServicesViewModel.startVisitCheckRequest(SharedPref.getInstance(this).getData(SharedPref.AGENT_ID));
            } else {
                Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        ivBack = findViewById(R.id.iv_drawer_menu);
        subHeader = findViewById(R.id.textView2);
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhn = findViewById(R.id.tvPhn);
        tvStartVisit = findViewById(R.id.tvStartVisit);
        pb = findViewById(R.id.pb);
        pbEnd = findViewById(R.id.pbEnd);
        llparent = findViewById(R.id.llparent);
        cbFollowUpSubLayout = findViewById(R.id.cbFollowUpSubLayout);
        cbInterestedSubLayout = findViewById(R.id.cbInterestedSubLayout);
        cbFollowUp = findViewById(R.id.cbFollowUp);
        cbInterested = findViewById(R.id.cbInterested);
        cbNonInterested = findViewById(R.id.cbNonInterested);
        rg = findViewById(R.id.rg);
        etChequeNumber = findViewById(R.id.etChequeNumber);
        rbCash = findViewById(R.id.rbCash);
        ivSelectDate = findViewById(R.id.ivSelectDate);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        tvEndVisit = findViewById(R.id.tvEndVisit);
        etComments = findViewById(R.id.etComments);
        spCompanyName = findViewById(R.id.spCompany);
        tvCompany = findViewById(R.id.tvCompany);
        tvPolicy = findViewById(R.id.tvPolicy);
        spPolicy = findViewById(R.id.spPolicy);
        spAddressProof = findViewById(R.id.spAddressProof);
        spAgeProof = findViewById(R.id.spAgeProof);
        spIdProof = findViewById(R.id.spIdProof);
        tvIdProof = findViewById(R.id.tvIdProof);
        tvAgeProof = findViewById(R.id.tvAgeProof);
        tvAddressProof = findViewById(R.id.tvAddressProof);
        tvUploadImage = findViewById(R.id.tvUploadImage);
        tvfilename = findViewById(R.id.tvfilename);
        etAmount = findViewById(R.id.etAmount);
        etApplicationNumber = findViewById(R.id.etApplicationNumber);
        rbCash = findViewById(R.id.rbCash);
        rbCheque = findViewById(R.id.rbCheque);


        datecalendar = Calendar.getInstance();


        subHeader.setVisibility(View.VISIBLE);
        subHeader.setText(getResources().getString(R.string.customer_details));
        ivBack.setImageResource(R.drawable.ic_left_arrow);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        subHeader.setVisibility(View.GONE);
    }

    private void openDatePicker(AppCompatTextView textView) {

        final DatePickerDialog.OnDateSetListener sdt = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                datecalendar.set(Calendar.YEAR, year);
                datecalendar.set(Calendar.MONTH, month);
                datecalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd MMM, yyyy"; //In which date Format needed
                String apiFormat = "yyyy-MM-dd"; //In which date Format needed
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                SimpleDateFormat sdfApiFormat = new SimpleDateFormat(apiFormat, Locale.ENGLISH);

                followUpDate = sdfApiFormat.format(datecalendar.getTime());

//            apiDateFormat = sdfApiFormat.format(datecalendar.getStartTime());
                textView.setText(sdf.format(datecalendar.getTime()));
            }
        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(this), sdt, datecalendar
                .get(java.util.Calendar.YEAR), datecalendar.get(java.util.Calendar.MONTH),
                datecalendar.get(java.util.Calendar.DAY_OF_MONTH));


        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);

        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();

    }

    @Override
    public <E> void selectedCompanyName(E selectedItem) {
        spCompanyName.onDetachedFromWindow();
        if (Misc.isNetworkAvailable(this)) {
            tvCompany.setText(selectedItem.toString());
            integratedServicesViewModel.getPolicyNamesRequest(selectedItem.toString());
        } else {
            Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public <E> void selectedPolicyName(E selectedItem) {
        spPolicy.onDetachedFromWindow();
        tvPolicy.setText(selectedItem.toString());
    }

    @Override
    public <E> void selectedAddressProof(E selectedItem) {
        spAddressProof.onDetachedFromWindow();
        tvAddressProof.setText(selectedItem.toString());

        for(KycResponsePojo pojo:kycList)
        {
            if(pojo.getDocumentName().equalsIgnoreCase(selectedItem.toString()))
            {
                addressId = pojo.getKycId();
            }
        }
    }

    @Override
    public <E> void selectedAgeProof(E selectedItem) {
        spAgeProof.onDetachedFromWindow();
        tvAgeProof.setText(selectedItem.toString());

        for(KycResponsePojo pojo:kycList)
        {
            if(pojo.getDocumentName().equalsIgnoreCase(selectedItem.toString()))
            {
                ageId = pojo.getKycId();
            }
        }
    }

    @Override
    public <E> void selectedIdProof(E selectedItem) {
        spIdProof.onDetachedFromWindow();
        tvIdProof.setText(selectedItem.toString());

        for(KycResponsePojo pojo:kycList)
        {
            if(pojo.getDocumentName().equalsIgnoreCase(selectedItem.toString()))
            {
                id = pojo.getKycId();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@ onActivityResult @@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("requestCode:"+requestCode);

        switch (requestCode){


            case REQUEST_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getParcelableExtra("path");
                    try {
                        // You can update this bitmap to your server
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                        selectedImageUri=uri;
//                        ivUploadImage.setImageURI(selectedImageUri);
                        filePath = uri.getPath();

                        String selectedFilePath = CaptureImage.getPath(this, selectedImageUri);
                        String selectedFileName = selectedFilePath.substring(selectedFilePath.lastIndexOf("/") + 1);
                        tvfilename.setText(selectedFileName);

                        encodedImage = Base64.encodeToString(getByteArrayOfImage(selectedFilePath), Base64.NO_WRAP);
//                        System.out.println("Base64 "+encodedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case RESULT_CROP:
                if (resultCode == RESULT_OK) {

                }
                break;

            case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:

                if(resultCode == RESULT_OK){

                    Uri imageUri = CropImage.getPickImageResultUri(this, data);
                    startCropImageActivity(imageUri);

                }
                break;


            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {

                    System.out.println("getUri:"+result.getUri());
                    System.out.println("result.getUri():"+result.getUri());

                    String selectedFilePath = CaptureImage.getPath(this, result.getUri());
                    String selectedFileName = selectedFilePath.substring(selectedFilePath.lastIndexOf("/") + 1);

                    System.out.println("selectedFileName:"+selectedFileName);
                    System.out.println("selectedFilePath:"+selectedFilePath);

                    selectedImageUri=result.getUri();
//                    ivUploadImage.setImageURI(selectedImageUri);
                    filePath = selectedFilePath;
                    tvfilename.setText(selectedFileName);
//                    fileUri = result.getUri();
//                    //convertToFile(selectedFilePath);
//
//                    File afile = new File(selectedFilePath);
//
//                    long sizeBytes = afile.length();
//                    long sizeMb = sizeBytes / (1024 * 1024);
//                    System.out.println("file size mb : "+sizeMb);
//
//                    if (sizeMb<4.5){
//                        uploadImage(fileUri, selectedFileName);
//                        llUploadImageLayout.setVisibility(View.GONE);
//                        SelectedImage.setVisibility(View.VISIBLE);
//                        SelectedImage.setImageBitmap(BitmapFactory.decodeFile(new ImageCompresseor().compressImage(CaptureImage.getPath(this, result.getUri()))));
//
//                    }else {
//                        AppUtil.showWarningSnakbar(binding.mainLayout,"Image must not be more than 5mb");
//                    }

                    encodedImage = Base64.encodeToString(getByteArrayOfImage(selectedFilePath), Base64.NO_WRAP);
//                    System.out.println("Base64 "+encodedImage);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    //Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                    Log.e(TAG, "CroppingError: "+result.getError() );
                }
                break;
        }
    }

    private byte[] getByteArrayOfImage(String filePath) {
        Bitmap bm = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 60, baos); //bm is the bitmap object
        return baos.toByteArray();
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                //.setMultiTouchEnabled(true)
                //.setMinCropResultSize(600,600)
                //.setAspectRatio(1,1)
                .start(this);
    }
}
