package com.syhg.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 时间格式化工具类
 */
public class DateUtils {
    public static final String STANDARD="yyyy-MM-dd HH:mm:ss";

    //将字符串类型的时间转成Date类型

    public static Date string2Date(String strDate){
        DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(STANDARD);
        return dateTimeFormatter.parseDateTime(strDate).toDate();
    }

    public static Date string2Date(String strDate,String formdate){
        DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(formdate);
        return dateTimeFormatter.parseDateTime(strDate).toDate();
    }

    //将Date类型的时间转成字符串时间

    public static String date2String(Date date){
        if(date==null){
            return null;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDARD);
    }
    public static String date2String(Date date,String formdate){
        if(date==null){
            return null;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(formdate);
    }



}
