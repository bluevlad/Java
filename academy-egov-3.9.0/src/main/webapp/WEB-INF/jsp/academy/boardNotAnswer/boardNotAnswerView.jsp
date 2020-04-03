<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head> 
<script type="text/javascript">

function goBoardReply(id){
	
	$("#form").attr("action", "<c:url value='/boardNotAnswer/boardNotAnswerReplyWrite.do' />");
	$("#form").submit();
}

function goBoardList(){
	$("#form").attr("action", "<c:url value='/boardNotAnswer/boardNotAnswerList.do' />");
	$("#form").submit();
}

function goFileDownload(filePath) {
	document.location.href = "<c:url value='/download.do' />?path="+filePath;
}

function goBoardModify(){
	$("#form").attr("action", "<c:url value='/boardNotAnswer/boardNotAnswerModify.do' />");
	$("#form").submit();
}

function goBoardDelete(board_seq ,board_mng_seq ) {
	
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
						$("#form").attr("action", "<c:url value='/boardNotAnswer/boardNotAnswerDelete.do' />");
						$("#form").submit();
					} else {
						return false;
					}
				}
			}
		});
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
   
function member_view(userid){
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
    	window.open('/productOrder/MemberView1.pop?userid=' + userid, '_blank', 'location=no,resizable=no,width=820,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
    }
}   
</script>
</head>

<!--content -->
<div id="content">
	<form id="form" name="form" method="post">
	<input type="hidden" id="FILE_PATH" name="FILE_PATH" value="${detailView.FILE_PATH}"/>
	
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
	<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
	
	<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
	<input type="hidden" id=BOARDVIEW_MNG_SEQ name="BOARDVIEW_MNG_SEQ" value="${params.BOARDVIEW_MNG_SEQ}">
	<input type="hidden" id=BOARDVIEW_SEQ name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
	<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
	<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">
	
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	
	<h2>● 미응답게시판 > <strong>미응답게시판 상세</strong></h2>	
	<!--테이블-->
		<table class="table02">
		<tr>
			<th scope="col">위치</th>
			<td scope="col" style="text-align:left;">${params.BOARDVIEWCODENAME}</td>
			<th scope="col">구분</th>
			<td scope="col" style="text-align:left;">
				<c:if test='${detailView.NOTICE_TOP_YN=="Y"}'>TOP</c:if>
				<c:if test='${detailView.NOTICE_TOP_YN=="N"}'>일반</c:if>
			</td>
		</tr>
		<tr>
			<th scope="col">작성자</th>
			<td scope="col" style="text-align:left;"><a href="javascript:member_view('${detailView.UPD_ID }');"><c:out value="${detailView.CREATENAME}"/></a></td>
			<th scope="col">작성일</th>
			<td scope="col" style="text-align:left;">
				${detailView.REG_DT}
			 </td>
		</tr>
		<tr>
			<th scope="col">첨부</th>
			<td scope="col" style="text-align:left;">
				<c:if test="${boardAttachFile.size()>0}">
					<!-- TB_BOARD_FILE 테이블에서 가지고 오기 위한 수정 (다수의 첨부파일을 올릴수 있게 하기 위함)-->
						<c:forEach items="${boardAttachFile}" var="data" varStatus="status">
							<a href="javascript:goFileDownload('<c:out value="${data.FILE_PATH}" />')">
								${data.FILE_NAME}&nbsp;<img src="/resources/img/common/icon_disk01.png" alt="파일첨부" />
							</a><br/>
						</c:forEach>
					<%-- <a href="javascript:goFileDownload('<c:out value="${detailView.FILE_PATH}" />')">${detailView.REAL_FILE_NAME}</a> --%>
				</c:if>
			</td>
			<th scope="col">조회수</th>
			<td scope="col" style="text-align:left;"><fmt:formatNumber value="${detailView.HITS}" type="number"/></td>
		</tr>
		<c:if test="${detailView.BOARD_MNG_SEQ eq 'BOARD_000'}">
        <tr>
            <th scope="col">캠퍼스</th>
            <td scope="col" style="text-align:left;">
            	${detailView.CAMPUS_YN_NM}
            </td>
            <th scope="col">상담분류</th>
            <td scope="col" style="text-align:left;">
               ${detailView.BOARD_OFF_TYPE_NM}
            </td>
        </tr>
        </c:if>
		<tr>
			<th scope="col" colspan>제목</th>
			<td scope="col" colspan="3" style="text-align:left;"><c:out value="${detailView.SUBJECT}"/></td>
		</tr>
		<tr>
			<th scope="col" height="150">내용</th>
			<td scope="col" colspan="3" style="text-align:left;">
	            <c:if test="${boardAttachFile_Img.size() > 0 }">	
	            	<div>
	            		<c:forEach items="${boardAttachFile_Img}" var="data" varStatus="status">
	            			<p>
	            				<img src="<c:url value="/imgFileView.do?path=${data.FILE_PATH}" />" />
	            			</p>
						</c:forEach>
	            	</div>
	            </c:if>	
            	<c:out value="${detailView.CONTENT}" escapeXml="false" /> 
			</td>
		</tr>
		<tr>
			<th scope="col">처리여부</th>
			<td scope="col" colspan="3" style="text-align:left;"><c:out value="${detailView.BOARD_REPLY_NM}"/></td>
		</tr>
		</tbody>
        </table>
    <!--//테이블-->
	<ul class="boardBtns">
		<li><a href='javascript:goBoardReply()'>답변</a></li>
   	  	<li><a href="javascript:goBoardModify()">수정</a></li>
   	  	<li><a href='javascript:goBoardDelete("${detailView.BOARD_SEQ}","${detailView.BOARD_MNG_SEQ}")'>삭제</a></li>
   	  	<li><a href="javascript:goBoardList()">목록</a></li>
    </ul>
	</form>
</div>
<!--//content --> 
<c:import url="/board/board_reply.do" />
