<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
	<title>우편번호 찾기</title>
	<script language="javascript" src="${CPATH}/js/sub.common.js"></script>
	<script language="javascript" src="${CPATH}/js/lib.validation.js"></script>

<script language="javascript">
    window.resizeTo("500","470");
</script>
<script language='javascript'>
<!--

	function doSubmit(){
		var form = $("findAddr");
		if(form) {
		    if(form.keyword.value.length == 0){
		        alert('☞ 찾고자 하는 주소를 입력하세요.');
		    }else{
		        form.submit();
		    }
		 } else {
		 	alert(" i can't found form [findAddr] ");
		 }
	}
	
	function changeAddr1Sel(frm)
	{
		frm.wbAddr1Disp.value = frm.wbAddr1Sel.value;
	}
	

//-->
</script>
<style>
	body, td {
		font-size:12px;
		font-family: "돋움";
	}
	.input {
		border:1px solid;
	}
</style>
</head>
<body bgcolor="ccdfee" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- TITLE START -->

<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="margin-bottom:20">
    <tr>
        <td height="3" bgcolor="218FBD"></td>
    </tr>
    <tr>
        <td height="25" bgcolor="3D9DE7" style="padding:0 20 0 20">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25"><img src="${CPATH}/maf_images/zipcode/bullet_popup.gif" width="15" height="13"></td>
					<td class="t_white1">주소 찾기</td>
					<td width="125" height="45" background="${CPATH}/maf_images/zipcode/popup_bi.gif">&nbsp;</td>
				</tr>
			</table>
		</td>
    </tr>
    <tr>
        <td height="3" bgcolor="B1D0E8"></td>
    </tr>
</table>
<!-- TITLE END -->

<!-- CONENTS START -->
<form name="findAddr" id="findAddr" method="post" action="${control_action }">
<input type="hidden" name="form" value="${mhtml:nvl(param.form,'form')}"/>
<input type="hidden" name="post" value="${mhtml:nvl(param.post,'post')}"/>
<input type="hidden" name="post1" value="${mhtml:nvl(param.post1,'post1')}"/>
<input type="hidden" name="post2" value="${mhtml:nvl(param.post2,'post2')}"/>
<input type="hidden" name="addr1" value="${mhtml:nvl(param.addr1,'addr1')}"/>
<input type="hidden" name="addr2" value="${mhtml:nvl(param.addr2,'addr2')}"/>
<input type="hidden" name="addrDisp" value="${mhtml:nvl(param.addrDisp,'addrDisp')}"/>
<TABLE  border="0" cellspacing="0" cellpadding="0" width=450 style="margin-left:20">
	<TR>
		<TD>
			<!-- 주소찾기 시작 -->	     
				<TABLE border="0" cellspacing="0" cellpadding="0" width=100%  >
					<TR>
						<TD width="10" align=left valign=top ><img src="${CPATH}/maf_images/zipcode/round-left.gif" ></TD>
						<TD valign=top background="${CPATH}/maf_images/zipcode/top_side.gif"></TD>
						<TD align=right valign=top width=10><img src="${CPATH}/maf_images/zipcode/round-right.gif" border=0></TD>
					</TR>
					<TR >
						<TD background="${CPATH}/maf_images/zipcode/side_left.gif" border=0></TD>
						<TD align=center bgcolor="#FFFFFF" ><table width="0" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="2" style="padding-bottom:8">동/읍/면의 이름을 입력하시고 주소찾기를 클릭하세요.<br>
      (예:삼성동 또는 창녕읍 또는 홍동면) </td>
							</tr>
							<tr>
                            	<td width="170" align="right" valign="bottom" style="padding-right:10"><input name="keyword" type="text" size="25" value="${param.keyword}" onkeypress="if (13 == event.keyCode) return doSubmit();"></td>
                            	<td width="124"><a href="javascript:doSubmit();" onFocus="this.blur()"><img src="${CPATH}/maf_images/zipcode/btn_enter10.gif" width="78" height="25" border="0"></a></td>
							</tr>
						</table></TD>
						<TD background="${CPATH}/maf_images/zipcode/side_right.gif" border=0></TD>
					</TR>
					<TR>
						<TD align=left valign=bottom ><img src="${CPATH}/maf_images/zipcode/round-left_bottom.gif" border=0></TD>
						<TD background="${CPATH}/maf_images/zipcode/bottom_side.gif" border=0></TD>
						<TD align=right valign=bottom><img src="${CPATH}/maf_images/zipcode/round-right_bottom.gif" border=0></TD>
					</TR>
				</TABLE>
				<!-- 주소찾기 끝-->
		</TD>
	</TR>
	<TR>
		<TD bgcolor="ccdfee" height=3>&nbsp;</TD>
	</TR>
	<TR>
		<TD>
			<!-- 완료시작 -->	     
			<TABLE border="0" cellspacing="0" cellpadding="0" width=100%  >
				<TR>
					<TD width="10" align=left valign=top ><img src="${CPATH}/maf_images/zipcode/round-left.gif" ></TD>
					<TD valign=top background="${CPATH}/maf_images/zipcode/top_side.gif"></TD>
					<TD align=right valign=top width=10><img src="${CPATH}/maf_images/zipcode/round-right.gif" border=0></TD>
				</TR>
				<TR >
					<TD background="${CPATH}/maf_images/zipcode/side_left.gif" border=0></TD>
					<TD align=center bgcolor="#FFFFFF" ><table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr>
                        	<td height="25" colspan="2">해당주소를 선택하시고 나머지 주소를 꼭 입력해주세요.</td>
						</tr>
						<tr align="center">
							<td colspan="2" valign="bottom">
								<c:if test="${!empty list}">
                                <select name="wbAddr1Sel" style="width:300px" onChange="javascript:changeAddr1Sel(document.findAddr);">
			                  	</select>
