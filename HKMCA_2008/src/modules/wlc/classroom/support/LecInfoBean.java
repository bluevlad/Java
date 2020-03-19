package modules.wlc.classroom.support;

import maf.base.BaseHttpSession;
import maf.util.SessionUtil;

/**
 * 강의실정보를 가짐
 * 강사에게는 lecnumb 사용 안함 //
 * @author bluevlad
 *
 */
public class LecInfoBean {
    private String sjt_cd = null;
    private String lec_cd = null;
    private String lec_num = null;
    private String lec_nm = null;

    public LecInfoBean(BaseHttpSession sessionBean) {
        this.sjt_cd = SessionUtil.getSjt_cd(sessionBean);
        this.lec_cd = SessionUtil.getLec_cd(sessionBean);
        this.lec_num = SessionUtil.getLec_num(sessionBean);
    }

    public String getLec_cd() {
        return lec_cd;
    }
    
    public String getLec_num() {
        return lec_num;
    }
    
    public String getSjt_cd() {
        return sjt_cd;
    }

    public String getLec_nm() {
        return lec_nm;
    }

    public void setLec_nm(String lec_nm) {
        this.lec_nm = lec_nm;
    }

}
