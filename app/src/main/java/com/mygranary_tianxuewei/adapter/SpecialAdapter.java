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
import com.mygranary_tianxuewei.bean.SpecialBean;
import com.mygranary_tianxuewei.ui.MainActivity;
import com.mygranary_tianxuewei.ui.SpecialActivity;
import com.mygranary_tianxuewei.utils.ConstantUtil;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/7 19:14
 * QQ：93226539
 * 作用：专题的适配器
 */
public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.MyViewHolder> {

    private final Context context;
    private final SpecialBean.DataBean datas;

    public SpecialAdapter(Context context, SpecialBean.DataBean data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(UiUtils.inflate(R.layout.fragment_special_item));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context)
                .load(datas.getItems().get(position).getCover_img())
                .into(holder.ivShopSpecialItem);
        holder.tvShopSpecialItem.setText(datas.getItems().get(position).getTopic_name());
    }

    @Override
    public int getItemCount() {
        return datas.getItems().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_shop_special_item)
        ImageView ivShopSpecialItem;
        @Bind(R.id.tv_shop_special_item)
        TextView tvShopSpecialItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                public List<SpecialBean.DataBean.ItemsBean> ItemsBean = datas.getItems();

                @Override
                public void onClick(View v) {
                    SpecialBean.DataBean.ItemsBean itemsBean = this.ItemsBean.get(getLayoutPosition());
                    Intent initent = new Intent(context, SpecialActivity.class);
                    initent.putExtra(ConstantUtil.SPECIAL_BEAN, itemsBean);
                    context.startActivity(initent);
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });
        }
    }
}