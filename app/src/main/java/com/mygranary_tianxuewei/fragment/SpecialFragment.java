package com.mygranary_tianxuewei.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.SpecialAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.SpecialBean;
import com.mygranary_tianxuewei.utils.RetrofitUtils;
import com.mygranary_tianxuewei.widget.ComprehensiveItemDecoration;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：田学伟 on 2017/7/5 23:14
 * QQ：93226539
 * 作用：专题
 */

public class SpecialFragment extends BaseFragment {
    @Bind(R.id.lv_special)
    RecyclerView lvSpecial;
    @Bind(R.id.special_refresh_layout)
    SwipeRefreshLayout specialRefreshLayout;
    private SpecialAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getDataFromNet();
        specialRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                specialRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 网络请求
     */
    private void getDataFromNet() {
        RetrofitUtils.getSpecialFragmentAPI()
                .getSpecialFragmentInfo()
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnNext(new Action1<SpecialBean>() {
                    @Override
                    public void call(SpecialBean specialBean) {
//                        saveUserInfo(userInfo);//保存用户信息到本地
                    }
                }).observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<SpecialBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(SpecialBean specialBean) {
                        setUpAdapter(specialBean);
                    }
                });
    }

    private void setUpAdapter(SpecialBean specialBean) {
        if (specialBean.getData().getItems() != null && specialBean.getData().getItems().size() > 0) {
            adapter = new SpecialAdapter(context, specialBean.getData());
            lvSpecial.setAdapter(adapter);
            //设置布局管理器
            GridLayoutManager manager = new GridLayoutManager(context, 1);
            lvSpecial.setLayoutManager(manager);
            //设置间距
            lvSpecial.addItemDecoration(new ComprehensiveItemDecoration(18));
        }
    }
}
