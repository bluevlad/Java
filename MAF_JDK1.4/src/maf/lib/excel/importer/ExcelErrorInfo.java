package maf.lib.excel.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Excel Import / Upload시 발생된 Error의 목록을 관리 한다.
 * @author goindole
 *
 */
public class ExcelErrorInfo {

	private List errorList = new ArrayList();
	private List fieldsList = null;
	private boolean error = false;
	
	
	public ExcelErrorInfo(ExcelImportInfo uinfo) {
		this.fieldsList = uinfo.getFieldsList();
	}
	
	public void addError(int row, int col, String message, Map param) {
		errorList.add(new ExcelErrorInfoBean(row, col, message, param));
		if(!error) {
			error = true;
		}
	}
	

	/**
	 * 에러가 발생된 row의 count를 구한다. 
	 * @return
	 */
	public int errorCnt() {
		return errorList.size();
	}
	/**
	 * 에러 목록을 돌려 준다.
	 * 
	 * @return ExcelErrorInfoBean
	 */
	public ExcelErrorInfoBean[] getErrorList() {
		if(this.errorList == null) {
			return null;
		} else {
			return (ExcelErrorInfoBean[]) errorList.toArray(new ExcelErrorInfoBean[0]);
		}
		
	}
	/**
	 * Field 목록을 돌려 준다. 
	 * @return
	 */
	public List getFieldsList() {
		return this.fieldsList;
	}

	/**
	 * 에러가 존재 했는지 돌려 준다.(true 면 에러 존재 )
	 * @return
	 */
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	/**
	 * 에러메시지들을 모아서 돌려 준다.
	 * @return
	 */
	public String getMessage() {
		ExcelErrorInfoBean[] list = getErrorList();
		StringBuffer err = new StringBuffer(100);
		
		for(int i = 0; i < list.length; i++) {
			err.append("[" + Util.getColumnName(list[i].getCol()) + list[i].getRow() + "]" + list[i].getMessage() + "\n" );
		}
		return err.toString();
	}
}
