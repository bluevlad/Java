/*
 * 작성된 날짜: 2005-01-17
 * 
 */
package modules.community.mboard.dao;

import java.util.ArrayList;
import java.util.List;

import maf.mdb.drivers.MdbDriver;
import modules.community.mboard.beans.MbbsGroupBean;

/**
 * @author bluevlad
 * 
 */
public class MbbsMeta {
    private static MbbsMeta instance = new MbbsMeta();

    private MbbsMeta() {

    }

    public static synchronized MbbsMeta getInstance() {
        return instance;
    }

    public MbbsGroupBean[] getBbsGrp(MdbDriver oDB) {
        List ListItem = new ArrayList();

        String sql = " SELECT   grp, grp_desc, seq, site, layout, c_path"   +
	        "     FROM MBBS_GRP"   +
	        " ORDER BY seq"  ;
        try {
            ListItem = oDB.selector(MbbsGroupBean.class, sql);
        } catch (Exception e) {
            
        }
        return (MbbsGroupBean[])ListItem.toArray(new MbbsGroupBean[0]);
    }
}