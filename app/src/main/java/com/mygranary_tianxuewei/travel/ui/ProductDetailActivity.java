package com.mygranary_tianxuewei.travel.ui;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.travel.adapter.ProductTravelListAdapter;
import com.mygranary_tianxuewei.travel.bean.ProductDetailBean;
import com.mygranary_tianxuewei.travel.bean.ProductTravelListBean;
import com.mygranary_tianxuewei.travel.utils.Base64Util;
import com.mygranary_tianxuewei.travel.utils.ConstantNetUtil;
import com.mygranary_tianxuewei.travel.widget.ExpandTextView;
import com.mygranary_tianxuewei.utils.CircleImageView;
import com.mygranary_tianxuewei.utils.LoadNet;
import com.mygranary_tianxuewei.utils.LoadNetHttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static com.mygranary_tianxuewei.R.id.iv_daren_head;
import static com.mygranary_tianxuewei.R.id.iv_main;
import static com.mygranary_tianxuewei.R.id.nest_scroll_view;
import static com.mygranary_tianxuewei.R.id.rl_fee_info;
import static com.mygranary_tianxuewei.R.id.tv_area;
import static com.mygranary_tianxuewei.R.id.tv_daren_intro;
import static com.mygranary_tianxuewei.R.id.tv_daren_name;
import static com.mygranary_tianxuewei.R.id.tv_early_togo;
import static com.mygranary_tianxuewei.R.id.tv_last_to_date;
import static com.mygranary_tianxuewei.R.id.tv_pro_name;
import static com.mygranary_tianxuewei.R.id.tv_pro_number;
import static com.mygranary_tianxuewei.R.id.tv_theme;
import static com.mygranary_tianxuewei.R.id.tv_xinyi_service;
import static com.mygranary_tianxuewei.R.id.wv_intro;
import static com.mygranary_tianxuewei.R.id.wv_pro_exs;

public class ProductDetailActivity extends BaseActivity {

