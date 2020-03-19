/*
 * Created on 2005-01-10

 */
package maf.lib.calendar;

import java.util.HashMap;

import maf.lib.calendar.configure.HolidaysConfig;



/**
 *  
 */
public class Holidays {
    private static Holidays instance = new Holidays(); 
    private HolidaysConfig config = null;
    
    public Holidays(){
        
    }
    
    public void setConfig(HolidaysConfig newConfig) {
    	final HolidaysConfig  xconfig = newConfig;
        config = xconfig;
        
    }

    public static synchronized Holidays getInstance() {
        return instance;
    }
    
    public HashMap getHolidays() {
        return config.getHolidays();
    }

}