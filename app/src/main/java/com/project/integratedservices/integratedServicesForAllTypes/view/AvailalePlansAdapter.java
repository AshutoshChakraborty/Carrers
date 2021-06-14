package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PlanDetailsResponse;

import java.util.List;

public class AvailalePlansAdapter extends RecyclerView.Adapter<AvailalePlansAdapter.MyViewHolder> {


    private Context context;
    private List<PlanDetailsResponse> availablePlans;
    private SelectedProduct selectedProduct;

    public AvailalePlansAdapter(Context context, List<PlanDetailsResponse> availablePlans,SelectedProduct selectedProduct) {
        this.context = context;
        this.availablePlans = availablePlans;
        this.selectedProduct = selectedProduct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_available_plans_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.tvPlanName.setText(availablePlans.get(position).getProductName());
        myViewHolder.item.setOnClickListener(v -> {
            selectedProduct.selectedProductLink(availablePlans.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return availablePlans.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvPlanName;
        LinearLayoutCompat item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlanName=itemView.findViewById(R.id.tvPlanName);
            item=itemView.findViewById(R.id.item);
        }
    }

    interface SelectedProduct
    {
        void selectedProductLink(PlanDetailsResponse detailsResponse);
    }

}
