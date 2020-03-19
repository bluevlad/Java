<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<c:if test="${!empty fileinfo}">
<br><br>
<style>
    .excel_cmt {
        background:#d0d0d0 !important; 
        text-align:center;
        font-weight:bold;
    }
</style>
<strong># Excel File Infomation</strong>
<div style="padding-left:15px;width:740px;height:300px;overflow:auto">
<table cellspacing="0" cellpadding="2" class="excel" >
    <thead>

        <tr>
            <td class="excel_cmt" nowrap>Column</td>
            <c:forEach var="item" items="${fileinfo.info.fieldsList}">
                <th nowrap><mh:out value="${item.title}" td="true" /></th>
            </c:forEach>
        </tr>
        <tr>
            <td class="excel_cmt" nowrap>Description</td>
            <c:forEach var="item" items="${fileinfo.info.fieldsList}">
                <th nowrap><mh:out value="${item.desc}" td="true" /></th>
            </c:forEach>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td class="excel_cmt" nowrap>Sample Value</td>
            <c:forEach var="item" items="${fileinfo.info.fieldsList}">
                <td align="center"><mh:out value="${item.sample}" td="true"  /></td>
            </c:forEach>
        </tr>
    </tbody>
</table>
</div>
</c:if>