package com.ovationtourism.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ovationtourism.R;
import com.ovationtourism.adapter.HomePageAdAdapter;
import com.ovationtourism.adapter.HotAdapter;
import com.ovationtourism.adapter.TalentSeeAdapter;
import com.ovationtourism.base.BaseFragment;
import com.ovationtourism.base.ViewHelper;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.BannerListBean;
import com.ovationtourism.domain.DaRenListBean;
import com.ovationtourism.domain.HomePage;
import com.ovationtourism.domain.HotListBean;
import com.ovationtourism.ui.OrderTourismActivity;
import com.ovationtourism.ui.ProductDetailActivity;
import com.ovationtourism.ui.QinSonActivityM;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;
import com.ovationtourism.widget.AutoPlayViewPager;
import com.ovationtourism.widget.MyRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;


/**
 * @name OvationTourism
 * @class name：com.ovationtourism.fragment
 * @anthor hx
 * @time 2017-05-09 10:53
 * @change
 * @class 首页
 */
public class HomePageFragment extends BaseFragment implements View.OnClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.viewpager)
    AutoPlayViewPager mViewPager;//viewpager
    @BindView(R.id.ll_dot_layout)
    LinearLayout dotLayout;//底部指示器
    @BindView(R.id.ll_qinzi)
    LinearLayout ll_qinzi; //亲子专题
    @BindView(R.id.ll_outdoor)
    LinearLayout ll_outdoor;//户外专题
    @BindView(R.id.ll_sheying)
    LinearLayout ll_sheying;//摄影专题
    @BindView(R.id.ll_renwen)
    LinearLayout ll_renwen;//人文专题
    @BindView(R.id.ll_all_all)
    LinearLayout ll_all_all;//
    @BindView(R.id.iv_order_tourism)
    ImageView iv_order_tourism;
    @BindView(R.id.rl_order_tourism)
    RelativeLayout rl_order_tourism;
    @BindView(R.id.ll_talent_see_world)
    LinearLayout ll_talent_see_world;
    @BindView(R.id.lv_talent_see)//达人带你看世界
            ListView lv_talent_see;
    @BindView(R.id.rcl_season_hot)
    MyRecyclerView rcl_season_hot;
    @BindView(R.id.fl_top)
    FrameLayout fl_top;
    @BindView(R.id.iv_relpace)
    ImageView iv_relpace;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;


    private HomePageAdAdapter mHomePageAdAdapter;//广告
    private TalentSeeAdapter mTalentSeeAdapter;//达人带你看世界
    private HotAdapter mHotAdapter;//当季热卖
    private LinearLayoutManager mLayoutManager;
    private List<BannerListBean> mBannerData;
    private List<DaRenListBean> mDarenData;
    private List<HotListBean> mHotData;
    private BannerListBean bannerListBean;
    private HomePage homePage;
    private boolean firstIn = true;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        ViewHelper.click(this, ll_qinzi, ll_outdoor, ll_sheying, ll_renwen, iv_order_tourism);

        newData();
        ll_all_all.setFocusable(true);
        ll_all_all.setFocusableInTouchMode(true);
        ll_all_all.requestFocus();
        //解决嵌套的卡顿
        rcl_season_hot.setHasFixedSize(true);
        rcl_season_hot.setNestedScrollingEnabled(false);
        initRefreshLayout(mRefreshLayout);
    }

    private void newData() {
        bannerListBean = new BannerListBean();
        final HashMap<String, String> map = new HashMap();
        LoadNet.getDataPost(ConstantNetUtil.HOME_PAGE, getActivity(), map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                homePage = JSON.parseObject(context, HomePage.class);
                if (homePage.getResultVo().getStatus().equals("1")) {
                    if (homePage.getBannerList().size() != 0) {
                        if (firstIn) {
                            mBannerData = homePage.getBannerList();
                            initViewPager();
                        }
                        firstIn = false;
                    } else {
                        fl_top.setVisibility(View.GONE);
                        iv_relpace.setVisibility(View.VISIBLE);
                    }
                    mDarenData = homePage.getDaRenList();
                    mHotData = homePage.getHotList();
                }


                //达人带你看世界
                if (mDarenData.size() == 0) {
                    ll_talent_see_world.setVisibility(View.GONE);
                } else {
                    mTalentSeeAdapter = new TalentSeeAdapter(getActivity());
                    mTalentSeeAdapter.setData(mDarenData);
                    lv_talent_see.setAdapter(mTalentSeeAdapter);
                    setListViewHeightBasedOnChildren2(mDarenData, lv_talent_see);
                    mTalentSeeAdapter.notifyDataSetChanged();
                }

                //当季热卖
                mHotAdapter = new HotAdapter(getActivity());
                mLayoutManager = new LinearLayoutManager(getActivity());
                rcl_season_hot.setLayoutManager(mLayoutManager);
                mHotAdapter.setData(mHotData);
                rcl_season_hot.setAdapter(mHotAdapter);
                mHotAdapter.notifyDataSetChanged();
                mHotAdapter.setOnItemClickLitener(new HotAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                        intent.putExtra("proId", mHotData.get(position).getProductId());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void failure(String error) {

            }
        });
    }

    private void updateDescAndDot() {
        int currentPage = mViewPager.getCurrentItem() % mBannerData.size();
        //更新点
        //遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i = 0; i < dotLayout.getChildCount(); i++) {
            dotLayout.getChildAt(i).setEnabled(i == currentPage);
        }
    }

    private void initViewPager() {

        initDot();
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateDescAndDot();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mHomePageAdAdapter = new HomePageAdAdapter(getActivity());
        //setcurrentItem必须在setadapter之后
        mHomePageAdAdapter.setData(mBannerData);

        mViewPager.setAdapter(mHomePageAdAdapter);
        mViewPager.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
        mViewPager.setCurrentItem(200); // 设置每个Item展示的时间
        mViewPager.start(); // 开始轮播
        mHomePageAdAdapter.notifyDataSetChanged();
    }

    /**
     * 动态添加点
     */
    private void initDot() {
        for (int i = 0; i < mBannerData.size(); i++) {
            View view = null;
            if (getActivity() != null) {
                view = new View(getActivity());
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
            params.leftMargin = (i == 0 ? 0 : 17);//给除了第一个点之外的点都加上marginLeft
            if (view != null) {
                view.setLayoutParams(params);//设置宽高
                view.setBackground(getResources().getDrawable(R.drawable.selector_dot));//设置背景图片
                dotLayout.addView(view);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_order_tourism:
                startActivity(new Intent(getActivity(), OrderTourismActivity.class));
                break;
            case R.id.ll_qinzi://亲子
                Intent intent = new Intent(new Intent(getActivity(), QinSonActivityM.class));
                intent.putExtra("themeId", "1");
                startActivity(intent);
                break;
            case R.id.ll_outdoor://户外
                Intent intent2 = new Intent(new Intent(getActivity(), QinSonActivityM.class));
                intent2.putExtra("themeId", "2");
                startActivity(intent2);
                break;
            case R.id.ll_sheying://摄影
                Intent intent3 = new Intent(new Intent(getActivity(), QinSonActivityM.class));
                intent3.putExtra("themeId", "3");
                startActivity(intent3);
                break;
            case R.id.ll_renwen://游学
                Intent intent4 = new Intent(new Intent(getActivity(), QinSonActivityM.class));
                intent4.putExtra("themeId", "4");
                startActivity(intent4);
                break;
            default:
                break;
        }
    }

    private void initRefreshLayout(BGARefreshLayout refreshLayout) {

        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);

        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置整个加载更多控件的背景颜色资源 id
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.background_line);
    }

    /**
     * 计算高度
     *
     * @param mList
     * @param listView
     */
    public void setListViewHeightBasedOnChildren2(List mList, ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (15 * (listAdapter.getCount()));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (isNetworkAvailable(getActivity())) {
            // 如果网络可用，则加载网络数据
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    // 加载完毕后在 UI 线程结束下拉刷新
                    mRefreshLayout.endRefreshing();
                    newData();
                }
            }.execute();
        } else {
            // 网络不可用，结束下拉刷新
            Toast.makeText(getActivity(), "网络不可用", Toast.LENGTH_SHORT).show();
            mRefreshLayout.endRefreshing();
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
