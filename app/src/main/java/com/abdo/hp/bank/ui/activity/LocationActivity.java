package com.abdo.hp.bank.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.abdo.hp.bank.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import butterknife.BindView;
import butterknife.ButterKnife;


public class LocationActivity extends FragmentActivity implements

        OnMapReadyCallback {


    @BindView(R.id.Location_btn_ChoseLocation)
    Button ChoseTheLocation;
    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private FusedLocationProviderClient mLocationClient;
    private float ZOOM_CAMERA = 5f;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private boolean MLocationPemissionsGranted = false;
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    public static final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        getLocationPermission();
        // initMap();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDeviceLocation();
            }
        });

        ChoseTheLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("lat", latitude);
intent.putExtra("long",longitude);
setResult(RESULT_OK,intent);
finish();


            }
        });

    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting current location");
        mLocationClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (MLocationPemissionsGranted) {
                Task taskLocation = mLocationClient.getLastLocation();
                taskLocation.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LocationActivity.this, " found location!", Toast.LENGTH_SHORT).show();
                            Location result = (Location) task.getResult();
                            latitude = result.getLatitude();
                            longitude = result.getLongitude();
                            LatLng latLng = new LatLng(latitude, longitude);
                            Toast.makeText(LocationActivity.this, "" + result.getLongitude() + "" + result.getLatitude(), Toast.LENGTH_LONG).show();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
                            mMap.addMarker(new MarkerOptions().position(latLng)).showInfoWindow();

                        } else {

                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(LocationActivity.this, "current location is null", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        } catch (SecurityException e) {
            Log.d(TAG, "getDeviceLocation: SecurityException" + e.getMessage());

        }

    }



    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "map is ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;

    }

    private void getLocationPermission() {

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                MLocationPemissionsGranted = true;
                initMap();

            } else {

                ActivityCompat.requestPermissions(this,
                        permissions, REQUEST_CODE_PERMISSIONS);
            }

        } else {

            ActivityCompat.requestPermissions(this,
                    permissions, REQUEST_CODE_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MLocationPemissionsGranted = false;

        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            MLocationPemissionsGranted = false;
                            return;
                        }
                    }
                    MLocationPemissionsGranted = true;
                    //init the map
                    initMap();
                }
            }
        }
    }
}

