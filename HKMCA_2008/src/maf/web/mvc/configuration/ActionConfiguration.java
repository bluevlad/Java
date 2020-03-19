package maf.web.mvc.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import maf.configuration.support.ConfigFileInfo;
import maf.web.context.PathInfo;
import maf.web.mvc.exception.CommandNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 설정 정보를 저장하고 있는 객체

 * 2005.12.6 / Execute 성능개선을 위해 commandPool 추가.
 */
public class ActionConfiguration extends ConfigFileInfo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

//	private long lastModified = 0;
//    private String configurationFile = null;
    private String desc = null; // 설명
    private Log logger = LogFactory.getLog(getClass());

    /**
     * ServletPath 정보 저장
     */
    private String servletPath = "";
    /**
     * <templateName, templateInfo>: 템플릿 정보 저장
     */
    private Map templateMap = new HashMap();

    /**
     * <name, ViewInfoConfig>: gloval-view에 포함되어 있는 글로벌 뷰를 저장하고 있다.
     */
    private Map globalViewMap = new HashMap();

    /**
     * <uri, CommandMapping>
     */
    private Map commandMapping = new HashMap();
    


//    private Map messageBundleFactoryConfigMap = new java.util.HashMap();

    public ActionConfiguration() {
    	System.out.println("### " + this.getClass() + " start ### ");
    }

    public void addTemplateInfoConfig(TemplateInfoConfig templateInfo) {
        templateMap.put( templateInfo.getName(), templateInfo );
    }

    public final TemplateInfoConfig getTemplateInfoConfig(String templateName) {
        return (TemplateInfoConfig) templateMap.get( templateName );
    }

    public void addViewInfoConfig(ViewInfoConfig viewInfo) {
        globalViewMap.put( viewInfo.getName(), viewInfo );
    }

    public final Map getGlobalViewMap() {
        return globalViewMap;
    }

    /**
     * ServletPath와 uri, commandConf
     * 
     * @param commandConf
     */
    public void addCommandConfig(ActionConfig commandConf) {
//    	System.out.println(" - " + commandConf.getUri() + " : " + commandConf.getType() + " added.");
        commandMapping.put( commandConf.getUri(), commandConf );
        List t = commandConf.getAliasList();
        if (t!= null) {
        	ActionConfig tCom = null;
        	ActionAlias tAlias = null;
        	for(int i = 0; i < t.size(); i++) {
        		tAlias = (ActionAlias) t.get(i);
        		try {
	        		tCom = (ActionConfig) commandConf.clone();
	        		tCom.setDefaultCmd(tAlias.getDefaultCmd());
	        		tCom.setParam(tAlias.getParam());
	        		tCom.setDesc(maf.MafUtil.nvl(tAlias.getDesc(),tCom.getDesc()));
	        		tCom.setTitle(maf.MafUtil.nvl(tAlias.getTitle(),tCom.getTitle()));
	        		commandMapping.put(tAlias.getUri(), tCom);
        		} catch (Exception e) {
        			logger.error(e);
        		}
        	}
        }
    }

    /**
     * uri 기준으로 ActionConfig를 돌려 준다.
     * 없으면 CommandNotFoundException을 읽으킴 
     * @param pathInfo
     * @return
     * @throws CommandNotFoundException
     */
    public final ActionConfig getCommandConfig(PathInfo pathInfo) throws CommandNotFoundException {
    	ActionConfig ac = (ActionConfig) commandMapping.get( pathInfo.getContextURI() );
		if (ac == null) {
			throw new CommandNotFoundException("CommandConfig : " +  pathInfo.getRequestURI() + "(" +pathInfo.getServletPath()+"/"+ pathInfo.getContextURI() + ") Not found");
		}
        return ac;
    }



    
//    public String getClassName(String uri){
//    	
//    	ActionConfig commandConfig = getCommandConfig( uri );
//		if (commandConfig == null) {
//        	logger.error(uri + " command not found");
//            return null;
//        }
//		return commandConfig.getType();
//    }
//    
    public void setServletPath(String servletPath) {
        this.servletPath = (servletPath == null) ? "" : servletPath;
    }

    /**
     * Command level의 servlet Path 구함.
     * @return
     */
    public final String getServletPath() {
        return servletPath;
    }
    public Map getCommandMapping() {
        return commandMapping;
    }

    public final Map getTemplateMap() {
        return templateMap;
    }

    public final String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}