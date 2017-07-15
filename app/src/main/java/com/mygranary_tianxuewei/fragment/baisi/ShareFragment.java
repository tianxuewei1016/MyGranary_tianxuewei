package com.mygranary_tianxuewei.fragment.baisi;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.MgzDetailsAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/5 19:21
 * QQ：93226539
 * 作用：分享
 */

public class ShareFragment extends BaseFragment {
    @Bind(R.id.tvMainCup)
    TextView tvMainCup;
    @Bind(R.id.tvMainTitle)
    TextView tvMainTitle;
    @Bind(R.id.tvMainRotate)
    TextView tvMainRotate;
    @Bind(R.id.tvMainRandom)
    TextView tvMainRandom;
    @Bind(R.id.tlMainActivity)
    TabLayout tlMainActivity;
    @Bind(R.id.vpMainActivity)
    ViewPager vpMainActivity;

    private List<BaseFragment> fragments;
    private List<String> titles;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    protected void initView() {
        tlMainActivity.setTabMode(TabLayout.MODE_FIXED);
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new TuiJianFragment());
        fragments.add(new SessionFragment());
        titles.add("推荐");
        titles.add("段子");

        tlMainActivity.setTabMode(TabLayout.GRAVITY_CENTER);//设置模式
        //设置指示器的 长短 大小
        tlMainActivity.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tlMainActivity, 90, 90);
            }
        });

        MgzDetailsAdapter adapter = new MgzDetailsAdapter(getChildFragmentManager(), fragments, titles);
        vpMainActivity.setAdapter(adapter);
        //TabLayout加载viewpager
        tlMainActivity.setupWithViewPager(vpMainActivity);
    }

    //设置tablayout指示器的长短
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    protected void initData() {

    }
}
