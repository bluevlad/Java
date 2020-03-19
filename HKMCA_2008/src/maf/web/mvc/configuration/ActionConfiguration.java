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
 * ���� ������ �����ϰ� �ִ� ��ü

 * 2005.12.6 / Execute ���ɰ����� ���� commandPool �߰�.
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
    private String desc = null; // ����
    private Log logger = LogFactory.getLog(getClass());

    /**
     * ServletPath ���� ����
     */
    private String servletPath = "";
    /**
     * <templateName, templateInfo>: ���ø� ���� ����
     */
    private Map templateMap = new HashMap();

    /**
     * <name, ViewInfoConfig>: gloval-view�� ���ԵǾ� �ִ� �۷ι� �並 �����ϰ� �ִ�.
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
     * ServletPath�� uri, commandConf
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
     * uri �������� ActionConfig�� ���� �ش�.
     * ������ CommandNotFoundException�� ����Ŵ 
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
     * Command level�� servlet Path ����.
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