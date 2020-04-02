<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
	//엔터키 검색
	function fn_checkEnter(){
		$('#SEARCHKEYWORD').keyup(function(e)  {
			if(e.keyCode == 13) {
				goList(1);
			}
		});
		
		$('#pageRow').keyup(function(e)  {
			if(e.keyCode == 13) {
				goList(1);
			}
		});
	}

	//검색
	function goList(page) {
		if(typeof(page) == "undefined") $("#currentPage").val(1);
		else $("#currentPage").val(page);
		
		$('#frm').attr('action','<c:url value="/counsel/presentReqList.do"/>').submit();
	}

	//엑셀
	function to_excel() { //ck 추가 
		$('#frm').attr('action','<c:url value="/counsel/present_req_list_excel.do"/>').submit();
	}
</script>
</head>
<body>

<!--content -->
<div id="content">

	<form id="frm" name="frm" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
	<input type="hidden" id="SCH_DAY" name="SCH_DAY" value="" />

    <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${pageRow}">

	<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
	
      <table class="table01">
          <tr>
            <th width="15%">검색</th>
            <td>            
						<label for="SEARCHCODE"></label>
						<select style="width:200px;"   id="SEARCHCODE" name="SEARCHCODE">
							<option value=""  <c:if test="${params.SEARCHCODE == ''}">selected</c:if>>-설명회 장소 전체-</option>
							<c:forEach items="${codelist}" var="codelist" varStatus="status">
							<option value="${codelist.CODE_VAL}"  <c:if test="${params.SEARCHCODE == codelist.CODE_VAL}">selected</c:if>>${codelist.CODE_NM}</option>
							</c:forEach>
						</select>
						<label for="SEARCHTYPE"></label>
						<select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
							<option value=""  <c:if test="${params.SEARCHTYPE == ''}">selected</c:if>>-전체-</option>
							<option value="USER_ID"  <c:if test="${params.SEARCHTYPE == 'USER_ID'}">selected</c:if>>회원ID</option>
							<option value="USER_NM"  <c:if test="${params.SEARCHTYPE == 'USER_NM'}">selected</c:if>>이름</option>
						</select>
						<label for="SEARCHKEYWORD"></label>
						<input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="20"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
			</td>
		    <th width="15%">화면출력건수</th>
		    <td width="15%">               
	                	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
		                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
          </tr>
      </table>

	<!--버튼-->
	<ul class="boardBtns">
		<li><a href="javascript:to_excel();">엑셀다운로드</a></li>
	</ul>

	<!-- 테이블 -->
	<table class="table02">
		<tr>
			<th>No</th>
			<th>설명회</th>
			<th>이름(ID)</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>신청일시</th>
		</tr>
		<tbody>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="status">
				<tr>
					<td>${totalCount-((currentPage-1)*pageRow)-status.index}</td>
					<td>${list.CODE_NM}</td>
					<td>${list.USER_NM}(${list.USER_ID})</td>
					<td>${list.PHONE_NO}</td>
					<td>${list.EMAIL}</td>
					<td>${list.REG_DT}</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center>
					<td colspan="7">설명회를 신청한 인원이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
          <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
          <!--//테이블--> 
   
	    <!-- paginate-->
		    <c:if test="${not empty list}">
				<c:out value="${pagingHtml}" escapeXml="false" />
			</c:if>
	   <!--//paginate-->

</form>
</div>
<!--//content -->

</body>
</html>