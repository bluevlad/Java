/*
 * 작성된 날짜: 2005-02-06
 *  
 */
package miraenet.app.webfolder.beans;

/**
 * @author goindole, 2005-02-06 dTree.js에 Tree 그릴때 정보 주는 Bean
 */
public class DTreeBean {

    //d.add(id, pid, name, url, title, target);
    private int id = 0;
    private int pid = 0;
    private String nm = null;
    private String nm_eng = null;
    private String url = null;
    private String title = null;
    private String target = null;

    public DTreeBean(int id, int pid, String nm, String nm_eng,  String url, String title, String target) {
        this.id = id;
        this.pid = pid;
        this.nm = nm;
        this.nm_eng = nm_eng;
        this.url = url;
        this.title = title;
        this.target = target;        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String name) {
        this.nm = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getNm_eng() {
        return nm_eng;
    }
    public void setNm_eng(String nm_eng) {
        this.nm_eng = nm_eng;
    }
}