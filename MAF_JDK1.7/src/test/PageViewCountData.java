/*
 * Created on 2006. 09. 22
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package test;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;

public class PageViewCountData implements DatasetProducer, Serializable {
//	 실제 프로젝트에서 아래의 값들은 하드 코딩 되어서는 안되고 
	  // database 또는 file 등의 data source 로 부터 동적으로 제공되어져야 한다. 
	  // 이것은 테스트 프로그램 이기 때문에 다음과 같이 하드 코딩 하였음을 알린다.
	  private final String[] categories =  { "mon", "tue", "wen", "thu", "fri", "sat", "sun" };

	private final String[] seriesNames = {"cewolfset.jsp", "tutorial.jsp", "testpage.jsp", 

	"performancetest.jsp" };  

	private final Integer[][] values = new Integer[seriesNames.length][categories.length];

	public Object produceDataset(Map params) throws DatasetProduceException {
	    
	    // 어떤 한 카테고리에 하나 혹은 그 이상의 항목들과 각각의 값이 있을때 
	    // 사용하는 데이타셋이다.
	    // 이 예제에서 categories[] 변수(요일)가 카테고리가 되고
	    // 각 카테고리(요일) 마다의 항목은 seriesNames[](jsp 페이지 이름들)이 된다.
	    // 그리고 각각 요일별 항목의 값은 다음 for 문 안에서 임의적으로 생성한다.     
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    for (int series = 0; series < seriesNames.length; series++) {

	    int lastY = (int) (Math.random() * 1000 + 1000);

	      for (int i = 0; i < categories.length; i++) {
	        // 실제로 구현할때  y 값은 데이타 베이스 쿼리로 부터 얻어와야 한다.
	        final int y = lastY + (int) (Math.random() * 200 - 100);
	        lastY = y;
	        // addValue(값, 가로키, 세로키)
	        dataset.addValue((double) y, seriesNames[series], categories[i]);
	      }
	    }
	    return dataset;
	  }

	  public boolean hasExpired(Map params, Date since) {
	    return (System.currentTimeMillis() - since.getTime()) > 5000;
	  }

	  public String getProducerId() {
	    return "PageViewCountData DatasetProducer";
	  }


}

