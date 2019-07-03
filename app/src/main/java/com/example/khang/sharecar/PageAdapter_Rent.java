package com.example.khang.sharecar;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khang.sharecar.Fragment_noti.dichungxe;
import com.example.khang.sharecar.Fragment_noti.dichungxe_contract;
import com.example.khang.sharecar.Fragment_noti.dichungxe_isposting;
import com.example.khang.sharecar.Fragment_noti.thuexe;
import com.example.khang.sharecar.Fragment_noti.thuexe_contract;
import com.example.khang.sharecar.Fragment_noti.thuexe_isPosting;

public class PageAdapter_Rent extends FragmentStatePagerAdapter {
    public PageAdapter_Rent(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){
            case  0:
                fragment=new thuexe_isPosting();
                break;
            case 1:
                fragment=new thuexe_contract();
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
                title = "Đang Đăng";
                break;
            case 1:
                title = "Đã được đặt";
                break;

        }
        return title;
    }

}
