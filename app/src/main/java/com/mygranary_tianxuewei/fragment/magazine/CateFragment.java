package com.mygranary_tianxuewei.fragment.magazine;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
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
import com.mygranary_tianxuewei.bean.MgzCategoryBean;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;
import com.mygranary_tianxuewei.utils.TimeUtils;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/12 12:29
 * QQ：93226539
 * 作用：分类
 */

public class CateFragment extends BaseFragment implements JsonCallBack {

    @Bind(R.id.mgz_grid_view)
    PullToRefreshGridView mgzGridView;
    private List<MgzCategoryBean.DataBean.ItemsBean> itemsBeen = new ArrayList<>();
    private CateAdapter adapter;
    private GridView gridView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
            mgzGridView.setLastUpdatedLabel("最后更新:" + TimeUtils.getCurrentTime());
            mgzGridView.onRefreshComplete();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cate;
    }

    @Override
    protected void initView() {
        mgzGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                HttpUtils.load(ApiConstants.MGZ_CATEGORY_URL).callBack(new JsonCallBack() {
                    @Override
                    public void successJson(String result, int requestCode) {
                        MgzCategoryBean bean = new Gson().fromJson(result, MgzCategoryBean.class);
                        List<MgzCategoryBean.DataBean.ItemsBean> items = bean.getData().getItems();
                        itemsBeen.clear();
                        itemsBeen.addAll(items);
                        handler.sendEmptyMessage(1);
                    }
                }, 45);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

            }
        });
        gridView = mgzGridView.getRefreshableView();
        gridView.setHorizontalSpacing(40);
        gridView.setVerticalSpacing(10);
        adapter = new CateAdapter();
        gridView.setAdapter(adapter);
    }


    @Override
    protected void initData() {
        HttpUtils.load(ApiConstants.MGZ_CATEGORY_URL).callBack(this, 45);
    }

    @Override
    public void successJson(String result, int requestCode) {
        if (requestCode == 45) {
            MgzCategoryBean been = new Gson().fromJson(result, MgzCategoryBean.class);
            itemsBeen.addAll(been.getData().getItems());
            adapter.notifyDataSetChanged();
        }
    }

    class CateAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemsBeen == null ? 0 : itemsBeen.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = UiUtils.inflate(R.layout.mgz_cate_items);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            switch (position) {
                case 0:
                    viewHolder.ivCateText.setText("我的收藏");
                    viewHolder.ivCateImage.setImageResource(R.drawable.bg_topic_favour);
                    Drawable drawable = getResources().getDrawable(R.drawable.icon_my_topics);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    viewHolder.ivCateText.setCompoundDrawables(drawable, null, null, null);
                    break;
                case 1:
                    viewHolder.ivCateText.setText("所有杂志");
                    viewHolder.ivCateImage.setImageResource(R.drawable.bg_topic_all);
                    Drawable drawable1 = getResources().getDrawable(R.drawable.icon_all_topics);
                    drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                    viewHolder.ivCateText.setCompoundDrawables(drawable1, null, null, null);
                default:
                    final MgzCategoryBean.DataBean.ItemsBean itemsBean = itemsBeen.get(position);
                    viewHolder.ivCateText.setText(itemsBean.getCat_name());
                    Glide.with(context)
                            .load(itemsBean.getThumb())
                            .into(viewHolder.ivCateImage);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent intent = new Intent(context,CateActivity.class);
//                            String catid = itemsBeen.get(position).getCat_id();
//                            intent.putExtra("catid",catid);
//                            startActivity(intent);
                        }
                    });
                    break;
            }
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.iv_cate_image)
            ImageView ivCateImage;
            @Bind(R.id.iv_cate_text)
            TextView ivCateText;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
