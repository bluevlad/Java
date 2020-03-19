package maf.web.mvc.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.base.BaseObject;
import maf.web.mvc.beans.SiteInfo;

import org.apache.log4j.Logger;

public class TemplateInfoConfig extends BaseObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	private Logger logger = Logger.getLogger( TemplateInfoConfig.class );
    /** ���ø��� �̸� */
    private String name;
    
    /** ���ø����� ���Ǵ� JSP �������� URI */
    private String uri;

    private String desc = null; // ����
    private boolean useSiteTemplate = false;
    /**
     * ���ø� �� ����� ����
     */
    private List mapList;
    /**
     * Site���ø�  ����� ����
     */
    private Map SiteTempLate = null;
    
    public TemplateInfoConfig() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

//    public String getUri() {
//        return getUri(null);
//    }
    
    public String getUri(SiteInfo siteinfo) {

		if (this.useSiteTemplate && siteinfo != null && SiteTempLate != null) {
			if (SiteTempLate.containsKey(siteinfo.getSite())) {
				return (String) SiteTempLate.get(siteinfo.getSite());
			}
		}
		return uri;
	}
    public void addTemplateMapConfig(TemplateMapConfig map) {
        if (mapList == null) {
            mapList = new java.util.ArrayList();
        }
        mapList.add( map );
    }

    public final List getTemplateMapConfigList() {
        return mapList;
    }
    
    public void addSiteTemplateConfig(SiteTemplateConfig map) {
        if (SiteTempLate == null) {
        	SiteTempLate = new HashMap();
        }
        SiteTempLate.put( map.getSite(), map.getUri());
    }
    
    
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setUseSiteTemplateByString(String redirect) {
        if (redirect != null && redirect.equals("true")) {
            this.useSiteTemplate = true;
        } else {
            this.useSiteTemplate = false;
        }
    }

	/**
	 * @return the useSiteTemplate
	 */
	public boolean isUseSiteTemplate() {
		return useSiteTemplate;
	}    
    
}