package modules.wlc.lcms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import miraenet.AppConfig;
import miraenet.app.downloader.CommonDownloadServlet;
import miraenet.app.downloader.FileMngDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ReportMngAction  extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	private final String MESSAGE_BUNDLENAME = "common.message";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		super.chkLogin();
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}
	
	/**
	 * 목록 조회
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"s_sjt_cd", "sjt_nm", "os_crs", "os_sjt"};

		String destination = null;

		// Order default 값 설정 시
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:sjt_nm"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("x.crs_cd", ":os_crs"));
		wb.addCond(Where.eq("x.sjt_cd", ":os_sjt"));
		wb.addCond(Where.like("sjt_cd", ":s_sjt_cd"));
		wb.addCond(Where.like("sjt_nm", ":sjt_nm"));
		ReportMngDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		
        if (!"".equals(param.get("os_sjt"))) {
        	destination = "os_sjt";
        } else if  (!"".equals(param.get("os_crs"))) {
        	destination = "os_crs";
        }

        result.setAttribute("navigator", navigator);
		result.setAttribute("destination", destination);
		result.setForward("list");
	}
	
	/**
	 * 등록 화면
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setAttribute("slist", ContentsMngDB.getSubjectList(super.oDb));
		result.setForward("edit");
	}

	/**
	 * 수정 화면
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("sjt_cd", _req.getP("sjt_cd"));
		param.put("quecode", _req.getP("quecode"));
		param.put("pds_cd", "report");
		param.put("main_cd", _req.getP("sjt_cd"));
		param.put("sub_cd", _req.getP("quecode"));

		result.setAttribute("slist", ContentsMngDB.getSubjectList(super.oDb));
		result.setAttribute("item", ReportMngDB.getOne(super.oDb, param));
		result.setAttribute("flist", FileMngDB.getList(super.oDb, param));
		result.setForward("edit");
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res)  throws MafException {

		Map param = _req.getKeyValueMap();
		String pPath = _req.getRealPath(AppConfig.DEFAULT_RTP_ATT_FILE_HTTP);
		param.put("upt_id",super.sessionBean.getUsn());
		boolean chk = false;
		int rcnt = 0;

		try {
	        //파일 업로드
			UploadedFile[] uFile = _req.FileSaveAllTo(_req.getRealPath(AppConfig.DEFAULT_RTP_ATT_FILE_HTTP), false);
			if(uFile != null) {
	        	param.put("pds_cd", "report");
	        	param.put("main_cd", param.get("sjt_cd"));
				chk = true;
	        }
			
			oDb.setAutoCommit(false);
			
			rcnt = ReportMngDB.insertOne(super.oDb, param);
        	param.put("sub_cd", ReportMngDB.maxQuecode(super.oDb, param));
			if(uFile !=null) {
				for(int i =0;i < uFile.length; i++) {
					if(uFile[i].getFileSize() > 0 ) {
		            	uFile[i].SaveAs(pPath, MafUtil.getDBGuid(oDb));
			        	param.put("new_filename", uFile[i].getNewfilename());
			        	param.put("org_filename", uFile[i].getOriginalFileName());
			        	param.put("file_size", uFile[i].getFileSize()+"");
			        	param.put("att_type", uFile[i].getContentType());
			        	rcnt = FileMngDB.insertOne(super.oDb, param);
	                	CommonDownloadServlet.DeleteAttchFile(uFile[i].getOriginalFileName() , pPath);
					}
				}
			}
			oDb.commit();
			
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
	}

    /**
     * 수정 등록
     *
     * @param _req
     * @param _res
     */
    public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		String pPath = _req.getRealPath(AppConfig.DEFAULT_RTP_ATT_FILE_HTTP);
		String delfiles[] = _req.getParameterValues("delfiles");
		param.put("upt_id",super.sessionBean.getUsn());
		
        boolean chk = false;
        int rcnt = 0;

        param.put("pds_cd", "report");
    	param.put("main_cd", param.get("sjt_cd"));
    	param.put("sub_cd", param.get("quecode"));

        try {
        	//파일 삭제
			if(delfiles!=null) {
				for(int i=0;i < delfiles.length; i++) {
		        	param.put("file_id", delfiles[i]);
					FileMngDB.deleteOne(super.oDb, param);
				}
			}
			
        	//파일 업로드
    		UploadedFile[] uFile = _req.FileSaveAllTo(_req.getRealPath(AppConfig.DEFAULT_RTP_ATT_FILE_HTTP), false);

            //update to DB
            rcnt = ReportMngDB.updateOne(super.oDb, param);
			if(uFile!=null) {
				for(int i=0;i < uFile.length; i++) {
					if(uFile[i].getFileSize() > 0 ) {
		            	uFile[i].SaveAs(pPath, MafUtil.getDBGuid(oDb));
			        	param.put("new_filename", uFile[i].getNewfilename());
			        	param.put("org_filename", uFile[i].getOriginalFileName());
			        	param.put("file_size", uFile[i].getFileSize()+"");
			        	param.put("att_type", uFile[i].getContentType());
			        	rcnt = FileMngDB.insertOne(super.oDb, param);
	                	CommonDownloadServlet.DeleteAttchFile(uFile[i].getOriginalFileName() , pPath);
					}
				}
			}
			chk = true;
			oDb.commit();
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
		} finally {
			oDb.setAutoCommit(true);
		}

        if(chk) {
            UrlAddress next = null;
        	next = new UrlAddress (super.controlAction);
            next.addParam("cmd", "edit");
            next.addParam("sjt_cd", param.get("sjt_cd"));
            next.addParam("quecode", param.get("quecode"));
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
        }
    }

    /**
     * 삭제
     *
     * @param _req
     * @param _res
     */
    public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    	Map param = _req.getKeyValueMap();

		String pPath = _req.getRealPath(AppConfig.DEFAULT_RTP_ATT_FILE_HTTP);
        boolean chk = true;
        int rcnt = 0;
        List flist = null;
        Map files = null;
        try {
        	oDb.setAutoCommit(false);
        	rcnt= ReportMngDB.deleteOne(super.oDb, param);
        	param.put("pds_cd", "report");
        	param.put("main_cd", param.get("sjt_cd"));
        	param.put("sub_cd", param.get("quecode"));
    		flist = FileMngDB.getList(super.oDb, param);
            if(flist.size() > 0) {
            	for(int i =0; i < flist.size(); i++) {
            		files = (Map)flist.get(i);
    	        	param.put("pds_cd", files.get("pds_cd"));
    	        	param.put("main_cd", files.get("main_cd"));
    	        	param.put("sub_cd", files.get("sub_cd"));
    	        	param.put("file_id", files.get("file_id"));
                	rcnt += FileMngDB.deleteOne(super.oDb, param);
                	
                	CommonDownloadServlet.DeleteAttchFile(files.get("new_filename").toString(), pPath);
           		}
            }
        	oDb.commit();
        	chk = true;
        } catch (Exception e) {
        	logger.debug(e);
        	oDb.rollback();
        }
        if(chk ) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail", new Integer(rcnt)));
        }
    }
    
}