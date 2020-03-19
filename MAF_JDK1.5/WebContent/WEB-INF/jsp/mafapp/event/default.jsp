<%@ page contentType="text/html; charset=euc-kr"%>
<table width="95%" border='0' cellspacing='0' cellpadding='0'>
	<tr>
        <td>
			<table width="100%" border='0' cellspacing='0' cellpadding='0'>
			<form action="${control_action}" method="post" name="myform" id="myform" >
			    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
				<input type="hidden" name="miv_page" value="1">
				<input type="hidden" name="${mrt:mvcCmd()}" value="default">
				<input type="hidden" name="evt_id" value=""> 			
				<tr>
			        <td>
				        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				            <tr>
					            <th class="search">이벤트제목</th>
					            <td class="search"><input type="text" name="evt_title" value="${LISTOP.ht.evt_title}"></td>
				            </tr>
				            <tr>
					            <th class="search">이벤트내용</th>
					            <td class="search"><input type="text" name="evt_contents" value="${LISTOP.ht.evt_contents}"></td>
				            </tr>
				            <tr>
					            <td colspan="10"><a href="javascript:doSearch()"><mform:submit bundle="button" key="search"/></a></td>
				            </tr>
				        </table>
			        </td>
			    </tr>
			    <tr>
				    <td>
						<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
						    <tr>
								<th class="list BL">No.</th>
								<th class="list">이벤트제목<mform:THSort id='evt_title'/></th>
								<th class="list">사용여부<mform:THSort id='evt_yn'/></th>
								<th class="list BR">수정일<mform:THSort id='evt_updt'/></th>
						    </tr>
						    <c:forEach var="item" items="${navigator.list}" varStatus="status">
							<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
								<td class="list">${status.count+(navigator.currentPage-1)*navigator.pageSize}</td>
								<td class="list"><a href="javaScript:doView('${item.evt_id}')">${mhtml:null2nbsp(item.evt_title)}</a></td>
								<td class="list center">${item.evt_yn == 'Y' ? '진행' : '삭제'}</td>
								<td class="list center">${mfmt:shortDate(item.evt_updt)}</td>
							</tr>
						    </c:forEach>
					    </table>
				    </td>
			    </tr>
			</form>    
		    </table>
			<table width="100%" border='0' cellspacing='0' cellpadding='0'>
				<tr>
				    <td align="center"><br><jsp:include page="/layout/lib/navigator.jsp" flush="true"/></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(id){
	    var frm = getObject("myform");
			if(frm) {
    		    frm.${mrt:mvcCmd()}.value = "view";
    		    frm.evt_id.value = id;
    		    frm.submit();
			}
	}
    function doSearch() {
	    var frm = getObject("myform");
			if(frm) {
    		    frm.${mrt:mvcCmd()}.value = "default";
                frm.miv_page.value = 1;
    		    frm.submit();
			}     
    }
//-->
</SCRIPT>
