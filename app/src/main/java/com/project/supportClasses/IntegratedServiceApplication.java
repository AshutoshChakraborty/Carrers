package com.project.supportClasses;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class IntegratedServiceApplication extends Application
{
    private static IntegratedServiceApplication sInstance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        AndroidThreeTen.init(this);
    }

    public static IntegratedServiceApplication getsInstance()
    {
        return sInstance;
    }
}
