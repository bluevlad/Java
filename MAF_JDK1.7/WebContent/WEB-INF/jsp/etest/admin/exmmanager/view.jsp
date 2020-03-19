<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<script>
    function goList() {

            var frm = getObject("myform");
            if(frm) {
                frm.cmd.value = "list";
                frm.submit();
            }
	    }
    
    function doEdit(){
        var frm = getObject("myform");
        
        if(frm) {
            frm.cmd.value = "edit";
            
            frm.submit();
        }
    }
            
	 
     
     function showDetailScore(usn, lecnumb) {
        var frm = getObject("myform");
        
        if(frm) {
            frm.cmd.value = "showDetailScore";
            frm.usn.value =  usn;
            frm.lecnumb.value = lecnumb;
            
            frm.submit();
        }
     }
     
     function goUserSel() {
        var frm = getObject("myform");
        
        if(frm) {
            frm.cmd.value = "userSel";
            frm.submit();
        }
     }
     
     function doExcel(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "excel";
            frm.submit();
        }
    }
    function doUploadExcel(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "uploadExcel";
            frm.submit();
        }
    }
    function goPrint(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "print";
            frm.submit();
        }
    }
     
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
    <mf:input type="hidden" name="cmd" value=""/>
    <mf:input type="hidden" name="usn" value=""/>
    <mf:input type="hidden" name="lecnumb" value=""/>
    <mf:input type="hidden" name="exmid" value="${item.exmid}"/>
    <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
