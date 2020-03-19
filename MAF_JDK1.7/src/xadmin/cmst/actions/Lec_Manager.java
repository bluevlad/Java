/*
 * Created on 2005. 5. 16.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.lib.Setter;
import maf.logger.Logging;
import maf.util.FileUtils;
import maf.web.http.MyHttpServletRequest;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.beans.ResultMessage;
import miraenet.MiConfig;
import miraenet.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xadmin.cmst.beans.LecMstBean;
import xadmin.cmst.beans.lec_prfBean;
import xadmin.cmst.dao.Cnt_ManagerDB;
import xadmin.cmst.dao.Crs_ManagerDB;
import xadmin.cmst.dao.Lec_ManagerDB;
import xadmin.cmst.dao.Sjt_ManagerDB;
import xadmin.common.actions.AdminAction;

/**
 * @author goindole
 * 
 * 개설교과목 관리
 */
public class Lec_Manager extends AdminAction {
	
	private  Log logger = LogFactory.getLog(this.getClass());
	
	public String doWork(MyHttpServletRequest _req, HttpServletResponse _res)
			throws MafException {
		
		
		String forward = null;
		String cmd = _req.getP("cmd", "list");
		int v_page = _req.getIntParameter("v_page", 1);
		v_page = (v_page < 1)?1:v_page;

        String usn = super.sessionBean.getUsn();
		
		int page_size = 10;

		int count = 0;
		int ret = 0;

		List list = null;
		
		Lec_ManagerDB dao = Lec_ManagerDB.getInstance();
		
		//      강의(과목)리스트 보기
		if ("list".equals(cmd)) {

			List crslist = Crs_ManagerDB.getCrsList(oDb);
			Map param = _req.getKeyValueMap(); //new HashMap();

			list = dao.getLecList(oDb, param, v_page, page_size);
			count = dao.getLecCount(oDb, param);
			
			_req.setAttribute("navigator", MafUtil.getNavigator(v_page, page_size, count));
			_req.setAttribute("list", list);
			_req.setAttribute("crslist", crslist);
			
			forward = "list";
		
		
		} else if ("update".equals(cmd)) {
			List crslist = Crs_ManagerDB.getCrsList(oDb);
			List sjtlist = Sjt_ManagerDB.getSjtList(oDb);

			String leccode = _req.getP("leccode", "");
			LecMstBean mbean = dao.getLecMstBean(oDb, leccode);
			List chapters = dao.getChpList(oDb, leccode);
            List prflist = dao.getPrfList(oDb);
            List lecprf = dao.getLecPrfList(oDb, leccode);
			
			_req.setAttribute("item", mbean);
			_req.setAttribute("crslist", crslist);
			_req.setAttribute("sjtlist", sjtlist);
			_req.setAttribute("chapters", chapters);
            _req.setAttribute("prflist", prflist);
            _req.setAttribute("lecprf", lecprf);
			
			forward = "edit";	
		
		//담당교수를 추가한다.
        } else if ("ins_prf".equals(cmd)) {
        	int retValue = 0;
            lec_prfBean lbean = new lec_prfBean();
            int vlng = 0;

            try {
            	    String leccode = _req.getP("leccode", "");
                    String lec_prf = _req.getP("lec_prf", "");
                    vlng = lec_prf.indexOf(":");
                    
                    lbean.setLeccode(leccode);
                    lbean.setUsn(lec_prf.substring(0, vlng));
                    lbean.setUpdate_id(usn);
                    lbean.setFlag(lec_prf.substring(vlng+1, lec_prf.length()));

                    retValue = dao.insLecPrf(oDb, lbean);

            }   catch (Throwable e) {}
                
            if(retValue > 0){
                forward = "message";
                _req.setAttribute( "message", Message.INSERT_OK);
                _req.setAttribute( "next", super.control_action);
            } else{
                forward = "error";
                _req.setAttribute( "message", Message.INSERT_FAIL);
            }            
//          담당교수를 삭제
        } else if ("del_prf".equals(cmd)) {
        	int retValue = 0;
            lec_prfBean lbean = new lec_prfBean();
            oDb.setDebug(true);
            String leccode = _req.getP("leccode", "");
            String prf_usn = _req.getP("prf_usn", "");            
            try {
            	    
                    
                    lbean.setLeccode(leccode);
                    lbean.setUsn(prf_usn);

                    retValue = dao.delLecPrf(oDb, lbean);

            }   catch (Throwable e) {}
                
            if(retValue > 0){
                forward = "message";
                _req.setAttribute( "message", Message.DELETE_OK);
                _req.setAttribute( "next", super.control_action + "?cmd=update&leccode=" + leccode);
            } else{
                forward = "error";
                _req.setAttribute( "message", Message.DELETE_FAIL);
            }            
		
		} else if ("update_act".equals(cmd)) {
//			LecMstBean mbean = new LecMstBean();

//			Setter setter = new Setter(mbean, _req);
//			setter.setProperty("*");
//			mbean.setUpdate_id(super.sessionBean.getUserid());
			Map mbean = _req.getKeyValueMap();
			mbean.put("user_id",super.sessionBean.getUserid());
			UploadedFile upfile = null;
			
			try {
			    String filepath = _req.getRealPath(MiConfig.DEFAULT_SYLLABUS_FILE_PATH);
			    String oldfile = _req.getP("oldfile");
			    String del_attach = _req.getP("del_attach");
			    oDb.setAutoCommit(false);
//			    oDb.setDebug(true);
			    
			    if("T".equals(del_attach)) {
			        if (oldfile != null) {
				        FileUtils.delFile(filepath, oldfile);
				    }
			        ret = dao.updateFile(oDb, mbean);
				}
			    
			    upfile = _req.getFileParameter("attach_file");
			    
			    if ( upfile != null && upfile.getFileSize() > 0 ) {
			        upfile.SaveAs(filepath, MafUtil.getDBGuid(oDb));
				    mbean.put("attach_file",upfile.getNewfilename());
				    mbean.put("attach_filename", upfile.getOriginalFileName());
				    ret = dao.updateFile(oDb, mbean);
				    if (oldfile != null) {
				        FileUtils.delFile(filepath, oldfile);
				    }
			    }
				ret = dao.update(oDb, mbean);
				oDb.commit();
				
			} catch (Throwable e) {
			    if(upfile != null) {
			        upfile.deleteFile();
			        upfile.deleteNewFile();
			    }
			    oDb.rollback();
			    logger.debug(e.getMessage());
			   Logging.trace(e);
			}

			if (ret > 0) {
				forward = "message";
				_req.setAttribute("message", Message.UPDATE_OK);
				_req.setAttribute("next", super.control_action);
			} else {
				forward = "error";
				_req.setAttribute("message", Message.UPDATE_FAIL);
			}	
		
		
		} else if ("delete".equals(cmd)) {
			String leccode = _req.getP("leccode", "");
			ret = dao.delete(oDb, leccode);
			if (ret > 0) {
				forward = "message";
				_req.setAttribute("message", Message.DELETE_OK);
				_req.setAttribute("next", super.control_action);
			} else {
				forward = "error";
				_req.setAttribute("message", Message.UPDATE_FAIL);
			}			
		
		
		} else if ("insert".equals(cmd)) {
			List crslist = Crs_ManagerDB.getCrsList(oDb);
			List sjtlist = Sjt_ManagerDB.getSjtList(oDb);

			LecMstBean mbean = new LecMstBean();
			_req.setAttribute("item", mbean);
			_req.setAttribute("crslist", crslist);
			_req.setAttribute("sjtlist", sjtlist);
			
			forward = "edit";
			
		} else if("insert_act".equals(cmd)) {
			LecMstBean mbean = new LecMstBean();
			Setter setter = new Setter(mbean, _req);
			setter.setProperty("*");
			mbean.setUpdate_id(super.sessionBean.getUserid());
			int cnt = 0;
			try {
				oDb.setAutoCommit(false);
				//String sjtcode = _req.getP("sjtcode");
				String lecyear = _req.getP("lecyear");
				String lecterm = _req.getP("lecterm");
				String lectype = _req.getP("lectype");
				String sjtcodes[] = _req.getParameterValues("sjtcode");
				
				for(int i = 0; i < sjtcodes.length; i ++ ) {
					mbean.setLeccode(sjtcodes[i] + lectype + "-" + lecyear + lecterm );
					mbean.setSjtcode(sjtcodes[i]);
					ret = dao.insert(oDb, mbean);
					if (ret >0) {
						cnt ++;
					}
				}
				dao.createBoard(oDb, mbean.getLeccode());
				oDb.commit();
			} catch (Throwable e) {
				oDb.rollback();
			}

			if (cnt > 0) {
				forward = "message";
				_req.setAttribute("message", cnt + "의 강좌가 개설 되었습니다.");
				_req.setAttribute("next", super.control_action);
			} else {
				forward = "error";
				_req.setAttribute("message", Message.INSERT_FAIL);
			}
		
		} else if("board_init".equals(cmd)) {
			String leccode = _req.getP("leccode");
			int cnt = dao.createBoard(oDb, leccode);
			forward = "message";
			_req.setAttribute("message", cnt+"개의 게시판이 조정되었습니다.");
			_req.setAttribute("next", super.control_action + "?cmd=update&leccode=" + leccode);
		} else if("update_chapter_act".equals(cmd)) {
			// chapter 정보 수정 
			String chapters[] = _req.getParameterValues("chapters");
			String leccode = _req.getP("leccode", "");
			if(chapters != null) {
				Map param = new HashMap();
				param.put("leccode", leccode);
				
				oDb.setAutoCommit(false);
				try{
					for(int i =0; i < chapters.length ; i ++ ) {
						int chpcode = MafUtil.parseInt(chapters[i], -1);
						if(chpcode > -1 ) {
							int chpweek = _req.getIntParameter("chpweek_" + chpcode);
							param.put("chpcode", chpcode+"");
							param.put("chpweek", chpweek+"");
							
							dao.updateChapterInfo(oDb, leccode, param);
						}
					}
					oDb.commit();
					result.setNext(super.control_action + "?cmd=update&leccode=" + leccode);
					result.setSuccess(true);
				} catch ( Throwable e) {
					oDb.rollback();
					logger.error(e.getMessage());
					result.setError(e,new ResultMessage("수정 중 오류가 발생하였습니다."));
				}
			}
			forward = result.getForward();
		/*
		 * 컨텐츠 가져오기 기능의 과목에 등록된 컨텐츠Set의 목록
		 * 
		 * author by YunCheol Kim.
		 * created 2005.06.24
		 */
		} else if ("contents_select".equals(cmd)) {
			String sjtcode = _req.getP("sjtcode");
			

			Cnt_ManagerDB cmdb = Cnt_ManagerDB.getInstance();
			List cntMstList = cmdb.getCntMstListBySjtcode(oDb, v_page, page_size, sjtcode);
			count = cmdb.getCntMstCountBySjtcode(oDb, sjtcode);
			
			_req.setAttribute("navigator", MafUtil.getNavigator(v_page, page_size, count));			
			_req.setAttribute("list", cntMstList);
			
			forward = "contents_select";
			
		
			
		/*
		 * 선택된 컨텐츠의 Set의 컨텐츠 가져오기 실행
		 */	
		} else if ("contents_import".equals(cmd)) {
			
			String sjtcode = _req.getP("sjtcode");
			String leccode = _req.getP("leccode");
			String cnt_id = _req.getP("cnt_id");
	
			Cnt_ManagerDB cmdb = Cnt_ManagerDB.getInstance();
			String row = cmdb.insertContentsIntoLecChp(oDb, sjtcode, cnt_id, leccode);
			
			String msg = "";
			if ("STARTED".equals(row))
				msg = "강의가 시작되어 컨텐츠를 가져올 수 없습니다.";
			else if ("ERROR".equals(row)) {
				msg = "컨텐츠를 가져오는 중 문제가 발생했습니다.";
			} else {
				msg = row + "개의 컨텐츠를 가져왔습니다.";
			}
			_req.setAttribute("message", msg);
			_req.setAttribute("next", super.control_action + "?cmd=update&leccode=" + leccode);
			
			forward = "message";
		
			
		
		/*
		 * 목차 정렬
		 */
		} else if ("chapter_sort".equals(cmd)) {
			
			String leccode = _req.getP("leccode");
			List chapters = dao.getChpList(oDb, leccode);
			
			_req.setAttribute("chapters", chapters);
			
			forward = "chapter_sort";
			
			
		
			
		/*
		 * 차시구성에 시험 Set 지정을 위해 해당과목의 시험Set 목록을 보여준다.
		 */
		} else if ("examset_import".equals(cmd)) {
			
			String sjtcode = _req.getP("sjtcode");
			
			list = dao.getExamSetList(oDb, v_page, page_size, sjtcode);
			count = dao.getExamSetCount(oDb, sjtcode);
			
			_req.setAttribute("navigator", MafUtil.getNavigator(v_page, page_size, count));
			_req.setAttribute("list", list);
			
			forward = "examset_list";
			
		
		
		/*
		 * 차시구성에 시험Set
		 */	
		} else if ("examset_save".equals(cmd)) {
			String leccode = _req.getP("leccode");
			String set_no = _req.getP("examset_no");
			String chpcode = _req.getP("sel_chpcode");
			String examSeq = _req.getP("exam_seq","");
			
			int row = dao.updateLecChpExamSet(oDb, leccode, chpcode, set_no, examSeq);
			
			if (row > 0 )	_req.setAttribute("message", "선택한 시험Set가 저장 되었습니다.");
			else 				_req.setAttribute("message", "선택한 시험Set가 저장되지 않았습니다.");
			_req.setAttribute("next", super.control_action + "?cmd=update&leccode=" + leccode);
			
			forward = "message";
			
			
				
		} else {
			forward = "error";
			_req.setAttribute("message", Message.INVALID_REQUEST);
		}
		
		
		dao = null;
		return forward;
	}
}
