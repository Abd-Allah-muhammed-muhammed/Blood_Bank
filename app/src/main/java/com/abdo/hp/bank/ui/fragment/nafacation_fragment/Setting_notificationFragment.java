package com.abdo.hp.bank.ui.fragment.nafacation_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.adapter.BloodTypeAdapter;
import com.abdo.hp.bank.adapter.GovernorateAdapter;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.api.RetrofitClient;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorate;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorateDatum;
import com.abdo.hp.bank.model.list_blood_typ.ListBloodType;
import com.abdo.hp.bank.model.list_blood_typ.ListBloodTypeData;
import com.abdo.hp.bank.model.notification.notificationsettings.NotificationSettings;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class Setting_notificationFragment extends Fragment {


    @BindView(R.id.setting_noti_Blood_Type)
    RecyclerView settingNotiBloodType;
    @BindView(R.id.setting_noti_cantry)
    RecyclerView settingNotiCantry;
    Unbinder unbinder;
    @BindView(R.id.Save_Settings_noti)
    Button SaveSettingsNoti;
    private List<String> list_bloo_type = new ArrayList<>();
    private ApiServices apiServer;
    private String apiToken;
    private List<ListBloodTypeData> bloodList;
    private List<ListOfGovernorateDatum> governoratesList;
    private BloodTypeAdapter adapterBloodType;
    private GovernorateAdapter adapterGovernment;

    public Setting_notificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settng_notification, container, false);
        unbinder = ButterKnife.bind(this, view);
        bloodList = new ArrayList<>();
        governoratesList = new ArrayList<>();
        apiServer = RetrofitClient.getClint().create( ApiServices.class );
        GridLayoutManager managerBlood = new GridLayoutManager( getContext(), 4 );
        settingNotiBloodType.setLayoutManager( managerBlood );
        adapterBloodType = new BloodTypeAdapter( getContext(), bloodList );
        settingNotiBloodType.setAdapter( adapterBloodType );
        getBloodTypeList();
        getGovernorates();
        GridLayoutManager manager = new GridLayoutManager( getContext(), 3 );
        settingNotiCantry.setLayoutManager( manager );
        adapterGovernment = new GovernorateAdapter( getContext(), governoratesList );
        settingNotiCantry.setAdapter( adapterGovernment );
        apiToken = SharedPreferencesManger.LoadStringData( getActivity(), "apiToken" );
        return view;
    }


    private void getBloodTypeList() {
        apiServer.getBloodList().enqueue( new Callback<ListBloodType>() {
            @Override
            public void onResponse(Call<ListBloodType> call, Response<ListBloodType> response) {
                if (response.body().getStatus() == 1) {
                    bloodList.addAll( response.body().getData() );
                    adapterBloodType.notifyDataSetChanged();
                } else {
                    Toast.makeText( getContext(), response.body().getMsg(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ListBloodType> call, Throwable t) {

            }
        } );
    }



    private void getGovernorates() {

        apiServer.governorates().enqueue( new Callback<ListOfGovernorate>() {
            @Override
            public void onResponse(Call<ListOfGovernorate> call, Response<ListOfGovernorate> response) {
                if (response.body().getStatus() == 1) {
                    governoratesList.addAll( response.body().getData() );
                    adapterGovernment.notifyDataSetChanged();

                } else {
                    Toast.makeText( getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ListOfGovernorate> call, Throwable t) {

            }
        } );

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Save_Settings_noti)
    public void onViewClicked() {

        saveData();

    }


    private void saveData() {
        if (adapterBloodType.bloodTypeSelectedList.isEmpty()) {
            Toast.makeText( getContext(), "Not Selected NotificationData", Toast.LENGTH_SHORT ).show();
        } else {
            apiServer.settingsNotification( apiToken, adapterGovernment.governorateSelectedList,
                    adapterBloodType.bloodTypeSelectedList ).enqueue( new Callback<NotificationSettings>() {
                @Override
                public void onResponse(Call<NotificationSettings> call, Response<NotificationSettings> response) {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText( getContext(), response.body().getMsg(), Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( getContext(), response.body().getMsg(), Toast.LENGTH_SHORT ).show();
                    }
                }

                @Override
                public void onFailure(Call<NotificationSettings> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_SHORT ).show();
                }
            } );
        }
    }
}
