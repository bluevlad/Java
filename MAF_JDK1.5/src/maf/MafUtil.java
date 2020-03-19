/*
 * @(#) Util.java 2005-02-21
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 */

package maf;

import java.rmi.dgc.VMID;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import maf.beans.NavigatorInfo;
import maf.logger.Logging;
import maf.mdb.drivers.MdbDriver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-21
 */
public class MafUtil {
	private static Log logger = LogFactory.getLog(MafUtil.class);
    /**
     * 개체가 비어있음 True
     *  Collection의경우 Size 가 0 이어도 True
     *  Array 의 경우 size 가 0 이어도 True 
     * @param obj
     * @return
     */
    public static boolean empty(Object obj) {
    	if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		} else if (obj instanceof Object[]) {
			return (((Object[]) obj).length == 0) ? true : false;
		} else {
			return (obj == null || "".equals(obj)) ? true : false;
		}
    }

    /**
     * Object의 값이 null 이거나 ""이면 default 값을 리턴하는 메소드 <br>
     * Oracle Nvl
     * 
     * @author : 김상준
     */
    
    public static Object nvl(Object obj, Object def) {
        return empty(obj) ? def : obj;
    }
    /**
     * 문자열의 값이 null 이거나 ""이면 default 값을 리턴하는 메소드 <br>
     * Oracle Nvl
     * 
     * @author : 김상준
     */
    public static String nvl(String obj, String def) {
        return empty(obj) ? def : obj;
    }

    /**
     * 
     * 문자를 int 형으로 변환 <br>
     * Exception 발생시 0 을 반환
     * 
     * @author 
     * @version 1.0
     * @modifydate 2004. 5. 19.
     * 
     * @param str
     * @return
     */
    public static int parseInt(String str) {
        return parseInt(str, 0);
    }
    

    /**
     * 
     * 문자를 int 형으로 변환 <br>
     * Exception 발생시 default_num 을 반환
     * 
     * @author 
     * @version 1.0
     * @modifydate 2004. 5. 19.
     * 
     * @param str
     * @param default_num
     *                에러 발생시 반환할 기본 값
     * @return
     */
    public static int parseInt(String str, int default_num) {
        int parseInt = 0;
        try {
            parseInt = Integer.parseInt(str);
        } catch (Exception nf) {
            parseInt = default_num;
        }
        return parseInt;
    }
    
    
    /**
     * 
     * 문자를 long 형으로 변환 <br>
     * Exception 발생시 0 을 반환
     * 
     * @author 
     * @version 1.0
     * @modifydate 2004. 5. 19.
     * 
     * @param str
     * @return
     */
    public static long parseLong(String str) {
        return parseLong(str, 0);
    }
    
    /**
     * 
     * 문자를 Long 형으로 변환 <br>
     * Exception 발생시 default_num 을 반환
     * 
     * @author 
     * @version 1.0
     * @modifydate 2004. 5. 19.
     * 
     * @param str
     * @param default_num
     *                에러 발생시 반환할 기본 값
     * @return
     */
    public static long parseLong(String str, long default_num) {
        long parseInt = 0;
        try {
            parseInt = Integer.parseInt(str);
        } catch (Exception nf) {
            parseInt = default_num;
        }
        return parseInt;
    }
    public static double parseDouble(Object str) {
    	double n = 0;
    	try{
    		n = Double.parseDouble(str.toString());
    	} catch ( Throwable e) {
    		logger.error("str = [" + str + "] : " + e.getMessage());
    	}
    	return n;
    }
    
    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 0 리턴
     *
     * @author : 
     * @e-mail : 
     */
    public static float parseFloat(String str){
        float parseFloat = 0.0f;
        try{
            parseFloat = Float.parseFloat(str);
        }catch(Exception nf){}
        return parseFloat;
    }
    
    /**
     * Global Unique ID릴 리턴 
     * ex) 66021fae7849d9ca6dd108102a4e664657fff (37 byte)
     * @return
     */
    public static String getGUID() {
        VMID id = new java.rmi.dgc.VMID();

        String tmp = id.toString();
        tmp = tmp.replaceAll("[:|-]","");
        Logging.log(MafUtil.class, tmp);
        return tmp;
        
    }
    
    /**
     * 오라클을 이용한 GUID 구하기.
     * @param oDb
     * @return
     * @throws FileManException
     */
    public static String getDBGuid(MdbDriver oDb) 
    throws Exception  {
    	String guid = null;
    	try {
//    		oDb.setDebug(true);
    		String sql = "select RAWTOHEX(sys_guid())  as guid from dual";
    		guid = oDb.selectOne(sql);
    	} catch (Exception e) {
    		Log logger = LogFactory.getLog(MafUtil.class);
            logger.error(e.getMessage());
            guid = null;
//            logger.error(Trace.getStackTrace(e));
            throw new Exception(e.getMessage(), e);
    	}
    	return guid;
    }

	/**
	 *
	 *페이징 처리를 위한 NavigatorInfo 객체를 생성하여 반환한다.
	 *  
	 * @author 김윤철
	 * @version 1.0
	 * @modifydate 2005. 6. 25.
	 *
	 * @param v_page
	 * @param page_size
	 * @param count
	 * @return
	 */
	public static NavigatorInfo getNavigator(int v_page, int page_size, int count) {
		NavigatorInfo navigator = null;
		navigator = new NavigatorInfo();
	
		navigator.setCurrentPage(v_page);
		navigator.setPageSize(page_size);
		navigator.setTotalCount(count);
		navigator.sync();
		return navigator;
	}
	
	/**
	 * List 를 m * n 의 배열로 return
	 * @param List obj
	 * @param nColumn 
	 * @return
	 */
	public static List getMatrix(List obj, int nColumn) {
		
		List at = null;
		if ( (obj != null)  && ( nColumn > 0)) {
			
			int st = 0;
			int max = obj.size();
			at = new ArrayList();
			while (st < max) {
//				int idx = 0;
//				List oRow = new ArrayList();
//				for(int i =0; i < nColumn; i++) {
//					if( idx < max) {
//						oRow.add(obj.get(idx));
//					} else {
//						oRow.add(null);
//					}
//					idx ++;
//				}
//				st += nColumn;
//				at.add(oRow);
				
				// 윗부분은 sublist로 속도를 개선하고
				// subList로 가져온것은 수정을 못하므로... 마지막 행은 별도로 생성
				List oRow = null;
				if((st + nColumn) <= max) {
					oRow = obj.subList(st, st + nColumn);
				} else {
					
					oRow = new ArrayList();
					int m = st + nColumn;
					for(int i = st; i < m; i++) {
						if (i < max) {
							oRow.add(obj.get(i));
						} else {
							oRow.add(null);
						}
					}
					
				}
				st += nColumn;
				at.add(oRow);
				
			}
		}
		return at;
	}
	/**
	 * 모든 Object의 String 값을 가져온다. 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj){
		if(obj == null) {
			return null;
		} else if (obj instanceof String) {
			return (String) obj;
		} else {
			return obj.toString();
		}
		
	}

}