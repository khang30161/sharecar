package com.example.khang.sharecar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectItemShareCustomer extends AppCompatActivity {
    private TextView mStartday, mStarttimeH, mStarttimeP, mEndtimeH, mEndtimeP,mLocalCity, mStartpoint, mEndpoint, mSochongoi, mLoaixe, mDOngxe, mSochotrong, mNote, mPrice;
    private ImageView mPic;
    private TextView Id;
    private Button mSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item_share_customer);
        mStartday=findViewById(R.id.tv_ngay_bat_dau);
        mStarttimeH=findViewById(R.id.tv_hour_start);
        mStarttimeP=findViewById(R.id.tv_phut_start);
        mEndtimeH=findViewById(R.id.tv_hour_end);
        mEndtimeP=findViewById(R.id.tv_phut_end);
        mLocalCity=findViewById(R.id.tv_diadiem);
        mStartpoint=findViewById(R.id.tv_endpoint);
        mEndpoint=findViewById(R.id.tv_startpoint);
        mSochongoi=findViewById(R.id.tv_so_cho_ngoi);
        mLoaixe=findViewById(R.id.tv_loai_xe);
        mDOngxe=findViewById(R.id.tv_dong_xe);
        mSochotrong=findViewById(R.id.tv_so_ghe_trong);
        mNote=findViewById(R.id.tv_noi_gi_go);
        mPrice=findViewById(R.id.tv_gia_ca);
        mPic=findViewById(R.id.iv_hinh_anh);
        Id=findViewById(R.id.tv_id);
        mSelect=findViewById(R.id.btn_dat_hang);
        ShareManager shareManager= (ShareManager) getIntent().getSerializableExtra("shareManager");
       mStartday.setText(shareManager.getStartday());
        mStarttimeH.setText(String.valueOf(shareManager.getStarttimeh()));
        mStarttimeP.setText(String.valueOf(shareManager.getStarttimem()));
        mEndtimeH.setText(String.valueOf(shareManager.getEndtimeh()));
        mEndtimeP.setText(String.valueOf(shareManager.getEndtimem()));
        mLocalCity.setText(shareManager.getLocalcity());
        mStartpoint.setText(shareManager.getStartpoint());
        mEndpoint.setText(shareManager.getEndpoint());
        mSochongoi.setText(shareManager.getSeat());
        mLoaixe.setText(shareManager.getLoaixe());
        mDOngxe.setText(shareManager.getIntro());
        mSochotrong.setText(shareManager.getSovitri());
        mNote.setText(shareManager.getNote());
        mPrice.setText(shareManager.getPrice());
        Id.setText(shareManager.getUid());
        Glide.with(
                SelectItemShareCustomer.this).load(shareManager.getUrl()).into(mPic);

        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();


        //DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(uid);

        mSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareManager shareManager= (ShareManager) getIntent().getSerializableExtra("shareManager");
                if(shareManager.getIsbooking1()==null) {
                    shareManager.setIsbooking1(FirebaseAuth.getInstance().getCurrentUser().getUid());

                }else if(shareManager.getIsBooking2()==null){
                    shareManager.setIsBooking2(FirebaseAuth.getInstance().getCurrentUser().getUid());
                }else if(shareManager.getIsBooking3()==null){
                    shareManager.setIsBooking3(FirebaseAuth.getInstance().getCurrentUser().getUid());
                }
                FirebaseDatabase.getInstance().getReference().child("Share").child("post").child(shareManager.getIdShare()).setValue(shareManager);

                Toast.makeText(SelectItemShareCustomer.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }
}
