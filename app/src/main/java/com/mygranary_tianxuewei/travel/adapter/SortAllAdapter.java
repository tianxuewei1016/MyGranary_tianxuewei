package com.mygranary_tianxuewei.travel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.utils.UiUtils;

/**
 * 作者：田学伟 on 2017/7/11 13:15
 * QQ：93226539
 * 作用：点击全部的适配器
 */

public class SortAllAdapter extends ArrayAdapter {
    private String[] strs;
    private int checkPos;

    public SortAllAdapter(@NonNull Context context, int resource, String[] strs) {
        super(context, resource);
        checkPos = resource;
        this.strs = strs;
    }

    @Override
    public String getItem(int position) {
        return strs[position];
    }

    @Override
    public int getCount() {
        if (strs != null) {
            return strs.length;
        }
        return 0;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = UiUtils.inflate(R.layout.fragment_tourism_sort_item);
        }

        TextView text = (TextView) convertView.findViewById(R.id.tv_sort);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv_checkd);

        if (position == checkPos || (checkPos == -1 && position == 0)) {
            text.setTextColor(Color.RED);
            iv.setVisibility(View.VISIBLE);
        } else {
            text.setTextColor(Color.BLACK);
            iv.setVisibility(View.GONE);
        }
        text.setText(getItem(position));
        return convertView;
    }
}
