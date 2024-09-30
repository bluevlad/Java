<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<title>WILLBES</title>
</head>
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEARCHSDATE" name="SEARCHSDATE" value="${params.SEARCHSDATE}"/>
<input type="hidden" id="SEARCHEDATE" name="SEARCHEDATE" value="${params.SEARCHEDATE}"/>
<input type="hidden" id="SEARCHRECEIPTTYPE" name="SEARCHRECEIPTTYPE" value="${params.SEARCHRECEIPTTYPE}"/>
<input type="hidden" id="SEARCHPAYMENT" name="SEARCHPAYMENT" value="${params.SEARCHPAYMENT}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
</form>
<script>
    if("${params.returnCode}" == "OVERDATE"){
        alert("접수마감일이 지났습니다.");
        $("#frm").attr("action","<c:url value='/offerer/offererWrite.do' />");
        $("#frm").submit();     
    }
    
    if("${params.returnCode}" == "OVERCNT"){
        alert("해당 모의고사에 접수인원이 다 찼습니다.");
        $("#frm").attr("action","<c:url value='/offerer/offererWrite.do' />");
        $("#frm").submit();     
    }   
</script>