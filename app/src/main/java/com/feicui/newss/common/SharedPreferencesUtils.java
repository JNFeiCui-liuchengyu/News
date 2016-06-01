package com.feicui.newss.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.feicui.newss.model.entity.BaseEntity;
import com.feicui.newss.model.entity.Register;

/**
 * Created by ｌ on 2016/6/1.
 */
public class SharedPreferencesUtils {
    //此方法用于注册或者登陆后,保存解析得到的内容
    public static void saveRegister(Context context, BaseEntity<Register>register){
        SharedPreferences sp=context.getSharedPreferences("register",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("message",register.getMessage());
        editor.putInt("status",Integer.parseInt(register.getStatus()));
        Register data=register.getData();
        editor.putString("result",data.getResult());
        editor.putString("token",data.getToken());
        editor.putString("explan",data.getExplain());
        editor.commit();
    }
}
