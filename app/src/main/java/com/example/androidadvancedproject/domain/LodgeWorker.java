package com.example.androidadvancedproject.domain;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class LodgeWorker extends Worker {

        public LodgeWorker(@NonNull Context context,
                           @NonNull WorkerParameters workerParams) {
            super(context, workerParams);
        }
        @NonNull
        @Override
        public Result doWork() {
            Data data = getInputData();
            String value = data.getString("type");
            if ("get".equals(value)) {
                // GET operation
            } else if ("post".equals(value)){
                // POST operation
            }
            return Result.success();
        }
    }


