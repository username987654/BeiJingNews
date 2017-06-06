package com.atguigu.myapplication.detailspager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.myapplication.R;
import com.atguigu.myapplication.base.BaseDetailsPager;
import com.atguigu.myapplication.domain.NewsContentBean;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaoMeng on 2017/6/3.
 */

public class NewsDetailsPager extends BaseDetailsPager {
    private final List<NewsContentBean.DataBean.ChildrenBean> chilDren;
    private List<TabDetailsPager> tabPagers;
    private ViewPager viewPager;
    private TabPageIndicator indicator;

    public NewsDetailsPager(Context context, NewsContentBean.DataBean dataBean) {
        super(context);
        this.chilDren = dataBean.getChildren();
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_news_menu_detail, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        return view;
    }

    @Override
    public void initData() {
        setTabPage();
        viewPager.setAdapter(new MyPagerAdapter());
        indicator.setViewPager(viewPager);
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return tabPagers.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return chilDren.get(position).getTitle();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailsPager tabDetailsPager = tabPagers.get(position);
            View roodView = tabDetailsPager.roodView;
            tabDetailsPager.initData();
            container.addView(roodView);
            return roodView;
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

    private void setTabPage() {
        tabPagers = new ArrayList<>();
        for (int i = 0; i < chilDren.size(); i++) {
            tabPagers.add(new TabDetailsPager(context, chilDren.get(i)));
        }

    }
}
