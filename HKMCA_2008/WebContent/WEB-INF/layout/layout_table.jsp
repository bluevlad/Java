<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
<jsp:include  page="/WEB-INF/layout/common/commonHead.jsp" flush="true"/> 

</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0"  >
<div id="div_r1">
	<jsp:include  page="/WEB-INF/layout/common/menu_top.jsp" flush="true"/> 
</div>

<table border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed;">
    <tr>
    <td valign="top" width="180"><div id="div_leftMenu"><jsp:include page="/WEB-INF/layout/common/menu_left.jsp" flush="true"/></div></td>
    <td valign="top">
        <div id="div_contents_container">
			<div id="div_title_container">
				<div class="title"><mh:out value="${JMF_INFO.title }"/></div>
				<c:import url="/WEB-INF/layout/common/st02_sub_navi.jsp">
           			<c:param name="mid" value="${content.mid}"/>
             	</c:import>
             </div>
             <div id="div_contents"><c:catch var="error">
                    <c:import url="${MAF_INFO.file}" />
             </c:catch>
             <c:if test="${!empty error}">
                <hr/>Error:<mh:out value="${error}" nl2br="true"/><hr/>
             </c:if>
             </div>
		</div>
       </td>
      </tr>
</table>

<div id="div_r3" class="clear"></div>
</body>
</html>