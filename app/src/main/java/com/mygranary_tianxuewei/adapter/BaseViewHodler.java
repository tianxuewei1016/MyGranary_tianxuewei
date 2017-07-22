package com.mygranary_tianxuewei.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者：田学伟 on 2017/7/18 10:32
 * QQ：93226539
 * 作用：直播的ViewHolder
 */


public abstract class BaseViewHodler extends RecyclerView.ViewHolder {
    public BaseViewHodler(View itemView) {
        super(itemView);
    }
    public abstract void setData();
}
