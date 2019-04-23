package com.abdo.hp.bank.ui.fragment.hom_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.adapter.AdapterArticleFavorite;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.abdo.hp.bank.model.posts.postes.Postes;
import com.abdo.hp.bank.model.posts.postes.PostesDatum;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.helper.SharedPreferencesManger.LoadStringData;
import static com.abdo.hp.bank.helper.SharedPreferencesManger.SaveData;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;

public class ArticleFragment extends Fragment {


    @BindView(R.id.Article_Spinner_chose_article)
    Spinner ArticleSpinnerChoseArticle;
    @BindView(R.id.Article_Et_search_Article)
    EditText ArticleSvSearchArticle;
    @BindView(R.id.Article_RecyclerV)
    RecyclerView ArticleRecyclerV;
    Unbinder unbinder;
    ApiServices apiServices ;

    private ArrayList<PostesDatum> postArticleList = new ArrayList<>();
    private AdapterArticleFavorite adapterArticleFavorite;

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClint().create(ApiServices.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ArticleRecyclerV.setLayoutManager(layoutManager);
         adapterArticleFavorite = new AdapterArticleFavorite(getContext(), postArticleList,getActivity());
        SharedPreferencesManger.setSharedPreferences(getActivity());
        getPostes();
        return view;
    }



    private void getPostes() {
        String api_token = LoadStringData(getActivity(), API_TOKEN_PREF);

        apiServices.getPosts(api_token,1).enqueue(new Callback<Postes>() {
    @Override
    public void onResponse(Call<Postes> call, Response<Postes> response) {
        try {
            Postes body = response.body();
            if (body.getStatus()==1) {
                ArticleRecyclerV.setAdapter(adapterArticleFavorite);
                postArticleList.addAll( body.getData().getData());
                adapterArticleFavorite.notifyDataSetChanged();
            }else {

                Toast.makeText(getContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onFailure(Call<Postes> call, Throwable t) {
       // Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();


    }
});

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
