/*
 * Created on 2005-01-07
 */
package maf.lib.calendar.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import maf.lib.calendar.MCalendar;
import maf.lib.calendar.MDate;

/**
 *
 */
public class SchMonthBean {
    //private ArrayList aWeeks = new ArrayList();
    private ArrayList aDays = new ArrayList();
    private TreeMap aDaysMap = new TreeMap();
    private int year = 0;
    private int month = 0;
//    private int day = 0;
    //private String today = new MDate().getDateString(MCalendar.CALENDAR_KEY_FORMAT);
    
    public String getTodayKey() {
    	Date dt = new Date();
    	return MDate.getDateFormat(dt, MCalendar.CALENDAR_KEY_FORMAT);
//        return today;
    }
//    Logger logger = Logger.getLogger(SchMonthBean.class);
    
    public SchMonthBean(int year, int month) {
        this.year = year;
        this.month = month;
        
        Calendar cal = MCalendar.getCalendarInstance();
        cal.set(year, month,1);        
    }
    
    /**
     * 날자 순으로 정리된 날들의 Map을 리턴
     * TreeMap과 SotedMap이용 
     * @return
     * @deprecated SortedMap을 이용할 경우가 있을런지?
     */
    public Map getDaysMap() {
        return  aDaysMap.tailMap(aDaysMap.firstKey());   
    }
    public ArrayList getDays() {
        return  aDays;   
    }
    
    public void addDay(SchDayBean oD) {
       aDaysMap.put(oD.getKey(), oD);  
       aDays.add(oD);
    }

    public List getWeeks() {
        int oldWeek = -1;
        int nWeek = -1;
        String key = null;

        ArrayList aWeeks = new ArrayList();
        SchWeekBean oWeeks = null;

        SimpleDateFormat fomatter = MCalendar.getSimpleDateFormat(MCalendar.CALENDAR_KEY_FORMAT);
        Calendar cal = MCalendar.getCalendarInstance();
        cal.set(year,month-1,1);

        int LastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i = 1; i <= LastDay; i++){
            cal.set(year,month-1,i);
            key = fomatter.format(cal.getTime());
            SchDayBean oD = (SchDayBean) aDaysMap.get(key);
            nWeek = cal.get(Calendar.WEEK_OF_MONTH);

            if (i == 1) {
                oWeeks = new SchWeekBean();
                oldWeek = nWeek;
            }
            if (nWeek != oldWeek) {
                oldWeek = nWeek;
                aWeeks.add(oWeeks);
                oWeeks = new SchWeekBean();
            }
            oWeeks.setDay(cal.get(Calendar.DAY_OF_WEEK), oD);
        }
        aWeeks.add(oWeeks);
        return aWeeks;
    }
    /**
     * @return month을 리턴합니다.
     */
    public int getMonth() {
        return month;
    }
    /**
     * @return year을 리턴합니다.
     */
    public int getYear() {
        return year;
    }
    
}
