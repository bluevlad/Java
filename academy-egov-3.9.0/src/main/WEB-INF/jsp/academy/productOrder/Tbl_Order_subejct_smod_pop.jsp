<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
var USER_ID = "${searchMap.USER_ID}";

var orderstatuscode = "${searchMap.orderstatuscode}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	if(USER_ID != ""){
		getSubCODE('CLASSSERIESCODE', '<c:url value="/productOrder/subCode.do"/>', USER_ID);
	}
}

//엔터키 검색
function fn_checkEnter(){
	 $('#search_keyword').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//검색
function goList(page) {	
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/pop_subject_mod.pop"/>').submit();
}

//수강신청하기 클릭
function put_leccode(LECCODE,SUBJECT_NM) {	
	window.opener.searchFrm.NEW_LECTURE_NO_<c:out value="${searchMap.Num}"/>.value=LECCODE;
	window.opener.document.getElementById("SUBJECT_NM_<c:out value='${searchMap.Num}'/>").innerHTML = SUBJECT_NM;
	window.self.close();
}
</script>
</head>
  <!--content -->
  <div>
  
    <form id="searchFrm" name="searchFrm" method="post">

	<input type="hidden" id="USER_ID" name="USER_ID" value="${searchMap.USER_ID}">	
	
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
	
	<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
	<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
	<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
	<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
	<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
	<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />
	
       
	<p style="text-align:left;">&nbsp;<span>총${totalCount}건</span></p>
    
    <table  style="width:100%;">
	    <tr>
			<td>
			    <!--테이블-->
			     <table class="table02" style="width:100%;">
			        <tr>
						<th>직종</th>
						<th>학습형태</th>
						<th>과목</th>
						<th>강의명</th>
						<th>교수명</th>
						<th>구분</th>
						<th>선택</th>
			        </tr>
			         <c:if test="${not empty list}">
			          <c:forEach items="${list}" var="list" varStatus="status">
			             <tr>
							<td>${list.CATEGORY_NM}</td>
							<td>${list.LEARNING_NM}</td>
							<td>${list.SUBJECT_NM}</td>
							<td style="text-align:left;">${list.SUBJECT_TITLE}<c:if test="${list.MYLEC_YN == 'Y'}"><span style="color: red;">(선택됨)</span></c:if></td>
				            <td>${list.SUBJECT_TEACHER_NM}</td>
				            <td>${list.GUBUN_NM}</td>
				            <td>
<%-- 				            	<c:if test="${list.MYLEC_YN == 'N'}"> --%>
				            		<input type="button" onclick="javascript:put_leccode('${list.MST_LECCODE}', '${list.SUBJECT_TITLE}')" value="선택" />
<%-- 				            	</c:if> --%>
				            </td>
				        </tr>
			      </c:forEach>
					</c:if>
					<c:if test="${empty list}">
					         <tr bgColor=#ffffff align=center> 
						<td colspan="9">데이터가 존재하지 않습니다.</td>
					</tr>
					</c:if>	 
			     </table>
			     <!--//테이블--> 
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
	     
		     </td>
	     </tr>
     </table>
</form>
</div>
</html>