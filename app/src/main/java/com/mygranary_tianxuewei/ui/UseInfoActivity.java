package com.mygranary_tianxuewei.ui;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.bean.GiftActivityBean;

import butterknife.Bind;

import static com.mygranary_tianxuewei.R.id.shop_info_customer;

/**
 * 商品详情页面
 */
public class UseInfoActivity extends BaseActivity {

    @Bind(shop_info_customer)
    ImageView shopInfoCustomer;
    @Bind(R.id.shop_info_joinCar)
    Button shopInfoJoinCar;
    @Bind(R.id.shop_info_Buy)
    Button shopInfoBuy;
    @Bind(R.id.shop_info_line)
    LinearLayout shopInfoLine;
    @Bind(R.id.shop_info_viewpager)
    ImageView imageView;
    @Bind(R.id.shop_info_name)
    TextView shopInfoName;
    @Bind(R.id.shop_info_goodsname)
    TextView shopInfoGoodsname;
    @Bind(R.id.shop_info_heart)
    ImageView shopInfoHeart;
    @Bind(R.id.shop_info_likecount)
    TextView shopInfoLikecount;
    @Bind(R.id.shop_info_share)
    ImageView shopInfoShare;
    @Bind(R.id.shop_info_price)
    TextView shopInfoPrice;
    @Bind(R.id.shop_info_mian)
    TextView shopInfoMian;
    @Bind(R.id.shop1)
    ImageView shop1;
    @Bind(R.id.shop2)
    TextView shop2;
    @Bind(R.id.shop3)
    ImageView shop3;
    @Bind(R.id.shop_info_size)
    RelativeLayout shopInfoSize;
    @Bind(R.id.shop_info_logo_img)
    ImageView shopInfoLogoImg;
    @Bind(R.id.shop_info_logo_name)
    TextView shopInfoLogoName;
    @Bind(R.id.shop4)
    ImageView shop4;
    @Bind(R.id.shop_info_logo)
    RelativeLayout shopInfoLogo;
    @Bind(R.id.shop_info_good_detail)
    RadioButton shopInfoGoodDetail;
    @Bind(R.id.shop_info_good_guide)
    RadioButton shopInfoGoodGuide;
    @Bind(R.id.shop_info_radio_group)
    RadioGroup shopInfoRadioGroup;
    @Bind(R.id.shop_info_fram)
    FrameLayout shopInfoFram;
    @Bind(R.id.shop_info_re)
    RelativeLayout shopInfoRe;
    @Bind(R.id.shop_info_scroll)
    ScrollView shopInfoScroll;
    @Bind(R.id.shop_info_back)
    ImageView shopInfoBack;
    @Bind(R.id.shop_info_shopcar)
    Button shopInfoShopcar;
    private GiftActivityBean.DataBean.ItemsBean gift;

    @Override
    public int getLayoutId() {
        return R.layout.activity_use_info;

    }

    @Override
    protected void initTitle() {
        //接收数据
        gift = (GiftActivityBean.DataBean.ItemsBean) getIntent().getSerializableExtra("gift");
    }

    @Override
    protected void initData() {
        //设置数据
        shopInfoName.setText(gift.getBrand_info().getBrand_name());
        shopInfoGoodsname.setText(gift.getGoods_name());
        shopInfoLikecount.setText(gift.getLike_count());
        shopInfoPrice.setText(gift.getPrice());
        shopInfoLogoName.setText(gift.getBrand_info().getBrand_name());
        //设置图片
        Glide.with(UseInfoActivity.this)
                .load(gift.getGoods_image())
                .into(imageView);
        Glide.with(UseInfoActivity.this)
                .load(gift.getBrand_info().getBrand_logo())
                .into(shopInfoLogoImg);

    }

    @Override
    protected void initListener() {
        shopInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
            }
        });
        //客服
        shopInfoCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //加入购物车
        shopInfoJoinCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //直接购买
        shopInfoBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //分享
        shopInfoShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
        }
        return super.onKeyUp(keyCode, event);
    }
}
