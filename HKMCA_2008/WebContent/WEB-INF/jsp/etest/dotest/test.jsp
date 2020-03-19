<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<%
	 /*************************************************
	 *
	 * 시험 문제 풀이 (조금이라도 속도를 위해 JSTL 사용 안함 )
	 *
	 *
	 */
%>
<%@page import="java.util.*"%>
<%@page import="maf.*"%>
<%@page import="maf.base.BaseHttpSession"%>
<%@page import="maf.web.session.MySession"%>
<%@page import="maf.web.util.CookieUtil"%>
<%@page import="modules.etest.support.*"%>
<%@page import="modules.etest.learner.*"%>
<%
	BaseHttpSession ms = MySession.getSessionBean(request, response);
	String test_name = CookieUtil.getValue(request,DoTestAction.EXAM_NAME_KEY);
//    String test_name = (String)request.getAttribute("test_name");
%>
<script language="javascript">
    function goPage(np,qseq) {
        var frm=getObject('myform');
        frm.pageno.value=np;
        frm.cmd.value="test";
        frm.action=frm.action + "#q_" + qseq ;
        frm.submit();
    }
    function test_finish() {
        if (confirm("are you finish")) {
            sendFinish() ;
        }
    }
    function sendFinish() {
        var frm=getObject('myform');
        frm.cmd.value="finish";
        var params = $('myform').serialize() ;
        var url = '<%=request.getAttribute("controlaction")%>';
        new Ajax.Request(url, {
                        method: 'get', 
                        parameters: params,
                        onSuccess: function(transport) {
                            window.opener.document.location.reload();
                            window.close();
                        }
                      }
                ); 
    }
    function sendAns(qid,qseq) {
        var frm=getObject('myform');
        frm.cmd.value="updateAnswer";
        frm.ansqid.value=qid;
        frm.qseq.value=qseq;
        var params = $('myform').serialize() ;
        //alert("str:" + params);
        var url = '<%=request.getAttribute("controlaction")%>';
        new Ajax.Request(url, {
                        method: 'get', 
                        parameters: params,
                        
                        onSuccess: function(transport) {
                            var json = transport.responseText.evalJSON();
                            var nv = 'nv_ans_'+json.qseq;
                            
                            displayLeftCnt(json.leftCnt);
                            if (!$(nv).hasClassName('q_answerd_y')) {
                                $(nv).removeClassName('q_answerd_n');
                                $(nv).addClassName('q_answerd_y');
                            };
                        }
                      }
                ); 
    }
    
    function toggleMark(obj,qseq) {
       
        var url = '<%=request.getAttribute("controlaction")%>';
        url += '?cmd=updateMark';
        url += '&qseq=' + qseq;
        url += '&marked=' + ((obj.checked)?'Y':'N');
        
        //alert("str:" + url);
        new Ajax.Request(url, {
                        method: 'get', 
                                             
                        onSuccess: function(transport) {
                            var json = transport.responseText.evalJSON();
                            var nv = 'nv_mark_'+json.qseq
                            if (!$(nv).hasClassName('q_marked_y')) {
                                $(nv).removeClassName('q_marked_n');
                                $(nv).addClassName('q_marked_y');
                            } else {
                                $(nv).removeClassName('q_marked_y');
                                $(nv).addClassName('q_marked_n');
                            };
                        }
                      }
                ); 
    }
    
    var timerID=null;
    var istimer = false;
    var past=0;
    
    function startclock (times){
        past = times;
        min = parseInt(past / 60);
        sec = past % 60;
    
        stopclock();
        timer();       
    }    
       
    function stopclock(){
        if (istimer) clearTimeout(timerID);        
        istimer=false;        
    }    
    
    function timer(){
        if(past == 0){
            doFin();
        }
    
        if(past>0) past  = past - 1;
        min = parseInt(past / 60);
        sec = past % 60;
        
        if(sec<10) sec="0"+sec;
        var obj = getObject("div_time_left");
        
        obj.innerHTML= "Time Left : " + min+":"+sec;
        timerID=setTimeout('timer()',1000);
        istimer=true;
    }
    function displayLeftCnt( leftCnt ) {
        var obj = getObject("div_question_left");
        obj.innerHTML = "Question Left : " + leftCnt;
    }
    
    function doFin(sel){
        var sw = true;    
        if(sel==1){
            if(!confirm('<mfmt:message bundle="common" key="table.message.exm.end"/>')){
                sw=false;
            }    
         }else if(sel==2){
            alert('<mfmt:message bundle="common" key="table.message.exm.timeout"/>');
         }    
        if(sw){
            sendFinish() ;
        }
    }
    addEvent(window, 'load',function() {startclock(<c:out value="${rstinfo.remain}" default="0"/>*60)} );
    addEvent(window, 'load',function() {displayLeftCnt(left_cnt)} );
    
