package maf.util;

import maf.base.BaseHttpSession;
import maf.base.BaseSessionUtil;
import modules.common.beans.UsrMstBean;
/**
 * �� ������Ʈ�� �°� Session�� Ȯ�� �����ؼ� ����ϱ� ���� Util
 * @author bluevlad
 *
 */
public class SessionUtil extends BaseSessionUtil {
    // �Ӽ� Ȯ��
    private final static String SESSION_KEY_EMAIL = "SESSION_KEY_EMAIL";
    
    // WLC����
    private final static String SESSION_KEY_SJT_CD = "SESSION_KEY_JST_CD"; //�����ڵ�(���� ���̺��� )
    private final static String SESSION_KEY_LEC_CD = "SESSION_KEY_LEC_CD"; //�����ڵ�
    private final static String SESSION_KEY_LEC_NUM = "SESSION_KEY_LEC_NUM"; //���� ����(���� ��û�� ���� ������û Ƚ���� ��)

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
     * email ��������
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
     * �����ڵ� ��������
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
     * �����ڵ� ��������
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
     * �����ڵ� ��������
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