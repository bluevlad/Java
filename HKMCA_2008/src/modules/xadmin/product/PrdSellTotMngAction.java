package modules.xadmin.product;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import modules.mall.order.OrdDetDB;

public class PrdSellTotMngAction extends MafAction {
    SerializeHashtable listOp = null;

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
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
		
		final String[] searchFields = {"s_prd_nm", "s_sell_nm"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("D:ord_dt"));
	
		Map param = this.listOp.getMergedParam(_req, searchFields);

		param.put("usn", super.sessionBean.getUsn());
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("x.prd_nm", ":s_prd_nm"));
		wb.addCond(Where.like("x.sell_nm", ":s_sell_nm"));

		PrdStatDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
    }

    /**
     * 목록 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
        Map param = _req.getKeyValueMap();

        result.setAttribute("item", PrdStatDB.getOne(super.oDb, param));
        result.setAttribute("list", OrdDetDB.getList(super.oDb, param));
        result.setForward("view");
    }
}