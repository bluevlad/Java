<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.com.cmm.util.EgovUserDetailsHelper" %>
<script src="<c:url value='/js/egovframework/com/main.js' />"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/main_portal.css' />">
<script type="text/javascript">
	function actionLogout(){
		document.selectOne.action = "<c:url value='/login/actionLogout.do'/>";
		document.selectOne.submit();
	}
</script>

<body topmargin="0" leftmargin="0">
<form name="selectOne">
<input name="menuNo" type="hidden" />
<input name="chkURL" type="hidden" />

	<c:if test="${loginVO == null }">
		<jsp:forward page="/login/egovLoginUsr.do"/>
	</c:if>

	<c:if test="${loginVO != null}">

		<!-- header -->
	    <div id="new_topnavi">
	        <ul>
				   <li class="gap"> l </li>
	               <li><a href="javascript:actionLogout()"><img src="<c:url value='/images/egovframework/com/cmm/main/logout_btn.gif' />" alt="로그아웃" /></a></li>
	        </ul>
	    </div>

		<!-- contents -->
		<div>
	
			<!-- 상단 -->
			<div class="mp_top">
				<div class="l"><!-- left layout -->
					<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.selectBBSListPortlet"/></h3><!-- 생성된 게시판 목록 -->
					<div style="height:150px">
						<c:import url="/cop/bbs/selectBBSListPortlet.do" />
					</div>
					<!-- 나의일정관리 -->
					<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.indvdlSchdulManageMainList"/></h3><!-- 나의일정관리 -->
					<div style="height:150px">
					<c:import charEncoding="utf-8" url="/cop/smt/sim/EgovIndvdlSchdulManageMainList.do" ></c:import>
					</div>
				</div>
			</div>
			
		</div>
		<!-- contents -->

	</c:if>
</form>
</body>
