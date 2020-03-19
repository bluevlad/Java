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
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="listContainer">
        <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
        <input type="hidden" name="cmd" value="list" />
        <input type='hidden' name='setid' value=''>
        <mf:input type="hidden" name="frm_id" value="${frm_id}"/>
        <mf:input type="hidden" name="elm_id" value="${elm_id}"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
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
                    <th><mf:header name="questions"  /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list}" varStatus="status">
                    <tr>
                        <td class="center"><mh:out value="${status.count}" /></td>
                        <td><a href="javaScript:doView('<c:out value="${item.setid}"/>')" /> <mh:out value="${item.settitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}" td="true" /></td>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </mf:form>
        </div>
        </td>
    </tr>
</table>