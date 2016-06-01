package com.feicui.newss.common;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ｌ on 2016/6/1.
 */
public class CommonUtil {
    public static final String APPURL="http://118.244.212.82:9092/newsClient";
    public static final int VERSION_CODE=1;
    public static String getSystime(){
        String systime;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddhhmmss");
        systime=dateFormat.format(new Date(System.currentTimeMillis()));
        return systime;
    }
    public static String getFileSize(long fileSize){
        DecimalFormat df=new DecimalFormat("#.00");
        StringBuffer sb=new StringBuffer();
        if (fileSize<1024){
            sb.append(fileSize);
            sb.append("B");
        }else if(fileSize<1048576){
            sb.append(df.format((double) fileSize/1024));
            sb.append("K");
        }else if(fileSize<1073741824){
            sb.append(df.format((double) fileSize/1048576));
            sb.append("K");
        }else {
            sb.append(df.format((double) fileSize/1073741824));
            sb.append("G");
        }
        return sb.toString();
    }
    //获取当前日期
    public static String getDate(){
        Date date=new Date(System.currentTimeMillis());
        String strs="";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            strs = sdf.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return strs;
    }
    //验证邮箱格式
    public static boolean verifyEmail(String email){
        Pattern pattern=Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)" +
                "|(([a-zA-Z0-9\\-]+\\.)+))" +
                "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    //验证密码格式
    public static boolean verifyPassword(String password){
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9]{6,16}$");
        Matcher matcher=pattern.matcher((password));
        return  matcher.matches();
    }
    //获取当前的版本号
    public static int getVersionCode(Context context){//获取版本号(内部识别号)

        try {
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            return pi.versionCode;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}
