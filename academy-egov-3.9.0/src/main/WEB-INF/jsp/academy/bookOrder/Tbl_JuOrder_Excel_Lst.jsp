<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="application/vnd.ms-excel; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
//******************************MS excel******************************
    // MS excel로 다운로드/실행, filename에 저장될 파일명을 적어준다.
    
    Date date = new Date();
  	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

	String excelName = simpleDate.format(date) + "_parcel service";

    response.setHeader("Content-Disposition","attachment;filename="+excelName+".xls");
    response.setHeader("Content-Description", "JSP Generated Data");
    
    //이걸 풀어주면 열기/저장 선택창이 뜨는 게 아니라 그냥 바로 저장된다.
	//response.setContentType("application/vnd.ms-excel");
%>
<html>
<head>
 <title>고객</title>
 <meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
 <style type="text/css">
  .tbl_grid {border:1px solid #333333;width:1000px;clear:both;line-height:15px;}
  .tbl_grid th {padding:8px 0;border:1px solid #666666;text-align:center;font-size:11px;font-weight:normal;color:#444;background:#dedede;line-height:14px;}
  .tbl_grid td {padding:8px;font-size:11px;color:#444;border:1px solid #666666;text-align:center;background:#ffffff;}
  .tbl_grid .td01 {background:#f2f4f4;}
  .tbl_grid .tleft {text-align:left;padding-right:4px;}
  .tbl_grid .tright {text-align:right;padding-right:4px;} 
 </style>
 
<style>   
.xl24   {mso-number-format:"\@";}   
br      {mso-data-placement:same-cell;}   
</style>
 
</head>
<body>
<table class="tbl_grid">           
 <colgroup>
  <col />
  <col />
  <col />
  <col />
  <col />
  <col />
  <col />
  <col />
  <col />
 </colgroup>
 <thead>    
 <tr>
  <th>수령인</th>
  <th>핸드폰</th>
  <th>집번호</th>
  <th>우편번호</th>
  <th>주소</th>
  <th></th>
  <th>메모</th>
  <th>NO</th>
  <th>교재명</th>
  <th>주문번호</th>
 </tr>
 </thead>
 <tbody>    
 <c:forEach var="result" items="${exe_list}" varStatus="status">
   <tr>
    <td><c:out value="${result.DELIVERS_USERNAME}"/></td>
    <td style=mso-number-format:'\@'><c:out value="${result.DELIVERS_CELLNO}"/></td>
    <td style=mso-number-format:'\@'><c:out value="${result.DELIVERS_TELNO}"/></td>
    <td><c:out value="Z_${result.DELIVERS_ZIPCD}"/></td>
    <td><c:out value="${result.DELIVERS_ADDR}"/></td>
    <td></td>
    <td><c:out value="${result.DELIVERS_MEMO}"/></td>
    <td>
    	<c:if test="${status.index+1 >= 10}">
    		${NO }<c:out value="-999${status.index+1 }"/>
    	</c:if>
    	<c:if test="${status.index+1 < 10}">
    		${NO}<c:out value="-9990${status.index+1 }"/>
    	</c:if>    	
    </td>
    <td>${result.MULTI_BOOK_NM}</td>
    <td>${result.ORDERNO}</td>
   </tr>
 </c:forEach>
</table>
</body>

</html>