/*
 * Selecter.java
 * 
 * 데이터 베이스에서 검색한 결과값을 특정한 Bean에 저장하여 배열로 리턴하는 클래스 리플렉션 API를 사용하여 자바빈의 구조를 조사하고,
 * ResultSetMetaData로 데이터베이스의 데이터 타입을 파악한다.
 * 
 * 제약 사항 - JDBC 2.0 API를 반드시 지원해 함 - 리턴되는 컬럼명 및 데이터 타입과 동일한 FIELD가 Bean 에 선언되어
 * 있고 setXxx() 메소드가 존재하여야 함
 * 
 * 사용방법 > Selecter s = new Selecter(DB_SID[0], "com.miraenet.beans.AdmLecBean",
 * true); > s.pageConfig(1, 10); // 페이지로 나누어서 특정한 페이지의 결과값만 리턴할때 사용 > Collection
 * col = s.execute(sql, Selecter.NULL);
 * 
 * Collection 에서 빈 배열 변화 방법 ((AdmAmtBean[])col.toArray(new AdmAmtBean[0]))[0];
 * 
 *  
 */
package maf.mdb.drivers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.lib.calendar.MDate;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MdbSelecter {

    private Object obj;

    private Class cls;

    private static Log logger = LogFactory.getLog(MdbSelecter.class);

    public MdbSelecter(Class cls)  throws MdbException{
        this.cls = cls;
        try {
            if(cls == Map.class) {
                this.obj = new HashMap();
            } else {
                this.obj = cls.newInstance();
            }
        } catch (InstantiationException e1) {
            throw new MdbException(e1.getMessage(), e1);
        } catch (IllegalAccessException e2) {
            throw new MdbException(e2.getMessage(), e2);            
        }
        //logger.debug("selector : cls");
    }
    /**
     * rs의 값들을 bean(obj)의 list에 담아 리턴  
     * @param rs
     * @return
     * @throws Exception
     */
    public synchronized List tokenResultSet(ResultSet rs, boolean isDebug) throws MdbException {
        return this.tokenResultSet(rs, -1, isDebug);
    }
    public synchronized List tokenResultSet(ResultSet rs, int fetchSize, boolean isDebug) throws MdbException {
        List list = new ArrayList();
        int count = 0;
        try {
        	ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            while (rs.next()) {
                for (int c = 1; c <= columnCount; c++) {
                    if(this.cls == Map.class) {
//                        logger.debug("MAP");
	                   ((Map)this.obj).put(rsmd.getColumnName(c).toLowerCase(), MdbLib.getColumn(rs, c, rsmd.getColumnType(c)));
                    } else {
	                    invoke(rsmd.getColumnName(c), 
	                            MdbLib.getColumn(rs, c, rsmd.getColumnType(c)) );
                    }
                }
                count++;
                list.add(this.obj);
                if(this.cls == Map.class) {
                    this.obj = new HashMap();
                } else {
                    this.obj = this.cls.newInstance();
                }
                if (count == fetchSize)
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MdbException(e.getMessage(), e);
        }
        if (isDebug) {
        	logger.debug(count + " records selected.");
        }
        return list;
    }
    
    /**
     * 하나의 row만 bean에 담아 리턴
     * @param rs
     * @return
     * @throws Exception
     */
    public synchronized Object tokenResultSetOne(ResultSet rs) throws Exception {
    	this.obj = this.cls.newInstance();
        String columnName = null;
        try {
        	ResultSetMetaData rsmd  = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

           if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    columnName = rsmd.getColumnName(i + 1) + "," + rsmd.getColumnTypeName(i + 1);
//                    Logging.log(this.getClass(), (i + 1) +" : ColumnName:["+columnName+"]");
                    invoke(rsmd.getColumnName(i + 1), MdbLib.getColumn(rs,
                            i + 1, rsmd.getColumnType(i + 1)));
                }
//                break;
            }
        } catch (Exception e) {
            logger.error("ColumnName:["+columnName+"]");
            throw new MdbException(e.getMessage(), e);
        }
        return this.obj;
    }
    /**
     * 데이터 베이스로 부터 읽은 컬럼명과 Beans의 Field를 비교하여 set 메소드가 있는지 검사한 후 <br>
     * Filed의 Object 형태를 파악하여 set메소드의 argument 로 넣어 메소드를 call 한다. <br>
     * java.lang.reflect API 를 이용하여 Beans를 파악한다.
     */
    private void invoke(String columnName, Object initValue) throws Exception {
        if (initValue == null)
            return;
        String fieldName = columnName.toLowerCase();
        Class fieldType = null;
        try {
            Field field = this.cls.getDeclaredField(fieldName);
            fieldType = field.getType();
            Method method = this.cls.getDeclaredMethod("set"
                    + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1, fieldName.length()),
                    new Class[] { fieldType });
            Object[] args = new Object[1];
            
            if (fieldType == Integer.TYPE) {
                try {
                    args[0] = new Integer(initValue.toString());
                } catch (NumberFormatException ne) {
                    args[0] = new Float(initValue.toString());
                }
            } else if (fieldType ==  Long.TYPE) {
                args[0] = new Long(initValue.toString());
            } else if (fieldType == Float.TYPE) {
                args[0] = new Float(initValue.toString());
            } else if (fieldType == Double.TYPE) {
                args[0] = new Double(initValue.toString());
            } else if (fieldType == java.util.Date.class) {
                //}else if(initValue.equals(new java.util.Date())){
                //    args [0] = Util.dateToString((java.util.Date)initValue,
                // "yyyy-MM-dd");
                args[0] = (java.util.Date) initValue;
            } else if (fieldType.equals(java.sql.Timestamp.class)) {
                //}else if(initValue.equals(new java.util.Date())){
                //    args [0] = Util.dateToString((java.util.Date)initValue,
                // "yyyy-MM-dd");
                args[0] = new java.sql.Timestamp(((java.sql.Date) initValue).getTime());    
            } else if (fieldType.equals(maf.lib.calendar.MDate.class)) {
                //logger.debug("MDATE : " +initValue + "," +initValue.getClass());
                args[0] = new MDate(initValue);
            
            } else {
                args[0] = (initValue.toString()).trim();
            }
            method.invoke(this.obj, args);
        } catch (NoSuchMethodException nsme) {
//            Logging.log(this.getClass(), "NoSuchMethodException :: " + fieldName);
        } catch (NoSuchFieldException nsfe) {
//            Logging.log(this.getClass(), "NoSuchFieldException :: " + cls.getName() + ".set"
//                    + fieldName.substring(0, 1).toUpperCase()
//                    + fieldName.substring(1, fieldName.length()));
        } catch (IllegalAccessException iae) {
        } catch (Exception invte) {
            logger.error("Error  :: methodType = " + fieldType + "  Class " + this.cls.getName() + ".set"
                    + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1, fieldName.length()) 
                    + " rs Type " + initValue.getClass()
                    );
            throw new MdbException(invte);
        }
    }
}