package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.TuiJianBean;
import com.mygranary_tianxuewei.ui.GifActivity;
import com.mygranary_tianxuewei.utils.Utils;
import com.mygranary_tianxuewei.widget.GlideCircleTransform;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 作者：田学伟 on 2017/7/15 23:46
 * QQ：93226539
 * 作用：百思不得姐,推荐的适配器
 */

public class ShareRecommendAdapter extends RecyclerView.Adapter<ShareRecommendAdapter.BaseViewHolder> {

    private final Context context;
    private final List<TuiJianBean.ListBean> lists;

    public ShareRecommendAdapter(Context context, List<TuiJianBean.ListBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    /**
     * 视频
     */
    private static final int TYPE_VIDEO = 0;

    /**
     * 图片
     */
    private static final int TYPE_IMAGE = 1;

    /**
     * 文字
     */
    private static final int TYPE_TEXT = 2;

    /**
     * GIF图片
     */
    private static final int TYPE_GIF = 3;

    /**
     * 软件推广
     */
    private static final int TYPE_HTML = 5;


    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        TuiJianBean.ListBean listBean = lists.get(position);

        //根据位置，从列表中得到一个数据对象
        String type = listBean.getType();
        if ("video".equals(type)) {
            itemViewType = TYPE_VIDEO;
        } else if ("image".equals(type)) {
            itemViewType = TYPE_IMAGE;
        } else if ("text".equals(type)) {
            itemViewType = TYPE_TEXT;
        } else if ("gif".equals(type)) {
            itemViewType = TYPE_GIF;
        } else if ("html".equals(type)) {
            itemViewType = TYPE_HTML;//广播
        }
        return itemViewType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        switch (viewType) {
            case TYPE_VIDEO:
                //不能使用UiUtils工具类,要不然会出错
                baseViewHolder = new VideoHoder(LayoutInflater.from(context)
                        .inflate(R.layout.all_video_item, parent, false));
                break;
            case TYPE_IMAGE:
                baseViewHolder = new ImageHolder(LayoutInflater.from(context)
                        .inflate(R.layout.all_image_item, parent, false));
                break;
            case TYPE_TEXT:
                baseViewHolder = new TextHolder(LayoutInflater.from(context)
                        .inflate(R.layout.all_text_item, parent, false));
                break;
            case TYPE_GIF:
                baseViewHolder = new GifHolder(LayoutInflater.from(context)
                        .inflate(R.layout.all_gif_item, parent, false));
                break;
            case TYPE_HTML:
                baseViewHolder = new HTMLHolder(LayoutInflater.from(context)
                        .inflate(R.layout.all_ad_item, parent, false));
                break;
        }
        return baseViewHolder;
    }

