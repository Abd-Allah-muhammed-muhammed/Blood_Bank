package com.abdo.hp.bank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.model.notification.notification_list.NotificationListDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterNotifications extends RecyclerView.Adapter<AdapterNotifications.ViewHolder> {


    private Context context;
    private List<NotificationListDatum> listNotifications ;

    public AdapterNotifications( Context context, List<NotificationListDatum> listNotifications) {
        this.context = context;
        this.listNotifications = listNotifications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_notifications, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        NotificationListDatum data = listNotifications.get(position);
        //String content = data.getContent();
        String title = data.getTitle();
        String time = data.getCreatedAt();
        viewHolder.NotiListTitle.setText(title);
        viewHolder.NotiListData.setText(time);


    }

    @Override
    public int getItemCount() {
        return listNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Noti_List_Icon)
        ImageView NotiListIcon;
        @BindView(R.id.Noti_List_Title)
        TextView NotiListTitle;
        @BindView(R.id.Noti_List_data)
        TextView NotiListData;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
