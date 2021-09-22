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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BusinessReportResponsePojo;

import java.util.List;

public class BusinessReportAdapter extends RecyclerView.Adapter<BusinessReportAdapter.MyViewHolder> {

    private Context context;
    private int lastPosition = -1;
    private List<BusinessReportResponsePojo> businessReportResponse;

    public BusinessReportAdapter(Context context, List<BusinessReportResponsePojo> businessReportResponse) {
        this.context = context;
        this.businessReportResponse = businessReportResponse;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_report_layout, parent, false));

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

        if (businessReportResponse.get(position).getStatus().equals("Success"))
        {
            holder.tvName.setText(businessReportResponse.get(position).getName());
            holder.tvPolicyNo.setText("Ref Number - "+businessReportResponse.get(position).getAgentCode());
            holder.tvDate.setText("Referee Number - "+businessReportResponse.get(position).getIntroCode());
            holder.tvCollectionAmount.setText("Total Business - RS "+businessReportResponse.get(position).getTotalBusiness());
            holder.tvStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
       return businessReportResponse.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvName, tvDate, tvPolicyNo, tvCollectionAmount, tvStatus;
        LinearLayoutCompat llparent;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPolicyNo = itemView.findViewById(R.id.tvPolicyNo);
            tvCollectionAmount = itemView.findViewById(R.id.tvCollectionAmount);
            tvStatus = itemView.findViewById(R.id.tvStatus);

        }
    }
}

