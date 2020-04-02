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

	<h2>● 강의 관리 > <strong>직렬별 예상 합격선 등록</strong></h2>
		<select name="GOSI_CD" id="GOSI_CD" onchange="fn_List()">
			<option value="">--분류 선택--</option>
			<c:forEach items="${gosi_list}" var="item" varStatus="loop">
				<option value="${item.GOSI_CD}" <c:if test="${params.GOSI_CD == item.GOSI_CD }">selected="selected"</c:if>>${item.GOSI_NM}</option>
			</c:forEach>
		</select>
	<c:if test="${gosi_info.IS_USE eq 'Y'}">
		<ul class="boardBtns">
			<li>시험분석노출 직렬: 
				<c:forEach items="${analist}" var="item" varStatus="loop">
					<input name="CH_IS_SHOW" type="checkbox" value="Y_${item.GOSI_TYPE}" <c:if test="${item.IS_SHOW == 'Y'}">checked="checked"</c:if>/>
					${item.GOSI_AREA_FULL_NM}
				</c:forEach>
			</li>
	    	<li><a href="javascript:fn_UpdateAna();">시험분석노출수수정</a></li>
	    </ul>
    </c:if>
   	<table class="table01">
		<tr>
  			<th rowspan="2">사용여부</th>
  			<th rowspan="2">집계여부</th>
  			<th rowspan="2">지역</th>
  			<th rowspan="2">직렬</th>
   			<th rowspan="2">선발인원</th>
   			<th rowspan="2">접수인원</th>
   			<th rowspan="2">경쟁률</th>
   			<th rowspan="2">합격가능석차</th>
  			<th rowspan="2">전년도 경쟁률</th>
  			<th rowspan="2">전년도 1차합격선</th>  
  			<th rowspan="2">전체평균점수</th>
  			<th rowspan="2">상위5%평균점수</th>	
  			<th rowspan="2">채점수/등록수</th>
		  	<th colspan="2">합격가능선 최고점</th>
	  		<th colspan="2">합격가능선 최저점</th>
	  	</tr>
  		 <tr>
		    <th>1 배수</th>
		    <th>점수</th>
		    <th>1.9배수</th>
		    <th>점수</th>
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
    		<td> 
				<select name="SHOW_STAT_${item.GOSI_TYPE}${item.GOSI_AREA}" id="SHOW_STAT_${item.GOSI_TYPE}${item.GOSI_AREA}">
                    <option value="Y" <c:if test="${item.SHOW_STAT == 'Y' }">selected="selected"</c:if>>활 성</option>
                    <option value="N" <c:if test="${item.SHOW_STAT == 'N' }">selected="selected"</c:if>>비활성</option>
				</select>
    		</td>
    		<td>${item.GOSI_AREA_NM}</td>
  			<td><input type="text" id="GOSI_AREA_FULL_NM_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_AREA_FULL_NM_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_AREA_FULL_NM}" style="width:100px;background:#FFECEC;"/></td>
  			<td><input type="text" id="REQ_NUM_${item.GOSI_TYPE}${item.GOSI_AREA}" name="REQ_NUM_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.REQ_NUM}" style="width:50px;background:#FFECEC;"/></td>
  			<td><input type="text" id="USE_NUM_${item.GOSI_TYPE}${item.GOSI_AREA}" name="USE_NUM_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.USE_NUM}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="GOSI_CMP_STAT_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_CMP_STAT_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_CMP_STAT}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="PASS_RANKING_${item.GOSI_TYPE}${item.GOSI_AREA}" name="PASS_RANKING_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.PASS_RANKING}" style="width:60px;background:#FFECEC;"/>%</td>
			<td><input type="text" id="GOSI_CMP_STAT_LAST_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_CMP_STAT_LAST_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_CMP_STAT_LAST}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="CAN_PASS_LAST_${item.GOSI_TYPE}${item.GOSI_AREA}" name="CAN_PASS_LAST_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.CAN_PASS_LAST}" style="width:60px;background:#FFECEC;"/></td>
  			<td><input type="text" id="AVR_POINT_${item.GOSI_TYPE}${item.GOSI_AREA}" name="AVR_POINT_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.AVR_POINT}" style="width:60px;"/></td>
  			<td><input type="text" id="GOSI_5_POINT_${item.GOSI_TYPE}${item.GOSI_AREA}" name="GOSI_5_POINT_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.GOSI_5_POINT}" style="width:60px;"/></td>
  			<td>${item.SCORE_CNT}/${item.USER_CNT}</td>
  			<td style="width:60px;">${item.PASS_1_POINT}</td>
			<td style="width:150px;">
				<c:set var="calL" value="+"/>
				<c:set var="minL" value="0"/>
				<select id="H_${item.GOSI_TYPE}${item.GOSI_AREA}" onchange="onScore('${item.PASS_1_POINT}','H_${item.GOSI_TYPE}${item.GOSI_AREA}')">
                    <option value="+" >+</option>
                    <option value="-" >-</option>
				</select>
				<input type="text" id="INPUT_H_${item.GOSI_TYPE}${item.GOSI_AREA}" value="" onKeyDown="onlyNumDecimalInput();" onKeyUp="onScore('${item.PASS_1_POINT}','H_${item.GOSI_TYPE}${item.GOSI_AREA}')" style="width:20px;"/>
				<span id="TX_H_${item.GOSI_TYPE}${item.GOSI_AREA}">
					<c:choose>
						<c:when test="${empty item.CAN_PASS_H}">${item.PASS_1_POINT}</c:when>
						<c:otherwise>${item.CAN_PASS_H}</c:otherwise>
					</c:choose>
				</span>
				<input type="hidden"  id="CAN_PASS_H_${item.GOSI_TYPE}${item.GOSI_AREA}" name="CAN_PASS_H_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.CAN_PASS_H}"/>
			</td>
			<td style="width:60px;">${item.PASS_1_9_POINT}</td>
			<td style="width:150px;">
				<select id="L_${item.GOSI_TYPE}${item.GOSI_AREA}" onchange="onScore('${item.PASS_1_9_POINT}','L_${item.GOSI_TYPE}${item.GOSI_AREA}')">
                    <option value="+">+</option>
                    <option value="-">-</option>
				</select>
				<input type="text" id="INPUT_L_${item.GOSI_TYPE}${item.GOSI_AREA}" value="" onKeyDown="onlyNumDecimalInput();" onKeyUp="onScore('${item.PASS_1_9_POINT}','L_${item.GOSI_TYPE}${item.GOSI_AREA}')" style="width:20px;"/>
				<span id="TX_L_${item.GOSI_TYPE}${item.GOSI_AREA}">
					<c:choose>
						<c:when test="${empty item.CAN_PASS_L}">${item.PASS_1_9_POINT}</c:when>
						<c:otherwise>${item.CAN_PASS_L}</c:otherwise>
					</c:choose>
				</span>
				<input type="hidden" id="CAN_PASS_L_${item.GOSI_TYPE}${item.GOSI_AREA}" name="CAN_PASS_L_${item.GOSI_TYPE}${item.GOSI_AREA}" value="${item.CAN_PASS_L}"/>
			</td>
  		</tr>
  		</c:forEach>
	</table>
    
    <!--버튼-->
	<!-- 
	<ul class="boardBtns">
    	<li><a href="javascript:fn_Submit();">수정</a></li>
    </ul>
	-->
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

