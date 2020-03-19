/*
 * Created on 2005. 11. 25.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.tags.old;

import java.util.List;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.configuration.CodeDetailBean;
import maf.configuration.CodeInfo;
import maf.web.context.MStore;

public class MiRoutine {


	/**
	 * �ش�׷��� Code List �������� 
	 * @param group_cd
	 * @return
	 */
	public static List getCodeDetList(String site, String groupCd) {
		CodeInfo codeInfo = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
		
		return codeInfo.getCodeList(groupCd);
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
		rv = codeInfo.getCodeInfo(groupCd,  codeNo);
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

