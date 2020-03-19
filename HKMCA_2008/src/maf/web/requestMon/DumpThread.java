/*
 * Created on 2006. 6. 26.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.requestMon;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DumpThread extends Thread {

    private Map activelist;
    public DumpThread(Map hashtable)
    {
        activelist = null;
        activelist = hashtable;
    }

    public void run()
    {
        long l = System.currentTimeMillis();
        Set set = activelist.entrySet();
        Iterator i = set.iterator();
        try
        {
            while(i.hasNext()) 
                try
                {
                	Map.Entry me = (Map.Entry)i.next();
                    TraceObject traceobject = (TraceObject)me.getValue();
                    long l1 = l - traceobject.getStartTime();
                    Logger.println("DUMP:" + l1 + ":" + traceobject.getURI());
                }
                catch(Exception _ex) { }
        }
        catch(Exception _ex) { }
    }

}

