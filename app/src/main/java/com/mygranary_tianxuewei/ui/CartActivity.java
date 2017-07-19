package com.mygranary_tianxuewei.ui;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.bean.GiftActivityBean;
import com.mygranary_tianxuewei.cart.db.CartStorage;

import java.util.HashMap;

import butterknife.Bind;

/**
 * 购物车界面
 */
public class CartActivity extends BaseActivity {

    @Bind(R.id.pay_back)
    ImageView payBack;
    @Bind(R.id.pay_brand_name)
    TextView payBrandName;
    @Bind(R.id.pay_good_name)
    TextView payGoodName;
    @Bind(R.id.pay_price)
    TextView payPrice;
    @Bind(R.id.pay_line)
    TextView payLine;
    @Bind(R.id.pay_type)
    TextView payType;
    @Bind(R.id.pay_rb1)
    RadioButton payRb1;
    @Bind(R.id.pay_rb2)
    RadioButton payRb2;
    @Bind(R.id.pay_radio_group)
    RadioGroup payRadioGroup;
    @Bind(R.id.pay_text)
    TextView payText;
    @Bind(R.id.pay_reduce)
    Button payReduce;
    @Bind(R.id.pay_count)
    Button payCount;
    @Bind(R.id.pay_add)
    Button payAdd;
    @Bind(R.id.pay_count_line)
    LinearLayout payCountLine;
    @Bind(R.id.pay_sure)
    Button paySure;
    @Bind(R.id.pay_re2)
    RelativeLayout payRe2;
    @Bind(R.id.pay_img)
    ImageView payImg;

    private GiftActivityBean.DataBean.ItemsBean gift;
    /**
     * 数据库的对象
     */
    private CartStorage cartStorage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void initTitle() {
        gift = (GiftActivityBean.DataBean.ItemsBean) getIntent().getSerializableExtra("goodsbean");
    }

    @Override
    protected void initData() {
        if (gift != null) {
            Glide.with(CartActivity.this)
                    .load(gift.getGoods_image())
                    .into(payImg);
            if (gift.getBrand_info() != null) {
                payBrandName.setText(gift.getBrand_info().getBrand_name());
            }
            payGoodName.setText(gift.getGoods_name());
            payPrice.setText("￥" + gift.getPrice());
            //进来初始化先调一次
            ensureToBuy();
        }
    }
    /**
     * //点击确定加入购物车或者直接购买
     * //然后页面返回后跳转...
     * //测试数据保存到数据库
     */
    private String price = "";
    private String priceid = "";
    private String attr_idcart = "";//记得初始值为""，不然下边会与null叠加
    private String typename = "";

    private HashMap<Integer, Integer> hashMap = new HashMap<>();
    private void ensureToBuy() {
        attr_idcart = "";


    }

    @Override
    protected void initListener() {
        payBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        }
        return super.onKeyUp(keyCode, event);
    }
}