    /**
     * //当视图滚动到这的时候回调该方法
     *
     * @param holder
     * @param position 在这里设置不同的item的数据,或点击事件
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_VIDEO:
                VideoHoder videoHoder = (VideoHoder) holder;
                videoHoder.setData(lists.get(position));
                break;
            case TYPE_IMAGE:
                ImageHolder imageHolder = (ImageHolder) holder;
                imageHolder.setData(lists.get(position));
                //设置图片的点击事件
                imageHolder.ivImageIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(context, "点击image", Toast.LENGTH_SHORT).show();
                        TuiJianBean.ListBean listBean = lists.get(position);
                        if (listBean != null) {
                            //3.传递视频列表
                            Intent intent = new Intent(context, GifActivity.class);
                            String url = listBean.getImage().getBig().get(0);
                            intent.putExtra("url", url);
                            context.startActivity(intent);
                        }
                    }
                });
                break;
            case TYPE_TEXT:
                TextHolder textHolder = (TextHolder) holder;
                textHolder.setData(lists.get(position));
                break;
            case TYPE_GIF:
                GifHolder gifHolder = (GifHolder) holder;
                gifHolder.setData(lists.get(position));
                //设置图片的点击事件
                gifHolder.ivImageGif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(context, "点击gif", Toast.LENGTH_SHORT).show();
                        TuiJianBean.ListBean listBean = lists.get(position);
                        if (listBean != null) {
                            if (listBean.getGif() != null && listBean.getGif().getImages() != null
                                    && listBean.getGif().getImages().size() > 0) {
                                //3.传递视频列表
                                Intent intent = new Intent(context, GifActivity.class);
                                String url = listBean.getGif().getImages().get(0);
                                intent.putExtra("url", url);
                                context.startActivity(intent);
                            }
                        }
                    }
                });
                break;
            case TYPE_HTML:
                HTMLHolder adHolder = (HTMLHolder) holder;
                adHolder.setData();
                break;
        }
    }

    //加载图标圆形图片用
    private RequestManager glideRequest;

    class BaseViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHeadpic;
        TextView tvName;
        TextView tvTimeRefresh;
        ImageView ivRightMore;
        ImageView ivVideoKind;
        TextView tvVideoKindText;
        TextView tvShenheDingNumber;
        TextView tvShenheCaiNumber;
        TextView tvPostsNumber;
        LinearLayout llDownload;

        TextView tvPinglunContent1;
        TextView tvPinglunContent2;
        TextView tvPinglunContent3;

        public BaseViewHolder(View convertView) {
            super(convertView);

            //公共的
            ivHeadpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvTimeRefresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
            ivRightMore = (ImageView) convertView.findViewById(R.id.iv_right_more);
            //bottom
            ivVideoKind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
            tvVideoKindText = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
            tvShenheDingNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
            tvShenheCaiNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
            tvPostsNumber = (TextView) convertView.findViewById(R.id.tv_posts_number);
            llDownload = (LinearLayout) convertView.findViewById(R.id.ll_download);

            tvPinglunContent1 = (TextView) convertView.findViewById(R.id.tv_pinglun_content1);
            tvPinglunContent2 = (TextView) convertView.findViewById(R.id.tv_pinglun_content2);
            tvPinglunContent3 = (TextView) convertView.findViewById(R.id.tv_pinglun_content3);
        }

        /**
         * 设置公共的数据
         *
         * @param mediaItem
         */
        public void setData(TuiJianBean.ListBean mediaItem) {
            if (mediaItem.getU() != null && mediaItem.getU().getHeader() != null && mediaItem.getU().getHeader().get(0) != null) {
                //加载头像
                glideRequest = Glide.with(context);

                glideRequest.load(mediaItem.getU().getHeader().get(0))
                        .placeholder(R.drawable.brand_logo_empty)
                        .error(R.drawable.brand_logo_empty)
                        .transform(new GlideCircleTransform(context)).into(ivHeadpic);
            }
            if (mediaItem.getU() != null && mediaItem.getU().getName() != null) {
                tvName.setText(mediaItem.getU().getName() + "");
            }
            tvTimeRefresh.setText(mediaItem.getPasstime());
            //设置标签
            List<TuiJianBean.ListBean.TagsBean> tagsEntities = mediaItem.getTags();
            if (tagsEntities != null && tagsEntities.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < tagsEntities.size(); i++) {
                    buffer.append(tagsEntities.get(i).getName() + " ");
                }
                tvVideoKindText.setText(buffer.toString());
            }
            //设置点赞，踩,转发
            tvShenheDingNumber.setText(mediaItem.getUp());
            tvShenheCaiNumber.setText(mediaItem.getDown() + "");
            tvPostsNumber.setText(mediaItem.getForward() + "");
            //设置第一条评论的消息
            List<TuiJianBean.ListBean.TopCommentsBean> top_comments = mediaItem.getTop_comments();
            if (top_comments != null && top_comments.size() > 0) {

                TuiJianBean.ListBean.TopCommentsBean bean2 = top_comments.get(0);
                tvPinglunContent1.setText(bean2.getU().getName() + ": " + bean2.getContent());
                //使名字变色
                SpannableStringBuilder builder = new SpannableStringBuilder(tvPinglunContent1.getText().toString());
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#1f83a4"));
                builder.setSpan(colorSpan, 0, bean2.getU().getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvPinglunContent1.setText(builder);

                if (top_comments.size() > 1) {
                    tvPinglunContent2.setVisibility(View.VISIBLE);

                    TuiJianBean.ListBean.TopCommentsBean bean3 = top_comments.get(1);
                    tvPinglunContent2.setText(bean3.getU().getName() + ": " + bean3.getContent());
                    //使名字变色
                    SpannableStringBuilder builder3 = new SpannableStringBuilder(tvPinglunContent2.getText().toString());
                    ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.parseColor("#1f83a4"));
                    builder3.setSpan(colorSpan3, 0, bean3.getU().getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvPinglunContent2.setText(builder3);
                }

                if (top_comments.size() > 2) {
                    tvPinglunContent3.setVisibility(View.VISIBLE);

                    TuiJianBean.ListBean.TopCommentsBean bean4 = top_comments.get(2);
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

    class VideoHoder extends BaseViewHolder {
        Utils utils;
        TextView tvContext;
        JCVideoPlayerStandard jcvVideoplayer;
        TextView tvPlayNums;
        TextView tvVideoDuration;
        ImageView ivCommant;

        public VideoHoder(View convertView) {
            super(convertView);

            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            utils = new Utils();
            tvPlayNums = (TextView) convertView.findViewById(R.id.tv_play_nums);
            tvVideoDuration = (TextView) convertView.findViewById(R.id.tv_video_duration);
            ivCommant = (ImageView) convertView.findViewById(R.id.iv_commant);
            jcvVideoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
        }


        public void setData(TuiJianBean.ListBean mediaItem) {
            super.setData(mediaItem);

            //设置文本-所有的都有,只有广告没有哦
            tvContext.setText(mediaItem.getText());

            //视频特有的------------------------
            //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
            boolean setUp = jcvVideoplayer.setUp(
                    mediaItem.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            //加载图片
            if (setUp) {
                Glide.with(context)
                        .load(mediaItem.getVideo().getThumbnail().get(0))
                        .centerCrop()//是图片填充父窗体
                        .into(jcvVideoplayer.thumbImageView);
            }
            tvPlayNums.setText(mediaItem.getVideo().getPlaycount() + "次播放");
            tvVideoDuration.setText(utils.stringForTime(mediaItem.getVideo().getDuration() * 1000) + "");
        }
    }

    class ImageHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView ivImageIcon;

        public ImageHolder(View convertView) {
            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            ivImageIcon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
        }

        public void setData(TuiJianBean.ListBean mediaItem) {
            super.setData(mediaItem);
            //设置文本-所有的都有
            tvContext.setText(mediaItem.getText());
            //图片特有的
            ivImageIcon.setImageResource(R.drawable.bg_item);
            if (mediaItem.getImage() != null && mediaItem.getImage().getSmall() != null) {
                Glide.with(context)
                        .load(mediaItem.getImage().getDownload_url().get(0))
                        .placeholder(R.drawable.bg_item)
                        .error(R.drawable.bg_item)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivImageIcon);
            }
        }
    }

    class TextHolder extends BaseViewHolder {
        TextView tvContext;

        public TextHolder(View itemView) {
            super(itemView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
        }

        public void setData(TuiJianBean.ListBean mediaItem) {
            super.setData(mediaItem);
            //设置文本-所有的都有
            tvContext.setText(mediaItem.getText());
        }
    }

    class GifHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView ivImageGif;

        public GifHolder(View convertView) {
            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            ivImageGif = (ImageView) convertView.findViewById(R.id.iv_image_gif);
        }

        public void setData(TuiJianBean.ListBean mediaItem) {
            super.setData(mediaItem);
            //设置文本-所有的都有
            tvContext.setText(mediaItem.getText());

            //下面是gif
            if (mediaItem.getGif() != null && mediaItem.getGif().getImages() != null) {
                Glide.with(context)
                        .load(mediaItem.getGif().getImages().get(0))
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(ivImageGif);
            }
        }
    }

    class HTMLHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView ivImageIcon;

        public HTMLHolder(View itemView) {
            super(itemView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
            ivImageIcon = (ImageView) itemView.findViewById(R.id.iv_image_icon);
        }

        public void setData() {
            tvContext.setText("HTML");
        }
    }
}
