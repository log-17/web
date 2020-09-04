package com.example.web.util;

import java.time.Clock;
import java.util.Date;

public class GlobalDateUtil {

    private GlobalDateUtil() {
        super();
    }

    /**
     * 获取全局统一的时间
     *
     * @return
     */
    public static Date getGlobalDate() {
        return new Date();
    }

    /**
     * 获取全局统一的时间戳
     *
     * @return
     */
    public static Long getGlobalDateTimestamp() {
        return Clock.systemUTC().millis();
    }

    /**
     * 获取全局统一的时间，精确到秒
     *
     * @return
     */
    public static Date getDateToSec() {
        Date date = Date.from(Clock.systemUTC().instant());
        String dateStr = DateUtil.convertToStr(date, DateUtil.DATE_TYPE_TIMESTAMP);
        return DateUtil.parseDate(dateStr, DateUtil.DATE_TYPE_TIMESTAMP);
    }

}

