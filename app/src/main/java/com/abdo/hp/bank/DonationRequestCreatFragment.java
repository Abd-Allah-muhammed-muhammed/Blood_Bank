package com.abdo.hp.bank;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.donation.donation_creat.DonationRequestCreate;
import com.abdo.hp.bank.model.general.list_of_cities.ListOfCities;
import com.abdo.hp.bank.model.general.list_of_cities.ListOfCitiesDatum;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorate;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorateDatum;
import com.abdo.hp.bank.ui.activity.Home;
import com.abdo.hp.bank.ui.activity.LocationActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonationRequestCreatFragment extends Fragment {


    @BindView(R.id.make_request_Et_name)
    EditText makeRequestEtName;
    @BindView(R.id.make_request_Et_age)
    EditText makeRequestEtAge;
    @BindView(R.id.Make_Request_Et_Blood_type)
    EditText MakeRequestEtBloodType;
    @BindView(R.id.Make_Request_Et_Number_of_Blood)
    EditText MakeRequestEtNumberOfBlood;
    @BindView(R.id.make_request_image_location_Hospital)
    ImageButton makeRequestImageLocationHospital;
    @BindView(R.id.Make_Request_tv_Address_of_hospital)
    EditText MakeRequestTvAddressOfHospital;
    @BindView(R.id.MAke_Request_Spinner_Cantry)
    Spinner MAkeRequestSpinnerCantry;
    @BindView(R.id.Make_Request_layout_cantry)
    LinearLayout MakeRequestLayoutCantry;
    @BindView(R.id.Make_Request_Spinner_Cities)
    Spinner MakeRequestSpinnerCities;
    @BindView(R.id.Make_Request_Et_Phone_Number)
    EditText MakeRequestEtPhoneNumber;
    @BindView(R.id.Request_notes_Tv)
    EditText RequestNotesTv;
    @BindView(R.id.Make_Request_btn_send_request)
    Button MakeRequestBtnSendRequest;
    Unbinder unbinder;
    @BindView(R.id.Make_Request_Et_Hospital_name)
    EditText MakeRequestEtHospitalName;
    @BindView(R.id.Make_request_layout_Hospital_address)
    LinearLayout MakeRequestLayoutHospitalAddress;
    @BindView(R.id.Make_Request_layout_cities)
    LinearLayout MakeRequestLayoutCities;
    private double LONG;
    private double LAT;
    private ApiServices apiServices;
    private long CITY_ID;


    public DonationRequestCreatFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_donation_request_creat, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClint().create(ApiServices.class);
        getGovernorates();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.make_request_image_location_Hospital, R.id.Make_Request_btn_send_request})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.make_request_image_location_Hospital:
                openMap();
                break;
            case R.id.Make_Request_btn_send_request:
                createDonation();
                break;
        }
    }

    private void openMap() {
        Intent intent = new Intent(getActivity(), LocationActivity.class);
        startActivityForResult(intent, 1);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // double late=data.getDoubleExtra("lat",1);
                LAT = data.getDoubleExtra("lat", 1);
                LONG = data.getDoubleExtra("long", 1);
                String hospitalAddress = getAddressFromCoordinates(getContext(), LAT, LONG);
                MakeRequestTvAddressOfHospital.setText(hospitalAddress);
            }
        }
    }

    public void createDonation() {
        String api_token = SharedPreferencesManger.LoadStringData(getActivity(), API_TOKEN_PREF);
        String hospitalAddress = getAddressFromCoordinates(getContext(), LAT, LONG);
       apiServices.donationRequestCreat(api_token,makeRequestEtName.getText().toString(),makeRequestEtAge.getText().toString()
       ,MakeRequestEtBloodType.getText().toString(),MakeRequestEtNumberOfBlood.getText().toString(),MakeRequestEtHospitalName
       .getText().toString(),hospitalAddress,CITY_ID,MakeRequestEtPhoneNumber.getText().toString(),RequestNotesTv.getText().toString()
       ,LAT,LONG).enqueue(new Callback<DonationRequestCreate>() {
           @Override
           public void onResponse(Call<DonationRequestCreate> call, Response<DonationRequestCreate> response) {
               if (response.body().getStatus()!=1){
                   Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

               }else {

                   Intent intent = new Intent(getActivity(),Home.class);
                   startActivity(intent);
               }

           }

           @Override
           public void onFailure(Call<DonationRequestCreate> call, Throwable t) {

           }
       });

    }

    public void getGovernorates() {
        apiServices.governorates().enqueue(new Callback<ListOfGovernorate>() {
            @Override
            public void onResponse(Call<ListOfGovernorate> call, Response<ListOfGovernorate> response) {
                List<ListOfGovernorateDatum> data = response.body().getData();
                List<String> gaveroratesname = new ArrayList<>();
                final ArrayList<Long> gaveroratesId = new ArrayList<>();
                gaveroratesname.add("اختار المحافظة");
                gaveroratesId.add((long) 0);
                for (int i = 0; i < data.size(); i++) {
                    gaveroratesname.add(data.get(i).getName());
                    gaveroratesId.add(data.get(i).getId());
// set Adapter spinner
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, gaveroratesname);

                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    MAkeRequestSpinnerCantry.setAdapter(spinnerAdapter);
                    MAkeRequestSpinnerCantry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position != 0) {

                                setCitySpinner(gaveroratesId.get(position));

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<ListOfGovernorate> call, Throwable t) {

            }
        });
    }

    private void setCitySpinner(long id_gavernment) {
        apiServices.cities(id_gavernment).enqueue(new Callback<ListOfCities>() {
            @Override
            public void onResponse(Call<ListOfCities> call, Response<ListOfCities> response) {

                List<ListOfCitiesDatum> data1 = response.body().getData();

                final ArrayList<String> cityName = new ArrayList<>();
                final ArrayList<Long> cityId = new ArrayList<>();

                for (int i = 0; i < data1.size(); i++) {
                    String name = data1.get(i).getName();
                    long Id = data1.get(i).getId();
                    cityName.add(name);
                    cityId.add(Id);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, cityName);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    MakeRequestSpinnerCities.setAdapter(arrayAdapter);
                    MakeRequestSpinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            CITY_ID = cityId.get(position);


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ListOfCities> call, Throwable t) {
            }
        });

    }


    public static String getAddressFromCoordinates(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        String address = null;
        List<Address> addressList;
        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1);

            address = addressList.get(0).getAddressLine(0);


        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("Donation: ", "address : " + address);

        return address;


    }

}
