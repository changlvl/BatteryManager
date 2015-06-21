package com.morega.batterymanager.Utils;

/**
 * Created by Morega03 on 2015/6/21.
 */
public class Touchable {
    private static boolean touchable = true;

    public static boolean isTouchable() {
        return touchable;
    }

    public static void setTouchable(boolean touchable) {
        Touchable.touchable = touchable;
    }
}
