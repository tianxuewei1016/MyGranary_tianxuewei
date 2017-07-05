package com.mygranary_tianxuewei.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;

import butterknife.Bind;

import static com.mygranary_tianxuewei.R.id.tv_shop_writ;

/**
 * 作者：田学伟 on 2017/7/5 19:19
 * QQ：93226539
 * 作用：商城
 */

public class ShopFragment extends BaseFragment {
    @Bind(R.id.iv_seek)
    ImageView ivSeek;
    @Bind(tv_shop_writ)
    TextView tvShopWrit;
    @Bind(R.id.iv_shop_cart)
    ImageView ivShopCart;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView() {
        tvShopWrit.setText("商店");
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
    }
}
