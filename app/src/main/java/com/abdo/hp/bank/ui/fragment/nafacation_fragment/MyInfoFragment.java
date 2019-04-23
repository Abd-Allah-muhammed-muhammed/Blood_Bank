package com.abdo.hp.bank.ui.fragment.nafacation_fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.general.list_of_cities.ListOfCities;
import com.abdo.hp.bank.model.general.list_of_cities.ListOfCitiesDatum;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorate;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorateDatum;
import com.abdo.hp.bank.model.user.profile.Profile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;
import static com.abdo.hp.bank.model.local.PrefData.BIRTH_PREF;
import static com.abdo.hp.bank.model.local.PrefData.BLOOD_TYPR_PREF;
import static com.abdo.hp.bank.model.local.PrefData.LAST_DATE_PREF;
import static com.abdo.hp.bank.model.local.PrefData.MAIL_PREF;
import static com.abdo.hp.bank.model.local.PrefData.NAME_PREF;
import static com.abdo.hp.bank.model.local.PrefData.PASS_PREF;
import static com.abdo.hp.bank.model.local.PrefData.PHONE_PREF;
import static java.util.Calendar.YEAR;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyInfoFragment extends Fragment {


    @BindView(R.id.MyInfo_Tv_name)
    EditText MyInfoTvName;
    @BindView(R.id.MyInfo_Tv_Maile)
    EditText MyInfoTvMaile;
    @BindView(R.id.MyInfo_Tv_birth)
    EditText MyInfoTvBirth;
    @BindView(R.id.MyInfo_Tv_blood_type)
    EditText MyInfoTvBloodType;
    @BindView(R.id.MyInfo_Tv_Last_time)
    EditText MyInfoTvLastTime;
    @BindView(R.id.MyInfo_Spinner_cantry)
    Spinner MyInfoSpinnerCantry;
    @BindView(R.id.MyInfo_Spinner_city)
    Spinner MyInfoSpinnerCity;
    @BindView(R.id.MyInfo_Et_phone)
    EditText MyInfoEtPhone;
    @BindView(R.id.MyInfo_Et_pass)
    EditText MyInfoEtPass;
    @BindView(R.id.MyInfo_Et_convert_pass)
    EditText MyInfoEtConvertPass;
    @BindView(R.id.MyInfo_btn_Edit)
    Button MyInfoBtnEdit;
    Unbinder unbinder;
    private ApiServices apiServices;
    private String name_cantry;
    private long id_cantry;
    private String name_city;
    private Long CITY_ID;
    private String api_token;
    private Calendar myCalendar;

    public MyInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClint().create(ApiServices.class);
        myCalendar = Calendar.getInstance();

        getGovernorates();
        setData();
        return view;
    }

    private void setData() {
        String name = SharedPreferencesManger.LoadStringData(getActivity(), NAME_PREF);
        String mail = SharedPreferencesManger.LoadStringData(getActivity(), MAIL_PREF);
        String birth = SharedPreferencesManger.LoadStringData(getActivity(), BIRTH_PREF);
        String bloodType = SharedPreferencesManger.LoadStringData(getActivity(), BLOOD_TYPR_PREF);
        String lastDate = SharedPreferencesManger.LoadStringData(getActivity(), LAST_DATE_PREF);
        String phone = SharedPreferencesManger.LoadStringData(getActivity(), PHONE_PREF);
        String pass = SharedPreferencesManger.LoadStringData(getActivity(), PASS_PREF);
        MyInfoTvName.setText(name);
        MyInfoTvMaile.setText(mail);
        MyInfoTvBirth.setText(birth);
        MyInfoTvBloodType.setText(bloodType);
        MyInfoTvLastTime.setText(lastDate);
        MyInfoEtPhone.setText(phone);
        MyInfoEtPass.setText(pass);
        MyInfoEtConvertPass.setText(pass);
        api_token = SharedPreferencesManger.LoadStringData(getActivity(), API_TOKEN_PREF);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                    name_cantry = data.get(i).getName();
                    gaveroratesname.add(name_cantry);
                    id_cantry = data.get(i).getId();
                    gaveroratesId.add(id_cantry);
// set Adapter spinner
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, gaveroratesname);

                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    MyInfoSpinnerCantry.setAdapter(spinnerAdapter);
                    //int PositionCantry = SharedPreferencesManger.LoadIntegerData(getActivity(), POSITION_CANTRY_PREF);
                    // MyInfoSpinnerCantry.setSelection(PositionCantry);
                    MyInfoSpinnerCantry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            view.getVerticalScrollbarPosition();
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
                    name_city = data1.get(i).getName();
                    long Id = data1.get(i).getId();
                    cityName.add(name_city);
                    cityId.add(Id);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, cityName);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    MyInfoSpinnerCity.setAdapter(arrayAdapter);
//                    int positionCity = SharedPreferencesManger.LoadIntegerData(getActivity(), POSITION_CITY_PREF);
//                    MyInfoSpinnerCity.setSelection(positionCity);
                    MyInfoSpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    @OnClick(R.id.MyInfo_btn_Edit)
    public void onViewClicked() {

        apiServices.profile(MyInfoTvName.getText().toString()
                , MyInfoTvMaile.getText().toString()
                , MyInfoTvBirth.getText().toString()
                , CITY_ID, MyInfoEtPhone.getText().toString()
                , MyInfoTvLastTime.getText().toString()
                , MyInfoEtPass.getText().toString()
                , MyInfoEtConvertPass.getText().toString(), MyInfoTvBloodType.getText().toString(), api_token).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }


    private void dilogLastBirth() {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                MyInfoTvLastTime.setText(sdf.format(myCalendar.getTime()));


            }
        };


        new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }


    private void dilogBirth() {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                MyInfoTvBirth.setText(sdf.format(myCalendar.getTime()));


            }
        };


        new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }


    @OnClick({R.id.MyInfo_Tv_birth, R.id.MyInfo_Tv_Last_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MyInfo_Tv_birth:
                dilogBirth();

                break;
            case R.id.MyInfo_Tv_Last_time:
                dilogLastBirth();
                break;
        }
    }
}
