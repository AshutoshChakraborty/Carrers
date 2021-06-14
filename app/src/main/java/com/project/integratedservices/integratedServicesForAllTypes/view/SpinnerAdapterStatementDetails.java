package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.StatementDetailsResponse;

import java.util.List;

public class SpinnerAdapterStatementDetails extends BaseAdapter {


    public static String selectedItemName, selectedItemId;
    private Context context;
    private List<StatementDetailsResponse> statementDetailsList;
    private LayoutInflater inflter;
    private SpinnerAdapterStatementDetails.OnItemSelectListener mCallback;


    public SpinnerAdapterStatementDetails(Context context, List<StatementDetailsResponse> statementDetailsList, SpinnerAdapterStatementDetails.OnItemSelectListener mCallback) {
        this.context = context;
        this.statementDetailsList = statementDetailsList;
        this.mCallback = (SpinnerAdapterStatementDetails.OnItemSelectListener) mCallback;
        inflter = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return statementDetailsList.size();
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

        tvItem.setText(statementDetailsList.get(i).getDESCRIPTION());


        tvItem.setOnClickListener(view1 -> {
//            selectedItemName = statementDetailsList.get(i).getBranchName();

            mCallback.selectedStatement(statementDetailsList.get(i));
        });



        return view;
    }

    public interface OnItemSelectListener {
        <E> void selectedStatement(E selectedItem);
    }
}


