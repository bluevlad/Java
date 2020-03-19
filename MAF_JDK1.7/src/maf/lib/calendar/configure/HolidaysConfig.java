/*
 * Created on 2005-01-10

 */
package maf.lib.calendar.configure;

import java.util.HashMap;

import maf.lib.calendar.beans.HolidayBean;


/**
 *  
 */
public class HolidaysConfig {
    
    private HashMap holidays = new java.util.HashMap();
    
    public HolidaysConfig(){
        
    }
    
    public void addHoliday(HolidayBean oH) {
        holidays.put(oH.getName(), oH);
        
    }
    
    public HashMap getHolidays() {
        return holidays;        
    }
    
    public int size() {
    	return holidays.size();
    }

}