package com.feicui.newss.ui.base;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by ｌ on 2016/5/31.
 */
public class FragmentMenuRight extends Fragment{
    private boolean           islogin;
    private SharedPreferences sharedPreferences;
    private RelativeLayout    relativelayout_unlogin;
    private RelativeLayout    relativeLayout_logined;
    //初始化用户信息
    private void initUserInfo(){
        
    }
    /**根据用户信息是否存在本地来设置当前视图**/
    public void changeView() {
        islogin = sharedPreferences.getBoolean("islogin", false);
        if (islogin) {
            relativeLayout_logined.setVisibility(View.VISIBLE);
            relativelayout_unlogin.setVisibility(View.GONE);
            initUserInfo();
        } else {
            relativelayout_unlogin.setVisibility(View.VISIBLE);
            relativeLayout_logined.setVisibility(View.GONE);
        }
    }
}
