<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<%@page import="maf.base.BaseHttpSession"%>
<%@page import="maf.web.session.MySession"%>
<%
    BaseHttpSession sessionBean =  MySession.getSessionBean( request, response );
%>   
<!-- 코멘트 입력하기 시작 -->
<TABLE width="576"  cellSpacing=0 cellPadding=2 border=0 class="bbs_table">
	<TR >
		<Th class="bbs_view_top_th" align=middle width=61><mfmt:message key="ecampus.std.username"/></Th>
		<TD class="bbs_view_top_td" colspan="3">
			<INPUT <c:when test="${!empty sessionScope.msession}">readonly</c:when>  maxLength=10 size=10 name=WNAME required hname="이름" maxbyte="10" value="<c:out var="wName"/>"> &nbsp;&nbsp;&nbsp;
			<c:when test="${empty sessionScope.msession}">
			<span class="bbs_th_text"><mfmt:message key="title.pass"/></span> &nbsp; 
			<INPUT class=required type=password maxLength=8 size=10 value="" name=password required hname="<mfmt:message key="title.pass"/>" maxbyte="8"> 
            </c:when>
			<INPUT type=radio CHECKED value=0 name=C_OPNION style="border:none;">:<img src="${CPATH}/mboard/images/icon/0.gif" alt="" border="0">
			<INPUT type=radio value=1 name=C_OPNION style="border:none;">:<img src="${CPATH}/mboard/images/icon/1.gif" alt=""  border="0">
			<INPUT type=radio value=2 name=C_OPNION style="border:none;">:<img src="${CPATH}/mboard/images/icon/2.gif" alt=""  border="0"></td>
	</TR>
	<TR class="bbs_view_td">
		<Th class="bbs_view_th" align=middle><mfmt:message key="title.desc"/></Th>
		<TD class="bbs_view_td" width=478><TEXTAREA class=required onkeyup="javascript:chkLength(this);"  name="C_MEMO" cols=60 rows=3 required hname="내용" maxbyte="250"></TEXTAREA></TD>
		<TD class="bbs_view_td" width=39><!-- 몇 글자 보이기 -->
			<TABLE cellSpacing=0 cellPadding=0 width=24 border=0>
			<TBODY>
				<TR>
					<TD align="center" class="td_cmt"><STRONG><DIV id="strcnt">0</DIV></STRONG></TD>
				</TR>
				<TR>
					<TD bgColor=#5a5a5a height=1></TD>
				</TR>
				<TR>
					<TD align="center" class="td_cmt"><STRONG>250</STRONG></TD>
				</TR>
			</TBODY>
			</TABLE><!-- 몇 글자 보이기 --></TD>
		<td class="bbs_view_td"><A href="javascript:cmtWrite();"><img src="images/button/save.gif" alt="" border="0" align="absmiddle"></a></td>
	</TR>
</TABLE>
</FORM>