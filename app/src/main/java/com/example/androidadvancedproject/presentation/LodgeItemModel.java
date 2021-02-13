package com.example.androidadvancedproject.presentation;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkManager;

import com.example.androidadvancedproject.domain.FetchLodgeItemsUseCase;
import com.example.androidadvancedproject.domain.LodgeItem;

import java.util.List;

public class LodgeItemModel extends ViewModel {
    private final WorkManager workManager;
    private final FetchLodgeItemsUseCase fetchItemsUseCase;
    public ObservableArrayList<LodgeItem> items = new ObservableArrayList<>();

    public LodgeItemModel(WorkManager workManager, FetchLodgeItemsUseCase fetchItemsUseCase) {
        this.workManager = workManager;
        this.fetchItemsUseCase = fetchItemsUseCase;
        LiveData<List<LodgeItem>> liveItems = fetchItemsUseCase.execute();
        liveItems.observeForever(heavyItems -> this.items.addAll(heavyItems));
    }
}
