package maf.mdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.mdb.drivers.MYSQL;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.drivers.MdbJdbc;
import maf.mdb.drivers.MdbOCI8;
import maf.mdb.drivers.MdbSQL2000;
import maf.mdb.exceptions.MdbException;
import miraenet.MiConfig;

import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Database Utility
 */
public class Mdb {
    
    /**
     * MdbDriver를 받아 간다.
     * @param dbms <= MdbDriver.DBMS_xxxx Databse 유형 
     * @return
     */
    public static MdbDriver getMdbDriver(String dBpoolName, int dbms){
        switch(dbms){
        	case MdbDriver.DBMS_OCI8:
        		return new MdbOCI8(dBpoolName);
        	case MdbDriver.DBMS_SQL2000:
        		return new MdbSQL2000(dBpoolName);
        	case MdbDriver.DBMS_MYSQL:
        		return new MYSQL(dBpoolName);            		
        	default:
        		return new MdbJdbc(dBpoolName);
        }
    }
    
    public static MdbDriver getMdbDriver() {
        return getMdbDriver(MiConfig.DEFAULT_DB_POOL_NAME, MiConfig.DEFAULT_DBMS);
    }
    
    
    /**
     * 지정된 Connection을 Pools에서 가져다 준다.
     * @param dbPoolName
     * @return
     * @throws MdbException
     */
    public static Connection getConnection(String dbPoolName) throws MdbException {
    	try {
			return DBResource.getConnection(dbPoolName);
    	} catch (Exception e) {
			throw new MdbException("DB Pool [" + dbPoolName
					+ "] Connection Failure!!!", e);
		}
    	
    }
    
    /**
     * 기본 Connection을 Pools에서 가져다 준다.
     * @param dbPoolName
     * @return
     * @throws MdbException
     */    
    public static Connection getConnection() throws MdbException {
    	return getConnection(MiConfig.DEFAULT_DB_POOL_NAME);
    }
    
    
    public static Map getStatus() {
    	Map status = new HashMap();
    	PoolingDriver driver = null;
    	try {
    		driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
    		String poolNames[] = driver.getPoolNames();
    		for(int i = 0; i < poolNames.length; i++) {
    			GenericObjectPool pool = (GenericObjectPool) driver.getConnectionPool(poolNames[i]);
    			Map t = new HashMap();
    			t.put("MaxActive", pool.getMaxActive() +"");
    			t.put("NumActive", pool.getNumActive() +"");
    			t.put("NumIdle", pool.getNumIdle() +"");
    			t.put("MaxIdle", pool.getMaxIdle() +"");
    			t.put("MinIdle", pool.getMinIdle() +"");

    			t.put("NumTestsPerEvictionRun", pool.getNumTestsPerEvictionRun() +"");
    			status.put(poolNames[i],t);
    			pool = null;
    		}
    	} catch (Throwable e ) {
    		
    	}
    	driver = null;
    	return status;
    }
    
    /**
     * id 및 pw에 공백허용을 위해 ' 로 쌓고.. 내부에 ' 는 ''로 변화 한다.
     * 
     * @param x
     * @return
     */
    public static String prepareString(String x) {
        String rv = MafUtil.nvl(x, "");
        rv.replaceAll("\"", "\"\"");
        return rv;
    }
    

}