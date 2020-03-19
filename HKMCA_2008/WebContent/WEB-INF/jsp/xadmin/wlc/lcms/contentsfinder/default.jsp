<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
	function SetSize() {
		window.resizeBodyTo(960,610);
	}

	function winClose() {
		self.close();
	}
	
	function setContents(filename) {
		var frm = window.opener.document.myform;
		frm.<c:out value="${param.elname}"/>.value=CPATH+"/contents"+filename;
		self.close();
	}
</script>	
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
	<tr>
        <td colspan="2" height="49" valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="top">
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src='<c:url value="/images/fileman/top_left.gif"/>' alt="" border="0"></td>
                                <td style="font-size: 14px;" background="/images/fileman/top_bg.gif">&nbsp;Contents Room</td>
                                <td valign="top"><img src='<c:url value="/images/fileman/top_right.gif"/>' alt="" width="29" height="49" border="0"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
	<tr>
		<td valign="top"><iframe src='<c:url value="${control_action}">
                                <c:param name="cmd" value="tree"/>
                                <c:param name="absPath" value="absPath"/></c:url>' name="folderLeft" id="folderLeft" width="210" height="480" marginwidth="0" marginheight="0" scrolling="no"></iframe></td>
        <td valign="top">
            <iframe src=<c:url value="${control_action}">
                <c:param name="cmd" value="list"/>
                <c:param name="absPath" value="absPath"/></c:url> name="folderRight" id="folderRight" width="530" height="480" marginwidth="0" marginheight="0" scrolling="no">
            </iframe>
        </td>
	</tr>
</table>
<script>
    SetSize();
</script>