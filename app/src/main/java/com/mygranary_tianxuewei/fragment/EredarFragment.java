package com.mygranary_tianxuewei.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.EredarFragmentBean;
import com.mygranary_tianxuewei.utils.RetrofitUtils;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：田学伟 on 2017/7/5 19:20
 * QQ：93226539
 * 作用：达人
 */

public class EredarFragment extends BaseFragment {
    @Bind(R.id.eretar_search)
    ImageView eretarSearch;
    @Bind(R.id.eretar_set)
    ImageView eretarSet;
    @Bind(R.id.eretar_pullgrid)
    PullToRefreshGridView eretarPullgrid;
    //先把数据放到集合中
    private List<EredarFragmentBean.DataBean.ItemsBean> items = new ArrayList<>();
    private List<String> menuList = new ArrayList<>();
    private EredarAdapter eredarAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_eredar;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getDataFromNet();
    }

    /**
     * 网络请求
     */
    private void getDataFromNet() {
        RetrofitUtils.getEredarFragmentAPI()
                .getEredarFragmentInfo()
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnNext(new Action1<EredarFragmentBean>() {
                    @Override
                    public void call(EredarFragmentBean eredarFragmentBean) {
//                        saveUserInfo(userInfo);//保存用户信息到本地
                    }
                }).observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<EredarFragmentBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(EredarFragmentBean fragmentBean) {
                        setUpAdapter(fragmentBean);
                    }
                });
    }

    /**
     * 设置适配器
     *
     * @param fragmentBean
     */
    private void setUpAdapter(EredarFragmentBean fragmentBean) {
        eredarAdapter = new EredarAdapter();
        initMenu();

    }

    private void initMenu() {
        menuList.add("默认推荐");
        menuList.add("最多推荐");
        menuList.add("最受欢迎");
        menuList.add("最新推荐");
        menuList.add("最新加入");
    }

    class EredarAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                convertView = UiUtils.inflate(R.layout.userinfofragment_follow_item);
            }
            return convertView;
        }
    }
}
