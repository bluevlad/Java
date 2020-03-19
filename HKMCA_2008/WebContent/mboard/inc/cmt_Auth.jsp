<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<table width="100%" border="0" cellspacing="0" cellpadding="2" class="bbs_table">
	<TR>
		<Th class="bbs_view_top_th" valign="middle" nowrap width="80"><mfmt:message bundle="board" key="label.writer"/></Th>
		<TD class="bbs_view_top_td" colspan="3"><c:out value="${sessionScope.msession.nm}"/>&nbsp;&nbsp;&nbsp;
			<input type="radio" name="c_opnion" value="0" checked style="border:none;">:<img src='<c:out value="${MBOARD.PATH}"/>/images/icon/0.gif' border="0">
			<input type="radio" name="c_opnion" value="1" style="border:none;">:<img src="<c:out value="${MBOARD.PATH}"/>/images/icon/1.gif" alt="" border="0">
			<input type="radio" name="c_opnion" value="2" style="border:none;">:<img src="<c:out value="${MBOARD.PATH}"/>/images/icon/2.gif" alt="" border="0"></td>
	</TR>
	<TR class="bbs_view_td">
		<Th class="bbs_view_th" valign="middle" nowrap><mfmt:message bundle="board" key="title.board.queswrite"/></Th>
		<TD class="bbs_view_td" width=458>
		<TEXTAREA class=required onkeyup="javascript:chkLength(this);" rows="3" name="c_memo" cols=100 required hname="Contents" maxbyte="1000"></TEXTAREA>
		</TD>
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
					<TD align="center" class="td_cmt"><STRONG>1000</STRONG></TD>
				</TR>
			</TBODY>
			</TABLE><!-- 몇 글자 보이기 -->
        </TD>
		<td class="bbs_view_td"><mf:button onclick="cmtWrite()" bundle="button" key="save" /></td>
	</TR>
</TABLE>