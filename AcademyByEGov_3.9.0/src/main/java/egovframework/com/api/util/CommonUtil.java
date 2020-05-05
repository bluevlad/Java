package egovframework.com.api.util;

public class CommonUtil {

    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 0 리턴
     *
     * @author : 
     */   
    public static float parseFloat(String str){
    	return parseFloat(str,0);
    }
    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 default_num 리턴
     *
     * @author : 
     */
    public static float parseFloat(String str, float default_num){
        float parseFloat = 0.0f;
        try{
        	if(str != null){
        		parseFloat = Float.parseFloat(str.trim());
	        } else {
	        	parseFloat = default_num;
	    	}
        }catch(Exception nf){}
        return parseFloat;
    }

    /**
     * 
     * 문자를 int 형으로 변환 <br>
     * Exception 발생시 0 을 반환
     * @author 
     * @version 1.0
     * @param str
     * @return int
     */
    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    /**
     * 
     * 문자를 int 형으로 변환 <br>
     * Exception 발생시 default_num 을 반환
     * @author 
     * @version 1.0
     * @param str
     * @param default_num 에러 발생시 반환할 기본 값
     * @return 
     */
    public static int parseInt(String str, int default_num) {
        int parseInt = 0;
        try {
        	if(str != null){
        		parseInt = Integer.parseInt(str.trim());
        	} else {
        		parseInt = default_num;
        	}
        } catch (Exception nf) {
            parseInt = default_num;
        }
        return parseInt;
    }
    
}
