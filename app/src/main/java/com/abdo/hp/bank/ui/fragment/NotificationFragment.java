package com.abdo.hp.bank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.adapter.AdapterNotifications;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.model.notification.notification_list.NotificationList;
import com.abdo.hp.bank.model.notification.notification_list.NotificationListDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
public class NotificationFragment extends Fragment {


    @BindView(R.id.Notification_Recycler)
    RecyclerView NotificationRecycler;
    Unbinder unbinder;
    ApiServices apiServices;
    List<NotificationListDatum> notificationListData  = new ArrayList<>();
    AdapterNotifications adapterNotifications;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiServices = getClint().create(ApiServices.class);

        NotificationRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterNotifications = new AdapterNotifications(getContext(), notificationListData);

        getNotifications();
        return view;
    }

    private void getNotifications() {
        String apiToken = LoadStringData(getActivity(), API_TOKEN_PREF);
        apiServices.getNotificationsList(apiToken).enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                if (response.body().getStatus() == 1) {
                    NotificationRecycler.setAdapter(adapterNotifications);
                    Toast.makeText(getContext(), "succeed" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    notificationListData.addAll(response.body().getData().getData());
                    adapterNotifications.notifyDataSetChanged();


                } else {
                    Toast.makeText(getContext(), "error" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<NotificationList> call, Throwable t) {
                Toast.makeText(getContext(), "Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
