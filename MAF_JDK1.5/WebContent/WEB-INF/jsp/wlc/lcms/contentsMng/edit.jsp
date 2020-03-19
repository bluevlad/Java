<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript">
<!--
    function frmSubmit(frmName) {
        var frm = getObject(frmName);
        if(frm) {
            if (validate(frm)) {
                frm.cmd.value="update";
                frm.submit();
            }
        } else {
            alert ("form[" + frmName + "] is invalid");
        }
    }
    
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
    
    // subject name set
    function setSjtname(frm) {
        if(frm.sjtcode.selectedIndex > 0)
            frm.sjtname.value = frm.sjtcode[frm.sjtcode.selectedIndex].text;
        else
            frm.sjtname.value = '';
    }
    function choose() {
    }
//-->
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
    <input type="image" value="test" width="0" height="0" border="0" class="hidden">
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
    <input type="hidden" name="cmd" value="">
    <mf:input type="hidden" name="htmcode" value="${item.htmcode}" />
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="20%" />
        <col width="30%" />
        <col width="20%" />
        <col width="30%" />
        <tr>
            <c:choose>
                <c:when test="${param.cmd == 'add'}">
                    <th nowrap><mf:label name="sjtcode" /></th>
                    <td><select name="sjtcode" required onChange="setSjtname(this.form);">
                        <option value=""><mfmt:message bundle="table.wra_inx_lst" key="select_subject" /></option>
                        <c:forEach var="sbjlist" items="${slist}" varStatus="status">
                            <mf:option text="${sbjlist.subject_nm}" value="${sbjlist.sjt_cd}" />
                        </c:forEach>
                    </select></td>
                </c:when>
                <c:when test="${param.cmd == 'edit'}">
                    <th nowrap><mf:label name="sjt_cd" /></th>
                    <td><mh:out value="${item.sjt_cd}" td="true" /></td>
                    <mf:input type="hidden" name="sjt_cd" value="${item.sjt_cd}" />
                </c:when>
            </c:choose>
            <th nowrap><mf:label name="subject_nm" /></th>
            <td><mh:out value="${item.subject_nm}" td="true" /></td>
        </tr>
        
        <tr>
            <th nowrap><mfmt:message bundle="table.wra_inx_lst" key="popup_size" /></th>
            <td colspan=3><mf:input type="text" name="cnt_width" size="20" maxlength="50" value="${item.cnt_width}" option="number" required="true"/> X <mf:input
                type="text" name="cnt_height" size="20" maxlength="50" value="${item.cnt_height}" option="number" required="true"/> (<span class="msginfo6"><mfmt:message
                bundle="table.wra_inx_lst" key="script.msg7" /></span>)</td>
        </tr>

        <tr>
            <th nowrap><mf:label name="orgn_cd" /></th>
            <td><mf:input type="text" name="orgn_cd" size="20" maxlength="50" value="${item.orgn_cd}" /></td>
            
        </tr>
    </table>
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td colspan="2" align="center"><mf:button bundle="button" key="save" onclick="frmSubmit('myform')" /> <mf:button
                bundle="button" key="goList" onclick="goList()" /></td>
        </tr>
    </table>
    

    <table border="0" cellpadding="2" cellspacing="0" class="list" width="100%">
        <thead>
            <tr>
                <th><mf:label name="daecode" /></th>
                <th><mf:label name="daename" /></th>
                <th nowrap><mf:label name="lrntime" /></th>
            </tr>
        </thead>
        
        <c:set var="maxChasi" value="0"/>
            <c:forEach var="i" items="${contents}" varStatus="status">
    		<c:set var="maxChasi" value="${i.chasi}"/>
    		<c:set var="maxRow" value="${status.count}"/>
                <tr>
                    <td><mf:input type="text" name="daecode_${maxRow}" size="5" maxlength="50" value="${i.daecode}"
                    required="true" option="alphanum"/></td>
                    <td><mf:input type="text" name="daename_${maxRow}" size="60" maxlength="50" value="${i.daename}" /><br>
                    <mf:input type="text" name="htmurl_${maxRow}" size="60" maxlength="50" value="${i.htmurl}"  />
                   
                    <a href="Javascript:openContents('<c:out value="htmurl_${maxRow}"/>')">[ Choose ]</a> 
                    <a href="Javascript:previewLec('<c:out value="htmurl_${maxRow}"/>')"> [ Preview ]</a></td>
                    <td align="center"><mf:input type="text" name="lrntime_${maxRow}" size="10" maxlength="50" value="${i.lrntime}" option="number"/></td>
                </tr>
            </c:forEach>
            <c:forEach var="i" begin="${maxRow+1}" end="50" varStatus="status" step="1">
                <c:set var="maxRow" value="${i}"/>
                <tr>
                    <td><mf:input type="text" name="daecode_${maxRow}" size="5" maxlength="50" value=""
                    required="true" option="alphanum"/></td>
                    <td><mf:input type="text" name="daename_${maxRow}" size="60" maxlength="50" value=""  /><br>
                    <mf:input type="text" name="htmurl_${maxRow}" size="60" maxlength="50" value="" />
                    
                    <a href="Javascript:openContents('<c:out value="htmurl_${maxRow}"/>')">Choose</a> 
                    <a href="Javascript:previewLec('<c:out value="htmurl_${maxRow}"/>')">Preview</a></td>
                    <td align="center"><mf:input type="text" name="lrntime_${maxRow}" size="10" maxlength="50" value="10" option="number"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</mf:form>
<script>
// 강의 보기 
function previewLec(nm){
    var frm = getObject("myform");
    var urlname = frm[nm].value;
    var width=frm.cnt_width.value;
    var height=frm.cnt_height.value;
    browsing_window = window.open(urlname, "imagewin","top=200px,left=200px,status=no,resizable=yes,menubar=no,scrollbars=yes,width="+width+",height="+height);
    browsing_window.focus();
}   
function openContents(elname) {
    popupWindow('<c:url value="/wlc.lcms/ContentsFinder.do"/>?elname='+elname);
}
</script>