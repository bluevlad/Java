/*
 * Created on 2006. 6. 7.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import maf.MafConstant;
import maf.MafUtil;
import maf.logger.Trace;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SerializeHashtableUtil {
	
	static Log logger = LogFactory.getLog(SerializeHashtableUtil.class);
	private final static String URL_ENCODE= "UTF-8";
	
	/**
	 * String(key=value;값을) hashtable로 만들어 넘겨준다. 
	 * @param st
	 * @return
	 */
	public static Map str2hashtable(String st) {
		Map ht = new HashMap();
		try {
			
			String[] sParam = st.split(";");
			String key = null;
	
			for (int i = 0; i < sParam.length; i++) {
				String[] sData = sParam[i].split("=");
				if(sData != null && sData.length > 0) {
					key = sData[0];
					// LISTOP자체는 배열에서 뺌ob.getFl_read().indexOf(os.getUtype())
					if (!MafConstant.LIST_OP_NAME.equals(key)) {
						if (sData.length > 1) {
							ht.put(key, URLDecoder.decode(sData[1], URL_ENCODE));
						} else if (sData.length == 1) {
							ht.put(key, "");
						}
					}
				}
			}
		} catch (Exception e) {
			maf.logger.Logging.trace(e);
			logger.error(e.getMessage());
			ht.clear();
		}
		return ht;
	}
	
	/**
	 * HashTable의 값을 key1=urlencode(value1);key2=urlencode(value2); 형태의 String으로 돌려 준다. 
	 * @param ht
	 * @return
	 */
	public static String hashtable2str(Map ht) {
		if (ht !=null) {
			try {
				StringBuffer st = new StringBuffer();

				ht.put(SerializeHashtable.SERIALISVALID, "T");
//				Enumeration ek = ht.keys();
//				while (ek.hasMoreElements())
//				{
//					key = (String) ek.nextElement();
//					st.append(key);
//					st.append("=");
//					st.append(URLEncoder.encode((String)ht.get(key), URL_ENCODE));
//					st.append(";");
//				}
//				 맵에 요소들을 넣는다.
				Set set = ht.entrySet();
//				 반복자를 얻는다.
				Iterator i = set.iterator();
//				 요소들을 출력한다.
				while(i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					st.append(me.getKey());
					st.append("=");
					st.append(URLEncoder.encode(MafUtil.nvl((String)me.getValue(),""), URL_ENCODE));
					st.append(";");					
				}
				return st.toString();
			} catch (Exception e) {
				logger.error(Trace.getStackTrace(e));
				return null;
			}
		} else {
			logger.error("Hashtable ht is null");
			return null;
		}
	}
}

