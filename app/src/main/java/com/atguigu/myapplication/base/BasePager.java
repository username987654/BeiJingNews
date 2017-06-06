package com.atguigu.myapplication.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atguigu.myapplication.MainActivity;
import com.atguigu.myapplication.R;

/**
 * Created by HaoMeng on 2017/6/4.
 */

public class BasePager {
    public Context context;
    public View rootView;
    public TextView tv_title;
    public FrameLayout sub_pager;
    public ImageButton titleber_menu;

    public BasePager(final Context context) {
        this.context = context;
        rootView = View.inflate(context, R.layout.base_pager, null);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        titleber_menu = (ImageButton) rootView.findViewById(R.id.titleber_menu);
        sub_pager = (FrameLayout) rootView.findViewById(R.id.sub_pager);
        titleber_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.slidingMenu.toggle();
            }
        });
    }

    public void initData() {

    }


}
