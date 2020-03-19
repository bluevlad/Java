package modules.wlc.classroom.learner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.beans.ResultMessage;
import miraenet.AppConfig;
import modules.wlc.classroom.tutor.ReportDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 강좌용 시험 관리자 
 * @author ubq
 *
 */
public class ReportAction extends BaseLearnerClassAction{
	private Log logger = LogFactory.getLog(ReportAction.class);
	private final String MESSAGE_BUNDLENAME = "common.message";
    
    /**
     * 시험 리스트 보기 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    	Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		result.setAttribute("list", ReportDB.getList(super.oDb, param));
		result.setForward("list");
    }
    
    
	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("sjt_cd", super.lectureInfo.getSjt_cd());
		param.put("rptcode", _req.getP("rptcode"));
		param.put("usn", super.sessionBean.getUsn());
		
		//출제파일 정보
		param.put("pds_cd", "report");
		param.put("main_cd", _req.getP("sjt_cd"));
		param.put("sub_cd", _req.getP("quecode"));

		//제출파일정보
		param.put("giv", "RPT_GIV");

		result.setAttribute("item", ReportDB.getUserOne(super.oDb, param));
		result.setAttribute("gfile", ReportDB.getGivFile(super.oDb, param));
		result.setAttribute("list", ReportDB.getRptFile(super.oDb, param));
		result.setForward("view");
	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		int rcnt = 0;
		Map param = _req.getKeyValueMap();
		String pPath = _req.getRealPath(AppConfig.DEFAULT_RTP_ATT_FILE_HTTP);
		UploadedFile uFile = _req.getFileParameter("givfile");
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("rptcode", _req.getP("rptcode"));
		param.put("reg_id", super.sessionBean.getUsn());
		param.put("usn", super.sessionBean.getUsn());

		boolean chk = false;
		try {
			oDb.setAutoCommit(false);

			//파일 업로드
			if(uFile != null && uFile.getFileSize() > 0) {
	        	param.put("pds_cd", "RPT_GIV");
	        	param.put("main_cd", super.lectureInfo.getLec_cd());
	        	param.put("sub_cd", _req.getP("rptcode"));
	        	param.put("file_id", super.sessionBean.getUsn());

	        	uFile.SaveAs(pPath, MafUtil.getDBGuid(oDb));
	        	param.put("new_filename", uFile.getNewfilename());
	        	param.put("org_filename", uFile.getOriginalFileName());
	        	param.put("file_size", uFile.getFileSize()+"");
	        	param.put("att_type", uFile.getContentType());
	        	ReportDB.insertFile(super.oDb, param);
	        }
			
            param.put("upt_id", super.sessionBean.getUsn());
    		param.put("lec_cd", super.lectureInfo.getLec_cd());
    		param.put("sjt_cd", super.lectureInfo.getSjt_cd());
    		param.put("rptcode", _req.getP("rptcode"));

            rcnt = ReportDB.insertGiv(super.oDb, param);

			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
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
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		int rcnt = 0;
		Map param = _req.getKeyValueMap();
		String pPath = _req.getRealPath(AppConfig.DEFAULT_RTP_ATT_FILE_HTTP);
		UploadedFile uFile = _req.getFileParameter("givfile");
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("rptcode", _req.getP("rptcode"));
		param.put("reg_id", super.sessionBean.getUsn());
		param.put("usn", super.sessionBean.getUsn());

		boolean chk = false;
		try {
			oDb.setAutoCommit(false);

			//파일 업로드
			if(uFile != null && uFile.getFileSize() > 0 ) {
	        	param.put("pds_cd", "RPT_GIV");
	        	param.put("main_cd", super.lectureInfo.getLec_cd());
	        	param.put("sub_cd", param.get("rptcode"));
	        	param.put("file_id", super.sessionBean.getUsn());
                maf.util.FileUtils.delFile(pPath, _req.getP("newfilename"));

	        	uFile.SaveAs(pPath, MafUtil.getDBGuid(oDb));
	        	param.put("new_filename", uFile.getNewfilename());
	        	param.put("org_filename", uFile.getOriginalFileName());
	        	param.put("file_size", uFile.getFileSize()+"");
	        	param.put("att_type", uFile.getContentType());
	        	ReportDB.updateFile(super.oDb, param);
	        }
			
            param.put("upt_id", super.sessionBean.getUsn());
    		param.put("lec_cd", super.lectureInfo.getLec_cd());
    		param.put("sjt_cd", super.lectureInfo.getSjt_cd());
    		param.put("rptcode", param.get("rptcode"));

            rcnt = ReportDB.updateGiv(super.oDb, param);

			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}

}