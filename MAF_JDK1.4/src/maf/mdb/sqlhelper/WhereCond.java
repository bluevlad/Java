package maf.mdb.sqlhelper;

import java.util.ArrayList;
import java.util.List;

import maf.MafUtil;

import org.apache.regexp.RE;

/**
 * 하나의 검색 조건을 나타내는 클래스들이 구현해야 할 인터페이스
 */
public abstract class WhereCond {
	// DB Field name
	protected String fieldName = null;
	protected String paramName = null;
	protected boolean force = false;
	protected final static RE re = new RE("(:[\\w*]+)|(:[@\\w*]+)");

	public abstract String getWhereString();

	public String getParamName() {
		String[] t = this.getParamNames();
		if (t!=null && t.length > 0 ) {
			return t[0];
		} else {
			return null;
		}
	}

	public String[] getParamNames() {
		List at = new ArrayList();
        if(paramName != null ) {
    		String tParam = paramName.substring(0);
    		while (re.match(tParam)) {
    			tParam = re.subst(tParam, "?", RE.REPLACE_FIRSTONLY);
    			String key = re.getParen(0).substring(1).toLowerCase();
    			at.add(key);
    		}

    		return (String[]) at.toArray(new String[0]);
        } else {
            return new String[] {};
        }
	}

	public  boolean isForced() {
		return force;
	}

	public boolean hasField() {
		return MafUtil.empty(this.fieldName)?false:true;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

}
