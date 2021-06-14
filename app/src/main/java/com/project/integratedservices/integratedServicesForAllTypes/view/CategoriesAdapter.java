package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {


    private ArrayList<String> categories;
    private Context context;
    private int selectedPosition;
    private SendSelectedValue sendSelectedValue;

    public CategoriesAdapter(Context context, ArrayList<String> categories, SendSelectedValue sendSelectedValue) {
        this.categories = categories;
        this.context = context;
        this.sendSelectedValue = sendSelectedValue;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_experience_categories_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.tvCategory.setText(categories.get(myViewHolder.getAdapterPosition()));

        if (selectedPosition == position){
            myViewHolder.tvCategory.setBackgroundResource(R.drawable.bg_light_blue);
        myViewHolder.tvCategory.setTextColor(context.getResources().getColor(R.color.white));
    }
        else {
            myViewHolder.tvCategory.setBackgroundResource(R.drawable.bg_light_blue_border);
            myViewHolder.tvCategory.setTextColor(context.getResources().getColor(R.color.light_blue));
        }

        myViewHolder.tvCategory.setOnClickListener(v -> {
        selectedPosition=position;
        notifyDataSetChanged();
        sendSelectedValue.getItemClicked(position);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    interface SendSelectedValue {
        void getItemClicked(int pos);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }

}
