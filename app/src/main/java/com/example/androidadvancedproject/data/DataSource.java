package com.example.androidadvancedproject.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataSource implements LodgeItemRepository{
    private List<LodgeItemDTO> lodgeItemDtos;
    @Override
    public List<LodgeItemDTO> getItems() {
        lodgeItemDtos = Arrays.asList(
                new LodgeItemDTO(2,"Baisoara", "Raducu","2020-11-12 01:02:03.123456789","654321","Baisoara")

        );
        return lodgeItemDtos;
    }


}
