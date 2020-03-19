package miraenet.etc.fusionChart;

import org.jdom.Element;

public class FC_Categories extends BeanXml {
	
	public FC_Categories() {
		elm = new Element("categories");
	}
	
	public void addCategory(String label) {
		this.addCategory(label, true, null);
	}

	public void addCategory(String label, boolean isShow) {
		this.addCategory(label, isShow, null);
	}
	
	public void addCategory(String label, boolean isShow, String toolText ) {
		Element c = new Element("category");
		super.setOptionAttribute(c, "label", label);
		super.setOptionAttribute(c, "showLabel", isShow?"1":"0");
		super.setOptionAttribute(c, "toolText ", toolText );
		elm.addContent(c);
	}

	
	
}
