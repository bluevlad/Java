package maf.web.mvc.configuration;

import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.web.mvc.view.MafView;


public class ActionConfig  extends MvcConfig{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PARAM_KEY = ActionConfig.class+".PARAM";
    private String uri = null;
    private String pgid = null;
    private String defaultCmd = null;
    private String param = null;
    private String jsppath = null;
    private boolean templateFileSpecified = false;
    private boolean isUseTemplate = false;
    private String templateName = null;;
    private String type = MafView.DEFAULT_VIEW_TYPE;;
    private String templateFile = null;
    private String filename = null;
    
    private boolean debug = false;
    
    private Map viewMap = new java.util.HashMap();
    private Map cmdMap = new java.util.HashMap();
    private Map formMap = new java.util.HashMap();
    private List aliasList = new java.util.ArrayList();
    
    public ActionConfig() {
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    
    public void addCmdConfig(CmdConfig cmdInfo) {
    	cmdMap.put(cmdInfo.getName(), cmdInfo);
    }
    /**
     * Action alias 를 추가 한다.
     * @param alias
     */
    public void addAlias(ActionAlias alias) {
    	aliasList.add(alias);
    }
    
    public final List getAliasList() {
    	return this.aliasList;
    }
    /**
     *
     * 디버깅용 
     * 
     * @return
     *@deprecated
     */
    public final Map getCmdMap() {
    	return cmdMap;
    }
    public final CmdConfig getMethod(String cmd) {
    	return (CmdConfig) cmdMap.get(cmd);
    }
    
    public void addViewInfoConfig(ViewInfoConfig viewInfo) {
    	String uri = viewInfo.getUri();
    	if(uri != null && !uri.startsWith("/") && this.jsppath != null) {
    		viewInfo.setUri(this.jsppath  + viewInfo.getUri());
    	}
        viewMap.put(viewInfo.getName(), viewInfo);
    }
    /**
    *
    * 디버깅용 
    * 
    * @return
    *@deprecated
    */
    public final Map getViewMap() {
        return viewMap;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri){
        this.uri = uri;
    }
  
    ///////////////////////////////////////
    public void addFormConfig(FormConfig formConfig) {
    	formMap.put(formConfig.getName(), formConfig);
    }
    
    public final FormConfig getForm(String formName) {
    	return (FormConfig) formMap.get(formName);
    }
    /**
    *
    * 디버깅용 
    * 
    * @return
    *@deprecated
    */
    public final Map getFormMap() {
        return formMap;
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
	 * @return Returns the defaultCmd.
	 */
	public final String getDefaultCmd() {
		return defaultCmd;
	}

	/**
	 * @param defaultCmd The defaultCmd to set.
	 */
	public void setDefaultCmd(String defaultCmd) {
		if(defaultCmd != null) {
			this.defaultCmd = defaultCmd;
		}
	}

	/**
	 * debug 상태를 return
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}

	public String getJsppath() {
    	return jsppath;
    }

	public void setJsppath(String jsppath) {
		String t = null;
		if(jsppath != null ) {
			t = jsppath.trim();
			if(!t.endsWith("/")) {
				t = t+"/";
			}
		}
    	this.jsppath = t;
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

	
}