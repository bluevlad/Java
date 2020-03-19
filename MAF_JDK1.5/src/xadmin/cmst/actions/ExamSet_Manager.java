/*
 * PrdManager.java, @ 2005-05-03
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.lib.Setter;
import maf.web.http.MyHttpServletRequest;
import maf.web.multipart.UploadedFile;
import miraenet.MiConfig;
import miraenet.Message;
import xadmin.cmst.beans.SjtMstBean;
import xadmin.cmst.beans.bank_ansBean;
import xadmin.cmst.beans.bank_examBean;
import xadmin.cmst.beans.bank_sjtBean;
import xadmin.cmst.dao.Crs_ManagerDB;
import xadmin.cmst.dao.Exam_ManagerDB;
import xadmin.cmst.dao.Sjt_ManagerDB;
import xadmin.common.actions.AdminAction;

/**
 * @author xxx
 *
 */
public class ExamSet_Manager extends AdminAction {
    
    public String doWork(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        
    	int v_page = _req.getIntParameter("v_page",1);
        v_page = (v_page < 1) ? 1 : v_page;
        String cmd = _req.getP("cmd","list");
        String forward = null;
        int page_size = 10;

        String v_key = _req.getP("v_key", "");
        String v_srch = _req.getP("v_srch", "");
        String v_sort = _req.getP("v_sort", "");
        String v_crscode = _req.getP("v_crscode", "ALL");
        
        int count  = 0;
        List list = null;

        bank_examBean bBean = new bank_examBean();
        bank_ansBean aBean = new bank_ansBean();
        bank_sjtBean sBean = new bank_sjtBean();

        Exam_ManagerDB dao = Exam_ManagerDB.getInstance();
        Sjt_ManagerDB sdao = Sjt_ManagerDB.getInstance();

        String usn = super.sessionBean.getUsn().toString();

        String cmdExt = "?v_page="+v_page;

        String filepath = _req.getRealPath(MiConfig.DEFAULT_EXAM_FILE_PATH); // 문제이미지 저장 경로
        UploadedFile upfile = null;

        if(cmd.equals("list")) {

            List crslist = Crs_ManagerDB.getCrsList(oDb);

            list = sdao.getSjtList(oDb, v_page, page_size, v_key, v_srch,
                    v_sort, v_crscode);
            count = sdao.getCrsCount(oDb, v_key, v_srch, v_sort, v_crscode);

            forward = "list";

            NavigatorInfo navigator = null;
            navigator = new NavigatorInfo();

            navigator.setCurrentPage(v_page);
            navigator.setPageSize(page_size);
            navigator.setTotalCount(count);
            navigator.sync();

            _req.setAttribute("navigator", navigator);
            _req.setAttribute("list", list);
            _req.setAttribute("crslist", crslist);
        }
        else if(cmd.equals("write")){ //교과목에 등록된 문제은행 리스트를 보여준다.

            List list_chp = null;

            String sjtcode = _req.getP("sjtcode");
            String module = _req.getP("module");
            SjtMstBean item = sdao.getSjtView(oDb, sjtcode);

            list_chp = dao.getChpList(oDb, sjtcode);
            if ("".equals(module) || module == null){
                if(list_chp.size() > 0) {
                    Map x = (Map) list_chp.get(0);
                    module = (String) x.get("chapter");
                }
                else{
                	list_chp = null;
                }
            }
            list = dao.getSjtExamList(oDb, sjtcode, module);
            
            _req.setAttribute("item", item);
            _req.setAttribute("list", list);
            _req.setAttribute("list_chp", list_chp);
            _req.setAttribute("sjtcode", sjtcode);
            _req.setAttribute("module", module);
            _req.setAttribute("v_page", v_page+"");
            forward = "write";
        }
        else if(("insert").equals(cmd)){

            int vNo = 0;

            String[] atitle = _req.getParameterValues("atitle");
            String sjtcode = _req.getP("sjtcode");
            String chapter = _req.getP("chapter");

            Setter setter = new Setter(bBean, _req);
            setter.setProperty("*");

            Setter setter2 = new Setter(aBean, _req);
            setter2.setProperty("*");

            Setter setter3 = new Setter(sBean, _req);
            setter3.setProperty("*");

            try {

                oDb.setAutoCommit(false);
                oDb.setDebug(true);


                upfile = _req.getFileParameter("imgfile");
                if ( upfile.getFileSize() > 0 ) {
                    upfile.SaveAs(filepath, MafUtil.getDBGuid(oDb));
                    bBean.setImg(upfile.getNewfilename());
                }
    
                bBean.setReg_id(usn);
                bBean.setStatus("1");
                vNo = dao.insertExam(oDb, bBean);
                    
                if(null != atitle && vNo > 0) {
                    for(int i = 0; i < atitle.length; i++) {
                        aBean.setExam_no(vNo);
                        aBean.setAns_no(i+1);
                        aBean.setTitle(atitle[i]);
                        aBean.setReg_id(usn);
                        dao.insertAns(oDb, aBean);
                    }
                        sBean.setSjtcode(sjtcode);
                        sBean.setExam_no(vNo);
                        sBean.setReg_id(usn);
                        sBean.setChapter(chapter);
                        dao.insertExamSjt(oDb, sBean);
                }
            }   catch (Throwable e) {
                if(upfile != null) {
                    upfile.deleteFile();
                    upfile.deleteNewFile();
                }
            }
            
            if(vNo > 0){
                forward = "message";
                cmdExt = cmdExt + "&cmd=write&sjtcode="+sjtcode;
                _req.setAttribute( "message", Message.INSERT_OK);
                _req.setAttribute( "next", super.control_action+cmdExt);

            } else{
                forward = "error";
                _req.setAttribute( "message", Message.INSERT_FAIL);
            }            
        }
        else if(("popExam").equals(cmd)) { // 문제의 상세정보를 본다.

            int exam_no = _req.getIntParameter("exam_no");
            
            Map mExam = null;
            
            mExam = dao.getExamView(oDb, exam_no);
            list = dao.getAnsList(oDb, exam_no);
            
            _req.setAttribute("mExam", mExam);
            _req.setAttribute("list", list);
            _req.setAttribute( "action", "update" );
            _req.setAttribute("v_page", v_page+"");
            _req.setAttribute("filepath", _req.getContextPath()+MiConfig.DEFAULT_EXAM_FILE_PATH);

            forward = "exam_pop";
        }
        else if(("exam_sjt_del").equals(cmd)) {  //교과목에 등록된 문제를 삭제한다.

            String[] ids = _req.getParameterValues("ids");
            String sjtcode = _req.getP("sjtcode");

            int ret = 0;

            if(null != ids) {
                for(int i =0; i < ids.length; i++) {
                    ret = dao.deleteExamSjt(oDb, MafUtil.parseInt(ids[i]), sjtcode);
                }
            }

            if(ret>0){
                forward = "message";
                cmdExt = cmdExt + "&cmd=write&sjtcode="+sjtcode;
                _req.setAttribute( "message", Message.DELETE_OK);
                _req.setAttribute( "next", super.control_action+cmdExt);

            } else{
                forward = "error";
                _req.setAttribute( "message", Message.DELETE_FAIL);
            }            
        }

        return forward;
    }
    
}
