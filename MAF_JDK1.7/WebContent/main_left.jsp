<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}
//-->
</script>
<style type="text/css">
<!--
.style1 {
	color: #00498d;
	font-weight: bold;
}
.pa_inner_box {
    padding:4px;
    background-repeat: no-repeat;
}
-->
</style>
<table width="500" height="130" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td height="406"><div style="position:relative;top:50;left:0">
                        <div id="test1"  style="position:absolute; left:33px; top:23px; width:69px; height:21px; z-index:4; visibility: hidden;" 
                            onMouseOver="MM_showHideLayers('test1','','hide','class1','','show','test','','show', 'moretest','','show','moreclass','','hide','moresurvey','','hide','test2','','show','class2','','hide','survey2','','hide','survey1','','show','class','','hide','survey','','hide')"><img src="img/tabmenu01_01b.gif" alt="My e-Test" width="84" height="21" border="0"></div>
                        <div id="class1" style="position:absolute; left:118px; top:23px; width:94px; height:21px; z-index:5; visibility: visible;" 
                            onMouseOver="MM_showHideLayers('test1','','show','class1','','hide','test','','hide', 'moretest','','hide','moreclass','','show','moresurvey','','hide','test2','','hide','class2','','show','survey2','','hide','survey1','','show','class','','show','survey','','hide')"><img src="img/tabmenu01_02b.gif" alt="my class room" width="117" height="21"></div>
                        <div class="pa_inner_box" id="test"   style="position:absolute; left:12px; top:44px; width:372px; height:171px; z-index:3; background-image: url(img/tab_inner_bg01.gif); layer-background-image: url(img/tab_inner_bg01.gif); border: 1px none #000000; overflow: visible; visibility: visible;">
                        <!-- e-test Start -->
                        <jsp:include page="main_etest.jsp"/>
                        <!-- e-test end --></div>
                        <!--------------------  test contents----------------------->
                        <div id="moretest" style="position:absolute; left:341px; top:25px; width:37px; height:8px; z-index:6; visibility: inherit;"><A href='<c:url value="/etest/examlist.do"/>'><img src="img/bt_more.gif" alt="more" width="37" height="11" border="0"></a></div>
                        <!--------------------  more Class----------------------->
                        <div id="moreclass" style="position:absolute; left:341px; top:25px; width:37px; height:8px; z-index:6; visibility: hidden;"><A href='<c:url value="/wlc.learner/main.do"/>'><img src="img/bt_more.gif" alt="more" width="37" height="11" border="0"></a></div>
                        <!--------------------  more survey----------------------->
                        <div id="moresurvey" style="position:absolute; left:341px; top:25px; width:37px; height:8px; z-index:6; visibility: hidden;"><A href='<c:url value="/survey/survey.do"/>'><img src="img/bt_more.gif" alt="more" width="37" height="11" border="0"></a></div>
                        <div id="test2" style="position:absolute; left:33px; top:17px; width:84px; height:27px; z-index:7; visibility: visible;"><img src="img/tabmenu01_01a.gif" width="84" height="27"></div>
                        <div id="class2" style="position:absolute; left:118px; top:17px; width:117px; height:27px; z-index:8; visibility: hidden;"><img src="img/tabmenu01_02a.gif" width="117" height="27"></div>
                        <div id="survey2" style="position:absolute; left:236px; top:17px; width:79px; height:27px; z-index:9; visibility: hidden;"><img src="img/tabmenu01_03a.gif" width="79" height="27"></div>
                        <div id="survey1" style="position:absolute; left:235px; top:23px; width:79px; height:21px; z-index:10; visibility: visible;" 
                              onMouseOver="MM_showHideLayers('test1','','show','class1','','show','test','','hide','moretest','','hide','moreclass','','hide','moresurvey','','show','test2','','hide','class2','','hide','survey2','','show','survey1','','hide','class','','hide','survey','','show')"><img src="img/tabmenu01_03b.gif" alt="my survey" width="79" height="21"></div>
                        <!--------------------  My class contents----------------------->
                        <div class="pa_inner_box" id="class" style="position:absolute; left:12px; top:44px; width:372px; height:171px; z-index:2; background-image: url(img/tab_inner_bg01.gif) ; layer-background-image: url(img/tab_inner_bg01.gif); border: 1px none #000000; overflow: hidden; visibility: hidden;">
                        <!-- e-test Start -->
                        <jsp:include page="main_classroom.jsp"/>
                        <!-- e-test end --></div>
                        <!--------------------  My survey contents----------------------->
                        <div class="pa_inner_box" id="survey" style="position:absolute; left:12px; top:44px; width:372px; height:171px; z-index:1; background-image: url(img/tab_inner_bg01.gif); layer-background-image: url(img/tab_inner_bg01.gif); border: 1px none #000000; visibility: hidden;">
                        <!-- Survey Start -->
                        <jsp:include page="main_survey.jsp"/>
                        <!-- Survey end --></div>
                        <table width="395" height="406" border="0" cellpadding="0" cellspacing="0" background="img/tab_bg.gif" >
                            <tr>
                                <td><table width="395" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td height="200" align="center" valign="bottom"><table width="372" height="198" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td height="27" align="right" valign="bottom"></td>
                                                        <td valign="bottom"></td>
                                                        <td valign="bottom"></td>
                                                        <td width="75" align="center" valign="middle"></td>
                                                    </tr>
                                                    <tr background="img/tab_inner_bg01.gif">
                                                        <td height="171" colspan="4"></td>
                                                    </tr>
                                            </table></td>
                                        </tr>
                                        <tr>
                                            <td height="206" valign="top"><br><table width="395" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td height="30"><table width="379" height="30" border="0" cellpadding="0" cellspacing="0">
                                                                <tr>
                                                                    <td width="16"></td>
                                                                    <td><img src="img/bullet_box_lb01.gif" width="12" height="11" align="absmiddle"> <span class="style1">Q&amp;A</span> </td>
                                                                    <td style="padding-top:8 "width="37"><a href='<c:url value="/board/list.do?bid=qna"/>'><img src="img/bt_more.gif" alt="more" width="37" height="11" border="0"></a></td>
                                                                </tr>
                                                        </table></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center"><table width="372" height="116" border="0" cellpadding="0" cellspacing="0" background="img/box_grey_bg.gif">
                                                                <tr>
                                                                    <td height="5"><img src="img/box_grey_top.gif" width="372" height="5"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="106" align="center" valign="top"><jsp:include  page="/WEB-INF/layout/common/inc_news_db_ul.jsp" flush="true">
                                    <jsp:param name="bid" value="qna"/>
                                    <jsp:param name="cnt" value="5"/>
                                    <jsp:param name="title_byte" value="40"/>
                                </jsp:include></td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="5"><img src="img/box_grey_b.gif" width="372" height="5"></td>
                                                                </tr>
                                                        </table></td>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                    </tr>
                                            </table></td>
                                        </tr>
                                </table></td>
                            </tr>
                        </table>
                </div></td>
            </tr>
        </table>
