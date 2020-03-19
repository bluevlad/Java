/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.lib.xml;

import java.io.File;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @author bluevlad
 *
 * XML문서를 digester를 이용하여 처리 Rull.xml 관 config.xml 이용 
 */
public class XMLDigester {
	static Log logger = LogFactory.getLog(XMLDigester.class);
    public static synchronized Object digest(String filePath, String ruleFilePath) throws Exception {
    	Object configuration = null;
    	StringBuffer st = new StringBuffer(100);
    	try{
	        File input = new File( filePath );
	        if( ! input.exists() ) {
	        	throw new Exception("Configfile not found!!");
	        }
	        
	        File rules = new File( ruleFilePath );
	        
	        if( ! rules.exists() ) {
	        	throw new Exception("digest Rulefile[" + ruleFilePath + "] not found!!");
	        }
	        st.append("      - config File : " + input.getAbsolutePath() );
	        st.append(" - rule File : " + rules.getAbsolutePath() );
	        Digester digester = DigesterLoader.createDigester( rules.toURL() );
	        //	      유효성 검사 하지 않음
	        digester.setValidating(false);
	        configuration = digester.parse( input );
        } catch ( Exception e ) {
        	st.append(" !!!Error!!! " + e.getMessage());
            logger.error(e.getMessage());
        }
//        System.out.println(st);
        return configuration;
    }
}

