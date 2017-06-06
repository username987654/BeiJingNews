package com.atguigu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.atguigu.myapplication.uitls.ChcheUtils;

public class WelcomeActivity extends AppCompatActivity {
    private RelativeLayout rl_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        rl_bg = (RelativeLayout) findViewById(R.id.activity_welcome);
        setStartAnimation();


    }

    private void setStartAnimation() {
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        AnimationSet as = new AnimationSet(false);
        as.addAnimation(alpha);
        as.addAnimation(rotate);
        as.addAnimation(scale);
        as.setDuration(2000);
        as.setFillAfter(true);
        rl_bg.startAnimation(as);
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent;
                boolean isEnter = ChcheUtils.getChche(WelcomeActivity.this, "isEnter");
                if (isEnter) {
                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                }
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
