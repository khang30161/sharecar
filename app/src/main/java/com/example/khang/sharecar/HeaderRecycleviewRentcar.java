package com.example.khang.sharecar;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class HeaderRecycleviewRentcar extends RecyclerView.ViewHolder {
    public TextView mLocation, mStartday, mEndday;
    private Button mBtnStartday, mBtnEndday;
    private Spinner spinner;

    public HeaderRecycleviewRentcar(@NonNull View itemView) {
        super(itemView);
        mBtnEndday=itemView.findViewById(R.id.btn_refund);
        mBtnStartday=itemView.findViewById(R.id.btn_pickup);
        mEndday=itemView.findViewById(R.id.tv_refund);
        mStartday=itemView.findViewById(R.id.tv_pickup);
        mLocation=itemView.findViewById(R.id.tv_local);
        spinner=itemView.findViewById(R.id.spinner);

    }

}
