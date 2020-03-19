<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="JavaScript" src='<c:out value="${MBOARD.PATH}"/>/js/list.js'	type="text/javascript"></script>
<script language="JavaScript">
function mboard_popup(bid, c_index, seq) {
	var urlname = '<c:url value="/board/viewimage.do"/>?bid=' + bid + 
				'&c_index=' + c_index +
				'&seq='+seq ;
	browsing_window = window.open(urlname, "imagewin",
			"top=10px,left=10px,status=yes,resizable=yes,location=false, menubar=no,status=no,scrollbars=no,width=10,height=10");
	browsing_window.focus();
}
</script>
<table width="95%" cellspacing="0" cellpadding="0" border="0">
	<!--  검색부  -->
        <mh:import url="${MBOARD.PATH}/inc/search.jsp" />	
    <!--  검색부  -->
	<!-- List -->
	<tr>
		<td>
            <div class="listContainer">
            <table width=100% border="0" cellspacing="0" cellpadding="2" class="list">
                <col width="50" />
                <col width="*" />
                <col width="120" />
                <col width="80" />
                <col width="100" />
                <thead>
                <tr>
                    <th>&nbsp;#&nbsp;</th>
                    <th><mfmt:message bundle="board" key="label.title" /></th>
                    <th><mfmt:message bundle="board" key="label.writer" /></th>
                    <th><mfmt:message bundle="board" key="label.view" /></th>
                    <th><mfmt:message bundle="board" key="label.regdt" /></th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                <c:when test="${!empty(list) || !empty(noticeList) }">
                <!-- Notice  -->
                <c:forEach items="${noticeList}" var="data" varStatus="iter">
                <c:url var="url" value="/board/view.do">
                    <c:param name="c_index" value="${data.c_index}" />
                    <c:param name="LISTOP" value="${MBOARD.listOpStr}" />
                </c:url>
                <tr style="background-color:#f5f5f5;" onMouseover="this.style.backgroundColor='#EFF8F9'" onMouseout="this.style.backgroundColor='#f5f5f5'">
                    <td class="list BL" align="center" nowrap><img src='<c:url value="/mboard/images/notice.gif"/>' alt="" border="0"></td>
                    <td class="list" align="left">
                        <mh:out value=" " repeate="${(data.c_level-1)*2}"/>
                        <c:if test="${data.c_level > 1}">
                        <img src="<c:out value="${MBOARD.CPATH}"/>/images/re.gif" border="0">&nbsp;
                                 </c:if>
                                 <c:if test="${! empty data.c_category}">
                        <a href="javascript:ShowOnly('category','${data.c_category}')"><mh:capsule value="${data.c_category}" left="[" right="]"/></a>
                                 </c:if>
                                 <a href='<c:out value="${url}"/>'><mh:out value="${data.c_subject}" bytes="${60-data.c_level}"/></a>
                                 <c:if test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
                                 (${data.c_ccnt})
                                 </c:if>
                                 <mh:betweenHour var="t" value="${data.c_date}"/>
                                 <c:if test="${t<24}">
                                 &nbsp;<img src='<c:out value="${MBOARD.CPATH}"/>/images/icon/notice_new.gif' alt="" border="0" align="absmiddle">
                        </c:if>
                             </td>
                    <td class="list" align="center"><mh:out value="${data.wname}" td="true" /></td>
                    <td class="list" align="center"><mh:out value="${data.c_visit}" td="true" /></td>
                    <td class="list BR" align="center"><mh:out value="${data.c_date}" format="fulldate" /></td>
                </tr>
                </c:forEach>
				<c:forEach items="${list}" var="data" varStatus="iter">
				    <c:url var="url" value="/mboard/view.do">
				        <c:param name="c_index" value="${data.c_index}" />
				        <c:param name="LISTOP" value="${MBOARD.listOpStr}" />
				    </c:url>
				    <tr style="background-color:#ffffff;">
				        <td align="center"><mh:out value="${(navigator.totalCount - (navigator.pageSize*(navigator.currentPage-1))) - (iter.count-1)}" td="true"/></td>
				        <td align="left">
				            <mh:out value="&nbsp;" repeate="${(data.c_level-1)*2}"/>
				            <c:if test="${data.c_level > 1}">
				                <img src='<c:out value="${MBOARD.CPATH}"/>/images/re.gif' border="0">&nbsp;
				            </c:if>
				            <c:if test="${!empty data.c_category}">
				                <a href="javascript:ShowOnly('category','<c:out value="${data.c_category}"/>')"><mh:capsule value="${data.c_category}" left="[" right="]"/></a>
				            </c:if>
				            <a href='<c:out value="${url}"/>'><mh:out value="${data.c_subject}" bytes="${60-data.c_level}"/></a>
				            <c:if test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
				                (${data.c_ccnt})
				            </c:if>
				            <mh:betweenHour  var="t" value="${data.c_date}"></mh:betweenHour>
				            <c:if test="${t<24}">
				            &nbsp;<img src='<c:out value="${MBOARD.CPATH}"/>/images/icon/notice_new.gif' alt="" width="25" height="13" border="0" valign="absmiddle">
				            </c:if>
				        </td>
				        <td align="center"><mh:out value="${data.wname}" td="true"/></td>
				        <td align="center"><mh:out value="${data.c_visit}" td="true"/></td>
				        <td align="center"><mh:out value="${data.c_date}" format="fulldate" /></td>
				    </tr>
				</c:forEach>                
                </c:when>
                <c:otherwise>
                <tr>
                    <td class="bbs_td" align=center colspan="15" width="100%">
                        <br><mfmt:message bundle="board" key="title.board.searchlist" /><br>&nbsp;
                    </td>
                </tr>
                </c:otherwise>
                </c:choose>
                </tbody>
            </table>
			</div>
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
	            <tr>
	                <td align="center"><mh:import url="${MBOARD.PATH}/inc/navigator.jsp" /></td>
	            </tr>
                <tr>
                    <td align="right">
                        <mh:indexOf var="t" value="${MBOARD.BTN_AUTH}" needle="W" />
                        <c:if test="${t >= 0 }">
                        <mf:button onclick="MBoard_Write()" bundle="button" key="add" icon="icon_add" />
                        </c:if>
                    </td>
                </tr>
            </table>
		</td>
	</tr>
</table>
<mh:import url="${MBOARD.PATH}/inc/frmBBS.jsp" />