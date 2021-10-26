package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.annotation.SuppressLint;
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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.ApplicationNumberWisePaymentNewPayment;
import java.util.List;

public class ApplicationNumberWisePaymentNewAdapter extends RecyclerView.Adapter<ApplicationNumberWisePaymentNewAdapter.MyViewHolder>{
    private Context context;
    private List<ApplicationNumberWisePaymentNewPayment> misCollectionRegisterResponsesList;
    private int lastPosition = -1;

    public ApplicationNumberWisePaymentNewAdapter(Context context, List<ApplicationNumberWisePaymentNewPayment> misCollectionRegisterResponsesList) {
        this.context = context;
        this.misCollectionRegisterResponsesList = misCollectionRegisterResponsesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_application_wise_layout, parent, false));

    }


    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

        holder.tvBranchName.setText(misCollectionRegisterResponsesList.get(position).getRefNo());
        holder.tvCompanyName.setText(misCollectionRegisterResponsesList.get(position).getDescription());
        holder.tvDate.setText(misCollectionRegisterResponsesList.get(position).getDate());
        holder.tvAgentCode.setText(misCollectionRegisterResponsesList.get(position).getApplicationNumber());
        holder.tvApplnNo.setText(misCollectionRegisterResponsesList.get(position).getFRMREG());
        holder.tvProposerName.setText(misCollectionRegisterResponsesList.get(position).getFrmAmt());
        holder.tvPlanName.setText(misCollectionRegisterResponsesList.get(position).getFrmComm());
        holder.tvBusinessType.setText(misCollectionRegisterResponsesList.get(position).getProposer_Name());
//        holder.tvPremiumFrequency.setText(misCollectionRegisterResponsesList.get(position).getFrmAmt());
//        holder.tv_premium_amt.setText(misCollectionRegisterResponsesList.get(position).getFrmComm());


    }

    @Override
    public int getItemCount() {
        return misCollectionRegisterResponsesList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvBranchName, tvCompanyName, tvDate, tvAgentCode, tvApplnNo,tvProposerName,tvPlanName,tvBusinessType,tvPremiumFrequency,tv_premium_amt;

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
            tv_premium_amt = itemView.findViewById(R.id.tv_premium_amt);



        }
    }
}
