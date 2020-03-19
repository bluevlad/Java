// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SmgMngBean.java

package xadmin.smg.beans;

import java.util.Date;

public class SmgMngBean
{

    public SmgMngBean()
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

    public void setMngsdat(Date date)
    {
        mngsdat = date;
    }

    public void setMngedat(Date date)
    {
        mngedat = date;
    }

    public void setMngtitl(String s)
    {
        mngtitl = s;
    }

    public void setMngdesc(String s)
    {
        mngdesc = s;
    }

    public void setMngtype(String s)
    {
        mngtype = s;
    }

    public void setUpdate_dt(Date date)
    {
        update_dt = date;
    }

    public void setUpdate_id(String s)
    {
        update_id = s;
    }

    public void setMngsdat1(String s)
    {
        mngsdat1 = s;
    }

    public void setMngsdat2(String s)
    {
        mngsdat2 = s;
    }

    public void setMngsdat3(String s)
    {
        mngsdat3 = s;
    }

    public void setMngedat1(String s)
    {
        mngedat1 = s;
    }

    public void setMngedat2(String s)
    {
        mngedat2 = s;
    }

    public void setMngedat3(String s)
    {
        mngedat3 = s;
    }

    public String getLeccode()
    {
        return leccode;
    }

    public int getMngnumb()
    {
        return mngnumb;
    }



    public Date getMngsdat()
    {
        return mngsdat;
    }


    public Date getMngedat()
    {
        return mngedat;
    }

    public String getMngtitl()
    {
        return mngtitl;
    }

    public String getMngdesc()
    {
        return mngdesc;
    }

    public String getMngtype()
    {
        return mngtype;
    }



    public Date getUpdate_dt()
    {
        return update_dt;
    }

    public String getUpdate_id()
    {
        return update_id;
    }

    public String getMngsdat1()
    {
        return mngsdat1;
    }

    public String getMngsdat2()
    {
        return mngsdat2;
    }

    public String getMngsdat3()
    {
        return mngsdat3;
    }

    public String getMngedat1()
    {
        return mngedat1;
    }

    public String getMngedat2()
    {
        return mngedat2;
    }

    public String getMngedat3()
    {
        return mngedat3;
    }

    private String leccode;
    private int mngnumb;
    private Date mngsdat;
    private Date mngedat;
    private String mngtitl;
    private String mngdesc;
    private String mngtype;
    private Date update_dt;
    private String update_id;
    private String mngsdat1;
    private String mngsdat2;
    private String mngsdat3;
    private String mngedat1;
    private String mngedat2;
    private String mngedat3;
}
