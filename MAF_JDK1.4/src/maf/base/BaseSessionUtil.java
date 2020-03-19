package maf.base;

import maf.MafUtil;

public abstract class BaseSessionUtil {
	/**
	 * 안전하게 String 값으로 가져오기 
	 * @param sessionBean
	 * @param key
	 * @return
	 */
	protected static String _getStringAttribute(BaseHttpSession sessionBean, String key) {
		if (sessionBean != null) {
			return MafUtil.getString(sessionBean.getAttribute(key));
		} else {
			return null;
		}
	}
}
