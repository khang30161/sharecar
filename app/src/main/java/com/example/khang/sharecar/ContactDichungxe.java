package com.example.khang.sharecar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ContactDichungxe extends AppCompatActivity {
    private TextView mStartday, mStarttimeH, mStarttimeP, mEndtimeH, mEndtimeP, mSochongoi, mLoaixe, mDOngxe, mSochotrong, mNote, mPrice;
    private ImageView mPic;
    private TextView Id;
    private Button mSelect;
    private TextView mCityStart, mDistrictStart, mWardStart, mCityEnd, mDistrictEnd, mWardEnd, isbooking1, isbooking2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_dichungxe);
        mStartday = findViewById(R.id.tv_ngay_bat_dau);
        mStarttimeH = findViewById(R.id.tv_hour_start);
        mStarttimeP = findViewById(R.id.tv_phut_start);
        mEndtimeH = findViewById(R.id.tv_hour_end);
        mEndtimeP = findViewById(R.id.tv_phut_end);
        mCityStart = findViewById(R.id.tv_city_share_start);
        mCityEnd = findViewById(R.id.tv_city_share_end);
        mDistrictStart = findViewById(R.id.tv_quan_share_start);
        mDistrictEnd = findViewById(R.id.tv_quan_share_end);
        mWardStart = findViewById(R.id.tv_phuong_share_start);
        mWardEnd = findViewById(R.id.tv_phuong_share_end);
        mSochongoi = findViewById(R.id.tv_so_cho_ngoi);
        isbooking1=findViewById(R.id.isboking);
        isbooking2=findViewById(R.id.isboking2);
        mLoaixe = findViewById(R.id.tv_loai_xe);
        mDOngxe = findViewById(R.id.tv_dong_xe);
        mSochotrong = findViewById(R.id.tv_so_ghe_trong);
        mNote = findViewById(R.id.tv_noi_gi_go);
        mPrice = findViewById(R.id.tv_gia_ca);
        mPic = findViewById(R.id.iv_hinh_anh);
        Id = findViewById(R.id.tv_id);
        mSelect = findViewById(R.id.btn_dat_hang);
        ShareManager shareManager = (ShareManager) getIntent().getSerializableExtra("shareManager");
        mStartday.setText(shareManager.getStartday());
        mStarttimeH.setText(String.valueOf(shareManager.getStarttimeh()));
        mStarttimeP.setText(String.valueOf(shareManager.getStarttimem()));
        mEndtimeH.setText(String.valueOf(shareManager.getEndtimeh()));
        mEndtimeP.setText(String.valueOf(shareManager.getEndtimem()));
        isbooking1.setText(shareManager.getIsbooking1());
        mCityStart.setText(shareManager.getLocalcity());
        mCityEnd.setText(shareManager.getLocalcity1());
        mDistrictStart.setText(shareManager.getLocalquan());
        mDistrictEnd.setText(shareManager.getLocalquan1());
        mWardStart.setText(shareManager.getLocalphuong());
        mWardEnd.setText(shareManager.getLocalphuong1());
        mSochongoi.setText(shareManager.getSeat());
        mLoaixe.setText(shareManager.getLoaixe());
        mDOngxe.setText(shareManager.getIntro());
        mSochotrong.setText(shareManager.getSovitri());
        mNote.setText(shareManager.getNote());
        mPrice.setText(shareManager.getPrice());
        Id.setText(shareManager.getUid());
        Glide.with(
                ContactDichungxe.this).load(shareManager.getUrl()).into(mPic);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        //DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(uid);


    }
}
