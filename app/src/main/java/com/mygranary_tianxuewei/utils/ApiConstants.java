package com.mygranary_tianxuewei.utils;

/**
 * 作者：田学伟 on 2017/7/5 18:59
 * QQ：93226539
 * 作用：网络常量工具类
 */

public class ApiConstants {
    //商店
    public static final String BASE_URL = "http://mobile.iliangcang.com/";
    //商店总分类的联网地址
    public static final String TYPE_FRAGMENT = "goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //商品详情
    public static String getGoodsDetail(String id){
        return "http://mobile.iliangcang.com" +
                "/goods/goodsDetail?app_key=Android&sig=5693B044D5F961D25489767100FA6553%7C731720001493468&v=1.0&goods_id="+id;
    }

    //品牌
    public static final String BASE_URL_BRAND = "http://mobile.iliangcang.com/";
    public static final String BRAND_FRAGMEN = "brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //家居
    public static final String HOME_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0045&count=10&coverId=1&page=1&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0";
    //家具
    public static final String FURNITURE_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0055&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //文具
    public static final String STATIONERY_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0062&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //数码
    public static final String NUMERICAL_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0069&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //玩乐
    public static final String LIBERTINISM_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0077&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //厨卫
    public static final String KITCHEN_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0082&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //美食
    public static final String CATE_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0092&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //男装
    public static final String MEN_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0101&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //女装
    public static final String WOMEN_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0112&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //童装
    public static final String CHILDREN_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0125&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //鞋包
    public static final String SHOES_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0129&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //配饰
    public static final String ACCESSORIES_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0141&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //美护
    public static final String BEAUTY_CARE_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0154&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //户外
    public static final String OUTDOORS_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0166&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //植物
    public static final String PLANT_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0172&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //图书
    public static final String BOOKS_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0182&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //礼物
    public static final String GIFT_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0190&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //推荐
    public static final String RECOMMEND_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0198&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //艺术
    public static final String ART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0214&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";

    //专题
    public static final String SPECIAL_URL = "http://mobile.iliangcang.com/";
    public static final String SPECIAL_FRAGMENT = "goods/shopSpecial?app_key=Android&count=10&page=1&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";

    //首页
    public static final String HOMEPAGER_URL = "http://mobile.iliangcang.com/";
    public static final String HOMEPAGER_FRAGMENT = "goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";

    /**
     * 达人默认排序
     */
    public static final String getOrderDefaultUrl(int page) {
        return "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=" +
                page +
                "&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    }

    //最多排序
    public static final String getOrderSumUrl(int page) {
        return "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=goods_sum&page=" +
                page +
                "&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    }

    //最受欢迎
    public static final String getOrderFollwerUrl(int page) {
        return "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=followers&page=" +
                page +
                "&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    }

    //最新排序
    public static final String getOrderActionUrl(int page) {
        return "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=action_time&page=" +
                page +
                "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
    }

    //最新加入
    public static final String getOrderTimeUrl(int page) {
        return "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=reg_time&page=" +
                page +
                "&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    }

    //搜索
    public static final String getTalentSearchUrl(String keyWord, int page) {
        return "http://mobile.iliangcang.com/user/search?app_key=Android&count=18&keyword=" +
                keyWord +
                "&page=" +
                page +
                "&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";
    }

    /**
     * 用户信息
     */
    //喜欢
    public static final String getUserLike(String id, int page) {
        return "http://mobile.iliangcang.com" +
                "/user/masterLike?app_key=Android&count=10&owner_id=" + id + "&page=" + page + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
    }

    //推荐
    public static String getUserTuijian(String id, int page) {
        return "http://mobile.iliangcang.com" +
                "/user/masterListInfo?app_key=Android&count=10&owner_id=" + id + "&page=" + page + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
    }

    //关注
    public static String getUserFollow(String id, int page) {
        return "http://mobile.iliangcang.com" +
                "/user/masterFollow?app_key=Android&count=12&owner_id=" + id + "&page=" + page + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
    }

    //粉丝
    public static String getUserFans(String id, int page) {
        return "http://mobile.iliangcang.com" +
                "/user/masterFollowed?app_key=Android&count=12&owner_id=" + id + "&page=" + page + "&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";
    }


    /**
     * 杂志的链接
     */
    //杂志主页
    public static final String MAGEZINE_URL = "http://mobile.iliangcang.com/topic/magazineList?app_key=Android&author_id=1&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //杂志作者
    public static final String MGZ_AUTHOR_URL = "http://mobile.iliangcang.com/topic/magazineAuthorList?app_key=Android&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";
    //杂志分类
    public static final String MGZ_CATEGORY_URL = "http://mobile.iliangcang.com/topic/magazineCatList?app_key=Android&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";

    //杂志选择
    public static String getMgzSelectUrl(String category, String id) {
        return "http://mobile.iliangcang.com/topic/magazineList?app_key=Android&" +
                category +
                "=" +
                id +
                "&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";
    }

    public static final String getMgzSeluri1(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri3(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri4(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri5(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri6(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri7(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri8(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri11(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri12(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri13(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri14(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static final String getMgzSeluri23(int page) {
        return "http://mobile.iliangcang.com"
                + "/topic/magazineList?app_key=Android&author_id=" + page + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    }

    public static String getShopBraDetailsUrl(){
        return "http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id=14&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    }

}
