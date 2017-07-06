package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.BrandFragmentBean;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/6 11:32
 * QQ：93226539
 * 作用：品牌的适配器
 */

public class BrandFragmentAdapter extends BaseAdapter {
    private final Context context;
    private final BrandFragmentBean.DataBean datas;


//    private List<BrandFragmentBean> datas;
//    private Context context;
//
//    public BrandFragmentAdapter(Context context) {
//        this.context = context;
//        datas = new ArrayList<>();
//    }

    public BrandFragmentAdapter(Context context, BrandFragmentBean.DataBean data) {
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
            convertView = UiUtils.inflate(R.layout.fragment_brand_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        List<BrandFragmentBean.DataBean.ItemsBean> items = datas.getItems();
        viewHolder.tvBrandName.setText(items.get(position).getBrand_name());
        Glide.with(context)
                .load(items.get(position).getBrand_logo())
                .into(viewHolder.ivBrand);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_brand)
        ImageView ivBrand;
        @Bind(R.id.tv_brand_name)
        TextView tvBrandName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
