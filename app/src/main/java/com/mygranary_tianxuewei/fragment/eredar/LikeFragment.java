package com.mygranary_tianxuewei.fragment.eredar;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.LikeAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.LikeAndRecommendBean;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;
import com.mygranary_tianxuewei.widget.ComprehensiveItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/10 14:07
 * QQ：93226539
 * 作用：达人,喜欢的
 */

public class LikeFragment extends BaseFragment implements JsonCallBack {

    public String id;
    @Bind(R.id.like_recyclerView)
    RecyclerView likeRecyclerView;
    @Bind(R.id.like_refresh_layout)
    SwipeRefreshLayout likeRefreshLayout;
    private List<LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean> goodsBeen = new ArrayList<>();
    private LikeAdapter adapter;
    public int page = 1;

    public LikeFragment(String id) {
        super();
        this.id = id;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        getDataFromNet(id);
        likeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                likeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getDataFromNet(String id) {
        page++;
        HttpUtils.load(ApiConstants.getUserLike(id, page)).callBack(new JsonCallBack() {
            @Override
            public void successJson(String result, int requestCode) {
                if (requestCode == 2) {
                    LikeAndRecommendBean bean = new Gson().fromJson(result, LikeAndRecommendBean.class);
                    List<LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean> goods = bean.getData().getItems().getGoods();
                    adapter = new LikeAdapter(context, goods);
                    likeRecyclerView.setAdapter(adapter);
                    //设置布局管理器
                    GridLayoutManager manager = new GridLayoutManager(context, 2);
                    likeRecyclerView.setLayoutManager(manager);
                    //设置间距
                    likeRecyclerView.addItemDecoration(new ComprehensiveItemDecoration(18));
                }
            }
        }, 2);
    }

    @Override
    public void successJson(String result, int requestCode) {
        if (requestCode == 1) {
            Gson gson = new Gson();
            LikeAndRecommendBean bean = gson.fromJson(result, LikeAndRecommendBean.class);
            List<LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean> goods = bean.getData().getItems().getGoods();
            if (goods != null) {
                goodsBeen.addAll(goods);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
