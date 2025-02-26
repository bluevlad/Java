/*
 * @(#) Util.java 2005-02-21
 * 
 * Copyright (c) 2005 Miraenet All rights reserved.
 */

package com.academy.common;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public class CommonUtil {
	public static final String FOLDER_SEPARATOR = "/";
    public static final int HIGHEST_SPECIAL = '>';
    public static char[][] specialCharactersRepresentation = new char[HIGHEST_SPECIAL + 1][];
    static {
        specialCharactersRepresentation['&'] = "&amp;".toCharArray();
        specialCharactersRepresentation['<'] = "&lt;".toCharArray();
        specialCharactersRepresentation['>'] = "&gt;".toCharArray();
        specialCharactersRepresentation['"'] = "&#034;".toCharArray();
        specialCharactersRepresentation['\''] = "&#039;".toCharArray();
    }
    

/**
 * @author rainend
 * @version 1.0
 * @modifydate 2025-02-21
 */
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
     * @author : rainend
     */
    public static Object nvl(Object obj) {
        return empty(obj) ? "" : obj;
    }
    
    public static Object nvl(Object obj, Object def) {
        return empty(obj) ? def : obj;
    }

    /**
     * 문자열의 값이 null 이거나 ""이면 default 값을 리턴하는 메소드 <br>
     * @author : rainend
     */
    public static String nvl(String obj, String def) {
    	
        return empty(obj) ? def : obj;
    }

    /**
     * 
     * 문자를 int 형으로 변환 <br>
     * Exception 발생시 0 을 반환
     * @param str
     * @return
     */
    public static int parseInt(String str) {
        int parseInt = 0;
        try {
        	if(str != null){
        		parseInt = Integer.parseInt(str.trim());
        	}
        } catch (Exception nf) {}
        return parseInt;
    }
    public static int parseInt(Object obj) {
        int parseInt = 0;
        try {
			if(obj != null) {
				if (obj instanceof String) {
					parseInt = Integer.parseInt(String.valueOf(obj).trim());
				} else {
					parseInt = Integer.parseInt(obj.toString().trim());
				}
			}
        } catch (Exception nf) {}
        return parseInt;
    }
    
    /**
     * 
     * 문자를 Long 형으로 변환 <br>
     * Exception 발생시 0 을 반환
     * @param str
    */
    public static long parseLong(String str) {
        long parseInt = 0;
        try {
        	if(str != null){
        		parseInt = Integer.parseInt(str.trim());
        	}
        } catch (Exception nf) {}
        return parseInt;
    }
    public static long parseLong(Object obj) {
        int parseInt = 0;
        try {
			if(obj != null) {
				if (obj instanceof String) {
					parseInt = Integer.parseInt(String.valueOf(obj).trim());
				} else {
					parseInt = Integer.parseInt(obj.toString().trim());
				}
			}
        } catch (Exception nf) {}
        return parseInt;
    }

    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 0 리턴
     */
    public static float parseFloat(String str){
        float parseFloat = 0.0f;
        try{
        	if(str != null){
        		parseFloat = Float.parseFloat(str.trim());
	    	}
        }catch(Exception nf) {}
        return parseFloat;
    }
    public static float parseFloat(Object obj) {
    	float parseFloat = 0.0f;
        try {
			if(obj != null) {
				if (obj instanceof String) {
	        		parseFloat = Float.parseFloat(String.valueOf(obj).trim());
				} else {
	        		parseFloat = Float.parseFloat(obj.toString().trim());
				}
			}
        } catch (Exception nf) {}
        return parseFloat;
    }

    /**
	 * .0123456789를 제외한 문자가 있는지 확인.
	 * 주의 !! Commonutil 의 StringUtils의 isNumeric은 "."를 포함해도 false 리턴 
	 * @param str
	 * @return
	 */
    public static boolean isNumeric(String str) {
    	if(str == null) {
    		return false;
    	}
    	try {
    		Double.valueOf(str);
    		return true;
    	} catch (Exception e) {
			return false;
		}
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
			return String.valueOf(obj);
		} else {
			return obj.toString();
		}
	}
	
    /**
     * <pre>
     * text를 format에 맞추어 출력한다.
     * getFormatedText("0193372412","???-???-????") --->> 019-337-2412로 출력
     * </pre>
     *
     * @param String text
     * @param String format
     *
     * @return String
     */
    public static String getFormatedText(String text, String format)
    {
        String rtn;
        int start,i,j,len;
        int tCount,fCount;

        tCount = text.length();
        fCount = format.length();

        rtn = "";

        if (text.equals("")) return rtn;
        if (text.equals("&nbsp;")) return "&nbsp;";
        // text가 01252412 이고 format 이 ????-???? 이면 0125-2412로 출력
        //text에서 -를 제거한다.
        for (i=0; i<tCount; ++i) {
            if (!text.substring(i,i+1).equals("-"))
                rtn = rtn + text.substring(i,i+1);
        }

        text = rtn;
        tCount = text.length();

        //포멧에서 ?의  count
        len = 0;
        for (j=0; j<fCount; ++j) {
            if (format.substring(j,j+1).equals("?")) ++len;
        }
        //text의 길이가 len보다 작으면 앞에 0를 붙인다.
        if (tCount<len) {
            for (i=0; i<(len-tCount); ++i) {
                text = '0' + text;
            }
            tCount = len;
        }

        rtn = "";
        start = 0;
        for (i=0; i<tCount; ++i) {
            for (j=start; j<fCount; ++j) {
                if (format.substring(j,j+1).equals("?")) {
                    rtn = rtn + text.substring(i,i+1);
                    start = start + 1;
                    break;
                }
                else {
                    rtn = rtn + format.substring(j,j+1);
                    start = start + 1;
                }
            }
        }
        return rtn+format.substring(start);
    }    
    
	/**
	 * src를 parser로 구분 하여 배열로 돌려줌 
	 * @param src
	 * @param parser
	 * @return
	 */
	public static String[] getItemArray(String src, char parser) {

	    String[] retVal = null;
	    if (src.length() == 0) return null;

	    int nitem = 1;

	    for (int i = 0; i < src.length(); i++)
	        if (src.charAt(i) == parser) nitem++;

	    retVal = new String[nitem];

	    int ep = 0;
	    int sp = 0;

	    for (int i = 0; i < nitem; i++) {
	        ep = src.indexOf(parser, sp);
	        if (ep == -1) ep = src.length();
	        retVal[i] = new String(src.substring(sp, ep));
	        sp = ep + 1;
	    }

	    return retVal; 
	}    
	
    /**
     * Performs the following substring replacements
     * (to facilitate output to XML/HTML pages):
     *
     *    & -> &amp;
     *    < -> &lt;
     *    > -> &gt;
     *    " -> &#034;
     *    ' -> &#039;
     *
     * See also OutSupport.writeEscapedXml().
     */
    public static String escapeXml(String buffer) {
        int start = 0;
        int length = buffer.length();
        char[] arrayBuffer = buffer.toCharArray();
        StringBuffer escapedBuffer = null;

        for (int i = 0; i < length; i++) {
            char c = arrayBuffer[i];
            if (c <= HIGHEST_SPECIAL) {
                char[] escaped = specialCharactersRepresentation[c];
                if (escaped != null) {
                    // create StringBuffer to hold escaped xml string
                    if (start == 0) {
                        escapedBuffer = new StringBuffer(length + 5);
                    }
                    // add unescaped portion
                    if (start < i) {
                        escapedBuffer.append(arrayBuffer,start,i-start);
                    }
                    start = i + 1;
                    // add escaped xml
                    escapedBuffer.append(escaped);
                }
            }
        }
        // no xml escaping was necessary
        if (start == 0) {
            return buffer;
        }
        // add rest of unescaped portion
        if (start < length) {
            escapedBuffer.append(arrayBuffer,start,length-start);
        }
        return escapedBuffer.toString();
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

	public static String getCurrentDate() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
		Date currentDate = new Date ( );
		String mDate = mSimpleDateFormat.format ( currentDate );
		
		return mDate;
	}
	
	/**
	 * 현재년월일시분초밀리세컨드
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMddHHmmssSSS", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		
		return mTime;
	}
    
}