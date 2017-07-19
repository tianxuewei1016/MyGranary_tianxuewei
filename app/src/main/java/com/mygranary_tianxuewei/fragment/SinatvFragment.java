package com.mygranary_tianxuewei.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.SinatvAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.SinatvBean;
import com.mygranary_tianxuewei.utils.RetrofitUtils;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：田学伟 on 2017/7/5 23:36
 * QQ：93226539
 * 作用：直播
 */

public class SinatvFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;

    private SinatvAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sinatv;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getDataFromNet();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                swiperefreshlayout.setRefreshing(false);
            }
        });
    }

    /**
     * 网络请求
     */
    private void getDataFromNet() {
        RetrofitUtils.getSinatvFragmentAPI()
                .getSinaTVFragmentInfo()
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnNext(new Action1<SinatvBean>() {
                    @Override
                    public void call(SinatvBean sinatvBean) {
//                        saveUserInfo(userInfo);//保存用户信息到本地
                    }
                }).observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<SinatvBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(SinatvBean sinatvBean) {
                        setUpAdapter(sinatvBean);
                    }
                });
    }

    /**
     * 设置适配器
     *
     * @param sinatvBean
     */
    private void setUpAdapter(SinatvBean sinatvBean) {
        SinatvBean.DataBean data = sinatvBean.getData();
        if (data != null) {
            adapter = new SinatvAdapter(context, data);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
