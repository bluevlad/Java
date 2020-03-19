package maf.web.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.aam.AAMManager;
import maf.aam.bean.AAMBean;
import maf.base.BaseHttpServlet;
import maf.base.BaseHttpSession;
import maf.web.context.MafActionConfigStore;
import maf.web.context.PathInfo;
import maf.web.context.PathInfoUtil;
import maf.web.exception.ActionConfigurationNotFoundException;
import maf.web.mvc.action.MafCommand;
import maf.web.mvc.beans.MvcInfo;
import maf.web.mvc.beans.SiteInfo;
import maf.web.mvc.configuration.ActionConfig;
import maf.web.mvc.configuration.ActionConfiguration;
import maf.web.mvc.configuration.TemplateInfoConfig;
import maf.web.mvc.configuration.TemplateMapConfig;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
import maf.web.mvc.exception.CommandNotFoundException;
import maf.web.mvc.menu.MenuItem;
import maf.web.mvc.menu.TreeMenu;
import maf.web.mvc.pool.MvcResourcePool;
import maf.web.mvc.view.Render;
import maf.web.session.MySession;
import maf.web.session.exception.SessionInvalidatedException;
import maf.web.session.exception.UnAuthorizedException;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 클라이언트의 요청 URI에 따라서 알맞은 처리를 해주는 컨트롤러.
 * 
 * @author 김상준
 */
