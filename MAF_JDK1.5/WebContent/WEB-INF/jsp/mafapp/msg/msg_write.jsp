<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle  var="m" basename="resources.msg"/>
<fmt:setBundle var="a" basename="resources.message"/>
<%
		String nm_rcv = (String) request.getAttribute("nm_rcv");
		String usn_rcv = (String) request.getAttribute("usn_rcv");
%>

<script language="JavaScript" type="text/JavaScript">
<!--
function cancel(form){
	form.nm_rcv.value="";
	form.usn_rcv.value="";
	form.title.value="";
	form.contents.value="";
}  
/*
function search_pop(){
 var win;
    if(win == null){
        win = window.open("${CPATH}/msgmst/searchForm.do", "searchpop","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=yes,width=370,height=257,left=0,top=0");
    }
    win.focus();
}
*/
function insertForm(){
	
    var msg = "";

	if(frm.nm_rcv.value.replace(/(^\s*)|(\s*$)/gi, "")==""){
    	//msg+='"쪽지받을 사람을 선택하세요"\n';
		msg+='"<fmt:message bundle = "${a}" key = "msgalt.selnm"/>"\n';
	}
	if(frm.title.value.replace(/(^\s*)|(\s*$)/gi, "")=="") {
		//msg+='"제목을 입력하세요"\n';
		msg+='"<fmt:message bundle = "${a}" key = "msgalt.intitle"/>"\n';
	}
	if(frm.contents.value.replace(/(^\s*)|(\s*$)/gi, "")=="") {
		//msg+='"내용을 입력하세요"\n';
		msg+='"<fmt:message bundle = "${a}" key = "msgalt.incontent"/>"\n';
	}
    
	if(msg == ""){
   		frm.submit();
	}else if(msg != ""){
      			alert(msg);
         		return;
	}
}

