<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
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
<form name="frm" action="${CPATH}/msgmst/snddelete.do" method="post">
<table width="456" height="490" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="top"><table width="100%" height="484" border="0" cellpadding="0" cellspacing="0">
        	<tr>
        		<td height="43">
				<!--menu start--><table width="450" height="43" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                		<td><img src="${CPATH}/images/msg/icon_02.gif" width="82" height="43"></td>
                		<td><a href="${CPATH}/msgmst/rcvlist.do" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('msg_m01','','${CPATH}/images/msg/menu_on_01.gif',1)"><img src="${CPATH}/images/msg/menu_off_01.gif" name="msg_m01" width="91" height="43" border="0"></a></td>
                		<td><img src="${CPATH}/images/msg/menu_on_02.gif" width="91" height="43"></td>
                		<td><a href="${CPATH}/msgmst/writeForm.do" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('msg_m03','','${CPATH}/images/msg/menu_on_03.gif',1)"><img src="${CPATH}/images/msg/menu_off_03.gif" name="msg_m03" width="91" height="43" border="0"></a></td>
                		<td><a href="${CPATH}/msgmst/msgmnglist.do" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('msg_m04','','${CPATH}/images/msg/menu_on_04.gif',1)"><img src="${CPATH}/images/msg/menu_off_04.gif" name="msg_m04" width="95" height="43" border="0"></a></td>
                		</tr>
                	</table><!--menu end-->
					</td>
        		</tr>
        	<tr>
        		<td height="3"></td>
        		</tr>
        	<tr>
        		<td height="463">
				<!--list start--><table width="450" height="463" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                		<td align="center" valign="middle" bgcolor="CCCCCC"><table width="448" height="461" border="0" cellpadding="0" cellspacing="0">
                        	<tr>
                        		<td align="center" valign="middle" bgcolor="F8F8F8"><table width="434" height="387" border="0" cellpadding="0" cellspacing="0">
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
																			${mhtml:nl2br(msgsndview.contents)}</div></td>
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
                        				<tr>
                        					<td height="4"></td>
                        					</tr>
                        				<tr>
                        					<td height="7"></td>
                        					</tr>
                        				</table></td>
                        		</tr>
                        	</table></td>
                	</tr>
                	</table><!--list end-->
					</td>
        		</tr>
        	<tr>
        		<td height="9"></td>
        		</tr>
        	<tr>
        		<td align="right"><table border="0" cellspacing="0" cellpadding="0">
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
                	</table></td>
        		</tr>
        	<tr>
        		<td height="5"></td>
        		</tr>
        	</table></td>
	</tr>
</table>

<input type="hidden" name="msgid" value="${msgsndview.msgid}">
<input type="hidden" name="Dusn_snd" value="${msgsndview.usn_snd}">
<input type="hidden" name="usn_snd" value="${msgsndview.usn_rcv}">
<input type="hidden" name="nm_snd" value="${msgsndview.nm_rcv}">
</form>
