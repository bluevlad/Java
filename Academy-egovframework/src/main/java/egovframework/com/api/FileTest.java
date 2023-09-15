package egovframework.com.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;

import org.apache.log4j.Logger;
 
public class FileTest {
 
    private static Logger LOG = Logger.getLogger(FileTest.class);
	
    public static void main(String args[]) {
        // TODO Auto-generated method stub
        try{
        	FileTest t = new FileTest();
        	t.init();
        }catch(Exception e){
        	
        }
    	
    }
 
    /**
     * @throws IOException 
     * @see Filter#init(FilterConfig)
     */
    public void init() throws IOException {
        // TODO Auto-generated method stub
    	String filepath = "C:\\Users\\bluev\\Downloads\\member.sql";
    	
    	File file = new File(filepath);

    	LOG.debug("start!");
    	
    	String strText = ReadFileText(file);
    	String tmp_yyyy = "";

    	int findIndex = strText.indexOf("TO_DATE");
    	
        StringBuffer buf = new StringBuffer(strText);
        
        while (findIndex > 0) {
//            LOG.debug("findIndex : " + findIndex);
            tmp_yyyy = strText.substring(findIndex + 15, findIndex + 19);
            buf.insert(findIndex+9, tmp_yyyy+"/");
            buf.delete(findIndex+19, findIndex+24);
            buf.replace(findIndex + 13, findIndex + 14, "-");
            buf.replace(findIndex + 16, findIndex + 17, "-");
            
            findIndex = strText.indexOf("TO_DATE", findIndex+1);
          LOG.debug("findIndex : " + findIndex);
        }
        
        strText = buf.toString();
    	String strText_2 = strText.replace("TO_DATE(", "(");
    	String strText_3 = strText_2.replace(", 'MM/DD/YYYY HH24:MI:SS')", ")");

        LOG.debug("strText : " + strText_3);
    	WrtiteFileText(file, strText_3);

//    	BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file)); 
//    	buffWrite.write(strText_3, 0, strText.length());
//    	buffWrite.flush();
//    	buffWrite.close();
        

        LOG.debug("end!");
    }
    
    public String ReadFileText(File file) {

    	String strText = "";
    	int nBuffer;
        try{
        	BufferedReader buffRead = new BufferedReader(new FileReader(file)); 
            while ((nBuffer = buffRead.read()) != -1) {  
                strText += (char)nBuffer;
            }  
            buffRead.close();
        } catch(Exception e){
        }
    	
        return strText;
    }
    
    public String WrtiteFileText(File file, String strText) {

        try{
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file)); 
        	buffWrite.write(strText, 0, strText.length());
        	buffWrite.flush();
        	buffWrite.close();
        } catch(Exception e){
        }
    	
        return strText;
    }
 
}