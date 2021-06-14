package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PlanDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PremiumCalculatorList;

import java.util.List;

public class PremiumCalculatorAdapter extends RecyclerView.Adapter<PremiumCalculatorAdapter.MyViewHolder> {


    private Context context;
    private List<PremiumCalculatorList> premiumCalculatorLists;
    private SelectedInsurer selectedInsurer;

    public PremiumCalculatorAdapter(Context context, List<PremiumCalculatorList> premiumCalculatorLists, SelectedInsurer selectedInsurer) {
        this.context = context;
        this.premiumCalculatorLists = premiumCalculatorLists;
        this.selectedInsurer = selectedInsurer;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_premium_calculator_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.tvPlanName.setText(premiumCalculatorLists.get(position).getInsurerName());
        Glide.with(context).load(premiumCalculatorLists.get(position).getInsurerLogo()).placeholder(R.drawable.ic_calculator).into(myViewHolder.img);
        myViewHolder.item.setOnClickListener(v -> {
            selectedInsurer.selectedInsurerLink(premiumCalculatorLists.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return premiumCalculatorLists.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvPlanName;
                AppCompatImageView img;
        LinearLayoutCompat item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlanName=itemView.findViewById(R.id.tvPlanName);
            item=itemView.findViewById(R.id.item);
            img=itemView.findViewById(R.id.img);
        }
    }

    interface SelectedInsurer
    {
        void selectedInsurerLink(PremiumCalculatorList detailsResponse);
    }

}