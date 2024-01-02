<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<style>
img{max-width:none;}
</style>
<script type="text/javascript">
var winResizeW=0;
var winResizeH=0; 
function popupAutoResize()
{
    var conW = $("#content_pop").innerWidth(); //컨텐트 사이즈
    var conH = $("#content_pop").innerHeight();

    var winOuterW = window.outerWidth; //브라우저 전체 사이즈
    var winOuterH = window.outerHeight;
    
    var winInnerW = window.innerWidth; //스크롤 포함한 body영역
    var winInnerH = window.innerHeight;
    
    var winOffSetW = window.document.body.offsetWidth; //스크롤 제외한 body영역
    var winOffSetH = window.document.body.offsetHeight;
    
    var borderW = winOuterW - winInnerW;
    var borderH = winOuterH - winInnerH;
    
    winResizeW = conW + borderW;
    winResizeH = conH + borderH+100;
    
    window.resizeTo(winResizeW,winResizeH); 
}

var currentsize = 0;
var valuesize = 1.2;
var imgw = 0;
var imgh = 0; 
var maximgw = 0;
var maximgh = 0; 
$(document).ready(function(){
	imgw = $("#showbig").width();
	imgh = $("#showbig").height();
	 $("#showbig").dblclick(function(){
		 $('#showbig').css('max-width', '');
		 $('#showbig').css('max-height', '');
		 if(maximgw==0){
			 maximgw = $('#showbig').width()*valuesize;
		 }
		 if(maximgh==0){
		 	maximgh = $('#showbig').height()*valuesize;
		 }
		 $('#showbig').width(maximgw);
         $('#showbig').height(maximgh);
         if(valuesize==1.2){
        	 currentsize = currentsize+1;
        	 if(currentsize==1){
        		 valuesize = 0;
        	 }
         }else{
        	 $('#showbig').width(imgw);
             $('#showbig').height(imgh);
        	 currentsize = 0;
       		 valuesize = 1.2;
         }
         popupAutoResize();
	  });
	 //크롬, 사파리일때
     if (navigator.userAgent.indexOf('Chrome')>-1 || navigator.userAgent.indexOf('Safari')>-1) 
     {
         $(window).resize(function() {
             
             if(winResizeW==0 && winResizeH==0)
             {
                 resizeWin();
             }
         });
     }
     //크롬, 사파리말고 모두
     else
     {
         resizeWin();
     }
 });
function setPopChk(id,name,phoneno,is_chk){
	var message = ""; 
	if(is_chk=="Y"){
		message = "인증 완료하시겠습니까?"; 
	}else if(is_chk=="N"){
		message = "인증 취소하시겠습니까?"; 
	}
	if(confirm(message)){
		var checkparam = "EVENT_NO="+$("#EVENT_NO").val()+"&EVENT_USER_ID="+id;
		checkparam+="&USER_NAME="+encodeURIComponent(name) +"&PHONE_NO="+phoneno+"&SEND_NO="+$("#SEND_NO").val()+"&IS_CHK="+is_chk;
		
		$.ajax({		     
		     type: "POST", 
		     url : '<c:url value="/LecEventMng/update_rebound_chk.json"/>?'+ checkparam,
		     dataType: "text",  
		     async : false,
		     success: function(RES) {
		      if($.trim(RES)=="Y"){           
		        alert("변경하였습니다.");
		        opener.location.reload();
		        window.close();
		      }else{
		    	  alert("실패하였습니다.");
		    	  return;
		      }
		     },error: function(){
		    	 alert("실패하였습니다.");
		      return;
		     }
		  });
	}
}
</script>
</head>
<body>
<!--content -->
<form id="frm" name="frm" method="get" action="">
<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="${params.EVENT_NO}"/>
<input type="hidden" id="PHONE_NO" name="PHONE_NO" value="${params.PHONE_NO}"/>
<input type="hidden" id="USER_ID" name="USER_ID" value="${params.USER_ID}"/>
<input type="hidden" id="EVENT_USER_ID" name="EVENT_USER_ID" value="${params.EVENT_USER_ID}"/>
<input type="hidden" id="SEND_NO" name="SEND_NO" value="${params.SEND_NO}"/>
<input type="hidden" id="IS_CHK" name="IS_CHK" value="${params.IS_CHK}"/>
<div id="content_pop" style="padding-left:10px;">
<div><img id="showbig" style="max-width:700px;max-height:700px;" src = "/imgFileView.do?path=/${params.FILE_PATH}"></div>
</div>
<ul class="boardBtns">
		<c:choose>
          <c:when test="${empty params.IS_CHK || params.IS_CHK==''}">
          	<li><a href="javascript:setPopChk('${params.EVENT_USER_ID}','${params.USER_NAME}','${params.PHONE_NO}','Y')">승인확인</a></li>
          	<li><a href="javascript:setPopChk('${params.EVENT_USER_ID}','${params.USER_NAME}','${params.PHONE_NO}','N')">승인취소</a></li>
          </c:when>
          <c:when test="${params.IS_CHK=='Y'}">
          	<li><a href="javascript:setPopChk('${params.EVENT_USER_ID}','${params.USER_NAME}','${params.PHONE_NO}','N')">승인취소</a></li>
          </c:when>
            <c:when test="${params.IS_CHK=='N'}">
          	<li><a href="javascript:setPopChk('${params.EVENT_USER_ID}','${params.USER_NAME}','${params.PHONE_NO}','Y')">승인확인</a></li>
          </c:when>
         </c:choose>
   	  	<li><a href="javascript:window.close();">닫기</a></li>
    </ul>
</form>
<!--//content --> 
</body>
</html>