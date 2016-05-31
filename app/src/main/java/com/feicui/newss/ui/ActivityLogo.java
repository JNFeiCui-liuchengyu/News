package com.feicui.newss.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.feicui.newss.R;

//logo界面
public class ActivityLogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_logo);
        ImageView logo = (ImageView) findViewById(R.id.iv_logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            //动画启动时调用
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画重复时调用
            @Override
            public void onAnimationEnd(Animation animation) {

            }

            //动画结束时调用
            @Override
            public void onAnimationRepeat(Animation animation) {
                Intent intent=new Intent(ActivityLogo.this,ActivityMain.class);
                startActivity(intent);
                finish();
            }
        });

        logo.setAnimation(animation);
    }
}
