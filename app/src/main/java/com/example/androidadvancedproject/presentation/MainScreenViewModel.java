package com.example.androidadvancedproject.presentation;

import androidx.lifecycle.ViewModel;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import com.example.androidadvancedproject.domain.LodgeWorker;
import com.example.androidadvancedproject.domain.Worker.LocationWorker;

import java.util.concurrent.TimeUnit;

public class MainScreenViewModel extends ViewModel {
    public static final String TAG = "mainScreen tag";

    private final WorkManager workManager;

    public MainScreenViewModel(WorkManager instance) {
        this.workManager = instance;

    }

    public void onButtonHit() {
        //pornire GPS tracking-> folosita pentru afisarea celor mai apropiate cabane(trebuia pusa la ListLodgings dar am adaugat aici ca si buton ca nu am baza de date pentru cabane)
                PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(LocationWorker.class, 15, TimeUnit.MINUTES)
                        .addTag(TAG)
                        .build();
                WorkManager.getInstance().enqueueUniquePeriodicWork("Location", ExistingPeriodicWorkPolicy.REPLACE, periodicWork);

    }
//    public MainScreenViewModel(WorkManager workManager, Context context1)
//    {
//        this.workManager = workManager;
//        this.context = context1;
//    }


}
