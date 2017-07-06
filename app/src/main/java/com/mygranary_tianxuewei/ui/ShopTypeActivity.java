package com.mygranary_tianxuewei.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.utils.ApiConstants;

import butterknife.Bind;

import static com.mygranary_tianxuewei.R.id.iv_seek;

/**
 * 分类点击事件的Activity
 */
public class ShopTypeActivity extends BaseActivity {

    @Bind(iv_seek)
    ImageView ivSeek;
    @Bind(R.id.tv_shop_writ)
    TextView tvShopWrit;
    @Bind(R.id.iv_shop_cart)
    ImageView ivShopCart;

    private String[] urls = new String[]{
            ApiConstants.HOME_URL,
            ApiConstants.FURNITURE_URL,
            ApiConstants.STATIONERY_URL,
            ApiConstants.NUMERICAL_URL,
            ApiConstants.LIBERTINISM_URL,
            ApiConstants.KITCHEN_URL,
            ApiConstants.CATE_URL,
            ApiConstants.MEN_URL,
            ApiConstants.WOMEN_URL,
            ApiConstants.CHILDREN_URL,
            ApiConstants.SHOES_URL,
            ApiConstants.ACCESSORIES_URL,
            ApiConstants.BEAUTY_CARE_URL,
            ApiConstants.OUTDOORS_URL,
            ApiConstants.PLANT_URL,
            ApiConstants.BOOKS_URL,
            ApiConstants.GIFT_URL,
            ApiConstants.RECOMMEND_URL,
            ApiConstants.ART_URL,
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_type;
    }

    @Override
    protected void initTitle() {
        tvShopWrit.setText("商店");
        ivSeek.setImageResource(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        ivSeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
