<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<style>
.pop{position:relative;width:770px;min-width:770px;}
.table01{position:relative;width:770px;min-width:770px;}
.table02{position:relative;width:770px;min-width:770px;}
</style>
<script type="text/javascript">
var sts = "${sts}"; //등록:I, 수정:U
var STSCNT = "${searchMap.STSCNT}"; //실제 등록된 문제 건수

var ITEMID = "${searchMap.ITEMID}"; //문제은행구분번호
var QUESTIONNUMBER = "${searchMap.QUESTIONNUMBER}"; //문제은행구분번호
var EXAMROUND = "${searchMap.EXAMROUND}"; //회차
var SUBJECT_CD = "${searchMap.SUBJECT_CD}"; //과목코드
var ENTRYNUM = "${searchMap.ENTRYNUM}"; //지문수
var QUESTIONREGISTRATIONOPTION = "${searchMap.QUESTIONREGISTRATIONOPTION}"; //문제등록옵션
var QUESTIONNUM = "${searchMap.QUESTIONNUM}"; //문제수
var QUESTIONFILEPATH = "${searchMap.QUESTIONFILEPATH}";
var ANSWEREXPLAINFILEPATH = "${searchMap.ANSWEREXPLAINFILEPATH}";

var S_EXAMYEAR = "${searchMap.S_EXAMYEAR}";
var S_EXAMROUND = "${searchMap.S_EXAMROUND}";
var S_searchFlag = "${searchMap.S_searchFlag}";
var S_searchKeyWord = "${searchMap.S_searchKeyWord}";
var S_currentPage = "${searchMap.S_currentPage}";
var S_pageRow = "${searchMap.S_pageRow}";

window.onload = function () {

    $("form input[name='QUESTIONPATTERN']").change(function() {       
        $("input:radio[name='ANSWERNUMBER']").each(function() {
            this.checked = false;
        });
        $("input:checkbox[name='ANSWERNUMBER']").each(function() {
            this.checked = false;
        });
    });
}

function fileCheck(obj,obj2,obj3){
    // 파일 확장자 체크하기. 엑셀 파일만 업로드 가능.
   //var obj = document.getElementById('uploadFile');
   // 파일 경로를 obj로 받아온다.
   var lastIndex = -1;
   var filePaht = "";
   filePath = obj.value;
   lastIndex = filePath.lastIndexOf('.');
   extension = filePath.substring( lastIndex+1, filePath.len );
   if(!((extension.toLowerCase() == obj2)) && !((extension.toLowerCase() == obj3)) && extension != "") {
       return false;
   }

   return true;
}

//등록
function checkParam() {
	
    var obj;
    var obj2 = "jpg";
    var obj3 = "png";
    
	if ($("#imgFile1").val() == "") {
        alert("문제 이미지를 등록 해주세요.");
        return;
    }
	if ($("#imgFile2").val() == "") {
        alert("해설 이미지를 등록 해주세요.");
        return;
    }
	if($("#DAP").val() == ""){
		alert("정답을 체크 해주세요.");
		return;
	}
	
	obj = document.getElementById('imgFile1');
	if(fileCheck(obj,obj2,obj3) == false){
        alert('확장자가 jpg, png 파일만 첨부가능 합니다.');
        return;
    }
	obj = document.getElementById('imgFile2');
	if(fileCheck(obj,obj2,obj3) == false){
        alert('확장자가 jpg, png 파일만 첨부가능 합니다.');
        return;
    }
	
	var formData = new FormData();

	//formData.append("MOCKNAME", $("#MOCKNAME").val());
	formData.append("ITEMID", $("#ITEMID").val());
	formData.append("DAP", $("#DAP").val());
	formData.append("QUESTIONNUMBER", $("#QUESTIONNUMBER").val());
   	formData.append("imgFile1", $("#imgFile1")[0].files[0]);
   	formData.append("imgFile2", $("#imgFile2")[0].files[0]);
   	
   	if($("#imgFile3").val() != ''){
   		formData.append("imgFile3", $("#imgFile3")[0].files[0]);
   	}
   	
	$.ajax({
	     
	     type: "POST", 
	     url : '<c:url value="/mouigosa/reg/mouigosaQuestionIns.do"/>',
	     data: formData,
	     processData: false,
	     contentType: false,
	     async : false,
	     success: function(RES) {
	      if($.trim(RES)=="Y"){           
	        alert("등록 되었습니다.");
	        window.opener.location.reload();
	        self.close();
	        return;
	      }
	     },error: function(){
	      alert("등록 실패");
	      return;
	     }
	  });
  	
}

