package modules.xadmin.product;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;


public class PrdSellStatMngAction extends MafAction {
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
    public void cmdDate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
        Map param = _req.getKeyValueMap();

		result.setAttribute("list", PrdStatDB.getStatMoney(super.oDb, param));

		result.setForward("date");
    }

}