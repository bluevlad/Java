<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>' ></script>
<c:choose>
	<c:when test="${param.cmd == 'edit'}">
		<c:set var="acttype" value="update"/>
	</c:when>
	<c:otherwise>
		<c:set var="acttype" value="insert"/>
	</c:otherwise>
</c:choose>
<style>
    .mth {
        padding-right:5px;
    }
    .mtd {
        padding-right:5px;
    }
</style>
<mf:form action='${control_action}' method="post" name="frmEdit" id="frmEdit">
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="view">
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
  <tr >
		<th class=" mth"  align='right'>PGID</th>
		<td class=" mtd"  >
		   <mf:input type="text" name="pgid" value='${pgid}'  required="true" hname="PGID" option="alphanum" readonly='${item.pgid == "HOME"}'/>
		   <br>&nbsp : 현재 선택한 메뉴의 고유 ID(2자리씩 사용)
		</td>
		<th class="mth"  align='right'>사용여부</th>
		<td class="mtd" >
            <mf:input type="radio" cssStyle="border:none" name="isuse" value="T" curValue='${item.isuse}' /> : Yes
            <mf:input type="radio" cssStyle="border:none" name="isuse" value="F" curValue='${item.isuse}' /> : No
	    </td>		
	</tr>
	<tr >
		<th class="mth" align='right'>TITLE</th>
		<td colspan="3" class='mtd'  >
		   <mf:input type="text" name="title" size="40" value="${item.title}" required="true" hname="TITLE" />
		   &nbsp : 현재 선택한 메뉴의 이름
		</td>
	</tr>
	<tr >
		<th class="mth"  align='right'>message key</th>
		<td colspan="3" class='mtd'  >
		   <mf:input type="text" name="messagekey" size="40" value="${item.messagekey}"  hname="messagekey" />
		   &nbsp : 다국어 지원 메시지 key
		</td>
	</tr>
	<tr >
		<th class="mth" align='right'>PAGE</th>
		<td colspan="3" class="mtd"   >
		   <mf:input type="text" name="page" size="50" value="${item.page}" />  :  (default.jsp : 공백)
		</td>
	</tr>
	<tr >
		<th class="mth" align='right'>PNODEID</th>
		<td class="mtd"   >
		   <mf:input type="text" name="pnodeid" value="${item.pnodeid}" readonly='${item.pgid == "HOME"}' />
	       &nbsp : 부모 PGID
		</td>
		<th class="view" style='padding-right:5px;' align='right'>SEQ</th>
		<td class="view" style='padding-left:5px;' align='left'  >
		   <mf:input type="text" name="seq" value="${item.seq}" size="5"  readonly='${item.pgid == "HOME"}' />
	       &nbsp : 정렬 순서
		</td>
	</tr>

	<tr >
	    <th class="mth" align='right'>ISLINK</th>
	    <td class="mtd" >
            <mf:input type="radio" name="islink" value="T" curValue="${item.islink}" cssClass="frm_checkbox"/> : Yes
            <mf:input type="radio" name="islink" value="F" curValue="${item.islink}" cssClass="frm_checkbox" /> : No
		<th class="mth" align='right'>SiteMap 표시</th>
		<td class="mtd" >
            <mf:input type="radio" cssStyle="border:none" name="is_sitemap" value="T" curValue="${item.is_sitemap}" /> : Yes
            <mf:input type="radio" cssStyle="border:none" name="is_sitemap" value="F" curValue="${item.is_sitemap}" /> : No
	    </td>
	</tr>	
	<tr >
		<th class="view" style='padding-right:5px;' align='right'>TARGET</th>
		<td colspan="3" class="view" style='padding-left:5px;' align='left'  >
		   <mf:input type="text" name="target" value="${item.target}" readonly='${item.pgid == "HOME"}' required="true" hname="TARGET"/>
	       &nbsp : 현재 선택한 메뉴의 표시 창<br>
		   (_self : 현재창 , _blank : 새창, 특정이름 : 해당명의 창, window:popup)
		</td>
	</tr>
	<tr >
	    <th class="view" style='padding-right:5px;' align='right'>DIR</th>
	    <td colspan="3" class="view" style='padding-left:5px;' align='left'  >
	        <mf:input type="text" name="dir" size="40" value="${item.dir}" readonly='${item.pgid == "HOME"}' />
	        <br>&nbsp : 현재 선택한 메뉴의 파일이 존재하는 폴더명
	    </td>
	</tr>
	<tr >
	    <th class="mth"  align='right'>Layout</th>
	    <td colspan="3" class="mtd"  >
	        <mf:input type="text" name="layout" size="40" value="${item.layout}" /> &nbsp : 
	    </td>
	</tr>	
	<tr >
	    <th class="mth" align='right'>Top MENU</th>
	    <td class="mtd" >
            예 : <mf:input type="radio" name="is_tmenu" value="T" curValue="${item.is_tmenu}" />
            아니오:<mf:input type="radio" name="is_tmenu" value="F" curValue="${item.is_tmenu}" />		
	    </td>
	    <th class="mth"  align='right'>Left MENU</th>
	    <td class="mtd" >
            예 : <mf:input type="radio" name="is_lmenu" value="T" curValue="${item.is_lmenu}"  />
            아니오:<mf:input type="radio" name="is_lmenu" value="F" curValue="${item.is_lmenu}"  />		
	    </td>
	</tr>		
	


	<tr >
	    <th class="mth" align='right'>담당자</th>
	    <td class="mtd"  >
	        <mf:input type="text" name="contact" size="35" value="${item.contact}" />
	    </td>
	    <th class="mth" align='right'>담당자 e-mail</th>
	    <td class="mtd"  >
	        <mf:input type="text" name="contact_email" size="35" value="${item.contact_email}" />
	    </td>
	</tr>
	<tr >
	    <th class="mth" style='padding-right:5px;' align='right'>help_file</th>
	    <td class="mtd" style='padding-left:5px;' align='left' >
	        <mf:input type="text" name="help_file" size="35" value="${item.help_file}" /><br>&nbsp : /help/경로에 넣은 파일명 
	    </td>
	    <th class="mth" style='padding-right:5px;' align='right'>설명</th>
	    <td class="mtd" style='padding-left:5px;' align='left' >
	        <mf:input type="text" name="short_desc" size="35" value="${item.short_desc}" />
	        &nbsp : 페이지 설명 
	    </td>
	</tr>	
	<tr>
		<th class="mth" align='right'>서브릿여부</th>
		<td class="mtd" >
		   <mf:input type="radio" name="isservlet" value="T" curValue="${item.isservlet}"  /> : Yes
		   <mf:input type="radio" name="isservlet" value="F" curValue="${item.isservlet}"  /> : No
		</td>

		<th class="mth" align='right'>인증필요</th>
		<td class="mtd" >
			<mf:input type="radio" name="session_chk" value="T" curValue="${item.session_chk}"  /> : Yes
     		<mf:input type="radio" name="session_chk" value="F"  curValue="${item.session_chk}"  /> : No
    	</td>
	</tr>

	<mf:input type="hidden" name="cmd" value="${acttype}"/>
	<mf:input type="hidden" name="site" value="${site}"/>
	<mf:input type="hidden" name="current_pgid" value="${pgid}"/>
