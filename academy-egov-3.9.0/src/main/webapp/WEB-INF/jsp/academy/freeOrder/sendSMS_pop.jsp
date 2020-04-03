<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<title>인증센터</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />


<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/js/modernizr.custom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/base64.js"/>"></script>

<script type="text/javascript">
function doCheck() {
    $.ajax({
        type : "GET", 
        url : '<c:url value="/freeOrder/PhoneCheck"/>?smsCode=' + ($("#smsCode").val()),
        //dataType : "json", <!-- 단순 문자나 숫자값이 리턴될 경우 json parser error 발생할 수 있음.-->      
        cache : false,
        success : function(data, textStatus, jqXHR) {
            if(data=="Y"){
				alert('인증번호가 확인되었습니다. \n수강등록을 진행해주세요.');
				window.opener.document.isChk.value="Y";
				window.close();
            } else if(data =="N"){
                alert('인증번호가 맞지 않습니다. \n확인후 정확하게 입력해 주십시요.');
                return;
            }
        },
        error : function(jqXHR, textStatus, errorThrown) {
            alert("인증에 실패하였습니다.");
            return;
        }
    });
}
</script>
</head>
<body>
	<div class="cf-pop">       
		<div class="cf-popcontent">         
            <h3>인증번호 입력</h3>
            <div class="cfBox">                
                <div class="sub-s-title">
                    윌비스 관리자에 등록된 아이피가 아닙니다. <br>휴대폰으로 전송된 인증코드로 본인인증 진행 후 로그인해 주세요.<br>
                    휴대폰번호가 입력되지 않은 담당자분은 관리자에게 문의 바랍니다.
                </div>
                <form name="frm" id="frm" method="post" action="">
                    <input type="text" id="smsCode" name="smsCode" value="${SMSCODE }" /> <a onClick="doCheck();" />확인</a>
                </form>                         
            </div><!--cfBox//-->
        </div><!--cf-popcontent//-->
	</div><!--cf-pop//-->
</body>
</html>