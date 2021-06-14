package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BranchWiseBusinessResponse;

import java.util.List;

public class BranchWiseBusinessAdapter extends RecyclerView.Adapter<BranchWiseBusinessAdapter.MyViewHolder> {

    private Context context;
    private List<BranchWiseBusinessResponse> branchWiseBusinessResponses;
    private int lastPosition = -1;

    public BranchWiseBusinessAdapter(Context context, List<BranchWiseBusinessResponse> businessReportResponse) {
        this.context = context;
        this.branchWiseBusinessResponses = businessReportResponse;
    }

    @NonNull
    @Override
    public BranchWiseBusinessAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BranchWiseBusinessAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_branch_wise_business_layout, parent, false));

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BranchWiseBusinessAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull BranchWiseBusinessAdapter.MyViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


            holder.tvBranchName.setText(branchWiseBusinessResponses.get(position).getBranch());
            holder.tvCompanyName.setText(branchWiseBusinessResponses.get(position).getCompanyName());
            holder.tvDate.setText(branchWiseBusinessResponses.get(position).getDate().substring(0,10));
            holder.tvAgentCode.setText(branchWiseBusinessResponses.get(position).getAgent());
            holder.tvApplnNo.setText(branchWiseBusinessResponses.get(position).getApplnNo());
            holder.tvProposerName.setText(branchWiseBusinessResponses.get(position).getProposerName());
            holder.tvPlanName.setText(branchWiseBusinessResponses.get(position).getPlanName());
            holder.tvBusinessType.setText(branchWiseBusinessResponses.get(position).getBusinessType());
            holder.tvPremiumFrequency.setText(branchWiseBusinessResponses.get(position).getPremiumFrequency());


    }

    @Override
    public int getItemCount() {
        return branchWiseBusinessResponses.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvBranchName, tvCompanyName, tvDate, tvAgentCode, tvApplnNo,tvProposerName,tvPlanName,tvBusinessType,tvPremiumFrequency;
        LinearLayoutCompat llparent;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBranchName = itemView.findViewById(R.id.tv_branch_name);
            tvCompanyName = itemView.findViewById(R.id.tv_company_name);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvAgentCode = itemView.findViewById(R.id.tv_agent_code);
            tvApplnNo = itemView.findViewById(R.id.tv_appln_no);
            tvProposerName = itemView.findViewById(R.id.tv_proposer_name);
            tvPlanName = itemView.findViewById(R.id.tv_plan_name);
            tvBusinessType = itemView.findViewById(R.id.tv_business_type);
            tvPremiumFrequency = itemView.findViewById(R.id.tv_premium_frequency);

        }
    }
}
