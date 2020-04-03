<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  last modified 2014-08-20 -->
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
function fn_Device_Reset(seq) {
	if(confirm("선택한 항목을 정말 초기화 하시겠습니까?")){
		$("#SEQ").val(seq);
		$("#frm").attr("action", "<c:url value='/macaddressmanager/Pop_macaddressUpdate.do' />");
		$("#frm").submit();
		
		//fn_reload();
	}
}
function fn_Device_Reset1(seq) {
	if(confirm("선택한 항목을 정말 초기화 하시겠습니까?")){
		$("#SEQ").val(seq);
		$("#frm").attr("action", "<c:url value='/macaddressmanager/Pop_macaddressUpdate1.do' />");
		$("#frm").submit();
		
		//fn_reload();
	}
}

function fn_reload(){
	opener.parent.location='<c:url value='/macaddressmanager/list.do' />';
	//window.close();
}

</script>
</head>
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="SEQ" name="SEQ" value="">
<input type="hidden" id="V_USER_ID" name="V_USER_ID" value="${params.V_USER_ID }">
<table style="width:100%;">
<tr>
	<td width="2%"></td>
	<td>
	  
	<table style="width:100%; border:0; cellspacing:0; cellpadding:12px;">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><h2>▣ 선택정보</h2></td>
      </tr>
    </table>
    <!--테이블-->
     <table class="table05">
        <tr>
          	<th>구분</th>
			<th>KEY</th>
			<th>등록일</th>
			
        </tr>
        
        <c:set var="check_value" value="0"/>
        <c:choose>
		<c:when test="${fn:length(view[0].DEVICE_GUBUN)> 0 }">
			<tr>
				<td>&nbsp;${view[0].DEVICE_GUBUN }</td>
				<td>&nbsp;${view[0].DEVICE_ID }</td>
				<c:if test="${view[0].DEVICE_GUBUN eq 'PC' }">
	            <td>&nbsp;<fmt:formatDate value="${view[0].PC_REG_DT}" pattern="yyyy-MM-dd"/></td>
	            </c:if>
				<c:if test="${view[0].DEVICE_GUBUN eq 'MO' }">
	            <td>&nbsp;<fmt:formatDate value="${view[0].MOBILE_REG_DT}" pattern="yyyy-MM-dd"/></td>
	            </c:if>
	        </tr>
			
			<c:set var="check_value" value="1"/>
		</c:when>
		<c:otherwise>
	        <tr>
				<td>&nbsp;${view[0].DEVICE_GUBUN }</td>
				<td>&nbsp;${view[0].DEVICE_ID }</td>
	            <c:if test="${view[0].DEVICE_GUBUN eq 'PC' }">
	            <td>&nbsp;<fmt:formatDate value="${view[0].PC_REG_DT}" pattern="yyyy-MM-dd"/></td>
	            </c:if>
				<c:if test="${view[0].DEVICE_GUBUN eq 'MO' }">
	            <td>&nbsp;<fmt:formatDate value="${view[0].MOBILE_REG_DT}" pattern="yyyy-MM-dd"/></td>
	            </c:if>
	        </tr>
	        <c:set var="check_value" value="1"/>
		</c:otherwise>
		</c:choose>
        
     </table>
    <!--//테이블-->
    <br>
    <!--//테이블-->
    <table style="width:100%;">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣초기화 내역</p></td>
        <td align="right"></td>
      </tr>
    </table>
    
    <!--테이블-->
     <table class="table05">
        <tr>
			<th>구분</th>
			<th>KEY</th>
			<th>초기화일시</th>
			<th>관리자ID</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="item" varStatus="status">
             <tr>
	            <td>${item.DEVICE_GUBUN }</td>
	            <td>${item.DEVICE_ID }</td>
				<c:if test="${item.DEVICE_GUBUN eq 'PC' }">
	            <td>&nbsp;<fmt:formatDate value="${item.PC_CANCEL_DT}" pattern="yyyy-MM-dd"/></td>
	            </c:if>
				<c:if test="${item.DEVICE_GUBUN eq 'MO' }">
	            <td>&nbsp;<fmt:formatDate value="${item.MOBILE_CANCEL_DT}" pattern="yyyy-MM-dd"/></td>	            
	            </c:if>
	            <td>${item.ADMIN_ID }</td>
	        </tr>
      </c:forEach>
		</c:if>
		
		<c:if test="${empty list}">
             <tr>
	            <td colspan="4">조회된 내용이 없습니다.</td>
	        </tr>
		</c:if>
     </table>
     <!--//테이블-->
     
     <!--버튼-->    
    <ul class="boardBtns">
        <li>
        <c:choose>
		<c:when test="${view[0].PC_USEYN eq 'Y' }">
			<%-- <a href="#" onclick="javascript:fn_Device_Reset('${view[0].SEQ}');" >초기화(PC)</a> --%>
		</c:when>
		<c:otherwise>
	         
		</c:otherwise>
		</c:choose>
        <c:choose>
		<c:when test="${view[0].MOBILE_USEYN eq 'Y' }">
			<%-- <a href="#" onclick="javascript:fn_Device_Reset1('${view[0].SEQ}');" >초기화(MOIBLE)</a> --%>
		</c:when>
		<c:otherwise>
	         
		</c:otherwise>
		</c:choose>
        
       
        
        </li>
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->
	</td>
</tr>
</table>

</html>