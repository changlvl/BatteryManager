package com.morega.batterymanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Morega03 on 2015/6/22.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    /**
     *
     * @param context   上下文
     * @param name      数据库名
     * @param factory   工厂游标
     * @param version   版本号
     */
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name,  int version) {
        this(context, name, null, version);
    }
    public DataBaseHelper(Context context, String name){
        this(context, name, SQLiteInfo.DB.VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 创建TABLE
         * 运用常量进行创建 便于后期管理 具体见SQLiteInfo类
         */
        db.execSQL(SQLiteInfo.Table.CREATE_TABLE);
    }
}
