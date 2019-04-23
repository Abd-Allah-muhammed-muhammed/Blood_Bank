package com.abdo.hp.bank.ui.activity.splash_avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.ui.activity.LoginActivity;


import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSplashActivity extends AppCompatActivity {

    @BindView(R.id.after_splash_btn_skip)
    Button SkipIdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);
        ButterKnife.bind(this);

        // inti the button

        SkipIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterSplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
