<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--    
    function doView(lec_cd, usn){
        var frm = getObject("myform");        
        if(frm) {
            frm.cmd.value = "view";            
            frm.lec_cd.value = lec_cd;
            frm.usn.value = usn;  
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
    
    function updateScore() {
        var frm = getObject("myform");
        var msg = "";

//        if(frm.vusn==undefined){
//            alert('<mfmt:message bundle="common.scripts" key="report.marking.notfound"/>');
//        }else if(CheckboxCheck(frm.vusn) == true){
//            alert('<mfmt:message bundle="common.scripts" key="report.marking.notselect"/>');
//        }else{
        	frm.cmd.value = "updateScores";
        	frm.submit();
//        }
    }    
    
function allSelect(frm, obj, allobj)
{
    var trk=0;
    for (var i=0;i<frm.elements.length;i++)
    {
        var e = frm.elements[i];
        if(e.name != 'checkbox' && e.name == obj) {
            e.checked = allobj.checked;
        }
    }
}

function CheckboxCheck(f){
    var i, flag=true;
    
    //
    if(f.length!=undefined){
        for (i=0; i<f.length; i++) {
            if (f[i].checked == true) {
                flag = false;
            }
        }
    //
    }else if(f.value!=undefined){
        if(f.checked==true){
            flag = false;
        }
    }
    
    if (flag) {
        return true;
    } else {
        return false;
    }
}

function checkForm(form) {
    sel_idx = form.liketype.selectedIndex;

    if(sel_idx > 0 && form.keyword.value=='') {
        alert('<mfmt:message bundle="common.scripts" key="script.alert.sel0"/>');
        form.keyword.focus();
        return;
    }

    form.submit();
}

function sort(orderfield, orderby){
    myform.orderfield.value = orderfield;
    myform.orderby.value = orderby;
    myform.submit();   
}

function jsAllchk(chk) {
    if (checkform.checkboxAll.checked == true) {
        for (var i=0; i<chk.length;i++)
            if (chk[i].type == "checkbox" && chk[i].checked == false)
                chk[i].checked = true;

    }else {
        for (var i=0; i<chk.length;i++)
            if (chk[i].type == "checkbox" && chk[i].checked == true)
                chk[i].checked = false;
    }
}
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="lec_cd" value="${lec_cd}"/>
<mf:input type='hidden' name='usn' value=''/> 
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">                            
                <col width="15%"/>
                <col width="35%"/>
                <col width="15%"/>
                <col width="35%"/>
                <tr>
	                <th><mfmt:message bundle="common" key="name"/></th>
	                <td><mf:input type="text" name="sch_nm" value="${LISTOP.ht.sch_nm}"/></td>
	                <th><mfmt:message bundle="common" key="id"/></th>
	                <td><mf:input type="text" name="sch_userid" value="${LISTOP.ht.sch_userid}"/></td>
                </tr>                
            </table>            
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/></td>
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
                    <th><mf:header name="userid" sort="true"/></th>
                    <th><mf:header name="nm" sort="true"/></th>
                    <th><mf:header name="score1"/></th>
                    <th><mf:header name="score3"/></th>
                    <th><mf:header name="score4"/></th>
                    <th><mf:header name="score10"/></th>
                    <th><mf:header name="tot_score"/></th>
                    <th><mfmt:message bundle="table.wlc_lec_req" key="is_grad"/> </th>                        
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
                    <mf:input type="hidden" name="vusn" value="${item.usn}" />
                    <td align="center">
                        <a href="javaScript:doView('<mh:out value="${item.lec_cd}" td="true" />', '<mh:out value="${item.usn}" td="true" />')"/>
                        <mh:out value="${item.userid}" td="true" /></a>
                    </td>
                    <td align="center"><mh:out value="${item.nm}" td="true" /></td>
                    <td align="center"><mh:out value="${item.score1}" td="true" /></td>
                    <td align="center"><mh:out value="${item.score3}" td="true" /></td>
                    <td align="center"><mh:out value="${item.score4}" td="true" /></td>
                    <td align="center"><mf:input type="text" name="${item.usn}_addscore" value="${item.score10}" cssStyle="width:30px" /></td>
                    <td align="center"><mh:out value="${item.tot_score}" td="true" /></td>
                    <td align="center">
                        <c:if test="${item.tot_cnt != '0'}">
                        <mh:out value="${item.is_grad}" td="true" />
                        </c:if>
                        <c:if test="${item.tot_cnt == '0'}">
                            <mfmt:message bundle="common" key="table.notscoreing"/>
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
            </table>
			<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
			    <tr>
			        <td align="right"><mf:button bundle="button" key="final.score.marking" onclick="updateScore()" icon="icon_add"/></td>
			    </tr>
			</table>
            </div>
        </td>
    </tr>
</table>
</mf:form>    
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
