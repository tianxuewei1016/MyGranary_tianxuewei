package com.ovationtourism.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.ovationtourism.R;
import com.ovationtourism.adapter.LVDyanmicAdapter;
import com.ovationtourism.base.BaseFragmentM;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.DynamicBeanM;
import com.ovationtourism.ui.DaPeopleActivity;
import com.ovationtourism.ui.ExperienceInfoActivity;
import com.ovationtourism.ui.ProductDetailActivity;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shkstart on 2017/5/21.
 */

public class DynamicFragmentM extends BaseFragmentM {
    @BindView(R.id.lv_dynmic)
    ListView lvDynmic;
    Unbinder unbinder;
    private LVDyanmicAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.dynamicfragmentm, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        // 联网请求数据 解析数据  设置适配器  并对item设置监听
        final HashMap<String, String> map = new HashMap();
        map.put("pageSize" , "99999");
        map.put("pageNo" , "1");
        LoadNet.getDataPost(ConstantNetUtil.HOME_DYNAMIC, context, new LoadNetHttp() {
            @Override
            public void success(String json) {
                final DynamicBeanM beanM = JSON.parseObject(json, DynamicBeanM.class);
                if (beanM == null) {
                    return;
                }
                adapter = new LVDyanmicAdapter(context, beanM.getQueryDongTaiList());
                lvDynmic.setAdapter(adapter);
                //设置监听事件
                initListerner();
            }

            @Override
            public void failure(String error) {
                Log.e("TAG", "错误信息: " + error);
            }
        });
    }

    private void initListerner() {
        adapter.setOnItemClickListener(new LVDyanmicAdapter.OnItemDaRenClick() {

            @Override
            public void onItemDaRenIdClick(String i) {
                Intent intent = new Intent(context, DaPeopleActivity.class);
                intent.putExtra("iddaren", i);
                startActivity(intent);
            }
        });
        adapter.setOnItemgonglueClickListener(new LVDyanmicAdapter.OnItemgonglueClickListener() {
            @Override
            public void onItemDaRengonglueClick(String i) {
                Intent intent = new Intent(context , ExperienceInfoActivity.class);
                intent.putExtra("ExperienceInfoId" , i );
                startActivity(intent);
            }
        });
        adapter.setOnItemzuopinClickListener(new LVDyanmicAdapter.OnItemzuopinClickListener() {
            @Override
            public void onItemchanpinxiangqinClick(String i) {
                Intent intent = new Intent(context , ProductDetailActivity.class);
                intent.putExtra("proId" , i );
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
