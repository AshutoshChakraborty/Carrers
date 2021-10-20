package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint2Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint4Response;

import java.util.List;

public class VoucherFourAdapter extends RecyclerView.Adapter<VoucherFourAdapter.MyViewHolder> {

    private Context context;
    private List<VoucherPrint4Response> voucher2responseList;
    private int lastPosition = -1;

    public VoucherFourAdapter(Context context, List<VoucherPrint4Response> voucher2responseList) {
        this.context = context;
        this.voucher2responseList = voucher2responseList;
    }

    @NonNull
    @Override
    public VoucherFourAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VoucherFourAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher_four_layout, parent, false));

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VoucherFourAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherFourAdapter.MyViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


         holder.tvCID.setText("" + voucher2responseList.get(position).getPhaseName().toString());
         holder.tvBranchCode.setText(voucher2responseList.get(position).getTotalTillDate().toString());
         holder.tvcdapl.setText(voucher2responseList.get(position).getLifeHeldupAmt().toString());
    }

    @Override
    public int getItemCount() {
        return voucher2responseList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCID, tvBranchCode, tvcdapl;
        LinearLayoutCompat llparent;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCID = itemView.findViewById(R.id.tvCID);
            tvBranchCode = itemView.findViewById(R.id.tvBranchCode);
            tvcdapl = itemView.findViewById(R.id.tvcdapl);

        }
    }
}

