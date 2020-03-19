/*
 * Created on 2006. 11. 10
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.view.support;


import maf.MafUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;

/**
 * View 필드 정보 
 * @author bluevlad
 *
 */
public class ViewerFieldBean {
	private String id = null;
	private String title = null; 
	private String style = null; 
	private String format = null;
	private String align = null;
	private short sAlign= HSSFCellStyle.ALIGN_GENERAL;;
	private short sType= HSSFCell.CELL_TYPE_STRING;
	private short sFormatNo = HSSFDataFormat.getBuiltinFormat("General");
	private String type = null;
	private String codeGroup = null;
	private boolean hasCodeGroup = false;


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	
	public short getFormatNo() {
		return this.sFormatNo;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.sFormatNo = HSSFDataFormat.getBuiltinFormat(format);
	}
	
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
//		string|money|float|date|datetime|percent
		if("money".equals(style)){
			sType = HSSFCell.CELL_TYPE_NUMERIC;
			sAlign = HSSFCellStyle.ALIGN_RIGHT;
			this.sFormatNo = HSSFDataFormat.getBuiltinFormat("#,##0");
		}else if("float".equals(style)) {
			sType = HSSFCell.CELL_TYPE_NUMERIC;
			sAlign = HSSFCellStyle.ALIGN_RIGHT;
			this.sFormatNo = HSSFDataFormat.getBuiltinFormat("#,##0.00");
		}else if("percent".equals(style)) {
			sAlign = HSSFCellStyle.ALIGN_RIGHT;
			sType = HSSFCell.CELL_TYPE_NUMERIC;
			this.sFormatNo = HSSFDataFormat.getBuiltinFormat("0.00%");
		}else if("date".equals(style)) {
			sType = HSSFCell.CELL_TYPE_STRING;
			sAlign = HSSFCellStyle.ALIGN_RIGHT;
			this.sFormatNo = HSSFDataFormat.getBuiltinFormat("m/d/yy");
		} else {
			sType = HSSFCell.CELL_TYPE_STRING;
			sAlign = HSSFCellStyle.ALIGN_LEFT;
		}
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	} 
	/**
	 * @return the align
	 */
	public short getAlign() {
		return sAlign;
	}

	/**
	 * @param align the align to set
	 */
	public void setAlign(String align) {
		if("right".equals(align)) {
			sAlign =  HSSFCellStyle.ALIGN_RIGHT;
		} if("center".equals(align)) {
			sAlign =  HSSFCellStyle.ALIGN_CENTER;
		} if("left".equals(align)) {
			sAlign =  HSSFCellStyle.ALIGN_LEFT;
		} else {
			sAlign =  HSSFCellStyle.ALIGN_GENERAL;
		}
	}
	/**
	 * @return the type (string|number)
	 */
	public short getType() {
		return this.sType;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		if("number".equals(type)) {
			sType= HSSFCell.CELL_TYPE_NUMERIC;
		} else {
			sType= HSSFCell.CELL_TYPE_STRING;
		}
	}
	
	public String getCodeGroup() {
		if(this.hasCodeGroup) {
			return codeGroup;
		} else {
			return null;
		}
    }
	
	public void setCodeGroup(String codeGroup) {
		if(!MafUtil.empty(codeGroup)) {
			this.hasCodeGroup = true;
	    	this.codeGroup = codeGroup;
		}
    }
	public boolean hasCodeGroup() {
		return this.hasCodeGroup;
	}
	
}


