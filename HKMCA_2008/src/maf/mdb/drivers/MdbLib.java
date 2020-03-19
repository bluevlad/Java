/*
 * Created on 2004-12-24
 *
 */
package maf.mdb.drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import maf.mdb.exceptions.MdbException;
import oracle.sql.CLOB;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *  
 */
public class MdbLib {
    static Log logger = LogFactory.getLog(MdbLib.class);

    /**
     * DB의 Object을 의 데이타를 아래룰에 따라 String으로 변환 CLOB -> String ; BLOB -> String
     * "[BLOB]"; Date -> ? Char 계열 -> String 수치계열 -> String Null -> null 오류의 경의
     * Exception
     * 
     * @return
     */
//    protected static String getField(Object o, int columnType) {
//        String rst = null;
//
//        if (o == null)
//            return null;
//        switch (columnType) {
//        case Types.CLOB:
//            rst = "[CLOB]";
//            break;
//        case Types.BLOB:
//            rst = "[BLOB]";
//            break;
//        case Types.DATE:
//            rst = o.toString();
//            break;
//        default:
//            rst = o.toString();
//        	logger.debug("ColumnType : " + columnType  + ", JavaClass : " + rst.getClass());
//
//        }
//        return rst;
//    }

    /**
     * for Oracle CLOB to String
     * 8.1.6에서 오류
     * @param cl
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public static String CLOBToString(CLOB cl) throws IOException, SQLException {
        if (cl == null)
            return "";

        StringBuffer strOut = new StringBuffer();
        String aux;

        // We access to stream, as this way we don't have to use the
        // CLOB.length() which is slower...
        BufferedReader br = new BufferedReader(cl.getCharacterStream());

        while ((aux = br.readLine()) != null)
            strOut.append(aux);

        return strOut.toString();
    }

    /**
     * Clob에서 데이타를 읽어 String으로 전환
     * 
     * @param cl
     * @return
     * @throws IOException
     * @throws SQLException
     */

    public static String ClobToString(Clob cl) throws IOException, SQLException {
        if (cl == null)
            return "";

        // Open a stream to read Clob data
        Reader clobStream = cl.getCharacterStream();
        // Holds the Clob data when the Clob stream is being read
        StringBuffer suggestions = new StringBuffer();
        // Read from the Clob stream and write to the stringbuffer
        int nchars = 0; // Number of characters read
        char[] buffer = new char[10]; //  Buffer holding characters being transferred
        while ((nchars = clobStream.read(buffer)) != -1)  {     // Read from Clob
            suggestions.append(buffer, 0, nchars); // Write to StringBuffer
        }
        clobStream.close(); // Close the Clob input stream
        return suggestions.toString();
    }

    public static String getLongRaw(InputStream text_data)
            throws Exception {

        StringBuffer str = new StringBuffer();
        int c;

        
        while ((c = text_data.read()) != -1) {
            str.append((char) c);
        }
        text_data.close();

        return str.toString();
    }

    
    
    

    /**
     * getColumn : ResultSet에서 하나의 Column에서 값을 읽어 온다.
     * Return은 Object 형이며 String으로 
     * Clob -> String
     * 나머지는 
     * @param rs
     * @param column
     * @param columnType
     * @return
     * @throws Exception
     */
    public static Object getColumn(ResultSet rs, int column, int columnType ) throws MdbException{    
        Object rst = null;
        try{
	        switch (columnType) {
	        case Types.CLOB:
	            rst = MdbLib.ClobToString( rs.getClob(column));
	            break;
	        case Types.LONGVARCHAR:
	            rst =rs.getObject(column);// "[LONG]";
	        	//rst = getLongRaw(rs.getCharacterStream(column));//
	        	//rst = "[LONG]";
	            break;
	        case Types.BLOB:
	            rst = "[BLOB]";
	            break;
	        case Types.DATE:
	            rst = rs.getTimestamp(column);
	            break;
	        case Types.TIMESTAMP:
	            rst = rs.getTimestamp(column);
	            break;     
	//        case Types.NUMERIC:
	//           rst = new Integer(rs.getInt(column));
	//        	break;                
	        default:
        		rst = rs.getObject(column);
        		//logger.debug("ColumnType : " + columnType  + ", JavaClass : " + rst.getClass());
	        } //switch
        } catch ( Throwable e) {
        	logger.error(e.getMessage() + " ColumnType : " + columnType);
            //throw new MdbException(e.getMessage(), e);
        }
        return rst;
    }

    protected static String getLimitSql(String sql, int maxRows) {
        StringBuffer limitSql = new StringBuffer();
        if (maxRows >= 0) {
            limitSql.append("SELECT inner.*  FROM ( ");
            limitSql.append(sql);
            limitSql.append(") inner WHERE rownum <= ?");
        } else {
            limitSql.append(sql);
        }
        return limitSql.toString();
    }
    
    
    public static String writeClob(Clob clob) {
        
        StringBuffer sb = new StringBuffer();
        try {
            if (clob == null) {
                return "";
            }

            BufferedReader br = new BufferedReader(clob.getCharacterStream());

            char[] buffer = new char[1024]; 
            int byteRead; 
            while((byteRead=br.read(buffer,0,1024))!=-1){ 
            sb.append(buffer,0,byteRead); 
            } 


        } catch (Exception e) {
            logger.error(e.getMessage());
        }

      return sb.toString();
  }
    
}