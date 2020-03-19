package maf.mdb.drivers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import maf.mdb.exceptions.MdbException;

import org.apache.log4j.Logger;

public class MYSQL extends MdbDriver {
	private Logger logger = Logger.getLogger(MYSQL.class);

	public MYSQL(String dbpool) {
		this.dbPoolName = dbpool;
		this.dbms = MdbDriverInf.DBMS_MYSQL;

	}

	protected List selectLimit(MdbSelecter ms, String sql, Object at) throws MdbException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//        Logging.log(this.getClass(), "selectLimit execute");
		if(this.isScroll) {
			sql = sql + " LIMIT "+ this.offset + " , "+  this.fetchSize; 

		}
		try {
			if (at != null) {
				stmt = this.prepareStatement(sql, at);
			} else {
				stmt = this.prepareStatement(sql);
			}
			rs = stmt.executeQuery();
//			moveCursor(rs);
			return ms.tokenResultSet(rs, this.fetchSize, this.isDebug);
		} catch (Exception e) {
			throw new MdbException(e);
		} finally {
			if (rs != null) try {rs.close();rs = null;} catch (Exception ex) {}
			if (stmt != null) try {stmt.close();stmt = null;} catch (Exception ex) {}
		}
	}

}
