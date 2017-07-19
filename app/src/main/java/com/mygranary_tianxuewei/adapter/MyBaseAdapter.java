package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：田学伟 on 2017/7/19 09:20
 * QQ：93226539
 * 作用：直播的分类的适配器
 */

public abstract class MyBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHodler> {

    private final Context context;
    private final List<T> datas;
    protected LayoutInflater inflater;

    public MyBaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        if (setItemCount() != 0) {
            return setItemCount();
        }
        return datas.size();
    }

    protected int setItemCount() {
        return 0;
    }

    @Override
    public BaseViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return setViewHolder(parent);
    }

    protected abstract BaseViewHodler setViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(BaseViewHodler holder, int position) {
        holder.setData();
    }
}
