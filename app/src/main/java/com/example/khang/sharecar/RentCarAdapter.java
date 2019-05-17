package com.example.khang.sharecar;

import android.app.Notification;
import android.content.Context;
import android.drm.DrmStore;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RentCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Action action;
    private Context context;
    private List<RentManagers> rentManagers;

    public RentCarAdapter(Context context, List rentmanager, Action action){
        this.context=context;
        this.rentManagers=rentmanager;
        this.action=action;
    }
    public interface Action {
        void onClickItem(RentManagers manager, int position);

        void onLongClickItem(RentManagers manager, int position);
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewholder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_rent_car_adapter, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (rentManagers.get(position) == null) {
            return;
        }


        Viewholder holder = (Viewholder) viewHolder;
        holder.mLocation.setText(rentManagers.get(position).getLocation());
        holder.mStartTime.setText(rentManagers.get(position).getStartdate());
        holder.mEndTime.setText(rentManagers.get(position).getEnddate());
        holder.mPrice.setText(rentManagers.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        {
            return (rentManagers != null ? rentManagers.size() : 0);
        }
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView mLocation, mPrice, mEndTime, mStartTime;

        public Viewholder(View itemview) {
            super(itemview);
            mLocation=itemview.findViewById(R.id.location_adapter);
            mEndTime=itemview.findViewById(R.id.endtime_adapter);
            mPrice=itemview.findViewById(R.id.price_adapter);
            mStartTime=itemview.findViewById(R.id.starttime_adapter);
        }
    }
}
