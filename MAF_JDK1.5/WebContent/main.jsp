<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Locale"%>
<%@page import="maf.web.context.MafConfig"%>
<%@page import="maf.base.BaseHttpSession"%>
<%@page import="maf.web.session.MySession"%>
<%@page import="maf.mdb.Mdb"%>
<%@page import="maf.mdb.drivers.*"%>
<%@page import="modules.wlc.learner.*"%>
<%@page import="modules.etest.learner.ExamListDB"%>
<%@page import="modules.survey.*"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%
    BaseHttpSession sessionBean = MySession.getSessionBean( request, response );

    Map param = new HashMap();
    param.put("usn", sessionBean.getUsn());
    param.put("userid", sessionBean.getUsn());
    MdbDriver oDb  = null;
    
    Locale mlocale = MafConfig.resolveLocale(request);
    try {    
        oDb = Mdb.getMdbDriver();
        
        ////본인을 기준으로 상위조직이 등록한 강좌를 모두 가져옵니다.
        request.setAttribute("list_ing", MainDB.getList(oDb,  param));
        
        // 수강신청 가능 목록 가져 오기 
        request.setAttribute("list_ext",  MainDB.getListExt(oDb,  param));
        
        request.setAttribute("etestlist",  ExamListDB.getRList(oDb,  param));
        
        // My Survey
        param.put("lang", mlocale.getLanguage());
        request.setAttribute("list", SurveyDB.getMySurveyList(oDb, param));
        
    } finally {

        if (oDb != null) try {oDb.close();} catch (Exception ex) {}
        oDb = null;
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<jsp:include  page="/web_src/www/commonHead.jsp" flush="true"/> 
<style>
    .main_board_title {
        background: url('<c:url value="/images/hmc/bullet.gif"/>') left no-repeat;
        font-weight:bold;
        color: #000000;
        padding-left:18px;
    }
    .main_notice_data {
        background: url('<c:url value="/images/main/board_data_bullet.gif"/>') left no-repeat;
        color: #3D3D3D;
        padding-left:8px;
        text-align:left;
    }
</style>
</head>
<body leftmargin="0" topmargin="0" 
	rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x;" >

<div id="div_r1">
	<jsp:include  page="/WEB-INF/layout/common/menu_top.jsp" flush="true"/> 
</div>
<div id="div_r2">
	<div id="div_r2_inner">
		<table border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td valign="top" style="padding-left:10px;padding-right:10px">
			<table border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td height="17"></td>
                </tr>
                <tr>
                    <td background="<c:url value="/images/hmc/newcommer_bg.gif"/>"><img src='<c:url value="/images/hmc/newcommer_top.gif"/>' border="0"></td>
                </tr>
                <tr>
                    <td background="<c:url value="/images/hmc/newcommer_bg.gif"/>" style="padding-left:12px">
                        <a href='<c:url value="/info/MyStat.do"/>'>Your Traning?</a><br>
                        <a href='<c:url value="/board/list.do?bid=faq"/>'>FAQ</a><br>
                        <br><br>&nbsp;
                        
                        </td>
                 </tr>
                <tr>
                    <td><img src='<c:url value="/images/hmc/newcommer_bot.gif"/>' border="0"></td>
                </tr>
            </table>
        </td>
        <td style="padding-top:17px">
			<table cellspacing="0" cellpadding="0" border="0" >
                <col width="360"/>
                <col width="15"/>
                <col width="395"/>
                <tr>
                    <td valign="top"><table cellspacing="0" cellpadding="0" border="0" width="395">
                        <tr>
                            <td><span class="main_board_title">Notice</span></td>
                            <td align="right"><a href='<c:url value="/board/list.do?bid=news"/>'><img src='<c:url value="/images/main/more.gif"/>' border="0"></a></td>
                         </tr>
                         <tr>
                            <td colspan="2"><table cellspacing="0" cellpadding="0" border="0">
                                <tr><td><img src='<c:url value="/images/main/board_top.gif"/>' border="0"></td></tr>
                                <tr align="center"><td background="<c:url value="/images/main/board_bg.gif"/>" >
                                <jsp:include  page="/WEB-INF/layout/common/inc_news_db_ul.jsp" flush="true">
                                    <jsp:param name="bid" value="news"/>
                                    <jsp:param name="cnt" value="5"/>
                                    <jsp:param name="title_byte" value="40"/>
                                </jsp:include>
                                </td></tr>
                                <tr><td><img src='<c:url value="/images/main/board_bot.gif"/>' border="0"></td></tr>
                               </table>
                            </td>
                         </tr>
                         <tr>
                            <td colspan="2" height="15"></td>
                         </tr>
                        <tr>
                            <td><span class="main_board_title">FAQ</span></td>
                            <td align="right"><a href='<c:url value="/board/list.do?bid=faq"/>'><img src='<c:url value="/images/main/more.gif"/>' border="0"></a></td>
                         </tr>
                         <tr>
                            <td colspan="2"><table cellspacing="0" cellpadding="0" border="0">
                                <tr><td><img src='<c:url value="/images/main/board_top.gif"/>' border="0"></td></tr>
                                <tr align="center"><td background="<c:url value="/images/main/board_bg.gif"/>">
                                <jsp:include  page="/WEB-INF/layout/common/inc_news_db_ul.jsp" flush="true">
                                    <jsp:param name="bid" value="faq"/>
                                    <jsp:param name="cnt" value="5"/>
                                    <jsp:param name="title_byte" value="40"/>
                                </jsp:include></td>
                                </tr>
                                <tr><td><img src='<c:url value="/images/main/board_bot.gif"/>' border="0"></td></tr>
                               </table>
                                
                            </td>
                         </tr>
                         </table>
                    </td>
                    <td width="15"><img src='<c:url value="/maf_images/a.gif"/>' border="0" width="15"></td>
                    <td valign="top"><jsp:include  page="main_left.jsp" flush="true"/> </td>
                </tr>
            </table>
		</td>
        </tr>
        </table>
        
		<div class="clear"></div>
	</div>
</div>
<div id="div_r3" class="clear"></div>
<jsp:include  page="/WEB-INF/layout/common/commonTailInfo.jsp" flush="true"/> 

<script>
    var t = getObject("divLangSelector");
    if(t) {
        Event.observe(window, 'load', function() {
            Event.observe('divLangSelector', 'focus', LangSelector.showLangList  );
            Event.observe('divLangSelector', 'blur', LangSelector.hideLangList );
            Event.observe('divLangList', 'mouseover', LangSelector.overLangSel); 
            Event.observe('divLangList', 'mouseout', LangSelector.outLangSel); 
        });
    }
</script>
</body>
</html>