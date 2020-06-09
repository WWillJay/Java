package com.example.demo.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil extends cn.hutool.core.date.DateUtil {

    /**
     * 获取当年的第一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获取一天最早的时候 yyyy-MM-dd 00:00:00
     */
    public static Date getDayEarliest(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天最晚的时候 yyyy-MM-dd 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getDayLatest(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        // 分
        calendar.set(Calendar.MINUTE, 59);
        // 秒
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static int weekSince2018(Date date) {
        long time20180101 = 1514736000000L;
        long week = (date.getTime() - time20180101) / 86400000 / 7;
        return (int) week;
    }

    /**
     * 获取时间，时间戳精确到秒，向下取整
     */
    public static Date getDateBySecondRoundDown(Date date){
        date.setTime(date.getTime() - (date.getTime() % 1000));
        return date;
    }

    public static Long getDayLeftSecond(){
        Date day = new Date();
        Date dayLatest = getDayLatest(day);
        return (dayLatest.getTime() - (day.getTime())) / 1000;
    }

    public static Date getDateBeforeNow(Integer day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -day);
        return cal.getTime();
    }
}
