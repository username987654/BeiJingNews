package com.atguigu.myapplication.pager;

import android.content.Context;

import com.atguigu.myapplication.base.BasePager;

/**
 * Created by HaoMeng on 2017/6/4.
 */

public class SetingPager extends BasePager {
    public SetingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("设置");
    }
}
