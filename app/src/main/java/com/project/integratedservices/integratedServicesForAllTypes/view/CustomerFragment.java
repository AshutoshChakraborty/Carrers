package com.project.integratedservices.integratedServicesForAllTypes.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.FollowUpCustomersResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.NewCustomerListResponsePojo;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;

import static com.project.supportClasses.SharedPref.AGENT_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment implements CategoriesAdapter.SendSelectedValue {


    private RecyclerView rvCategories, rvCustomerList;
    private CategoriesAdapter adapter;
    private ArrayList<String> categories = new ArrayList<>();
    private IntegratedServicesViewModel integratedServicesViewModel;
    private List<NewCustomerListResponsePojo> newCustomerList = new ArrayList<>();
    private List<FollowUpCustomersResponsePojo> followUpCustomerList = new ArrayList<>();
    private CustomerListAdapter customerListAdapter = null;
    private FollowUpListAdapter followUpListAdapter = null;
    private int currentPos;
    private Boolean onResumeExecuted = false;

    public CustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        onResumeExecuted = true;
        getNewCustomersList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_iscustomer, container, false);
        ((DashboardActivity) getActivity()).tvCustomerText.setVisibility(View.VISIBLE);
        ((DashboardActivity) getActivity()).tvCustomerText.setText(getResources().getString(R.string.customer));

        init(view);

        ((DashboardActivity)getActivity()).floatingActionButtonAdd.setVisibility(View.VISIBLE);

        categories.clear();
        categories.add("NEW");
        categories.add("FOLLOW UP");
        categories.add("CLOSED");



        integratedServicesViewModel.getNewCustomerListResponse().observe(this,newCustomerListResponsePojos -> {
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());

            newCustomerList.clear();
            if(!newCustomerListResponsePojos.get(0).getStatus().equalsIgnoreCase("Unsuccessful"))
            {
            newCustomerList.addAll(newCustomerListResponsePojos);
            customerListAdapter.notifyDataSetChanged();

            }
            else
            {
//                Toast.makeText(getActivity(), ""+newCustomerListResponsePojos.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), R.string.no_items_found, Toast.LENGTH_SHORT).show();
            }
        });

        integratedServicesViewModel.getFollowupCustomerList().observe(this,followUpCustomersResponsePojos -> {
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.GONE);
            Misc.enableScreenTouch(getActivity());

            followUpCustomerList.clear();
            followUpCustomerList.addAll(followUpCustomersResponsePojos);
            followUpListAdapter.notifyDataSetChanged();
        });

        if(!onResumeExecuted)
        getNewCustomersList();
        else
            onResumeExecuted = false;

        return view;
    }

    private void init(View view) {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        rvCategories = view.findViewById(R.id.rvCategories);
        rvCustomerList = view.findViewById(R.id.rvCustomerList);

        rvCustomerList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        rvCategories.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new CategoriesAdapter(getActivity(), categories, this);
        rvCategories.setAdapter(adapter);

        customerListAdapter = new CustomerListAdapter(getActivity(),newCustomerList);
        rvCustomerList.setAdapter(customerListAdapter);
    }

    @Override
    public void getItemClicked(int pos) {
        rvCategories.smoothScrollToPosition(pos);

        if (pos == 0)
        {
            ((DashboardActivity)getActivity()).floatingActionButtonAdd.setVisibility(View.VISIBLE);
            ((DashboardActivity)getActivity()).floatingActionButtonSearch.setVisibility(View.GONE);

            if(followUpCustomerList.size()>0) {
                followUpCustomerList.clear();
                followUpListAdapter.notifyDataSetChanged();
            }

//            customerListAdapter = new CustomerListAdapter(getActivity(),newCustomerList);
//            rvCustomerList.setAdapter(customerListAdapter);

            newCustomerList.clear();
            customerListAdapter.notifyDataSetChanged();

            if(pos!=currentPos)
            getNewCustomersList();

        }
        else if (pos == 2) {
            ((DashboardActivity)getActivity()).floatingActionButtonAdd.setVisibility(View.GONE);
//            ((DashboardActivity)getActivity()).floatingActionButtonSearch.setVisibility(View.VISIBLE);

            newCustomerList.clear();
            customerListAdapter.notifyDataSetChanged();

        } else if (pos == 1) {
            ((DashboardActivity)getActivity()).floatingActionButtonAdd.setVisibility(View.GONE);
            ((DashboardActivity)getActivity()).floatingActionButtonSearch.setVisibility(View.GONE);

            newCustomerList.clear();
            customerListAdapter.notifyDataSetChanged();

            followUpListAdapter = new FollowUpListAdapter(getActivity(),followUpCustomerList);
            rvCustomerList.setAdapter(customerListAdapter);

            getFollowUpCustomersList();
        }

        currentPos = pos;
    }

    @Override
    public void onDestroy() {
        ((DashboardActivity) getActivity()).tvCustomerText.setVisibility(View.GONE);
        ((DashboardActivity)getActivity()).floatingActionButtonAdd.setVisibility(View.GONE);
        ((DashboardActivity)getActivity()).floatingActionButtonSearch.setVisibility(View.GONE);
        super.onDestroy();
    }

    private void getNewCustomersList()
    {
        if (Misc.isNetworkAvailable(getActivity())) {

            if(customerListAdapter==null)
            {
                customerListAdapter = new CustomerListAdapter(getActivity(),newCustomerList);
                rvCustomerList.setAdapter(customerListAdapter);
            }
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
            Misc.disableScreenTouch(getActivity());
            integratedServicesViewModel.getNewCustomers(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                ColorDialog.dismiss();
                getNewCustomersList();
            });
            colorDialog.setNegativeListener("CANCEL",colorDialog1 -> {
                colorDialog1.dismiss();
                getActivity().onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }

    private void getFollowUpCustomersList()
    {
        if (Misc.isNetworkAvailable(getActivity())) {
            ((DashboardActivity) getActivity()).spinKitView.setVisibility(View.VISIBLE);
            Misc.disableScreenTouch(getActivity());
            integratedServicesViewModel.getFollowUpCustomers(SharedPref.getInstance(getActivity()).getData(AGENT_ID));
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(getActivity());
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                ColorDialog.dismiss();
                getFollowUpCustomersList();
            });
            colorDialog.setNegativeListener("CANCEL",colorDialog1 -> {
                colorDialog1.dismiss();
                getActivity().onBackPressed();
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }
}
