/*
 * 작성된 날짜: 2005-03-02
 *  
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.multipart.UploadedFile;
import modules.community.actions.CommunityAction;
import modules.community.club.ClubManager;
import modules.community.club.beans.ClubMasterBean;
import modules.community.club.dao.ClubDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class CommonClubAdd extends CommunityAction {

      Log logger = LogFactory.getLog(CommonClubAdd.class);
    public void doWork(MyHttpServletRequest request, HttpServletResponse response)
            throws MafException {

        String c_id = request.getParameter( "cid" ); 
        String c_name = request.getParameter( "c_name" ); 
        String c_purpose = request.getParameter( "c_purpose" ); 
        String c_auto_confirm = request.getParameter( "c_auto_confirm" );
        String c_bg_image = request.getParameter( "c_bg_image" ); 


        String forward = "message";
        String msg = null;
        //        insertClub
        ClubMasterBean bean = new ClubMasterBean();
        UploadedFile  cImg  = null;
        try {
            bean.setC_id( c_id.toLowerCase());
            bean.setC_sysopusn( sessionBean.getUsn() );
            bean.setC_name( c_name );
            bean.setC_purpose( c_purpose );
            bean.setC_auto_confirm( c_auto_confirm );
            bean.setC_bg_image(c_bg_image);

             cImg = request.getFileParameter( "c_logo_image" );
            if (cImg != null && cImg.getFileSize() > 0) {
//                String fid = Util.getGUID();
                ClubManager cm = new ClubManager(bean.getC_id());
                cm.saveLogo(request, cImg );
                bean.setC_logo_image( cImg.getNewfilename() );
            }

            ClubDB.insertClub( oDb, bean );

            msg = "성공적으로 커뮤니티를 등록을 했습니다.";
        } catch (Exception e) {
            try{cImg.deleteFile();} catch (Exception ex ) {};
            try{cImg.deleteNewFile();} catch (Exception ex ) {};
            msg = "커뮤니티중 DB 에러가 발생했습니다.";
            forward = "error";
            logger.error( e.getMessage() );
        }

        request.setAttribute( "message", msg );
        request.setAttribute( "next", "/community/clublist.do" );
        result.setForward(forward);

    }

}