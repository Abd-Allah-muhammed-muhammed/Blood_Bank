package com.abdo.hp.bank.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.hp.bank.helper.SharedPreferencesManger;
import com.bumptech.glide.Glide;
import com.abdo.hp.bank.DetailsArticleFragment;
import com.abdo.hp.bank.R;
import com.abdo.hp.bank.api.ApiServices;
import com.abdo.hp.bank.model.posts.postes.PostesDatum;
import com.abdo.hp.bank.model.posts.postes_toggle_favorite.PostesToggleFavorite;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdo.hp.bank.api.RetrofitClient.getClint;
import static com.abdo.hp.bank.model.local.PrefData.API_TOKEN_PREF;
import static com.abdo.hp.bank.ui.activity.LocationActivity.TAG;


public class AdapterArticleFavorite extends RecyclerView.Adapter<AdapterArticleFavorite.ViewHolder> {
Activity activity ;
    Context context;
    ArrayList<PostesDatum> postesDatumArrayList;
    private ApiServices apiServices;



    public AdapterArticleFavorite(Context context, ArrayList<PostesDatum> postesDatumArrayList , Activity activity1 ) {
        this.context = context;
        this.postesDatumArrayList = postesDatumArrayList;
        this.activity = activity1;
        apiServices = getClint().create(ApiServices.class);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_article, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final PostesDatum postesDatum = postesDatumArrayList.get(i);


        viewHolder.itemArticleTitle.setText(postesDatum.getTitle());
        viewHolder.LayoutFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = postesDatum.getId();
                Bundle bundle  =new Bundle();
                bundle.putLong("id_Details",id);
                DetailsArticleFragment   detailsArticleFragment   =new DetailsArticleFragment();
                detailsArticleFragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_home_frame_replace_fragment, detailsArticleFragment)
                        .commit();
            }
        });
        Glide.with(context).load(postesDatum.getThumbnailFullPath()).into(viewHolder.itemArticleImg);
        viewHolder.itemArticleBtnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "add to favorite'", Toast.LENGTH_SHORT).show();
                if (postesDatum.getIsFavourite()) {
                    PushPOstFavorite(postesDatum.getId());
                    viewHolder.itemArticleBtnFavorite.setBackgroundResource(R.drawable.ic_favoret);
                }else {
                    postesDatum.setIsFavourite(false);
                    viewHolder.itemArticleBtnFavorite.setBackgroundResource(R.drawable.ic_favrite_gray);

                }
            }
        });


    }

    private void PushPOstFavorite(long posi) {
        String api_token = SharedPreferencesManger.LoadStringData(activity, API_TOKEN_PREF);
        apiServices.PostToggleFavourite(posi, api_token).enqueue(new Callback<PostesToggleFavorite>() {
            @Override
            public void onResponse(Call<PostesToggleFavorite> call, Response<PostesToggleFavorite> response) {
                Long status = response.body().getStatus();
                Log.d(TAG, "onResponse: " + status);

            }

            @Override
            public void onFailure(Call<PostesToggleFavorite> call, Throwable t) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return postesDatumArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_article_title)
        TextView itemArticleTitle;
        @BindView(R.id.item_article_btn_favorite)
        ImageView itemArticleBtnFavorite;
        @BindView(R.id.item_article_img)
        ImageView itemArticleImg;
        @BindView(R.id.Layout_Frame)
        RelativeLayout LayoutFrame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
