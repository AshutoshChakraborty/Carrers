package com.project.integratedservices.integratedServicesForAllTypes.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PremiumCalculatorList;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class PremiumCalculatorFragment extends Fragment implements PremiumCalculatorAdapter.SelectedInsurer {

    private RecyclerView rvItems;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private List<PremiumCalculatorList> premiumCalculatorLists = new ArrayList<>();
    private PremiumCalculatorAdapter adapter = null;
    public PremiumCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_premium_calculator, container, false);

        
        
        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.VISIBLE);
        ((DashboardActivity)getActivity()).tvCustomerText.setText(getResources().getString(R.string.premium_calculator));

        init(view);


        if(Misc.isNetworkAvailable(getActivity())) {
            fetchListing();
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

        integratedServicesViewModel.getPremiumCalculatorlistResponse().observe(this,premiumCalculatorLists -> {
            if(premiumCalculatorLists.get(0).getStatus().equals("Successful")) {
                this.premiumCalculatorLists.clear();
                this.premiumCalculatorLists.addAll(premiumCalculatorLists);
                adapter.notifyDataSetChanged();
            }
        });
        
    return  view;
    }

    private void fetchListing() {
        integratedServicesViewModel.getPremiumCalculatorList();
    }

    private void init(View view) {
        rvItems = view.findViewById(R.id.rvItems);
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);

        adapter = new PremiumCalculatorAdapter(getActivity(),premiumCalculatorLists,this);
        rvItems.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvItems.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DashboardActivity)getActivity()).tvCustomerText.setVisibility(View.GONE);
    }

    @Override
    public void selectedInsurerLink(PremiumCalculatorList detailsResponse) {
        startActivity(new Intent(getActivity(),PlanWebActivity.class).putExtra("premium",detailsResponse));
    }
}
