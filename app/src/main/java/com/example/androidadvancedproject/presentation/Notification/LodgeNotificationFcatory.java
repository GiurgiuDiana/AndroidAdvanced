package com.example.androidadvancedproject.presentation.Notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.androidadvancedproject.presentation.LodgeService;
import com.example.androidadvancedproject.presentation.View.*;

public class LodgeNotificationFcatory {
    private static final int BASE_ID = 1;
    public static final int SERVICE_NOTIFICATION_ID = BASE_ID + 1;
    public static final int HELLO_NOTIFICATION_ID = SERVICE_NOTIFICATION_ID + 1;

    public static Notification createProcessingWorkNotification(Context context,String latitude, String longitutde) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, LodgeNotificationChannelFactory.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_popup_sync)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContentTitle("New Location Update")
                .setContentText("lat" + latitude+"long " +longitutde)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(createContentIntent(context))
                .addAction(createStopAction(context));

        return builder.build();
    }
    public static Notification createServiceLodgeNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, LodgeNotificationChannelFactory.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_popup_sync)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("work")
                .setContentText("Something is being working...")
                .setAutoCancel(true)
                .setContentIntent(createContentIntent(context))
                .addAction(createStopAction(context));

        return builder.build();
    }


    private static PendingIntent createContentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(context,
                MainActivity.NOTIFICATION_LAUNCH_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
    private static NotificationCompat.Action createStopAction(Context context) {
        return new NotificationCompat.Action.Builder(android.R.drawable.ic_media_pause,
                "Stop",
                createStopActionPendingIntent(context))
                .build();
    }

    private static PendingIntent createStopActionPendingIntent(Context context) {
        Intent intent = new Intent(context, LodgeService.class);
        intent.putExtra(LodgeService.TYPE_KEY, LodgeService.TYPE_FINISH);

        return PendingIntent.getService(context,
                LodgeService.TYPE_FINISH,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
}
