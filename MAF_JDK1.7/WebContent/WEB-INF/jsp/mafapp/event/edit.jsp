<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript" src="${CPATH}/js/calendar.js"></script>
<script language="JavaScript1.2">
<!-- 
// load htmlarea
	_editor_url = "${CPATH}/mboard/mHtmlArea/";                     // URL to htmlarea files
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	if (navigator.userAgent.indexOf('Mac')        >= 0) { win_ie_ver = 0; }
	if (navigator.userAgent.indexOf('Windows CE') >= 0) { win_ie_ver = 0; }
	if (navigator.userAgent.indexOf('Opera')      >= 0) { win_ie_ver = 0; }
	if (win_ie_ver >= 5.5) {
		document.write('<scr' + 'ipt src="' +_editor_url+ 'editor.js"');
		document.write(' language="Javascript1.2"></scr' + 'ipt>');  
	} else { 
		document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); 
	}
// -->
</script>
<script language="JavaScript1.2" src="${CPATH}/mboard/mHtmlArea/extend/writeList.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function frmSubmit() {
		var frm = getObject("myform");	
		if (validate(frm)) {
			frm.${mrt:mvcCmd()}.value = "${(param.cmd == 'edit') ? 'update' : 'insert'}";
			frm.submit();
		} else {
			return;
		}
	}
	function doWrite(){
	    var frm = getObject("myform");
	    frm.${mrt:mvcCmd()}.value = "add";
	    frm.submit();
	}
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
            <c:param name="${mrt:mvcCmd()}" value="list"/>
        </c:url>
        document.location = "${urlList}";
    }        
//-->
</SCRIPT>

<form action="${control_action}" method="post" enctype="multipart/form-data" name="myform" id="myform"> 
<!--field start-->
<br/>
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="1" class="view">
	<input type="hidden" name="evt_id" value="${item.evt_id}">
	<input type="hidden" name="${mrt:mvcCmd()}" value="edit">
	<tr>
		<th class="view" nowrap>����</th>
		<td class="view"><input type="text" name="evt_title" size="100%" required hname="����" maxlength="200" value="${item.evt_title}"></td>
	</tr>		
	<tr>
		<th class="view" nowrap>�̺�Ʈ�Ⱓ(�����Է�)</th>
		<td class="view"><input type="text" name="evt_period" size="100%" hname="�̺�Ʈ�Ⱓ" maxlength="100" value="${item.evt_period}"></td>
	</tr>		
	<tr>
		<th class="view" nowrap>�̺�Ʈ��û�Ⱓ</th>
		<td class="view">
			<input type="text" name="evt_stdt" size="10" readonly OnClick="popUpCalendar(this, this, 'yyyy-mm-dd');" hname="��������" value="${item.evt_stdt}">
			~
			<input type="text" name="evt_endt" size="10" readonly OnClick="popUpCalendar(this, this, 'yyyy-mm-dd');" hname="��������" value="${item.evt_endt}">
			<input type="checkbox" name="evt_req" value="Y" ${item.evt_req == 'Y' ? 'checked':''}>�̺�Ʈ ��û�Ⱓ ����
		</td>
	</tr>		
	<tr>
		<th class="view" nowrap>������̹���</th>
		<td class="view">
			<input type="file" name="upfile" size="100%" hname="�̺�Ʈ�Ⱓ" maxlength="100">
			<input type="hidden" name="evt_img" value="${item.evt_img}"><br>
			<c:if test="${item.evt_img != '' && item.evt_img != null}">
	 			<input type="checkbox" name="del_attach" value="T">�������� ���� 
	 			<img src="${filepath}/${item.evt_img}">
 			</c:if>
 		</td>
	</tr>		
	<tr>
		<th class="view" nowrap>����</th>
		<td class="view">
			<textarea style="width:100%; height:500px" name="evt_contents" id="evt_content">${item.evt_contents}</textarea>
			<script language="javascript1.2">
				editor_generate('evt_contents');
			</script>
		</td>
	</tr>
	<tr>
		<th class="view" nowrap>��뿩��</th>
		<td class="view">
			<select name="evt_yn">
				<option value="Y" ${item.evt_yn == 'Y' ? 'checked':''}>���</option>
				<option value="N" ${item.evt_yn == 'N' ? 'checked':''}>����</option>
			</select>
		</td>
	</tr>				
</table>
<!--field end-->
<!--button start-->
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="1">
	<tr>
		<td colspan="2" align="center">	
			<c:if test="${param.cmd == 'edit'}">
				<a href="javascript:doWrite();"><mfmt:message bundle="button" key="register"/></a>			
			</c:if>
			<a href="javascript:frmSubmit();"><mfmt:message bundle="button" key="ok"/></a>			
			<a href="javascript:goList();"><mfmt:message bundle="button" key="list"/></a>
		</td>
	</tr>
</table>
<!--button end-->
</form>

		