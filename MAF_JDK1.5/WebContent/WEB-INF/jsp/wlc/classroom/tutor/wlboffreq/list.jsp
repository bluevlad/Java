<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function doView(id){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "edit";
            frm.itm_id.value = id;
            frm.submit();
        }
    }
  
    function doSearch() {
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "list";
            frm.miv_page.value = 1;
            frm.submit();
        }     
    }

//-->
</SCRIPT>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<input type="hidden" name="miv_page" value="1"/>
<input type="hidden" name="cmd" value="list"/>    
<input type='hidden' name='itm_id' value=''>
<mf:input type="hidden" name="leccode" value="${leccode}"/> 
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<!-- 
    <tr>
        <td><div class="searchContainer">
          <h1><mfmt:message bundle="common" key="searchtitle"/></h1>
          <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
        </div></td>
    </tr> 
   -->
    <tr>
        <td><div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
        <thead>
            <tr>
                <th width="15%"><mfmt:message bundle="table.wlb_lec_chp" key="itm_sequence"/></th>
                <th width="50%"><mfmt:message bundle="table.wlb_lec_chp" key="itm_title"/></th>
                <th width="30%"><mfmt:message bundle="table.wlb_off_req" key="req_num"/></th>
                <th width="5%"></th>
            </tr>
        </thead>
        <tbody>
      <c:forEach var="item" items="${navigator.list}" varStatus="status">
            <tr >
                <td align="center"><mh:out value="${item.itm_sequence}" td="true" /></td>
                <td align="left"><a href="javaScript:doView(
                  '<mh:out value="${item.itm_id}" td="true" />')"/>
                  <mh:out value="${item.itm_title}" td="true" /></a></td>
                <td align="center"><mh:out value="${item.req_cnt}" td="true" /></td>
                <td ></td>
            </tr>
      </c:forEach>
        </tbody>
        </table></div>
        </td>
    </mf:form>  
    </tr>
    <tr>
        <td align="center">
        <jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
        </td>
    </tr>
</table>
