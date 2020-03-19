<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript"   src="${CPATH}/js/lib.validate.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
    <input type="hidden" name="miv_page" value="1">
    <input type="hidden" name="${mrt:mvcCmd()}" value="selist">
    <input type="hidden" name="sjtcode" value="${mhtml:null2nbsp(item.sjtcode)}">
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th class="search"><mfmt:message bundle="table.Wlblecchp" key="wlb_lec_chp.itm_title"/></th>
                    <td class="search">${mhtml:null2nbsp(citem.itm_title)}</td>
                 </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td bgcolor="#FFFFFF" align="center" height=20>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
            <thead>
            <tr>
                <th width=80% class="list BL"><mfmt:message bundle="common" key="stat.stat"/></th>
                <th width=20% class="list BR"><mfmt:message bundle="table.Wlblecchp" key="wlb_lec_chp.page_prg"/></th>
            </tr>
            </thead>
            <c:set var="wth" value="0"/>
            <c:choose>
              <c:when test="${pitem.page_cnt > pitem.totpage}">
                <c:set var="wth" value="100"/>
              </c:when>
              <c:otherwise>
                <c:set var="wth" value="${(empty pitem.page_prog2)?'0':pitem.page_prog2}"/>
              </c:otherwise>
            </c:choose>
            <c:set var="pg" value="0"/>
            <c:choose>
              <c:when test="${pitem.page_cnt > pitem.totpage}">
                <c:set var="pg" value="${(empty pitem.totpage)?'0':pitem.totpage}${'/'}${(empty pitem.totpage)?'0':pitem.totpage}"/>
              </c:when>
              <c:otherwise>
                <c:set var="pg" value="${(empty pitem.page_cnt)?'0':pitem.page_cnt}${'/'}${(empty pitem.totpage)?'0':pitem.totpage}"/>
              </c:otherwise>
            </c:choose>
            <tbody>
            <tr >
               <td width=80% class="list" align=left><img height="100%" src="${CPATH}/maf_images/graph/graph1.gif" width="${wth}%">
               <c:if test="${wth <= 80}"><span style="vertical-align:50%">${wth} %</span></c:if>
               </td>
               <td width=20% class="list" align=center><c:if test="${wth > 80}">${wth} %<br></c:if>(${pg} Pages)</td>
            </tr>
            </tbody>
            </table>
        </td>
    </tr>
</form>    
    <tr>
        <td align="center">
        <a href="javascript:self.close();"><mfmt:button bundle="button" key="close"/></a>
        </td>
    </tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
//-->
</SCRIPT>