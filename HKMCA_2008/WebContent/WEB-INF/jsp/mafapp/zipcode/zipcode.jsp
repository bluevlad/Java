<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mf" uri="http://maf.jstl.com/jsp/tld/maf-form.tld"%>
<%@ taglib prefix="mh" uri="http://maf.jstl.com/jsp/tld/maf-html.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.jstl.com/jsp/tld/maf-fmt.tld" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title><mfmt:message bundle="common.title" key="zipcode" /></title>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/sub.common.js"></script>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validation.js"></script>
<script language="javascript">
    window.resizeTo("500","470");
</script>
<script language='javascript'>
<!--
    function doSubmit(){
        var form = getObject("findAddr");
        if(form) {
            if(form.keyword.value.length == 0){
                alert('<mfmt:message bundle="common.scripts" key="zipcode.addr.input"/>'); 
            }else{
                form.submit();
            }
         } else {
        	   alert('<mfmt:message bundle="common.scripts" key="zipcode.form.notfound"/>'); 
         }
    }
    
    function changeAddr1Sel(frm)
    {
        var tmp = new Array();
        tmp = frm.wbAddr1Sel.value.split("|");
        frm.zipcd.value = tmp[0];
        frm.DisAddr1.value = tmp[1];
    }
//-->
</script>
<style>
    body, td, select, input {
        font-size:12px;
        font-family: "돋움";
    }
    .input {
        border:1px solid;
    }
</style>
</head>
<body bgcolor="ccdfee" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- TITLE START -->

<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="margin-bottom:20">
    <tr>
        <td height="3" bgcolor="218FBD"></td>
    </tr>
    <tr>
        <td height="25" bgcolor="3D9DE7" style="padding:0 20 0 20">
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="25"><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/bullet_popup.gif" width="15" height="13"></td>
                    <td class="t_white1"><mfmt:message bundle="common.title" key="zipcode" /></td>
                    <td width="125" height="45">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td height="3" bgcolor="B1D0E8"></td>
    </tr>
</table>
<!-- TITLE END -->

<!-- CONENTS START -->
<mf:form name="findAddr" id="findAddr" method="post" action="${control_action}">
<mf:input type="hidden" name="form" value="${form}"/>
<mf:input type="hidden" name="post" value="${post}"/>
<mf:input type="hidden" name="addr1" value="${addr1}"/>
<mf:input type="hidden" name="addr2" value="${addr2}"/>
<TABLE  border="0" cellspacing="0" cellpadding="0" width=450 style="margin-left:20">
    <TR>
        <TD>
            <!-- 주소찾기 시작 -->         
                <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                    <TR>
                        <TD width="10" align=left valign=top ><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/round-left.gif" ></TD>
                        <TD valign=top background="<mh:out value="${CPATH}"/>/maf_images/zipcode/top_side.gif"></TD>
                        <TD align=right valign=top width=10><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/round-right.gif" border=0></TD>
                    </TR>
                    <TR >
                        <TD background="<mh:out value="${CPATH}"/>/maf_images/zipcode/side_left.gif"></TD>
                        <TD align=center bgcolor="#FFFFFF" ><table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td colspan="2" style="padding-bottom:8"><mfmt:message bundle="common.message" key="zipcode.comment"/></td>
                            </tr>
                            <tr>
                                <td width="170" align="right" valign="bottom" style="padding-right:10"><input name="keyword" type="text" size="25" value="<mh:out value='${keyword}'/>" onkeypress="if (13 == event.keyCode) return doSubmit();"></td>
                                <td width="124"><a href="javascript:doSubmit();" onFocus="this.blur()"><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/btn_enter10.gif" width="78" height="25" border="0"></a></td>
                            </tr>
                        </table></TD>
                        <TD background="<mh:out value="${CPATH}"/>/maf_images/zipcode/side_right.gif" border=0></TD>
                    </TR>
                    <TR>
                        <TD align=left valign=bottom ><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/round-left_bottom.gif" border=0></TD>
                        <TD background="<mh:out value="${CPATH}"/>/maf_images/zipcode/bottom_side.gif" border=0></TD>
                        <TD align=right valign=bottom><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/round-right_bottom.gif" border=0></TD>
                    </TR>
                </TABLE>
                <!-- 주소찾기 끝-->
        </TD>
    </TR>
    <TR>
        <TD bgcolor="ccdfee" height=3>&nbsp;</TD>
    </TR>
    <TR>
        <TD>
            <!-- 완료시작 -->        
            <TABLE border="0" cellspacing="0" cellpadding="0" width=100%  >
                <TR>
                    <TD width="10" align=left valign=top ><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/round-left.gif" ></TD>
                    <TD valign=top background="<mh:out value="${CPATH}"/>/maf_images/zipcode/top_side.gif"></TD>
                    <TD align=right valign=top width=10><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/round-right.gif" border=0></TD>
                </TR>
                <TR >
                    <TD background="<mh:out value="${CPATH}"/>/maf_images/zipcode/side_left.gif" border=0></TD>
                    <TD align=center bgcolor="#FFFFFF" ><table width="0" border="0" cellpadding="0" cellspacing="0">
                        <tr align="center">
                            <td colspan="2" valign="bottom">
                                <select name="wbAddr1Sel" style="width:400px" onChange="javascript:changeAddr1Sel(document.findAddr);">
                                    <option value=""><mfmt:message bundle="common.message" key="zipcode.addr.write"/></option>
                                    <c:forEach var="item" items="${list}" varStatus="status">
    								<option value="<mh:out value="${item.zipcode}"/>|<mh:out value="${item.r_addr}"/>"><mh:out value="${item.addr}"/></option>
	   							    </c:forEach>
                                </select>
