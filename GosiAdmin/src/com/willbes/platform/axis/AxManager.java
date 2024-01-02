/**
 * 
 */
package com.willbes.platform.axis;

//import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
//import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author axissoft,inc
 *
 */
public class AxManager {

	public AxManager(String xmlurl) throws ParserConfigurationException, SAXException, IOException
	{
		setXml(xmlurl);
	}
	
	public void setXml(String xmlurl) throws ParserConfigurationException, SAXException, IOException
	{
		this.docBuilder_ = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        this.doc_ = this.docBuilder_.parse(xmlurl);	
	}
	
	public String getQueryStringByName(String name) throws ParserConfigurationException, SAXException, IOException
	{
        String result = "";
        NodeList nList = this.doc_.getDocumentElement().getElementsByTagName(name);

        if (nList.getLength() >= 1)
        {
        	result =  nList.item(0).getLocalName();
//        	result =  nList.item(0).getTextContent();
        }
        return result;
    }

	private DocumentBuilder docBuilder_;
	private Document doc_;


}
