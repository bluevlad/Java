/*
 * Created on 2006. 9. 22.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;


public class ProgressAction extends BaseTutorClassAction {

    /**
     * 목록 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        final String[] searchFields = {"sch_usernm", "sch_userid"};

        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:userid"));
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", super.lectureInfo.getLec_cd());

        SqlWhereBuilder wb = oDb.getWhereBuilder();
//        wb.addCond(Where.eq("lec_cd",":lec_cd"));
        wb.addCond(Where.eq("nm",":sch_usernm"));
        wb.addCond(Where.eq("userid",":sch_userid"));

        ProgressDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
        result.setAttribute("navigator", navigator);
        result.setAttribute("lec_cd", super.lectureInfo.getLec_cd());
        result.setForward("list");
    }

    /**
     * 상세 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();

        result.setAttribute("item", ProgressDB.getJindo(super.oDb, param));

        //수료기준 최소학습시간
        result.setAttribute("limit_study_time", ProgressDB.getOneLimit_study_time(super.oDb, param) );
        //총학습시간 가지고 오기
        result.setAttribute("totaltime", ProgressDB.getOneLearningTotalTime(super.oDb, param));

        result.setForward("view");
    }
}