package web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtil {

	public static String isNull(String param) {
		return isNull(param, "");
	}
	
	public static String isNull(String param, String ref) {
		String result = param;
		if(param == null || "".equals(param)) result = ref;
		return result;
	}

	public static String getCurrentDate() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
		Date currentDate = new Date ( );
		String mDate = mSimpleDateFormat.format ( currentDate );
		
		return mDate;
	}

	public static String getCurrentDateTime() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMddHHmmssSSS", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		
		return mTime;
	}
	
	public static Boolean isEmail(String email) {
       String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
       Boolean b = email.matches(EMAIL_REGEX);
       return b;
    }
	
    public static String rmStringKor(String Str, int limit){
        int len = Str.length();
        int cnt = 0;
        int index = 0;

        while (index < len && cnt < limit) {
            if (Str.charAt(index++) < 256) {
                cnt++;
            } else {
                cnt += 2;
            }
        }
        if (index < len) {
        	Str = Str.substring(0, index) + "...";
        }
        return Str;
    }
    
    
	/**
	 * 현재년도
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		
		return mTime;
	}
	
	/**
	 * 현재월
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "MM", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		
		return mTime;
	}


}