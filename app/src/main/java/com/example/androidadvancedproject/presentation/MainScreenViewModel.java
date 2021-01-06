package com.example.androidadvancedproject.presentation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.androidadvancedproject.Worker.LocationWorker;

import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainScreenViewModel extends ViewModel {
    public static final String TAG = "mainScreen tag";
    private final Context context;
    private static final int PERMISSION_REQUEST_CODE = 200;

    private final WorkManager workManager;

    public void onButtonHit() {
        //pornire GPS tracking-> folosita pentru afisarea celor mai apropiate cabane(trebuia pusa la ListLodgings dar am adaugat aici ca si buton ca nu am baza de date pentru cabane)
                PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(LocationWorker.class, 15, TimeUnit.MINUTES)
                        .addTag(TAG)
                        .build();
                WorkManager.getInstance().enqueueUniquePeriodicWork("Location", ExistingPeriodicWorkPolicy.REPLACE, periodicWork);

    }
    public MainScreenViewModel(WorkManager workManager, Context context1)
    {    //verificare permisiuni
        this.workManager = workManager;
        this.context = context1;
    }

    //permisiuni pentru locatie

    private boolean checkLocationPermission() {
        int coarse_location_permision = ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION);
        int fine_location_permision = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);
        return coarse_location_permision == PackageManager.PERMISSION_GRANTED &&
                fine_location_permision == PackageManager.PERMISSION_GRANTED;
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean coarseLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean fineLocation = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (coarseLocation && fineLocation)
                    Log.d(TAG,"Permission Granted");
                else {
                    Log.e(TAG, "Permission Denied");
                }
            }
        }
    }

}
