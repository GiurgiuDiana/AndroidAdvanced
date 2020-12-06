package com.example.androidadvancedproject.domain;

import com.example.androidadvancedproject.data.LodgeItemDTO;
import com.example.androidadvancedproject.data.LodgeItemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FetchLodgeItemsUseCase {
    private final LodgeItemRepository lodgeRepository;

    public FetchLodgeItemsUseCase(LodgeItemRepository repository) {
        this.lodgeRepository = repository;
    }

    public List<LodgeItem> execute() {
        try {
            ArrayList<LodgeItem> items = new ArrayList<>();
            for (LodgeItemDTO dto : lodgeRepository.getItems()) {
                items.add(new LodgeItem(dto.getLodge_id(), dto.getLodge_name(),dto.getLodge_owner(),dto.getAvailability(),dto.getLodge_number(),dto.getLodge_location() ));
            }
            return items;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
