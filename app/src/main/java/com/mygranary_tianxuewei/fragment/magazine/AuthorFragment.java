package com.mygranary_tianxuewei.fragment.magazine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.MgzAuthor;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;
import com.mygranary_tianxuewei.widget.CircleImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/12 12:29
 * QQ：93226539
 * 作用：作者
 */

public class AuthorFragment extends BaseFragment implements JsonCallBack {

    @Bind(R.id.mgz_author_list_view)
    ListView mListView;
    private List<MgzAuthor.DataBean.ItemsBean> itemsBeanList = new ArrayList<>();
    private PullToRefreshListView mRefresh;
    private MyAdapter myAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mgz_author;
    }

    @Override
    protected void initView() {
        myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

    @Override
    protected void initData() {
        HttpUtils.load(ApiConstants.MGZ_AUTHOR_URL).callBack(this, 46);
    }

    @Override
    public void successJson(String result, int requestCode) {
        if (requestCode == 46) {
            Gson gson = new Gson();
            List<MgzAuthor.DataBean.ItemsBean> items = gson.fromJson(result, MgzAuthor.class).getData().getItems();
            itemsBeanList.addAll(items);
            myAdapter.notifyDataSetChanged();
        }
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
                convertView = LayoutInflater.from(context).inflate(R.layout.mgz_author_item, parent, false);
                viewHodler = new ViewHodler(convertView);
                convertView.setTag(viewHodler);
            } else {
                viewHodler = (ViewHodler) convertView.getTag();
            }
            MgzAuthor.DataBean.ItemsBean itemsBean = itemsBeanList.get(position);
            viewHodler.nameTxt.setText(itemsBean.getAuthor_name());
            viewHodler.noteTxt.setText(itemsBean.getNote());
            Glide.with(context).load(itemsBean.getThumb()).into(viewHodler.imageView);
            return convertView;
        }

        class ViewHodler {
            public CircleImage imageView;
            public TextView nameTxt;
            public TextView noteTxt;

            public ViewHodler(View view) {
                imageView = (CircleImage) view.findViewById(R.id.mgz_author_item_circle_image);
                nameTxt = (TextView) view.findViewById(R.id.mgz_author_item_name_txt);
                noteTxt = (TextView) view.findViewById(R.id.mgz_author_item_note_txt);
            }
        }
    }
}
