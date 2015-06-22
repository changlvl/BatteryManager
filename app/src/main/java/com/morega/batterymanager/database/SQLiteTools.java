package com.morega.batterymanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Morega03 on 2015/6/22.
 */
public class SQLiteTools {
    public static void updataTable(Context context,Object[] parms){
        DataBaseHelper helper = null;
        SQLiteDatabase database = null;
        try {
            helper = new DataBaseHelper(context, SQLiteInfo.DB.DB_NAME);
            String sql = "update battery set has_wrongs = ?,time_tick = ?";
            database = helper.getWritableDatabase();
            database.execSQL(sql,parms);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (database!=null){
                database.close();
            }
        }
    }
}
