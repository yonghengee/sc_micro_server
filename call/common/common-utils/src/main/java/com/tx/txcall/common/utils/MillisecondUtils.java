package com.tx.txcall.common.utils;/**
 * Created by wyh in 2019/6/6 14:26
 **/

/**
 * @program:
 * @description: 毫秒(int)转时:分:秒
 * @author: forever-wang
 * @create: 2019-06-06 14:26
 **/
public class MillisecondUtils {

    private static String getString(int t) {
        String m = "";
        if (t > 0) {
            if (t < 10) {
                m = "0" + t;
            } else {
                m = t + "";
            }
        } else {
            m = "00";
        }
        return m;
    }

    /**
     * @param t 秒
     * @return
     * @author
     */
    public static String format(int t) {
        if (t < 60) {
            return (t % 60)  + "秒";
        } else if ((t >= 60) && (t < 3600)) {
            return getString((t % 3600) / 60) + ":" + getString((t % 60) );
        } else {
            return getString(t / 3600) + ":" + getString((t % 3600) / 60) + ":" +
                   getString((t % 60) );
        }
    }

    public static void main(String[] args) {
        System.out.println(format(23001));
    }


}
