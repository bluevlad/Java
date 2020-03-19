<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle var = "m" basename = "resources.msg"/>
<fmt:setBundle var = "a" basename = "resources.message"/>
<script language="javascript">
<!--
	function doSelcat (obj) {
	    var txt = document.getElementById("category");
	    if(txt && obj) {
	    	txt.value = obj.value;
	    	if(txt.style) {
			    if (obj.value == "") {
			        txt.style.display = '';
			    }else{
			        txt.style.display = 'none';
			    }
			}
		}
	}
	

	function searchGrp(){
		var frm = document.getElementById("frm");
		var selectgrp = frm.selectgrp.value;
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
		var frm = document.getElementById("frm");
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
		var frm = document.getElementById("frm");
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
	
	//doSelcat (document.getElementById("sel_cat"));

//-->
</script>
<!-- 메인 컨텐츠 들어가는 부분 -->
<TABLE WIDTH=460 BORDER=0 CELLPADDING=0 CELLSPACING=0>
<form id="frm" name="frm" action="${CPATH}/msgmst/grpList.do" method="post">
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
									<td height="7" align="center" valign="middle"></td>
									<td width="226" height="7" align="center" valign="middle"></td>
									<td rowspan="2" align="right" valign="middle"><SELECT
										id="sel_cat" name="selectgrp">
										<!-- onchange=javascript:doSelcat(this);-->
										<OPTION value="" selected><fmt:message bundle = "${m}" key = "msg.selgrp"/></OPTION>
										<option value="all" <c:if test="${param.selectgrp=='all'}">SELECTED</c:if> ><fmt:message bundle = "${m}" key = "msg.grpall"/></option>
										
										<c:if test="${count > 0}">
											<c:forEach var="grplist" items="${msggrplist}">
												${mhtml:HtmlOption(grplist.c_group, grplist.c_group, param.selectgrp)}
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
							<td height="33"></td>
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
				</table><!-- 하단 버튼부분 시작 -->
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
		
				