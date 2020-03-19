/*
 * Created on 2006. 11. 08
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.view.support;

import java.util.List;
import java.util.Map;
import java.util.Set;

import maf.MafUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewDataSource implements JRDataSource {
	private Log logger = LogFactory.getLog(ViewDataSource.class);
	private List data = null;
	private int index = 0;
	private Map record = null;

	/**
	 * Data Source�� Data�� �����Ѵ�.
	 * @param dataList
	 */
	public ViewDataSource(List dataList) {
		this.data = dataList;
	}

	/**
	 * ���� record�� key(String arg0)�� ���� ���� �´�.
	 * @param arg0
	 * @return
	 * @throws JRException
	 */
	public Object getFieldValue(String arg0) throws JRException {
		if (record != null) {
			return MafUtil.nvl(record.get(arg0), "");
		} else {
			return "";
		}
	}

	/**
	 * ���� record�� key(JRField arg0)�� ���� ���� �´�.
	 * @param arg0
	 * @return
	 * @throws JRException
	 */
	public Object getFieldValue(JRField arg0) throws JRException {
		if (record != null) {
			return MafUtil.nvl(record.get(arg0.getName()), "");
		} else {
			return "";
		}
	}

	/** 
	 * ���� record��  ���� �ش�.
	 * @return
	 */
	public final Map getRecord() {
		return record;
	}

	/**
	 * ���� ���ڵ�� �̵��ϰ� true ����  �� �̵� �� ���� ������ false
	 */
	public boolean next() throws JRException {
		if (data == null || data.size() < index) {
			return false;
		}
		try {
			record = (Map) data.get(index++);
			return true;
		} catch (IndexOutOfBoundsException _ignore) {
			return false;
		} catch (Exception e) {
			logger.error(this.getClass() + " is only support MAP !!!");
			return false;
		}
	}
}
