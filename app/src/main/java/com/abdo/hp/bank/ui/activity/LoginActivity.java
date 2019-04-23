package com.abdo.hp.bank.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.ui.fragment.users_fragment.LoginFragment;

import static com.abdo.hp.bank.helper.HelperMethod.replace;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager manager = getSupportFragmentManager();
        replace(new LoginFragment(), R.id.Login_Frame_Replace, manager.beginTransaction());

    }

}
