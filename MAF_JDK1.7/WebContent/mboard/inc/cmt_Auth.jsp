<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>

<table width="100%" border="0" cellspacing="0" cellpadding="2" class="bbs_table">
	<TR >
		<Th class="bbs_view_top_th" align=middle nowrap width="80"><mfmt:message bundle="board" key="label.writer"/></Th>
		<TD class="bbs_view_top_td" colspan="3">${sessionScope.msession.nm}&nbsp;&nbsp;&nbsp;
			<input type="radio" name="c_opnion" value="0" checked style="border:none;">:<img src="${MBOARD.CPATH}/images/icon/0.gif" alt="" border="0">
			<input type="radio" name="c_opnion" value="1" style="border:none;">:<img src="${MBOARD.CPATH}/images/icon/1.gif" alt="" border="0">
			<input type="radio" name="c_opnion" value="2" style="border:none;">:<img src="${MBOARD.CPATH}/images/icon/2.gif" alt="" border="0"></td>
	</TR>
	<TR class="bbs_view_td">
		<Th class="bbs_view_th" align="middle" nowrap><mfmt:message bundle="board" key="title.board.queswrite"/></Th>
		<TD class="bbs_view_td" width=458><TEXTAREA class=required onkeyup="javascript:chkLength(this);"  
			rows="3" name="c_memo" cols=60 required hname="Contents" maxbyte="1000"></TEXTAREA></TD>
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
			</TABLE><!-- 몇 글자 보이기 --></TD>
		<td class="bbs_view_td"><A href="javascript:cmtWrite();"><img src="${MBOARD.CPATH}/images/button/save.gif" alt="" border="0" align="absmiddle"></a></td>
	</TR>
</TABLE>
