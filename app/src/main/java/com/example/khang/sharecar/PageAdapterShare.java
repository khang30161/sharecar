package com.example.khang.sharecar;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khang.sharecar.Fragment_noti.dichungxe;
import com.example.khang.sharecar.Fragment_noti.thuexe;

public class PageAdapterShare extends FragmentStatePagerAdapter {
    PageAdapterShare(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){
            case  0:
                fragment=new dichungxe();
                break;
            case 1:
                fragment=new thuexe();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";
        switch (position){
            case 0:
                title = "Đi chung xe";
                break;
            case 1:
                title = "Thuê xe";
                break;

        }
        return title;
    }

}
