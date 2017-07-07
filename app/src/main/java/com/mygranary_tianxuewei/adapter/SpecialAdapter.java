package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.SpecialBean;
import com.mygranary_tianxuewei.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/7 19:14
 * QQ：93226539
 * 作用：专题的适配器
 */

public class SpecialAdapter extends BaseAdapter {
    private final Context context;
    private final SpecialBean.DataBean datas;


    public SpecialAdapter(Context context, SpecialBean.DataBean data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = UiUtils.inflate(R.layout.fragment_special_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SpecialBean.DataBean.ItemsBean itemsBean = datas.getItems().get(position);
        viewHolder.tvShopSpecialItem.setText(itemsBean.getTopic_name());
        Glide.with(context)
                .load(itemsBean.getCover_img())
                .into(viewHolder.ivShopSpecialItem);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_shop_special_item)
        ImageView ivShopSpecialItem;
        @Bind(R.id.tv_shop_special_item)
        TextView tvShopSpecialItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
