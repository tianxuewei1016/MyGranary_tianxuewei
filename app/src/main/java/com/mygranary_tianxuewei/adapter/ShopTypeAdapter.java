package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.TypeActivityBean;
import com.mygranary_tianxuewei.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/6 17:20
 * QQ：93226539
 * 作用：分类点击事件的适配器
 */

public class ShopTypeAdapter extends RecyclerView.Adapter<ShopTypeAdapter.MyViewHolder> {

    private final Context context;
    private final TypeActivityBean.DataBean datas;


    public ShopTypeAdapter(Context context, TypeActivityBean.DataBean data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(UiUtils.inflate(R.layout.shop_type_item));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TypeActivityBean.DataBean.ItemsBean itemsBean = datas.getItems().get(position);
        Glide.with(context)
                .load(itemsBean.getGoods_image())
                .into(holder.ivHot);
        holder.tvShopName.setText(itemsBean.getGoods_name());
        holder.tvBrandJex.setText(itemsBean.getBrand_info().getBrand_name());
        holder.tvPrice.setText("￥" + itemsBean.getPrice());
    }

    @Override
    public int getItemCount() {
        return datas.getItems().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_hot)
        ImageView ivHot;
        @Bind(R.id.tv_shop_name)
        TextView tvShopName;
        @Bind(R.id.tv_brand_jex)
        TextView tvBrandJex;
        @Bind(R.id.tv_price)
        TextView tvPrice;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    private TypeFragmentAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(TypeFragmentAdapter.OnItemClickListener l) {
        this.listener = l;
    }
}
