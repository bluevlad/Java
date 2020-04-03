<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
<meta name="decorator" content="index">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/libs/cheditor/cheditor.js" /></script>
<script type="text/javascript">
var editor = null;
$(document).ready(function(){
	/*  editor = CKEDITOR.replace('c_content', {
	 	language : 'ko',
	 	width : '96%',
	 	height : 400,
	 	filebrowserUploadUrl : '<c:url value="/file/editorImgUpload"/>'
	 });  */

	 editor = new cheditor();              // 에디터 개체를 생성합니다.
	 editor.config.editorHeight = '400px';     // 에디터 세로폭입니다.
	 editor.config.editorWidth = '96%';        // 에디터 가로폭입니다.
	 editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
	 editor.inputForm = 'CONTENT';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	 editor.run();
});

function fileAdd() {
	$("#fileControl").append("<br/><input type=\"file\" name=\"upFile\" class=\"multipartFile\" onchange=\"fileAdd()\" >");
}

function deleteAttachFile(id){
	$.ajax({
		type: "GET",
		url: '<c:url value="/board/deleteFile/"/>'+id,
		dataType: "json",
		async : false,
		success: function(json) {
			$("#file_"+id).remove();
		}
	});
}

function paramCheck() {
	//처리상태 값: CS,운영 현재 게시판 처리상태만 변경 답변완료이면 답변까지 추가됨
	var boardReply 	= $(':radio[name="BOARD_REPLY"]:checked').val();
	//처리상태 답변완료 체크시에만 등록값을 체크
	if(boardReply=='Y'){
		var contentStr = editor.outputBodyHTML();
		var strlength = editor.strLength(contentStr);
		if(strlength>4000&&'${detailView.CAMPUS_YN}'!='C'&&'${detailView.CAMPUS_YN}'!=''){
	   		alert("내용이 4000자 초과하였습니다.\n4000자 이내로 줄여주세요.");
	   		return;
	   	}
		if($("#SUBJECT").val() == "") {
			alert("제목을 등록해 주세요.");
			$("#SUBJECT").focus();
			return;
		} else if(contentStr == ""){
			alert("내용을 등록해 주세요.");
			$("#CONTENT").focus();
			return;
		}
	}
	//먼저 처리상태 변경 후 리턴값으로 체크
	var params = "BOARDVIEW_SEQ="+$("#BOARDVIEW_SEQ").val()+'&BOARDVIEW_MNG_SEQ='+$("#BOARD_MNG_SEQ").val()+'&BOARD_REPLY='+boardReply;
	$.ajax({
		type: "POST", 
		url: '<c:url value="/board/updateBoardReply.do"/>?'+params,
		dataType: "json",		
		async : false,
		success: function(data) {
			var result = data.resultmsg;
			if(result=="Y"){
					$("#frm").attr("action", "<c:url value='/board/boardReplyInsertProcess.pop' />");
					$("#frm").submit();
			}else{
				var msg = "";
				if(result=="C") msg="CS";
				else if(result=="A") msg="운영";
				else msg = result;
				alert("처라상태가 "+msg+"로 처리되었습니다.");
				fn_boardList();
			}
		}
	});
}
function fn_boardList(){
	$("#frm").attr("action", "<c:url value='/board/boardList.pop' />");
	$("#frm").submit();
}

function member_view(userid){
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
        window.open('<c:url value="/productOrder/MemberView1.pop" />?userid=' + userid, '_blank', 'location=no,resizable=no,width=820,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
    }
}
</script>
</head>


<!--content -->
<div id="content_pop">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="codeStr" name="codeStr"  value=""/>

<input type="hidden" name="CREATENAME" value="${params.USERNAME}"/>
<input type="hidden" name="REG_ID" value="${params.USERID}"/>

<input type="hidden" id="NOTICE_TOP_YN" name="NOTICE_TOP_YN" value="N"/>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
<input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
<input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
<input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>
<input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">

