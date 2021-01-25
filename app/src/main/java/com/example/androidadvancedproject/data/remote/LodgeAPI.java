package com.example.androidadvancedproject.data.remote;

import com.example.androidadvancedproject.data.LodgeItemDTO;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import okhttp3.OkHttpClient;
import retrofit2.http.POST;

public interface LodgeAPI {

     final  String BASE_URL="https://lodge-df482-default-rtdb.europe-west1.firebasedatabase.app/";
    @GET("lodges.json")
    Call<List<LodgeItemDTO>> getItems();

   // @POST("lodges.json")


    static LodgeAPI createApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(LodgeAPI.class);
    }

}
