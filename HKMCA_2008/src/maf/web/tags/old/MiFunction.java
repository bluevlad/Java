package maf.web.tags.old;

import java.util.Collection;
import java.util.Map;

import maf.MafUtil;
import maf.base.BaseHttpSession;

public class MiFunction {
	/**
	 * String Object 의 lastIndexOf 의 tagLibrary용 
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
	 * Object target 에 key 가 포함되어 있느지 처리
	 *  target 가 String이면 instring
	 *  target 가 Map 이면 contain
	 *  target이 Array 면 값 확인 
	 * @param target
	 * @param key
	 * @return
	 */
	public static boolean contains(Object target, Object key) {
		return MiFunction.contains(target, key, false);
	}
	/**
	 * Object target 에 key 가 포함되어 있느지 처리
	 *  target 가 String이면 instring
	 *  target 가 Map 이면 contain
	 *  target이 Array 면 값 확인 
	 *  Array 면 목록 중 일치하는 값이 있나 확인 (String 형으로 비교 )
	 * @param target
	 * @param key
	 * @param equal (String 으로 바꿨을때 같아야 함) 
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
	 * Session 사용자가 role을 가졋는지 확인 
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
