package com.example.androidadvancedproject.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.androidadvancedproject.R;
import com.example.androidadvancedproject.presentation.MainActivity;

public class LodgeNotificationChannelFactory {

    public static final String CHANNEL_ID = "AndroidAdvancedProject";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static NotificationChannel createProcessingWorkNotificationChannel() {
        CharSequence name = "Location";
        String description = "This is where u are";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                name,
                importance);
        channel.setShowBadge(true);
        channel.enableVibration(true);
        channel.setDescription(description);
        return channel;
    }
}
