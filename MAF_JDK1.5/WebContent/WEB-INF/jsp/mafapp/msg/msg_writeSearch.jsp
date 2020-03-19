<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle  var="m" basename="resources.msg"/>
<fmt:setBundle var="a" basename="resources.message"/>
<script language="javascript">
<!--
//추가버튼 클릭
function choice_name(){
    var form_id1 = getObject("select_1");
	var form_id2 = getObject("select_2");

    for(i=0;i < form_id1.options.length  ; i++){
        if(form_id1.options[i].selected == true){
            var text = form_id1.options[i].text;
            var value = form_id1.options[i].value;
            var option2 = new Option(text, value);
            var sl = form_id2.options.length;
            form_id2.options[sl] = option2;
            form_id1.options[i] = null;
            i --;
        }
    }
}
    
//삭제버튼 클릭
function choice_cancel(){
	var form_id1 = getObject("select_1");
	var form_id2 = getObject("select_2");

    for(i=0;i < form_id2.options.length ; i++){
        if(form_id2.options[i].selected == true){
            var text = form_id2.options[i].text;
            var value = form_id2.options[i].value;
            var option1 = new Option(text, value);
            var sl = form_id1.options.length;
            form_id1.options[sl] = option1;
            form_id2.options[i]=null;                
            i--;                
        }
    }
}

//확인버튼 클릭
function update(){
	var frm= getObject("frm");
	var lenn = frm.select_2.options.length;

    if(lenn == 0){                      
		alert("<fmt:message bundle = '${a}' key = 'msgalt.selnm'/>");
    } else {
    	if(window.opener) {
    		var wid = window.opener; 
    		if (wid) {    
				var temp = frm.select_2.options[0].text;	      		  		        
   		        var names = temp.substring(0,temp.indexOf("("));
   		        var usns = document.frm.select_2.options[0].value;

   		        for(i=1; i< lenn; i++){
						var temp = frm.select_2.options[i].text;
    			        names = names + "," + temp.substring(0, temp.indexOf("("));
    			        usns = usns + ";" + frm.select_2.options[i].value;
   				}
				if ( wid.document.forms[0].nm_rcv.value == "")  {
	    			wid.document.forms[0].nm_rcv.value = names;
	    			wid.document.forms[0].usn_rcv.value = usns;
				} else {
					wid.document.forms[0].nm_rcv.value = wid.document.forms[0].nm_rcv.value + "," + names;
					wid.document.forms[0].usn_rcv.value = wid.document.forms[0].usn_rcv.value + ";" + usns;
				}

    			top.window.close(); 
    		}	 
    	}
    	
    	return;
    }       
}

function InquiryForm()
{
	var frm = getObject("frm");
	if(frm.selname.value == ""){
			alert("<fmt:message bundle="${a}" key="msgalt.selcon"/>");
		}
    else if (frm.nm.value == "") {
        //alert("검색어를 입력하세요");
		alert("<fmt:message bundle = "${a}" key = "msgalt.input"/>");
        frm.nm.focus();

    } else {
        
        frm.submit();

        return true;
    }
}
window.resizeTo(380, 300);
//-->
</script>
<form name="frm" action="${CPATH}/msgmst/search.do" method="post">
<table width="376" height="257" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="top"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
        	<tr>
        		<td>
				<!--menu start--><table width="370"  border="0" cellpadding="0" cellspacing="0">
                	<tr>
                		<td><img src="${CPATH}/images/msg/search_title.gif" width="370" height="43"></td>
                		</tr>
                	</table>
				<!--menu end-->
					</td>
        		</tr>
        	<tr>
        		<td height="3"></td>
        		</tr>
        	<tr>
        		<td>
				<!--list start-->
				<table width="370" height="169" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                		<td align="center" valign="middle" bgcolor="CCCCCC"><table width="368" height="167" border="0" cellpadding="0" cellspacing="0">
                        	<tr>
                        		<td align="center" valign="middle" bgcolor="F8F8F8"><table width="350" height="149" border="0" cellpadding="0" cellspacing="0">
                                	<tr>
                                		<td height="22"><table width="166" border="0" cellspacing="0" cellpadding="0">
                                        	<tr>
                                        		<td height="5"></td>
                                        		<td width="60"><select name="selname">
														<option ><fmt:message bundle = "${m}" key ="msg.search.sel"/></option>
                                        				<option  value="1" selected><fmt:message bundle = "${m}" key ="msg.search.nm"/></option>
                                        				<!--<option  value="2"><fmt:message bundle = "${m}" key ="msg.search.coll"/></option>-->
														<option value="3"><fmt:message bundle = "${m}" key ="msg.search.addr"/></option>
                                        				</select></td>
                                        		<td><input name="nm" type="text" style="border-style :solid;border-width=1;border-color:#898989" size="10" value="${nm}"></td>
                                        		<td width="38" align="center"><a href="javascript:InquiryForm()"><img src="${CPATH}/images/button/s_search.gif" border = "0" width="30" height="18"></a></td>
                                        		</tr>
                                        	</table></td>
                                		</tr>
                                	<tr>
                                		<td><table width="350" height="127" border="0" cellpadding="0" cellspacing="0">
                                        	<tr>
                                        		<td width="148" height="20"><img src="${CPATH}/images/msg/search_result.gif" width="71" height="20"></td>
                                        		<td width="54">&nbsp;</td>
                                        		<td width="148"><img src="${CPATH}/images/msg/search_msg.gif" width="83" height="20"></td>
                                        	</tr>
                                        	<tr>
                                        		<td align="center" valign="middle">
													<select name="select_1" size="7" style='width:150px; height:130px' multiple>
														<c:forEach var="searchlist" items="${searchlist}">	
															<option value = "${searchlist.usn}">${searchlist.nm}(${searchlist.userid})</option>
														</c:forEach>
													</select>
												</td>
                                        			
												<td align="center" valign="middle"><table width="40" border="0" cellspacing="0" cellpadding="0">
                                                	<tr>
                                                		<td><a href="javascript:choice_name()"><img src="${CPATH}/images/button/add.gif" width="40" height="18" border="0"></a></td>
                                                		</tr>
                                                	<tr>
                                                		<td height="4"></td>
                                                		</tr>
                                                	<tr>
                                                		<td><a href="javascript:choice_cancel()"><img src="${CPATH}/images/button/exc.gif" width="40" height="18" border="0"></a></td>
                                                		</tr>
                                                	</table></td>
                                        		<td align="center" valign="middle">
													<select name="select_2" size="7" style='width:150px;height:130px' multiple></select>
												</td>
                                        	</tr>
                                        	</table></td>
                                		</tr>
                                	</table></td>
                        	</tr>
                        	</table></td>
                	</tr>
                	</table><!--list end-->					</td>
        		</tr>
        	<tr>
        		<td height="9"></td>
        		</tr>
        	<tr>
        		<td align="right"><table border="0" cellspacing="0" cellpadding="0">
                	<tr>
                		<td><a href="javascript:update();"><img src="${CPATH}/images/button/ok.gif" width="56" height="21" border="0"></a></td>
                		<td width="4"></td>
                		<td><a href="javascript:top.window.close()"><img src="${CPATH}/images/button/close.gif" width="56" height="21" border="0"></a></td>

                		<td width="11"></td>
                	</tr>
                	</table></td>
        		</tr>
        	<tr>
        		<td height="5"></td>
        		</tr>
        	</table></td>
	</tr>
</table>
</form>