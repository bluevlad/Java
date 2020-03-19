/*
 * 작성된 날짜: 2005-02-02
 *
 */
package miraenet.app.dbadmin.beans;


/**
 * @author Rainend
 *
 */
public class ColumnBean {

	int id	= 0;
    String name = null;
    String comments = null;
    String pk = null;
    String nullable = null;
    String type = null;
    String data_default = null;
    
    int dlength = 0;		// 물리적 저장소 길이
    int length = 0;
    int precision = 0;
    int scale = 0;
    
    String javaType= null;
    
    String attr1 = null;
    String attr2 = null;
    String attr3 = null;
    
    boolean list = false;	// list에 나오는 여부
    boolean search = false; // search 조건 여부 
    
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getData_default() {
        return data_default;
    }
    public void setData_default(String data_default) {
        this.data_default = data_default;
    }
    public int getDlength() {
        return dlength;
    }
    public void setDlength(int dlength) {
        this.dlength = dlength;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getNullable() {
        return nullable;
    }
    public boolean isNotNull() {
        return ("Y".equals(nullable)?true:false);
    }
    public void setNullable(String nullable) {
        this.nullable = nullable;
    }
    public String getPk() {
        return pk;
    }
    public void setPk(String pk) {
        this.pk = pk;
    }
    public int getPrecision() {
        return precision;
    }
    public void setPrecision(int precision) {
        this.precision = precision;
    }
    public int getScale() {
        return scale;
    }
    public void setScale(int scale) {
        this.scale = scale;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public String getJavaType() {
        String t = null;
        if("NUMBER".equals(this.type)) {
            t = "int";
        } else if("DATE".equals(this.type)) {
            t = "Date";
        } else{
            t = "String";
        }
        return t;
    }
	/**
	 * @return the attr1
	 */
	public String getAttr1() {
		return attr1;
	}
	/**
	 * @param attr1 the attr1 to set
	 */
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	/**
	 * @return the attr2
	 */
	public String getAttr2() {
		return attr2;
	}
	/**
	 * @param attr2 the attr2 to set
	 */
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	/**
	 * @return the attr3
	 */
	public String getAttr3() {
		return attr3;
	}
	/**
	 * @param attr3 the attr3 to set
	 */
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
	/**
	 * @return the list
	 */
	public boolean isList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(boolean list) {
		this.list = list;
	}
	/**
	 * @return the search
	 */
	public boolean isSearch() {
		return search;
	}
	/**
	 * @param search the search to set
	 */
	public void setSearch(boolean search) {
		this.search = search;
	}
    
}
