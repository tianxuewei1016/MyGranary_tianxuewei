package com.mygranary_tianxuewei.ui;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;

/**
 * 点击达人页面跳转过来的Activity
 */
public class UserInfoActivity extends BaseActivity {
    private String id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initTitle() {
        //接收id
        id = getIntent().getExtras().getString("id");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
