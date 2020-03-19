package maf.web.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import maf.MafProperties;
import maf.logger.Logging;
import maf.logger.Trace;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.mvc.beans.SiteInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author G2BKorea Create.
 *  
 */
public class Sites {
    Log logger = LogFactory.getLog(getClass());
    public static final String ROOT_CODE_SITE = "ROOT";
    private Map sites = null;

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
    	if ( sites.containsKey(site)) {
    		return (SiteInfo) sites.get(site);
    	} else {
    		return (SiteInfo) sites.get(MafProperties.DEFAULT_SITE);
    	}

    }

    private synchronized Map  init() {
        List tempSites = null;
        SiteInfo tbean = null;
        Map tsites = new HashMap();
        MdbDriver oDb = null;
        Map rvMap = new HashMap();
        
        String sql = " SELECT SITE, SITE_TITLE, LAYOUT, URL " + 
        		" FROM MAF_SITE";
        
        try {
            oDb = Mdb.getMdbDriver();
            tempSites = oDb.selector( SiteInfo.class, sql );
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
        return rvMap;
    }

}
