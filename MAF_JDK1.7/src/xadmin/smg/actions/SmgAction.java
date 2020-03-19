package xadmin.smg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.lib.Setter;
import maf.logger.Logging;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.exception.CommandNotFoundException;
import miraenet.Message;
import xadmin.common.actions.AdminAction;
import xadmin.smg.beans.SmgMngBean;
import xadmin.smg.beans.SmgQueBean;
import xadmin.smg.dao.SmgMngDB;
import xadmin.smg.dao.SmgQueDB;


/**
 *
 *  PrfSmgServlet.java
 *  
 *   - 설문관리 프로세스를 담당하는 서블릿.
 * 
 * @ author : 이성수
 * @ e-mail : poison29@miraenet.com
 * @ create-date : 2003-07-31
 * @ modify-date :
 *
 */

public class SmgAction extends AdminAction {
    
     public String doWork(MyHttpServletRequest req, HttpServletResponse res) 	
    	throws MafException {

        
        String leccode = req.getParameter("leccode"); //"SYSTEM"; //(String)ss.getAttribute(Header.LECCODE);
        String admcode = super.sessionBean.getUsn(); //"admin"; //(String)session.getAttribute(Header.ID);

        String cmd = req.getParameter("cmd");
        
        String forward = null;   
        
        SmgMngDB mngHandler = SmgMngDB.getInstance();
        SmgQueDB queHandler = SmgQueDB.getInstance();
        
        // 온라인 설문 리스트
        if(cmd==null){
            req.setAttribute("data",  mngHandler.getAllRec(oDb, leccode));
            req.setAttribute("leccode",  leccode);
            forward = "list";

        // 온라인 설문관리 등록폼
        }else if(cmd.equals("add")){
            req.setAttribute("leccode", leccode);
            forward = "req";

        // 온라인 설문 조회
        }else if(cmd.equals("view")){
            String mngnumb = req.getParameter("mngnumb");
            int cnt = mngHandler.getSmgRec(oDb, leccode, mngnumb);
            req.setAttribute("data", mngHandler.getRecode(oDb, leccode, mngnumb));
            req.setAttribute("quedata", queHandler.getAllRec(oDb, leccode, mngnumb));
            req.setAttribute("ansdata", queHandler.getAllRecAns(oDb, leccode, mngnumb));
            req.setAttribute("leccode", leccode);
            req.setAttribute("ucnt", cnt + "");
            req.setAttribute("can_edit", (cnt > 0) ? new Boolean(false): new Boolean(true));
            forward = "view";

        // 온라인 설문 수정 폼
        }else if(cmd.equals("edit")){
            String mngnumb = req.getParameter("mngnumb");            
            req.setAttribute("data",  mngHandler.getRecode(oDb, leccode, mngnumb));
            req.setAttribute("leccode", leccode);
            forward = "edit";

        // 온라인 설문 등록
        }else if(cmd.equals("insert")){
            SmgMngBean bean = new SmgMngBean();
            Setter s = new Setter(bean, req);
            s.setProperty("*");
            bean.setLeccode(leccode);
            if(bean.getMngsdat1() == null || bean.getMngsdat1().equals("")) {
                bean.setMngsdat1("1753");
                bean.setMngsdat2("01");
                bean.setMngsdat3("01");
            }
            if(bean.getMngedat1() == null || bean.getMngedat1().equals("")) {
                bean.setMngedat1("9999");
                bean.setMngedat2("12");
                bean.setMngedat3("31");
            }
            int result = mngHandler.insert(oDb, bean, admcode);
            if(result == 0) {
                req.setAttribute("message", Message.INSERT_OK);
                req.setAttribute("next", super.control_action+"?cmd=view&leccode="+leccode+"&mngnumb="+mngHandler.getMngnumb());
                forward = "message";
            } else if(result == 1) {//강의가 없음
            	req.setAttribute("message", Message.INSERT_FAIL);
            	forward = "error";
            } else {
            	req.setAttribute("message", Message.INSERT_FAIL);
            	forward = "error";
            }

        // 온라인 설문 수정
        }else if(cmd.equals("update")){
            SmgMngBean bean = new SmgMngBean();
            Setter s = new Setter(bean, req);
            s.setProperty("*");                
            bean.setLeccode(leccode);
            if(bean.getMngsdat1() == null || bean.getMngsdat1().equals("")) {
                bean.setMngsdat1("1753");
                bean.setMngsdat2("01");
                bean.setMngsdat3("01");
            }
            if(bean.getMngedat1() == null || bean.getMngedat1().equals("")) {
                bean.setMngedat1("9999");
                bean.setMngedat2("12");
                bean.setMngedat3("31");
            }
            int result = mngHandler.update(oDb, bean, admcode);  //0:성공, 1:강의가 없음, 2:설문에 응한 회원이 존재, 그외:에러
            if(result == 0) {
                req.setAttribute("message", Message.UPDATE_OK);
                req.setAttribute("next", super.control_action+"?cmd=view&leccode="+leccode+"&mngnumb="+bean.getMngnumb());
                forward = "message";
            } else if(result == 1) {//강의가 없음
            	req.setAttribute("message", Message.UPDATE_FAIL);
            	forward = "error";
            } else if(result == 2) {//설문에 응한 회원이 존재
            	req.setAttribute("message", Message.UPDATE_FAIL);
            	forward = "error";
            } else {
            	req.setAttribute("message", Message.UPDATE_FAIL);
            	forward = "error";
            }

        // 온라인 설문 삭제
        }else if(cmd.equals("delete")){
            String mngnumb = req.getParameter("mngnumb");
            Logging.log(this.getClass(), "mngnumb : " + mngnumb);
            
            int result = mngHandler.delete(oDb, leccode, Integer.parseInt(mngnumb)); //0:성공, 1:강의가 없음, 그외:에러
            if(result == 0) {
                req.setAttribute("message", Message.DELETE_OK);
                req.setAttribute("next", super.control_action+"?leccode="+leccode);
                forward = "message";
            } else if(result == 1) {//강의가 없음
            	req.setAttribute("message", Message.DELETE_FAIL);
            	forward = "error";
            } else {
            	req.setAttribute("message", Message.DELETE_FAIL);
            	forward = "error";
            }
            
        // 온라인 설문 질문 등록 폼
        }else if(cmd.equals("write")){     
            req.setAttribute("leccode", leccode);
        	req.setAttribute("mngnumb", req.getParameter("mngnumb"));
        	req.setAttribute("quetype", req.getParameter("quetype"));
        	forward = "write";

        // 온라인 설문 질문 등록
        }else if(cmd.equals("reg")){
            String mngnumb = req.getParameter("mngnumb");
            int ans_count = req.getParameter("anscount")==null ? 0 : Integer.parseInt(req.getParameter("anscount"));
            String[] ans_desc = new String[ans_count];
            for(int i=0; i<ans_count; i++) {
                ans_desc[i] = req.getParameter("ansdesc"+(i+1));
            }//end for
            SmgQueBean bean = new SmgQueBean();
            Setter s = new Setter(bean, req);
            s.setProperty("*");
            bean.setLeccode(leccode);
            int result = queHandler.insert(oDb, bean, admcode, ans_count, ans_desc);
            if(result == 0) {
                req.setAttribute("message", Message.INSERT_OK);
                req.setAttribute("next", super.control_action+"?cmd=view&leccode="+leccode+"&mngnumb="+mngnumb);
                forward = "message";
            } else if(result == -2) {
            	req.setAttribute("message", Message.INSERT_FAIL);
            	forward = "error";
            } else if(result == 1) {
            	req.setAttribute("message", Message.INSERT_FAIL);
            	forward = "error";
            } else {
            	req.setAttribute("message", Message.INSERT_FAIL);
            	forward = "error";
            }

        // 온라인 설문 질문 수정 폼
        }else if(cmd.equals("modify")){
            String mngnumb = req.getParameter("mngnumb");
            String quenumb = req.getParameter("quenumb");
            req.setAttribute("quetype", req.getParameter("quetype"));

            req.setAttribute("data", queHandler.getRecode(oDb, leccode, mngnumb, quenumb));
            req.setAttribute("ansdata", queHandler.getAnsRecode(oDb, leccode, mngnumb, quenumb));
            req.setAttribute("leccode", leccode);
            forward = "modify";

        // 온라인 설문 질문 수정
        }else if(cmd.equals("upgrade")){
            String mngnumb = req.getParameter("mngnumb");
            int ans_count = req.getParameter("anscount")==null ? 0 : Integer.parseInt(req.getParameter("anscount"));
            String[] ans_desc = new String[ans_count];
            for(int i=0; i<ans_count; i++) {
                ans_desc[i] = req.getParameter("ansdesc"+(i+1));
            }//end for
            SmgQueBean bean = new SmgQueBean();
            Setter s = new Setter(bean, req);
            s.setProperty("*");
            bean.setLeccode(leccode);
            int result = queHandler.update(oDb, bean, admcode, ans_count, ans_desc);
            if(result == 0) {
                req.setAttribute("message", Message.UPDATE_OK);
                req.setAttribute("next", super.control_action+"?cmd=view&leccode="+leccode+"&mngnumb="+mngnumb);
                forward = "message";
            } else if(result == -2) {
            	req.setAttribute("message", Message.UPDATE_FAIL);
            	forward = "error";
            } else if(result == 1) {
            	req.setAttribute("message", Message.UPDATE_FAIL);
            	forward = "error";
            } else {
            	req.setAttribute("message", Message.UPDATE_FAIL);
            	forward = "error";
            }

        // 온라인 설문 질문 삭제
        }else if(cmd.equals("cancel")){        
            int mngnumb = Integer.parseInt(req.getParameter("mngnumb"));
            int quenumb = Integer.parseInt(req.getParameter("quenumb"));
            int result = queHandler.delete(oDb, leccode, mngnumb, quenumb);  //0:성공, -1:설문마스터 없음, -2:설문참여자 있음, 그외:에러
            if(result == 0) {
                req.setAttribute("message", Message.DELETE_OK);
                req.setAttribute("next", super.control_action+"?cmd=view&leccode="+leccode+"&mngnumb="+mngnumb);
//                forward_url = Header.MESG_PAGE;
                forward = "message";
            } else if(result == -2) {
//                forward_url = "/etc/code_error.jsp?err_code=13";
            	req.setAttribute("message", Message.DELETE_FAIL);
            	forward = "error";
            } else {
//                forward_url = "/etc/code_error.jsp?err_code=3";
            	req.setAttribute("message", Message.DELETE_FAIL);
            	forward = "error";
            }

        // 온라인 설문 결과 조회
        }else if(cmd.equals("result")){
            String mngnumb = req.getParameter("mngnumb");
            int usrcount = mngHandler.getSmgUsrCount(oDb, leccode, mngnumb);
            if(usrcount < 1) {
//                forward_url = JSP_BASE + "code_error.jsp?err_code=13";
            	req.setAttribute("message", "설문에 응한 학생이 없습니다.");
            	forward = "error";
            } else {
                req.setAttribute("usrcount", String.valueOf(usrcount));
                req.setAttribute("quedata",  queHandler.getAllRec(oDb, leccode, mngnumb));
                req.setAttribute("ans", queHandler.getAllRecAns(oDb, leccode, mngnumb));
                req.setAttribute("ant", queHandler.getAllRecAnt(oDb, leccode, mngnumb));
                req.setAttribute("leccode", leccode);
//                forward_url = JSP_BASE +  "prfsmg_result.jsp";
                forward = "result";
            }

        // 온라인설문관리 예외처리
        }else{
            throw new CommandNotFoundException();
        }

//        setAttribute(req, "servlet_name", servlet_name);
//        forward(req, res, forward_url);
//        oDb.close();
        return forward;
    }
}