<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		fn_Search();
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

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/eventManagement/eventMgtList.do"/>').submit();
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
			<select name="SEARCHCATEGORY" id="SEARCHCATEGORY">
				<option value="">전체</option>
				<c:if test="${ MENUTYPE eq 'FM_ROOT' }">
					<option value="H" <c:if test="${params.SEARCHCATEGORY eq 'H' }">selected="selected"</c:if>>Hub</option>
				</c:if>
				<c:forEach items="${kindlist}" var="item" varStatus="loop">
					<option value="${item.CODE}" <c:if test="${params.SEARCHCATEGORY eq item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
				</c:forEach>
			</select>
			
			<label for="SEARCHTYPE"></label>
			<select style="width:80px;" id="SEARCHTYPE" name="SEARCHTYPE">
				<option value="TITLE"  <c:if test="${params.SEARCHTYPE == 'TITLE'}">selected</c:if>>제목</option>
				<option value="CONTENTS_TEXT"  <c:if test="${params.SEARCHTYPE == 'CONTENTS_TEXT'}">selected</c:if>>내용</option>
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
        <th width="60">
			<!-- <input id="allCheck"  value="" type="checkbox" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />  -->
			번호
		</th>
        <th width="90">등록일</th>
        <th width="100">직렬</th>
        <th width="100">썸네일</th>
        <th>제목</th>
        <th width="90">시작일</th>
        <th width="90">종료일</th>
        <th width="60">적용여부</th>
        <th width="60">미리보기</th>
        <th width="70">강의 목록</th>
      </tr>
      	<c:if test="${not empty list}">
	      <c:forEach var="item" items="${list}" varStatus="status">
		      <tr>
		        <td>
		        	<!-- <input type="checkbox" name="DEL_ARR" value="${item.SEQ},${item.BANNER_IMAGE},${item.BANNER_THUMBNAIL_IMAGE}"/> -->
		        	${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		        <td>${item.UPD_DT }</td>
		        <td>${item.CATEGORY_NAME }</td>
		        <td><img src="<c:url value="/imgFileView.do?path=${item.LIST_THUMBNAIL}"/>" width="132" height="67"></td>
		        <td><a href="javascript:fn_view('${item.EVENT_NO}')">${item.TITLE}</a></td>
		        <td>${fn:substring(item.START_DATE, 0, 4)}-${fn:substring(item.START_DATE, 4, 6)}-${fn:substring(item.START_DATE, 6, 8)}</td>
		        <td>${fn:substring(item.END_DATE, 0, 4)}-${fn:substring(item.END_DATE, 4, 6)}-${fn:substring(item.END_DATE, 6, 8)}</td>
		        <td class="txt04">
		        	<c:if test="${ item.OPEN_YN eq 'N' }">미적용</c:if>
		        	<c:if test="${ item.OPEN_YN eq 'Y' }">적용</c:if>
		        </td>
		        <td><a href="javascript:fn_popup('${item.EVENT_NO}')">[미리보기]</a></td>
		        <td><a href="javascript:fn_lecture('${item.EVENT_NO}')">강의</a></td>
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