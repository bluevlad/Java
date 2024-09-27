<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
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
	$("#form").attr("action", "<c:url value='/board/boardFAQList.pop' />");
	$("#form").submit();
}
function goBoardView(boardSeq , boardParentSeq , codeName){
	var searchCategory = $("#searchCategory").val();  
	var searchKind = $("#searchKind").val();
	var searchText = $("#searchText").val();
	$("#SEARCHCATEGORY").val(searchCategory);
	$("#SEARCHKIND").val(searchKind);
	$("#SEARCHTEXT").val(searchText);
	$("#BOARDVIEW_SEQ").val(boardSeq);
	$("#BOARDVIEWPARENT_SEQ").val(boardParentSeq);
	$("#BOARDVIEWCODENAME").val(codeName);
	//원본글인지
	if(boardParentSeq == 0){
	//	alert("원본글");
		$("#form").attr("action", "<c:url value='/board/boardFAQView.pop' />");
		$("#form").submit();
	}
	else{
	//	alert("답변글");
	$("#form").attr("action", "<c:url value='/board/boardFAQView.pop' />");
	$("#form").submit();
	//	$("#form").attr("action", "<c:url value='/board/boardReplyView.pop' />");
	//	$("#form").submit();
	}
	
}

function fn_Write(){
	var searchCategory = $("#searchCategory").val();
	var searchKind = $("#searchKind").val();
	var searchText = $("#searchText").val();
	$("#SEARCHCATEGORY").val(searchCategory);
	$("#SEARCHKIND").val(searchKind);
	$("#SEARCHTEXT").val(searchText);
	$("#form").attr("action", "<c:url value='/board/boardFAQWrite.pop' />");
	$("#form").submit();
}
</script>
</head>

<div id="content_pop">

<form id="form" name="form" method="post">
	<input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
	<input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
	<input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>

	<input type="hidden" id="ATTACH_FILE_YN" name="ATTACH_FILE_YN" value="${params.ATTACH_FILE_YN}"/>
	<input type="hidden" id="OPEN_YN" name="OPEN_YN" value="${params.OPEN_YN}"/>
	<input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>
	<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>

	<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value=""/>
	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value=""/>
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value=""/>
	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
<%-- 	<input type="hidden" id="pageRow" name="pageRow" value="${pageRow}">
 --%>
	<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="">
	<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="">
	<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="">
	<h2>● 생성게시판 > <strong>${params.BOARD_MNG_NAME}</strong></h2>
	<!--테이블-->
		<table class="table01">
		<tr>
            <th width="15%">검색</th>
			<td>
					<select style="width:100px;" name="searchCategory" id="searchCategory">
			<%-- 			<c:forEach items="${rankList}"  var="data" varStatus="status" >
							<option value="${data.CODE}" >${data.CODENAME }</option>
						</c:forEach>
						 --%>
						<c:forEach items="${rankList}"  var="data" varStatus="status" >
							 <c:set var="vChecked">
									   <c:choose>
									    <c:when test="${data.CODE == params.SEARCHCATEGORY}">selected="selected"</c:when>
									    <c:otherwise></c:otherwise>
									   </c:choose>
							 </c:set>
							<option value="${data.CODE}"  <c:out value='${vChecked}'/> >${data.NAME }</option>
						</c:forEach>
						
						
					</select>
					<select style="width:100px;" name="searchKind" id="searchKind">
						<option value="SEARCHSUBJECT" <c:if test="${params.SEARCHKIND == 'SEARCHSUBJECT' }">selected="selected"</c:if>>제목</option>
						<option value="SEARCHNAME" <c:if test="${params.SEARCHKIND == 'SEARCHNAME' }">selected="selected"</c:if>>이름</option>
						<option value="SEARCHCONTENT" <c:if test="${params.SEARCHKIND == 'SEARCHCONTENT' }">selected="selected"</c:if>>내용</option>
					</select>
	            	<input class="i_text" title="레이블 텍스트" type="text" name="searchText" id="searchText" size="35" value="${params.SEARCHTEXT}" onkeypress="fn_checkEnter()" />
            </td>
            <th width="15%">화면출력건수</th>
		    <td width="30%">               
	                	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${pageRow}" onkeypress="fn_RowNumCheck()">
		                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
		</tr>
     	</table>
     	
     <!--//테이블-->
		<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
		<table class="table02">
			<tr>
				<th scope="col">No</th>
				<th scope="col">구분</th>
				<th scope="col">제목</th>
				<th scope="col">첨부</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일</th>
				<th scope="col">조회수</th>
			</tr>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="data" varStatus="status">
		        <tr>
					<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		            <td>${data.CODENAME}</td>
					<td style="text-align:left; padding-left:${data.LISTLEVEL*10}px;" >
						<a href='javascript:goBoardView("${data.BOARD_SEQ}" ,"${data.PARENT_BOARD_SEQ}" , "${data.CODENAME}" );'>
		            		<c:if test="${data.PARENT_BOARD_SEQ ne '0'}"><img src="<c:url value="/resources/images//re.gif"/>" align="absmiddle" /><c:out value="${data.SUBJECT}"/></c:if>
		            		<c:if test="${data.PARENT_BOARD_SEQ eq '0'}">
		            			<c:if test="${data.NOTICE_TOP_YN == 'Y'}">
		            				<strong style="color:black;"><c:out value="${data.SUBJECT}"/></strong>
		            			</c:if>
		            			<c:if test="${data.NOTICE_TOP_YN == 'N'}">
	   						       <c:out value="${data.SUBJECT}"/> 			
			            		</c:if>
		            		</c:if>
		            	</a>
		            </td>
					<td><c:if test="${data.FILE_NAME ne null}"><img src="<c:url value="/resources/images/icon_file.png"/>" width="16" height="16" /></c:if></td>
					<td>
							<c:out value="${data.CREATENAME}"/>
					</td>
					<td>
							${data.REG_DT}
					</td>
					<td><c:out value="${data.HITS}"/></td>
		        </tr>
				</c:forEach>
			
			</c:if>
			<c:if test="${empty list}">
	            <tr bgColor=#ffffff align=center> 
					<td colspan="7">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	
       </table>
</form>	            
		 <ul class="boardBtns">
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