package com.abdo.hp.bank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.bumptech.glide.Glide;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.model.posts.postes_details.PostesDetails;
import com.abdo.hp.bank.model.posts.postes_details.PostesDetailsData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsArticleFragment extends Fragment {


    @BindView(R.id.Details_img)
    ImageView DetailsImg;
    @BindView(R.id.Details_Title)
    TextView DetailsTitle;
    @BindView(R.id.Details_Toggle)
    ToggleButton DetailsToggle;
    @BindView(R.id.Details_desc)
    TextView DetailsDesc;
    Unbinder unbinder;
    ApiServices apiServices;
    private long ID_DETAILS;

    public DetailsArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_article, container, false);
        unbinder = ButterKnife.bind(this, view);
        SharedPreferencesManger.setSharedPreferences(getActivity());
        apiServices = getClint().create(ApiServices.class);
       ID_DETAILS = getArguments().getLong("id_Details", 0);
        getDetails();
        return view;
    }

    private void getDetails() {
        String api_token = SharedPreferencesManger.LoadStringData(getActivity(), API_TOKEN_PREF);
        apiServices.PostDetails(api_token, ID_DETAILS).enqueue(new Callback<PostesDetails>() {
            @Override
            public void onResponse(Call<PostesDetails> call, Response<PostesDetails> response) {
                PostesDetailsData data = response.body().getData();
                String title = data.getTitle();
                String imagPath = data.getThumbnailFullPath();
                String Desc = data.getContent();
                if (response.body().getStatus()==1){
                DetailsTitle.setText(title);
                DetailsDesc.setText(Desc);
                Glide.with(getContext()).load(imagPath).into(DetailsImg);

                }else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostesDetails> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
