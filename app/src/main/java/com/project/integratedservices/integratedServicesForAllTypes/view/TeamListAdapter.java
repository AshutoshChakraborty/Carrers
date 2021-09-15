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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.TeamDetailsResponse;
import com.project.supportClasses.SharedPref;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.MyViewHolder> {

    private List<TeamDetailsResponse> teamDetailsResponses;
    private Context context;
    private int lastPosition = -1;
    private HandleClick handleClick;
    private boolean shouldShow;

    public TeamListAdapter(Context context, List<TeamDetailsResponse> teamDetailsResponses, HandleClick handleClick, boolean shouldShow) {
        this.context = context;
        this.teamDetailsResponses = teamDetailsResponses;
        this.handleClick = handleClick;
        this.shouldShow = shouldShow;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_list_layout, parent, false));

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

        if (teamDetailsResponses.get(position).isSelected()) {
            holder.parent.setBackgroundColor(Color.parseColor("#B6B3B3"));
        } else {
            holder.parent.setBackgroundColor(Color.parseColor("#EDEDED"));
        }

        if (teamDetailsResponses.get(position).getName() == null) {
            holder.tvName.setText("");
        } else
            holder.tvName.setText(teamDetailsResponses.get(position).getName().trim());

        if (teamDetailsResponses.get(position).getAgentCode() == null) {
//            holder.tvDesignation.setText("null");
            holder.tvDesignation.setVisibility(View.GONE);
        } else {
            holder.tvDesignation.setText(teamDetailsResponses.get(position).getAgentCode());
            holder.tvDesignation.setVisibility(View.VISIBLE);
        }

        String loggedInAgentsId = SharedPref.getInstance(context).getData(AGENT_ID);
            holder.llparent.setOnClickListener(v -> {
                if(loggedInAgentsId.length() != 7) {
                context.startActivity(new Intent(context, TeamMemberDetailsActivity.class).putExtra("details", teamDetailsResponses.get(position)));
                } else {
                    Toast.makeText(context,"Permission not Granted",Toast.LENGTH_LONG).show();
                }
            });

        holder.ivInfo.setVisibility(shouldShow ? View.VISIBLE : View.GONE);


        holder.ivInfo.setOnClickListener(v -> {
            clearSelection();
            teamDetailsResponses.get(position).setSelected(true);
            holder.parent.setBackgroundColor(Color.parseColor("#B6B3B3"));
            handleClick.handleClick(teamDetailsResponses.get(position));
        });

//        holder.rank.setText(teamDetailsResponses.get(position).getRank());
        holder.code.setText(teamDetailsResponses.get(position).getGrade());
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
        void handleClick(TeamDetailsResponse team);
    }

    private void clearSelection() {
        for (TeamDetailsResponse response : teamDetailsResponses) {
            if (response.isSelected()) {
                response.setSelected(false);
                notifyItemChanged(teamDetailsResponses.indexOf(response));
            }
        }

    }
}
