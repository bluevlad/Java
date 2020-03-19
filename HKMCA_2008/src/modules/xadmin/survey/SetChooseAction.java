package modules.xadmin.survey;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;

public class SetChooseAction extends MafAction {
    SerializeHashtable listOp = null;

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
        
		result.setAttribute("frm_id", _req.getP("frm_id"));
		result.setAttribute("elm_id", _req.getP("elm_id"));
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

        // Order default 값 설정 시
        //NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:###"));
        
        final String[] searchFields = {"s_setowner", "s_settitle", "s_active_yn"};

        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
    
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("usn", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());

        SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.like("settitle", ":s_settitle"));
        wb.addCond(Where.eq("active_yn", "'Y'"));

        SetManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
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
        Map param = new HashMap();
        param.put("setid", _req.getP("setid"));
        param.put("lang", super.locale.getLanguage());

        result.setAttribute("item", SetManagerDB.getOne(super.oDb, param));
        result.setAttribute("setitems", SetManagerDB.getSetItems(super.oDb, param));
        result.setForward("view");
    }

}
