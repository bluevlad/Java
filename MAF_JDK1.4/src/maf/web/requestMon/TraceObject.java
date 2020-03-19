/*
 * Created on 2006. 6. 26.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.requestMon;

public class TraceObject {

    public TraceObject()
    {
        uri = null;
        start = 0L;
        address = null;
        depth = 0;
        uri = null;
        start = 0L;
        address = null;
        depth = 0;
    }

    public String getURI()
    {
        return uri;
    }

    public int getDepth()
    {
        return depth;
    }

    public String getRemoteAddr()
    {
        return address;
    }

    public long getStartTime()
    {
        return start;
    }

    public void increaseDepth()
    {
        depth++;
    }

    public void setData(String s, String s1, long l, int i)
    {
        uri = s;
        address = s1;
        start = l;
        depth = i;
    }

    public void setDepth(int i)
    {
        depth = i;
    }

    public String uri;
    public long start;
    public String address;
    public int depth;
}

