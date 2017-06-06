package com.atguigu.myapplication;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.atguigu.myapplication.uitls.ChcheUtils;
import com.atguigu.myapplication.uitls.DensityUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private int[] ids = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ViewPager guide_vp;
    private MyViewPageAdapter vpAdapter;
    private List<ImageView> images;
    private Button btn_guide;
    private LinearLayout ll_indicate;
    private ImageView red_point;
    private int spaceBetween;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        btn_guide = (Button) findViewById(R.id.btn_guide);
        guide_vp = (ViewPager) findViewById(R.id.guide_page_bg);
        ll_indicate = (LinearLayout) findViewById(R.id.ll_indicate);
        red_point = (ImageView) findViewById(R.id.red_point);
        setImageView();
        vpAdapter = new MyViewPageAdapter();
        guide_vp.setAdapter(vpAdapter);
        guide_vp.addOnPageChangeListener(new MyOnpageChange());
        setIndicatePoint();
        setMovePoint();
    }

    public void ClickEnterMainPageButton(View view) {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        ChcheUtils.setChche(GuideActivity.this, "isEnter", true);
        finish();
    }

    private void setMovePoint() {
        red_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                spaceBetween = ll_indicate.getChildAt(0).getLeft() - ll_indicate.getChildAt(1).getLeft();
            }
        });
    }

    private void setIndicatePoint() {
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setImageResource(R.drawable.guide_indicate_point_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dip2px(this, 10), DensityUtils.dip2px(this, 10));
            if (i != 0) {
                params.leftMargin = DensityUtils.dip2px(this, 10);
            }
            imageView.setLayoutParams(params);
            ll_indicate.addView(imageView);
        }
    }

    class MyOnpageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int distance = (int) (spaceBetween * (positionOffset + position));
            RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(DensityUtils.dip2px(GuideActivity.this, 10), DensityUtils.dip2px(GuideActivity.this, 10));
            rlParams.leftMargin = -distance;
            red_point.setLayoutParams(rlParams);
        }

        @Override
        public void onPageSelected(int position) {
            if (position == images.size() - 1) {
                btn_guide.setVisibility(View.VISIBLE);
            } else {
                btn_guide.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void setImageView() {
        images = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(ids[i]);
            images.add(imageView);
        }
    }

    class MyViewPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = images.get(position);
            guide_vp.addView(imageView);
            return imageView;
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
