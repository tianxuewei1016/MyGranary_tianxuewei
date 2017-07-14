package com.mygranary_tianxuewei.ui;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.GiftAdapter;
import com.mygranary_tianxuewei.adapter.TypeFragmentAdapter;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.bean.GiftActivityBean;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;
import com.mygranary_tianxuewei.widget.ComprehensiveItemDecoration;

import java.util.List;

import butterknife.Bind;

/**
 * 礼物的点击事件
 */
public class GiftActivity extends BaseActivity implements JsonCallBack {

    @Bind(R.id.tv_gift_writ)
    TextView tvGiftWrit;
    @Bind(R.id.iv_ac_gift)
    ImageView ivAcGift;
    @Bind(R.id.iv_type_gift)
    ImageView ivTypeGift;
    @Bind(R.id.tv_shop_gift)
    TextView tvShopGift;
    @Bind(R.id.rv_gift)
    RecyclerView rvGift;

    private GiftAdapter adapter;
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    private String url6;
    private String url7;
    private GiftActivityBean.DataBean data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gift;
    }

    @Override
    protected void initTitle() {
        url1 = getIntent().getStringExtra("url1");
        url2 = getIntent().getStringExtra("url2");
        url3 = getIntent().getStringExtra("url3");
        url4 = getIntent().getStringExtra("url4");
        url5 = getIntent().getStringExtra("url5");
        url6 = getIntent().getStringExtra("url6");
        url7 = getIntent().getStringExtra("url7");
    }

    @Override
    protected void initData() {
        HttpUtils.load(url1).callBack(this, 1);
        HttpUtils.load(url2).callBack(this, 2);
        HttpUtils.load(url3).callBack(this, 3);
        HttpUtils.load(url4).callBack(this, 4);
        HttpUtils.load(url5).callBack(this, 5);
        HttpUtils.load(url6).callBack(this, 6);
        HttpUtils.load(url7).callBack(this, 7);
    }

    @Override
    public void successJson(String result, int requestCode) {
        if (requestCode == 1) {
            setUpAdapter(result);
        } else if (requestCode == 2) {
            setUpAdapter(result);
        } else if (requestCode == 3) {
            setUpAdapter(result);
        } else if (requestCode == 4) {
            setUpAdapter(result);
        } else if (requestCode == 5) {
            setUpAdapter(result);
        } else if (requestCode == 6) {
            setUpAdapter(result);
        } else if (requestCode == 7) {
            setUpAdapter(result);
        }
    }

    private void setUpAdapter(String result) {
        GiftActivityBean bean = new Gson().fromJson(result, GiftActivityBean.class);
        data = bean.getData();
        adapter = new GiftAdapter(this, data);
        //设置适配器
        rvGift.setAdapter(adapter);
        //设置布局管理器
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvGift.setLayoutManager(manager);
        //设置间距
        rvGift.addItemDecoration(new ComprehensiveItemDecoration(18));

        adapter.setOnItemClickListener(new TypeFragmentAdapter.OnItemClickListener() {
            List<GiftActivityBean.DataBean.ItemsBean> ItemsBean = data.getItems();

            @Override
            public void OnItemClick(int position) {
                GiftActivityBean.DataBean.ItemsBean itemsBean = this.ItemsBean.get(position);
                Intent intent = new Intent(GiftActivity.this, UseInfoActivity.class);
                intent.putExtra("gift", itemsBean);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }

    @Override
    protected void initListener() {
        ivAcGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
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


