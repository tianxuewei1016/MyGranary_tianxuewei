package com.mygranary_tianxuewei.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mygranary_tianxuewei.common.MyApplication;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/5 18:50
 * QQ：93226539
 * 作用：所有Fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    public Context context;

    /**
     * 当BaseFragment被系统创建的时候回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            TextView view = new TextView(getActivity());
            view.setTextSize(30);
            view.setGravity(Gravity.CENTER);
            view.setTextColor(Color.RED);
            view.setText("布局还没有创建......");
            return view;
        }
        View view = View.inflate(getActivity(), BaseFragment.this.getLayoutId(), null);
        ButterKnife.bind(BaseFragment.this, view);
        initView();
        return view;

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类
     * 1.需要绑定数据到视图上的时候重写该方法
     * 2.联网请求数据得到数据，并且要绑定数据到视图上的时候重写该方法
     */
    protected abstract void initData();

    //弹出吐司
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 检测Fragment中的内存泄漏问题
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(context);
        refWatcher.watch(this);
    }
}

