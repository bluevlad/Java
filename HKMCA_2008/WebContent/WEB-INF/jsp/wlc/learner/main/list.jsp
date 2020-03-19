<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doView(lec_cd, lec_num){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "view";
            frm.lec_cd.value = lec_cd;
            frm.lec_num.value = lec_num;
            frm.submit();
        }
    }

	function doStudy(lec_cd, lec_num){
	    var frm = getObject("myform");
	    if(frm) {
	        frm.action = "/wlc.learner/chapters.do";
	        frm.lec_cd.value = lec_cd;
	        frm.lec_num.value = lec_num;
	        frm.submit();
	    }
	}
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
<mf:form action="${control_action}" method="get" name="myform" id="myform">
<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="lec_cd" value=""/> 
<mf:input type="hidden" name="lec_num" value=""/> 
</mf:form> 
    <tr>
        <td>
        <div class="listContainer">
            <!-- 수강중인 강좌 -->
            <B><mfmt:message bundle="common.title" key="stdlec"/></B>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="20%">
                <col width="35%">
                <col width="20%">
                <col width="15%">
            <thead>
                <tr>
                    <th><mf:header name="lec_cd"/></th>
                    <th><mf:header name="lec_nm"/></th>
                    <th><mf:header name="lec_date"/></th>
                    <th><mf:header name="req_stat"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list_ing}" varStatus="status">
                <tr>
                    <td align="center"><mh:out value="${item.lec_cd}" td="true"/></td>
                    <td>
                        <c:if test="${item.req_stat == 'LP' or item.req_stat == 'LE'}">
                        <a href="javascript:doStudy('<mh:out value="${item.lec_cd}"/>','<mh:out value="${item.lec_num}"/>');">
                        </c:if>
                        <mh:out value="${item.lec_nm}" td="true"/></a>
                    </td>
                    <td align="center"><mh:out value="${item.req_sdat}" td="true"/>~<mh:out value="${item.req_edat}" td="true"/></td>
                    <td class="center"><mh:out value="${item.req_stat}" codeGroup="LEC.REQ_STAT" td="true"/></td>
                </tr>
                </c:forEach>
            </tbody>
            </table>
            <br/><br/>      
            <!--  수강신청 가능 강좌  -->
            <B><mfmt:message bundle="common.title" key="stdlec2" /></B>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="25%">
                <col width="50%">
                <col width="25%">
            <thead>
                <tr>
                    <th><mf:header name="lec_cd"/></th>
                    <th><mf:header name="lec_nm"/></th>
                    <th><mf:header name="lec_date"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list_ext}" varStatus="status">
                <tr>
                    <td align="center"><mh:out value="${item.lec_cd}" td="true"  /></td>
                    <td><a href='javascript:doView("<c:out value="${item.lec_cd}"/>","<c:out value="${item.lec_num}"/>");'><mh:out value="${item.lec_nm}" td="true"/></a></td>
                    <td align="center"><mh:out value="${item.sch_a_c}" td="true"/> ~ <mh:out value="${item.sch_e_c}" td="true"/></td>
                </tr>
                </c:forEach>
            </tbody>
            </table>            
            <br/><br/>
            </div>
        </td>
    </tr>
</table>