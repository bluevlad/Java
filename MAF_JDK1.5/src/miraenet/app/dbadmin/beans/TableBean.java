/*
 * 작성된 날짜: 2005-02-02
 *
 */
package miraenet.app.dbadmin.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goindole
 *
 */
public class TableBean {
    String name = null;
    String comments = null;
    String owner = null;
    List columns = new ArrayList();
    
    
    public List getColumns() {
        return columns;
    }
    public void setColumns(List columns) {
        this.columns = columns;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getName() {
        return name;
    }
    public void setName(String nm) {
        this.name = nm;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
