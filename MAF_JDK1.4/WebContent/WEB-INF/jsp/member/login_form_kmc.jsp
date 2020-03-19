<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<c:set var="imgpath" value="${CPATH}/images/login/kmc" />
<style type="text/css">
    .login_txt {
        color: #020202;
        font-size:12px;
        font-family: arial;
        
    }
    .login_box {
        border:1px solid #DCDCDC;
    }
    .login_input {
        width:130px;
        border:1px solid #DCDCDC;
        ime-mode:disabled;
    }
    #div_login {
        text-align:left;
    }
    #a_forget_password {
        font-size:13px;
        font-family: arial;
        color:#D13C3E;
    }
</style>
<script language='javascript'>
    function doForgotPassword() {
        var sUrl = 'http://ccc.hyundai-motor.com/servlet/ccc.login.CccLoginServlet?cmd=forgotPass';
        var popwin = window.open(sUrl,'popwin', "top=10px,left=10px,status=yes,resizable=no,menubar=no,scrollbars=no,width=380,height=320");
        popwin.focus();
    }
</script>
<script language='javascript' src='<c:url value="/js/lib.validate.js"/>'></script>
<div id="div_login">
<mf:form action='${CPATH}/login.do' method="post" name="frmLogin" id="frmLogin" onSubmit="return validate(this)">
    <input type="hidden" name="cmd" value="login">
    <mf:input type="hidden" name="type" value="${type}" />
    <c:set var="t" value="/" />
    <c:if test="${!empty next}">
        <c:set var="t" value="${next}" />
    </c:if>
    <mf:input type="hidden" name="next" value="${t}" />
    <img src='<c:out value="${imgpath}/title_t_login.gif"/>' border="0" />
    <table border="0" cellspacing="0" cellpadding="0" class="login_box">
        <tr>
            <td colspan="2"><img src='<c:out value="${imgpath}/img_login.gif"/>' border="0" /></td>
        </tr>
        <tr>
            <td height="25"></td>
        </tr>
        <tr>
            <td align="center">
            <table border="0" cellspacing="0" cellpadding="0">
                <col width=""/>
                <col width="66"/>
                <col width=""/>
                <tr>
                    <td><img src='<c:out value="${imgpath}/title_b_login.gif"/>' border="0" /></td>
                    <td></td>
                    <td align="center"><table border="0" cellspacing="0" cellpadding="4">
                       
                        <tr>
                            <td align="right"><label for="userid" class="login_txt">ID</label></td>
                            <td><input type="text" name="userid" id="userid" maxlength="30"
                                class="login_input" tabindex="1" required HNAME="userid"></td>
                            <td rowspan="2"><input type="image" src="<c:out value="${imgpath}/button_login.gif"/>"></td>
                        <tr>
                            <td><label for="password" class="login_txt">Password</label></td>
                            <td><input type="password" name="password" maxlength="50" class="login_input"
                                    tabindex="2" required hname="password"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><br><a href="javascript:doForgotPassword()" id="a_forget_password">*Forgot your password?  click here</a></td>
                        </tr>
                        </table></td>
                </tr>
            </table>
            </td>
        </tr>
        <tr>
            <td height="22"></td>
        </tr>
    </table>
    <img src='<c:out value="${imgpath}/copyright.gif"/>' border="0" vspace="10"/>
</mf:form></div>
