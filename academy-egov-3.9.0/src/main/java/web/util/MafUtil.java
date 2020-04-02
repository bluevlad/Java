/*
 * @(#) Util.java 2005-02-21
 * 
 * Copyright (c) 2005 Miraenet All rights reserved.
 */

package web.util;

import java.io.UnsupportedEncodingException;
import java.rmi.dgc.VMID;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafUtil {
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
 * @author bluevlad
 * @version 1.0
 * @modifydate 2005-02-21
 */
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
    
    public static Object nvl(Object obj) {
        return empty(obj) ? "" : obj;
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
    	return parseFloat(str,0);
    }
    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 default_num 리턴
     *
     * @author : 
     * @e-mail : 
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
     * Global Unique ID릴 리턴 
     * ex) 66021fae7849d9ca6dd108102a4e664657fff (37 byte)
     * @return
     */
    public static String getGUID() {
        VMID id = new java.rmi.dgc.VMID();

        String tmp = id.toString();
        tmp = tmp.replaceAll("[:|-]","");
//        Logging.log(MafUtil.class, tmp);
        return tmp;
        
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
			return String.valueOf(obj);
//			return ((String) obj).v;
		} else {
			return obj.toString();
		}
	}
	
    /**
     * 문자열의 값이 null 이거나 ""이면 default 값을 리턴하는 메소드<br>
     *
     * @author : miraenet 
     */
         
    public static String getString(Object line, String def){
        if(line == null || line.equals(""))
            return def;
        return line.toString(); 
    } 
    
	/**
	 * Returns str, left-padded to length N 
	 * with the sequence of characters in c. c defaults to a single blank.
	 */
    public static String lpad(String str, int len, String c) {
        StringBuffer sbuf = new StringBuffer(str.length() + len);

        if (str == null) return null;
        for (int i = str.length(); i < len; i++) {
            sbuf.append( c );
        }
        sbuf.append( str );
        return sbuf.toString();
    }

    /**
     * 문자열 오른쪽에 길이가 len이 되도록 c를 채움
     * @param str
     * @param len
     * @param c
     * @return
     */
    public static String rpad(String str, int len, String c) {
        StringBuffer sbuf = new StringBuffer(str.length() + len);

        if (str == null) return null;
        sbuf.append( str );
        for (int i = str.length(); i < len; i++) {
            sbuf.append( c );
        }
        return sbuf.toString();
    }

    /**
     * multiplier번 반복한 input_str을 반환합니다. multiplier는 0 이상이여야 합니다. multiplier를 0으로 설정하면, 빈 문자열을
     * 반환합니다.
     */
    public static String strRepeat(String input, int multiplier)  {
        StringBuffer sBuf = new StringBuffer();
        try {
            for (int i = 0; i < multiplier; i++) {
                sBuf.append( input );
            }
        } catch (Exception e) {
            return null;
        }
        return sBuf.toString();
    }
    
    /**
     * 입력된 스트링을 지정된 길이만큼 Byte단위로 남기고 나머지를 잘라낸다.! <br>
     * 
     * @param str 잘라내고자 하는 원본 문자열
     * @param sz 자르고 남을 문자열의 byte단위 길이.
     * @return 원본 문자열에서 자르고 남은 sz만큼의 문자열
     * @throws UnsupportedEncodingException
     */
    public static String getByteCut(String str, int sz) throws UnsupportedEncodingException {
        str = MafUtil.nvl( str, "" );

        if (str.equals( "" ) || str.getBytes().length <= sz) {
            return str;
        }

        String a = str;
        int i = 0;
        String imsi = "";
        String rlt = "";
        imsi = a.substring( 0, 1 );
        while (i < sz) {
            byte[] ar = imsi.getBytes();

            i += ar.length;

            rlt += imsi;
            a = a.substring( 1 );
            if (a.length() == 1) {
                imsi = a;
            } else if (a.length() > 1) {
                imsi = a.substring( 0, 1 );
            }
        }

        return rlt + "...";
    }

	/**
	 * 문자열을 대문자로 , null일 경우 def값을 리턴 
	 * @param str
	 * @param def
	 * @return
	 */
	public static String toUpperCase(String str, String def) {
		if(str == null) {
			return def;
		} else {
			return str.toUpperCase();
		}
	}
	
	/**
	 * 문자열을 대문자로 , null일 경우 ""값을 리턴 
	 * @param str
	 * @param def
	 * @return
	 */		
	public static String toUpperCase(String str) {
		return toUpperCase(str,"");
	}
	
	/**
	 * 문자열을 소문자로 , null일 경우 def값을 리턴 
	 * @param str
	 * @param def
	 * @return
	 */
	public static String toLowerCase(String str, String def) {
		if(str == null) {
			return def;
		} else {
			return str.toLowerCase();
		}
	}
	/**
	 * 문자열을 소문자로 , null일 경우 ""값을 리턴 
	 * @param str
	 * @param def
	 * @return
	 */	
	public static String toLowerCase(String str) {
		return toLowerCase(str,"");
	}
	
    /**
     * String 배열안에 특정 문자가 있는지 확인.
     * @param sr[]
     * @param at
     * @return
     */
    public static boolean chkStringInArray(String[] sr, String at) {
    	if(sr != null) {
    		for(int i =0; i< sr.length; i++) {
    			if(sr[i].equals(at)) {
    				return true;
    			}
    		}
    	}
    	return false;
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
	 * Replace all occurences of a substring within a string with
	 * another string.
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * ','로 분리되어 있는 문자열을 분리하여 Return
	 * List에서 일괄 삭제시 ID값을 일괄로 받아와서 Parsing...
	 * written by Blue.
	 */

	public static String[] getItemArray(String src) {

	    String[] retVal = null;
	    if (src.length() == 0) return null;

	    int nitem = 1;

	    for (int i = 0; i < src.length(); i++)
	        if (src.charAt(i) == ',') nitem++;

	    retVal = new String[nitem];

	    int ep = 0;
	    int sp = 0;

	    for (int i = 0; i < nitem; i++) {
	        ep = src.indexOf(",", sp);
	        if (ep == -1) ep = src.length();
	        retVal[i] = new String(src.substring(sp, ep));
	        sp = ep + 1;
	    }

	    return retVal; 
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
    
}