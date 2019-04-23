package com.abdo.hp.bank.ui.fragment.users_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.model.user.newpass.NewPass;
import com.abdo.hp.bank.ui.activity.Home;

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
public class ChangePassFragment extends Fragment {


    @BindView(R.id.chang_pass_btn)
    Button changPassBtn;
    Unbinder unbinder;
    @BindView(R.id.Change_pass_Inquiry_Code_Et)
    EditText ChangePassInquiryCodeEt;
    @BindView(R.id.Change_pass_new_pass_Et)
    EditText ChangePassNewPassEt;
    @BindView(R.id.Change_pass_Confime_new_pass_Et)
    EditText ChangePassConfimeNewPassEt;
    private ApiServices apiServices;


    public ChangePassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClint().create(ApiServices.class);


        return view;
    }

    public void newPass(){

        Bundle phone = getArguments().getBundle("phone");

        apiServices.newPassword(ChangePassNewPassEt.getText().toString().trim(),
                ChangePassConfimeNewPassEt.getText().toString().trim(),
                ChangePassInquiryCodeEt.getText().toString().trim()
                , String.valueOf(phone)).enqueue(new Callback<NewPass>() {
            @Override
            public void onResponse(Call<NewPass> call, Response<NewPass> response) {

                Long status = response.body().getStatus();
                if (status==1){
                    Intent intent = new Intent(getContext(), Home.class);
                    startActivity(intent);
                }else {
                    String msg = response.body().getMsg();
                    Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewPass> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.chang_pass_btn)
    public void onViewClicked() {
        newPass();

    }
}
