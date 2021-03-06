package com.example.khang.sharecar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RentCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Action action;
    private Context context;
    private Resource resource;
    private List<RentManagers> rentManagers;


    public RentCarAdapter(List<RentManagers> rentManagers, Context context, Action action) {
        this.rentManagers = rentManagers;
        this.context = context;
        this.action = action;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewholder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_rent_car_adapter, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (rentManagers.get(position) == null) {
            return;
        }

        Viewholder holder = (Viewholder) viewHolder;

        holder.mLocation.setText(rentManagers.get(position).getLocation());
        holder.mStartTime.setText(rentManagers.get(position).getStartdate());
        holder.mEndTime.setText(rentManagers.get(position).getEnddate());
        holder.mPrice.setText(rentManagers.get(position).getPrice());
        holder.mStyle.setText(rentManagers.get(position).getStyle());
        Glide.with(context)
                .load(rentManagers.get(position).getUrl())

                .into(holder.mPic);
        holder.mLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null)
                    action.onClickItem(rentManagers.get(position), position);
            }
        });
        holder.mLine.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (action != null) {
                    action.onLongClickItem(rentManagers.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        {
            return (rentManagers != null ? rentManagers.size() : 0);
        }
    }

    public void setRentManagers(List<RentManagers> rentManagers) {
        this.rentManagers = rentManagers;
    }

    public interface Action {
        void onClickItem(RentManagers manager, int position);

        void onLongClickItem(RentManagers manager, int position);
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        ImageView mPic;
        LinearLayout mLine;
        private TextView mLocation, mPrice, mEndTime, mStartTime, mStyle;

        public Viewholder(View view) {
            super(view);
            mLocation = view.findViewById(R.id.location_adapter);
            mEndTime = view.findViewById(R.id.endtime_adapter);
            mPrice = view.findViewById(R.id.price_adapter);
            mStartTime = view.findViewById(R.id.starttime_adapter);
            mPic = view.findViewById(R.id.iv_adapter);
            mLine = view.findViewById(R.id.line);
            mStyle = view.findViewById(R.id.tv_info123);

        }
    }
}
