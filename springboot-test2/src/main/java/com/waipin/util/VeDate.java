package com.waipin.util;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
 import java.text.*;
 import java.util.Calendar;
//////@Component
 public class VeDate {
////    public static VeDate veDate;
//////    @PostConstruct
//////   public void init(){
//////       veDate=this;
//////    }
////
////    /**
////      * 获取现在时间
////      *
////      * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
////      */
////     public static Date getNowDate() {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         String dateString = formatter.format(currentTime);
////         ParsePosition pos = new ParsePosition(8);
////         Date currentTime_2 = formatter.parse(dateString, pos);
////         return currentTime_2;
////     }
////
////     /**
////      * 210   * 获取现在时间
////      * 211   *
////      * 212   * @return返回短时间格式 yyyy-MM-dd
////      * 213
////      */
////     public static Date getNowDateShort() {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////         String dateString = formatter.format(currentTime);
////         ParsePosition pos = new ParsePosition(8);
////         Date currentTime_2 = formatter.parse(dateString, pos);
////         return currentTime_2;
////     }
////
////     /**
////      * 224   * 获取现在时间
////      * 225   *
////      * 226   * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
////      * 227
////      */
////     public static String getStringDate() {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         String dateString = formatter.format(currentTime);
////         return dateString;
////     }
////
////     /**
////      * 236   * 获取现在时间
////      * 237   *
////      * 238   * @return 返回短时间字符串格式yyyy-MM-dd
////      * 239
////      */
////     public static String getStringDateShort() {
////        Date currentTime = new Date();
////        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////        String dateString = formatter.format(currentTime);
////        return dateString;
////    }
////
////     /**
////      * 248   * 获取时间 小时:分;秒 HH:mm:ss
////      * 249   *
////      * 250   * @return
////      * 251
////      */
////     public static String getTimeShort() {
////         SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
////         Date currentTime = new Date();
////         String dateString = formatter.format(currentTime);
////         return dateString;
////     }
////
////     /**
////      * 260   * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
////      * 261   *
////      * 262   * @param strDate
////      * 263   * @return
////      * 264
////      */
////     public static Date strToDateLong(String strDate) {
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         ParsePosition pos = new ParsePosition(0);
////         Date strtodate = formatter.parse(strDate, pos);
////         return strtodate;
////     }
////
////     /**
////      * 273   * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
////      * 274   *
////      * 275   * @param dateDate
////      * 276   * @return
////      * 277
////      */
////     public static String dateToStrLong(java.util.Date dateDate) {
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         String dateString = formatter.format(dateDate);
////         return dateString;
////     }
////
////     /**
////         * 将短时间格式时间转换为字符串 yyyy-MM-dd
////
////      */
////     public static String dateToStr(java.util.Date dateDate) {
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////         String dateString = formatter.format(dateDate);
////         return dateString;
////     }
////
////     /**
////      * 298   * 将短时间格式字符串转换为时间 yyyy-MM-dd
////      */
////     public static Date strToDate(String strDate) {
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////         ParsePosition pos = new ParsePosition(0);
////         Date strtodate = formatter.parse(strDate, pos);
////         return strtodate;
////     }
////
////     /**
////      * 311   * 得到现在时间
////      * 312   *
////      * 313   * @return
////      * 314
////      */
////     public static Date getNow() {
////         Date currentTime = new Date();
////         return currentTime;
////     }
////
////     /**
////      * 321   * 提取一个月中的最后一天
////      * 322   *
////      * 323   * @param day
////      * 324   * @return
////      * 325
////      */
////     public static Date getLastDate(long day) {
////         Date date = new Date();
////         long date_3_hm = date.getTime() - 3600000 * 34 * day;
////         Date date_3_hm_date = new Date(date_3_hm);
////         return date_3_hm_date;
////     }
////
////     /**
////      * 334   * 得到现在时间
////      * 335   *
////      * 336   * @return 字符串 yyyyMMdd HHmmss
////      * 337
////      */
////     public static String getStringToday() {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
////         String dateString = formatter.format(currentTime);
////         return dateString;
////     }
////
////     /**
////      * 346   * 得到现在小时
////      * 347
////      */
////     public static String getHour() {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         String dateString = formatter.format(currentTime);
////         String hour;
////         hour = dateString.substring(11, 13);
////         return hour;
////     }
////
////     /**
////      * 358   * 得到现在分钟
////      * 359   *
////      * 360   * @return
////      * 361
////      */
////     public static String getTime() {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         String dateString = formatter.format(currentTime);
////         String min;
////         min = dateString.substring(14, 16);
////         return min;
////     }
////     public static String getYear() {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         String dateString = formatter.format(currentTime);
////         String year;
////         year = dateString.substring(1, 5);
////         return year;
////     }
////     public static String getMouth() {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         String dateString = formatter.format(currentTime);
////         String mouth;
////         mouth = dateString.substring(6,8);
////         return mouth;
////     }
////
////     /**
////      * 372   * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
////      * 373   *
////      * 374   * @param sformat
////      * 375   *            yyyyMMddhhmmss
////      * 376   * @return
////      * 377
////      */
////     public static String getUserDate(String sformat) {
////         Date currentTime = new Date();
////         SimpleDateFormat formatter = new SimpleDateFormat(sformat);
////         String dateString = formatter.format(currentTime);
////         return dateString;
////     }
////
////     /**
////      * 386   * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
////      * 387
////      */
////     public static String getTwoHour(String st1, String st2) {
////         String[] kk = null;
////         String[] jj = null;
////         kk = st1.split(":");
////         jj = st2.split(":");
////         if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
////             return "0";
////         else {
////             double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
////             double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
////             if ((y - u) > 0)
////                 return y - u + "";
////             else
////                 return "0";
////         }
////     }
////
////     /**
////      * 406   * 得到二个日期间的间隔天数
////      * 407
////      */
////     public static String getTwoDay(String sj1, String sj2) {
////         SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
////         long day = 0;
////         try {
////             java.util.Date date = myFormatter.parse(sj1);
////             java.util.Date mydate = myFormatter.parse(sj2);
////             day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
////         } catch (Exception e) {
////             return "";
////         }
////         return day + "";
////     }
////
////     /**
////      * 422   * 时间前推或后推分钟,其中JJ表示分钟.
////      * 423
////      */
////     public static String getPreTime(String sj1, String jj) {
////         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         String mydate1 = "";
////         try {
////             Date date1 = format.parse(sj1);
////             long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
////             date1.setTime(Time * 1000);
////             mydate1 = format.format(date1);
////         } catch (Exception e) {
////         }
////         return mydate1;
////     }
////
////     /**
////      * 438   * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
////      * 439
////      */
////     public static String getNextDay(String nowdate, String delay) {
////         try {
////             SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
////             String mdate = "";
////             Date d = strToDate(nowdate);
////             long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
////             d.setTime(myTime * 1000);
////             mdate = format.format(d);
////             return mdate;
////         } catch (Exception e) {
////             return "";
////         }
////     }
////
////     /**
////      * 455   * 判断是否润年
////      * 456   *
////      * 457   * @param ddate
////      * 458   * @return
////      * 459
////      */
////     public static boolean isLeapYear(String ddate) {
////
////         /**
////          463    * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
////          464    * 3.能被4整除同时能被100整除则不是闰年
////          465    */
////         Date d = strToDate(ddate);
////         GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
////         gc.setTime(d);
////         int year = gc.get(Calendar.YEAR);
////         if ((year % 400) == 0)
////             return true;
////         else if ((year % 4) == 0) {
////             if ((year % 100) == 0)
////                 return false;
////             else
////                 return true;
////         } else
////             return false;
////     }
////
////     /**
////      * 482   * 返回美国时间格式 26 Apr 2006
////      * 483   *
////      * 484   * @param str
////      * 485   * @return
////      * 486
////      */
////     public static String getEDate(String str) {
////         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////         ParsePosition pos = new ParsePosition(0);
////         Date strtodate = formatter.parse(str, pos);
////         String j = strtodate.toString();
////         String[] k = j.split(" ");
////         return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
////     }
////
////     /**
////      * 497   * 获取一个月的最后一天
////      * 498   *
////      * 499   * @param dat
////      * 500   * @return
////      * 501
////      */
////     public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
////         String str = dat.substring(0, 8);
////         String month = dat.substring(5, 7);
////         int mon = Integer.parseInt(month);
////         if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
////             str += "31";
////         } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
////             str += "30";
////         } else {
////             if (isLeapYear(dat)) {
////                 str += "29";
////             } else {
////                 str += "28";
////             }
////         }
////         return str;
////     }
////
////     /**
////      * 521   * 判断二个时间是否在同一个周
////      * 522   *
////      * 523   * @param date1
////      * 524   * @param date2
////      * 525   * @return
////      * 526
////      */
////     public static boolean isSameWeekDates(Date date1, Date date2) {
////         Calendar cal1 = Calendar.getInstance();
////         Calendar cal2 = Calendar.getInstance();
////         cal1.setTime(date1);
////         cal2.setTime(date2);
////         int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
////         if (0 == subYear) {
////             if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
////                 return true;
////         } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
////             // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
////             if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
////                 return true;
////         } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
////             if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
////                 return true;
////         }
////         return false;
////     }
////
////     /**
////      * 548   * 产生周序列,即得到当前时间所在的年度是第几周
////      * 549   *
////      * 550   * @return
////      * 551
////      */
////     public static String getSeqWeek() {
////         Calendar c = Calendar.getInstance(Locale.CHINA);
////         String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
////         if (week.length() == 1)
////             week = "0" + week;
////         String year = Integer.toString(c.get(Calendar.YEAR));
////         return year + week;
////     }
////
////     /**
////      * 562   * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
////      * 563   *
////      * 564   * @param sdate
////      * 565   * @param num
////      * 566   * @return
////      * 567
////      */
////     public static String getWeek(String sdate, String num) {
////         // 再转换为时间
////         Date dd = VeDate.strToDate(sdate);
////         Calendar c = Calendar.getInstance();
////         c.setTime(dd);
////         if (num.equals("1")) // 返回星期一所在的日期
////             c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
////         else if (num.equals("2")) // 返回星期二所在的日期
////             c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
////         else if (num.equals("3")) // 返回星期三所在的日期
////             c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
////         else if (num.equals("4")) // 返回星期四所在的日期
////             c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
////         else if (num.equals("5")) // 返回星期五所在的日期
////             c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
////         else if (num.equals("6")) // 返回星期六所在的日期
////             c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
////         else if (num.equals("0")) // 返回星期日所在的日期
////             c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
////         return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
////     }
}