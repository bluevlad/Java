<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
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
	<input type="hidden" name="SETID" id="SETID"  value=""/>
    
	<table class="table01">
		<col width="20%"/>
		<col width="80%"/>
	    <tr>
	        <th>설문세트명</th>
	        <td><input type="text" id="SETTITLE" name="SETTITLE" value="" style="width:95%"/></td>
	    </tr>
	    <tr>
	        <th>설문세트설명</th>
	        <td><textarea name="SETDESC" id="SETDESC" style="width:95%" rows="3"></textarea></td>
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
   		<li><a href="javascript:fn_Submit();">등록</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
	</form>
</div>
<!--//content --> 
<script type="text/javascript">
// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/survey/set/list.do' />");
	$("#frm").submit();
}

//등록
function fn_Submit(){
	if($.trim($("#SETTITLE").val()) == ""){
		alert("제목을 입력하세요");
		$("#SETTITLE").focus();
     return;
	}

	if(confirm("설문세트를 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/survey/set/insert.do' />");
		$("#frm").submit();
	}
}
</script>
</body>
</html>