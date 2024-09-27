<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head> 
<script type="text/javascript">

function goBoardReply(id){
	
	$("#form").attr("action", "<c:url value='/board/boardReplyWrite.pop' />");
	$("#form").submit();
}

function goBoardList(){
	$("#form").attr("action", "<c:url value='/board/boardFAQList.pop' />");
	$("#form").submit();
}

function goFileDownload(filePath) {
	document.location.href = "<c:url value='/download.do' />?path="+filePath;
}

function goBoardModify(){
	$("#form").attr("action", "<c:url value='/board/boardFAQModify.pop' />");
	$("#form").submit();
}

function goBoardDelete(board_seq ,board_mng_seq ) {
	/* 
		$.ajax({
			type: "GET", 
			url: '<c:url value="getReplyData.do/?BOARDVIEW_SEQ="/>'+board_seq+'&BOARD_MNG_SEQ='+board_mng_seq,
			dataType: "json",		
			async : false,
			success: function(result) {
				if(result.message=="N"){
					alert("답변이 존재하여 삭제 할수 없습니다.");
					return;
				}else{
					if(confirm("삭제하시겠습니까?")) {
						//location.href='<c:url value="/board/${board.boardId}/delete/${boardContent.boardContentId}"/>';
						$("#form").attr("action", "<c:url value='/board/boardFAQDelete.pop' />");
						$("#form").submit();
					} else {
						return false;
					}
				}
			}
		});
		 */
		
		if(confirm("삭제하시겠습니까?")) {
			//location.href='<c:url value="/board/${board.boardId}/delete/${boardContent.boardContentId}"/>';
			$("#form").attr("action", "<c:url value='/board/boardFAQDelete.pop' />");
			$("#form").submit();
		} else {
			return false;
		}
		
		
}

function checkComment() {
	if($("#commentContent").val().length < 1) {
		alert("내용을 등록해주세요.");
		return false;
	} else {
		document.commentWriteFrm.submit();
	}
}


function checkModifyComment(id) {
	document.commentModityFrm.content.value=$("#m_contnet_"+id).val();
	document.commentModityFrm.boardCommentId.value=id;
	
	if($("#m_contnet_"+id).val().length < 1) {
		alert("덧글내용을 등록해주세요.");
		return false;
	} else {
		document.commentModityFrm.submit();	
	}
}

function modifyComment(id) {
	$("#m_showFrm_"+id).hide();
	$("#m_hide_"+id).show();
}

function modifyCommentCancal(id) {
	$("#m_showFrm_"+id).show();
	$("#m_hide_"+id).hide();
}



var initBody;
function beforePrint(){
  	initBody = document.body.innerHTML;
  	document.body.innerHTML = idPrint.innerHTML;
}

function afterPrint(){
 	document.body.innerHTML = initBody;
}

//프린트하기
function print_info() {
    window.print();
}
   window.onbeforeprint = beforePrint;
   window.onafterprint = afterPrint;
</script>
</head>

