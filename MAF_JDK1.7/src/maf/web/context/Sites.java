/*
 * �ۼ��� : ����� Created on 2004. 10. 13. Last Date : 200410.16 001
 */
package maf.web.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import maf.logger.Logging;
import maf.logger.Trace;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.mvc.beans.SiteInfo;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author �����(goindole@miraenet.com) Create by 2004. 10. 13.
 *  
 */
public class Sites {
    Log logger = LogFactory.getLog(getClass());
    public static final String ROOT_CODE_SITE = "ROOT";
    private Map sites = null;
    private Map siteAliases = null;

    private static Sites _instance;

    /**
     * Constructor
     *  
     */
    private Sites() {
    	DataReset();
    }

    /**
     * Sites instance �� ���� �ش�.
     * 
     * @return
     */
    public static  Sites getInstance() {
        if (_instance == null) {
            _instance = new Sites();
        }
        return _instance;
    }

    /**
     * DB���� Data�� ���� ������
     *  
     */
    public synchronized void DataReset() {
    	
        Map tSite = null;
        Map tSiteAlias = null;
        List tAlias = null;
        SiteInfo t = null;
        tSite = init();
        if (tSite != null) {
        	sites = (Map) tSite.get("SITE");
        	
	        // ��Ʈ���� ������ �����´�.
	        Set set = sites.entrySet();
	        // �ݺ��ڸ� ��´�.
	        Iterator i = set.iterator();
	        // ��ҵ��� ����Ѵ�.
	        while(i.hasNext()) {
	        	Map.Entry me = (Map.Entry)i.next();
	        	t = (SiteInfo) sites.get(me.getKey());
	        	Logging.log(this.getClass(), "[" + me.getKey() + " : " + t.getUrl() + "] site loaded.");
	        }
	        tAlias = (List) tSite.get("ALIAS");
	        if (tAlias != null) {
	        	tSiteAlias = new HashMap();
		        for(int j= 0; j < tAlias.size(); j ++ ) {
		        	t = (SiteInfo) tAlias.get(j);
		        	tSiteAlias.put(t.getUrl(), t.getSite());
		        }
		        siteAliases = tSiteAlias;
	        }
	        
        }
    }

    public final SiteInfo getSite(String site) {
        if (site == null) return null;
       return (SiteInfo) sites.get(site);
    }
    
    /**
     * host(URL�� protocol����) ������ site ������ ã�ƿ´�.
     * matching �Ǵ°� ������ default site�� ������
     * @param host
     * @return
     */
    public final SiteInfo getSiteByName(String serverName) {
    	String site = null;
    	if(siteAliases != null){
    		site = (String) siteAliases.get(serverName);
    	}
    	if ( sites.containsKey(site)) {
    		return (SiteInfo) sites.get(site);
    	} else {
    		return (SiteInfo) sites.get(MiConfig.DEFAULT_SITE);
    	}

    }

    private synchronized Map  init() {
        List tempSites = null;
        List tempAlias = null;        
        SiteInfo tbean = null;
        Map tsites = new HashMap();
        MdbDriver oDb = null;
        Map rvMap = new HashMap();
        
        String sql = " SELECT SITE, TITLE, SHORT_DESC, LAYOUT, URL, CUST_CD " + 
        		" FROM MAF_SITE";
        
        String sql2 = " select site, url from maf_site"   +
        " union all"   +
        " select site, url from maf_site_alias"  ;     

        try {
            oDb = Mdb.getMdbDriver();
            tempSites = oDb.selector( SiteInfo.class, sql );
            tempAlias = oDb.selector( SiteInfo.class, sql2 );
            rvMap.put("STAUS", "OK");
        } catch (Exception e) {
            logger.error( Trace.getStackTrace( e ) );
        } finally {
            if (oDb != null) try {
                oDb.close();
            } catch (Exception e) {}
            oDb = null;
        }
        // list�� map ���� ��ȯ 
        if( tempSites != null ) {
	        for(int i = 0 ; i < tempSites.size(); i ++ ) {
	            tbean = (SiteInfo) tempSites.get(i);
	            tsites.put(tbean.getSite().toLowerCase(), tbean);
	        }
        }
        rvMap.put("SITE",tsites );
        rvMap.put("ALIAS",tempAlias );
        return rvMap;
    }

}
