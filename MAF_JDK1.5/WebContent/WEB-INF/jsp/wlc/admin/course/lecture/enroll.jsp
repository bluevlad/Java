<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.calendar.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
    function doEnroll(){
      var frm = getObject("myform");
      var retVal=0;
      for(i=0;i<frm.elements.length;i++) {
          if(frm.elements[i].name=="v_usn" && frm.elements[i].checked==true) {
              retVal=1;
              break;
          }
      }
      if(retVal==0)  {
          maf.alert('common.scripts','script.alert.not.select');
      } else {
          frm.cmd.value = "enroll_ok";
          frm.submit();
      }
    }
//-->
</SCRIPT>

<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
        <th><mf:label name="leccode"/></th> 
        <td><mh:out value="${item.leccode}" td="true"/></td>
        <th><mf:label name="alias_cd"/></th> 
        <td><mh:out value="${item.alias_cd}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_year"/></th> 
        <td><mh:out value="${item.lec_year}" td="true"/></td>
        <th><mf:label name="lecnumb"/></th> 
        <td><mh:out value="${item.lecnumb}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lecname"/></th> 
        <td colspan="3"><mh:out value="${item.lecname}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lecstat"/></th> 
        <td><mh:out td="true"  codeGroup="LECSTAT" value="${item.lecstat}"/></td>

        <th><mf:label name="ltype"/></th> 
        <td><mh:out td="true"  codeGroup="LTYPE" value="${item.ltype}"/></td>
    </tr>
    <tr>
        <th><mf:label name="otype"/></th> 
        <td><mh:out td="true"  codeGroup="OTYPE" value="${item.otype}"/></td>
        <th><mf:label name="lec_type"/></th> 
        <td><mh:out td="true"  codeGroup="LEC_TYPE" value="${item.lec_type}" /></td>
    </tr>
    <tr>
        <th><mf:label name="lec_capacity"/></th> 
        <td><mh:out td="true" value="${item.lec_capacity}" /></td>
        <th><mf:label name="lec_min_capacity"/></th> 
        <td><mh:out td="true" value="${item.lec_min_capacity}"/></td>
     </tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'enroll');return false;">
<input type="image" value="test" width="0" height="0" border="0" class="hidden"/>
<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="leccode" value="${leccode}"/> 
<mf:input type="hidden" name="lecnumb" value="${lecnumb}"/> 
    <tr>
        <td><div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_usn')"/></th>
                    <th>#</th>
                    <th><mf:header name="sub_section" sort="true"/></th>
                    <th><mf:header name="org_name" sort="true"/></th>
                    <th><mfmt:message bundle="table.maf_user" key="userid"/></th>
                    <th><mfmt:message bundle="table.maf_user" key="nm"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
                    <td class="center"><mf:input type="checkbox" name="v_usn" value="${item.usn}"/></td>
                    <td class="center"><mh:out value="${status.count}"/></td>
                    <td class="center"><mh:out value="${item.sub_section}" td="true" codeGroup="SUB_SECTION"/></td>
                     <td class="center"><mh:out value="${item.org_name}" td="true"/></td>
                    <td class="center"><mh:out value="${item.userid}" td="true"/></td>
                    <td class="center"><mh:out value="${item.nm}" td="true"/></td>
                </tr>
                </c:forEach>
            </tbody>
            </table>
            </mf:form> 
            </div>
        </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="doEnroll()"/>
            <mf:button bundle="button" key="goList" onclick="javascript:goList()"/><br/>
            
            <mf:form action="${control_action}" method='post' name='frmNavi' id='frmNavi'>
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <mf:input type="hidden" name="LISTOP2" value="${LISTOP2.serializeUrl}"/>
            <mf:input type="hidden" name="cmd" value="${param.cmd}"/>
            <mf:input type="hidden" name="miv_page" value="${navigator.currentPage}"/>
            <mf:input type="hidden" name="miv_order" value="${navigator.order.param}"/>
            <jsp:include page="/WEB-INF/layout/lib/navigator_noform.jsp" flush="true"/>
            </mf:form>
        </td>
    </tr>
</table>
