<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${ params.result eq '1' }">alert('등록되었습니다.')
</c:if>
});

function fn_Delete(){
	
	if(!confirm("이벤트를 삭제하시겠습니까?")) return;
	$("#NO").val('');
	$.ajax({
		type: "POST",
		url: '<c:url value="/eventManagement/eventDelete.do"/>',
		data: $("#frm").serialize(),
		cache: false,
		dataType: "json",
		error: function (request, status, error) {
		},
		success: function (response, status, request) {
			if(response.result == "1") {
				alert("이벤트가 삭제 되었습니다.");
				$("#frm").attr("action","<c:url value='/eventManagement/eventMgtList.do' />");
				$("#frm").submit();
			}
		}
	});
}

function fn_DeleteComment(param){
	if(param != 'undefined') $("#NO").val(param);
	
	if(!confirm("댓글을 삭제하시겠습니까?")) return;
	
	$.ajax({
		type: "POST",
		url: '<c:url value="/eventManagement/eventDetailCommentDelete.do"/>',
		data: $("#frm").serialize(),
		cache: false,
		dataType: "json",
		error: function (request, status, error) {
		},
		success: function (response, status, request) {
			if(response.result == "1") {
				alert("댓글이 삭제 되었습니다.");
				$("#frm").submit();
			}
		}
	});
}

function fn_UpdateView(){
	if(confirm("수정화면으로 이동하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/eventManagement/eventUpdateView.do' />");
		$("#frm").submit();
	}
}

function fn_ResultList(){
	if(confirm("결과보기로 이동하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/eventManagement/eventResultList.do' />");
		$("#frm").submit();
	}
}

function fn_List(){
	if(confirm("목록으로 돌아가시겠습니까?")){
		$("#frm").attr("action","<c:url value='/eventManagement/eventMgtList.do' />");
		$("#frm").submit();
	}
}

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#frm').attr('action','<c:url value="/eventManagement/eventDetail.do"/>').submit();
}

function goFileDownload(filePath) {
	document.location.href = "<c:url value='/download.do' />?path="+filePath;
}

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

function fn_comment_insert() {
	if($("#EVENT_TXT").val() == "") {
		alert("댓글을 입력하여 주십시오.");
		$("#EVENT_TXT").focus();
		return;
	}
	
	if(!confirm("등록 하시겠습니까?")) return;
	
	$.ajax({
			type: "POST",
			url: '<c:url value="/eventManagement/eventDetailComment.do"/>',
			data: $("#frm").serialize(),
			cache: false,
			dataType: "json",
			error: function (request, status, error) {
			},
			success: function (response, status, request) {
				if(response.result == "1") {
					alert("댓글이 등록 되었습니다.");
					//$("#frm").attr("action","<c:url value='/eventManagement/eventDetailComment.do' />");
					$("#frm").submit();
				}
			}
		});
}

