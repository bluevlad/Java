<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script>
	function whois() {
		var frm = document.getElementById("frmwhois");
		if(frm) {
			frm.submit();
		}
	}
	
	function removeSession() {
		var frm = document.getElementById("frmLogin");
		if(frm) {
			frm.submit();
		}
	}	
</script>
<table border="0" cellspacing="0" cellpadding="5" align="center" style="border: 5px solid #EBEBEB;">

	<tr> 
		<td align="center">다음과 같은 접속 정보가 존재 합니다.<br>기존 접속정보를 삭제 하시겟습니까?<br>
			<hr>
			접속 IP : ${sinfo.loginIP} <span style="text-decoration: underline; color: #0000ff; cursor: pointer;" onClick="whois()">Whois</span><br>
			최종 접근 시간 : ${mdate:long2Date(sinfo.httpSession.lastAccessedTime)}<br>
			<a href="javascript:removeSession()">[예]</a> <a href="/">[아니오]</a>
		</td>
	</tr>
<form action="${CPATH}/login.do" method="post" name="frmLogin" id="frmLogin" >
	<input type="hidden" name="cmd" value="removesess">

	<input type="hidden" name="${TOKEN.key}" value="${TOKEN.value}">
	<input type="hidden" name="next" value="${(empty next)?'/':mhtml:JSaddSlashes(next)}">
	<input type="hidden" name="type" value="${type}">
</form>
<form action="http://whois.nida.or.kr/result.php" method="post" name="frmwhois" id="frmwhois" target="_blank">
	<input type="hidden" name="domain_name" value="${sinfo.loginIP}">
</form>
</table>