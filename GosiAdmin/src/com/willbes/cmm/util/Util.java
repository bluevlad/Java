/*
 * @ Util.java
 *
 * TYPE : 공통 클래스
 *
 *
 * 사용법
 *    - static method는 Util.mehtod()로 호출한다.
 *    - 기타 메소드는 객체생성후 접근한다.
 */

package com.willbes.cmm.util;

import java.io.*;
import java.text.*;

public class Util{

    public Util() {
    }

    // round_type : java.math.BigDecimal.ROUND_HALF_DOWN
    public static float round(float f, int len, int round_type){
        float retval = 0F;
        try{
            retval = (new java.math.BigDecimal(f).setScale(len, round_type)).floatValue();
        }catch(NumberFormatException nfe){}
        return retval;
    }

    // 분모가 0이면 ArithmeticException이 발생하는 것을 방지하기 위한 메소드
    public static float division(float son, float mother){
        float retval = 0F;
        if(mother == 0){
            retval = son;
        }else{
            retval = (son / mother);
        }
        return retval;
    }

    /**
     * 8859_1 -> KSC5601 <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String toEN(String ko)
    {
        String new_str = null;
        try {
            if(ko != null ){
                new_str=new String(ko.getBytes("KSC5601"),"8859_1");
            }
        } catch(UnsupportedEncodingException e) { }
        return new_str;
    }

    /**
     * UTF-8 -> 해당 charset <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String Convert_String(String str, String charset)
    {
        String new_str = null;
        try {
            if(str != null){
                new_str = new String (str.getBytes("UTF-8"), "8859_1");
                new_str = new String (str.getBytes("8859_1"),"KSC5601");
            }
        } catch (UnsupportedEncodingException e) {}
        return new_str;
    }

    /**
     * KSC5601 -> 8859_1 <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String toKSC(String en)
    {
        String new_str = null;
        try {
            if(en != null){
                new_str = new String (en.getBytes("8859_1"), "KSC5601");
            }
        } catch (UnsupportedEncodingException e) {}
        return new_str;
    }

    /**
     * KSC5601 -> 8859_1 <br>
     *
     * @author : miraenet
     * @e-mail : dev@mirenet.com
     * return  : null일 경우, ""을 return
     */
    public static String toKSC2(String en)
    {
        String new_str = null;
        try {
            if(en != null){
                new_str = new String (en.getBytes("8859_1"), "KSC5601");
            } else {
                new_str = "";
            }
        } catch (UnsupportedEncodingException e) {}
        return new_str;
    }

    /**
     * space -> &nbsp;  <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String spaceToNBSP(String source)
    {
        StringBuffer sb = new StringBuffer(source);
        StringBuffer result = new StringBuffer();
        String ch = null;
        for(int i=0; i<source.length(); i++) {

            if (Character.isSpaceChar(sb.charAt(i)))
                ch = "&nbsp;";
            else
                ch = String.valueOf(sb.charAt(i));
            result.append(ch);
        }
        return result.toString();
    }

    /**
     * Encode URL -> Decode URL : jdk 1.x 버전에서는 java.net.URLDecoder 클래스를 지원하지 않기 때문에<br>
     *  jdk1.x 에서 URL 디코딩시 사용. <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String decodeURL(String s)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream(s.length());
        for (int i = 0; i < s.length(); i++){
            int c = (int) s.charAt(i);
            if ( c == '+')
                out.write(' ');
            else if (c == '%'){
                int c1 = Character.digit(s.charAt(++i), 16);
                int c2 = Character.digit(s.charAt(++i), 16);
                out.write((char) (c1 * 16 + c2));
            }else
                out.write(c);
        }
        return out.toString();
    }

    /**
     * 스트링을 int로 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 0 리턴
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static int parseInt(String str)
    {
        int parseInt = 0;
        try{
            parseInt = Integer.parseInt(str);
        }catch(Exception nf){}
        return parseInt;
    }

    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 0 리턴
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static float parseFloat(String str)
    {
        float parseFloat = 0.0f;
        try{
            parseFloat = Float.parseFloat(str);
        }catch(Exception nf){}
        return parseFloat;
    }

    /**
     * 스트링을 int로 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 0 리턴
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static long parseLong(String str)
    {
        long parseLong = 0L;
        try{
            parseLong = Long.parseLong(str);
        }catch(Exception nf){}
        return parseLong;
    }

    /**
     * 스트링을 double로 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 0 리턴
     *
     * @author : miraenet
     * @e-mail : navair@mirenet.com
     */
    public static double parseDouble(String str)
    {
        double parseDouble = 0.0;
        try{
            parseDouble = Double.parseDouble(str);
        }catch(Exception nf){}
        return parseDouble;
    }

