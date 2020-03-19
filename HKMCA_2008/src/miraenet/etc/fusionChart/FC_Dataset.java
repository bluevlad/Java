package miraenet.etc.fusionChart;

import org.jdom.Element;

public class FC_Dataset extends BeanXml {
	
	
	public FC_Dataset(String seriesName) {
		super.elm = new Element("dataset");
		super.setOptionAttribute( "seriesName", seriesName);
	}
	
	public void addValue(String value) {
		Element c = new Element("set");
		super.setOptionAttribute(c, "value", value);
		super.addContent(c);
	}
	public void addValue(String value, String label) {
		Element c = new Element("set");
		super.setOptionAttribute(c, "value", value);
		super.setOptionAttribute(c, "label", label);
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