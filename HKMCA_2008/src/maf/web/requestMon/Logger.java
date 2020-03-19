/*
 * Created on 2006. 6. 26.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.requestMon;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	   private Logger()
	    {
	    }

	    public static void println(String s)
	    {
	        Date date = new Date();
	        FileWriter filewriter = null;
	        try
	        {
	            String s1 = System.getProperty("trace.log", "trace.log");
	            filewriter = new FileWriter(s1, true);
	            PrintWriter printwriter = new PrintWriter(filewriter);
	            printwriter.println(df.format(date) + ":" + s);
	        }
	        catch(Exception exception)
	        {
	            exception.printStackTrace();
	        }
	        finally
	        {
	            if(filewriter != null)
	                try
	                {
	                    filewriter.close();
	                }
	                catch(Exception _ex) { }
	        }
	    }

	    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd/HHmmss");
}

