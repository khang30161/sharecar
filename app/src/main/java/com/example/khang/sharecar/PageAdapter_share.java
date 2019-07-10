package com.example.khang.sharecar;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khang.sharecar.Fragment_noti.dichungxe_contract;
import com.example.khang.sharecar.Fragment_noti.dichungxe_isposting;
import com.example.khang.sharecar.Fragment_noti.thuexe_contract;
import com.example.khang.sharecar.Fragment_noti.thuexe_isPosting;

public class PageAdapter_share extends FragmentStatePagerAdapter {
    public PageAdapter_share(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){
            case  0:
                fragment=new dichungxe_isposting();
                break;
            case 1:
                fragment=new dichungxe_contract();
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
