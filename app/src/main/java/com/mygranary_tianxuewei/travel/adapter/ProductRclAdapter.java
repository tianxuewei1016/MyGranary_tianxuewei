package com.mygranary_tianxuewei.travel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.travel.bean.QueryProductListBean;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/11 11:36
 * QQ：93226539
 * 作用：
 */

public class ProductRclAdapter extends RecyclerView.Adapter<ProductRclAdapter.MyViewHolder> {


    private Context context;
    private List<QueryProductListBean> mData;

    public ProductRclAdapter(Context context) {
        this.context = context;
    }

    public void setmData(List<QueryProductListBean> mData) {
        this.mData = mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(UiUtils.inflate(R.layout.fragment_homepage_hot_item));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(mData.get(position).getProductUrl())
                .placeholder(R.drawable.fault_pic)
                .into(holder.ivProHot);
        holder.tvPriceHot.setText("¥"+mData.get(position).getPrice());
        holder.tvProHotName.setText(mData.get(position).getProductName());
        holder.btnHotTag1.setText(mData.get(position).getThemeName());
        holder.btnHotTag2.setText(mData.get(position).getAreaName());

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_pro_hot)
        ImageView ivProHot;
        @Bind(R.id.btn_hot_tag1)
        Button btnHotTag1;
        @Bind(R.id.btn_hot_tag2)
        Button btnHotTag2;
        @Bind(R.id.tv_pro_hot_name)
        TextView tvProHotName;
        @Bind(R.id.tv_price_hot)
        TextView tvPriceHot;
        @Bind(R.id.ll_price)
        LinearLayout llPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private ProductRclAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(ProductRclAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
