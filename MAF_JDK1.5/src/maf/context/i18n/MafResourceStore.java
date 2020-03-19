/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.context.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import maf.configuration.support.ReloadableConfigLoader;
import maf.context.NoSuchBundleException;
import maf.context.NoSuchMessageException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	//	public void addBundle( MafResourceBundle bundle) {
	//
	//		synchronized (this.bundleMap) {
	//			if(bundleMap == null) {
	//				bundleMap = new HashMap();
	//			}
	//			bundleMap.put(bundle.getBundleName(), bundle);
	//		}
	//	}

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
//				logger.debug(filename + " added");
			}
		}
	}

	//	public String getMessage(String bundleName, String localeString, String key, String site) throws NoSuchMessageException, NoSuchBundleException {
	//		MafResourceBundle resourceBundle = (MafResourceBundle) bundleMap.get(bundleName);
	//		if( resourceBundle != null ) {
	//			return resourceBundle.getMessage(key, localeString);
	//		} else {
	//			throw new NoSuchBundleException( bundleName );
	//		}
	//	}
	public String getMessage(String bundleName, Locale locale, String key, String site) throws NoSuchMessageException,
			NoSuchBundleException {
		MafResourceBundle resourceBundle = (MafResourceBundle) this.getBundle(bundleName);
		if (resourceBundle != null) {
			return resourceBundle.getMessage(key, locale);
		} else {
			throw new NoSuchBundleException(bundleName);
		}
	}
	
	public MafResourceLocale getMafResourceLocale(String bundleName, Locale locale, String key) throws NoSuchMessageException,
			NoSuchBundleException {
		MafResourceBundle resourceBundle = (MafResourceBundle) this.getBundle(bundleName);
		if (resourceBundle != null) {
			return resourceBundle.getMafResourceLocale(key, locale);
		} else {
			throw new NoSuchBundleException(bundleName);
		}
	}
	/**
	 * @@deprecated
	 * Status 확인용 
	 * @return
	 */
	public final Map getBundleMap() {
		return this.bundleMap;
	}

	private MafResourceBundle getBundle(String bundleName) throws NoSuchBundleException {
		MafResourceBundle rb = (MafResourceBundle) bundleMap.get(bundleName);
		if (rb == null ) {
			throw new NoSuchBundleException("[" + bundleName +".xml not found!!!");
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

}
