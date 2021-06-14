package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISCompanyDetailsResponse;

import java.util.List;

public class SpinnerAdapterBusinessType extends BaseAdapter {


    public static String selectedItemName, selectedItemId;
    private Context context;
    private List<String> businessTypeList;
    private LayoutInflater inflter;
    private SpinnerAdapterBusinessType.OnItemSelectListener mCallback;


    public SpinnerAdapterBusinessType(Context context, List<String> businessTypeList, SpinnerAdapterBusinessType.OnItemSelectListener mCallback) {
        this.context = context;
        this.businessTypeList = businessTypeList;
        this.mCallback = (SpinnerAdapterBusinessType.OnItemSelectListener) mCallback;
        inflter = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return businessTypeList.size();
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

        tvItem.setText(businessTypeList.get(i));


        tvItem.setOnClickListener(view1 -> {
//            selectedItemName = businessTypeList.get(i).getBranchName();

            mCallback.selectedBusinessType(businessTypeList.get(i));
        });



        return view;
    }

    public interface OnItemSelectListener {
        <E> void selectedBusinessType(E selectedItem);
    }
}


