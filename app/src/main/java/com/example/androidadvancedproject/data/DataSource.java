package com.example.androidadvancedproject.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataSource implements LodgeItemRepository{
    private List<LodgeItemDTO> lodgeItemDtos;
    @Override
    public List<LodgeItemDTO> getItems() {
        lodgeItemDtos = Arrays.asList(
                new LodgeItemDTO(1,"Marisel", "Radu",new Date("25/12/2020"),"123456","Valenii de munte"),
                new LodgeItemDTO(2,"Baisoara", "Raducu",new Date("15/12/2020"),"654321","Baisoara")

        );
        return lodgeItemDtos;
    }


}
