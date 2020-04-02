<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#form").attr("action", "<c:url value='/boardNotAnswer/boardNotAnswerList.do' />");
	$("#form").submit();
}
function goBoardView(boardMngSeq ,  boardSeq , boardParentSeq , codeName , onoff_div ){

	$("#BOARDVIEW_MNG_SEQ").val(boardMngSeq);
	$("#BOARDVIEW_SEQ").val(boardSeq);
	$("#BOARDVIEWPARENT_SEQ").val(boardParentSeq);
	$("#BOARDVIEWCODENAME").val(codeName);
	$("#ONOFF_DIV").val(onoff_div);
	
	$("#form").attr("action", "<c:url value='/boardNotAnswer/boardNotAnswerView.do' />");
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
	
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	
	<h2>● 미응답게시판 > <strong>미응답게시판</strong></h2>
	<!--테이블-->
		<table class="table01">
		<tr>
			<c:if test="${params.ONOFF_DIV eq 'F'}">
				<th width="10%">검색</th>
				<td width="">	
						<select class="sele" id="SEARCHCAMPUS_YN" name="SEARCHCAMPUS_YN">
						   <option <c:if test="${params.SEARCHCAMPUS_YN == '' or empty params.SEARCHCAMPUS_YN }">selected="selected"</c:if> value="">전체</option>
	                       <option <c:if test="${params.SEARCHCAMPUS_YN == 'C' }">selected="selected"</c:if> value="C">노량진</option>
	                       <option <c:if test="${params.SEARCHCAMPUS_YN == 'G' }">selected="selected"</c:if> value="G">광주</option>  
	                       <option <c:if test="${params.SEARCHCAMPUS_YN == 'S' }">selected="selected"</c:if> value="S">신림</option>  
	                       <option <c:if test="${params.SEARCHCAMPUS_YN == 'D' }">selected="selected"</c:if> value="D">대구</option>   
	                       <option <c:if test="${params.SEARCHCAMPUS_YN == 'B' }">selected="selected"</c:if> value="B">부산</option>  
	                       <option <c:if test="${params.SEARCHCAMPUS_YN == 'J' }">selected="selected"</c:if> value="J">제주</option>                               
	                   </select>
						<select style="width:100px;" name="SEARCHCATEGORY" id="SEARCHCATEGORY">
				<%-- 			<c:forEach items="${rankList}"  var="data" varStatus="status" >
								<option value="${data.CODE}" >${data.CODENAME }</option>
							</c:forEach>
							 --%>
							 <option <c:if test="${params.SEARCHCATEGORY == '' or empty params.SEARCHCATEGORY }">selected="selected"</c:if> value="">전체직종</option>
							<c:forEach items="${rankList}"  var="data" varStatus="status" >
								<option value="${data.CODE}"  <c:if test="${data.CODE == params.SEARCHCATEGORY}">selected="selected"</c:if>>${data.NAME }</option>
							</c:forEach>
						</select>
							<select class="sele" id="SEARCHBOARD_OFF_TYPE" name="SEARCHBOARD_OFF_TYPE">
							   <option <c:if test="${params.SEARCHBOARD_OFF_TYPE == '' or empty params.SEARCHBOARD_OFF_TYPE }">selected="selected"</c:if> value="">전체</option>
		                       <option <c:if test="${params.SEARCHBOARD_OFF_TYPE == 'A' }">selected="selected"</c:if> value="A">강의문의</option>
		                       <option <c:if test="${params.SEARCHBOARD_OFF_TYPE == 'I' }">selected="selected"</c:if> value="I">이용문의</option>  
		                       <option <c:if test="${params.SEARCHBOARD_OFF_TYPE == 'J' }">selected="selected"</c:if> value="J">교재문의</option>  
		                       <option <c:if test="${params.SEARCHBOARD_OFF_TYPE == 'S' }">selected="selected"</c:if> value="S">스파르타</option>                               
		                   </select>
						<select style="width:100px;" name="SEARCHKIND" id="SEARCHKIND">
							<option value="SEARCHSUBJECT" <c:if test="${params.SEARCHKIND == 'SEARCHSUBJECT' }">selected="selected"</c:if>>제목</option>
							<option value="SEARCHNAME" <c:if test="${params.SEARCHKIND == 'SEARCHNAME' }">selected="selected"</c:if>>작성자</option>
							<option value="SEARCHCONTENT" <c:if test="${params.SEARCHKIND == 'SEARCHCONTENT' }">selected="selected"</c:if>>내용</option>
						</select>
		            	<input class="i_text" title="레이블 텍스트" type="text" name="SEARCHTEXT" id="SEARCHTEXT" size="35" value="${params.SEARCHTEXT}" onKeyPress="fn_checkEnter()" />
	            </td>
             </c:if>
            <th width="15%">화면출력건수</th>
		    <td width="15%">               
	                	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${pageRow}" onkeypress="fn_RowNumCheck()">
		                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
		</tr>
     	</table>
     	
     <!--//테이블-->
		<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
		<table class="table02">
			<tr>
				<th width="5%">No</th>
				<th width="10%">게시판명</th>
				<c:if test="${MENUTYPE eq 'FM_ROOT'}">
					<th scope="col" width="5%">캠퍼스</th>
					<th scope="col" width="5%">구분</th>
					<th scope="col" width="5%">분류</th>
				</c:if>
				<th width="25%">제목</th>
				<th width="10%">첨부</th>
				<th width="5%">작성자</th>
				<th width="10%">답변상태</th>
				<th width="15%">작성일</th>
				<th width="5%">조회수</th>
			</tr>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="data" varStatus="status">
		        <tr>
					<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		            <td>${data.BOARD_NAME}</td>
		            <c:if test="${MENUTYPE eq 'FM_ROOT'}">
					<td>${data.CAMPUS_YN_NM}</td>
					<td>${data.CODENAME}</td>
					<td>${data.BOARD_OFF_TYPE_NM}</td>
				</c:if>
					<td style="text-align:left; padding-left:${data.LISTLEVEL*10}px;" >
						<a href='javascript:goBoardView("${data.BOARD_MNG_SEQ}","${data.BOARD_SEQ}" ,"${data.PARENT_BOARD_SEQ}" , "${data.BOARD_NAME}" , "${data.ONOFF_DIV}" );'>
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
						<c:choose>
							<c:when test="${data.BOARD_REPLY_NM eq '답변완료'}">
								답변완료
							</c:when>
							<c:otherwise>
								<font color="blue">${data.BOARD_REPLY_NM}</font>
							</c:otherwise>
						</c:choose>
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
					<td colspan="8">데이터가 존재하지 않습니다.</td>
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