<!--content -->
<div id="content_pop">
	<form id="form" name="form" method="post">
	<input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
	<input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
	<input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>
	
	<input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>
	<input type="hidden" id="FILE_PATH" name="FILE_PATH" value="${detailView.FILE_PATH}"/>
	<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
	
	<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}" />
	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
	<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<input type="hidden" id=BOARDVIEW_SEQ name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
	<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
	<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">
	
	<h2>● 생성게시판 > <strong>${params.BOARD_MNG_NAME} 상세</strong></h2>	
	<!--테이블-->
		<table class="table02">
		<tr>
			<th width="8%">위치</th>
			<td scope="col" style="text-align:left;">${params.BOARDVIEWCODENAME}</td>
			<th width="8%"> 상단구분</th>
			<td scope="col" style="text-align:left;">
				<c:if test='${detailView.NOTICE_TOP_YN=="Y"}'>TOP</c:if>
				<c:if test='${detailView.NOTICE_TOP_YN=="N"}'>일반</c:if>
			</td>
			<th width="7%">공개,비공개</th>
			<td scope="col" colspan="3"  style="text-align:left;">
				<c:if test='${detailView.OPEN_YN=="Y"}'>공개</c:if>
				<c:if test='${detailView.OPEN_YN=="N"}'>비공개</c:if>
			</td>
		</tr>
		<tr>
			<th scope="col">작성자</th>
			<td scope="col" style="text-align:left;">${detailView.CREATENAME}</td>
			<th scope="col">작성일</th>
			<td scope="col" colspan="3" style="text-align:left;">
				${detailView.REG_DT}
			 </td>
		</tr>
		<tr>
			<th scope="col">첨부</th>
			<td scope="col" style="text-align:left;">
				<c:if test="${detailView.FILE_PATH ne null}">
					<a href="javascript:goFileDownload('<c:out value="${detailView.FILE_PATH}" />')">${detailView.REAL_FILE_NAME}</a>
				</c:if>
			</td>
			<th scope="col">조회수</th>
			<td scope="col" colspan="3"  style="text-align:left;"><fmt:formatNumber value="${detailView.HITS}" type="number"/></td>
		</tr>
		<tr>
			<th scope="col" colspan>질문</th>
			<td scope="col" colspan="5" style="text-align:left;"><c:out value="${detailView.SUBJECT}"/></td>
		</tr>
		<tr>
			<th scope="col" height="150">답변</th>
			<td scope="col" colspan="5" style="text-align:left;">
	            <c:if test="${detailView.ISIMAGE == 'Y' }">	
	            	<div>
	            		<img src="<c:url value="/imgFileView.do?path=${detailView.FILE_PATH}" />" />
	            	</div>
	            </c:if>	
            	<c:out value="${detailView.ANSWER}" escapeXml="false" /> 
			</td>
		</tr>
		</tbody>
        </table>
    <!--//테이블-->
	<ul class="boardBtns">
   	  	<c:if test="${params.REPLY_YN == 'Y' }">
				<li><a href='javascript:goBoardReply("${detailView.BOARD_SEQ}")'>답변</a></li>
		</c:if>
   	  	<li><a href="javascript:goBoardModify()">수정</a></li>
   	  	<li><a href='javascript:goBoardDelete("${detailView.BOARD_SEQ}","${params.BOARD_MNG_SEQ}")'>삭제</a></li>
   	  	<li><a href="javascript:goBoardList()">목록</a></li>
    </ul>
	</form>
	
	
	<!--댓글-->
	<%-- 
	<div class="cb_module" style=" float:left; margin-top:30px; width:100%;">
		<h5 class="cb_h_type cb_h_type2">댓글 <span>(<strong>${boardContent.commentCount }</strong>)</span></h5>
		
		<!-- Comment Input Area -->
		<div class="cb_wrt cb_wrt_default" style="margin-top:10px;">
			<div class="cb_wrt_box">
				<div class="cb_wrt_box2">
				<form name="commentWriteFrm" action="<c:url value="/board/${board.boardId }/${boardContent.boardContentId }/comment"/>" method="post">
				<input type="hidden" name="_method" value="post">
				<input type="hidden" name="boardId" value="${board.boardId }">
				<input type="hidden" name="boardContentId" value="${boardContent.boardContentId }">
				<input type="hidden" name="accountId" value="${userAccount.accountId }">
				
				<fieldset>
				<legend>댓글 등록 폼</legend>
					<div class="cb_usr_area">
						<div >
							<div class="cb_section">
	                            <input id="commentContent" name="content" type="text" style="width:86%;position:relative;margin:2px;padding:2px 4px;border:1px solid #b7b7b7;border-right-color:#e1e1e1;border-bottom-color:#e1e1e1; background-color:#FFFFFF;"  />
								<div style="display:inline; vertical-align:middle">
									<img src="<c:url value="/resources/img/common/bt_comment01_01.gif"/>" alt="등록" style="vertical-align:middle;cursor:pointer" onclick="checkComment()">
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				</form>
				</div>
			</div>
		</div>
		<!-- //Comment Input Area -->
		
		<form name="commentModityFrm" action="<c:url value="/board/${board.boardId }/${boardContent.boardContentId }/comment"/>" method="post">
		<input type="hidden" name="_method" value="put">
		<input type="hidden" name="boardCommentId">
		<input type="hidden" name="accountId" value="${userAccount.accountId }">
		<input type="hidden" name="content">
		</form>
		
		<!-- Comment List -->
		<div class="cb_lstcomment">
			<ul>
				<c:forEach items="${boardContent.commentList }" var="boardComment">
				<li class="cb_thumb_off">
					<div class="cb_comment_area">
						
						<div class="cb_info_area">
							<div class="cb_section">
								<span class="cb_nick_name">${boardComment.writer }</span>
								<span class="cb_usr_id">(${boardComment.userId })</span>
								<span class="cb_date"><fmt:formatDate value="${boardComment.regTime}" type="DATE" pattern="yyyy.MM.dd KK:mm" /></span>
							</div>
						</div>
						
						<div id="m_hide_${boardComment.boardCommentId }" class="hideFrm"><div class="cb_wrt_box2">
							<div class="cb_section2">
								<span class="cb_nobar"><a href="javascript:modifyCommentCancal('${boardComment.boardCommentId}')">수정취소</a></span>
								<span class="cb_nobar"><a href="<c:url value="/board/${board.boardId }/${boardComment.boardContentId }/deleteComment/${boardComment.boardCommentId}"/>">삭제</a></span>
							</div>
		                    <input id="m_contnet_${boardComment.boardCommentId }" name="m_content" value="${boardComment.content }" type="text" style="width:86%;position:relative;margin:2px;padding:2px 4px;border:1px solid #b7b7b7;border-right-color:#e1e1e1;border-bottom-color:#e1e1e1; background-color:#FFFFFF;"  />
							<div style="display:inline; vertical-align:middle">
								<img src="<c:url value="/resources/img/common/bt_comment01_01.gif"/>" alt="수정완료" style="vertical-align:middle;cursor:pointer" onclick="checkModifyComment('${boardComment.boardCommentId}')">
							</div>
						</div></div>
						
						<div id="m_showFrm_${boardComment.boardCommentId }">
							<div class="cb_section2">
								<span class="cb_nobar"><a href="javascript:modifyComment('${boardComment.boardCommentId}')">수정</a></span>
								<span class="cb_nobar"><a href="<c:url value="/board/${board.boardId }/${boardComment.boardContentId }/deleteComment/${boardComment.boardCommentId}"/>">삭제</a></span>
							</div>						
							<div id="comment_${boardComment.boardCommentId }" class="cb_dsc_comment">
								<p class="cb_dsc">${boardComment.content}</p>
							</div>
						</div>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
		<!-- //Comment List -->
	</div>
 --%>
	<!-- //댓글 -->
</div>
<!--//content --> 

