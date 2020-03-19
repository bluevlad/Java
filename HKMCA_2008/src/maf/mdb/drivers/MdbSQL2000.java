package maf.mdb.drivers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MdbSQL2000 extends MdbDriver{
    private Log logger = LogFactory.getLog(getClass());
    
    public MdbSQL2000(String dbpool) {
        this.dbPoolName = dbpool;
        this.dbms = MdbDriver.DBMS_SQL2000;
    }
    
    protected List pSelector(MdbSelecter ms, ResultSet rs) throws MdbException {

		try {
			moveCursor(rs);
			return ms.tokenResultSet(rs, this.fetchSize, this.isDebug);
		} catch (Exception e) {
			throw new MdbException(e);
		} finally {
			if (rs != null)try {rs.close();} catch (Exception ex) {}
			rs = null;
		}
	}

	/**
	 * 데이터를 SELECT 할 위치로 커서를 이동시킨다. <br>
	 * JDBC 2.0 API 를 이용하였음.
	 */
	protected void moveCursor(ResultSet rs) throws SQLException {
		int count = 0;
		if (!(offset <= 0)) {
			logging("absolute move offset : " + offset + ", Page:" + page
						+ ", fetchSize : " + fetchSize);
			rs.setFetchSize(offset + fetchSize);
	        if ( offset > 0  ) {
		        while (rs.next()) {
		            count ++;
		            if (offset == count) break;
		        }
	        }
        	logging(count + " rows moved");
		}

	}
}
