package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.project.integratedservices.R;
import com.project.integratedservices.authenticate.viewmodel.AuthenticationViewModel;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.otherCategory.LocateUsActivity;
import com.project.supportClasses.Misc;
import com.project.supportClasses.MyColorDialog;
import com.project.supportClasses.SharedPref;

import cn.refactor.lib.colordialog.ColorDialog;
import cn.refactor.lib.colordialog.PromptDialog;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.project.supportClasses.Constants.endAttendanceGiven;
import static com.project.supportClasses.Constants.isSalaried;
import static com.project.supportClasses.Constants.startAttendanceGiven;
import static com.project.supportClasses.SharedPref.AGENT_ID;
import static com.project.supportClasses.SharedPref.COMPANY_NAME;
import static com.project.supportClasses.SharedPref.LOGIN_TYPE;
import static com.project.supportClasses.SharedPref.USER_DETAILS;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public AppCompatImageView iv_drawer_menu;
    public TextView header;
    public CircleImageView profileImageView;
    public FloatingActionButton floatingActionButtonAdd,
            floatingActionButtonSearch;
    public String agentId = "";
    public SpinKitView spinKitView;
    public UserDetailsResponse userDetails;
    AppCompatTextView tvCustomerText, tv_name_nav, tv_email_nav;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private IntegratedServicesViewModel integratedServicesViewModel;


    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();
        // If the constant state is identical, they are using the same drawable resource.
        // However, the opposite is not necessarily true.
        return (stateA != null && stateB != null && stateA.equals(stateB))
                || getBitmap(drawableA).sameAs(getBitmap(drawableB));
    }

    public static Bitmap getBitmap(Drawable drawable) {
        Bitmap result;
        if (drawable instanceof BitmapDrawable) {
            result = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            // Some drawables have no intrinsic width - e.g. solid colours.
            if (width <= 0) {
                width = 1;
            }
            if (height <= 0) {
                height = 1;
            }

            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        if (SharedPref.getInstance(this).getData(COMPANY_NAME) == null || SharedPref.getInstance(this).getData(COMPANY_NAME) == "") {
            header.setText(getResources().getString(R.string.integrated_services));
            header.setVisibility(View.GONE);
        } else {
            header.setText(SharedPref.getInstance(this).getData(COMPANY_NAME));
            header.setVisibility(View.GONE);
        }


//        if(getIntent().hasExtra("id"))
//        {
//            agentId = getIntent().getStringExtra("id");
//        }
        if (getIntent().hasExtra("userdata")) {
            userDetails = getIntent().getParcelableExtra("userdata");

            tv_name_nav.setText(userDetails.getUserName());
            tv_email_nav.setText(userDetails.getEmail());
            if (userDetails.getGender().contains("F") || userDetails.getGender().equalsIgnoreCase("F")) {
                profileImageView.setImageResource(R.drawable.profile);
            } else {
                profileImageView.setImageResource(R.drawable.profilepic);
            }
        }

        iv_drawer_menu.setOnClickListener(v -> {
            if (areDrawablesIdentical(iv_drawer_menu.getDrawable(), getResources().getDrawable(R.drawable.ic_left_arrow))) {
                onBackPressed();
                iv_drawer_menu.setImageResource(R.drawable.menu);
            } else if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(Gravity.LEFT);
            } else {
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });

        floatingActionButtonAdd.setOnClickListener(v -> {
            loadFragment(new NewAssignCustomerFragment());
            floatingActionButtonAdd.setVisibility(View.GONE);
            iv_drawer_menu.setImageResource(R.drawable.ic_left_arrow);
        });


        nav_view.setNavigationItemSelectedListener(this);

        loadFragment(new DashboardFragment());

        checkforAlertMsg(SharedPref.getInstance(this).getData(AGENT_ID));
        integratedServicesViewModel.getAlertMsgResponseLiveData().observe(this, alertMessageResponses -> {

            if (alertMessageResponses != null) {
                if (alertMessageResponses.size() > 0) {
                    if (alertMessageResponses.get(0).getLeaveStatus() != null && !alertMessageResponses.get(0).getLeaveStatus().equals("0")) {
                        ColorDialog dialog = new ColorDialog(this);
                        dialog.setContentText("You cannot use the app when your on leave");
                        dialog.setPositiveListener("OK", colorDialog -> {
                            colorDialog.dismiss();
                            SharedPref.getInstance(this).clearAllPref();
                            Toast.makeText(this.getApplicationContext(), "logged out", Toast.LENGTH_SHORT).show();
                            this.finishAffinity();
                        });
                        dialog.setCancelable(false);
                        dialog.show();
                    } else if (alertMessageResponses.get(0).getSMSTextDisplay() != null && !alertMessageResponses.get(0).getSMSTextDisplay().isEmpty() && !alertMessageResponses.get(0).getSMSTextDisplay().equals("0")) {
                        Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        dialog.setContentView(R.layout.full_screen_dialog_layout);
                        dialog.setCancelable(false);
                        dialog.show();

                        LinearLayoutCompat container = dialog.findViewById(R.id.notificationContainer);

                        for (int i = 0; i < alertMessageResponses.size(); i++) {
                            TextView tv = new TextView(this);
                            TextView devider = new TextView(this);
                            container.addView(tv);
                            if (i != alertMessageResponses.size() - 1) {
                                container.addView(devider);
                                LinearLayoutCompat.LayoutParams deviderParam = (LinearLayoutCompat.LayoutParams) devider.getLayoutParams();
                                deviderParam.height = 1;
                                deviderParam.width = LinearLayoutCompat.LayoutParams.MATCH_PARENT;
                                devider.setPadding(20,0,20,0);
                                devider.setBackgroundColor(getResources().getColor(R.color.white));
                                devider.setLayoutParams(deviderParam);
                            }
                            LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) tv.getLayoutParams();
                            layoutParams.height = LinearLayoutCompat.LayoutParams.WRAP_CONTENT;
                            layoutParams.width = LinearLayoutCompat.LayoutParams.WRAP_CONTENT;



                            tv.setTextColor(getResources().getColor(R.color.white));
                            tv.setPadding(40,40,40,40);

                            tv.setLayoutParams(layoutParams);

                            tv.setText(alertMessageResponses.get(i).getSMSTextDisplay());
                        }
                        ImageView close = dialog.findViewById(R.id.ivCross);


                        close.setOnClickListener(v -> {
                            dialog.dismiss();
                        });

                        //                new PromptDialog(this)
                        //                        .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                        //                        .setAnimationEnable(true)
                        ////                        .setTitleText(getString(R.string.success))
                        //                        .setContentText(alertMessageResponses.get(0).getSMSTextDisplay())
                        //                        .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                        //                            @Override
                        //                            public void onClick(PromptDialog dialog) {
                        //                                dialog.dismiss();
                        //                            }
                        //                        }).show();
                    } else {
                        spinKitView.setVisibility(View.GONE);
                    }
                }
            } else {
                spinKitView.setVisibility(View.GONE);
            }

        });


        integratedServicesViewModel.getApiError().observe(this, s -> {
            Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
        });


    }


    private void checkforAlertMsg(String data) {
        if (Misc.isNetworkAvailable(this)) {
            Misc.disableScreenTouch(this);
            integratedServicesViewModel.getAlertMessage(data);
        } else {
            ColorDialog colorDialog = MyColorDialog.getInstance(this);
            colorDialog.setContentText("Please check your Internet connection and retry");
            colorDialog.setPositiveListener("RETRY", ColorDialog -> {
                ColorDialog.dismiss();
                checkforAlertMsg(SharedPref.getInstance(this).getData(AGENT_ID));
            });
            colorDialog.setCancelable(false);
            colorDialog.setAnimationEnable(true);
            colorDialog.show();
        }
    }

    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        tvCustomerText = findViewById(R.id.textView2);
        nav_view = findViewById(R.id.nav_view);
        drawer_layout = findViewById(R.id.drawer_layout);
        iv_drawer_menu = findViewById(R.id.iv_drawer_menu);
        floatingActionButtonAdd = findViewById(R.id.floatingActionButtonAdd);
        floatingActionButtonSearch = findViewById(R.id.floatingActionButtonSearch);
        header = findViewById(R.id.tv_header_text);
        spinKitView = findViewById(R.id.spin_kit);


        View headerView = nav_view.getHeaderView(0);
        tv_name_nav = headerView.findViewById(R.id.tv_name_nav);
        tv_email_nav = headerView.findViewById(R.id.tv_email_nav);
        profileImageView = headerView.findViewById(R.id.imageView);
    }

    public void loadFragment(Fragment fragment) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.setSharedElementEnterTransition(new DetailsTransition());
            fragment.setEnterTransition(new Fade());
            fragment.setExitTransition(new Fade());
            fragment.setSharedElementReturnTransition(new DetailsTransition());
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flContainer, fragment);
        ft.addSharedElement(tvCustomerText, "subHeader");
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            int fragments = getSupportFragmentManager().getBackStackEntryCount();
            if (fragments == 1) {
                finish();
            } else if (getFragmentManager().getBackStackEntryCount() > 1) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
