package com.example.web.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DateUtil extends DateUtils {

    public static final String DATE_TYPE_YYYYMMDD = "yyyyMMdd";

    public static final String DATE_TYPE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String DATE_TYPE_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TYPE_TIMESTAMP_NO_MINUS = "yyyyMMdd HH:mm:ss";

    public static final String DATE_TYPE_YYMMDDHH = "yyMMddHH";

    public static final String DATE_TYPE_YYMMDDHHMMSS = "yyMMddHHmmss";

    public static final String DATE_TYPE_YYYYMM = "yyyyMM";

    public static final String DATE_TYPE_HHMMSS = "HHmmss";

    public static final String DATE_TYPE_YYYY = "yyyy";

    public static final String DATE_TYPE_MM = "MM";

    public static final String DATE_TYPE_DD = "dd";

    public static final String DATE_TYPE_YYYYMMDD_HHMMSS = "yyyyMMdd HH:mm:ss";

    public static final String DATE_TYPE_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DATE_TYPE_HH_MM_SS = "HH:mm:ss";

    public static final String EXPIRE_DATE = "2099-12-31 23:59:59";

    public static final String DATE_TYPE_DAY_SRART = " 00:00:00";

    public static final String DATE_TYPE_DAY_END = " 23:59:59";

    public static final String DATE_TYPE_DAY_END_HHMMSS = "235959";

    public static Long toDate(Long milliseconds, int days) {
        Long dayToMinutes = Long.valueOf(TimeUnit.MILLISECONDS.convert((long) days, TimeUnit.DAYS));
        return Long.valueOf(milliseconds.longValue() - dayToMinutes.longValue());
    }

    public static Date toDate(String dateStr, String pattern) {
        if (dateStr == null) {
            return null;
        } else {
            SimpleDateFormat sd = new SimpleDateFormat(pattern);
            try {
                return sd.parse(dateStr);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public static String convertToStr(Long milliseconds) {
        Date date = new Date(milliseconds.longValue());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sd.format(date);
    }

    public static String convertToStr(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        return sd.format(date);
    }

    public static String convertToStr(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            return sd.format(date);
        }
    }

    public static String convertToStr(String date, String dateType, String targetType) {
        if ( !StringUtil.isBlank(date) && !StringUtil.isBlank(dateType) && !StringUtil.isBlank(targetType)) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateType);
            try {
                Date e = sdf.parse(date);
                SimpleDateFormat sd = new SimpleDateFormat(targetType);
                return sd.format(e);
            } catch (ParseException arg5) {
                throw new RuntimeException(arg5);
            }
        } else {
            return null;
        }
    }

    public static Date parseDate(String dateStr, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        if (StringUtil.isBlank(dateStr)) {
            return null;
        } else {
            try {
                return sd.parse(dateStr);
            } catch (ParseException arg3) {
                return null;
            }
        }
    }

    /**
     * 获取传入日期的前一天
     *
     * @param date
     * @return
     */
    public static Date parsePreDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取传入日期增加几分钟的日期
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date getAddMinuteDate(Date date, Integer minute) {
        if (minute == null) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取昨天开始日期
     *
     * @return
     */
    public static Date getYTDStart() {
        Date date = getYesterdayDate();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    /**
     * 获取昨天结束日期
     *
     * @return
     */
    public static Date getYTDEnd() {
        Date date = getYesterdayDate();
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }

    /**
     * 获取昨天日期
     *
     * @return
     */
    public static Date getYesterdayDate() {
        Date date = GlobalDateUtil.getGlobalDate();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        return date;
    }
    /**
     * 获取昨天开始日期
     *
     * @return
     */
    public static Date getWeekStart() {
        Date date = getWeekDate();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    /**
     * 获取昨天结束日期
     *
     * @return
     */
    public static Date getWeekEnd() {
        Date date = getWeekDate();
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }

    /**
     * 获取一周前日期
     *
     * @return
     */
    public static Date getWeekDate() {
        Date date = GlobalDateUtil.getGlobalDate();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取一天中起始时间点
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        if (date == null) {
            return null;
        }
        String dateStr = convertToStr(date, DATE_TYPE_YYYYMMDD) + DATE_TYPE_DAY_SRART;
        return parseDate(dateStr, DATE_TYPE_YYYYMMDD_HHMMSS);
    }

    /**
     * 获取一天中最后时间点
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        if (date == null) {
            return null;
        }
        String dateStr = convertToStr(date, DATE_TYPE_YYYYMMDD) + DATE_TYPE_DAY_END;
        return parseDate(dateStr, DATE_TYPE_YYYYMMDD_HHMMSS);
    }

    /**
     * 获取一个月的第一天
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date getMonthBegin(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return parseDate(convertToStr(c.getTime(), pattern), pattern);
    }

    /**
     * 获取一个月的最后一天
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date getMonthEnd(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return parseDate(convertToStr(c.getTime(), pattern), pattern);
    }

    /**
     * 得到几天前或几天后的时间
     *
     * @param d
     * @param day
     *            正数：后几天；负数：前几天
     * @return
     */
    public static Date getDateBeforeOrAfter(Date d, Integer day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到几个月前或几个月后的时间
     *
     * @param d
     * @param month
     *            正数：后几个月；负数：前几个月
     * @return
     */
    public static Date getMonthBeforeOrAfter(Date d, Integer month) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + month);
        return now.getTime();
    }

    /**
     * 得到几年前或几年后的时间
     *
     * @param d
     * @param year
     *            正数：后几年；负数：前几年
     * @return
     */
    public static Date getYearBeforeOrAfter(Date d, Integer year) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) + year);
        return now.getTime();
    }

    /**
     * 校验字符串是否是指定日期格式
     *
     * @param str
     * @param format
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        if (StringUtil.isBlank(str)) {
            return false;
        }

        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写 "yyyy/MM/dd HH:mm"；
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            sdf.setLenient(false);
            sdf.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

}