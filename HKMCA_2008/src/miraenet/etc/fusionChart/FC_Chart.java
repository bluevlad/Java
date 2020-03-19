package miraenet.etc.fusionChart;

import org.jdom.Element;

public class FC_Chart extends BeanXml {
	
	public FC_Chart(String caption) {
		elm = new Element("chart");
		setOptionAttribute("caption", caption);
	}

	public void addValue(String value, String label, String color ) {
		Element c = new Element("set");
		super.setOptionAttribute(c, "label", label);
		super.setOptionAttribute(c, "value", value);
		super.setOptionAttribute(c, "color", color);
		elm.addContent(c);
	}
	
}
