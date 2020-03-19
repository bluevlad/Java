/*
 * PrdManagerDB.java, @ 2005-05-03
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.dao;

import maf.mdb.CommonDB;

/**
 * @author goindole
 *
 */
public class ExamSet_ManagerDB  extends CommonDB{
    private static ExamSet_ManagerDB instance;


    private ExamSet_ManagerDB() {
    }

    public static synchronized ExamSet_ManagerDB getInstance() {
        if (instance == null)
            instance = new ExamSet_ManagerDB();
        return instance;
    }


}
