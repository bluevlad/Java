<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function view(bundle) {   
        var frm = getObject('myForm');
        frm.cmd.value="view";
        frm.bundle.value=bundle;
        frm.submit();
    }
    function getExcel(bundle) {   
        var frm = getObject('myForm');
        frm.cmd.value="downloadExcel";
        frm.bundle.value=bundle;
        frm.submit();
    }
//-->
</SCRIPT>
<mf:form action="${controlaction}" name="myForm" method="post">
    <input type="hidden" name="cmd" value=""/>
    <input type="hidden" name="bundle" value=""/>
</mf:form>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td>
            <div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
	            <col width="80"/>
	            <col width="80"/>
	            <col width="*"/>
	    		<thead>
    		    <tr>
			    	<th colspan="2">Category</th>
	                <th>desc</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${bundleMap}" varStatus="status">
                <tr>
					<td class='center'><a href="javascript:view('<c:out value="${item.key}"/>');"><c:out value="${item.key}"/></a></td>
					<td class='center'><jf:button onclick="getExcel('${item.key}')" bundle="button" key="excel.down" /></td>
					<td class='center'><mh:out value="${item.value.desc}" td="true"/></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
	    </td>
    </tr>
</table>