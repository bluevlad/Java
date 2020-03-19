/*
 * Created on 2005. 5. 18.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tags.old;

import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.configuration.CodeDetailBean;
import maf.web.util.HTMLUtil;

/**
 * @author goindole
 * 
 */
public class MiHtmlTag {
	/**
	 * Checkbox checked 자동 부여
	 * String, String[], Boolean에 대해 처리 
	 * @param value
	 * @param vText
	 * @param curValue
	 * @return
	 */
	public static String getHtmlCheckbox(String name, String value,
			Object curValue) {
		StringBuffer sHtml = new StringBuffer(50);
		sHtml.append("<input style='border:none;' type='checkbox' name='");
		sHtml.append(name.trim());
		sHtml.append("' value='");
		sHtml.append(HTMLUtil.htmlspecialchars(value));
		sHtml.append("' ");

		if (curValue != null && value != null) {
//			if (java.lang.String.class.equals(curValue.getClass())) {
			if (curValue instanceof Object[]) {
				
				String[] x = (java.lang.String[]) curValue;
				for (int i = 0; i < x.length; i++) {
//					System.out.println(x[i] + "=" + value +",");
					if (x[i] != null && x[i].equals(value)) {
						sHtml.append("checked");
						break;
					}
				}
			} else if (curValue instanceof Object) {
					if (value.equals(curValue.toString())) {
						sHtml.append("checked");
					}
			} else if(Boolean.class.equals(curValue.getClass())) {
				if(((Boolean) curValue).booleanValue()) {
					sHtml.append("checked");
				}
			} 
		}
		sHtml.append(">");
		return sHtml.toString();
	}

	/**
	 * radio에 checked 자동 부여
	 * 
	 * @param value
	 * @param vText
	 * @param curValue
	 * @return
	 */
	public static String getHtmlRadio(String name, String value, String curValue) {
		StringBuffer sHtml = new StringBuffer(50);
		sHtml.append("<input style='border:none;' type='radio' name='");
		sHtml.append(name.trim());
		sHtml.append("' value='");
		sHtml.append(HTMLUtil.htmlspecialchars(value));
		if (curValue != null && value != null
				&& value.equals(curValue.toString())) {
			sHtml.append("' checked>");
		} else {
			sHtml.append("'>");
		}
		return sHtml.toString();
	}

	/**
	 * Select Tag의 option에 SELECTED 자동 부여
	 * 
	 * @param value
	 * @param vText
	 * @param curValue
	 * @return
	 */
	public static String getHtmlOption(String value, String vText,
			Object curValue) {
		if (value == null) {
			value = "";
		}
		if (vText == null){
			vText = "";
		}

		StringBuffer sHtml = new StringBuffer(50);
		sHtml.append("<OPTION value='");
		sHtml.append(HTMLUtil.htmlspecialchars(value));
		sHtml.append("' ");
		
		if (curValue != null && value != null) {
//			if (java.lang.String.class.equals(curValue.getClass())) {
			if (curValue instanceof Object) {
				if (value.equals(curValue.toString())) {
					sHtml.append("SELECTED");
				}
//			} else if (java.lang.String[].class.equals(curValue.getClass())) {
			} else if (curValue instanceof Object[]) {
				String[] x = (java.lang.String[]) curValue;
				for (int i = 0; i < x.length; i++) {
					if (x[i] != null && x[i].equals(value.toString())) {
						sHtml.append("SELECTED");
						break;
					}
				}
			}
		}
		
		sHtml.append(">");
		
		sHtml.append(MafUtil.nvl(vText,""));
		sHtml.append("</OPTION>");
		return sHtml.toString();
	}

	/**
	 * Select Tag의 option에 
	 * 
	 * @param Value
	 * @param vText
	 * @param CurValue
	 * @return
	 */
	public static String getHtmlOption(String Value, String vText) {
		return getHtmlOption(Value, vText, null);
	}
	
	/**
	 * Map의 List 형태를 Option으로 그려 준다.
	 * @param list
	 * @param nm_value
	 * @param nm_caption
	 * @param curValue
	 * @return
	 */
	public static String getHtmlOptions(List list, String nm_value, String nm_caption, Object curValue) {
		StringBuffer sHtml = new StringBuffer(100);
		String value = null;
		String caption = null;
		Object ob = null;
		if(list != null) {
			for(int i = 0; i<list.size() ; i++) {
				ob = list.get(i);
				if ( ob instanceof CodeDetailBean) { 
					value = ((CodeDetailBean)ob).getCodeNo();
					caption = ((CodeDetailBean)ob).getCodeName();
				} else {
					
					value = MafUtil.getString(((Map)ob).get(nm_value));
					caption = MafUtil.getString(((Map)ob).get(nm_caption));
				}
				sHtml.append(getHtmlOption(value,caption,curValue));
			}
			return sHtml.toString();
		} else {
			return "";
		}
	}
	

	public static String getHtmlBarGraphH(String witdh, String height, String percent, String img, String bgcolor) {
		StringBuffer shtml = new StringBuffer(100);
		double p = MafUtil.parseDouble(percent);
		if ( p > 1)  p = 1;
		if ( p < 0)  p = 0;
		shtml.append("<TABLE border='0' cellspacing='0' cellpadding='0' width='");
		shtml.append(witdh);
		shtml.append("'>");
		shtml.append("<TR><TD align='left' style='background-color: ");
		shtml.append(bgcolor);
		shtml.append(";'><img src='");
		shtml.append(img);
		shtml.append("' border='0' height='");
		shtml.append(height);
		shtml.append("' width='");
		shtml.append("" + MafUtil.parseDouble(witdh) / 100.0 * 100 * p);
		shtml.append("'></TD></TR></TABLE>");
		
		return shtml.toString();
	}
	
	
	
}
