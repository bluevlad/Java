<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" >
<!--
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }        

  function fileCheck(filename, file_type)
  {
      allowSubmit = false;
      while (filename.indexOf("\\") != -1) {
          filename = filename.slice(filename.indexOf("\\") + 1);
          ext = filename.slice(filename.indexOf(".")).toLowerCase();
          words = file_type.split("#");
          for (var i = 0; i < words.length; i++) {
              if (words[i] == ext) {
                  allowSubmit = true; 
                  break; 
              }
          }
          if(allowSubmit) {
              return true;
          } else {
              return false;
          }
      }
      return false;
  }

  function frmSubmit(frmName) {
    var frm = getObject(frmName);
    var retVal=0;
    if(frm.excel_file.value=='') {
        alert("<mfmt:message bundle="table.wlc_inx_lst" key="script.msg10" param="*.xls, *.csv"/>");
        frm.excel_file.focus();
        return false;
    } else if(!fileCheck(frm.excel_file.value, '.xls#.csv')) {
        alert("<mfmt:message bundle="table.wlc_inx_lst" key="script.msg10" param="*.xls, *.csv"/>");
        frm.excel_file.focus();
        return false;
    } else {
        frm.cmd.value="insert_excel";
        frm.submit();
    }
  }

//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="frmSubmit('myform');return false;" enctype="multipart/form-data">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="htmcode" value="${item.htmcode}"/>
<table border="0" cellpadding="2" cellspacing="1" class="view" width="100%"> 
    <tr>
        <td colspan="4" height="28" class="view">
            <mfmt:message bundle="common.message" key="reqbatch.info"/>
            <mfmt:message bundle="common.message" key="reqbatch.info1"/><br>
            <mfmt:message bundle="table.wlc_inx_lst" key="script.msg10" param="*.xls, *.csv"/>
            [<a href="<mh:out value="${CPATH}"/>/contents/sample_content.xls" target="_new">
            <mfmt:message bundle="common.message" key="reqbatch.info3"/></a>]
        </td>
    </tr>
    <tr>
        <th height="28" class="view"><mfmt:message bundle="table.wlc_inx_lst" key="batchupload"/></th>
        <td colspan=3 bgcolor=#ffffff class="view"><input type="file" name="excel_file" size=50 value=""></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="viewBtn"> 
    <tr>
        <td align="right">
	        <mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save"/>       
	        <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>