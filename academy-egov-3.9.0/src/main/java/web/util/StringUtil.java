/**
 * @ StringUtil.java
 *
 * TYPE : 문자열 자르기
 *
 *
 * 사용법
 *    - static method는 StringUtil.mehtod()로 호출한다.
 *
 */
package web.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class StringUtil {

    public StringUtil() { }

    /**
     * @Method Name : fnStrCut
     * @작성일 : 2015. 10.
     * @Method 설명 : 문자열 자르기
     *                      strCut(대상문자열, 시작위치로할키워드, 자를길이, 키워드위치에서얼마나이전길이만큼포함할것인가, 태그를없앨것인가, 긴문자일경우"..."을추가할것인가)
     * @param szText : 대상문자열
     * @param szKey : 시작위치로할키워드
     * @param nLength : 자를길이
     * @param nPrev : 키워드위치에서얼마나이전길이만큼포함할것인가
     * @param isNotag : 태그를없앨것인가
     * @param isAdddot : 긴문자일경우"..."을추가할것인가
     * @return String
     * @throws Exception
     */
    public static String fnStrCut(String szText, String szKey, Integer nLength, Integer nPrev, Boolean isNotag, Boolean isAdddot){
        String r_val = szText;
        int oF = 0, oL = 0, rF = 0, rL = 0;
        int nLengthPrev = 0;
        Pattern p = Pattern.compile("<(/?)([^<>]*)?>", Pattern.CASE_INSENSITIVE);  // 태그제거 패턴

        if(isNotag) {r_val = p.matcher(r_val).replaceAll("");}  // 태그 제거
        r_val = r_val.replaceAll("&amp;", "&");
        r_val = r_val.replaceAll("(!/|\r|\n|&nbsp;)", "");  // 공백제거

        try {
            byte[] bytes = r_val.getBytes("UTF-8");     // 바이트로 보관
            if(szKey != null && !szKey.equals("")) {
                nLengthPrev = (r_val.indexOf(szKey) == -1)? 0: r_val.indexOf(szKey);  // 일단 위치찾고
                nLengthPrev = r_val.substring(0, nLengthPrev).getBytes("MS949").length;  // 위치까지길이를 byte로 다시 구한다
                nLengthPrev = (nLengthPrev-nPrev >= 0)? nLengthPrev-nPrev:0;    // 좀 앞부분부터 가져오도록한다.
            }

            // x부터 y길이만큼 잘라낸다. 한글안깨지게.
            int j = 0;

            if(nLengthPrev > 0) while(j < bytes.length) {
                if((bytes[j] & 0x80) != 0) {
                    oF+=2; rF+=3; if(oF+2 > nLengthPrev) {break;} j+=3;
                } else {if(oF+1 > nLengthPrev) {break;} ++oF; ++rF; ++j;}
            }

            j = rF;

            while(j < bytes.length) {
                if((bytes[j] & 0x80) != 0) {
                    if(oL+2 > nLength) {break;} oL+=2; rL+=3; j+=3;
                } else {if(oL+1 > nLength) {break;} ++oL; ++rL; ++j;}
            }

            r_val = new String(bytes, rF, rL, "UTF-8");  // charset 옵션

            if(isAdddot && rF+rL+3 <= bytes.length) {r_val+="...";}  // ...을 붙일지말지 옵션
        } catch(UnsupportedEncodingException e){ e.printStackTrace(); }

        return r_val;
    }

    /**
     * @Method Name : fnStrCut
     * @작성일 : 2015. 10.
     * @Method 설명 : 문자열 자르기
     *                      strCut(대상문자열, 자를길이, 태그를없앨것인가, 긴문자일경우"..."을추가할것인가)
     * @param szText : 대상문자열
     * @param szKey : 시작위치로할키워드
     * @param nLength : 자를길이
     * @param nPrev : 키워드위치에서얼마나이전길이만큼포함할것인가
     * @param isNotag : 태그를없앨것인가
     * @param isAdddot : 긴문자일경우"..."을추가할것인가
     * @return String
     * @throws Exception
     */
    public static String fnStrCut(String szText, Integer nLength, Boolean isNotag, Boolean isAdddot){
        return fnStrCut(szText, null, nLength, 0, isNotag, isAdddot);
    }

    /**
     * @Method Name : fnStrCut
     * @작성일 : 2015. 10.
     * @Method 설명 : 문자열 자르기
     *                      strCut(대상문자열, 자를길이)
     * @param szText : 대상문자열
     * @param nLength : 자를길이
     * @return String
     * @throws Exception
     */
    public static String fnStrCut(String szText, Integer nLength){
        return fnStrCut(szText, null, nLength, 0, true, true);
    }

}
