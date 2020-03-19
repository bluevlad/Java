/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2008 rainend All rights reserved.
 */
package maf.context.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import maf.configuration.support.ReloadableConfigLoader;
import maf.context.exceptions.NoSuchBundleException;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.context.exceptions.NoSuchMessageException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 다국어 지원 메시지 정보를 보관함
 * 
 * @author bluevlad
 *
 */
public class MafResourceStore extends ReloadableConfigLoader {

	private Map bundleMap = null;

	private static MafResourceStore instance;

	private Log logger = LogFactory.getLog(MafResourceStore.class);

	private MafResourceStore() {
		bundleMap = new HashMap();
	}

	public static synchronized MafResourceStore getInstance() {
		if (instance == null) instance = new MafResourceStore();
		return instance;
	}



	public void addBundle2(String filename, String configFileName, String ruleFilePath) {

		MafResourceBundle rb = new MafResourceBundle();
		rb.setBundleName(filename);
		rb.setConfigFileName(configFileName);
		rb.setRuleFileName(ruleFilePath);

		synchronized (this.bundleMap) {
			rb = (MafResourceBundle) super.digest(rb);
			if (rb != null) {
				rb.setBundleName(filename);

				bundleMap.put(rb.getBundleName(), rb);
				System.out.println(filename + " added");
			}
		}
	}


	/**
	 * bundle, local, key에 맞는 resource 가져옴
	 * locale에 맍는 key가 없으면 default 값 가져옴
	 * @param bundleName
	 * @param locale
	 * @param key
	 * @return
	 * @throws NoSuchMessageException
	 * @throws NoSuchBundleException
	 */
	public static String getMessage(String bundleName, Locale locale, String key) throws NoSuchMessageException,
			NoSuchBundleException, NoSuchLocaleMessageException {
		MafResourceBundle resourceBundle = (MafResourceBundle) instance.getBundle(bundleName);
		if (resourceBundle != null) {
			return resourceBundle.getMessage(key, locale);
		} else {
			throw new NoSuchBundleException(bundleName);
		}
	}
	/**
	 * 
	 * @param bundleName
	 * @param locale
	 * @param key
	 * @return
	 * @throws NoSuchMessageException
	 * @throws NoSuchBundleException
	 */
	public static MafResourceLocale getMafResourceLocale(String bundleName, Locale locale, String key) throws NoSuchMessageException,
			NoSuchBundleException {
		MafResourceBundle resourceBundle = (MafResourceBundle) instance.getBundle(bundleName);
		if (resourceBundle != null) {
			return resourceBundle.getMafResourceLocale(key, locale);
		} else {
			throw new NoSuchBundleException(bundleName);
		}
	}
	/**
	 * @@deprecated
	 * Status 확인용  (bundle map을 돌려줌)
	 * @return
	 */
	public final Map getBundleMap() {
		return this.bundleMap;
	}

	public MafResourceBundle getBundle(String bundleName) throws NoSuchBundleException {
		MafResourceBundle rb = (MafResourceBundle) bundleMap.get(bundleName);
		if (rb == null ) {
			throw new NoSuchBundleException("[" + bundleName +"] not found!!!");
		}
		rb = (MafResourceBundle) super.getConfig(rb);
		if (rb != null && rb.getRefreshTimestamp() < 0) {
			synchronized (this.bundleMap) {
				if (bundleMap == null) {
					bundleMap = new HashMap();
				}
				bundleMap.put(bundleName, rb);
			}
		}
		return rb;
	}
	
	/**
	 * 하나의 메시지를 update 한다. 
	 * @param bundle
	 * @param key
	 * @param locale
	 * @param message
	 * @return
	 */
	public boolean updateOneMessage(String bundleName, String key, String locale, String message) {
		boolean chk = false;
		MafResourceBundle bundle = (MafResourceBundle) bundleMap.get(bundleName);
		if(bundle != null) {
			chk = bundle.updateMessage(key, locale, message);
			bundleMap.put(bundleName, bundle);
		} else {
			logger.debug("bundle [" + bundleName + "] is null");
		}
		return chk;
	}

	/**
	 * resource config 파일을 저장 한다. 
	 * 
	 * @param bundleName
	 * @return
	 */
	public boolean saveResourceConfig(String bundleName) {
		boolean chk = false;
		MafResourceBundle bundle = (MafResourceBundle) bundleMap.get(bundleName);
		if(bundle != null) {
			chk = ResourceManager.saveBundle(bundle);
		} else {
			logger.debug("can't get bundle : " + bundleName);
		}
		
		return chk;
	}
}
