<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
function fn_List(){
	$("#frm").attr("action", "<c:url value='/memberManagement/memberCoopList.do' />");
	$("#frm").submit();
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
<input type="hidden" id="USER_ID" name="USER_ID" value="${detail.USER_ID}">

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

	<h2>● 회원관리 > <strong>회원조회</strong></h2>	
    	<table class="table01">
   		<tr>
   			<th>회원아이디</th>
  			<td>
	   			${detail.USER_ID}
  			</td>
  		</tr>  		
   		<tr>
   			<th>성명</th>
  			<td>${detail.USER_NM}</td>
  		</tr>  		
   		<tr>
   			<th>생년월일</th>
  			<td>${detail.BIRTH_DAY}</td>
  		</tr>
   		<tr>
   			<th>이메일</th>
  			<td>${detail.EMAIL}</td>
  		</tr>
		<tr>
   			<th>관심분야</th>
  			<td>
				<c:forEach items="${categoryList}" var="item" varStatus="loop">
   					<c:set var="CHECKEDSET" value="" />
   					<c:if test="${item.CODE eq detail.CATEGORY_CODE}" ><c:set var="CHECKEDSET" value="checked='checked'" /></c:if> 
   					<input type="radio" name="CATEGORY_CODE" value="${item.CODE}" ${CHECKEDSET} />${item.NAME} &nbsp;
   				</c:forEach>
  			</td>
  		</tr>		
  		<tr>
   			<th>상태</th>
   			<td>
   					<label for="ISUSE"></label>
   					<select  id="ISUSE" name="ISUSE">
						<option value="Y"  <c:if test="${detail.ISUSE == 'Y'}">selected</c:if>>활성</option>
						<option value="N" <c:if test="${detail.ISUSE == 'N'}">selected</c:if>>비활성</option>
					</select>
   			</td>
   		</tr>
   		<tr>
   			<th>핸드폰</th>
  			<td>
	   			<input type="text" id="PHONE_NO" name="PHONE_NO" value="${detail.PHONE_NO}" size="20"  maxlength="20" title="핸드폰" style="width:18%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
  			</td>
  		</tr>
   		<tr>
   			<th>자택주소</th>
  			<td colspan=3>
	   			<input type="text" id="ZIP_CODE" name="ZIP_CODE" value="${detail.ZIP_CODE}"  readonly="readonly" title="우편번호" style="width:50px;ime-mode:disabled;"/>&nbsp;
  	   			<input type="text" id="ADDRESS1" name="ADDRESS1" value="${detail.ADDRESS1}"  readonly="readonly"  title="자택주소" style="width:220px;ime-mode:disabled;"/>&nbsp;
  				<input type="text" id="ADDRESS2" name="ADDRESS2" value="${detail.ADDRESS2}" title="자택주소 상세" style="width:250px;ime-mode:disabled;" "/>
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
