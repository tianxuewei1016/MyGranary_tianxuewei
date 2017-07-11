package com.mygranary_tianxuewei.travel.bean;

import java.util.List;

/**
 * 作者：田学伟 on 2017/7/11 12:57
 * QQ：93226539
 * 作用：
 */

public class ProductListBean {

    /**
     * queryProductList : [{"productId":"4","productName":"王府井1111111111","themeId":"1","themeName":"亲子","destinationAreaId":"1","areaName":"北京","imgId":"7","productUrl":"http://10.4.66.43:8080/.../1111222333.png","price":"0.00","sortDate":"2017-05-16 19:07:14","putawayDate":"2017-05-16 19:07:14","orderNum":"0"},{"productId":"3","productName":"天安门11111111","themeId":"1","themeName":"亲子","destinationAreaId":"1","areaName":"北京","imgId":"5","productUrl":"http://10.4.66.43:8080/.../1111222333.png","price":"0.00","sortDate":"2017-05-16 19:07:14","putawayDate":"2017-05-16 19:07:14","orderNum":"0"}]
     * msg : 成功
     * resultNum : 2
     */

    private String msg;
    private String resultNum;
    private List<QueryProductListBean> queryProductList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultNum() {
        return resultNum;
    }

    public void setResultNum(String resultNum) {
        this.resultNum = resultNum;
    }

    public List<QueryProductListBean> getQueryProductList() {
        return queryProductList;
    }

    public void setQueryProductList(List<QueryProductListBean> queryProductList) {
        this.queryProductList = queryProductList;
    }
}
