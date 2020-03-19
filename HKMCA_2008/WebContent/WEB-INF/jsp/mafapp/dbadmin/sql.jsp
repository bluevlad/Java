<%@ page contentType = "text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<mh:length var="ColumnCnt" value="${columns}"/>
cnt:[<c:out value="${ColumnCnt}"/>]

<br/><br/>
<table width="100%"  border="0" cellpadding="3" cellspacing="0" class="table">
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
    <td class="td" align="center" ><mh:out value="${item.name}"/></td>
    <td class="td" align="center"><mh:out value="${item.type}"/></td>
    <td class="td" align="center"><mh:out value="${item.uniqueness}" nl2br="true"/></td>    
    
    <td class="td" align="left"><mh:iif test="${item.column_expression != null}" trueValue="${item.column_expression }"  falseValue="${item.column_name }" ></mh:iif>  </td>        
    <td class="td" align="center"><mh:out value="${item.descend}"/></td>    
    <td class="td" align="center"><mh:out value="${item.column_position}"/></td>    
        
    <td class="td" align="center"><mh:out value="${item.lastanalyzed}" nl2br="true"/></td>  
    <td class="td" align="right"><mh:out value="${item.distinctkeys}"/></td>        
    <td class="td" align="right"><mh:out value="${item.numrows}"/></td>     
  </tr>
</c:forEach>
</table>
