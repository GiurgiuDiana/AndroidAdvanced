package com.example.androidadvancedproject.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.WorkManager;

import android.os.Bundle;

import com.example.androidadvancedproject.R;
import com.example.androidadvancedproject.data.DataSource;
import com.example.androidadvancedproject.data.LodgeItemRepository;
import com.example.androidadvancedproject.data.remote.LodgeAPI;
import com.example.androidadvancedproject.data.remote.RemoteDataSource;
import com.example.androidadvancedproject.databinding.ActivityListLodgingsBinding;
import com.example.androidadvancedproject.domain.FetchLodgeItemsUseCase;
import com.example.androidadvancedproject.domain.LodgeMediator;


public class ListLodgings extends AppCompatActivity {
    //afisare lista cu cabane( cele din apropierea utilizatorului)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                FetchLodgeItemsUseCase useCase = createUseCase();
                return (T) new LodgeItemModel(WorkManager.getInstance(ListLodgings.this), useCase);
            }
        };

        LodgeItemModel viewModel = new ViewModelProvider(this, factory).get(LodgeItemModel.class);

        ActivityListLodgingsBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_list_lodgings);
        binding.setLodgeModel(viewModel);
    }

    private FetchLodgeItemsUseCase createUseCase() {
        LodgeItemRepository localRepository = new DataSource();
        LodgeItemRepository remoteRepository = new RemoteDataSource(LodgeAPI.createApi());

        LodgeMediator mediator = new LodgeMediator(localRepository, remoteRepository);

        return new FetchLodgeItemsUseCase(mediator);
    }

}