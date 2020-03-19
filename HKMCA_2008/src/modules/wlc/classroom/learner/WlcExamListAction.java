package modules.wlc.classroom.learner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * ����ڰ� ������ ���� ��� ��ȸ
 * @author bluevlad
 *
 */
public class WlcExamListAction extends BaseLearnerClassAction {
	final private Log logger = LogFactory.getLog(this.getClass());
	private final String MESSAGE_BUNDLENAME = "common.message";

	/**
	 * ��� ��������
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());

		result.setAttribute("item", WlcExamListDB.getList(super.oDb, param));
		result.setForward("list");
	}
	
	/**
	 * �ϳ��� record�� �о�ͼ� �����ش�.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] reqfields = {"exmid"};
		super.chkEmpty(_req, reqfields);
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("usn", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());
		
		// ���� ���� �������� 
		Map item = WlcExamListDB.getOne(super.oDb, param);
		if(item == null) {
			//[ :exmid, :usn, :insert_id ]
			WlcExamListDB.insertRstOne(oDb, param);
			item = WlcExamListDB.getOne(super.oDb, param);
		}
		
		result.setAttribute("item", item);
		result.setAttribute("setlangs", WlcExamListDB.getLangList(super.oDb, param));
		result.setForward("view");
	}
	
	/**
	 * �ϳ��� ������ ���� �ֱ� 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdResult(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		param.put("usn", super.sessionBean.getUsn());
		result.setAttribute("item", WlcExamListDB.getTestResultbyUser(super.oDb, param));
		
		result.setForward("result");
	}
	
}
