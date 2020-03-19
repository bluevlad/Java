/*
 * Created on 2006. 4. 11.
 * mvc ���� command ����
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf;

public class MafConstant {
	
	/**
	 * List Option�� ������ ���� �� 
	 */
	public static final String LIST_OP_NAME = "LISTOP";
	
	public static final String CMD_NAME = "cmd";

	
	public static final String CMD_LIST = "list";

	/**
	 * view
	 */
	public static final String CMD_VIEW = "view";

	/**
	 * insert �� update�� form
	 */
	public static final String CMD_EDIT = "edit";

	/**
	 * Update
	 */
	public static final String CMD_UPDATE = "update";

	/**
	 * delete 
	 */
	public static final String CMD_DELETE = "delete";
	
	/**
	 * tag Library�� 
	 * @return
	 */
	public static String getCmdName() {
		return CMD_NAME;
	}
	/**
	 * tag Library�� 
	 * @return
	 */
	public static String getListOpName() {
		return LIST_OP_NAME;
	}	
	public static String get(String nm) {
		if ("name".equals(nm)) {
			return CMD_NAME;
		} else if ("list".equals(nm)) {
			return CMD_LIST;
		} else if ("view".equals(nm)) {
			return CMD_VIEW;
		} else if ("edit".equals(nm)) {
			return CMD_EDIT;
		} else if ("update_act".equals(nm)) {
			return CMD_UPDATE;
		} else if ("delete_act".equals(nm)) {
			return CMD_DELETE;
		} else {
			return null;
		}
	}
}
