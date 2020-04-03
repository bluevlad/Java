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
    $('#frm').attr('action','<c:url value="/mouigosa/areaInsert.do"/>').submit();
}
function fn_Submit() {
    $('#frm').attr('action','<c:url value="/mouigosa/areaUpdate.do"/>').submit();
}
</script>
</head>

<body>
<form id="frm" name="frm" method="get">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
<input type="hidden" id="SUBJECT_CD" name="SUBJECT_CD" value="${params.SUBJECT_CD}"/>

<!--테이블-->
<table class="table04" style="font-size: 9pt; width:600px">
	<col width="5%">
    <col width="">
    <col width="">
    <col width="">
    <thead>
    <tr>
    	<th scope="col">No</th>
        <th scope="col">영역명</th>
        <th scope="col">영역순번</th>
        <th scope="col">사용여부</th>
	</tr>
    </thead>
    <tbody>
	<c:if test="${not empty list}">
    <c:forEach items="${list}" var="list" varStatus="status">
    <input type="hidden" id="V_ID" name="V_ID" value="${list.ID}"/></td>
    <tr>
    	<td>${status.index+1}</td>
        <td><input type="text" id="${list.ID}_SUBJECTAREA" name="${list.ID}_SUBJECTAREA" class="i_text" value="${list.QUESTIONRANGENAME}" style="width:250px;" /></td>
        <td><input type="text" id="${list.ID}AREAORDER" name="${list.ID}_AREAORDER" class="i_text" value="${list.AREAORDER}" style="width:30px;" /></td>
        <td>
        	<select id="${list.ID}_USEFLAG" name="${list.ID}_USEFLAG">
            <option value="0" <c:if test="${list.USEFLAG == '0'}">selected</c:if>>활성</option>
            <option value="1" <c:if test="${list.USEFLAG == '1'}">selected</c:if>>비활성</option>
            </select>
		</td>
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
    	<td>*</td>
        <td><input type="text" id="SUBJECTAREA" name="SUBJECTAREA" class="i_text" value="" style="width:250px;" /></td>
        <td><input type="text" id="AREAORDER" name="AREAORDER" class="i_text" value="" style="width:30px;" /></td>
        <td><a href="javascript:fn_Add();">새로등록</a></td>
	</tr>
    </tbody>
</table>
<!--//테이블-->
</form>

</body>
</html>