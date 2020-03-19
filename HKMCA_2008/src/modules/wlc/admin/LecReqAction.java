/*
 * Created on 2006. 03. 03.
 *
 */
package modules.wlc.admin;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;



import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.lib.excel.importer.ExcelErrorInfo;
import maf.lib.excel.importer.ExcelErrorInfoBean;
import maf.lib.excel.importer.ExcelImportManager;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.mvc.view.ViewerSupport;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/*
*/
public class LecReqAction extends MafAction {
    final private Log  logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";
		
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}	
	
	/**
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"s_reqstat", "s_sch_sdt", "s_sch_edt", "s_userid", "s_nm", "os_crs", "os_sjt", "os_lec"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		String destination = null;
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("x.req_stat", ":s_reqstat"));
		wb.addCond(Where.between("x.req_dt", ":s_sch_sdt", ":s_sch_edt"));
        wb.addCond(Where.like("x.userid", ":s_userid"));
        wb.addCond(Where.like("x.nm", ":s_nm"));
        wb.addCond(Where.eq("x.crs_cd", ":os_crs"));
        wb.addCond(Where.eq("x.sjt_cd", ":os_sjt"));
        wb.addCond(Where.eq("x.lec_cd", ":os_lec"));

        if (!"".equals(param.get("os_lec"))) {
        	destination = "os_lec";
        } else if (!"".equals(param.get("os_sjt"))) {
        	destination = "os_sjt";
        } else if  (!"".equals(param.get("os_crs"))) {
        	destination = "os_crs";
        }
		LecReqDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setAttribute("destination", destination);
		result.setForward("list");
	}
	
	/**
	 * 수강상태 처리하기. (신청, 승인, 수료, 취소)
	 * @param _req
	 * @param _res
	 */
	public void cmdComplete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		boolean chk = false;
		
		String[] vusn = _req.getParameterValues("v_usn");
		param.put("upt_id",super.sessionBean.getUsn());
		param.put("req_stat", _req.getP("req_stat"));
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
            if(null != vusn) {
            	for(int i =0; i < vusn.length; i++) {
                    String[] tmpData = vusn[i].split("##");
            		param.put("usn", tmpData[0]);
                    param.put("lec_cd", tmpData[1]);
        			rcnt += LecReqDB.completeOne(super.oDb, param);
           		}
           	}
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}

	public void cmdExcel(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		
        final String[] searchFields = {"s_reqstat", "s_sch_sdt", "s_sch_edt", "os_crs", "os_sjt", "os_lec"};
		
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		param.put("usn", super.sessionBean.getUsn());

		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("x.req_stat", ":s_reqstat"));
		wb.addCond(Where.between("x.req_dt", ":s_sch_sdt", ":s_sch_edt"));
        wb.addCond(Where.eq("x.crs_cd", ":os_crs"));
        wb.addCond(Where.eq("x.sjt_cd", ":os_sjt"));
        wb.addCond(Where.eq("x.lec_cd", ":os_lec"));
        
		ViewerSupport.setDataSourceList(_req, LecReqDB.getList(super.oDb, param, wb.getWhereString(param)));
		
		result.setForward("excel");
	}	

	/**
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpload(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("upload");
	}
	
	public void cmdSample(MyHttpServletRequest _req, HttpServletResponse _res)	throws MafException {
		
        Map param = new HashMap();
        ViewerSupport.setDataSourceList(_req, LecReqDB.getSample(super.oDb, param));
		result.setForward("sample");
	}	

	/**
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUploadOk(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        UploadedFile excel = _req.getFileParameter("lecfile");
        Map param = new HashMap();
		boolean chk = false;
		StringBuffer ErrorMsg = new StringBuffer(200);
        param.put("upt_id", super.sessionBean.getUsn());
        
        if (excel != null) {
            ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/upload_lecreq.xml", param);
            ExcelErrorInfo errorInfo = up.process(super.oDb, excel);
            if(errorInfo.isError()) {
                ExcelErrorInfoBean[] errList = errorInfo.getErrorList();
                if (errList != null) {
	                for(int i = 0; i < errList.length; i++) {
	                	ExcelErrorInfoBean eb = errList[i];
	                	ErrorMsg.append(eb.getRow()+",");
	                }
                	ErrorMsg.append(" 열에서  Error가 발생했습니다.");
                }
                
                chk = false;
            }else {
                chk = true;
            }
        }else{
        	chk = false;	
            result.setForward("list");       
        }

        if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.upload.ok"));
		} else {
			result.setSuccess(false,new ResultMessage(ErrorMsg.toString()));
		}
	}

} 