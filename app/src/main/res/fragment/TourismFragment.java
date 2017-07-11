package com.ovationtourism.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ovationtourism.R;
import com.ovationtourism.adapter.ProductRclAdapter;
import com.ovationtourism.adapter.SortAllAdapter;
import com.ovationtourism.adapter.SortRecommendAdapter;
import com.ovationtourism.adapter.SortThemeAdapter;
import com.ovationtourism.base.BaseFragment;
import com.ovationtourism.base.ViewHelper;
import com.ovationtourism.constant.ConstantNetUtil;
import com.ovationtourism.domain.ProductListBean;
import com.ovationtourism.domain.ProductType;
import com.ovationtourism.domain.QueryProductListBean;
import com.ovationtourism.domain.XyTypeDictionarysBean;
import com.ovationtourism.ui.ProductDetailActivity;
import com.ovationtourism.utils.LoadNet;
import com.ovationtourism.utils.LoadNetHttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * @name OvationTourism
 * @class name：com.ovationtourism.fragment
 * @anthor hx
 * @time 2017-05-09 11:44
 * @change
 * @class describe
 */
public class TourismFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.ll_sort_all)
    LinearLayout ll_sort_all;//全部
    @BindView(R.id.ll_sort_zhuanti)
    LinearLayout ll_sort_zhuanti;//专题
    @BindView(R.id.ll_sort_recommend)
    LinearLayout ll_sort_recommend;//推荐
    @BindView(R.id.tv_all)
    TextView tv_all;//
    @BindView(R.id.tv_zhuanti)
    TextView tv_zhuanti;
    @BindView(R.id.tv_recommend)
    TextView tv_recommend;
    @BindView(R.id.iv_all_arrow)
    ImageView iv_all_arrow;//
    @BindView(R.id.iv_zhuanti_arrrow)
    ImageView iv_zhuanti_arrow;
    @BindView(R.id.iv_recommend_arrow)
    ImageView iv_recommend_arrow;
    @BindView(R.id.view_down)
    View view_down;
    @BindView(R.id.nest_scroll_view)
    NestedScrollView nest_scroll_view;
    @BindView(R.id.rcl_product)
    RecyclerView rcl_product;//产品rel


    private ProductRclAdapter mProductRclAdapter;
    private LinearLayoutManager mLayoutManager;
    private View v;
    private PopupWindow mPopupWindow;
    private SortAllAdapter adapter = null;//全部分类
    private SortThemeAdapter mThemeAdapter = null;//主题adapter
    private SortRecommendAdapter mSortRecAdapter = null;//推荐adapter
    private int mCheckAllPos = -1, mCheckZhuanPos = -1, mCheckRecPos = -1;
    private boolean mClick = false;
    private ListView listView;//popupwindow的listview
    private List<String> proTypeAll;//全部产品类型
    private List<QueryProductListBean> mData,mRefresh;//产品列表的数据
    private List<XyTypeDictionarysBean> mTypeData;//全部类型的数据
    private String[] toBeStored;//转化成string[]
    private int mCurrentPage=1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tourism;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        ViewHelper.click(this, ll_sort_all, ll_sort_recommend, ll_sort_zhuanti);
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        mLayoutManager = new LinearLayoutManager(getActivity());
        mProductRclAdapter = new ProductRclAdapter(getActivity());
        mRefresh=new ArrayList<>();

        mLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager.setAutoMeasureEnabled(true);
        rcl_product.setLayoutManager(mLayoutManager);
        View view1 = inflater.inflate(R.layout.fragment_tourism_poplistview, null);
        listView = (ListView) view1.findViewById(R.id.lv_sort);
        mPopupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v = view1.findViewById(R.id.ll_cover);
        v.getBackground().setAlpha(100);
        getProductType();
        loadMore();

         //解决嵌套的卡顿
        rcl_product.setHasFixedSize(true);
        rcl_product.setNestedScrollingEnabled(false);
    }

    private void getProductType() {
        proTypeAll = new ArrayList<>();
        HashMap<String, String> map = new HashMap();
        map.put("typeCode", "PRODUCT_TYPE_CODE");
        LoadNet.getDataPost(ConstantNetUtil.PRODUCT_TYPE, getActivity(), map, new LoadNetHttp() {
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
                } else {
                    Toast.makeText(getActivity(), productType.getMsg(), Toast.LENGTH_SHORT).show();
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
        map.put("pageNo", mCurrentPage+"");
        LoadNet.getDataPost(ConstantNetUtil.PRODUCT_LIST, getActivity(), map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                ProductListBean productListBean = JSON.parseObject(context, ProductListBean.class);
                mData=new ArrayList<>();
                mData = productListBean.getQueryProductList();
                mRefresh.addAll(mData);
                if (mCurrentPage==1){
                    mProductRclAdapter.setmData(mData);
                }else {
                    mProductRclAdapter.setmData(mRefresh);
                }
                rcl_product.setAdapter(mProductRclAdapter);
                mProductRclAdapter.notifyDataSetChanged();

            }

            @Override
            public void failure(String error) {

            }
        });

    }

    private void loadMore() {
        nest_scroll_view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                if (i1 == (nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight())) {
                    mCurrentPage++;
                    getProductList(tv_all.getText().toString(),tv_zhuanti.getText().toString(),tv_recommend.getText().toString());

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_sort_all://点击全部
                if (!mClick) {
                    tv_all.setTextColor(android.graphics.Color.RED);
                    iv_all_arrow.setImageResource(R.drawable.up_red_arrow);
                    adapter = new SortAllAdapter(getActivity(), mCheckAllPos, toBeStored);
                    listView.setAdapter(adapter);
                    showmPopupWindow(view_down, adapter, 1);
                    mClick = true;
                } else {
                    mClick = false;
                    mPopupWindow.dismiss();
                }
                break;
            case R.id.ll_sort_zhuanti://点击主题
                if (!mClick) {
                    if (mCheckZhuanPos == -1) {
                        tv_zhuanti.setText(R.string.buxian);
                    }
                    tv_zhuanti.setTextColor(android.graphics.Color.RED);
                    iv_zhuanti_arrow.setImageResource(R.drawable.up_red_arrow);
                    mThemeAdapter = new SortThemeAdapter(getActivity(), mCheckZhuanPos);
                    listView.setAdapter(mThemeAdapter);
                    mThemeAdapter.notifyDataSetChanged();
                    showmPopupWindow(view_down, mThemeAdapter, 2);
                    mClick = true;
                } else {
                    mClick = false;
                    mPopupWindow.dismiss();
                }
                break;
            case R.id.ll_sort_recommend://点击推荐
                if (!mClick) {
                    iv_recommend_arrow.setImageResource(R.drawable.up_red_arrow);
                    mSortRecAdapter = new SortRecommendAdapter(getActivity(), mCheckRecPos);
                    listView.setAdapter(mSortRecAdapter);
                    mSortRecAdapter.notifyDataSetChanged();
                    showmPopupWindow(view_down, mSortRecAdapter, 3);
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

    public void showmPopupWindow(View view, final ArrayAdapter ada, final int flag) {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(getActivity());
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
                 mCurrentPage=1;
                 mRefresh=new ArrayList<>();
                 nest_scroll_view.scrollTo(0,0);
                if (flag == 1) {
                    tv_all.setText(ada.getItem(pos) + "");
                    tv_all.setTextColor(android.graphics.Color.RED);
                    iv_all_arrow.setImageResource(R.drawable.down_red_arrow);
                    mCheckAllPos = pos;

                } else if (flag == 2) {
                    tv_zhuanti.setText(ada.getItem(pos) + "");
                    tv_zhuanti.setTextColor(android.graphics.Color.RED);
                    iv_zhuanti_arrow.setImageResource(R.drawable.down_red_arrow);
                    mCheckZhuanPos = pos;
                } else {
                    tv_recommend.setText(ada.getItem(pos) + "");
                    tv_recommend.setTextColor(android.graphics.Color.RED);
                    iv_recommend_arrow.setImageResource(R.drawable.down_red_arrow);
                    mCheckRecPos = pos;
                }
                getProductList(tv_all.getText().toString(), tv_zhuanti.getText().toString(), tv_recommend.getText().toString());
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
                        tv_all.setTextColor(Color.BLACK);
                        iv_all_arrow.setImageResource(R.drawable.small_arrow_down);
                    } else {
                        iv_all_arrow.setImageResource(R.drawable.down_red_arrow);
                    }
                } else if (flag == 2) {
                    if (mCheckZhuanPos == -1 || mCheckZhuanPos == 0) {
                        tv_zhuanti.setText(R.string.buxian);
                        tv_zhuanti.setTextColor(Color.BLACK);
                        iv_zhuanti_arrow.setImageResource(R.drawable.small_arrow_down);
                    } else {
                        iv_zhuanti_arrow.setImageResource(R.drawable.down_red_arrow);
                    }
                } else {
                    iv_recommend_arrow.setImageResource(R.drawable.down_red_arrow);
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
