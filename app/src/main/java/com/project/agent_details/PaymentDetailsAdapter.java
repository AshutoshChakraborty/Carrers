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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_payment_detail.PaymentDetail;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_voucher.VoucherDetail;

import java.util.List;

public class PaymentDetailsAdapter extends RecyclerView.Adapter<PaymentDetailsAdapter.MyViewHolder> {

    private Context context;
    private List<PaymentDetail> misIndividualBusinessReportList;
    private int lastPosition = -1;

    public PaymentDetailsAdapter(Context context, List<PaymentDetail> misIndividualBusinessReportList) {
        this.context = context;
        this.misIndividualBusinessReportList = misIndividualBusinessReportList;
    }

    @NonNull
    @Override
    public PaymentDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PaymentDetailsAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_payment_details_child_layout, parent, false));

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PaymentDetailsAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentDetailsAdapter.MyViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


        holder.tvCode.setText(String.valueOf(misIndividualBusinessReportList.get(position).getId()));
        holder.tvName.setText(misIndividualBusinessReportList.get(position).getDescription());
        holder.tvBranchName.setText(misIndividualBusinessReportList.get(position).getStatementDate());
        holder.tvCompanyName.setText(misIndividualBusinessReportList.get(position).getGrossComm());
        holder.tvDate.setText(misIndividualBusinessReportList.get(position).getSportComm());
        holder.tvApplnNo.setText(misIndividualBusinessReportList.get(position).getHptds());
        holder.tvBusinessType.setText(misIndividualBusinessReportList.get(position).getTds());
        holder.tvProposerName.setText(misIndividualBusinessReportList.get(position).getTdsNoPan());
        holder.tvPlanName.setText(misIndividualBusinessReportList.get(position).getFwdf());
        holder.tvPlanFreq.setText(misIndividualBusinessReportList.get(position).getNetComm());
        /*holder.tvInstalment.setText(String.valueOf(misIndividualBusinessReportList.get(position).getFrmadj()));
        holder.tvStatus.setText(misIndividualBusinessReportList.get(position).getInvestor());
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
            tvPlanFreq = itemView.findViewById(R.id.tv_plan_freq);
            /*tvInstalment = itemView.findViewById(R.id.tv_instalment);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvPremTerm = itemView.findViewById(R.id.tv_prem_term);
            tvPremiumAmmount = itemView.findViewById(R.id.tv_premium_amount);
            tv_weighted_premium = itemView.findViewById(R.id.tv_weighted_premium);
            tv_premium_with_tax = itemView.findViewById(R.id.tv_premium_with_tax);*/


        }
    }
}

