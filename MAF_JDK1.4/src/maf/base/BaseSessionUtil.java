package maf.base;

import maf.MafUtil;

public abstract class BaseSessionUtil {
	/**
	 * �����ϰ� String ������ �������� 
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
