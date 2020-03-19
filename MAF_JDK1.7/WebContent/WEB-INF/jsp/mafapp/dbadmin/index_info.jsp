<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
Table Name : ${tabsname}
<c:set var="ColumnCnt" value="${fn:length(columns)}"/>
<table border="0" cellpadding="3" cellspacing="0" class="table">
	<tr>
		<th colspan="10" class="table_top"></th>
	</tr>
  <tr>
    <th class="th" >Index Name</th>
    <th class="th" >Type</th>
	<th class="th" >Uniqueness</th>
    <th class="th" width="50">Column Name</th>
    <th class="th">Order</th>
    <th class="th">Position</th>
    <th class="th" >Last Analyzed</th>
    <th class="th" width="20">Distinct Keys</th>
    <th class="th_r" width="20">Num Rows</th>
  </tr>
<c:forEach items="${index}" var="item">
  <tr class="tr">
    <td class="td" align="center" >${item.name}</td>
    <td class="td" align="center">${mhtml:Capitailize(item.type)}</td>
	<td class="td" align="center">${mhtml:null2nbsp(mhtml:Capitailize(item.uniqueness))}</td>	
	
	<td class="td" align="left">${(item.column_expression != null)?item.column_expression:item.column_name}</td>		
	<td class="td" align="center">${item.descend}</td>	
	<td class="td" align="center">${item.column_position}</td>	
		
	<td class="td" align="center">${mhtml:null2nbsp(item.lastanalyzed)}</td>	
	<td class="td" align="right">${item.distinctkeys}</td>		
    <td class="td" align="right">${item.numrows}</td>		
  </tr>
</c:forEach>
</table>
