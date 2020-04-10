<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>eGovFrame 공통 컴포넌트</title>
<link href="<c:url value='/css/egovframework/com/cmm/main.css' />" rel="stylesheet" type="text/css">
<style type="text/css">
link { color: #666666; text-decoration: none; }
link:hover { color: #000000; text-decoration: none; }
</style>
</head>
<body>
<div id="lnb">
	<ul class="lnb_title">
		<li><strong class="left_title_strong"><strong class="top_title_strong">포털메인화면</strong></strong></li>
	</ul>
		<ul class="2depth">
			<li><a href="/sym/mnu/mpm/EgovMainMenuHome.do" target="_content" class="link"> 1. 포털(예제) 메인화면</a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/menu/EgovMenuListSelect.do" target="_content" class="link"> 1090. 메뉴리스트관리<!-- 메뉴리스트관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olh/faq/selectFaqList.do" target="_content" class="link"> 540. FAQ관리<!-- FAQ관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olh/qna/selectQnaList.do" target="_content" class="link"> 550. Q&A관리<!-- Q&amp;A관리 --></a></li>
		</ul>
	<ul class="lnb_title">
		<li><strong class="left_title_strong"><strong class="top_title_strong">보안</strong></strong><!-- 보안 --></li>
	</ul>
		<ul class="2depth">
			<li><a href="/sec/ram/EgovAuthorList.do" target="_content" class="link"> 60. 권한관리<!-- 권한관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sec/rgm/EgovAuthorGroupList.do" target="_content" class="link"> 70. 권한그룹관리<!-- 권한그룹관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sec/gmt/EgovGroupList.do" target="_content" class="link"> 80. 그룹관리<!-- 그룹관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sec/rmt/EgovRoleList.do" target="_content" class="link"> 90. 롤관리<!-- 롤관리 --></a></li>
		</ul>
	<ul class="lnb_title">
		<li><strong class="left_title_strong"><strong class="top_title_strong">통계/리포팅</strong></strong><!-- 통계/리포팅 --></li>
	</ul>
		<ul class="2depth">
		<li><a href="/sts/bst/selectBbsStats.do" target="_content" class="link"> 120. 게시물통계<!-- 게시물통계 --></a></li>
		</ul>
		<ul class="2depth">
		<li><a href="/sts/ust/selectUserStats.do" target="_content" class="link"> 130. 사용자통계<!-- 사용자통계 --></a></li>
		</ul>
		<ul class="2depth">
		<li><a href="/sts/cst/selectConectStats.do" target="_content" class="link"> 140. 접속통계<!-- 접속통계 --></a></li>
		</ul>
		<ul class="2depth">
		<li><a href="/sts/sst/selectScrinStats.do" target="_content" class="link"> 150. 화면통계<!-- 화면통계 --></a></li>
		</ul>
	<ul class="lnb_title">
		<li><strong class="left_title_strong"><strong class="top_title_strong">협업</strong></strong><!-- 협업 --></li>
	</ul>
		<ul class="2depth">
			<li><a href="/cop/tpl/selectTemplateInfs.do" target="_content" class="link"> 200. 템플릿관리<!-- 템플릿관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/cop/sms/selectSmsList.do" target="_content" class="link"> 310. 문자메시지<!-- 문자메시지 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/cop/smt/sdm/EgovDeptSchdulManageList.do" target="_content" class="link"> 320. 부서일정관리<!-- 부서일정관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/cop/smt/sim/EgovIndvdlSchdulManageList.do" target="_content" class="link"> 330. 일정관리<!-- 일정관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/cop/smt/dsm/EgovDiaryManageList.do" target="_content" class="link"> 340. 일지관리<!-- 일지관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/cop/smt/sam/EgovAllSchdulManageList.do" target="_content" class="link"> 350. 전체일정관리<!-- 전체일정관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/cop/ems/insertSndngMailView.do" target="_content" class="link"> 360. 메일발송<!-- 메일발송 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/cop/ems/selectSndngMailList.do" target="_content" class="link"> 361. 발송메일내역<!-- 발송메일내역 --></a></li>
		</ul>
	<ul class="lnb_title">
			<li>
				<strong class="left_title_strong"><strong class="top_title_strong">사용자지원</strong></strong><!-- 사용자지원 -->
			</li>
	</ul>
		<ul class="2depth">
			<li><a href="/uss/umt/EgovUserManage.do" target="_content" class="link"> 460. 업무사용자관리<!-- 업무사용자관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/umt/dpt/selectDeptManageListView.do" target="_content" class="link"> 461. 부서관리<!-- 부서관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olp/qmc/EgovQustnrManageList.do" target="_content" class="link"> 590. 설문관리<!-- 설문관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olp/qnn/EgovQustnrRespondInfoManageList.do" target="_content" class="link"> 600. 설문조사<!-- 설문조사 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olp/qtm/EgovQustnrTmplatManageList.do" target="_content" class="link"> 610. 설문템플릿관리<!-- 설문템플릿관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olp/qrm/EgovQustnrRespondManageList.do" target="_content" class="link"> 620. 응답자관리<!-- 응답자관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olp/qqm/EgovQustnrQestnManageList.do" target="_content" class="link"> 630. 질문관리<!-- 질문관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olp/qim/EgovQustnrItemManageList.do" target="_content" class="link"> 640. 항목관리<!-- 항목관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olp/opm/listOnlinePollManage.do" target="_content" class="link"> 660. 온라인poll관리<!-- 온라인poll관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/olp/opp/listOnlinePollPartcptn.do" target="_content" class="link"> 661. 온라인poll참여<!-- 온라인poll참여 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/pwm/listPopup.do" target="_content" class="link"> 720. 팝업창관리<!-- 팝업창관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/noi/selectNotificationList.do" target="_content" class="link"> 730. 정보알림이<!-- 정보알림이 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/bnr/selectBannerList.do" target="_content" class="link"> 740. 배너관리<!-- 배너관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/bnr/selectBannerMainList.do" target="_content" class="link"> 741. MYPAGE배너관리<!-- MYPAGE배너관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/tir/selectTwitterMain.do" target="_content" class="link"> 830. Twitter연동<!-- Twitter연동 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/fbk/facebook.do" target="_content" class="link"> 831. Facebook 연동<!-- Facebook 연동 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/ntm/registEgovNoteManage.do" target="_content" class="link"> 840. 쪽지관리<!-- 쪽지관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/ntr/listNoteRecptn.do" target="_content" class="link"> 850. 받은쪽지함관리<!-- 받은쪽지함관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/nts/listNoteTrnsmit.do" target="_content" class="link"> 860. 보낸쪽지함관리<!-- 보낸쪽지함관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/mtg/selectMtgPlaceManageList.do" target="_content" class="link"> 870. 회의실관리<!-- 회의실관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/uss/ion/mtg/selectMtgPlaceResveManageList.do" target="_content" class="link"> 871. 회의실예약관리<!-- 회의실예약관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/com/uss/ion/rmm/selectRoughMapList.do" target="_content" class="link"> 943. 약도 관리<!-- 약도 관리 --></a></li>
		</ul>
	<ul class="lnb_title">
			<li><strong class="left_title_strong"><strong class="top_title_strong">시스템관리</strong></strong><!-- 시스템관리 --></li>
	</ul>			
		<ul class="2depth">
			<li><a href="/sym/ccm/ccc/SelectCcmCmmnClCodeList.do" target="_content" class="link"> 960. 공통분류코드<!-- 공통분류코드 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do" target="_content" class="link"> 970. 공통상세코드<!-- 공통상세코드 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/ccm/cca/SelectCcmCmmnCodeList.do" target="_content" class="link"> 980. 공통코드<!-- 공통코드 --></a></li>
		</ul>
		<ul class="2depth">
		<li><a href="/sym/log/lgm/SelectSysLogList.do" target="_content" class="link"> 1030. 로그관리<!-- 로그관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/log/ulg/SelectUserLogList.do" target="_content" class="link"> 1040. 사용로그관리<!-- 사용로그관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/log/wlg/SelectWebLogList.do" target="_content" class="link"> 1070. 웹로그관리<!-- 웹로그관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/log/clg/SelectLoginLogList.do" target="_content" class="link"> 1080. 접속로그관리<!-- 접속로그관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/log/plg/SelectPrivacyLogList.do" target="_content" class="link"> 1085. 개인정보조회로그관리<!-- 개인정보조회로그관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/mnu/mpm/EgovMenuManageSelect.do" target="_content" class="link"> 1091. 메뉴관리리스트<!-- 메뉴관리리스트 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/mnu/mcm/EgovMenuCreatManageSelect.do" target="_content" class="link"> 1100. 메뉴생성관리<!-- 메뉴생성관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/mnu/stm/EgovSiteMapng.do" target="_content" class="link"> 1101. 사이트맵<!-- 사이트맵 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sym/prm/EgovProgramListManageSelect.do" target="_content" class="link"> 1111. 프로그램관리<!-- 프로그램관리 --></a></li>
		</ul>
		<ul class="2depth">
			<li><a href="/sec/pki/EgovCryptoInfo.do" target="_content" class="link"> 2200. 암호화/복호화<!-- 암호화/복호화 --></a></li>
		</ul>
		<ul class="2depth">
		<li><a href="/cop/msg/websocketMessengerView.do" target="_content" class="link"> 3200. 웹소켓 메신저<!-- 웹소켓 메신저 --></a></li>
		</ul>
</div>

</body>
</html>
