package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.project.integratedservices.R;

import java.util.List;

public class SpinnerAdapterAgeProof extends BaseAdapter {


    public static String selectedItemName, selectedItemId;
    private Context context;
    private List<String> stringList;
    private LayoutInflater inflter;
    private SpinnerAdapterAgeProof.OnAgeSelectListener mCallback;


    public SpinnerAdapterAgeProof(Context context, List<String> listCompanyName, CustomerDetailsActivity mCallback) {
        this.context = context;
        stringList = listCompanyName;
        this.mCallback = (SpinnerAdapterAgeProof.OnAgeSelectListener) mCallback;
        inflter = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return stringList.size();
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

        tvItem.setText(stringList.get(i));


        tvItem.setOnClickListener(view1 -> {
            selectedItemName = stringList.get(i);

            mCallback.selectedAgeProof(stringList.get(i));
        });



        return view;
    }

    public interface OnAgeSelectListener {
        <E> void selectedAgeProof(E selectedItem);
    }
}
