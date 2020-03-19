<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<SCRIPT LANGUAGE="JavaScript">
<!--

    window.resizeTo(860, 480);
    function doView(setid){
        var frm = getObject("myform");
        
        if(frm) {
            frm.cmd.value = "view";
            
            frm.setid.value=setid;
            
            
            frm.submit();
        }
    }
    function doSearch(frm) {
        if(frm) {
            frm.cmd.value = "list";
            frm.miv_page.value = 1;
            return true;
        }     
    }

//-->
</SCRIPT>
<table width="90%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
        <h1><mfmt:message bundle="common" key="searchtitle" /></h1>
        <mf:form action="${control_action}" method="post" name="myform" id="myform"
            onSubmit="return frmSubmit(this,'list');return false; ">
            <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden" />
            <input type="hidden" name="miv_page" value="1" />
            <input type="hidden" name="cmd" value="list" />
            <input type='hidden' name='setid' value=''>

            <mf:input type="hidden" name="frm_id" value="${param.frm_id}"/>
            <mf:input type="hidden" name="elm_id" value="${param.elm_id}"/>
            
            
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>

                    <th><mf:label name="settitle" for="s_settitle" /></th>
                    <td><mf:input type="text" name="s_settitle" value="${LISTOP.ht.s_settitle}" /></td>

                    <th><mf:label name="cat_name" for="s_cat_name" /></th>
                    <td><mf:input type="text" name="s_cat_name" value="${LISTOP.ht.s_cat_name}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" /> <mf:button
                        onclick="frmReset('myform');" bundle="button" key="reset" /></td>
                </tr>
            </table>
        </mf:form></div>
        </td>
    </tr>
    <tr>
        <td>
        <div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"
            rowAlternateClass="alternateRow">
            <col width="50"/>
            <col width="*"/>
            <col width="50"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="settitle" sort="true" /></th>
                    <th><mf:header name="cat_name" sort="true" /></th>
                    <th><mf:header name="questions"  /></th>
                    <th><mf:header name="active_yn" sort="true" /></th>
                    <th><mf:header name="update_dt" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td><a href="javaScript:doView(
                        '<c:out value="${item.setid}"/>')" /> <mh:out
                            value="${item.settitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.cat_name}" td="true" /></td>
                        <td align="center"><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}" td="true" /></td>
                        <td align="center"><mh:out value="${item.active_yn}" td="true" codeGroup="ACTIVE_YN" /></td>
                        <td align="center"><mh:out value="${item.update_usn}" />(<mh:out value="${item.setowner}" />)
                            <mh:out value="${item.update_dt}" format="fulldate" td="true" /></td>
</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        </td>
    </tr>
    <tr>
        <td align="center"><mf:form action="${control_action}" method='post' name='frmNavi' id='frmNavi'>
            <mf:input type="hidden" name="frm_id" value="${param.frm_id}"/>
            <mf:input type="hidden" name="elm_id" value="${param.elm_id}"/>
        <jsp:include page="/WEB-INF/layout/lib/navigator_noform.jsp" flush="true" />
        </mf:form></td>
    </tr>
</table>
