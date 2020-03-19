package maf.lib.excel.importer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import maf.MafUtil;
import maf.util.StringUtils;

public class ExcelErrorInfoBean {
	private int row=0;
	private int col=0;
	
	private String message = null;
	private Map param = null;
	
	public  ExcelErrorInfoBean(int row, int col, String message, Map param){
		this.row = row +0;
		this.col = col +0;
		
		this.message = message;
		this.param = new HashMap();
		if(param != null) {
			Iterator entrys = param.entrySet().iterator();
			while (entrys.hasNext()) {
				Map.Entry me = (Map.Entry)entrys.next();
				String t = MafUtil.getString( me.getValue());
				this.param.put(me.getKey(), StringUtils.leftString(t,10));
			}
		}
		
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Map getParam() {
		return param;
	}
	public void setParam(Map param) {
		this.param = param;
	}
	public int getRow() {
		return row;
	}
	public short getCol() {
		return (short) col;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setCol(int col) {
		this.col = col;
	}
	/**
	 * Column의 no를 알파벳으로 보여 준다. 0 -> A
	 * @return
	 */
	public String getCellName() {
		return Util.getColumnName((short) this.col) + "" + (row +1);
	}
	
	
}
