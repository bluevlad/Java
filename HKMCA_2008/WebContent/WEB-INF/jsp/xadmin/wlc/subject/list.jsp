<%@ page contentType="text/html; charset=utf-8"%>
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
	function doView(sjt_cd){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "edit";
			frm.sjt_cd.value = sjt_cd; 
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
<mf:input type="hidden" name="sjt_cd" value=""/> 
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>
            <div class="searchContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="search">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
				<tr>
                    <th><mf:label name="crs_nm"/></th>
                    <td>
                        <jsp:include page="/WEB-INF/jsp/common/sel_crs.jsp" flush="true">
                            <jsp:param name="os_crs" value='<%=request.getParameter("os_crs")%>'/>
                        </jsp:include>
                    </td>                 
					<th><mf:label name="sjt_nm"/></th>
					<td><mf:input name="s_sjt_nm" value="${LISTOP.ht.s_sjt_nm}" cssStyle="width:98%"/></td>					
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
            <col width="30"/>
            <col width="150"/>
			<col width="*"/>
			<col width="80"/>
			<thead>
			    <tr>
			    	<th>#</th>
                    <th><mf:header name="sjt_cd" sort="true"/></th>
		            <th><mf:header name="sjt_nm" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                    <td class="center"><a href='javascript:doView("<c:out value="${item.sjt_cd}"/>");'><mh:out value="${item.sjt_cd}" td="true"/></a></td>
					<td><a href='javascript:doView("<c:out value="${item.sjt_cd}"/>");'><mh:out value="${item.sjt_nm}" td="true"/></a></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
			    <tr>
			        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/></td>
			    </tr>
			</table>
		    </div>
	    </td>
    </tr>
</table>
</mf:form> 
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>