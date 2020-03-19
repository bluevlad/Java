package maf.lib.excel.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Excel Import / Upload�� �߻��� Error�� ����� ���� �Ѵ�.
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
	 * ������ �߻��� row�� count�� ���Ѵ�. 
	 * @return
	 */
	public int errorCnt() {
		return errorList.size();
	}
	/**
	 * ���� ����� ���� �ش�.
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
	 * Field ����� ���� �ش�. 
	 * @return
	 */
	public List getFieldsList() {
		return this.fieldsList;
	}

	/**
	 * ������ ���� �ߴ��� ���� �ش�.(true �� ���� ���� )
	 * @return
	 */
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	/**
	 * �����޽������� ��Ƽ� ���� �ش�.
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
