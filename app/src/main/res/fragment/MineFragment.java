package com.ovationtourism.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ovationtourism.R;
import com.ovationtourism.base.BaseFragment;
import com.ovationtourism.base.ViewHelper;
import com.ovationtourism.constant.AppConstants;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.PersonalInfoBean;
import com.ovationtourism.ui.AboutUsActivity;
import com.ovationtourism.ui.FeedBackActivity;
import com.ovationtourism.ui.LoginActivity;
import com.ovationtourism.ui.MyOrderActivity;
import com.ovationtourism.ui.PersonalCenterActivity;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;
import com.ovationtourism.utils.SPCacheUtils;
import com.ovationtourism.view.CircleImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * @name OvationTourism
 * @class name：${CLASS_NAME}
 * @anthor hx
 * @time 2017-05-10 9:36
 * @change
 * @description ：
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.rl_check_info)
    RelativeLayout rl_check_info;
    @BindView(R.id.rl_my_order)
    RelativeLayout rl_my_order;
    @BindView(R.id.rl_feedback)
    RelativeLayout rl_feedBack;
    @BindView(R.id.rl_aboutus)
    RelativeLayout rl_aboutus;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_head_image)
    CircleImageView iv_head_image;
    private String mPhoneNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }
    @Override
    public void onStart() {
        super.onStart();
        Map<String, String> map = new HashMap<>();
        LoadNet.getDataPost(ConstantNetUtil.GET_PERSONAL_INFO, getActivity(), map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                PersonalInfoBean personalInfoBean = JSON.parseObject(context, PersonalInfoBean.class);
                if (personalInfoBean.getStatus().equals("1")) {
                    if (!personalInfoBean.getIconUrl().equals("")){
                        Glide.with(getActivity()).load(personalInfoBean.getIconUrl()).into(iv_head_image);
                    }
                    if (!TextUtils.isEmpty(personalInfoBean.getUserName())) {
                        tv_name.setText(personalInfoBean.getUserName());
                    }
                    mPhoneNumber = personalInfoBean.getUserMobile();
                }else if (personalInfoBean.getStatus().equals("9")){
                    SPCacheUtils.setBoolean(getActivity(), AppConstants.IS_LOGIN, false);
                    tv_name.setText(getResources().getString(R.string.mine_name));
                    iv_head_image.setImageDrawable(getResources().getDrawable(R.drawable.headimage1));
                }
            }
            @Override
            public void failure(String error) {
                SPCacheUtils.setBoolean(getActivity(), AppConstants.IS_LOGIN, false);
            }
        });
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        ViewHelper.click(this, rl_check_info, rl_my_order, rl_feedBack, rl_aboutus);
        newData();
    }

    private void newData() {
        Map<String, String> map = new HashMap<>();
        LoadNet.getDataPost(ConstantNetUtil.GET_PERSONAL_INFO, getActivity(), map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                PersonalInfoBean personalInfoBean = JSON.parseObject(context, PersonalInfoBean.class);
                if (personalInfoBean.getStatus().equals("1")) {
                    if (!personalInfoBean.getIconUrl().equals("")){
                        Glide.with(getActivity()).load(personalInfoBean.getIconUrl()).into(iv_head_image);
                    }
                    if (!TextUtils.isEmpty(personalInfoBean.getUserName())) {
                        tv_name.setText(personalInfoBean.getUserName());
                    }
                    mPhoneNumber = personalInfoBean.getUserMobile();
                }else if (personalInfoBean.getStatus().equals("9")){
                    SPCacheUtils.setBoolean(getActivity(), AppConstants.IS_LOGIN, false);
                }

            }

            @Override
            public void failure(String error) {
                SPCacheUtils.setBoolean(getActivity(), AppConstants.IS_LOGIN, false);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_check_info://查看并编辑个人资料
                if (SPCacheUtils.getBoolean(getActivity(), AppConstants.IS_LOGIN)) {
                    startActivity(new Intent(getActivity(), PersonalCenterActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

                break;
            case R.id.rl_my_order://我的订单
                if (SPCacheUtils.getBoolean(getActivity(), AppConstants.IS_LOGIN)) {
                    startActivity(new Intent(getActivity(), MyOrderActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_feedback://向我们反馈
                if (SPCacheUtils.getBoolean(getActivity(), AppConstants.IS_LOGIN)) {
                    Intent intent = new Intent(getActivity(), FeedBackActivity.class);
                    intent.putExtra("phoneNumber", mPhoneNumber);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_aboutus://关于我们
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            default:
                break;

        }
    }
}
