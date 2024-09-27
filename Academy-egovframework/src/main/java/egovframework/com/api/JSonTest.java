package egovframework.com.api; 

import org.apache.log4j.Logger;

public class JSonTest {

	private Logger log = Logger.getLogger(getClass());

    public static void main(String args[]) {
   		
    	try {
    		JSonTest t = new JSonTest();
    		t.test();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }

    public void test() {

    	try {
    		
    		String filename = "파일 문자열_테스트 문서_테스트 문서 파일 문자열_테스트 문서_테스트 문서 123456789 EMC_Documentum_Administrator_Version 7.3_User Guide 파일 문자열_테스트 문서_테스트 문서 123456789 파일 문자열_테스트 문서_테스트 문서.txt";
    		int limit = filename.getBytes("UTF-8").length;
    		
    		String new_filename = substringByBytes(filename, 0, 250);

    		log.debug("filename : " + filename);
    		log.debug("limit : " + limit);
    		log.debug("new_filename : " + new_filename);
          
    	} catch (Exception e) {
    	}
    }

    public static String substringByBytes(String str, int beginBytes, int endBytes) {
        int len = str.length();

        int beginIndex = -1;
        int endIndex = 0;

        int curBytes = 0;
        String ch = null;
        for (int i = 0; i < len; i++) {
            ch = str.substring(i, i + 1);
            curBytes += ch.getBytes().length;
            if (beginIndex == -1 && curBytes >= beginBytes) { beginIndex = i; }
            if (curBytes > endBytes) {
            	break;
            } else {
                endIndex = i + 1;
            }
        }

        return str.substring(beginIndex, endIndex);
    }    
}