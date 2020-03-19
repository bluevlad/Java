<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<fmt:setBundle var = "m" basename = "resources.msg"/>
<fmt:setBundle var = "a" basename = "resources.message"/>
<script language="javascript">
<!--
		function delfrm(){ 
		if(confirm("<fmt:message bundle="${a}" key="msgalt.delsure"/>")){
			frm.submit();
	    }
	} 
-->
</script>
<!-- 메인 컨텐츠 들어가는 부분 -->
<TABLE WIDTH=460 BORDER=0 CELLPADDING=0 CELLSPACING=0>
<form name="frm" action="${CPATH}/msgmst/snddelete.do" method="post">
	<TR>
		<TD valign="top" background="${CPATH}/images/msg/papertopbg.jpg" width="460" height="10">

		</TD>
	</TR>
    <TR>
		<TD width="460" height="10" valign="top" background="${CPATH}/images/msg/papermiddlebg.jpg" align="center">
          <TABLE WIDTH=430 height="150" BORDER=0 CELLPADDING=0 CELLSPACING=0>
	        <TR>
              <td valign="top">
				<!--list start--><table width="434" height="387" border="0" cellpadding="0" cellspacing="0">
                        				<tr>
                        					<td height="26"><table width="434" height="26" border="0" cellpadding="0" cellspacing="0">
                        							<tr>
                        								<td height="7" colspan="2" align="center" valign="middle"></td>
                        								</tr>
                        							<tr>
                        								<td width="26" align="center" valign="middle"><img src="${CPATH}/images/msg/msg_icon.gif" width="26" height="16"></td>
                        								<td align="left"><span class="style3"><fmt:message bundle = "${m}" key = "msg.sendmsg"/></span></td>
                        								</tr>
                        							</table></td>
                        					</tr>
                        				<tr>
                        					<td height="376">
												<table width="434" height="376" border="0" cellpadding="0" cellspacing="0">
                                            	<tr bgcolor="ACACAC">
                                            		<td width="434" colspan="9" align="center" valign="middle" bgcolor="D7D7D7"><table width="432" height="374" border="0" cellpadding="0" cellspacing="0">
                                                    	<tr>
                                                    		<td valign="top" bgcolor="#FFFFFF"><table width="432" height="314" border="0" cellpadding="0" cellspacing="0">
                                                            	<tr>
                                                            		<td width="73" height="25" align="left" bgcolor="EBEBEB" class="msg_header"><strong><fmt:message bundle = "${m}" key = "msg.rcvnm"/></strong></td>
                                                            		<td width="143" align="left" class="msg_td">${msgsndview.nm_rcv}</td>
                                                            		<td width="73" align="left" bgcolor="EBEBEB" class="msg_header"><strong><fmt:message bundle = "${m}" key = "msg.sendDate"/></strong></td>
                                                            		<td width="143" align="left" class="msg_td"><fmt:formatDate value="${msgsndview.reg_dt}" pattern="yyyy-MM-dd HH:mm" /></td>
                                                            	</tr>
                                                            	<tr>
                                                            		<td height="1" colspan="4" bgcolor="D7D7D7"></td>
                                                            		</tr>
                                                            	<tr>
                                                            		<td height="25" align="left" bgcolor="EBEBEB" class="msg_header"><strong><fmt:message bundle = "${m}" key = "msg.msgTitle"/></strong></td>
                                                            		<td height="25" colspan="3" align="left" class="msg_td">${msgsndview.title}</td>
                                                            		</tr>
                                                            	<tr>
                                                            		<td height="1" colspan="4" bgcolor="D7D7D7"></td>
                                                            		</tr>
                                                            	<tr align="left" valign="top">
                                                            		<td height="262" colspan="4" class="msg_td">
																		<div style="width: 432px; height: 322px; overflow: auto; solid #898989;">
																			${mhu:nl2br(msgsndview.contents)}</div></td>
                                                            		</tr>
                                                            	</table></td>
                                                    		</tr>
                                                    	</table>

														</td>
                                            		</tr>
                                            	</table></td>
                        				</tr>
                        				<tr>
											<c:if test="${empty msgsndview.rcv_dt}">
                        						<td height="20" align="right">[<fmt:message bundle = "${m}" key = "msg.rcvDate"/> : <fmt:message bundle = "${m}" key = "msg.rcvStatus"/>]</td>
											</c:if>   
											<c:if test="${!(empty msgsndview.rcv_dt)}">
												<td height="20" align="right">[<fmt:message bundle = "${m}" key = "msg.rcvDate"/> : <fmt:formatDate value="${msgsndview.rcv_dt}" pattern="yyyy-MM-dd HH:mm" />]</td>
											</c:if>                     				
										</tr>
                        				<tr>
                        					<td>&nbsp;</td>
                        				</tr>
                        	
                        				</table><!--list end-->
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
						<td><a href="<c:url value="/msgmst/writeForm.do">
												<c:param name="usn_snd" value="${msgsndview.usn_rcv}"/>
												<c:param name="nm_snd" value="${msgsndview.nm_rcv}"/>
										</c:url>"><img
							src="${CPATH}/images/button/msg_write.gif"  height="21" border="0"></a></td>
						<td width="4"></td>
						<td><a href="javascript:history.back();"><img src="${CPATH}/images/button/back.gif"  height="21" border="0"></a></td>
						<td width="4"></td>
                		<td><a href="javascript:delfrm();"><img src="${CPATH}/images/button/delete.gif" width="56" height="21" border="0"></a></td>
                		<td width="4"></td>
                		<td><a href="javascript:top.window.close();"><img src="${CPATH}/images/button/close.gif" width="56" height="21" border="0"></a></td>
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
<input type="hidden" name="msgid" value="${msgsndview.msgid}">
<input type="hidden" name="Dusn_snd" value="${msgsndview.usn_snd}">
<input type="hidden" name="usn_snd" value="${msgsndview.usn_rcv}">
<input type="hidden" name="nm_snd" value="${msgsndview.nm_rcv}">
</form>