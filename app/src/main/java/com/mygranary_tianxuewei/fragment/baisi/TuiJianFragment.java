package com.mygranary_tianxuewei.fragment.baisi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.ShareRecommendAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.TuiJianBean;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.HttpOKUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/15 18:13
 * QQ：93226539
 * 作用：推荐的fragment
 */

public class TuiJianFragment extends BaseFragment {

    @Bind(R.id.rl_share_recommend)
    RecyclerView rlShareRecommend;
    @Bind(R.id.refresh)
    PullToRefreshLayout refresh;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    private List<TuiJianBean.ListBean> list;
    private ShareRecommendAdapter adapter;

    private String url;
    private int page = 20;
    private boolean isLoadMore = false;
    private TuiJianBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tuijian;
    }

    @Override
    protected void initView() {
        url = ApiConstants.SHARE_BASE_RECOMMEND + page + ".json";
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(url)) {
            getDataFromNet(ApiConstants.SHARE_RECOMMEND);
        }

        initListener();
    }

    private void initListener() {
        refresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                isLoadMore = false;
                page = 20;
                getDataFromNet(ApiConstants.SHARE_RECOMMEND);
            }

            @Override
            public void loadMore() {
                page += 20;
                isLoadMore = true;
                if (bean != null && page < bean.getInfo().getCount()) {
                    getDataFromNet(ApiConstants.SHARE_BASE_RECOMMEND + page + ".json");
                }else{
                    showToast("没有更多数据了...");
                    refresh.finishLoadMore();
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

            }
        });
    }

    /**
     * 解析数据
     *
     * @param json
     */
    private void processData(String json) {
        bean = new Gson().fromJson(json, TuiJianBean.class);
        list = bean.getList();
        if (list != null && list.size() > 0) {
            adapter = new ShareRecommendAdapter(context, list);
            rlShareRecommend.setAdapter(adapter);
            //设置RecyclerView的布局模式
            LinearLayoutManager manager = new LinearLayoutManager(context);
            rlShareRecommend.setLayoutManager(manager);
            if (isLoadMore) {//判断是上拉还是下拉刷新,isLoadMore为false是为下拉,默认为false
                //为上拉加载更多
                rlShareRecommend.scrollToPosition(page - 20);
            }
        }
        progressbar.setVisibility(View.GONE);
        refresh.finishRefresh();//刷新结束
        refresh.finishLoadMore();
    }
}
