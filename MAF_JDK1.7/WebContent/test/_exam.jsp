<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=utf-8" />
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>문제은행관리</title>
<link href='/hkmca/css/maf_common.css' rel="stylesheet" type="text/css"></link>
<link href='/hkmca/css/kmc_common.css' rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src='/hkmca/dojo/dojo.js'></script>
<script type="text/javascript" src='/hkmca/js/sub.common.js'></script>
<script type="text/javascript" src='/hkmca/js/maf.js'></script>
<script type="text/javascript">
    function pageInit() {
       dojo.require("dojo.widget.DropdownDatePicker");
    }
    addEvent(document,'load',pageInit);

</script>
<noscript>홈페이지를 표준 보기로 사용하시려면 JAVASCRIPT가 활성화되어 있어야 합니다. <br>
JAVASCRIPT가 비활성화되어 있거나 브라우저에서 지원되지 않는 것 같습니다. 표준 보기를 사용하려면 브라우저 옵션을 변경하여 JAVASCRIPT를 활성화한 다음 <a href="">다시 시도해 주십시오</a>.</noscript>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0"
    style="background-repeat: repeat-x;">
<div id="questionContainer">
    <div class="question"></div>
    <div class="questionNavi"></div>
</div>
</body>
</html>
