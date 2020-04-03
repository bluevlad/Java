<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
//숫자 입력 폼
function chk(obj){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {          
			$('#pageRow').val("");      
			$('#pageRow').focus();         
			return;       
			}       
		else {          
			if (val < 1) {             
				$('#pageRow').val("");          
				$('#pageRow').focus();            
				return;          
				}       
			}    
		}
}

//엔터키 검색
function fn_checkEnter(){
	$('#searchText').keyup(function(e)  {
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
//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		goList(1);
		return;
	}
	if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#pageRow").val("${params.pageRow}");
    }
}

function fn_IsNumber(input) {
    var chars = "0123456789";
    for (var inx = 0; inx < input.value.length; inx++) {
        if (chars.indexOf(input.value.charAt(inx)) == -1)
            return false;
     }
     return true;
}

function goList(page) {
//	alert("준비중 입니다.");
	var searchCategory = $("#searchCategory").val();  
	var searchKind = $("#searchKind").val();
	var searchText = $("#searchText").val();

	$("#SEARCHCATEGORY").val(searchCategory);
	$("#SEARCHKIND").val(searchKind);
	$("#SEARCHTEXT").val(searchText);
	
	
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#form").attr("action", "<c:url value='/CoopBoardMng/boardList.do' />");
	$("#form").submit();
}

function goBoardView(boardSeq){
	$("#COOP_BOARD_SEQ").val(boardSeq);
	$("#form").attr("action", "<c:url value='/CoopBoardMng/boardModify.do' />");
	$("#form").submit();
	
}

function fn_Write(){
	$("#form").attr("action", "<c:url value='/CoopBoardMng/boardWrite.do' />");
	$("#form").submit();
}

function fn_Rank(){
	$("#form").attr("action", "<c:url value='/CoopBoardMng/updateCoopRank.do' />");
	$("#form").submit();
}
</script>
</head>

  <!--content -->
  <div id="content">
	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

<form id="form" name="form" method="post" >
	<input type="hidden" id="COOP_BOARD_SEQ" name="COOP_BOARD_SEQ" value="" />

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
	
	<!--테이블-->
		<table class="table01">
		<tr>
            <th width="15%">검색</th>
			<td>
					<select id="SEARCH_COOP_AREA" name="SEARCH_COOP_AREA" style="width:120px;">
						<option value="">지역선택</option>
						<c:forEach items="${codeAreaList}"  var="area_list">
						<option value="${area_list.CODE_CD}" <c:if test="${params.SEARCH_COOP_AREA == area_list.CODE_CD}">selected</c:if>>${area_list.CODE_NM }</option>
						</c:forEach>
		  			</select>
					<select id="SEARCH_COOP_CATE" name="SEARCH_COOP_CATE" style="width:120px;">
						<option value="">의료분과</option>
						<c:forEach items="${codeHsptList}"  var="hspt_list">
						<option value="${hspt_list.CODE_CD}" <c:if test="${params.SEARCH_COOP_CATE == hspt_list.CODE_CD}">selected</c:if>>${hspt_list.CODE_NM }</option>
						</c:forEach>
		  			</select>
					<%-- <select style="width:100px;" name="searchCategory" id="searchCategory">
						<option value="" >전체</option>
						<c:forEach items="${rankList}"  var="data" varStatus="status" >
							 <c:set var="vChecked">
									   <c:choose>
									    <c:when test="${data.CODE == params.SEARCHCATEGORY}">selected="selected"</c:when>
									    <c:otherwise></c:otherwise>
									   </c:choose>
							 </c:set>
							<option value="${data.CODE}"  <c:out value='${vChecked}'/> >${data.NAME }</option>
						</c:forEach>
					</select> --%>
					<select style="width:100px;" name="searchKind" id="searchKind">
						<option value="ALL" <c:if test="${params.SEARCHKIND == 'ALL' }">selected="selected"</c:if>>전체</option>
						<option value="SEARCHSUBJECT" <c:if test="${params.SEARCHKIND == 'SEARCHSUBJECT' }">selected="selected"</c:if>>제목</option>
						<option value="SEARCHNAME" <c:if test="${params.SEARCHKIND == 'SEARCHNAME' }">selected="selected"</c:if>>병원명</option>
						<option value="SEARCHCONTENT" <c:if test="${params.SEARCHKIND == 'SEARCHCONTENT' }">selected="selected"</c:if>>내용</option>
					</select>
	            	<input class="i_text" title="레이블 텍스트" type="text" name="searchText" id="searchText" size="35" value="${params.SEARCHTEXT}" onKeyPress="fn_checkEnter()" />
	                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
		</tr>
     	</table>
     	
     <!--//테이블-->
		<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
		<!--버튼-->
		 <ul class="boardBtns">
	   	  <li><a href="javascript:fn_Rank();">순서저장</a></li>
	   	  <li><a href="javascript:fn_Write();">등록</a></li>
	    </ul>
		<!--//버튼-->
		<table class="table02">
			<tr>
				<th scope="col" width="5%">Rank</th>
				<th scope="col" width="5%">No</th>
				<th scope="col" width="10%">지역</th>
				<th scope="col">제목</th>
				<th scope="col" width="15%">병원명</th>
				<th scope="col" width="7%">작성일</th>
				<th scope="col" width="5%">조회수</th>
				<th scope="col" width="5%">추천여부</th>
				<th scope="col" width="5%">공지여부</th>
				<th scope="col" width="5%">공개여부</th>
			</tr>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="data" varStatus="status">
				<input type="hidden" name="v_coop_seq" id="v_coop_seq"  value="${data.COOP_BOARD_SEQ}" />
		        <tr>
					<td><input class="i_text" title="순서" type="text" name="v_coop_rank_${data.COOP_BOARD_SEQ}" id="v_coop_rank_${data.COOP_BOARD_SEQ}"  style="width:20px;" value="${data.COOP_RANK}" /></td>
					<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		            <td>${data.COOP_AREA_NM}</td>
					<td style="text-align:left; padding-left:10px;" >
						<a href='javascript:goBoardView("${data.COOP_BOARD_SEQ}" );'>
	            			<c:if test="${data.NOTICE_TOP_YN == 'Y'}">
	            				<strong style="color:black;"><c:out value="${data.SUBJECT}"/></strong>
	            			</c:if>
	            			<c:if test="${data.NOTICE_TOP_YN == 'N'}">
   						       <c:out value="${data.SUBJECT}"/> 			
		            		</c:if>
		            	</a>
		            </td>
					<td><c:out value="${data.CREATENAME}"/></td>
					<td>${fn:substring(data.REG_DT, 0, 10)}</td>
					<td><fmt:formatNumber value="${data.HITS}" type="number"/></td>
					<td>${data.RECOMMEND}</td>
					<td>${data.NOTICE_TOP_YN}</td>
					<td>${data.OPEN_YN}</td>
		        </tr>
				</c:forEach>
			
			</c:if>
			<c:if test="${empty list}">
	            <tr bgColor=#ffffff align=center> 
					<td colspan="10">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	
       </table>
</form>
		<!--버튼-->
		 <ul class="boardBtns">
	   	  <li><a href="javascript:fn_Rank();">순서저장</a></li>
	   	  <li><a href="javascript:fn_Write();">등록</a></li>
	    </ul>
		<!--//버튼-->
	<!--paging  -->
	<c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
    <!-- //paging  -->
</div>
<!--//content --> 

</html>