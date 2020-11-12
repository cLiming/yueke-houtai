package com.yuekehoutai.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 当天凌晨时间戳
     * @return
     */
    public static Timestamp getStartTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        Timestamp timestamp = new Timestamp(start.getTime());
        return timestamp;
    }
    /**
     * 第二天凌晨时间戳
     * @return
     */
    public static Timestamp getEndTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date start = calendar.getTime();
        Timestamp timestamp = new Timestamp(start.getTime());
        return timestamp;
    }

}
