package miraenet.app.resource;

import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class ResourceManagerDB {
	/**
	 * 현재 사용중인 언어 리스트를 돌려 준다.
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public static  List getLangList(MdbDriver oDb) throws MdbException {
		final String sql = " SELECT   *"   +
		"     FROM v_maf_lang"   +
		" ORDER BY code"  ;
		return oDb.selector(Map.class, sql);
		
	}
}
