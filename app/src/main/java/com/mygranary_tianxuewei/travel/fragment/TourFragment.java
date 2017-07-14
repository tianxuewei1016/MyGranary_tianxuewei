package com.mygranary_tianxuewei.travel.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.travel.adapter.ProductRclAdapter;
import com.mygranary_tianxuewei.travel.adapter.SortAllAdapter;
import com.mygranary_tianxuewei.travel.adapter.SortRecommendAdapter;
import com.mygranary_tianxuewei.travel.adapter.SortThemeAdapter;
import com.mygranary_tianxuewei.travel.bean.ProductListBean;
import com.mygranary_tianxuewei.travel.bean.ProductType;
import com.mygranary_tianxuewei.travel.bean.QueryProductListBean;
import com.mygranary_tianxuewei.travel.bean.XyTypeDictionarysBean;
import com.mygranary_tianxuewei.travel.ui.ProductDetailActivity;
import com.mygranary_tianxuewei.travel.utils.ConstantNetUtil;
import com.mygranary_tianxuewei.utils.LoadNet;
import com.mygranary_tianxuewei.utils.LoadNetHttp;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.mygranary_tianxuewei.R.id.iv_all_arrow;
import static com.mygranary_tianxuewei.R.id.iv_recommend_arrow;
import static com.mygranary_tianxuewei.R.id.rcl_product;
import static com.mygranary_tianxuewei.R.id.tv_all;
import static com.mygranary_tianxuewei.R.id.tv_recommend;
import static com.mygranary_tianxuewei.R.id.tv_zhuanti;
import static com.mygranary_tianxuewei.R.id.view_down;

/**
 * 作者：田学伟 on 2017/7/11 11:00
 * QQ：93226539
 * 作用：旅游
 */

public class TourFragment extends BaseFragment {
    @Bind(R.id.lv)
    TextView lv;
    @Bind(R.id.collapsing_tool_bar_test_ctl)
    CollapsingToolbarLayout collapsingToolBarTestCtl;
    @Bind(tv_all)
    TextView tvAll;
    @Bind(iv_all_arrow)
    ImageView ivAllArrow;
    @Bind(R.id.ll_sort_all)
    LinearLayout llSortAll;
    @Bind(tv_zhuanti)
    TextView tvZhuanti;
    @Bind(R.id.iv_zhuanti_arrrow)
    ImageView ivZhuantiArrrow;
    @Bind(R.id.ll_sort_zhuanti)
    LinearLayout llSortZhuanti;
    @Bind(tv_recommend)
    TextView tvRecommend;
    @Bind(iv_recommend_arrow)
    ImageView ivRecommendArrow;
    @Bind(R.id.ll_sort_recommend)
    LinearLayout llSortRecommend;
    @Bind(view_down)
    View viewDown;
    @Bind(R.id.id_appbarlayout)
    AppBarLayout idAppbarlayout;
    @Bind(rcl_product)
    RecyclerView rclProduct;
    @Bind(R.id.lv1)
    TextView lv1;
    @Bind(R.id.nest_scroll_view)
    NestedScrollView nestScrollView;

