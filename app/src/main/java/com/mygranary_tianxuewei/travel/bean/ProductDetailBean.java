package com.mygranary_tianxuewei.travel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：田学伟 on 2017/7/11 14:24
 * QQ：93226539
 * 作用：商品详情的bean
 */

public class ProductDetailBean {

    /**
     * advanceReserveDayNum : 2
     * areaName : 天津
     * daRenAbout : 111
     * daRenId : 1
     * daRenNickName : AAAA1
     * destinationAreaId : 2
     * imgId : 1
     * photoUrl : http://10.4.66.43:8080/.../1111222333.png
     * price : 0.00
     * productCostInclude : 111111
     * productCostIncludeNo : 111111
     * productFeatureId : 1
     * productFeatureUrl : http://10.4.66.43:8080/.../1111222333.png
     * productId : 1
     * productImgList : [{"imgId":"1","productUrl":"http://10.4.66.43:8080/.../1111222333.png"},{"imgId":"2","productUrl":"http://10.4.66.43:8080/.../1111222333.png"}]
     * productName : 北京颐和园111111
     * productNumber : A1111
     * productReserveNotice : 111111
     * productService : 新绎服务1111111
     * productTravelList : [{"travelFeatureId":"1","travelFeatureUrl":"http://.../.../1111222333.png","travelId":"1","travelNumber":"1","travelTitle":"新疆"},{"travelFeatureId":"1","travelFeatureUrl":"http://.../.../1111222333.png","travelId":"2","travelNumber":"2","travelTitle":"北京"}]
     * productUrl : http://10.4.66.43:8080/.../1111222333.png
     * resultVo : {"msg":"成功。","status":"1"}
     * themeId : 1
     * themeName : 亲子
     * wanDate :
     * zaoDate :
     */

    private String advanceReserveDayNum;
    private String areaName;
    private String daRenAbout;
    private String daRenId;
    private String daRenNickName;
    private String destinationAreaId;
    private String imgId;
    private String photoUrl;
    private String price;
    private String productCostInclude;
    private String productCostIncludeNo;
    private String productFeatureId;
    private String productFeatureUrl;
    private String productId;
    private String productName;
    private String productNumber;
    private String productReserveNotice;
    private String productService;
    private String productUrl;
    private ResultVoBean resultVo;
    private String themeId;
    private String themeName;
    private String wanDate;
    private String zaoDate;
    private List<ProductImgListBean> productImgList;
    private List<ProductTravelListBean> productTravelList;

    public String getAdvanceReserveDayNum() {
        return advanceReserveDayNum;
    }

    public void setAdvanceReserveDayNum(String advanceReserveDayNum) {
        this.advanceReserveDayNum = advanceReserveDayNum;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDaRenAbout() {
        return daRenAbout;
    }

    public void setDaRenAbout(String daRenAbout) {
        this.daRenAbout = daRenAbout;
    }

    public String getDaRenId() {
        return daRenId;
    }

    public void setDaRenId(String daRenId) {
        this.daRenId = daRenId;
    }

    public String getDaRenNickName() {
        return daRenNickName;
    }

    public void setDaRenNickName(String daRenNickName) {
        this.daRenNickName = daRenNickName;
    }

    public String getDestinationAreaId() {
        return destinationAreaId;
    }

    public void setDestinationAreaId(String destinationAreaId) {
        this.destinationAreaId = destinationAreaId;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductCostInclude() {
        return productCostInclude;
    }

    public void setProductCostInclude(String productCostInclude) {
        this.productCostInclude = productCostInclude;
    }

    public String getProductCostIncludeNo() {
        return productCostIncludeNo;
    }

    public void setProductCostIncludeNo(String productCostIncludeNo) {
        this.productCostIncludeNo = productCostIncludeNo;
    }

    public String getProductFeatureId() {
        return productFeatureId;
    }

    public void setProductFeatureId(String productFeatureId) {
        this.productFeatureId = productFeatureId;
    }

    public String getProductFeatureUrl() {
        return productFeatureUrl;
    }

    public void setProductFeatureUrl(String productFeatureUrl) {
        this.productFeatureUrl = productFeatureUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductReserveNotice() {
        return productReserveNotice;
    }

    public void setProductReserveNotice(String productReserveNotice) {
        this.productReserveNotice = productReserveNotice;
    }

    public String getProductService() {
        return productService;
    }

    public void setProductService(String productService) {
        this.productService = productService;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public ResultVoBean getResultVo() {
        return resultVo;
    }

    public void setResultVo(ResultVoBean resultVo) {
        this.resultVo = resultVo;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getWanDate() {
        return wanDate;
    }

    public void setWanDate(String wanDate) {
        this.wanDate = wanDate;
    }

    public String getZaoDate() {
        return zaoDate;
    }

    public void setZaoDate(String zaoDate) {
        this.zaoDate = zaoDate;
    }

    public List<ProductImgListBean> getProductImgList() {
        return productImgList;
    }

    public void setProductImgList(List<ProductImgListBean> productImgList) {
        this.productImgList = productImgList;
    }

    public List<ProductTravelListBean> getProductTravelList() {
        return productTravelList;
    }

    public void setProductTravelList(List<ProductTravelListBean> productTravelList) {
        this.productTravelList = productTravelList;
    }

    public static class ResultVoBean {
        /**
         * msg : 成功。
         * status : 1
         */

        private String msg;
        private String status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class ProductImgListBean implements Serializable {
        /**
         * imgId : 1
         * productUrl : http://10.4.66.43:8080/.../1111222333.png
         */

        private String imgId;
        private String productUrl;

        public String getImgId() {
            return imgId;
        }

        public void setImgId(String imgId) {
            this.imgId = imgId;
        }

        public String getProductUrl() {
            return productUrl;
        }

        public void setProductUrl(String productUrl) {
            this.productUrl = productUrl;
        }
    }

}
