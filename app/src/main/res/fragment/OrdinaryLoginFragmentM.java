package com.ovationtourism.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.ovationtourism.R;
import com.ovationtourism.base.BaseFragmentM;
import com.ovationtourism.constant.AppConstants;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.IsRegistrationBean;
import com.ovationtourism.domain.LoginBean;
import com.ovationtourism.ui.RegisterActivity;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;
import com.ovationtourism.utils.MD5Util;
import com.ovationtourism.utils.SPCacheUtils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by shkstart on 2017/5/25.
 */

public class OrdinaryLoginFragmentM extends BaseFragmentM {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_posword)
    EditText etPosword;
    @BindView(R.id.seemima)
    Button seemima;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    Unbinder unbinder;
    private String strname;
    private String strpw;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.ordinary_login_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        etName.setText(SPCacheUtils.getString(context, "number"));
        etPosword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)}); //设置密码最大长度20
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        etName.setText(SPCacheUtils.getString(context, "number"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    boolean isop = true;

    @OnClick({R.id.seemima, R.id.login_bt, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seemima:
                if (isop) {
                    seemima.setBackgroundResource(R.drawable.seemimao);
                    etPosword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etext = etPosword.getText();
                    Selection.setSelection(etext, etext.length());
                    isop = false;
                } else {
                    seemima.setBackgroundResource(R.drawable.seemima);
                    etPosword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable etext = etPosword.getText();
                    Selection.setSelection(etext, etext.length());
                    isop = true;
                }
                break;
            case R.id.login_bt:
                initlogin();//  去服务器登录
                break;

            case R.id.tv_register:
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }

    // 验证密码是否是有字母数字符号中的两种组合而成 长度不小于6位 不大于20位
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{6,}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //验证是不是手机号
    public static boolean isMobilenumber(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    private void initlogin() {
        HashMap map = new HashMap();
        strname = etName.getText().toString().trim(); // 账号
        strpw = etPosword.getText().toString().trim(); // 密码

        if (strname.length() <= 0 || strpw.equals("")) {
            Toast.makeText(context, "账号密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isMobileNO(strpw)) {
            Toast.makeText(context, "密码由数字，字母和符号两种以上组合成，长度6-20", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isMobilenumber(strname)) {
                Toast.makeText(context, "请填入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
        }


        map.put("loginName", strname);
        map.put("password", MD5Util.encrypt(strpw));
        LoadNet.getDataPost(ConstantNetUtil.ORDINARY_LOGIN, context, map, new LoadNetHttp() {
            @Override
            public void success(String json) {
                LoginBean loginBean = JSON.parseObject(json, LoginBean.class);
                if (loginBean.getStatus().equals("0")) {
                    Toast.makeText(context, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                } else if (loginBean.getStatus().equals("1")) {
                    huanXinLogin();
                    SPCacheUtils.setString(context, AppConstants.USENUMBER, strname);
                    SPCacheUtils.setBoolean(context, AppConstants.IS_LOGIN, true);

                    SPCacheUtils.setString(context, AppConstants.USERNAME, loginBean.getUserName());
                    SPCacheUtils.setString(context, AppConstants.USERPHOTOURL, loginBean.getIconUrl());

                    String string = SPCacheUtils.getString(context, AppConstants.USERNAME);
                    String string1 = SPCacheUtils.getString(context, AppConstants.USERPHOTOURL);
                    Log.e("TAG", "用戶昵称： " + string);
                    Log.e("TAG", "用户头像地址：" + string1);

                    //  去环信登录
                    getActivity().finish();
                } else if (loginBean.getStatus().equals("2")) {
                    dialog();
                }

            }

            @Override
            public void failure(String error) {
                Log.e("TAG", "error: " + error);
            }
        });
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("该手机号码未注册，是否需要注册为新会员？");

        builder.setNegativeButton("返回修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("去注册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, RegisterActivity.class);
                intent.putExtra("number", etName.getText().toString().trim());
                startActivity(intent);
                dialog.dismiss();
                etPosword.setText("");
            }
        });
        builder.create().show();
    }

    private void getboolean() {
        if (!SPCacheUtils.getBoolean(context, "ISREGISTRATIONHX")) {
            huanXinLogin();
        } else {

            HashMap map = new HashMap();
            map.put("userMobile", SPCacheUtils.getString(context, AppConstants.USENUMBER));
            LoadNet.getDataPost(ConstantNetUtil.REGISTER_SUB_EASEMOB_HX, context, map, new LoadNetHttp() {
                @Override
                public void success(String json) {
                    Log.e("TAG", "success: " + json);
                    IsRegistrationBean bean = JSON.parseObject(json, IsRegistrationBean.class);
                    switch (bean.getEasemobStatus()) {

                        case "1": // 成功

                            break;
                        case "2": //失败
                            SPCacheUtils.setBoolean(context, "ISREGISTRATIONHX", false);
                            break;
                    }

                }

                @Override
                public void failure(String error) {
                }
            });
        }

    }


    private void huanXinLogin() {

        EMChatManager.getInstance().login(strname, MD5Util.encrypt("111111"), new EMCallBack() {//回调

            @Override
            public void onSuccess() {
                EMGroupManager.getInstance().loadAllGroups();
                EMChatManager.getInstance().loadAllConversations();
                Log.e("TAG", "onSuccess: 环信登录成功");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });

    }


}


