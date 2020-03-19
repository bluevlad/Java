<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
function doView(id, req){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "view";
        frm.usn.value = id;
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

function callMail(form) {
    var retVal=0;
    for(i=0;i<form.elements.length;i++)   {
         if(form.elements[i].name=="email" && form.elements[i].checked==true) {
            retVal=1;
            break;
        }
    }
    if(retVal==0)  {
        alert('<mfmt:message bundle="common.scripts" key="alert.not.select"/>');
    } else {
        var win = window.open("about:blank", "mail_win", "resizable=yes,width=800,height=600,top=0,left=0,scrollbars=no,location=no,status=yes");
        if(win != null) {
            form.action ="<c:url value="/wlc.common/mailsend.do" />";
            form.target = 'mail_win';
            form.cmd.value="add";
            form.submit();
        }
    }
}
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>    
<mf:input type="hidden" name="lec_cd" value="${lec_cd}"/> 
<mf:input type="hidden" name="usn" value=""/>
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
	                <td><mf:input type="text" name="sch_usernm" value="${LISTOP.ht.sch_usernm}"/></td>
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
                <col width="10%">
                <col width="15%">
                <col width="10%">
                <col width="25%">
                <col width="25%">
                <col width="15%">
                <thead>
	            <tr>
	                <th>#</th>
	                <th><mf:header name="userid" sort="true"/></th>
	                <th><mf:header name="nm" sort="true"/></th>
	                <th><mfmt:message bundle="table.wlc_rat_mst" key="jindo_page"/></th>
	                <th><mfmt:message bundle="table.wlc_rat_mst" key="jindo_time"/></th>
	                <th><mfmt:message bundle="common" key="email"/><input type="checkbox" name="allMailbox" value="allcode" onClick="allChekboxToggle(this,'myform','email');"></th>
	            </tr>
	            </thead>
	            <tbody>
	            <c:forEach var="item" items="${navigator.list}" varStatus="status">
	            <tr>
	                <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
	                <td align=center><a href="javaScript:doView('<mh:out value="${item.usn}" td="true" />', '<mh:out value="${item.lec_num}" td="true" />');"><mh:out value="${item.userid}" td="true" /></a></td>
	                <td align=center><mh:out value="${item.nm}" td="true" /></td>
	                <td align=center>
	                <c:if test="${item.jindo_page > 0.0}">
	                    <img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph10.gif" width="<mh:out value="${item.jindo_page}" td="true" />" height="12"/>
	                </c:if><mh:out value="${item.jindo_page}" td="true" />%</td>
	                <td align=center>
	                <c:if test="${item.jindo_time > 0.0}">
	                    <img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph10.gif" width="<mh:out value="${item.jindo_time}" td="true" />" height="12"/>
	                </c:if><mh:out value="${item.jindo_time}" td="true" />%</td>
	                <td align=center>
	                <mf:input type="checkbox" name="email" value="${item.usermail}#${item.usn}#${item.usernm}" />
	                </td>
	            </tr>
	            </c:forEach>
	            </tbody>
            </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
			    <tr>
			        <td align="right"><mf:button bundle="button" key="email.send" onclick="callMail(document.myform)" icon="icon_add"/></td>
			    </tr>
			</table>
            </div>
        </td>
    </tr>
</table>
</mf:form>  
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
