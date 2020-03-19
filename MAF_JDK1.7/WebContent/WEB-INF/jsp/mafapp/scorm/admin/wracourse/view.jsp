<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript"	src="${CPATH}/js/lib.validate.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
    <input type="hidden" name="${mrt:mvcCmd()}" value="list">
    <input type="hidden" name="crs_id" value="">
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
        <thead>
        <tr>
          <th class="list BL" width="50">No.</th>
          <th class="list"><mfmt:message bundle="table.Wraitem" key="wra_item.itm_id"/><mform:THSort id='itm_id'/></th>
          <th class="list"><mfmt:message bundle="table.Wraitem" key="wra_item.itm_title"/><mform:THSort id='itm_title'/></th>
          <th class="list"><mfmt:message bundle="table.Wraitem" key="wra_item.itm_maxtimeallowed"/><mform:THSort id='itm_maxtimeallowed'/></th>
          <th class="list"><mfmt:message bundle="table.Wraitem" key="wra_item.itm_masteryscore"/><mform:THSort id='itm_masteryscore'/></th>
          <th class="list BR"><mfmt:message bundle="table.Wraitem" key="wra_item.itm_pid"/><mform:THSort id='itm_pid'/></th>
        </tr>
        </thead>
        <tbody>
     <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <tr >
           <td class="list" align="center">${mrt:getListSeq(navigator, status.count)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(item.itm_id)}</td>
           <td class="list" align=left>${mhtml:null2nbsp(item.itm_title)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(item.itm_maxtimeallowed)}</td>
           <td class="list" align=center>${mhtml:nvl(item.itm_masteryscore,'0')}</td>
           <td class="list" align=center>${mhtml:null2nbsp(item.itm_pid)}</td>
         </tr>
    </c:forEach>
        </tbody>
        </table></td>
    </tr>
</form>    
    <tr>
        <td align="center">
        <a href="javascript:goList()"><mfmt:button bundle="button" key="button.list"/></a><br>
            <jsp:include page="/layout/lib/navigator.jsp" flush="true"/>
        </td>
    </tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="${mrt:mvcListOp()}" value=""/>
            <c:param name="${mrt:mvcCmd()}" value="list"/>
        </c:url>
        document.location = "${urlList}";
    }        

    function doSearch() {
        var frm = getObject("myform");
        if(frm) {
            frm.${mrt:mvcCmd()}.value = "list";
            frm.miv_page.value = 1;
            frm.submit();
        }     
    }
//-->
</SCRIPT>

