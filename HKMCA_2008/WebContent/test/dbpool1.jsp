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

	/* Connection ������ ���1 Driver�� �̿��ؼ� ������ */
	/*
	Driver driver = (Driver)Class.forName("jeus.jdbc.pool.Driver").newInstance();
	conPool = driver.connect("jdbc:jeus:pool:edua1", null);
	*/
	
	
	/* Connection ������ ���2 DriverManager ������ */
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

	//���ݾ��־�� �Ѵ�....���� �߻� ���ɼ�....
}
%>
</body>
</html> 
   
   