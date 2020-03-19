package miraenet.etc.fusionChart;

import org.jdom.Element;

public class FC_TrendLines extends BeanXml {
	
	
	public FC_TrendLines(String seriesName) {
		super.elm = new Element("trendLines");
	}
	

	public void addLine(String startValue, String endValue, String color, String displayValue) {
		Element c = new Element("line");
		super.setOptionAttribute(c, "color", color);
		super.setOptionAttribute(c, "startValue", startValue);
		super.setOptionAttribute(c, "endValue", endValue);
		super.setOptionAttribute(c, "color", color);
		super.setOptionAttribute(c, "displayValue", displayValue);
		super.addContent(c);
	}

	
	public void addValue(String value, String color, String link, 
			int alpha, boolean  showValues ) {
		Element c = new Element("set");
		super.setOptionAttribute(c, "value", value);
		super.setOptionAttribute(c, "color", color );
		super.setOptionAttribute(c, "link", link );
		super.setOptionAttribute(c, "alpha", alpha +"" );
		super.setOptionAttribute(c, "showValues", showValues?"1":"0");
		super.addContent(c);
	}

	
	
}