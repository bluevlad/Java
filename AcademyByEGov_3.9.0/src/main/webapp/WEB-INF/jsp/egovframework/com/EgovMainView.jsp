<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="<c:url value='/js/egovframework/com/main.js' />"></script>

<!-- contents -->
<div>
	
	<!-- 상단 -->
	<div class="mp_top">
		<div class="l"><!-- left layout -->
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.selectBBSListPortlet"/></h3><!-- 생성된 게시판 목록 -->
			<div style="height:150px">
				<c:import url="/cop/bbs/selectBBSListPortlet.do" />
			</div>
			
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.selectCommuMasterListPortlet"/></h3><!-- 생성된 커뮤니티 목록 -->
			<div style="height:150px">
				<c:import url="/cop/cmy/selectCommuMasterListPortlet.do" />
			</div>
			
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.selectBlogListPortlet"/></h3><!-- 생성된 블로그 목록 -->
			<div style="height:181px">
				<c:import url="/cop/bbs/selectBlogListPortlet.do" />
			</div>
		</div>
		
		<div class="r"><!-- right layout -->
			<!-- 부서일정관리  -->
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.deptSchdulManageMainList"/></h3><!-- 부서일정관리 -->
			<div style="height:150px">
			<c:import charEncoding="utf-8" url="/cop/smt/sdm/EgovDeptSchdulManageMainList.do" ></c:import>
			</div>
			
			<!-- 나의일정관리 -->
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.indvdlSchdulManageMainList"/></h3><!-- 나의일정관리 -->
			<div style="height:150px">
			<c:import charEncoding="utf-8" url="/cop/smt/sim/EgovIndvdlSchdulManageMainList.do" ></c:import>
			</div>
			
		</div>
	</div>
	
	<!-- 배너 -->
	<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.getBannerImage"/></h3><!-- 배너 -->
	<div class="mp_bn">
		<c:import url="/uss/ion/bnr/getBannerImage.do" charEncoding="utf-8">
			<c:param name="atchFileId" value="${banner.bannerImageFile}" />
		</c:import>
	</div>

</div><!-- contents -->
