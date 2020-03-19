package miraenet.app.downloader;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileMngDB extends BaseDAO {

    /**
     * 목록 가져오기
     *
     * @param MdbDriver
     * @param NavigatorInfo
     * @param param
     * @param sWhere
     * @return void
     * @throws MdbException
     */
    public static List getList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select * " +
        " from file_att " +
        " where pds_cd = :pds_cd" +
        " and main_cd = :main_cd" +
        " and sub_cd = :sub_cd";

        return oDb.selector(Map.class, sql, param);
    }

    /**
     * 해당 과목의 컨텐츠 목록을 가져온다.
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql = "select * " +
        " from file_att " +
        " where pds_cd = :pds_cd" +
        " and main_cd = :main_cd" +
        " and sub_cd = :sub_cd " +
        " and file_id = :file_id";

        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 하나의 레코르를 insert 한다.
     *
     * @param oDb
     * @param bean
     * @return int
     * @throws MdbException
     */
    public static int insertOne(MdbDriver oDb, Map param) throws MdbException {

        String sql = "INSERT INTO file_att (PDS_CD, MAIN_CD, SUB_CD, FILE_ID, " +
        		" NEW_FILENAME, ORG_FILENAME, REG_DT, DOWN_CNT, FILE_SIZE, ATT_TYPE) " +
        		" VALUES (:PDS_CD, :MAIN_CD, :sub_cd, GET.maxFileid(:PDS_CD, :MAIN_CD, :sub_cd), " +
        		" :NEW_FILENAME, :ORG_FILENAME, sysdate, 0, :FILE_SIZE, :ATT_TYPE) ";
        
        return oDb.executeUpdate(sql, param);
    }

    /**
     * 하나의 레코드를 Update 한다.
     *
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public static int updateOne(MdbDriver oDb, Map param) throws MdbException {

	        final String sql = " UPDATE file_att"   +
	        "    SET "   +
	        "        NEW_FILENAME = :NEW_FILENAME,"   +
	        "        ORG_FILENAME = :ORG_FILENAME,"   +
	        "        DOWN_CNT = 0,"   +
	        "        FILE_SIZE = :FILE_SIZE,"   +
	        "        ATT_TYPE = :ATT_TYPE"   +
	        " WHERE PDS_CD = :PDS_CD " +
	        " and MAIN_CD = :MAIN_CD " +
	        " and SUB_CD = :SUB_CD " +
	        " and FILE_ID = :FILE_ID";

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
    	String sql = "DELETE FROM file_att" +
        " WHERE PDS_CD = :PDS_CD " +
        " and MAIN_CD = :MAIN_CD " +
        " and SUB_CD = :SUB_CD " +
        " and FILE_ID = :FILE_ID";
        return oDb.executeUpdate(sql, param);
    }

	public static String getSysGuid(MdbDriver oDb) throws MdbException {
		String sql = "select sys_guid() from dual";
        return oDb.selectOne(sql);
	}

}