//            case R.id.nav_premium_calculator:
//                if (isSalaried) {
//                    if (startAttendanceGiven && endAttendanceGiven) {
//
//                    } else if (startAttendanceGiven) {
//                        loadFragment(new PremiumCalculatorFragment());
//                    } else {
//                        Toast.makeText(this, getString(R.string.make_your_attendance), Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    loadFragment(new PremiumCalculatorFragment());
//                }
//
//                break;
            case R.id.nav_change_password:

                loadFragment(new ChangePasswordFragment());

      /*          if(startAttendanceGiven && endAttendanceGiven)
                {

                }
                else if(startAttendanceGiven && endAttendanceGiven)
                {

                }
                else if (startAttendanceGiven) {
                    loadFragment(new ChangePasswordFragment());
                } else {
                    Toast.makeText(this, getString(R.string.make_your_attendance), Toast.LENGTH_SHORT).show();
                }*/
                break;
            case R.id.nav_locate_us:
                if (isSalaried) {
                    if (startAttendanceGiven && endAttendanceGiven) {

                    } else if (startAttendanceGiven) {
                        startActivity(new Intent(this, LocateUsActivity.class));
                    } else {
                        Toast.makeText(this, getString(R.string.make_your_attendance), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    startActivity(new Intent(this, LocateUsActivity.class));
                }

                break;
            case R.id.nav_logout:
                MyColorDialog.getInstance(this)
                        .setContentText("Do you want to logout?")
                        .setPositiveListener("YES", colorDialog -> {
                            SharedPref.getInstance(this).clearAllPref();
                            colorDialog.dismiss();
                            finish();
                        })
                        .setNegativeListener("NO", ColorDialog::dismiss)
                        .show();
                break;

            default:
//                Toast.makeText(this, "clicked on an item", Toast.LENGTH_SHORT).show();
        }

        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    public UserDetailsResponse getUserDetails() {
        return userDetails;
    }

    public class DetailsTransition extends TransitionSet {
        public DetailsTransition() {
            setOrdering(ORDERING_TOGETHER);
            addTransition(new ChangeBounds()).
                    addTransition(new ChangeTransform()).
                    addTransition(new ChangeImageTransform());
        }
    }
}
