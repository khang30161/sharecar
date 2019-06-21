package com.example.khang.sharecar;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khang.sharecar.Fragment.Date_share;
import com.example.khang.sharecar.Fragment.Local_Share;
import com.example.khang.sharecar.Fragment.Lotrinh_share;
import com.example.khang.sharecar.Fragment.Seat_share;
import com.example.khang.sharecar.Fragment.StyleCar_Share;

public class PageAdapterShare extends FragmentStatePagerAdapter {
    PageAdapterShare(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){
            case  0:
                fragment=new Date_share();
                break;
            case 1:
                fragment=new Local_Share();
                break;
            case 2:
                fragment=new StyleCar_Share();
                break;
            case 3:
                fragment=new Seat_share();
                break;
            case 4:
                fragment=new Lotrinh_share();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";
        switch (position){
            case 0:
                title = "Thời gian";
                break;
            case 1:
                title = "Địa điểm";
                break;
            case 2:
                title = "Phương tiện";
                break;
            case 3:
                title="Lộ trình";
                break;
        }
        return title;
    }

}
