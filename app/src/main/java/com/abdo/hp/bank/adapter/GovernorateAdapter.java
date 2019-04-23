package com.abdo.hp.bank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorateDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GovernorateAdapter extends RecyclerView.Adapter<GovernorateAdapter.GovernorateViewHolder> {

    Context context;
    List<ListOfGovernorateDatum> governoratesList = new ArrayList<>();
    public List<String> governorateSelectedList = new ArrayList<>();


    public GovernorateAdapter(Context context, List<ListOfGovernorateDatum> governoratesList) {
        this.context = context;
        this.governoratesList = governoratesList;
    }

    @NonNull
    @Override
    public GovernorateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_check_box, viewGroup, false);
        return new GovernorateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GovernorateViewHolder holder, final int position) {

        holder.checkBoxSetting.setText( governoratesList.get( position ).getName() );
        holder.checkBoxSetting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBoxSetting.isChecked()) {
                    governorateSelectedList.add(String.valueOf( governoratesList.get( position ).getId()) );
                } else {
                    governorateSelectedList.remove(String.valueOf(governoratesList.get( position ).getId()));

                }
            }
        } );

    }

    @Override
    public int getItemCount() {
        return governoratesList.size();
    }

    public class GovernorateViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.check_box_Setting)
        CheckBox checkBoxSetting;
        public GovernorateViewHolder(@NonNull View itemView) {
            super( itemView );
            view = itemView;
            ButterKnife.bind( this, view );
        }
    }
}
