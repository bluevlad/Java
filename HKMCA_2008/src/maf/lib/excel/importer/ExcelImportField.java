package maf.lib.excel.importer;

import maf.base.BaseObject;

public class ExcelImportField extends BaseObject {
	public final static String FIELD_TYPE_STRING = "string";
	public final static String FIELD_TYPE_NUMBER = "number";
	public final static String FIELD_TYPE_INTEGER = "integer";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short col = 0;
	private String fieldname = null;
	private String title = null;
	private String type = "string";
	private String sample = null;
	private String desc = null;
	/**
	 * Excel colum 넘버 0부터 시작 
	 * @return
	 */
	public short getCol() {
		return col;
	}
	public void setCol(short col) {
		this.col = col;
	}
	
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * string, number
	 * @return
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if(FIELD_TYPE_NUMBER.equals(type)) {
			this.type = "number";
		} else {
			this.type = "string";
		}
	}
	public String getSample() {
		return sample;
	}
	public void setSample(String sample) {
		this.sample = sample;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