<input type="hidden" id="CAMPUS_YN" name="CAMPUS_YN" value="${detailView.CAMPUS_YN}">
<input type="hidden" id="BOARD_OFF_TYPE" name="BOARD_OFF_TYPE" value="${detailView.BOARD_OFF_TYPE}">

	<h2>● 생성게시판  > <strong>${params.BOARD_MNG_NAME} 답변 등록</strong></h2>
    	<table class="table01">
    	<caption></caption>
    	<colgroup>
    		<col width="15%">
    		<col width="">
    	</colgroup>
   		<tr>
    		<th scope="col">공개 여부</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="OPEN_YN"  class="i_check" value="Y" type="radio" ><label for="a2">공개</label>&nbsp;&nbsp;
	   				<input name="OPEN_YN"  class="i_check" value="N" type="radio"  checked><label for="a3">비공개</label>
	   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:member_view('${detailView.UPD_ID }');">
		           	 질문자: <c:choose>
						<c:when test="${detailView.BOARD_MNG_SEQ eq 'NOTICE_013'  or detailView.BOARD_MNG_SEQ eq 'BOARD_002' 
						or detailView.BOARD_MNG_SEQ eq 'BOARD_003' or detailView.BOARD_MNG_SEQ eq 'BOARD_004' 
						or detailView.BOARD_MNG_SEQ eq 'BOARD_005' or detailView.BOARD_MNG_SEQ eq 'BOARD_006'}">
							<c:out value="${detailView.USER_NM}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${detailView.CREATENAME}"/>
						</c:otherwise>
					</c:choose>
		            </a>
				</div>
   			</td>
   		</tr>
		<tr>
		<c:if test="${detailView.BOARD_MNG_SEQ eq 'BOARD_000'}">
        <tr>
            <th scope="col">캠퍼스</th>
            <td scope="col" style="text-align:left;">
            	${detailView.CAMPUS_YN_NM}
            </td>
        </tr>
        <tr>
            <th scope="col">상담분류</th>
            <td scope="col" style="text-align:left;">
               ${detailView.BOARD_OFF_TYPE_NM}
            </td>
        </tr>
        </c:if>
   		<tr>
   			<th scope="col">제목</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="SUBJECT" name="SUBJECT" class="i_text" title="레이블 텍스트" value="RE:${detailView.SUBJECT}" style="width:96%;" />&nbsp;&nbsp;
   					<!-- <input name="isOpenCk" id="a2" class="i_check" value="1" type="checkbox"><label for="a2">비공개</label> -->
   				</div>
   			</td>
   		</tr>
			<th height="150">내용</th>
			<td style="text-align:left;">
				<div class="item">
					<textarea id="CONTENT" name="CONTENT" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;">
						<c:if test="${params.BOARD_MNG_SEQ eq 'BOARD_000'  or params.BOARD_MNG_SEQ eq 'BOARD_002' 
						or params.BOARD_MNG_SEQ eq 'BOARD_003' or params.BOARD_MNG_SEQ eq 'BOARD_004' 
						or params.BOARD_MNG_SEQ eq 'BOARD_005' or params.BOARD_MNG_SEQ eq 'BOARD_006'}">
							<c:choose>
							 <c:when test="${params.ONOFF_DIV eq 'O'}">
							 	 <div>안녕하세요.</div>
								 <div>윌비스 신광은 경찰 온라인 담당자 입니다.</div>
								 <div>문의해주신 내용 답변 드립니다.</div>
								 <br><br>
		                         <div>"당장 내 뜻대로 되지 않는다고 해서 쉽게 포기하지 마세요.</div>
								 <div>같은 환경, 같은 기회가 주어져도 성공의 계단은 어떤 상황에도 낙심하지 않는 사람,위기를 기회로 삼는 사람,</div> 
								 <div>신념과 인내를 갖고 준비하는 사람에게 찾아올 것입니다."</div>
								 <br>
								 <div>그럼 오늘도 파이팅 하세요^^</div>
		                         <br><br>
							 </c:when>
							 <c:otherwise>
							 	 <div>안녕하세요. 대한민국 경찰 독보적 1위!</div>
								 <div>윌비스 신광은 경찰학원입니다.</div>
								 <div>문의 주셔서 감사 드립니다.</div>
								 <br><br>
		                         <div>학원에 문의하시면 더 자세한 상담이 가능합니다.</div>
								 <div>☎ 1544-0336</div> 
								 <br>
								 <div>상담실을 이용해 주셔서 감사합니다.</div>
								 <br><br>
							 </c:otherwise>
							</c:choose>
						</c:if>
						------------  원본 내용 ------------------<br>
						<c:out value="${detailView.CONTENT}" escapeXml="false"/>
					</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td style="text-align:left;">
			<%-- <c:forEach items="${boardContent.attachFiles }" var="attachFile">
				<span style="cursor:pointer" id="file_${attachFile.attachFileId }" onclick="deleteAttachFile('${attachFile.attachFileId}')">${attachFile.fileName } - 삭제</span><br/>
			</c:forEach> <br/> --%>
				<div class="item" id="fileControl">
				<%-- <c:if test="${detailView.ATTACHFILEID ne null }">
					<span style="cursor:pointer" id="file_${detailView.ATTACHFILEID }" onclick="deleteAttachFile('${detailView.ATTACHFILEID}')">${detailView.FILENAME} - 삭제</span><br/>
				<!-- <input title="레이블 텍스트" type="file" name="upFile" onchange="fileAdd();" /> -->
				</c:if>
				<c:if test="${detailView.ATTACHFILEID eq null }">
					<input title="레이블 텍스트" type="file" name="uploadFile"  />
				</c:if> --%>
				<input title="레이블 텍스트" type="file" name="uploadFile"  />
			</div>
			</td>
		</tr>
