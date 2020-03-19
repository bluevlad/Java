<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%
    List navlist = (List) request.getAttribute("navi");
%>
<table border="0" cellspacing='0' cellpadding='0' class="etest_navi" >
    <col width="20"/>
    <col width="20"/>
    <col width="20"/>
<%
    int left_cnt = navlist.size();
    for (int i = 0; i < navlist.size(); i++) {
        Map rec = (Map) navlist.get(i);
        String url_page = "goPage('" + rec.get("pageno") +"','')";
        //String url_page = "goPage('" + rec.get("pageno") +"','" + rec.get("qseq") + "')";
        String url_q = "goPage('" + rec.get("pageno") +"','" + rec.get("qseq") + "')";
%>
    <tr  class="<%=rec.get("navi_class")%>" title="<%=rec.get("pageno")%> page, question <%=rec.get("qseq")%>">
        <td align="center navi_bdr" onclick="<%=url_page%>"><a name="np_<%=rec.get("qseq")%>"/><%=rec.get("qseq")%></td>
        <%
        if ("Y".equals(rec.get("mark_yn"))) {
        %>
        <td class="q_marked_y navi_bd_left" id="nv_mark_<%=rec.get("qseq")%>" title="marked" onclick="<%=url_q%>">&nbsp;</td>
        <%
        } else {
        %>
        <td class="q_marked_n navi_bd_left" id="nv_mark_<%=rec.get("qseq")%>" onclick="<%=url_q%>">&nbsp;</td>
        <%
        }
        %>
        <%
        if ("Y".equals(rec.get("answerd"))) {
        	left_cnt --;
        %>
        <td class="q_answerd_y navi_bd_left" id="nv_ans_<%=rec.get("qseq")%>" onclick="<%=url_q%>">&nbsp;</td>
        <%
        } else {
            
        %>
        <td class="q_answerd_n navi_bd_left" id="nv_ans_<%=rec.get("qseq")%>" onclick="<%=url_q%>">&nbsp;</td>
        <%
        }
        %>
        <td width="50">&nbsp;</td>
    </tr>
<%
    }
%>
</table>
<Script>
    var left_cnt = <%=left_cnt%>;
    
</script>