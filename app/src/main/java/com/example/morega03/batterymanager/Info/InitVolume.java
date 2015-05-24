package com.example.morega03.batterymanager.Info;

/**
 * Created by Morega03 on 2015/5/23.
 * 设置电池容量的初始值，以及电池容量的测定值的存放
 */
public class InitVolume {
    public static int getVolume() {
        return volume;
    }

    public static void setVolume(int volume) {
        InitVolume.volume = volume;
    }

    public static int getInitVolume() {
        return initVolume;
    }

    public static void setInitVolume(int initVolume) {
        InitVolume.initVolume = initVolume;
    }

    public static int initVolume;
    public static int volume;
}