<%-- 	 	<c:if test="${detailView.PARENT_BOARD_SEQ > 0}"> --%>
			<c:if test="${params.BOARD_MNG_SEQ eq 'BOARD_000'  or params.BOARD_MNG_SEQ eq 'BOARD_002' 
				or params.BOARD_MNG_SEQ eq 'BOARD_003' or params.BOARD_MNG_SEQ eq 'BOARD_004' 
				or params.BOARD_MNG_SEQ eq 'BOARD_005' or params.BOARD_MNG_SEQ eq 'BOARD_006'}">
			<tr>
	    		<th scope="col">처리여부</th>
	   			<td scope="col" style="text-align:left;">
	   			
	   				<div class="item">
		   				<input name="BOARD_REPLY"  class="i_check" value="C" type="radio" <c:if test="${detailView.BOARD_REPLY eq 'C'}">checked</c:if>><label for="a2">CS</label>&nbsp;&nbsp;
		   				<input name="BOARD_REPLY"  class="i_check" value="A" type="radio" <c:if test="${detailView.BOARD_REPLY eq 'A'}">checked</c:if>><label for="a3">운영</label>&nbsp;&nbsp;
		   				<input name="BOARD_REPLY"  class="i_check" value="Y" type="radio" <c:if test="${detailView.BOARD_REPLY eq 'Y' || empty detailView.BOARD_REPLY || detailView.BOARD_REPLY eq ''}">checked</c:if>><label for="a3">답변완료</label>
					</div>
	   			</td>
	   		</tr>	
	   		</c:if>
<%--    		</c:if> --%>
		</table>
	<!--//테이블-->

    <!--버튼-->
      <ul class="boardBtns">
	   	  <li><a href="javascript:paramCheck();">등록</a></li>
	      <li><a href="javascript:fn_boardList();">목록</a></li>
	  </ul>
    <!--//버튼-->
</form>
</div>
<!--//content -->
<c:if test="${params.BOARD_MNG_SEQ eq 'BOARD_000'  or params.BOARD_MNG_SEQ eq 'BOARD_002' 
				or params.BOARD_MNG_SEQ eq 'BOARD_003' or params.BOARD_MNG_SEQ eq 'BOARD_004' 
				or params.BOARD_MNG_SEQ eq 'BOARD_005' or params.BOARD_MNG_SEQ eq 'BOARD_006'}">
<c:import url="/board/board_reply.do" />
</c:if>