package com.mygranary_tianxuewei.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者：田学伟 on 2017/7/6 09:42
 * QQ：93226539
 * 作用：设置RecycleView的Item的间距
 */

public class ComprehensiveItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public ComprehensiveItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;

        if (parent.getChildLayoutPosition(view) < 4) {
            //由于每行都只有2个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % 2 == 1) {

                outRect.right = space;
            }
        } else {
            if (parent.getChildLayoutPosition(view) % 2 == 0) {

                outRect.right = space;
            }
        }

        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = space;
        }
    }
}

