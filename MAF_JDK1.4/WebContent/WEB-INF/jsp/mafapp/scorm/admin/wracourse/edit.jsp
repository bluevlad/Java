<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript" src="${CPATH}/js/lib.calendar.js"></script>
<script language="javascript" >
<!--
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
            <c:param name="${mrt:mvcCmd()}" value="list"/>
        </c:url>
        document.location = "${urlList}";
    }        

    function frmSubmit(frm) {
        if (validate(frm)) {
            frm.cmd.value="${(param.cmd=='edit')?'update':'insert'}";
            return true;
        } else {
            return false;
        }
    }

    function doView(id){
        var frm = getObject("myform1");
        if(frm) {
            frm.${mrt:mvcCmd()}.value = "view";
            frm.org_id.value = id;
            frm.submit();
        }
    }
    
    function doWrite(){
        var frm = getObject("myform");
        if(frm) {
            frm.${mrt:mvcCmd()}.value = "update";
            frm.submit();
        }
    }
//-->
</script>
<table border="0" cellpadding="2" cellspacing="1" class="view" width="100%"> 
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
    <input type="hidden" name="${mrt:mvcCmd()}" value="">
    <input type="hidden" name="crs_id" value="${item.crs_id}">
        <tr>
            <th width=20% height="28" class="view" nowrap><mfmt:message bundle="table.Wracourse" key="wra_course.sjt_cd"/></th>
            <td colspan=3 width=80% bgcolor=#ffffff class="view">
                <c:forEach var="sbjlist" items="${slist}" varStatus="status">
                    ${(sbjlist.prtcode == item.sjt_cd)?mhtml:null2nbsp(sbjlist.name):''}
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th width=20% height="28" class="view"><mfmt:message bundle="table.Wracourse" key="wra_course.crs_id"/></th>
            <td colspan=3 bgcolor=#ffffff class="view">${item.crs_id}</td>
        </tr>
        <tr>
            <th width=20% height="28" class="view"><mfmt:message bundle="table.Wracourse" key="wra_course.crs_name"/><span id='lbl_required'></span></th>
            <td colspan=3 bgcolor=#ffffff class="view"><input type="text" name="crs_name" size=100 value="${item.crs_name}" hname="<mfmt:message bundle="table.Wracourse" key="wra_course.crs_name"/>" required></td>
        </tr>
    <tr>
        <td colspan="4" align="right">
        <a href="javascript:doWrite()"><mfmt:button bundle="button" key="button.save"/></a> 
        <a href="javascript:goList()"><mfmt:message bundle="button" key="goList"/></a> 
        </td>
    </tr>
</form>
</table>
<br>    
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
<form action="${control_action}" method="post" name="myform1" id="myform1" onSubmit="return frmSubmit(this)">
    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="">
    <input type="hidden" name="${mrt:mvcCmd()}" value="">
    <input type="hidden" name="crs_id" value="${item.crs_id}">
    <input type="hidden" name="org_id" value="">
        <thead>
        <tr>
          <th class="list BL" width="20">No.</th>
          <th class="list"><mfmt:message bundle="table.Wraorganization" key="wra_organization.org_id"/></th>
          <th class="list"><mfmt:message bundle="table.Wraorganization" key="wra_organization.org_title"/></th>
          <th class="list"><mfmt:message bundle="table.Wraorganization" key="wra_organization.org_isdefault"/></th>
          <th class="list"><mfmt:message bundle="table.Wraorganization" key="wra_organization.org_structure"/></th>
          <th class="list"><mfmt:message bundle="table.Wraorganization" key="wra_organization.org_sequence"/></th>
          <th class="list BR"><mfmt:message bundle="table.Wraorganization" key="wra_organization.update_dt"/></th>
        </tr>
        </thead>
        <tbody>
     <c:forEach var="sitem" items="${org_list}" varStatus="status">
        <tr >
           <td class="list" align="center">${status.count}</td>
           <td class="list" align=center>${mhtml:null2nbsp(sitem.org_id)}</td>
           <td class="list" align=center><a href="javaScript:doView('${sitem.org_id}')">${mhtml:null2nbsp(sitem.org_title)}</a></td>
           <td class="list" align=center>${mhtml:null2nbsp(sitem.org_isdefault)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(sitem.org_structure)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(sitem.org_sequence)}</td>
           <td class="list" align=center><fmt:formatDate value="${sitem.update_dt}" pattern="yyyy.MM.dd"/></td>
         </tr>
    </c:forEach>
        </tbody>
        </table></td>
</form>
    </tr>
</table>
