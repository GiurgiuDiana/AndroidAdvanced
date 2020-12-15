package com.example.androidadvancedproject.presentation;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class LodgeApplication extends Application {

    @Override
    public void onCreate() {
        Stetho.initializeWithDefaults(this);
        super.onCreate();
    }
}
