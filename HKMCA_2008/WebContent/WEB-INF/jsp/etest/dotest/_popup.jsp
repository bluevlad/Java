<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=utf-8" />
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>eTest</title>
<link href='<c:url value="/css/maf_common.css"/>' rel="stylesheet" type="text/css"></link>
<link href='<c:url value="/css/common.css"/>' rel="stylesheet" type="text/css"></link>
<link href='<c:url value="/css/etest/doTest.css"/>' rel="stylesheet" type="text/css"></link>
<script>
    function resize() {
        //window.resizeTo(800,600);
//        var w = 850;
//        var h = 600;
        var w = screen.availWidth-100;
        var h = screen.availHeight-100;

        with(document.body)
        {
           window.resizeTo(w, h);
        }

    }
        
    addEvent(window, 'load',function() {resize()} )
        
    function ClickHandler() {    
        var e = window.event;
        if (e.button == 2 || e.button == 3 || e.keyCode == 93) {
            window.alert("<mfmt:message bundle="common.scripts" key="script.classroom.right.button.not.use"/>");
        //} else if (e.ctrlKey || e.shiftKey || e.altKey) {
        } else if (e.ctrlKey ) {
            window.alert("<mfmt:message bundle="common.scripts" key="script.classroom.button.not.use"/>");
        }   
    }
//    window.ondragstart=false;
//    window.oncontextmenu=false;
//    window.onselectstart=false;
    
//    document.onmousedown = ClickHandler;
//    document.onkeydown = ClickHandler;;        
    </script>
</head>
<!-- oncontextmenu="return false"    ondragstart="return false" onselectstart="return false" -->
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" >
<mh:import url="${MAF_INFO.file}" />
</body>
</html>