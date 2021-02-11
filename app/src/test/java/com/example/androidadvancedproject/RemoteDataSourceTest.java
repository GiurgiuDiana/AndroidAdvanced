package com.example.androidadvancedproject;

import com.example.androidadvancedproject.data.LodgeItemDTO;
import com.example.androidadvancedproject.data.LodgeItemRepository;
import com.example.androidadvancedproject.data.remote.LodgeAPI;
import com.example.androidadvancedproject.data.remote.RemoteDataSource;
import com.example.androidadvancedproject.domain.LodgeItem;
import com.example.androidadvancedproject.presentation.LodgeApplication;

import org.junit.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class RemoteDataSourceTest {

    @Test
    public void when_getLodges_failed() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        LodgeAPI lodge = new Retrofit.Builder()
                .baseUrl("https://www.google.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LodgeAPI.class);

        RemoteDataSource remoteDataSource = new RemoteDataSource(lodge);
        assertEquals(0, remoteDataSource.getItems().size());
    }
    @Test
    public void Lodges_check_size() {
        LodgeItemRepository remoteRepository = new RemoteDataSource(LodgeAPI.createApi());
        assertEquals(3,remoteRepository.getItems().size());
    }
    @Test
    public void Lodges_CheckItContainsValue() {
        LodgeItem lodgeItem =new LodgeItem(2,"Baisoara", "Raducu",Timestamp.valueOf("2020-11-12 01:02:03.123456789"),"654321","Baisoara");
        LodgeItemRepository remoteRepository = new RemoteDataSource(LodgeAPI.createApi());
        assertTrue(remoteRepository.getItems().contains(lodgeItem));
    }

}

