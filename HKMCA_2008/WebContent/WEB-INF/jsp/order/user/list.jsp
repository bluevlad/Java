<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doView(ord_cd){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "view";
            frm.ord_cd.value = ord_cd; 
            frm.submit();
        }
    }

    function doSearch() {
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "list";
            frm.submit();
        }     
    }
//-->
</SCRIPT>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'list');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="ord_cd" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <col width="15%"/>
                <col width="35%"/>
                <col width="15%"/>
                <col width="35%"/>
                <tr>
                    <th><mf:label name="ord_sta"/></th>
                    <td>
                        <mf:select name="s_ord_sta" codeGroup="ORDER.ORD_TYPE" curValue="${LISTOP.ht.s_ord_sta}">
                            <mf:option value="">전체</mf:option>
                        </mf:select>
                    </td>                 
                    <th><mf:label name="ord_dt"/></th>
                    <td>
                        <mf:input type="date" name="s_start_dt" cssStyle="width:90px" value="${LISTOP.ht.s_start_dt}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown"/> ~
                        <mf:input type="date" name="s_end_dt" cssStyle="width:90px" value="${LISTOP.ht.s_end_dt}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown"/>
                    </td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn" width="100%">
                <tr>
                    <td align="right"><mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/></td>
                </tr>
            </table>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="20"/>
                <col width="*"/>
                <col width="150"/>
                <col width="120"/>
                <col width="120"/>
                <col width="120"/>
                <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="ord_cd" sort="true"/></th>
                    <th><mf:header name="ord_dt" sort="true"/></th>
                    <th><mf:header name="total_pay" sort="true"/></th>
                    <th><mf:header name="discount" sort="true"/></th>
                    <th><mf:header name="ord_sta" sort="true"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
                    <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                    <td align="center"><a href="javaScript:doView('<c:out value="${item.ord_cd}"/>')" /><mh:out value="${item.ord_cd}" td="true" /></a></td>
                    <td align="center"><a href="javaScript:doView('<c:out value="${item.ord_cd}"/>')" /><mh:out value="${item.ord_dt}" format="fulldate" /></a></td>
                    <td align="center"><mh:out value="${item.total_pay}" format="currency"/></td>
                    <td align="center"><mh:out value="${item.discount}" format="currency"/></td>
                    <td align="center"><mh:out value="${item.ord_sta}" codeGroup="ORDER.ORD_TYPE" /></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>