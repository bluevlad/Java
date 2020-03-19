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
	function doView(lec_cd){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "edit";
			frm.lec_cd.value = lec_cd; 
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
    function doCopy(){
        var frm = getObject("myform");
            frm.cmd.value = "copy";
            frm.submit();
    }
//-->
</SCRIPT>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'list');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="lec_cd" value=""/> 
<mf:input type="hidden" name="destination" value="${destination}"/> 
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
                    <th><mf:label name="lec_year"/></th>
                    <td><mf:input type="text" name="s_lec_year" value="${LISTOP.ht.s_lec_year}" cssStyle="width:50px"/></td>
                    <th><mf:label name="lec_stat"/></th>
                    <td>
                        <mf:select name="s_lec_stat" codeGroup="LEC.LEC_STAT" curValue="${LISTOP.ht.s_lec_stat}">
                        <option value="">-</option></mf:select>
                    </td>                    
                </tr>
				<tr>
                    <th><mf:label name="sjt_cd"/></th>
                    <td colspan="3">
                        <jsp:include page="/WEB-INF/jsp/common/sel_sjt.jsp" flush="true">
                            <jsp:param name="os_crs" value='<%=request.getParameter("os_crs")%>'/>
                            <jsp:param name="os_sjt" value='<%=request.getParameter("os_sjt")%>'/>
                            <jsp:param name="destination" value='<%=request.getAttribute("destination")%>'/>
                        </jsp:include>
                    </td>                 
                </tr>
                <tr>
					<th><mf:label name="lec_nm"/></th>
					<td colspan="3"><mf:input type="text" name="s_lec_nm" value="${LISTOP.ht.s_lec_nm}" cssStyle="width:98%"/></td>					
				</tr>
	        </table>
	        <table border="0" cellspacing="0" cellpadding="2" class="searchBtn" width="100%">
	        	<tr>
	        		<td align="right">
		                <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
	                </td>
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
                <col width="150"/>
                <col width="100"/>
                <col width="*"/>
                <col width="100"/>
			<thead>
			    <tr>
                    <th>#</th>
		            <th><mf:header name="lec_cd" sort="true"/></th>
                    <th><mf:header name="lec_year" sort="true"/></th>
		            <th><mf:header name="lec_nm" sort="true"/></th>
		            <th><mf:header name="lec_stat" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.lec_cd}"/>");'><mh:out value="${item.lec_cd}" td="true"/></a></td>
					<td class="center"><mh:out value="${item.lec_year}" td="true"/></td>
                    <td><a href='javascript:doView("<c:out value="${item.lec_cd}"/>");'><mh:out value="${item.lec_nm}" td="true"/></a></td>
					<td class="center"><mh:out value="${item.lec_stat}" codeGroup="LEC.LEC_STAT"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
			    <tr>
			        <td align="right">
			            <mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
			            <mf:button bundle="button" key="lec.copysubject" onclick="doCopy()"  icon="icon_add"/>
			        </td>
			    </tr>
			</table>
		    </div>
	    </td>
    </tr>
</table>
</mf:form> 
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>