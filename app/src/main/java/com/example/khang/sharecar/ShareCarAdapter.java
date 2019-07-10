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

public class ShareCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private Action action;
private Context context;
private Resource resource;
private List<ShareManager> shareManager;





    public ShareCarAdapter(List<ShareManager> shareManager, Context context, Action action) {
        this.shareManager= shareManager;
        this.context=  context;
        this.action= action;
}

    public interface Action {
    void onClickItem(ShareManager shareManager, int position);

    void onLongClickItem(ShareManager shareManager, int position);
}



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ShareCarAdapter.Viewholder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_sharecar_cusomer_adapter, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (shareManager.get(position) == null) {
            return;
        }

        Viewholder holder = (Viewholder) viewHolder;
        holder.mStartdate.setText(shareManager.get(position).getStartday());
        holder.mLocation.setText(shareManager.get(position).getLocalcity());
        holder.mStartTimeM.setText(String.valueOf(shareManager.get(position).getStarttimem()));
        holder.mStartTimeH.setText(String.valueOf(shareManager.get(position).getStarttimeh()));
        holder.mPrice.setText(shareManager.get(position).getPrice());
        holder.mEndTimeM.setText(String.valueOf(shareManager.get(position).getEndtimem()));
        holder.mEndTimeH.setText(String.valueOf(shareManager.get(position).getEndtimeh()));
        holder.mSeatEmpty.setText(shareManager.get(position).getSovitri());

        Glide.with(context)
                .load(shareManager.get(position).getUrl())

                .into(holder.mPicture);
        holder.mLine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null)
                    action.onClickItem(shareManager.get(position), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        {
            return (shareManager != null ? shareManager.size() : 0);
        }
    }

    public void setShareManager(List<ShareManager> shareManager) {
        this.shareManager = shareManager;
    }

public class Viewholder extends RecyclerView.ViewHolder{
    private TextView mLocation, mPrice, mEndTimeH, mEndTimeM,mStartTimeH, mStartTimeM, mStartdate, mSeatEmpty;
    ImageView mPicture;
    LinearLayout mLine1;

    public Viewholder(View view) {
        super(view);
        mStartdate=view.findViewById(R.id.tv_sh_cus);
        mLocation=view.findViewById(R.id.tv_local_sh);
        mEndTimeH=view.findViewById(R.id.tv_sh_endhour);
        mPrice=view.findViewById(R.id.tv_sh_pric_cus);
        mEndTimeM=view.findViewById(R.id.tv_sh_endminute);
        mPicture=view.findViewById(R.id.iv_sh_cus);
        mStartTimeH=view.findViewById(R.id.tv_sh_starthour);
        mStartTimeM=view.findViewById(R.id.tv_sh_startminute);
        mSeatEmpty=view.findViewById(R.id.tv_seatempty_sh);
        mLine1=view.findViewById(R.id.linexx);

    }
}
}


