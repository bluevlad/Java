<%--
  Class Name : List.jsp
  Description : 사물함 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2024.05   rainend        video
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="box.title.boxInfo"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
</head>
<body>

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<video controls='true' height='360' loop='true' poster='https://res.cloudinary.com/demo/video/upload/c_pad,du_10,h_360,q_70,w_480/blue_sports_car.jpg' width='480'>
  <source src='https://res.cloudinary.com/demo/video/upload/c_pad,du_10,h_360,q_70,w_480/blue_sports_car.webm' type='video/webm'>
  <source src='https://res.cloudinary.com/demo/video/upload/c_pad,du_10,h_360,q_70,w_480/blue_sports_car.mp4' type='video/mp4'>
  <source src='https://res.cloudinary.com/demo/video/upload/c_pad,du_10,h_360,q_70,w_480/blue_sports_car.ogv' type='video/ogg'>
  Your browser does not support HTML5 video tags.
</video>


</body>
</html>
