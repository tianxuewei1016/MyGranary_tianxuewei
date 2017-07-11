package com.mygranary_tianxuewei.fragment.eredar;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.AttentionAdapter;
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
 * 作用：达人,关注
 */

public class AttentionFragment extends BaseFragment {
    public String id;
    @Bind(R.id.att_recyclerView)
    RecyclerView attRecyclerView;
    @Bind(R.id.att_refresh)
    SwipeRefreshLayout attRefresh;
    private AttentionAdapter adapter;
    private int page = 1;

    public AttentionFragment(String id) {
        super();
        this.id = id;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void initView() {
        getDataFromNet(id);
        attRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                attRefresh.setRefreshing(false);
            }
        });
    }

    private void getDataFromNet(String id) {
        page++;
        HttpUtils.load(ApiConstants.getUserFollow(id, page)).callBack(new JsonCallBack() {
            @Override
            public void successJson(String result, int requestCode) {
                Gson gson = new Gson();
                FollowAndFollowedsBean bean = gson.fromJson(result, FollowAndFollowedsBean.class);
                List<FollowAndFollowedsBean.DataBean.ItemsBean.UsersBean> users = bean.getData().getItems().getUsers();
                adapter = new AttentionAdapter(context, users);
                attRecyclerView.setAdapter(adapter);
                //设置布局管理器
                GridLayoutManager manager = new GridLayoutManager(context, 3);
                attRecyclerView.setLayoutManager(manager);
                //设置间距
                attRecyclerView.addItemDecoration(new ComprehensiveItemDecoration(18));
            }
        }, 2);
    }

    @Override
    protected void initData() {

    }
}
