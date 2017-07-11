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
 * 作者：田学伟 on 2017/7/11 09:15
 * QQ：93226539
 * 作用：
 */

public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.MyViewHolder> {
    private final Context context;
    private final List<FollowAndFollowedsBean.DataBean.ItemsBean.UsersBean> datas;


    public AttentionAdapter(Context context, List<FollowAndFollowedsBean.DataBean.ItemsBean.UsersBean> users) {
        this.context = context;
        this.datas = users;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(UiUtils.inflate(R.layout.fragment_att));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FollowAndFollowedsBean.DataBean.ItemsBean.UsersBean usersBean = datas.get(position);
        holder.fragmentItemFollowName.setText(usersBean.getUser_name());
        holder.fragmentItemFollowJob.setText(usersBean.getUser_desc());
        Glide.with(context)
                .load(usersBean.getUser_image().getOrig())
                .into(holder.fragmentItemFollowImg);

        holder.fragmentItemFollowImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "被点击了...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.fragment_item_follow_img)
        ImageView fragmentItemFollowImg;
        @Bind(R.id.fragment_item_follow_name)
        TextView fragmentItemFollowName;
        @Bind(R.id.fragment_item_follow_job)
        TextView fragmentItemFollowJob;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
