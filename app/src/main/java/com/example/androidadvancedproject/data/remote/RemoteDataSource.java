package com.example.androidadvancedproject.data.remote;

import android.util.Log;

import com.example.androidadvancedproject.data.LodgeItemDTO;
import com.example.androidadvancedproject.data.LodgeItemRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;
import timber.log.Timber;

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
            return response.body();
        } catch (IOException e) {
            Timber.tag(TAG).d("Something went wrong");
            return Collections.emptyList();
        }
    }
//
//    @Override
//    public void addLodge(LodgeItemDTO lodge) {
//        try {
//            Response<List<LodgeItemDTO>> response = lodgeAPI.getItems().execute();
//            return response.body();
//        } catch (IOException e) {
//            Log.w(TAG, "Something went wrong", e);
//            return Collections.emptyList();
//        }    }
//    }
}