//-->
</script>
<!-- 메인 컨텐츠 들어가는 부분 -->
<TABLE WIDTH=460 BORDER=0 CELLPADDING=0 CELLSPACING=0>
<form name="frm" action="${CPATH}/msgmst/write.do" method="post">
	<TR>
		<TD valign="top" background="${CPATH}/images/msg/papertopbg.jpg" width="460" height="10"></TD>
	</TR>
    <TR>
		<TD width="460" height="10" valign="top" background="${CPATH}/images/msg/papermiddlebg.jpg" align="center">
          <TABLE WIDTH=430 height="150" BORDER=0 CELLPADDING=0 CELLSPACING=0>
	        <TR>
              <td valign="top"><!--list start-->
										<table width="434" height="26" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td height="7" colspan="2" align="center" valign="middle"></td>
											</tr>
											<tr>
												<td width="26" align="center" valign="middle"><img
													src="${CPATH}/images/msg/msg_icon.gif" width="26" height="16"></td>
												<td align="left"><span class="style3"><fmt:message bundle="${m}" key="msg.write"/></span></td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td height="376">
										<table width="434"  border="0" cellpadding="0"
											cellspacing="0">
											<tr bgcolor="ACACAC">
												<td width="434" colspan="9" align="center" valign="middle"
													bgcolor="D7D7D7">
												<table width="432"  border="0" cellpadding="0"
													cellspacing="0">
													<tr> 
														<td valign="top" bgcolor="#FFFFFF"> 
														<table width="434" border="0" cellpadding="0"
															cellspacing="0">
															<tr bgcolor="ACACAC">
																<td height="2" colspan="9"></td>
															</tr>
															<tr bgcolor="#FFFFFF">
																<td height="1" colspan="9"></td>
															</tr>
															<tr bgcolor="EBEBEB">
																<td width="118" height="26" align="center" bgcolor="EBEBEB"
																	class="msg_header"><fmt:message bundle="${m}" key="msg.rcvnm"/></td>
																<td height="26" colspan="9" bgcolor="#FFFFFF">
																<table width="299" border="0" align="left"
																	cellpadding="3" cellspacing="0">
																	<tr>
																		<td width="212">
																	<% 
																			if(nm_rcv == null){
																	%> 
																			<input readonly name="nm_rcv" type="text" size="35"  style="border-style :solid;border-width=1;border-color:#C0C0C0">
																	<%} else {%> 
																			<input readonly name="nm_rcv" type="text" size="35"
																			style="border-style :solid;border-width=1;border-color:#C0C0C0"
																			value="<%=nm_rcv%>"> 
																	<%
																   		}
																	%>
																		</td>
																		<td width="30"><a href="javascript:popupWindow('<c:url value='/msgmst/searchForm.do'/>','msg_srch')"><img
																			src="${CPATH}/images/button/s_search.gif" width="30"
																			height="18" border="0"></a></td>
																	</tr>
																</table>
																</td>
															</tr>
															<tr bgcolor="D7D7D7">
																<td height="1" colspan="9"></td>
															</tr>
															<tr bgcolor="EBEBEB">
																<td width="118" height="26" align="center"
																	class="msg_header"><fmt:message bundle="${m}" key="msg.msgTitle"/></td>
																<td height="26" colspan="8" bgcolor="#FFFFFF">
																<table width="299" border="0" cellspacing="0"
																	cellpadding="3">
																	<tr>
																		<td><input name="title" type="text" size="50"
																			style="border-style :solid;border-width=1;border-color:#C0C0C0"></td>
																	</tr>
																</table>
																</td>
															</tr>
															<tr bgcolor="D7D7D7">
																<td height="1" colspan="9" align="center"></td>
															</tr>
															<tr bgcolor="#FFFFFF">
																<td height="230" colspan="9">
																<table width="434" height="230" border="0"
																	cellpadding="3" cellspacing="0">
																	<tr>
																		<td><textarea name="contents" cols="59" rows="22"
																			wrap="hard"></textarea></td>
																	</tr>
																</table>
																</td>
															</tr>
															<tr bgcolor="D7D7D7">
																<td height="1" colspan="9" align="center"></td>
															</tr>
														</table>
														</td>
													</tr>
												</table>
												</td>
											</tr>
										</table>
										
								
				<!--list end--></td>
			</tr>
		  </table>
	</TR>
	<TR>
		<TD valign="top" background="${CPATH}/images/msg/paperbtbg.jpg" width="460" height="10">
		</TD>
	</TR>
</TABLE>
<!-- 메인컨텐츠 들어가는 부분 -->
<!-- 하단 -->
<TABLE WIDTH=460 BORDER=0 CELLPADDING=0 CELLSPACING=0>
	<TR>
		<TD height="10" width="460" valign="top" background="${CPATH}/images/msg/paperbt2.jpg">

		</TD>
	</TR>
    <TR>
		<TD width="460" height="20" valign="top" background="${CPATH}/images/msg/paperbt1.jpg" align="center">
          <TABLE WIDTH=430 height="20" BORDER=0 CELLPADDING=0 CELLSPACING=0>
	        <TR>
              <td align="right"><!-- 하단 버튼부분 시작 --><table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><a href="javascript:insertForm();"><img
							src="${CPATH}/images/button/send.gif" width="56" height="21" border="0"></a></td>
						<td width="4"></td>
						<td><a href="javascript:cancel(document.frm);"><img
							src="${CPATH}/images/button/cancel.gif" width="56" height="21"
							border="0"></a></td>
						<td width="4"></td>
						<td><a href="javascript:top.window.close();"><img
							src="${CPATH}/images/button/close.gif" width="56" height="21"
							border="0"></a></td>
						<td width="11"></td>
					</tr>
				</table>
			  </td>
			</tr>
		  </table>
	</TR>
	<TR>
		<TD width="460" height="10" valign="top" background="${CPATH}/images/msg/paperbt3.jpg" height="30">
	    </TD>
	</TR>
</TABLE>
<!-- 하단 -->
<% 
		if(usn_rcv == null){
%> 
		<input type="hidden" name="usn_rcv">
<%} else {%> 
		<input type="hidden" name="usn_rcv" value="<%=usn_rcv%>">
<%
   		}
%>
</form>
