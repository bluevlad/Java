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
	
	function InquiryForm()
	{	
		var frm = document.getElementById("frm");
		if(frm.selname.value == ""){
			alert("<fmt:message bundle="${a}" key="msgalt.selcon"/>");
		}
	    else if (frm.nm.value == "") {
	        //alert("검색어를 입력하세요");
			alert("<fmt:message bundle="${a}" key="msgalt.input"/>");
	        frm.nm.focus();
	
	    } else {
	        
	        frm.submit();
	
	        return true;
	    }
	}

	function addrInsert(){ 
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

			if(cnt <= 0){
				//alert("등록할 사람을 선택하세요.");
				alert("<fmt:message bundle="${a}" key="msgalt.inperson"/>");
			} else if (frm.selectgrp.value == ""){
				//alert("그룹을 선택하세요.");		
				alert("<fmt:message bundle="${a}" key="msgalt.selgrp"/>");
			}else{
				frm.action = "${CPATH}/msgmst/addrInsert.do";
				frm.submit();
			}
		}
	} 
	
//-->
</script>
<form name="frm" id="frm" action="${CPATH}/msgmst/grpSearch.do" method="post">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<TR>
		<TD COLSPAN=6><IMG SRC="${CPATH}/images/msg/pptop3_1.jpg" WIDTH=460 HEIGHT=48 ALT=""></TD>
	</TR>
	<tr>
		<TD ROWSPAN=2><IMG SRC="${CPATH}/images/msg/pptop3_2.jpg" WIDTH=66 HEIGHT=32 ALT=""></TD>
		<td><a href="${CPATH}/msgmst/rcvlist.do"><IMG SRC="${CPATH}/images/msg/pptop3_3.jpg" WIDTH=86 HEIGHT=27 BORDER=0 ALT=""></a></td>
		<td><a href="${CPATH}/msgmst/sndlist.do"><IMG SRC="${CPATH}/images/msg/pptop3_4.jpg" WIDTH=78 HEIGHT=27 BORDER=0 ALT=""></a></td>
		<td><a href="${CPATH}/msgmst/writeForm.do"><IMG SRC="${CPATH}/images/msg/pptop3_5.jpg" WIDTH=80 HEIGHT=27 BORDER=0 ALT=""></a></td>
		<td><IMG SRC="${CPATH}/images/msg/pptop3_6.jpg" WIDTH=79 HEIGHT=27 BORDER=0 ALT=""></td>
		<TD ROWSPAN=2><IMG SRC="${CPATH}/images/msg/pptop3_7.jpg" WIDTH=71 HEIGHT=32 ALT=""></TD>
	</tr>
	<TR>
		<TD COLSPAN=6>
			<IMG SRC="${CPATH}/images/msg/pptop3_8.jpg" WIDTH=323 HEIGHT=5 ALT=""></TD>
	</TR>
