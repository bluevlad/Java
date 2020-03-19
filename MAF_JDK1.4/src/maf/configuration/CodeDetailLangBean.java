package maf.configuration;

import maf.base.BaseObject;
/**
 * Code Detail 정보를 저장함 
 * @author goindole
 *
 */
public class CodeDetailLangBean extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected String groupCD = null;
	protected String codeNo = null;
	protected String lang = null;
	protected String bigo = null;
	protected String seq = null;
	protected String codeName = null;
	

	public String toString() {
		return codeName;
	}

	/**
	 * @return Returns the groupCD.
	 */
	public String getGroupCD() {
		return groupCD;
	}

	/**
	 * @param groupCD The groupCD to set.
	 */
	public void setGroupCD(String groupCD) {
		this.groupCD = groupCD;
	}

	/**
	 * @return Returns the codeName.
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * @param codeName The codeName to set.
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	 * @return Returns the codeNo.
	 */
	public String getCodeNo() {
		return codeNo;
	}

	/**
	 * @param codeNo The codeNo to set.
	 */
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getBigo() {
		return bigo;
	}

	public void setBigo(String bigo) {
		this.bigo = bigo;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}
