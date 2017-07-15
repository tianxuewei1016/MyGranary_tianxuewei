package com.mygranary_tianxuewei.ui;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.ShopTypeAdapter;
import com.mygranary_tianxuewei.adapter.TypeFragmentAdapter;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.bean.TypeActivityBean;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.ConstantUtil;
import com.mygranary_tianxuewei.widget.ComprehensiveItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;

/**
 * 分类点击事件的Activity
 */
public class ShopTypeActivity extends BaseActivity {

    @Bind(R.id.iv_ac_type)
    ImageView ivAcType;
    @Bind(R.id.tv_type_writ)
    TextView tvTypeWrit;
    @Bind(R.id.iv_type_cart)
    ImageView ivTypeCart;
    @Bind(R.id.tv_shop_type)
    TextView tvShopType;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.activity_shop_type)
    RelativeLayout activityShopType;
    private ShopTypeAdapter adapter;

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
        tvTypeWrit.setText("商店");
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

    /**
     * 解析数据
     *
     * @param json
     */
    private void processData(String json) {
        TypeActivityBean bean = new Gson().fromJson(json, TypeActivityBean.class);
        adapter = new ShopTypeAdapter(this, bean.getData());
        rv.setAdapter(adapter);
        //设置布局管理器
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(manager);
        //设置间距
        rv.addItemDecoration(new ComprehensiveItemDecoration(15));
        adapter.setOnItemClickListener(new TypeFragmentAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(ShopTypeActivity.this, DetailsActivity.class);
                intent.putExtra(ConstantUtil.POSITION_TYPE, position);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }

    @Override
    protected void initListener() {
        ivAcType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
            }
        });
        //筛选的点击事件
        tvShopType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("价格筛选");
            }
        });
        //购物车
        ivTypeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("分类里面的购物车被点击了");
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
