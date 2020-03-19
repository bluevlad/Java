<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%
final String sql = "select *  " +
	" from (select poll_id, title  " +
	"     from www_poll " +
	"     where start_dt <= to_char(sysdate, 'YYYY-MM-DD') " +
	"     and end_dt >= to_char(sysdate, 'YYYY-MM-DD') " +
	"     and is_use = 'T' " +
	"     and is_show = 'T' " +
	"     order by poll_id desc) " +
	" where rownum <= 1 ";

    MdbDriver oDb = null;
    Map poll = null;
    try {
        oDb = Mdb.getMdbDriver();
        Map param = new HashMap();
        poll = (Map) oDb.selectorOne(Map.class, sql, param);
        request.setAttribute("poll", poll);
    } finally {
        try {
            oDb.close();
        } catch (Exception ex) {
        }
        oDb = null;
    }

    final String sql_list = "select *  " +
    " from www_poll_item " +
    " where poll_id = :poll_id" +
    " order by seq asc";
    oDb = null;
    try {
        oDb = Mdb.getMdbDriver();
        Map param = new HashMap();
        param.put("poll_id", poll.get("poll_id"));
        request.setAttribute("pollitems", oDb.selector(Map.class, sql_list, param));
    } finally {
        try {
            oDb.close();
        } catch (Exception ex) {
        }
        oDb = null;
    }

%>

<script language="javascript">
function doSelect() {
    var frm = getObject("myform");
    var poll_id = frm.poll_id.value;
    var item_id = 1;
    for (i=0; i<frm.item_id.length;i++) {
        if(frm.item_id[i].checked) break;
        item_id = item_id + 1;
    }

    var Result = window.open('/poll.do?poll_id='+poll_id+'&item_id='+item_id,'Message','height=10,width=10,left=0,top=0, menubar=no,directories=no,resizable=yes,status=no,scrollbars=no');
    Result.focus();
}

function goResult() {
    var frm = getObject("myform");
    var poll_id = frm.poll_id.value;

    var Result = window.open('/poll.do?cmd=result&poll_id='+poll_id,'Message','height=10,width=10,left=0,top=0, menubar=no,directories=no,resizable=yes,status=no,scrollbars=no');
    Result.focus();
}
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="poll_id" value="${poll.poll_id}"/>  
<table width="100%" border="0" cellspacing="2" cellpadding="2">
    <tr>
        <td bgcolor="#EEEEEE">
			<table width="100%" border="0" cellspacing="5" cellpadding="5" bgcolor="#FFFFFF">
			    <col width="50"/>
			    <col width="*"/>
			    <tr>
			        <td colspan="2"><mh:out value="${poll.title}"/></td>
			    </tr>
			    <c:forEach var="item" items="${pollitems}" varStatus="status">
			    <tr>
			        <td><mf:input type="radio" name="item_id" value="${item.seq}"/></td>  
			        <td><mh:out value="${item.question}"/></td> 
			    </tr>
			    </c:forEach>
			    <tr>
			        <td align="center" colspan="2">
			            <mf:button bundle="button" key="select" onclick="doSelect()" /> 
			            <mf:button bundle="button" key="survey.result" onclick="goResult();" />
			        </td>
			    </tr>
			</table>
        </td>
	</tr>
</table>
</mf:form>