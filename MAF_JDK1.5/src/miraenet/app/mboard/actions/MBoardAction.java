/*
 * �ۼ��� : ����� Created on 2004. 10. 29.
 *  
 */
package miraenet.app.mboard.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.logger.Trace;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
import maf.web.util.SerializeHashtable;
import miraenet.app.club.beans.ClubMasterBean;
import miraenet.app.mboard.MBoardManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author �����(goindole@miraenet.com) Create by 2004. 10. 29.
 *  
 */
public class MBoardAction extends MBoardSuperCommand {
    protected final String MSG_PAGE = "message";
    protected final String ERROR_MSG_PAGE = "error";
    protected final String MBOARD_PATH = "/mboard";

    protected MBoardManager oMbbs = null;;
    private  Log logger = LogFactory.getLog(MBoardAction.class);
    protected SerializeHashtable listOp = null;

    protected String listOpStr = "";
    protected String forward = null;
    protected Map map = null;

    protected String auth = "X";

    public  ViewInfoConfig process(ViewInfoMap viewInfoMap, MyHttpServletRequest _req,
            HttpServletResponse response) throws Exception {
    	this.map = new HashMap();
        ViewInfoConfig forward = null;
        String viewPage = null;
        String pgid = null;
        this.listOpStr = _req.getParameter( "LISTOP", "" );
        this.listOp = new SerializeHashtable( listOpStr );
        String bid = listOp.get( "bid", _req.getParameter( "bid" ) );
        String adminmode = listOp.get( "adminmode", _req.getParameter( "adminmode" ) );
        ClubMasterBean clubBean = null;
        logger.debug("board id = " + bid);
        if (bid != null) {
            this.oMbbs = new MBoardManager( oDb, bid, _req
                    .getRealPath( MBoardManager.ATT_FILE_PATH ), sessionBean );
            if(!this.oMbbs.getBoard().isOk()) {
            	return errorPage( viewInfoMap, _req, "Invalid Board ID" );
            }
            this.listOp.put( "bid", bid );
        } else {
            return errorPage( viewInfoMap, _req, "Invalid Board ID" );
        }

        /*
         * MbbsMetaBean �Խ��� ��Ÿ������ ����� ���õǾ��ٸ� oMbbs.isOk�� true�̴�. bid�� null�̸�
         * isOk�� false�� �ȴ�.
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
                 * MyMBoardAction�� extends�� ��� Class���� �̸޼ҵ带 �����Ѵ�.
                 *  
                 */
                viewPage = this.doWork( _req, response );

                // �⺻������ JSP�� �Ѱܾ��� �Ӽ�

                // board���� Jsp�� �ѱ� ������ map�� ��� �ѱ��.
               
                this.map.put( "bid", this.oMbbs.getBid() );
                this.map.put( "bidPath", this.oMbbs.getBidPath() );
                this.map.put( "board", this.oMbbs.getBoard() );
                this.map.put( "CPATH", _req.getContextPath() + "/mboard" );
                this.map.put( "PATH", "/mboard" );
                listOp.put( "cmd", "" );
                listOp.put( "adminmode", adminmode);
                this.map.put( "listOpStr", this.listOp.getSerializeUrl() );
                _req.setAttribute( "MBOARD", this.map );

                // template�� title ������ �ѱ��.
                _req.setAttribute( "title", this.oMbbs.getBoard().getSubject() );

                forward = viewInfoMap.getViewInfoConfig( viewPage );
                forward.setPgid(pgid);
                if("T".equals(adminmode)) {
                	forward.setTemplateName( "adminData" );
                }
                else{
	                if ("club".equals( grp )) {
	                    _req.setAttribute("mstBean", clubBean);
	                    forward.setTemplateName( "club_inner" );
	                } else if (!"ImageView".equals( viewPage )) {
	                    forward.setTemplateName( this.oMbbs.getBoard().getLayout() );
	                }
                }
                return forward;

            } catch (Throwable e) {
                logger.error( Trace.getStackTrace( e ) );
                return errorPage( viewInfoMap, _req, "�Խ��� �۾��� ������ �߻��Ͽ����ϴϴ�.�����ڿ��� ���� �ٸ��ϴ�." );
            }
        } else {
            return errorPage( viewInfoMap, _req, "Invalid Board info " );
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