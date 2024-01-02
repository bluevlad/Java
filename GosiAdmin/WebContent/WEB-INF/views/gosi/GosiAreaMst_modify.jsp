<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>

<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
	
	<div align="right">
		<select id="GOSI_CD" name="GOSI_CD" onchange="fn_List()">
		<c:forEach items="${gosi_list}" var="item" varStatus="loop">
			<option value="${item.GOSI_CD}" <c:if test="${params.GOSI_CD eq item.GOSI_CD}">selected="selected"</c:if>>${item.GOSI_NM}</option>
		</c:forEach>
		</select>
	</div><br>
	
   	<table class="table01">
		<tr>
  			<th>사용여부</th>
  			<th>직렬</th>
   			<th>선발인원</th>
   			<th>접수인원</th>
   			<th>경쟁률</th>
  			<th>2016년합격선</th>
  			<th>2016년경쟁률</th>
  			<th>2017합격선시작</th>
  			<th>2017합격선종료</th>
  		</tr>
		<c:forEach items="${list}" var="item" varStatus="loop">
  		<input type="hidden" id="GOSI_GUBUN" name="GOSI_GUBUN" value="${item.GOSI_TYPE}${item.GOSI_AREA}" />
  		<input type="hidden" id="GOSI_TYPE_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_TYPE_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_TYPE}" />
  		<input type="hidden" id="GOSI_AREA_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_AREA_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_AREA}" />
   		<tr>
    		<td> 
				<select name="ISUSE_${item.GOSI_TYPE}${item.GOSI_AREA}" id="ISUSE_${item.GOSI_TYPE}${item.GOSI_AREA}">
                    <option value="Y" <c:if test="${item.ISUSE == 'Y' }">selected="selected"</c:if>>활 성</option>
                    <option value="N" <c:if test="${item.ISUSE == 'N' }">selected="selected"</c:if>>비활성</option>
				</select>
    		</td>
  			<td><input type="text" id="GOSI_AREA_FULL_NM_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_AREA_FULL_NM_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_AREA_FULL_NM}" style="width:300px;background:#FFECEC;"/></td>
  			<td><input type="text" id="REQ_NUM_${item.GOSI_TYPE}${item.GOSI_AREA}" name="REQ_NUM_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.REQ_NUM}" style="width:50px;background:#FFECEC;"/></td>
  			<td><input type="text" id="USE_NUM_${item.GOSI_TYPE}${item.GOSI_AREA}" name="USE_NUM_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.USE_NUM}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="GOSI_CMP_STAT_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_CMP_STAT_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_CMP_STAT}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="GOSI_CMP_STAT_2016_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_CMP_STAT_2016_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_CMP_STAT_2016}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="PASS_2016_${item.GOSI_TYPE}${item.GOSI_AREA}" name="PASS_2016_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.PASS_2016}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="PASS_2017_S_${item.GOSI_TYPE}${item.GOSI_AREA}" name="PASS_2017_S_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.PASS_2017_S}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="PASS_2017_E_${item.GOSI_TYPE}${item.GOSI_AREA}" name="PASS_2017_E_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.PASS_2017_E}" style="width:60px;background:#FFECEC;"/></td>
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
		$("#frm").attr("action","<c:url value='/gosi/GosiAreaMst_update.do' />");
		$("#frm").submit();
	}
}

function fn_List(){
	$("#frm").attr("action","<c:url value='/gosi/GosiAreaMst_modify.do' />");
	$("#frm").submit();
}
</script>
</body>
</html>