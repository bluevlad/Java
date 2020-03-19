<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<table width='100%' border='0' cellspacing='0' cellpadding='0'>
    <tr height='26'>
        <td>
            <select name='site' onChange='javascript:doChange(this);'>
			<c:forEach var="item" items="${sites}">
                <option value='<c:out value="${item.site}"/>' 
                	<c:if test="${item.site == site}">selected</c:if> ><c:out value="${item.title}"/></option>
			</c:forEach>
            </select>

        </td>
    </tr>
</table>
<table width="100%" height='550' border="0" cellspacing="0" cellpadding="0">
<tr >
    <td width='250' height="550">
       <iframe src='<c:url value="${control_action}">
                    <c:param name="cmd" value="tree"/>
                    <c:param name="site" value="${site}"/></c:url>' name="frmTree" id="frmTree" width="250" height="100%" marginwidth="0" marginheight="0" scrolling="no"></iframe>
    </td>
    <td  height="550">
       <iframe src='<c:url value="${control_action}" >
                    <c:param name="cmd" value="edit"/>
                    <c:param name="site" value="${site}"/></c:url>' name="frmMenuEdit" id="frmMenuEdit" width="550" height="100%" marginwidth="0" marginheight="0"></iframe>
    </td>
</tr>
</table>


<script language='javascript'>
function doChange (obj)
{
    document.location.href = '<c:url value="${control_action}"><c:param name="site" value=""/></c:url>' + obj.value;
}
</script>

