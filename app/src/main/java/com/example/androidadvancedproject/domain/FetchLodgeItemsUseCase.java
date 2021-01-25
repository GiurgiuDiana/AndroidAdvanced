package com.example.androidadvancedproject.domain;

import androidx.lifecycle.LiveData;
import java.util.List;

public class FetchLodgeItemsUseCase {
    private final LodgeMediator lodgeMediator;
    private static final String TAG = "FetchLodgeItemsUseCase";

    public FetchLodgeItemsUseCase(LodgeMediator mediator) {
        this.lodgeMediator = mediator;
    }
    public LiveData<List<LodgeItem>> execute() {
        return lodgeMediator.getItems();
    }

//    public List<LodgeItem> execute() {
//        try {
//            Log.d(TAG, "get Items");
//            ArrayList<LodgeItem> items = new ArrayList<>();
//            for (LodgeItemDTO dto : lodgeRepository.getItems()) {
//                items.add(new LodgeItem(dto.getLodgeId(), dto.getLodgeName(),dto.getLodgeOwner(),dto.getLodgeAvailability(),dto.getLodgeNumber(),dto.getLodgeLocation() ));
//            }
//            return items;
//        } catch (Exception e) {
//            Log.e(TAG,"error "+ e.getStackTrace());
//            return Collections.emptyList();
//        }
//    }


}
