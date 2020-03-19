package maf.web.mvc.configuration;

import maf.MafUtil;
import maf.web.mvc.view.MafView;

public class ViewInfoConfig extends MvcConfig {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = null;
    private String uri = null;;
    private String pgid = null;;
    private boolean redirect = false;;
    private boolean templateFileSpecified = false;
    private boolean isUseTemplate = false;
    private String templateName = null;;
    private String type = MafView.DEFAULT_VIEW_TYPE;;
    private String templateFile = null;
    private String filename = null;
    
    public ViewInfoConfig() {
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    
    public String getUri() {
        return uri;
    }
    
    public void setRedirectByString(String redirect) {
        if (redirect != null && redirect.equals("true")) {
            this.redirect = true;
        } else {
            this.redirect = false;
        }
    }
    
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
    
    public boolean isRedirect() {
        return redirect;
    }
    
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
        if(!MafUtil.empty(templateName)) {
        	isUseTemplate = true;
        }
    }
    /**
     * 사용하는는 Template 명을 return 
     * @return
     */
    public String getTemplateName() {
        return templateName;
    }
	/**
	 * Template 기능 사용 여부(template 파일이 지정되었으면 true)
	 * @return true:사용
	 */
    public boolean isUseTemplate() {
    	return this.isUseTemplate;
    }
	/**
	 * @return Returns the pgid.
	 */
	public String getPgid() {
		return pgid;
	}
	/**
	 * @param pgid The pgid to set.
	 */
	public void setPgid(String pgid) {
		this.pgid = pgid;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		
		this.type = MafUtil.empty(type)?MafView.DEFAULT_VIEW_TYPE:type;
	}


	/**
	 * @param templateFile the templateFile to set
	 */
	public void setTemplateFile(String templateFile) {
		if( templateFile != null) {
			this.templateFile = templateFile;
			templateFileSpecified = true;
		}
	}
	
	/**
	 * 실제 추력될 jsp 파일명(template 파일명 또는 실 파일명)
	 * @return
	 */
	public String getRenderFile() {
		return (templateFileSpecified)?this.templateFile:this.uri; 
	}

	/**
	 * pdf나 excel에 전달될 파일명 result.getFilename()
	 * @return
	 */
	public String getFilename() {
    	return filename;
    }

	public void setFilename(String filename) {
    	this.filename = filename;
    }
	
}