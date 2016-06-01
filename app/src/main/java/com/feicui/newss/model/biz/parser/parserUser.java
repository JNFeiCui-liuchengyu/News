package com.feicui.newss.model.biz.parser;

import com.feicui.newss.model.entity.BaseEntity;
import com.feicui.newss.model.entity.Register;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by ｌ on 2016/6/1.
 */
public class ParserUser {
    public static BaseEntity<Register> parserRegister(String json){
        Gson gson =new Gson();
        return gson.fromJson(json,new TypeToken<BaseEntity<Register>>(){}.getType());
    }
}
