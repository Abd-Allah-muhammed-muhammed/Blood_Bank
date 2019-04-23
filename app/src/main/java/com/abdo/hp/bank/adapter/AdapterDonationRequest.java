package com.abdo.hp.bank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.model.donation.donation_request.DonationRequestDatum;
import com.abdo.hp.bank.ui.fragment.hom_fragment.DonationFromActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterDonationRequest extends RecyclerView.Adapter<AdapterDonationRequest.ViewHolder> {
    Activity activity;
    Context context;
    ArrayList<DonationRequestDatum> donationRequestDatumArrayList;


    public AdapterDonationRequest(Activity activity,Context context, ArrayList<DonationRequestDatum> donationRequestDatumArrayList) {
        this.context = context;
        this.donationRequestDatumArrayList = donationRequestDatumArrayList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,  int position) {

         final DonationRequestDatum requestDatum = donationRequestDatumArrayList.get(position);
         String patientName = requestDatum.getPatientName();
        String hospitalName = requestDatum.getHospitalName();
        String bloodType = requestDatum.getBloodType();
        String name = requestDatum.getCity().getName();
        viewHolder.patientName.setText(patientName);
        viewHolder.bloodTypeTv.setText(bloodType);
        viewHolder.hospitalName.setText(hospitalName);
        viewHolder.city.setText(name);

        // btn call and description >>
        viewHolder.DescriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = requestDatum.getId();
                Intent intent = new Intent(context,DonationFromActivity.class);
                 intent.putExtra("id_desc", id);
                activity.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return donationRequestDatumArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.blood_type_Tv)
        TextView bloodTypeTv;
        @BindView(R.id.patient_name)
        TextView patientName;
        @BindView(R.id.hospital_name)
        TextView hospitalName;
        @BindView(R.id.city)
        TextView city;
        @BindView(R.id.Description_btn)
        Button DescriptionBtn;
        @BindView(R.id.Call_btn)
        Button CallBtn;
        View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this, view);

        }
    }


}
