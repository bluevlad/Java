<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle var ="m" basename="resources.msg"/>
<fmt:setBundle var = "a" basename="resources.message"/>
<script language="javascript">
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
-->
</script>
<!-- 메인 컨텐츠 들어가는 부분 -->
<TABLE WIDTH=460 BORDER=0 CELLPADDING=0 CELLSPACING=0>
<form name="frm" action="${CPATH}/msgmst/rcvdelete.do" method="post">
	<TR>
		<TD valign="top" background="${CPATH}/images/msg/papertopbg.jpg" width="460" height="10">
		</TD>
	</TR>
    <TR>
		<TD width="460" height="10" valign="top" background="${CPATH}/images/msg/papermiddlebg.jpg" align="center">
          <TABLE WIDTH=430 height="150" BORDER=0 CELLPADDING=0 CELLSPACING=0>
	        <TR>
              <td valign="top"><!--list start-->

										<table width="434" height="26" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td height="7" colspan="3" align="center" valign="middle"></td>
											</tr>
											<tr>
												<td width="26" align="center" valign="middle"><img
													src="${CPATH}/images/msg/msg_icon.gif" width="26" height="16"></td>
												<td align="left"><span class="style3"><fmt:message bundle = "${m}" key = "msg.rcvmsg"/></span></td>
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
										<td height="360">
										<table width="434" height="360" border="0" cellpadding="0"
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
													src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
												<td width="77" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.sndnm"/></strong></td>
												<td width="2" height="10"><img
													src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
												<td width="179" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.content"/></strong></td>
												<td width="2" height="10"><img
													src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
												<td width="85" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.date"/></strong></td>
												<td width="2" height="10"><img
													src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
												<td width="33" height="10" align="center"><input
													type="checkbox" name="checkboxAll" value="checkbox" class= "checkbox"
													onclick="allChekboxToggle(this, 'frm','msgid_ck');"> 
												</td>
											</tr>
											<tr bgcolor="D7D7D7">
												<td height="1" colspan="9" align="center"></td>
											</tr>

											<c:if test="${count > 0}">

												<c:forEach var="msglist" items="${msgmstlist}" varStatus="status">
													<c:if test="${msglist.deleted != 'D'}">
														<tr bgcolor="#FFFFFF">
															<td height="10" align="center">${status.count + (currentPage-1)*pageSize}</td>
															<td height="10">&nbsp;</td>
															<td height="10" align="center">${msglist.nm_snd}</td>
															<td height="10">&nbsp;</td>
															<td height="10" align="left"><a href="${CPATH}/msgmst/rcvview.do?usn_rcv=${msglist.usn_rcv}&msgid=${msglist.msgid}">
																	<c:if test="${fn:length(msglist.title) >= 10}">${fn:substring(msglist.title, 0, 10)}...</c:if>
																	<c:if test="${fn:length(msglist.title) < 10}">${msglist.title}</c:if>
																	<c:if test="${empty msglist.rcv_dt}"><strong><font color="red" size="1">[new]</font></strong></c:if>
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
													<td height="10" align="center"  colspan="9"><fmt:message bundle = "${m}" key = "msg.emptyrcv"/>
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
															href="${CPATH}/msgmst/rcvlist.do?num=${startPage - screenSize}"><img
															src="${CPATH}/images/msg/pre.gif" width="12" height="12" border="0"></a>
													</c:if>

													<c:forEach var="i" begin="${startPage}" end="${endPage}">
														<a href="${CPATH}/msgmst/rcvlist.do?num=${i}">[${i}]</a>
													</c:forEach>

													<c:if test="${endPage < pageCount}">
														<a
															href="${CPATH}/msgmst/rcvlist.do?num=${startPage + screenSize}"><img
															src="${CPATH}/images/msg/next.gif" width="12" height="12" border="0"></a>
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
								
				<!--list end-->
			  </td>
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
						<td><a href="javascript:deleteForm();"><img
							src="${CPATH}/images/button/delete.gif" width="56" height="21"
							border="0"></a></td>
						<td width="4"></td>
						<td><a href="javascript:top.window.close();"><img
							src="${CPATH}/images/button/close.gif" width="56" height="21"
							border="0"></a></td>
						<td width="11"></td>
					</tr>
				<!-- 하단 버튼부분 끝 --></table>
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
</form>

