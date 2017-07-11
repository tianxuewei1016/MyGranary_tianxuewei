package com.mygranary_tianxuewei.travel.bean;

import java.util.List;

/**
 * 作者：田学伟 on 2017/7/11 11:51
 * QQ：93226539
 * 作用：
 */

public class ProductType {

    /**
     * status : 1
     * xyTypeDictionarys : [{"itemCode":"1","itemName":"跟团游"},{"itemCode":"2","itemName":"周边游"}]
     * msg :
     */

    private String status;
    private String msg;
    private List<XyTypeDictionarysBean> xyTypeDictionarys;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<XyTypeDictionarysBean> getXyTypeDictionarys() {
        return xyTypeDictionarys;
    }

    public void setXyTypeDictionarys(List<XyTypeDictionarysBean> xyTypeDictionarys) {
        this.xyTypeDictionarys = xyTypeDictionarys;
    }
}

