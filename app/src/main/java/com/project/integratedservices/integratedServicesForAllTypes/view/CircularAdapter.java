package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.circular_response.CircularResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.message_response.SmsDetailsResposne;

import java.util.List;

public class CircularAdapter extends RecyclerView.Adapter<CircularAdapter.MyViewHolder> {

    private List<CircularResponse> teamDetailsResponses;
    private Context context;
    private int lastPosition = -1;
    private HandleClick handleClick;
    private boolean shouldShow;

    public CircularAdapter(Context context, List<CircularResponse> teamDetailsResponses, HandleClick handleClick, boolean shouldShow) {
        this.context = context;
        this.teamDetailsResponses = teamDetailsResponses;
        this.handleClick = handleClick;
        this.shouldShow = shouldShow;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_list_layout, parent, false));

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

       /* if (teamDetailsResponses.get(position).isSelected()) {
            holder.parent.setBackgroundColor(Color.parseColor("#B6B3B3"));
        } else {
            holder.parent.setBackgroundColor(Color.parseColor("#EDEDED"));
        }*/
        holder.parent.setBackgroundColor(Color.parseColor("#EDEDED"));

        if (teamDetailsResponses.get(position).getCircularName() == null) {
            holder.tvName.setText("null");
        } else
            holder.tvName.setText(teamDetailsResponses.get(position).getCircularName().trim());

        if (teamDetailsResponses.get(position).getDate() == null) {
//            holder.tvDesignation.setText("null");
            holder.tvDesignation.setVisibility(View.GONE);
        } else {
            holder.tvDesignation.setText(teamDetailsResponses.get(position).getDate());
            holder.tvDesignation.setVisibility(View.VISIBLE);
        }


        holder.ivInfo.setOnClickListener(v -> {
            handleClick.handleClick(teamDetailsResponses.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return teamDetailsResponses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvName, tvDesignation, rank, code;
        LinearLayoutCompat llparent;
        AppCompatImageView ivInfo;
        MaterialCardView parent;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvMemberName);
            tvDesignation = itemView.findViewById(R.id.tvAddress);
            llparent = itemView.findViewById(R.id.llparent);
            ivInfo = itemView.findViewById(R.id.ivInfo);
            parent = itemView.findViewById(R.id.parent);
            rank = itemView.findViewById(R.id.tv_rank);
            code = itemView.findViewById(R.id.tvCode);

        }
    }

    public interface HandleClick {
        void handleClick(CircularResponse team);
    }

    }
