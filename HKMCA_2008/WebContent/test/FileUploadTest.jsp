<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jmf.web.http.MyHttpServletRequest" %>
<%@ page import="jmf.web.multipart.UploadedFile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<META HTTP-EQUIV="Content-type"	CONTENT="text/html;charset=euc-kr" />
<title>Common File Upload Wrapper Test</title>
</head>
<body>
<%
    MyHttpServletRequest req = new MyHttpServletRequest(request);
	req.FileSaveAllTo("d:/tt/u",false);
	UploadedFile uf = req.getFileParameter("file-02");
	if(uf != null ) {
		uf.SaveTo("d:/tt/u",false);
		%> filename : <%=uf.getNewfilename()%> , getOriginalFileName : <%=uf.getOriginalFileName()%> 
		<%
	}
    request.setAttribute("reqw", req);
%>
<h2>POST:multipart/form-data
<table border="1" cellspacing="0" cellpadding="2">
    <form action="FileUploadTest.jsp" method="post" enctype="multipart/form-data">
       <tr>
        <th>text 1-1</th>
        <td><input type="text" name="txt01" value="txt01-1(한글)"></td>
       </tr>
       <tr>
        <th>text 1-2</th>
        <td><input type="text" name="txt01" value="txt01-2(한글)"></td>
       </tr>
        <tr>
        <th>text 1-3</th>
        <td><input type="text" name="txt01" value="txt01-3(한글)"></td>
       </tr>
       <tr>
        <th>text 1-4</th>
        <td><input type="text" name="txt01" value="txt01-4(한글)"></td>
       </tr>       
       <tr>
        <th>text 2</th>
        <td><input type="text" name="txt02" value="txt02(한글)"></td>
       </tr>       
       <tr>
        <th>File 1-1</th>
        <td><input type="file" name="file-01"></td>
       </tr>            
       <tr>
        <th>File 1-2</th>
        <td><input type="file" name="file-01"></td>
       </tr>            
       <tr>
        <th>File 2</th>
        <td><input type="file" name="file-02"></td>
       </tr>            
       <tr>
        <td colspan="2" clign="center"><input type="submit"> <input type="reset"></td>
       </tr>
        
    </form>
</table> 
<h2>POST:
<table border="1" cellspacing="0" cellpadding="2">
    <form action="FileUploadTest.jsp" method="post" >
       <tr>
        <th>text 1-1</th>
        <td><input type="text" name="txt01" value="txt01-1(한글)"></td>
       </tr>
       <tr>
        <th>text 1-2</th>
        <td><input type="text" name="txt01" value="txt01-2(한글)"></td>
       </tr>
       <tr>
        <th>text 1-3</th>
        <td><input type="text" name="txt01" value="txt01-3(한글)"></td>
       </tr>
       <tr>
        <th>text 1-4</th>
        <td><input type="text" name="txt01" value="txt01-4(한글)"></td>
       </tr>
       <tr>
        <th>text 2</th>
        <td><input type="text" name="txt02" value="txt02(한글)"></td>
       </tr>       
       </tr>            
       <tr>
        <td colspan="2" clign="center"><input type="submit"> <input type="reset"></td>
       </tr>
        
    </form>
</table> 
<h2>GET:
<table border="1" cellspacing="0" cellpadding="2">
    <form action="FileUploadTest.jsp" method="get" >
       <tr>
        <th>text 1-1</th>
        <td><input type="text" name="txt01" value="txt01-1(한글)"></td>
       </tr>
       <tr>
        <th>text 1-2</th>
        <td><input type="text" name="txt01" value="txt01-2(한글)"></td>
       </tr>
<tr>
        <th>text 1-3</th>
        <td><input type="text" name="txt01" value="txt01-3(한글)"></td>
       </tr>
       <tr>
        <th>text 1-4</th>
        <td><input type="text" name="txt01" value="txt01-4(한글)"></td>
       </tr>       
       <tr>
        <th>text 2</th>
        <td><input type="text" name="txt02" value="txt02(한글)"></td>
       </tr>       
       </tr>            
       <tr>
        <td colspan="2" clign="center"><input type="submit"> <input type="reset"></td>
       </tr>
        
    </form>
</table> 
<hr>
<table border="1" cellspacing="0" cellpadding="2">
	<tr>
		<td colspan="2">
		<h3>requestScope</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${requestScope}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:if test="${!empty(aKey.value)}"><c:out value="${aKey.value}"/></c:if>]</td>
		</tr>
	</c:forEach>   
	<tr>
		<td colspan="2">
		<h3> parameter info:</h3>
		</td>
	</tr>

	<c:forEach var="aKey" items="${paramValues}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td><c:forEach var="val" items="${aKey.value}"><c:out value="${val}"/></c:forEach></td>
		</tr>
	</c:forEach>    
	<tr>
		<td colspan="2">
		<h3>FileUpload info:</h3>
		</td>
	</tr>

	<c:forEach var="aKey" items="${reqw.parameterMap}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td><c:if test="${!empty(aKey.value)}"><c:forEach var="val" items="${aKey.value}" varStatus="status"><c:out value="${status.count}  : ${val}"/><br /></c:forEach></c:if></td>
		</tr>
	</c:forEach>   
   
</table>    
</body>
</html>