    @Bind(iv_main)
    ImageView ivMain;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_see_more_pic)
    ImageView ivSeeMorePic;
    @Bind(tv_theme)
    TextView tvTheme;
    @Bind(tv_area)
    TextView tvArea;
    @Bind(tv_pro_number)
    TextView tvProNumber;
    @Bind(R.id.ll_top_cover)
    LinearLayout llTopCover;
    @Bind(R.id.first)
    FrameLayout first;
    @Bind(tv_pro_name)
    TextView tvProName;
    @Bind(iv_daren_head)
    CircleImageView ivDarenHead;
    @Bind(tv_daren_name)
    TextView tvDarenName;
    @Bind(tv_daren_intro)
    TextView tvDarenIntro;
    @Bind(R.id.rl_daren_info)
    RelativeLayout rlDarenInfo;
    @Bind(R.id.tv_early_to)
    TextView tvEarlyTo;
    @Bind(tv_early_togo)
    TextView tvEarlyTogo;
    @Bind(R.id.tv_last_to)
    TextView tvLastTo;
    @Bind(tv_last_to_date)
    TextView tvLastToDate;
    @Bind(R.id.no1)
    View no1;
    @Bind(R.id.tv_need_to_early_day)
    TextView tvNeedToEarlyDay;
    @Bind(R.id.iv_right_arrow)
    ImageView ivRightArrow;
    @Bind(R.id.rl_early_to)
    RelativeLayout rlEarlyTo;
    @Bind(R.id.no2)
    TextView no2;
    @Bind(tv_xinyi_service)
    ExpandTextView tvXinyiService;
    @Bind(R.id.tv_read_more)
    TextView tvReadMore;
    @Bind(R.id.rl_no)
    RelativeLayout rlNo;
    @Bind(R.id.collapsing_tool_bar_test_ctl)
    CollapsingToolbarLayout collapsingToolBarTestCtl;
    @Bind(R.id.anchor_tagContainer)
    TabLayout mTabLayout;
    @Bind(R.id.rl_pro_exs)
    RelativeLayout rlProExs;
    @Bind(R.id.id_appbarlayout)
    AppBarLayout idAppbarlayout;
    @Bind(R.id.no18)
    View no18;
    @Bind(R.id.no12)
    TextView no12;
    @Bind(wv_pro_exs)
    WebView wvProExs;
    @Bind(R.id.no15)
    View no15;
    @Bind(R.id.no13)
    TextView no13;
    @Bind(wv_intro)
    LinearLayout lv_intro;
    @Bind(R.id.no5)
    View no5;
    @Bind(R.id.no14)
    TextView no14;
    @Bind(R.id.no8)
    TextView no8;
    @Bind(R.id.tv_fee_include)
    TextView tvFeeInclude;
    @Bind(R.id.no6)
    TextView no6;
    @Bind(R.id.tv_no_include)
    TextView tvNoInclude;
    @Bind(R.id.no17)
    View no17;
    @Bind(R.id.rl_pre_need_konw)
    RelativeLayout rlPreNeedKonw;
    @Bind(R.id.tv_notice)
    TextView tvNotice;
    @Bind(rl_fee_info)
    RelativeLayout rlFeeInfo;
    @Bind(nest_scroll_view)
    NestedScrollView nestScrollView;
    @Bind(R.id.coor)
    CoordinatorLayout coor;
    @Bind(R.id.tv_total)
    TextView tv_totalmoney;
    @Bind(R.id.c)
    TextView c;
    @Bind(R.id.no44)
    View no44;
    @Bind(R.id.rb_orderdetail_service)
    RadioButton rbOrderdetailService;
    @Bind(R.id.btn_next_step_to)
    Button btnNextStepTo;

    private boolean tagFlag = false;
    private ProductTravelListAdapter mProTrvAdapter;
    private List<String> mPicList;
    private List<ProductTravelListBean> mWvIntro;
    private String proId, mNeedToKnow, mDarenId, mProNumber, murl = "";
    private static Boolean click = false;
    private String[] navigationTag = {"产品特色", "行程介绍", "费用说明"};
    private int addHeight = 0, mListSize = 0, plusHeight = 110, mheight;
    private WebView wvintro;

    /**
     * 用于切换内容模块，相应的改变导航标签，表示当一个所处的位置
     */
    private int lastTagIndex = 0;
    private HashMap<String, Integer> list;
    /**
     * 用于在同一个内容模块内滑动，锁定导航标签，防止重复刷新标签
     * true: 锁定
     * false ; 没有锁定
     */
    private boolean content2NavigateFlagInnerLock = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initTitle() {
        llTopCover.getBackground().setAlpha(100);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mProTrvAdapter = new ProductTravelListAdapter(this);
        list = new HashMap<>();
    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            proId = getIntent().getStringExtra("proId");
        }
        mPicList = new ArrayList();
        mWvIntro = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("productId", proId);
        LoadNet.getDataPost(ConstantNetUtil.PRODUCT_DETAIL, this, map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                ProductDetailBean productDetailBean = JSON.parseObject(context, ProductDetailBean.class);
                if (productDetailBean.getResultVo().getStatus().equals("1")) {
                    if (productDetailBean == null) {
                        return;
                    }
                    Glide.with(ProductDetailActivity.this)
                            .load(productDetailBean.getProductUrl())
                            .placeholder(R.drawable.fault_pic)
                            .into(ivMain);

                    for (int i = 0; i < productDetailBean.getProductImgList().size(); i++) {
                        mPicList.add(productDetailBean.getProductImgList().get(i).getProductUrl());
                    }
                    if (mPicList.size() <= 1) {
                        ivSeeMorePic.setVisibility(View.GONE);
                    }
                    if (productDetailBean.getDaRenId().equals("")) {
                        rlDarenInfo.setVisibility(View.GONE);
                    } else {
                        Glide.with(ProductDetailActivity.this)
                                .load(productDetailBean.getPhotoUrl()).into(ivDarenHead);
                        tvDarenIntro.setText(Base64Util.jie(productDetailBean.getDaRenAbout()));//达人标签
                        tvDarenName.setText(productDetailBean.getDaRenNickName());
                    }

                    mDarenId = productDetailBean.getDaRenId();
                    tvTheme.setText(productDetailBean.getThemeName());//主题
                    tvArea.setText(productDetailBean.getAreaName());//区域
                    tvEarlyTogo.setText(productDetailBean.getZaoDate());//最早出发
                    tvLastToDate.setText(productDetailBean.getWanDate());//最晚出发
                    tvProNumber.setText(getResources().getString(R.string.pro_number) + ":" + productDetailBean.getProductNumber());
                    mProNumber = productDetailBean.getProductNumber();
                    tvProName.setText(productDetailBean.getProductName());
                    tvXinyiService.setText(Base64Util.jie(productDetailBean.getProductService()));
                    mNeedToKnow = Base64Util.jie(productDetailBean.getProductReserveNotice());
                    if (LinesCount(Base64Util.jie(productDetailBean.getProductService())) >= 3) {
                        tvReadMore.setVisibility(View.VISIBLE);
                    } else {
                        tvReadMore.setVisibility(View.GONE);
                    }

                    tv_totalmoney.setText("¥" + productDetailBean.getPrice());
                    tvFeeInclude.setText(Base64Util.jie(productDetailBean.getProductCostInclude()));//费用包含
                    tvNoInclude.setText(Base64Util.jie(productDetailBean.getProductCostIncludeNo()));//费用不含
                    tvNeedToEarlyDay.setText("需出行前" + productDetailBean.getAdvanceReserveDayNum() + "天预订");
                    //设置WebView属性，能够执行Javascript脚本
                    WebSettings webSettings = wvProExs.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    wvProExs.loadUrl(productDetailBean.getProductFeatureUrl());
                    wvProExs.setWebViewClient(new MyWebViewClient());

                    if (productDetailBean.getProductTravelList().size() != 0) {
                        mWvIntro.addAll(productDetailBean.getProductTravelList());
                        for (int i = 0; i < mWvIntro.size(); i++) {
                            View view = LayoutInflater.from(ProductDetailActivity.this).inflate(R.layout.activity_webview_pro_detail, null);
                            wvintro = (WebView) view.findViewById(R.id.wv_intro);
                            TextView tv_day = (TextView) view.findViewById(R.id.tv_day);
                            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                            wvintro.loadUrl(mWvIntro.get(i).getTravelFeatureUrl());
                            tv_day.setText("D" + mWvIntro.get(i).getTravelNumber());
                            tv_title.setText(mWvIntro.get(i).getTravelTitle());
                            WebSettings webSettings1 = wvintro.getSettings();
                            webSettings1.setJavaScriptEnabled(true);
                            wvintro.setWebViewClient(new MyWebViewClient());
                            lv_intro.addView(view);
                        }
                    }
                }
            }

            @Override
            public void failure(String error) {

            }
        });
        tvXinyiService.setTextMaxLine(3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nestScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    scrollRefreshNavigationTag(v);
                }
            });
        }

        nestScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tagFlag = true;
                }
                return false;
            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //表明当前的动作是由 TabLayout 触发和主导
                // 根据点击的位置，使ScrollView 滑动到对应区域
                int position = tab.getPosition();
                int targetY = 0;
                switch (position) {
                    case 0:
                        targetY = no18.getTop();
                        break;
                    case 1:
                        targetY = no15.getTop();
                        break;
                    case 2:
                        targetY = rlFeeInfo.getTop();
                        break;
                    default:
                        break;
                }
                // 移动到对应的内容区域
                nestScrollView.smoothScrollTo(0, targetY + 5);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (String item : navigationTag) {
            mTabLayout.addTab(mTabLayout.newTab().setText(item));
        }
    }

    /**
     * 内容区域滑动刷新导航标签
     *
     * @param scrollView 内容模块容器
     */
    private void scrollRefreshNavigationTag(View scrollView) {
        if (scrollView == null) {
            return;
        }
        // 获得ScrollView滑动距离
        int scrollY = scrollView.getScrollY();
        // 确定ScrollView当前展示的顶部内容属于哪个内容模块
        if (scrollY > rlFeeInfo.getTop()) {
            refreshContent2NavigationFlag(2);

        } else if (scrollY > no15.getTop()) {
            refreshContent2NavigationFlag(1);

        } else if (scrollY > no18.getTop()) {
            refreshContent2NavigationFlag(0);
        }
    }

    /**
     * 刷新标签
     *
     * @param currentTagIndex 当前模块位置
     */
    private void refreshContent2NavigationFlag(int currentTagIndex) {
        // 上一个位置与当前位置不一致是，解锁内部锁，是导航可以发生变化
        if (lastTagIndex != currentTagIndex) {
            content2NavigateFlagInnerLock = false;
        }
        if (!content2NavigateFlagInnerLock) {
            // 锁定内部锁
            content2NavigateFlagInnerLock = true;
            // 动作是由ScrollView触发主导的情况下，导航标签才可以滚动选中
            if (tagFlag) {
                mTabLayout.setScrollPosition(currentTagIndex, 0, true);
            }
        }
        lastTagIndex = currentTagIndex;
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            // html加载完成之后，添加监听图片的点击js函数
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        wvProExs.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
        wvintro.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }

    private static long LinesCount(String s) {
        long count = 0;
        int position = 0;
        while ((position = s.indexOf('\n', position)) != -1) {
            count++;
            position++;
        }
        return count;
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
