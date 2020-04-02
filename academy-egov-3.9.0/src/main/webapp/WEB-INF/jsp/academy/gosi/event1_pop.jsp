<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">

</script>
</head>
<body>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<div><img style="width:500px;height:200px;" src = "/imgFileView.do?path=/${params.FILE_PATH}"></div>
</div>
<!--//content --> 
</body>
</html>