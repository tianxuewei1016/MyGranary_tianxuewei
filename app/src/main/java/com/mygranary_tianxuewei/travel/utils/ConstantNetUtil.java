package com.mygranary_tianxuewei.travel.utils;

/**
 * 作者：田学伟 on 2017/7/11 11:47
 * QQ：93226539
 * 作用：
 */

public class ConstantNetUtil {
    //    public static final String SERVER_IOT = "http://10.4.66.39:8080/xyzmwebsite/";//测试域名
//    public static final String SERVER_IOT = "http://10.4.66.139:8080/xyzmwebsite/";//测试域名
    public static final String SERVER_IOT = "http://api.ennovatourism.com/";//生产环境域名

    public static final String CHECK_APP_VERSION = SERVER_IOT+"app/comCtrl/checkAppUpdate.do";//检查版本更新
    public static final String HOME_PAGE = SERVER_IOT+"app/homePageCtrl/getHomePageInfo.do";//首页
    public static final String MY_ORDER = SERVER_IOT+"app/orderCtrl/findCustOrderInfo.do";//我的订单
    public static final String PRODUCT_TYPE = SERVER_IOT+"app/comCtrl/findXyTypeDictionarys.do";//产品类型
    public static final String PRODUCT_LIST = SERVER_IOT+"app/productCtrl/queryProductList.do";//产品列表
    public static final String PRODUCT_DETAIL = SERVER_IOT+"app/productCtrl /getProductDetailsInfo.do";//产品详情
    public static final String DATE_MONEY = SERVER_IOT+"app/orderCtrl/showOrderPage.do";//可供选择的日期和金额
    public static final String ORDER_FILL = SERVER_IOT+"app/orderCtrl/createNewOrder.do";//订单填写
    public static final String LOGOUT_PERSONAL = SERVER_IOT+"app/custCtrl/logOut.do";//注销登录
    public static final String ORDER_TOURISM = SERVER_IOT+"app/orderCtrl/customizeTravel.do";//定制旅游
    public static final String UPDATE_PERSONAL_INFO = SERVER_IOT+"app/myCenterCtrl/updateUserInfo.do";//上传个人资料
    public static final String UPLOAD_FILE = SERVER_IOT+"app/comCtrl/uploadFileApp.do";//上传文件或者头像
    public static final String GET_PERSONAL_INFO = SERVER_IOT+"app/myCenterCtrl/getUserInfo.do";//
    public static final String FEED_BACK = SERVER_IOT+"app/myCenterCtrl /saveSuggestionFeedback.do";//意见反馈
    public static final String ORDER_DETAIL = SERVER_IOT+"app/orderCtrl/findOrderDetailInfo.do";//订单详情
    public static final String CANCEL_ORDER = SERVER_IOT+"app/orderCtrl/concelOrder.do";//取消订单
    public static final String CHECK_OLD_PSD = SERVER_IOT+"app/myCenterCtrl/checkOldPassword.do";//验证旧密码
    public static final String UPDATE_PSD = SERVER_IOT+"app/myCenterCtrl/updateNewPassword.do";//验证旧密码
    public static final String PAY_CHOOSE = SERVER_IOT+"app/orderCtrl/findPayPageInfo.do";//支付方式
    public static final String PAY_SIGN = SERVER_IOT+"app/appPayCtrl/getOrderAliCode.do";//支付生成签名
    public static final String PAY_NOTIFY = SERVER_IOT+"app/appPayCtrl/syncNotifyByAlipay.do";//支付完成同步通知
    public static final String FEE = SERVER_IOT+"app/orderCtrl/getOrderFeeDetailInfo.do";//费用明细


    //  主题页面 亲子 里面 产品
    public static final String HOME_APRODUCT = SERVER_IOT + "app/themeCtrl/queryThemeProductList.do";
    //  查询主题页面攻略列表
    public static final String HOME_STRATEGY = SERVER_IOT + "app/themeCtrl/queryThemeExperienceList.do";
    //查询主题页面达人列表
    public static final String HOME_AMASTER = SERVER_IOT + "app/themeCtrl/queryThemeDaRenList.do";
    // 查询动态列表  主题页面 达人li
    public static final String HOME_DYNAMIC = SERVER_IOT + "app/daRenCtrl/queryDongTaiList.do";
    //查询达人列表
    public static final String HOME_QUERYDARENLIST = SERVER_IOT + "app/daRenCtrl/queryDaRenList.do";
    //获取达人过往活动照片
    public static final String HOME_DARENGUOWANG = SERVER_IOT + "app/daRenCtrl /queryDaRenActivityPhotoList.do";
    // 获取达人详情
    public static final String HOME_DAPEOPLEINFO = SERVER_IOT + "app/daRenCtrl /getDaRenDetailsInfo.do";

    public static final String PHONE_VERSION = SERVER_IOT + "app/comCtrl/checkAppUpdate.do";//


    //1（20001）注册发送短信
    public static final String SENDMESS_FORREG = SERVER_IOT + "app/custCtrl/sendMessForReg.do";//注册发送短信
    // 6（20006）注册短信验证码验证
    public static final String REGISTER_CHECK_MOBILE_CODE = SERVER_IOT + "app/custCtrl/registerCheckMobileCode.do";
    // 2（20002）注册  携带 手机号 验证码 密码
    public static final String REGISTER = SERVER_IOT + "app/custCtrl/registerSub.do";
    //4（20004）手机密码登录发送短信
    public static final String GET_PHONE_PW = SERVER_IOT + "app/custCtrl/loginSendMessForMobile.do";
    //5（20005）手机密码登录  携带手机号 动态密码
    public static final String PHONE_PW_LOGIN = SERVER_IOT + "app/custCtrl/loginForMobileLogin.do";
    //3（20003）普通登录  携带 账号 密码
    public static final String ORDINARY_LOGIN = SERVER_IOT + "app/custCtrl/login.do";
    // 7（20007）注销登录
    public static final String LOGOUT = SERVER_IOT + "app/custCtrl/logOut.do";
    // 过往活动照片
    public static final String PAST_EVENT_PHOTOS = SERVER_IOT + "app/daRenCtrl /queryDaRenActivityPhotoList.do";
    //攻略详情信息
    public static final String GET_EXPERIENCE_INFO = SERVER_IOT + "app/experienceCtrl /getExperienceInfo.do";

    //环信是否注册成功
    public static final String REGISTER_SUB_EASEMOB_HX = SERVER_IOT + "app/custCtrl/registerSubEasemob.do";
}

