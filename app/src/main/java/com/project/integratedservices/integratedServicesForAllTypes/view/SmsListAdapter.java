package com.project.integratedservices.integratedServicesForAllTypes.view;

import static com.project.supportClasses.SharedPref.AGENT_ID;

import android.content.Context;
import android.content.Intent;
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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.TeamDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.message_response.SmsDetailsResposne;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;

public class SmsListAdapter extends RecyclerView.Adapter<SmsListAdapter.MyViewHolder> {

    private List<SmsDetailsResposne> teamDetailsResponses;
    private Context context;
    private int lastPosition = -1;
    private HandleClick handleClick;
    private boolean shouldShow;

    public SmsListAdapter(Context context, List<SmsDetailsResposne> teamDetailsResponses, HandleClick handleClick, boolean shouldShow) {
        this.context = context;
        this.teamDetailsResponses = teamDetailsResponses;
        this.handleClick = handleClick;
        this.shouldShow = shouldShow;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_list_layout, parent, false));

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

        if (teamDetailsResponses.get(position).getSms() == null) {
            holder.tvName.setText("");
        } else
            holder.tvName.setText(teamDetailsResponses.get(position).getSms().trim());

        if (teamDetailsResponses.get(position).getSMSDate() == null) {
//            holder.tvDesignation.setText("null");
            holder.tvDesignation.setVisibility(View.GONE);
        } else {
            holder.tvDesignation.setText(teamDetailsResponses.get(position).getSMSDate());
            holder.tvDesignation.setVisibility(View.VISIBLE);
        }


        holder.ivInfo.setOnClickListener(v -> {
            handleClick.handleClick(teamDetailsResponses.get(position));
        });

        if (teamDetailsResponses.get(position).getSms().trim().length() > 0 ) {
            holder.parent.setOnClickListener(v -> {
                ColorDialog colorDialog = MyColorDialog.getInstance(context);
                colorDialog.setContentText(teamDetailsResponses.get(position).getSms().trim());
                colorDialog.setCancelable(true);
                colorDialog.setAnimationEnable(true);
                colorDialog.show();
            });
        }

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
        void handleClick(SmsDetailsResposne team);
    }

    }
