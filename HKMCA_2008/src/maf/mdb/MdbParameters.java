package maf.mdb;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MdbParameters {
//	private Logger logger = Logger.getLogger(this.getClass());
	Log logger = LogFactory.getLog(getClass());
	private String innersql = null;
    private List columns = new ArrayList();
    private List values = new ArrayList();
    private List types	= new ArrayList();	// OUT에 사용될 상수 
    private List inOuts	= new ArrayList();	// i : true, o:false
    
    public int add(String columnName, Object value,  int sqlType, int IN_OUT) {
        columns.add(columnName);
        values.add(value);
        types.add(new Integer(sqlType));
        inOuts.add(new Integer(IN_OUT));
//        logger.debug(columnName + " : " + value + " added , " + this.getSize());
        return columns.size()-1;
    }
    
    public int add(String columnName, Object value) {
        return this.add(columnName, value, -1, MdbConfig.TYPE_PARAMETER_IN);
    }
    /**
     * Column Name을 돌려준다.
     * @param index
     * @return
     */
    public String getColumn(int index) {
        return (String) columns.get( index);
    }
    
    /**
     * Column의 값을 돌려 준다.
     * @param index
     * @return
     */
    public Object getValue(int index) {
        return  values.get(index);
    }

    /**
     * SqlType 을 돌려준다.
     * java.sql.Types, oracle.jdbc.OracleTypes 의 값 
     * @param index
     * @return
     */
    public int getSqlType(int index) {
        return ((Integer) types.get(index)).intValue();
    }
    
    /**
     * IN : 0, OUT:1
     * @param index
     * @return
     */
    public int getINOUT(int index) {
        return  ((Integer) inOuts.get(index)).intValue();
    }
    
    /**
     * 개수(크기)를 돌려 준다.
     * @return
     */
    public int getSize() {
        return columns.size();
    }
    
	/**
	 * @return Returns the innersql.
	 */
	public String getInnersql() {
		return innersql;
	}
	/**
	 * @param innersql The innersql to set.
	 */
	public void setInnersql(String innersql) {
		this.innersql = innersql;
	}
}
