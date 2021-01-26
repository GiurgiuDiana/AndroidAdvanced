package com.example.androidadvancedproject.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import timber.log.Timber;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidadvancedproject.R;
import com.example.androidadvancedproject.Worker.LocationWorker;
import com.example.androidadvancedproject.databinding.ActivityListLodgingsBinding;
import com.example.androidadvancedproject.databinding.ActivityMainBinding;
import com.example.androidadvancedproject.domain.FetchLodgeItemsUseCase;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final String TAG = "Location";
    private FirebaseAnalytics mFirebaseAnalytics;
    public static final int NOTIFICATION_LAUNCH_CODE = 485;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //verificare permisiuni
        if (!checkLocationPermission()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
        //pornire GPS tracking-> folosita pentru afisarea celor mai apropiate cabane(trebuia pusa la ListLodgings dar am adaugat aici ca si buton ca nu am baza de date pentru cabane)
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new MainScreenViewModel(WorkManager.getInstance(MainActivity.this));
            }
        };

        MainScreenViewModel viewModel = new ViewModelProvider(this, factory).get(MainScreenViewModel.class);

        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainViewModel(viewModel);
    }
    public void OpenActivity_AddLodging(View view) {
        Intent startAddLodge= new Intent(this, AddLodging.class);
        startActivity(startAddLodge);

    }
    public void OpenLodgingsExisting(View view) {
        Intent startRecycler= new Intent(this, ListLodgings.class);
        startActivity(startRecycler);
    }

    //permisiuni pentru locatie

    private boolean checkLocationPermission() {
        int coarse_location_permision = ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION);
        int fine_location_permision = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);
        return coarse_location_permision == PackageManager.PERMISSION_GRANTED &&
                fine_location_permision == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean coarseLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean fineLocation = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (coarseLocation && fineLocation)
                    Timber.tag(TAG).d("Permission Granted");
                else {
                    Timber.tag(TAG).d("Permission Denied");
                }
            }
        }
    }
}