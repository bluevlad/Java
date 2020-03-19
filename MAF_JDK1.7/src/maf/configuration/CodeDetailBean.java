/*
 * Created on 2006. 4. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.configuration;

public class CodeDetailBean {
	String groupCD = null;
	String site = null;
	String codeNo = null;
	String codeName = null;
	String bigo = null;
	String seq = null;
	
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
	 * @return Returns the bigo.
	 */
	public String getBigo() {
		return bigo;
	}
	/**
	 * @param bigo The bigo to set.
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
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
	/**
	 * @return Returns the seq.
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq The seq to set.
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * @return Returns the site.
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSite(String site) {
		this.site = site;
	}
	
	
}

