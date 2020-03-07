package com.paulo.mybeer.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    public static final String PREFS = "BEER_PREFS";

    public static SharedPreferences.Editor editor;

    public static void setStringInPrefs(Context context, String key, String value) {
        editor = openEdit(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringInPrefs(Context context, String key) {
        return openEdit(context).getString(key, null);
    }

    private static SharedPreferences openEdit(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }
}
