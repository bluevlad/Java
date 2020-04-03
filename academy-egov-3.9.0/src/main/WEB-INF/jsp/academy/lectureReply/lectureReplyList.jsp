<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
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
//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/lectureReply/lectureReplyList.do"/>').submit();
}

// 코드 상세
function view(LECCODE) {
	$("#DETAIL_LECCODE").val(LECCODE);
	$('#searchFrm').attr('action','<c:url value="/lectureReply/lectureReplyDetail.do"/>').submit();
}
</script>
</head>

  <!--content -->
  <div id="content">
	<h2>● 게시판관리> <strong>수강후기 게시판</strong></h2>
    
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
	<input type="hidden" id="deleteIds" name="deleteIds">
	<input type="hidden" id="USERID" name="USERID" value="${AdmUserInfo.USERID}" />
	<input type="hidden" id="DETAIL_LECCODE" name="DETAIL_LECCODE" value="" />
	
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	
    
      <table class="table01">
          <tr>
            <th width="15%">검색</th>
            <td>            

						<label for="SEARCHTYPE"></label>
						<select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
							<option value="">--전체검색--</option>
							<option value="CATEGORY_NAME"  <c:if test="${searchMap.SEARCHTYPE == 'CATEGORY_NAME'}">selected</c:if>>직종명</option>
							<option value="SUBJECT_TEACHER_NAME"  <c:if test="${searchMap.SEARCHTYPE == 'SUBJECT_TEACHER_NAME'}">selected</c:if>>강사명</option>
							<option value="SUBJECT_TITLE"  <c:if test="${searchMap.SEARCHTYPE == 'SUBJECT_TITLE'}">selected</c:if>>강의명</option>
							<option value="SUBJECT_NM"  <c:if test="${searchMap.SEARCHTYPE == 'SUBJECT_NM'}">selected</c:if>>과목명</option>
						</select>
						<label for="SEARCHKEYWORD"></label>
						<input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name=SEARCHKEYWORD  type="text" size="40"  value="${searchMap.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">

		    <th width="15%">화면출력건수</th>
		    <td width="30%">               
	                	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${searchMap.pageRow }" onkeypress="fn_RowNumCheck()">
		                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
          </tr>
      </table>
    </form>
    <!--//테이블-->
    <!--테이블-->
    <!--//테이블-->
    <!--버튼-->
    			<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
                <!--//버튼--> 
    <!--테이블-->
          
            <table class="table02">
                <tr>
                  <th width="85">No</th>
                  <th>직종</th>
                  <th>과목</th>
                  <th>강사명</th>
                  <th>강의명</th>
                  <th>평점</th>
                  <th>등록자(ID)</th>
                </tr>
              <tbody>
              <c:if test="${not empty list}">
	              <c:forEach items="${list}" var="list" varStatus="status">
		                <tr>
		                  <td>
			              		${totalCount - (( currentPage - 1) * pageRow) - status.index} 
			              </td>
		                  <td>${list.CATEGORY_NAME}</td>
		                  <td>${list.SUBJECT_NM}</td>
		                  <td>${list.SUBJECT_TEACHER_NAME}</td>
		                  <td><a href="javascript:view('${list.LECCODE}')">${list.SUBJECT_TITLE}</a></td>
		                  <td>
		                  		<c:forEach var="b" begin="1" end="${list.CHOICE_POINT}" step="1">
									<img src="<c:url value="/resources/images/star.png"/>" border="0" />
								</c:forEach>
		                  </td>
		                  <td>${list.USER_ID}</td>
		                </tr>
			        </c:forEach>
				</c:if>
				<c:if test="${empty list}">
		            <tr bgColor=#ffffff align=center> 
						<td colspan="8">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>	 
                </tbody>
            </table>
          
          
          <!--//테이블--> 
   
	    <!-- paginate-->
		    <c:if test="${not empty list}">
				<c:out value="${pagingHtml}" escapeXml="false" />
			</c:if>
	   <!--//paginate-->
	</div>
  <!--//content --> 