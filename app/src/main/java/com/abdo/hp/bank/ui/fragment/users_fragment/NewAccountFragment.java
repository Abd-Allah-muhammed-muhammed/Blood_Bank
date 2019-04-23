package com.abdo.hp.bank.ui.fragment.users_fragment;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.general.list_of_cities.ListOfCities;
import com.abdo.hp.bank.model.general.list_of_cities.ListOfCitiesDatum;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorate;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorateDatum;
import com.abdo.hp.bank.model.user.register.Register;
import com.abdo.hp.bank.ui.activity.Home;

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

import static com.abdo.hp.bank.model.local.PrefData.BIRTH_PREF;
import static com.abdo.hp.bank.model.local.PrefData.BLOOD_TYPR_PREF;
import static com.abdo.hp.bank.model.local.PrefData.CITY_PREF;
import static com.abdo.hp.bank.model.local.PrefData.LAST_DATE_PREF;
import static com.abdo.hp.bank.model.local.PrefData.MAIL_PREF;
import static com.abdo.hp.bank.model.local.PrefData.NAME_PREF;
import static com.abdo.hp.bank.model.local.PrefData.PASS_PREF;
import static com.abdo.hp.bank.model.local.PrefData.PHONE_PREF;
import static com.abdo.hp.bank.model.local.PrefData.POSITION_CANTRY_PREF;
import static com.abdo.hp.bank.model.local.PrefData.POSITION_CITY_PREF;
import static java.util.Calendar.YEAR;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewAccountFragment extends Fragment {

    @BindView(R.id.NewAcc_Et_Name)
    EditText NewAccEtName;
    @BindView(R.id.NewAcc_Et_Mail)
    EditText NewAccEtMail;
    @BindView(R.id.NewAcc_Tv_Birth)
    TextView NewAccTvBirth;


    @BindView(R.id.NewAcc_Tv_Last_date)
    TextView NewAccTvLastDate;
    @BindView(R.id.NewAcc_spinner_cantry)
    Spinner NewAccSpinnerCantry;
    @BindView(R.id.NewAcc_spinner_city)
    Spinner NewAccSpinnerCity;
    @BindView(R.id.NewAcc_Ed_phone)
    EditText NewAccEdPhone;
    @BindView(R.id.NewAcc_Et_pss)
    EditText NewAccEtPss;
    @BindView(R.id.NewAcc_Et_Confirm_pss)
    EditText NewAccEtConfirmPss;
    @BindView(R.id.NewAcc_Btn_Regester)
    Button NewAccBtnRegester;
    Unbinder unbinder;
    @BindView(R.id.birth_day_layout)
    LinearLayout birthDayLayout;
    @BindView(R.id.last_date_day_layout)
    LinearLayout lastDateDayLayout;
    @BindView(R.id.NewAcc_Tv_Blood_Type)
    EditText NewAccTvBloodType;
    private static final String TAG = "TAG";

    private ApiServices apiServices;
    private long CITY_ID;
    Calendar myCalendar;
    private SharedPreferences pref;
    private String name_city;
    private String name_cantry;
    private long id_cantry;
    private int selectedItemPositionCity;
    private int selectedItemPositionCantry;

    public NewAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        SharedPreferencesManger.setSharedPreferences(getActivity());
        apiServices = getClint().create(ApiServices.class);
        myCalendar = Calendar.getInstance();
        getGovernorates();
        return view;


    }


    private void register() {
        final String name = NewAccEtName.getText().toString();
        final String mail = NewAccEtMail.getText().toString();
        final String birth = NewAccTvBirth.getText().toString();
        final String phone = NewAccEdPhone.getText().toString();
        final String lastDate = NewAccTvLastDate.getText().toString();
        final String pass = NewAccEtPss.getText().toString();
        final String confirmPass = NewAccEtConfirmPss.getText().toString();
        final String typeBlood = NewAccTvBloodType.getText().toString();
        apiServices.newRegister(name, mail, birth, CITY_ID, phone, lastDate, pass, confirmPass, typeBlood)
                .enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(@NonNull Call<Register> call, @NonNull Response<Register> response) {
                        try {
                            Long status = response.body().getStatus();
                            if (status != 1) {
                                Log.d(TAG, "getStatus: " + status);
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                SharedPreferencesManger.SaveData(getActivity(), NAME_PREF, name);
                                SharedPreferencesManger.SaveData(getActivity(), MAIL_PREF, mail);
                                SharedPreferencesManger.SaveData(getActivity(), BIRTH_PREF, birth);
                                SharedPreferencesManger.SaveData(getActivity(), PHONE_PREF, phone);
                                SharedPreferencesManger.SaveData(getActivity(), LAST_DATE_PREF, lastDate);
                                SharedPreferencesManger.SaveData(getActivity(), PASS_PREF, pass);
                                SharedPreferencesManger.SaveData(getActivity(), BLOOD_TYPR_PREF, typeBlood);
                                SharedPreferencesManger.SaveData(getActivity(), CITY_PREF, CITY_ID);
                                SharedPreferencesManger.SaveData(getActivity(), POSITION_CITY_PREF, selectedItemPositionCity);
                                SharedPreferencesManger.SaveData(getActivity(), POSITION_CANTRY_PREF, selectedItemPositionCantry);
                                Intent intent = new Intent(getContext(), Home.class);
                                startActivity(intent);

                            }
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();


                        }


                    }

                    @Override
                    public void onFailure(@NonNull Call<Register> call, @NonNull Throwable t) {

                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.NewAcc_Btn_Regester)
    public void onViewClicked() {
        register();

    }


    public void getGovernorates() {
        apiServices.governorates().enqueue(new Callback<ListOfGovernorate>() {
            @Override
            public void onResponse(Call<ListOfGovernorate> call, Response<ListOfGovernorate> response) {
                List<ListOfGovernorateDatum> data = response.body().getData ();
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
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_spinner_item, gaveroratesname);

                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    NewAccSpinnerCantry.setAdapter(spinnerAdapter);
                    NewAccSpinnerCantry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedItemPositionCantry = NewAccSpinnerCantry.getSelectedItemPosition();
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
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_spinner_item, cityName);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    NewAccSpinnerCity.setAdapter(arrayAdapter);
                    NewAccSpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            CITY_ID = cityId.get(position);
                            selectedItemPositionCity = NewAccSpinnerCity.getSelectedItemPosition();
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


    @OnClick({R.id.NewAcc_Tv_Birth, R.id.NewAcc_Tv_Last_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.NewAcc_Tv_Birth:
                dilogBirth();
                break;
            case R.id.NewAcc_Tv_Last_date:
                dilogLastBirth();
                break;
        }
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
                NewAccTvBirth.setText(sdf.format(myCalendar.getTime()));


            }
        };


        new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

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
                NewAccTvLastDate.setText(sdf.format(myCalendar.getTime()));


            }
        };


        new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

}




