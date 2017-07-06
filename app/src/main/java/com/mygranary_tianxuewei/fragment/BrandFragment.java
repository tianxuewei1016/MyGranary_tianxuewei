package com.mygranary_tianxuewei.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.BrandFragmentAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.BrandFragmentBean;
import com.mygranary_tianxuewei.utils.RetrofitUtils;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：田学伟 on 2017/7/5 23:14
 * QQ：93226539
 * 作用：品牌
 */

public class BrandFragment extends BaseFragment {
    @Bind(R.id.lv_brand)
    ListView lvBrand;
    @Bind(R.id.brand_refresh_layout)
    SwipeRefreshLayout brandRefreshLayout;
    private BrandFragmentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getDataFromNet();
        brandRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                brandRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 网络请求
     */
    private void getDataFromNet() {
        RetrofitUtils.getBrandFragmentAPI()
                .getBrandFragmentInfo()
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnNext(new Action1<BrandFragmentBean>() {
                    @Override
                    public void call(BrandFragmentBean brandFragmentBean) {
//                        saveUserInfo(userInfo);//保存用户信息到本地
                    }
                }).observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<BrandFragmentBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("onError");
                    }

                    @Override
                    public void onNext(BrandFragmentBean brandFragmentBean) {
                        setUpAdapter(brandFragmentBean);
                    }
                });
    }

    private void setUpAdapter(BrandFragmentBean brandFragmentBean) {
        if (brandFragmentBean.getData().getItems() != null && brandFragmentBean.getData().getItems().size() > 0) {
            BrandFragmentBean.DataBean data = brandFragmentBean.getData();
            adapter = new BrandFragmentAdapter(context, data);
            lvBrand.setAdapter(adapter);
        }
    }
}
