/*
 * 작성된 날짜: 2005-03-03
 *
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.club.dao.ClubDB;
import modules.community.actions.CommunityAction;




/**
 * 
 *
 * 작성자 :신규문
 * 작성된 날짜 : 2005-03-03
 */
public class CommonClubIDCheck extends  CommunityAction {    
//    private Logger logger = Logger.getLogger(CommonClubIDCheck.class);
    
    public void doWork(
            MyHttpServletRequest request,
            HttpServletResponse response) throws MafException {
        
        String cid = request.getParameter("cid","").toLowerCase();

        request.setAttribute("check",String.valueOf(ClubDB.chkClub(oDb,cid)));
        result.setForward("default");

    }

}
