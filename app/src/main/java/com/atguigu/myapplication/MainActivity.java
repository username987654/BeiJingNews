package com.atguigu.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.atguigu.myapplication.fragment.ContentFragment;
import com.atguigu.myapplication.fragment.LeftFragment;
import com.atguigu.myapplication.uitls.DensityUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
    private static final String MAIN_TAG = "main_tag";
    private static final String LEFT_TAG = "left_tag";
    public SlidingMenu slidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSildingFragment();
        setFragmentTransaction();
    }

    private void setFragmentTransaction() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, new ContentFragment(), MAIN_TAG);
        ft.replace(R.id.left_menu, new LeftFragment(), LEFT_TAG);
        ft.commit();
    }

    public LeftFragment getLeft() {
        FragmentManager fm = getSupportFragmentManager();

        return (LeftFragment) fm.findFragmentByTag(LEFT_TAG);

    }

    public ContentFragment getContent() {
        FragmentManager fm = getSupportFragmentManager();

        return (ContentFragment) fm.findFragmentByTag(MAIN_TAG);

    }

    private void setSildingFragment() {
        setBehindContentView(R.layout.left_menu);
        slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.setBehindOffset(DensityUtils.dip2px(this, 200));
    }
}
