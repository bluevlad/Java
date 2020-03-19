/*
 * Created on 2006. 4. 6.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeInfo {
	Map allListMap = new HashMap();
	Map allMapMap = new HashMap();

	
	public CodeInfo(List codeList) {
		if (codeList != null) {

			List cList = null;
			

			
			// group�� list �����.
			for (int i = 0; i < codeList.size(); i++) {
				CodeDetailBean bean = (CodeDetailBean) codeList.get(i);
				if (this.allListMap.containsKey(bean.getGroupCD())) {
					cList = (List) this.allListMap.get(bean.getGroupCD());
				} else {
					cList = new ArrayList();

					this.allListMap.put(bean.getGroupCD(), cList);
				}
				//System.out.println("  " + bean.getGroupCD() + ", " +  bean.getLang() + " added. ");
				cList.add(bean);
			}
			

			Map cMap = null;			

			for (int i = 0; i < codeList.size(); i++) {
				CodeDetailBean bean = (CodeDetailBean) codeList.get(i);
				if (this.allMapMap.containsKey(bean.getGroupCD())) {
					cMap = (Map) this.allMapMap.get(bean.getGroupCD());
				} else {
					cMap = new HashMap();
					this.allMapMap.put(bean.getGroupCD(), cMap);
				}

				cMap.put(bean.getCodeNo(), bean);
			}			
		}
	}
	
	/**
	 * �ش� �׷��� CodeDetailBean �� List ����� ���� ��.
	 * @param site
	 * @param groupCd
	 * @return
	 */
	public final  List getCodeList(String groupCd ) {

		List rv = null;
		
		if (this.allListMap.containsKey(groupCd)) {
			rv = (List) this.allListMap.get(groupCd);
		}

		if (rv == null) {
			rv = Collections.EMPTY_LIST;
		}
		return rv;
		
	}

	/**
	 * group�� code�� ������ �����´�.
	 * @param groupCd
	 * @param codeNo
	 * @return CodeDetailBean
	 */
	public final CodeDetailBean getCodeInfo(String groupCd,  String codeNo) {

		Map cMap = null;
		
		if (allMapMap.containsKey(groupCd)) {
			cMap = (Map) allMapMap.get(groupCd);
		}		

		if (cMap != null) {
			return (CodeDetailBean) cMap.get(codeNo);
		} else {
			return null;
		}
	}

	/**
	 * Code List Map �Ǵ� Code Map Map�� ������
	 * @param gbn (LIST | MAP);
	 * @return
	 */
	public final Map getInternalMap(String gbn) {
		if ("LIST".equals(gbn)) {
			return this.allListMap;
		} else {
			return this.allMapMap;			
		}
	}
}
