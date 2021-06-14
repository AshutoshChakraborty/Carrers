package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.SalesDetailsResponsePojo;

import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.MyViewHolder> {
    private Context context;
    private List<SalesDetailsResponsePojo> salesDetailsList;
    public SalesAdapter(Context context, List<SalesDetailsResponsePojo> salesDetailsList) {
        this.context=context;
        this.salesDetailsList = salesDetailsList;
    }

    @NonNull
    @Override
    public SalesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull SalesAdapter.MyViewHolder holder, int position) {
        holder.tvMemberName.setText(salesDetailsList.get(position).getCustomerName());
        holder.tvAddress.setText("Address : "+salesDetailsList.get(position).getAddress());
        holder.tvNumber.setText(salesDetailsList.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return salesDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvMemberName, tvAddress,tvNumber;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvMemberName = itemView.findViewById(R.id.tvMemberName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
