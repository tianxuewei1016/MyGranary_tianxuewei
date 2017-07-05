package com.mygranary_tianxuewei.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/5 18:49
 * QQ：93226539
 * 作用：所有Activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //初始化标题
        initTitle();
        //初始化数据
        initData();
        //事件监听
        initListener();
    }

    public abstract int getLayoutId();

    protected abstract void initTitle();

    protected abstract void initData();

    protected abstract void initListener();

    //弹出吐司
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param activityClazz
     */
    public void startActivity(Class activityClazz) {
        startActivity(new Intent(this, activityClazz));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

