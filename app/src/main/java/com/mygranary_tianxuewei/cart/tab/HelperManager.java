package com.mygranary_tianxuewei.cart.tab;

import android.content.Context;

import com.mygranary_tianxuewei.cart.dao.ProductDAO;
import com.mygranary_tianxuewei.cart.db.HelperDB;

/**
 * 作者：田学伟 on 2017/7/16 22:35
 * QQ：93226539
 * 作用：
 */

public class HelperManager {
    private final HelperDB helper;
    private ProductDAO productDAO;

    /**
     * 初始化 联系人 及消息的 数据库
     *
     * @param context
     * @param name
     */
    public HelperManager(Context context, String name) {
        helper = new HelperDB(context, name + ".db");

        productDAO = new ProductDAO(helper);

    }

    /**
     * 关闭数据库
     */
    public void closeDB() {

        if (helper != null) {

            helper.close();
        }
    }

    public HelperDB getHelper() {
        return helper;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }
}

