package com.ovationtourism.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.ovationtourism.R;
import com.ovationtourism.adapter.GVMasterAdapter;
import com.ovationtourism.base.BaseFragmentM;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.AMasterBean;
import com.ovationtourism.ui.DaPeopleActivity;
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
 * 点击亲子跳转过来的 达人 页面
 */
//                      QinSonActivityM 此类里
public class AMasterFragment_M extends BaseFragmentM {
    @BindView(R.id.gv_master_daren)
    GridView gvMasterDaren;
    Unbinder unbinder;
    Unbinder unbinder1;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.a_master_fragment_m, null);
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
        LoadNet.getDataPost(ConstantNetUtil.HOME_AMASTER, context, map, new LoadNetHttp() {
            @Override
            public void success(String json) {
                final AMasterBean bean = JSON.parseObject(json, AMasterBean.class);
                GVMasterAdapter adapter = new GVMasterAdapter(context, bean.getQueryThemeDaRenList());
                gvMasterDaren.setAdapter(adapter);
                gvMasterDaren.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String daRenId = bean.getQueryThemeDaRenList().get(position).getDaRenId();
                        Intent intent = new Intent(context, DaPeopleActivity.class);
                        intent.putExtra("iddaren", daRenId);
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void failure(String error) {
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
