package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.TypeActivityBean;
import com.mygranary_tianxuewei.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/7 16:12
 * QQ：93226539
 * 作用：
 */

public class DetailsAdapter extends BaseAdapter {
    private final Context context;
    private final TypeActivityBean.DataBean datas;

    public DetailsAdapter(Context context, TypeActivityBean.DataBean data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.getItems() == null ? 0 : datas.getItems().size();
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
            convertView = UiUtils.inflate(R.layout.activity_details);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TypeActivityBean.DataBean.ItemsBean itemsBean = datas.getItems().get(position);
        viewHolder.tvGoodInfoName.setText(itemsBean.getGoods_name());
        viewHolder.tvGoodInfoDesc.setText(itemsBean.getBrand_info().getBrand_name());
        viewHolder.tvGoodInfoPrice.setText("￥" + itemsBean.getDiscount_price());
        Glide.with(context)
                .load(datas.getItems().get(position).getGoods_image())
                .into(viewHolder.ivGoodInfoImage);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_good_info_image)
        ImageView ivGoodInfoImage;
        @Bind(R.id.tv_good_info_name)
        TextView tvGoodInfoName;
        @Bind(R.id.tv_good_info_desc)
        TextView tvGoodInfoDesc;
        @Bind(R.id.tv_good_info_price)
        TextView tvGoodInfoPrice;
        @Bind(R.id.tv_good_info_store)
        TextView tvGoodInfoStore;
        @Bind(R.id.tv_good_info_style)
        TextView tvGoodInfoStyle;
        @Bind(R.id.wb_good_info_more)
        WebView wbGoodInfoMore;
        @Bind(R.id.tv_more_share)
        TextView tvMoreShare;
        @Bind(R.id.tv_more_search)
        TextView tvMoreSearch;
        @Bind(R.id.tv_more_home)
        TextView tvMoreHome;
        @Bind(R.id.btn_more)
        Button btnMore;
        @Bind(R.id.ll_root)
        LinearLayout llRoot;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
