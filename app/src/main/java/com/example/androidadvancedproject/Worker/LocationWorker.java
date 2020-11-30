package com.example.androidadvancedproject.Worker;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.androidadvancedproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LocationWorker extends Worker {

	private static final String DEFAULT_START_TIME = "00:00";
	private static final String DEFAULT_END_TIME = "24:00";
	private static final String TAG = "LocationWorker";
	private Location mLocation;
	private FusedLocationProviderClient mFusedLocationClient;
	private Context mContext;
	private LocationCallback mLocationCallback;

	public LocationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
		super(context, workerParams);
		mContext = context;
	}
	@NonNull
	@Override
	public Result doWork() {
		Log.d(TAG, "doWork");
		DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		String formattedDate = dateFormat.format(date);
		try {
			Date currentDate = dateFormat.parse(formattedDate);
			Date startDate = dateFormat.parse(DEFAULT_START_TIME);
			Date endDate = dateFormat.parse(DEFAULT_END_TIME);
			if (currentDate.after(startDate) && currentDate.before(endDate)) {
				mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
				mLocationCallback = new LocationCallback() {
					@Override
					public void onLocationResult(LocationResult locationResult) {
						super.onLocationResult(locationResult);
					}
				};
				try {
					mFusedLocationClient
							.getLastLocation()
							.addOnCompleteListener(new OnCompleteListener<Location>() {
								@Override
								public void onComplete(@NonNull Task<Location> task) {
									if (task.isSuccessful() && task.getResult() != null) {
										mLocation = task.getResult();
										Log.d(TAG, "Location : " + mLocation);

										NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, mContext.getString(R.string.app_name))
												.setSmallIcon(android.R.drawable.ic_menu_mylocation)
												.setContentTitle("New Location Update")
												.setContentText("lat" + (Double.toString(mLocation.getLatitude()))+"long " +(Double.toString( mLocation.getLongitude())))
												.setPriority(NotificationCompat.PRIORITY_DEFAULT) ;
												NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
										notificationManager.notify(1001, builder.build());

										mFusedLocationClient.removeLocationUpdates(mLocationCallback);
									} else {
										Log.w(TAG, "Failed to get location.");
									}
								}
							});
				} catch (SecurityException securityException) {
					Log.e(TAG, "Lost location permission." + securityException);
				}
			}
		} catch (ParseException ignored) {

		}
		return Result.success();
	}
}