</script>
<script language="javascript">
    function SetDivPosition()
    { 
        var intY = document.getElementById("div_navi").scrollTop; 
        document.title = intY; 
        document.cookie = "yPos=!~" + intY + "~!";
     } 
     function setNaviPos() {
         var strCook = document.cookie; 
        if(strCook.indexOf("!~")!=0) { 
            var intS = strCook.indexOf("!~"); 
            var intE = strCook.indexOf("~!"); 
    
            var strPos = strCook.substring(intS+2,intE); 
    
            document.getElementById("div_navi").scrollTop = strPos; 
    
         } 
     }
     addEvent(window, 'load',function() {setNaviPos()} );
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td colspan="2">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="height:26px;color:#E6E6E6;background:#595959;border-bottom:1px solid #000000;">
			    <tr>
			        <td>
			        <table border="0" cellspacing="0" cellpadding="0">
			            <tr>
			                <td width="13"></td>
			                <td width="15" align="center"><img src='<c:url value="/images/etest/icon_man.gif"/>' border="0" /></td>
			                <td>Examinee : <c:out value="${sessionScope.msession.nm}" /></td>
			            </tr>
			        </table>
			        </td>
			        <td align="right">
			        <table border="0" cellspacing="0" cellpadding="0">
			            <tr>
			                <td><img src='<c:url value="/images/etest/button_help.gif"/>' border="0" onClick="" class="link" /></td>
			                <td width="6"></td>
			                <td><img src='<c:url value="/images/etest/button_finish.gif"/>' border="0" onClick="test_finish()" class="link" /></td>
			                <td width="26"></td>
			            </tr>
			        </table>
			        </td>
			    </tr>
			</table>
		</td>
	</tr>
	<tr>
	   <td colspan="2">
			<table width="100%" height="46" border="0" cellspacing="0" cellpadding="0">
			    <col width="755" />
			    <col width="95" />
			    <tr>
			        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			            <tr height="46">
			                <td style="padding-left:20px; font-size:18px; font-weight:bold; color:#010000;"><%=test_name%></td>
			                <td style="padding-right:10px; " align="right" valign="bottom">
			                    <table border="0" cellspacing="0" cellpadding="0" >
			                        <tr>
			                            <td>
			                                <div id="div_question_left" >Question Left : </div> 
			                             </td>
			                             <td> &nbsp;/&nbsp;</td>
			                             <td>
			                                <div id="div_time_left" > Time Left : 0 </div>
			                             </td>
			                            </tr>
			                           </table>
			                    </td>
			            </tr>
			        </table>
			        </td>        
			    </tr>
			</table>
		</td>
	</tr>
	<tr>
        <td>
            <form action="<%=request.getAttribute("controlaction")%>" method="post" name="myform" id="myform">
		    <input type="hidden" name="exmid" value="<%=request.getParameter("exmid") %>" /> 
		    <input type="hidden" name="pageno" value="" />
		    <input type="hidden" name="cmd" value="" />
		    <input type="hidden" name="ansqid" value="" />
		    <input type="hidden" name="qseq" value="" />
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		            <td valign="top">
		                <table border="0" cellspacing='0' cellpadding='0' width="100%" bordercolor="#f0f0f0">
		                <col width="100" />
		                <col width="*" />
		                <%
		                    List quelist = (List) request.getAttribute("quelist");
		                    String iType = null;
		                    String ansId = null;
		                    String qseq = null;
		                    for (int i = 0; i < quelist.size(); i++) {
		                        Map rec = (Map) quelist.get(i);
		                        QueViewInfo view = new QueViewInfo(rec);
		                        ansId = "queansw_" + rec.get("queid");
		                        qseq = MafUtil.getString(rec.get("qseq"));
		                %>
	                    <input type="hidden" name="queids" value="<%=rec.get("queid")%>" />
                            <tr>
                                <td valign="top" bgcolor="#F5F5F5" style="border-top:1px solid #E6E6E6;">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td><img src='<c:url value="/images/etest/el_qestion.gif"/>' border="0" /></td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                                <span style="color:#F7941D; font-size:30px;"><%=qseq%><a name="q_<%=qseq%>"/></span><br>
                                                Mark <input type="checkbox" name="m_<%=qseq%>" value="Y" onClick="toggleMark(this,'<%=qseq%>')"
                                                <%if("Y".equals(rec.get("mark_yn"))) { out.print ("CHECKED"); } %> >
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td bgcolor="FBFCFE" style="border-top:1px solid #E6E6E6; padding:10px;">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td>
                                                <%=rec.get("quetitle")%>
                                                <%// quetext 가 있으면
                                                if (!MafUtil.empty(rec.get("quetext"))) {  %><br>
                                                <div style="border:1px solid #eaeaea"><%=rec.get("quetext")%></div>
                                                <% } %>
                                            </td>
                                        </tr>
                                        <%
                                        // 이미지 있으면 이미지 뿌려줌 
		                                  if (!MafUtil.empty(rec.get("queimag"))) {
		                                %>
		                                <tr>
                                            <td align="center"><div style="width:620px;overflow-x:auto;"><img src='<c:url value="/wdownload?type=exam_file&ext="/>&filename=<%=rec.get("queimag")%>' border='0' /></div></td>
                                        </tr>
                                        <%
                                           } // if
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
		                                <tr>
                                            <td>
                                                <input type="<%=iType%>" id="<%=ansId%>" name="<%=ansId%>" value="<%=j%>"
                                                <% if(view.isViewCheck(j)) {out.print("CHECKED");} %> onClick="sendAns('<%=ansId%>','<%=qseq%>')" />&nbsp;<%=view.getView(j)%>
                                            </td>
                                        </tr>
                                        <%
                                        }
		                                } else if (Etest.QUE_TYPE_DESCRIPTION.equals(view.getQtype())) {
		                                %>
                                        <tr>
                                            <td><textarea name="<%=ansId%>" style=width:95% rows="7" onChange="sendAns('<%=ansId%>','<%=qseq%>')"><%=view.getUserAnswer()%></textarea></td>
                                        </tr>
                                        <%
                                        } else if (Etest.QUE_TYPE_TEXT.equals(rec.get("quetype"))) {
		                                %>
                                        <tr>
                                            <td><input type="text" name="<%=ansId%>" value="<%=view.getUserAnswer() %>" onChange="sendAns('<%=ansId%>','<%=qseq%>')"></td>
                                        </tr>
        		                        <%
        		                        }
		                                %>
                                    </table>
                                </td>
                            </tr>
        		            <%
        		            request.setAttribute("cur_page", rec.get("pageno"));
        		            } // for
        		            %>
                        </table>
                    </td>
                </tr>
            </table>
            </form>
	   </td>
	   <td width="250" valign="top">
    	   <jsp:include page="_navi.jsp" flush="true" />
	   </td>
    </tr>
    <tr>
       <td colspan="2">
            <table width="100%" height="46" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="3" align="center">
                        <c:choose>
                            <c:when test="${cur_page > 1 }">
                            <mf:button bundle="button" key="etest.prev" onclick="goPage('${cur_page-1 }')"/>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                            &nbsp;<c:out value="${cur_page } / ${rstinfo.maxpage}"/>&nbsp;
                        <c:choose>
                            <c:when test="${cur_page < rstinfo.maxpage }">
                            <mf:button bundle="button" key="etest.next" onclick="goPage('${cur_page+1 }')"/>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>    