package com.morega.batterymanager.Info;

/**
 * Created by Morega03 on 2015/6/22.
 */
public class StatusInfo {
    public static String getTime_tick() {
        return time_tick;
    }

    public static void setTime_tick(String time_tick) {
        StatusInfo.time_tick = time_tick;
    }

    public static String getHas_wrongs() {
        return has_wrongs;
    }

    public static void setHas_wrongs(String has_wrongs) {
        StatusInfo.has_wrongs = has_wrongs;
    }

    private static String has_wrongs = "yes";
    private static String time_tick;
}
