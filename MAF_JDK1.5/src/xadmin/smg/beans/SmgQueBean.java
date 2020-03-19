// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SmgQueBean.java

package xadmin.smg.beans;

import java.util.Date;

public class SmgQueBean
{

    public SmgQueBean()
    {
    }

    public void setLeccode(String s)
    {
        leccode = s;
    }

    public void setMngnumb(int i)
    {
        mngnumb = i;
    }

    public void setQuenumb(int i)
    {
        quenumb = i;
    }

    public void setQuetext(String s)
    {
        quetext = s;
    }

    public void setQuetype(String s)
    {
        quetype = s;
    }

    public void setUpdate_dt(Date date)
    {
        update_dt = date;
    }

    public void setUpdate_id(String s)
    {
        update_id = s;
    }

    public String getLeccode()
    {
        return leccode;
    }

    public int getMngnumb()
    {
        return mngnumb;
    }

    public int getQuenumb()
    {
        return quenumb;
    }

    public String getQuetext()
    {
        return quetext;
    }

    public String getQuetype()
    {
        return quetype;
    }


    public Date getUpdate_dt()
    {
        return update_dt;
    }

    public String getUpdate_id()
    {
        return update_id;
    }

    private String leccode;
    private int mngnumb;
    private int quenumb;
    private String quetext;
    private String quetype;
    private Date update_dt;
    private String update_id;
}
