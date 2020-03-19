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
import modules.xadmin.etest.ExmManagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChaptersAction extends BaseLearnerClassAction {
    private Log logger = LogFactory.getLog(this.getClass());
    private final String MESSAGE_BUNDLENAME = "common.message";

    /**
     * 목록 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        final String[] searchFields = {"lec_cd", "userid"};
        String sjt_cd = super.lecReqInfo.getSjt_cd();
        String lec_cd = super.lecReqInfo.getLec_cd();
        String usn = super.sessionBean.getUsn();

        
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", lec_cd);
        param.put("usn", usn);

		/* 출석 체크 */
		//AttendDB.doAttend(super.oDb, param);
        
        SqlWhereBuilder wb = oDb.getWhereBuilder();
        //wb.addCond(Where.like(_req.getP("leccode"),":leccode"));

        String cnt_type = ChaptersDB.getContent_type(oDb, lec_cd);
        //result.setAttribute("ritem", dao.getWlaratmst(oDb, param));
        // 강의안정보
        ChaptersDB.getList(super.oDb, navigator, param, wb.getWhereString(param), true);

        result.setAttribute("navigator", navigator);
        result.setAttribute("cnt_type", cnt_type);
        result.setAttribute("limit_study_time", ChaptersDB.getLimit_study_time(oDb, param));
        result.setAttribute("totaltime", ChaptersDB.getLearningTotalTime(oDb, param));
        result.setAttribute("prgtype", ChaptersDB.getLecType(oDb, param));
        result.setAttribute("daycount", ChaptersDB.getDayCount(oDb, param));
        result.setAttribute("studingtime", ChaptersDB.getDayTime(oDb, param));
        result.setAttribute("prginfo", ChaptersDB.getProgInfo(oDb, param));
        result.setAttribute("sjt_cd", sjt_cd);
        result.setAttribute("lec_cd", lec_cd);
        result.setAttribute("usn", usn);
        
        if(MafUtil.parseInt(super.lectureInfo.getExm_condition()) > 0  ) {
        	param.put("exmid", super.lectureInfo.getExmid());
        	result.setAttribute("exmset", ExmManagerDB.getOne(super.oDb, param));
        }
        result.setForward("list");
    }

}