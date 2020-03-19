/*
 * Created on 2005. 11. 25.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.web.tag;

import java.util.List;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.configuration.CodeDetailBean;
import maf.configuration.CodeInfo;
import maf.configuration.TableDictionary;
import maf.configuration.TableDictionaryBean;
import maf.logger.Logging;
import maf.web.context.MStore;

public class MiRoutine {


	/**
	 * �ش�׷��� Code List �������� 
	 * @param group_cd
	 * @return
	 */
	public static List getCodeDetList(String site, String groupCd) {
		CodeInfo codeInfo = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
		
		return codeInfo.getCodeList(groupCd, site);
	}
	
	
	/**
	 * �ش�׷��� �ڵ� ���� ���� 
	 * @param group_cd
	 * @param codeNo
	 * @return
	 */
	public static CodeDetailBean getCodeDet(String site, String groupCd, String codeNo) {
		CodeDetailBean rv = null;
		
		CodeInfo codeInfo = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
		rv = codeInfo.getCodeInfo(groupCd, site, codeNo);
		return rv;
	}
	
	/**
	 * table ���� �������� 
	 * @param site
	 * @param key
	 * @return
	 */
	public static TableDictionaryBean getTableDictionary(String site, String key) {
		TableDictionaryBean rv = null;
		TableDictionary t = (TableDictionary) MStore.getInstance().getConfig(MStore.KEY_TABLE_DICTIONARY);
		try {
			rv =  t.getInfo(site, key);
		} catch (Exception e) {
			Logging.trace(e); 
		} 
		return rv;
	}
	/**
	 * ��� �����ٶ� ���� �����ֱ�
	 * @param navigator
	 * @param count
	 * @return
	 */
	public static String getListSeq(NavigatorInfo navigator, Object count) {
		long i = 0;
		if (navigator == null || count == null) {
			return "";
		} else {
			i = navigator.getTotalCount() - ( navigator.getPageSize() * ( navigator.getCurrentPage() - 1) + MafUtil.parseLong(count.toString()) - 1);
			return i+"";
		}
		
	}
}

