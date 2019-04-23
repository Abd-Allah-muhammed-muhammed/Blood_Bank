package com.abdo.hp.bank.ui.fragment.hom_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.adapter.AdapterDonationRequest;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.donation.donation_request.DonationRequest;
import com.abdo.hp.bank.model.donation.donation_request.DonationRequestDatum;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorate;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorateDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.helper.SharedPreferencesManger.LoadStringData;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment {


    @BindView(R.id.Requests_Sp_Blood_type)
    Spinner RequestsSpBloodType;
    @BindView(R.id.Requests_Sp_city)
    Spinner RequestsSpCity;
    @BindView(R.id.Requestes_donation_Rv)
    RecyclerView RequestesDonationRv;
    Unbinder unbinder;
    @BindView(R.id.Requests_choseBloodT)
    EditText RequestsChoseBloodT;
    @BindView(R.id.Requests_choseCantry)
    EditText RequestsChoseCantry;
    @BindView(R.id.Requestss_Search)
    ImageView RequestssSearch;
    private ArrayList<DonationRequestDatum> listDonation = new ArrayList<>();
    private ApiServices apiServices;
    private AdapterDonationRequest adapterDonationRequest;

    public RequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requests, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClint().create(ApiServices.class);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        RequestesDonationRv.setLayoutManager(manager);
        adapterDonationRequest = new AdapterDonationRequest(getActivity(), getContext(), listDonation);
        RequestesDonationRv.setAdapter(adapterDonationRequest);
        SharedPreferencesManger.setSharedPreferences(getActivity());
        getRequestes();
        putBloodTyp();
        putGavernment();

        return view;
    }

    private void putGavernment() {
        apiServices.governorates().enqueue(new Callback<ListOfGovernorate>() {
            @Override
            public void onResponse(Call<ListOfGovernorate> call, Response<ListOfGovernorate> response) {
                List<ListOfGovernorateDatum> data = response.body().getData();
                String nameofGavornement = null;

                for (int i = 0; i < data.size(); i++) {
                     nameofGavornement = data.get(i).getName();

                }

                List<String> listGovernment = new ArrayList<>();
                if (nameofGavornement!=null){
                    listGovernment.add(nameofGavornement);
                }

                ArrayAdapter<String> spinnerAdapterGovernment = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item, listGovernment);
                spinnerAdapterGovernment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                RequestsSpCity.setAdapter(spinnerAdapterGovernment);
                RequestsSpCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        RequestsChoseCantry.setText(parent.getSelectedItem().toString());


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ListOfGovernorate> call, Throwable t) {

            }
        });


    }

    private void putBloodTyp() {

        List<String> listBloodType = new ArrayList<>();

        listBloodType.add("A+");
        listBloodType.add("B+");
        listBloodType.add("A-");
        listBloodType.add("B-");
        listBloodType.add("AB+");
        listBloodType.add("AB-");
        listBloodType.add("O+");
        listBloodType.add("O-");


        ArrayAdapter<String> spinnerAdapterBloodType = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, listBloodType);
        spinnerAdapterBloodType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RequestsSpBloodType.setAdapter(spinnerAdapterBloodType);
        RequestsSpBloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    RequestsChoseBloodT.setText(parent.getSelectedItem().toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getRequestes() {
        String api_token = LoadStringData(getActivity(), API_TOKEN_PREF);
        apiServices.getDonationRequests(api_token).enqueue(new Callback<DonationRequest>() {
            @Override
            public void onResponse(Call<DonationRequest> call, Response<DonationRequest> response) {

                DonationRequest body = response.body();
                listDonation.addAll(body.getData().getData());
                adapterDonationRequest.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DonationRequest> call, Throwable t) {

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Requestss_Search)
    public void onViewClicked() {
        Toast.makeText(getContext(), "blood type is "+RequestsChoseBloodT.getText(), Toast.LENGTH_SHORT).show();
    }
}
