package com.project.supportClasses;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SharedPref {
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    public static final String USER_DETAILS = "USER_DETAILS";
    public static final String IS_LOGGED_IN_THROUGH_FB = "IS_LOGGED_IN_THROUGH_FB";
    public static final String AGENT_ID = "AGENT_ID";
    public static final String COMPANY_NAME = "company_name";
    public static final String ATTENDANCE_DATE = "ATTENDANCE_DATE";
    public static final String ATTENDACE_START = "ATTENDACE_START";
    public static final String ATTENDACE_END = "ATTENDACE_END";
    public static final String LOGIN_TYPE = "LOGIN_TYPE";
    public static final String UUID = "UUID";

    private static SharedPref preference;
    private SharedPreferences sharedPreferences;

    private SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("IntegratedServicesPreference", Context.MODE_PRIVATE);
    }

    public static SharedPref getInstance(Context context) {
        if (preference == null) {
            preference = new SharedPref(context);
        }
        return preference;
    }

    public synchronized void saveData(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public synchronized String getData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    private synchronized void saveData(String key, int value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.apply();
    }

    private synchronized void saveData(String key, float value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putFloat(key, value);
        prefsEditor.apply();
    }

    public synchronized void saveData(String key, long value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putLong(key, value);
        prefsEditor.apply();
    }

    public synchronized void saveData(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    private synchronized int getIntData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getInt(key, 0);
        }
        return 0;
    }

    public synchronized long getLongData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getLong(key, 0);
        }
        return 0;
    }

    private synchronized float getFloatData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getFloat(key, 0);
        }
        return 0.0f;
    }

    public synchronized boolean getBooleanData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    public synchronized boolean getBooleanDataDefaultTrue(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, true);
        }
        return false;
    }


    public void clearAllPref() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
//        prefsEditor.clear().apply();

        Map<String,?> prefs = sharedPreferences.getAll();
        for(Map.Entry<String,?> prefToReset : prefs.entrySet()){
            if(!prefToReset.getKey().equalsIgnoreCase(SharedPref.UUID))
                prefsEditor.remove(prefToReset.getKey()).apply();
        }
    }

    public void clearPref() {

    }
}