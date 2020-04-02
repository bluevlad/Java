<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Script-Type" content="text/javascript" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title><decorator:title default="WILLBES" /></title>
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />   
    
    <link href="<c:url value="/resources/css/skin/blue.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/libs/jquery-ui/css/redmond/jquery-ui-1.8.18.custom.css" />" rel="stylesheet" type="text/css"  />
    <link href="<c:url value="/resources/libs/jquery-timepicker/jquery.ui.timepicker.css" />" rel="stylesheet" type="text/css"  />
    <link href="<c:url value="/resources/css/paginate.css" />" type="text/css" rel="stylesheet" />
        
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery-ui/jquery-ui-1.8.18.custom.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.tmpl.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.form.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.cookie.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.hoverIntent.minified.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.dcverticalmegamenu.1.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.dcjqaccordion.2.7.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.validator.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/jquery.ui.timepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.position.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.widget.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.tabs.min.js" />"></script>
    
    <script type="text/javascript" src="<c:url value="/resources/js/commons.js" />"></script>
    <script type="text/javascript">
    $(function(){
         $.datepicker.regional['ko'] = {
              closeText: '닫기',
              prevText: '이전',
              nextText: '다음',
              currentText: '오늘',
              monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
              monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
              dayNames: ['일','월','화','수','목','금','토'],
              dayNamesShort: ['일','월','화','수','목','금','토'],
              dayNamesMin: ['일','월','화','수','목','금','토'],
              dateFormat: 'yymmdd',
              firstDay: 0,
              showMonthAfterYear: true,
              yearSuffix: '년 ',
              autoSize: false};
         $.datepicker.setDefaults($.datepicker.regional['ko']);
    });
    </script>
<decorator:head />
</head>

<body class="pop">

<decorator:body />
</body>
</html>