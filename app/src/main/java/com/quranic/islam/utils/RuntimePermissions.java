package com.quranic.islam.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RuntimePermissions {
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 100;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 200;
    public static final int REQUEST_CHECK_SETTINGS = 300;
    public static final int REQ_ID_MULTIPLE_PERMISSIONS = 1;

    public static boolean checkPermission(final Activity mActivity) {
        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mActivity);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("GPS Permission Necessary");
                    alertBuilder.setMessage("Allows us to access location data");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_LOCATION);
                        }
                    });

                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_LOCATION);
                }

                return false;
            } else if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mActivity);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("GPS Permission Necessary");
                    alertBuilder.setMessage("Allows us to access location data");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                        }
                    });

                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                }

                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

//    public static void displayLocationSettingsRequest(final Activity mActivity) {
//        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(mActivity)
//                .addApi(LocationServices.API).build();
//        googleApiClient.connect();
//
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(10000 / 2);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
//        builder.setAlwaysShow(true);
//
//        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
//        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//            @Override
//            public void onResult(LocationSettingsResult result) {
//                final Status status = result.getStatus();
//                switch (status.getStatusCode()) {
//                    case LocationSettingsStatusCodes.SUCCESS:
//                        if (mActivity instanceof SingleLocationActivity)
//                            ((SingleLocationActivity) mActivity).onActivityResult(REQUEST_CHECK_SETTINGS, Activity.RESULT_OK, null);
//                        if (mActivity instanceof MultipleLocationActivity)
//                            ((MultipleLocationActivity) mActivity).onActivityResult(REQUEST_CHECK_SETTINGS, Activity.RESULT_OK, null);
//
//                        break;
//                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                        Log.i("", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
//
//                        try {
//                            // Show the dialog by calling startResolutionForResult(), and check the result
//                            // in onActivityResult().
//                            status.startResolutionForResult(mActivity, REQUEST_CHECK_SETTINGS);
//                        } catch (IntentSender.SendIntentException e) {
//                            Log.i("", "PendingIntent unable to execute request.");
//                        }
//                        break;
//                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                        Log.i("", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
//                        break;
//                }
//            }
//        });
//    }
}
