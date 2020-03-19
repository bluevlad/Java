<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
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

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="navigator" style="margin-bottom:5px;">
    <col width="4"/>
    <col width="*"/>
    <col width="4"/>
<tr>
    <td class="navigator tl"></td>
    <td rowspan="2">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="navigator">
	<c:if test="${navigator.totalCount > 0 }">
	<tr>
		<td align="center"><table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<c:if test="${navigator.startPage > navigator.screenSize}">
			<td><a href='javascript:mnavi_goPage(<c:out value="${navigator.startPage - navigator.screenSize}"/>)'><img
					src='<c:url value="/maf_images/navigator/pre.gif"/>' valign='absmiddle'  border='0'></a>
			&nbsp;</td>
			</c:if>
			<td class="navigator">
				<c:forEach var="i" begin="${navigator.startPage}" end="${navigator.endPage}">
					<c:choose>
						<c:when test="${i == navigator.currentPage}">
							<span class="navigator" id="curPage"><c:out value="${i}"/></span>
						</c:when>
						<c:otherwise>
							<a href='javascript:mnavi_goPage(<c:out value="${i}"/>)'><span class="navigator" id="goPage"><c:out value="${i}"/></span></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</td>
			<c:if test="${navigator.endPage < navigator.pageCount}">
			<td>&nbsp;
				<a href='javascript:mnavi_goPage(<c:out value="${navigator.startPage + navigator.screenSize}"/>)'><img
					src='<c:url value="/maf_images/navigator/next.gif"/>' valign='absmiddle' border='0'></a>
			</td>
			</c:if>

		</tr>
		</table></td>
	</tr>
	
	<tr>
		<td align="center"><mfmt:message bundle="message" key="navigator.totalcount" param="${navigator.totalCount}"/> <c:out value="(${navigator.currentPage} of  ${navigator.pageCount} page)"/> 
            <select id="miv_pagesize" name="miv_pagesize" style="width: 40px; text-align: center;" onChange="mnavi_setListPageSize(this)">
                <c:forTokens var="val" items="5,10,15,20,30,40,50" delims=",">
                <option value="<c:out value="${val}"/>" <c:if test="${navigator.pageSize==val}">SELECTED</c:if> ><c:out value="${val}"/></option>
                </c:forTokens>
            </select></td>
	</tr>
	</c:if>
	<c:if test="${navigator.totalCount == 0}">
		<tr>
			<td height="50" align="center" valign="middle" class="td"><mfmt:message bundle="message" key="navigator.noDataFound" /> </td>
		</tr>
	</c:if>
</table></td>
    <td class="navigator tr"></td>
</tr>
<tr>
    <td class="navigator bl"></td>
    <td class="navigator br"></td>
</tr>
</table>
</mf:form>
</c:if>
