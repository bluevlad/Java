package modules.wlc.classroom.learner;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.wlc.classroom.BaseClassAction;

public class BasClassInfoAction extends BaseClassAction {
	
	/**
     * ����
     *
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();
        param.put("lec_cd", super.lectureInfo.getLec_cd());
        param.put("sjt_cd", super.lectureInfo.getSjt_cd());
        //���� ���� ��������
        result.setAttribute("item", BasClassInfoDB.getOne(super.oDb, param));
        result.setForward("view");
    }
}
