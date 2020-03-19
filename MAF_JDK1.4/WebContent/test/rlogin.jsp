<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@page import="hddms.main.*" %>    
<%@page import="java.net.*" %>    
<%@page import="maf.web.util.*" %>   
<%
    String userid = request.getParameter("userid");
    if(userid == null) {
     userid="2822131";   
    }
    String encode = EnDeCrypt.encode(userid);
    String renc = null;
   renc = URLEncoder.encode(encode);
   // renc = encode;
%>
encode = <%=encode%>, renc=<%=renc%>,
ttt=<%=HTMLUtil.rawURLEncode(renc)%>,
js  = <%=JavaScriptUtils.javaScriptEscape(renc)%>,
decode = <%=EnDeCrypt.decode(encode)%>
<form action="" method="post">
    <label> login : <input type="text" name="userid" value="<%=userid%>"/></label>
    <input type = "submit"/>
</form>


<form action='<c:url value="/rlogin.do"/>' method="get">
    <label> login : <input type="text" name="userid" value="<%=renc%>"/></label>
    <input type="text" name="ccc" value="<%=renc%>"/>
    <input type = "submit"/>
</form>

<a href='<c:url value="/rlogin.do"/>?userid=<%=renc%>'>k login</a>
<a href='<c:url value="/rlogin.do"/>?ccc=<%=renc%>'>ccc login</a>
<A 
href="javascript:open_window('win', 'rlogin_redir.jsp?userid=<%=userid%>', 0, 0, 980, 570, 0, 0, 0, 1, 1)"><STRONG>Link 
to the KCA</STRONG></A>

<script>
function open_window(name, url, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable)
    {
      toolbar_str = toolbar ? 'yes' : 'no';
      menubar_str = menubar ? 'yes' : 'no';
      statusbar_str = statusbar ? 'yes' : 'no';
      scrollbar_str = scrollbar ? 'yes' : 'no';
      resizable_str = resizable ? 'yes' : 'no';
      window.open(url, name, 'left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str+',resizable='+resizable_str);
    }
    
  
</script>