function fn_UpdateAna(){
	if(confirm("시험분석 노출에 대해 수정하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/GosiAna_update.do' />");
		$("#frm").submit();
	}
}

function fn_List(){
	$("#frm").attr("action","<c:url value='/gosi/GosiAreaMst_modify.do' />");
	$("#frm").submit();
}

function onScore(score,el){
	if(score!=""&&score!=null){
		var can_score = 0;
		var cal = $("#"+el+" option:selected").val();
		var val = $("#INPUT_"+el).val();
		if(cal=='+'){
			can_score = parseFloat(parseFloat(score)+parseFloat(val)).toFixed(2) ;
		}else if(cal=='-'){
			can_score = parseFloat(parseFloat(score)-parseFloat(val)).toFixed(2) ;
		}
		if(cal!=''&&val!=''&&val!=null){
			$("#TX_"+el).text(can_score);
			$("#CAN_PASS_"+el).val(can_score);
		}
	}
}

//숫자만 소수점만 입력
function onlyNumDecimalInput(){
 var code = window.event.keyCode;
  if ((code >= 48 && code <= 57) || (code >= 96 && code <= 105) || code == 110 || code == 190 || code == 8 || code == 9 || code == 13 || code == 46){
  window.event.returnValue = true;
   return;
  }
  window.event.returnValue = false;
}
</script>
</body>
</html>