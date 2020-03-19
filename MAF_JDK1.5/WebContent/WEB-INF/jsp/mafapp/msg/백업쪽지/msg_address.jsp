<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle var = "m" basename = "resources.msg"/>
<fmt:setBundle var = "a" basename = "resources.message"/>
<script language="javascript">
<!--
doSelcat (document.getElementById("sel_cat"));

function searchGrp(){
	//var selectgrp = document.getElementsByName("selectgrp");
	var selectgrp = document.frm.selectgrp.value;
	if(selectgrp == ""){
		//alert("그룹을 선택해주세요.");
		alert("<fmt:message bundle="${a}" key="msgalt.selgrp"/>");
	}else if(selectgrp == "all"){
		frm.action = "${CPATH}/msgmst/msgmnglist.do";
		frm.submit();
	}else{
		frm.submit();
	}
}

	function addrDelete(){ 
		var frmchk = document.getElementsByName("c_usn_ck");
	
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
				//alert("삭제할 사람을 선택하세요");
				alert("<fmt:message bundle="${a}" key="msgalt.delgrp"/>");
			else{					
				//if(confirm("정말 삭제 하시겠습니까?")){
				if(confirm("<fmt:message bundle="${a}" key="msgalt.delsure"/>")){
					frm.action = "${CPATH}/msgmst/addrDelete.do";
					frm.submit();
				}
			}
		}
	} 

	function writeForm(){

		var frmchk = document.getElementsByName("c_usn_ck");
	
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
				//alert("쪽지보낼 사람을 선택하세요.");
				alert("<fmt:message bundle="${a}" key="msgalt.selnm"/>");
			else{					
				frm.action = "${CPATH}/msgmst/grpWrite.do";
				frm.submit();
	
			}
		}
	}
