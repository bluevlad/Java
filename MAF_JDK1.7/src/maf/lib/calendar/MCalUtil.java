/*
 * Created on 2005-01-10
 */
package maf.lib.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import maf.MafUtil;
import maf.lib.calendar.exception.InvalidDateStringException;

//import com.ibm.icu.util.Calendar;

/**
 *
 */
public class MCalUtil {
    private static SimpleDateFormat fomatter = new SimpleDateFormat(MCalendar.CALENDAR_KEY_FORMAT);
    /**
     * 해당달의 마지막날을 구해 옵니다.
     * @param year
     * @param month
     * @return
     */
    public static synchronized int lastDayOfMonth(int year, int month) {
        Calendar tCal = Calendar.getInstance();
        tCal.set(year,month-1,1);		// set에서 month는 0부터 시작 
        return tCal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 해당달의 다음달 구해 옵니다.
     * @param month
     * @return
     */
    public static synchronized int getAddtMonth(int month) {
        Calendar tCal = Calendar.getInstance();
        tCal.set(2004,month-1,1);		// set에서 month는 0부터 시작
        tCal.add(Calendar.MONTH,1);
        return tCal.get(Calendar.MONTH);
    }
    
    /**
     * 해당달의 이전달을 구해 옵니다.
     * @param month
     * @return
     */
    public static synchronized int getPrevMonth(int month) {
        Calendar tCal = Calendar.getInstance();
        tCal.set(2004,month-1,1);		// set에서 month는 0부터 시작
        tCal.add(Calendar.MONTH,-1);
        return tCal.get(Calendar.MONTH);
    }
    
    /**
     * 20040101을 formatString으로 
     * @param shotDate
     * @return
     */
    public static String yyyymmdd2(String yyyymmdd, String formatString ) {
        if(    yyyymmdd == null ) 
            return "" ; 

         String date = yyyymmdd.trim() ; 
         if( date.length() != 8 ) { 
            if( date.length() == 4 ) 
               date = date + "0101" ; 
            else if( date.length() == 6 ) 
               date = date + "01" ; 
            else if( date.length() > 8 ) 
               date = date.substring(0,8) ; 
            else 
               return "" ; 
         } 
        Calendar tCal = MCalendar.getCalendarInstance();
        tCal.set( Calendar.YEAR, MafUtil.parseInt(date.substring(0,4)) ) ; 
        tCal.set( Calendar.MONTH, MafUtil.parseInt(date.substring(4,6))-1 ) ; 
        tCal.set( Calendar.DAY_OF_MONTH, MafUtil.parseInt(date.substring(6)) ) ; 
        SimpleDateFormat formatter = MCalendar.getSimpleDateFormat(formatString);
        return formatter.format(tCal.getTime());
    }
    /**
     * 20040101을 2004-01-01로  
     * @param shotDate
     * @return
     */
    public static String yyyymmdd2(String yyyymmdd) {
        return yyyymmdd2(yyyymmdd, MCalendar.CALENDAR_STD_FORMAT);
        
    }
    
    public static String getShortDate(Date date){
        return fomatter.format(date);        
    }
    
    public static Date long2Date(long x) {
        return new Date(x);
    }
    
    /**
     * 현재 시간과 주어진 Date 간의 hour 차이를 구함 
     * @param d
     * @return
     */
    public  static long  betweenHour(Date d) {
        long x = System.currentTimeMillis() - d.getTime();
        x = x / (60*60*1000);
        
        return Math.abs(x);
    }
    
    /**
     * 두 Date 간의 hour 차이를 구함 
     * @param d
     * @return
     */
    public static long betweenHour(Date d1, Date d2) {
        long x = d1.getTime() - d2.getTime();
        x = x / (60*60*1000);
        return Math.abs(x);
        
    }
    

    
    public static Calendar dateString2Calendar(String dateString) throws InvalidDateStringException {
    	int nYear = 0;
    	int nMonth = 0;
    	int nDay = 0;
    	try {
	        if(dateString.length() == 10) {	//yyyy-mm-dd
		        String[] dt = dateString.split("\\-");
		        nYear= Integer.parseInt(dt[0]);
		        nMonth=Integer.parseInt(dt[1]);
		        nDay = Integer.parseInt(dt[2]);
	        } else if (dateString.length() == 8){ // yyyymmdd
	        	nYear = Integer.parseInt(dateString.substring(0,4)); 
	        	nMonth = Integer.parseInt(dateString.substring(4,6)); 
	        	nDay = Integer.parseInt(dateString.substring(6));
	        }
	        Calendar cal = MCalendar.getCalendarInstance();
	        cal.set(nYear, nMonth-1, nDay, 0, 0);
	        return cal;
    	} catch (Exception  e) {
    		throw new InvalidDateStringException(e.getMessage());
    	}
        
    }
    
    /**
     * 두일자간의 날자수를 구해준다.
     * @param cal1
     * @param cal2
     * @return
     */
    public static int dateDiff(Calendar cal1, Calendar cal2) {
    	int count = 0;
    	while ( !cal1.after ( cal2 ) )
    	{
    		count++;
    		cal1.add ( Calendar.DATE, 1 ); // 다음날로 바뀜
    	}
    	return count;

    }
    
    /**
     * 두일자간의 날자수를 구해준다.
     * @param cal1
     * @param cal2
     * @return
     */
    public static int dateDiff(Date d1, Date d2) {
    	return Math.round((d2.getTime()-d1.getTime())/(1000*60*60*24));
    }
    
    /**
     * 두일자간의 날자수를 구해준다.
     * @param cal1
     * @param cal2
     * @return
     */
    public static int dateDiff(String d1, String d2) throws InvalidDateStringException {
    	return dateDiff(dateString2Calendar(d1), dateString2Calendar(d2));
    }
}
