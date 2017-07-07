package com.mygranary_tianxuewei.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.adapter.TypeFragmentAdapter;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.TypeFragmentBean;
import com.mygranary_tianxuewei.ui.MainActivity;
import com.mygranary_tianxuewei.ui.ShopTypeActivity;
import com.mygranary_tianxuewei.utils.ConstantUtil;
import com.mygranary_tianxuewei.utils.RetrofitUtils;
import com.mygranary_tianxuewei.widget.ComprehensiveItemDecoration;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：田学伟 on 2017/7/5 23:13
 * QQ：93226539
 * 作用：分类
 */

public class TypeFragment extends BaseFragment {
    @Bind(R.id.type_recyclerView)
    RecyclerView typeRecyclerView;
    @Bind(R.id.type_refresh_layout)
    SwipeRefreshLayout typeRefreshLayout;

    private TypeFragmentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        getDataFromNet();
//        //设置下拉的距离
//        typeRefreshLayout.setDistanceToTriggerSync(100);
//        //设置颜色
//        typeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.RED);
//        //设置背景颜色
//        typeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_orange_dark);
        typeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭刷新动画
                typeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 网络请求
     */
    private void getDataFromNet() {
        RetrofitUtils.getTypeFragmentAPI()
                .getTypeFragmentInfo()
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnNext(new Action1<TypeFragmentBean>() {
                    @Override
                    public void call(TypeFragmentBean typeFragmentBean) {
//                        saveUserInfo(userInfo);//保存用户信息到本地
                    }
                }).observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<TypeFragmentBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(TypeFragmentBean typeFragmentBean) {
                        setUpAdapter(typeFragmentBean);
                    }
                });
    }

    private void setUpAdapter(TypeFragmentBean typeFragmentBean) {
        adapter = new TypeFragmentAdapter(context, typeFragmentBean);
        //设置适配器
        typeRecyclerView.setAdapter(adapter);
        //设置布局管理器
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        typeRecyclerView.setLayoutManager(manager);
        //设置间距
        typeRecyclerView.addItemDecoration(new ComprehensiveItemDecoration(18));

        //可以设置一条Item所占的列数
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 4) {
                    //按钮隐藏
                    return 2;
                }
                return 1;
            }
        });
        adapter.setOnItemClickListener(new TypeFragmentAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(context, ShopTypeActivity.class);
                intent.putExtra(ConstantUtil.POSITION_TYPE, position);
                startActivity(intent);
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }
}
