package com.morega.batterymanager.database;

/**
 * Created by Morega03 on 2015/6/22.
 */
public class SQLiteInfo {
    /**
     * 数据库属性
     */
    public static class DB {
        //数据库名称
        public static final String DB_NAME = "mydatabase.db";
        //数据库版本号
        public static final int VERSION = 1;
    }

    /**
     * 数据表属性
     */
    public static class Table {

        public static final String TABLE_NAME = "battery";
        public static final String HAS_WRONGS = "has_wrongs";
        public static final String TIME_TICK = "time_tick";

        public static final String CREATE_TABLE = "create table " + TABLE_NAME +
                "(" + HAS_WRONGS + " varchar(20)," + TIME_TICK + " varchar(20)" + ")";
    }


}
