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
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#form").attr("action", "<c:url value='/board/teacher/boardNotList.do' />");
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

    window.open('', 'viewBoard', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=880 ');
    $('#form').attr('target' ,'viewBoard');
    $("#form").attr("action", "<c:url value='/board/teacher/boardView.pop' />");
    $("#form").submit();
}

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

<div id="content">

<form id="form" name="form" method="post">
	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
	
	<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value=""/>
	<input type="hidden" id="BOARDVIEW_MNG_SEQ" name="BOARDVIEW_MNG_SEQ" value="">
	<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="">
	<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="">
	<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="">
	
    <input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
    <input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
    <input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>
    <input type="hidden" id="SEARCHONOFFDIV" name="SEARCHONOFFDIV" value="${params.SEARCHONOFFDIV}">
	
    <input type="hidden" id="REPLY_YN" name="REPLY_YN" value="Y"/>
    <input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value=""/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value=""/>
    <input type="hidden" id="currentPage" name="currentPage" value="1">
	
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	
	<h2>● 게시판관리 > <strong>교수미응답게시판</strong></h2>
	<!--테이블-->
		<table class="table01">
		<tr>
            <th width="10%">화면출력건수</th>
		    <td width="90%">               
	                	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${pageRow}" onkeypress="fn_RowNumCheck()">
		                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
		</tr>
     	</table>
     	
     <!--//테이블-->
		<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
		<table class="table02">
			<tr>
				<th width="8%">No</th>
				<th width="12%">게시판명</th>
				<th width="35%">제목</th>
				<th>첨부</th>
				<th>작성자</th>
				<th width="15%">작성일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="data" varStatus="status">
		        <tr>
					<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		            <td>${data.BOARD_NAME}</td>
					<td style="text-align:left; padding-left:${data.LISTLEVEL*10}px;" >
						<a href='javascript:goBoardView("${data.BOARD_SEQ}" ,"${data.PARENT_BOARD_SEQ}" , "${data.BOARD_NAME}");'>
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
					<td><c:if test="${data.FILE_NAME ne null}"><img src="<c:url value="/resources/img/common/icon_disk01.png"/>" width="16" height="16" /></c:if></td>
					<td>
							<a href="javascript:member_view('${data.UPD_ID }');"><c:out value="${data.CREATENAME}"/></a>
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
		<!--//버튼-->
	<!--paging  -->
	<c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
    <!-- //paging  -->
</div>
<!--//content --> 

</html>