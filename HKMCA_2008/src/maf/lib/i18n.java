package maf.lib;

/**
 * @author bluevlad
 *i18n : CharacterEncoding 을 iso 8859-1에서 ksc5601이나 기타 다른 Encording으로 변환
 */
public class i18n {
    
    public static String convertTo(String str, String characterSet) {
        if(str == null) return null;
        try {
	        if(characterSet == null) {
	            return str;
	        } else {
	            return new String(str.getBytes("8859_1"), characterSet );
	        }
        } catch (Exception e) {
			return null;
		}
    }
    
    public static String convertTo(String str, String srcCharacterSet, String descCharacterSet) {
        if(str == null) return null;
        try {
	        if(srcCharacterSet == null || descCharacterSet == null ) {
	            return str;
	        } else {
	            return new String(str.getBytes(srcCharacterSet), descCharacterSet );
	        }
        } catch (Exception e) {
			return null;
		}
    }
}
