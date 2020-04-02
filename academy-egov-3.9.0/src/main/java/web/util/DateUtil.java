/**
 * DateUtil.java  날짜관련 유틸리티
 *
 * @author Miraenet (Copyright Miraenet Co.,Ltd.)
 * @email  dev@miraenet.com
 * @version 1.0 , 2014/11/03
 */
/*
 * 사용법
 *    - static method는 DateUtil.mehtod()로 호출한다.
 *    - 기타 메소드는 객체생성후 접근한다.
 */

package web.util;

import java.util.*;
import java.text.*;

public class DateUtil {

    private static Calendar cal;

    /*
     * 년, 월, 일을 저장하는 String
     */
    private String year;
    private String syear;
    private String month;
    private String date;

    /*
     * 시, 분, 초를 저장하는 String
     */
    private String hour;
    private String minute;
    private String second;

    public DateUtil() {
        cal = Calendar.getInstance();
        init();
    }

    /*
     * initialize the fields
     */
    private void init() {
        DecimalFormat form;

        form = new DecimalFormat("0000");
        this.year = form.format(cal.get(Calendar.YEAR));
        this.syear = year.substring(2);

        form = new DecimalFormat("00");
        this.month = form.format(cal.get(Calendar.MONTH) + 1);
        this.date = form.format(cal.get(Calendar.DATE));
        this.hour = form.format(cal.get(Calendar.HOUR_OF_DAY));
        this.minute = form.format(cal.get(Calendar.MINUTE));
        this.second = form.format(cal.get(Calendar.SECOND));
    }

    /**
     * 현재날짜를 YYYY-MM-DD HH:MM:SS 형식으로 만들어 리턴 <br>
     *
     * @author Miraenet (Copyright Miraenet Co.,Ltd.)
     * @email  dev@miraenet.com
     * @version 1.0 , 2004/11/03
     */
    public static String getTime2(){
        String ch = null;
        try {
            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss");
            ch = sdf.format(d);
        } catch(Exception dfdf){}
        return ch;
    }

    /**
     * 현재날짜를 YYYY-MM-DD HH:MM:SS 형식으로 만들어 리턴 <br>
     *
     * @author : Miraenet
     * @email : dev@mirenet.com
     */
    public static String getTime(){
        String ch = null;
        try{
            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd'-'HH'-'mm'-'ss");
            ch = sdf.format(d);
        }catch(Exception dfdf){}
        return ch;
    }

    /**
     * 현재날짜를 지정된 포맷으로 만들어 리턴 <br>
     *
     * @author : Miraenet
     * @e-mail : dev@mirenet.com
     */
    public static String dateFormat(String format)
    {
        String date=null;
        try
        {
            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.format(d);
        } catch(Exception kkkk) { }
        return date;
    }

    // 오늘날짜의 년도
    public static int getYear()
    {
        java.util.SimpleTimeZone kst = new java.util.SimpleTimeZone(9*60*60*1000,"KST");
        java.util.Calendar cal = java.util.Calendar.getInstance(kst);

        int today = 0;
        today = cal.get(Calendar.YEAR);
        return today;
    }

    // 오늘날짜의 달
    public static int getMonth()
    {
        java.util.SimpleTimeZone kst = new java.util.SimpleTimeZone(9*60*60*1000,"KST");
        java.util.Calendar cal = java.util.Calendar.getInstance(kst);

        int today = 0;
        today = cal.get(Calendar.MONTH)+1;
        return today;
    }

    // 오늘일자
    public static int getDay()
    {
        java.util.SimpleTimeZone kst = new java.util.SimpleTimeZone(9*60*60*1000,"KST");
        java.util.Calendar cal = java.util.Calendar.getInstance(kst);

        int today = 0;
        today = cal.get(Calendar.DAY_OF_MONTH);
        return today;
    }

    // 오늘일자가 속한 주
    public static int getWeek()
    {
        java.util.SimpleTimeZone kst = new java.util.SimpleTimeZone(9*60*60*1000,"KST");
        java.util.Calendar cal = java.util.Calendar.getInstance(kst);

        int today = 0;
        today = cal.get(Calendar.WEEK_OF_MONTH);
        return today;
    }

