<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
<link rel="stylesheet" href="<c:url value='/assets/css/common.css'/>">
<title>dataAccessFailure</title>
</head>
<body>
<div class="errorWrap">
    <div class="up">
        <img src="/assets/img/common/er_no500.png" alt="서버 일시 오류 서버 과부하 또는 예기치 못한 오류로 인해 서비스가 일시적으로 중단되었습니다.입력하신 정보를 다시 한 번 확인해 주세요.문제가 지속될 경우 고객센터로 연락주시면 안내해 드리겠습니다.">
    </div>
    <div class="down">
        <div class="inner">
            <img src="/assets/img/common/er_cs.png" alt="1544-5006 평일 9:00 ~ 18:00 토요일 9:00 ~ 13:00">
        </div>
        <div class="btns">
            <a href="javascript:history.go(-1);"><img src="/assets/img/common/er_btn1.png" alt="이전화면"></a>
            <a href="/main/index.html"><img src="/assets/img/common/er_btn2.png" alt="메인화면"></a>
        </div>
    </div>
</div>
</body>
</html>