</table>
<!-- 상단 메뉴부분 끝 -->
<!-- 메인 컨텐츠 들어가는 부분 -->
<TABLE WIDTH=460 BORDER=0 CELLPADDING=0 CELLSPACING=0>
	<TR>
		<TD valign="top" background="${CPATH}/images/msg/papertopbg.jpg" width="460" height="10">

		</TD>
	</TR>
    <TR>
		<TD width="460" height="10" valign="top" background="${CPATH}/images/msg/papermiddlebg.jpg" align="center">
          <TABLE WIDTH=430 height="150" BORDER=0 CELLPADDING=0 CELLSPACING=0>
	        <TR>
              <td valign="top"><!--list start-->
			
			<table width="434" height="387" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="26">
					<table width="434" height="26" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="7" align="center" valign="middle"></td>
							<td width="226" height="7" align="center" valign="middle"></td>
							<td  rowspan="2" align="right" valign="middle">
							<!--<TD class=bbs_view_td><INPUT id=category size=10
								name=c_group maxbyte="10" hname="카테고리" align="absmiddle"></td>
							
							<td width="56" rowspan="2" align="center" valign="middle"><img
								src="${CPATH}/images/button/group_regist.gif" width="56"
								height="21"></td>
							-->
							<select name="selname">
								<option><fmt:message bundle = "${m}" key = "msg.search.sel"/></option>
								<option value="1" selected><fmt:message bundle = "${m}" key = "msg.search.nm"/></option>
								<!--<option value="2"><fmt:message bundle = "${m}" key = "msg.search.coll"/></option>-->
							</select></td>
							<td rowspan="2" align="center" valign="middle"><input name="nm" type="text"
								style="border-style :solid;border-width=1;border-color:#898989"
								size="10" value="${nm}"></td>
							<td  rowspan="2" align="center" valign="middle"><a href="javascript:InquiryForm()"><img
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
							<td height="2" colspan="6"></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td height="1" colspan="6"></td>
						</tr>

						<tr bgcolor="EBEBEB" height="10">
							<td width="52" height="10" align="center"><strong
								class="txt02"><fmt:message bundle = "${m}" key = "msg.no"/></strong></td>
							<td width="2" height="10"><img
								src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
							<!--<td width="142" height="10" align="center"><strong
								class="txt02">소속</strong></td>
							<td width="2" height="10"><img
								src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>-->
							<td width="345" height="10" align="center" class="txt02"><strong><fmt:message bundle = "${m}" key = "msg.search.nm"/></strong></td>
							<td width="2" height="10"><img
								src="${CPATH}/images/msg/title_line.gif" width="2" height="26"></td>
							<td width="33" height="10" align="center"><input
								type="checkbox" name="checkboxAll" value="checkbox" class= "checkbox"
								onclick="allChekboxToggle(this, 'frm','c_usn_ck');"> <!--<input type='checkbox' name='checkAll' style="border:none;" onclick='javascript:commAllCheck(frm, "msgid", this,  true);' />-->
							</td>
							<td width="15" height="10" align="center"></td>
						</tr>
						<tr bgcolor="D7D7D7">
							<td height="1" colspan="6" align="center"></td>
						</tr>	
					<c:if test="${!(empty searchlist)}">							
						<c:forEach var="vsearchlist" items="${searchlist}" varStatus="status">
							<tr bgcolor="#FFFFFF">
								<td height="10" align="center">${status.count}</td>
								<td height="10">&nbsp;</td>
								<!--  <td height="10" align="center">그룹</td>
									<td height="10">&nbsp;</td>-->
								<td height="10" align="center">${vsearchlist.nm}(${vsearchlist.userid})</td>
								<td height="10">&nbsp;</td>
								<td height="10" align="center"><input type="checkbox" class= "checkbox"
									name="c_usn_ck" value="${vsearchlist.usn}"></td>
								<td width="15" height="10" align="center"></td>
							</tr>
							<tr bgcolor="D7D7D7">
								<td height="1" colspan="6" align="center"></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty searchlist}">
							<tr bgcolor="#FFFFFF">
								<td height="10" align="center" colspan="6"><fmt:message bundle = "${m}" key = "msg.emptysch"/></td>
							</tr>
							<tr bgcolor="D7D7D7">
								<td height="1" colspan="6" align="center"></td>
							</tr>
					</c:if>
						<tr>
					</table>
					</div>
					</td>
				</tr>
			</table></td>
			</tr>
		</table></td>
	</TR>
	<TR>
		<TD valign="top" background="${CPATH}/images/msg/paperbtbg.jpg" width="460" height="10">
		</TD>
	</TR>
</TABLE>
<!-- 하단 -->
<TABLE WIDTH=460 BORDER=0 CELLPADDING=0 CELLSPACING=0>
	<TR>
		<TD height="10" width="460" valign="top" background="${CPATH}/images/msg/paperbt2.jpg">

		</TD>
	</TR>
    <TR>
		<TD width="430" height="20" valign="top" background="${CPATH}/images/msg/paperbt1.jpg" align="center">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><SELECT id="sel_cat" name="tselectgrp"
							onchange="javascript:doSelcat(this);">
							<OPTION value="" selected><fmt:message bundle = "${m}" key = "msg.grpwte"/></OPTION>
							<c:if test="${count > 0}">
								<c:forEach var="grplist" items="${msggrplist}">
									<OPTION value="${grplist.c_group}">${grplist.c_group}</OPTION>
								</c:forEach>
							</c:if>
						</SELECT></td>
						<td><INPUT id="category" size=10 name="selectgrp" maxbyte="10"
							hname="카테고리" align="absmiddle"></td>
						<td><a href="javascript:addrInsert();"><img
							src="${CPATH}/images/button/friend_regist.gif" width="56" height="21"
							border="0"></a></td>
						<td width="4"></td>
						<td><a href="${CPATH}/msgmst/msgmnglist.do"><img src="${CPATH}/images/button/back.gif"  height="21" border="0"></a></td>
						<td width="4"></td>
						<td><a href="javascript:top.window.close();"><img src="${CPATH}/images/button/close.gif" width="56" height="21" border="0"></a></td>
						<td width="11"></td>
					</tr>
				</table><!-- 하단 버튼부분 시작 --></td>
	</TR>
	<TR>
		<TD width="460" height="10" valign="top" background="${CPATH}/images/msg/paperbt3.jpg" height="30">
	    </TD>
	</TR>
</TABLE>
</form>
