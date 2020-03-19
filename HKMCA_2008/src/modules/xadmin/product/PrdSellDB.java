package modules.xadmin.product;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class PrdSellDB extends BaseDAO {
    

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		
		List list = null;

		final String sql = "select *  " +
		"  from (select a.prd_cd, a.prd_nm, b.reg_dt, b.psell_cd, b.psell_nm, b.psell_price,  " +
		"               c.sell_cd, c.sell_nm, c.sell_phone  " +
		"          from prd_mst a, prd_sell b, jmf_seller c  " +
		"          where a.prd_cd = b.prd_cd  " +
		"          and b.sell_cd = c.sell_cd  " +
		"  ) x  ";
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(PrdSellDB.getRecordCount(oDb, param, sWhere.toString()));
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
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final String sql =  " SELECT count(*) "   +
		"  from (select a.prd_cd, a.prd_nm, b.reg_dt, b.psell_cd, b.psell_nm, b.psell_price,  " +
		"               c.sell_cd, c.sell_nm, c.sell_phone  " +
		"          from prd_mst a, prd_sell b, jmf_seller c  " +
		"          where a.prd_cd = b.prd_cd  " +
		"          and b.sell_cd = c.sell_cd  " +
		"  ) x" + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 하나의 정보를 읽어온다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select a.psell_cd, a.psell_nm, a.psell_price, a.sell_cd, a.prd_cd, a.reg_dt, a.active_yn, a.psell_desc,  " +
		"       b.prd_nm, b.prd_type, b.org_price, c.sell_nm, c.sell_phone, c.sell_fax  " +
		"  from prd_sell a, prd_mst b, jmf_seller c   " +
		"  where a.prd_cd = b.prd_cd  " +
		"  and a.sell_cd = c.sell_cd  " +
		"  and psell_cd = :psell_cd ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

    /**
     * 하나의 레코드를 Insert 한다.
     *
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public static int InsertOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO prd_sell " +
        "    (psell_cd, psell_nm, psell_price, psell_desc, sell_cd, prd_cd, reg_dt, active_yn) " +
        "  VALUES"   +
        "    (seq_product.NEXTVAL, :psell_nm, :psell_price, :psell_desc, :sell_cd, :prd_cd, sysdate, :active_yn)"  ;
        
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

            final String sql = " UPDATE prd_sell"   +
            "    SET "   +
            "        psell_nm = :psell_nm,"   +
            "        psell_price = :psell_price,"   +
            "        psell_desc = :psell_desc,"   +
            "        active_yn = :active_yn," +
            "        upt_dt = sysdate "   +
            "  WHERE psell_cd = :psell_cd"  ;
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
        String sql = "DELETE FROM prd_sell WHERE psell_cd = :psell_cd";
        return oDb.executeUpdate(sql, param);
    }

}