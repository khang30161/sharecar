package com.example.khang.sharecar;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khang.sharecar.FragmentPhanQuyen.thuexemay;
import com.example.khang.sharecar.FragmentPhanQuyen.thuexeoto;

public class PageAdapterPhanQuyen extends FragmentStatePagerAdapter {
    public PageAdapterPhanQuyen(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){
            case  0:
                fragment=new thuexeoto();
                break;
            case 1:
                fragment=new thuexemay();
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
                title = "Xe Ô tô";
                break;
            case 1:
                title = "Xe máy";
                break;

        }
        return title;
    }

}
