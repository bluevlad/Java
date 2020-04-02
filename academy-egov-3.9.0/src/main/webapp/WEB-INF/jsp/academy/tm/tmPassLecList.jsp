<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mt_upg.css' />" >

<script type="text/javascript">
//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$('#searchFrm').attr('action','<c:url value="/tm/tmPassLecList.do"/>').submit();
}

//엑셀
function excel_onclick1() { //ck 추가
	<c:if test="${empty list}">
		alert('엑셀파일로 저장할 데이타가 없습니다.');
		return;
	</c:if>
	$('#searchFrm').attr('action','<c:url value="/tm/tmPassLecListExcel.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#SEARCHTEXT').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

function fn_view(leccode){
	$("#MGNTNO").val(leccode);
	$("#currentPage").val("");
	$("#pageRow").val("");
	$("#searchFrm").attr("action","<c:url value='/tm/tmPassLecStdList.do' />");
	$("#searchFrm").submit();
}
</script>
</head>

<!--content -->
<div id="content">
	   
    <!--테이블-->    
<form id="searchFrm" name="searchFrm" method="post">    
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="MGNTNO" name="MGNTNO" value="" />

<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
	
    <ul class="tabA">
        <li><a href="<c:url value="tmPassList.do"/>?MENU_ID=${MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${MENU_NM}">한눈에보기</a></li>
        <li><a href="<c:url value="tmPassLecList.do"/>?MENU_ID=${MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${MENU_NM}" class="active">상품별보기</a></li>
    </ul>

	<div>※ TM 회원 배정에서 등록된 관리 인원만 노출 됩니다.</div>
    
	<table class="table01">
    	<tr>
        	<th width="10%">수강종료일</th>
          	<td  style="width:60%;">
	            <select name="CATEGORY_CD" id="CATEGORY_CD">
					<option value="">--직종검색--</option>
					<c:forEach items="${kindlist}" var="item" varStatus="loop">
						<option value="${item.CODE}" <c:if test="${params.CATEGORY_CD == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
					</c:forEach>
	            </select>
				&nbsp;&nbsp;
		    	<select name="SUBJECT_PERIOD" id="SUBJECT_PERIOD">
				<option value="" >--강의기간구분--</option>
				<option value="6" <c:if test="${ params.SUBJECT_PERIOD eq '6'}">selected="selected"</c:if>>6개월</option>
		        <option value="12" <c:if test="${ params.SUBJECT_PERIOD eq '12'}">selected="selected"</c:if>>12개월</option>
		        </select>  
				&nbsp;&nbsp;
				<input type="text" class="i_text"  title="검색" id="SEARCHTEXT" name="SEARCHTEXT"  type="text" style="width:150px;"  value="${params.SEARCHTEXT}" onkeypress="fn_checkEnter()">
        		<span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="goList(1)">검색</button></span>
			</td>
        	<td><span class="btn_pack medium" style="vertical-align:middle;align:right;"><button type="button" onclick="excel_onclick1();">Excel</button></span></td>
		</tr>
	</table>
      
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">No</th>
			<th>강의코드</th>
          	<th>직종</th>
			<th>분류</th>
			<th>강의명</th>
			<th>인원/배정</th>
        </tr>
		<c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
			<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
			<td>${list.LECCODE}</td>
			<td>${list.CATEGORY_NM}</a></td>
			<td>${list.SUBJECT_PERIOD}개월</td>
			<td>${list.SUBJECT_TITLE}</td>
			<td><a href="#" onclick="javascript:fn_view('${list.LECCODE}');">${list.TOT}/${list.CNT}</a></td>
		</tr>
		</c:forEach>
		</c:if>

		<c:if test="${empty list}">
	    <tr bgColor=#ffffff align=center> 
			<td colspan="9">표시할 데이터가 없습니다.</td>
		</tr>
		</c:if>	 
	</table>
    <!--//테이블-->
	
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
</form>

</div> 
</html>