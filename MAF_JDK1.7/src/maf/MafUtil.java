/*
 * @(#) Util.java 2005-02-21
 * 
 * Copyright (c) 2005 (��)�̷��� All rights reserved.
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
     * ��ü�� ������� True
     *  Collection�ǰ�� Size �� 0 �̾ True
     *  Array �� ��� size �� 0 �̾ True 
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
     * Object�� ���� null �̰ų� ""�̸� default ���� �����ϴ� �޼ҵ� <br>
     * Oracle Nvl
     * 
     * @author : �����
     */
    
    public static Object nvl(Object obj, Object def) {
        return empty(obj) ? def : obj;
    }
    /**
     * ���ڿ��� ���� null �̰ų� ""�̸� default ���� �����ϴ� �޼ҵ� <br>
     * Oracle Nvl
     * 
     * @author : �����
     */
    public static String nvl(String obj, String def) {
        return empty(obj) ? def : obj;
    }

    /**
     * 
     * ���ڸ� int ������ ��ȯ <br>
     * Exception �߻��� 0 �� ��ȯ
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
     * ���ڸ� int ������ ��ȯ <br>
     * Exception �߻��� default_num �� ��ȯ
     * 
     * @author 
     * @version 1.0
     * @modifydate 2004. 5. 19.
     * 
     * @param str
     * @param default_num
     *                ���� �߻��� ��ȯ�� �⺻ ��
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
     * ���ڸ� long ������ ��ȯ <br>
     * Exception �߻��� 0 �� ��ȯ
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
     * ���ڸ� Long ������ ��ȯ <br>
     * Exception �߻��� default_num �� ��ȯ
     * 
     * @author 
     * @version 1.0
     * @modifydate 2004. 5. 19.
     * 
     * @param str
     * @param default_num
     *                ���� �߻��� ��ȯ�� �⺻ ��
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
     * ��Ʈ���� float ��ȯ. NumberFormatException, NullPointerException �� �˻��ϱ� ����, Exception �߻��� 0 ����
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
     * Global Unique ID�� ���� 
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
     * ����Ŭ�� �̿��� GUID ���ϱ�.
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
	 *����¡ ó���� ���� NavigatorInfo ��ü�� �����Ͽ� ��ȯ�Ѵ�.
	 *  
	 * @author ����ö
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
	 * List �� m * n �� �迭�� return
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
				
				// ���κ��� sublist�� �ӵ��� �����ϰ�
				// subList�� �����°��� ������ ���ϹǷ�... ������ ���� ������ ����
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
	 * ��� Object�� String ���� �����´�. 
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