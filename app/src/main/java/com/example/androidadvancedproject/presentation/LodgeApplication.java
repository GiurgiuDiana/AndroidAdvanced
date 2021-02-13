package com.example.androidadvancedproject.presentation;
import android.os.Build;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.example.androidadvancedproject.BuildConfig;
import com.example.androidadvancedproject.presentation.Notification.*;
import com.facebook.stetho.Stetho;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class LodgeApplication extends Application {
    public static LodgeApplication instance;
    public List<Activity> activities = new ArrayList<>();
    @Override
    public void onCreate() {
        Stetho.initializeWithDefaults(this);
        super.onCreate();
        instance = this;

        setupLibs();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(LodgeNotificationChannelFactory.createProcessingWorkNotificationChannel());
        }
    }
    private void setupLibs() {
        Stetho.initializeWithDefaults(this);

        if (!BuildConfig.my_flag) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
                    if (priority < Log.INFO) {
                        return;
                    }

                    if (t != null) {
                        FirebaseCrashlytics.getInstance().recordException(t);
                    }

                    String crashlyticsMessage = String.format("[%s] %s", tag, message);
                    FirebaseCrashlytics.getInstance().log(crashlyticsMessage);
                }
            });
        }
    }
}
