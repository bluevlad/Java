/*
 * Selecter.java
 * 
 * ������ ���̽����� �˻��� ������� Ư���� Bean�� �����Ͽ� �迭�� �����ϴ� Ŭ���� ���÷��� API�� ����Ͽ� �ڹٺ��� ������ �����ϰ�,
 * ResultSetMetaData�� �����ͺ��̽��� ������ Ÿ���� �ľ��Ѵ�.
 * 
 * ���� ���� - JDBC 2.0 API�� �ݵ�� ������ �� - ���ϵǴ� �÷��� �� ������ Ÿ�԰� ������ FIELD�� Bean �� ����Ǿ�
 * �ְ� setXxx() �޼ҵ尡 �����Ͽ��� ��
 * 
 * ����� > Selecter s = new Selecter(DB_SID[0], "com.miraenet.beans.AdmLecBean",
 * true); > s.pageConfig(1, 10); // �������� ����� Ư���� �������� ������� �����Ҷ� ��� > Collection
 * col = s.execute(sql, Selecter.NULL);
 * 
 * Collection ���� �� �迭 ��ȭ ��� ((AdmAmtBean[])col.toArray(new AdmAmtBean[0]))[0];
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
     * rs�� ������ bean(obj)�� list�� ��� ����  
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
     * �ϳ��� row�� bean�� ��� ����
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
     * ������ ���̽��� ���� ���� �÷���� Beans�� Field�� ���Ͽ� set �޼ҵ尡 �ִ��� �˻��� �� <br>
     * Filed�� Object ���¸� �ľ��Ͽ� set�޼ҵ��� argument �� �־� �޼ҵ带 call �Ѵ�. <br>
     * java.lang.reflect API �� �̿��Ͽ� Beans�� �ľ��Ѵ�.
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