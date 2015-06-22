package com.morega.batterymanager.Info;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.morega.batterymanager.database.DataBaseHelper;
import com.morega.batterymanager.database.SQLiteInfo;

/**
 * Created by Morega03 on 2015/6/22.
 */
public class TableInfo {
    private static DataBaseHelper helper = null;
    private static String FIRST_SQL = "select * from battery";

    public TableInfo(Context context){
        helper = new DataBaseHelper(context, SQLiteInfo.DB.DB_NAME);
        SQLiteDatabase db = helper.getReadableDatabase();
    }

    public static void nowStatus(Context context){
        helper = new DataBaseHelper(context, SQLiteInfo.DB.DB_NAME);
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(FIRST_SQL, null);
            int colums = cursor.getColumnCount();

            while (cursor.moveToNext()){
                for (int i = 0;i < colums;i++){
                    String cols_name = cursor.getColumnName(i);
                    String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                    if (cols_value == null){
                        cols_value = "";
                    }
                    if (cols_name.equals("has_wrongs")){
                        StatusInfo.setHas_wrongs(cols_value);
                    }else if (cols_name.equals("time_tick")){
                        StatusInfo.setTime_tick(cols_value);
                    }
                }
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
