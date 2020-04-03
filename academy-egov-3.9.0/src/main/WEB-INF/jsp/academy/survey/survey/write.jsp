<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
</head>
<body>
<!--content -->
<div id="content">

<c:import url="/WEB-INF/views/survey/tab.jsp" />

    <p>&nbsp; </p>
	<form name="frm" id="frm" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
	
	<input type="hidden" id="S_MENU" name="S_MENU" value="${params.S_MENU}" />
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
	<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
	
	<table class="table01">
		<col width="20%"/>
		<col width="80%"/>
	    <tr>
	        <th>제목</th>
	        <td><input type="text" id="SURVEYTITLE" name="SURVEYTITLE" value="" style="width:95%"/></td>
	    </tr>
	    <tr>
	        <th>설문세트</th>
	        <td>
	        	<input type="text" id="SETID" name="SETID" value="" onclick="fn_selSet()" style="width:50px;background:#FFECEC;" readonly="readonly"/>
	        	<input type="text" id="SETTITLE" name="SETTITLE" value="" onclick="fn_selSet()" style="width:400px;background:#FFFFFF;" readonly="readonly"/>
	        </td>
	    </tr>
	    <tr>
	        <th>설문기간</th>
	        <td>
				시작일 <input type="text" id="SURVEY_SDAT" name="SURVEY_SDAT" value="" style="background:#FFECEC;" readonly="readonly"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				종료일 <input type="text" id="SURVEY_EDAT" name="SURVEY_EDAT" value="" style="background:#FFECEC;" readonly="readonly"/>
	        </td>
	    </tr>
	    <tr>
	        <th>설명</th>
	        <td><textarea name="SURVEYDESC" id="SURVEYDESC" style="width:95%" rows="3"></textarea></td>
	    </tr>
	    <tr>
	        <th>사용여부</th>
	        <td>
	        	<select name="ISUSE" id="ISUSE">
	        		<option value="Y" selected>사용</option>
	        		<option value="N">비사용</option>
	        	</select>
	        </td>
	    </tr>
    </table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">저장</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
	</form>
</div>
<!--//content --> 
<script type="text/javascript">
$(document).ready(function(){	
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("SURVEY_SDAT");	
	initDateFicker2("SURVEY_EDAT");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/survey/survey/list.do' />");
	$("#frm").submit();
}

//등록
function fn_Submit(){
	if($.trim($("#SURVEYTITLE").val()) == ""){
		alert("제목을 입력하세요");
		$("#SURVEYTITLE").focus();
     return;
	}

	if($.trim($("#SETID").val()) == ""){
		alert("설문세트를 선택하세요");
     return;
	}

	if(confirm("설문 내용을 저장하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/survey/survey/insert.do' />");
		$("#frm").submit();
	}
}

function fn_selSet() {
	window.open('<c:url value="/survey/survey/setList.pop"/>', '_survey', 'location=no,resizable=no,width=800,height=600,top=0,left=0,location=no,scrollbars=yes');
}
</script>
</body>
</html>