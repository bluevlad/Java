/*
 * Created on 2005-01-10
 */
package maf.lib.calendar.dao;


/**
 *
 */
public class CalendarDB {

    
    public CalendarDB(){

    }
    
//    /**
//     * 
//     * @param startDT ������ : YYYY-MM-DD
//     * @param endDT ������ : YYYY-MM-DD
//     * @return
//     * @throws EcampusException
//     */
//    public ScheduleBean[] getAcademicCal(String startDT, String endDT ) throws EcampusException{
//        MdbDriver oDb = null;
//        try{
//            oDb = Mdb.getMdbDriver();
//           return ScheduleDB.getInstance().getSchedules(oDb,"S","ACADEMIC", startDT, endDT);
//        } catch (Exception e) {
//            throw new EcampusException(e.getMessage());
//        }finally {
//            if (oDb != null)try {oDb.close();} catch (Exception ex) {}
//        }
//    }
}
