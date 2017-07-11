package com.ovationtourism.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ovationtourism.R;
import com.ovationtourism.base.BaseFragmentM;
import com.ovationtourism.constant.AppConstants;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.LoginBean;
import com.ovationtourism.ui.PhonePasswordLoginActivity;
import com.ovationtourism.ui.RegisterActivity;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;
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

public class PhonePasswordLoginFragmentM extends BaseFragmentM {
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.getmima)
    Button getmima;
    Unbinder unbinder;
    private String str;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.phone_password_login_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "onCreate: ");
    }


    @OnClick({R.id.getmima})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getmima:   // h获取手机动态密码
                getDynamicPassword();
                break;
        }
    }

    private void getDynamicPassword() {// h获取手机动态密码

        str = etPhoneNumber.getText().toString().trim();
        if (!isMobileNO(str)) {
            Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap map = new HashMap();
        map.put("userMobile", str);
        LoadNet.getDataPost(ConstantNetUtil.GET_PHONE_PW, context, map, new LoadNetHttp() {
            @Override
            public void success(String json) {
                LoginBean loginBean = JSON.parseObject(json, LoginBean.class);
                if (loginBean.getStatus().equals("1")) {
                    //跳转到手机填写验证码页面进行验证
                    Log.e("TAG", "发送成功: " + loginBean.getStatus());
                    Intent intent = new Intent(context, PhonePasswordLoginActivity.class);
                    intent.putExtra("phonenumber", str);
                    startActivity(intent);
                    SPCacheUtils.setString(context, AppConstants.USENUMBER, str);
                    getActivity().finish();

                } else if (loginBean.getStatus().equals("2")) {
                    dialog();//  当手机号是未注册的时候 弹出窗口 询问是去注册还是后返回修改
                } else if (loginBean.getStatus().equals("0")) {
                    Toast.makeText(context, "" + loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(String error) {
                Log.e("TAG", "error: " + error);
            }
        });
    }

    // 验证手机号是否正确
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    protected void dialog() {
        Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
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
                intent.putExtra("number", etPhoneNumber.getText().toString().trim());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
