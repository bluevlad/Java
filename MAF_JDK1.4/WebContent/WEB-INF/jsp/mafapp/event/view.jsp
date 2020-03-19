<%@ page contentType="text/html; charset=euc-kr"%>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
            <c:param name="${mrt:mvcCmd()}" value="default"/>
        </c:url>
        document.location = "${urlList}";
    }        
//-->
</SCRIPT>

<form action="${control_action}" method="post" enctype="multipart/form-data" name="myform" id="myform"> 
<!--field start-->
<br/>
<table width="95%" align="center" border="0" cellpadding="2" cellspacing="1" class="view">
	<input type="hidden" name="evt_id" value="${item.evt_id}">
	<input type="hidden" name="${mrt:mvcCmd()}" value="edit">
	<tr>
		<th class="view" nowrap>${item.evt_title}</th>
	</tr>		
	<tr>
		<td class="view center">${item.evt_period}</td>
	</tr>		
	<tr>
		<td class="view">${item.evt_contents}</td>
	</tr>
</table>
<!--field end-->
<!--button start-->
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="1">
	<tr>
		<td colspan="2" align="center">	
			<a href="javascript:goList();"><mfmt:message bundle="button" key="list"/></a>
		</td>
	</tr>
</table>
<!--button end-->
</form>