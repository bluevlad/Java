/*
 * 작성된 날짜: 2005-02-02
 *
 */
package miraenet.app.dbadmin.beans;

import java.util.Date;

/**
 * @author bluevlad, 2005-02-02
 *
 */
public class IndexBean {
    String name = null;
    String type = null;
    String uniqueness = null;
    Date lastanalyzed = null;
    int distinctkeys = 0;
    int numrows = 0;
    String column_name = null;
    int column_position = 0;
    String descend = null;
    int column_length = 0;
    String column_expression = null;
    
    
    public String getColumn_expression() {
        return column_expression;
    }
    public void setColumn_expression(String column_expression) {
        this.column_expression = column_expression;
    }
    public int getColumn_length() {
        return column_length;
    }
    public void setColumn_length(int column_length) {
        this.column_length = column_length;
    }
    public String getColumn_name() {
        return column_name;
    }
    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }
    public int getColumn_position() {
        return column_position;
    }
    public void setColumn_position(int column_position) {
        this.column_position = column_position;
    }
    public String getDescend() {
        return descend;
    }
    public void setDescend(String descent) {
        this.descend = descent;
    }
    public int getDistinctkeys() {
        return distinctkeys;
    }
    public void setDistinctkeys(int distinctkeys) {
        this.distinctkeys = distinctkeys;
    }
    public Date getLastanalyzed() {
        return lastanalyzed;
    }
    public void setLastanalyzed(Date lastanalyzed) {
        this.lastanalyzed = lastanalyzed;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumrows() {
        return numrows;
    }
    public void setNumrows(int numrows) {
        this.numrows = numrows;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUniqueness() {
        return uniqueness;
    }
    public void setUniqueness(String uniqueness) {
        this.uniqueness = uniqueness;
    }
}
