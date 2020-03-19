/*
 * Created on 2005-01-10
 */
package maf.lib.calendar.beans;

/**
 *  휴일 관련 정보 
 */
public class HolidayBean {
    
    String name = null;

    String date = null;

    String lunar = null;

    String dayoff = null;

    int prev = 0;

    int next = 0;
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDayoff() {
        return dayoff;
    }
    public void setDayoff(String dayoff) {
        this.dayoff = dayoff;
    }
    public String getLunar() {
        return lunar;
    }
    public void setLunar(String lunar) {
        this.lunar = lunar;
    }
    public int getNext() {
        return next;
    }
    public void setNext(int next) {
        this.next = next;
    }
    public int getPrev() {
        return prev;
    }
    public void setPrev(int prev) {
        this.prev = prev;
    }
    public String getName() {
        return name;
    }
    public void setName(String title) {
        this.name = title;
    }
    

}