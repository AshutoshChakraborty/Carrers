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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AgentDetail;

import java.util.List;

public class AgentDetailsAdapter extends RecyclerView.Adapter<AgentDetailsAdapter.MyViewHolder> {
    private Context context;
    private List<AgentDetail> misCollectionRegisterResponsesList;
    private int lastPosition = -1;

    public AgentDetailsAdapter(Context context, List<AgentDetail> misCollectionRegisterResponsesList) {
        this.context = context;
        this.misCollectionRegisterResponsesList = misCollectionRegisterResponsesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agent_details_layout, parent, false));

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

        holder.tvAgentCode.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getSlno()));
        holder.tvName.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getCode()));
        holder.tvBranchCode.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getName()));
        holder.tvBranchName.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getIntroducer()));
        holder.tvBusinessType.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getDoj()));

        holder.PAN_VerifiedOn.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getEnrolAmt()));
        holder.PAN_Verified_By.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getRankId()));
        holder.IFS_Code.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getGradeName()));
/*        holder.Bank_Address.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBankAddress()));
        holder.Account_No.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getAccountNo()));
        holder.IFS_Code.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getIFSCode()));
        holder.Bank_Name.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBankName()));
        holder.Bank_Verified.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBankVerified()));
        holder.Bank_Verified_Date.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBankVerifiedDate()));
        holder.Bank_Verified_By.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBankVerifiedBy()));
        holder.Status.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getStatus()));*/


    }

    @Override
    public int getItemCount() {
        return misCollectionRegisterResponsesList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvAgentCode, tvName, tvBranchCode, tvBranchName, tvBusinessType, tvCompanyName, tvActualPremium, tvPremiumAmount,
                PAN_VerifiedOn,
                PAN_Verified_By,
                Bank_Branch,
                Bank_Address,
                Account_No,
                IFS_Code,
                Bank_Name,
                Bank_Verified,
                Bank_Verified_Date,
                Bank_Verified_By,
                Status;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAgentCode = itemView.findViewById(R.id.tv_agent_code);
            tvName = itemView.findViewById(R.id.tv_name);
            tvBranchCode = itemView.findViewById(R.id.tv_branch_code);
            tvBranchName = itemView.findViewById(R.id.tv_branch_name);
            tvBusinessType = itemView.findViewById(R.id.tv_business_type);

            PAN_VerifiedOn = itemView.findViewById(R.id.PAN_VerifiedOn);
            PAN_Verified_By = itemView.findViewById(R.id.PAN_Verified_By);
            Bank_Branch = itemView.findViewById(R.id.Bank_Branch);
            Bank_Address = itemView.findViewById(R.id.Bank_Address);
            Account_No = itemView.findViewById(R.id.Account_No);
            IFS_Code = itemView.findViewById(R.id.IFS_Code);
            Bank_Name = itemView.findViewById(R.id.Bank_Name);
            Bank_Verified = itemView.findViewById(R.id.Bank_Verified);
            Bank_Verified_Date = itemView.findViewById(R.id.Bank_Verified_Date);
            Bank_Verified_By = itemView.findViewById(R.id.Bank_Verified_By);
            Status = itemView.findViewById(R.id.Status);


        }
    }
}
