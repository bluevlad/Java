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

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
		
    <p>&nbsp; </p>
	<form name="frm" id="frm" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
	<input type="hidden" id="SEARCHFORM" name="SEARCHFORM" value="${params.SEARCHFORM}"/>
	<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}"/>
	<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
	<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
	<input type="hidden" id="POLL_ID" name="POLL_ID" value="${view.POLL_ID}"/>
    <input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
    
	<table class="table01">
		<tr>
			<th width="160">제목</th>
        	<td><input type="text" id="TITLE" name="TITLE" value="${view.TITLE}" size="60" maxlength="333" style="background:#FFECEC;"/></td>
		</tr>
		<tr>
			<th>기간</th>
			<td>
					시작일 <input type="text" id="START_DT" name="START_DT" value="${view.START_DT}" style="background:#FFECEC;" readonly="readonly"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					종료일 <input type="text" id="END_DT" name="END_DT" value="${view.END_DT}" style="background:#FFECEC;" readonly="readonly"/>
          	</td>
		</tr>
		<tr>
        	<th>공개여부</th>
        	<td>
        		<input type="radio" name="IS_SHOW" value="Y"  <c:if test="${view.IS_SHOW == 'Y' }">checked="checked"</c:if>><span class="txt03">공개</span>
          		<input type="radio" name="IS_SHOW" value="N"  <c:if test="${view.IS_SHOW == 'N' }">checked="checked"</c:if>><span class="txt04">비공개</span>
          	</td>
      </tr>
		<tr>
        	<th>사용여부</th>
        	<td>
        		<input type="radio" name="ISUSE" value="Y"  <c:if test="${view.ISUSE == 'Y' }">checked="checked"</c:if>><span class="txt03">활성</span>
          		<input type="radio" name="ISUSE" value="N"  <c:if test="${view.ISUSE == 'N' }">checked="checked"</c:if>><span class="txt04">비활성</span>
          	</td>
      </tr>
    </table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">저장</a></li>
   		<li><a href="javascript:fn_Delete();">삭제</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 
<script type="text/javascript">
$(document).ready(function(){	
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("START_DT");	
	initDateFicker2("END_DT");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/poll/list.do' />");
	$("#frm").submit();
}

// 등록
function fn_Submit(){
 	if($.trim($("#TITLE").val()) == ""){
 		alert("제목을 입력하세요");
 		$("#TITLE").focus();
        return;
 	}
 	if($.trim($("#START_DT").val()) == ""){
 		alert("시작일을 입력하세요");
 		$("#START_DT").focus();
        return;
 	}	
 	if($.trim($("#END_DT").val()) == ""){
 		alert("종료일을 입력하세요");
 		$("#END_DT").focus();
        return;
 	} 	

	if(confirm("온라인 폴을 변경하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/poll/update.do' />");
		$("#frm").submit();
	}
}

//등록
function fn_Delete(){

	if(confirm("온라인 폴을 삭제하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/poll/delete.do' />");
		$("#frm").submit();
	}
}
</script>
</body>
</html>