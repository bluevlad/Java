<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="oracle.jdbc.driver.*" %>
<%@ page import="javax.sql.*" %><html><body>
<%
Connection conPool = null;
java.sql.Statement stmt = null;
ResultSet rs = null;

try {
	StringBuffer query = new StringBuffer();

	/* Connection 얻어오기 방법1 Driver를 이용해서 얻어오기 */
	/*
	Driver driver = (Driver)Class.forName("jeus.jdbc.pool.Driver").newInstance();
	conPool = driver.connect("jdbc:jeus:pool:edua1", null);
	*/
	
	
	/* Connection 얻어오기 방법2 DriverManager 얻어오기 */
	/* Class.forName( "jeus.jdbc.pool.Driver" );*/
	   conPool = DriverManager.getConnection( "jdbc:jeus:pool:jdbc/edua1", null);
	
	
	stmt = conPool.createStatement();
	query.append( " select * " ).append( " from JMF_LANG_ISO639_1 " ); 
	System.out.println( "ConnectionPool query is " + query.toString());     	
	System.out.println( "con is " + conPool.toString());      
	rs = stmt.executeQuery( query.toString() );

	while ( rs.next() )
	{	
		%>
		 <%= rs.getString(1) %> | <%= rs.getString(2) %> |
		 <%= rs.getString(3) %> |
		 <%= rs.getString(4) %><br>
	<%
	}	
} catch ( Exception e ) {
	e.printStackTrace();
} finally {
	if ( stmt != null ) try { stmt.close(); } catch(Exception e) {}	
	if ( conPool != null ) try { conPool.close(); } catch(Exception e) {}		

	//꼭닫아주어야 한다....에러 발생 가능성....
}
%>
</body>
</html> 
   
   