<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
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
	
	$('#searchFrm').attr('action','<c:url value="/LecEventMng/eventMgtList.do"/>').submit();
}

//등록
function fn_write() {
	$('#searchFrm').attr('action','<c:url value="/LecEventMng/eventWrite.do"/>').submit();
}

// 상세
function fn_view(EVENTNO) {
	$("#EVENT_NO").val(EVENTNO);
	$('#searchFrm').attr('action','<c:url value="/LecEventMng/eventDetail.do"/>').submit();
}

//결과보기
function fn_popup(EVENT_NO) {
	window.open('<c:url value="http://localhost/event/movie/event_lecture_view.html"/>?EVENT_NO='+EVENT_NO, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=800');
}

function fn_lecture(EVENT_NO) {
	var url = "<c:url value='/LecEventMng/lectureList.do'/>?EVENT_NO="+EVENT_NO+"&TOP_MENU_ID=";
	url = url + "${params.TOP_MENU_ID}&MENU_ID=${params.MENU_ID}&MENUTYPE=${params.MENUTYPE}&L_MENU_NM=";
	url = url + "${params.L_MENU_NM}+&MENU_NM=+${params.MENU_NM}";
	document.location.href = url;
}
</script>
</head>

<!--content -->
<div id="content">
	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
        
    <!--테이블-->    
	<form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
	<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="" />
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
	<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<table class="table01">
		<tr>
			<th width="10%">검색</th>
			<td>
				<label for="SEARCHTYPE"></label>
				<select style="width:80px;" id="SEARCHTYPE" name="SEARCHTYPE">
				<option value="TITLE"  <c:if test="${params.SEARCHTYPE == 'TITLE'}">selected</c:if>>제목</option>
				<option value="CONTENTS"  <c:if test="${params.SEARCHTYPE == 'CONTENTS'}">selected</c:if>>내용</option>
				</select>
				<label for="SEARCHKEYWORD"></label>
				<input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="40"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
			</td>
		  	<th width="15%">화면출력건수</th>
		  	<td width="20%">
            	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
                <input name="textfield3" type="button" id="textfield3" value="검색" onclick="goList(1)"  >
			</td>
		</tr>
	</table>           
    
    <ul class="boardBtns">
		<li><a href="javascript:fn_write();">등록</a></li>
    </ul>
    
    <p>Total: ${totalCount} | page: ${currentPage} / ${totalPage}</p>
    
	<table class="table02">
		<tr>
        	<th width="60">번호</th>
			<th width="90">등록일</th>
        	<th width="90">시작일</th>
        	<th width="90">종료일</th>
        	<th>제목</th>
		</tr>
      	<c:if test="${not empty list}">
	    <c:forEach var="item" items="${list}" varStatus="status">
		<tr>
			<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		    <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
		    <td>${item.START_DATE}</td>
		    <td>${item.END_DATE}</td>
		    <td><a href="javascript:fn_view('${item.EVENT_NO}')">${item.TITLE}</a></td>
		</tr>
	    </c:forEach>
	    </c:if>
      
		<c:if test="${empty list}">
		<tr bgColor=#ffffff align=center> 
			<td colspan="11">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>
	</table>   
    
	<!-- paginate-->
	<c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
    
    <ul class="boardBtns">
		<li><a href="javascript:fn_write();">등록</a></li>
    </ul>
    
	</form>
</div>
  <!--//content -->