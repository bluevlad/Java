package modules.xadmin.survey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.context.support.LocaleUtil;
import maf.exception.MafException;
import maf.logger.Logging;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import miraenet.app.resource.ResourceManagerDB;
import modules.etest.support.Etest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BankManagerAction extends MafAction {
    final private Log logger = LogFactory.getLog(this.getClass());
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
     * 목록 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        final String[] searchFields = {"s_quetitle"};
        
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lang", super.locale.getLanguage());
        
        SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.like("x.quetitle",":s_quetitle"));

        BankManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param));

        result.setAttribute("navigator", navigator);
        result.setForward("list");
    }

    /**
     * 등록 화면
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
     * 수정
     *
     * @param _req
     * @param _res
     */
    public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();
        param.put("lang", super.locale.getLanguage());

        result.setAttribute("queid", _req.getP("queid"));
        result.setAttribute("from", _req.getP("from"));
        result.setAttribute("setid", _req.getP("setid"));
        result.setAttribute("item", BankManagerDB.getOne(super.oDb, param));
        result.setForward("edit");
    }

    /**
     * 등록
     *
     * @param _req
     * @param _res
     */
    public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();

        param.put("upt_id", super.sessionBean.getUsn());
        param.put("queowner", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());

        if(Etest.QUE_TYPE_TEXT.equals(param.get("quetype")) || Etest.QUE_TYPE_DESCRIPTION.equals(param.get("quetype")) ){   //단답형 정답
            String queansw_word = _req.getP("queansw_word");
            if(queansw_word!=null && queansw_word.length()>0){
                queansw_word = queansw_word.replaceAll(Etest.ANS_SEPERATOR+" ", Etest.ANS_SEPERATOR);  // #사이의 앞뒤 공백을 없앤다
                queansw_word = queansw_word.replaceAll(" "+Etest.ANS_SEPERATOR, Etest.ANS_SEPERATOR);
            }
            param.put("queansw", queansw_word);

        }else if(Etest.QUE_TYPE_MULTI.equals(param.get("quetype"))){   //multi선택형 정답
            String[] queansw_check = _req.getParameterValues("queansw_Mcheck");
            String queansw = null;
            if(queansw_check!=null && queansw_check.length>0){
                queansw = queansw_check[0];
                for(int i=1; i<queansw_check.length; i++){
                    queansw += Etest.ANS_SEPERATOR+queansw_check[i];
                }
            }
            param.put("queansw", queansw);

        }else if(Etest.QUE_TYPE_SINGLE.equals(param.get("quetype"))){   //single선택형 정답
            String queansw_check = _req.getP("queansw_Scheck");
            param.put("queansw", queansw_check);
        }

        int rcnt = 0;

        try {
           
            //insert to DB
            rcnt = BankManagerDB.mergeOne(super.oDb, param);
            rcnt = BankManagerDB.mergeLangOne(super.oDb, param);
        } catch (Throwable e) {
            Logging.trace(e);
        }

        if( rcnt > 0 ) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
        }
    }

    /**
     * 수정 등록
     *
     * @param _req
     * @param _res
     */
    public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();

        param.put("upt_id", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());

        int rcnt = 0;

        if(Etest.QUE_TYPE_TEXT.equals(param.get("quetype")) || Etest.QUE_TYPE_DESCRIPTION.equals(param.get("quetype"))){   //단답형 정답
            String queansw_word = _req.getP("queansw_word");
            if(queansw_word!=null && queansw_word.length()>0){
                queansw_word = queansw_word.replaceAll(Etest.ANS_SEPERATOR +" ", Etest.ANS_SEPERATOR);  // #사이의 앞뒤 공백을 없앤다
                queansw_word = queansw_word.replaceAll(" " + Etest.ANS_SEPERATOR, Etest.ANS_SEPERATOR);
            }
            param.put("queansw", queansw_word);

        }else if(Etest.QUE_TYPE_MULTI.equals(param.get("quetype"))){   //multi선택형 정답
            String[] queansw_check = _req.getParameterValues("queansw_Mcheck");
            String queansw = null;
            if(queansw_check!=null && queansw_check.length>0){
                queansw = queansw_check[0];
                for(int i=1; i<queansw_check.length; i++){
                    queansw += Etest.ANS_SEPERATOR + queansw_check[i];
                }
            }
            param.put("queansw", queansw);
            
        }else if(Etest.QUE_TYPE_SINGLE.equals(param.get("quetype"))){   //single선택형 정답
            String queansw_check = _req.getP("queansw_Scheck");
            param.put("queansw", queansw_check);
        }


        try {
            //update to DB
            rcnt= BankManagerDB.mergeOne(super.oDb, param);
            if (!"".equals(_req.getP("lang")) && _req.getP("lang") != null) {
                rcnt = BankManagerDB.mergeLangOne(super.oDb, param);
            }else {
                rcnt = BankManagerDB.mergeLangOne(super.oDb, param);
            }
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
     * 삭제
     *
     * @param _req
     * @param _res
     */
    public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String queid = _req.getP("queid");
        int rcnt = 0;
        boolean chk = false;
        Map param = new HashMap();
        param.put("queid", queid);
        
        try {
            //update to DB
            oDb.setAutoCommit(false);
            rcnt= BankManagerDB.deleteOne(super.oDb, param);
            oDb.commit();
            chk = true;
        } catch (Throwable e) {
            oDb.rollback();
            logger.error(e.getMessage());
            rcnt = 0;
        }
        
        if(chk) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail", new Integer(rcnt)));
        }
    }
    
    /**
     * 수정
     *
     * @param _req
     * @param _res
     */
    public void cmdEditLang(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = _req.getKeyValueMap();

        String curLang = _req.getP("lang", LocaleUtil.getLocaleString(_req));
        result.setAttribute("curLang", curLang);
        List langList = ResourceManagerDB.getLangList(super.oDb);
        //특정 정보 가져오기
        result.setAttribute("item", BankManagerDB.getOne(super.oDb, param));
        result.setAttribute("from", _req.getP("from"));
        result.setAttribute("setid", _req.getP("setid"));
        result.setAttribute("langList", langList);
        result.setForward("editLang");
    }
}
