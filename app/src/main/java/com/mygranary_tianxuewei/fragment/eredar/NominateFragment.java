package com.mygranary_tianxuewei.fragment.eredar;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.NominateAdapter;
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
 * 作者：田学伟 on 2017/7/10 14:09
 * QQ：93226539
 * 作用：达人,推荐
 */

public class NominateFragment extends BaseFragment implements JsonCallBack {
    public String id;
    @Bind(R.id.nominate_recyclerView)
    RecyclerView nominateRecyclerView;
    @Bind(R.id.nominate_refresh)
    SwipeRefreshLayout nominateRefresh;
    private List<LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean> goodsBeanList = new ArrayList<>();
    private NominateAdapter adapter;
    private int page = 1;


    public NominateFragment(String id) {
        super();
        this.id = id;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nominate;
    }

    @Override
    protected void initView() {
        getDataFromNet(id);
        nominateRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                nominateRefresh.setRefreshing(false);
            }
        });
    }

    private void getDataFromNet(String id) {
        page++;
        HttpUtils.load(ApiConstants.getUserTuijian(id, page)).callBack(new JsonCallBack() {
            @Override
            public void successJson(String result, int requestCode) {
                if (requestCode == 2) {
                    Gson gson = new Gson();
                    LikeAndRecommendBean bean = gson.fromJson(result, LikeAndRecommendBean.class);
                    List<LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean> goods = bean.getData().getItems().getGoods();
                    adapter = new NominateAdapter(context, goods);
                    nominateRecyclerView.setAdapter(adapter);
                    //设置布局管理器
                    GridLayoutManager manager = new GridLayoutManager(context, 2);
                    nominateRecyclerView.setLayoutManager(manager);
                    //设置间距
                    nominateRecyclerView.addItemDecoration(new ComprehensiveItemDecoration(18));
                }
            }
        }, 2);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void successJson(String result, int requestCode) {
        if (requestCode == 1) {
            Gson gson = new Gson();
            LikeAndRecommendBean bean = gson.fromJson(result, LikeAndRecommendBean.class);
            List<LikeAndRecommendBean.DataBean.ItemsBean.GoodsBean> goods = bean.getData().getItems().getGoods();
            goodsBeanList.addAll(goods);
            adapter.notifyDataSetChanged();
        }
    }
}

