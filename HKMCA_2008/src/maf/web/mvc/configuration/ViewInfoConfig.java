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
     * ����ϴ´� Template ���� return 
     * @return
     */
    public String getTemplateName() {
        return templateName;
    }
	/**
	 * Template ��� ��� ����(template ������ �����Ǿ����� true)
	 * @return true:���
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
	 * ���� �߷µ� jsp ���ϸ�(template ���ϸ� �Ǵ� �� ���ϸ�)
	 * @return
	 */
	public String getRenderFile() {
		return (templateFileSpecified)?this.templateFile:this.uri; 
	}

	/**
	 * pdf�� excel�� ���޵� ���ϸ� result.getFilename()
	 * @return
	 */
	public String getFilename() {
    	return filename;
    }

	public void setFilename(String filename) {
    	this.filename = filename;
    }
	
}