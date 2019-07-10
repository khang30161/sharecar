package com.example.khang.sharecar;

import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class FirebaseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatInfo> chatInfos;
    private Context context;

    public FirebaseListAdapter(List<ChatInfo> chatInfos, Context context) {
        this.chatInfos = chatInfos;
        this.context = context;



    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RentCarAdapter.Viewholder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_chat, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (chatInfos.get(position) == null) {
            return;
        }

        FirebaseListAdapter.Viewholder holder = (FirebaseListAdapter.Viewholder) viewHolder;

        holder.mTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                chatInfos.get(position).getMessageTime()));
        holder.mText.setText(chatInfos.get(position).getMessageText());
        holder.mUser.setText(chatInfos.get(position).getMessageUser());


    }

    @Override
    public int getItemCount() {
        {
            return (chatInfos != null ? chatInfos.size() : 0);
        }

    }
    public static class Viewholder extends RecyclerView.ViewHolder {

        private TextView mUser, mTime, mText;

        public Viewholder(View view) {
            super(view);
            mUser = view.findViewById(R.id.message_user);
            mText = view.findViewById(R.id.message_text);
            mTime = view.findViewById(R.id.message_time);



        }
    }
}
