/*
 * Created on 2004-12-23
 *
 */
package maf.mdb;

/**
 * @author goindole
 *
 */
public class MdbField {
    int columnIndex = 0;
    int columnType = 0;
    String columnName = null;        
    
    /**
     * 
     * @param columnName
     * @param columnIndex
     * @param columnType
     */
    protected MdbField(String columnName, int columnIndex, int columnType) {
        this.columnName = columnName;
        this.columnIndex = columnIndex;
        this.columnType = columnType;
    }
    
    
    /**
     * @return Returns the columnIndex.
     */
    public int getColumnIndex() {
        return columnIndex;
    }
    /**
     * @return Returns the columnName.
     */
    public String getColumnName() {
        return columnName;
    }
    /**
     * @return Returns the columnType.
     */
    public int getColumnType() {
        return columnType;
    }
}