    private boolean mClick = false;
    private View v;
    private PopupWindow mPopupWindow;
    private ListView listView;//popupwindow的listview
    private LinearLayoutManager mLayoutManager;
    private ProductRclAdapter mProductRclAdapter;
    private List<QueryProductListBean> mData, mRefresh;//产品列表的数据
    private List<String> proTypeAll;//全部产品类型
    private List<XyTypeDictionarysBean> mTypeData;
    private String[] toBeStored;//转化成string[]
    private int mCurrentPage = 1;
    private SortAllAdapter adapter = null;//全部分类
    private int mCheckAllPos = -1, mCheckZhuanPos = -1, mCheckRecPos = -1;
    private SortThemeAdapter mThemeAdapter = null;//主题adapter
    private SortRecommendAdapter mSortRecAdapter = null;//推荐adapter

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tourism;
    }

    @Override
    protected void initView() {
        mLayoutManager = new LinearLayoutManager(context);
        mProductRclAdapter = new ProductRclAdapter(context);
        mRefresh = new ArrayList<>();

        mLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager.setAutoMeasureEnabled(true);
        rclProduct.setLayoutManager(mLayoutManager);
        View view1 = UiUtils.inflate(R.layout.fragment_tourism_poplistview);

        listView = (ListView) view1.findViewById(R.id.lv_sort);
        mPopupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v = view1.findViewById(R.id.ll_cover);
        v.getBackground().setAlpha(100);
        getProductType();
        loadMore();
        //解决嵌套的卡顿
        rclProduct.setHasFixedSize(true);
        rclProduct.setNestedScrollingEnabled(false);
    }


    /**
     * 网络请求
     */
    private void getProductType() {
        proTypeAll = new ArrayList<>();
        HashMap<String, String> map = new HashMap();
        map.put("typeCode", "PRODUCT_TYPE_CODE");
        LoadNet.getDataPost(ConstantNetUtil.PRODUCT_TYPE, context, map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                ProductType productType = JSON.parseObject(context, ProductType.class);
                if (productType.getStatus().equals("1")) {
                    mTypeData = productType.getXyTypeDictionarys();
                    proTypeAll.add("全部");
                    if (productType.getXyTypeDictionarys() != null) {
                        for (int i = 0; i < productType.getXyTypeDictionarys().size(); i++) {
                            proTypeAll.add(productType.getXyTypeDictionarys().get(i).getItemName());
                        }
                    }
                    toBeStored = proTypeAll.toArray(new String[proTypeAll.size()]);
                    getProductList("", "", "1");
                }
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    private void getProductList(String proType, String proTheme, String queryType) {
        if (proType.equals("全部") || proType == null) {
            proType = "";
        }
        for (int i = 0; i < proTypeAll.size(); i++) {
            if (proTypeAll.get(i).equals(proType)) {
                if (i == 0) {
                    proType = mTypeData.get(i).getItemCode();
                } else {
                    proType = mTypeData.get(i - 1).getItemCode();
                }
            }
        }

        if (proTheme.equals("主题") || proTheme.equals("不限")) {
            proTheme = "";
        }
        if (proTheme.equals("亲子")) {
            proTheme = "1";
        } else if (proTheme.equals("户外")) {
            proTheme = "2";
        } else if (proTheme.equals("摄影")) {
            proTheme = "3";
        } else if (proTheme.equals("游学")) {
            proTheme = "4";
        }
        if (queryType.equals("推荐排序")) {
            queryType = "1";
        }
        if (queryType.equals("最新上架")) {
            queryType = "2";
        }
        if (queryType.equals("销量最好")) {
            queryType = "3";
        }
        if (queryType.equals("价格从低到高")) {
            queryType = "4";
        }
        if (queryType.equals("价格从高到低")) {
            queryType = "5";
        }
        HashMap<String, String> map = new HashMap();
        map.put("productTypeCode", proType);
        map.put("themeId", proTheme);
        map.put("queryType", queryType);
        map.put("pageSize", "10");
        map.put("pageNo", mCurrentPage + "");
        LoadNet.getDataPost(ConstantNetUtil.PRODUCT_LIST, context, map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                ProductListBean productListBean = JSON.parseObject(context, ProductListBean.class);
                mData = new ArrayList<>();
                mData = productListBean.getQueryProductList();
                mRefresh.addAll(mData);
                if (mCurrentPage == 1) {
                    mProductRclAdapter.setmData(mData);
                } else {
                    mProductRclAdapter.setmData(mRefresh);
                }
                rclProduct.setAdapter(mProductRclAdapter);
                mProductRclAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        nestScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                if (i1 == (nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight())) {
                    mCurrentPage++;
                    getProductList(tvAll.getText().toString(), tvZhuanti.getText().toString(), tvRecommend.getText().toString());
                }
            }
        });

        mProductRclAdapter.setOnItemClickLitener(new ProductRclAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("proId",mRefresh.get(position).getProductId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_sort_all, R.id.ll_sort_zhuanti, R.id.ll_sort_recommend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sort_all://点击全部
                if (!mClick) {
                    tvAll.setTextColor(Color.RED);
                    ivAllArrow.setImageResource(R.drawable.up_red_arrow);
                    adapter = new SortAllAdapter(context, mCheckAllPos, toBeStored);
                    listView.setAdapter(adapter);
                    showmPopupWindow(viewDown, adapter, 1);
                    mClick = true;
                } else {
                    mClick = false;
                    mPopupWindow.dismiss();
                }
                break;
            case R.id.ll_sort_zhuanti://点击主题
                if (!mClick) {
                    if (mCheckZhuanPos == -1) {
                        tvZhuanti.setText(R.string.buxian);
                    }
                    tvZhuanti.setTextColor(Color.RED);
                    ivZhuantiArrrow.setImageResource(R.drawable.up_red_arrow);
                    mThemeAdapter = new SortThemeAdapter(context, mCheckZhuanPos);
                    listView.setAdapter(mThemeAdapter);
                    mThemeAdapter.notifyDataSetChanged();
                    showmPopupWindow(viewDown, mThemeAdapter, 2);
                    mClick = true;
                } else {
                    mClick = false;
                    mPopupWindow.dismiss();
                }
                break;
            case R.id.ll_sort_recommend://点击推荐
                if (!mClick) {
                    ivRecommendArrow.setImageResource(R.drawable.up_red_arrow);
                    mSortRecAdapter = new SortRecommendAdapter(getActivity(), mCheckRecPos);
                    listView.setAdapter(mSortRecAdapter);
                    mSortRecAdapter.notifyDataSetChanged();
                    showmPopupWindow(viewDown, mSortRecAdapter, 3);
                    mClick = true;
                } else {
                    mClick = false;
                    mPopupWindow.dismiss();
                }
                break;
            default:
                break;
        }
    }

    private void showmPopupWindow(View view, final ArrayAdapter ada, final int flag) {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(context);
        }
        mPopupWindow.update();
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        // 选择item的监听事件
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                mCurrentPage = 1;
                mRefresh = new ArrayList<>();
                nestScrollView.scrollTo(0, 0);
                if (flag == 1) {
                    tvAll.setText(ada.getItem(pos) + "");
                    tvAll.setTextColor(Color.RED);
                    ivAllArrow.setImageResource(R.drawable.down_red_arrow);
                    mCheckAllPos = pos;
                } else if (flag == 2) {
                    tvZhuanti.setText(ada.getItem(pos) + "");
                    tvZhuanti.setTextColor(Color.RED);
                    ivZhuantiArrrow.setImageResource(R.drawable.down_red_arrow);
                    mCheckZhuanPos = pos;
                } else {
                    tvRecommend.setText(ada.getItem(pos) + "");
                    tvRecommend.setTextColor(Color.RED);
                    ivRecommendArrow.setImageResource(R.drawable.down_red_arrow);
                    mCheckRecPos = pos;
                }
                getProductList(tvAll.getText().toString(), tvZhuanti.getText().toString(), tvRecommend.getText().toString());
                mClick = false;
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.showAsDropDown(view, 0, 0);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //旋转0度是复位ImageView
                if (flag == 1) {
                    if (mCheckAllPos == -1 || mCheckAllPos == 0) {
                        tvAll.setTextColor(Color.BLACK);
                        ivAllArrow.setImageResource(R.drawable.small_arrow_down);
                    } else {
                        ivAllArrow.setImageResource(R.drawable.down_red_arrow);
                    }
                } else if (flag == 2) {
                    if (mCheckZhuanPos == -1 || mCheckZhuanPos == 0) {
                        tvZhuanti.setText(R.string.buxian);
                        tvZhuanti.setTextColor(Color.BLACK);
                        ivZhuantiArrrow.setImageResource(R.drawable.small_arrow_down);
                    } else {
                        ivZhuantiArrrow.setImageResource(R.drawable.down_red_arrow);
                    }
                } else {
                    ivRecommendArrow.setImageResource(R.drawable.down_red_arrow);
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }
}
