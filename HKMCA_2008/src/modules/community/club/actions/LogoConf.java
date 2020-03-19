/*
 * LogoConf.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;


/**
 * @author UBQ
 * 클럽 로고 변경 폼 
 */
public class LogoConf extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response){


        result.setForward("logo_conf");
    }

}