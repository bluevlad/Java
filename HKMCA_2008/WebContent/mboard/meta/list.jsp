<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<c:set var="m" value="board" />

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doWrite(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "add";
		    frm.submit();
		}
	}
	
	function doView(code){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "edit";
			frm.bid.value = code; 
			frm.submit();
		}
	}
    function doSearch(frm) {
		if(frm) {
   			frm.cmd.value = "list";
			frm.miv_page.value = 1;
			return true;
		}     
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="list" />
<mf:input type="hidden" name="bid" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="20%"/>
				<col width="30%"/>
                <col width="20%"/>
                <col width="30%"/>
                <tr>
                    <th><mfmt:message bundle="${m}" key="title.board.titlename" /></th>
                    <td><mf:input name="s_subject" type="text" cssStyle="width:98%" value="${LISTOP.ht.s_subject}" /></td>
                    <th><mfmt:message bundle="${m}" key="title.board.boardutype" /></th>
                    <td>
                        <mf:select name="s_fl_board_type" codeGroup="BRD_TYPE" curValue="${LISTOP.ht.s_fl_board_type}">
                        <option value="">--</option>
                        </mf:select>
                    </td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" icon="icon_search" /></td>
                </tr>
            </table>
        </div>
        </td>
    </tr>
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="100" />
                <col width="*" />
                <col width="100"/>
                <col width="50"/>
                <col width="100"/>
                <col width="100"/>
                <col width="100"/>
                <thead>
                <tr>
                    <th>ID</th>
                    <th><mfmt:message bundle="${m}" key="title.board.titlename" /></th>
                    <th><mfmt:message bundle="${m}" key="title.board.boardutype" /></th>
                    <th><mfmt:message bundle="${m}" key="board.use" /></th>
                    <th><mfmt:message bundle="${m}" key="title.board.gunhan" /></th>  
                    <th><mfmt:message bundle="${m}" key="title.board.countlist" /><br>(<mfmt:message bundle="${m}" key="title.board.deletelist" />|<mfmt:message bundle="${m}" key="title.board.heuso" />)</th>
                    <th><mfmt:message bundle="${m}" key="title.board.allfilecoutn" /><br><mfmt:message bundle="${m}" key="title.board.size" /></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
                    <td align="center"><a href='javascript:doView("<c:out value="${item.bid}"/>");'><mh:out value="${item.bid}"/></a></td>
                    <td align="center"><a href='javascript:doView("<c:out value="${item.bid}"/>");'><mh:out value="${item.subject}" td="true"/></a></td>
                    <td align="center"><mh:out value="${item.fl_board_type}" td="true"/></td>
                    <td align="center"><mh:out value="${item.is_use}" td="true"/></td>
                    <td align="center"><mh:out value="[${item.grant_list} | ${item.grant_write} |  ${item.fl_comment}]" td="true"/></td>
                    <td align="center"><mh:out value="${item.cnt_t} / ${item.cnt_w}" td="true"/></td>
                    <td align="center"><mh:out value="${item.total_attach_size}" td="true"/></td>                   
                </tr>
                </c:forEach>
                </tbody>
	        </table>
			<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">     
			    <tr>
			        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/></td>
			    </tr>
			</table>
        	</div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" />