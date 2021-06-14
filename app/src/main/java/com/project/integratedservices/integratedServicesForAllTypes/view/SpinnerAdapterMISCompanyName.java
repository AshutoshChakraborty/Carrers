package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BranchDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISCompanyDetailsResponse;

import java.util.List;

public class SpinnerAdapterMISCompanyName extends BaseAdapter {


    public static String selectedItemName, selectedItemId;
    private Context context;
    private List<MISCompanyDetailsResponse> companyNamesList;
    private LayoutInflater inflter;
    private SpinnerAdapterMISCompanyName.OnItemSelectListener mCallback;


    public SpinnerAdapterMISCompanyName(Context context, List<MISCompanyDetailsResponse> listCompanyName, SpinnerAdapterMISCompanyName.OnItemSelectListener mCallback) {
        this.context = context;
        companyNamesList = listCompanyName;
        this.mCallback = (SpinnerAdapterMISCompanyName.OnItemSelectListener) mCallback;
        inflter = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return companyNamesList.size();
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

        tvItem.setText(companyNamesList.get(i).getCompanyName());


        tvItem.setOnClickListener(view1 -> {
//            selectedItemName = companyNamesList.get(i).getBranchName();

            mCallback.selectedCompany(companyNamesList.get(i));
        });



        return view;
    }

    public interface OnItemSelectListener {
        <E> void selectedCompany(E selectedItem);
    }
}

