<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">
<script type="text/javascript">
	alert('${MESSAGE}');
	history.go(-1);
	//location.href= '/boardCounselRoom/counselRoom_list_layout.html?topMenu=${topMenu}&topMenuType=${topMenuType}&topMenuName=${topMenuName}&BOARD_MNG_SEQ=BOARD_000&BOARDTYPE=counselRoom';
</script>
</head>
<body>
</body>
</html>