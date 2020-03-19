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
    // [] �ۿ����� -�� ������, ^, ., *, ?, +, (, ), $, | �� Ư������
    // [] �ȿ����� -�� ^ �� Ư�� ���� 
    static Pattern regSpecialChar = Pattern.compile("([\\$|\\(|\\)|\\*|\\+|\\.|\\?|\\[|\\\\|\\]|\\^|\\{|\\||\\}])");

    public regexTool() {
        
    }
    /**
     * ���ԽĿ� Ư�����ڿ��� �ֱ� ���� ���ԽĿ��� ����ϴ�
     * Ư�� ���� �տ� \ �� �־� �Ϲ� ���ڷ� �ٲپ� �ִ� ������ ��
     * [] ���ڴ� []�� ��ȿȭ �ǹǷ� ���� ���ʿ� 
     * @param sText
     * @return
     */
    public static String toNormalStr(String sText) {
    	Matcher m = regSpecialChar.matcher(sText);
    	return m.replaceAll("\\\\$1");
    }
}