public class ControllerServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String GLOBAL_CONFIG = "ROOT";
	private final Log logger = LogFactory.getLog(ControllerServlet.class);


	/**
	 * 실제 Command 를 Call 하는 부문
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionConfiguration configuration = null;
		ActionConfiguration globalConfiguration = null;
		ViewInfoConfig viewInfo = null;
		
		PathInfo pathInfo = PathInfoUtil.getPathInfo(request);
		
//		Map mvc_info = null;

		request.setCharacterEncoding(MiConfig.DEFAULT_CHARACTER_ENCODING);
		
		// 강제 GC
		if (MiConfig.USE_MAF_GC) {
			maf.lib.system.JavaGCreq.getInstance().chk();
		}
		
		try {
			globalConfiguration =  MafActionConfigStore.getInstance().getCommandConfig("ROOT");
			configuration =  MafActionConfigStore.getInstance().getCommandConfig(pathInfo.getServletPath());
			if (configuration == null) {
				throw new CommandNotFoundException("Configuration : " 
				                           + pathInfo.getServletPath() + ", " 
				                           + request.getRemoteAddr() + " " 
				                           + pathInfo.getRequestURI()+ 
				                           " Not found" + "\n" + configuration);
			}
			
			// uri 기준으로 수행될 class 정보 가져 오기
			ActionConfig commandConfig = configuration.getCommandConfig(pathInfo);
			request.setAttribute(ActionConfig.PARAM_KEY, commandConfig.getParam());
			
            SiteInfo oSite = pathInfo.getSiteInfo();
            if(oSite == null) {
            	throw new CommandNotFoundException("Site not init !!! default site = [" +MiConfig.DEFAULT_SITE+ "]");
            }
            TreeMenu oTm = TreeMenu.getInstance(oSite.getSite());
            String pgid = oTm.findPGID(pathInfo.getMUri());
            MenuItem oM = oTm.getMenu(pgid);
            
            if(pgid != null) {
            	BaseHttpSession s = MySession.getSessionBean( request, response );
            	if(oM != null) {
            		AAMBean aam = oTm.getAuthInfo(pgid, s);
//            		logger.debug("pgid : " + pgid + " " + aam);
           			request.setAttribute(AAMManager.AUTH_INFO_KEY, aam);
            	}
            	// 메뉴기준으로 권한 체크(sesseion 사용 유무만 
                if(oM!= null && oM.isSession_chk()) {
                	if(s != null) {
                		if(! oTm.chkAuth_READ(oM.getPgid(), s)) {
                			throw new UnAuthorizedException(" PAGE 접근 권한이 없습니다. ");
                		}
                	} else {
                		throw new SessionInvalidatedException(" Login이 필요합니다. ");
                	}
                }
                

            }
            
			// contextURI를 기준으로 실제 수행될 class 찾아옴 
			MafCommand command = null;

//			String className = commandConfig.getType();//configuration.getClassName(pathInfo.getContextURI());
			Class commandClass = Class.forName(commandConfig.getType());
			
			if(commandClass == null) {
				throw new CommandNotFoundException("CommandMap Not Found!!!" +pathInfo.getContextURI());
			}

			//	Common pool에서 사용된 리소스를 가져온다.
			command = MvcResourcePool.getInstance().getObject(commandClass);
			
			if (command == null) {
				throw new CommandNotFoundException("Action Class Not Found!!!" + commandConfig.getType());
			} else {
				try {
					ViewInfoMap viewInfoMap = new ViewInfoMap(globalConfiguration.getGlobalViewMap(), configuration.getGlobalViewMap(),
							commandConfig.getViewMap());
	
					// 해당 Command 를 수행 한다.
					viewInfo = command.execute(commandConfig, viewInfoMap, request, response);
				
				}finally {
					// Common pool에서 사용된 리소스를 반환 한다.
					MvcResourcePool.getInstance().returnObject(commandClass, command);
				}
			}
			
			if (viewInfo == null) {
				logger.error("viewInfoMap Error: " + request.getRemoteAddr() + ":" + pathInfo.getRequestURI());
				throw new CommandNotFoundException("viewInfoMap Error: " + request.getRemoteAddr() + ":" + pathInfo.getRequestURI());
			}

			if (viewInfo.isRedirect()) {
				response.sendRedirect(pathInfo.getContextPath() + "/" + response.encodeRedirectURL(viewInfo.getUri()));

			} else {
				
				MvcInfo mvcInfo = new MvcInfo();
				
				if (viewInfo.isUseTemplate()) {
					TemplateInfoConfig templateInfo = null;
					if ("print".equals(request.getParameter("_template"))) {
						templateInfo = globalConfiguration.getTemplateInfoConfig("print");
					} else {
						templateInfo = configuration.getTemplateInfoConfig(viewInfo.getTemplateName());
						if (templateInfo == null) {
							if (globalConfiguration != null) {
								templateInfo = globalConfiguration.getTemplateInfoConfig(viewInfo.getTemplateName());
							}
						}
					}
					if (templateInfo != null) {

						viewInfo.setTemplateFile(templateInfo.getUri(oSite));
						List mapList = templateInfo.getTemplateMapConfigList();
//						mvc_info = new HashMap();
						if (mapList != null && mapList.size() > 0) {
							for (int i = 0; i < mapList.size(); i++) {
								TemplateMapConfig mapConfig = (TemplateMapConfig) mapList.get(i);
								if (mapConfig != null) {
//									mvc_info.put(mapConfig.getName(), mapConfig.getUri());
									mvcInfo.addMapConfig(mapConfig.getName(), mapConfig.getUri());
								}
							}
						}
						mvcInfo.setTemplateInfo(templateInfo);
			            
					}
				} else {
//					logger.debug("no use template:" + viewInfo.getTemplateName());
				}
				
				// page 정보 를 bean으로 제공
				mvcInfo.setViewInfo(viewInfo);
				String title = MafUtil.nvl(viewInfo.getTitle(), commandConfig.getTitle());
				title = MafUtil.nvl(title,viewInfo.getTitle());
				mvcInfo.setTitle(title);
				
				mvcInfo.setTreeMenu(oTm);
				mvcInfo.setCurMenu(oM);
				// 게시판의 경우만 사용 
				String tpgid = viewInfo.getPgid();
				if(tpgid != null) {
					pgid = tpgid;
				} 
				mvcInfo.setPgid(pgid);
				mvcInfo.setSiteInfo(oSite);
				mvcInfo.setClassName(commandConfig.getType());
				
				if (MySession.getSessionBean(request, response) != null) {
                	mvcInfo.setLogin(true);
                } else {
                	mvcInfo.setLogin(false);

				}
				request.setAttribute("MAF_INFO", mvcInfo);

				Render render = new Render(viewInfo);
				
				render.render(request,response);
			}
		} catch (Throwable e) {
			ErrorProcess.sendError(response, e);
		}
	}
	


	public static TemplateInfoConfig getTemplate(String layout) throws ActionConfigurationNotFoundException {

		ActionConfiguration globalConfiguration =  MafActionConfigStore.getInstance().getCommandConfig("ROOT");
		return globalConfiguration.getTemplateInfoConfig(layout);
	}



}