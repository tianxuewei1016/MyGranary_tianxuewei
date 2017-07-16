package com.mygranary_tianxuewei.fragment.baisi;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.TuiJianAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.TuiJianBean;

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
    private TuiJianAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tuijian;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
