<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<table width='100%' border='0' cellspacing='0' cellpadding='0'>
    <tr height='26'>
        <td>
            <iframe src='<c:url value="${control_action}">
                    <c:param name="cmd" value="head"/>
                    <c:param name="site" value="${site}"/></c:url>' name="frmTree" id="frmTree" width="250" height="100%" marginwidth="0" marginheight="0" scrolling="no"></iframe>
        </td>
    </tr>
</table>
<table width="100%" height='550' border="0" cellspacing="0" cellpadding="0">
    <col width="250"/>
    <col width="*"/>
<tr >
    <td  height="550">
       <iframe src='<c:url value="${control_action}">
                    <c:param name="cmd" value="tree"/>
                    <c:param name="site" value="${site}"/></c:url>' name="frmTree" id="frmTree" width="250" height="100%" marginwidth="0" marginheight="0" scrolling="no"></iframe>
    </td>
    <td height="550">
       <iframe src='<c:url value="${control_action}" >
                    <c:param name="cmd" value="content"/>
                    <c:param name="site" value="${site}"/></c:url>' name="frmMenuEdit" id="frmMenuEdit" width="550" height="100%" marginwidth="0" marginheight="0"></iframe>
    </td>
</tr>
</table>