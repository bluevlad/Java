/******************************************************************
 * ȭ�� �̸� : DateUtil.java
 * Date/Time ���� Util Class
 * @deprecated
 ******************************************************************/
package maf.util;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;



import maf.MafUtil;
import maf.lib.calendar.MCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils {
    private static String[] weekend = {"��","��","ȭ","��","��","��","��"};
    private Calendar cal;
    final private Log logger = LogFactory.getLog(DateUtils.class);
//  private static Logger slogger = Logger.getLogger(DateUtil.class);
    /*
     * ��, ��, ���� �����ϴ� String
     */
    private String year;
    private String syear;
    private String month;
    private String date;

    /*
     * ��, ��, �ʸ� �����ϴ� String
     */
    private String hour;
    private String minute;
    private String second;

    /*
     * �ð��� ǥ���ϴ� format
     */
    private final static String YEAR = "yyyy";
    private final static String SYEAR = "yy";
    private final static String MONTH = "mm";
    private final static String DATE = "dd";
    private final static String HOUR = "hh";
    private final static String MINUTE = "mi";
    private final static String SECOND = "ss";

    /**
     * constructor - ������ ��¥�� �ð����� object �ʱ�ȭ
     */
    public DateUtils() {
        cal = Calendar.getInstance();
        init();
    }

    /*
     * initialize the fields
     *
     */
    private void init() {
        DecimalFormat form;

        form = new DecimalFormat("0000");
        year = form.format(cal.get(Calendar.YEAR));
        syear = year.substring(2);

        form = new DecimalFormat("00");
        month = form.format(cal.get(Calendar.MONTH) + 1);
        date = form.format(cal.get(Calendar.DATE));
        hour = form.format(cal.get(Calendar.HOUR_OF_DAY));
        minute = form.format(cal.get(Calendar.MINUTE));
        second = form.format(cal.get(Calendar.SECOND));
    }

    /**
     * ���� �ð����� Calendar setting
     */
    public void setCurrentTime() {
        cal = Calendar.getInstance();
        init();
    }

    /**
     * ���� �ð��� YYYYMMDD
     * @return
     */
    public static String getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        return ""+ cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE);

    }
    
    /**
     * ���� �ð��� YYYYMMDD
     * @return
     */
    public static String getCurrentYYYY(){
        Calendar cal = Calendar.getInstance();
        return ""+ cal.get(Calendar.YEAR);

    }
    /**
     * ���� ����
     *
     * @param amount ���� ��
     */
    public void addYear(int amount) {
        cal.add(Calendar.YEAR, amount);
        init();
    }

    /**
     * �� ����
     *
     * @param amount �� ��
     */
    public void addMonth(int amount) {
        cal.add(Calendar.MONTH, amount);
        init();
    }
    public String addMonth(int amount, String format) {
        cal.add(Calendar.MONTH, amount);
        init();
        return getDate(format);
    }
    /**
     * ��¥ ����
     *
     * @param amount ��¥ ��
     */
    public void addDate(int amount) {
        cal.add(Calendar.DATE, amount);
        init();
    }

    /**
     * ���� ��¥�� �Էµ� �������� return
     *
     * @param format ex> "yyyy/mm/dd"
     * @return ex> "1999/01/29"
     */
    public String getDate(String format) {
        format = format.toLowerCase();

        int index;

        char[] buf = format.toCharArray();

        if((index = format.indexOf(YEAR)) != -1) {
            System.arraycopy(year.toCharArray(), 0, buf, index, 4);
        }
        else if((index = format.indexOf(SYEAR)) != -1) {
            System.arraycopy(syear.toCharArray(), 0, buf, index, 2);
        }

        if((index = format.indexOf(MONTH)) != -1) {
            System.arraycopy(month.toCharArray(), 0, buf, index, 2);
        }

        if((index = format.indexOf(DATE)) != -1) {
            System.arraycopy(date.toCharArray(), 0, buf, index, 2);
        }

        return new String(buf);
    }


    /**
     * �ð��� �Էµ� �������� return
     *
     * @param format ex> "hh:mi:ss"
     * @return ex> "22:23:29"
     */
    public String getTime(String format) {
        int index;
        byte[] buf = format.getBytes();

        if((index = format.indexOf(HOUR)) != -1) {
            System.arraycopy(hour.getBytes(), 0, buf, index, 2);
        }

        if((index = format.indexOf(MINUTE)) != -1) {
            System.arraycopy(minute.getBytes(), 0, buf, index, 2);
        }

        if((index = format.indexOf(SECOND)) != -1) {
            System.arraycopy(second.getBytes(), 0, buf, index, 2);
        }

        return new String(buf);
    }

    /**
     * Ư�� ��¥�� ������ ��¥�� ���ؿ��� �޼ҵ�
     *
     * @param curr_date ex> "YYYYMMDD"
     * @param amount ���� ��¥
     * @return toDate ex>"YYYYMMDD"
     */
    public String getToDate(String curr_date, int amount) {
        cal.set(MafUtil.parseInt(curr_date.substring(0,4)), MafUtil.parseInt(curr_date.substring(4,6))-1, new Integer(curr_date.substring(6,8)).intValue());

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

    public void setDate(int Year, int Month, int Day) {
        cal.set(Year, Month, Day);
        init();
    }


    /**
     * �ش� ���� �Ͽ��Ϻ��� ����ϱ����� ��¥�� ����
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public String[] getWeek(int year, int month, int date) {
        Format format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar g = new GregorianCalendar(year, month-1 , date);
        g.add(Calendar.HOUR,1);
        int dayOfWeek =  g.get(Calendar.DAY_OF_WEEK);
        String[] week = new String[7];
        // dayOfWeek : sunday = 1, Saturday = 7;
        week[0] = getFullDate(g, 1-dayOfWeek, format);
        week[1] = getFullDate(g, 1, format);
        week[2] = getFullDate(g, 1, format);
        week[3] = getFullDate(g, 1, format);
        week[4] = getFullDate(g, 1, format);
        week[5] = getFullDate(g, 1, format);
        week[6] = getFullDate(g, 1, format);
        logger.debug("CurDate = " + getFullDate(g,0,format) + " getWeek : " + week[0] + "," + week[6]);

        return week;
    }
    /**
     * dayOfWeek : 1=��, 7:�� ;
     * @param dayOfWeek
     * @return
     */
    public static String getWeekDay_KR(int dayOfWeek) {
//      String[] weekend = {"��","��","ȭ","��","��","��","��"};
        if( dayOfWeek < 1 || dayOfWeek > 7) return null;
        return weekend[dayOfWeek-1];
    }

    private String getFullDate(Calendar g, int i, Format format) {
        g.add(Calendar.DATE, i);

        return format.format(g.getTime());
    }

    public static String getFormatDate(Calendar g, String formatStr ) {
        Format format = new SimpleDateFormat(formatStr);
        return format.format(g.getTime());
    }

    public static String getWeek(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return weekend[cal.get(Calendar.DAY_OF_WEEK)];
    }

    /**
     * ���糯¥�� ������ �������� ����� ���� <br>
     *
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

    /**
     *  �ΰ� YYYYMMDD�� �Ķ���ͷ� �޾� ���ó�¥(YYYY-MM-DD)�� ���Ͽ� �� �Ķ���� ��¥ ���̿� �ִ��� üũ
     */
    public boolean dateStringBetween(String sday, String eday){
        if (sday==null || sday.length() != 8) return false;
        if (eday==null || eday.length() != 8) return false;

        if(MafUtil.parseLong(sday)<=MafUtil.parseLong(getDate(4)) && MafUtil.parseLong(getDate(4))<=MafUtil.parseLong(eday)){
            return true;
        }else{
            return false;
        }
    }

    /**
     *  YYYYMMDD�� �Ķ���ͷ� �޾� ���ó�¥(YYYY-MM-DD)�� ���Ͽ� �Ķ���� ��¥ ���̿� �ִ��� üũ
     */
    public boolean dateStringBetween(String eday){
        if (eday==null || eday.length() != 8) return false;

        if(MafUtil.parseLong(getDate(4))<=MafUtil.parseLong(eday)){
            return true;
        }else{
            return false;
        }
    }

    /**
     *  �ΰ� YYYYMMDDHHMM�� �Ķ���ͷ� �޾� ���ó�¥(YYYY-MM-DD HH:MM)�� ���Ͽ� �� �Ķ���� ��¥ ���̿� �ִ��� üũ
     */
    public static boolean datetimeStringBetweens(String sday, String eday){
        if (sday==null || sday.length() != 12) return false;
        if (eday==null || eday.length() != 12) return false;

        //if(MafUtil.parseLong(sday)<=MafUtil.parseLong(getFormatDate(cal, "yyyymmddhhmi")) && MafUtil.parseLong(getFormatDate(cal, "yyyymmddhhmi"))<=MafUtil.parseLong(eday)){
        //jstl EL ������ static �Լ��� ����Ǿ ������
        if(MafUtil.parseLong(sday)<=MafUtil.parseLong(dateFormat("yyyyMMddHHmm")) && MafUtil.parseLong(dateFormat("yyyyMMddHHmm"))<=MafUtil.parseLong(eday)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * YYYYMMDDHHMM�� �Ķ���ͷ� �޾� ���ó�¥(YYYY-MM-DD HH:MM)�� ���Ͽ� �������� üũ
     */
    public static boolean datetimeStringBetween(String eday){
        if (eday==null || eday.length() != 12) return false;

        //if(MafUtil.parseLong(getFormatDate(cal, "yyyymmddhhmi"))<=MafUtil.parseLong(eday)){
        //jstl EL ������ static �Լ��� ����Ǿ ������
        if(MafUtil.parseLong(dateFormat("yyyyMMddHHmm"))<=MafUtil.parseLong(eday)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * ���� ��¥�� Ÿ�Կ� ���� ��, ��,�� ���� ���� <br>
     *
     * @author :
     * @e-mail :
     */
    public String getDate(int type) {
        String format = null;
        switch(type){
        case 1:
            format = "" + cal.get(Calendar.YEAR);
            break;
        case 2:
            format = "" +  cal.get(Calendar.MONTH);
            break;
        case 4:
            format = getCurrentDate();
            break;
        default:
            format = "" + cal.get(Calendar.DATE);
        break;
        }
        return format;
    }
    /**
     * YYYYMMDD�� ������� ù���� ���� �ش�.
     * @return
     */
    public static String getFirstDateOfMonth(){
    	StringBuffer dt = new StringBuffer(10);
    	Calendar cal = MCalendar.getCalendarInstance();
    	
    	dt.append(cal.get(Calendar.YEAR));
    	dt.append(StringUtils.lpad(cal.get(Calendar.MONTH)+1,2,"0"));
    	dt.append("01");
    	return dt.toString();
    }
    /**
     * YYYYMMDD�� ������� ���������� ���� �ش�.
     * @return
     */
    public static String getLastDateOfMonth(){
    	StringBuffer dt = new StringBuffer(10);
    	Calendar cal = MCalendar.getCalendarInstance();
    	
    	dt.append(cal.get(Calendar.YEAR));
    	dt.append(StringUtils.lpad(cal.get(Calendar.MONTH)+1,2,"0"));
    	dt.append(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    	return dt.toString();
    }
}
