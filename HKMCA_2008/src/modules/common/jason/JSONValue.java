/*
 * $Id: JSONValue.java,v 1.1 2012/10/31 00:19:57 cvsuser Exp $
 * Created on 2006-4-15
 */
package modules.common.jason;

import java.io.Reader;
import java.io.StringReader;

import modules.common.jason.parser.JSONParser;



/**
 * @author FangYidong<fangyidong@yahoo.com.cn>
 */
public class JSONValue {
	/**
	 * parse into java object from input source.
	 * @param in
	 * @return instance of : JSONObject,JSONArray,String,Boolean,Long,Double or null
	 */
	public static Object parse(Reader in){
		try{
			JSONParser parser=new JSONParser();
			return parser.parse(in);
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static Object parse(String s){
		StringReader in=new StringReader(s);
		return parse(in);
	}
}
