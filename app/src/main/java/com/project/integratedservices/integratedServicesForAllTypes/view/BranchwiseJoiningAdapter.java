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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BranchwiseJoiningResponse;

import java.util.List;

public class BranchwiseJoiningAdapter extends RecyclerView.Adapter<BranchwiseJoiningAdapter.MyViewHolder>{
    private Context context;
    private List<BranchwiseJoiningResponse> misCollectionRegisterResponsesList;
    private int lastPosition = -1;

    public BranchwiseJoiningAdapter(Context context, List<BranchwiseJoiningResponse> misCollectionRegisterResponsesList) {
        this.context = context;
        this.misCollectionRegisterResponsesList = misCollectionRegisterResponsesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brach_wise_joining_layout, parent, false));

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


        holder.tvAgentCode.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getAgentCode()));
        holder.tvName.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getAgentType()));
        holder.tvBranchCode.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getAgentName()));
        holder.tvBranchName.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getJoiningDate()));
        holder.tvBusinessType.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getNO_OF_JOINING()));
        holder.tvCompanyName.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getStatus()));
        holder.tvActualPremium.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getIntroducerCode()));
//        holder.tvPremiumAmount.setText(String.valueOf(misCollectionRegisterResponsesList.get(position).getBranchName()));


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
