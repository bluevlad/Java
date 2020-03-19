package maf.lib.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
/**
 * JCS를 이용한 Object Cache 사용 
 * @author bluevlad
 *
 */
public class MafCache {
	private static MafCache instance = new MafCache();

	private Log logger = LogFactory.getLog(getClass());

	private MafCache() {
		
	}
	
	
	
	public  static Object getObject(String region, Object key) {
		JCS cache = null;
		
		
		try {
			cache = JCS.getInstance(region);
			return cache.get(key);
		} catch (CacheException e) {
			instance.logger.error("Problem initializing cache for region name ["
			        + region
			        + "].", e);
			return null;
		}
	}

	public static void setObject(String region, Object key, Object value) {
		JCS cache = null;
		
		try {
			
			if (value != null) {
				cache.put(key, value);
			}
		} catch (CacheException e) {
			instance.logger.error("Problem putting "
			        + value
			        + " in the cache, for key "
			        + key, e);
		}
	}
}
