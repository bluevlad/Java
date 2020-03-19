package miraenet.app;


/**
 * BeanMaker.java 
 *
 * : JSP 개발시 DB 테이블에 맞는 Beans 클래스의 소스를 생성시켜 주는 클래스 *^^*
 * : Free ware 입니다. 마구마구 퍼다 쓰세여~
 *
 * @ author : Sim Jae Jin 
 * @ e-mail : sim11@miraenet.com
 * @ create-date : 2001-07-16 16:50
 * @ modify-date : 2001-08-07
 * 
 * 
 * @ modify : 진영석
 * @ date : 2004-05-17
 * 
 * @ modify : 박혁
 * @ date : 2004-11-24
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.StringTokenizer;



public class BeanMakerAll{
    
    static String table_name;
    static String table_org_name;
    static String prop_file;
    static String folder;
    
    // DB Server Config
    static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    static String SERVER = "203.234.247.19"; 
    static int PORT = 1522; // oracle
    static String SID = "ora920";
    static String URL = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + SID;
    static String USER = "ehrd";    
    static String PASSWORD = "qwer12";
    
    Connection con;
    
    public static void main(String args[]){
        if(args == null || args.length < 1){
            System.out.println("인수를 입력하지 않습니다.");
            System.out.println("사용예 : java BeanMaker table_name folder_name");
            System.exit(0);  
        }
        
        BeanMakerAll bm = new BeanMakerAll();
        
        /*
        prop_file = args[0];
        table_name = args[1];
        folder = args[2];
        
        if (bm.getProperties ()) {
            bm.go();
        }else{
            System.exit (0);
        }
        */

        // ASIC BeanMaker..
                
        table_name = args[0];
        table_org_name = args[0];
        folder = args[1];
        bm.go();
    }
    
    // properties load
   
    
    // DB Connect
    private boolean connect(){
        try{
            // Load the driver
            Class.forName(DRIVER);
            
            // Connect to the database
            con = DriverManager.getConnection (URL, USER, PASSWORD);
            
            System.out.println("1. 데이터 베이스에 연결되었습니다.");
        }catch(Exception e){
            System.out.println("error : 데이터 베이스에 연결 실패했습니다.");
            e.printStackTrace();
            return false;
        }              
        return true;
    }
    
    // DB Query
    private void go(){
        String sql = "select * from "+table_name +" where rownum < 10";
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        // DB Connection
        if(!connect()){
            return;  
        }
        
        // table information
        int column_type [] = null;
        String column_name [] = null;
        String column_type_name [] = null;
        String column_lable [] = null;
        int column_size [] = null;
        int column_count = 0;
        
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);            
            rsmd = rs.getMetaData();
            
            column_count = rsmd.getColumnCount();
            column_type = new int[column_count];
            column_name = new String[column_count];
            column_type_name = new String[column_count];
            column_size = new int[column_count];
            column_lable = new String[column_count];
            
            for(int i=0; i<column_count; i++){
                column_type[i] = rsmd.getColumnType(i+1);
                column_name[i] = (rsmd.getColumnName(i+1)).toLowerCase();
                column_type_name[i] = (rsmd.getColumnTypeName(i+1)).toLowerCase();
                column_size[i] = (rsmd.getColumnDisplaySize(i+1));
                column_lable[i] = rsmd.getColumnLabel(i+1);
            }
            
            rs.close();
            stmt.close();
            System.out.println("2. 데이터 베이스로 부터 "+table_name+" 테이블의 정보를 가져왔습니다.");
        }catch(Exception e){
            e.printStackTrace();
            return;
        }finally{
            try{
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            }catch(SQLException _ignored){}
        }  
        
        // File Write
        new BeanWriterAll(column_type, column_size, column_type_name, column_name, table_name, folder, column_lable);
    }
}

class BeanWriterAll{
    static int [] column_type;
    static String [] column_name;
    static String [] column_type_name;
    static int [] column_size;
    static String [] type_name;
    static String [] column_lable; 
    static String table_name;
    static String table_org_name;
    static PrintWriter out;
    static PrintWriter view;
    static PrintWriter write;
    static PrintWriter modify;
    static PrintWriter dao;
    static PrintWriter pkgspec;
    static PrintWriter pkgbody;
    static PrintWriter act;
    static PrintWriter list;
    static String folder;
    
    public BeanWriterAll(int [] column_type, int [] column_size, String [] column_type_name, String [] column_name, String table_name, String folder, String [] column_lable){
        
    	BeanWriterAll.column_type = column_type;
    	BeanWriterAll.column_name = column_name;
    	BeanWriterAll.column_type_name = column_type_name;
    	BeanWriterAll.column_size = column_size;
    	BeanWriterAll.column_lable = column_lable;
    	BeanWriterAll.folder = folder;
        
        // table name : free rule..
        // 진영석
        String name = "";
        table_org_name = table_name;
        StringTokenizer st = new StringTokenizer (table_name, "_");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            name += token.substring(0, 1).toUpperCase() + token.substring(1, token.length()).toLowerCase();
        }
        
        BeanWriterAll.table_name = name;
        