    /**
     * 스트링을 int로 변환. NumberFormatException, NullPointerException을 검사하기 위해, Exception 발생시 default value 리턴<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static int parseInt(String str, int def){
        int parseInt = 0;
        try{
            parseInt = Integer.parseInt(str);
        }catch(Exception nf){parseInt = def;}
        return parseInt;
    }

    /**
     * 파라미터의 값이 "" 일때 null 을 리턴하게끔 하는 메소드<br>
     * URL에서 파라미터를 받을때 name 값이 존재하면 "" 이 넘어올 수 있기 때문에 null 값을 검사할때 사용.<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     *
     */
    public static String checkNull(String key){
        String value = key;
        if(key == null || key.equals(""))
            value = null;
        return value;
    }

    /**
     * 날짜형을 년, 월, 일로 나누어 리턴하는 메소드<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String parseDate(String date, int type){
        String parse = "";
        if(date != null && date.length() == 8){
            switch(type){
            case 1: //년
                parse = date.substring(0, 4);
                break;
            case 2: //월
                parse = date.substring(4, 6);
                break;
            default: //일
                parse = date.substring(6, 8);
                break;
            }
        }
        return parse;
    }

    public static String parseYYMM(String date, String delim){
        String parse = null;
        if(date != null && date.length() == 8){
            parse = date.substring(4, 6) + delim +  date.substring(6, 8);
        }
        return parse;
    }

    /**
     * &nbsp -> ""
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String nbspToSpace(String nbsp){
        String value = "";
        if(nbsp != null && !nbsp.trim().equals("&nbsp;")){
            value = nbsp;
        }
        return value;
    }

    /**
     * null -> "" 로 변환하는 메소드<br>
     * : 데이터 수정시 데이터 베이스로 부터 읽은 값이 null이면 수정 폼에 null이 들어가므로 이값을 변환하는 메소드 <br>
     * : 데이터 수정시 null 값을 수정 폼에 setting 할때 사용하면 유용한 메소드. <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String nullToString(String str){
        String value = str;
        if(str == null){
            value = "";
        }
        return value;
    }

    /**
     * null -> default value 로 변환하는 메소드<br>
     *
     * @author :
     */
    public static String defToString(String str,String d_value){
        String value = str;
        if(str == null){
            value = d_value;
        }
        return value;
    }

    /**
     * null or "" --> "&nbsp;" <br>
     * : HTML에서 테이블의 셀에 "" 이 들어가면 테이블이 깨지므로(netscape) 공백문자(&nbsp;)로 대치하는 메소드<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String nullToNbsp(String str){
        String value = str;
        if(str == null || str.equals("")){
            value = "&nbsp;";
        }
        return value;
    }

    /**
     * Object 형을 String 으로 변환<br>
     * : Object 가 null 일때 NullpointerException 을 검사하기 위해서 사용.<br>
     * : ResultSet 으로부터 getObject()로 값을 가져왔을경우 String으로 변환할때 사용하면 유용한 메소드.<br>
     *
     * @author : miraenet
     * @E-mail : sim11@miraenet.com
     */
    public static String toString(Object obj){
        String str = "";
        if(obj != null)
            str = obj.toString();
        return str;
    }

