package com.project.integratedservices.authenticate.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.project.integratedservices.R;
import com.project.supportClasses.SharedPref;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class SplashActivity extends AppCompatActivity {

    Calendar calendar;
    private TextView gretingsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        gretingsView = findViewById(R.id.greetingText);

        calendar = Calendar.getInstance();
        checkTimeInBetween();
        Date restrictedDate = new Date();
//        String s = "22/10/2020 10:00:00";
        String s = "18/07/2021 10:00:00";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
             calendar.setTime(format.parse(s));
            restrictedDate = calendar.getTime();

            if(Calendar.getInstance().getTime().after(restrictedDate))
            {
//                Toast.makeText(this, "Time up", Toast.LENGTH_SHORT).show();
//                finish();
            }
            else
            {
                if(SharedPref.getInstance(this).getData(SharedPref.UUID).isEmpty()) {
                    SharedPref.getInstance(this).saveData(SharedPref.UUID, UUID.randomUUID().toString());
                    System.out.println("uuid - "+SharedPref.getInstance(this).getData(SharedPref.UUID));
                }

                redirectToLogin();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    private void redirectToLogin() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }, 4000);
    }
    private void checkTimeInBetween() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >=1 && timeOfDay < 12){
            gretingsView.setText("Good Morning");
        }else if(timeOfDay >= 12 && timeOfDay < 17){
            gretingsView.setText("Good Afternoon");
        }else if(timeOfDay >= 17){
            gretingsView.setText("Good Evening");
        }
    }
}
