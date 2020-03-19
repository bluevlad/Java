/*
 * 작성된 날짜: 2005-03-03
 *
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;


import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.community.actions.CommunityAction;
import modules.community.club.dao.ClubDB;


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
