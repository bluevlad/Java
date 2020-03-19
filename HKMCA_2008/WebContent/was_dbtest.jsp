<%@page contentType="text/html;charset=euc-kr"%>
<%@page import="java.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="javax.sql.DataSource"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>DataSource 연결 테스트</TITLE>
</HEAD>
<%
	InitialContext ctx = null ; 
	DataSource ds =null; 
	Connection con =null; 
	PreparedStatement pstm =null; 
	ResultSet rs =null; 
	String [] DataSourceName = {"jdbc/mafdb"}; // 테스트할 DataSource이름 목록을 넣는다.

	for(int i = 0; i < 1 /* <== 여기의 갯수를 수정하세요. */; i++){     //테스트할 DataSource 갯수를 넣어야한다.

		try {

			if(ctx == null) {
				ctx = new InitialContext();
				if(ds == null) {
					//ds  = (DataSource) ctx.lookup(DataSourceName[i]);  
					Context envCtx = (Context) ctx.lookup("java:comp/env");
					ds = (DataSource) envCtx.lookup(DataSourceName[i]);
				}
			}	

			con = ds.getConnection();
		
			out.println("DataSource (" + DataSourceName[i] + ") 연결테스트 =>> 정상 연결.");

		}catch(javax.naming.NamingException ne){		
			out.println("DataSource (" + DataSourceName[i] + ") 연결테스트 =>> 연결 에러.");
			out.println("<br>");
			out.println( "javax.naming.NamingException "+ ne.getMessage());
			ne.printStackTrace();	   	

		} catch(Exception e) {
			out.println("DataSource (" + DataSourceName[i] + ") 연결테스트 =>> 연결 에러.\n");
			out.println("<br>");
			out.println( "CONNECTION-ERROR : " + e.getMessage());

		}finally{
			if(pstm!=null) try{ pstm.close();	}catch(Exception e){}
			if(con!=null) try{ con.close();	}catch(Exception e){}
		}

		out.println("<br><br>");
	}

%>

