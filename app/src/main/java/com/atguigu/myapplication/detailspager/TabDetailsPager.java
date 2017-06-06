package com.atguigu.myapplication.detailspager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.myapplication.R;
import com.atguigu.myapplication.base.BaseDetailsPager;
import com.atguigu.myapplication.domain.NewsContentBean;
import com.atguigu.myapplication.domain.TabDetailPagerBean;
import com.atguigu.myapplication.uitls.ChcheUtils;
import com.atguigu.myapplication.uitls.ConstantUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

/**
 * Created by HaoMeng on 2017/6/6.
 */

public class TabDetailsPager extends BaseDetailsPager {
    private final NewsContentBean.DataBean.ChildrenBean childrenBean;
    private ViewPager viewPager;
    private List<TabDetailPagerBean.DataBean.TopnewsBean> topnews;

    public TabDetailsPager(Context context, NewsContentBean.DataBean.ChildrenBean childrenBean) {
        super(context);
        this.childrenBean = childrenBean;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.tab_detail_topnews, null);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        return view;
    }

    @Override
    public void initData() {
        setDataFramNet();

    }

    private void setDataFramNet() {
        String url = ConstantUtils.BASE_URL + childrenBean.getUrl();
        Log.e("TAG", "联网失败" + url);
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("TAG", "联网失败");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("TAG", "联网成功");
                        proessData(response);

                    }
                });
    }

    private void proessData(String response) {
        TabDetailPagerBean tabDetailPagerBean = new Gson().fromJson(response, TabDetailPagerBean.class);
        topnews = tabDetailPagerBean.getData().getTopnews();
        viewPager.setAdapter(new MyPageAdapter());

    }

    class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return topnews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            String topimage = topnews.get(position).getTopimage();



            return super.instantiateItem(container, position);
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
}
