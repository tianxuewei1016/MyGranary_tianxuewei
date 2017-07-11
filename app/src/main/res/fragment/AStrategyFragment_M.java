package com.ovationtourism.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.ovationtourism.R;
import com.ovationtourism.adapter.LVStrategyAdapter;
import com.ovationtourism.base.BaseFragmentM;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.AStrategyBeanM;
import com.ovationtourism.ui.ExperienceInfoActivity;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shkstart on 2017/5/22.
 * <p>
 * <p>
 * 点击亲子跳转过来的 攻略 页面
 * QinSonActivityM 此类里
 */

public class AStrategyFragment_M extends BaseFragmentM {
    @BindView(R.id.lv_strategy)
    ListView lvStrategy;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.a_strategy_fragment_m, null);
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
        LoadNet.getDataPost(ConstantNetUtil.HOME_STRATEGY, context, map, new LoadNetHttp() {
            @Override
            public void success(String json) {
                final AStrategyBeanM beanM = JSON.parseObject(json, AStrategyBeanM.class);

                LVStrategyAdapter adapter = new LVStrategyAdapter(context, beanM.getQueryThemeExperienceList());

                lvStrategy.setAdapter(adapter);
                lvStrategy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //跳转到攻略详情页面
                        Intent intent = new Intent(context, ExperienceInfoActivity.class);
                        intent.putExtra("ExperienceInfoId", beanM.getQueryThemeExperienceList().get(position).getExperienceId()); // 攻略id
                        Log.e("TAG", "onItemClick:  0------------" + beanM.getQueryThemeExperienceList().get(position).getExperienceId());
                        startActivity(intent);


                    }
                });
            }


            @Override
            public void failure(String error) {
                Log.d("TAG", "failure() returned:----------- " + error);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
