package com.mygranary_tianxuewei.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.HomepagerAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.HomepagerBean;
import com.mygranary_tianxuewei.utils.RetrofitUtils;

import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：田学伟 on 2017/7/5 23:14
 * QQ：93226539
 * 作用：首页
 */

public class HomepageFragment extends BaseFragment {

    @Bind(R.id.recyclerview_home)
    RecyclerView recyclerviewHome;
    @Bind(R.id.type_home_layout)
    SwipeRefreshLayout typeHomeLayout;
    private HomepagerAdapter adapter;
    private List<HomepagerBean.DataBean.ItemsBean.ListBean> lists;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getDataFromNet();
        typeHomeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                typeHomeLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 网络请求
     */
    private void getDataFromNet() {
        RetrofitUtils.getHomepagerFragmentAPI()
                .getHomepagerFragmentInfo()
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnNext(new Action1<HomepagerBean>() {
                    @Override
                    public void call(HomepagerBean homepagerBean) {
//                        saveUserInfo(userInfo);//保存用户信息到本地
                    }
                }).observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<HomepagerBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomepagerBean homepagerBean) {
                        setUpAdapter(homepagerBean);
                    }
                });
    }

    private void setUpAdapter(final HomepagerBean homepagerBean) {
        lists = homepagerBean.getData().getItems().getList();
        adapter = new HomepagerAdapter(context, lists);
        recyclerviewHome.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        recyclerviewHome.setLayoutManager(manager);
    }
}
