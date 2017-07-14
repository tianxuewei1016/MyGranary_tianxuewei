package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.GiftActivityBean;
import com.mygranary_tianxuewei.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/13 14:55
 * QQ：93226539
 * 作用：礼物的适配器
 */

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.MyViewHolder> {

    private GiftActivityBean.DataBean datas;
    private Context context;


    public GiftAdapter(Context context, GiftActivityBean.DataBean data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public int getItemCount() {
        return datas.getItems().size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(UiUtils.inflate(R.layout.activity_items));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GiftActivityBean.DataBean.ItemsBean itemsBean = datas.getItems().get(position);
        Glide.with(context)
                .load(itemsBean.getGoods_image())
                .into(holder.ivGift);
        holder.tvGiftName.setText(itemsBean.getGoods_name());
        holder.tvGiftJex.setText(itemsBean.getBrand_info().getBrand_name());
        holder.tvGiftPrice.setText(itemsBean.getShop_price());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_gift)
        ImageView ivGift;
        @Bind(R.id.tv_gift_name)
        TextView tvGiftName;
        @Bind(R.id.tv_gift_jex)
        TextView tvGiftJex;
        @Bind(R.id.tv_gift_price)
        TextView tvGiftPrice;

        public MyViewHolder(View itemView) {
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
