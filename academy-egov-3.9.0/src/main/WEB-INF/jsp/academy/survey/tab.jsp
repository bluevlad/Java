<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
function goTab(no) {
	var url = "TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${MENU_NM}";
	if (no ==1){
		location.href = "<c:url value='/survey/bank/list.do'/>?S_MENU=BANK&"+url;
	}else if (no == 2){
		location.href = "<c:url value='/survey/set/list.do'/>?S_MENU=SET&"+url;
	}else if (no == 3){
		location.href = "<c:url value='/survey/survey/list.do'/>?S_MENU=SURVEY&"+url;
	}else {
		location.href = "<c:url value='/survey/survey/list.do'/>?S_MENU=SURVEY&"+url;
	}
}
</script>  

<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

    <ul class="lecWritheTab">
    	<li><a href="javascript:goTab(1);" <c:if test="${params.S_MENU eq 'BANK'}">class="active"</c:if>>온라인설문문항관리</a></li>
    	<li><a href="javascript:goTab(2);" <c:if test="${params.S_MENU eq 'SET'}">class="active"</c:if>>온라인설문세트관리</a></li>
    	<li><a href="javascript:goTab(3);" <c:if test="${params.S_MENU eq 'SURVEY'}">class="active"</c:if>>온라인설문관리</a></li>
    </ul>       
	<br>