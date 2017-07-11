package com.ovationtourism.fragment;

import android.view.View;

import com.easemob.chat.EMMessage;
import com.easemob.easeui.ui.EaseChatFragment;
import com.easemob.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.ovationtourism.constant.AppConstants;
import com.ovationtourism.utils.SPCacheUtils;

/**
 * Created by shkstart on 2017/6/16.
 */

public class MyChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentListener {




    private boolean isRobot;
    @Override
    public void onSetMessageAttributes(EMMessage message) {
        if(isRobot){
            //set message extension
            message.setAttribute("em_robot_message", isRobot);
        }
        //设置要发送扩展消息用户昵称
        message.setAttribute(AppConstants.USERNAME, SPCacheUtils.getString(getContext() ,AppConstants.USERNAME));
        //设置要发送扩展消息用户头像
        message.setAttribute(AppConstants.USERPHOTOURL, SPCacheUtils.getString(getContext() , AppConstants.USERPHOTOURL));

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
