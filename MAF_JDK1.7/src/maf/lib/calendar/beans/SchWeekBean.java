/*
 * Created on 2005-01-07
 */
package maf.lib.calendar.beans;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;


/**
 *
 */
public class SchWeekBean {
    
    private TreeMap weekDays = new TreeMap();
    
    public SchWeekBean(){
        weekDays.put(new Integer(Calendar.SUNDAY), null);
        weekDays.put(new Integer(Calendar.MONDAY), null);
        weekDays.put(new Integer(Calendar.TUESDAY), null);
        weekDays.put(new Integer(Calendar.WEDNESDAY), null);
        weekDays.put(new Integer(Calendar.THURSDAY), null);
        weekDays.put(new Integer(Calendar.FRIDAY), null);
        weekDays.put(new Integer(Calendar.SATURDAY), null);
    }
    
    public SchDayBean[] getDays() {
        SchDayBean[] at = {
                (SchDayBean) weekDays.get(new Integer(Calendar.SUNDAY)),
                (SchDayBean) weekDays.get(new Integer(Calendar.MONDAY)),
                (SchDayBean) weekDays.get(new Integer(Calendar.TUESDAY)),
                (SchDayBean) weekDays.get(new Integer(Calendar.WEDNESDAY)),
                (SchDayBean) weekDays.get(new Integer(Calendar.THURSDAY)),
                (SchDayBean) weekDays.get(new Integer(Calendar.FRIDAY)),
                (SchDayBean) weekDays.get(new Integer(Calendar.SATURDAY)) };
        return at;
    }
    public Map getDaysmap() {
        return weekDays.tailMap(weekDays.firstKey());
    }
    
    public void setDay(int DayOfWeek, SchDayBean oDay) {
        weekDays.put(new Integer(DayOfWeek), oDay);
    }

}
