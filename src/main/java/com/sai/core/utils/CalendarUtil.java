package com.sai.core.utils;

/**
 * Created by xuehan on 2017/7/1.
 */

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtil {
    /**
     * 年-月-日 时:分:秒
     */
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年-月-日
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 时-分-秒
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 是否在两个日期之间，包含起始和结束时间,如果before或者end为null则代表是无限
     *
     * @param before
     * @param end
     * @return
     */
    public static boolean isBetweenDate(Object middle, Object before, Object end) {
        return isInRange(middle, before, end, true);
    }

    /**
     * 是否在两个日期之间，不包含起始和结束时间,如果before或者end为null则代表是无限
     *
     * @param before
     * @param end
     * @return
     */
    public static boolean isInDate(Object middle, Object before, Object end) {
        return isInRange(middle, before, end, false);
    }

    /**
     * 是否在两个日期之间，不包含起始和结束时间,如果before或者end为null则代表是无限
     *
     * @param middle
     * @param before
     * @param end
     * @param hasBoundary
     *            是否包含起终边界,true包含，false不包含
     * @return
     */
    private static boolean isInRange(Object middle, Object before, Object end, boolean hasBoundary) {
        Date middleDate = convertDate(middle);
        Date beforeDate = convertDate(before);
        Date afterDate = convertDate(end);

        if (middleDate == null) {
            return false;
        }
        if (beforeDate == null && afterDate != null) {
            int value = middleDate.compareTo(afterDate);
            if (hasBoundary && value == 0) {
                return true;
            }
            if (value < 0) {
                return true;
            } else {
                return false;
            }
        }
        if (beforeDate != null && afterDate == null) {
            int value = middleDate.compareTo(beforeDate);
            if (hasBoundary && value == 0) {
                return true;
            }
            if (value > 0) {
                return true;
            } else {
                return false;
            }
        }
        if (before == null && end == null) {
            return true;
        }
        // 包含边界
        if (hasBoundary) {
            if (middleDate.compareTo(beforeDate) >= 0 && middleDate.compareTo(afterDate) <= 0)
                return true;
        } else {
            // 不包含边界
            if (middleDate.compareTo(beforeDate) > 0 && middleDate.compareTo(afterDate) < 0)
                return true;
        }

        return false;
    }

    /**
     * 比较两个日期对象，舍取毫秒
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
        Date date1 = convertDate(obj1);
        Date date2 = convertDate(obj2);
        if (date1 == null && date2 == null) {
            return true;
        }
        if (date1 == null && date2 != null) {
            return false;
        }
        if (date2 == null && date1 != null) {
            return false;
        }
        return date1.equals(date2);
    }

    public static Calendar cloneAddDayOfMonth(Calendar cal, int day) {
        Calendar clone = (Calendar) cal.clone();
        clone.add(Calendar.DAY_OF_MONTH, day);
        return clone;
    }

    public static Calendar cloneAddSecond(Calendar cal, int second) {
        Calendar clone = (Calendar) cal.clone();
        clone.add(Calendar.SECOND, second);
        return clone;
    }

    /**
     * 如果第一个date1小，则返回true，否则返回false
     */
    public static boolean firstDateSmall(Object date1Obj, Object date2Obj) {
        if (date1Obj == null && date2Obj == null) {
            return false;
        }
        Date date1 = convertDate(date1Obj);
        Date date2 = convertDate(date2Obj);
        if (date1 == null) {
            return false;
        }
        if (date2 == null) {
            return true;
        }
        return date1.compareTo(date2) < 0;
    }

    private static Date convertDate(Object obj) {
        if (obj != null) {
            if (obj instanceof Calendar) {
                Calendar cal = (Calendar) obj;
                return cal.getTime();
            }
            if (obj instanceof Date) {
                return (Date) obj;
            }
        }
        return null;
    }

    public static String long2String(long longTime, String dataFormat) {
        Date d = new Date(longTime);
        SimpleDateFormat s = new SimpleDateFormat(dataFormat);
        String str = s.format(d);
        return str;

    }

    public static String date2String(Date d, String dataFormat) {
        SimpleDateFormat s = new SimpleDateFormat(dataFormat);
        String str = s.format(d);
        return str;
    }

    /**
     * 日期转时间格式
     * @param d
     * @return
     */
    public static String dateToTimeStr(Date d) {
        SimpleDateFormat s = new SimpleDateFormat(TIME_FORMAT);
        String str = s.format(d);
        return str;
    }

    public static String date2String(Date d) {
        if (d == null) {
            return "";
        }
        SimpleDateFormat s = new SimpleDateFormat(FORMAT);
        String str = s.format(d);
        return str;
    }

    public static Date stringToDate(String dateStr) {

        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date stringToDateWithOutTime(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long yearToLong(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        return calendar.getTimeInMillis();

    }

    /**
     * 字符串转日期类型
     */
    public static Date stringToDate(String str, String dataFormat) {
        SimpleDateFormat s = new SimpleDateFormat(dataFormat);
        try {
            return s.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将日期修改为只有年月日
     */
    public static Calendar dateAdjustYMD(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis / 1000 * 1000);// 舍其ms
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);// 舍其h:m:s
        return calendar;
    }

    /**
     * 将日期修改为只有年月日
     */
    public static Calendar dateAdjustYMD(Date date) {
        return dateAdjustYMD(date.getTime());
    }

    /**
     * 比较两个日期的年月日是否相等
     */
    public static boolean isEqualYMD(Date date1, Date date2) {
        return dateAdjustYMD(date1).compareTo(dateAdjustYMD(date2)) == 0 ? true : false;
    }

    /**
     * 比较两个日期的年月日是否相等
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareYMD(Date date1, Date date2) {
        return dateAdjustYMD(date1).compareTo(dateAdjustYMD(date2));
    }

    /**
     * 将日期修改为只有年月
     */
    public static Calendar dateAdjustYM(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis / 1000 * 1000);// 舍其ms
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 0, 0, 0, 0);// 舍其h:m:s
        return calendar;
    }

    /**
     * 获取当前时间前多天或者后多少天的时间
     */
    public static Date getDateFromNow(int preOrSufDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, preOrSufDays);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取当前时间前多少小时或者后多少小时的时间
     */
    public static Date getHourFromNow(int preOrHour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, preOrHour);
        Date date = calendar.getTime();
        return date;
    }

    public static Date getMinuteFromNow(int preOrMin) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, preOrMin);
        Date date = calendar.getTime();
        return date;
    }
    /**
     * 获取now往前推或者后推interval年的时间
     */
    public static Date getPreYearFromNow(Date now, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, interval);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 时分秒转为Integer 单位秒
     */
    public static Integer timeToInteger(Time time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(time);
        return instance.get(Calendar.HOUR_OF_DAY) * 3600 + instance.get(Calendar.MINUTE) * 60 + instance.get(Calendar.SECOND);

    }

    /**
     * 时分秒转为Integer 单位秒
     */
    public static Integer timeToInteger(Date time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(time);
        return instance.get(Calendar.HOUR_OF_DAY) * 3600 + instance.get(Calendar.MINUTE) * 60 + instance.get(Calendar.SECOND);
    }

    /**
     * 时分转为Integer 单位秒
     */
    public static Integer mimAndHourToSec(Date time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(time);
        return instance.get(Calendar.HOUR_OF_DAY) * 3600 + instance.get(Calendar.MINUTE) * 60;
    }

    /**
     * 时分秒转为Integer 单位秒
     */
    public static Date secondToDate(Integer second) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(0);
        instance.set(Calendar.HOUR_OF_DAY, second / 3600);
        instance.set(Calendar.MINUTE, (second % 3600) / 60);
        instance.set(Calendar.SECOND, (second % 3600) % 60);
        return instance.getTime();

    }

    /**
     * 两个日期的时分秒是否相等
     */
    public static boolean isEqualHMS(Date date1, Date date2) {
        return timeToInteger(date1).compareTo(timeToInteger(date2)) == 0;

    }

    /**
     * 得到星期几
     */
    public static Integer dayOfWeek(Date time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(time);
        return instance.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 计算两个日期之间相差的天数
     */
    public static int daysBetween(Date date1,Date date2)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }
    /**
     * 获取指定时间，指定小时
     * @param hour=9
     * @return 2017-08-31 09:00:00
     */
    public static Date dateByHour(Date date, int hour) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

}