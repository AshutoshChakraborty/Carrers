package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISBusinessSummaryResponse;

import java.util.List;

public class MISBusinessSummaryAdapter extends RecyclerView.Adapter<MISBusinessSummaryAdapter.MyViewHolder> {

    private Context context;
    private List<MISBusinessSummaryResponse> misBusinessSummaryResponsesList;
    private int lastPosition = -1;

    public MISBusinessSummaryAdapter(Context context, List<MISBusinessSummaryResponse> misBusinessSummaryResponsesList) {
        this.context = context;
        this.misBusinessSummaryResponsesList = misBusinessSummaryResponsesList;
    }

    @NonNull
    @Override
    public MISBusinessSummaryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MISBusinessSummaryAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_summary_layout, parent, false));

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MISBusinessSummaryAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull MISBusinessSummaryAdapter.MyViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


        holder.tvBranchCode.setText(String.valueOf(misBusinessSummaryResponsesList.get(position).getBranchCode()));
        holder.tvBranchName.setText(misBusinessSummaryResponsesList.get(position).getBranchName());
        holder.tvBusinessType.setText(misBusinessSummaryResponsesList.get(position).getBusinessType());
        holder.tvCompanyName.setText(misBusinessSummaryResponsesList.get(position).getCompanyName());
        holder.tvNoOfPolicy.setText(String.valueOf(misBusinessSummaryResponsesList.get(position).getNoOfPolicy()));
        if (misBusinessSummaryResponsesList.get(position).getWeightage() != null) {
            holder.tvActualPremium.setText(String.valueOf(misBusinessSummaryResponsesList.get(position).getWeightage().toString()));
        } else {
            holder.tvActualPremium.setText("");
        }
        holder.tvPremiumAmount.setText(String.valueOf(misBusinessSummaryResponsesList.get(position).getPremiumAmt()));
       /* if (misBusinessSummaryResponsesList.get(position).getBusinessDate() != null) {
            holder.tv_bussiness_date.setText(String.valueOf(misBusinessSummaryResponsesList.get(position).getBusinessDate().toString()));
        } else {
            holder.tv_bussiness_date.setText("");
        }*/
    }

    @Override
    public int getItemCount() {
        return misBusinessSummaryResponsesList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvBranchCode, tvBusinessType,tvBranchName,tvCompanyName, tvNoOfPolicy,tvActualPremium, tvPremiumAmount/*,tv_bussiness_date*/;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBranchCode = itemView.findViewById(R.id.tv_branch_code);
            tvBranchName = itemView.findViewById(R.id.tv_branch_name);
            tvBusinessType = itemView.findViewById(R.id.tv_branch_type);
            tvCompanyName = itemView.findViewById(R.id.tv_company_name);
            tvNoOfPolicy = itemView.findViewById(R.id.tv_no_of_policy);
            tvActualPremium = itemView.findViewById(R.id.tv_actual_premium);
            tvPremiumAmount = itemView.findViewById(R.id.tv_premium_amount);
//            tv_bussiness_date = itemView.findViewById(R.id.tv_bussiness_date);



        }
    }
}


