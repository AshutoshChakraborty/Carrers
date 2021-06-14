package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.integratedservices.R;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint2Response;

import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyViewHolder> {

    private Context context;
    private List<VoucherPrint2Response> voucher2responseList;
    private int lastPosition = -1;

    public VoucherAdapter(Context context, List<VoucherPrint2Response> voucher2responseList) {
        this.context = context;
        this.voucher2responseList = voucher2responseList;
    }

    @NonNull
    @Override
    public VoucherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VoucherAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher_layout, parent, false));

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VoucherAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherAdapter.MyViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


         holder.tvCID.setText("" + voucher2responseList.get(position).getCID());
         holder.tvBranchCode.setText(voucher2responseList.get(position).getBRANCH());
         holder.tvcdapl.setText(voucher2responseList.get(position).getCDAPL());
         holder.tvdate.setText(voucher2responseList.get(position).getSDATE());
         holder.tvScheme.setText(voucher2responseList.get(position).getSCHEME());
         holder.tvCollection.setText(voucher2responseList.get(position).getCOLLECTION());
         holder.tvPercentage.setText(voucher2responseList.get(position).getPERCENTAGE());
         holder.tvComm.setText(voucher2responseList.get(position).getCOMM());
         holder.tvSpot.setText(voucher2responseList.get(position).getSPOT());
         holder.tvadjust.setText(voucher2responseList.get(position).getADJUST());
         holder.tvRtype.setText(voucher2responseList.get(position).getRTYPE());
         holder.tvInvestorJunior.setText(voucher2responseList.get(position).getINVESTORJUNIOR());

    }

    @Override
    public int getItemCount() {
        return voucher2responseList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCID, tvBranchCode, tvcdapl, tvdate, tvScheme, tvCollection, tvPercentage, tvComm, tvSpot, tvadjust, tvRtype, tvInvestorJunior;
        LinearLayoutCompat llparent;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCID = itemView.findViewById(R.id.tvCID);
            tvBranchCode = itemView.findViewById(R.id.tvBranchCode);
            tvcdapl = itemView.findViewById(R.id.tvcdapl);
            tvdate = itemView.findViewById(R.id.tvdate);
            tvScheme = itemView.findViewById(R.id.tvScheme);
            tvCollection = itemView.findViewById(R.id.tvCollection);
            tvPercentage = itemView.findViewById(R.id.tvPercentage);
            tvComm = itemView.findViewById(R.id.tvComm);
            tvSpot = itemView.findViewById(R.id.tvSpot);
            tvadjust = itemView.findViewById(R.id.tvadjust);
            tvRtype = itemView.findViewById(R.id.tvRtype);
            tvInvestorJunior = itemView.findViewById(R.id.tvInvestorJunior);

        }
    }
}

