/*
 * Created on 2006. 6. 22.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.learner;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.web.http.MyHttpServletRequest;
import maf.web.util.CookieUtil;
import miraenet.AppConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �н�������ȸ
 */
public class ProgressAction extends BaseLearnerClassAction {
	private Log logger = LogFactory.getLog(this.getClass());
	private final String MESSAGE_BUNDLENAME = "common.message";

    /**
     * ��� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();
        param.put("lec_cd", super.lecReqInfo.getLec_cd());
        param.put("usn", super.sessionBean.getUsn());

        ProgressDB.getList(super.oDb, param);

        result.setForward("list");
    }

    /**
     * ���ú� ������ ���� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdPageProgressView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String lec_cd = super.lecReqInfo.getLec_cd();
        String usn = super.sessionBean.getUsn();
        
        Map param = _req.getKeyValueMap();
        param.put("lec_cd", lec_cd);
        param.put("usn", usn);

        //Ư�� ������ Ư�� ������ ���� ��������
        result.setAttribute("citem", ChaptersDB.getLearningInfo(super.oDb, param));
        result.setAttribute("pitem", ChaptersDB.getLbPagePrgGraph(super.oDb, param));
        result.setForward("view_page");
    }

    /**
     * �� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        
        Map param = _req.getKeyValueMap();
        //param.put("htmcode", _req.getP("htmcode"));

        //Ư�� ������ Ư�� ������ ���� ��������
        result.setAttribute("item", ChaptersDB.getOne(super.oDb, param));
        result.setForward("view");
    }

    /**
     * �������� ���� ��û ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdOffView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        //Map param = this.listOp.getMergedParam(_req, searchFields);
        Map param = _req.getKeyValueMap();
        String usn = super.sessionBean.getUsn();
        param.put("usn", usn);

        SqlWhereBuilder wb = oDb.getWhereBuilder();
        
        ChaptersDB.getWlboffreqList(super.oDb, navigator, param, wb.getWhereString(param));

        result.setAttribute("navigator", navigator);
        result.setAttribute("item", ChaptersDB.getOfflineOne(super.oDb, param));
        result.setAttribute("uitem", ChaptersDB.getOfflineUserOne(super.oDb, param));
        result.setForward("view_off");
    }

    /**
	 * �н����� (�˾�â)
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdStudy(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

		String lec_cd = super.lecReqInfo.getLec_cd();
		String usn = super.sessionBean.getUsn();
		String htmcode = _req.getP("htmcode");
		String total_time = _req.getP("total_time");
		String act_move = _req.getP("act_move");
		if (null == total_time || "".equals(total_time))
			total_time = Integer.toString(AppConfig.LECTURE_SAVE_INTERVAL_TIME);

		param.put("lec_cd", lec_cd);
		param.put("usn", usn);
		param.put("htmcode", htmcode);

		// ������������ �ܿ��̵� ��û�� ������ ó��
		if (null != act_move && !"".equals(act_move)) {
			htmcode = ProgressDB.getMovingLearningInfo(super.oDb, param);
			result.setAttribute("act_move", act_move);
		}
		Map citem = ProgressDB.getLearningInfo(super.oDb, param);
		result.setAttribute("citem", citem );
        result.setAttribute("v_width",  _req.getP("v_width", citem.get("cnt_width")+""));
        result.setAttribute("v_height",  _req.getP("v_height", citem.get("cnt_height")+""));

		// �ش� ������ ����������
		String cnt_type = ProgressDB.getContent_type(oDb, param);
		//result.setAttribute("cnt_type", cnt_type);

		String realserver = AppConfig.WEB_DOMAIN; // ������ �����θ�
		String lmsservlet = super.controlAction;

		CookieUtil.setCookieValue(_res, "lmsserver", realserver, null, realserver);
		CookieUtil.setCookieValue(_res, "lmsservlet", lmsservlet, null, realserver);
		CookieUtil.setCookieValue(_res, "htmcode", htmcode, null, realserver);
		CookieUtil.setCookieValue(_res, "lec_cd", lec_cd, null, realserver);
		CookieUtil.setCookieValue(_res, "usn", usn, null, realserver);

		// ����͸�
		//userroles.getRoleMap("")
		//if("MR".equals(user_role) || "MW".equals(user_role)) {
		if (false) {
			;
		} else {
			param.put("lesson_location", "");
			param.put("lesson_status", "");
			param.put("core_entry", "");
			param.put("score_raw", "");
			param.put("total_time", "0");
			param.put("core_exit", "");
			param.put("flag", "time");
			param.put("save_time_flag", "");

			ProgressDB.saveTimeProgress(oDb, param);
		}
		result.setAttribute("usn", usn);
		result.setAttribute("lec_cd", lec_cd);
		result.setAttribute("htmcode", htmcode);

		{
			result.setAttribute("lmsserver", realserver);
			result.setAttribute("lmsservlet", lmsservlet);
			result.setAttribute("htmcode", htmcode);
			result.setAttribute("lec_cd", lec_cd);
			result.setAttribute("usn", usn);
	
		}
		{
			result.setAttribute("cntserver", AppConfig.CONTENTS_SERVER);
			result.setAttribute("save_interval", AppConfig.LECTURE_SAVE_INTERVAL_TIME+"");			
		}
		if (cnt_type.equals("MOV")) {
			result.setForward("view_mov");
		}else if(cnt_type.equals("URL")){
			result.setForward("url");
		}else if(cnt_type.equals("FILE")){
			result.setForward("file_down");
		}else {
			result.setForward("classroom_start");
		}
	}

	/**
	 * ���� ����
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdSaveScore(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String lec_cd = _req.getP("lec_cd");
		String usn = _req.getP("usn");
		String htmcode = _req.getP("htmcode");

		String insflag = _req.getP("insflag");
		String total_time = _req.getP("total_time");
		String test_score = _req.getP("test_score");
		if (MafUtil.parseInt(test_score) >= 60) {
			//if("MR".equals(user_role) || "MW".equals(user_role)) {
			if (false) {
				;
			} else {
				Map param = _req.getKeyValueMap();
				param.put("lec_cd", lec_cd);
				param.put("usn", usn);
				param.put("htmcode", htmcode);
				param.put("lesson_location", "");
				param.put("lesson_status", "");
				param.put("core_entry", "");
				param.put("score_raw", test_score);
				param.put("total_time", total_time);
				param.put("core_exit", "");
				param.put("flag", "time");
				param.put("save_time_flag", insflag);

				ProgressDB.saveTimeProgress(oDb, param);
			}
		}
		result.setForward("saved");
	}

	/**
	 * �ð� ���� ��� ����
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdSaveTime(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String lec_cd = _req.getP("lec_cd");
		String usn = _req.getP("usn");
		String htmcode = _req.getP("htmcode");

		String insflag = _req.getP("insflag");
		String total_time = _req.getP("total_time");

		if (!"".equals(lec_cd) && !"".equals(usn) && !"".equals(htmcode)) {
			//if("MR".equals(user_role) || "MW".equals(user_role)) {
			if (false) {
				;
			} else {
				Map param = _req.getKeyValueMap();
				param.put("lec_cd", lec_cd);
				param.put("usn", usn);
				param.put("htmcode", htmcode);

				param.put("lesson_location", "");
				param.put("lesson_status", "");
				param.put("core_entry", "");
				param.put("score_raw", "");
				param.put("total_time", total_time);
				param.put("core_exit", "");
				param.put("flag", "time");
				param.put("save_time_flag", insflag);

				ProgressDB.saveTimeProgress(oDb, param);
			}
			result.setForward("saved");
		} else {
			result.setForward("not_saved");
		}
	}

	/**
	 * ������ ���� ��� ����
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdSavePage(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		String lec_cd = _req.getP("lec_cd");
		String usn = _req.getP("usn");
		String htmcode = _req.getP("htmcode");

		String page_nm = _req.getP("page_nm");
		String page_no = _req.getP("page_no");
        if("undefined".equals(page_no)) {
            page_no = "0";
        }
		String page_flag = _req.getP("page_flag");

		if (!"".equals(lec_cd) && !"".equals(usn) && !"".equals(htmcode) && !"".equals(page_nm)) {
			//if("MR".equals(user_role) || "MW".equals(user_role)) {
			if (false) {
				;
			} else {
				Map param = _req.getKeyValueMap();
				param.put("lec_cd", lec_cd);
				param.put("usn", usn);
				param.put("htmcode", htmcode);

				param.put("page_nm", page_nm);
				param.put("page_no", page_no);
				param.put("page_flag", page_flag);

				ProgressDB.savePageProgress(oDb, param);
			}
			result.setForward("saved");
		} else {
			result.setForward("not_saved");
		}
	}

}