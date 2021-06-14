package com.project.integratedservices.integratedServicesForAllTypes.view;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AvailablePlansResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PlanDetailsResponse;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailablePlansFragment extends Fragment implements AdapterView.OnItemSelectedListener, AvailalePlansAdapter.SelectedProduct {

    private RecyclerView rvAvailablePlans;
    private AppCompatSpinner spAvailablePlans;
    private List<String> availablePlans = new ArrayList<>();
    private List<PlanDetailsResponse> planDetailsResponses = new ArrayList<>();
    private IntegratedServicesViewModel integratedServicesViewModel;
    private String selectedPlanNmae = "";
    public AvailablePlansFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_available_plans, container, false);



        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.VISIBLE);
        ((DashboardActivity)getActivity()).tvCustomerText.setText(getResources().getString(R.string.available_plan));


        init(view);

        spAvailablePlans.setVisibility(View.INVISIBLE);
        rvAvailablePlans.setVisibility(View.INVISIBLE);

        if(Misc.isNetworkAvailable(getActivity())) {
            callGetAvailablePlansApi();
        }
        else
        {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("OK", ColorDialog->{
                        ColorDialog.dismiss();
                        getActivity().onBackPressed();
                    });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }

        integratedServicesViewModel.getAvailablePlans().observe(getActivity(),availablePlansResponse -> {
            try {
                ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(getActivity());
                availablePlans.clear();
                if (availablePlansResponse.size() > 0) {
                    spAvailablePlans.setVisibility(View.VISIBLE);
                    for (AvailablePlansResponse response : availablePlansResponse) {
                        availablePlans.add(response.getCompanyName());
                    }


                    ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, availablePlans) {
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);

                            Typeface externalFont = ResourcesCompat.getFont(getActivity(), R.font.roboto_bold);
                            ((TextView) v).setTypeface(externalFont);
                            ((TextView) v).setTextSize(18);
                            ((TextView) v).setTextColor(getActivity().getResources().getColor(R.color.grey));
//                v.setBackgroundColor(getActivity().getResources().getColor(R.color.white));

                            return v;
                        }


                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View v = super.getDropDownView(position, convertView, parent);

                            Typeface externalFont = ResourcesCompat.getFont(getActivity(), R.font.roboto_bold);
                            ((TextView) v).setTypeface(externalFont);
                            ((TextView) v).setTextSize(getActivity().getResources().getColor(R.color.grey));
                            v.setBackgroundColor(getActivity().getResources().getColor(R.color.white));

                            return v;
                        }
                    };
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spAvailablePlans.setAdapter(aa);
                    spAvailablePlans.setSelection(0, false);


                } else {
                    MyColorDialog.getInstance(getActivity()).setContentText("There are no available plans right now")
                            .setPositiveListener("OK", ColorDialog::dismiss)
                            .show();
                }
            }
            catch (Exception e)
            {e.printStackTrace();}
        });

        integratedServicesViewModel.getPlanDetails().observe(getActivity(),planDetailsResponses -> {
            try {
                ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(getActivity());

                if (planDetailsResponses.size() > 0) {
                    this.planDetailsResponses.clear();
                    rvAvailablePlans.setVisibility(View.VISIBLE);
                    this.planDetailsResponses.addAll(planDetailsResponses);

                    rvAvailablePlans.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    rvAvailablePlans.setAdapter(new AvailalePlansAdapter(getActivity(), this.planDetailsResponses, this));
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        integratedServicesViewModel.getApiError().observe(getActivity(),s -> {
            try {
                ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
                Misc.enableScreenTouch(getActivity());
                MyColorDialog.getInstance(getActivity()).setContentText(s)
                        .setPositiveListener("RETRY", colorDialog -> {
                            if (availablePlans.size() == 0) {
                                callGetAvailablePlansApi();
                            } else {
                                if (!selectedPlanNmae.equals(""))
                                    callGetDetailsApi(selectedPlanNmae);
                            }

                            colorDialog.dismiss();
                        })
                        .setNegativeListener("CANCEL", ColorDialog::dismiss)
                        .show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        return view;
    }

    private void callGetAvailablePlansApi() {
        ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.VISIBLE);
        Misc.disableScreenTouch(getActivity());
        integratedServicesViewModel.callAvailablePlans();
    }

    private void init(View view) {
        integratedServicesViewModel = ViewModelProviders.of(getActivity()).get(IntegratedServicesViewModel.class);
        rvAvailablePlans = view.findViewById(R.id.rvAvailablePlans);
        spAvailablePlans = view.findViewById(R.id.spAvailablePlans);


        spAvailablePlans.setOnItemSelectedListener(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPlanNmae=availablePlans.get(position);
        callGetDetailsApi(selectedPlanNmae);
//        Toast.makeText(getActivity(), ""+availablePlans.get(position), Toast.LENGTH_SHORT).show();
    }

    private void callGetDetailsApi(String selectedPlanName) {
        if(Misc.isNetworkAvailable(getActivity())) {
            rvAvailablePlans.setVisibility(View.INVISIBLE);
            ((DashboardActivity)getActivity()).spinKitView.setVisibility(View.VISIBLE);
            Misc.disableScreenTouch(getActivity());
            integratedServicesViewModel.callPlanDetails(selectedPlanName);
        }
        else
        {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("OK", ColorDialog->{
                ColorDialog.dismiss();
                getActivity().onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void selectedProductLink(PlanDetailsResponse detailsResponse) {
        startActivity(new Intent(getActivity(),PlanWebActivity.class).putExtra("product",detailsResponse));
    }
}
