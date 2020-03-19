/*
 * Created on 2006. 07. 19
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.mdb.sqlhelper.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import maf.MafProperties;
import maf.MafUtil;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.WhereCond;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AbstractWhereBuilder implements SqlWhereBuilder {
	private Log logger = LogFactory.getLog(AbstractWhereBuilder.class);
	private List conds;
	
	public AbstractWhereBuilder() {
	}
	
	public SqlWhereBuilder insertCond(int idx, WhereCond cond) {
		if (conds == null) conds = new ArrayList(10);
		conds.add(idx, cond);
		
		return this;
	}
	
	public SqlWhereBuilder addCond(WhereCond cond) {
		if (conds == null) conds = new ArrayList(10);
		conds.add(cond);
		
		return this;
	}
	
	public String getWhereString(Map param) {
		StringBuffer sb = new StringBuffer();
		if (conds != null) {
			for (int i = 0; i < conds.size(); i++) {
				WhereCond re = (WhereCond) conds.get(i);
				if (re.hasField() && this.chkKey(re, param)) {

					if (sb.length() > 0) {
						sb.append(" AND ");
					}
//					if (re instanceof WhereIN) {
//						
//						sb.append(re.getWhereString());
//						sb.append("(");
//						String paramName = re.getParamName();
//						if (param.get(re.getParamName()) instanceof String[]) {
//							String[] sa = (String[]) param.get(paramName);
//							for (int j = 0; j < sa.length; j++) {
//								if (j > 0) sb.append(", ");
//								sb.append("'");
//								sb.append(sa[j]);
//								sb.append("'");
//							}
//						} else {
//							sb.append(" '");
//							sb.append(param.get(paramName));
//							sb.append("'");
//						}
//						sb.append(")");
//					} else {
						sb.append(re.getWhereString());
//					}

				}
			}
		}
		if(sb.length() > 0 ) {
			sb.insert(0, " WHERE ");
		}
		if(MafProperties.DEBUG) {
			logger.debug("where : " + sb);
		}
		return sb.toString();
	}
	
	private boolean chkKey(WhereCond re, Map param) {
		boolean chk = true;
		if (re.isForced()) {
//			logger.debug(re.getFieldName() + " is forced");
			return true;
		}
		
		String[] keys = re.getParamNames();
//		logger.debug("params : " + StringUtils.arrayToCommaDelimitedString(keys));
		for(int i = 0 ; i < keys.length; i++) {
			if(!param.containsKey(keys[i])) {
				chk = false;
				break;
			} else if(MafUtil.empty(param.get(keys[i]))) {
				chk = false;
				break;
			}
		}
		return chk;
	}

	
	public boolean hasConds() {
		return conds != null && conds.size() > 0;
	}

	
}