</table>
</mf:form>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr>
    <td align="center" class="view">
	<c:if test="${acttype == 'update'}">
        <img src='<c:url value="/xadmin/images/button/add.gif"/>' alt="" border="0" class="IMG_BTN" onClick="javascript:m_add();">
	</c:if>
        <img src='<c:url value="/xadmin/images/button/save.gif"/>'  alt="" border="0" class="IMG_BTN" onClick="javascript:m_update();">
	<c:if test="${acttype == 'update'}">

        <img src='<c:url value="/xadmin/images/button/delete.gif"/>' alt=""  border="0" class="IMG_BTN" onClick="javascript:m_delete();">
	</c:if>
    </td>
</tr>
</table>

<script>
var vfrm = document.frmEdit;
function m_delete(){
	if (confirm("삭제하시겠습니까?")) {

		vfrm.cmd.value="delete";
		vfrm.submit();
	}
}
function m_update(){
	if (validate(vfrm)) {      
		if (confirm("저장하시겠습니까?")) {

			vfrm.submit();
		}
	}	
}
function m_add(){
	<c:url var="t" value="${control_action}">
		<c:param name="cmd" value="add"/>
		<c:param name="site" value="${site}"/>
	</c:url>
    if (confirm("하위 메뉴를 작성하시겠습니까?")) {
        document.location.href = '<c:out value="${t}" escapeXml="false"/>&pnodeid=' + vfrm.pgid.value;
    }
}
<c:if test="${param.reload =='T'}">
	parent.frmTree.location.reload();
</c:if>
</script>
