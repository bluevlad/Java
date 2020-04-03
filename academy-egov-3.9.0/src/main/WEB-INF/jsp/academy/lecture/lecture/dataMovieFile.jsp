<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>

<script type="text/javascript">
	if($.trim("${params.RETURNDATA}") == "Y"){
		$(opener.document).find("#"+ "${params.LOWNUM}").find("input[name='MOVIE_DATA_FILENAME']").val("${params.ATTACH_FILE}");
		$(opener.document).find("#"+ "${params.LOWNUM}").find("#FILETEXT").html("${params.ATTACH_FILE}");
		self.close();
	}
</script>

</head>
<body style="overflow-x:hidden">
<!--content -->
<div id="content_pop" style="width:480px;padding-left:10px;">

<h2>● <strong>파일첨부</strong></h2>
	
	<div>
		<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="">
			<input type="hidden" id="LOWNUM" name="LOWNUM" value="${params.LOWNUM}" />
			<input type="hidden" id="BLECCODE" name="BLECCODE" value="${params.BLECCODE}" />
			<input type="file" name="ATTACH_FILE" value="" size="60"/>
		</form>

		<!--버튼-->
		<ul class="boardBtns">
			<li><a href="javascript:fn_Save();">추가</a></li>
			<li><a href="javascript:self.close();">닫기</a></li>
		</ul>
		<!--//버튼-->
	</div>
</div>

<script type="text/javascript">
function fn_Save(){
	$("#frm").attr("action", "<c:url value='/lecture/dataMovieFileSave.pop'/>");
	$("#frm").submit();
}
</script>
</body>
</html>