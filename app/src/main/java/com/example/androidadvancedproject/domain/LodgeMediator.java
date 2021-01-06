package com.example.androidadvancedproject.domain;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidadvancedproject.data.LodgeItemDTO;
import com.example.androidadvancedproject.data.LodgeItemRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import timber.log.Timber;

public class LodgeMediator {
    private static final String TAG = "mediator";
    private final LodgeItemRepository localRepository;
    private final LodgeItemRepository remoteRepository;
    private final ExecutorService executorService;
    private final MutableLiveData<List<LodgeItem>> liveItems;

    public LodgeMediator(LodgeItemRepository localRepository, LodgeItemRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.executorService = Executors.newSingleThreadExecutor();
        this.liveItems = new MutableLiveData<>();
    }

    public LiveData<List<LodgeItem>> getItems() {
        ArrayList<LodgeItem> items = new ArrayList<>();
        for (LodgeItemDTO dto : localRepository.getItems()) {
            items.add(new LodgeItem(dto.getLodgeId(), dto.getLodgeName(),dto.getLodgeOwner(),Timestamp.valueOf(dto.getLodgeAvailability()),dto.getLodgeNumber(),dto.getLodgeLocation()));
        }
        executorService.execute(() -> {
            for (LodgeItemDTO dto : remoteRepository.getItems()) {
                items.add(new LodgeItem(dto.getLodgeId(), dto.getLodgeName(),dto.getLodgeOwner(),Timestamp.valueOf(dto.getLodgeAvailability()),dto.getLodgeNumber(),dto.getLodgeLocation()));
            }
            this.liveItems.postValue(items);
        });
        Timber.tag(TAG).d("Items ADDED ");


        return liveItems;
    }
}
