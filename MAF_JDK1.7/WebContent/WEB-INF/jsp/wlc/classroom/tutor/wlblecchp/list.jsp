<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function doWrite(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "add";
            frm.submit();
        }
    }

    function doView(id){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "edit";
            frm.itm_id.value = id;
            frm.submit();
        }
    }

    // study
    function view(form, title,version,launch_data,w_width,w_height)
    {
        var opt = "width="+w_width+",height="+w_height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=no,copyhistory=no,top=50,left=50";
        var remote = window.open("about:blank","classroom_win", opt);
        remote.opener = self;
        if(remote != null) {
            if (remote.opener == null) { remote.opener = self; }
            remote.location.href="http://devcontents.esunhrd.co.kr"+launch_data;
            remote.focus();   
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

    function updateSequence(frm) {
        var frm = getObject("myform");
        frm.cmd.value='update_seq';
        frm.submit();
    }
    
    //
    function contentSelect(){
        window.open("<mh:out value="${control_action}" td="true"/>?cmd=mselist&sjtcode=<mh:out value="${sjt_cd}" td="true"/>","selCnt_win", "resizable=yes,width=800,height=600,top=0,left=0,status=yes,scrollbars=yes,location=no");
    }
    
//-->
</SCRIPT>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
          <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
      <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
      <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
      <input type="hidden" name="miv_page" value="1"/>
      <input type="hidden" name="cmd" value="list"/>    
      <mf:input type="hidden" name="leccode" value="${leccode}"/> 
      <input type='hidden' name='itm_id' value=''>
      <td><div class="listContainer">
    <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
    <thead>
      <tr>
        <th width="5%"><mf:header name="itm_sequence"/></th>
        <th width="30%"><mf:header name="itm_title"/></th>
        <th width="15%"><mfmt:message bundle="table.wlb_lec_chp" key="itm_maxtimeallowed"/></th>
        <th width="15%"><mf:header name="totpage"/></th>
        <th width="15%"><mf:header name="place_type"/></th>
        <th width="5%"></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${list}" varStatus="status">
    <tr >
      <td class="center" ><mf:input type="text" name="itm_sequence" value="${item.itm_sequence}" size="2" option="number" />
           <mf:input type="hidden" name="act_key" value="${item.leccode}#${item.itm_id}" />
      </td>
        <td align="left"><a href="javaScript:doView('<mh:out value="${item.itm_id}" escapeJS="true" />')"/>
            <mh:out value="${item.itm_title}" td="true" /></a></td>
        <td align="center"><mh:out value="${item.itm_maxtimeallowed}" td="true" /></td>
        <td align="center"><mh:out value="${item.totpage}" td="true" /></td>
        <td align="center"><mh:out value="${item.place_type}" codeGroup="PLACE_TYPE" td="true"/></td>
        <td ></td>
    </tr>
      </c:forEach>
      </tbody>
      </table></div>
      </td>
    </mf:form>  
    </tr>
  <tr>
      <td align="center">
        <mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
        <mf:button bundle="button" key="button.lec_chp.add.multi" onclick="contentSelect()" />
        <mf:button bundle="button" key="button.lec_chp.sequence" onclick="updateSequence()" /><br>
        
        </td>
    </tr>
</table>
