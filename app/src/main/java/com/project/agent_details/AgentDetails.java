package com.project.agent_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.project.integratedservices.R;

public class AgentDetails extends AppCompatActivity {
    AgentTabAdapter agenttabadapter;
    ViewPager2 viewPager;
    private AppCompatEditText editEnterCode;
    private MaterialCardView cvSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_details);

        viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        agenttabadapter = new AgentTabAdapter(this,"");
        viewPager.setAdapter(agenttabadapter);
        viewPager.setUserInputEnabled(false);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Field Work Detail");
                            break;
                        case 1:
                            tab.setText("Bank Detail");
                            break;
                        case 2:
                            tab.setText("Promotion Detail");
                            break;
                        case 3:
                            tab.setText("Voucher Detail");
                            break;
                        case 4:
                            tab.setText("Payment Detail");
                            break;
                    }
                }
        ).attach();
        editEnterCode = findViewById(R.id.editEnterCode);
        cvSubmit = findViewById(R.id.cvSubmit);
        cvSubmit.setOnClickListener(v -> {
            if (!editEnterCode.getText().toString().isEmpty()) {
                agenttabadapter = new AgentTabAdapter(this,editEnterCode.getText().toString());
                viewPager.setAdapter(agenttabadapter);

            } else {
                Toast.makeText(this, "Please enter agent code", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public static class AgentTabAdapter extends FragmentStateAdapter {
        String agentCode;
        public AgentTabAdapter(FragmentActivity fragment,String agentCode) {
            super(fragment);
            this.agentCode = agentCode;
        }


        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return a NEW fragment instance in createFragment(int)
            Fragment fragment;
            Bundle args = new Bundle();
            switch (position) {
                case 0:
                    FieldWorkDetailsFragment fieldDetailsFragment = new FieldWorkDetailsFragment();
                    args.putString(FieldWorkDetailsFragment.AGENT_CODE,agentCode);
                    fieldDetailsFragment.setArguments(args);
                    fragment = fieldDetailsFragment;
                    break;
                case 1:
                    BankDetailsFragment bankDetailsFragment = new BankDetailsFragment();
                    args.putString(BankDetailsFragment.AGENT_CODE,agentCode);
                    bankDetailsFragment.setArguments(args);
                    fragment = bankDetailsFragment;
                    break;
                case 2:
                    PromotionDetailsFragment promotionDetailsFragment = new PromotionDetailsFragment();
                    args.putString(PromotionDetailsFragment.AGENT_CODE,agentCode);
                    promotionDetailsFragment.setArguments(args);
                    fragment = promotionDetailsFragment;
                    break;
                case 3:
                    VoucherDetailsFragment voucherDetailsFragment = new VoucherDetailsFragment();
                    args.putString(VoucherDetailsFragment.AGENT_CODE,agentCode);
                    voucherDetailsFragment.setArguments(args);
                    fragment = voucherDetailsFragment;
                    break;
                default:
                    PaymentDetailsFragment paymentDetailsFragment = new PaymentDetailsFragment();
                    args.putString(PaymentDetailsFragment.AGENT_CODE,agentCode);
                    paymentDetailsFragment.setArguments(args);
                    fragment = paymentDetailsFragment;
                    break;
            }

           /* Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(DemoObjectFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);*/
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }
}