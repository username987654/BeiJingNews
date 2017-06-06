package com.atguigu.myapplication.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.myapplication.MainActivity;
import com.atguigu.myapplication.R;
import com.atguigu.myapplication.base.BaseFragment;
import com.atguigu.myapplication.domain.NewsContentBean;
import com.atguigu.myapplication.pager.NewsPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaoMeng on 2017/6/4.
 */

public class LeftFragment extends BaseFragment {
    private ListView listView;
    private List<NewsContentBean.DataBean> datas;
    private MainActivity mainActivity;

    private MyAdapter adapter;
    private int prePosition = 0;

    @Override
    protected View initView() {
        listView = new ListView(context);
        mainActivity = (MainActivity) context;
        listView.setPadding(0, 40, 0, 0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prePosition = position;

                mainActivity.slidingMenu.toggle();
                switchPager(prePosition);
            }
        });
        return listView;
    }

    @Override
    protected void initData() {
    }

    private void switchPager(int position) {
        ContentFragment content = mainActivity.getContent();
        NewsPager newsPager = content.getNewsPager();
        newsPager.switchPager(position);

    }

    public void getData(List<NewsContentBean.DataBean> datas) {
        this.datas = datas;
        adapter = new MyAdapter();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        switchPager(prePosition);

    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenu, null);
            textView.setText(datas.get(position).getTitle());
            return textView;
        }
    }
}
