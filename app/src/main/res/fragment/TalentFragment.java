package com.ovationtourism.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.ovationtourism.R;
import com.ovationtourism.adapter.VptabAdapterM;
import com.ovationtourism.base.BaseFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @name OvationTourism
 * @class name：${CLASS_NAME}
 * @anthor hx
 * @time 2017-05-10 9:35
 * @change
 * @description ：
 */
public class TalentFragment extends BaseFragment {

    @BindView(R.id.tab_FindFragment_title)
    TabLayout tabFindFragmentTitle;
    @BindView(R.id.vp_FindFragment_pager)
    ViewPager vpFindFragmentPager;
    Unbinder unbinder;
    private List<Fragment> list_fragment;   //定义要装fragment的列表
    private List<String> list_title;           //tab名称列表

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_talent;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);

        tabFindFragmentTitle.setTabMode(TabLayout.MODE_FIXED);
        initTabLayout();
    }

    private void initTabLayout() {
        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();
        list_fragment.add(new DynamicFragmentM());
        list_fragment.add(new DaPeopleFragmentM());
        list_title.add("动态");
        list_title.add("达人");
        tabFindFragmentTitle.setTabMode(TabLayout.GRAVITY_CENTER);//设置模式

        // 设置指示器的 长短 大小
        tabFindFragmentTitle.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabFindFragmentTitle, 60, 60);
            }
        });

        VptabAdapterM adapter = new VptabAdapterM(getChildFragmentManager(), list_fragment, list_title);
        vpFindFragmentPager.setAdapter(adapter);
        //TabLayout加载viewpager
        tabFindFragmentTitle.setupWithViewPager(vpFindFragmentPager);
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
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }


}

