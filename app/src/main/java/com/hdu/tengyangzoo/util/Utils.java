package com.hdu.tengyangzoo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String convertTime(int time){
        String beginDate=String.valueOf(time);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        String sd = sdf.format(new Date(Long.parseLong(beginDate)));
        return sd;
    }

    public static String convertPlay(int play){
        //123333
        if(play>10000){
            String pre = String.valueOf(play/10000);
            String last = String.valueOf((play/1000)%10);
            return pre+"."+last+"万";
        }
        return String.valueOf(play);
    }

    public static String convertDanmaku(int danmaku){
        if(danmaku>10000){
            String pre = String.valueOf(danmaku/10000);
            String last = String.valueOf((danmaku/1000)%10);
            return pre+"."+last+"万";
        }
        return String.valueOf(danmaku);
    }
}
