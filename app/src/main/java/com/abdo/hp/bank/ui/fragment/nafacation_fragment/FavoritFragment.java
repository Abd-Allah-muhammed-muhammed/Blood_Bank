package com.abdo.hp.bank.ui.fragment.nafacation_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.adapter.AdapterArticleFavorite;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.posts.postes.PostesDatum;
import com.abdo.hp.bank.model.posts.posts_favorete_list.PostesFavouriteList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.helper.SharedPreferencesManger.LoadStringData;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;


public class FavoritFragment extends Fragment {
    @BindView(R.id.RecyclerV_Favorite)
    RecyclerView RecyclerVFavorite;
    Unbinder unbinder;
    private ApiServices apiServices;
    private static final String TAG = "TAG";
    private ArrayList<PostesDatum> postArticleList = new ArrayList<>();
    private AdapterArticleFavorite adapterMyFavorite;

    public FavoritFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorit, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClint().create(ApiServices.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerVFavorite.setLayoutManager(layoutManager);
        adapterMyFavorite = new AdapterArticleFavorite(getContext(), postArticleList , getActivity());
        SharedPreferencesManger.setSharedPreferences(getActivity()
        );
        getPostes();
        return view;
    }

    private void getPostes() {
        String api_token = LoadStringData(getActivity(), API_TOKEN_PREF);
        apiServices.myFavourites(api_token).enqueue(new Callback<PostesFavouriteList>() {
            @Override
            public void onResponse(Call<PostesFavouriteList> call, Response<PostesFavouriteList> response) {

                RecyclerVFavorite.setAdapter(adapterMyFavorite);

                PostesFavouriteList data = response.body();
                if (data.getStatus()==1) {

                    postArticleList.addAll(data.getData().getData());
                    adapterMyFavorite.notifyDataSetChanged();
                }else {

                    Toast.makeText(getContext(), ""+data.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PostesFavouriteList> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}