/*
 * Created on 2004. 10. 5.
 *
 */
package maf.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bluevlad
 * Create by 2004. 10. 5.
 * 
 */
public class regexTool {
    // [] 밖에서는 -를 제외한, ^, ., *, ?, +, (, ), $, | 이 특수문자
    // [] 안에서는 -과 ^ 이 특수 문자 
    static Pattern regSpecialChar = Pattern.compile("([\\$|\\(|\\)|\\*|\\+|\\.|\\?|\\[|\\\\|\\]|\\^|\\{|\\||\\}])");

    public regexTool() {
        
    }
    /**
     * 정규식에 특정문자열을 넣기 위해 정규식에서 사용하는
     * 특수 문자 앞에 \ 를 넣어 일반 문자로 바꾸어 주는 역할을 함
     * [] 문자는 []가 무효화 되므로 변경 불필요 
     * @param sText
     * @return
     */
    public static String toNormalStr(String sText) {
    	Matcher m = regSpecialChar.matcher(sText);
    	return m.replaceAll("\\\\$1");
    }
}
