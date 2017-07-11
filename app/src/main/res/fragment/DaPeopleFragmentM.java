package com.ovationtourism.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.ovationtourism.R;
import com.ovationtourism.adapter.VPAdapterDaPeopleM;
import com.ovationtourism.base.BaseFragmentM;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.DaPeopleBeanM;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shkstart on 2017/5/21.
 */

public class DaPeopleFragmentM extends BaseFragmentM {


    @BindView(R.id.vp_qinzidaren)
    ViewPager vpQinzidaren;
    @BindView(R.id.ll_dot_qinzidaren)
    LinearLayout llDotQinzidaren;
    @BindView(R.id.vp_huawaidaren)
    ViewPager vpHuawaidaren;
    @BindView(R.id.ll_dot_huawaidaren)
    LinearLayout llDotHuawaidaren;
    @BindView(R.id.vp_sheyingdaren)
    ViewPager vpSheyingdaren;
    @BindView(R.id.ll_dot_sheyingdaren)
    LinearLayout llDotSheyingdaren;
    @BindView(R.id.vp_youxue)
    ViewPager vpYouXuedaren;
    @BindView(R.id.ll_dot_renwendaren)
    LinearLayout llDotRenwendaren;
    Unbinder unbinder;
    private List<Fragment> qinziList;
    private List<Fragment> huwaiList;
    private List<Fragment> sheyingList;
    private List<Fragment> youxueList;


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.dapeoplefragmentm, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void initData() {
        initQinZiData("1");
        initQinZiData("2");
        initQinZiData("3");
        initQinZiData("4");
    }

    private void initQinZiData(final String s) {
        HashMap map = new HashMap();
        map.put("themeId", s);
        map.put("pageNo","1");
        map.put("pageSize","99999");
        LoadNet.getDataPost(ConstantNetUtil.HOME_QUERYDARENLIST, context, map, new LoadNetHttp() {
            @Override
            public void success(String json) {
                DaPeopleBeanM beanM = JSON.parseObject(json, DaPeopleBeanM.class);
                List<DaPeopleBeanM.QueryDaRenListBean> daRenList = beanM.getQueryDaRenList();
                if (s.equals("1")) {
                    initQINZI(daRenList); // 亲子
                } else if (s.equals("2")) {
                    initHuWai(daRenList);  // 户外
                } else if (s.equals("3")) {
                    initSheYing(daRenList); // s摄影
                } else if (s.equals("4")) {
                    initYouXue(daRenList); // 游学
                }
            }

            @Override
            public void failure(String error) {
            }
        });
    }

    private void initYouXue(List<DaPeopleBeanM.QueryDaRenListBean> daRenList) {
        youxueList = new ArrayList<>();
        int size = daRenList.size();
        if (size == 0) {
            return;
        }
        if (size < 6){
            llDotRenwendaren.setVisibility(View.GONE);
        }
        int b = 0;
        if (size % 6 == 0) {
            b = size / 6;
        } else {
            b = size / 6 + 1;
        }
        for (int i = 0; i < b; i++) {
            ContentFragment fragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", "游学达人");
            bundle.putSerializable("key0", (Serializable) daRenList);
            bundle.putInt("key3", i);
            fragment.setArguments(bundle);
            youxueList.add(fragment);
        }
        initDotYouXue();
        VPAdapterDaPeopleM adapter = new VPAdapterDaPeopleM(getFragmentManager(), youxueList);
        vpYouXuedaren.setAdapter(adapter);
        vpYouXuedaren.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateDescAndYouXue();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updateDescAndYouXue() {
        int currentPage = vpYouXuedaren.getCurrentItem() % youxueList.size();
        //更新点
        //遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i = 0; i < llDotRenwendaren.getChildCount(); i++) {
            llDotRenwendaren.getChildAt(i).setEnabled(i == currentPage);
        }

    }

