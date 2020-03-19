<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript"	src="${CPATH}/js/lib.validate.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
    <input type="hidden" name="miv_page" value="1">
    <input type="hidden" name="${mrt:mvcCmd()}" value="list">
    <input type="hidden" name="crs_id" value="">
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
            <tr>
                <th class="search"><mfmt:message bundle="common" key="search"/></th>
                <td class="search"><select name="schtype" hname='<mfmt:message bundle="common" key="search"/>' >
                    <option value=""><mfmt:message bundle="common" key="search.all"/></option>
                    <option value="name" ${(LISTOP.ht.schtype=='name')?'selected':''}><mfmt:message bundle="table.Wracourse" key="wra_course.sjt_name"/></option>
                    <option value="crs_name" ${(LISTOP.ht.schtype=='crs_name')?'selected':''}><mfmt:message bundle="table.Wracourse" key="wra_course.crs_name"/></option>
                    </select><input type="text" name="keyword" value="${LISTOP.ht.keyword}" ></td>
             </tr>
             <tr>
                <td colspan="10" align="right">
                <a href="javascript:doSearch()"><mfmt:button bundle="button" key="button.search"/></a>
                </td>
             </tr>
        </table>
        </td>
    </tr>
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
        <thead>
        <tr>
          <th class="list BL" width="50">No.</th>
          <th class="list"><mfmt:message bundle="table.Wracourse" key="wra_course.sjt_name"/><mform:THSort id='name'/></th>
          <th class="list"><mfmt:message bundle="table.Wracourse" key="wra_course.crs_id"/><mform:THSort id='crs_id'/></th>
          <th class="list"><mfmt:message bundle="table.Wracourse" key="wra_course.crs_name"/><mform:THSort id='crs_name'/></th>
          <th class="list"><mfmt:message bundle="table.Wracourse" key="wra_course.crs_version"/><mform:THSort id='crs_version'/></th>
          <th class="list"><mfmt:message bundle="table.Wracourse" key="wra_course.crs_identifier"/><mform:THSort id='crs_identifier'/></th>
          <th class="list BR"><mfmt:message bundle="table.Wracourse" key="wra_course.update_dt"/><mform:THSort id='update_dt'/></th>
        </tr>
        </thead>
        <tbody>
     <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <tr >
           <td class="list" align="center">${mrt:getListSeq(navigator, status.count)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(item.name)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(item.crs_id)}</td>
           <td class="list" align=center><a href="javaScript:doEdit('${item.crs_id}')">${item.crs_name}</a></td>
           <td class="list" align=center>${mhtml:null2nbsp(item.crs_version)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(item.crs_identifier)}</td>
           <td class="list" align=center><fmt:formatDate value="${item.update_dt}" pattern="yyyy.MM.dd"/></td>
         </tr>
    </c:forEach>
        </tbody>
        </table></td>
    </tr>
</form>    
    <tr>
        <td align="center">
        <a href="javascript:doWrite()"><mfmt:button bundle="button" key="register"/></a><br>
            <jsp:include page="/layout/lib/navigator.jsp" flush="true"/>
        </td>
    </tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function doWrite(){
        var frm = getObject("myform");
        if(frm) {
            frm.${mrt:mvcCmd()}.value = "add";
            frm.submit();
        }
    }

    function doEdit(id){
        var frm = getObject("myform");
        if(frm) {
            frm.${mrt:mvcCmd()}.value = "edit";
            frm.crs_id.value = id;
            frm.submit();
        }
    }

    function doSearch() {
        var frm = getObject("myform");
        if(frm) {
            frm.${mrt:mvcCmd()}.value = "list";
            frm.miv_page.value = 1;
            frm.submit();
        }     
    }

    function doDelete(frm) {
        var frm = getObject("myform");
        if(validate(frm)) {
            frm.${mrt:mvcCmd()}.value = "delete";
             frm.submit();
        }     
    }

    function doExcelUp() {
        var frm = getObject("myform");
        if(frm) {
            frm.${mrt:mvcCmd()}.value = "add_excel";
            frm.submit();
        }     
    }
//-->
</SCRIPT>

