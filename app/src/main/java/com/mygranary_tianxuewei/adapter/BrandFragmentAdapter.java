package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.BrandFragmentBean;
import com.mygranary_tianxuewei.ui.BrandActivity;
import com.mygranary_tianxuewei.ui.MainActivity;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/6 11:32
 * QQ：93226539
 * 作用：品牌的适配器
 */

public class BrandFragmentAdapter extends RecyclerView.Adapter<BrandFragmentAdapter.MyViewHolder> {
    private final Context context;
    private final BrandFragmentBean.DataBean datas;


    public BrandFragmentAdapter(Context context, BrandFragmentBean.DataBean data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public int getItemCount() {
        return datas.getItems().size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(UiUtils.inflate(R.layout.fragment_brand_item));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        List<BrandFragmentBean.DataBean.ItemsBean> items = datas.getItems();
        holder.tvBrandName.setText(items.get(position).getBrand_name());
        Glide.with(context)
                .load(items.get(position).getBrand_logo())
                .into(holder.ivBrand);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_brand)
        ImageView ivBrand;
        @Bind(R.id.tv_brand_name)
        TextView tvBrandName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                List<BrandFragmentBean.DataBean.ItemsBean> ItemsBean = datas.getItems();
                @Override
                public void onClick(View v) {
                    BrandFragmentBean.DataBean.ItemsBean itemsBean = this.ItemsBean.get(getLayoutPosition());
                    Intent intent = new Intent(context, BrandActivity.class);
                    intent.putExtra("brand", itemsBean);
                    context.startActivity(intent);
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });
        }
    }
}
