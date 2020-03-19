package modules.wlc.classroom.tutor;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class ReportDB {

	public static List getList(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql = "select rptcode, rpttitle, rptdesc, rpt_sdat, rpt_edat, rptrate, rptopen,   " +
		"       (select count(usn) from wlc_lec_req where lec_cd = aa.lec_cd and req_stat in ('LP', 'LE')) cnt,  " +
		"       (select count(usn) from wlc_rpt_giv where lec_cd = aa.lec_cd and rptcode = aa.rptcode) giv  " +
		"  from wlc_rpt_mst aa  " +
		"  where lec_cd = :lec_cd ";
		
		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * 하나의 레코드 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select a.rptcode, a.rpttitle, a.rptdesc, a.rpt_sdat, a.rpt_edat, a.rptrate, a.rptopen,  " +
		" b.usn, b.givtitle, b.givfile, b.givdat,   " +
		" b.fedtitle, b.feddesc, b.feduser, b.feddat, b.usrscore  " +
		" from wlc_rpt_mst a, wlc_rpt_giv b  " +
		" where a.lec_cd = b.lec_cd(+)  " +
		" and a.rptcode = b.rptcode(+)  " +
		" and a.lec_cd = :lec_cd   " +
		" and a.rptcode = :rptcode ";


		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	public static Map getUserOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select a.rptcode, a.rpttitle, a.rptdesc, a.rpt_sdat, a.rpt_edat, a.rptrate, a.rptopen,    " +
		"       decode(:usn, b.usn, :usn) as usn, b.givtitle, b.givdesc, b.givfile, b.givdat,     " +
		"       b.fedtitle, b.feddesc, b.feduser, b.feddat, b.usrscore    " +
		"  from wlc_rpt_mst a, (select * from wlc_rpt_giv where lec_cd = :lec_cd and usn = :usn)  b    " +
		"  where a.lec_cd = b.lec_cd(+) " +
		"  and a.rptcode = b.rptcode(+) ";


		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	public static Map getGivFile(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select c.pds_cd, c.main_cd, c.sub_cd, c.file_id, c.new_filename, c.org_filename   " +
		" from file_att c " +
		" where c.pds_cd = :giv " +
		" and c.main_cd = :lec_cd " +
		" and c.sub_cd = :rptcode " +
		" and c.file_id = :usn ";

		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	public static List getRptBnk(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql = "select aa.rptcode, aa.lec_cd, bb.quetitle, bb.quedesc, bb.quecode, aa.quecode chk " +
		" from ( select a.rptcode, a.lec_cd, b.quecode " +
		" from wlc_rpt_mst a, wlc_rpt_que b " +
		" where a.lec_cd = b.lec_cd(+) " +
		" and a.rptcode = b.rptcode(+)) aa, wlc_rpt_bnk bb " +
		" where bb.quecode = aa.quecode(+) " +
		" and sjt_cd = :sjt_cd " +
		" and rptcode(+) = :rptcode " +
		" order by bb.quecode asc";
		
		return oDb.selector(Map.class, sql, param);
	}
	
	public static List getRptFile(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql = "select a.lec_cd, a.rptcode, b.quecode, b.sjt_cd, b.quetitle, b.quedesc, c.* " +
		" from wlc_rpt_que a, wlc_rpt_bnk b, file_att c " +
		" where a.quecode = b.quecode " +
		" and c.pds_cd = :pds_cd " +
		" and c.main_cd = b.sjt_cd " +
		" and c.sub_cd = b.quecode " +
		" and lec_cd = :lec_cd " +
		" and sjt_cd = :sjt_cd " +
		" and a.rptcode = :rptcode ";
		
		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getStdList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		
		List list = null;

		final String sql = "select * from (select a.lec_cd, a.usn, d.userid, d.nm, b.rptcode, b.givtitle, b.givdesc, b.givfile, b.givdat,  " +
		" b.fedtitle, b.feddesc, b.feduser, b.feddat, b.usrscore, " +
		" (select new_filename from file_att where pds_cd = :pds_cd and main_cd = a.lec_cd and sub_cd = c.rptcode and file_id = a.usn) new_filename, " +
		" (select org_filename from file_att where pds_cd = :pds_cd and main_cd = a.lec_cd and sub_cd = c.rptcode and file_id = a.usn) org_filename " +
		" from wlc_lec_req a, wlc_rpt_giv b, wlc_rpt_mst c, maf_user d  " +
		" where a.lec_cd = b.lec_cd(+)  " +
		" and a.req_stat in ('LP', 'LE')  " +
		" and a.usn = b.usn(+)  " +
		" and a.lec_cd = c.lec_cd  " +
		" and a.usn = d.usn  " +
		" and a.lec_cd = :lec_cd  " +
		" and c.rptcode = :rptcode  " +
		") x ";

		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(ReportDB.getStdRecordCount(oDb, param, sWhere.toString()));
			navigator.sync();
		}
		
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		
	}
	
	/**
	 * 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private static long getStdRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = "select count(*) from (select a.lec_cd, a.usn, d.userid, d.nm, b.rptcode, b.givtitle, b.givdesc, b.givfile, b.givdat,  " +
		" b.fedtitle, b.feddesc, b.feduser, b.feddat, b.usrscore, " +
		" (select new_filename from file_att where pds_cd = :pds_cd and main_cd = a.lec_cd and sub_cd = c.rptcode and file_id = a.usn) new_filename, " +
		" (select org_filename from file_att where pds_cd = :pds_cd and main_cd = a.lec_cd and sub_cd = c.rptcode and file_id = a.usn) org_filename " +
		" from wlc_lec_req a, wlc_rpt_giv b, wlc_rpt_mst c, maf_user d  " +
		" where a.lec_cd = b.lec_cd(+)  " +
		" and a.req_stat in ('LP', 'LE')  " +
		" and a.usn = b.usn(+)  " +
		" and a.lec_cd = c.lec_cd  " +
		" and a.usn = d.usn  " +
		" and a.lec_cd = :lec_cd  " +
		" and c.rptcode = :rptcode) x " + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 하나의 레코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int insertOne(MdbDriver oDb, Map param) throws MdbException {

		final String sql = " INSERT INTO wlc_rpt_mst "   +
		" (lec_cd, rptcode, rpttitle, rptdesc, rpt_sdat, rpt_edat, rptrate, rptopen, upt_dt, upt_id) " +
		" VALUES"   +
		" (:lec_cd, GET.maxRptcode(:lec_cd)+1, :rpttitle, :rptdesc, :rpt_sdat, :rpt_edat, :rptrate, :rptopen, sysdate, :upt_id)";
				
		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update wlc_rpt_mst set " +
		" rpttitle = :rpttitle, " +
		" rptdesc = :rptdesc, " +
		" rpt_sdat = :rpt_sdat, " +
		" rpt_edat = :rpt_edat, " +
		" rptrate = :rptrate, " +
		" rptopen = :rptopen, " +
		" upt_dt = sysdate, " +
		" upt_id = :upt_id " +
        " where lec_cd = :lec_cd " +
        " and rptcode = :rptcode";
		
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 레코드를 삭제 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete from wlc_rpt_mst  " +
        " where lec_cd = :lec_cd " +
        " and rptcode = :rptcode";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 */
	public static int insertQue(MdbDriver oDb, Map param) throws MdbException {

		final String sql = " INSERT INTO wlc_rpt_que "   +
		" (lec_cd, rptcode, quecode, upt_dt, upt_id) " +
		" VALUES"   +
		" (:lec_cd, :rptcode, :quecode, sysdate, :upt_id)";
				
		return oDb.executeUpdate(sql, param);
	}

	public static int deleteQue(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete from wlc_rpt_que " +
        " where lec_cd = :lec_cd " +
        " and rptcode = :rptcode";
		return oDb.executeUpdate(sql, param);
	}
	
	public static int insertGiv(MdbDriver oDb, Map param) throws MdbException {

		final String sql = " INSERT INTO wlc_rpt_giv "   +
		" (lec_cd, rptcode, usn, givtitle, givdesc, givdat, reg_id, reg_dt) " +
		" VALUES"   +
		" (:lec_cd, :rptcode, :usn, :givtitle, :givdesc, sysdate, :reg_id, sysdate)";
				
		return oDb.executeUpdate(sql, param);
	}

	public static int updatePoint(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update wlc_rpt_giv set " +
		" usrscore = :usrscore, " +
		" upt_id = :upt_id, " +
		" upt_dt = sysdate " +
        " where lec_cd = :lec_cd " +
        " and rptcode = :rptcode " +
        " and usn = :usn";
		
		return oDb.executeUpdate(sql, param);
	}
	
	public static int updateGiv(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update wlc_rpt_giv set " +
		" givtitle = :givtitle, " +
		" givdesc = :givdesc, " +
		" givdat = sysdate, " +
		" upt_id = :upt_id, " +
		" upt_dt = sysdate " +
        " where lec_cd = :lec_cd " +
        " and rptcode = :rptcode " +
        " and usn = :usn";
		
		return oDb.executeUpdate(sql, param);
	}
	
    /**
     * 하나의 레코르를 insert 한다.
     *
     * @param oDb
     * @param bean
     * @return int
     * @throws MdbException
     */
    public static int insertFile(MdbDriver oDb, Map param) throws MdbException {

        String sql = "INSERT INTO file_att (PDS_CD, MAIN_CD, SUB_CD, FILE_ID, " +
        		" NEW_FILENAME, ORG_FILENAME, REG_DT, DOWN_CNT, FILE_SIZE, ATT_TYPE) " +
        		" VALUES (:PDS_CD, :MAIN_CD, :sub_cd, :file_id, " +
        		" :NEW_FILENAME, :ORG_FILENAME, sysdate, 0, :FILE_SIZE, :ATT_TYPE) ";
        
        return oDb.executeUpdate(sql, param);
    }

    public static int updateFile(MdbDriver oDb, Map param) throws MdbException {

        String sql = "UPDATE file_att" +
        		" set NEW_FILENAME = :NEW_FILENAME," +
        		" ORG_FILENAME = :ORG_FILENAME, " +
        		" FILE_SIZE = :FILE_SIZE, " +
        		" ATT_TYPE = :ATT_TYPE " +
        		" where pds_cd = :PDS_CD" +
        		" and main_cd = :MAIN_CD" +
        		" and sub_cd = :sub_cd" +
        		" and file_id = :file_id ";
        
        return oDb.executeUpdate(sql, param);
    }

}