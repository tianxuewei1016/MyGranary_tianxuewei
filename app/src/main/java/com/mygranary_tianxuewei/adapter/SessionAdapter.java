package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.SessionBean;
import com.mygranary_tianxuewei.widget.GlideCircleTransform;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/16 18:38
 * QQ：93226539
 * 作用：百思不得姐,段子的适配器
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.MyViewHolder> {

    private final Context context;
    private final List<SessionBean.ListBean> datas;


    public SessionAdapter(Context context, List<SessionBean.ListBean> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_session_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(datas.get(position), position);
    }

    //加载图标圆形图片用
    private RequestManager glideRequest;

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_user_icon)
        ImageView ivUserIcon;
        @Bind(R.id.tv_user_name)
        TextView tvUserName;
        @Bind(R.id.tv_updatetime)
        TextView tvUpdatetime;
        @Bind(R.id.tv_joke_content)
        TextView tvJokeContent;
        @Bind(R.id.tv_ding)
        TextView tvDing;
        @Bind(R.id.tv_zan)
        TextView tvZan;
        @Bind(R.id.ll_ding)
        LinearLayout llDing;
        @Bind(R.id.iv_cai)
        TextView ivCai;
        @Bind(R.id.tv_down_joke)
        TextView tvDownJoke;
        @Bind(R.id.ll_cai)
        LinearLayout llCai;
        @Bind(R.id.tv_share_joke)
        TextView tvShareJoke;
        @Bind(R.id.ll_share)
        LinearLayout llShare;
        @Bind(R.id.tv_pinglun_joke)
        TextView tvPinglunJoke;
        @Bind(R.id.ll_download)
        LinearLayout llDownload;
        @Bind(R.id.tv_pinglun_content1)
        TextView tvPinglunContent1;
        @Bind(R.id.tv_pinglun_content2)
        TextView tvPinglunContent2;
        @Bind(R.id.tv_pinglun_content3)
        TextView tvPinglunContent3;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(SessionBean.ListBean bean, int position) {
            glideRequest = Glide.with(context);

            glideRequest.load(bean.getU().getHeader().get(0))
                    .placeholder(R.drawable.brand_logo_empty)
                    .error(R.drawable.brand_logo_empty)
                    .transform(new GlideCircleTransform(context)).into(ivUserIcon);

            tvUserName.setText(bean.getU().getName());

            tvUpdatetime.setText(bean.getPasstime());
            tvJokeContent.setText(bean.getText());
            tvZan.setText(bean.getUp());
            //设置第一条评论的消息
            List<SessionBean.ListBean.TopCommentsBean> top_comments = bean.getTop_comments();
            if (top_comments != null && top_comments.size() > 0) {
                SessionBean.ListBean.TopCommentsBean bean2 = top_comments.get(0);
                tvPinglunContent1.setText(bean2.getU().getName() + ": " + bean2.getContent());
                //使名字变色
                SpannableStringBuilder builder = new SpannableStringBuilder(tvPinglunContent1.getText().toString());
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#1f83a4"));
                builder.setSpan(colorSpan, 0, bean2.getU().getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvPinglunContent1.setText(builder);

                if (top_comments.size() > 1) {
                    tvPinglunContent2.setVisibility(View.VISIBLE);

                    SessionBean.ListBean.TopCommentsBean bean3 = top_comments.get(1);
                    tvPinglunContent2.setText(bean3.getU().getName() + ": " + bean3.getContent());
                    //使名字变色
                    SpannableStringBuilder builder3 = new SpannableStringBuilder(tvPinglunContent2.getText().toString());
                    ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.parseColor("#1f83a4"));
                    builder3.setSpan(colorSpan3, 0, bean3.getU().getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvPinglunContent2.setText(builder3);
                }

                if (top_comments.size() > 2) {
                    tvPinglunContent3.setVisibility(View.VISIBLE);

                    SessionBean.ListBean.TopCommentsBean bean4 = top_comments.get(2);
                    tvPinglunContent3.setText(bean4.getU().getName() + ": " + bean4.getContent());
                    //使名字变色
                    SpannableStringBuilder builder3 = new SpannableStringBuilder(tvPinglunContent3.getText().toString());
                    ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.parseColor("#1f83a4"));
                    builder3.setSpan(colorSpan3, 0, bean4.getU().getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvPinglunContent3.setText(builder3);
                }
            }
        }
    }
}
