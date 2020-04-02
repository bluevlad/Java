<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">

var message = "${message}";
var smsname = "${searchMap.smsname}";
var userphone = "${searchMap.userphone}";

window.onload = function () {
	
	if(message == "등록완료") {
		alert("SMS를 정상적으로 보냈습니다.");
		self.close();
	}
	
}

// 보내기
function sendMessage() {
        
    if ($('#msg_rc_id').val() == "") {
    	alert("받을사람을 입력하세요.");
		$('#msg_rc_id').focus();
		return;
	}
    
    if ($('#tran_msg').val() == "") {
    	alert("SMS 메세지를 입력하세요.");
		$('#tran_msg').focus();
		return;
	}
    
    $('#popFrm').attr('action','<c:url value="/productOrder/insertSms.do"/>').submit();
}

function check_msglen() {
    var obj = document.popFrm;
    var length = calculate_msglen(obj.tran_msg.value);
    document.all.nbytes.innerText = length;
//    if (length > 80) {
//        alert("무선메시지는 최대 80 바이트까지 전송하실 수 있습니다.\r\n초과된 " + (length - 80) + "바이트는 자동으로 삭제됩니다.");
//        obj.tran_msg.value = assert_msglen(obj.tran_msg.value, 80);
//    }
}

function calculate_msglen(message) {
    var nbytes = 0;
    for (i=0; i<message.length; i++) {
        var ch = message.charAt(i);
        if (escape(ch).length > 4) {
            nbytes += 2;
        } else if (ch != '\r') {
            nbytes++;
        }
    }
    return nbytes;
}

function assert_msglen(message, maximum) {
    var inc = 0;
    var nbytes = 0;
    var msg = "";
    var msglen = message.length;

    for (i=0; i<msglen; i++) {
        var ch = message.charAt(i);
        if (escape(ch).length > 4) {
            inc = 2;
        } else if (ch != '\r') {
            inc = 1;
        }
        if ((nbytes + inc) > maximum) {
            break;
        }
        nbytes += inc;
        msg += ch;
    }
    document.all.nbytes.innerText = nbytes;
    return msg;
}

function cal_byte(cont,txtbox,lengbox,length) {
    var onechar;
    var tcount=0;

    var tmpStr = new String(txtbox.value);

    for (k=0;k<tmpStr.length;k++)
    {
        onechar = tmpStr.charAt(k);
        if (escape(onechar) =='%0D') { //CR
        } else if (escape(onechar).length > 4) {
            tcount += 2;
        } else {
            tcount++;
        }
    }

    lengbox.value = tcount;
    if(tcount>length) {
        reserve = tcount-length;
        alert(cont+ "은 " + length + " Byte 이상 입력할 수가 없습니다. \r\n\n 입력한 " + cont + "는 " + reserve + "Byte초과가 되었습니다. \r\n\n 초과된 부분은 자동 삭제됩니다.");
        cutText(txtbox,lengbox,length);
        return;
    }

}

function cutText(txtbox,lengbox,length){
    var onechar;
    var tcount = 0;
    var tmpStr = new String(txtbox.value);
    for(k=0;k<tmpStr.length;k++)
    {
        onechar = tmpStr.charAt(k);
        if(escape(onechar).length > 4) {
            tcount += 2;
        } else {
            tcount++;
        }

        if(tcount>length) {
            tmpStr = tmpStr.substring(0,k);
            break;
        }
    }
    txtbox.value = tmpStr;
    cal_byte(txtbox.value,txtbox,lengbox,length);
}

</script>
</head>

<body>
<form id="popFrm" name="popFrm" method="post">

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
   
<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
<input type="hidden" id="search_date_type" name="search_date_type" value="${searchMap.search_date_type}" />
<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
<input type="hidden" id="payall" name="payall" value="${searchMap.payall}" />
<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

<table style="width:100%;">
    <tr>
	    <td width="3%">
	    </td>
    	<td width="94%">
				
			<table width="100%" border="0" cellspacing="0" cellpadding="12">
			  <tr>
			    <td align="left" bgcolor="#FFFFFF"><h2>▶SMS  보내기</h2></td>
			  </tr>
			</table>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				  <td height="20" align="center" bgcolor="#FFFFFF"><p>&nbsp;</p>
				    <font color="blue">번호를 추가 하실경우 구분자 </font><strong><font color="red">/</font></strong><font color="blue">으로 해주세요! ex></font><font color="red">/</font><font color="blue">123-456-1234</font>
				  </td>
				</tr>
			</table>
			
			<!--//테이블--> 
			    	<table class="table05">
			    	<tr>
			    		<th >받을사람</th>
			   			<td class="tdLeft">
				   				<input type="text" id="msg_rc_id" name="msg_rc_id"  title="레이블 텍스트"  style="width:68%; background:#FFECEC;" value="${searchMap.smsname }" />
			   			</td>
			   		</tr>
			   		<tr>
			    		<th >전화번호</th>
			   			<td class="tdLeft">
			   					<input type="text" id="userphone" name="userphone"  title="레이블 텍스트"  style="width:68%; background:#FFECEC;" value="${searchMap.userphone }" />
			   			</td>
			   		</tr>	
			   		<tr>
			   			<th >메세지</th>
			   			<td class="tdLeft">
			   					<textarea rows="2" cols="60" name="tran_msg" id="tran_msg" onKeyUp="check_msglen();"></textarea>
			   					<br><font color="blue">
									<label class="nbytes" onmousedown="return false" ondragstart="return false" onselect="return false" id="nbytes" ondragdrop="return false" size="1">
										0
									</label>
									</font>
			   			</td>
			   		</tr>
			   		<tr>
			   			<th>발신자전화번호</th>
			   			<td class="tdLeft">
			   					<c:choose>
			   						<c:when test="${fn:substring(menuParams.TOP_MENU_ID, 0, 1) == 'F'}">
			   							<c:set var="send_phone" value="1544-0336"/>
			   						</c:when>
			   						<c:otherwise>
			   							<c:set var="send_phone" value="1544-6219"/>
			   						</c:otherwise>
			   					</c:choose>
			   					<input type="text" id="send_no" name="send_no"  title="레이블 텍스트"  maxlength="13" style="width:30%; background:#FFECEC;" value="${send_phone}" />
			   			</td>
			   		</tr>
					</table>
				<!--//테이블-->
				
				<!--버튼-->
			    <ul class="boardBtns">
			   	  <li>
			   	  	<a href="javascript:sendMessage();">보내기</a>				
			   	  </li>
			   	  <li>
			   	  	<a href="javascript:self.close();">닫기</a>				
			   	  </li>
			    </ul>
			    <!--//버튼-->
	    </td>
        <td width="3%">
	    </td>
    </tr>
    </table>
    
</form>
</body>
</html>