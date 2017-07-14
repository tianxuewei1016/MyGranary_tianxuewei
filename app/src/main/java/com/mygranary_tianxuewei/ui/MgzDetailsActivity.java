package com.mygranary_tianxuewei.ui;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.MgzDetailsAdapter;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.fragment.magazine.AuthorFragment;
import com.mygranary_tianxuewei.fragment.magazine.CateFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 点击杂志跳转的Activity
 */
public class MgzDetailsActivity extends BaseActivity {

    @Bind(R.id.mgz_rg_all)
    RadioGroup mgzRgAll;
    @Bind(R.id.mgz_tab_layout)
    TabLayout mgzTabLayout;
    @Bind(R.id.mgz_up_txt)
    TextView mgzUpTxt;
    @Bind(R.id.mgz_view_pager)
    ViewPager mgzViewPager;
    private List<BaseFragment> fragments;
    private List<String> titles;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mgz_details;
    }

    @Override
    protected void initTitle() {
        mgzTabLayout.setTabMode(TabLayout.MODE_FIXED);
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new CateFragment());
        fragments.add(new AuthorFragment());
        titles.add("分类");
        titles.add("作者");
        mgzTabLayout.setTabMode(TabLayout.GRAVITY_CENTER);//设置模式
        //设置指示器的 长短 大小
        mgzTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mgzTabLayout, 90, 90);
            }
        });

        MgzDetailsAdapter adapter = new MgzDetailsAdapter(getSupportFragmentManager(), fragments, titles);
        mgzViewPager.setAdapter(adapter);
        //TabLayout加载viewpager
        mgzTabLayout.setupWithViewPager(mgzViewPager);
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

    @Override
    protected void initListener() {
        mgzUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.bottom_in_ac, R.anim.top_out_ac);
            }
        });
        mgzRgAll.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mgz_rb_category:
                        mgzViewPager.setCurrentItem(0);
                        break;
                    case R.id.mgz_rb_author:
                        mgzViewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.bottom_in_ac, R.anim.top_out_ac);
        }
        return super.onKeyUp(keyCode, event);
    }
}
