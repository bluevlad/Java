package maf.web.tags.old;

import java.util.Collection;
import java.util.Map;

import maf.MafUtil;
import maf.base.BaseHttpSession;

public class MiFunction {
	/**
	 * String Object �� lastIndexOf �� tagLibrary�� 
	 * @param obj
	 * @param srch
	 * @return
	 */
	public static int lastIndexOf(String obj, String srch) {
		if(obj != null) {
			return obj.lastIndexOf(srch);
		} else {
			return 0;
		}
	}
	
	/**
	 * Object target �� key �� ���ԵǾ� �ִ��� ó��
	 *  target �� String�̸� instring
	 *  target �� Map �̸� contain
	 *  target�� Array �� �� Ȯ�� 
	 * @param target
	 * @param key
	 * @return
	 */
	public static boolean contains(Object target, Object key) {
		return MiFunction.contains(target, key, false);
	}
	/**
	 * Object target �� key �� ���ԵǾ� �ִ��� ó��
	 *  target �� String�̸� instring
	 *  target �� Map �̸� contain
	 *  target�� Array �� �� Ȯ�� 
	 *  Array �� ��� �� ��ġ�ϴ� ���� �ֳ� Ȯ�� (String ������ �� )
	 * @param target
	 * @param key
	 * @param equal (String ���� �ٲ����� ���ƾ� ��) 
	 * @return
	 */
	public static boolean contains(Object target, Object key, boolean equal) {
		boolean chk = false;
		if(target != null && key != null) {
			if( target instanceof Map) {
				chk = ((Map) target).containsKey(key);
			} else if ( target instanceof String && !equal ) {
				chk = (((String) target).indexOf(MafUtil.getString(key)) > -1) ? true : false;
			} else if ( target instanceof String && equal ) {
				chk = ((String)target).equals(MafUtil.getString(key)) ? true : false;
			} else if ( target instanceof Object[]) {
				for(int i = 0; i < ((Object[]) target).length; i ++ ) {
					chk = MiFunction.contains(((Object[]) target)[i], key, true);
					if (chk) {
						break;
					}
				}
			} else if ( target instanceof Collection) {
				chk =  ((Collection) target).contains( key);
			}
			
		}
		return chk;
	}
	
	/**
	 * Session ����ڰ� role�� �������� Ȯ�� 
	 * @param session
	 * @param roleID
	 * @return
	 */
	public static boolean hasRole(Object session, String roleID) {
		boolean chk = false;
		if(session != null  && session instanceof BaseHttpSession ) {
			return ((BaseHttpSession)session).hasRole(roleID);
		}
		return chk;
	}
}
