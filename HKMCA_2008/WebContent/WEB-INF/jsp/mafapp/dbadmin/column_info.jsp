<%@ page contentType = "text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<br/>Table Name : <mh:out value="${tabsname}" case="capital"/><br/>
<mh:length var="ColumnCnt" value="${columns}"/>
<form action="<c:url value="${control_action}"/>" name="frmColumn" id="frmColumn" method="post">
<input type="hidden" name="owner" value="<c:out value="${owner}"/>"/>
<input type="hidden" name="tabsname" value="<c:out value="${tabsname}"/>"/>
<input type="hidden" name="cmd" value="<c:out value="${param.cmd}"/>"/>
<div id="divColumnInfo" style="width: 750px; height: 280px; overflow: auto; border: 1px solid Gray;">
<table width="100%" border="0" cellpadding="3" cellspacing="0" class="table">
	<col width="30"/>
	<col width="150"/>
	<col width="20"/>
	<col width="100"/>
	<col width="20"/>
	<col width="50"/>
	<col width="*"/>
	<tr>
		<th colspan="11" class="table_top"></th>
	</tr>
  <tr>
	<th class="th" nowrap>id</th>
    <th class="th" nowrap>Column Name</th>
	<th class="th" nowrap>Pk</th>
    <th class="th" nowrap>Null</th>
    <th class="th" nowrap>Data Type</th>
    <th class="th" nowrap>Length</th>
    <th class="th" nowrap>Default V.</th>
    <th class="th_r">Comments</th>
  </tr>
<c:forEach items="${columns}" var="item">
  <tr class="tr">
		
    <td class="td" align="center" ><c:out value="${item.id}"/></td>
    <td class="td"><mh:out value="${item.name}" case="lower" /></td>
	<td class="td" align="center"><mh:out value="${item.pk}" td="true"/></td>	
	<td class="td" align="center"><mh:out value="${item.nullable}" td="true"/></td>	
    <td class="td"><mh:out value="${item.type}" td="true"/></td>	
    <td class="td" align="right"><mh:out value="${item.dlength}" td="true"/></td>		
    <td class="td"  align="center"><mh:out value="${item.data_default}" td="true"/></td>		
    <td class="td" nowrap><mh:out value="${item.comments}" td="true"/></td>
  </tr>
</c:forEach>
</table>
</div>
</form>