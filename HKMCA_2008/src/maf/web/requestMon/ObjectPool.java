/*
 * Created on 2006. 6. 26.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.requestMon;

import java.util.Stack;


public class ObjectPool {

    private static ObjectPool instance = null;
    private Stack pool;
    
    public ObjectPool()
    {
        pool = new Stack();
    }

    public void freeObject(TraceObject traceobject)
    {
        pool.push(traceobject);
    }

    public static synchronized ObjectPool getInstance()
    {
        if(instance == null)
            instance = new ObjectPool();
        return instance;
    }

    public synchronized TraceObject getObject()
    {
        TraceObject traceobject = null;
        if(pool.empty())
            traceobject = new TraceObject();
        else
            traceobject = (TraceObject)pool.pop();
        return traceobject;
    }

}

