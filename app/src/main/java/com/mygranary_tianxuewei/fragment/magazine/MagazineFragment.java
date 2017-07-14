package com.mygranary_tianxuewei.fragment.magazine;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.MgzBean;
import com.mygranary_tianxuewei.bean.MgzInfoBean;
import com.mygranary_tianxuewei.ui.MainActivity;
import com.mygranary_tianxuewei.ui.MgzDetailsActivity;
import com.mygranary_tianxuewei.ui.WebActivity;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/5 19:20
 * QQ：93226539
 * 作用：杂志
 */

public class MagazineFragment extends BaseFragment implements JsonCallBack {

    @Bind(R.id.magazine_time_txt)
    TextView mTimeTxt;
    @Bind(R.id.magazine_title_txt)
    TextView mTitleTxt;
    @Bind(R.id.magazine_expandable_list)
    ExpandableListView mExpandableList;
    //放数据
    private List<String> keyList = new ArrayList<>();
    private Map<String, List<MgzInfoBean>> dataMap = new LinkedHashMap<>();
    private MyAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_magazine;
    }

    @Override
    protected void initView() {
        mExpandableList.setGroupIndicator(null);
        mAdapter = new MyAdapter();
        mExpandableList.setAdapter(mAdapter);
        /**
         *  ExpandableListView的子元素点击监听
         */
        mExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                MgzInfoBean mgzInfoBean = dataMap.get(keyList.get(groupPosition)).get(childPosition);
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", mgzInfoBean.getTopic_url());
                intent.putExtra("name", mgzInfoBean.getTopic_name());
                startActivity(intent);
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                return false;
            }
        });
        /**
         * ExpandableListView的组监听事件
         */
        mExpandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        mExpandableList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int num = 0;
                for (int i = 0; i < keyList.size() - 1; i++) {
                    String key = keyList.get(i);
                    num = dataMap.get(key).size() + num;
                    int after = num + dataMap.get(keyList.get(i + 1)).size();
                    if (after > firstVisibleItem - i && firstVisibleItem - i >= num) {
                        mTimeTxt.setText(getSubResult(key));
                    }
                }
            }
        });

        mTitleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MgzDetailsActivity.class);
                context.startActivity(intent);
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.overridePendingTransition(R.anim.bottom_in,R.anim.top_out);
            }
        });
    }

    /**
     * 当请求数据成功的时候回调这个接口,在这里解析数据设置适配器
     *
     * @param result
     * @param requestCode
     */
    @Override
    public void successJson(String result, int requestCode) {
        if (requestCode == 42) {
            Gson gson = new Gson();
            List<String> keys = gson.fromJson(result, MgzBean.class).getData().getItems().getKeys();
            keyList.addAll(keys);
            mTimeTxt.setText(getSubResult(keyList.get(0)));
            try {
                JSONObject object = new JSONObject(result);
                JSONObject data = object.getJSONObject("data");
                JSONObject item = data.getJSONObject("items");
                JSONObject infos = item.getJSONObject("infos");
                for (int i = 0; i < keyList.size(); i++) {
                    List<MgzInfoBean> mgzInfoBeenList = new ArrayList<>();
                    String key = keyList.get(i);
                    JSONArray jsonArray = infos.getJSONArray(key);
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(j);
                        String taid = jsonObject.getString("taid");
                        String topic_name = jsonObject.getString("topic_name");
                        String cat_id = jsonObject.getString("cat_id");
                        String author_id = jsonObject.getString("author_id");
                        String topic_url = jsonObject.getString("topic_url");
                        String access_url = jsonObject.getString("access_url");
                        String cover_img = jsonObject.getString("cover_img");
                        String cover_img_new = jsonObject.getString("cover_img_new");
                        String hit_number = jsonObject.getString("hit_number");
                        String addtime = jsonObject.getString("addtime");
                        String content = jsonObject.getString("content");
                        String nav_title = jsonObject.getString("nav_title");
                        String author_name = jsonObject.getString("author_name");
                        String cat_name = jsonObject.getString("cat_name");
                        MgzInfoBean mgzInfoBean = new MgzInfoBean(taid, topic_name, cat_id, author_id, topic_url, access_url, cover_img, cover_img_new, hit_number, addtime, content, nav_title, author_name, cat_name);
                        mgzInfoBeenList.add(mgzInfoBean);
                    }
                    dataMap.put(key, mgzInfoBeenList);
                }
                mAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void initData() {
        HttpUtils.load(ApiConstants.MAGEZINE_URL).callBack(this, 42);
    }

    /**
     * 1. 当设置setOnGroupClickListener监听并让其返回true时,
     * 所有Group消费点击事件，事件均不能分发传递给child(换言之，设置setOnChildClickListener不起任何作用)。
     * 2.默认设置完Group以及child，Group左边会默认有以上下切换的图标，
     * 假如你有强迫症可以通过mListView.setGroupIndicator(null)去除。
     * <p>
     * 3.前面已经简单说明了isChildSelectable(int groupPosition, int childPosition)方法的作用，
     * 所以当我们需要child可点击时，必须将setOnGroupClickListener和isChildSelectable对应设置为false和true。
     * <p>
     * 4.说到这里也许读者会问，到底还是没有说出图一的做法。现在对图一进行详细的介绍。图一主要是将Group设置
     * 为不能收缩并且使其默认展开(即设置setOnGroupClickListener返回true，并且添加源码中setAdapter后三行代码)。
     */
    class MyAdapter extends BaseExpandableListAdapter {
        /**
         * 获取组元素数目
         *
         * @return
         */
        @Override
        public int getGroupCount() {
            return keyList == null ? 0 : keyList.size();
        }

        /**
         * 获取子元素数目
         *
         * @param groupPosition
         * @return
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = keyList.get(groupPosition);
            List<MgzInfoBean> mgzInfoBeen = dataMap.get(key);
            return mgzInfoBeen.size();
        }

        /**
         * 获取组元素对象
         *
         * @param groupPosition
         * @return
         */
        @Override
        public Object getGroup(int groupPosition) {
            return keyList.get(groupPosition);
        }

        /**
         * //获取子元素对象
         *
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = keyList.get(groupPosition);
            List<MgzInfoBean> mgzInfoBeen = dataMap.get(key);
            return mgzInfoBeen.get(childPosition);
        }

        /**
         * 获取组元素Id
         *
         * @param groupPosition
         * @return
         */
        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        /**
         * 获取子元素Id
         *
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        /**
         * 加载并显示组元素
         *
         * @param groupPosition
         * @param isExpanded
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder viewHodler;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.magazine_item_father, parent, false);
                viewHodler = new GroupViewHolder(convertView);
                convertView.setTag(viewHodler);
            } else {
                viewHodler = (GroupViewHolder) convertView.getTag();
            }
            viewHodler.mgzItemText.setText(getSubResult(keyList.get(groupPosition)));
            mExpandableList.expandGroup(groupPosition);
            return convertView;
        }

        class GroupViewHolder {
            @Bind(R.id.mgz_item_text)
            TextView mgzItemText;

            GroupViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

        /**
         * 加载子元素并显示
         *
         * @param groupPosition
         * @param childPosition
         * @param isLastChild
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder viewHodler;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.magazine_item_child, parent, false);
                viewHodler = new ChildViewHolder(convertView);
                convertView.setTag(viewHodler);
            } else {
                viewHodler = (ChildViewHolder) convertView.getTag();
            }
            MgzInfoBean mgzInfoBean = dataMap.get(keyList.get(groupPosition)).get(childPosition);
            viewHodler.mgzItemDescTxt.setText(mgzInfoBean.getCat_name());
            viewHodler.mgzItemCategoryTxt.setText(mgzInfoBean.getTopic_name());
            Glide.with(context).load(mgzInfoBean.getCover_img_new()).into(viewHodler.mgzItemIconImg);
            return convertView;
        }

        class ChildViewHolder {
            @Bind(R.id.mgz_item_icon_img)
            ImageView mgzItemIconImg;
            @Bind(R.id.mgz_item_desc_txt)
            TextView mgzItemDescTxt;
            @Bind(R.id.mgz_item_category_txt)
            TextView mgzItemCategoryTxt;

            ChildViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    private String getSubResult(String string) {
        String substring = string.substring(5);
        return substring;
    }
}
