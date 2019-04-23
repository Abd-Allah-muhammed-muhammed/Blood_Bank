package com.abdo.hp.bank.ui.fragment.users_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.model.user.passwordrest.RestPassword;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.helper.HelperMethod.replace;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivPhoneFragment extends Fragment {


    @BindView(R.id.Active_phone_btn_send_code)
    Button SendCode;
    Unbinder unbinder;
    @BindView(R.id.Activ_phone_edit_text)
    EditText ActivPhoneEditText;
    private ApiServices apiServices;


    public ActivPhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activ_phone, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClint().create(ApiServices.class);


        return view;
    }


    public void sendCode() {
        apiServices.resetPassword(ActivPhoneEditText.getText().toString().trim()).enqueue(new Callback<RestPassword>() {
            @Override
            public void onResponse(Call<RestPassword> call, Response<RestPassword> response) {

                Long status = response.body().getStatus();
                if (status==1){
                    FragmentManager manager = getFragmentManager();

                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("phone",ActivPhoneEditText.getText().toString().trim());

                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    changePassFragment.setArguments(bundle);
                    replace(changePassFragment, R.id.Login_Frame_Replace, manager.beginTransaction());
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RestPassword> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Active_phone_btn_send_code)
    public void onViewClicked() {
        sendCode();


    }


}
