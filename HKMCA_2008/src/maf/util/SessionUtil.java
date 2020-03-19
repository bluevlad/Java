package maf.util;

import maf.base.BaseHttpSession;
import maf.base.BaseSessionUtil;
import modules.common.beans.UsrMstBean;
/**
 * 각 프로젝트에 맞게 Session을 확장 수정해서 사용하기 위한 Util
 * @author bluevlad
 *
 */
public class SessionUtil extends BaseSessionUtil {
    // 속성 확장
    private final static String SESSION_KEY_EMAIL = "SESSION_KEY_EMAIL";
    
    // WLC관련
    private final static String SESSION_KEY_SJT_CD = "SESSION_KEY_JST_CD"; //과목코드(과목 테이블의 )
    private final static String SESSION_KEY_LEC_CD = "SESSION_KEY_LEC_CD"; //강의코드
    private final static String SESSION_KEY_LEC_NUM = "SESSION_KEY_LEC_NUM"; //강의 차수(수강 신청에 따라 수강신청 횟수가 됨)

    public static BaseHttpSession makeSessionBean(UsrMstBean ob, String loginIP) {
        BaseHttpSession sessionBean = new BaseHttpSession();

        sessionBean.setUsn(ob.getUsn());
        sessionBean.setUserid( ob.getUserid());
        sessionBean.setNm( ob.getNm());
        sessionBean.setLoginIP(loginIP);
        sessionBean.addAttribute(SESSION_KEY_EMAIL,ob.getEmail());
        return sessionBean;
    }

    /**
     * email 가져오기
     * @param sessionBean
     * @return
     */
    public static String getEmail(BaseHttpSession sessionBean) {
        if(sessionBean != null) {
            return _getStringAttribute(sessionBean,SESSION_KEY_EMAIL);
        } else {
            return null;
        }
    }

    public static void setEmail(BaseHttpSession sessionBean, String value) {
        Assert.notNull(sessionBean);
        sessionBean.addAttribute(SESSION_KEY_EMAIL, value);
    }

    /**
     * 과목코드 가져오기
     * @param sessionBean
     * @return
     */
    public static String getSjt_cd(BaseHttpSession sessionBean) {
        if(sessionBean != null) {
            return _getStringAttribute(sessionBean,SESSION_KEY_SJT_CD);
        } else {
            return null;
        }
    }

    public static void setSjt_cd(BaseHttpSession sessionBean, String value) {
        Assert.notNull(sessionBean);
        sessionBean.addAttribute(SESSION_KEY_SJT_CD, value);
    }

    /**
     * 강좌코드 가져오기
     * @param sessionBean
     * @return
     */
    public static String getLec_num(BaseHttpSession sessionBean) {
        if(sessionBean != null) {
            return _getStringAttribute(sessionBean,SESSION_KEY_LEC_NUM);
        } else {
            return null;
        }
    }

    public static void setLec_num(BaseHttpSession sessionBean, String value) {
        Assert.notNull(sessionBean);
        sessionBean.addAttribute(SESSION_KEY_LEC_NUM, value);
    }

    /**
     * 차수코드 가져오기
     * @param sessionBean
     * @return
     */
    public static String getLec_cd(BaseHttpSession sessionBean) {
        if(sessionBean != null) {
            return _getStringAttribute(sessionBean,SESSION_KEY_LEC_CD);
        } else {
            return null;
        }
    }

    public static void setLec_cd(BaseHttpSession sessionBean, String value) {
        Assert.notNull(sessionBean);
        sessionBean.addAttribute(SESSION_KEY_LEC_CD, value);
    }
    
}