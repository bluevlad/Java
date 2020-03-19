/*
 * Created on 2004-12-23
 *
 */
package maf.mdb;

/**
 * @author goindole
 *
 */
public class MdbResultSetMetaData {
    private int columnCount = 0;
    private String columnName = null;
    private int columnType = MdbTypes.UNKNOWN;
    private int columnIndex = 0;
    
    public MdbResultSetMetaData(){
        
    }
    
    /**
     * Returns the number of columns in this ResultSet object.
     * @return Returns the columnCount.
     */
    public int getColumnCount(int column) {
        return columnCount;
    }
    /**
     * 
     * @return Returns the columnIndex.
     */
    public int getColumnIndex(int column) {
        return columnIndex;
    }
    /**
     * Get the designated column's name.
     * @return Returns the columnName.
     */
    public String getColumnName(int column) {
        return columnName;
    }
    /**
     * Retrieves the designated column's SQL type.
     * @return Returns the columnType.
     */
    public int getColumnType(int column) {
        return columnType;
    }
}
