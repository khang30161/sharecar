package com.example.khang.sharecar;

import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class DriverShareCar extends AppCompatActivity {
    private ViewPager viewPager;
    private Button mPre, mNext;
    private EditText mNote;
    private TextView mStartday, mStarttime, mEndtime;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_share_car);
        mNext=findViewById(R.id.next);
        mPre=findViewById(R.id.pre);
        mNote=findViewById(R.id.et_note_share);

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
    public   void next(View view){
        viewPager.setCurrentItem(viewPager.getCurrentItem() +1, true);
    }

}
