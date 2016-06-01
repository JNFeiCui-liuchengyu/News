package com.feicui.newss.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.newss.view.slidingmenu.SlidingMenu;
import com.feicui.newss.R;

public class ActivityMain extends AppCompatActivity {
    private Fragment fragmentMenu, fragmentMenuRight;
    private Fragment fragmentType, fragmentMain, fragmentLogin, fragmentRegister, fragmentFavorite, fragmentForgetPass;
    public static SlidingMenu slidingMenu;
    private       ImageView   iv_set, iv_user;
    private TextView textView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main);
        textView_title = (TextView) findViewById(R.id.textView1);
        iv_set = (ImageView) findViewById(R.id.imageView_set);
        iv_user = (ImageView) findViewById(R.id.imageView_user);
        iv_set.setOnClickListener(onClickListener);
        iv_user.setOnClickListener(onClickListener);
        initSlidingMenu();
        showFragmentMain();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView_set:
                    if (slidingMenu != null && slidingMenu.isMenuShowing()) {
                        slidingMenu.showContent();
                    } else if (slidingMenu != null) {
                        slidingMenu.showMenu();
                    }
                    break;
                case R.id.imageView_user:
                    if (slidingMenu != null && slidingMenu.isMenuShowing()) {
                        slidingMenu.showContent();
                    } else if (slidingMenu != null) {
                        slidingMenu.showSecondaryMenu();
                    }
                    break;
            }
        }
    };

    //初始化侧滑菜单
    public void initSlidingMenu() {
        //fragmentMenu 侧滑菜单
        fragmentMenu = new FragmentMenu();
        //fragmentMenuRight 侧滑 右侧界面
        fragmentMenuRight = new FragmentMenuRight();
        slidingMenu = new SlidingMenu(this);
        //侧滑setMode(风格), 从左边正直的出来
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //侧滑后留下的 边距
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        slidingMenu.setMenu(R.layout.layout_menu);
        slidingMenu.setSecondaryMenu(R.layout.layout_menu_right);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layou_menu, fragmentMenu)
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layou_menu_right,fragmentMenuRight)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (slidingMenu.isMenuShowing()){
            slidingMenu.showContent();
        }else{
            exitTwice();
        }
    }
    //两次退出
    private boolean isFirstExit=true;
    private void exitTwice(){
        if (isFirstExit){
            Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
            isFirstExit=false;
            new Thread(){
                public void run(){
                    try {
                        Thread.sleep(3000);
                        isFirstExit=true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        }else {
            finish();
        }
    }
    /**
     * 显示:"显示新闻跟多分类Fragment
     */
    public void showFragmentType(){
        setTitle("分类");
        slidingMenu.showContent();
        if (fragmentMain==null){
            fragmentMain=new FragmentMain();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.layout_content,fragmentMain)
                    .commit();

        }


    }
    //显示: 显示新闻列表的Fragment
    public void showFragmentMain(){
        setTitle("咨讯");
        slidingMenu.showContent();
        if (fragmentMain==null){
            fragmentMain=new FragmentMain();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.layout_content,fragmentMain)
                    .commit();
        }
    }
    //显示 登录Fragment
    public void showFragmentLogin(){
        setTitle("用户登录");
        slidingMenu.showContent();
        if (fragmentLogin==null){
            fragmentLogin=new FragmentLogin();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.layout_content,fragmentRegister)
                    .commit();
        }
    }
    //显示 忘记密码fragment
    public void showFragmentForgetPass(){
        setTitle("忘记密码");
        if (fragmentForgetPass==null){
            fragmentForgetPass=new FragmentForgetPass();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.layout_content,fragmentForgetPass)
                    .commit();
        }
    }
    //显示 显示收藏新闻列表的fragment
    public void showFragmentFavorite(){
        setTitle("收藏新闻");
        slidingMenu.showContent();
        if (fragmentFavorite==null){
            fragmentFavorite=new FragmentFavorite();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.layout_content,fragmentFavorite)
                    .commit();
        }
    }
    //右侧是否登录的切换
    public void changeFrangmentUser(){
        ((FragmentMenuRight) fragmentMenuRight).changeView();
    }
    //切换当前界面的title
    private void setTitle(String title){
        textView_title.setText(title);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("ActivityMain");
        return false;
    }
}
