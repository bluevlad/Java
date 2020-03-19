<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<div id="div_login">
<mf:form action='${CPATH}/login.do' method="post" name="frmLogin" id="frmLogin" onSubmit="return validate(this)">
<mf:input type="hidden" name="cmd" value="login"/>
<mf:input type="hidden" name="type" value="${type}"/>
<c:set var="t" value="/" />
<c:if test="${!empty next}">
    <c:set var="t" value="${next}" />
</c:if>
<mf:input type="hidden" name="next" value="${t}" />
<table width="300" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        <table border="0" cellspacing="0" cellpadding="4">
                            <tr>
                                <td align="right"><label for="userid" class="login_txt">ID</label></td>
                                <td><input type="text" name="userid" id="userid" maxlength="30" class="login_input" tabindex="1" required HNAME="userid"></td>
                                <td rowspan="2"><input type="image" src='<c:out value="/images/login/button_login.gif"/>' alt=""/></td>
                            </tr>
                            <tr>
                                <td><label for="password" class="login_txt">Password</label></td>
                                <td><input type="password" name="password" maxlength="50" class="login_input" tabindex="2" required hname="password"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <a href="javascript:UserInfo('findid', 'myform');">[<mfmt:message bundle="common.login" key="btn.findid" />]</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:UserInfo('findpwd', 'myform');">[<mfmt:message bundle="common.login" key="btn.findpwd" />]</a>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</mf:form>
</div>