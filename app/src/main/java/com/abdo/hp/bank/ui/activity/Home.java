package com.abdo.hp.bank.ui.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdo.hp.bank.DonationRequestCreatFragment;
import com.abdo.hp.bank.R;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.notification.notificationToken.NotificationToken;
import com.abdo.hp.bank.model.notification.notification_count.NotificationCount;
import com.abdo.hp.bank.model.notification.removenotifications.RemoveNotifications;
import com.abdo.hp.bank.ui.fragment.ContentFragment;
import com.abdo.hp.bank.ui.fragment.NotificationFragment;
import com.abdo.hp.bank.ui.fragment.nafacation_fragment.ConnectWithUsFragment;
import com.abdo.hp.bank.ui.fragment.nafacation_fragment.FavoritFragment;
import com.abdo.hp.bank.ui.fragment.nafacation_fragment.HowToUsedFragment;
import com.abdo.hp.bank.ui.fragment.nafacation_fragment.MyInfoFragment;
import com.abdo.hp.bank.ui.fragment.nafacation_fragment.Setting_notificationFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.helper.HelperMethod.replace;
import static com.abdo.hp.bank.helper.SharedPreferencesManger.LoadStringData;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.App_Bar_TextViewChange)
    TextView AppBarTextViewChange;
    @BindView(R.id.textContNotification)
    TextView textCountNotification;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.iconContNotification)
    ImageView iconContNotification;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private FragmentManager manager = getSupportFragmentManager();
    private ApiServices apiServices;
    private String TAG = "log";
    private int NOT_USED = 22;
    private Context ctx;
    private int mId = 33;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        AppBarTextViewChange.setText("الرئيسية ");




        FirebaseApp.initializeApp(this);
        String token = FirebaseInstanceId.getInstance().getToken();
        pushNotification(token);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DonationRequestCreatFragment mDonationRequestCreatFragment = new DonationRequestCreatFragment();
                AppBarTextViewChange.setText("انشاء طلب تبرع ");

                replace(mDonationRequestCreatFragment, R.id.content_home_frame_replace_fragment, manager.beginTransaction());

            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ContentFragment contentFragment = new ContentFragment();
        replace(contentFragment, R.id.content_home_frame_replace_fragment, manager.beginTransaction());
        String api_token = LoadStringData(this, API_TOKEN_PREF);

        getCountNotifications(api_token);

    }

    private void getCountNotifications(String api_token) {
        apiServices = getClint().create(ApiServices.class);
        apiServices.getNotificationsCount(api_token).enqueue(new Callback<NotificationCount>() {
            @Override
            public void onResponse(Call<NotificationCount> call, Response<NotificationCount> response) {

                if (response.body().getStatus() == 1) {
                    textCountNotification.setText(response.body().getData().getNotificationsCount().toString());


                } else {
                    String msg = response.body().getMsg();
                    Log.d(TAG, "getCountNotifications: " + msg);
                }
            }

            @Override
            public void onFailure(Call<NotificationCount> call, Throwable t) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_info) {
            AppBarTextViewChange.setText("معلوماتي ");
            replace(new MyInfoFragment(), R.id.content_home_frame_replace_fragment, manager.beginTransaction());

        } else if (id == R.id.setting_notification) {
            AppBarTextViewChange.setText("تعديل الاشعارات ");

            replace(new Setting_notificationFragment(), R.id.content_home_frame_replace_fragment, manager.beginTransaction());

        } else if (id == R.id.favoret) {
            AppBarTextViewChange.setText("المفضلة ");

            replace(new FavoritFragment(), R.id.content_home_frame_replace_fragment, manager.beginTransaction());

        } else if (id == R.id.info_used) {
            AppBarTextViewChange.setText("معلومات الاستخدام ");

            replace(new HowToUsedFragment(), R.id.content_home_frame_replace_fragment, manager.beginTransaction());

        } else if (id == R.id.connect_withe_use) {
            AppBarTextViewChange.setText("اتصل بنا  ");

            replace(new ConnectWithUsFragment(), R.id.content_home_frame_replace_fragment, manager.beginTransaction());

        } else if (id == R.id.about_app) {
            AppBarTextViewChange.setText("عن التطبيق ");

            replace(new HowToUsedFragment(), R.id.content_home_frame_replace_fragment, manager.beginTransaction());
        } else if (id == R.id.home) {
            replace(new ContentFragment(), R.id.content_home_frame_replace_fragment, manager.beginTransaction());

        } else if (id == R.id.log_out) {
            SharedPreferencesManger.clean(this);
            FirebaseApp.initializeApp(this);
            String token = FirebaseInstanceId.getInstance().getToken();
            removeNotifications(token);
            finish();
        }

// navigation
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void removeNotifications(String token) {
        apiServices = getClint().create(ApiServices.class);
        String api_token = LoadStringData(this, API_TOKEN_PREF);
        apiServices.removeNotification(token, api_token, "android").enqueue(new Callback<RemoveNotifications>() {
            @Override
            public void onResponse(Call<RemoveNotifications> call, Response<RemoveNotifications> response) {
                Long status = response.body().getStatus();
                if (status == 1) {
                    Log.d(TAG, "removeNotifications: status" + response.body().getMsg());

                } else {
                    Log.d(TAG, "removeNotifications: status" + response.body().getMsg());


                }
            }

            @Override
            public void onFailure(Call<RemoveNotifications> call, Throwable t) {

            }
        });
    }

    private void pushNotification(String token) {

        apiServices = getClint().create(ApiServices.class);
        String apiToken = LoadStringData(this, API_TOKEN_PREF);
        apiServices.registerNotification(token, apiToken, "android").enqueue(new Callback<NotificationToken>() {
            @Override
            public void onResponse(Call<NotificationToken> call, Response<NotificationToken> response) {
                if (response.body().getStatus() == 1) {

                    Log.d(TAG, "onResponse: " + response.body().getMsg());

                } else {
                    Log.d(TAG, "onResponse: " + response.body().getMsg());

                }

            }

            @Override
            public void onFailure(Call<NotificationToken> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.iconContNotification)
    public void onViewClicked() {
        AppBarTextViewChange.setText("الاشعارات ");
        replace(new NotificationFragment(), R.id.content_home_frame_replace_fragment, manager.beginTransaction());

    }



}
