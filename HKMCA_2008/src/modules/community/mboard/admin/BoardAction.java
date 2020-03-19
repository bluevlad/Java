/*
 * 작성자 : bluevlad Created on 2004. 10. 29.
 *  
 */
package modules.community.mboard.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.logger.Trace;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
import maf.web.util.SerializeHashtable;
import modules.community.club.beans.ClubMasterBean;
import modules.community.mboard.MBoardManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author bluevlad Create by 2004. 10. 29.
 *  
 */
public class BoardAction extends BoardSuperCommand {
    protected final String MSG_PAGE = "message";
    protected final String ERROR_MSG_PAGE = "error";
    protected final String MBOARD_PATH = "/mboard";

    protected MBoardManager oMbbs = null;;
    private  Log logger = LogFactory.getLog(BoardAction.class);
    protected SerializeHashtable listOp = null;

    protected String listOpStr = "";
    protected String forward = null;
    protected Map map = null;

    protected String auth = "X";

    public  ViewInfoConfig process(ViewInfoMap viewInfoMap, MyHttpServletRequest _req, HttpServletResponse response) throws Exception {
    	this.map = new HashMap();
        ViewInfoConfig viewInfo = null;
        String viewPage = null;
        String pgid = null;
        this.listOpStr = _req.getParameter( "LISTOP", "" );
        this.listOp = new SerializeHashtable( listOpStr );
        String bid = listOp.get( "bid", _req.getParameter( "bid" ) );
        String adminmode = listOp.get( "adminmode", _req.getParameter( "adminmode" ) );
        ClubMasterBean clubBean = null;
        try {
	        if (bid != null) {
	            this.oMbbs = new MBoardManager( bid, _req.getRealPath( MBoardManager.ATT_FILE_PATH ), sessionBean );
	            if(!this.oMbbs.getBoard().isOk()) {
	            	return errorPage( viewInfoMap, _req, "Invalid Board ID" );
	            }
	            this.listOp.put( "bid", bid );
	        } else {
	            return errorPage( viewInfoMap, _req, "Invalid Board ID" );
	        }
	
	        /*
	         * MbbsMetaBean 게시판 메타정보가 제대로 셋팅되었다면 oMbbs.isOk가 true이다. bid가 null이면
	         * isOk가 false가 된다.
	         */
	        if (this.oMbbs != null && this.oMbbs.isOk()) {
	            try {
	                int v_page = MafUtil.parseInt(_req.getP( "v_page"), listOp.getInt( "v_page", 1 ) );
	                listOp.put( "v_page", v_page + "" );
	                this.oMbbs.setV_page( v_page );
	
	                String grp = this.oMbbs.getBoard().getGrp();
	                if ("system".equals( grp )) {
	                    if(sessionBean != null) {
	                        grp = "mypage";
	                    }
	                }
	
	                pgid = this.oMbbs.getBoard().getPgid();
	               
	
	                /*
	                 * MyMBoardAction을 extends한 모든 Class에서 이메소드를 구현한다.
	                 *  
	                 */
	                viewPage = this.doWork( _req, response );
	
	                // 기본적으로 JSP에 넘겨야할 속성
	                // board관련 Jsp에 넘길 정보를 map에 담아 넘긴다.
	                this.map.put( "bid", this.oMbbs.getBid() );
	                this.map.put( "bidPath", this.oMbbs.getBidPath() );
	                this.map.put( "board", this.oMbbs.getBoard() );
	                this.map.put( "CPATH", _req.getContextPath() + "/mboard" );
	                this.map.put( "PATH", "/mboard" );
	                listOp.put( "cmd", "" );
	                listOp.put( "adminmode", adminmode);
	                this.map.put( "listOpStr", this.listOp.getSerializeUrl() );
	                _req.setAttribute( "MBOARD", this.map );
	
	                // template에 title 정보를 넘긴다.
	                _req.setAttribute( "title", this.oMbbs.getBoard().getSubject() );
	
	                viewInfo = viewInfoMap.getViewInfoConfig( viewPage );
	                viewInfo.setPgid(pgid);
	                if("T".equals(adminmode)) {
	                	viewInfo.setTemplateName( "adminData" );
	                }
	                else{
		                if ("club".equals( grp )) {
		                    _req.setAttribute("mstBean", clubBean);
		                    viewInfo.setTemplateName( "club_inner" );
		                } else if (!"ImageView".equals( viewPage )) {
		                    viewInfo.setTemplateName( this.oMbbs.getBoard().getLayout() );
		                }
	                }
	                return viewInfo;
	
	            } catch (Throwable e) {
	                logger.error( Trace.getStackTrace( e ) );
	                return errorPage( viewInfoMap, _req, "게시판 작업중 오류가 발생하였습니니다.관리자에게 문의 바립니다." );
	            }
	            
	        } else {
	            return errorPage( viewInfoMap, _req, "Invalid Board info " );
	        }
        } finally {
        	if(oMbbs != null) {
        		oMbbs.destructODB();
        	}
        }

    }

    /**
     * dumy
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String doWork(MyHttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return null;
    }

}