package maf.lib.excel.importer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.base.BaseObject;
import maf.util.StringUtils;
/**
 * Excel Upload용 정보 
 * @author bluevlad
 *
 */
public class ExcelImportInfo extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List sqlList = null;
	private Map tmpSql = null;
	private int startrow = 0;
	private String desc = null;
	private List fileds = null;
	private String sampleSql = null;
	
	public ExcelImportInfo() {
		fileds = new ArrayList();
		sqlList = new ArrayList();
		tmpSql = new HashMap();
	}
	
	public String[] getSqls() {
		if(sqlList.size()>0) {
			return StringUtils.toStringArray(sqlList);
		} else {
			return new String[0];
		}
	}

	public void addSql(String sql, String seq) {
		int idx =0;
		idx = MafUtil.parseInt(seq);
		if(sqlList.size() < idx ) {
			idx = sqlList.size();
		}
//		System.out.println(">>> size:" + sqlList.size() + ", seq:" + seq + ", idx:" + idx +"," + sql);
		this.sqlList.add(idx,sql);
		

	}
	public final int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public void addField( ExcelImportField field){
		fileds.add( field);
		
	}
	
	public int size() {
		return fileds.size();
	}
	/**
	 * 하나의 field 정보를 return 
	 * @param index
	 * @return
	 */
	public final ExcelImportField getExcelUploadField(int index) {
		return (ExcelImportField) fileds.get(index);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	

	public final List getFieldsList() {
		return  this.fileds;
	}

	public String getSampleSql() {
    	return sampleSql;
    }

	public void setSampleSql(String sampleSql) {
    	this.sampleSql = sampleSql;
    }
	
}
