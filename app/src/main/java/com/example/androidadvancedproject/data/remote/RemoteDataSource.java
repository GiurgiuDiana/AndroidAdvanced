package com.example.androidadvancedproject.data.remote;

import android.util.Log;

import com.example.androidadvancedproject.data.LodgeItemDTO;
import com.example.androidadvancedproject.data.LodgeItemRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public class RemoteDataSource implements LodgeItemRepository {
    private static final String TAG = "remote lodge data";
    private final LodgeAPI lodgeAPI;

    public RemoteDataSource(LodgeAPI api) {
        this.lodgeAPI = api;
    }
    @Override
    public List<LodgeItemDTO> getItems() {
        try {
            Response<List<LodgeItemDTO>> response = lodgeAPI.getItems().execute();
           Log.w(TAG,response.body().toString()) ;
            return response.body();
        } catch (IOException e) {
            Log.w(TAG, "Something went wrong", e);
            return Collections.emptyList();
        }    }
}
