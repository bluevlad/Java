<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />	
<script type="text/javascript"  src="<c:url value="/resources/js/eduhome/common.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<script type="text/javascript">
//등록
function fn_Add() {
    if ($('#SUBJECT_CD').val() == "") {
        alert('과목을 먼저 선택해주세요.');
        return;
    }
    $('#frm').attr('action','<c:url value="/mouigosa/kindSubjectInsert.do"/>').submit();
}
function fn_Submit() {
    $('#frm').attr('action','<c:url value="/mouigosa/kindSubjectUpdate.do"/>').submit();
}
function fn_Delete(id) {
	$('#ID').val(id);
    $('#frm').attr('action','<c:url value="/mouigosa/kindSubjectDelete.do"/>').submit();
}
</script>
</head>

<body>
<form id="frm" name="frm" method="get">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
<input type="hidden" id="CLASSCODE" name="CLASSCODE" value="${params.CLASSCODE}"/>
<input type="hidden" id="CLASSSERIESCODE" name="CLASSSERIESCODE" value="${params.CLASSSERIESCODE}"/>
<input type="hidden" id="ID" name="ID" value=""/>

<!--테이블-->
<table class="table04" style="font-size: 9pt; width:600px">
	<col width="10%">
    <col width="">
    <col width="">
    <col width="">
    <thead>
    <tr>
    	<th scope="col">순번</th>
        <th scope="col">과목명</th>
        <th scope="col">필수여부</th>
        <th scope="col">*</th>
	</tr>
    </thead>
    <tbody>
	<c:if test="${not empty list}">
    <c:forEach items="${list}" var="list" varStatus="status">
    <input type="hidden" id="V_ID" name="V_ID" value="${list.ID}"/>
    <tr>
    	<td><input type="text" id="${list.ID}_OD" name="${list.ID}_OD" value="${list.SUBJECTORDER}" style="width:30px;"/></td>
        <td>${list.SUBJECT_NM}</td>
        <td>
        	<select id="${list.ID}_DIV" name="${list.ID}_DIV">
            <option value="1" <c:if test="${list.SUBJECTTYPEDIVISION == '1'}">selected</c:if>>필수</option>
            <option value="2" <c:if test="${list.SUBJECTTYPEDIVISION == '2'}">selected</c:if>>선택</option>
            </select>
        </td>
        <td align="center"><a href="javascript:fn_Delete('${list.ID}');">[삭제]</a></td>
	</tr>
    </c:forEach>
	<!--버튼-->
	<li><a href="javascript:fn_Submit();">[저장]</a></li>
	<!--//버튼-->
    </c:if>
	<c:if test="${empty list}">
    <tr bgColor=#ffffff align=center>
    	<td colspan="5">데이터가 존재하지 않습니다.</td>
	</tr>
    </c:if>
	<tr>
    	<td><input type="text" id="SUBJECTORDER" name="SUBJECTORDER" value="" style="width:30px;"/></td>
        <td>과목코드 : <input type="text" id="SUBJECT_CD" name="SUBJECT_CD" class="i_text" value="" style="width:100px;" /></td>
        <td>
        	<select id="SUBJECTTYPEDIVISION" name="SUBJECTTYPEDIVISION">
            <option value="1" selected>필수</option>
            <option value="2">선택</option>
            </select>
        </td>
        <td><a href="javascript:fn_Add();">새로등록</a></td>
	</tr>
    </tbody>
</table>
<!--//테이블-->
</form>

</body>
</html>