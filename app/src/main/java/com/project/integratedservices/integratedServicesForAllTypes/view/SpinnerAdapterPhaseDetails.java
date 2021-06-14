package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PhaseDetailsResponse;

import java.util.List;

public class SpinnerAdapterPhaseDetails extends BaseAdapter {


    public static String selectedItemName, selectedItemId;
    private Context context;
    private List<PhaseDetailsResponse> phaseDetailsList;
    private LayoutInflater inflter;
    private SpinnerAdapterPhaseDetails.OnItemSelectListener mCallback;


    public SpinnerAdapterPhaseDetails(Context context, List<PhaseDetailsResponse> phaseDetailsList, SpinnerAdapterPhaseDetails.OnItemSelectListener mCallback) {
        this.context = context;
        this.phaseDetailsList = phaseDetailsList;
        this.mCallback = (SpinnerAdapterPhaseDetails.OnItemSelectListener) mCallback;
        inflter = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return phaseDetailsList.size();
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

        tvItem.setText(phaseDetailsList.get(i).getPhaseName());


        tvItem.setOnClickListener(view1 -> {
//            selectedItemName = phaseDetailsList.get(i).getBranchName();

            mCallback.selectedPhase(phaseDetailsList.get(i));
        });



        return view;
    }

    public interface OnItemSelectListener {
        <E> void selectedPhase(E selectedItem);
    }
}


