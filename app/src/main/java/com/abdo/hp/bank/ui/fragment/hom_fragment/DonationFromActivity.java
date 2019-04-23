package com.abdo.hp.bank.ui.fragment.hom_fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.donation.donation_details.DonationDetails;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;

import static com.abdo.hp.bank.helper.SharedPreferencesManger.LoadStringData;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;
import static com.abdo.hp.bank.ui.activity.LocationActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationFromActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.Request_Frome_Tv_name)
    TextView RequestFromeTvName;
    @BindView(R.id.Request_Frome_Tv_age)
    TextView RequestFromeTvAge;
    @BindView(R.id.Request_Frome_Tv_blood_type)
    TextView RequestFromeTvBloodType;
    @BindView(R.id.Request_Frome_Tv_Number_blood)
    TextView RequestFromeTvNumberBlood;
    @BindView(R.id.Request_Frome_Tv_Hospital_name)
    TextView RequestFromeTvHospitalName;
    @BindView(R.id.Request_Frome_Tv_phone)
    TextView RequestFromeTvPhone;
    @BindView(R.id.Request_From_Tv_description)
    TextView RequestFromTvDescription;
    ApiServices apiServices;

    private GoogleMap map;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private boolean MLocationPemissionsGranted = false;
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    private double latitude;
    private double longitude;
    private long id_desc;


    public DonationFromActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donation_from);
        ButterKnife.bind(this);
        apiServices = getClint().create(ApiServices.class);
        initMap();
        getLocationPermission();
        id_desc = getIntent().getLongExtra("id_desc", 0);
        Log.d(TAG, "onCreate: " + id_desc);
        SharedPreferencesManger.setSharedPreferences(this);
        //Request_From_map_location
        getDonationDetails();
    }

    private void getDonationDetails() {
        String api_token = LoadStringData(this, API_TOKEN_PREF);
        apiServices.getDonationRequestDetails(api_token, id_desc).enqueue(new Callback<DonationDetails>() {
            @Override
            public void onResponse(Call<DonationDetails> call, Response<DonationDetails> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(DonationFromActivity.this, "status 1" + response.message(), Toast.LENGTH_SHORT).show();
                        DonationDetails body = response.body();
                        RequestFromeTvName.setText(body.getData().getPatientName());
                        RequestFromeTvAge.setText(body.getData().getPatientAge());
                        RequestFromeTvBloodType.setText(body.getData().getBloodType());
                        RequestFromeTvNumberBlood.setText(body.getData().getBagsNum());
                        RequestFromeTvHospitalName.setText(body.getData().getHospitalName());
                        RequestFromeTvPhone.setText(body.getData().getPhone());
                        RequestFromTvDescription.setText(body.getData().getNotes());
                        latitude = Double.parseDouble(body.getData().getLatitude());
                        longitude = Double.parseDouble(body.getData().getLongitude());
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15f));
                        map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));

                    } else {
                        Toast.makeText(DonationFromActivity.this, "status 0" + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(DonationFromActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<DonationDetails> call, Throwable t) {
                Toast.makeText(DonationFromActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: ");
        map = googleMap;
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.Request_From_map_location);
        mapFragment.getMapAsync(this);
        Log.d(TAG, "initMap: ok");

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