    /**
     * 특정 날짜의 더해진 날짜를 구해오는 메소드
     *
     * @param curr_date ex> "YYYYMMDD"
     * @param amount 더할 날짜
     * @return toDate ex>"YYYYMMDD"
     */
    public static String getToDate(String curr_date, int amount) {
        cal.set(new Integer(curr_date.substring(0,4)).intValue(), new Integer(curr_date.substring(4,6)).intValue()-1, new Integer(curr_date.substring(6,8)).intValue());

        cal.add(Calendar.DATE, amount);

        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH) + 1;
        int nowDay = cal.get(Calendar.DATE);

        String sYear = String.valueOf(nowYear);
        String sMonth = String.valueOf(nowMonth);
        if(sMonth.length() ==1) sMonth = "0" + sMonth;
        String sDay = String.valueOf(nowDay);
        if(sDay.length() ==1) sDay = "0" + sDay;

        String toDate = sYear + sMonth + sDay;

        return toDate;
    }

    /**
     * 특정 날짜의 주를 구해오는 메소드
     *
     * @param curr_date ex> "YYYYMMDD"
     * @return toDate ex>"1~5"
     */
    public static String getToWeekOfMonth(String curr_date) {
        cal.set(new Integer(curr_date.substring(0,4)).intValue(), new Integer(curr_date.substring(4,6)).intValue()-1, new Integer(curr_date.substring(6,8)).intValue());
        int nowWeek = cal.get(Calendar.WEEK_OF_MONTH);
        return nowWeek+"";
    }

    /**
     * 특정 날짜의 요일을 구해오는 메소드
     *
     * @param curr_date ex> "YYYYMMDD"
     * @return toDate ex>"SUN~SAT"
     */
    public static String getDayOfWeek(String curr_date) {

        java.util.SimpleTimeZone kst = new java.util.SimpleTimeZone(9*60*60*1000,"KST");
        java.util.Calendar cal = java.util.Calendar.getInstance(kst);
        cal.set(new Integer(curr_date.substring(0,4)).intValue(), new Integer(curr_date.substring(4,6)).intValue()-1, new Integer(curr_date.substring(6,8)).intValue());

        int nowWeek = cal.get(Calendar.DAY_OF_WEEK);

        if(nowWeek == Calendar.SUNDAY)   return "Sun";
        else if(nowWeek == Calendar.SUNDAY)   return "Sun";
        else if(nowWeek == Calendar.MONDAY)   return "Mon";
        else if(nowWeek == Calendar.TUESDAY)   return "Tue";
        else if(nowWeek == Calendar.WEDNESDAY)   return "Wed";
        else if(nowWeek == Calendar.THURSDAY)   return "Thu";
        else if(nowWeek == Calendar.FRIDAY)   return "Fri";
        else if(nowWeek == Calendar.SATURDAY)   return "Sat";
        return "";
    }

    /**
     * 특정 날짜(YYYYMMDD)문자열을 delimeter로 구분하여 return<br>
     */
    public static String returnDate2(String dates, String delimeter)
    {

        if(dates != null && dates.length()==8) {
            if("".equals(delimeter)) delimeter="/";
            String year = dates.substring(0,4);
            String month = dates.substring(4,6);
            String day = dates.substring(6,8);

            return year + delimeter + month + delimeter + day;
        } else {
            return dates;
        }
    }

    /**
     * 특정 날짜(YYYYMMDD)문자열을 delimeter로 구분하여 년월 return<br>
     */
    public static String parseYYMM(String date, String delim){
        String parse = null;
        if(date != null && date.length() == 8){
            parse = date.substring(0, 4) + delim +  date.substring(4, 6);
        }
        return parse;
    }

    /**
     * 특정 날짜(YYYYMMDD)문자열을 delimeter로 구분하여 월일 return<br>
     */
    public static String parseMMDD(String date, String delim){
        String parse = null;
        if(date != null && date.length() == 8){
            parse = date.substring(4, 6) + delim +  date.substring(6, 8);
        }
        return parse;
    }


    /**
     * 현재 날짜(YYYY-MM-DD)를 타입에 따라 년, 월,일 만을 리턴 <br>
     *
     * @author : Miraenet
     * @email : dev@mirenet.com
     */
    public static String getDate(int type)
    {
        String ch = getDate();
        String format = null;
        switch(type){
        case 1:
            format = ch.substring(0,4);
            break;
        case 2:
            format = ch.substring(5,7);
            break;
        case 4:
            format = ch.substring(0,4)+ch.substring(5,7)+ch.substring(8,10);
            break;
        default:
            format = ch.substring(8,10);
            break;
        }
        return format;
    }


    /**
     * 현재날짜를 YYYY-MM-DD 형식으로 만들어 리턴 <br>
     *
     * @author : Miraenet
     * @e-mail : dev@mirenet.com
     */
    public static String getDate()
    {
        String ch = null;
        try
        {
            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
            ch = sdf.format(d);
        } catch(Exception dfdf) { }
        return ch;
    }

    /**
     * 현재 날짜를 YYYYMMDD로 return.
     *
     * @author :
     * @e-mail : dev@mirenet.com
     */
    public static String getDate2()
    {
        SimpleTimeZone kst = new SimpleTimeZone(9 *  60 * 60   * 1000, "KST");
        Calendar cal = Calendar.getInstance(kst);
        String y,m,d;

        y =  ""+cal.get(Calendar.YEAR);

        if((cal.get(Calendar.MONTH)+1) < 10)
            m =  "0"+(cal.get(Calendar.MONTH)+1);
        else m = ""+(cal.get(Calendar.MONTH)+1);

        if(cal.get(Calendar.DATE) <  10)
            d =  "0"+ cal.get(Calendar.DATE);
        else d = ""+cal.get(Calendar.DATE);

        return y+m+d;
    }


    /**
     * 현재날짜를 YYYYMMDD 형식으로 만들어 리턴 <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String getDate3()
    {
        String ch = null;
        try
        {
            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            ch = sdf.format(d);
        } catch(Exception dfdf) { }
        return ch;
    }

    /**
     * 현재 날짜에서n으로 이동한 날짜 YYYYMMDD로 return.<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String getDate2(int n)
    {
        SimpleTimeZone kst = new SimpleTimeZone(9 *    60 * 60     * 1000, "KST");
        Calendar cal = Calendar.getInstance(kst);
        cal.add(Calendar.DATE, n);
        String y,m,d;

        y =    ""+cal.get(Calendar.YEAR);

        if((cal.get(Calendar.MONTH)+1) < 10)
            m =    "0"+(cal.get(Calendar.MONTH)+1);
        else m = ""+(cal.get(Calendar.MONTH)+1);

        if(cal.get(Calendar.DATE) <    10)
            d =    "0"+ cal.get(Calendar.DATE);
        else d = ""+cal.get(Calendar.DATE);

        return y+m+d;
    }

    public static String getDateDepart(int n)
    {
        SimpleTimeZone kst = new SimpleTimeZone(9 * 60 * 60  * 1000, "KST");
        Calendar cal = Calendar.getInstance(kst);
        cal.add(Calendar.DATE, n);
        String y,m,d;

        y = ""+cal.get(Calendar.YEAR);

        if((cal.get(Calendar.MONTH)+1) < 10)
            m = "0"+(cal.get(Calendar.MONTH)+1);
        else m = ""+(cal.get(Calendar.MONTH)+1);

        if(cal.get(Calendar.DATE) < 10)
            d = "0"+ cal.get(Calendar.DATE);
        else d = ""+cal.get(Calendar.DATE);

        return y+m+d;
    }

    /**
     * 특정일을 셋하고 그 날짜에서 n만큼 증가한 날짜 얻기
     *
     * @author : Miraenet
     * @e-mail : dev@mirenet.com
     */
    public static String getTermDate(String sdate, int n, String delimeter)
    {
        SimpleTimeZone kst = new SimpleTimeZone(9 * 60 * 60  * 1000, "KST");
        Calendar cal = Calendar.getInstance(kst);

        if(sdate == null || sdate.equals("")) {
            return "";
        }
        Date dd = DateUtil.stringToDate(sdate, "yyyy'-'MM'-'dd");
        cal.setTime(dd);
        cal.add(Calendar.DATE, n);
        String y,m,d;

        y = ""+cal.get(Calendar.YEAR);

        if((cal.get(Calendar.MONTH)+1) < 10)
            m = "0"+(cal.get(Calendar.MONTH)+1);
        else m = ""+(cal.get(Calendar.MONTH)+1);

        if(cal.get(Calendar.DATE) < 10)
            d = "0"+ cal.get(Calendar.DATE);
        else d = ""+cal.get(Calendar.DATE);

        return y+ delimeter + m + delimeter + d;
    }

    /**
     * 입력받은 Date 오브젝트를 YYYY-MM-DD 형식의 String 으로 만들어 리턴 <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String dateToString(Date d)
    {
        String ch = null;
        try
        {
            //TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
            ch = sdf.format(d);
        } catch(Exception dfdf) { }
        return ch;
    }

    /**
     * 입력받은 Date 오브젝트를 특정한 포멧 형식의 String 으로 만들어 리턴 <br>
     *
     * 예) dateToString(new Date(), "MMM d,  yyyy")<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String dateToString(Date d, String format)
    {
        String ch = null;
        try
        {
            //TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            ch = sdf.format(d);
        } catch(Exception dfdf) { }
        return ch;
    }

    /**
     * 입력받은 String오브젝트를 특정한 포멧 형식의 Date 형으로 만들어 리턴 <br>
     *
     * 예) stringToDate("2001-06-01", "yyyy'-'MM'-'dd")<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static java.util.Date stringToDate(String d, String format)
    {
        java.util.Date ch = null;
        try
        {
            //TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            ch = sdf.parse(d);
        } catch(Exception dfdf) { }
        return ch;
    }

    public static java.util.Date dateFormat(Date d, String format)
    {
        String str = dateToString(d, format);
        return stringToDate(str, format);
    }

    public static String stringToDateString(String d, String format)
    {
        if(d == null || d.length() < 6) return "";
        if(d.length() < 7){
            d = d.substring(0,4) +"-"+ d.substring(4,6) +"-"+ d.substring(6);
        }
        return dateToString(stringToDate(d, "yyyy'-'MM'-'dd"), format);
    }

    /**
     * 현재일이  특정 기간에 속해있는지 검사하는 메소드<br>
     * : argument : 시작일(yyyy-mm-dd), 종료일(yyyy-mm-dd)
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static boolean betweenDate(String first, String second){
        boolean flag = false;
        java.util.Date start = null;
        java.util.Date end = null;
        java.util.Date current = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        try{
            start = formatter.parse(first);
            end = formatter.parse(second);
            current = formatter.parse(DateUtil.getDate());
        }catch(Exception pe){
            return false;
        }
        if((start.before(current) && end.after(current)) || start.equals(current) || end.equals(current)) {
            flag= true;
        }
        return flag;
    }

    public static String reqTime(){
        String ch = null;
        try{
            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH");
            ch = sdf.format(d);
        }catch(Exception dfdf){}
        return ch;
    }

    /******************************************************
     ** 현재일(오늘: 기준일)이 종료일이 이후(미래)에 있는지 검사**
     *   종료일 이후인지 검사
     *     --->과거  ---> 종료일  ---> 현재일(오늘)  ---> 미래--->
     ******************************************************/
    public static boolean afterDate(String second)
    {
        boolean flag = false;
        java.util.Date end = null;
        java.util.Date current = null;
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREA);
        try{
            end = df.parse(second);
            current = df.parse(DateUtil.reqTime());
        } catch(Exception pe) {
            //System.err.println("afterDate 에러입니다."+pe.getMessage());
        }
        //if(end.before(current) || end.equals(current)) {
        if(end.before(current)) {
            flag= true;
        }
        return flag;
    }

    /******************************************************
     ** 시작일이 현재일(오늘) 보다 이후(미래)에 있는지 검사**
     *  시작일 이전인지 검사
     *     --->과거 ---> 현재일(오늘) ---> 시작일 ---> 미래--->
     ******************************************************/
    public static boolean beforeDate(String first)
    {
        boolean flag = false;
        java.util.Date start = null;
        java.util.Date current = null;
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREA);
        try{
            start = df.parse(first);
            current = df.parse(DateUtil.reqTime());
        } catch(Exception pe) {
            //System.err.println("beforeDate 에러입니다."+pe.getMessage());
        }
        //if(start.after(current)  || start.equals(current)) {
        if(start.after(current)) {
            flag= true;
        }
        return flag;
    }


    /**
     * 특정 날짜를 'YYYY/MM/DD' 형식으로 return<br>
     *
     *
     * @author :
     * @e-mail : dev@mirenet.com
     *
     */
    public static String returnDate(String date) {

        if(date == null){
            return "";
        }else if(date.length() < 8){
            return date;
        }

        String year = date.substring(0,4);
        String month = date.substring(4,6);
        String day = date.substring(6,8);

        return year + "-" + month + "-" + day;
    }

    /**
     * 윤년 check Method...
     * : 올해가 윤년인지를 check하여 booelan으로 return;
     *
     * @author :
     * @e-mail : dev@mirenet.com
     *
     */
    public static boolean checkEmbolism(int year) {

        int remain = 0;
        int remain_1 = 0;
        int remain_2 = 0;

        remain = year % 4;
        remain_1 = year % 100;
        remain_2 = year % 400;

        // the ramain is 0 when year is divided by 4;
        if (remain == 0) {
            // the ramain is 0 when year is divided by 100;
            if (remain_1 == 0) {
                // the remain is 0 when year is divided by 400;
                if (remain_2 == 0) return true;
                else return false;
            } else  return true;
        }
        return false;
    }

    /**
     * 각 월의 마지막 일을 return
     * 해당 월의 마지막일을 return. 윤년 check후 해당 일을 return
     *
     * @author : miraenet
     * @e-mail :dev@miraenet.com
     *
     */
    public static int getMonthDate(int year, int month) {

        int[] dateMonth = new int[12];

        dateMonth[0] = 31;
        dateMonth[1] = 28;
        dateMonth[2] = 31;
        dateMonth[3] = 30;
        dateMonth[4] = 31;
        dateMonth[5] = 30;
        dateMonth[6] = 31;
        dateMonth[7] = 31;
        dateMonth[8] = 30;
        dateMonth[9] = 31;
        dateMonth[10] = 30;
        dateMonth[11] = 31;

        if (DateUtil.checkEmbolism(year)) dateMonth[1] = 29;

        return dateMonth[month - 1];
    }


    /**
     * 현재 년/월/일
     *
     * @author :
     * @e-mail :
     */
    public static String[] GetTodayString()
    {
        java.util.SimpleTimeZone kst = new java.util.SimpleTimeZone(9*60*60*1000,"KST");
        java.util.Calendar cal = java.util.Calendar.getInstance(kst);

        String[] today = new String[3];

        int year = cal.get(Calendar.YEAR);
        today[0] = Integer.toString(year);

        if((cal.get(Calendar.MONTH)+1) <10)
            today[1] = "0"+(cal.get(Calendar.MONTH)+1);
        else    today[1] +=  (cal.get(Calendar.MONTH)+1);

        if(cal.get(Calendar.DAY_OF_MONTH)<10)
            today[2] = "0"+cal.get(Calendar.DAY_OF_MONTH);
        else    today[2] += cal.get(Calendar.DAY_OF_MONTH);

        return today;
    }

    public static int[] GetTodayInt()
    {
        java.util.SimpleTimeZone kst = new java.util.SimpleTimeZone(9*60*60*1000,"KST");
        java.util.Calendar cal = java.util.Calendar.getInstance(kst);

        int[] today = new int[3];

        today[0] = cal.get(Calendar.YEAR);
        today[1] = cal.get(Calendar.MONTH)+1;
        today[2] = cal.get(Calendar.DAY_OF_MONTH);
        return today;
    }

    /*
     * Date : yyyymmdd -> yyyy-mm-dd
     */
    public static String makeDateString(String date) {
        if (date.length() != 8) return date;
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6,8);
    }

    /*
     * Date : yyyy-mm-dd -> yyyymmdd
     */
    public static String makeSringDate(String date) {
        if (date==null || date.length() != 10) return date;
        return date.replaceAll("-", "");
    }

    /*
   YYYY-MM-DD를 파라미터로 받아 오늘날짜(YYYY-MM-DD)와 비교하여 두 파라미터 날짜 사이에 있는지 체크
     */
    public static boolean dateStringBetween(String sday, String eday){
        if (sday==null || sday.length() != 8) return false;
        if (eday==null || eday.length() != 8) return false;

        if(MafUtil.parseLong(sday)<=MafUtil.parseLong(getDate(4)) && MafUtil.parseLong(getDate(4))<=MafUtil.parseLong(eday)){
            return true;
        }else{
            return false;
        }
    }

    /*
   YYYY-MM-DD를 파라미터로 받아 오늘날짜(YYYY-MM-DD)와 비교하여 두 파라미터 날짜 사이에 있는지 체크
     */
    public static boolean dateStringBetween(String eday){
        if (eday==null || eday.length() != 8) return false;
        if(MafUtil.parseLong(getDate(4))<=MafUtil.parseLong(eday)){
            return true;
        }else{
            return false;
        }
    }

    /*
   YYYYMMDDHHMM를 파라미터로 받아 오늘날짜(YYYY-MM-DD HH:MM)와 비교하여 두 파라미터 날짜 사이에 있는지 체크
     */
    public static boolean datetimeStringBetween(String sday, String eday){
        if (sday==null || sday.length() != 12) return false;
        if (eday==null || eday.length() != 12) return false;
        if(MafUtil.parseLong(sday)<=MafUtil.parseLong(dateFormat("yyyyMMddHHmm")) && MafUtil.parseLong(dateFormat("yyyyMMddHHmm"))<=MafUtil.parseLong(eday)){
            return true;
        }else{
            return false;
        }
    }

    /*
   YYYYMMDDHHMM를 파라미터로 받아 오늘날짜(YYYY-MM-DD HH:MM)와 비교하여 지났는지 체크
     */
    public static boolean datetimeStringBetween(String eday){
        if (eday==null || eday.length() != 12) return false;

        if(MafUtil.parseLong(dateFormat("yyyyMMddHHmm"))<=MafUtil.parseLong(eday)){
            return true;
        }else{
            return false;
        }
    }

    /*
     * Date : yyyymmddHHMM -> 유형별 리턴
     */
    public static String makeDateSubString(String date, int type) {

        String str = null;

        if (date==null || date.length() != 12) type=0;

        switch (type){
        case 1:
            str = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6,8);
            break;
        case 2:
            str = date.substring(8,10);
            break;
        case 3:
            str = date.substring(10,12);
            break;
        default:
            str = "";
            break;
        }
        return str;
    }

    /*
     * Date : yyyymmddHHMM -> yyyy-mm-dd HH:MM
     */
    public static String makeDateTimeString(String date) {
        if (date.length() != 12) return date;
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6,8) + " " + date.substring(8,10) + ":" + date.substring(10,12);
    }

    /*
    Calendar calendar = new GregorianCalendar();
    // 1. 현재 로그인 날짜의 월요일부터 금요일까지 날짜 예를 지금 2012-06-11 월요일에 것은 바로 2012-06-15 2012-06-11 금요일.
    calendar.set(Calendar.DAY_OF_WEEK, 2);
     System.out.println("로그인 날짜의 월요일: " + print(calendar.getTime()));
     // 2.금요일, 일s
    calendar.set(Calendar.DAY_OF_WEEK, 6);
    System.out.println("로그인 날짜의 금요일: " + print(calendar.getTime()));
     // 3.현재 월 첫 날
    alendar.set(Calendar.DAY_OF_MONTH, 1);
    System.out.println("현재 월 첫 날: " + print(calendar.getTime()));
    . // 4.현재 달 마지막 날
    calendar.add(Calendar.MONTH, 1);
    calendar.set(Calendar.DAY_OF_MONTH, 0);
    System.out.println("현재 달 마지막 날: " + print(calendar.getTime()));
    // 분기초
    calendar.setTime(new Date());
    int month = getQuarterInMonth(calendar.get(Calendar.MONTH), true);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    System.out.println("이 분기의 첫 날: " + print(calendar.getTime()));
    // 분기의 끝
    alendar.setTime(new Date());
    month = getQuarterInMonth(calendar.get(Calendar.MONTH), false);
    calendar.set(Calendar.MONTH, month + 1);
    calendar.set(Calendar.DAY_OF_MONTH, 0);
    System.out.println("현재 시간 분기 끝: " + print(calendar.getTime()));

    // 분기 일년 사계절, 1분기: 2월 -4 월 이사분기: 5월 -7 월 3분기: 8월 -10 월 4분기: 11월 -1 달
    private static int getQuarterInMonth(int month, boolean isQuarterStart) {
        int months[] = { 1, 4, 7, 10 };
        if (!isQuarterStart) {
            months = new int[] { 3, 6, 9, 12 };
        }
        if (month >= 2 && month <= 4)
            return months[0];
        else if (month >= 5 && month <= 7)
            return months[1];
        else if (month >= 8 && month <= 10)
            return months[2];
        else
            return months[3];
    }
     */
    /**
     * 지정한 기일 속한 주 월요일에
     * @Methods Name getMonday
     * @return Date
     */
    public Date getMonday(Date date){
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_WEEK, 2);//외국인이 는 일요일 위치 첫날 월요일에 찾다 다음날
        return cDay.getTime();
    }

    /**
     * 지정한 기일 있는 일요일
     * @Methods Name getSunday
     * @return Date
     */
    public Date getSunday(Date date){
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        if(Calendar.DAY_OF_WEEK==cDay.getFirstDayOfWeek()){ //만약 마침 일요일, 직접 복귀
            return date;
        }else{//아니, 일요일, 그리고 일주일 계산
            cDay.add(Calendar.DAY_OF_YEAR, 7);
            cDay.set(Calendar.DAY_OF_WEEK, 1);
            System.out.println(cDay.getTime());
            return cDay.getTime();
        }
    }

    /**
     * 이번 달 첫날 날짜 받다
     * @Methods Name getFirstDayOfMonth
     * @return Date
     */
    public Date getFirstDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, 1);
        //System.out.println(cDay.getTime());
        return cDay.getTime();
    }

    /**
     * 이번 달 첫날 날짜 받다
     * @Methods Name getFirstDayOfMonth
     * @return String
     */
    public static String getFirstDayOfMonth(Date date, String fmt) {
        Calendar cDay = Calendar.getInstance();
        if(null == date) date = new Date();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, 1);
        if(null == fmt || "".equals(fmt)) fmt = "yyyy'-'MM'-'dd'-'HH'-'mm'-'ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        //System.out.println(sdf.format(cDay.getTime()));
        return sdf.format(cDay.getTime());
    }

    /**
     * 이번 달 말일 날짜 받다
     * @Methods Name getLastDayOfMonth
    . * @return Date
     */
    public Date getLastDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        //System.out.println(cDay.getTime());
        return cDay.getTime();
    }

    /**
     *  이번 달 말일 날짜 받다
     * @Methods Name getLastDayOfMonth
     * @return String
     */
    public static String getLastDayOfMonth(Date date, String fmt) {
        Calendar cDay = Calendar.getInstance();
        if(null == date) date = new Date();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        if(null == fmt || "".equals(fmt)) fmt = "yyyy'-'MM'-'dd'-'HH'-'mm'-'ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        //System.out.println(sdf.format(cDay.getTime()));
        return sdf.format(cDay.getTime());
    }

    /**
     * 이번 분기의 첫날 날짜 받다
     * @Methods Name getFirstDayOfQuarter
     * @return Date
     */
    public Date getFirstDayOfQuarter(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        int curMonth = cDay.get(Calendar.MONTH);
        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){
            cDay.set(Calendar.MONTH, Calendar.JANUARY);
        }
        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){
            cDay.set(Calendar.MONTH, Calendar.APRIL);
        }
        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
            cDay.set(Calendar.MONTH, Calendar.JULY);
        }
        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
            cDay.set(Calendar.MONTH, Calendar.OCTOBER);
        }
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
        //System.out.println(cDay.getTime());
        return cDay.getTime();
    }

    /**
     * 얻은 이번 분기의 마지막 날 날짜
     * @Methods Name getLastDayOfQuarter
     * @return Date
     */
    public Date getLastDayOfQuarter(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        int curMonth = cDay.get(Calendar.MONTH);
        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){
            cDay.set(Calendar.MONTH, Calendar.MARCH);
        }
        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){
            cDay.set(Calendar.MONTH, Calendar.JUNE);
        }
        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
            cDay.set(Calendar.MONTH, Calendar.AUGUST);
        }
        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
            cDay.set(Calendar.MONTH, Calendar.DECEMBER);
        }
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        //System.out.println(cDay.getTime());
        return cDay.getTime();
    }

    //날짜 차이값 리턴
    public static long diffOfDate(String begin, String end) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        long diffDays = 0;

        try {
            Date beginDate = formatter.parse(begin);
            Date endDate = formatter.parse(end);

            long diff = endDate.getTime() - beginDate.getTime();
            diffDays = diff / (24 * 60 * 60 * 1000);
        } catch (ParseException e) { }

        return diffDays;
    }
}
