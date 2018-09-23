package com.ansh.obaazo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ansh.obaazo.ObazzoApp;

public class PreferencesUtils {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String PREFS_NAME = "nutriverse";

    public PreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }


    public void clearSession() {
        editor.clear();
        editor.apply();
    }


    private static SharedPreferences getPrefs() {
        return ObazzoApp.getmContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor() {
        return getPrefs().edit();
    }

    public static void putString(String key, String value) {
        getEditor().putString(key, value).commit();
    }

    public static String getString(String key) {
        return getPrefs().getString(key, "");
    }

    public static void putInteger(String key, int value) {
        getEditor().putInt(key, value).commit();
    }

    public static int getInteger(String key) {
        return getPrefs().getInt(key, 0);
    }

    public static void putBoolean(String key, boolean value) {
        getEditor().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key) {
        return getPrefs().getBoolean(key, false);
    }

    public static void putFloat(String key, float value) {
        getEditor().putFloat(key, value).commit();
    }

    public static float getFloat(String key) {
        return getPrefs().getFloat(key, 0.0f);
    }

    public static void putLong(String key, long value) {
        getEditor().putLong(key, value).commit();
    }

    public static long getLong(String key) {
        return getPrefs().getLong(key, 0);
    }

    public static void putDouble(String key, double value) {
        getEditor().putLong(key, Double.doubleToLongBits(value)).commit();
    }

    public static double getDouble(String key) {
        return Double.longBitsToDouble(getPrefs().getLong(key, 0));
    }

    public static void clearValue(String key) {
        getEditor().remove(key).commit();
    }

    public static void clearSharedPrefs() {
        getEditor().clear().commit();
    }

}
