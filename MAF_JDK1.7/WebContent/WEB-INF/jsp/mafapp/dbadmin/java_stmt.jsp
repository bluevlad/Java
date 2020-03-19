<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
<textarea cols="90" rows="20" name="bean" id="frmBean">
    CallableStatement   stmt    = null;
    stmt = oDb.getConnection().prepareCall(sql);
    try {
        int i = 1;
<c:forEach items="${stmt}" var="item">
        stmt.setString(i++, bean.get${mhtml:Capitailize(item)}());</c:forEach>
        stmt.execute();
    }finally{
        try {if (stmt != null) {stmt.close();}} catch (Throwable e) {} 
        stmt = null;
    }
</textarea>
<script language="JavaScript">
<!--
function SetSize() {
	var oBody = document.body;
	var divx = getObject("frmBean");
	divx.style.width=oBody.clientWidth-30 ;//- oBody;
	divx.style.height=oBody.clientHeight - (60);//- oBody 60;
}
SetSize();
//-->
</script>
