package com.feicui.newss;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public static final String SPLASH_CONFIN = "splash_confin";
    public static final String IS_FIRST_RUN  = "isFirstRun";
    private ArrayList<View> mList;
    private ViewPager       mViewPager;
    int[] pics = {R.drawable.welcome,
            R.drawable.wy,
            R.drawable.bd,
            R.drawable.small};
    private ImageView[] icons=new ImageView[4];
private void initleadIcon(){
    icons[0]= (ImageView) findViewById(R.id.iv_p1);
    icons[1]= (ImageView) findViewById(R.id.iv_p2);
    icons[2]= (ImageView) findViewById(R.id.iv_p3);
    icons[3]= (ImageView) findViewById(R.id.iv_p4);
    setPoint(0);
    //icons[0].setImageResource(R.drawable.a4);
}
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setPoint(int index){
        for (int i = 0; i <icons.length ; i++) {
            if (i==index){
                icons[i].setImageAlpha(255);
            }else {
                icons[i].setImageAlpha(100);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences= getSharedPreferences(SPLASH_CONFIN,MODE_PRIVATE);
        boolean isFirstRun=preferences.getBoolean(IS_FIRST_RUN,true);
        if (!isFirstRun){
            Intent intent=new Intent(this,ActivityLogo.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        initleadIcon();
        initView();
    }
    private void initView(){
        mList=new ArrayList<>();
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        for (int i = 0; i <pics.length ; i++) {
            ImageView iv=new ImageView(this);
            iv.setImageResource(pics[i]);
            mList.add(iv);
        }

        mViewPager.setAdapter(new MyPagerAdapter(mList));
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPoint(position);
        if (position >= 3) {
            Intent intent = new Intent(MainActivity.this, ActivityLogo.class);
            startActivity(intent);
            finish();

            SharedPreferences preferences =getSharedPreferences(SPLASH_CONFIN,
                    MODE_PRIVATE);
            SharedPreferences.Editor editor =preferences.edit();
            editor.putBoolean(IS_FIRST_RUN,false);
            editor.apply();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private class MyPagerAdapter extends PagerAdapter{

        private ArrayList<View> list;

        public MyPagerAdapter(ArrayList<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position), 0);
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
