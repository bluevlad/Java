package maf.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import maf.MafUtil;
import maf.base.BaseObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Hashtable ����ü�� ��ȣȭ�� String���� �����ϴ� ���
 * 
 * @author bluevlad
 * 
 */
public class SerializeHashtable extends BaseObject {
	private	Log logger = LogFactory.getLog(SerializeHashtable.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String SERIALISVALID = "_SERIALISVALID";

	private Map ht = null;

	private boolean isValid = false;

	public SerializeHashtable() {
		
		isValid = true;
	}

	public boolean isValid() {
		return this.isValid;
	}

	public void cleat() {
		ht.clear();
	}

	public SerializeHashtable(String str)    {
		if (str != null) {
			try {
				str = URLDecoder.decode(str,"UTF-8");
				this.ht = SerializeHashtableUtil.str2hashtable(str);

				if (ht != null && "T".equals(ht.get(SERIALISVALID))) {
					isValid = true;

				}
			} catch (UnsupportedEncodingException e) {
				logger.error("Err _UnSerialUrl :" + e.getMessage());
			}
		} else {
			this.ht = new HashMap();
		}

	}

	public String getSerializeUrl()   {
		try {
			
			return URLEncoder.encode(SerializeHashtableUtil.hashtable2str(this.ht),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Err _UnSerialUrl :" + e.getMessage());
			return null;
		}		
	}

	public void remove(String key) {
		ht.remove(key);
	}

	public String get(String key, String def) {
		String sT = null;
		if (ht !=null && ht.containsKey(key)) {
			sT = (String) ht.get(key);
			if (sT != null && !"".equals(sT.trim())) {
				return sT;
			}
		}
		return def;
	}

	public String get(String Key) {
		return get(Key, "");
	}

	public int getInt(String key, int def) {
		if (ht.containsKey(key)) {
			return MafUtil.parseInt((String) ht.get(key), def);
		} else {
			return def;
		}
	}

	public int getInt(String key) {
		return getInt(key, 0);
	}

	public void put(String key, String value) {
		if(ht != null && key != null) {
//			logger.debug("class:" + ht.getClass());
			ht.put(key, value);
		}
	}

	public int size() {
		return ht.size();

	}

	// public String toStringX() {
	// return ht.toString();
	// }

	public boolean containsKey(String Key) {
		return ht.containsKey(Key);
	}

	public boolean isEmpty() {
		return ht.isEmpty();
	}

	public void clear() {
		this.ht.clear();
	}

//	public final Enumeration keys() {
//		return this.ht.keySet();
//	}

	/**
	 * ����� ������ �����ش�. JSTL���� ���� �ٷ� ���� ���� �ϰ�
	 * 
	 * @return
	 */
	public final Map getHt() {
		return this.ht;
	}
	/**
	 * ���� �ѿ��� ���� ���߿� �°��� merge �Ѵ�.
	 * @param _req
	 * @param searchFields
	 * @return
	 */
	public Map getMergedParam(HttpServletRequest _req, String[] searchFields ) {
		
		Map reqMap = _req.getParameterMap();
		Map param = new HashMap();
		for(int i = 0 ;i < searchFields.length; i++) {
			// ���� �ѿ��� ������ Ȯ
			
			if (reqMap.containsKey(searchFields[i])) {
				param.put(searchFields[i], _req.getParameter(searchFields[i]));
				
				this.put(searchFields[i], _req.getParameter(searchFields[i]));
			} else {
				param.put(searchFields[i], this.get(searchFields[i]));
				
			}
		}
		return param;
	}
}
