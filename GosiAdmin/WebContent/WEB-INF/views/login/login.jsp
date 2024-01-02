<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>학원 관리자</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/js/modernizr.custom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.bpopup.min2.js"/>"></script>
<script type="text/javascript">
    window.onload = function(){
        getLogin();
        $("#USERID").focus();
    };
    
    //엔터키
    function fn_enter(){
        if(event.keyCode == 13) {
            fn_submit();
        }
        return;
    }
    
    function fn_submit(){
        if($("#USERID").val()== ""){
            alert("아이디를 입력해 주세요.");
            $("#USERID").focus();
            return;
        } else if($("#PSWD").val()== ""){
            alert("비밀번호를 입력해 주세요.");
            return;
        } else {
            if(frm.idCheck.checked) {
                saveLogin(frm.USERID.value);
            } else {
                saveLogin("");
            }
            
            $.ajax({
                type : "GET", 
                url : '<c:url value="/login/loginIP"/>?USERID='+($("#USERID").val()),
                cache : false,
                success : function(data, textStatus, jqXHR) {
                    if(data=="Y"){
                      	$('#frm').attr('action','<c:url value="/login/loginProc.adm" />').submit();
                    } else if(data =="E"){
                        alert("해당하는 관리자 정보가 없습니다. 로그인 정보를 확인해주세요.");
                        return;
                    } else if(data =="N"){
                        alert("새로운 아이피로 접속이 시도되었습니다.개인정보 인증을 진행해주세요.");
                      	$('#frm').attr('action','<c:url value="/login/loginProc.adm" />').submit();
//                        return;
                    }
                },
                error : function(jqXHR, textStatus, errorThrown) {
                    alert("다시 로그인해주시기 바랍니다.");
                    return;
                }
            });
        }
    }

    function getLogin(){
        // userid 쿠키에서 id 값을 가져온다.
        var cook = document.cookie + ";";  
        var idx = cook.indexOf("USERID", 0);
        var val = "";
    
        if(idx != -1){
            cook = cook.substring(idx, cook.length);
            begin = cook.indexOf("=", 0) + 1;
            end = cook.indexOf(";", begin);
            val = unescape( cook.substring(begin, end) );
         }
    
         // 가져온 쿠키값이 있으면
         if(val!= ""){
            document.frm.USERID.value = val;
            document.frm.idCheck.checked = true;
         }
    }

    function setsave(name, value, expiredays){
        var today = new Date();
        today.setDate( today.getDate() + expiredays );
        document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + today.toGMTString();
    }
    
    function saveLogin(id){
        if(id != ""){
            // userid 쿠키에 id 값을 7일간 저장
            setsave("USERID", id, 7);
        } else {
            // userid 쿠키 삭제
            setsave("USERID", id, -1);
        }
    }
</script>
</head>
<body>
<div class="admWrap">
    <div class="admInner">
        <div class="admHeader"><h1>학원 관리자</h1></div>
        <div class="admContainer">
            <div class="admForm">
                <h2><img src="<c:url value='/resources/img/adm_t1.png'/>" alt="LOGIN 로그인 정보를 입력하여 주십시오."></h2>
                <form id="frm" name="frm" action="" method="post">
                <fieldset>
                    <legend>관리자페이지 로그인</legend>                
                    <div class="logForm">
                        <div class="ln1"><label for="USERID">아이디</label><input type="text" id="USERID" name="USERID" value=""></div>
                        <div class="ln2"><label for="PSWD">비밀번호</label><input type="password" id="PSWD" name="PSWD" value="" onkeypress="fn_enter()"></div>
                        <div class="ln3"><input type="checkbox" name="idCheck" ID="idCheck"><label for="idCheck">아이디 저장</label></div>
                        <div class="btn"><a href="javascript:fn_submit();">로그인</a></div>
                    </div>                  
                </fieldset>
                <input name="j_username" type="hidden">
                </form>

            </div>
        </div>
        <div class="admFooter">Copyright ⓒ 2015. All rights reserved.</div>
    </div>
</div>
</body>
</html>