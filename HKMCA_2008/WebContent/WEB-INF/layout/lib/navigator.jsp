<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mh" uri="http://maf.jstl.com/jsp/tld/maf-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.jstl.com/jsp/tld/maf-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.jstl.com/jsp/tld/maf-fmt.tld"%>

<c:if test="${navigator != null}">
<script>
    function mnavi_setListPageSize(o) {
        frm = o.form;
        if (frm) {
            frm.miv_page.value=1;
            frm.submit();

        }
    }
    function mnavi_goPage( npage) {
        frm = document.getElementById("frmNavi");
        if (frm) {
            frm.miv_page.value=npage;
            frm.submit();

        }    
    }
    function mnavi_Sort(sort, key) {
	    var frm = getObject("frmNavi");
			if(frm) {
                if (frm.miv_page) {
                    frm.miv_page.value=1;
                }
                if (frm.miv_order) {
        		    frm.miv_order.value = sort + ":" + key;
        		    frm.submit();
                }
			}
    }
</script>

<mf:form action="${control_action}" method='post' name='frmNavi' id='frmNavi'>
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
    <mf:input type="hidden" name="cmd" value="${param.cmd}"/>
    <mf:input type="hidden" name="miv_page" value="${navigator.currentPage}"/>
    <mf:input type="hidden" name="miv_order" value="${navigator.order.param}"/>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	    <td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="navigator">
				<c:if test="${navigator.totalCount > 0 }">
				<tr>
					<th>
                                <c:if test="${navigator.startPage > navigator.screenSize}">
                                    <a href='javascript:mnavi_goPage(<c:out value="${navigator.startPage - navigator.screenSize}"/>)'><img
								    src='<c:url value="/maf_images/navigator/pre.gif"/>'></a>&nbsp;&nbsp;&nbsp;
                                </c:if>
                                    <c:forEach var="i" begin="${navigator.startPage}" end="${navigator.endPage}">
									    <c:choose>
										<c:when test="${i == navigator.currentPage}">
											&nbsp;&nbsp;<c:out value="${i}"/>&nbsp;
										</c:when>
										<c:otherwise>
											&nbsp;&nbsp;<a href='javascript:mnavi_goPage(<c:out value="${i}"/>)'><c:out value="${i}"/></a>&nbsp;
										</c:otherwise>
									    </c:choose>
                                    </c:forEach>
                                <c:if test="${navigator.endPage < navigator.pageCount}">
                                    &nbsp;
                                    <a href='javascript:mnavi_goPage(<c:out value="${navigator.startPage + navigator.screenSize}"/>)'><img
                                    src='<c:url value="/maf_images/navigator/next.gif"/>'></a>
                                </c:if>
                    </th>
                </tr>
				<tr>
					<td align="center">
                        <mfmt:message bundle="common" key="navigator.totalcount" param="${navigator.totalCount}"/><c:out value="(${navigator.currentPage} of  ${navigator.pageCount} page)"/> 
			            <select id="miv_pagesize" name="miv_pagesize" style="width: 40px; text-align: center;" onChange="mnavi_setListPageSize(this)">
			                <c:forTokens var="val" items="5,10,15,20,30,40,50" delims=",">
			                <option value='<c:out value="${val}"/>' <c:if test="${navigator.pageSize==val}">SELECTED</c:if>><c:out value="${val}"/></option>
			                </c:forTokens>
			            </select>
			        </td>
				</tr>
				</c:if>
				<c:if test="${navigator.totalCount == 0}">
                <tr>
					<th height="50" align="center" valign="middle"><mfmt:message bundle="common.message" key="navigator.noDataFound" /></th>
				</tr>
				</c:if>
			</table>
        </td>
	</tr>
</table>
</mf:form>
</c:if>