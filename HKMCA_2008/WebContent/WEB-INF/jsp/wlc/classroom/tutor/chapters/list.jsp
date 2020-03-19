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

    function doView(id){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "edit";
            frm.htmcode.value = id;
            frm.submit();
        }
    }
  
    function updateSequence(frm) {
        var frm = getObject("myform");
        frm.cmd.value='update_seq';
        frm.submit();
    }
    
    //
    function contentSelect(){
        window.open("<mh:out value="${control_action}" td="true"/>?cmd=mselist&sjt_cd=<mh:out value="${sjt_cd}" td="true"/>","selCnt_win", "resizable=yes,width=850,height=600,top=0,left=0,status=yes,scrollbars=yes,location=no");
    }
    
//-->
</SCRIPT>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
    <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
    <mf:input type="hidden" name="cmd" value="list"/>
    <mf:input type="hidden" name="lec_cd" value="${lec_cd}"/> 
    <mf:input type='hidden' name='htmcode' value=''/>
        <td>
            <div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="10%">
                <col width="30%">
                <col width="30%">
                <col width="20%">
                <col width="10%">
                <thead>
                <tr>
					<th><mf:header name="itm_sequence"/></th>
					<th><mfmt:message bundle="table.wlc_inx_lst" key="daename"/></th>
                    <th><mf:header name="itm_title"/></th>
                    <th><mfmt:message bundle="table.wlc_inx_lst" key="soname"/></th>
					<th><mf:header name="totpage"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list}" varStatus="status">
                <tr>
                    <td class="center" ><mf:input type="text" name="itm_sequence" value="${item.itm_sequence}" size="2" option="number" />
                        <mf:input type="hidden" name="act_key" value="${item.lec_cd}#${item.itm_id}" />
                    </td>
                    <td align="left">
                        <a href="javaScript:doView('<mh:out value="${item.htmcode}" escapeJS="true" />')"/>
                        <mh:out value="${item.daename}" td="true" /></a>
                    </td>
                    <td align="left">
                        <a href="javaScript:doView('<mh:out value="${item.htmcode}" escapeJS="true" />')"/>
                        <mh:out value="${item.itm_title}" td="true" /></a>
                    </td>
                    <td align="center"><mh:out value="${item.soname}" td="true" /></td>
					<td align="center"><mh:out value="${item.totpage}" td="true" /></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
			    <tr>
			        <td align="right">
			            <mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
			            <mf:button bundle="button" key="lec_chp.add.multi" onclick="contentSelect()" />
			            <mf:button bundle="button" key="lec_chp.sequence" onclick="updateSequence()" /><br>
			        </td>
			    </tr>
			</table>
            </div>
        </td>
    </mf:form>  
    </tr>
</table>
