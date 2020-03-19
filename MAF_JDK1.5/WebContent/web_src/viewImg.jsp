<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=<fmt:message key='page.charset'/>">
</head>

<BODY bgcolor="CED2DB" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 onload="viewimg();">
<!-- 상단부분 -->
<!-- 상단부분끝 -->
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
    <!-- 내용부분 시작 -->
        <form name="d1">
            <a href="javascript:close();">
            <img name="img1" src="<mh:out value="${CPATH}"/>/pds/org/<%=request.getParameter("img")%>" border="0">
            </a>
        </form>
    <!-- 내용부분 끝 -->
    </td>
  </tr>
</table>
</BODY>
</HTML>
<script language="javascript">
<!--
	function viewimg(){
        imgW=document.d1.img1.width+5;
        //창을 여는 방법에 따라 적절히 더해준다
        imgH=document.d1.img1.height+30;
        //창을 여는 방법에 따라 적절히 더해준다
        window.resizeTo(imgW,imgH)
    }
</script>
