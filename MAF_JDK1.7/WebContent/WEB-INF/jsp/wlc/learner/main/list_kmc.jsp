<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doView(sjtcode, leccode, lecnumb){
        var frm = getObject("myform");
        if(frm) {
            frm.action = CPATH+"/wlc.learner/default.do";
            frm.cmd.value = "default";
            frm.to.value  = CPATH+"/wlc.learner/chapters.do";
            frm.sjtcode.value  = sjtcode;
            frm.leccode.value = leccode;
            frm.lecnumb.value = lecnumb;
            frm.submit();
        }
    }
    function doSearch() {
        var frm = getObject("myform");
        if(frm) {
            frm.action = '<c:out value="${control_action}"/>';
            frm.cmd.value = "list";
            frm.submit();
        }     
    }
    function setSjtcd(sjt_cd) {
        var frm = getObject("myform");
        frm.sjt_cd.value=sjt_cd;
        return;
    }
    function doTest(exmid,lecnumb) {
        document.location = CPATH+'/etest/examlist.do?cmd=view&exmid=' + exmid +"&lecnumb="+lecnumb;
    }
    
    function showSubjectInfo(leccode) {
        document.location = CPATH+'/wlc.learner/classlist.do?cmd=view&leccode=' + leccode;
    
    }
    function doSurvey(surveyid){
        var frm = getObject("myform");
        if(frm) {
            frm.action = CPATH+"/survey/survey.do";
            frm.cmd.value = "view";
            frm.surveyid.value = surveyid;
            frm.submit();
        }
    }
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
            <mf:form action="${control_action}" method="get" name="myform" id="myform">
                <input type="image" value="test" width="0" height="0" border="0" class="hidden"/>
                <mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
                <mf:input type="hidden" name="miv_page" value="1"/>
                <mf:input type="hidden" name="cmd" value="list"/>
                <mf:input type="hidden" name="sjtcode" value=""/> 
                <mf:input type="hidden" name="leccode" value=""/> 
                <mf:input type="hidden" name="lecnumb" value=""/> 
                <mf:input type="hidden" name="to" value=""/>
                <mf:input type="hidden" name="surveyid" value=""/>
            </mf:form> 
    <tr>
        <td>
        <div class="listContainer">
            <!-- 수강중인 강좌 -->
            <h5><font color="#B43D38"><mfmt:message bundle="common.message" key="message.notice.stdlec"/></font></h5>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th><mf:header name="section"/></th>
                    <th><mf:header name="field"/></th>
                    <th><mf:header name="cert_lvl"/></th>
                    <th><mf:header name="leccode"/></th>
                    <th><mf:header name="lecname"/></th>
                    <th><mf:header name="lec_date"/></th>
                    <th><mf:header name="otype"/></th>
                    <th><mf:header name="ltype"/></th>
                    <th><mfmt:message bundle="common" key="table.status"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list_ing}" varStatus="status">
                <tr>
                    <td align="center"><mh:out value="${item.section}" codeGroup="SECTION" td="true"/></td>
                    <td align="center"><mh:out value="${item.field}" codeGroup="FIELD" td="true"/></td>
                    <td align="center"><mh:out value="${item.cert_lvl}" codeGroup="CERT_LVL" td="true"/></td>
                    <td align="center"><mh:out value="${item.leccode}" td="true"/></td>
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <fmt:formatDate var="date" value="${now}" pattern="yyyyMMdd"/>
                    <c:choose>
                    <c:when test="${item.lec_type=='R' && (item.sch_sdt<=date && date<=item.sch_edt)}" >
                    <td><a href='javascript:doView("<c:out value="${item.sjt_cd}"/>","<c:out value="${item.leccode}"/>","<c:out value="${item.lecnumb}"/>");'><mh:out value="${item.lecname}" td="true"/></a></td>
                    </c:when>
                    <c:when test="${item.lec_type=='G' && (item.req_sdat<=date && date<=item.req_edat)}" >
                    <td><a href='javascript:doView("<c:out value="${item.sjt_cd}"/>","<c:out value="${item.leccode}"/>","<c:out value="${item.lecnumb}"/>");'><mh:out value="${item.lecname}" td="true"/></a></td>
                    </c:when>
                    <c:otherwise>
                    <td><mh:out value="${item.lecname}" td="true"/></td>
                    </c:otherwise>
                    </c:choose>
                    <c:choose>
                    <c:when test="${item.lec_type=='G'}" >
                    <td align="center"><mh:out value="${item.req_sdat}" format="fulldate" td="true"/>~<mh:out value="${item.req_edat}" format="fulldate" td="true"/></td>
                    </c:when>
                    <c:otherwise>
                    <td align="center"><mh:out value="${item.sch_sdt}" format="fulldate" td="true"/>~<mh:out value="${item.sch_edt}" format="fulldate" td="true"/></td>
                    </c:otherwise>
                    </c:choose>
                    <td align="center"><mh:out value="${item.otype}" codeGroup="OTYPE"  td="true"/></td>
                    <td align="center"><mh:out value="${item.ltype}" codeGroup="LTYPE"  td="true"/></td>
                    <c:choose>
                    <c:when test="${!empty item.req_stat || item.req_stat != ''}" >
                    <td align="center" ><mh:out value="${item.req_stat}" codeGroup="REQ_ST"  td="true"/></td>
                    </c:when>
                    <c:otherwise>
                    <td align="center"><mh:out value="${item.lecstat}" codeGroup="LECSTAT"  td="true"/></td>
                    </c:otherwise>
                    </c:choose>
                </tr>
                </c:forEach>
            </tbody>
            </table>
            <br/><br/>      
            <!--  수강신청 가능 강좌  -->
            <h5><font color="#B43D38"><mfmt:message bundle="common.message" key="message.notice.stdlec2" /></font></h5>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th><mf:header name="section"/></th>
                    <th><mf:header name="field"/></th>
                    <th><mf:header name="cert_lvl"/></th>
                    <th><mf:header name="leccode"/></th>
                    <th><mf:header name="lecname"/></th>
                    <th><mf:header name="lec_date"/></th>
                    
                    <th><mf:header name="otype"/></th>
                    <th><mf:header name="ltype"/></th>
                    <th><mfmt:message bundle="common" key="table.status"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list_ext}" varStatus="status">
                <tr>
                    <td align="center" ><mh:out value="${item.section}" codeGroup="SECTION"  td="true"/></td>
                    <td align="center" ><mh:out value="${item.field}" codeGroup="FIELD"  td="true"/></td>
                    <td align="center" ><mh:out value="${item.cert_lvl}" codeGroup="CERT_LVL"  td="true"/></td>
                    <td align="center" ><mh:out value="${item.leccode}" td="true"  /></td>
                    <td><a href='javascript:showSubjectInfo("<c:out value="${item.leccode}"/>","<c:out value="${item.leccode}"/>","<c:out value="${item.lecnumb}"/>");'><mh:out value="${item.lecname}" td="true"/></a></td>
                    <c:choose>
                    <c:when test="${item.lec_type=='G'}" >
                    <td align="center" ><mh:out value="${item.req_sdat}" format="fulldate" td="true"/>~<mh:out value="${item.req_edat}" format="fulldate" td="true"/></td>
                    </c:when>
                    <c:otherwise>
                    <td align="center" ><mh:out value="${item.sch_sdt}" format="fulldate" td="true"/>~<mh:out value="${item.sch_edt}" format="fulldate" td="true"/></td>
                    </c:otherwise>
                    </c:choose>
                    
                    <td align="center" ><mh:out value="${item.otype}" codeGroup="OTYPE"  td="true"/></td>
                    <td align="center" ><mh:out value="${item.ltype}" codeGroup="LTYPE"  td="true"/></td>
                    <c:choose>
                    <c:when test="${!empty item.req_stat || item.req_stat != ''}" >
                    <td align="center" ><mh:out value="${item.req_stat}" codeGroup="REQ_ST"  td="true"/></td>
                    </c:when>
                    <c:otherwise>
                    <td align="center" >aaa</td>
                    </c:otherwise>
                    </c:choose>
                </tr>
                </c:forEach>
            </tbody>
            </table>            
            <br/><br/>    
            
            <!-- 봐야할 시험  -->      
            <h5><font color="#B43D38"><mfmt:message bundle="common.message" key="message.notice.myetest"/></font></h5>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th>#</th>
                    <th nowrap><mf:header name="exmid"  /></th>
                    <th nowrap><mf:header name="exmtitle"  /></th>
                    <th nowrap><mf:header name="exm_sdat_t1" /></th>
                    <th nowrap><mf:header name="exm_edat_t1" /></th>
                    <th nowrap><mf:header name="rst_status" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${etestlist}" varStatus="status">
                    <tr>
                        <td class="center"><mh:out value="${status.count}" /></td>
                        <td align="center"><a href="javaScript:doTest(<c:out value="'${item.exmid}','${item.lecnumb}'"/>)" /><mh:out value="${item.exmid}"
                            td="true" /></a></td>
                        <td align="center"><a href="javaScript:doTest(<c:out value="'${item.exmid}','${item.lecnumb}'"/>)" /><mh:out value="${item.exmtitle}"
                            td="true" /></a></td>
                        <td align="center"><mh:out value="${item.exm_sdat_t1}" format="fulldate" td="true" /></td>
                        <td align="center"><mh:out value="${item.exm_edat_t1}" format="fulldate" td="true" /></td>
                        <td align="center"><mh:out value="${item.rst_status}" td="true" codeGroup="ETEST.RST_STATUS"/></td>
                        
                    </tr>
                </c:forEach>
            </tbody>
            </table>            
            <br/><br/>     
            
            <!--  설문 -->     
            <h5><font color="#B43D38"><mfmt:message bundle="common.message" key="message.notice.mysurvey"/></font></h5>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"
            rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th nowrap><mf:header name="surveyid" /></th>
                    <th nowrap><mf:header name="surveytitle" /></th>
                    <th nowrap><mf:header name="survey_sdat_t1" /></th>
                    <th nowrap><mf:header name="survey_edat_t1" /></th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list}" varStatus="status">
                    <tr>
                        <td align="center"><a href="javaScript:doSurvey('<c:out value="${item.surveyid}"/>')" /><mh:out value="${item.surveyid}" td="true" /></a></td>
                        <td align="center"><a href="javaScript:doSurvey('<c:out value="${item.surveyid}"/>')" /><mh:out value="${item.surveytitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.survey_sdat_t1}" format="fulldate" td="true" /></td>
                        <td align="center"><mh:out value="${item.survey_edat_t1}" format="fulldate"  td="true" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
            </div>
        </td>
    </tr>
</table>
