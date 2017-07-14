package com.mygranary_tianxuewei.ui;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.DetailsAdapter;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.bean.TypeActivityBean;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.ConstantUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;

/**
 * 商品的详情页面
 */
public class DetailsActivity extends BaseActivity {
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @Bind(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @Bind(R.id.ll_l)
    LinearLayout llL;
    @Bind(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @Bind(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @Bind(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @Bind(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @Bind(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;

    private DetailsAdapter adapter;
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
        return R.layout.details_activity;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        int position = getIntent().getIntExtra(ConstantUtil.POSITION_TYPE, getLayoutId());
        getDataFromNet(urls[position]);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        TypeActivityBean bean = new Gson().fromJson(json, TypeActivityBean.class);
        adapter = new DetailsAdapter(this, bean.getData());
        lv.setAdapter(adapter);
    }


    @Override
    protected void initListener() {
        ibGoodInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibGoodInfoMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("更多被点击了...");
            }
        });

        tvGoodInfoCallcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("联系客服");
            }
        });

        tvGoodInfoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("跳转到购物车...");
            }
        });

        btnGoodInfoAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("加入购物车...");
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
