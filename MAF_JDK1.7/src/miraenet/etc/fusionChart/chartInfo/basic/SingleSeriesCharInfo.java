/*
 * Created on 2006. 09. 28
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.etc.fusionChart.chartInfo.basic;

import java.util.List;

public abstract class SingleSeriesCharInfo extends BasicChartInfo {
	private List data = null;
	
	/**
	 * @return the data
	 */
	public List getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List data) {
		this.data = data;
	}
	
	public StringBuffer getXmlData() {
		StringBuffer str  = new StringBuffer(100);
		if(this.data != null) {
			for (int i = 0; i < data.size(); i ++) {
				
			}
		}
		return str;
	}
}

