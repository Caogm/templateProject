package com.template.demo.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 从Date类型的时间中提取日期部分
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 从Date类型的时间中提取时间部分
     */
    public static Date getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 校验格式为2018-03的日志格式(可用正则)
     * @param yearsStr
     * @return
     */
    public static boolean dateCheck(String yearsStr){
        try{
            if (yearsStr.length() != 6){
                return false;
            }

            String[] arr = new String[2];
            arr[0] = yearsStr.substring(0,4);
            arr[1] = yearsStr.substring(4);
            //个数是否相等
            if (arr.length != 2){
                return false;
            }
            //是否全数字且月份合法
            int years = Integer.parseInt(arr[0]),month = Integer.parseInt(arr[1]);
            if (month < 0 || month > 12 || arr[0].length() != 4 || arr[1].length() != 2){
                return false;
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }

    /**
     * 获取当前时间年月格式 201803
     * @return
     */
    public static String getNowYearsStr(){

        return DateTimeFormatter.ofPattern("yyyyMM").format(LocalDate.now());
    }

    /**
     *
     * @param yearsStr 20190920
     * @return
     */
    public static int getLastMonth(String yearsStr){

        return  LocalDate.parse(yearsStr+"01", DateTimeFormatter.ofPattern("yyyyMMdd"))
                .with(TemporalAdjusters.lastDayOfMonth())
                .getDayOfMonth();
    }

    public static String getComleteYears(String years){

        return years+""+getLastMonth(years);
    }

    /**
     * 获取下一个月日期   201909格式
     * @param years
     * @return
     */
    public static String nextMonth(String years) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMM");

        Calendar cd = Calendar.getInstance();
        cd.setTime(df.parse(years));
        cd.add(Calendar.MONTH,1);

        return  df.format(cd.getTime());
    }

    public static String getCorDate(String years,Integer cnt){
        DateFormat df = new SimpleDateFormat("yyyyMM");
        Calendar cd = Calendar.getInstance();

        try {
            cd.setTime(df.parse(years));
            cd.add(Calendar.MONTH,cnt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  df.format(cd.getTime());
    }
}
