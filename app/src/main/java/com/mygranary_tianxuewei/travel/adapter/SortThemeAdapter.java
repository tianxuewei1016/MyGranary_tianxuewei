package com.mygranary_tianxuewei.travel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.utils.UiUtils;

/**
 * 作者：田学伟 on 2017/7/11 13:38
 * QQ：93226539
 * 作用：主题的适配器
 */

public class SortThemeAdapter extends ArrayAdapter {
    private final Context context;
    private String[] strs = {"不限", "亲子", "户外", "摄影", "游学"};
    private int checkPos;

    public SortThemeAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        checkPos = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = UiUtils.inflate(R.layout.fragment_tourism_sort_item);
        }
        TextView text = (TextView) convertView.findViewById(R.id.tv_sort);
        ImageView iv= (ImageView) convertView.findViewById(R.id.iv_checkd);
        if (position==checkPos||(checkPos==-1&&position==0)){
            text.setTextColor(Color.RED);
            iv.setVisibility(View.VISIBLE);
        } else {
            text.setTextColor(Color.BLACK);
            iv.setVisibility(View.GONE);
        }
        text.setText(getItem(position));
        return convertView;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return strs[position];
    }

    @Override
    public int getCount() {
        return strs.length;
    }
}
