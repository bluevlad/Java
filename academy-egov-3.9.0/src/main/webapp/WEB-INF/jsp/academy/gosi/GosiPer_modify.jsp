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

	<h2>● 강의 관리 > <strong>합격예측 분포도</strong></h2>
	<select name="GOSI_CD" id="GOSI_CD" onchange="fn_List()">
		<option value="">--분류 선택--</option>
		<c:forEach items="${gosi_list}" var="item" varStatus="loop">
			<option value="${item.GOSI_CD}" <c:if test="${params.GOSI_CD == item.GOSI_CD }">selected="selected"</c:if>>${item.GOSI_NM}</option>
		</c:forEach>
	</select>
	<c:if test="${gosi_info.IS_USE eq 'Y'}">
		<ul class="boardBtns">
	    	<li><a href="javascript:fn_UpdatePer();">총점,과목별 분포도 계산</a></li>
	    </ul>
    </c:if>
    
    <h3>-총점 성적 분포</h3>
   	<table class="table01">
		<tr>
  			<th>0~100</th>
  			<th>101~200</th>
  			<th>201~300</th>
  			<th>301~400</th>
   			<th>401~500</th>
  		</tr>
		<c:forEach items="${list}" var="item" varStatus="loop">
   		<tr>
  			<td><input type="text" id="SCORE_1_PER" name="SCORE_1_PER" value="${item.SCORE_1_PER}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="SCORE_2_PER" name="SCORE_2_PER" value="${item.SCORE_2_PER}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="SCORE_3_PER" name="SCORE_3_PER" value="${item.SCORE_3_PER}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="SCORE_4_PER" name="SCORE_4_PER" value="${item.SCORE_4_PER}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="SCORE_5_PER" name="SCORE_5_PER" value="${item.SCORE_5_PER}" style="width:300px;background:#FFECEC;"/></td>
  		</tr>
  		</c:forEach>
	</table>
	<br/>
	<br/>
	<br/>
	<h3>-과목별 분포</h3>
	<table class="table01">
		<tr>
			<th>과목별</th>
  			<th>0~100</th>
  			<th>101~200</th>
  			<th>201~300</th>
  			<th>301~400</th>
   			<th>401~500</th>
  		</tr>
		<c:forEach items="${list2}" var="item" varStatus="loop">
		<input type="hidden" id="GOSI_SUBJECT" name="GOSI_SUBJECT" value="${item.SUBJECT_CD}" />
   		<tr>
   			<td>${item.SUBJECT_NM}</td>
  			<td><input type="text" id="SBJ_1_PER_${item.SUBJECT_CD}" name="SBJ_1_PER_${item.SUBJECT_CD}" value="${item.SCORE_1_PER}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="SBJ_2_PER_${item.SUBJECT_CD}" name="SBJ_2_PER_${item.SUBJECT_CD}" value="${item.SCORE_2_PER}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="SBJ_3_PER_${item.SUBJECT_CD}" name="SBJ_3_PER_${item.SUBJECT_CD}" value="${item.SCORE_3_PER}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="SBJ_4_PER_${item.SUBJECT_CD}" name="SBJ_4_PER_${item.SUBJECT_CD}" value="${item.SCORE_4_PER}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="SBJ_5_PER_${item.SUBJECT_CD}" name="SBJ_5_PER_${item.SUBJECT_CD}" value="${item.SCORE_5_PER}" style="width:300px;background:#FFECEC;"/></td>
  		</tr>
  		</c:forEach>
	</table>
	<ul class="boardBtns">
    	<li><a href="javascript:fn_Submit();">수정</a></li>
    </ul>
     
</form>
</div>
<!--//content --> 

<script type="text/javascript">
function fn_List(){
	$("#frm").attr("action","<c:url value='/gosi/GosiPer_modify.do' />");
	$("#frm").submit();
}

function fn_Submit(){
	if(confirm("수정하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/GosiPer_update.do' />");
		$("#frm").submit();
	}
}

function fn_UpdatePer(){
	if(confirm("분포도 업데이트하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/makeGosiPer.do' />");
		$("#frm").submit();
	}
}
</script>
</body>
</html>