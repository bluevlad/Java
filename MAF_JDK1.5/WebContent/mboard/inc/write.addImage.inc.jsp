<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<c:set var="m" value="board" />
<tr>
	<td colspan="2" class="bbs_view_td"><mfmt:message bundle="${m}" key="board.desc" /></td>
</tr>
<tr valign="top">
	<td class="bbs_view_th"><mfmt:message bundle="${m}" key="title.board.picadd" /></td>
	<td class="bbs_view_td" style="padding:5 10 0 0;">
		<table border="0" cellpadding="2" cellspacing="0" >
		<tr valign="top" class="td">
			<td width="61"><a href="javascript:;" onClick="createFileObj()"><mfmt:message bundle="${m}" key="title.board.countadd" /></td>
			<td id="filesDiv"><div id="filelist0" style="padding-bottom:4px;"><input type="file" name="file0" id="file0" size="32" 
				style="width:277px;" class="box" 
				onchange="setImg(0)"> <a href="javascript:removeFileObj(0)"><mfmt:message bundle="${m}" key="title.board.boarddel" /></a></div></td>
		</tr>
		</table>
    </td>
</tr>
<tr valign="top">
	<th ><mfmt:message bundle="${m}" key="title.board.preveiw" /></th>
	<td style="padding:5 10 10 0;">
		<div id="pictureArea" style="width:460px; height:68px; overflow:auto; border:1px solid #958B82; background-color:#FFFFFF;"></div>
		<table border="0" cellpadding="2" cellspacing="0" width="365">
			<tr style="padding-top:5px;" class="td">
				<td width="87" nowrap><a href="javascript:calcFileSize()">[Check Size]</a> &nbsp;<mfmt:message bundle="${m}" key="title.board.now" /> </td>
				<td width="48" nowrap><div id="sizeCheck" align="right">0</div></td>
				<td nowrap><mfmt:message bundle="${m}" key="title.board.picexm" /></td>
				<td width="100%"></td>
			</tr>
		</table>
		<script language="JavaScript1.2" src='<c:out value="${MBOARD.CPATH}"/>/js/writeList.js'></script>
	</td>
</tr>