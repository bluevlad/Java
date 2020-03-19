/*
 * Created on 2005-01-10
 */
package maf.lib.calendar;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import maf.MafUtil;
import maf.base.BaseObject;

//import com.ibm.icu.util.Calendar;



/**
 *  
 */
public class MDate  extends BaseObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private Calendar cal = null;
    private int nYear = 0;
    private int nMonth = 0;
    private int nDay = 0;
    private int nHour = 0;
    private int nMinute = 0;
    private int nSec = 0;
    
    public MDate() {
    	setDate();
    }
    public MDate(int year, int month, int day) {
        setDate(year, month, day,0,0);
    }

    public MDate(int year, int month) {
        setDate(year, month, 1,0,0);
    }

//    public MDate(Timestamp ts) {
//    	setDate(ts);
//    }
//
//    public MDate(Date date) {
//    	setDate(date);
//    }
    
    public MDate(Object obj ) {
    	if(obj != null ) {
	        if(obj instanceof java.sql.Date) {
	            setDate((Date) obj);
	        } else if(obj instanceof java.util.Date) {
	            setDate((Date) obj);
	        } else  if(obj instanceof oracle.sql.DATE) {
	            oracle.sql.DATE td = (oracle.sql.DATE) obj;
	            
	            try {
//	                logger.debug( "Text : " + td.toText("YYYY-MM-DD HH24:MI",null));
	                setDate(td.dateValue());
	                this.setHour(MafUtil.parseInt(td.toText("HH24",null)));
	                this.setMinute(MafUtil.parseInt(td.toText("MI",null)));
	            } catch (Exception e) {
	                
	            }
	        } else  if(obj instanceof java.sql.Timestamp ) {
	            setDate((Timestamp) obj);
//	            logger.debug(obj + " timestatmp:" + this.nHour + ":" + this.nMinute);
	        } else if(obj instanceof java.lang.String ) {
	        	setDate((String) obj);
	        }
    	}
    }
    
    /**
     * 
     * @param dateString
     * @return
     */
    public static Date getDate(String dateString) {
        MDate md = new MDate(dateString);
        return md.getDate();
    }
    /**
     * java.sql.Date, java.util.Date, oracle.sql.DATE, java.sql.Timestamp
     * 로 부터 date를 얻는다.
     * @param ob
     * @return
     */
    public static Date getDate(Object ob) {
        MDate md = new MDate(ob);
        return md.getDate();
    }    