//SMS보내기 
function fn_sendSMS() {
	
	var tmp = "";
	var userphone = "";
	$("input[name=smsname]").each(function(index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
			userphone = $("#userphone"+index).val();
		}else{
			tmp = tmp + "/" + $(this).val();
			userphone = userphone + "/" + $("#userphone"+index).val();
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("받을사람을 선택해 주세요.");
		return;
	}
	/* alert("tmp:"+tmp +"\n"+
			"userphone:"+userphone); */
	window.open('<c:url value="/productOrder/add.pop"/>?userphone=' + userphone + '&smsname=전체발송', '_blank', 'location=no,resizable=no,width=1005,height=400,top=0,left=0,scrollbars=no,location=no,scrollbars=no');
}

//엑셀
function fn_excel() { //ck 추가 
	$('#frm').attr('action','<c:url value="/eventManagement/eventCommentListExcel.do"/>').submit();
}
</script>
</head>


<!--content -->
<div id="content">
<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>

<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="${ params.EVENT_NO }"/>
<input type="hidden" id="NO" name="NO" value=""/>

    	<table class="table01">
		      <tr>
		        <th>직렬</th>
		        <td class="tdLeft">${view.CATEGORY_NAME }</td>
		        <th>공지구분</th>
		        <td class="tdLeft">
		        	<c:if test="${view.NOTICE_GUBUN eq '1,2'}">Top, 이슈</c:if>
		        	<c:if test="${view.NOTICE_GUBUN eq '1'}">Top</c:if>
		        	<c:if test="${view.NOTICE_GUBUN eq '2'}">이슈</c:if>
		        </td>
		      </tr>
		      <tr>
		        <th>작성일</th>
		        <td class="tdLeft">${view.REG_DT }</td>
		        <th>조회수</th>
		        <td class="tdLeft">${view.HIT }</td>
		      </tr>
		      <tr>
		        <th>시작일</th>
		        <td class="tdLeft">${fn:substring(view.START_DATE, 0, 4)}-${fn:substring(view.START_DATE, 4, 6)}-${fn:substring(view.START_DATE, 6, 8)}
		        	${ view.START_TIME }:00</td>
		        <th>종료일</th>
		        <td class="tdLeft">${fn:substring(view.END_DATE, 0, 4)}-${fn:substring(view.END_DATE, 4, 6)}-${fn:substring(view.END_DATE, 6, 8)}
		        	${ view.END_TIME }:00</td>
		      </tr>
		      <tr>
		        <th>적용여부</th>
		        <td class="tdLeft">
		        	<c:if test="${view.OPEN_YN eq 'Y'}">적용</c:if>
		        	<c:if test="${view.OPEN_YN ne 'Y'}">미적용</c:if>
		        </td>
		        <th>상태</th>
		        <td class="tdLeft">${view.STATUS }</td>
		      </tr>
		      <tr>
		      	<th>제목</th>
		      	<td td class="tdLeft" colspan="3">${view.TITLE }</td>
		      </tr>
		      <tr>
		        <th>첨부파일</th>
		        <td class="tdLeft" colspan="3">
		        	<c:forEach var="item" items="${fileList }">
						<c:if test="${item.FILE_PATH ne null}">
							<a href="javascript:goFileDownload('<c:out value="${item.FILE_PATH}" />')">${item.FILE_NAME}</a><br/>
						</c:if>
					</c:forEach>
				</td>
		      </tr>
		      <tr>
		        <th>내용</th>
		        <td class="tdLeft" colspan="3">
		        	<c:if test="${ ! empty view.CONTENTS_IMG}">
		        		<img src="<c:url value="/imgFileView.do?path=${view.CONTENTS_IMG}"/>" width="550" height="280">
		        	</c:if>		        
		        	<c:if test="${ ! empty view.CONTENTS_TEXT }">
		        		<c:out value="${view.CONTENTS_TEXT }" escapeXml="false"></c:out>
		        	</c:if>
		        </td>
		      </tr>
	</table>
	<!--//테이블--> 
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_UpdateView();">수정</a></li>
   	  <li><a href="javascript:fn_Delete();">삭제</a></li>
      <li><a href="javascript:fn_List();">목록</a></li>
      <li><a href="javascript:fn_ResultList();">결과보기</a></li>
    </ul>
    <!--//버튼--> 

	<ul class="reply_write">
		<li>
			<div class="tit_comment">
				<p class="user_id no_back">댓글쓰기</p>
			</div>
			<p class="box_comment">
				<textarea name="EVENT_TXT" id="EVENT_TXT" rows="2" cols="55" class="txt_logout"  onclick="javascript:this.value='';">댓글을 남겨주세요.</textarea>
				<button type="button" class="btn_gray" onclick="javascript:fn_comment_insert();">등록</button>
			</p>
		</li>
	</ul>
   
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_excel();">Excel(전체)</a></li>
   	  <li><a href="javascript:fn_sendSMS();">참여자SMS발송(전체)</a></li>
   	  <li><a href="javascript:fn_DeleteComment();">삭제</a></li>
    </ul>
    <table class="table02">
      <tr>
        <th width="60">
			<input id="allCheck" value="" type="checkbox" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />
		</th>
        <th width="90">번호</th>
        <th width="100">작성자(아이디)</th>
        <th width="100">작성일</th>
        <th>내용</th>
        <th width="90">삭제</th>
      </tr>
      	<c:if test="${not empty listAll}">
	      <c:forEach var="itemAll" items="${listAll}" varStatus="status">
				<input type="hidden" name="smsname" id="${itemAll.USERID}" value="${itemAll.USER_NM}">
				<input type="hidden" name="userphone${status.index}" id="userphone${status.index}" value="${fn:replace(itemAll.PHONE_NO,'-', '')}">
	      </c:forEach>
	    </c:if>

      	<c:if test="${not empty list}">
	      <c:forEach var="item" items="${list}" varStatus="status">
		      <tr>
		        <td><input type="checkbox" name="DEL_ARR" value="${item.NO}"/></td>
		        <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		        <td>${item.USER_NM }<br/>(${item.USER_ID })</td>
		        <td>${item.REG_DT }</td>
		        <td>${item.TXT }</td>
		        <td><button type="button" onclick="javascript:fn_DeleteComment('${item.NO}');">삭제</button></td>
		      </tr>
	      </c:forEach>
	    </c:if>
      
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="6">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
    </table>   

	<!-- paginate-->
	<c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
    
</form>
</div>
<!--//content --> 
