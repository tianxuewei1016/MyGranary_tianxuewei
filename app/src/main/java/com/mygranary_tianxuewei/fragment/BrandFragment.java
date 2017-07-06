package com.mygranary_tianxuewei.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.BrandFragmentAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/5 23:14
 * QQ：93226539
 * 作用：品牌
 */

public class BrandFragment extends BaseFragment {
    @Bind(R.id.lv_brand)
    ListView lvBrand;
    @Bind(R.id.brand_refresh_layout)
    SwipeRefreshLayout brandRefreshLayout;

    private BrandFragmentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
