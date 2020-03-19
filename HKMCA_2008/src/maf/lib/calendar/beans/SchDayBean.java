/*
 * Created on 2005-01-07
 * 일정 저장을 위해 MDate를 확장한 Class
 * by 김상준 
 */
package maf.lib.calendar.beans;

import java.util.ArrayList;
import java.util.Date;

import maf.MafUtil;
import maf.lib.calendar.MCalendar;
import maf.lib.calendar.MDate;

/**
 *  
 */
public class SchDayBean extends MDate{
    private String key = null;
    private String stdDate = null;
    private boolean dayoff = false;
    private String lunarDate = null; // 20040101 형태 입니다.
    private ArrayList schedules = null;		// 일정을 가진다.
    private ArrayList holidays = null;			// 휴일 정보를 가진다.

    public SchDayBean(int year, int month, int day) {
        super.setDate(year,month,day,0,0);
        setKey();
    }

    public SchDayBean(Date date) {
        super.setDate(date);
        setKey();        
    }
    
    public SchDayBean(String dateString) {
    	 super.setDate(dateString);
         setKey();        
    }
    
    /////////////////////////////////////////////////////////////////////////////////
    
    
    private void setKey(){
        this.key = super.getDateString(MCalendar.CALENDAR_KEY_FORMAT);
        this.stdDate = super.getDateString(MCalendar.CALENDAR_STD_FORMAT);
        
        String lunar = super.getLunarDate();
        if (lunar != null ) {
            int lday = MafUtil.parseInt(lunar.substring(6));
            if ((lday == 1) || (lday == 15)) {
                this.lunarDate = lunar;
            }
        }
    }
    

    public void addSchedule(ScheduleBean osch) {
    	if(schedules == null) {
    		schedules = new ArrayList();
    	}
        schedules.add(osch);
    }

    public ArrayList getScedules() {
        return schedules;
    }
    
    public void setScedules(ArrayList scedule) {
        schedules = scedule;
    }

    public String getShortLunarDate() {
        if (lunarDate != null && lunarDate.length() > 7) {
            return Integer.parseInt(lunarDate.substring(4, 6)) + "." +Integer.parseInt( lunarDate.substring(6));
        } else {
            return lunarDate;
        }
    }

    public void setLunarDate(String lunarDate) {
        this.lunarDate = lunarDate;
    }

    public String getKey() {
        return key;
    }
    
    public String getStdDate() {
        return stdDate;
    }
    
	
	/**
	 * @return dayoff을 리턴합니다.
	 */
	public boolean isDayoff() {
		return dayoff;
	}
	/**
	 * @param dayoff 설정하려는 dayoff.
	 * 기존에 휴일이면 휴일 
	 */
	public void setDayoff(boolean dayoff) {
		this.dayoff = (this.dayoff || dayoff);
	}
	
	
	/**
	 * @return holidays을 리턴합니다.
	 */
	public ArrayList getHolidays() {
		return holidays;
	}
	
	public boolean isHoliday() {
	    if(holidays != null && holidays.size() > 0 ) {
	        return true;
	    } else {
	        return false;
	    }
	}
	/**
	 * @param holidays 설정하려는 holidays.
	 */
	public void setHolidays(ArrayList holidays) {
		this.holidays = holidays;
	}
	
	public void addHoliday(String name) {
		if(holidays == null) {
			holidays = new ArrayList();
		}

		if(! holidays.contains(name)) {
			holidays.add(name);
		}
	}
}