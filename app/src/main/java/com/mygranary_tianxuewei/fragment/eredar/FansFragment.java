package com.mygranary_tianxuewei.fragment.eredar;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.FansAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.FollowAndFollowedsBean;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;
import com.mygranary_tianxuewei.widget.ComprehensiveItemDecoration;

import java.util.List;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/10 14:10
 * QQ：93226539
 * 作用：达人,粉丝
 */

public class FansFragment extends BaseFragment {
    @Bind(R.id.fans_recyclerView)
    RecyclerView fansRecyclerView;
    @Bind(R.id.fans_refresh_layout)
    SwipeRefreshLayout fansRefreshLayout;
    private String id;
    public int page = 1;
    private FansAdapter adapter;

    public FansFragment(String id) {
        this.id = id;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fans;
    }

    @Override
    protected void initView() {
        getDataFromNet(id);
        fansRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                fansRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getDataFromNet(String id) {
        page++;
        HttpUtils.load(ApiConstants.getUserFans(id, page)).callBack(new JsonCallBack() {
            @Override
            public void successJson(String result, int requestCode) {
                Gson gson = new Gson();
                FollowAndFollowedsBean bean = gson.fromJson(result, FollowAndFollowedsBean.class);
                List<FollowAndFollowedsBean.DataBean.ItemsBean.UsersBean> users = bean.getData().getItems().getUsers();
                adapter = new FansAdapter(context,users);
                fansRecyclerView.setAdapter(adapter);
                //设置布局管理器
                GridLayoutManager manager = new GridLayoutManager(context, 3);
                fansRecyclerView.setLayoutManager(manager);
                //设置间距
                fansRecyclerView.addItemDecoration(new ComprehensiveItemDecoration(18));
            }
        }, 1);
    }

    @Override
    protected void initData() {

    }
}