// 파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
}

function fn_dap_chk(i){
	$("#DAP").val(i);
}
</script>
</head>

<!--팝업-->
<form id="popFrm" name="popFrm" enctype="multipart/form-data" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

<input type="hidden" id="ITEMID" name="ITEMID" value="${ITEMID}" />
<input type="hidden" id="USER_ID" name="USER_ID" value="${AdmUserInfo.USER_ID}" />
<input type="hidden" id="DAP" name="DAP"  value=""/>
<input type="hidden" id="QUESTIONNUMBER" name="QUESTIONNUMBER"  value="${QUESTIONNUMBER }"/>
    <div id="content_pop" style="width:770px;position:relative;">
    <h2>● <strong>문제 등록</strong></h2>

    <!--테이블-->
    <table class="table01">
        <caption>
        </caption>
        <colgroup>
        <col width="15%">
        <col width="85%">
        </colgroup>

    </table>
    <!--//테이블-->

    <!--테이블-->
    <table class="table02">
        <colgroup>
            <col width="25%">
            <col width="75%">
        </colgroup>
        <tbody>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제번호</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    ${QUESTIONNUMBER }
                </div>
            </td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제</th>
            <td style="text-align:left;"><div class="item"><input id="imgFile1" name="imgFile1" title="레이블 텍스트" type="file" /></div></td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">해설</th>
            <td style="text-align:left;"><div class="item"><input id="imgFile2" name="imgFile2" title="레이블 텍스트" type="file" /></div></td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제 유형</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <input id="QUESTIONPATTERN" name="QUESTIONPATTERN" class="i_radio must" value="A" title="문제 유형을 선택해 주세요." type="radio" checked="checked" /><label for="QUESTIONPATTERN1">단일선택형</label>
                    <!--<input id="QUESTIONPATTERN" name="QUESTIONPATTERN" class="i_radio" value="B" type="radio" <c:if test="${searchMap.QUESTIONPATTERN == 'B' }">checked="checked"</c:if> /><label for="QUESTIONPATTERN2">다지선택형</label>
                    <input id="QUESTIONPATTERN" name="QUESTIONPATTERN" class="i_radio" value="C" type="radio" <c:if test="${searchMap.QUESTIONPATTERN == 'C' }">checked="checked"</c:if> /><label for="QUESTIONPATTERN3">단답형</label>
                    <input id="QUESTIONPATTERN" name="QUESTIONPATTERN" class="i_radio" value="D" type="radio" <c:if test="${searchMap.QUESTIONPATTERN == 'D' }">checked="checked"</c:if> /><label for="QUESTIONPATTERN4">서술형</label>-->
                </div>
            </td>
        </tr>
        <tr id="multi-select">
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">정답</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                	﻿<c:forEach var="i" begin="1" end="${ENTRYNUM }">
						<input id="ANSWERNUMBER${i }" name="ANSWERNUMBER" value="${i }" title="정답을 선택해 주세요." type="radio"  onclick="javascript:fn_dap_chk('${i}')"/><label for="ANSWERNUMBER${i }">${i }</label>                	
                	</c:forEach>
                </div>
            </td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">유사문제</th>
            <td style="text-align:left;"><div class="item"><input id="imgFile3" name="imgFile3" title="유사문제파일" type="file" /></div></td>
        </tr>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:checkParam();">저장</a></li>
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->
    <!--<a class="clse" href="#" onclick="self.close()"> <img alt="닫기" src="<c:url value="/resources/img/common/btn_close_layer3.gif"/>" width="19" height="19"></a> -->

    </div>
</form>
<!--//팝업-->