    /**
     * 전체 데이터수로 마지막페이지를 계산해오기 위한 Method.<br>
     * : 게시판 목록 같은 경우 몇 페이지 까지 있는지 계산할때 사용하면 유용한 메소드<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static int getPageCount(int token, int allPage){
        int lastPage =(int)(((allPage-1)/token)+1);
        if(lastPage<=1) lastPage=1;
        return lastPage;
    }

    /**
     * 데이터의 번호를 주기위해 번호를 계산해오는 Method <br>
     * : 게시판 목록 같은 경우 페이지별 데이터의 번호를 계산해 주는 메소드. <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static int getDataNum(int token, int page, int allPage){
        if(allPage<=token){
            return allPage;
        }
        int num = allPage - (token*page) + token;
        return num;
    }

    /**
     * 숫자 만큼 공백문자를 붙혀서 리턴하는 메소드<br>
     * 답변에서 Depth 처리 하는데 사용하면 유용할꺼 같음.<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String levelCount(int level){
        StringBuffer sb = new StringBuffer("");
        if(level > 1) {
            for(int i=0; i<level; i++){
                sb.append("&nbsp;&nbsp;");
            }
        }
        return sb.toString();
    }

    /**
     * 문자열의 값이 null 이거나 ""이면 default 값을 리턴하는 메소드<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String getString(String line, String def){
        if(line == null || line.equals(""))
            return def;
        return line;
    }

    /**
     * 가격등의 값을 3자리 마다 comma(,)로 분리하여 리턴<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String parseDecimal(double unFormat){
        DecimalFormat df = new DecimalFormat("###,###.##");
        String format = df.format(unFormat);
        return format;
    }

    /**
     * 가격등의 값을 3자리 마다 comma(,)로 분리하여 리턴<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String parseDecimal(double unFormat, String foramt){
        DecimalFormat df = new DecimalFormat(foramt);
        String format = df.format(unFormat);
        return format;
    }

    /**
     * 가격등의 값을 3자리 마다 comma(,)로 분리하여 리턴<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String parseDecimal(String unFormat){
        DecimalFormat df = new DecimalFormat("###,###.##");
        String format = df.format(parseDouble(unFormat));
        return format;
    }

    /**
     * Object 의 복제하여 주는 메소드<br>
     * 일반적으로 java.lang.Object.clone() 메소드를 사용하여 Object를 복제하면 Object내에 있는 Primitive type을 제외한 Object <br>
     * field들은 복제가 되는 것이 아니라 같은 Object의 reference를 갖게 된다.<br>
     * 그러나 이 Method를 사용하면 각 field의 동일한 Object를 새로 복제(clone)하여 준다.<br>
     * java.lang.reflect API 를 사용하였음. <br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static Object clone(Object object){
        Class c = object.getClass();
        Object newObject = null;
        try {
            newObject = c.newInstance();
        }catch(Exception e ){
            return null;
        }

        java.lang.reflect.Field[] field = c.getFields();
        for (int i=0 ; i<field.length; i++) {
            try {
                Object f = field[i].get(object);
                field[i].set(newObject, f);
            }catch(Exception e){}
        }
        return newObject;
    }

    /**
     * 디버깅시 Servlet 에서는 PrintWriter 를 넣어서 쉽게 디버깅을 할 수 있었지만 <br>
     * JSP 에서 처럼 PrintWriter가 없을때 디버깅을 쉽게 하기 위하여 메세지를 문자열로 만들어 리턴하게 하였음.<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static String getStackTrace(Throwable e) {
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        java.io.PrintWriter writer = new java.io.PrintWriter(bos);
        e.printStackTrace(writer);
        writer.flush();
        return bos.toString();
    }

    /**
     * 특정 문자열을 다른 문자열로 대체하는 메소드<br>
     * : 문자열 검색시 검색어에 색깔을 넣거나 ... 테그를 HTML 문자로 바꾸는데 사용하면 유용할거 같음.<br>
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     * @ 참고 문헌 : JAVA Servlet Programming(Oreilly)
     */
    public static String replace(String line, String oldString, String newString){
        int index=0;
        while((index = line.indexOf(oldString, index)) >= 0){
            line = line.substring(0, index) + newString + line.substring(index + oldString.length());
            index += newString.length();
        }
        return line;
    }