<script language='javascript'>
<!--
    function setAddr(){
        var frm = getObject("<mh:out value='${form}'/>",opener.document);
        
        if (document.findAddr.DisAddr2.value == "") {
            document.findAddr.DisAddr2.focus();
            alert('<mfmt:message bundle="common.scripts" key="zipcode.addr2.input"/>'); 
            return;
        }
        if(frm) {
            var zipcd = document.findAddr.zipcd.value;
            var zipcd = zipcd.substr(0,3) + "-" + zipcd.substr(3,6); 
            frm.<mh:out value="${post}"/>.value = zipcd;
            frm.<mh:out value="${addr1}"/>.value = document.findAddr.DisAddr1.value;
            frm.<mh:out value="${addr2}"/>.value = document.findAddr.DisAddr2.value;
            self.close();
        } else {
            alert('<mfmt:message bundle="common.scripts" key="zipcode.form.notfound"/>'); 
        }
    }
//-->
</SCRIPT>
                                              </td>
                                          </tr>
                                          <tr align="center">
                                            <td colspan="2" style="padding-top:8"></td>
                                          </tr>
                                           <tr>
                                            <td colspan="2" style="padding-top:8"><input type="text" name="zipcd" class="fc_02" readOnly=true value="" style="border:none; width:100; font-weight:bold; background-color:#E7EEF3;"></td>
                                          </tr>
                                           <tr align="center">
                                            <td colspan="2" style="padding-top:8"><input type="text" name="DisAddr1" class="fc_02" readOnly=true value="" style="border:none; width:400; font-weight:bold; background-color:#E7EEF3;"></td>
                                          </tr>
                                           <tr align="center">
                                            <td colspan="2" style="padding-top:8"></td>
                                          </tr>
                                          <tr>
                                            <td width="170" align="right" valign="bottom" style="padding-right:10"><input name="DisAddr2" type="text" size="25"></td>
                                            <td width="130" style="padding-top:8"><a href="javascript:setAddr();" onFocus="this.blur()"><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/btn_enter11.gif" width="58" height="25" border="0"></a></td>
                                          </tr>
                                        </table></TD>
                                        <TD background="<mh:out value="${CPATH}"/>/maf_images/zipcode/side_right.gif" border=0></TD>                                      
                                    </TR>
                                    <TR>
                                        <TD align=left valign=bottom ><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/round-left_bottom.gif" border=0></TD>
                                        <TD background="<mh:out value="${CPATH}"/>/maf_images/zipcode/bottom_side.gif" border=0></TD>
                                        <TD align=right valign=bottom><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/round-right_bottom.gif" border=0></TD>
                                    </TR>
                                    </TABLE>
                                    <!-- 완료 끝-->
                            
                            </TD>
                          </TR>
			</TABLE>
			</mf:form> 
                        <!-- CONENTS END -->
                        <!-- CLOSE BAR START -->
                        <table width="100%"  border="0" cellspacing="0" cellpadding="0" style="margin-top:30">
                            <tr>
                                <td height="1" bgcolor="C7D9E8"></td>
                            </tr>
                            <tr>
                                <td height="40" align="center" bgcolor="ACCEE8"><a href="javascript:window.close();" onFocus="this.blur()"><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/close.gif" width="65" height="20" border="0"></a></td>
                            </tr>
                    </table>
<!-- CLOSE BAR END -->
                    
</body>
</html>