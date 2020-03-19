package modules.etest.learner;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import modules.etest.stat.EtestStatDB;
import modules.wlc.classroom.tutor.TestManagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 사용자가 봐야할 시험 목록 조회
 * @author bluevlad
 *
 */
public class ExamListAction extends MafAction {
	final private Log logger = LogFactory.getLog(this.getClass());
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
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {  };
		// Order default 값 설정 시
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("usn", super.sessionBean.getUsn());
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		ExamListDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * 하나의 record를 읽어와서 보여준다.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] reqfields = {"exmid"};
		super.chkEmpty(_req, reqfields);
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		param.put("usn", super.sessionBean.getUsn());
		
		// 시험 정보 가져오기 
		Map item = ExamListDB.getOne(super.oDb, param);
		if(item == null) {
			//[ :exmid, :usn, :lecnumb, :insert_id ]
			ExamListDB.insertRstOne(oDb, param);
			item = ExamListDB.getOne(super.oDb, param);
		}
		
		result.setAttribute("item", item);
		result.setAttribute("setlangs", ExamListDB.getLangList(super.oDb, param));
		result.setForward("view");
	}
	
	/**
	 * 하나의 시험결과 보여 주기 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdResult(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		param.put("usn", super.sessionBean.getUsn());
		result.setAttribute("item", EtestStatDB.getTestResultbyUser(super.oDb, param));
		
		result.setForward("result");
	}
	
//	/**
//	 * 하나의 시험결과 보여 주기 
//	 * 
//	 * @param _req
//	 * @param _res
//	 */
//	public void cmdUserHistgram(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
//		Map param = new HashMap();
//		param.put("exmid", _req.getP("exmid"));
//		param.put("usn", super.sessionBean.getUsn());
//		
//		
////		FC_Chart root = new FC_Chart("Histogram");
////		root.setAttribute("xAxisName", "score");
////		root.setAttribute("yAxisName", "people");
////		//root.setAttribute("showValues", "1");
////
////		  
////		List list =  StatDB.getTestHistogram( param);
////		for(int i =0; i< list.size(); i++) {
////			Map rec = (Map) list.get(i);
////			root.addValue(MafUtil.getString(rec.get("cnt")), getRange100(MafUtil.getString(rec.get("sc_range"))), null );
////			
////		}
////		
////		Document oDoc = new Document(root.getElement());
////		result.setXmlDoc(oDoc);
////		_res.setContentType(MimeType.getMimeType("xml"));
////		_res.setHeader("Cache-Control", "no-cache");
////		try {
////			XmlWriter writer = new XmlWriter(_res.getWriter());
////			
////		} catch (Exception e) {
////			// TODO: handle exception
////		}
//		result.setForward("resultXml");
//	}
	
	
	
}
