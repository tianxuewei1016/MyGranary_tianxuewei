package com.mygranary_tianxuewei.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.ShopViewpagerAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/5 19:19
 * QQ：93226539
 * 作用：商城
 */

public class ShopFragment extends BaseFragment {
    @Bind(R.id.iv_seek)
    ImageView ivSeek;
    @Bind(R.id.iv_shop_cart)
    ImageView ivShopCart;
    @Bind(R.id.tv_shop_writ)
    TextView tvShopWrit;
    @Bind(R.id.main_title_tablelable)
    TabLayout mainTitleTablelable;
    @Bind(R.id.main_tab_viewpager)
    ViewPager mainTabViewpager;

    private ArrayList<BaseFragment> fragments;
    private String[] titles = {"分类", "品牌", "首页", "专题", "礼物","直播"};
    private ShopViewpagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView() {
        tvShopWrit.setText("商店");
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new TypeFragment());   //分类
        fragments.add(new BrandFragment());//品牌
        fragments.add(new HomepageFragment());//首页
        fragments.add(new SpecialFragment());//专题
        fragments.add(new GiftFragment());//礼物
        fragments.add(new SinatvFragment());//礼物
    }

    @Override
    protected void initData() {
        ivSeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("搜索被点击了...");
            }
        });
        ivShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("购物车被点击了...");
            }
        });
        adapter = new ShopViewpagerAdapter(getChildFragmentManager(), fragments, titles);
        mainTabViewpager.setAdapter(adapter);
        //关联ViewPager
        mainTitleTablelable.setupWithViewPager(mainTabViewpager);
        mainTitleTablelable.setTabMode(TabLayout.MODE_SCROLLABLE);
        mainTabViewpager.setCurrentItem(1);
    }
}
