package com.example.khang.sharecar;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DriverShareCar extends AppCompatActivity {
    private ViewPager viewPager;
    private Button mPre, mNext, finsh;
    private EditText mNote;
    private TextView mTvStartdate, mTvStarttime, mEndtime;
    private TabLayout tabLayout;
    private TextView mStylecar;
    private ImageButton mFord, mToyota;
    public static String FB_STORAGE_PATH = "image/";
    public static String FB_DATABASE_PATH = "post";
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseAnalytics mFirebaseAnalytics;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Button finish;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_share_car);
        mNext=findViewById(R.id.next);
        mPre=findViewById(R.id.pre);
        mNote=findViewById(R.id.et_note_share);
        finsh=findViewById(R.id.btn_finish_share);
        mTvStartdate=findViewById(R.id.tv_startdate);
        mTvStarttime=findViewById(R.id.tv_starttime);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Share").child(FB_DATABASE_PATH);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
      //  finish.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View v) {
            //    loadContent();
           // }
       // });

        addControl();



    }




    private void addControl() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        FragmentManager manager = getSupportFragmentManager();
        PageAdapterShare adapter = new PageAdapterShare(manager);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {



            }

            @Override
            public void onPageSelected(int i) {
                int page = i;
                switch (page) {
                    case 0:
                        mNext.setVisibility(View.VISIBLE);
                        mPre.setVisibility(View.GONE);
                        break;
                    case 1:
                        mNext.setVisibility(View.VISIBLE);
                        mPre.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mNext.setVisibility(View.VISIBLE);
                        mPre.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        mNext.setVisibility(View.VISIBLE);
                        mPre.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        mNext.setVisibility(View.VISIBLE);
                        mPre.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                    mNext.setVisibility(View.VISIBLE);
                    mPre.setVisibility(View.VISIBLE);
                    break;
                    case 6:
                    mNext.setVisibility(View.VISIBLE);
                    mPre.setVisibility(View.GONE);
                    break;

                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
    public void prev(View view){
        viewPager.setCurrentItem(viewPager.getCurrentItem() -1,true);
    }
    public void next(View view){
        viewPager.setCurrentItem(viewPager.getCurrentItem() +1, true);
    }

}
