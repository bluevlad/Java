<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle var = "m" basename="resources.msg"/>
<fmt:setBundle var = "a" basename="resources.message"/>
<script language="JavaScript" type="text/JavaScript">
<!--
	function deleteForm(){ 
		var frmchk = document.getElementsByName("msgid_ck");
	
		if(frmchk != null){
			len = frmchk.length;
			cnt = 0;
			if(len == null){
				if(frmchk.checked)
					cnt++;
			}
			else{
				for(i = 0; i < len; i++){
					if(frmchk[i].checked)
						cnt++;
				}
			}
	
			if(cnt <= 0)
				//alert("삭제할 쪽지를 선택하세요");
				alert("<fmt:message bundle = "${a}" key = "msgalt.delmsg"/>");
			else{					
				//if(confirm("정말 삭제 하시겠습니까?")){
				if(confirm("<fmt:message bundle="${a}" key="msgalt.delsure"/>")){
					frm.submit();
				}
			}
		}
	} 

function getLength(obj){
	if(obj ==  "undefined" || obj != "[object]"){ 
		return 0;
	}else if(toString(obj.length) == "[object]" && obj.length > 1){
		return obj.length;
	}else{
		return 1;
	}
}
//-->
</script>
<form name="frm" id="frm"  action="${CPATH}/msgmst/snddelete.do" method="post">
<table width="456" height="490" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td align="center" valign="top">
		<table width="100%" height="484" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td><!--menu start-->
				<table width="450" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><img src="${CPATH}/images/msg/icon_02.gif" width="82" height="43"></td>
						<td><a href="${CPATH}/msgmst/rcvlist.do"
							onMouseOut="MM_swapImgRestore()"
							onMouseOver="MM_swapImage('msg_m01','','${CPATH}/images/msg/menu_on_01.gif',1)"><img
							src="${CPATH}/images/msg/menu_off_01.gif" name="msg_m01" width="91"
							height="43" border="0"></a></td>
						<td><img src="${CPATH}/images/msg/menu_on_02.gif" width="91" height="43"></td>
						<td><a href="${CPATH}/msgmst/writeForm.do"
							onMouseOut="MM_swapImgRestore()"
							onMouseOver="MM_swapImage('msg_m03','','${CPATH}/images/msg/menu_on_03.gif',1)"><img
							src="${CPATH}/images/msg/menu_off_03.gif" name="msg_m03" width="91"
							height="43" border="0"></a></td>
						<td><a href="${CPATH}/msgmst/msgmnglist.do" onMouseOut="MM_swapImgRestore()"
							onMouseOver="MM_swapImage('msg_m04','','${CPATH}/images/msg/menu_on_04.gif',1)"><img
							src="${CPATH}/images/msg/menu_off_04.gif" name="msg_m04" width="95"
							height="43" border="0"></a></td>
					</tr>
				</table>
				<!--menu end--></td>
			</tr>
			<tr>
				<td height="3"></td>
			</tr>
			<tr>
				<td height="463"><!--list start-->
				<table width="450" height="463" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="center" valign="middle" bgcolor="CCCCCC">
						<table width="448" height="461" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="center" valign="middle" bgcolor="F8F8F8">
								<table width="434" height="387" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="26">
										<table width="434" height="26" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td height="7" colspan="3" align="center" valign="middle"></td>
											</tr>
											<tr>
												<td width="26" align="center" valign="middle"><img
													src="${CPATH}/images/msg/msg_icon.gif" width="26" height="16"></td>
												<td align="left"><span class="style3"><fmt:message bundle = "${m}" key = "msg.sendmsg"/></span></td>
											<c:if test="${count>0}">
												<td width="30"><span class="style4">${currentPage}/${pageCount}</span></td>
											</c:if>
											<c:if test="${count<1}">
												<td width="30"><span class="style4">${currentPage-1}/${pageCount}</span></td>
											</c:if>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td height="376">
										<table width="434" height="376" border="0" cellpadding="0"
											cellspacing="0">
											<tr bgcolor="ACACAC">
												<td height="2" colspan="9"></td>
											</tr>
											<tr bgcolor="#FFFFFF">
												<td height="1" colspan="9"></td>
											</tr>
											<tr bgcolor="EBEBEB" height="10">
												<td width="52" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.no"/></strong></td>
												<td width="2" height="10"><img
													src="../images/msg/title_line.gif" width="2" height="26"></td>
												<td width="77" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.rcvnm"/></strong></td>
												<td width="2" height="10"><img
													src="../images/msg/title_line.gif" width="2" height="26"></td>
												<td width="179" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.content"/></strong></td>
												<td width="2" height="10"><img
													src="../images/msg/title_line.gif" width="2" height="26"></td>
												<td width="85" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.date"/></strong></td>
												<td width="2" height="10"><img
													src="../images/msg/title_line.gif" width="2" height="26"></td>
												<td width="33" height="10" align="center"><input
													type="checkbox" name="checkboxAll"  id="checkboxAll"  class= "checkbox" 
														onclick="allChekboxToggle(this, 'frm','msgid_ck');"><!-- <input type='checkbox' name='checkAll' style="border:none;" onclick='javascript:commAllCheck(frm, "msgid", this,  true);' /> -->
												</td>
											</tr>
											<tr bgcolor="D7D7D7">
												<td height="1" colspan="9" align="center"></td>
											</tr>
											<!-- list start -->
											<c:if test="${count > 0}">

												<c:forEach var="msglist" items="${msgmstlist}" varStatus="status">
													<c:if test="${msglist.deleted != 'D'}">
														<tr bgcolor="#FFFFFF">
															<td height="10" align="center">${status.count + (currentPage-1)*pageSize}</td>
															<td height="10">&nbsp;</td>
															<td height="10" align="center">${msglist.nm_rcv}</td>
															<td height="10">&nbsp;</td>
															<td height="10" align="left"><a href="${CPATH}/msgmst/sndview.do?usn_snd=${msglist.usn_snd}&msgid=${msglist.msgid}">
																	<c:if test="${fn:length(msglist.title) >= 10}">${fn:substring(msglist.title, 0, 10)}...</c:if>
																	<c:if test="${fn:length(msglist.title) < 10}">${msglist.title}</c:if>
																</a></td>
															<td height="10">&nbsp;</td>
															<td height="10" align="center"><fmt:formatDate
																value="${msglist.reg_dt}" pattern="yyyy-MM-dd" /></td>
															<td height="10">&nbsp;</td>
															<td height="10" align="center"><input type="checkbox" class= "checkbox"
																name="msgid_ck" value="${msglist.msgid}">
															</td>
														</tr>
														<tr bgcolor="D7D7D7">
															<td height="1" colspan="9" align="center"></td>
														</tr>
													</c:if>

												</c:forEach>
											</c:if>
											<c:if test="${count<1}">
												<tr bgcolor="#FFFFFF">
													<td height="10" align="center"  colspan="9"><fmt:message bundle = "${m}" key = "msg.emptysnd"/>
													</td>
												</tr>
												<tr bgcolor="D7D7D7">
													<td height="1" colspan="9" align="center"></td>
												</tr>
											</c:if>
											<!-- list end -->
											<tr>
										</table>
										</td>
									</tr>
									<tr>
										<td height="12"></td>
									</tr>
									<tr>
										<td height="12" align="center">
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td><c:if test="${count > 0}">
													<c:if test="${startPage > screenSize}">
														<a
															href="${CPATH}/msgmst/sndlist.do?num=${startPage - screenSize}"><img
															src="../images/msg/pre.gif" width="12" height="12" border="0"></a>
													</c:if>

													<c:forEach var="i" begin="${startPage}" end="${endPage}">
														<a href="${CPATH}/msgmst/sndlist.do?num=${i}">[${i}]</a>
													</c:forEach>

													<c:if test="${endPage < pageCount}">
														<a
															href="${CPATH}/msgmst/sndlist.do?num=${startPage + screenSize}"><img
															src="../images/msg/next.gif" width="12" height="12" border="0"></a>
													</c:if>
												</c:if></td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td height="12"></td>
									</tr>
									<tr>
										<td height="7"></td>
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
			<tr>
				<td height="9"></td>
			</tr>
			<tr>
				<td align="right">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><a href="javascript:deleteForm();"><img src="${CPATH}/images/button/delete.gif" width="56" height="21" border="0"></a></td>
						<td width="4"></td>
						<td><a href="javascript:top.window.close();"><img src="${CPATH}/images/button/close.gif" width="56" height="21" border="0"></a></td>
						<td width="11"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="5"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
