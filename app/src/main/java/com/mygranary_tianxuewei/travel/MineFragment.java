package com.mygranary_tianxuewei.travel;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.travel.adapter.MineAdapter;
import com.mygranary_tianxuewei.travel.fragment.ExpertFragment;
import com.mygranary_tianxuewei.travel.fragment.HomeFragment;
import com.mygranary_tianxuewei.travel.fragment.MyFragment;
import com.mygranary_tianxuewei.travel.fragment.ServiceFragment;
import com.mygranary_tianxuewei.travel.fragment.TourFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/5 19:21
 * QQ：93226539
 * 作用：个人中心
 */

public class MineFragment extends BaseFragment {
    @Bind(R.id.mine_title_tablelable)
    TabLayout mineTitleTablelable;
    @Bind(R.id.mine_tab_viewpager)
    ViewPager mineTabViewpager;
    private ArrayList<BaseFragment> fragments;
    private String[] titles = {"首页", "旅游", "达人", "客服", "我的"};

    private MineAdapter mineAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());   //首页
        fragments.add(new TourFragment());//旅游
        fragments.add(new ExpertFragment());//达人
        fragments.add(new ServiceFragment());//客服
        fragments.add(new MyFragment());//我的
    }

    @Override
    protected void initData() {
        mineAdapter = new MineAdapter(getChildFragmentManager(), fragments, titles);
        mineTabViewpager.setAdapter(mineAdapter);
        //关联ViewPager
        mineTitleTablelable.setupWithViewPager(mineTabViewpager);
        mineTitleTablelable.setTabMode(TabLayout.MODE_SCROLLABLE);
        mineTabViewpager.setCurrentItem(1);
    }
}
