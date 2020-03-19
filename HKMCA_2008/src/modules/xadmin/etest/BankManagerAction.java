package modules.xadmin.etest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.context.support.LocaleUtil;
import maf.exception.MafException;
import maf.lib.excel.importer.ExcelErrorInfo;
import maf.lib.excel.importer.ExcelImportManager;
import maf.logger.Logging;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.mvc.view.ViewerSupport;
import maf.web.util.SerializeHashtable;
import miraenet.AppConfig;
import miraenet.app.resource.ResourceManagerDB;
import modules.etest.support.Etest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ���� ���� ����
 * 
 * @author bluevlad
 *
 */
public class BankManagerAction extends MafAction {
    private Log logger = LogFactory.getLog(this.getClass());
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
     * ��� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        final String[] searchFields = {"s_quetitle", "os_crs", "os_sjt"};
        
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
		String destination = null;
        
        SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("x.crs_cd", ":os_crs"));
		wb.addCond(Where.eq("x.queowner", ":os_sjt"));
        wb.addCond(Where.like("quetitle",":s_quetitle"));
        
        if (!"".equals(param.get("os_sjt"))) {
        	destination = "os_sjt";
        } else if  (!"".equals(param.get("os_crs"))) {
        	destination = "os_crs";
        }

        BankManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
        result.setAttribute("navigator", navigator);
		result.setAttribute("destination", destination);
        result.setForward("list");
    }

    /**
     * ��� ȭ��
     *
     * @param _req
     * @param _res
     */
    public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
    	 Map param = new HashMap();
    	 param.put("active_yn", "N");
    	 param.put("quescore", "1");
    	 param.put("quelevel", "3");
    	 
    	 _req.setAttribute("item", param);
    	 
        result.setForward("add");
    }

    /**
     * ����
     *
     * @param _req
     * @param _res
     */
    public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();

        //Ư�� ���� ��������
        result.setAttribute("item", BankManagerDB.getOne(super.oDb, param));
        result.setForward("edit");
    }

    /**
     * ���
     *
     * @param _req
     * @param _res
     */
    public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		
        param.put("usn",super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());

        UploadedFile uFile = _req.getFileParameter("queimag");
        String pPath = _req.getRealPath(AppConfig.DEFAULT_EXAM_FILE_PATH);

        if(Etest.QUE_TYPE_TEXT.equals(param.get("quetype")) || Etest.QUE_TYPE_DESCRIPTION.equals(param.get("quetype")) ){   //�ܴ��� ����
            String queansw_word = _req.getP("queansw_word");
            if(queansw_word!=null && queansw_word.length()>0){
                queansw_word = queansw_word.replaceAll(Etest.ANS_SEPERATOR+" ", Etest.ANS_SEPERATOR);  // #������ �յ� ������ ���ش�
                queansw_word = queansw_word.replaceAll(" "+Etest.ANS_SEPERATOR, Etest.ANS_SEPERATOR);
            }
            param.put("quecount", "0");
            param.put("queansw",queansw_word);

        }else if(Etest.QUE_TYPE_MULTI.equals(param.get("quetype"))){   //multi������ ����
            String[] queansw_check = _req.getParameterValues("queansw_Mcheck");
            String queansw = null;
            if(queansw_check!=null && queansw_check.length>0){
                queansw = queansw_check[0];
                for(int i=1; i<queansw_check.length; i++){
                    queansw += Etest.ANS_SEPERATOR + queansw_check[i];
                }
            }
            param.put("queansw", queansw);

        }else if(Etest.QUE_TYPE_SINGLE.equals(param.get("quetype"))){   //single������ ����
            String queansw_check = _req.getP("queansw_Scheck");
            param.put("queansw",queansw_check);
        }

        int rcnt = 0;
        boolean chk = false;

        try {
        	oDb.setAutoCommit(false);
			//���� ���ε�
			if(uFile != null && uFile.getFileSize() > 0) {
	        	uFile.SaveAs(pPath, MafUtil.getDBGuid(oDb));
	        	param.put("queimag", uFile.getNewfilename());
	        }

            //insert to DB
            rcnt = BankManagerDB.mergeOne(super.oDb, param);
            rcnt = BankManagerDB.mergeLangOne(super.oDb, param);
        	oDb.commit();
        	chk = true;
        } catch (Exception e) {
        	logger.debug(e);
        	oDb.rollback();
        }

        if(chk) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
        }
    }

    /**
     * ���� ���
     *
     * @param _req
     * @param _res
     */
    public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
        UploadedFile uFile = _req.getFileParameter("queimag");
        String pPath = _req.getRealPath(AppConfig.DEFAULT_EXAM_FILE_PATH);

        param.put("usn",super.sessionBean.getUsn());
        
        if(Etest.QUE_TYPE_TEXT.equals(param.get("quetype")) || Etest.QUE_TYPE_DESCRIPTION.equals(param.get("quetype"))){   //�ܴ��� ����
            String queansw_word = _req.getP("queansw_word");
            if(queansw_word!=null && queansw_word.length()>0){
                queansw_word = queansw_word.replaceAll(Etest.ANS_SEPERATOR +" ", Etest.ANS_SEPERATOR);  // #������ �յ� ������ ���ش�
                queansw_word = queansw_word.replaceAll(" " + Etest.ANS_SEPERATOR, Etest.ANS_SEPERATOR);
            }
            param.put("quecount", "0");
            param.put("queansw", queansw_word);

        }else if(Etest.QUE_TYPE_MULTI.equals(param.get("quetype"))){   //multi������ ����
            String[] queansw_check = _req.getParameterValues("queansw_Mcheck");
            String queansw = null;
            if(queansw_check!=null && queansw_check.length>0){
                queansw = queansw_check[0];
                for(int i=1; i<queansw_check.length; i++){
                    queansw += Etest.ANS_SEPERATOR + queansw_check[i];
                }
            }
            param.put("queansw", queansw);
            
        }else if(Etest.QUE_TYPE_SINGLE.equals(param.get("quetype"))){   //single������ ����
            String queansw_check = _req.getP("queansw_Scheck");
            param.put("queansw", queansw_check);
        }

        int rcnt = 0;

        try {
			//���� ���ε�
			if(uFile != null && uFile.getFileSize() > 0) {
	        	uFile.SaveAs(pPath, MafUtil.getDBGuid(oDb));
	            param.put("queimag", uFile.getNewfilename());
	        } else{
	            param.put("queimag", _req.getP("orgimg"));
	        }
        	
            //update to DB
            rcnt = BankManagerDB.mergeOne(super.oDb, param);
        } catch (Throwable e) {
            Logging.trace(e);
        }

        if(rcnt>0) {
            UrlAddress next = null;
            if("set".equals(_req.getP("from"))) {
            	next = new UrlAddress ("setmanager.do");
                next.addParam("cmd", "view");
                next.addParam("setid", _req.getP("setid"));
            } else {
            	next = new UrlAddress (super.controlAction);
                next.addParam("cmd", "edit");
                next.addParam("queid", param.get("queid"));
            }
            
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
        }
    }
    /**
     * ���� ���
     *
     * @param _req
     * @param _res
     */
    public void cmdUpdateLang(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();

        param.put("usn",super.sessionBean.getUsn());
        boolean chk = false;

        int rcnt = 0;

        try {
            UploadedFile qImagFile =  _req.getFileParameter("queimag");
            String pPath = _req.getRealPath(AppConfig.DEFAULT_EXAM_FILE_PATH);
            //���� ���ε�
            if(qImagFile != null && qImagFile.getFileSize() > 0) {
                qImagFile.SaveTo(pPath, false);
                param.put("queimag", qImagFile.getNewfilename());
            }

            //update to DB
            rcnt = BankManagerDB.mergeLangOne(super.oDb, param);
            chk = true;
        } catch (Throwable e) {
            Logging.trace(e);
        }

        if( chk) {
            UrlAddress next = null;
            if("set".equals(_req.getP("from"))) {
            	next = new UrlAddress ("setmanager.do");
                next.addParam("cmd", "view");
                next.addParam("setid", _req.getP("setid"));
            } else {
            	next = new UrlAddress (super.controlAction);
                next.addParam("cmd", "editLang");
                next.addParam("queid", param.get("queid"));
            }
            
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
        }
    }
    /**
     * ����
     *
     * @param _req
     * @param _res
     */
    public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String queid = _req.getP("queid");
        
        Map param = new HashMap();
        param.put("queid", queid);
        boolean chk = true;
        int rcnt = 0;
        try {
        	oDb.setAutoCommit(false);
        	rcnt = BankManagerDB.deleteOne(super.oDb, param);
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
    
    /**
     * Excel UPload Sample �ٿ�ε�
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdSample(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
		ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/etest_bank.xml", param);
		up.sampleDownLoader(_req, _res);
		
		result.setForward("dummy");
	}
    
    /**
	 * ���� Upload
	 * 
	 * @param _req
	 * @param _res
	 */	
	public void cmdUploadOk(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        UploadedFile excel = _req.getFileParameter("certifile");
        Map param = new HashMap();
        param.put("reg_usn", super.sessionBean.getUsn());
		boolean chk = false;
		String errorMsg = null;
		
        if (excel != null) {
            ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/etest_bank.xml", param);
            ExcelErrorInfo errorInfo = up.process(super.oDb, excel);
            if(errorInfo.isError()) {
            	errorMsg = errorInfo.getMessage();
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
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "upload.ok"));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "upload.fail"));
		}
	}		

	
	public void cmdUploadExcel(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/etest_bank.xml",null);
		result.setAttribute("fileinfo", up);
		result.setForward("uploadExcel");
		
	}
	
	/**
	 * ������ Excel UPload ������ 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdTransupload(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/etest_trans.xml",null);
		result.setAttribute("fileinfo", up);
		result.setForward("transupload");
		
	}


    /**
     * ����
     *
     * @param _req
     * @param _res
     */
    public void cmdEditLang(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = _req.getKeyValueMap();

    	String curLang = _req.getP("lang", LocaleUtil.getLocaleString(_req));
		result.setAttribute("curLang", curLang);
		param.put("lang", curLang);
       
        result.setAttribute("item", BankManagerDB.getOne(super.oDb, param));
        result.setAttribute("langItem", BankManagerDB.getOneLang(super.oDb, param));
        result.setAttribute("from", _req.getP("from"));
        result.setAttribute("setid", _req.getP("setid"));
        result.setAttribute("langList", ResourceManagerDB.getLangList(super.oDb));
        result.setForward("editLang");
    }
    
    /**
     * Excel UPload Sample �ٿ�ε�
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void getTransSource(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		String[] chks = {"cat_id", "lang"};
		super.chkEmpty(_req, chks);
		Map param = new HashMap();
		param.put("cat_id", _req.getP("cat_id"));
		param.put("lang", _req.getP("lang"));
		
        List list = BankManagerDB.getTrasnSource(super.oDb, param);
        ViewerSupport.setDataSourceList(_req, list);
		
		result.setForward("transexcel");
	}
    
    
    /**
	 * ���� Upload
	 * 
	 * @param _req
	 * @param _res
	 */	
	public void uploadTransSource(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        UploadedFile excel = _req.getFileParameter("certifile");
        Map param = new HashMap();
        param.put("update_usn", super.sessionBean.getUsn());
		boolean chk = false;
		String errorMsg = null;
		
        if (excel != null) {
            ExcelImportManager up = new ExcelImportManager(
                    "/WEB-INF/reports/excelUpload/etest_trans.xml", param);
            ExcelErrorInfo errorInfo = up.process(super.oDb, excel);
            if(errorInfo.isError()) {
            	errorMsg = errorInfo.getMessage();
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
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "upload.ok"));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "upload.fail"));
		}
	}	
}
