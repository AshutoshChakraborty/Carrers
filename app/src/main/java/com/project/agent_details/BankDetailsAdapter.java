package com.project.agent_details;

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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISIndividualBusinessResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_bank_detail.BankDetailResponse;

import java.util.List;

public class BankDetailsAdapter extends RecyclerView.Adapter<BankDetailsAdapter.MyViewHolder> {

    private Context context;
    private List<BankDetailResponse> misIndividualBusinessReportList;
    private int lastPosition = -1;

    public BankDetailsAdapter(Context context, List<BankDetailResponse> misIndividualBusinessReportList) {
        this.context = context;
        this.misIndividualBusinessReportList = misIndividualBusinessReportList;
    }

    @NonNull
    @Override
    public BankDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BankDetailsAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_details_child_layout, parent, false));

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BankDetailsAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull BankDetailsAdapter.MyViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


        holder.tvCode.setText(String.valueOf(misIndividualBusinessReportList.get(position).getBankName()));
        holder.tvName.setText(misIndividualBusinessReportList.get(position).getBranch());
        holder.tvBranchName.setText(misIndividualBusinessReportList.get(position).getIfsc());
        holder.tvCompanyName.setText(misIndividualBusinessReportList.get(position).getAccntNo());
        holder.tvDate.setText(misIndividualBusinessReportList.get(position).getMicr());
        holder.tvApplnNo.setText(misIndividualBusinessReportList.get(position).getVerified());
        holder.tvBusinessType.setText(misIndividualBusinessReportList.get(position).getVerifiedDate());
        holder.tvProposerName.setText(misIndividualBusinessReportList.get(position).getActive());
        holder.tvPlanName.setText(misIndividualBusinessReportList.get(position).getRemarks());
       /* holder.tvPlanFreq.setText(misIndividualBusinessReportList.get(position).getPremiumFrequency());
        holder.tvInstalment.setText(String.valueOf(misIndividualBusinessReportList.get(position).getInstallment()));
        holder.tvStatus.setText(misIndividualBusinessReportList.get(position).getStatus());
        holder.tvPremTerm.setText(String.valueOf(misIndividualBusinessReportList.get(position).getPremTerm()));
        holder.tvPremiumAmmount.setText(String.valueOf(misIndividualBusinessReportList.get(position).getPremiumAmt()));
        holder.tv_weighted_premium.setText(String.valueOf(misIndividualBusinessReportList.get(position).getWeightedPremium()));
        holder.tv_premium_with_tax.setText(String.valueOf(misIndividualBusinessReportList.get(position).getPremiumWithTax()));*/



    }

    @Override
    public int getItemCount() {
        return misIndividualBusinessReportList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvCode,tvName,tvBranchName,tvCompanyName, tvInstalment,tvStatus,tvPremTerm,tvPremiumAmmount,tvPlanFreq,tvDate, tvAgentCode, tvApplnNo,tvProposerName,tvPlanName,tvBusinessType,tvPremiumFrequency,tv_weighted_premium, tv_premium_with_tax;
        LinearLayoutCompat llparent;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCode = itemView.findViewById(R.id.tv_code);
            tvName = itemView.findViewById(R.id.tv_name);
            tvBranchName = itemView.findViewById(R.id.tv_branch_name);
            tvCompanyName = itemView.findViewById(R.id.tv_company_name);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvApplnNo = itemView.findViewById(R.id.tv_appln_no);
            tvBusinessType = itemView.findViewById(R.id.tv_business_type);
            tvProposerName = itemView.findViewById(R.id.tv_proposer_name);
            tvPlanName = itemView.findViewById(R.id.tv_plan_name);
            /*tvPlanFreq = itemView.findViewById(R.id.tv_plan_freq);
            tvInstalment = itemView.findViewById(R.id.tv_instalment);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvPremTerm = itemView.findViewById(R.id.tv_prem_term);
            tvPremiumAmmount = itemView.findViewById(R.id.tv_premium_amount);
            tv_weighted_premium = itemView.findViewById(R.id.tv_weighted_premium);
            tv_premium_with_tax = itemView.findViewById(R.id.tv_premium_with_tax);*/


        }
    }
}

