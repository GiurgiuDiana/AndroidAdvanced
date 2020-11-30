package com.example.androidadvancedproject;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LodgeViewModel extends ViewModel {
    private String TAG = LodgeViewModel.class.getSimpleName();

    private MutableLiveData<List<String>> lodgetList;

    LiveData<List<String>> getLodgeList() {
        if (lodgetList == null) {
            lodgetList = new MutableLiveData<>();
            loadlodge();
        }
        return lodgetList;
    }
    private void loadlodge() {
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            DataSource lodges= new DataSource();
            List<String> lodgeStringList = lodges.getLodge();
            long seed = System.nanoTime();
            Collections.shuffle(lodgeStringList, new Random(seed));

            lodgetList.setValue(lodgeStringList);
        }, 5000);

    }

}