<script language='javascript'>
<!--
	function makeArray(n){
        this.length = n;
        for(var i = 1; i <= n; i++)
            this[i] = 0;
        return this;
    }

	var i = 0;
    var p_zipcd1 = new makeArray('1');
    var p_zipcd2= new makeArray('1');
    var p_addr1 = new makeArray('1');  

<c:forEach var="item" items="${list}" varStatus="status">
	<c:set var="celltel" value="${fn:split(item.zipcode, '-')}" />
	p_zipcd1[${status.count-1}]='${celltel[0]}';
	p_zipcd2[${status.count-1}]='${celltel[1]}';
	p_addr1[${status.count-1}]='${item.addr1}';

	document.findAddr.wbAddr1Sel.options[i] = new Option('<c:out value="[${item.zipcode}] ${item.addr1} ${item.bunji}" escapeXml="true"/>');
	document.findAddr.wbAddr1Sel.options[i].value = '<c:out value="[${item.zipcode}] ${item.addr1}" escapeXml="true"/>'; 

	i++;
</c:forEach>
	<c:if test="${fn:length(list)>200}">
		document.findAddr.wbAddr1Sel.options[i] = new Option('### 너무 많은 주소가 검색되었습니다, 더욱 자세한 주소를 넣어 주세요. ###');
		document.findAddr.wbAddr1Sel.options[i].value = ''; 
	</c:if>
	function setAddr(){
		var frm = getObject("findAddr");
		var n = frm.wbAddr1Sel.selectedIndex;

		var addr1 = p_addr1[n];
		var addr2 = frm.text2.value;
		var code1 = p_zipcd1[n];
		var code2 = p_zipcd2[n];
		var frm = getObject("join",opener.document);
		var frm = getObject("${param.form}",opener.document);
		
		if (document.findAddr.text2.value == "") {
		    document.findAddr.text2.focus();
			alert("상세주소를 입력해 주십시요.");
			return;
		}
		if(frm) {

		  <c:forTokens items="${param.post1}" delims="|" var="i"> 
		  	try{frm.${mhtml:nvl(i,'post1')}.value = code1;}catch(e){}
		  </c:forTokens>
		  <c:forTokens items="${param.post2}" delims="|" var="i"> 
		  	try{frm.${mhtml:nvl(i,'post2')}.value = code2;}catch(e){}
		  </c:forTokens>
			try{frm.${mhtml:nvl(param.post,'post')}.value = code1 +"-"+code2;}catch(e){}
		    try{frm.${mhtml:nvl(param.addr1,'addr1')}.value = addr1;}catch(e){}
		    try{frm.${mhtml:nvl(param.addr2,'addr2')}.value = addr2;}catch(e){}
		    try{frm.${mhtml:nvl(param.addrDisp,'addrDisp')}.value = addr1 + " "+ addr2;}catch(e){}
		    
		    self.close();
		} else {
			alert ("${param.form}을 찾을 수 없습니다.");
		}
	}
//-->
</SCRIPT>
							                  </c:if>
							                  </td>
                                          </tr>
                                          <tr align="center">
                                            <td colspan="2" style="padding-top:8"></td>
                                          </tr>
                                           <tr align="center">
                                            <td colspan="2" style="padding-top:8"><input type="text" name="wbAddr1Disp" class="fc_02" readOnly=true value="" style="border:none; width:400; font-weight:bold; background-color:#E7EEF3;"></td>
                                          </tr>
                                          
                                          
                                           <tr align="center">
                                            <td colspan="2" style="padding-top:8"></td>
                                          </tr>
                                          <tr>
                                            <td width="170" align="right" valign="bottom" style="padding-right:10"><input name="text2" type="text" size="25"></td>
                                            <td width="130" style="padding-top:8"><a href="javascript:setAddr();" onFocus="this.blur()"><img src="${CPATH}/maf_images/zipcode/btn_enter11.gif" width="58" height="25" border="0"></a></td>
                                          </tr>
                                        </table></TD>
										<TD background="${CPATH}/maf_images/zipcode/side_right.gif" border=0></TD>										
									</TR>
									<TR>
										<TD align=left valign=bottom ><img src="${CPATH}/maf_images/zipcode/round-left_bottom.gif" border=0></TD>
										<TD background="${CPATH}/maf_images/zipcode/bottom_side.gif" border=0></TD>
										<TD align=right valign=bottom><img src="${CPATH}/maf_images/zipcode/round-right_bottom.gif" border=0></TD>
									</TR>
									</TABLE>
									<!-- 완료 끝-->
							
							</TD>
                          </TR>
</TABLE>
</form>	
						<!-- CONENTS END -->
						<!-- CLOSE BAR START -->
						<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="margin-top:30">
						    <tr>
						        <td height="1" bgcolor="C7D9E8"></td>
						    </tr>
						    <tr>
						        <td height="40" align="center" bgcolor="ACCEE8"><a href="javascript:window.close();" onFocus="this.blur()"><img src="${CPATH}/maf_images/zipcode/close.gif" width="65" height="20" border="0"></a></td>
						    </tr>
					</table>
<!-- CLOSE BAR END -->
					
</body>
</html>