/*
 * Created on 2005. 12. 17.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.aam;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import maf.aam.bean.MafRole;
import maf.base.BaseHttpSession;
import maf.base.UserRoles;

/**
 * 
 * MafConfig�� getUserManger�� ���� ����Ͻñ⸦
 * 
 * @author goindole
 * 
 */
public class MafUserManager {
	// private Map userType = null;

	// protected void setUserTypes(Map map) {
	// this.userType = map;
	// }

	/**
	 * Super ����� ���� Ȯ��
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isSuperAdmin(BaseHttpSession s) {
		// Map roleMap = s.getRoleMap();
		UserRoles ur = s.getUserRole();
		Set set = ur.getRoleMap().entrySet();
		Iterator iter = set.iterator();

		while (iter.hasNext()) {
			Map.Entry me = (Map.Entry) iter.next();
			MafRole usr = (MafRole) me.getValue();
			if (usr.isRoot()) {
				return true;
			}
		}
		return false;
	}



}
