<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="oracle.jdbc.driver.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.rmi.PortableRemoteObject" %>
<html>
<body>
<%
Context ctx = null;
DataSource ds = null;
//Connection conPool = null;
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try {
	StringBuffer query = new StringBuffer();

	ctx = new InitialContext();
	ds = (DataSource) ctx.lookup("edua1");
	conn = ds.getConnection(); 

	
	
	stmt = conn.createStatement();
	query.append( " select * " )
	     .append( " from MAF_LANG_ISO639_1 " ); 
	System.out.println( "ConnectionPool query is " + query.toString());     	
	System.out.println( "con is " + conn.toString());      
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
	if ( conn != null ) try { conn.close(); } catch(Exception e) {}		
	if ( ds != null ) try { ds=null; } catch(Exception e) {}	
}


%>
</body>
</html> 
   
   