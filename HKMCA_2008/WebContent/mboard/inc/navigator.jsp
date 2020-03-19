<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:20px;">
    <tr>
        <td>
            <c:if test="${navigator != null}">
            <c:url var="listUrl" value="${controlaction}">
                <c:param name="LISTOP" value="${MBOARD.listOpStr}" />
            </c:url>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="navigator">
				<tr>
				    <td>
					<c:if test="${navigator.startPage > navigator.screenSize}">
					<c:url var="tUrl" value="${listUrl}">
			               <c:param name="v_page" value="${navigator.startPage - navigator.screenSize}"/>
					</c:url>
					<a href="<c:out value='${tUrl}'/>"><img src="<c:out value='${MBOARD.PATH}'/>/images/pre.gif" valign="absmiddle" border="0"></a>&nbsp;
					</c:if>
			               <c:forEach var="i" begin="${navigator.startPage}" end="${navigator.endPage}">
    						<c:choose>
							<c:when test="${i == navigator.currentPage}">
								<strong><c:out value="[${i}]"/></strong>
							</c:when>
							<c:otherwise>
								<c:url var="tUrl" value="${listUrl}">
									<c:param name="v_page" value="${i}"/>
								</c:url>
								<a href='<c:out value="${tUrl}"/>'><c:out value="[${i}]"/></a>
							</c:otherwise>
	   					   </c:choose>
			               </c:forEach>
					<c:if test="${navigator.endPage < navigator.pageCount}">
						<c:url var="tUrl" value="${listUrl}">
							<c:param name="v_page" value="${navigator.startPage + navigator.screenSize}"/>
						</c:url>
					   &nbsp; <a href="<c:out value='${tUrl}'/>"><img src="<c:out value='${MBOARD.PATH}'/>/images/next.gif" valign="absmiddle" border="0"></a>
					</c:if>
					</td>
				</tr>
			</table>
			</c:if>
        </td>
    </tr>
</table>
