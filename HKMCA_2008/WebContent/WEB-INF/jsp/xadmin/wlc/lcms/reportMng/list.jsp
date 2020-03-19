<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doWrite(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "add";
            frm.submit();
        }
    }

    function doView(sjt_cd, quecode){
        var frm = getObject("myform");

        if(frm) {
            frm.cmd.value = "edit";
            frm.sjt_cd.value=sjt_cd;
            frm.quecode.value=quecode;
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

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="sjt_cd" value=""/>
<mf:input type="hidden" name="quecode" value="list"/>
<mf:input type="hidden" name="destination" value="${destination}"/> 
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <col width="15%"/>
                <col width="85%"/>
	            <tr>
	                <th><mf:label name="sjt_cd"/></th>
	                <td>
                        <jsp:include page="/WEB-INF/jsp/common/sel_sjt.jsp" flush="true">
                            <jsp:param name="os_crs" value='<%=request.getParameter("os_crs")%>'/>
                            <jsp:param name="os_sjt" value='<%=request.getParameter("os_sjt")%>'/>
                            <jsp:param name="destination" value='<%=request.getAttribute("destination")%>'/>
                        </jsp:include>
                    </td>
                </tr>
                <tr>
                    <th><mf:label name="quetitle"/></th>
                    <td><mf:input type="text" name="s_quetitle" size="50" value="${LISTOP.ht.s_quetitle}"/></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                 <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" icon="icon_search"/></td>
                </tr>
            </table>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
		            <th>#</th>
		            <th><mf:header name="sjt_nm" sort="true"/></th>
		            <th><mf:header name="quetitle" sort="true"/></th>
                </tr>
            </thead>
            <tbody>
		        <c:forEach var="item" items="${navigator.list}" varStatus="status">
		        <tr>
		            <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
		            <td align="center"><a href="javaScript:doView('<mh:out value="${item.sjt_cd}"/>', '<mh:out value="${item.quecode}"/>')"/><mh:out value="${item.sjt_nm}" td="true" /></a></td>
                    <td align="center"><a href="javaScript:doView('<mh:out value="${item.sjt_cd}"/>', '<mh:out value="${item.quecode}"/>')"/><mh:out value="${item.quetitle}" td="true" /></a></td>
		        </tr>
                </c:forEach>
            </tbody>
            </table>
            </div>
        </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
    <tr>
        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/></td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>