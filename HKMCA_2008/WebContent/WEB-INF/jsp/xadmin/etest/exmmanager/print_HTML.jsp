<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<%@page import="java.util.*"%>
<%@page import="maf.*"%>
<%@page import="maf.base.BaseHttpSession"%>
<%@page import="maf.web.session.MySession"%>
<%@page import="maf.web.util.CookieUtil"%>
<%@page import="modules.etest.support.*"%>
<%@page import="modules.etest.learner.*"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td style="padding-left:20px">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <col width="20%"/>
                <col width="30%"/>
                <col width="20%"/>
                <col width="30%"/>
                <tr>
                    <td colspan="4">Course Name : <strong><mh:out value="${item.exmtitle }"/></strong></td>
                </tr>
                <tr>
                    <td>Distributer/Dealer Code :</td>
                    <td></td>  
                    <td>Distributer/Dealer Name :</td>  
                    <td></td>  
                 </tr>
                 <tr>
                    <td>Applicant ID :</td>  
                    <td></td>  
                    <td>Applicant Name :</td>  
                    <td></td>  
                 </tr>
                 <tr>
                    <td>Inspector's Signature :</td>  
                    <td></td>  
                    <td>Score :</td>  
                    <td></td>  
                 </tr>
            </table><br><br>&nbsp;
           </td>
        </tr>
        <tr>
        <td>

        <table border="0" cellspacing='0' cellpadding='0' width="100%" bordercolor="#f0f0f0">
            <col width="50" />
            <col width="*" />
            <%
                List quelist = (List) request.getAttribute("list");
                String iType = null;
                String ansId = null;
                String qseq = null;
                for (int i = 0; i < quelist.size(); i++) {
                    Map rec = (Map) quelist.get(i);
                    QueViewInfo view = new QueViewInfo(rec);

                    qseq = (i+1) + "";
            %>

            <tr>
                <td valign="top" align="center"><strong>Q<%=qseq%>.</strong></td>
                <td style="padding-bottom:20px;" valign="top">                
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td valign="top"><strong><%=rec.get("quetitle")%></strong>
                        <% 
                            // quetext 가 있으면 
                            if (!MafUtil.empty(rec.get("quetext"))) {  %><br>
                        <div style="border:1px solid #eaeaea"><%=rec.get("quetext")%></div>
                        <% } %>
                        </td>
                        <td align="right" valign="top"><pre>[        ]</pre></td>
                    </tr>
                    <%
                            // 이미지 있으면 이미지 뿌려줌 
                            if (!MafUtil.empty(rec.get("queimag"))) {
                    %>
                    <tr>
                        <td align="center" colspan="2"><img src='<c:url value="/wdownload?type=exam_file&ext="/>&filename=<%=rec.get("queimag")%>' border='0' /></td>
                    </tr>
                    <%
                            } // if
                    %>
                    <tr>
                        <td style="padding-left:20px">
                    <%
                            
                            // 문제 유형별로 답안 입력 부문 표시 
                            if (Etest.QUE_TYPE_MULTI.equals(view.getQtype())
                                    || Etest.QUE_TYPE_SINGLE.equals(view.getQtype())) {
                                if (Etest.QUE_TYPE_MULTI.equals(view.getQtype())) {
                            iType = "checkbox";
                                } else {
                            iType = "radio";
                                }
                                for (int j = 1; j <= view.getQuecount(); j++) {
                    %>
                        <%=j%>)&nbsp;<%=view.getView(j)%><br>
                    <%
                            }
                            } else if (Etest.QUE_TYPE_DESCRIPTION.equals(view.getQtype())) {
                    %>
                        <br><br><br><br><br>&nbsp;
                    <%
                    } else if (Etest.QUE_TYPE_TEXT.equals(rec.get("quetype"))) {
                    %>
                        <br><br><br><br><br>&nbsp;
                    <%
                        }
                        
                    %>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
                
           <%

           } // for
           %>

        </table>
</td>
</tr>
</table>