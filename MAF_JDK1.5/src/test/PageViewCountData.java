/*
 * Created on 2006. 09. 22
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package test;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;

public class PageViewCountData implements DatasetProducer, Serializable {
//	 ���� ������Ʈ���� �Ʒ��� ������ �ϵ� �ڵ� �Ǿ�� �ȵǰ� 
	  // database �Ǵ� file ���� data source �� ���� �������� �����Ǿ����� �Ѵ�. 
	  // �̰��� �׽�Ʈ ���α׷� �̱� ������ ������ ���� �ϵ� �ڵ� �Ͽ����� �˸���.
	  private final String[] categories =  { "mon", "tue", "wen", "thu", "fri", "sat", "sun" };

	private final String[] seriesNames = {"cewolfset.jsp", "tutorial.jsp", "testpage.jsp", 

	"performancetest.jsp" };  

	private final Integer[][] values = new Integer[seriesNames.length][categories.length];

	public Object produceDataset(Map params) throws DatasetProduceException {
	    
	    // � �� ī�װ��� �ϳ� Ȥ�� �� �̻��� �׸��� ������ ���� ������ 
	    // ����ϴ� ����Ÿ���̴�.
	    // �� �������� categories[] ����(����)�� ī�װ��� �ǰ�
	    // �� ī�װ�(����) ������ �׸��� seriesNames[](jsp ������ �̸���)�� �ȴ�.
	    // �׸��� ���� ���Ϻ� �׸��� ���� ���� for �� �ȿ��� ���������� �����Ѵ�.     
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    for (int series = 0; series < seriesNames.length; series++) {

	    int lastY = (int) (Math.random() * 1000 + 1000);

	      for (int i = 0; i < categories.length; i++) {
	        // ������ �����Ҷ�  y ���� ����Ÿ ���̽� ������ ���� ���;� �Ѵ�.
	        final int y = lastY + (int) (Math.random() * 200 - 100);
	        lastY = y;
	        // addValue(��, ����Ű, ����Ű)
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

