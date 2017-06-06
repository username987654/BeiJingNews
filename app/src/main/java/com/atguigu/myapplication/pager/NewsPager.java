package com.atguigu.myapplication.pager;

import android.content.Context;
import android.drm.ProcessedData;
import android.telecom.Call;
import android.util.Log;
import android.view.View;

import com.atguigu.myapplication.MainActivity;
import com.atguigu.myapplication.base.BaseDetailsPager;
import com.atguigu.myapplication.base.BasePager;
import com.atguigu.myapplication.detailspager.GroupDetailsPager;
import com.atguigu.myapplication.detailspager.InteractDetailsPager;
import com.atguigu.myapplication.detailspager.NewsDetailsPager;
import com.atguigu.myapplication.detailspager.SpecialDetailsPager;
import com.atguigu.myapplication.detailspager.VoteDetailsPager;
import com.atguigu.myapplication.domain.NewsContentBean;
import com.atguigu.myapplication.fragment.LeftFragment;
import com.atguigu.myapplication.uitls.ConstantUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaoMeng on 2017/6/4.
 */

public class NewsPager extends BasePager {
    private List<NewsContentBean.DataBean> datas;
    private List<BaseDetailsPager> detailsPagers;
    private MainActivity mainActivity;

    public NewsPager(Context context) {
        super(context);
    }


    @Override
    public void initData() {
        tv_title.setText("新闻");
        titleber_menu.setVisibility(View.VISIBLE);
        super.initData();
        String url = ConstantUtils.NEWSCENTER_PAGER_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        processData(response);

                    }
                });
    }


    private void processData(String json) {
        NewsContentBean newsContentBean = new Gson().fromJson(json, NewsContentBean.class);
        mainActivity = (MainActivity) context;
        LeftFragment left = mainActivity.getLeft();
        datas = newsContentBean.getData();
        detailsPagers = new ArrayList<>();
        detailsPagers.add(new NewsDetailsPager(context, datas.get(0)));
        detailsPagers.add(new SpecialDetailsPager(context));
        detailsPagers.add(new GroupDetailsPager(context));
        detailsPagers.add(new InteractDetailsPager(context));
        detailsPagers.add(new VoteDetailsPager(context));
        left.getData(datas);
    }

    public void switchPager(int position) {
        BaseDetailsPager baseDetailsPager = detailsPagers.get(position);
        View roodView = baseDetailsPager.roodView;
        sub_pager.removeAllViews();
        sub_pager.addView(roodView);
        baseDetailsPager.initData();
    }

}
