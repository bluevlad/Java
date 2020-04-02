<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>

<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

	<h2>● 강의 관리 > <strong>국가고시 해설강의 관리</strong></h2>	

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th>
				<label for="GOSI_CD"></label>
				<select name="GOSI_CD" id="GOSI_CD" onchange="fn_List()">
				<option value="">--분류 선택--</option>
				<c:forEach items="${gosi_list}" var="item" varStatus="loop">
					<option value="${item.GOSI_CD}" <c:if test="${GOSI_CD == item.GOSI_CD }">selected="selected"</c:if>>${item.GOSI_NM}</option>
				</c:forEach>
				</select>
            </th>
		</tr>
	</table>
    <!-- //검색 -->

   	<table class="table02">
		<tr>
  			<th>순서</th>
   			<th>과목</th>
   			<th>교수</th>
   			<th>제목</th>
  			<th>해설특강</th>
  			<th>해설자료</th>
  			<th>공개여부</th>
  		</tr>
		<c:forEach items="${list}" var="item" varStatus="loop">
  		<input type="hidden" id="PRF_ID" name="PRF_ID" value="${item.PRF_ID}" />
  		<input type="hidden" id="T_NO_${item.PRF_ID}_${loop.index}" name="T_NO_${item.PRF_ID}_${loop.index}" value="${item.T_NO}" />
   		<tr>
  			<td><input type="text" id="IDX_${item.PRF_ID}_${loop.index}" name="IDX_${item.PRF_ID}_${loop.index}" value="${item.IDX}" style="width:20px;background:#FFECEC;"/></td>
  			<td>${item.SBJ_NM}</td>
  			<td>${item.PRF_NM}</td>
  			<td><input type="text" id="TITLE_${item.PRF_ID}_${loop.index}" name="TITLE_${item.PRF_ID}_${loop.index}" value="${item.TITLE}" style="width:250px;background:#FFECEC;"/></td>
  			<td><input type="text" id="VOD_URL_${item.PRF_ID}_${loop.index}" name="VOD_URL_${item.PRF_ID}_${loop.index}" value="${item.VOD_URL}" style="width:250px;background:#FFECEC;"/></td>
  			<td>
	  			<input type="text" id="FILE_URL_${item.PRF_ID}_${loop.index}" name="FILE_URL_${item.PRF_ID}_${loop.index}" value="${item.FILE_URL}" style="width:250px;background:#FFECEC;"/>
  			</td>
  			<td>
				<select id="ISUSE_${item.PRF_ID}_${loop.index}" name="ISUSE_${item.PRF_ID}_${loop.index}">
					<option value="Y" <c:if test="${item.ISUSE eq 'Y' }">selected="selected"</c:if>>활성</option>
					<option value="N" <c:if test="${item.ISUSE eq 'N' }">selected="selected"</c:if>>비활성</option>
				</select>
  			</td>
  		</tr>
  		</c:forEach>
	</table>
    
    <!--버튼-->
	<ul class="boardBtns">
    	<li><a href="javascript:fn_Submit();">수정</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 

<script type="text/javascript">
function fn_Submit(){
	if(confirm("수정하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/vod_update.do' />");
		$("#frm").submit();
	}
}

function fn_List(){
	$("#frm").attr("action","<c:url value='/gosi/vod_modify.do' />");
	$("#frm").submit();
}
</script>
</body>
</html>