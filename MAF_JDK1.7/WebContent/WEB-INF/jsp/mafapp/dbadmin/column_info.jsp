<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
Table Name : <mh:out value="${tabsname}" case="capital"/>
<mh:length var="ColumnCnt" value="${columns}"/>
<form action="<c:url value="${control_action}"/>" name="frmColumn" id="frmColumn" method="post">
<input type="hidden" name="owner" value="<c:out value="${owner}"/>"/>
<input type="hidden" name="tabsname" value="<c:out value="${tabsname}"/>"/>
<input type="hidden" name="cmd" value="<c:out value="${param.cmd}"/>"/>
Package name : <input type="text" name="packageName" value="<c:out value="${packageName}"/>"/>
class name : <input type="text" name="className" value="<c:out value="${className}"/>"/>
<div id="divColumnInfo" style="width: 750px; height: 280px; overflow: auto; border: 1px solid Gray;">
<table width="100%" border="0" cellpadding="3" cellspacing="0" class="table">
	<col width="20"/>
	<col width="20"/>
	<col width="20"/>
	<col width="20"/>
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
  	<th class="th"><input type="checkbox" name="checkboxAll" 
							value="allcode"  class="checkbox" 
							onclick="allChekboxToggle(this,'frmColumn','selColumn')"><br>select</th>  
  	<th class="th"><input type="checkbox" name="checkboxSearchAll" 
							value="allcode"  class="checkbox" 
							onclick="allChekboxToggle(this,'frmColumn','searchColumn')"><br>Search</th>
  	<th class="th"><input type="checkbox" name="checkboxListAll" 
							value="allcode"  class="checkbox" 
							onclick="allChekboxToggle(this,'frmColumn','listColumn')"><br>List</th>
	<th class="th" nowrap>id</th>
    <th class="th" nowrap>Column Name</th>
	<th class="th" nowrap>Pk</th>
    <th class="th" nowrap>Null</th>
    <th class="th" nowrap>Data Type</th>
    <th class="th" nowrap>Length</th>
    <th class="th" nowrap>Default Value</th>
    <th class="th_r">Comments</th>
  </tr>
<c:forEach items="${columns}" var="item">
  <tr class="tr">
		
  	<td class="td" align="center" ><input type="checkbox" name="selColumn" value="<c:out value="${item.name}"/>" 
  		class="checkbox" <c:if test="${item.attr1 == 'T'}" >checked</c:if> /></td>
  	<td class="td" align="center" ><input type="checkbox" name="searchColumn" value="<c:out value="${item.name}"/>" 
  		class="checkbox" <c:if test="${item.search}" >checked</c:if> /></td>
  	<td class="td" align="center" ><input type="checkbox" name="listColumn" value="<c:out value="${item.name}"/>" 
  		class="checkbox" <c:if test="${item.list == true}" >checked</c:if> /></td>
    <td class="td" align="center" ><c:out value="${item.id}"/></td>
    <td class="td"><mh:out value="${item.name}" case="lower" /></td>
	<td class="td" align="center"><mh:out value="${item.pk}" td="true"/></td>	
	<td class="td" align="center"><mh:out value="${item.nullable}" td="true"/></td>	
    <td class="td"><mh:out value="${item.type}" td="true"/></td>	
    <td class="td" align="right"><mh:out value="${item.dlength}" td="true"/></td>		
    <td class="td" ><mh:out value="${item.data_default}" td="true"/></td>		
    <td class="td" nowrap><mh:out value="${item.comments}" td="true"/></td>
  </tr>
</c:forEach>
</table>
</div>
</form>
