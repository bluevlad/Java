<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovLoginUsr.jsp
  * @Description : Login 인증 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.03    박지욱          최초 생성
  * @ 2011.09.25    서준식          사용자 관리 패키지가 미포함 되었을때에 회원가입 오류 메시지 표시
  * @ 2011.10.27    서준식          사용자 입력 탭 순서 변경
  * @ 2017.07.21    장동한 	    	로그인인증제한 작업
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.03
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="comUatUia.title" /></title><!-- 로그인 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uat/uia/login.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
</head>
<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="login_form">
	<fieldset>
	<c:set var="naver_url" value="https://nid.naver.com/oauth2.0/authorize?response_type=code"/>
	<c:set var="clientId" value="eMqIM7rjmaGVoKLIwWEZ"/>
	<c:set var="redirectURI" value="https://localhost/api/naver/callback"/>
	<c:set var="state" value="CmhCRWqZBa"/>
	
	<a href="${naver_url}&client_id=${clientId}&redirect_uri=${redirectURI}&state=${state}"><img width="300" src="<c:url value='/images/egovframework/com/uat/uia/naver-login.png'/>" alt="Naver Login" /></a>
	<br><br>
</div>

</body>
</html>