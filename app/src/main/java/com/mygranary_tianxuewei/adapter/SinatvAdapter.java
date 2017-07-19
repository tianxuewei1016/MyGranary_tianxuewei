package com.mygranary_tianxuewei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.bean.SinatvBean;
import com.mygranary_tianxuewei.utils.BitmapUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/7/18 10:31
 * QQ：93226539
 * 作用：直播的适配器
 */

public class SinatvAdapter extends RecyclerView.Adapter<BaseViewHodler> {

    private final Context context;
    private static SinatvBean.DataBean datas;
    private static final int BANNER = 0;
    private static final int DEFAULT = 2;
    private final LayoutInflater inflater;

    public SinatvAdapter(Context context, SinatvBean.DataBean data) {
        this.context = context;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
//        return 1 + datas.getPartitions().size();
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            return BANNER;
        } else if (position == 1) {
            return 1;
        }
        return DEFAULT;
    }

    @Override
    public BaseViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerViewHodler(inflater.inflate(R.layout.fragment_sina_banner, parent, false));
            case 1:
                return new BannerBottomViewHodler(inflater.inflate(R.layout.fragment_sina_bot, parent, false));
            case DEFAULT:
                return new DefaultViewHolder(inflater.inflate(R.layout.fragment_default, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHodler holder, int position) {
        holder.setData();
    }

    class BannerViewHodler extends BaseViewHodler {
        @Bind(R.id.banner)
        Banner banner;

        public BannerViewHodler(View itemView) {
            super(itemView);
        }

        @Override
        public void setData() {
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    BitmapUtils.glideToImage((String) path, imageView);
                }
            });
            List<String> list = new ArrayList<>();
            for (int i = 0; i < datas.getBanner().size(); i++) {
                list.add(datas.getBanner().get(i).getImg());
            }
            if (datas.getBanner().size() == 1) {
                list.add(datas.getBanner().get(0).getImg());
            }
            banner.setImages(list);
            banner.start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(context, "被点击了...", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class BannerBottomViewHodler extends BaseViewHodler {
        @Bind(R.id.attention)
        LinearLayout attention;
        @Bind(R.id.imageView)
        ImageView imageView;
        @Bind(R.id.centre)
        LinearLayout centre;
        @Bind(R.id.smallvideo)
        LinearLayout smallvideo;
        @Bind(R.id.seek)
        LinearLayout seek;
        @Bind(R.id.classify)
        LinearLayout classify;

        public BannerBottomViewHodler(View itemView) {
            super(itemView);
        }

        @Override
        public void setData() {
            seek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "被点击了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class DefaultViewHolder extends BaseViewHodler {
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_number)
        TextView tvNumber;
        @Bind(R.id.recyclerview)
        RecyclerView recyclerview;
        @Bind(R.id.tv_more)
        TextView tvMore;
        @Bind(R.id.tv_refresh)
        TextView tvRefresh;
        @Bind(R.id.ll_default)
        LinearLayout llDefault;
        ViewHolder viewHolder;

        public DefaultViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData() {
            SinatvBean.DataBean.PartitionsBean partitionsBean = datas.getPartitions().get(getLayoutPosition() - 2);
            String src = partitionsBean.getPartition().getSub_icon().getSrc();
            BitmapUtils.glideToImage(src, ivIcon);
            tvNumber.setText(partitionsBean.getPartition().getCount() + "");
            MyBaseAdapter<SinatvBean.DataBean.PartitionsBean.LivesBean> myBaseAdapter = new MyBaseAdapter<SinatvBean.DataBean.PartitionsBean.LivesBean>(context, partitionsBean.getLives()) {
                @Override
                protected BaseViewHodler setViewHolder(ViewGroup parent) {
                    viewHolder = new ViewHolder(inflater.inflate(R.layout.item_live_grid, parent, false), datas);
                    return viewHolder;
                }
            };
        }
    }

    class ViewHolder extends BaseViewHodler {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData() {

        }
    }
}
