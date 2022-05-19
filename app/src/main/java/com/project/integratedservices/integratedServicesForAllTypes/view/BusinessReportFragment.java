package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.BusinessReportRequestPojo;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.AGENT_ID;


public class BusinessReportFragment extends Fragment {

    private RecyclerView rvBusinessReport;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private BusinessReportAdapter businessReportAdapter = null;
    private MaterialCardView materialCardViewSelf,materialCardViewTeam;
    private Calendar datecalendar;
    private LinearLayout llFrom,llTo;
    private AppCompatTextView tvFromDate,tvToDate,tvFresh,tvRenewal;
    private AppCompatImageView ivTickSelfReport,ivTickTeamReport;
    private AppCompatButton btCheck;
    private RadioButton rd_all,rd_agent;
    private EditText et_agentcode;
    private String agentCode = "0";
    public BusinessReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        ((DashboardActivity) getActivity()).tvCustomerText.setVisibility(View.VISIBLE);
        ((DashboardActivity) getActivity()).tvCustomerText.setText(getResources().getString(R.string.business_report));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_report, container, false);

        init(view);

        MyColorDialog.getInstance(getActivity()).setContentText("Choose date range and select the type of report you want to view.")
                .setPositiveListener("OK", ColorDialog::dismiss)
                .show();

        integratedServicesViewModel.getBusinessReportResponse().observe(this,businessReportResponsePojos -> {
            try {
                ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(getActivity());

                if(businessReportResponsePojos.get(0).getStatus().equals("Success")) {
                    rvBusinessReport.setVisibility(View.VISIBLE);
                    businessReportAdapter = new BusinessReportAdapter(getActivity(),businessReportResponsePojos);

                    tvRenewal.setText(businessReportResponsePojos.get(0).getRenewal());
                    tvFresh.setText(businessReportResponsePojos.get(0).getFresh());
                }
                else if(businessReportResponsePojos.get(0).getStatus().equals("UnSuccess")) {
                    rvBusinessReport.setVisibility(View.GONE);
                }
                else if (!(businessReportResponsePojos.get(0).getStatus().equalsIgnoreCase("Success") || businessReportResponsePojos.get(0).getStatus().equalsIgnoreCase("UnSuccess"))) {
                    ColorDialog colorDialog = MyColorDialog.getInstance(getContext());
                    colorDialog.setContentText(businessReportResponsePojos.get(0).getStatus());
                    colorDialog.setCancelable(true);
                    colorDialog.setAnimationEnable(true);
                    colorDialog.show();

                    rvBusinessReport.setVisibility(View.GONE);
                }

//
//                {
//                    businessReportResponsePojos.clear();
//                    businessReportAdapter = new BusinessReportAdapter(getActivity(), businessReportResponsePojos);
//
//                    Toast.makeText(getActivity(), R.string.no_items_found, Toast.LENGTH_SHORT).show();
//                }

                rvBusinessReport.setAdapter(businessReportAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        materialCardViewSelf.setOnClickListener(v -> {
//            if(!isDateFieldsEmpty()) {
//                rvBusinessReport.setVisibility(View.INVISIBLE);
//                ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.VISIBLE);
//                Misc.disableScreenTouch(getActivity());
//                BusinessReportRequestPojo requestPojo = new BusinessReportRequestPojo();
//                requestPojo.setAgentCode(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
//                requestPojo.setStartDate(tvFromDate.getText().toString());
//                requestPojo.setEndDate(tvToDate.getText().toString());
//                requestPojo.setType("1");
//                integratedServicesViewModel.getBusinessReport(requestPojo);
//            }
//            else
//                Toast.makeText(getActivity(), "Select from & to date", Toast.LENGTH_SHORT).show();

            ivTickTeamReport.setVisibility(View.GONE);
            ivTickSelfReport.setVisibility(View.VISIBLE);

        });

        materialCardViewTeam.setOnClickListener(v -> {
//            if(!isDateFieldsEmpty()) {
//                rvBusinessReport.setVisibility(View.INVISIBLE);
//                ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
//                Misc.disableScreenTouch(getActivity());
//                BusinessReportRequestPojo requestPojo = new BusinessReportRequestPojo();
//                requestPojo.setAgentCode(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
//                requestPojo.setStartDate(tvFromDate.getText().toString());
//                requestPojo.setEndDate(tvToDate.getText().toString());
//                requestPojo.setType("2");
//                integratedServicesViewModel.getBusinessReport(requestPojo);
//            }
//            else
//                Toast.makeText(getActivity(), "Select from & to date", Toast.LENGTH_SHORT).show();

            ivTickSelfReport.setVisibility(View.GONE);
            ivTickTeamReport.setVisibility(View.VISIBLE);

        });

        integratedServicesViewModel.getApiError().observe(this,s -> {
            try {
                ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(getActivity());
                Toast.makeText(getActivity(), "" + s, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }


        });

        llFrom.setOnClickListener(v -> {
            datecalendar = Calendar.getInstance();
            openDatePicker(tvFromDate,tvToDate,true);
        });

        llTo.setOnClickListener(v -> {
            datecalendar = Calendar.getInstance();
            openDatePicker(tvToDate,tvFromDate,false);
        });

        btCheck.setOnClickListener(v -> {
            if(!isDateFieldsEmpty()) {
                if(ivTickTeamReport.getVisibility()==View.VISIBLE || ivTickSelfReport.getVisibility()==View.VISIBLE) {
                    if (rd_all.isChecked()) {
                        agentCode = "0";
                    } else {
                        if (et_agentcode.getText().toString().isEmpty()) {
                            agentCode = "0";
                        } else {
                            agentCode = et_agentcode.getText().toString();
                        }
                    }
                    rvBusinessReport.setVisibility(View.INVISIBLE);
                    ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
                    Misc.disableScreenTouch(getActivity());
                    BusinessReportRequestPojo requestPojo = new BusinessReportRequestPojo();
                    requestPojo.setAgentCode(agentCode);
                    requestPojo.setStartDate(tvFromDate.getText().toString());
                    requestPojo.setEndDate(tvToDate.getText().toString());
                    if (ivTickTeamReport.getVisibility() == View.VISIBLE)
                        requestPojo.setType("2");
                    else
                        requestPojo.setType("1");


                    String loggedInAgentsId = SharedPref.getInstance(getActivity()).getData(AGENT_ID);
                    integratedServicesViewModel.getBusinessReport(requestPojo,loggedInAgentsId);
                }
                else
                {
                    Toast.makeText(getActivity(), "Select the type of report you want to view", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(getActivity(), "Select from & to date", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void init(View view) {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        rvBusinessReport = view.findViewById(R.id.rvBusinessReport);
        materialCardViewSelf = view.findViewById(R.id.materialCardViewSelf);
        materialCardViewTeam = view.findViewById(R.id.materialCardViewTeam);
        llFrom = view.findViewById(R.id.llFrom);
        llTo = view.findViewById(R.id.llTo);
        tvFromDate = view.findViewById(R.id.tvFromDate);
        tvToDate = view.findViewById(R.id.tvToDate);
        tvFresh = view.findViewById(R.id.tvFreshValue);
        tvRenewal = view.findViewById(R.id.tvRenewalValue);
        ivTickSelfReport = view.findViewById(R.id.ivTickSelfReport);
        ivTickTeamReport = view.findViewById(R.id.ivTickTeamReport);
        btCheck = view.findViewById(R.id.btCheck);
        rd_all = view.findViewById(R.id.rd_all);
        rd_agent = view.findViewById(R.id.rd_agent);
        et_agentcode = view.findViewById(R.id.et_agentcode);
        et_agentcode.setEnabled(false);


        rvBusinessReport.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvBusinessReport.setVisibility(View.INVISIBLE);
        rd_all.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rd_agent.setChecked(false);
                et_agentcode.setEnabled(false);
                et_agentcode.getEditableText().clear();
            }
        });
        rd_agent.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rd_all.setChecked(false);
                et_agentcode.setEnabled(true);
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.GONE);
    }


    private void openDatePicker(AppCompatTextView textView,AppCompatTextView tvToBeChecked,boolean isFrom) {

        final DatePickerDialog.OnDateSetListener sdt = (view, year, month, dayOfMonth) -> {

            datecalendar.set(Calendar.YEAR, year);
            datecalendar.set(Calendar.MONTH, month);
            datecalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "dd MMM yyyy"; //In which date Format needed
            String apiFormat = "yyyy-MM-dd"; //In which date Format needed
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
            SimpleDateFormat sdfApiFormat = new SimpleDateFormat(apiFormat, Locale.ENGLISH);

//            apiDateFormat = sdfApiFormat.format(datecalendar.getStartTime());

            if(isFrom && getDateFromString(tvToBeChecked)!=null && datecalendar.getTime().after(getDateFromString(tvToBeChecked))) {
                Toast.makeText(getActivity(), "From date cannot be after to date", Toast.LENGTH_SHORT).show();
                textView.setText("");
            }
            else if(isFrom)
            {
                textView.setText(sdfApiFormat.format(datecalendar.getTime()));
            }
            else if(!isFrom && getDateFromString(tvToBeChecked)!=null && datecalendar.getTime().before(getDateFromString(tvToBeChecked)))
            {
                Toast.makeText(getActivity(), "To date cannot be before from date", Toast.LENGTH_SHORT).show();
                textView.setText("");
            }
            else if(!isFrom)
            {
                textView.setText(sdfApiFormat.format(datecalendar.getTime()));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), sdt, datecalendar
                .get(java.util.Calendar.YEAR), datecalendar.get(java.util.Calendar.MONTH),
                datecalendar.get(java.util.Calendar.DAY_OF_MONTH));

     /*   //calculation of 18 years from current time
        Calendar cal = Calendar.getInstance();
//            cal.set(2015, Calendar.JUNE, 01); // Comment this out for today...
        cal.add(Calendar.YEAR, -18);
        cal.add(Calendar.DATE, -1);
        Date date = cal.getStartTime();
        System.out.println("@@@18 "+new SimpleDateFormat("dd/MM/yyyy").format(date));

        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis() - 1000);*/
//        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() - 1000);
        datePickerDialog.show();

    }

    private Date getDateFromString(TextView tvCheckOut) {
        Date date = null;
        if (!tvCheckOut.getText().toString().isEmpty()) {
            String apiFormat = "yyyy-MM-dd";
            SimpleDateFormat sdfApiFormat = new SimpleDateFormat(apiFormat, Locale.ENGLISH);
            try {
                date = sdfApiFormat.parse(tvCheckOut.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    private boolean isDateFieldsEmpty()
    {
        if(tvToDate.getText().toString().trim().isEmpty())
            return  true;
        else if(tvFromDate.getText().toString().trim().isEmpty())
            return true;
        else
            return false;
    }

}
