package com.example.androidadvancedproject.presentation;

import android.util.Log;

import androidx.annotation.StringRes;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.androidadvancedproject.R;
import com.example.androidadvancedproject.domain.FetchLodgeItemsUseCase;
import com.example.androidadvancedproject.domain.LodgeItem;
import com.example.androidadvancedproject.domain.LodgeWorker;

import java.util.List;

public class LodgeItemModel extends ViewModel {
    public static final String LOG_TAG = "heavy_tag";
    private final WorkManager workManager;
    private final FetchLodgeItemsUseCase fetchItemsUseCase;
    public ObservableArrayList<LodgeItem> items = new ObservableArrayList<>();
    public LodgeItemModel(WorkManager workManager, FetchLodgeItemsUseCase fetchItemsUseCase) {
        this.workManager = workManager;
        this.fetchItemsUseCase = fetchItemsUseCase;
        this.items.addAll(fetchItemsUseCase.execute());

    }

    public void onButtonHit() {
        Log.d(LOG_TAG, "My button was hit from view model");

        launchWorker();
        List<LodgeItem> items1 = fetchItemsUseCase.execute();
        this.items.addAll(items1);

    }

    private void launchWorker() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.METERED)
                .build();

        Data inputData = new Data.Builder()
                .putString("type", "get")
                .build();

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(LodgeWorker.class)
                .setInputData(inputData)
                .setConstraints(constraints)
                .build();

        workManager.beginUniqueWork(
                "some-heavy-work",
                ExistingWorkPolicy.APPEND,
                request)
                .enqueue();
    }

}
