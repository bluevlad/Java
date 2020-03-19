/*
 * Created on 2005. 11. 30.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.context;

import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import maf.beans.FileInfo;
import maf.lib.xml.XMLDigester;
import maf.util.ClassUtils;
import maf.util.FileUtils;
import maf.util.StringUtils;
import maf.web.context.support.BasemafHandler;
import maf.web.context.support.MafWebListner;
import maf.web.context.support.ResourceLoaderByXML;
import maf.web.mvc.beans.SiteInfo;
import maf.web.servlet.MafLocaleChangeInterceptor;
import maf.web.servlet.LocaleResolver;
import maf.web.servlet.SiteResolver;
import maf.web.servlet.support.CookieLocaleResolver;
import maf.web.servlet.support.HeaderSiteResolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

public class MafConfig {
	private final Log logger = LogFactory.getLog(getClass());

	public final static String MAF_ATTRIBUTE_MESSAGE = "message";
	public static final RE reg_XML = new RE("\\.xml$|\\/$", RE.MATCH_CASEINDEPENDENT);
	public static final RE reg_LASTSLASH = new RE("\\/$");
	
	private MafConfigBean configBean = null;
	private ServletContext servletContext = null;
	private LocaleResolver localeResolver = null;
	private SiteResolver siteResolver = null;
	private static MafConfig _instance = new MafConfig();
	private String[] welcomeFiles = { "index.html" };

	//	private Map comandConfigMap = null;
	private MafConfig() {
	}

	public static synchronized MafConfig getInstance() {
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
		String[] t = { "index.jsp", "index.html" };
		this.servletContext = ctx;
		synchronized (welcomeFiles) {
			try {
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
		
		return _instance.configBean.getContextPath();
	}

	public static String getContextImagePath() {

		return _instance.configBean.getContextImagePath();
	}

	protected synchronized void init() {
		
		// 설정 파일읽기 
		try {
			this.configMaf();
		} catch (Throwable e) {
			System.out.println(this.getClass() + ":  configMaf ERROR !!! " + e.getMessage());
		}
		
		// MVC Action 초기화 
		try {
			this.configureMVC("/WEB-INF/maf-action/", "/WEB-INF/maf-config/MvcDigesterRules.xml");
		} catch (Throwable e) {
			System.out.println(this.getClass() + ":  configureMVC ERROR !!! " + e.getMessage());
		}
		
		
		// Resource 초기화 
		try {
			ResourceLoaderByXML.getInstance().configureResource(this.servletContext);
		} catch (Throwable e) {
			System.out.println(this.getClass() + ":  ResourceLoaderByXML ERROR !!! " + e.getMessage());
		}
		// Locale Chage 핸들러 등록
		BasemafHandler obj = new MafLocaleChangeInterceptor();
		MafWebListner.addHandler(obj);
	}

	/**
	 * WEB-INF_maf.xml 파일 읽어 드림
	 *
	 */
	private synchronized void configMaf() {
		try {
			this.configBean = (MafConfigBean) XMLDigester.digest(this.servletContext.getRealPath("/WEB-INF/maf-config.xml"), 
			                                this.servletContext.getRealPath("/WEB-INF/maf-config/maf-config-digesterRules.xml"));
//			this.context_path = obj.getContextPath();
//			this.context_image_path = obj.getContextImagePath();
//			this.localeResolver = MafProperties.JMF_LOCALE_RESOLVER;
			try {
				this.localeResolver = (LocaleResolver) ClassUtils.forName(configBean.getLocale_resolver()).newInstance();
				if(this.localeResolver == null) {
					throw new Exception(configBean.getLocale_resolver() + " not found ");
				} else {
					System.out.println("  Locale Rresolver : " + localeResolver.getClass());
				}
			} catch (Exception e) {
				logger.error(" Can't load " + configBean.getLocale_resolver() + " !!! \n " );
				this.localeResolver = new CookieLocaleResolver();
				System.out.println("  Locale Rresolver : " + localeResolver.getClass());
			}
			
			try {
				this.siteResolver = (SiteResolver) ClassUtils.forName(configBean.getSite_resolver()).newInstance();
				System.out.println("  Site Resolver : " + siteResolver.getClass());
			} catch (Exception e) {
				logger.error(" Can't load " + configBean.getLocale_resolver() + " !!! \n " );
				this.siteResolver = new HeaderSiteResolver();
				System.out.println("  Site Resolver : " + siteResolver.getClass());
			}
			
			System.out.print(" ============== \n" + configBean);
		} catch (Exception e) {
			logger.error("maf-config.xml parsing error!!! \n" + e.getMessage());
		}
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

				if (MafConfig.reg_XML.match(configFile.getFilename())) {
					String parameterName = StringUtils.stripFilenameExtension(configFile.getFilename());
					if (parameterName != null) {
						try {
							configureMVCConfigNew(parameterName, configFile.getFullFileName(),
													this.servletContext.getRealPath(rulefile));
						} catch (Exception e) {}
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

	private synchronized void configureMVCConfigNew(String filename, String configFileName, String ruleFilePath) {
		StringBuffer st = new StringBuffer(100);
		try {

			st.append("   >> Configure MVC config : [" + filename + "] Start ...");

			MafActionConfigStore resourceStore = MafActionConfigStore.getInstance();
			resourceStore.addBundle2(filename, configFileName, ruleFilePath);
			st.append("   <<  Finished ");
		} catch (Exception e) {
			st.append("\nconfigure failue on " + filename + ", ConfigFile is " + configFileName);
			logger.error("configure failue on " + filename + ", ConfigFile is " + configFileName);
		}
		System.out.println(st.toString());
	}

	public static Locale resolveLocale(HttpServletRequest request) {
		if(_instance == null) {
			System.out.println("MafConfig not init!!!");
			return request.getLocale();
		} else {
			if(_instance.localeResolver == null) {
				_instance.localeResolver = new CookieLocaleResolver();
			}
			return _instance.localeResolver.resolveLocale(request);
		}
	}
	
	public static SiteInfo resolveSite(HttpServletRequest request) {
		if(_instance.siteResolver == null)  {

			_instance.siteResolver = new HeaderSiteResolver();
		}
		return _instance.siteResolver.resolveSite(request);
	}
	
	public static LocaleResolver getLocaleResolver() {
		return _instance.localeResolver;
	}
}
