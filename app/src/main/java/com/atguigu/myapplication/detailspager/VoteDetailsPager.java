package com.atguigu.myapplication.detailspager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.atguigu.myapplication.base.BaseDetailsPager;

/**
 * Created by HaoMeng on 2017/6/3.
 */

public class VoteDetailsPager extends BaseDetailsPager {
    public VoteDetailsPager(Context context) {
        super(context);
    }

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(context);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("投票详情页面");
    }
}
