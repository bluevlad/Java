/*
 * 작성된 날짜: 2005-01-17
 *

 */
package modules.community.mboard.beans;

/**
 * @author bluevlad
 *

 */
public class MbbsGroupBean {
    String grp =null;
    String grp_desc = null;
    int seq = 0;
    String site = null;
    String layout = null;
    String c_path = null;
    

    public String getC_path() {
        return c_path;
    }
    public void setC_path(String path) {
        c_path = path;
    }
    public String getGrp() {
        return grp;
    }
    public void setGrp(String grp) {
        this.grp = grp;
    }
    public String getGrp_desc() {
        return grp_desc;
    }
    public void setGrp_desc(String grp_desc) {
        this.grp_desc = grp_desc;
    }
    public String getLayout() {
        return layout;
    }
    public void setLayout(String layout) {
        this.layout = layout;
    }
    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
}
