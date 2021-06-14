package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BranchDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.CompanyNamesResponsePojo;

import java.util.List;

public class SpinnerAdapterBranchName extends BaseAdapter {


    public static String selectedItemName, selectedItemId;
    private Context context;
    private List<BranchDetailsResponse> branchDetailsList;
    private LayoutInflater inflter;
    private SpinnerAdapterBranchName.OnItemSelectListener mCallback;


    public SpinnerAdapterBranchName(Context context, List<BranchDetailsResponse> branchDetailsList, SpinnerAdapterBranchName.OnItemSelectListener mCallback) {
        this.context = context;
        this.branchDetailsList = branchDetailsList;
        this.mCallback = (SpinnerAdapterBranchName.OnItemSelectListener) mCallback;
        inflter = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return branchDetailsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_statespinner_layout, null);
        AppCompatTextView tvItem = view.findViewById(R.id.tvItems);

        tvItem.setText(branchDetailsList.get(i).getBranchName());


        tvItem.setOnClickListener(view1 -> {
//            selectedItemName = branchDetailsList.get(i).getBranchName();

            mCallback.selectedBranchId(branchDetailsList.get(i));
        });



        return view;
    }

    public interface OnItemSelectListener {
        <E> void selectedBranchId(E selectedItem);
    }
}

