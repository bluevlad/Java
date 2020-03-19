/*
 * Created on 2005. 12. 5.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.exception.MafException;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import miraenet.AppConfig;
import modules.common.beans.UsrMstBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LoginDB extends CommonDB{
//    private static LoginDB instance;

    private static final Log logger = LogFactory.getLog(LoginDB.class);


    
    /**
     * mst_user에서 userid를 기준으로 사용자 정보를 가져 오며 없을 경우 * com.vusrmst에서 mst_user로 동기
     * 
     * @param oDb
     * @param userID
     * @return
     * @throws Exception
     */
    public static String getUsnByUserID(MdbDriver oDb, String userID) throws MdbException {
        String usn = null;
	    String sql = " SELECT usn"   +
	        "   FROM v_maf_user"   +
	        "  WHERE userid = :userid"  ;
        try {
        	Map param = new HashMap();
        	param.put("userid", userID);
            
            usn = (String) oDb.selectOne(sql, param);
        } finally {
           
        }
        return usn;
    }
    
    /**
     * User_Log에 접속 정보를 담든다.
     * 
     * @param oDb
     * @param userid
     * @param usn
     * @param status
     * @param remoteIP
     */
    public static void userlog(MdbDriver oDb, String userid, String usn, String status, String remoteIP) {
        Map param = new HashMap();
        try {
            String sql = "insert into MAF_LOG_USER (USERID, USN,  STATUS, IP) " +
            		"	values(:userid, :usn, :status, :ip) ";
            param.put("userid", userid);
            param.put("usn", usn);
            param.put("status", status);
            param.put("ip", remoteIP);

            oDb.executeUpdate(sql, param);
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
    }
    
    
    /**
     * USN(직번,학번) 을 가지고 개인 정보를 가져온다.
     * 
     * @param oDb
     * @param usn
     * @return
     * @throws Exception
     */
    public static UsrMstBean getMstUserByUSN(MdbDriver oDb, String usn) throws MdbException {
        Map param = new HashMap();

        String sql = " SELECT mu.usn, mu.userid, mu.nm, mu.nm_eng, email"   +
        "  FROM v_maf_user mu"+
        "  WHERE mu.usn = :usn";

        //inputarray.add(usn);
        param.put("usn", usn);

        return (UsrMstBean) oDb.selectorOne(UsrMstBean.class, sql, param);
    }
    
    
    /**
     * USN(직번,학번) 을 가지고 개인 정보를 가져온다.
     * 
     * @param oDb
     * @param usn
     * @return
     * @throws Exception
     */
    public static UsrMstBean getRUserByUSN(MdbDriver oDb, String usn) throws MdbException {
        Map param = new HashMap();

        final String sql = " SELECT mu.usn, mu.userid, mu.nm"   +
        "  FROM r_userinfo mu"   +
        "  WHERE mu.usn = :usn"  ;
        //inputarray.add(usn);
        param.put("usn", usn);

        return (UsrMstBean) oDb.selectorOne(UsrMstBean.class, sql, param);
    }
    
    public static List getUserRoleList(MdbDriver oDb, String usn) throws MdbException {

        final String sql = " SELECT usn, role_id, site" +
        " FROM maf_user_role" +
        " WHERE usn = :usn";

        Map param = new HashMap();
        param.put("usn", usn);

        return  oDb.selector(Map.class, sql, param);
    }
    
    /**
     * Local 계정에서 사용자 확인
     */
    public static UsrMstBean getLocalUserByUserID(MdbDriver oDb, String userID, String passwd) throws MafException, InvalidPasswordException, InvalidUserIdException {
        UsrMstBean ob = null;
	    final String sqlChk = " SELECT count(*) FROM maf_user WHERE userid = :userid"  ;

        //USN, NM, NM_ENG, LAST_DT, UTYPE
        final String sql = " SELECT mu.usn, mu.userid, mu.nm, mu.nm_eng, email"   +
        " FROM v_maf_user mu" +
        " WHERE UPPER (mu.userid) = UPPER (:userid)"   +
        " AND passwd = :passwd";

		Map param = new HashMap();
		param.put("userid", MafUtil.nvl(userID, ""));
		param.put("passwd", passwd);
		param.put("hash", AppConfig.DES_HASH);

		int cnt = oDb.selectOneInt(sqlChk,param);
		if (cnt < 1) {
			throw new InvalidUserIdException(userID);
		}
		ob = (UsrMstBean) oDb.selectorOne(UsrMstBean.class, sql, param);
		if (ob == null) {
			throw new InvalidPasswordException("USERID : " + userID + ", Passwd: " + passwd);
		}

		return ob;
    }

}