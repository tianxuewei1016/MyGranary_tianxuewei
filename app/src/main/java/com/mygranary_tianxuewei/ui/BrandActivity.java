package com.mygranary_tianxuewei.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.BrandFragmentBean;
import com.mygranary_tianxuewei.fragment.BrandProductFragment;
import com.mygranary_tianxuewei.fragment.BrandStoryFragment;
import com.mygranary_tianxuewei.utils.CircleImageView;

import java.util.ArrayList;

import butterknife.Bind;

import static com.mygranary_tianxuewei.R.id.fl_type;

/**
 * 点击品牌里面的Activity
 */
public class BrandActivity extends BaseActivity {

    @Bind(R.id.tv_relat_writ)
    TextView tvRelatWrit;
    @Bind(R.id.iv_relat_type)
    ImageView ivRelatType;
    @Bind(R.id.relat)
    RelativeLayout relat;
    @Bind(R.id.iv_user_icon)
    CircleImageView ivUserIcon;
    @Bind(R.id.tv_user_change)
    TextView tvUserChange;
    @Bind(R.id.tl_1)
    SegmentTabLayout tl1;
    @Bind(fl_type)
    FrameLayout flType;
    @Bind(R.id.activity_brand)
    RelativeLayout activityBrand;

    private BrandFragmentBean.DataBean.ItemsBean brand;
    private String[] titles = {"品牌故事", "品牌产品"};
    private ArrayList<BaseFragment> fragments;
    /**
     * 刚才被显示的fragment
     */
    private Fragment tempFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_brand;
    }

    @Override
    protected void initTitle() {
        brand = (BrandFragmentBean.DataBean.ItemsBean) getIntent().getSerializableExtra("brand");
        tvUserChange.setText(brand.getBrand_name());
        Glide.with(this).load(brand.getBrand_logo())
                .into(ivUserIcon);
    }

    @Override
    protected void initData() {
        //设置内容
        tl1.setTabData(titles);
        //监听Tab的状态
        tl1.setOnTabSelectListener(new MyOnTabSelectListener());
        initFragment();
        switchFragment(fragments.get(0));
    }

    private void switchFragment(Fragment currentFragment) {
        //切换的不是同一个页面
        if (tempFragment != currentFragment) {
            //得到FragmentMager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //如果没有添加就添加
            if (!currentFragment.isAdded()) {
                //缓存的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //添加
                ft.add(fl_type, currentFragment);
            } else {
                //缓存的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //显示
                ft.show(currentFragment);
            }
            //事务提交
            ft.commit();
            //把当前的赋值成缓存的
            tempFragment = currentFragment;
        }
    }

    class MyOnTabSelectListener implements OnTabSelectListener {

        @Override
        public void onTabSelect(int position) {
            switchFragment(fragments.get(position));
        }

        @Override
        public void onTabReselect(int position) {

        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new BrandStoryFragment());//品牌故事
        fragments.add(new BrandProductFragment());//品牌产品
    }


    @Override
    protected void initListener() {
        ivRelatType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
