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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISCollectionRegisterResponse;

import java.util.List;

public class CollectionReportAdapter  extends RecyclerView.Adapter<CollectionReportAdapter.MyViewHolder> {

    private Context context;
    private List<MISCollectionRegisterResponse> misCollectionRegisterResponsesList;
    private int lastPosition = -1;

    public CollectionReportAdapter(Context context, List<MISCollectionRegisterResponse> misCollectionRegisterResponsesList) {
        this.context = context;
        this.misCollectionRegisterResponsesList = misCollectionRegisterResponsesList;
    }

    @NonNull
    @Override
    public CollectionReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectionReportAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection_summary_layout, parent, false));

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CollectionReportAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionReportAdapter.MyViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


        holder.tvAgentCode.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getAgentCode()));
        holder.tvName.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getName()));
        holder.tvBranchCode.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBranchCode()));
        holder.tvBranchName.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBranchName()));
        holder.tvBusinessType.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBusinessType()));
        holder.tvCompanyName.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getCompanyName()));
        holder.tvActualPremium.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getActualPremium()));
        holder.tvPremiumAmount.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getPremiumAmt()));


    }

    @Override
    public int getItemCount() {
        return misCollectionRegisterResponsesList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvAgentCode,tvName, tvBranchCode,tvBranchName,tvBusinessType,tvCompanyName,tvActualPremium,tvPremiumAmount;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAgentCode = itemView.findViewById(R.id.tv_agent_code);
            tvName = itemView.findViewById(R.id.tv_name);
            tvBranchCode = itemView.findViewById(R.id.tv_branch_code);
            tvBranchName = itemView.findViewById(R.id.tv_branch_name);
            tvBusinessType = itemView.findViewById(R.id.tv_business_type);
            tvCompanyName = itemView.findViewById(R.id.tv_company_name);
            tvActualPremium = itemView.findViewById(R.id.tv_actual_premium);
            tvPremiumAmount = itemView.findViewById(R.id.tv_premium_amount);



        }
    }
}



