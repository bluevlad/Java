
package maf.menu;

import java.util.ArrayList;
import java.util.List;


public class XMLMenuBean implements Cloneable  {
    private String id = null;
    private String nm= null;
    private String nm_eng = null;
    private List subMenus = null; 
    private String url = null;
    private String seq = null;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNm() {
        return nm;
    }
    public void setNm(String nm) {
        this.nm = nm;
    }
    public String getNm_eng() {
        return nm_eng;
    }
    public void setNm_eng(String nm_eng) {
        this.nm_eng = nm_eng;
    }
    
    public void addMenu(XMLMenuBean m) {
        if(subMenus == null) {
            subMenus = new ArrayList();
        }
        subMenus.add(m);
    }
    
    public List getSubMenu() {
        return subMenus;
    }
    
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    

	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
    public XMLMenuBean getClone()  throws Exception{
        XMLMenuBean ob = null;
        List tsubmenu = new ArrayList();
        try {
            ob = (XMLMenuBean) this.clone();
            if(subMenus != null) {
	            for (int i=0; i < subMenus.size(); i++) {
	                tsubmenu.add(subMenus.get(i));
	            }
            }
            ob.subMenus = tsubmenu;
            
        } catch (CloneNotSupportedException e) {
            throw new Exception("Clone Not Support!!!");
        }
        return ob;
    }

}
