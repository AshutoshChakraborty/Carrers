package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.NewCustomerListResponsePojo;

import java.util.ArrayList;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {


    private ArrayList<String> categories;
    private Context context;
    private int selectedPosition;
    private CategoriesAdapter.SendSelectedValue sendSelectedValue;
    List<NewCustomerListResponsePojo> newCustomerList;

    public CustomerListAdapter(Context context, List<NewCustomerListResponsePojo> newCustomerList) {

        this.context = context;
        this.newCustomerList = newCustomerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customer_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
//        myViewHolder.tvPlanName.setText(categories.get(myViewHolder.getAdapterPosition()));

        myViewHolder.tvIName.setText(newCustomerList.get(position).getCustomerName());
        myViewHolder.tvAddress.setText(newCustomerList.get(position).getAddress());
        myViewHolder.tvPhn.setText(newCustomerList.get(position).getContact());

        myViewHolder.cvParent.setOnClickListener(v -> {
//            Toast.makeText(context, "Under Development", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context,CustomerDetailsActivity.class).putExtra("customerDetails",newCustomerList.get(position)));
        });
    }

    @Override
    public int getItemCount() {
        return newCustomerList.size();
    }

    interface SendSelectedValue {
        void getItemClicked(int pos);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvIName,tvAddress,tvPhn;
        MaterialCardView cvParent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIName = itemView.findViewById(R.id.tvIName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhn = itemView.findViewById(R.id.tvPhn);
            cvParent = itemView.findViewById(R.id.cvParent);

        }
    }

}
