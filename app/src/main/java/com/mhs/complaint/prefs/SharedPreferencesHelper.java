package com.mhs.complaint.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public static void insertData(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String retriveData(Context context, String key) {
        return getPrefs(context).getString(key, "");
    }

    public static void deleteData(Context context, String key) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.remove(key);
        editor.apply();
    }
}
