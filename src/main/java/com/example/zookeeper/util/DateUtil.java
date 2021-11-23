package com.example.zookeeper.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    private static final String BASE_DATE_FORMAT = "yyyy-MM-dd";

    public static Date convertDateFromStr(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(BASE_DATE_FORMAT);
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            LOGGER.warn("字符串转时间对象失败, dateStr:{}", dateStr);
            return null;
        }
    }

    public static String convertStrFromDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(BASE_DATE_FORMAT);
        return dateFormat.format(date);
    }

}
