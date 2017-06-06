package com.atguigu.myapplication.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atguigu.myapplication.MainActivity;
import com.atguigu.myapplication.R;
import com.atguigu.myapplication.base.BaseFragment;
import com.atguigu.myapplication.base.BasePager;
import com.atguigu.myapplication.pager.HomePager;
import com.atguigu.myapplication.pager.NewsPager;
import com.atguigu.myapplication.pager.SetingPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaoMeng on 2017/6/4.
 */

public class ContentFragment extends BaseFragment {
    private List<BasePager> pagers;
    private ViewPager vp_content;
    private MyPageAdapter pageAdapter;
    private MainActivity mainActivity;
    private RadioGroup rg_main;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_content, null);
        vp_content = (ViewPager) view.findViewById(R.id.vp_content);
        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);
        mainActivity = (MainActivity) context;
        return view;
    }

    @Override
    protected void initData() {
        setPager();
        pageAdapter = new MyPageAdapter();
        vp_content.setAdapter(pageAdapter);
        setPageListener();
        rg_main.check(R.id.rb_home);
        setRadioGroupListener();
    }

    public NewsPager getNewsPager() {
        return (NewsPager) pagers.get(1);
    }

    private void setRadioGroupListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vp_content.setCurrentItem(0);
                        break;
                    case R.id.rb_newscontent:
                        vp_content.setCurrentItem(1);
                        break;
                    case R.id.rb_seting:
                        vp_content.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    private void setPageListener() {
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {

                    mainActivity.slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                } else {
                    mainActivity.slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = pagers.get(position);
            basePager.initData();
            vp_content.addView(basePager.rootView);
            return basePager.rootView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void setPager() {
        pagers = new ArrayList<>();
        pagers.add(new HomePager(context));
        pagers.add(new NewsPager(context));
        pagers.add(new SetingPager(context));
    }
}
