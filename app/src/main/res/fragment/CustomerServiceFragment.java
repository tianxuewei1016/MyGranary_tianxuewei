package com.ovationtourism.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.ovationtourism.R;
import com.ovationtourism.base.BaseFragment;
import com.ovationtourism.constant.AppConstants;
import com.ovationtourism.utils.MD5Util;
import com.ovationtourism.utils.SPCacheUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @name OvationTourism
 * @class name：${CLASS_NAME}
 * @anthor hx
 * @time 2017-05-10 9:36
 * @change
 * @description ：
 */
public class CustomerServiceFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.frament_chat)
    FrameLayout framentChat;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_customer_service;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initInterface();
    }


    private void initInterface() {
        upDateService();
    }


    private void upDateService() {
        if (SPCacheUtils.getBoolean(context, AppConstants.IS_LOGIN)) {
            huanXinLogin();
            //new出EaseChatFragment或其子类的实例
            MyChatFragment chatFragment = new MyChatFragment();
            Bundle args = new Bundle();//传入参数
            args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
            args.putString(EaseConstant.EXTRA_USER_ID, "ENNCustomerService");
            chatFragment.setArguments(args);
            getFragmentManager().beginTransaction().add(R.id.frament_chat, chatFragment).commit();
        }


    }


    @Override
    public void onStart() {
        super.onStart();
        if (SPCacheUtils.getBoolean(context, AppConstants.IS_LOGIN)) {
            huanXinLogin();
            //new出EaseChatFragment或其子类的实例
            EaseChatFragment chatFragment = new EaseChatFragment();
            Bundle args = new Bundle();//传入参数
            args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
            args.putString(EaseConstant.EXTRA_USER_ID, "ENNCustomerService");
            chatFragment.setArguments(args);
            getFragmentManager().beginTransaction().add(R.id.frament_chat, chatFragment).commit();
        }
    }

    private void huanXinLogin() {
        EMChatManager.getInstance().login(SPCacheUtils.getString(context, AppConstants.USENUMBER), MD5Util.encrypt("111111"), new EMCallBack() {//回调

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
