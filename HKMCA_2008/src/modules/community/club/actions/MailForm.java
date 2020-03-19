/*
 * MailForm.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;


/**
 * @author UBQ
 * 메일발송 Form 
 */
public class MailForm  extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response){
        
        result.setForward("mail_form");
    }
}
