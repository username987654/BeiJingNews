package com.atguigu.myapplication.pager;

import android.content.Context;

import com.atguigu.myapplication.base.BasePager;

/**
 * Created by HaoMeng on 2017/6/4.
 */

public class HomePager extends BasePager {
    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("Home界面");
    }
}
