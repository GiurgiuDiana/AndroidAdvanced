package com.example.androidadvancedproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.androidadvancedproject.Notification.LodgeNotificationFcatory;

import java.util.concurrent.TimeUnit;

import timber.log.Timber;

import static com.example.androidadvancedproject.Notification.LodgeNotificationFcatory.SERVICE_NOTIFICATION_ID;

public class LodgeService extends Service {
        public static String TYPE_KEY = "type";

        public static int TYPE_BASIC = 0;
        public static int TYPE_ADVANCED = 1;
        public static int TYPE_FINISH = 2;

        private long startMillis;

        @Override
        public void onCreate() {
            super.onCreate();
            startMillis = System.currentTimeMillis();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            int type = intent.getIntExtra(TYPE_KEY, TYPE_BASIC);

            if (type == TYPE_BASIC) {
                startForeground(SERVICE_NOTIFICATION_ID, LodgeNotificationFcatory.createServiceLodgeNotification(this));

                Timber.i("Basic work in progress");

            } else if (type == TYPE_ADVANCED) {
                Timber.i("Advanced work in progress");

            } else if (type == TYPE_FINISH) {
                Timber.i("Finishing service");
                stopSelf();

            } else {
                Timber.w("Unknown service type. Killing.");
                stopSelf();
            }

            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            long currentMillis = System.currentTimeMillis();
            Timber.d("Service was active for %d seconds", TimeUnit.MILLISECONDS.toSeconds(currentMillis - startMillis));
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            // ignore, unused for now
            return null;
        }
    }
