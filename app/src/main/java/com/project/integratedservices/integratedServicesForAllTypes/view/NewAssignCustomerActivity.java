package com.project.integratedservices.integratedServicesForAllTypes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.textfield.TextInputEditText;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AssignCustomerActivityRequestPojo;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.project.supportClasses.SharedPref.AGENT_ID;

public class NewAssignCustomerActivity extends AppCompatActivity {

    private AppCompatImageView ivBack;
    private AppCompatTextView subHeader;
    private String agentCode;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private TextInputEditText etCustomerName,etAddress,etPinCode,etEmailAddress,etMobileNumber;
    private AppCompatTextView tvConfirm;
    private SpinKitView spin_kit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_assign_customer);

        init();

        if(getIntent().hasExtra("Agent_Code"))
        {
            agentCode = getIntent().getStringExtra("Agent_Code");
        }

        ivBack.setOnClickListener(v -> onBackPressed());
        tvConfirm.setOnClickListener(v -> {
            if(Misc.isNetworkAvailable(this))
            {
                if(isValid())
                {
                    spin_kit.setVisibility(View.VISIBLE);
                    Misc.disableScreenTouch(this);
                    AssignCustomerActivityRequestPojo requestPojo = new AssignCustomerActivityRequestPojo();
                    requestPojo.setAgentCode(SharedPref.getInstance(this).getData(AGENT_ID));
                    requestPojo.setAssignAgentCode(agentCode);
                    requestPojo.setCustomerName(etCustomerName.getText().toString().trim());
                    requestPojo.setAddress(etAddress.getText().toString().trim());
                    requestPojo.setMobileNumber(etMobileNumber.getText().toString().trim());
                    requestPojo.setPinCode(etPinCode.getText().toString().trim());
                    if(etEmailAddress.getText().toString().trim().length()==0)
                    requestPojo.setEmailID(" ");
                    else
                    requestPojo.setEmailID(etEmailAddress.getText().toString().trim());

                    integratedServicesViewModel.assignNewCustomerRequest(requestPojo);

                }
            }
            else
            {
                Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
            }
        });

        integratedServicesViewModel.getAssignCustomerLiveData().observe(this,assignCustomerResponsePojos -> {
            spin_kit.setVisibility(View.GONE);
            Misc.enableScreenTouch(NewAssignCustomerActivity.this);
            if(assignCustomerResponsePojos.size()>0 && assignCustomerResponsePojos.get(0).getStatus().equals("Successfull"))
            {
                Toast.makeText(this, assignCustomerResponsePojos.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                if(assignCustomerResponsePojos.size()>0)
                Toast.makeText(this, assignCustomerResponsePojos.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();

            }
        });

        integratedServicesViewModel.getApiError().observe(this,s -> {
            spin_kit.setVisibility(View.GONE);
            Misc.enableScreenTouch(NewAssignCustomerActivity.this);
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isValid() {
        boolean isValid = true;

        if(etCustomerName.getText().toString().trim().isEmpty())
        {
            isValid = false;
            Toast.makeText(this, "Please enter customer name", Toast.LENGTH_SHORT).show();
        }
        else if(etAddress.getText().toString().trim().isEmpty())
        {
            isValid = false;
            Toast.makeText(this, "Please enter address", Toast.LENGTH_SHORT).show();
        }else if(etPinCode.getText().toString().trim().isEmpty())
        {
            isValid = false;
            Toast.makeText(this, "Please enter pincode", Toast.LENGTH_SHORT).show();
        }
        else if(etMobileNumber.getText().toString().trim().isEmpty())
        {
            isValid = false;
            Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
        }
        else  if(!etEmailAddress.getText().toString().trim().isEmpty())
        {
            if(!isEmailValid(etEmailAddress.getText().toString().trim())) {
                isValid = false;
                Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            }
        }

        return isValid;
    }

    private void init() {
        ivBack = findViewById(R.id.iv_drawer_menu);
        subHeader = findViewById(R.id.textView2);
        etCustomerName = findViewById(R.id.etCustomerName);
        etAddress = findViewById(R.id.etAddress);
        etPinCode = findViewById(R.id.etPinCode);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        tvConfirm = findViewById(R.id.tvConfirm);
        spin_kit = findViewById(R.id.spin_kit);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);

        subHeader.setVisibility(View.VISIBLE);
        subHeader.setText(getResources().getString(R.string.new_assign_customer));
        ivBack.setImageResource(R.drawable.ic_left_arrow);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        subHeader.setVisibility(View.GONE);
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
