<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("searchDate");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function fn_search() {
	$("#form").attr("action", "<c:url value='/manage/offAdvanceReceived.do' />");
	$("#form").submit();
}

function fn_excel() {
	$("#form").attr("action", "<c:url value='/manage/offReceivedExcel.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
	<h2>● 경영관리 > <strong>학원선수금관리</strong></h2>
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">조회일자</th>
            <td>
				<input type="radio" id="LEC" name="LEC" VALUE="D" checked="checked"/> 단과		
				<input type="radio" id="LEC" name="LEC" VALUE="P" /> 종합반(패키지)
            	<input type="text" id="searchDate" name="searchDate" value="${searchDate}" maxlength="8" readonly="readonly"/> 
            	<!-- 
				<input type="button" onclick="fn_search()" value="검색" />
				<input type="button" onclick="fn_excel()" value="Excel" />
            	 -->
            </td>
		</tr>
	</table>
    <!-- //검색 -->
			    <!--버튼-->    
			    <ul class="boardBtns">
			        <li><a href="javascript:fn_excel();">엑셀다운로드</a></li>
			    </ul>
			    <!--//버튼-->
</form>
</div>    

</html>