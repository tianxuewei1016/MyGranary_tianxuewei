package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.TypeFragmentBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/6 09:34
 * QQ：93226539
 * 作用：分类的适配器
 */

public class TypeFragmentAdapter extends RecyclerView.Adapter<TypeFragmentAdapter.MyViewHolder> {

    private final Context context;
    private final TypeFragmentBean datas;

    public TypeFragmentAdapter(Context context, TypeFragmentBean typeFragmentBean) {
        this.context = context;
        this.datas = typeFragmentBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.fragment_type_item, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TypeFragmentBean.DataBean.ItemsBean itemsBean = datas.getData().getItems().get(position);
        Glide.with(context)
                .load(itemsBean.getNew_cover_img())
                .into(holder.ivType);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.getData().getItems().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_type)
        ImageView ivType;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
