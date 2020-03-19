/*
 * Created on 2005-01-05
 */
package maf.lib.calendar;

import java.util.Calendar;
import java.util.Date;

/**
 *  현재 시간을 가져 온다.
 */
public class LazyDate {
    private static LazyDate instance = new LazyDate();
    private long lastCheck = 0; //Never checked before
    private long cacheRefresh = 10*000; // 1 second
    private Date today = null;
    
    private LazyDate() {
        update();
    }

//    private LazyDate getInstance() {
//        return instance;
//    }

    private Date getNow() {
        long now = System.currentTimeMillis();
        if ((now - lastCheck) > cacheRefresh) {
            update();
        }
//        Logging.log(this.getClass(), "now:lastcheck = " +now +" : " + lastCheck);
        return today;
    }

    private void update() {
        lastCheck = System.currentTimeMillis();
        Calendar oCal = MCalendar.getCalendarInstance();
        today = oCal.getTime();
    }
    
    public static Date  now() {
        return instance.getNow();        
    }
}