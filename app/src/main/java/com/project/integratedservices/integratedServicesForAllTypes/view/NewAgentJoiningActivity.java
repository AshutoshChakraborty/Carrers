package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.branch_details.BranchDetails;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.grade_details.GradeDetails;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.intro_details.IntroDetails;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.mis_agent_joining_details.MisAgentJoiningDetails;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.new_joinee_final_submit.NewJoiningFinalRespons;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.phas_master.PhaseMasterResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.project.supportClasses.SharedPref.AGENT_ID;

public class NewAgentJoiningActivity extends AppCompatActivity {
    private Date start;
    private Calendar calendar;
    private AppCompatTextView dob;
    private AppCompatTextView phase;
    private AppCompatTextView introducerCode;
    private AppCompatTextView introducerName;
    private AppCompatTextView grade;
    private AppCompatTextView number;
    private AppCompatTextView name;
    private AppCompatTextView introNumber;
    private AppCompatTextView intNa;
    private AppCompatTextView commencementDate;
    private AppCompatTextView location;
    private AppCompatTextView grade1;
    private AppCompatTextView introGrade;
    private AppCompatTextView retirementDate;
    private AppCompatTextView itemIssuedStatus;
    private TextInputEditText companyName;
    private TextInputEditText phoneNumber;
    private TextInputEditText grd;
    private AutoCompleteTextView joiningBranch;
    private RadioGroup genderGroup;
    private RadioGroup bagGroup;
    private AutoCompleteTextView gradeSpinner;
    private TextInputLayout joiningBrachInputLayout;
    private TextInputEditText inputintroducerCode;
    private TextInputEditText applicantName;
    private MaterialCardView viewButton;
    private MaterialCardView submit;
    private ProgressBar pb;
    private String phaseId;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private String agentId;
    private String branchId;
    private String gender;
    private String enrollAmount;
    private String dateOfBirth;
    private String companyId;
    private ImageView ivBack;
    private RadioButton male;
    private RadioButton female;
    private RadioButton withBag;
    private RadioButton withoutBag;
    private String rankId;
    private String gradeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        setContentView(R.layout.activity_new_agent_joining);
        agentId = SharedPref.getInstance(this).getData(AGENT_ID);
        initView();
        addObservers();
        dob.setOnClickListener(v -> openDatePicker(dob));
        ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
        viewButton.setOnClickListener(v -> {

            if (inputintroducerCode.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter Introducer code", Toast.LENGTH_SHORT).show();
            } else {
                pb.setVisibility(View.VISIBLE);
                callIntroDetailsApi();
            }
        });
        submit.setOnClickListener(v -> {
            callSubmitApi();
        });
        pb.setVisibility(View.VISIBLE);
        integratedServicesViewModel.getPhaseMaster();
        integratedServicesViewModel.getJoiningBranchSpinner();



    }

    private void callSubmitApi() {
        String agentName = applicantName.getText().toString();
        int checkedRadioButtonId = genderGroup.getCheckedRadioButtonId();
        int bagChecked = bagGroup.getCheckedRadioButtonId();
        RadioButton viewById = (RadioButton) findViewById(checkedRadioButtonId);
        if (viewById.getText().toString().equalsIgnoreCase("Male")) {
            gender = "M";
        } else {
            gender = "F";
        }
        RadioButton bagRadio = (RadioButton) findViewById(bagChecked);
        if (bagRadio.getText().toString().equalsIgnoreCase("With Bag")) {
            enrollAmount = "1400";
        } else {
            enrollAmount = "600";
        }

        dateOfBirth = dob.getText().toString();
        String introducer = introducerCode.getText().toString();
        String phoneNumber = this.phoneNumber.getText().toString();

        if (agentName.isEmpty()) {
            Toast.makeText(this, "Please provide applicant name", Toast.LENGTH_SHORT).show();
        } else if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please provide phone number", Toast.LENGTH_SHORT).show();
        } else if (start==null) {
            Toast.makeText(this, "Please provide Date of Birth", Toast.LENGTH_SHORT).show();
        } else if (introducer.isEmpty()) {
            Toast.makeText(this, "Please provide Introducer", Toast.LENGTH_SHORT).show();
        } else if (branchId == null) {
            Toast.makeText(this, "Please select a branch", Toast.LENGTH_SHORT).show();
        } else if (phoneNumber.length() < 10) {
            Toast.makeText(this, "Not a valid phone number", Toast.LENGTH_SHORT).show();
        } else if (rankId == null) {
            Toast.makeText(this, "Please select you grade name", Toast.LENGTH_SHORT).show();
        } else {
            integratedServicesViewModel.submitApi(agentName, gender, dateOfBirth, introducer, branchId, introducer, enrollAmount, rankId, "0", "2", companyId, phaseId, phoneNumber);

        }
    }

    private void callIntroDetailsApi() {
        if (inputintroducerCode.getText() != null) {
            integratedServicesViewModel.getIntroDetails(inputintroducerCode.getText().toString(), agentId);
        }
    }

    private void addObservers() {
        integratedServicesViewModel.getPhaseMasterObserver().observe(this, phaseMaster -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);

            if (phaseMaster.size() > 0) {
                PhaseMasterResponse phaseMasterResponse = phaseMaster.get(0);
                phase.setText(phaseMasterResponse.getPhaseName());
                phaseId = phaseMasterResponse.getPhaseId().toString();


            }
        });

        integratedServicesViewModel.getIntroDetailsObserver().observe(this, introDetails -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (introDetails.size() > 0) {
                IntroDetails introDeails = introDetails.get(0);
                companyId = introDeails.getCompanyId();
                if (introDeails.getAgentCode() != null) {
                    introducerCode.setText(introDeails.getAgentCode().toString());
                }
                if (introDeails.getName() != null) {
                    introducerName.setText(introDeails.getName());
                }
                if (introDeails.getGrade() != null) {
                    grade.setText(introDeails.getGrade());
                }
                if (introDeails.getCompanyName() != null) {
                    companyName.setText(introDeails.getCompanyName());
                }
                if (introDeails.getGradeId()!=null) {
                    gradeId = introDeails.getGradeId();
                    integratedServicesViewModel.getGradeNameSpinner(gradeId);
                }

            }
        });
        integratedServicesViewModel.getJoiningBrachDetailsObsever().observe(this, joiningBranchDetals -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (joiningBranchDetals.size() > 0) {
                ArrayList<String> branchNamelist = new ArrayList<>();
                branchNamelist.clear();
                for (BranchDetails joiningBranchDetal : joiningBranchDetals) {
                    branchNamelist.add(joiningBranchDetal.getBranchName());
                }
                ArrayAdapter branchDetailsArrayAdapter = new ArrayAdapter(NewAgentJoiningActivity.this, android.R.layout.simple_list_item_1, branchNamelist);
                joiningBranch.setAdapter(branchDetailsArrayAdapter);
                joiningBranch.setOnItemClickListener((parent, view, position, id) -> {
                    branchId = joiningBranchDetals.get(position).getBranchId();
                });
            }
        });
        integratedServicesViewModel.getGradeNameSpinnerObserver().observe(this, gredeDetails -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (gredeDetails.size() > 0) {
                ArrayList<String> gradeList = new ArrayList<>();
                gradeList.clear();
                for (GradeDetails joiningBranchDetal : gredeDetails) {
                    gradeList.add(joiningBranchDetal.getGradeName());
                }
                ArrayAdapter branchDetailsArrayAdapter = new ArrayAdapter(NewAgentJoiningActivity.this, android.R.layout.simple_list_item_1, gradeList);
                gradeSpinner.setAdapter(branchDetailsArrayAdapter);
                gradeSpinner.setOnItemClickListener((parent, view, position, id) -> {
//                    branchId = gredeDetails.get(position).getGradeId();
                    grd.setText(gredeDetails.get(position).getGradeId());
                    rankId = gredeDetails.get(position).getGradeId();
                });
            }
        });
        integratedServicesViewModel.submitNewJoineeFinalDataObserver().observe(this, finalResponse -> {
            pb.setVisibility(View.GONE);
            Misc.enableScreenTouch(this);
            if (finalResponse.size() > 0) {
                NewJoiningFinalRespons newJoiningFinalRespons = finalResponse.get(0);
                number.setText(newJoiningFinalRespons.getNumber());
                name.setText(newJoiningFinalRespons.getName());
                introNumber.setText(newJoiningFinalRespons.getIntroNumber());
                intNa.setText(newJoiningFinalRespons.getIntroName());
                if (newJoiningFinalRespons.getCommencementFrom() != null) {
                    String commencementFrom = newJoiningFinalRespons.getCommencementFrom();
                    String[] ts = commencementFrom.split("T");
                    if (ts.length > 0) {
                        String formattedDate = getFormattedDate(ts[0]);
                        if (formattedDate != null) {
                            commencementDate.setText(formattedDate);

                        }
                    }

                }
                location.setText(newJoiningFinalRespons.getLocation());
                grade1.setText(newJoiningFinalRespons.getRank());
                introGrade.setText(newJoiningFinalRespons.getIntroRank());
                if (newJoiningFinalRespons.getRDate() != null) {
                    String rDate = newJoiningFinalRespons.getRDate();
                    String[] ts = rDate.split("T");
                    if (ts.length > 0) {
                        String formattedDate = getFormattedDate(ts[0]);
                        if (formattedDate != null) {
                            retirementDate.setText(formattedDate);
                        }
                    }
                }
                itemIssuedStatus.setText(newJoiningFinalRespons.getUnit());
                itemIssuedStatus.requestFocus();

                clearAllData();

            }
        });

    }

    private void clearAllData() {
        applicantName.setText("");
        phoneNumber.setText("");
        enrollAmount = null;
        dob.setText("");
        start = null;
        dateOfBirth = null;
        introducerCode.setText("");
        introducerName.setText("");
        grade.setText("");
        branchId = null;
        joiningBranch.setText("");
        gradeSpinner.setText("");
        grd.setText("");
        grade.setText("");
        companyName.setText("");
        companyId="";
        inputintroducerCode.setText("");
        withBag.setChecked(true);
        enrollAmount = "600";
        male.setChecked(true);
        gender = "M";
        rankId = null;


    }

    private String getFormattedDate(String strCurrentDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(newDate);
    }

    private void initView() {
        dob = findViewById(R.id.dob);
        phase = findViewById(R.id.phase);
        pb = findViewById(R.id.pb);
        inputintroducerCode = findViewById(R.id.inputintroducerCode);
        viewButton = findViewById(R.id.cvView);
        introducerCode = findViewById(R.id.introducerCode);
        introducerName = findViewById(R.id.introducerName);
        grade = findViewById(R.id.grade);
        companyName = findViewById(R.id.companyName);
        joiningBrachInputLayout = findViewById(R.id.joiningBrachInputLayout);
        joiningBranch = findViewById(R.id.joiningBranch);
        gradeSpinner = findViewById(R.id.gradeSpinner);
        applicantName = findViewById(R.id.applicantName);
        grd = findViewById(R.id.grd);
        ivBack = findViewById(R.id.ivBack);


        number = findViewById(R.id.number);
        name = findViewById(R.id.name);
        introNumber = findViewById(R.id.introNumber);
        intNa = findViewById(R.id.intNa);
        commencementDate = findViewById(R.id.commencementDate);
        location = findViewById(R.id.location);
        grade1 = findViewById(R.id.grade1);
        introGrade = findViewById(R.id.introGrade);
        retirementDate = findViewById(R.id.retirementDate);
        itemIssuedStatus = findViewById(R.id.itemIssuedStatus);
        submit = findViewById(R.id.submit);
        phoneNumber = findViewById(R.id.phoneNumber);

        genderGroup = findViewById(R.id.genderGroup);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        bagGroup = findViewById(R.id.bagGroup);
        withBag = findViewById(R.id.withBag);
        withoutBag = findViewById(R.id.withoutBag);

    }

    private void openDatePicker(TextView textView) {
        calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener sdt = (view, year, month, dayOfMonth) -> {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "dd MMM, yyyy"; //In which date Format needed
            String apiFormat = "yyyy-MM-dd"; //In which date Format needed
            SimpleDateFormat sdf = new SimpleDateFormat(apiFormat, Locale.ENGLISH);
            SimpleDateFormat sdfApiFormat = new SimpleDateFormat(apiFormat, Locale.ENGLISH);

            textView.setText(sdf.format(calendar.getTime()));

            if (textView == dob) {
                start = calendar.getTime();
            }

        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(NewAgentJoiningActivity.this, sdt, calendar
                .get(java.util.Calendar.YEAR), calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH));


//        Date dt = new Date();
//        Calendar c = Calendar.getInstance();
//        c.setTime(dt);
//        c.add(Calendar.DATE, 1);
//
        if (textView != dob) {
            datePickerDialog.getDatePicker().setMinDate(start.getTime());
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        datePickerDialog.getDatePicker().setMaxDate(date.getTime());

        datePickerDialog.show();

    }
}