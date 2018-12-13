package com.ansh.obaazo;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class ObazzoApp extends Application {
    private static Context mContext;


    public static Context getmContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base;
        MultiDex.install(this);
    }
}
