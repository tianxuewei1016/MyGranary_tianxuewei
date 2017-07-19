package com.mygranary_tianxuewei.fragment.baisi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.SessionAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.SessionBean;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.HttpOKUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/15 18:14
 * QQ：93226539
 * 作用：段子的fragment
 */

public class SessionFragment extends BaseFragment {
    @Bind(R.id.rv_recyclerview_sess)
    RecyclerView rvRecyclerviewSess;
    @Bind(R.id.pt_fresh_sess)
    PullToRefreshLayout ptFreshSess;
    @Bind(R.id.pb_bar)
    ProgressBar pbBar;
    private boolean isLoadMore = false;

    private List<SessionBean.ListBean> list;
    private SessionAdapter adapter;

    private String url;
    private int page = 20;
    private SessionBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_session;
    }

    @Override
    protected void initView() {
        url = ApiConstants.SHARE_BASE_JOKE + page + ".json";
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(url)) {
            getDataFromNet(ApiConstants.SHARE_JOKE);
        }
        initListener();
    }

    private void initListener() {
        ptFreshSess.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                isLoadMore = false;
                page = 20;
                getDataFromNet(ApiConstants.SHARE_JOKE);
            }

            @Override
            public void loadMore() {
                page += 20;
                isLoadMore = true;
                if (bean != null && page < bean.getInfo().getCount()) {//小于数据的最大值

                    getDataFromNet(ApiConstants.SHARE_BASE_JOKE + page + ".json");
                } else {
                    Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
                    ptFreshSess.finishLoadMore();
                }
            }
        });
    }

    /**
     * 网络请求
     *
     * @param url
     */
    private void getDataFromNet(String url) {
        HttpOKUtils.getInstance().get(url, new HttpOKUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                if (!TextUtils.isEmpty(content)) {
                    processData(content);
                }
            }

            @Override
            public void onFailure(String content) {
                pbBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 解析数据
     *
     * @param json
     */
    private void processData(String json) {
        bean = new Gson().fromJson(json, SessionBean.class);
        list = bean.getList();
        if (list != null && list.size() > 0) {
            adapter = new SessionAdapter(context, list);
            rvRecyclerviewSess.setAdapter(adapter);
            //设置RecyclerView的布局模式
            LinearLayoutManager manager = new LinearLayoutManager(context);
            rvRecyclerviewSess.setLayoutManager(manager);
            if (isLoadMore) {//判断是上拉还是下拉刷新,isLoadMore为false是为下拉,默认为false
                //为上拉加载更多
                rvRecyclerviewSess.scrollToPosition(page - 20);
            }
        }
        pbBar.setVisibility(View.GONE);
        ptFreshSess.finishRefresh();
        ptFreshSess.finishLoadMore();
    }
}
