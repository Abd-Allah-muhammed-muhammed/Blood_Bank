package com.abdo.hp.bank.ui.fragment.users_fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.model.user.login.LoginClient;
import com.abdo.hp.bank.ui.activity.Home;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.helper.HelperMethod.replace;
import static com.abdo.hp.bank.helper.SharedPreferencesManger.LoadStringData;
import static com.abdo.hp.bank.helper.SharedPreferencesManger.SaveData;
import static com.abdo.hp.bank.helper.SharedPreferencesManger.remove;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;
import static com.abdo.hp.bank.model.local.PrefData.BIRTH_PREF;
import static com.abdo.hp.bank.model.local.PrefData.BLOOD_TYPR_PREF;
import static com.abdo.hp.bank.model.local.PrefData.CITY_PREF;
import static com.abdo.hp.bank.model.local.PrefData.LAST_DATE_PREF;
import static com.abdo.hp.bank.model.local.PrefData.MAIL_PREF;
import static com.abdo.hp.bank.model.local.PrefData.NAME_PREF;
import static com.abdo.hp.bank.model.local.PrefData.PASS_PREF;
import static com.abdo.hp.bank.model.local.PrefData.PHONE_PREF;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    @BindView(R.id.did_you_forget_the_pass)
    TextView didYouForgetThePass;
    @BindView(R.id.Register_Btn_Enter)
    Button EnterBtn;
    @BindView(R.id.Register_Btn_NewAcc)
    Button newAccountBtn;
    Unbinder unbinder;
    @BindView(R.id.Register_Et_phone)
    EditText RegisterEtPhone;
    @BindView(R.id.Register_Et_pass)
    EditText RegisterEtPass;
    @BindView(R.id.Register_Sp_Remember_me)
    CheckBox RegisterSpRememberMe;
    private ApiServices apiServices;
    Context context ;
    private AlertDialog alertDialog;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
       context = getContext() ;
        apiServices = getClint().create(ApiServices.class);

         alertDialog= new SpotsDialog.Builder().setContext(getActivity()).build();


        RegisterSpRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    SaveData(getActivity(),"phone_login",RegisterEtPhone.getText().toString());
                    SaveData(getActivity(),"pass_login",RegisterEtPass.getText().toString());

                }else {
                    remove(getActivity(),"phone_login");
                    remove(getActivity(),"pass_login");

                }


            }
        });
        return view;
    }

    private void login() {
        alertDialog.setMessage("Loading");
        alertDialog.show();
        String phone = RegisterEtPhone.getText().toString();

        String pass = RegisterEtPass.getText().toString();



        apiServices.login(phone, pass).enqueue(new Callback<LoginClient>() {
            @Override
            public void onResponse(Call<LoginClient> call, Response<LoginClient> response) {
                Long status = response.body().getStatus();
                if (status == 1) {
                    String apiToken = response.body().getData().getApiToken();
                    //save data to sharedPREF
                    if (response.body().getData().getClient()!=null) {
                        SaveData(getActivity(), API_TOKEN_PREF, apiToken);
                        SaveData(getActivity(), NAME_PREF, response.body().getData().getClient().getName());
                        SaveData(getActivity(), BIRTH_PREF, response.body().getData().getClient().getBirthDate());
                        SaveData(getActivity(), MAIL_PREF, response.body().getData().getClient().getEmail());
                        SaveData(getActivity(), LAST_DATE_PREF, response.body().getData().getClient().getDonationLastDate());
                        SaveData(getActivity(), BLOOD_TYPR_PREF, response.body().getData().getClient().getBloodType().getName());
                        SaveData(getActivity(), MAIL_PREF, response.body().getData().getClient().getEmail());
                        SaveData(getActivity(), CITY_PREF, response.body().getData().getClient().getCityId());
                        SaveData(getActivity(), PHONE_PREF, response.body().getData().getClient().getPhone());
                        SaveData(getActivity(), PASS_PREF, String.valueOf(RegisterEtPass.getText()));
                    }else {

                    }
                    String msg = response.body().getMsg();
                    Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
                    //startActivity
                    alertDialog.cancel();
                    Intent intent = new Intent(getContext(), Home.class);
                    startActivity(intent);

                } else {
                    alertDialog.cancel();
                    Toast.makeText(getContext(), "error" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginClient> call, Throwable t) {
alertDialog.cancel();
                Toast.makeText(getActivity(), "onFailure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.did_you_forget_the_pass, R.id.Register_Btn_Enter, R.id.Register_Btn_NewAcc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.did_you_forget_the_pass:
                FragmentManager manager = getFragmentManager();
                replace(new ActivPhoneFragment(), R.id.Login_Frame_Replace, manager.beginTransaction());
                break;
            case R.id.Register_Btn_Enter:
                login();
                break;
            case R.id.Register_Btn_NewAcc:
                FragmentManager manager1 = getFragmentManager();
                replace(new NewAccountFragment(), R.id.Login_Frame_Replace, manager1.beginTransaction());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String phone = LoadStringData(getActivity(), "phone_login");
        String pass = LoadStringData(getActivity(), "pass_login");
        RegisterEtPhone.setText(phone);
        RegisterEtPass.setText(pass);
    }
}
