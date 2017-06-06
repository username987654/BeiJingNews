package com.atguigu.myapplication.base;

import android.content.Context;
import android.view.View;

/**
 * Created by HaoMeng on 2017/6/4.
 */

public abstract class BaseDetailsPager {
    public Context context;
    public View roodView;

    public BaseDetailsPager(Context context) {
        this.context = context;
        this.roodView = initView();
    }

    public abstract View initView();

    public void initData() {

    }
}
