package com.mygranary_tianxuewei.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.CgDetailsBean;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;
import com.mygranary_tianxuewei.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/9 16:37
 * QQ：93226539
 * 作用：品牌产品
 */

public class BrandProductFragment extends BaseFragment {

    @Bind(R.id.fragment_brand_details_grid_view)
    PullToRefreshGridView mRefreshView;
    private int page = 1;
    private List<CgDetailsBean.DataBean.ItemsBean> itemsBeanList = new ArrayList<>();
    private GridView mGridView;
    private MyAdapter mAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mAdapter.notifyDataSetChanged();
            mRefreshView.setLastUpdatedLabel("最后更新:" + TimeUtils.getCurrentTime());
            mRefreshView.onRefreshComplete();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_brand_details;
    }

    @Override
    protected void initView() {
        mRefreshView.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshView.setOnRefreshListener(refreshListener2);
        mGridView = mRefreshView.getRefreshableView();
        mGridView.setVerticalSpacing(10);
        mGridView.setHorizontalSpacing(10);
        mAdapter = new MyAdapter();
        mGridView.setAdapter(mAdapter);
//        mGridView.setOnItemClickListener(itemClickListener);
    }

    private PullToRefreshBase.OnRefreshListener2 refreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        //上拉
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            page = 1;
            itemsBeanList.clear();
            HttpUtils.load(ApiConstants.getShopBraDetailsUrl()).callBack(new JsonCallBack() {
                @Override
                public void successJson(String result, int requestCode) {
                    if (requestCode == 35) {
                        Gson gson = new Gson();
                        CgDetailsBean cgDetailsBean = gson.fromJson(result, CgDetailsBean.class);
                        itemsBeanList.addAll(cgDetailsBean.getData().getItems());
                        mHandler.sendEmptyMessage(1);
                    }
                }
            }, 35);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            page++;
            HttpUtils.load(ApiConstants.getShopBraDetailsUrl()).callBack(new JsonCallBack() {
                @Override
                public void successJson(String result, int requestCode) {
                    if (requestCode == 36) {
                        Gson gson = new Gson();
                        CgDetailsBean cgDetailsBean = gson.fromJson(result, CgDetailsBean.class);
                        itemsBeanList.addAll(cgDetailsBean.getData().getItems());
                        mHandler.sendEmptyMessage(1);
                    }
                }
            }, 36);
        }
    };


    @Override
    protected void initData() {
        loadData();
    }

    private void loadData() {
        itemsBeanList.clear();
        HttpUtils.load(ApiConstants.getShopBraDetailsUrl()).callBack(new JsonCallBack() {
            @Override
            public void successJson(String result, int requestCode) {
                if (requestCode == 34) {
                    Gson gson = new Gson();
                    CgDetailsBean cgDetailsBean = gson.fromJson(result, CgDetailsBean.class);
                    itemsBeanList.addAll(cgDetailsBean.getData().getItems());
                    mAdapter.notifyDataSetChanged();
                }
            }
        }, 34);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemsBeanList == null ? 0 : itemsBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemsBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodler viewHodler;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.product_item_view, parent, false);
                viewHodler = new ViewHodler(convertView);
            } else {
                viewHodler = (ViewHodler) convertView.getTag();
            }
            CgDetailsBean.DataBean.ItemsBean itemsBean = itemsBeanList.get(position);
            Glide.with(context).load(itemsBean.getGoods_image()).into(viewHodler.iconImg);
            viewHodler.goodNameTxt.setText(itemsBean.getGoods_name());
            viewHodler.brandNameTxt.setText(itemsBean.getBrand_info().getBrand_name());
            viewHodler.priceTxt.setText("¥" + itemsBean.getPrice());
            viewHodler.countTxt.setText(itemsBean.getLike_count());
            return convertView;
        }

        class ViewHodler {
            public ImageView iconImg;
            public TextView goodNameTxt;
            public TextView brandNameTxt;
            public TextView priceTxt;
            public TextView countTxt;

            public ViewHodler(View view) {
                view.setTag(this);
                iconImg = (ImageView) view.findViewById(R.id.product_item_icon_img);
                goodNameTxt = (TextView) view.findViewById(R.id.product_item_good_name_txt);
                brandNameTxt = (TextView) view.findViewById(R.id.product_item_brand_name_txt);
                priceTxt = (TextView) view.findViewById(R.id.product_item_price_txt);
                countTxt = (TextView) view.findViewById(R.id.product_item_count_txt);
            }
        }
    }
}