        setColumnType(column_type);
        if(init()){
            start();
        }
    }
    
    private static void start(){
        // copyright
        out.println("/*");
        out.println(" * By beans generator source file ");
        out.println(" * source file name : "+table_name+"Bean.java");
        out.println(" * ");
        out.println(" * ### BeanMaker 1.0 ###");
        out.println(" * @ author : Sim Jae Jin");
        out.println(" * @ date : 2001-07-16 16:50");
        out.println(" * @ mail : sim11@miraenet.com");
        out.println(" *");
        out.println(" */");
        out.println();
        out.println("/*");
        out.println(" * By beans generator source file ");
        out.println(" * source file name : "+table_name+"Bean.java");
        out.println(" * extended BasicBean.java file");
        out.println(" * column name, column type, column size info");
        out.println(" * ");
        out.println(" * ### BeanMaker 1.1 ###");
        out.println(" * @ author : Jin Young Sug");
        out.println(" * @ date : 2004-05-17");
        out.println(" * @ mail : ysjin@miraenet.com");
        out.println(" *");
        out.println(" */");
        out.println();
        // package        
        out.println("package org.kipa.asic.comm.beans.table;");
        out.println();
        // import
        // out.println("import com.miraenet.lib.BasicBean;");
        // out.println();
        // class name
        out.println("public class "+table_name+"Bean extends BasicBean {");
        
        // member variable
        tab();out.println("// MEMBER VARIABLE");
        for(int i=0; i<column_name.length; i++){
            tab();out.println("private "+type_name[i] +" "+ column_name[i]+";");
        }
        out.println();
        
        // table info variable
        tab();out.println("// TABLE INFO VARIABLE");
        tab();out.println("private String column_name[]={"+getColumnName_String(column_name)+"};");
        tab();out.println("private String column_type[]={"+getColumnType_String(column_type_name)+"};");
        tab();out.println("private int column_size[]={"+getColumnSize_String(column_size)+"};");
        out.println();
        
        // construct
        tab();out.println("// CONSTRUCT");
        tab();out.println("public "+table_name+"Bean(){");
        tab();tab();out.println("this.setColumn_name(column_name);");
        tab();tab();out.println("this.setColumn_type(column_type);");
        tab();tab();out.println("this.setColumn_size(column_size);");
        tab();out.println("}");
        out.println();

        // setter
        tab();out.println("// SETTER");   
        for(int i=0; i<column_name.length; i++){  
            tab();out.println("public void set"+getSetter(i)+"("+type_name[i]+" args){");
            tab();tab();out.println("this."+column_name[i]+" = args;//" + column_lable[i] );
            tab();out.println("}");
        }
        out.println();
        
        // gatter        
        tab();out.println("// GETTER");        
        for(int i=0; i<column_name.length; i++){   
            if(type_name[i].equals("java.util.Date")){
                tab();out.println("// 날짜형일 경우 'yyyy-MM-dd' 포멧의 스트링으로 리턴하는 메소드를 추가");
                tab();out.println("public String get"+getSetter(i)+"_String(){");
                // tab();tab();out.println("return com.miraenet.lib.Util.date.getDateString("+column_name[i]+", \"yyyy-MM-dd\");");
                tab();tab();out.println("return org.kipa.asic.comm.lib.Util.DateUtil.getDateString("+column_name[i]+", \"yyyy-MM-dd\");");
                tab();out.println("}");
            }
            
            tab();out.println("public "+(type_name[i])+" get"+getSetter(i)+"(){");
            tab();tab();out.println("return this."+column_name[i]+";");

            tab();out.println("}");
        }        
        out.println("}");
        out.flush();
        out.close();
        
        setView();
        setModify();
        setWrite();
        setDao();
        setPkgspec();
        setPkgbody();
        setAct();
        setList();
        
        view.flush();
        view.close();
        modify.flush();
        modify.close();
        write.flush();
        write.close();
        dao.flush();
        dao.close();
        pkgspec.flush();
        pkgspec.close();
        pkgbody.flush();
        pkgbody.close();
        act.flush();
        act.close();
        list.flush();
        list.close();
        
        System.out.println("4. "+table_name+"Bean 클래스의 소스파일을 생성하였습니다.");
        System.out.println("5. 작업이 완료되었습니다.");
        System.out.println("6. 소스파일명 : "+table_name+"Bean.java");
    }
    
    private static boolean init(){
        // 클래스명은 첫번째자를 대문자로 한다.
        // table_name = (table_name.substring(0,1)).toUpperCase() + table_name.substring(1, table_name.length());
        
        File f = new File(folder + "/" + table_name + "Bean.java");
        File v = new File(folder + "/" + "view.jsp");
        File m = new File(folder + "/" + "modify.jsp");
        File in = new File(folder + "/" + "insert.jsp");
//        File db = new File(folder + "/" + table_name + "DB.java");
        if(f.exists()){
            System.out.println("3. "+table_name+"Bean.java 와 동일한 파일명이 존재합니다.");
            System.out.println("4. 프로그램을 종료합니다.");
            return false;  
        }
        if(v.exists()){
            System.out.println("3. "+table_name+"View.jsp 와 동일한 파일명이 존재합니다.");
            System.out.println("4. 프로그램을 종료합니다.");
            return false;  
        }
        if(m.exists()){
            System.out.println("3. "+table_name+"Modify.jsp 와 동일한 파일명이 존재합니다.");
            System.out.println("4. 프로그램을 종료합니다.");
            return false;  
        }
        if(in.exists()){
            System.out.println("3. "+table_name+"Insert.jsp 와 동일한 파일명이 존재합니다.");
            System.out.println("4. 프로그램을 종료합니다.");
            return false;  
        }
        
        try {
            out = new PrintWriter(new FileWriter(table_name+"Bean.java", false), true);
            view = new PrintWriter(new FileWriter("view.jsp", false), true);
            modify = new PrintWriter(new FileWriter("modify.jsp", false), true);
            write = new PrintWriter(new FileWriter("insert.jsp", false), true);
            dao = new PrintWriter(new FileWriter(table_name+"DB.java", false), true);
            pkgspec = new PrintWriter(new FileWriter(table_name+"_spc.sql", false), true);
            pkgbody = new PrintWriter(new FileWriter(table_name+"_body.sql", false), true);
            act = new PrintWriter(new FileWriter(table_name+"Action.java", false), true);
            list = new PrintWriter(new FileWriter("list.jsp", false), true);
        }catch (IOException e) {
            System.err.println("Can't create the class file: " + table_name +"Bean.java");
        }
        System.out.println("3. "+table_name+" 클래스의 소스를 저장할 파일을 열었습니다.");
        return true;
    }    
    
    private static void tab(){
        out.print("    ");  
    }
    
    private static void setList() {
    	list.println("<%@ page language=\"java\" contentType=\"text/html; charset=euc-kr\"%>");
    	list.println("<%@ page import=\"org.kipa.asic.cad.beans.table.*\" %>");
    	list.println("<%@ page import=\"org.kipa.asic.comm.lib.Util.*\" %>");
    	list.println("<%@ page errorPage=\"../etc/error.jsp\" %>");
    	list.println("<%@ taglib uri=\"/WEB-INF/classes/com/xboard/tld/NavigationTag.tld\" prefix=\"navi\" %>");
    	list.println("<%@ taglib uri=\"/WEB-INF/classes/com/xboard/tld/PagePropertiesTag.tld\" prefix=\"page\" %>");
		list.println("<jsp:useBean id=\"_req\" class=\"org.kipa.asic.comm.lib.ExtHttpServletRequest\" scope=\"page\">");
    	list.println("    <jsp:setProperty name=\"_req\" property=\"request\" value=\"<%=request%>\" />");
    	list.println("</jsp:useBean>");
    	list.println("");
    	list.println("<%");
    	list.println("	"+table_name+"Bean [] beans = ("+table_name+"Bean []) request.getAttribute (\"beans\");");
    	list.println("	int RecordCnt = StrUtil.parseInt((String)request.getAttribute(\"cnt\"));");
    	list.println("String action_name = _req.getAttribute (\"action_name\", \"\");");
    	list.println("int currentPage = _req.getParameter (\"page\", 1);");
    	list.println("int max = _req.getParameter (\"max\", 10);");
    	list.println("int datanum = MathUtil.getDataNum(RecordCnt, currentPage, max);");
    	list.println("String searchtype = _req.getParameter (\"searchtype\");");
    	list.println("String keyword = (String)request.getAttribute(\"keyword\");");
    	list.println("String order = _req.getParameter (\"order\", \"1\");");
    	list.println("String ordertype = _req.getParameter (\"ordertype\", \"desc\");");
    	list.println("%>");
    	
    	list.println("");
    	list.println("<LINK rel=\"stylesheet\" type=\"text/css\" href=\"/css/asic.css\">");
    	list.println("<SCRIPT LANGUAGE=\"JavaScript\">");
    	list.println("<!--");
    	list.println("	function doSearch(){");
    	list.println("		document.frm.submit();");
    	list.println("	}");
    	list.println("function doOrder (frm, order) {");
    	list.println("   		frm.order.value = order;");
    	list.println("    		if (frm.ordertype.value == 'asc') frm.ordertype.value = 'desc';");
    	list.println("    		else frm.ordertype.value = 'asc';");
    	list.println("    		frm.submit ();");
    	list.println("	}");
    	
    	list.println("//    	-->");
    	list.println("</SCRIPT>");
    	list.println("<table width='100%'  border='0' cellspacing='1' cellpadding='0' class='table'>");
    	list.println("<form name='frm' method='post' action='<%=action_name %>'>");
    	list.println("<input type='hidden' name='max' value='<%=max %>'/>"); 
    	list.println("<input type='hidden' name='order' value='<%=order%>'/>");
    	list.println("<input type='hidden' name='ordertype' value='<%=ordertype%>'/>");
    	list.println("<tr>"); 
    	list.println("    <td height='0' colspan='4'><img src='/images/common/trans.gif' width='1' height='1'></td>");
    	list.println("</tr><tr>"); 
    	list.println("    <td height='0' bgcolor='#FFFFFF' colspan='4'><img src='/images/common/trans.gif' width='1' height='1'></td>");
    	list.println("</tr><tr>"); 
    	list.println("    <td width='140' align=center class='class'>검색조건</td>");
    	list.println("    <td width='200' class='p2'>");
    	list.println("<select name=\"searchtype\">");
    	for(int i=0; i<column_name.length; i++){
     	   list.println("<option value='"+column_name[i]+"' <%=HtmlUtil.getOption(searchtype,\"\")%>>"+column_name[i] +"</option>");
     	}
    	list.println("</select>");
    	list.println("	<td width='140' align='center' class='class'>검색어</td>");
    	list.println("    <td width='199' class='p2'>");
    	list.println("    <input type='text' name='keyword' value='<%=keyword%>'>");
    	list.println("    </td>");
    	list.println("    </tr>");
    	list.println("</form>");
    	list.println("</table>");
    	list.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
    	list.println("<tr>");
    	list.println("    <td height=\"6\"><img src=\"/images/common/trans.gif\" width=\"1\" height=\"1\"></td>");
    	list.println("</tr>");
    	list.println("<tr>");
    	list.println("    <td align=\"right\">");
    	list.println("        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
    	list.println("            <tr>");
    	list.println("                <td>");
    	list.println("                    <a href='javascript:doSearch();'>");
    	list.println("                    <img src=\"/images/but/search03.gif\" width=\"60\" height=\"26\" border=\"0\" align=absmiddle></a>");
    	list.println("                </td>");
    	list.println("            </tr>");
    	list.println("        </table>");
    	list.println("    </td>");
    	list.println("</tr>");
    	list.println("<tr><td height=\"10\" colspan='2'><img src=\"/images/common/trans.gif\" width=\"1\" height=\"1\"></td></tr>");
    	list.println("</table>");
    	
    	list.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
    	list.println("<tr height='24'>");
    	list.println("    <td class=\"p2\" >");
    	list.println("        <navi:list url=\"<%=action_name%>\" pageParam=\"page\" maxParam=\"max\" totalSize=\"<%=RecordCnt%>\" style=\"basic\"/>");
    	list.println("    </td>");
    	list.println("</tr>");
    	list.println("</table>");
    	list.println("<table width=\"100%\"  border=\"0\" cellspacing=\"1\" cellpadding=\"0\" class=\"table\">");
    	list.println("<tr>"); 
    	list.println("    <td height='0' colspan='4'><img src='/images/common/trans.gif' width='1' height='1'></td>");
    	list.println("</tr><tr>"); 
    	list.println("    <td height='0' bgcolor='#FFFFFF' colspan='4'><img src='/images/common/trans.gif' width='1' height='1'></td>");
    	list.println("</tr>"); 
    	list.println("    <tr>");

    	for(int i=0; i<column_name.length; i++){
    	   list.println("		<td class=\"class\"><a href='javascript:doOrder (document.frm, '"+column_name[i]+"');'>"+column_name[i]+ "</a></td>");
    	}
    	list.println("	</tr>");
    	list.println("    <%");
    	list.println("    for (int i = 0; i < beans.length; i++) {");
    	list.println("    %>");
    	list.println("    <tr bgcolor='white'>");
    	for(int i=0; i<column_name.length; i++){
    		if (i==0){
    			list.println("		<td class='p2'><a href='/itsoc/actions/"+table_name+"Action?cmd=view><%=beans[i].get"+getSetter(i)+ "()%></a></td>");
    		}else{
    			list.println("		<td class='p2'><%=beans[i].get"+getSetter(i)+"()%></td>");
    		}
    	}
    	list.println("    </tr>");
    	list.println("    <%");
    	list.println("    }");
    	list.println("    %>");
    	list.println("</table>");
    	
    	list.println("<table width='100%'>");
    	list.println("<tr>");
    	list.println("    <td align=\"center\" style=\"font-size:11px\">");
    	list.println("        <navi:page url=\"<%=action_name%>\" pageParam=\"page\" maxParam=\"max\" totalSize=\"<%=RecordCnt%>\" style=\"basic\"/>");
    	list.println("    </td>");
    	list.println("</tr>");
    	list.println("</table>");
    	
    	
    	list.println("<br>");
    	list.println("<a href='/itsoc/actions/"+table_name+"Action?cmd=writeform'><img src=\"/images/but/registration.gif\"></a>");

    }
    private static void setView() {
    	view.println("<%@ page language=\"java\" contentType=\"text/html; charset=euc-kr\"%>");
    	view.println("<%@ page import=\"org.kipa.asic.ip.beans.table.*\" %>");
    	view.println("<%@ page errorPage=\"../etc/error.jsp\" %>");
    	view.println("<jsp:useBean id=\"bean\" class=\"com.xboard.beans.BbsDataBeanEx\" scope=\"page\"/>");
    	view.println("");
    	view.println("<%");
    	view.println("	"+table_name+"Bean beans = ("+table_name+"Bean) request.getAttribute (\"beans\");");
    	view.println("%>");
    	view.println("");
    	view.println("<table width=\"100%\"  border=\"0\" cellspacing=\"1\" cellpadding=\"0\" class=\"table\">");
    	for(int i=0; i<column_name.length; i++){   
    		view.println("<tr bgcolor='white'>");
    		view.println("<td class=\"class\">"+column_lable[i]+"</td>");
    		view.println("<td class=\"p2\"><%=beans.get"+getSetter(i)+"()%></td>");
    		view.println("</tr>");
    	}
    	view.println("</table>");
    	view.println("<a href=\"/itsoc/actions/"+table_name+"Action\"><img src=\"/images/but/list.gif\"></a>");
    	view.println("<a href=\"/itsoc/actions/"+table_name+"Action?cmd=update\"><img src=\"/images/but/save.gif\"></a>");
    }
    private static void setModify() {
    	modify.println("<%@ page language=\"java\" contentType=\"text/html; charset=euc-kr\"%>");
    	modify.println("<%@ page errorPage=\"../etc/error.jsp\" %>");
    	modify.println("<%@ page import=\"org.kipa.asic.ip.beans.table.*\" %>");
    	modify.println("");
    	modify.println("<%");
    	modify.println("	"+table_name+"Bean [] beans = ("+table_name+"Bean []) request.getAttribute (\"beans\");");
    	modify.println("%>");
    	modify.println("");
    	modify.println("<SCRIPT LANGUAGE='JavaScript'>");
    	modify.println("<!--");
    	modify.println("function goWrite(){");
    	modify.println("	document.frm.submit();");
    	modify.println("}");
    	modify.println("//-->");
    	modify.println("</SCRIPT>");
    	modify.println("<table width=\"100%\"  border=\"0\" cellspacing=\"1\" cellpadding=\"0\" class=\"table\">");
    	modify.println("<form name='frm' method='post' action='/itsoc/actions/"+table_name+"Action?cmd=modify'>");
    	for(int i=0; i<column_name.length; i++){   
    		modify.println("<tr bgcolor='white'>");
    		modify.println("<td class='class'>"+column_lable[i]+"</td>");
    		modify.println("<td class='p2'><input type='text' name='"+column_name[i]+"' maxlength='"+column_size[i]+"' value='<%=beans.get"+getSetter(i)+"()%>'></td>");
    		modify.println("</tr>");
    	}
    	modify.println("</form>");
    	modify.println("</table>");
    	modify.println("<a href='javascript:goWrite()'><img src=\"/images/but/save.gif\"></a>");
    }

    private static void setWrite() {
    	write.println("<%@ page language=\"java\" contentType=\"text/html; charset=euc-kr\"%>");
    	write.println("<%@ page errorPage=\"../etc/error.jsp\" %>");
    	write.println("");
    	write.println("<SCRIPT LANGUAGE='JavaScript'>");
    	write.println("<!--");
    	write.println("function goWrite(){");
    	write.println("	document.frm.submit();");
    	write.println("}");
    	write.println("//-->"); 
    	write.println("</SCRIPT>");
    	write.println("<table width=\"100%\"  border=\"0\" cellspacing=\"1\" cellpadding=\"0\" class=\"table\">");
    	write.println("<form name='frm' method='post' action='/itsoc/actions/"+table_name+"Action?cmd=save'>");
    	for(int i=0; i<column_name.length; i++){   
    		write.println("	<tr bgcolor='white'>");
    		write.println("		<td class='class'>"+column_lable[i]+"</td>");
    		write.println("		<td class='p2'><input type='text' name='"+column_name[i]+"' maxlength='"+column_size[i]+"' value=''></td>");
    		write.println("	</tr>");
    	}
    	write.println("</form>");
    	write.println("</table>");
    	write.println("<a href='javascript:goWrite()'><img src=\"/images/but/save.gif\"></a>");
    }
    
    private static void setDao() {
    	dao.println("package org.kipa.asic.ip.dao;");
    	dao.println("import java.sql.SQLException;");
    	dao.println("import java.util.Collection;");
    	dao.println("import org.kipa.asic.comm.lib.DAOHome;");
    	dao.println("import org.kipa.asic.comm.lib.HashMapEx;");
    	dao.println("import org.kipa.asic.comm.lib.Selecter;");
    	dao.println("import org.kipa.asic.comm.lib.DBManager.DBConnectionFailedException;");
    	dao.println("import org.kipa.asic.comm.lib.DBManager.DBHandlerException;");
    	dao.println("import org.kipa.asic.comm.lib.DBManager.DataNotFoundException;");
    	dao.println("import org.kipa.asic.comm.lib.DBManager.IllegalSqlException;");

    	dao.println("");
    	dao.println("");

    	dao.println("public class " + table_name + "DB extends DAOHome { ");
    		
    	dao.println("//리스트 불러오기");
    	dao.println("	public "+table_name+"BeanEx [] getRecords (String where, int page, int max)");
    	dao.println("		throws ClassNotFoundException, DataNotFoundException, DBHandlerException, DBConnectionFailedException,"); dao.println("	IllegalSqlException, DataNotFoundException {");
    	dao.println("");           
    	dao.println("		String sql = \"select /*+ RULE index ("+table_org_name+" idx_"+table_org_name+"_01) */ * \"");
    	dao.println("			+    \"from "+table_org_name+"\" + where;");
    	dao.println("		Selecter s = new Selecter(DB_SID[0], \"org.kipa.asic.ip.beans."+table_name+"BeanEx\", true);");
    	dao.println("		s.pageConfig(page,max);");
    	dao.println("		Collection col = s.execute(sql, Selecter.NULL);");
    	dao.println("		return ("+table_name+"BeanEx[]) col.toArray(new "+table_name+"BeanEx[0]);");
    	dao.println("}");
    	dao.println("");
    	    
    	dao.println("//Row 수 불러오기");
    	dao.println("	public String getRecordsCount (String where)"); 
    	dao.println("		throws DBHandlerException, DBConnectionFailedException {");
    	dao.println("		String sql = \"select /*+ RULE index ("+table_org_name+" idx_"+table_org_name+"_01) */ count(*) \"");
    	dao.println("			+    \"from "+table_org_name+"\" + where;");
    	dao.println("");    
    	dao.println("		return executeQuery (sql);");
    	dao.println("	}");
    	dao.println("");    
    	    
    	dao.println("//저장하기");
    	dao.println("	public int setRecord ("+table_name+"Bean bean)");
    	dao.println("		throws NoSuchMethodException,SQLException, DBHandlerException, DBConnectionFailedException, NoSuchFieldException {");
    	dao.println("");            
    	dao.println("		String sql = \"{call ASIC_"+table_org_name+".insert"+table_name+"Record\" + "); 
    	dao.println("		\"(\" +");
    	dao.print("		\"");
    	for(int i=0; i<column_name.length; i++){
    	dao.print(":"+column_name[i]+",");
    	}
    	dao.print(":@String\"+");
    	dao.println("\")}\"; ");

    	dao.println("		String err;");
    	dao.println("		err = executeCall(sql, bean);");
    	dao.println("		return StrUtil.parseInt (err);");
    	dao.println("	}");
    	dao.println("");

    	dao.println("//수정하기");
    	dao.println("	public int "+table_name+"Update ("+table_name+"Bean bean)");
    	dao.println("		throws NoSuchMethodException,SQLException, DBHandlerException, DBConnectionFailedException, NoSuchFieldException {");
    	dao.println("");            
    	dao.println("		String sql = \"{call ASIC_"+table_org_name+".update"+table_name+"Record(\" + ");
    	dao.print("		\"");
    	for(int i=0; i<column_name.length; i++){
    	dao.print(":"+column_name[i]+",");
    	}
    	dao.print(":@String\"+");
    	dao.println("\")}\"; ");

    	dao.println("		String err;");
    	dao.println("		err = executeCall(sql, bean);");
    	dao.println("		return StrUtil.parseInt (err);");
    	dao.println("	}");
    	dao.println("");
    	        
    	dao.println("//삭제하기");
    	dao.println("	public int "+table_name+"Del (String code)");
    	dao.println("		throws SQLException, DBHandlerException, DBConnectionFailedException, NoSuchFieldException {");
    	dao.println("");            
    	dao.println("		String sql = \"{call ASIC_"+table_org_name+".delete"+table_name+"Record (:code,:@String)}\";");
    	dao.println("		HashMapEx where = new HashMapEx ();");
    	dao.println("		where.put(\"code\", code);");
    	dao.println("		where.put(\"@String\", \"\");");
    	dao.println("		return StrUtil.parseInt (executeCall (sql, where));");

    	dao.println("	}");            

    	dao.println("}");
    }
    private static void setPkgspec() {
    	pkgspec.println("CREATE OR REPLACE PACKAGE ASIC_"+table_org_name+" AS");
    	pkgspec.println("/******************************************************************************");
    	pkgspec.println("   NAME:       "+table_org_name);
    	pkgspec.println("   PURPOSE:");
    	pkgspec.println("   REVISIONS:");
    	pkgspec.println("   Ver        Date        Author           Description");
    	pkgspec.println("   ---------  ----------  ---------------  ------------------------------------");
    	pkgspec.println("******************************************************************************/");
    	pkgspec.println("");
    	pkgspec.println("	/************************************************** ");
    	pkgspec.println("        NAME : insert"+table_name+"Record");
    	pkgspec.println("        DESC : ");
    	pkgspec.println("        VERSION");
    	pkgspec.println("    **************************************************/");
    	pkgspec.println("    PROCEDURE insert"+table_name+"Record");
    	pkgspec.println("(");
    	for(int i=0; i<column_name.length; i++){
    	pkgspec.println("p"+column_name[i]+ "	"+table_org_name+"."+column_name[i]+"%type,");
    	}
    	pkgspec.println("rValue		OUT VARCHAR2");
    	pkgspec.println(");");			  
    	pkgspec.println("");


    	pkgspec.println("	/************************************************** ");
    	pkgspec.println("        NAME : update"+table_name+"Record");
    	pkgspec.println("        DESC : UPDATE");
    	pkgspec.println("        VERSION");
    	pkgspec.println("    **************************************************/");
    	pkgspec.println("    PROCEDURE update"+table_name+"Record");
    	pkgspec.println("(");
    	for(int i=0; i<column_name.length; i++){
    	pkgspec.println("p"+column_name[i]+ "	"+table_org_name+"."+column_name[i]+"%type,");
    	}
    	pkgspec.println("rValue		OUT VARCHAR2");
    	pkgspec.println(");");			  
    	pkgspec.println("");

    	pkgspec.println("     /************************************************** ");
    	pkgspec.println("        NAME : delete"+table_name+"Record");
    	pkgspec.println("        DESC : IP 카테고리 코드 Delete");
    	pkgspec.println("        VERSION");
    	pkgspec.println("    **************************************************/");
    	pkgspec.println("    PROCEDURE delete"+table_name+"Record");
    	pkgspec.println("    (pReq_code	 IN     INT_REQ.REQ_CODE%TYPE,");
    	pkgspec.println("     rValue			OUT VARCHAR2");
    	pkgspec.println("  );");
    				  
    	pkgspec.println("END ASIC_"+table_org_name+";");
    	pkgspec.println("/");

    }
    
    private static void setPkgbody() {
    	pkgbody.println("CREATE OR REPLACE PACKAGE BODY ASIC_"+table_org_name+" AS");
    	pkgbody.println("/******************************************************************************");
    	pkgbody.println("   NAME:       "+table_org_name);
    	pkgbody.println("   PURPOSE:");
    	pkgbody.println("   REVISIONS:");
    	pkgbody.println("   Ver        Date        Author           Description");
    	pkgbody.println("   ---------  ----------  ---------------  ------------------------------------");
    	pkgbody.println("******************************************************************************/");
    	pkgbody.println("");
    	pkgbody.println("	/************************************************** ");
    	pkgbody.println("        NAME : insert"+table_name+"Record");
    	pkgbody.println("        DESC : ");
    	pkgbody.println("        VERSION");
    	pkgbody.println("    **************************************************/");
    	pkgbody.println("    PROCEDURE insert"+table_name+"Record");
    	pkgbody.println("(");
    	for(int i=0; i<column_name.length; i++){
    	pkgbody.println("p"+column_name[i]+ "	"+table_org_name+"."+column_name[i]+"%type,");
    	}
    	pkgbody.println("rValue		OUT VARCHAR2");
    	pkgbody.println(") IS");
    	pkgbody.println("BEGIN");
    	pkgbody.println("");
    	pkgbody.println("	insert into "+table_org_name+"(");
    	for(int i=0; i<column_name.length; i++){
    		if (i == (column_name.length - 1)){
    			pkgbody.print(column_name[i]);
    		}else{
    			pkgbody.print(column_name[i]+ ",");
    		}
    	}
    	pkgbody.println(")");
    	pkgbody.println("values (");
    	for(int i=0; i<column_name.length; i++){
    		if (i == (column_name.length - 1)){
    			pkgbody.print("p"+column_name[i]);
    		}else{
    			pkgbody.print("p"+column_name[i]+ ",");
    		}
    	}
    	pkgbody.println(");");
    	pkgbody.println("");	
    	pkgbody.println("	rValue := '1';");	
    	pkgbody.println("");
    	pkgbody.println("	EXCEPTION WHEN OTHERS THEN rValue :=  SQLERRM;");
    	pkgbody.println("");	
    	pkgbody.println("END;");
    				  
    	pkgbody.println("");


    	pkgbody.println("	/************************************************** ");
    	pkgbody.println("        NAME : update"+table_name+"Record");
    	pkgbody.println("        DESC : UPDATE");
    	pkgbody.println("        VERSION");
    	pkgbody.println("    **************************************************/");
    	pkgbody.println("    PROCEDURE update"+table_name+"Record");
    	pkgbody.println("(");
    	for(int i=0; i<column_name.length; i++){
    	pkgbody.println("p"+column_name[i]+ "	"+table_org_name+"."+column_name[i]+"%type,");
    	}
    	pkgbody.println("rValue		OUT VARCHAR2");
    	pkgbody.println(") IS");		  
    	pkgbody.println("BEGIN");
    	pkgbody.println("	rValue := '1';");

    	pkgbody.println("		update "+table_org_name+" set");
    	for(int i=0; i<column_name.length; i++){
    		if (i == (column_name.length - 1)){
    			pkgbody.println(column_name[i]+" = "+"p"+column_name[i]);
    		}else{
    			pkgbody.println(column_name[i]+" = "+"p"+column_name[i]+",");
    		}
    	}
    	pkgbody.println("		where    ;");

    	pkgbody.println("");
    	pkgbody.println("		rValue := '1';");	
    	pkgbody.println("		EXCEPTION WHEN OTHERS THEN rValue :=  SQLERRM;");
    	pkgbody.println("");	
    	pkgbody.println("	END;");
    				  
    	pkgbody.println("");

    	pkgbody.println("     /************************************************** ");
    	pkgbody.println("        NAME : delete"+table_name+"Record");
    	pkgbody.println("        DESC : IP 카테고리 코드 Delete");
    	pkgbody.println("        VERSION");
    	pkgbody.println("    **************************************************/");
    	pkgbody.println("    PROCEDURE delete"+table_name+"Record");
    	pkgbody.println("    (pReq_code	 IN     INT_REQ.REQ_CODE%TYPE,");
    	pkgbody.println("     rValue			OUT VARCHAR2");
    	pkgbody.println("		) IS");		  
    	pkgbody.println("	BEGIN");
    	pkgbody.println("");
    	pkgbody.println("	rValue := '1';");	
    	pkgbody.println("		EXCEPTION WHEN OTHERS THEN rValue :=  SQLERRM;");
    	pkgbody.println("");	
    	pkgbody.println("	END;");
    				  
    	pkgbody.println("");
    				  
    	pkgbody.println("END ASIC_"+table_org_name+";");
    	pkgbody.println("/");

    }
    private static void setAct() {
    	act.println("package org.kipa.asic.cad.actions;");

		act.println("import javax.servlet.http.HttpServletRequest;");
		act.println("import javax.servlet.http.HttpServletResponse;");

		act.println("import org.apache.struts.action.ActionForward;");
		act.println("import org.kipa.asic.cad.beans.table.CadEdaToolBean;");
		act.println("import org.kipa.asic.cad.dao.CadEdaToolDB;");
		act.println("import org.kipa.asic.comm.framework.struts.StrutsBasicAction;");
		act.println("import org.kipa.asic.comm.lib.CommandNotFoundException;");
		act.println("import org.kipa.asic.comm.lib.Setter;");


		act.println("/**");
		act.println(" * Class Name : "+table_name+"Action.class");
		act.println(" * File Name : "+table_name+"Action.java");
		act.println(" * <br>");
		act.println(" * IP 카테고리관리 관련 ");
		act.println(" * ");
		act.println(" * @inheritdoc ");
		act.println(" * @author Park Hyuk");
		act.println(" * @version 1.0");
		act.println(" * @modifydate 2004. 11. 15.");
		act.println(" */");
		act.println("public class "+table_name+"Action extends StrutsBasicAction {");
		act.println("	public ActionForward doWork(ExtHttpServletRequest request, HttpServletResponse response)");
		act.println("	throws Exception {");
		act.println("");		
		act.println("	    String forward_url = null;");
		act.println("	    forward_url = \"list\";");
		act.println("");        
		act.println("	    String cmd = getParameter (request, \"cmd\");");
		act.println("");		    
		act.println("	    if (\"\".equals (cmd)) {");
		act.println("int page = request.getParameter(\"page\",1);");
		act.println("int max = request.getParameter(\"max\",10);");
		act.println("String searchtype = request.getParameter(\"searchtype\");");
		act.println("String keyword = request.getParameter(\"keyword\");");
		act.println("String order = request.getParameter (\"order\", \"cam_start_date\");");
		act.println("String ordertype = request.getParameter (\"ordertype\", \"desc\");");
    	
		act.println("String Where=\"\";");

		act.println("if (!(\"\".equals(searchtype)))");
		act.println("	Where = \" where \" + searchtype + \" = '\" + keyword + \"'\";");
		act.println("Where += \" order by \" + order + \" \" + ordertype;");
		
		act.println("	    	"+table_name+"DB db = new "+table_name+"DB();");
		act.println("            request.setAttribute(\"cnt\", db.getRecordsCount(Where));");
		act.println("            request.setAttribute(\"beans\", db.getRecords(Where,page,max));");
		act.println("request.setAttribute(\"page\",\"\"+ page);");
		act.println("request.setAttribute(\"max\",\"\"+ max);");
		act.println("request.setAttribute(\"searchtype\",searchtype);");
		act.println("request.setAttribute(\"keyword\",keyword);");
		act.println("            forward_url = \"list\";");
		act.println("	    }else if (\"writeform\".equals (cmd)) {");
		act.println("	    	forward_url = \"writeform\";");
		act.println("	    }else if (\"view\".equals (cmd)) {");
		act.println("	    	String code = getParameter (request, \"code\");");
		act.println("	    	"+table_name+"DB db = new "+table_name+"DB();");
		act.println("            request.setAttribute(\"beans\", db.getRecord(code));");
		act.println("	    	forward_url = \"view\";");
		act.println("	    }else if (\"update\".equals (cmd)) {");
		act.println("	    	String code = getParameter (request, \"code\");");
		act.println("	    	"+table_name+"DB db = new "+table_name+"DB();");
		act.println("            request.setAttribute(\"beans\", db.getRecord(code));");
		act.println("	    	forward_url = \"update\";");
		act.println("	    }else if (\"modify\".equals (cmd)) {");
		act.println("	    	"+table_name+"Bean bean = new "+table_name+"Bean();");
		act.println("");	    	
		act.println("	    	Setter s = new Setter(bean, request);");
		act.println("	    	s.setProperty(\"*\");");
		act.println("");
		act.println("           "+table_name+"DB db = new "+table_name+"DB();");
		act.println("            db."+table_name+"Update(bean);");
		
		act.println("			request.setAttribute(\"msg\", MessageProperties.getProperties(\"UPDATE_OK\"));");
		act.println("			request.setAttribute(\"next\", \"/itsoc/actions/CadAppMstAction?cmd=view&code=\"+ );");
		act.println("			forward_url = \"msg\";");
		
		act.println("	    }else if (\"save\".equals (cmd)) {");
		act.println("	    	"+table_name+"Bean bean = new "+table_name+"Bean();");
		act.println("");
		act.println("	    	Setter s = new Setter(bean, request);");
		act.println("           s.setProperty(\"*\");");
		act.println("");            
		act.println("           "+table_name+"DB db = new "+table_name+"DB();");
		act.println("           db.setRecord(bean);");
		act.println("");            
		act.println("			request.setAttribute(\"msg\", MessageProperties.getProperties(\"INSERT_OK\"));");
		act.println("			forward_url = \"msg\";");
		act.println("	    }else{");
		act.println("	        String errorMsg = \"action : \" + this.action_name + \"\\ncmd : \" + cmd;");
		act.println("	        throw new CommandNotFoundException(errorMsg);");
		act.println("	    }");
		act.println("");	    
		act.println("	    return mapping.findForward(forward_url);");
		act.println("	}");
		act.println("");
		act.println("}");


    }
    private static String getSetter(int i){
        return   (column_name[i].substring(0,1)).toUpperCase() + column_name[i].substring(1, column_name[i].length());
    }
    
    private void setColumnType(int []column_type){
        type_name = new String[column_type.length];
        for(int i=0; i<column_type.length; i++){
            int column = column_type[i];
            if(column == Types.VARCHAR || column == Types.CHAR || column == Types.LONGVARCHAR || column == Types.VARBINARY){
                type_name[i] = "String";
            }else if(column == Types.DATE || column == Types.TIME  || column == Types.TIMESTAMP){
                type_name[i] = "java.util.Date";
            }else if(column == Types.NUMERIC || column == Types.INTEGER || column == Types.SMALLINT){
                type_name[i] = "long";
            }else if(column == Types.DECIMAL){
                type_name[i] = "float";
            }else{
                type_name[i] = "String";
            }
        } 
    }
    
    private static String getColumnName_String(String[] column_name){
        StringBuffer name = new StringBuffer();
        for (int i = 0; i < column_name.length; i++){
            name.append("\"");
            name.append(column_name[i]);
            name.append("\",");
        }
        
        name.delete(name.length() - 1, name.length());
        return name.toString();
    }
    
    private static String getColumnType_String(String[] column_type){
        StringBuffer name = new StringBuffer();
        for (int i = 0; i < column_type.length; i++){
            name.append("\"");
            name.append(column_type[i]);
            name.append("\",");
        }
        
        name.delete(name.length() - 1, name.length());
        return name.toString();
    }

    private static String getColumnSize_String(int[] column_size){
        StringBuffer name = new StringBuffer();
        for (int i = 0; i < column_size.length; i++){
            name.append(column_size[i]);
            name.append(",");
        }
        
        name.delete(name.length() - 1, name.length());
        return name.toString();
    }
}
