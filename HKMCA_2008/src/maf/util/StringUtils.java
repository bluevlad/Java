package maf.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;



import maf.MafUtil;

import org.apache.regexp.RE;

public class StringUtils {
	private static final char EXTENSION_SEPARATOR = '.';
	public static final String FOLDER_SEPARATOR = "/";
	private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
	

	private static final String TOP_PATH = "..";

	private static final String CURRENT_PATH = ".";
	
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
     * 숫자 n 왼쪽에 t를 채움 
     * ex) lpad(1,2,"0") = 01
     * @param n
     * @param len
     * @param t
     * @return
     */
    public static String lpad(int n, int len, String t) {
        String str = String.valueOf( n );
        return lpad( str, len, t );
    }
    /**
     * 숟자 오른쪽에 길이가 len이 되도록 c를 채움
     * @param str
     * @param len
     * @param c
     * @return
     */
    public static String rpad(int n, int len, String t) {
        String str = String.valueOf( n );

        return rpad( str, len, t );
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
     * TRUE나 T, Y, y의 경우 true로
     * @param v
     * @return
     */
    public static boolean toBoolean(Object v) {
    	if(v instanceof String) {
	    	if ("t".equalsIgnoreCase((String)v) || "true".equalsIgnoreCase((String)v)|| "y".equalsIgnoreCase((String)v)|| "yes".equalsIgnoreCase((String)v)) {
	    		return true;
	    	} else {
	    		return false;
	    	}
    	} else if (v instanceof Boolean) {
    		return ((Boolean) v).booleanValue();
    	} else {
    		return false;
    	}

    }
    /**
     * preg_replace -- 정규 표현식 검색과 치환을 수행합니다. From PHP
     * 
     * mixed preg_replace ( mixed pattern, mixed replacement, mixed subject) subject를 검색하여 매치된
     * pattern을 replacement로 치환합니다.
     *  
     */
    public static String preg_replace(String pattern, String replacement, String subject) {
        org.apache.regexp.RE re = new org.apache.regexp.RE( pattern );
        return re.subst( subject, replacement );
    }

    public static String addQuote(String str) {
        return ( str.replaceAll( "'", "''" ) );
    }
    
    /**
     * 문자열에 '," 를 제거 합니다
     * 
     * @return
     */
    public static String stripQuote(String str) {
    	if(str == null) return null;
        String x = str.replaceAll( "'", "" );
        return x.replaceAll("\"","");
    }
    /**
     * 문자열에 슬래쉬를 덧붙입니다
     * 
     * @return
     */
    public static String addSlashes(String str) {
        return str.replaceAll( "\\", "\\\\" );
    }

    /**
     * 문자열에 슬래쉬를 제거 합니다
     * 
     * @return
     */
    public static String stripSlashes(String str) {
        return str.replaceAll( "\\\\", "\\" );
    }

    /**
     * 
     * 
     * 입력된 스트링을 지정된 길이만큼 Byte단위로 남기고 나머지를 잘라낸다.! <br>
     * 
     * @author Choi Eui Youb
     * @version 1.0
     * @createdate 2004. 6. 9.
     * @modifydate 2004. 6. 9.
     * 
     * @param str
     *                  잘라내고자 하는 원본 문자열
     * @param sz
     *                  자르고 남을 문자열의 byte단위 길이.
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

//    /**
//     * 문자열의 값이 null 이거나 ""이면 default 값을 리턴하는 메소드 <br>
//     * 
//     * @author : 심재진
//     * @e-mail : sim11@mirenet.com
//     */
//    public static String getString(String line, String def) {
//        if (line == null || line.equals( "" )) return def;
//        return line;
//    }

    /**
     * String이 /로 끝나는가 체크
     * 
     * @param st
     * @return
     */
    public static boolean chkLastSlash(String st) {
        final RE reg_LASTSLASH = new RE( "\\/$" );
        if (st == null) {
            return false;
        } else {
            return reg_LASTSLASH.match( st );
        }

    }

	/**
	 * Capitalize a <code>String</code>, changing the first letter to
	 * upper case as per {@link Character#toUpperCase(char)}.
	 * No other letters are lower.
	 * @param str the String to capitalize, may be <code>null</code>
	 * @return the capitalized String, <code>null</code> if null
	 */
	public static String capitalize(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		return changeFirstCharacterCase(str.toLowerCase(), true);
	}

	/**
	 * Uncapitalize a <code>String</code>, changing the first letter to
	 * lower case as per {@link Character#toLowerCase(char)}.
	 * No other letters are changed.
	 * @param str the String to uncapitalize, may be <code>null</code>
	 * @return the uncapitalized String, <code>null</code> if null
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
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
     * 비어 있지 않은경우 앞뒤에 문자 삽입
     * 
     * @param pre
     * @param dst
     * @param after
     * @return
     */
    public static String capsule(String pre, Object dst, String after) {
        if (MafUtil.empty( dst )) return "";
        else
            return pre + dst + after;
    }
    
    public static String leftString(String ori, int length) {
        if(ori != null) {
            return ori.substring(0,Math.min(length, ori.length()));
        } else {
            return null;
        }
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
     * 주민등록 번호 앞자리 9개와 뒷자리를 #로 채워서 리턴해준다.
     * @param pin
     * @return
     */
    public static String getHiddenPin(String pin) {
    	if(pin != null) {
    		int length = (pin.length()<9) ? pin.length():9 ;
    		
    		return rpad(pin.substring(0,length),14,"#");
    	} else {
    		return "";
    	}
    }
    
    //
    // from Spring Framework StringUtil
    //
	/**
	 * Check if a String has length.
	 * <p><pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param str the String to check, may be <code>null</code>
	 * @return <code>true</code> if the String is not null and has length
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * Check if a String has text. More specifically, returns <code>true</code>
	 * if the string not <code>null<code>, it's <code>length is > 0</code>, and
	 * it has at least one non-whitespace character.
	 * <p><pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param str the String to check, may be <code>null</code>
	 * @return <code>true</code> if the String is not null, length > 0,
	 *         and not whitespace only
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean hasText(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return false;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Trim leading and trailing whitespace from the given String.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
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
	 * Delete all occurrences of the given substring.
	 * @param pattern the pattern to delete all occurrences of
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}
	
	/**
	 * Quote the given String with single quotes.
	 * @param str the input String (e.g. "myString")
	 * @return the quoted String (e.g. "'myString'"),
	 * or <code>null<code> if the input was <code>null</code>
	 */
	public static String quote(String str) {
		return (str != null ? "'" + str + "'" : null);
	}
	
	/**
	 * Turn the given Object into a String with single quotes
	 * if it is a String; keeping the Object as-is else.
	 * @param obj the input Object (e.g. "myString")
	 * @return the quoted String (e.g. "'myString'"),
	 * or the input object as-is if not a String
	 */
	public static Object quoteIfString(Object obj) {
		return (obj instanceof String ? quote((String) obj) : obj);
	}
	/**
	 * Test if the given String starts with the specified prefix,
	 * ignoring upper/lower case.
	 * @param str the String to check
	 * @param prefix the prefix to look for
	 * @see java.lang.String#startsWith
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * Test if the given String ends with the specified suffix,
	 * ignoring upper/lower case.
	 * @param str the String to check
	 * @param suffix the suffix to look for
	 * @see java.lang.String#endsWith
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}
	/**
	 * Extract the filename from the given path,
	 * e.g. "mypath/myfile.txt" -> "myfile.txt".
	 * @param path the file path (may be <code>null</code>)
	 * @return the extracted filename, or <code>null</code> if none
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		String mpath = cleanPath(path);
		int separatorIndex = mpath.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? mpath.substring(separatorIndex + 1) : mpath);
	}
	
	/**
	 * Extract the filename extension from the given path,
	 * e.g. "mypath/myfile.txt" -> "txt".
	 * @param path the file path (may be <code>null</code>)
	 * @return the extracted filename extension, or <code>null</code> if none
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
	}
	
	/**
	 * Strip the filename extension from the given path,
	 * e.g. "mypath/myfile.txt" -> "mypath/myfile".
	 * @param path the file path (may be <code>null</code>)
	 * @return the path with stripped filename extension,
	 * or <code>null</code> if none
	 */
	public static String stripFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
	}
	/**
	 * Apply the given relative path to the given path,
	 * assuming standard Java folder separation (i.e. "/" separators);
	 * @param path the path to start from (usually a full file path)
	 * @param relativePath the relative path to apply
	 * (relative to the full file path above)
	 * @return the full file path that results from applying the relative path
	 */
	public static String applyRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
				newPath += FOLDER_SEPARATOR;
			}
			return newPath + relativePath;
		}
		else {
			return relativePath;
		}
	}
	/**
	 * Normalize the path by suppressing sequences like "path/.." and
	 * inner simple dots.
	 * <p>The result is convenient for path comparison. For other uses,
	 * notice that Windows separators ("\") are replaced by simple slashes.
	 * @param path the original path
	 * @return the normalized path
	 */
	public static String cleanPath(String path) {
		String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);

		// Strip prefix from path to analyze, to not treat it as part of the
		// first path element. This is necessary to correctly parse paths like
		// "file:core/../core/io/Resource.class", where the ".." should just
		// strip the first "core" directory while keeping the "file:" prefix.
		int prefixIndex = pathToUse.indexOf(":");
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			pathToUse = pathToUse.substring(prefixIndex + 1);
		}

		String[] pathArray = delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
		List pathElements = new LinkedList();
		int tops = 0;

		for (int i = pathArray.length - 1; i >= 0; i--) {
			if (CURRENT_PATH.equals(pathArray[i])) {
				// Points to current directory - drop it.
			}
			else if (TOP_PATH.equals(pathArray[i])) {
				// Registering top path found.
				tops++;
			}
			else {
				if (tops > 0) {
					// Merging path element with corresponding to top path.
					tops--;
				}
				else {
					// Normal path element found.
					pathElements.add(0, pathArray[i]);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, TOP_PATH);
		}

		return prefix + collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
	}
	
	/**
	 * Copy the given Collection into a String array.
	 * The Collection must contain String elements only.
	 * @param collection the Collection to copy
	 * @return the String array (<code>null</code> if the Collection
	 * was <code>null</code> as well)
	 */
	public static String[] toStringArray(Collection collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}
	
	/**
	 * Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. E.g. useful for toString() implementations.
	 * @param arr array to display. Elements may be of any type (toString
	 * will be called on each element).
	 * @param delim delimiter to use (probably a ",")
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (arr == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	
	public static String arrayToDelimitedString(Object[] arr , String delim, String prefix, String suffix) {
		if (arr == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(prefix).append(arr[i]).append(suffix);
		}
		
		return sb.toString();
	}
	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for toString() implementations.
	 * @param coll Collection to display
	 * @param delim delimiter to use (probably a ",")
	 * @param prefix string to start each element with
	 * @param suffix string to end each element with
	 */
	public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
		if (coll == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		Iterator it = coll.iterator();
		int i = 0;
		while (it.hasNext()) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(prefix).append(it.next()).append(suffix);
			i++;
		}
		return sb.toString();
	}
	
	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for toString() implementations.
	 * @param coll Collection to display
	 * @param delim delimiter to use (probably a ",")
	 */
	public static String collectionToDelimitedString(Collection coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}
	
	/**
	 * Take a String which is a delimited list and convert it to a String array.
	 * <p>A single delimiter can consists of more than one character: It will still
	 * be considered as single delimiter string, rather than as bunch of potential
	 * delimiter characters - in contrast to <code>tokenizeToStringArray</code>.
	 * @param str the input String
	 * @param delimiter the delimiter between elements (this is a single delimiter,
	 * rather than a bunch individual delimiter characters)
	 * @return an array of the tokens in the list
	 * @see #tokenizeToStringArray
	 */
	public static String[] delimitedListToStringArray(String str, String delimiter) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] {str};
		}

		List result = new ArrayList();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(str.substring(i, i + 1));
			}
		}
		else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(str.substring(pos, delPos));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(str.substring(pos));
			}
		}
		return toStringArray(result);
	}

	/**
	 * Convenience method to return a String array as a CSV String.
	 * E.g. useful for toString() implementations.
	 * @param arr array to display. Elements may be of any type (toString
	 * will be called on each element).
	 */
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}

	/**
	 * Convenience method to return a Collection as a CSV String.
	 * E.g. useful for toString() implementations.
	 * @param coll Collection to display
	 */
	public static String collectionToCommaDelimitedString(Collection coll) {
		return collectionToDelimitedString(coll, ",");
	}
	
	/**
	 * Compare two paths after normalization of them.
	 * @param path1 First path for comparizon
	 * @param path2 Second path for comparizon
	 * @return whether the two paths are equivalent after normalization
	 */
	public static boolean pathEquals(String path1, String path2) {
		return cleanPath(path1).equals(cleanPath(path2));
	}
	
	/**
	 * Parse the given locale string into a <code>java.util.Locale</code>.
	 * This is the inverse operation of Locale's <code>toString</code>.
	 * @param localeString the locale string, following
	 * <code>java.util.Locale</code>'s toString format ("en", "en_UK", etc).
	 * Also accepts spaces as separators, as alternative to underscores.
	 * @return a corresponding Locale instance
	 */
	public static Locale parseLocaleString(String localeString) {
		String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
		String language = (parts.length > 0 ? parts[0] : "");
		String country = (parts.length > 1 ? parts[1] : "");
		String variant = (parts.length > 2 ? parts[2] : "");
		return (language.length() > 0 ? new Locale(language, country, variant) : null);
	}
	

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * Trims tokens and omits empty tokens.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter).
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter)
	 * @param trimTokens trim the tokens via String's <code>trim</code>
	 * @param ignoreEmptyTokens omit empty tokens from the result array
	 * (only applies to tokens that are empty after trimming; StringTokenizer
	 * will not consider subsequent delimiters as token in the first place).
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(
			String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
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
	 * Selector의 일반 결과 형태 Map -> List 구조에서 특정 Column을 빼네 String[]로 만듬
	 * check Box 나 radio, select 등에 사용 
	 * @param list
	 * @param key
	 * @return
	 */
	public static String[] mapList2StringArray(List list, String key) {
		List rItem = new ArrayList();
		Map t = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				t = (Map) list.get(i);
				if (t != null) {
					rItem.add(t.get(key));
				}
			}

		}

		return StringUtils.toStringArray(rItem);
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