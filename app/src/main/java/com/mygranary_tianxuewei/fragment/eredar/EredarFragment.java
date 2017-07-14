package com.mygranary_tianxuewei.fragment.eredar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.EredarFragmentBean;
import com.mygranary_tianxuewei.ui.MainActivity;
import com.mygranary_tianxuewei.ui.UserInfoActivity;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.CircleImageView;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：田学伟 on 2017/7/5 19:20
 * QQ：93226539
 * 作用：达人
 */

public class EredarFragment extends BaseFragment implements JsonCallBack {
    @Bind(R.id.eretar_search)
    ImageView eretarSearch;
    @Bind(R.id.eretar_set)
    ImageView eretarSet;
    private PullToRefreshGridView refreshGridView;
    private ListView lvtalent;
    private GridView gridview;
    private PopupWindow popupWindow;
    //先把数据放到集合中
    private List<EredarFragmentBean.DataBean.ItemsBean> items = new ArrayList<>();
    //popupWindow的里面的五个数据
    private List<String> menuList = new ArrayList<>();
    //达人主页的适配器
    private EredarAdapter eredarAdapter;
    //popupWindow的适配器
    private MyMenuAdapter myMenuAdapter;
    private int page = 1;
    private View viewmenu;
    private int mPosition;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UiUtils.inflate(R.layout.fragment_eredar);
        //创建适配器
        eredarAdapter = new EredarAdapter();
        //把东西放到listview视图中去
        initview(view);
        //网络请求,字段需要拼接
        getData();
        //数据
        initMenu();
        //设置适配器
        gridview.setAdapter(eredarAdapter);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.eretar_search, R.id.eretar_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eretar_search:
                showToast("搜索被点击了...");
                break;
            case R.id.eretar_set:
                //设置图片
                eretarSet.setBackgroundResource(R.drawable.close);
                lvtalent = (ListView) viewmenu.findViewById(R.id.lv_talent);
                //创建popupWindow的适配器
                myMenuAdapter = new MyMenuAdapter();
                lvtalent.setAdapter(myMenuAdapter);
                /**
                 * 设置PopupWindow是否响应touch事件，默认是true，如果设置为false，
                 * 结果会是所有touch事件无响应，包括点击事件
                 */
                popupWindow.setTouchable(true);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                //设置背景颜色
                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.colorTalent));
                popupWindow.showAsDropDown(view, 0, 10);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        eretarSet.setBackgroundResource(R.drawable.actionbar_navigation_menu);
                    }
                });

                lvtalent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //点击的时候重新去网络请求,并且让popupWindow消失
                        mPosition = position;
                        page = 1;
                        if (position == 0) {
                            url = ApiConstants.getOrderDefaultUrl(page);
                            HttpUtils.load(url).callBack(EredarFragment.this, 2);
                        } else if (position == 1) {
                            url = ApiConstants.getOrderSumUrl(page);
                            HttpUtils.load(url).callBack(EredarFragment.this, 2);
                        } else if (position == 2) {
                            url = ApiConstants.getOrderFollwerUrl(page);
                            HttpUtils.load(url).callBack(EredarFragment.this, 2);
                        } else if (position == 3) {
                            url = ApiConstants.getOrderActionUrl(page);
                            HttpUtils.load(url).callBack(EredarFragment.this, 2);
                        } else if (position == 4) {
                            url = ApiConstants.getOrderTimeUrl(page);
                            HttpUtils.load(url).callBack(EredarFragment.this, 2);
                        }
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                    }
                });
                break;
        }
    }

    class MyMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return menuList.size();
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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = UiUtils.inflate(R.layout.menu_item);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.talentMenuItemTv.setText(menuList.get(position));
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.talent_menu_item_tv)
            TextView talentMenuItemTv;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    private void initview(View view) {
        viewmenu = UiUtils.inflate(R.layout.talent_menu);

        popupWindow = new PopupWindow(viewmenu, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        refreshGridView = (PullToRefreshGridView) view.findViewById(R.id.eretar_pullgrid);
        gridview = refreshGridView.getRefreshableView();
        refreshGridView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置有多少列
        gridview.setNumColumns(3);
        //设置垂直间距
        gridview.setVerticalSpacing(15);
        //设置横向间距
        gridview.setHorizontalSpacing(15);

        refreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                String label = DateUtils.formatDateTime(
                        context.getApplicationContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel("最后更新" + label);
                page = 1;
                if (mPosition == 0) {
                    url = ApiConstants.getOrderDefaultUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 2);
                } else if (mPosition == 1) {
                    url = ApiConstants.getOrderSumUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 2);
                } else if (mPosition == 2) {
                    url = ApiConstants.getOrderFollwerUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 2);
                } else if (mPosition == 3) {
                    url = ApiConstants.getOrderActionUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 2);
                } else if (mPosition == 4) {
                    url = ApiConstants.getOrderTimeUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 2);
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                page++;
                if (mPosition == 0) {
                    url = ApiConstants.getOrderDefaultUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 3);
                } else if (mPosition == 1) {
                    url = ApiConstants.getOrderSumUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 3);
                } else if (mPosition == 2) {
                    url = ApiConstants.getOrderFollwerUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 3);
                } else if (mPosition == 3) {
                    url = ApiConstants.getOrderActionUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 3);
                } else if (mPosition == 4) {
                    url = ApiConstants.getOrderTimeUrl(page);
                    HttpUtils.load(url).callBack(EredarFragment.this, 3);
                }
            }
        });
    }

    /**
     * 网络请求
     */
    private void getData() {
        url = ApiConstants.getOrderDefaultUrl(page);
        HttpUtils.load(url).callBack(this, 1);
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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = UiUtils.inflate(R.layout.userinfofragment_follow_item);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final EredarFragmentBean.DataBean.ItemsBean itemsBean = items.get(position);
            viewHolder.useFragmentItemFollowName.setText(itemsBean.getUsername());
            viewHolder.useFragmentItemFollowJob.setText(itemsBean.getDuty());
            Glide.with(getActivity())
                    .load(itemsBean.getUser_images().getOrig())
                    .into(viewHolder.useFragmentItemFollowImg);
            viewHolder.useFragmentItemFollowImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uid = itemsBean.getUid();
                    Intent intent = new Intent(context, UserInfoActivity.class);
                    intent.putExtra("id", uid);
                    context.startActivity(intent);
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.use_fragment_item_follow_img)
            CircleImageView useFragmentItemFollowImg;
            @Bind(R.id.use_fragment_item_follow_name)
            TextView useFragmentItemFollowName;
            @Bind(R.id.use_fragment_item_follow_job)
            TextView useFragmentItemFollowJob;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 联网成功的时候回调的接口
     *
     * @param result
     * @param requestCode
     */
    @Override
    public void successJson(String result, int requestCode) {
        if (requestCode == 1) {
            EredarFragmentBean bean = new Gson().fromJson(result, EredarFragmentBean.class);
            items = bean.getData().getItems();
            eredarAdapter.notifyDataSetChanged();
        }
        if (requestCode == 2) {
            EredarFragmentBean bean = new Gson().fromJson(result, EredarFragmentBean.class);
            List<EredarFragmentBean.DataBean.ItemsBean> items1 = bean.getData().getItems();
            items = items1;
            refreshGridView.onRefreshComplete();
            eredarAdapter.notifyDataSetChanged();
        }
        if (requestCode == 3) {
            EredarFragmentBean bean = new Gson().fromJson(result, EredarFragmentBean.class);
            List<EredarFragmentBean.DataBean.ItemsBean> items3 = bean.getData().getItems();
            items.addAll(items3);
            refreshGridView.onRefreshComplete();
            eredarAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

}
