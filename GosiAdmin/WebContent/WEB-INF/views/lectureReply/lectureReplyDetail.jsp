<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPageReply").val(1);
	else $("#currentPageReply").val(page);
	
	$('#frm').attr('action','<c:url value="/lectureReply/lectureReplyDetail.do"/>').submit();
}

function fn_List(){
	
	$("#frm").attr("action", "<c:url value='/lectureReply/lectureReplyList.do' />");
	$("#frm").submit();
}

//fn_replyDelete
function fn_replyDelete(LECCODE , SEQ){
	if(confirm("삭제하시겠습니까?")) {
		$("#DELETE_LECCODE").val(LECCODE);
		$("#DELETE_SEQ").val(SEQ);
		$("#frm").attr("action", "<c:url value='/lectureReply/replyDelete.do' />");
		$("#frm").submit();
	} else {
		return false;
	}
}

</script>
</head>


<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>

<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="currentPageReply" name="currentPageReply" value="${currentPageReply}">
<input type="hidden" id="pageRowReply" name="pageRowReply" value="${pageRowReply}">

<input type="hidden" id="DETAIL_LECCODE" name="DETAIL_LECCODE" value="${params.DETAIL_LECCODE}" />	
<input type="hidden" id="DELETE_LECCODE" name="DELETE_LECCODE" value="" />	
<input type="hidden" id="DELETE_SEQ" name="DELETE_SEQ" value="" />	

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

<h2>● 게시판관리 > <strong>수강후기게시판</strong></h2>
<!--//테이블--> 
    	<table class="table01">
    	<tr>
    		<th >과목</th>
   			<td >
	   				${detail.SJT_NM}
   			</td>
   		</tr>
   		<tr>
    		<th >강사명</th>
   			<td >
   					${detail.SUBJECT_TEACHER_NAME}
   			</td>
   		</tr>	
   		<tr>
   			<th >강좌 설명 </th>
   			<td >
   					${detail.SUBJECT_TITLE}
   			</td>
   		</tr>
		</table>
	<!--//테이블--> 
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPageReply}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table01">
       <c:if test="${not empty replyList}">
		        <c:forEach items="${replyList}" var="list" varStatus="status">
		           <tr>
		           		<td width="30">
			          		${totalCount - (( currentPageReply - 1) * pageRowReply) - status.index} 
			          	</td>
						<td width="606" height="20" style="padding-left:40px">
						<!-- 제목 -->
						<b>${list.USER_NAME}</b>(${list.USER_ID}) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<!-- 날짜 -->
						<font color="9a9a9a">
							${list.REG_DT}
						</font> &nbsp;
					
						<font color="9a9a9a">
									     평점 : 
								<c:forEach var="b" begin="1" end="${list.CHOICE_POINT}" step="1">
									<img src="<c:url value="/resources/images/star.png"/>" border="0" />
								</c:forEach>									  
						</font>
						</td>
						<td width="87">
							<input type="button" onclick='javascript:fn_replyDelete("${list.LECCODE}" , "${list.SEQ}");' value="삭제" />						                                 
						</td>
				</tr>
				<tr>
				      <td width="693" colspan="3" style="padding-left:70px;">
						${list.CONTENT}
				      </td>
				 </tr>
		    </c:forEach>
		</c:if>
		
		<c:if test="${empty replyList}">
		         <tr bgColor=#ffffff align=center> 
			<td colspan="8">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
     </table>
    <!-- paginate-->
		    <c:if test="${not empty replyList}">
				<c:out value="${pagingHtml}" escapeXml="false" />
			</c:if>
	   <!--//paginate-->
    
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
