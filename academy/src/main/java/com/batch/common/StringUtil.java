package com.batch.common;

import java.io.UnsupportedEncodingException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	static String DefaultCharSet = "UTF-8";
	/**
	 * 만든 이: 자바클루(javaclue) 만든 날: 2003/05/15
	 * 
	 * 지정한 정수의 개수 만큼 빈칸(" ")을 스트링을 구한다.
	 * 
	 * @param int 문자 개수
	 * @return String 지정된 개수 만큼의 빈칸들로 연결된 String
	 */
	private static String spaces(int count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}

	/**
	 * 만든 이: 자바클루(javaclue) 만든 날: 2003/06/26
	 * 
	 * 지정한 정수의 개수 만큼 빈칸(" ")을 스트링을 구한다. 절단된 String의 바이트 수가 자를 바이트 개수보다 모자라지 않도록
	 * 한다.
	 * 
	 * @param str
	 *            원본 String
	 * @param int 자를 바이트 개수
	 * @return String 절단된 String
	 */
	private static String cutStringByBytes(String str, int length) {
		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;
		if (length >= len) { return str + spaces(length - len); }
		for (int i = length - 1; i >= 0; i--) {
			if (((int) bytes[i] & 0x80) != 0) counter++;
		}
		return new String(bytes, 0, length + (counter % 2));
	}

	/**
	 * 만든 이: 자바클루(javaclue) 만든 날: 2003/06/26
	 * 
	 * 지정한 정수의 개수 만큼 빈칸(" ")을 스트링을 구한다. 절단된 String의 바이트 수가 자를 바이트 개수를 넘지 않도록 한다.
	 * 
	 * @param str
	 *            원본 String
	 * @param int 자를 바이트 개수
	 * @return String 절단된 String
	 */
	private static String cutInStringByBytes(String str, int length) {
		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;
		if (length >= len) { return str + spaces(length - len); }
		for (int i = length - 1; i >= 0; i--) {
			if (((int) bytes[i] & 0x80) != 0) counter++;
		}
		return new String(bytes, 0, length - (counter % 2));
	}

	private static int getCutLength(String str, int byteLength) {

		int length = str.length();
		int retLength = 0;
		int tempSize = 0;
		int asc;

		for (int i = 1; i <= length; i++) {
			asc = (int) str.charAt(i - 1);
			if (asc > 127) {
				if (byteLength > tempSize) {
					tempSize += 2;
					retLength++;
				}
			} else {
				if (byteLength > tempSize) {
					tempSize++;
					retLength++;
				}
			}
		}

		return retLength;
	}

	private static String getCutUTFString(String str2, int len, String tail) {
		String str = "";
		try {
			str = new String(str2.getBytes(), "8859_1");
		} catch (UnsupportedEncodingException uEE) {
			System.out.println(uEE.toString());
			str = str2;
		}

		if (str.getBytes().length <= len) { return str; }
		java.text.StringCharacterIterator sci = new java.text.StringCharacterIterator(str);
		StringBuffer buffer = new StringBuffer();
		buffer.append(sci.first());
		for (int i = 1; i < len; i++) {
			if (i < len - 1) {
				char tempChar = sci.next();
				buffer.append(tempChar);
			} else {
				char c = sci.next();
				if (c != 32) { //마지막 charater가 공백이 아닐경우 
					buffer.append(c);
				}
			}
		}
		buffer.append(tail);
		return toKor("" + buffer.toString());
	}

	private static String toKor(String e) {
		String kor = null;
		try {
			kor = new String(e.getBytes("8859_1"), "KSC5601");
		} catch (UnsupportedEncodingException ue) {
			ue.printStackTrace();
		}
		return kor;
	}

	
	/**
	 * 스트링 자르기 지정한 정수의 개수 만큼 빈칸(" ")을 스트링을 구한다. 절단된 String의 바이트 수가 자를 바이트 개수를 넘지
	 * 않도록 한다.
	 * 
	 * @param str
	 *            원본 String
	 * @param int 자를 바이트 개수
	 * @param char +1 or -1
	 * @return String 절단된 String
	 */
	private static String cutStr(String str, int length, char type) {
		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;
		if (length >= len) {
			StringBuffer sb = new StringBuffer();
			sb.append(str);
			for (int i = 0; i < length - len; i++) {
				sb.append(' ');
			}
			return sb.toString();
		}
		for (int i = length - 1; i >= 0; i--) {
			if (((int) bytes[i] & 0x80) != 0) counter++;
		}
		String f_str = null;
		if (type == '+') {
			f_str = new String(bytes, 0, length + (counter % 2));
		} else if (type == '-') {
			f_str = new String(bytes, 0, length - (counter % 2));
		} else {
			f_str = new String(bytes, 0, length - (counter % 2));
		}
		return f_str;
	}
	
	
	private static String getCutMultiString1(String orig, double length) {
		List<String> list = getCutMultiString(orig, length);
		StringBuffer sb = new StringBuffer();
		for (String a : list) {
			sb.append(a);
		}
		
		return sb.toString();
	}
	
	private static String lengthLimit(String inputStr, int limit, String fixStr) {
        if (inputStr == null)
            return "";
        if (limit <= 0)
            return inputStr;
        byte[] strbyte = null;
        strbyte = inputStr.getBytes();

        if (strbyte.length <= limit) {
            return inputStr;
        }
        char[] charArray = inputStr.toCharArray();
        int checkLimit = limit;
        for ( int i = 0 ; i < charArray.length ; i++ ) {
            if (charArray[i] < 256) {
                checkLimit -= 1;
            }
            else {
                checkLimit -= 2;
            }
        	System.out.println ("charArray[i] " + charArray[i] + "," + checkLimit);
            if (checkLimit <= 0) {
                break;
            }
        }
        
        //대상 문자열 마지막 자리가 2바이트의 중간일 경우 제거함
        byte[] newByte = new byte[limit + checkLimit];
        for ( int i = 0 ; i < newByte.length ; i++ ) {
            newByte[i] = strbyte[i];
        }
        if (fixStr == null) {
            return new String(newByte);
        }
        else {
            return new String(newByte) + fixStr;
        }
    }
	
	private static List<String> getCutMultiString(String orig, double length) {
		List<String> result = new ArrayList<String>();
		byte[] byteString = orig.getBytes();
		if (byteString.length <= length) {
			result.add(orig);
			return result;
		} else {
			int minusByteCount = 0;
			int sendCount = new Double(Math.ceil(byteString.length / length)).intValue();
			// System.out.println(sendCount);
			int index = 0;
			int start = 0;
			for (int j = 0; j < sendCount; j++) {
				// System.out.println(new Double(j*length));
				minusByteCount = 0;
				for (int i = index; (i < length * (j + 1) && i < byteString.length); i++) {
					minusByteCount += (byteString[i] < 0) ? 1 : 0;
					index++;
				}
				if (minusByteCount % 2 != 0) {
					index--;
				}
				int size = index;
				if (j + 1 == sendCount) {
					size = byteString.length - start;
					// System.out.println(index);
				} else {
					if (size > length) {
						size = index - start;
					}
				}
				// System.out.println(start + " " + index + " " + new
				// String(byteString, start, index ));
				result.add(new String(byteString, start, size));
				start = index;
			}
			return result;
		}
	}
	
	
	private static String cutStr(String str, int cutByte) {
		byte[] strByte = str.getBytes();
		if (strByte.length < cutByte) return str;
		int cnt = 0;
		for (int i = 0; i < cutByte; i++) {
			if (strByte[i] < 0) cnt++;
		}

		String r_str;
		if (cnt % 2 == 0) r_str = new String(strByte, 0, cutByte);
		else r_str = new String(strByte, 0, cutByte + 1);

		return r_str;
	}
	
	
	/**
	 * 
	 * 문자열 자르기
	 * 
	 * @param szText
	 * @param nLength
	 * @return
	 */
	public static String cutInByte2(String szText, int nLength) { // 문자열 자르기
		String r_val = szText;
		int oF = 0, oL = 0, rF = 0, rL = 0;
		int nLengthPrev = 0;
		try {
			byte[] bytes = r_val.getBytes("UTF-8"); // 바이트로 보관
			// x부터 y길이만큼 잘라낸다. 한글안깨지게.
			int j = 0;
			if (nLengthPrev > 0) while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					oF += 2;
					rF += 3;
					if (oF + 2 > nLengthPrev) {
						break;
					}
					j += 3;
				} else {
					if (oF + 1 > nLengthPrev) {
						break;
					}
					++oF;
					++rF;
					++j;
				}
			}
			j = rF;
			while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					if (oL + 2 > nLength) {
						break;
					}
					oL += 2;
					rL += 3;
					j += 3;
				} else {
					if (oL + 1 > nLength) {
						break;
					}
					++oL;
					++rL;
					++j;
				}
			}
			r_val = new String(bytes, rF, rL, "UTF-8"); // charset 옵션
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return r_val;
	}
	
	public static String cutInByte(String szText, int nLength) { // 문자열 자르기
		return cutInByte(szText, nLength, DefaultCharSet);
	}
	public static String cutInByte(String szText, int nLength, String charset) { // 문자열 자르기
		String r_val = szText;
		int oF = 0, oL = 0, rF = 0, rL = 0;
		int nLengthPrev = 0;
		try {
			byte[] bytes = r_val.getBytes(charset); // 바이트로 보관
			// x부터 y길이만큼 잘라낸다. 한글안깨지게.
			int j = 0;
			int charLenth = 3;
			if (nLengthPrev > 0) while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					oF += charLenth;
					rF += (charLenth+1);
					if (oF + charLenth > nLengthPrev) {
						break;
					}
					j += charLenth;
				} else {
					if (oF + 1 > nLengthPrev) {
						break;
					}
					++oF;
					++rF;
					++j;
				}
			}
			j = rF;
			while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					if (oL + charLenth > nLength) {
						break;
					}
					oL += charLenth;
					rL += (charLenth);
					j += (charLenth);
					//System.out.println ( "1. rL  " + rL ) ;
				} else {
					if (oL + 1 > nLength) {
						break;
					}
					++oL;
					++rL;
					++j;
					//System.out.println ( "2. rL  " + rL ) ;
				}
			}
			//System.out.println ( "length " + rF + "," + rL ) ;
			r_val = new String(bytes, rF, rL, charset); // charset 옵션
			//System.out.println ( "length " + r_val.getBytes(charset).length ) ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return r_val;
	}
	 
	
	public static void main(String[] args) {
		String str = "자바클루(javaclue)가 만든 글자 자르기";
		for (int i = 4; i < 24; i++) {
			String returnVal = cutInByte(str, i) ;
			try {
				System.out.println(i + ": [" + returnVal + "] " + returnVal.getBytes("UTF-8").length );
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
