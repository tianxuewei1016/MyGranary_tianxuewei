package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.FollowAndFollowedsBean;
import com.mygranary_tianxuewei.utils.UiUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/11 09:34
 * QQ：93226539
 * 作用：
 */

public class FansAdapter extends RecyclerView.Adapter<FansAdapter.MyViewHolde> {
    private final Context context;
    private final List<FollowAndFollowedsBean.DataBean.ItemsBean.UsersBean> datas;


    public FansAdapter(Context context, List<FollowAndFollowedsBean.DataBean.ItemsBean.UsersBean> users) {
        this.context = context;
        this.datas = users;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public MyViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolde(UiUtils.inflate(R.layout.fragment_fans_item));
    }

    @Override
    public void onBindViewHolder(MyViewHolde holder, int position) {
        FollowAndFollowedsBean.DataBean.ItemsBean.UsersBean usersBean = datas.get(position);

        holder.fragmentItemFansName.setText(usersBean.getUser_name());
        holder.fragmentItemFansJob.setText(usersBean.getUser_desc());
        Glide.with(context)
                .load(usersBean.getUser_image().getOrig())
                .into(holder.fragmentItemFansImg);

        holder.fragmentItemFansImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "被点击了...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyViewHolde extends RecyclerView.ViewHolder {
        @Bind(R.id.fragment_item_fans_img)
        ImageView fragmentItemFansImg;
        @Bind(R.id.fragment_item_fans_name)
        TextView fragmentItemFansName;
        @Bind(R.id.fragment_item_fans_job)
        TextView fragmentItemFansJob;

        public MyViewHolde(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