</mf:form>
    <div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />
        <tr>
            <th nowrap><mf:header name="exmowner" /></th>
            <td><mh:out value="${item.exmowner}" /></td>
            <th nowrap><mf:header name="exmtitle" /></th>
            <td><mh:out value="${item.exmtitle}" /></td>
        </tr>
        <tr>            
            <th nowrap><mf:header name="exm_sdat_t1" /></th>
            <td><mh:out value="${item.exm_sdat_t1}" /></td>
            <th nowrap><mf:header name="exm_edat_t1" /></th>
            <td><mh:out value="${item.exm_edat_t1}" /></td>
        <tr>
            <th nowrap><mf:header name="setid" /></th>
            <td><mh:out value="${item.settitle}"/>(<mh:out value="${item.setid}" />)</td>
            <th nowrap><mf:header name="active_yn" /></th>
            <td><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" /></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="questions" /></th>
            <td ><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}" td="true"/></td>
            <th nowrap><mf:header name="update_dt" /></th>
            <td><mh:out value="${item.update_usn }"/>, <mh:out value="${item.update_dt}" format="fulldatetime"/></td>
        </tr>
    </table>
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td colspan="2" align="center">
                <mf:button bundle="button" key="edit" onclick="doEdit();" /> 
                <mf:button bundle="button" key="goList" onclick="goList();" />
                <mf:button bundle="etest.button" key="goPrint" onclick="goPrint();" />
                </td>
        </tr>
    </table>
    <br>
    <br>
    <table border="0" cellpadding="2" cellspacing="0"  width="100%">
        <tr>
            <td width="30%" valign="top">
            <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
                <col width="50%" />
                <col width="50%" />
                <tr>
                    <th nowrap><mfmt:message bundle="etest.common" key="result.rstavg" /></th>
                    <td align="center"><mh:out value="${exmStat.rstavg}" default="0"/></td>
                </tr>
                <tr>
                    <th nowrap><mfmt:message bundle="etest.common" key="result.rstavg10" /></th>
                    <td align="center"><mh:out value="${exmStat.rstavg10}" default="0"/></td>
                </tr>
                <tr>
                    <th nowrap><mfmt:message bundle="etest.common" key="result.passavg" remark="합격자 평균"/></th>
                    <mh:iif var="t" test="${empty exmStat.passavg}" trueValue="0" falseValue="${exmStat.passavg}"/>
                    <td align="center"><fmt:formatNumber value="${t}" pattern="##0.0#"/></td>
                </tr>
                <tr>
                    <th nowrap><mfmt:message bundle="etest.common" key="result.rstscoremax" /></th>
                    <td align="center"><mh:out value="${exmStat.rstscoremax}" default="0"/></td>
                </tr>
                <tr>
                    <th nowrap><mfmt:message bundle="etest.common" key="result.rstscoremin" /></th>
                    <td align="center"><mh:out value="${exmStat.rstscoremin}" default="0"/></td>
                </tr>
                <tr>
                    <th nowrap><mfmt:message bundle="etest.common" key="result.countoffinish" remark="제출인원"/> /
                               <mfmt:message bundle="etest.common" key="result.countoftarget" remark="대상인원"/></th>
                    <td align="center"><mh:out value="${item.cnt_s} / ${item.cnt_reg}" /></td>
                </tr>
                <tr>
                    <th nowrap><mfmt:message bundle="etest.common" key="result.countofprogress" /></th>
                    <td align="center"><mh:out value="${item.cnt_i}"  default="0"/></td>
                </tr>
                <tr>
                    <th nowrap><mfmt:message bundle="etest.common" key="result.passcnt" /></th>
                    <td align="center"><mh:out value="${exmStat.cnt_pass}( ${exmStat.passrate} %) " default="0" /></td>
                </tr>
               
            </table>
            </td>
            <td width="70%">
            <table border="0" cellpadding="2" cellspacing="0"  width="100%">
                <tr>
                    <td align="center">
                    <div id="chartdiv" align="center">FusionCharts.</div>
                    <c:url var="dataUrl" value="${control_action}">
                        <c:param name="cmd" value="userHistgram" />
                        <c:param name="exmid" value="${item.exmid}" />
                        <c:param name="rstscore100" value="${item.rstscore100}" />
                    </c:url> <script type="text/javascript">
                   var chart = new FusionCharts("Column3D.swf", "ChartId", "500", "300", "0", "0");
                   chart.setDataURL(escape('<c:out value="${dataUrl}" escapeXml="false"/>'));         
                   chart.render("chartdiv");
                </script></td>
                </tr>
            </table>
            </td>
        </tr>
    </table>
    <div class="listContainer">
    <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="false"
        rowAlternateClass="alternateRow">
        <col width="20" />
        <col width="120" />
        <col width="120" />
        <col width="20" />
        <col width="80" />
        <col width="80" />
        <col width="50" />
        <col width="20" />
        
        <thead>
            <tr>
                <th nowrap>#</th>
                <th nowrap><mf:header name="org_cd" />&nbsp;</th>
                <th nowrap><mf:header name="nm" />&nbsp;</th>
                <th nowrap><mf:header name="lang" /> &nbsp;</th>
                <th nowrap><mf:header name="exm_sdat_t1"  />&nbsp;</th>
                <th nowrap><mf:header name="exm_edat_t1"  />&nbsp;</th>
                <th nowrap><mf:header name="rst_status" /> &nbsp;</th>
                <th nowrap><mf:header name="rstscore100"  /> &nbsp;</th>
                <th nowrap><mf:header name="passing_yn"  /> &nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ri" items="${rstlist}" varStatus="status">
                <tr>
                    <td class="center"><mh:out value="${status.count}" /></td>
                    <td class="center"><mh:out value="${ri.org_cd}" td="true" /></td>
                    
                    <td class="center"><a href='javascript:showDetailScore(<c:out value="'${ri.usn}','${ri.lecnumb}'"/>)'><mh:out value="${ri.nm}(${ri.usn})" td="true" /></a></td>
                    <td class="center"><c:out value="${ri.lang}"  default="-"/></td>
                    <td class="center"><mh:out value="${ri.rst_sdt}" td="true" /></td>
                    <td class="center"><mh:out value="${ri.rst_edt}" td="true" /></td>
                    <td class="center"><mh:out value="${ri.rst_status}" td="true" codeGroup="ETEST.RST_STATUS" /></td>
                    <td class="center"><mh:out value="${ri.rstscore100}" td="true" /></td>
                    <td class="center"><mh:out value="${ri.passing_yn}" td="true" codeGroup="ETEST.PASSING_YN"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td colspan="2" align="center"><mf:button bundle="button" key="goList" onclick="goList();" />
                <mf:button bundle="etest.button" key="insert_apply" onclick="goUserSel();" />
                <mf:button bundle="button" key="button.excel.down" onclick="doExcel();" />
                <mf:button bundle="etest.button" key="upload.result" onclick="doUploadExcel();" />
                
                
                </td>
        </tr>
    </table>
    </div>


