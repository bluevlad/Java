<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<c:forEach items="${subjectteacherlist}" var="item" varStatus="loop">
		<c:set var="CHECKSET" value="" />
		<c:if test="${item.SUBJECT_CD eq params.SUBJECT_SJT_CD and item.USER_ID eq params.SUBJECT_TEACHER}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>				
		<li>${item.SUBJECT_NM}(${item.USER_NM})<span><input name="SUBJECT_INFO_ARR" type="radio" value="${item.SUBJECT_CD}#${item.USER_ID}#${item.PAYMENT}" ${CHECKSET}/></span></li>
	</c:forEach>