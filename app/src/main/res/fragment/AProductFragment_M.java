package com.ovationtourism.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.ovationtourism.R;
import com.ovationtourism.adapter.LVProductSonAdapterM;
import com.ovationtourism.base.BaseFragmentM;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.AProductBeanM;
import com.ovationtourism.ui.ProductDetailActivity;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shkstart on 2017/5/22.
 * <p>
 * <p>
 * 点击亲子跳转过来的  产品  页面
 * QinSonActivityM 此类里
 */

public class AProductFragment_M extends BaseFragmentM {


    @BindView(R.id.lv_product_son)
    ListView lvProductSon;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.a_product_fragment_m, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {


    }

    public void initDataFramNet(String s) {
        // 联网请求数据 解析数据  设置适配器  并对item设置监听
        final HashMap<String, String> map = new HashMap();
        map.put("themeId", s);
        map.put("pageSize", "99999");
        map.put("pageNo", "1");
        LoadNet.getDataPost(ConstantNetUtil.HOME_APRODUCT, context, map, new LoadNetHttp() {
            @Override
            public void success(String json) {
                if (json == null) {
                    return;
                }
                final AProductBeanM beanM = JSON.parseObject(json, AProductBeanM.class);
                List<AProductBeanM.QueryThemeProductListBean> listBeen = beanM.getQueryThemeProductList();
                LVProductSonAdapterM adapter = new LVProductSonAdapterM(context, listBeen);

                lvProductSon.setAdapter(adapter);
                lvProductSon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        intent.putExtra("proId", beanM.getQueryThemeProductList().get(position).getProductId());
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void failure(String error) {
                Log.e("TAG", "failur     e: " + error);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
