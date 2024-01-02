<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
//숫자 입력 폼
function chk(obj){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {          
			$('#rsltPageRow').val("");      
			$('#rsltPageRow').focus();         
			return;       
			}       
		else {          
			if (val < 1) {             
				$('#rsltPageRow').val("");          
				$('#rsltPageRow').focus();            
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

	$('#rsltPageRow').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		fn_Search();
		return;
	}
	if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#rsltPageRow").val("${params.rsltPageRow}");
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
	if(typeof(page) == "undefined") $("#rsltCurrentPage").val(1);
	else $("#rsltCurrentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/eventManagement/eventResultList.do"/>').submit();
}

function fn_List(){
	if(confirm("목록으로 돌아가시겠습니까?")){
		$("#searchFrm").attr("action","<c:url value='/eventManagement/eventMgtList.do' />");
		$("#searchFrm").submit();
	}
}

//회원 상세
function fn_view(EVENTNO) {
	$('#searchFrm').attr('action','<c:url value="/eventManagement/eventDetail.do"/>').submit();
}

//엑셀
function fn_excel() { //ck 추가 
	//alert($('#payall').val());
	$('#searchFrm').attr('action','<c:url value="/eventManagement/eventResultListExcel.do"/>').submit();
}
</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 이벤트 관리</h2>
        
        <!--테이블-->    
	    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
	    <input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
		<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
		<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
		<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
		<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
	    
	    <input type="hidden" id="rsltCurrentPage" name="rsltCurrentPage" value="${params.rsltCurrentPage}">
	    
		<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="${ params.EVENT_NO }"/>
	    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
		<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
		<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
		
		<h4>${ view.TITLE }</h4><br/>
		<c:forEach items="${option1List}" var="item" varStatus="loop">
			<span style="font-size:24px;">${ item.OPTION_NO }. ${ item.OPTION_NAME } - 
			<strong>
				<c:if test="${ item.PEOPLE_GUBUN eq '1' }">무제한</c:if>
				<c:if test="${ item.PEOPLE_GUBUN ne '1' }">${ item.PEOPLE_CNT }명</c:if>
			</strong></span>
			<br/>					
		</c:forEach>		
		<br/>
        <table class="table01">
        <tr>
          <th width="10%">검색</th>
          <td>
			<select name="SEARCHOPTIONNO" id="SEARCHOPTIONNO">
				<option value="">전체</option>
				<c:forEach items="${option1List}" var="item" varStatus="loop">
					<option value="${item.OPTION_NO}" <c:if test="${params.SEARCHOPTIONNO eq item.OPTION_NO }">selected="selected"</c:if>>${item.OPTION_NO}</option>					
				</c:forEach>
			</select>
			
			<label for="SEARCHKEYWORD"></label>
			<input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="40"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
          </td>
		  <th width="15%">화면출력건수</th>
		  <td width="20%">
               	<input size="5" title="검색" type="text" id="rsltPageRow" name="rsltPageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.rsltPageRow }" onkeypress="fn_RowNumCheck()">
                <input name="textfield3" type="button" id="textfield3" value="검색" onclick="goList(1)"  >
          </td>          
        </tr>
    </table>           
    
    <ul class="boardBtns">
        <li><a href="javascript:fn_excel();">Excel</a></li>
		<li><a href="javascript:fn_view();">상세보기</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    
    <p>Total: ${rsltTotalCount} | page: ${rsltCurrentPage} / ${rsltPageRow}</p>
    
    <table class="table02">
      <tr>
        <th width="60">
			<!-- <input id="allCheck"  value="" type="checkbox" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />  -->
			번호
		</th>
        <th width="90">이름</th>
        <th width="100">아이디</th>
        <th width="100">전화번호</th>
        <th width="90">이메일</th>
        <th width="90">신청일자</th>
        <th width="60">신청구분</th>
        <th width="60">관심직렬</th>
      </tr>
      	<c:if test="${not empty list}">
	      <c:forEach var="item" items="${list}" varStatus="status">
		      <tr>
		        <td>
		        	<!-- <input type="checkbox" name="DEL_ARR" value="${item.SEQ},${item.BANNER_IMAGE},${item.BANNER_THUMBNAIL_IMAGE}"/> -->
		        	${rsltTotalCount - (( rsltCurrentPage - 1) * rsltPageRow) - status.index}</td>
		        <td>${item.USER_NAME }</td>
		        <td>${item.USER_ID }</td>
		        <td>${item.PHONE_NO }</td>
		        <td>${item.EMAIL }</td>
		        <td>${item.REG_DT }</td>
		        <td>${item.OPTION_NO }</td>
		        <td>${item.CATEGORY_INFO }</td>
		      </tr>
	      </c:forEach>
	    </c:if>
      
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="7">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
    </table>   
    
	<!-- paginate-->
		<c:if test="${not empty list}">
			<c:out value="${pagingHtml}" escapeXml="false" />
		</c:if>
	<!--//paginate-->
    
    <ul class="boardBtns">
        <li><a href="javascript:fn_excel();">Excel</a></li>
    	<li><a href="javascript:fn_view();">상세보기</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    
 	</form>
</div>
  <!--//content --> 