package maf.web.session;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import maf.base.BaseHttpSession;
import maf.web.session.beans.SessionInfoBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author G2BKorea
 * 현재 Login된  세션정보를 담고 있음 
 *
 *  */
public class SessionPool {
	static Log logger = LogFactory.getLog(SessionPool.class);

	private HashMap sessionPool = null;

	private HashMap usn2sidMap = null;

	private static SessionPool instance = new SessionPool();

	/**
	 * 외부에서 생성 못함 
	 *
	 */
	private SessionPool() {
		sessionPool = new HashMap();
		usn2sidMap = new HashMap();
	}

	/**
	 * Session 정보 추가.
	 * @param sid
	 * @param sb
	 */
	public static synchronized void add(HttpSession s, BaseHttpSession bhs) {
		SessionInfoBean sb = new SessionInfoBean(s, bhs);

		instance.sessionPool.put(s.getId(), sb);
		if (sb != null) {
			instance.usn2sidMap.put(sb.getUsn(), s.getId());
			logger.debug(">> " + sb.getUserid() + " logged!!");
		}
		logSesionPool();
	}

	/**
	 * sid로 Usn 정보 가져 오기 
	 * @param sid
	 * @return
	 */
	private static String _getUsnBySid(String sid) {
		if (sid != null) {
			SessionInfoBean sb = (SessionInfoBean) instance.sessionPool.get(sid);
			if (sb != null) {
				logger.debug("usn : " + sb.getUserid());
				return sb.getUsn();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * Usn으로 sid 가져 오기 
	 * @param usn
	 * @return
	 */
	private static String _getSidByUsn(String usn) {
		if (usn != null) {
			return (String) instance.usn2sidMap.get(usn);
		} else {
			return "";
		}
	}

	public static int size() {
		return instance.sessionPool.size();
	}

	public static synchronized void remove(HttpSession s) {
		SessionInfoBean sb = (SessionInfoBean) instance.sessionPool.get(s.getId());
		if (sb != null) {
			logger.debug("<< " + sb.getUserid() + " log out!!");
		}
		// 순서주의 !!!
		instance.usn2sidMap.remove(_getUsnBySid(s.getId()));
		instance.sessionPool.remove(s.getId());
		logSesionPool();

	}

	public static synchronized void removeByUsn(String usn) {
		String sid = _getSidByUsn(usn);
		if (sid != null) {
			SessionInfoBean sb = (SessionInfoBean) instance.sessionPool.get(sid);
			if (sb != null) {
				logger.debug("<< " + sb.getUserid() + " remove!!");
			}
			// 순서주의 !!!
			instance.usn2sidMap.remove(usn);
			instance.sessionPool.remove(sid);
			logSesionPool();
		}
	}

	public static Map getSessinInfo() {
		return (Map) instance.sessionPool.clone();
	}

	/**
	 * Usn으로 SessionPool에 있는 정보 가져 오기  
	 * @param usn
	 * @return
	 */
	public static SessionInfoBean getSessionInfoByUsn(String usn) {

		return (SessionInfoBean) instance.sessionPool.get(_getSidByUsn(usn));
	}

	/**
	 * HttpSession 으로 SessionPool에 있는 정보 가져 오기  
	 * @param usn
	 * @return
	 */
	public static SessionInfoBean getSessionInfo(HttpSession s) {

		return (SessionInfoBean) instance.sessionPool.get(s.getId());
	}

	/**
	 * 해당 usn이 Session Pool에 있는지 있다면 True
	 * @param usn
	 * @return
	 */
	public static boolean isUserLogined(String usn) {
		return instance.usn2sidMap.containsKey(usn);
	}

	/**
	 * 해당 세션이 유효한지 확인 
	 * @param s
	 * @return
	 */
	public static boolean isValidSession(MyHttpSession s) {
		if (instance.sessionPool.containsKey(s.getId())) {
			return true;
		} else {
			return false;
		}
	}

	private static void logSesionPool() {
		logger.debug(" loggin /  session = " + instance.usn2sidMap.size() + "/" + instance.sessionPool.size());
	}
}
