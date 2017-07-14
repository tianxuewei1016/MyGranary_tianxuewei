package com.mygranary_tianxuewei.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mygranary_tianxuewei.base.BaseFragment;

import java.util.List;

/**
 * 作者：田学伟 on 2017/7/12 15:21
 * QQ：93226539
 * 作用：
 */

public class MgzDetailsAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private List<String> titles;

    public MgzDetailsAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position % titles.size());
    }
}