//-->
</script>
<form name="frm" action="${CPATH}/msgmst/grpList.do" method="post">
<table width="456" height="490" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td align="center" valign="top">
		<table width="100%" height="484" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<!--menu start-->
				<td>
				<table width="450" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><img src="${CPATH}/images/msg/icon_04.gif" width="82" height="43"></td>
						<td><a href="${CPATH}/msgmst/rcvlist.do"
							onMouseOut="MM_swapImgRestore()"
							onMouseOver="MM_swapImage('msg_m01','','${CPATH}/images/msg/menu_on_01.gif',1)"><img
							src="${CPATH}/images/msg/menu_off_01.gif" name="msg_m01" width="91"
							height="43" border="0"></a></td>
						<td><a href="${CPATH}/msgmst/sndlist.do"
							onMouseOut="MM_swapImgRestore()"
							onMouseOver="MM_swapImage('msg_m02','','${CPATH}/images/msg/menu_on_02.gif',1)"><img
							src="${CPATH}/images/msg/menu_off_02.gif" name="msg_m02" width="91"
							height="43" border="0"></a></td>
						<td><a href="${CPATH}/msgmst/writeForm.do"
							onMouseOut="MM_swapImgRestore()"
							onMouseOver="MM_swapImage('msg_m03','','${CPATH}/images/msg/menu_on_03.gif',1)"><img
							src="${CPATH}/images/msg/menu_off_03.gif" name="msg_m03" width="91"
							height="43" border="0"></a></td>
						<td><img src="${CPATH}/images/msg/menu_on_04.gif" width="95" height="43"></td>
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
												<td height="7" align="center" valign="middle"></td>
												<td width="226" height="7" align="center" valign="middle"></td>
												<td rowspan="2" align="right" valign="middle"><SELECT
													id=sel_cat name=selectgrp>
													<!-- onchange=javascript:doSelcat(this);-->
													<OPTION value="" selected><fmt:message bundle = "${m}" key = "msg.selgrp"/></OPTION>
													<option value="all"><fmt:message bundle = "${m}" key = "msg.grpall"/></option>
													<c:if test="${count > 0}">
														<c:forEach var="grplist" items="${msggrplist}">
															<OPTION value="${grplist.c_group}">${grplist.c_group}</OPTION>
														</c:forEach>
													</c:if>
												</SELECT></TD>
												<!--<TD class=bbs_view_td><INPUT id=category size=10
													name=c_group maxbyte="10" hname="카테고리" align="absmiddle"></td>-->
												<td width="38" rowspan="2" align="center" valign="middle"><a
													href="javascript:searchGrp();"> <img
													src="${CPATH}/images/button/s_search.gif" border="0" width="30"
													height="18"></a></td>
											</tr>
											<tr>
												<td width="28" align="center" valign="middle"><img
													src="${CPATH}/images/msg/msg_icon.gif" width="26" height="16"></td>
												<td align="left" class="style3"><fmt:message bundle = "${m}" key = "msg.addrgrp"/></td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td height="376">
										<div style="width: 100%; height: 100%; overflow-x: hidden; overflow-y: auto; solid #898989;">
										<table width="434" height="376" border="0" cellpadding="0"
											cellspacing="0">
											<tr bgcolor="ACACAC">
												<td height="2" colspan="8"></td>
											</tr>
											<tr bgcolor="#FFFFFF">
												<td height="1" colspan="8"></td>
											</tr>

											<tr bgcolor="EBEBEB" height="10">
												<td width="52" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.no"/></strong></td>
												<td width="2" height="10"><img
													src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
												<td width="142" height="10" align="center"><strong
													class="txt02"><fmt:message bundle = "${m}" key = "msg.grpnm"/></strong></td>
												<td width="2" height="10"><img
													src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
												<td width="201" height="10" align="center"><strong><fmt:message bundle = "${m}" key = "msg.search.nm"/></strong></td>
												<td width="2" height="10"><img
													src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
												<td width="33" height="10" align="center"><input
													type="checkbox" name="checkboxAll" value="checkbox" class= "checkbox"
													onclick="allChekboxToggle(this, 'frm','c_usn_ck');"> <!--<input type='checkbox' name='checkAll' style="border:none;" onclick='javascript:commAllCheck(frm, "msgid", this,  true);' />-->
												</td>
												<td width="15" height="10" align="center"></td>
											</tr>
											<tr bgcolor="D7D7D7">
												<td height="1" colspan="8" align="center"></td>
											</tr>
											<c:if test="${count > 0}">
												<c:forEach var="mnglist" items="${msgmnglist}"
													varStatus="status">
													<tr bgcolor="#FFFFFF">

														<td height="10" align="center">${status.count}</td>
														<td height="10">&nbsp;</td>
														<td height="10" align="center">${mnglist.c_group}</td>
														<td height="10">&nbsp;</td>
														<td height="10" align="center">${mnglist.nm}</td>
														<td height="10">&nbsp;</td>
														<td height="10" align="center"><input type="checkbox" class= "checkbox"
															name="c_usn_ck" value="${mnglist.c_usn}:${mnglist.nm}"> <!--<input type="hidden" name=c_nm value="${mnglist.nm}">-->
														</td>
														<td width="15" height="10" align="center"></td>
													</tr>
													<tr bgcolor="D7D7D7">
														<td height="1" colspan="8" align="center"></td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${count < 1}">
												<tr bgcolor="#FFFFFF">
													<td height="10" align="center"  colspan="8"><fmt:message bundle = "${m}" key = "msg.emptyaddr"/>
													</td>
												</tr>
												<tr bgcolor="D7D7D7">
													<td height="1" colspan="8" align="center"></td>
												</tr>
											</c:if>
											<tr>
										</table>
										</div>
										</td>
									</tr>
									<tr>
										<td height="12"></td>
									</tr>
									<tr>
										<td height="12" align="center">&nbsp;</td>
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
						<td><a href="javascript:writeForm();"><img
							src="${CPATH}/images/button/msg_write.gif" height="21" border="0"></a></td>
						<td width="4"></td>
						<td><a href="javascript:addrDelete();"><img
							src="${CPATH}/images/button/group_del.gif" width="56" height="21"
							border="0"></a></td>
						<td width="4"></td>
						<td><a href="${CPATH}/msgmst/addrInsertForm.do"><img
							src="${CPATH}/images/button/friend_regist.gif" width="56" height="21"
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
			<tr>
				<td height="5"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<!--<input type="hidden" name="c_group" value="${grplist.c_group}">--> <input
	type="hidden" name="usn" value="${sessionScope.msession.usn}">