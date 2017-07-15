package com.mygranary_tianxuewei.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.bean.GiftActivityBean;

import butterknife.Bind;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.mygranary_tianxuewei.R.id.shop_info_customer;

/**
 * 商品详情页面
 */
public class UseInfoActivity extends BaseActivity {
    @Bind(shop_info_customer)
    ImageView shopInfoCustomer;
    @Bind(R.id.shop_info_joinCar)
    Button shopInfoJoinCar;
    @Bind(R.id.shop_info_Buy)
    Button shopInfoBuy;
    @Bind(R.id.shop_info_line)
    LinearLayout shopInfoLine;
    @Bind(R.id.shop_info_viewpager)
    ImageView imageView;
    @Bind(R.id.shop_info_name)
    TextView shopInfoName;
    @Bind(R.id.shop_info_goodsname)
    TextView shopInfoGoodsname;
    @Bind(R.id.shop_info_heart)
    ImageView shopInfoHeart;
    @Bind(R.id.shop_info_likecount)
    TextView shopInfoLikecount;
    @Bind(R.id.shop_info_share)
    ImageView shopInfoShare;
    @Bind(R.id.shop_info_price)
    TextView shopInfoPrice;
    @Bind(R.id.shop_info_mian)
    TextView shopInfoMian;
    @Bind(R.id.shop1)
    ImageView shop1;
    @Bind(R.id.shop2)
    TextView shop2;
    @Bind(R.id.shop3)
    ImageView shop3;
    @Bind(R.id.shop_info_size)
    RelativeLayout shopInfoSize;
    @Bind(R.id.shop_info_logo_img)
    ImageView shopInfoLogoImg;
    @Bind(R.id.shop_info_logo_name)
    TextView shopInfoLogoName;
    @Bind(R.id.shop4)
    ImageView shop4;
    @Bind(R.id.shop_info_logo)
    RelativeLayout shopInfoLogo;
    @Bind(R.id.shop_info_good_detail)
    RadioButton shopInfoGoodDetail;
    @Bind(R.id.shop_info_good_guide)
    RadioButton shopInfoGoodGuide;
    @Bind(R.id.shop_info_radio_group)
    RadioGroup shopInfoRadioGroup;
    @Bind(R.id.shop_info_fram)
    FrameLayout shopInfoFram;
    @Bind(R.id.shop_info_re)
    RelativeLayout shopInfoRe;
    @Bind(R.id.shop_info_scroll)
    ScrollView shopInfoScroll;
    @Bind(R.id.shop_info_back)
    ImageView shopInfoBack;
    @Bind(R.id.shop_info_shopcar)
    Button shopInfoShopcar;

    private GiftActivityBean.DataBean.ItemsBean gift;

    @Override
    public int getLayoutId() {
        return R.layout.activity_use_info;

    }

    @Override
    protected void initTitle() {
        //接收数据
        gift = (GiftActivityBean.DataBean.ItemsBean) getIntent().getSerializableExtra("gift");
    }

    @Override
    protected void initData() {
        //设置数据
        shopInfoName.setText(gift.getBrand_info().getBrand_name());
        shopInfoGoodsname.setText(gift.getGoods_name());
        shopInfoLikecount.setText(gift.getLike_count());
        shopInfoPrice.setText(gift.getPrice());
        shopInfoLogoName.setText(gift.getBrand_info().getBrand_name());
        //设置图片
        Glide.with(UseInfoActivity.this)
                .load(gift.getGoods_image())
                .into(imageView);
        Glide.with(UseInfoActivity.this)
                .load(gift.getBrand_info().getBrand_logo())
                .into(shopInfoLogoImg);

    }

    @Override
    protected void initListener() {
        shopInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
            }
        });
        //客服
        shopInfoCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLineServicePopWindow();
            }
        });
        //加入购物车
        shopInfoJoinCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //直接购买
        shopInfoBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //分享
        shopInfoShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
                oks.setTitle("来自尚硅谷it教育");
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                oks.setTitleUrl("http://atguigu.com/");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("大王派我来巡山");
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://atguigu.com/");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("尚硅谷it教育好");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite("尚硅谷it教育");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://atguigu.com/");
                // 启动分享GUI
                oks.show(UseInfoActivity.this);
            }
        });
    }

    /**
     * 联系客服
     */
    private void toLineServicePopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_toline_service, null);
        TextView btn_pro_id = (TextView) view.findViewById(R.id.btn_pro_id);
        TextView btn_online_service = (TextView) view.findViewById(R.id.btn_online_service);
        TextView btn_to_call = (TextView) view.findViewById(R.id.btn_to_call);
        TextView btncancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });


        btn_online_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UseInfoActivity.this, CallCenterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        btn_to_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "010-80509333"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
        }
        return super.onKeyUp(keyCode, event);
    }
}
