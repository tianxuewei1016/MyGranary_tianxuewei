package com.mygranary_tianxuewei.cart.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mygranary_tianxuewei.cart.tab.ProductTable;

/**
 * 作者：田学伟 on 2017/7/16 22:35
 * QQ：93226539
 * 作用：
 */

public class HelperDB extends SQLiteOpenHelper {
    /**
     * 注意 name后边加.db
     * @param context
     * @param name
     */
    public HelperDB(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /**
         * 创建 商品的数据库
         */
        sqLiteDatabase.execSQL(ProductTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

