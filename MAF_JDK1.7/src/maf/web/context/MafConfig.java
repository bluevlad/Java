/*
 * Created on 2005. 11. 30.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.context;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import maf.beans.FileInfo;
import maf.context.i18n.MafResourceStore;
import maf.util.FileUtils;
import maf.util.StringUtils;
import maf.web.servlet.LocaleResolver;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

public class MafConfig {
	Log logger = LogFactory.getLog(getClass());

	public final static String MAF_ATTRIBUTE_MESSAGE = "message";


	public static final RE reg_XML = new RE("\\.xml$|\\/$",RE.MATCH_CASEINDEPENDENT);
	public static final RE reg_LASTSLASH = new RE("\\/$");

	private ServletContext servletContext = null;

	private LocaleResolver localeResolver = null;

	private static MafConfig _instance =null;
	private String[] welcomeFiles = {"index.html"};
//	private Map comandConfigMap = null;

	private MafConfig() {
		this.localeResolver =MiConfig.MAF_LOCALE_RESOLVER;
	}

	public static synchronized MafConfig getInstance() {

		if (_instance == null) {
			_instance = new MafConfig();
		}
		return _instance;
	}

	public String[] getWelcomeFiles() {
		return this.welcomeFiles;
	}

	/**
	 * @return Returns the servletContext.
	 */
	public static final ServletContext getServletContext() {
		return _instance.servletContext;
	}

	/**
	 * @param servletContext
	 *            The servletContext to set.
	 */
	protected void setServletContext(ServletContext ctx) {
		String[] t = {"index.jsp", "index.html"};
		this.servletContext = ctx;
		synchronized (welcomeFiles) {
			try{
				welcomeFiles = (String[]) ctx.getAttribute("org.apache.catalina.WELCOME_FILES");
			} catch (Exception e) {
				welcomeFiles = t;
			}
		}
		
	}

	public static String getRealPath(String path) {
		return _instance.servletContext.getRealPath(path);
	}

	public static String getRealPath() {
		return _instance.servletContext.getRealPath("/");
	}



	public static String getContextPath() {
		return _instance.servletContext.getServletContextName();
	}



	protected synchronized void init() {
		
		this.configureMVC("/WEB-INF/maf-action/", "/WEB-INF/maf-config/MvcDigesterRules.xml");
		this.configureResource("/WEB-INF/maf-resource/", "/WEB-INF/maf-config/resourceDigesterRules.xml");
		
	}
	
	/**
	 * Command Config 설정 
	 * @param path
	 * @param rulefile
	 */
	private synchronized void configureMVC(String path, String rulefile) {
		System.out.println(">>>> MVC Action Confiure Start ");
		String configFilePath = this.servletContext.getRealPath(path);
		FileInfo[] files = FileUtils.getFileList(configFilePath);

		FileInfo configFile = null;
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				configFile = files[i];

				if(MafConfig.reg_XML.match(configFile.getFilename())) {
					String parameterName = StringUtils.stripFilenameExtension(configFile.getFilename());
					if (parameterName != null) {
						try {
	
							configureMVCConfigNew(parameterName, configFile.getFullFileName(),
										this.servletContext.getRealPath(rulefile));
						} catch (Exception e) {
						}
					}
				} else {
					System.out.println("    " + configFile.getFilename() + " is not xml file ");
				}
			}
		} else {
			System.out.println(" !!! files is null ");
		}

		System.out.println("<<<< MVC Action Confiure  Finished ");
	}
	/**
	 * ServletPath에대한 설정정보 configFilePath의 xml파일을 읽어 드린다.
	 * 
	 * @param servletPath
	 * @param configFileName
	 * @throws ServletException
	 */
/*	private synchronized void configureMVCConfig(String servletPath, String configFileName, String ruleFilePath) {
		ActionConfiguration configuration = null;
		try {
			// File configurationFile = new File(configFilePath);
			System.out.println("   >> Configure servletPath : [" + servletPath + "] Start ...");
			// configuration = ConfigurationDigester.digest(configFilePath,
			// ruleFilePath);
			
			if (comandConfigMap == null) {
				comandConfigMap = new HashMap();
			}

			configuration = (ActionConfiguration) XMLDigester.digest(configFileName, ruleFilePath);
			configuration.setServletPath(servletPath);
			CommandFactory factory = CommandFactory.getInstance(configuration.getServletPath());
			factory.setConfiguration(configuration);
			comandConfigMap.put(servletPath, configuration);
			System.out.println("   << Configure servletPath : [" + servletPath + "] Finished ");
			
		} catch (ConfigurationDigesterException ex) {
			logger.error("ConfigurationDigesterException failue on " + servletPath + ", ConfigFile is " + configFileName);

		} catch (Exception e) {
			logger.error("configure failue on " + servletPath + ", ConfigFile is " + configFileName);
		}
	}*/
	private synchronized void configureMVCConfigNew(String filename, String configFileName, String ruleFilePath) {
		StringBuffer st = new StringBuffer(100);
		try {

			st.append("   >> Configure MVC config : [" + filename + "] Start ...");

			MafActionConfigStore resourceStore = MafActionConfigStore.getInstance();
			resourceStore.addBundle2( filename, configFileName, ruleFilePath);
			st.append("   <<  Finished ");
		} catch (Exception e) {
			st.append("\nconfigure failue on " + filename + ", ConfigFile is " + configFileName);
			logger.error("configure failue on " + filename + ", ConfigFile is " + configFileName);
		}
		System.out.println(st.toString());
	}
	
	/**
	 * Validator Config 설정 
	 * @param path
	 * @param rulefile
	 */
	private synchronized void configureResource(String path, String rulefile) {
		StringBuffer st = new StringBuffer(100);
		st.append(">>>> MVC Resource Confiure Start ");
		String configFilePath = this.servletContext.getRealPath(path);
		FileInfo[] files = FileUtils.getFileList(configFilePath);

		FileInfo configFile = null;
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				configFile = files[i];

				if(MafConfig.reg_XML.match(configFile.getFilename())) {
					String filename = StringUtils.stripFilenameExtension(configFile.getFilename());
					if (filename != null) {
						try {
	
							configureResourceConfig(filename, configFile.getFullFileName(),
										this.servletContext.getRealPath(rulefile));
						} catch (Exception e) {
						}
					}
				} else {
					st.append("\n     [" + configFile.getFilename() + "] is not xml file ");
				}
			}
		} else {
			st.append(" !!! files is null ");
		}

		st.append("\n<<<< MVC Resource Confiure  Finished ");
		logger.debug(st);
	}
	
	/**
	 * ServletPath에대한 설정정보 configFilePath의 xml파일을 읽어 드린다.
	 * 
	 * @param servletPath
	 * @param configFileName
	 * @throws ServletException
	 */
	private synchronized void configureResourceConfig(String filename, String configFileName, String ruleFilePath) {
		try {
			MafResourceStore resourceStore = MafResourceStore.getInstance();
			resourceStore.addBundle2( filename, configFileName, ruleFilePath);
		} catch (Exception e) {
			logger.error("configure failue on " + filename + ", ConfigFile is " + configFileName);
		}

	}
	
//	public ActionConfiguration getCommandConfig(String servletPath) {
//		return (ActionConfiguration) comandConfigMap.get(servletPath);
//	}

//	public final Map getCommandConfigMap() {
//		return comandConfigMap;
//	}	
	
	public Locale resolveLocale(HttpServletRequest request) {
		return this.localeResolver.resolveLocale(request);
	}
}
