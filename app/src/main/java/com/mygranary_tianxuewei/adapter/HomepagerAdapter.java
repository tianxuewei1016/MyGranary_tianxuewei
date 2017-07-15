package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.HomepagerBean;
import com.mygranary_tianxuewei.ui.HomepageActivity;
import com.mygranary_tianxuewei.ui.MainActivity;
import com.mygranary_tianxuewei.utils.ConstantUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/7/8 11:56
 * QQ：93226539
 * 作用：首页的适配器
 */

public class HomepagerAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<HomepagerBean.DataBean.ItemsBean.ListBeanX> datas;

    @Override
    public int getItemViewType(int position) {
        int itemViewType = 0;
        int type = datas.get(position).getHome_type();
        if (type == 2) {
            itemViewType = 2;
        } else if (type == 4) {
            itemViewType = 4;
        } else {
            itemViewType = 1;
        }
        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public HomepagerAdapter(Context context, List<HomepagerBean.DataBean.ItemsBean.ListBeanX> lists) {
        this.context = context;
        this.datas = lists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View itemView = View.inflate(context, R.layout.hpage_item_1, null);
            return new MyViewHodler(itemView);
        }
        if (viewType == 2) {
            View itemView = View.inflate(context, R.layout.hpage_item, null);
            return new MyViewHodler1(itemView);
        }

        if (viewType == 4) {
            View itemView = View.inflate(context, R.layout.hpage_item_2, null);
            return new MyViewHolder2(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            MyViewHodler myViewHodler = (MyViewHodler) holder;
            Glide.with(context)
//                    .load(datas.get(position).getOne().getPic_url())
                    .load(datas.get(position).getPic_url())
                    .into(myViewHodler.ivHpageIcon);
        }

        if (getItemViewType(position) == 2) {
            MyViewHodler1 myViewHodler = (MyViewHodler1) holder;
            Glide.with(context)
                    .load(datas.get(position).getOne().getPic_url())
                    .into(myViewHodler.gvItemHpage1);

            Glide.with(context)
                    .load(datas.get(position).getTwo().getPic_url())
                    .into(myViewHodler.gvItemHpage2);
        }
        if (getItemViewType(position) == 4) {
            MyViewHolder2 myViewHolder2 = (MyViewHolder2) holder;
            ImageView imageView = new ImageView(context);
            myViewHolder2.ll1.addView(imageView);
            Glide.with(context)
                    .load(datas.get(position).getOne().getPic_url())
                    .into(imageView);

            ImageView imageView1 = new ImageView(context);
            myViewHolder2.ll1.addView(imageView1);
            Glide.with(context)
                    .load(datas.get(position).getTwo().getPic_url())
                    .into(imageView1);

            ImageView imageView2 = new ImageView(context);
            myViewHolder2.ll2.addView(imageView2);
            Glide.with(context)
                    .load(datas.get(position).getThree().getPic_url())
                    .into(imageView2);


            ImageView imageView3 = new ImageView(context);
            myViewHolder2.ll2.addView(imageView3);
            Glide.with(context)
                    .load(datas.get(position).getFour().getPic_url())
                    .into(imageView3);

        }
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_hpage_icon)
        ImageView ivHpageIcon;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            /**
             * 跳转到新的页面练习数据的传递
             */
            ivHpageIcon.setOnClickListener(new View.OnClickListener() {
                List<HomepagerBean.DataBean.ItemsBean.ListBeanX> ItemsBean = datas;
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "被点击了....", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class MyViewHodler1 extends RecyclerView.ViewHolder {
        @Bind(R.id.gv_item_hpage1)
        ImageView gvItemHpage1;
        @Bind(R.id.gv_item_hpage2)
        ImageView gvItemHpage2;

        public MyViewHodler1(View view) {
            super(view);
            ButterKnife.bind(this, view);
            gvItemHpage1.setOnClickListener(new View.OnClickListener() {
                List<HomepagerBean.DataBean.ItemsBean.ListBeanX> ItemsBean = datas;
                @Override
                public void onClick(View v) {
                    HomepagerBean.DataBean.ItemsBean.ListBeanX itemsBean = this.ItemsBean.get(getLayoutPosition());
                    Intent intent = new Intent(context, HomepageActivity.class);
                    intent.putExtra(ConstantUtil.HOME_PAGER, itemsBean);
                    context.startActivity(intent);
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });
            /**
             * 这个里面数据不对,还得调参数
             */
            gvItemHpage2.setOnClickListener(new View.OnClickListener() {
                List<HomepagerBean.DataBean.ItemsBean.ListBeanX> ItemsBean = datas;

                @Override
                public void onClick(View v) {
                    HomepagerBean.DataBean.ItemsBean.ListBeanX itemsBean = this.ItemsBean.get(getLayoutPosition());
                    Intent intent = new Intent(context, HomepageActivity.class);
                    intent.putExtra(ConstantUtil.HOME_PAGER, itemsBean);
                    context.startActivity(intent);
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        @Bind(R.id.ll_1)
        LinearLayout ll1;
        @Bind(R.id.ll_2)
        LinearLayout ll2;

        /**
         * 这里布局写错了应该设置四张图片分别设置点击事件
         *
         * @param view
         */
        public MyViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                List<HomepagerBean.DataBean.ItemsBean.ListBeanX> ItemsBean = datas;

                @Override
                public void onClick(View v) {
                    HomepagerBean.DataBean.ItemsBean.ListBeanX itemsBean = this.ItemsBean.get(getLayoutPosition());
                    Intent intent = new Intent(context, HomepageActivity.class);
                    intent.putExtra(ConstantUtil.HOME_PAGER, itemsBean);
                    context.startActivity(intent);
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });
        }
    }
}