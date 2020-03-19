/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 8.
 *
 */
package modules.community.mboard.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.beans.NavigatorInfo;
import maf.web.http.MyHttpServletRequest;
import modules.community.mboard.BoardConfig;
import modules.community.mboard.MBoardAuth;
import modules.community.mboard.admin.BoardAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 * Create by 2004. 10. 8.
 * 
 */
public class BoardList extends BoardAction{
    private  Log logger = LogFactory.getLog(BoardList.class);
    public synchronized String doWork( MyHttpServletRequest _req, HttpServletResponse response)	throws Exception    
	{
//    	if(! MBoardAuth.chkListAuth(oMbbs.getBoard(), super.sessionBean) ) {
//		_req.setAttribute("message","해당게시판의 목록 보기 권한이 없습니다.");
//		return "error";
//}
    	String v_key = _req.getParameter( "v_key" ,listOp.get("v_key", ""));
    	String v_status = _req.getParameter( "v_status" ,listOp.get("v_status", "T"));
    	String v_srch = null;
    	if(_req.containsKey("v_srch")) {
    		v_srch = ( v_key.equals("") )? "" : _req.getParameter( "v_srch");
    	} else {
    		v_srch = ( v_key.equals("") )? "" : listOp.get("v_srch", "");
    	}
    	String btnAuth = "";
    	
    	v_status = ("W".equals(v_status)? "W":"T");
//      List Option 에 숨은 정보 넣기	
//    	logger.debug("bid:" + super.oMbbs.getBid());
    	listOp.put("bid", super.oMbbs.getBid());
    	listOp.put("c_index", "");
    	listOp.put("v_key", v_key);
    	listOp.put("v_srch", v_srch);
    	listOp.put("v_status", v_status);
    	
    	oMbbs.setV_page(super.oMbbs.getV_page());
    	
    	List list = oMbbs.getList( super.oMbbs.getV_page(),  v_key, v_srch, v_status);
    	List noticeList = oMbbs.getNoticeList();

    	//oMbbs.setListDataCount(list.size());
    	btnAuth = MBoardAuth.getBthAuth(super.oMbbs.getBoard(), super.sessionBean, "list", null);
    	NavigatorInfo navigator = null;

    	if(oMbbs.getListDataCount() > 0 ) {
    	    navigator = new NavigatorInfo();
    	    navigator.setCurrentPage(super.oMbbs.getV_page());
    	    navigator.setPageSize(oMbbs.getBoard().getNrows());
    	    navigator.setTotalCount(oMbbs.getListDataCount());
    	    navigator.sync();
    	}
		super.map.put(BoardConfig.PARAM_BTN_AUTH, btnAuth);
		
		_req.setAttribute("list",list );
		_req.setAttribute("noticeList",noticeList );
		
		_req.setAttribute("v_status",v_status);
		_req.setAttribute("v_key", v_key);
		_req.setAttribute("v_srch", v_srch);
		_req.setAttribute("navigator", navigator);
		
		return "boardList"; 
	}

}