//    /**
//     * 
//     * @param DateString yyyy-mm-dd 형으로 들어온 값으로 MDate 생성 
//     */
//    public MDate(String dateString) {
//    	setDate(dateString);
//    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////
    public void setDate() {
        Calendar cal = MCalendar.getCalendarInstance();
        setDate(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH)+1,
                cal.get(Calendar.DAY_OF_MONTH),
                0,0
                );
    }
    public void setDate(Timestamp ts) {
        Calendar cal = MCalendar.getCalendarInstance();
        cal.setTime(ts);
        setDate(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH)+1,
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE)
                );
    }
    public void setDate(Date date ) {
        Calendar cal = MCalendar.getCalendarInstance();
        cal.setTime(date);
        setDate(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH)+1,
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE)
                );         	
    }
    public void setDate(long dlong) {
        Calendar cal = MCalendar.getCalendarInstance();
        
       cal.setTimeInMillis(dlong);
       setDate(
               cal.get(Calendar.YEAR),
               cal.get(Calendar.MONTH)+1,
               cal.get(Calendar.DAY_OF_MONTH),
               cal.get(Calendar.HOUR_OF_DAY),
               cal.get(Calendar.MINUTE)
               
               );         
    }
    public void setDate(int year, int month, int day, int hour, int minute) {
        nYear = year;
        nMonth = month;
        nDay = day;
        nHour = hour;
        nMinute = minute;
       
    }
    
    /**
     * 문자열을 파싱하여 날자로 만든다.
     * 1) 10자리 이상의 경우 (yyyy-mm-dd hh:mm)으로 인식 
     * 2) 8자리의 경우 yyyymmdd 로 인식
     * 
     * @param dateString
     */
    public void setDate(String dateString) {
    	 if(dateString == null) {
            
        } else {
	        dateString = dateString.trim();
	        if(dateString.length() >= 10) {	//yyyy-mm-dd
		        //String[] dt = dateString.split("\\-");
		        String[] dt = dateString.split("-| ");
		        if(dt != null) {
		        	if (dt.length < 2) {
				        setDate(
				                Integer.parseInt(dt[0]), 
				                Integer.parseInt(dt[1]) ,
				                Integer.parseInt(dt[2]),
				                0,0);
		        	} else {
		        		String[] st = dt[3].split(":");
		        		if (st.length > 1) {
		        			setDate(
					                Integer.parseInt(dt[0]), 
					                Integer.parseInt(dt[1]) ,
					                Integer.parseInt(dt[2]),
					                Integer.parseInt(st[0]),
					                Integer.parseInt(st[1]));
					        
		        		} else {
		        			setDate(
					                Integer.parseInt(dt[0]), 
					                Integer.parseInt(dt[1]) ,
					                Integer.parseInt(dt[2]),
					                0,0);
		        		}
		        	}
		        }
		        
	        } else if (dateString.length() == 8){ // yyyymmdd
	            setDate(  
	                    Integer.parseInt(dateString.substring(0,4)) , 
	                    Integer.parseInt(dateString.substring(4,6)) , 
	                    Integer.parseInt(dateString.substring(6)),
	                    0, 0) ; 
	        }
        }
    }
    private Calendar getCalendar() {
        Calendar cal = MCalendar.getCalendarInstance();
        cal.set(nYear, nMonth-1, nDay, nHour, nMinute);
        return cal;
    }

    
    public void setYear(int year) {
        this.nYear = year;
    }    
    public void setMonth(int month) {
        this.nMonth = month;
    }
    public void setDay(int day) {
        this.nDay = day;
    }
    

    /**
     * 시간 설정 0-23
     * @param hour
     */
    public void setHour(int hour) {
        nHour = hour;
    }
    /**
     * 분설정 0-59
     * @param minute
     */
    public void setMinute(int minute){
        nMinute = minute;
    }
    /**
     * 시간 분 설정 
     * @param hour
     * @param minute
     */
    public void setTime(int hour, int minute){
        setHour(hour);
        setMinute(minute);
    }
    public int getYear() {
        return nYear;
    }
    public int getMonth() {
        return nMonth;
    }
    public int getDay() {
        return nDay;
    }

    public int getHour() {
        return nDay;
    }    
    
    public int getMinute() {
        return nDay;
    }

    /**
     * long java.util.Date.getTime()
     * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
     *  represented by this Date object.
     * 리턴:
     * 	the number of milliseconds since January 1, 1970, 00:00:00 GMT
     * 	 represented by this date.
     */
    public long getTime() {
        //Calendar cal = getCalendar();
        return this.getDate().getTime();
        //return cal.getTime().getTime();
    }

    //////////////////////////////////////////////////////////////////////////////
    //
    //
    /**
     * 해당달의 마지막날을 구해 옵니다.
     * 
     * @param year
     * @param month
     * @return
     */
    public int getLastDayOfMonth() {
        Calendar cal = getCalendar();
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public MDate getLastDateOfMonth() {
        Calendar cal = getCalendar();
        return new MDate(this.nYear, this.nMonth, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    }
    /**
     * 월을 더함 
     * 
     * @param month
     * @return
     */
    public MDate addMonth(int offset) {
        Calendar cal = getCalendar();
        cal.add(Calendar.MONTH, offset);
        return new MDate(cal.getTime());
    }


    /**
     * 주를 더함 
     * 
     * @param month
     * @return
     */
    public MDate addWeek(int offset) {
        Calendar cal = getCalendar();
        cal.add(Calendar.WEEK_OF_YEAR, offset);
        return new MDate(cal.getTime());
    }
    
    public MDate firstDayOfWeek() {
        Calendar cal = getCalendar();
        cal.add(Calendar.DAY_OF_MONTH, 1 - cal.get(Calendar.DAY_OF_WEEK));
        return new MDate(cal.getTime());
    }
    public MDate lastDayOfWeek() {
        Calendar cal = getCalendar();
        cal.add(Calendar.DAY_OF_MONTH,  7 - cal.get(Calendar.DAY_OF_WEEK) );
        return new MDate(cal.getTime());
    }

    /**
     * 일요일 : 1
     * 월요일 : 2
     * 토요일 : 7
     * @return
     */
    public int getDayOfWeek() {
        return get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 현재일자가 이번달에서 몇번째 주인지 
     * @return
     */
    public int getWeekOfMonth() {
        return get(Calendar.WEEK_OF_MONTH);
    }
    /** 
     * 해당달에는 몇주가 있는지 구함.
     * @return
     */
    public int getWeeks() {
        Calendar cal = getCalendar();
        return cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }
    /**
     * Date를 forMatString 형으로 돌려줌
     * yyyy : 2004
     * MM : 두자리 월
     * dd : 두자리 일
     * hh : 두자리 시간 
     * mm : 두자리 분
     * @param formatString
     * @return
     */
    public String getDateString(String formatString) {
        Calendar cal = getCalendar();
        if(cal.isSet(Calendar.DAY_OF_MONTH)) {
	        SimpleDateFormat formatter = MCalendar.getSimpleDateFormat(formatString);
	        return formatter.format(cal.getTime());
        } else {
            return null;
        }
    }
    
    /**
     * 현재 날자의 Date 형을 돌려줌
     * @return
     */
    public Date getDate() {
        Calendar cal = getCalendar();
        return cal.getTime();
    }
    
    public Timestamp getTimestamp() {
        Calendar cal = getCalendar();
        
        return new Timestamp(cal.getTimeInMillis());
    }
    
    /**
     * 현재일자 Calendar의 한 필드를 리턴  
     * Ex) get(Calendar.YEAR);
     * @param field
     * @return
     */
    public int get(int field) {
        Calendar cal = getCalendar();
        return cal.get(field);
    }
    
    public String getLunarDate() {
    	return LunarCalendar.toLunar(this.nYear, this.nMonth, this.nDay);
    }
    
    public MDate getPrevMonth() {
    	Calendar cal = getCalendar();
    	cal.add(Calendar.MONTH, -1);
    	return new MDate(cal.getTime());
    }
    public MDate getNextMonth() {
    	Calendar cal = getCalendar();
    	cal.add(Calendar.MONTH, 1);
    	return new MDate(cal.getTime());
    }    

    /**
     * 
     * @param od
     * @param formatString (yyyy, MM, dd, H(0-23), m(0-59) 
     * @return
     */
    public static String getDateFormat(Date od, String formatString) {
        String st = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatString);
            st = formatter.format(od);
        } catch (Exception e) {
            st = "??" + formatString + "??";
        }
        return st; 
        
    }
    

    /**
     * 두 MDate가 같은지 확인 초단위 까지
     * @param obj
     * @return
     */
    public boolean equals(MDate obj) {
        if(this.nSec == obj.nSec &&
                this.nMinute == this.nMinute &&
                this.nHour == this.nHour &&
                this.nDay == this.nDay &&
                this.nMonth == this.nMonth &&
                this.nYear == this.nYear
                ) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 두 MDate가 같은지 확인 초단위 까지
     * @param obj
     * @return
     */
    public boolean equals(Date obj) {
        if(this.getDate().equals(obj) ) {
            return true;
        } else {
            return false;
        }
    }
    
}