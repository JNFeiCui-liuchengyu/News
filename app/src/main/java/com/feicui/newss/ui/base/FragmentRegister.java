package com.feicui.newss.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.feicui.newss.R;
import com.feicui.newss.common.CommonUtil;
import com.feicui.newss.common.SharedPreferencesUtils;
import com.feicui.newss.model.biz.parser.ParserUser;
import com.feicui.newss.model.entity.BaseEntity;
import com.feicui.newss.model.entity.Register;

public class FragmentRegister extends Fragment {
    private View     view;
    private EditText editTextEmail, editTextName, editTextPwd;
    private Button      but_register;
    private CheckBox    checkBox;
    private UserManager userManager;
    private String      email, name, pwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_register, container, false);
        editTextEmail = (EditText) view.findViewById(R.id.editText_emil);
        editTextName = (EditText) view.findViewById(R.id.editTExt_name);
        editTextPwd = (EditText) view.findViewById(R.id.editText_pwd);
        but_register = (Button) view.findViewById(R.id.button_rigister);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox1);
        but_register.setOnClickListener(clickListener);


        return view;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!checkBox.isChecked()) {
                Toast.makeText(getActivity(), "没有同意条款", Toast.LENGTH_SHORT).show();
                return;
            }
            email = editTextEmail.getText().toString();
            name = editTextName.getText().toString().trim();
            pwd = editTextPwd.getText().toString().trim();
            if (!CommonUtil.verifyEmail(email)) {
                Toast.makeText(getActivity(), "请求输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };
    Response.ErrorListener errorListener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        }
    };
    Response.Listener<String> listener=new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            BaseEntity<Register> register= ParserUser.parserRegister(response);
            String result=null;
            String explain=null;
            Register data=register.getData();
            int status=Integer.parseInt(register.getStatus());
            if (status==0){
                result=data.getResult().trim();
                explain=data.getExplain();
                result="注册成功";
                if (result.equals("0")){
                    //保存用户信息
                    SharedPreferencesUtils.saveRegister(getActivity(),register);
                    startActivity(new Intent(getActivity(),ActivityUser.class));
                    //增加动画
                    getActivity().overridePendingTransition(R.anim.anim_activity_right_in,R.anim.anim_activity_bottom_out);
                    //更新界面
                    ((ActivityMain) getActivity()).changeFrangmentUser();
                }
                Toast.makeText(getActivity(), explain, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