    /**
     * 문자열을 substring할때 문자열 길이를 넘어설 경우 "" 를리턴하는 메소드
     *
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     * @date : 2001-04-21
     *
     */
    public static String substring(String str, int start, int end){
        String val = null;
        try{
            val = str.substring(start, end);
        }catch(Exception e){
            return "";
        }
        return val;
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
     * 한 자리 숫자에 앞에 '0'을 붙여 String으로 return하는 메소드<br>
     * : argument : str, int
     *
     * @author : miraenet
     * @e-mail :dev@miraenet.com
     */
    public static String addZero(String str) {
        return (Integer.toString(Integer.parseInt(str) + 100)).substring(1,3);
    }

    public static String addZero2(int num) {
        return (Integer.toString(num + 100)).substring(1,3);
    }

    /**
     * 특정 디렉토리의 파일 삭제
     * : argument : 파일위치 + 파일명
     *
     * @author :
     * @e-mail : dev@miraenet.com
     */
    public static void DeleteFile(String path)
    {
        File f = new File(path);
        f.delete();
    }

    /**
     * UserID 복호화 함수
     * @param idStr 복호화된 사내아이디
     * @param company_id 회원사id
     * @return long 회원사의 접속자의 사번
     */
    public static long idDecode_biz(String idStr, String compnay_id) {
        long id=0;
        long decode_id=0;
        long C1 = 52845;
        long C2 = 28317;
        long MYKEY = 0;

        if ("SKT".equals(compnay_id)) {
            MYKEY = 7305;   //SKT 코드
        } else if ("PPS".equals(compnay_id)) {
            MYKEY = 7405;   //PPS 조달청 코드
        }

        if(idStr != null && !idStr.equals("")){
            id = Long.parseLong(idStr);
            if(id != 0){
                decode_id = (long)((id - C2)/C1)-MYKEY;
            }
        }
        return decode_id;   // 회원사의 접속자의 사번
    }

    /**
     * UserID 복호화 함수
     * @param idStr 복호화된 사내아이디
     * @return long 회원사의 접속자의 사번
     */
    public static long idDecode_skt(String idStr) {
        long id=0;
        long decode_id=0;
        long C1 = 52845;
        long C2 = 28317;
        long MYKEY = 7305;  //skt 코드

        if(idStr != null && !idStr.equals("")){
            id = Long.parseLong(idStr);
            if(id != 0){
                decode_id = (long)((id - C2)/C1)-MYKEY;
            }
        }
        return decode_id;   // 회원사의 접속자의 사번
    }

    /**
     * UserID 암호화 함수
     * @param idStr 사내아이디
     * @return String 암호화된 회원사의 접속자의 사번
     */
    public static String idEncode(String idStr) {
        long id=0;
        long encode_id=0;
        long C1 = 52845;
        long C2 = 28317;
        long MYKEY = 7405;            // 조달청 코드

        if(idStr != null && !idStr.equals("")) {
            id = Long.parseLong(idStr);
            if(id != 0){
                encode_id = (long)((id + MYKEY)*C1)+C2;
            }
        }
        return encode_id+"";
    }

    /**
     * UserID 복호화 함수
     * @param idStr 복호화된 사내아이디
     * @return long 회원사의 접속자의 사번
     */
    public static long idDecode(String idStr) {
        long id=0;
        long decode_id = 0;
        long C1 = 52845;
        long C2 = 28317;
        long MYKEY = 7405;

        if(idStr !=null && !"".equals(idStr)) {
            id = Long.parseLong(idStr);
            if(id != 0) {
                decode_id = (long)((id-C2)/C1) - MYKEY;
            }
        }
        return decode_id;   // 회원사의 접속자의 사번
    }

    /**
     * 사용자비밀번호를 암호화
     *
     * @param userpswd 회원비밀번호
     * @return String 인코딩된 회원비밀번호
     * @exception
     */
    public static String encode_passwd(String userpswd) {

        int str_len = 0;
        int add_code = 0;
        int ascii_char = 0;
        int add_ascii = 0;
        char text_char = 'a';
        String enc_string = "";

        str_len = userpswd.length();

        if (userpswd != null && str_len != 0) {
            for (int i=0; i<str_len; i++) {
                add_ascii += userpswd.charAt(i);
            }
            add_code = add_ascii % 60;

            for (int i = 0; i < str_len; i++) {
                ascii_char = userpswd.charAt(i) + add_code + 1;

                if (ascii_char > 255) {
                    ascii_char = ascii_char % 255;
                } else {
                    if (ascii_char < 33) {
                        ascii_char = ascii_char + 32;
                    } else if (ascii_char >= 127 && ascii_char <= 220) {
                        ascii_char -= 94;
                    } else if (ascii_char > 220) {
                        ascii_char -= 188;
                    }
                }

                text_char = (char)ascii_char;

                if (text_char == ';') {
                    text_char = '*';
                } else if (text_char == ' ') {
                    text_char = '%';
                } else if (text_char == '\'') {
                    text_char = '#';
                } else if (text_char == '\"') {
                    text_char = '^';
                }
                enc_string += text_char;
            }

            if (str_len < 12) {
                for (int i = 0; i < (12 - str_len); i++) {
                    ascii_char = enc_string.charAt(i) + add_code + 1;

                    if (ascii_char > 255) {
                        ascii_char = ascii_char % 255;
                    } else {
                        if (ascii_char < 33) {
                            ascii_char = ascii_char + 32;
                        }
                        else if (ascii_char >= 127 && ascii_char <= 220) {
                            ascii_char -= 94;
                        }
                        else if (ascii_char > 220) {
                            ascii_char -= 188;
                        }
                    }
                    text_char = (char)ascii_char;

                    if (text_char == ';') {
                        text_char = '*';
                    } else if (text_char == ' ') {
                        text_char = '%';
                    } else if (text_char == '\'') {
                        text_char = '#';
                    } else if (text_char == '\"') {
                        text_char = '^';
                    }
                    enc_string += text_char;
                }
            }
            return enc_string;
        } else {
            return "";
        }
    }

    /**
     * 주민번호 암호화
     *
     * @param jumin_no 회원주민번호
     * @return String  인코딩된 회원주민번호
     * @exception
     */
    public static String jumin_encode(String in){
        String result = "";
        String eidx[] = {"6522028499","0097667815","2104987583","7185012017","1403907469","0618117781","8276051973","0980721368","8607128910","1687086788"};

        for(int j=0; j<in.length(); j++) {
            int tmp = Integer.parseInt(eidx[Integer.parseInt(in.substring(j,j+1))].substring((j%5)*2,(j%5)*2+2));
            result += (char)(tmp<23?tmp+100:tmp);
        }
        return result;
    }

    /**
     * 주민번호 복호화
     *
     * @param jumin_no 회원주민번호 (암호화된)
     * @return String  디코딩된 회원주민번호
     * @exception
     */
    public static String jumin_decode(String in){
        String result = "";
        String didx[] = {"65002171140682098616","22970485031876800787","02669801901105721208","84787520747719138967","99158317698173681088"};

        for(int k=0; k<in.length(); k++) {
            for(int l=0, m=0; m<10; l+=2,m++) {
                if(didx[(k%5)].substring(l,l+2).equals(in.charAt(k)>99?Integer.toString(in.charAt(k)).substring(1,3):Integer.toString(in.charAt(k)))) result+=Integer.toString(m);
            }
        }
        return result;
    }

    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해, Exception 발생시 0 리턴
     * div 만큼의 소수 이하 자릿수를 잘라 리턴한다.
     * @author : miraenet
     * @e-mail : dev@miraenet.com
     */
    public static float parseFloat(String str, int div){
        int round = 1;
        String d = "1";
        for(int i=0; i<div; i++) {
            d = d + "0";
        }
        round = Integer.parseInt(d);

        float parseFloat = 0.0f;
        try{
            parseFloat = Math.round((Float.parseFloat(str)*round))/(float)round;
        }catch(Exception nf){}
        return parseFloat;
    }

    public static String getSwitchCardCode( String org_cd ) {
        if(null == org_cd || "".equals(org_cd)) {
            return "";
        }
        String retVal = "";
        if("01".equals(org_cd)) { //비씨
            retVal = "31";
        } else if("02".equals(org_cd)) { //국민
            retVal = "11";
        } else if("03".equals(org_cd)) { //외환
            retVal = "21";
        } else if("04".equals(org_cd)) { //삼성
            retVal = "51";
        } else if("05".equals(org_cd)) { //신한
            retVal = "41";
        } else if("08".equals(org_cd)) { //현대
            retVal = "61";
        } else if("09".equals(org_cd)) { //롯데
            retVal = "71";
        } else if("11".equals(org_cd)) { //한미
            retVal = "99";  //기타
        } else if("12".equals(org_cd)) { //수협
            retVal = "34";
        } else if("13".equals(org_cd)) { //신세계
            retVal = "99";  //기타
        } else if("14".equals(org_cd)) { //우리
            retVal = "33";
        } else if("15".equals(org_cd)) { //NH
            retVal = "91";
        } else if("16".equals(org_cd)) { //제주
            retVal = "42";
        } else if("17".equals(org_cd)) { //광주
            retVal = "46";
        } else if("18".equals(org_cd)) { //전북
            retVal = "35";
        } else if("24".equals(org_cd)) { //하나
            retVal = "32";
        } else if("25".equals(org_cd)) { //해외
            retVal = "99";  //기타
        } else if("26".equals(org_cd)) { //씨티
            retVal = "36";
        }

        return retVal;
    }

}