package com.christmas.strawberryweibo.util;


import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {

  // Convert date format as "Tue Jun 07 13:31:42 +0800 2016" to time flies.
  public static String convertDateToTimeFlies(@NonNull String date) {
    DateFormat inputFormat = new SimpleDateFormat("dd MMM HH:mm:ss Z yyyy", Locale.US);
    inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date parsed = new Date();
    try {
      parsed = inputFormat.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return secondToTime(parsed.getTime());
  }

  /**
   * Convert MS to days, if bigger than 14 return specific time.
   *
   * @param milliSecond MS
   * @return days, if bigger than 14 return specific time.
   */
  public static String secondToTime(long milliSecond) {
    Date now = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    // get date format yyyy-MM-dd by MS.
    String date = simpleDateFormat.format(milliSecond);

    long between = now.getTime() - milliSecond;
    long day = between / DateUtils.DAY_IN_MILLIS;
    long hour = between % DateUtils.DAY_IN_MILLIS / DateUtils.HOUR_IN_MILLIS;
    long minute = between % DateUtils.HOUR_IN_MILLIS / DateUtils.MINUTE_IN_MILLIS;
    long second = between % DateUtils.SECOND_IN_MILLIS;

    String result = "";
    if (day > 14) {
      result = date;
    } else if (day <= 14 && day > 0) {
      result = String.valueOf(day) + "天前";
    } else if (hour > 0) {
      result = String.valueOf(hour) + "小时前";
    } else if (minute > 0) {
      result = String.valueOf(minute) + "分钟前";
    } else if (second > 0) {
      result = String.valueOf(second) + "秒前";
    }
    return result;
  }

  public static boolean isBlank(String string) {
    return string == null || string.trim().length() == 0;
  }
}