    private void initDotYouXue() {
        for (int i = 0; i < youxueList.size(); i++) {
            View view = null;
            if (getActivity() != null) {
                view = new View(getActivity());
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
            params.leftMargin = (i == 0 ? 0 : 17);//给除了第一个点之外的点都加上marginLeft
            if (view != null) {
                view.setLayoutParams(params);//设置宽高
                view.setBackground(getResources().getDrawable(R.drawable.selector_dot));//设置背景图片
                if (i == 0) {
                    view.setBackgroundResource(R.drawable.selector_dot);//设置背景图片
                } else {
                    view.setEnabled(false);
                }
                llDotRenwendaren.addView(view);
            }
        }
    }

    private void initSheYing(List<DaPeopleBeanM.QueryDaRenListBean> daRenList) {
        sheyingList = new ArrayList<>();
        int size = daRenList.size();
        if (size == 0) {
            return;
        }
        if (size < 6){
            llDotSheyingdaren.setVisibility(View.GONE);
        }
        int b = 0;
        if (size % 6 == 0) {
            b = size / 6;
        } else {
            b = size / 6 + 1;
        }
        for (int i = 0; i < b; i++) {
            ContentFragment fragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", "摄影达人");
            bundle.putSerializable("key0", (Serializable) daRenList);
            bundle.putInt("key3", i);
            fragment.setArguments(bundle);
            sheyingList.add(fragment);
        }
        initDotSheYing();
        VPAdapterDaPeopleM adapter = new VPAdapterDaPeopleM(getFragmentManager(), sheyingList);
        vpSheyingdaren.setAdapter(adapter);
        vpSheyingdaren.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateDescAndSheYing();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDotSheYing() {
        for (int i = 0; i < sheyingList.size(); i++) {
            View view = null;
            if (getActivity() != null) {
                view = new View(getActivity());
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
            params.leftMargin = (i == 0 ? 0 : 17);//给除了第一个点之外的点都加上marginLeft
            if (view != null) {
                view.setLayoutParams(params);//设置宽高
                view.setBackground(getResources().getDrawable(R.drawable.selector_dot));//设置背景图片
                if (i == 0) {
                    view.setBackgroundResource(R.drawable.selector_dot);//设置背景图片
                } else {
                    view.setEnabled(false);
                }
                llDotSheyingdaren.addView(view);
            }
        }

    }

    private void updateDescAndSheYing() {
        int currentPage = vpSheyingdaren.getCurrentItem() % sheyingList.size();
        //更新点
        //遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i = 0; i < llDotSheyingdaren.getChildCount(); i++) {
            llDotSheyingdaren.getChildAt(i).setEnabled(i == currentPage);
        }
    }

    private void initHuWai(List<DaPeopleBeanM.QueryDaRenListBean> daRenList) {
        huwaiList = new ArrayList<>();
        int size = daRenList.size();
        if (size == 0) {
            return;
        }
        if (size < 5){
            llDotHuawaidaren.setVisibility(View.GONE);
        }
        int b = 0;
        if (size % 6 == 0) {
            b = size / 6;
        } else {
            b = size / 6 + 1;
        }
        for (int i = 0; i < b; i++) {
            ContentFragment fragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", "户外达人");
            bundle.putSerializable("key0", (Serializable) daRenList);
            bundle.putInt("key3", i);
            fragment.setArguments(bundle);
            huwaiList.add(fragment);
        }
        initDotHuWai();
        VPAdapterDaPeopleM adapter = new VPAdapterDaPeopleM(getFragmentManager(), huwaiList);
        vpHuawaidaren.setAdapter(adapter);
        vpHuawaidaren.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateDescAndDotHuWai();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void updateDescAndDotHuWai() {
        int currentPage = vpHuawaidaren.getCurrentItem() % huwaiList.size();
        //更新点
        //遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i = 0; i < llDotQinzidaren.getChildCount(); i++) {
            llDotHuawaidaren.getChildAt(i).setEnabled(i == currentPage);
        }

    }

    private void initDotHuWai() {
        for (int i = 0; i < huwaiList.size(); i++) {
            View view = null;
            if (getActivity() != null) {
                view = new View(getActivity());
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
            params.leftMargin = (i == 0 ? 0 : 17);//给除了第一个点之外的点都加上marginLeft
            if (view != null) {
                view.setLayoutParams(params);//设置宽高
                view.setBackground(getResources().getDrawable(R.drawable.selector_dot));//设置背景图片
                if (i == 0) {
                    view.setBackgroundResource(R.drawable.selector_dot);//设置背景图片
                } else {
                    view.setEnabled(false);
                }
                llDotHuawaidaren.addView(view);
            }
        }

    }


    private void initQINZI(List<DaPeopleBeanM.QueryDaRenListBean> daRenList) {
        qinziList = new ArrayList<>();
        int size = daRenList.size();
        if (size == 0) {
            return;
        }
        int b = 0;
        if (size < 6){
            llDotQinzidaren.setVisibility(View.GONE);
        }
        if (size % 6 == 0) {
            b = size / 6;
        } else {
            b = size / 6 + 1;
        }
        for (int i = 0; i < b; i++) {
            ContentFragment fragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", "亲子达人");
            bundle.putSerializable("key0", (Serializable) daRenList);
            bundle.putInt("key3", i);
            fragment.setArguments(bundle);
            qinziList.add(fragment);
        }
        initDot();
        VPAdapterDaPeopleM adapter = new VPAdapterDaPeopleM(getFragmentManager(), qinziList);
        vpQinzidaren.setAdapter(adapter);
        vpQinzidaren.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateDescAndDot();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    /**
     * 动态添加点
     */
    private void initDot() {
        for (int i = 0; i < qinziList.size(); i++) {
            View view = null;
            if (getActivity() != null) {
                view = new View(getActivity());
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
            params.leftMargin = (i == 0 ? 0 : 17);//给除了第一个点之外的点都加上marginLeft
            if (view != null) {
                view.setLayoutParams(params);//设置宽高
                view.setBackground(getResources().getDrawable(R.drawable.selector_dot));//设置背景图片
                if (i == 0) {
                    view.setBackgroundResource(R.drawable.selector_dot);//设置背景图片
                } else {
                    view.setEnabled(false);
                }
                llDotQinzidaren.addView(view);
            }
        }
    }


    private void updateDescAndDot() {
        int currentPage = vpQinzidaren.getCurrentItem() % qinziList.size();
        //更新点
        //遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i = 0; i < llDotQinzidaren.getChildCount(); i++) {
            llDotQinzidaren.getChildAt(i).setEnabled(i == currentPage);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}




