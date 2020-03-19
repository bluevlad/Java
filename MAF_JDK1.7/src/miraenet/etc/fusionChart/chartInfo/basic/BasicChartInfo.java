/*
 * Created on 2006. 09. 28
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.etc.fusionChart.chartInfo.basic;

import java.util.HashMap;
import java.util.Map;

import maf.base.BaseObject;

public abstract class BasicChartInfo extends BaseObject {


	
	protected Map graphAttributeMap = null;
	
	public abstract  String getChartType();
	
	public abstract  StringBuffer getXmlData();

	
	public BasicChartInfo() {
		graphAttributeMap = new HashMap();
	}
	public void setGraphAttribute( String key , String value) {
		graphAttributeMap.put(key, value);
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		setGraphAttribute("caption",caption);
	}

	/**
	 * @param subcaption the subcaption to set
	 */
	public void setSubcaption(String subcaption) {
		setGraphAttribute("subcaption",subcaption);
	}

	/**
	 * @param xaxisName the xaxisName to set
	 */
	public void setXaxisName(String xaxisName) {
		setGraphAttribute("xaxisName",xaxisName);
	}

	/**
	 * @param yaxisName the yaxisName to set
	 */
	public void setYaxisName(String yaxisName) {
		setGraphAttribute("yaxisName",yaxisName);
	}
	
}

