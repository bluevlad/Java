/*
 * Created on 2005-01-07
 */
package maf.lib.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import maf.lib.calendar.beans.HolidayBean;
import maf.lib.calendar.beans.SchDayBean;
import maf.lib.calendar.beans.SchMonthBean;
import maf.lib.calendar.beans.ScheduleBean;
import maf.lib.calendar.exception.MCalendarException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.ibm.icu.util.Calendar;

/**
 *  
 */
public class MCalendar {
    public static final String CALENDAR_KEY_FORMAT = "yyyyMMdd";
    public static final String CALENDAR_MONTH_KEY_FORMAT = "yyyyMM";
    public static final String CALENDAR_STD_FORMAT = "yyyy-MM-dd";
    public static final String CALENDAR_FULL_FORMAT = "yyyy-MM-dd HH:mm";
    
    private static MCalendar _instance = null;
    
    private Log logger = LogFactory.getLog(getClass());

    private HashMap aDays = new HashMap();
    private TreeMap aMonths = new TreeMap();
    

    private MCalendar() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        this.chknMakeYear(year);

    }

    public static  MCalendar getInstance() {
    	if(_instance == null) {
    		_instance = new MCalendar();
    	}
        return _instance;
    }

    public SchMonthBean getMonth(){
        Calendar cal = getCalendarInstance();
        cal.setTime(new Date());
      return getMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
    }
    /**
     * 해당 년월의 달력을 돌려 준다.
     * 
     * @param year
     * @param month
     * @return
     */
    public SchMonthBean getMonth(int year, int month) {
        Calendar cal = getCalendarInstance();
        SimpleDateFormat fomatter = getSimpleDateFormat(CALENDAR_MONTH_KEY_FORMAT);
        cal.set(year, month -1, 1);
        String mKey = fomatter.format(cal.getTime());
        SchMonthBean oMonth = null;
        
        // 해당월 달력 instance가 존재 하면 바로 리턴 
        if(aMonths.containsKey(mKey)) {
            oMonth =  (SchMonthBean) aMonths.get(mKey);
        } else {
        	this.chknMakeYear(year);
            
            oMonth = new SchMonthBean(year, month);
            fomatter.applyPattern(CALENDAR_KEY_FORMAT);
            String key = null;
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            
	        for (int i = 1; i <= lastDay ; i++) {
	            cal.set(year, month - 1, i);
	            key = fomatter.format(cal.getTime());
	            SchDayBean oday = null;
	            if (aDays.containsKey(key)) {
	                oday = (SchDayBean)aDays.get(key);
	            } else {
	                oday = new SchDayBean(key);
	            }
	            oMonth.addDay(oday);
	        }
	        aMonths.put(mKey,oMonth);
        }
        return oMonth;
    }

    /**
     * 해당년도의 일력을 만든다.
     * 
     * @param year
     * @return
     */

    private synchronized void _makeMYear(int year) {
        
        System.out.println(">>>> " + year + " Calendat Make Start!!!" );
        try{
            mergeHolidays(year);
        } catch (Exception e) {
            
        }
        System.out.println("<<<< " + year + " Caledar Make finished !!!, Size of DayPool size is "  + aDays.size());
    }

    /**
     * 공휴일 과 달력 정보를 Merge 한다.
     * @param year
     * @throws Exception
     */
    private void mergeHolidays(int year) throws Exception {
        try {
            HashMap ah = Holidays.getInstance().getHolidays();
            //HashMap ah = ((HolidaysConfig) Config.getInstance().get("HOLIDAY")).getHolidays();
            Set set = ah.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
            	Map.Entry e = (Map.Entry) i.next();
                HolidayBean h = (HolidayBean)e.getValue();
                mergeHoliday(year, h);
            }
        } catch (Exception e) {
            throw new MCalendarException(e.getMessage(), e);
        }
    }
    
    /**
     * 년도에 공유일 정보를 합친다.
     * @param h
     */
    private void mergeHoliday(int year, HolidayBean h ) {
        Calendar cal = getCalendarInstance();

        SimpleDateFormat fomatter = getSimpleDateFormat("yyyyMMdd");
        int prev = h.getPrev();
        int next = h.getNext();

        String key = null;
        String sYear = year + "";

        boolean isLunar = false;
        String dateString = h.getDate();
        SchDayBean sch = null;
        
        if (dateString.length() == 4) {  // 월일의 경우 매년 반복 
        	dateString = sYear + dateString;
        }

        isLunar = "T".equals(h.getLunar())?true: false;
        if(isLunar) {
        	dateString = LunarCalendar.fromLunar(dateString);
        }
        MDate dt = new MDate(dateString);
        cal.setTime(dt.getDate());
        cal.add(Calendar.DAY_OF_MONTH, -prev);
        for(int i = 0; i <= (next+prev); i ++) {
        	key = fomatter.format(cal.getTime());
        	
        	if(key.startsWith(sYear)) {
        	    if(aDays.containsKey(key) ) {
        	        sch = (SchDayBean) aDays.get(key);
        	    } else {
        	        sch = new SchDayBean(key);
        	    }
        		sch.setDayoff("T".equals(h.getDayoff())?true:false);
        		if(dateString.equals(key)) {
        			sch.addHoliday(h.getName());
        		}
        		aDays.put(key, sch);
        	}
        	cal.add(Calendar.DAY_OF_MONTH, 1);
        }

    }
    
    /**
     * Local화된 Calendar 오브젝트 가져 온다.
     * 
     * @return
     */
    public static Calendar getCalendarInstance() {
        Calendar oCal = Calendar.getInstance();
        
        //oCal.setTimeZone(TimeZone.getTimeZone("KST"));
        //oCal.setFirstDayOfWeek(Calendar.SUNDAY);
        return oCal;
    }
    
    /**
     * Local화된  SimpleDateFormat 오브젝트 가져 온다.
     * 
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat() {
        return getSimpleDateFormat(null);
    }
    public static SimpleDateFormat getSimpleDateFormat(String formatString) {
        SimpleDateFormat fomatter = new SimpleDateFormat();
        //fomatter.setTimeZone(TimeZone.getTimeZone("KST"));
        fomatter.applyPattern(formatString);
        return fomatter;
    }
    
    public  void mergeSchedule(List schedules) {
        ScheduleBean bean = null;
        for(int i  = 0; i< schedules.size(); i ++ ) {
            bean = (ScheduleBean) schedules.get(i);
            if (bean.getCenddate() != null ) {
                
            } else {
                String key = MDate.getDateFormat(bean.getCstartdate(),CALENDAR_KEY_FORMAT);
                if(aDays.containsKey(key)) {
                    SchDayBean t = (SchDayBean) aDays.get(key);
                    t.addSchedule(bean);
                    aDays.put(key, t);
                }
            }
        }
    }
    
    /**
     * 해당년도 달력이 있는지 확인 없으면 만듬 
     * @param cal
     */
    private void chknMakeYear(int year) {
    	String t = year + "0101";
    	if(!aDays.containsKey(t)) {
    		_makeMYear(year);
    	}
    	
    }
    


}