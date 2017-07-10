package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.LikeAndRecommendBean;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/10 16:19
 * QQ：93226539
 * 作用：达人,喜欢的适配器
 */

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.MyViewHolder> {

    private final List<LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean> datas;
    private final Context context;

    public LikeAdapter(Context context, List<LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean> goods) {
        this.context = context;
        this.datas = goods;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(UiUtils.inflate(R.layout.like_items));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean goodsBean = datas.get(position);
        Glide.with(context)
                .load(goodsBean.getGoods_image())
                .into(holder.useLikeItemImg);
        holder.useLikeItemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "被点击了...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.use_like_item_img)
        ImageView useLikeItemImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
