package com.example.khang.sharecar.Fragment_noti;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.khang.sharecar.PageAdapter_Rent;
import com.example.khang.sharecar.PageAdapter_share;
import com.example.khang.sharecar.R;


public class dichungxe extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_post_is_booking, container, false);
        viewPager=view.findViewById(R.id.view_pager2);
        tabLayout=view.findViewById(R.id.tablayout1);
        addControl();
        return view;
    }
    private void addControl() {


        FragmentManager manager = getChildFragmentManager();
        PageAdapter_share adapter = new PageAdapter_share(manager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);//deprecated
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.light));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

    }

}





