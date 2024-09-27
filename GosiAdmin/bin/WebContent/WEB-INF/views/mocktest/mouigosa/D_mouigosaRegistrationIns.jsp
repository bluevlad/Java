<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">

//저장
function checkParam() {

	if($("#MOCKNAME").val() == ''){
		alert("동형모의고사 명을 입력해주세요");
		$("#MOCKNAME").focus();
	}else if($('#MOCKLECCODE').val() == ''){
		alert("강의코드를 입력해주세요");
		$("#MOCKLECCODE").focus();
	}else if($('#MOCKPRFCODE').val() == ''){
		alert("강사코드를 입력해주세요");
		$("#MOCKPRFCODE").focus();
	}else if($('#QUESTIONNUM').val() == ''){
		alert("문제수를 입력해주세요");
		$("#QUESTIONNUM").focus();
	}else if($('#ENTRYNUM').val() == ''){
		alert("문제보기수를 입력해주세요");
		$("#ENTRYNUM").focus();
	}else if($('#EXAMTIME').val() == ''){
		alert("시험응시시간을 입력해주세요");
		$("#EXAMTIME").focus();
	}else{

		//var dataString = $("#popFrm").serialize(); // frm은 form 이름
		var formData = new FormData();

		formData.append("MOCKNAME", $("#MOCKNAME").val());
		formData.append("MOCKLECCODE", $("#MOCKLECCODE").val());
		formData.append("MOCKPRFCODE", $("#MOCKPRFCODE").val());
		formData.append("QUESTIONNUM", $("#QUESTIONNUM").val());
		formData.append("ENTRYNUM", $("#ENTRYNUM").val());
		formData.append("EXAMTIME", $("#EXAMTIME").val());
		formData.append("MOCKISUSE", $("input[name=MOCKISUSE]:checked").val());
       	formData.append("uploadFile1", $("#uploadFile1")[0].files[0]);
       	formData.append("uploadFile2", $("#uploadFile2")[0].files[0]);
       	
		$.ajax({
		     
		     type: "POST", 
		     url : '<c:url value="/mouigosa/reg/D_mouigosaRegistrationInsert.do"/>',
		     data: formData,
		     processData: false,
     	     contentType: false,
		     async : false,
		     success: function(RES) {
		      if($.trim(RES)=="Y"){           
		        alert("등록 되었습니다.");
		        location.href = "http://loginadmin.willbesgosi.net/mouigosa/reg/D_mouigosaRegistrationList.do?&MENU_ID=TM_003_008&TOP_MENU_ID=TM_003&MENUTYPE=TM_ROOT&L_MENU_NM=모의고사등록&MENU_NM=동형모의고사등록";
		        return;
		      }
		     },error: function(){
		      alert("등록 실패");
		      return;
		     }
		  });
	}
}

//취소
function goList() {
    $('#popFrm').attr('action','<c:url value="/mouigosa/reg/D_mouigosaRegistrationList.do"/>').submit();
}

// 숫자만입력
function fn_OnlyNumber(obj) {
    for (var i = 0; i < obj.value.length ; i++){
        chr = obj.value.substr(i,1);  
        chr = escape(chr);
        key_eg = chr.charAt(1);
        if (key_eg == "u"){
            key_num = chr.substr(i,(chr.length-1));   
            if((key_num < "AC00") || (key_num > "D7A3")) { 
                event.returnValue = false;
            }    
        }
    }
    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
        MoveFocus(obj);
    }else{
        alert("숫자만 입력해 주세요.");
        $(obj).val('');
        $(obj).focus();
        event.returnValue = false;
    }
}



</script>
</head>

<form id="popFrm" name="popFrm" method="post" enctype="multipart/form-data">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}">

<div id="content">
    <h2>
		● 모의고사등록 > <strong>동형모의고사 등록
    </h2>

    <!--테이블-->
    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th>동형모의고사 명</th>
            <td>
                <div class="item">
                <input id="MOCKNAME" name="MOCKNAME" type="text" class="i_text must" style="width:320px;"  value=""/>
                </div>
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>강의코드</th>
            <td style="text-align:left;">
                <div class="item">
                	<input id="MOCKLECCODE" name="MOCKLECCODE" type="text" class="i_text must" style="width:120px;"  value=""/>
                </div>
            </td>
        </tr>
        <tr>
            <th>강사코드</th>
            <td style="text-align:left;">
                <div class="item">
                	<input id="MOCKPRFCODE" name="MOCKPRFCODE" type="text" class="i_text must" style="width:120px;"  value=""/>
                </div>
            </td>
        </tr>
        <tr>
            <th>문제수</th>
            <td style="text-align:left;">
                <div class="item">
                	<input id="QUESTIONNUM" name="QUESTIONNUM" type="text" class="i_text must" style="width:50px;"  onkeyup="fn_OnlyNumber(this);" value=""/>&nbsp;문항
                </div>
            </td>
        </tr>
        <tr>
            <th>문제보기수</th>
            <td style="text-align:left;">
                <div class="item">
                	<input id="ENTRYNUM" name="ENTRYNUM" type="text" class="i_text must" style="width:50px;"  onkeyup="fn_OnlyNumber(this);" value=""/>&nbsp;개
                </div>
            </td>
        </tr>
        <tr>
            <th>시험응시시간</th>
            <td style="text-align:left;">
                <div class="item"><input id="EXAMTIME" name="EXAMTIME" type="text" alt="시험시간" class="i_text must number" style="width:3%;" maxlength="3"  onkeyup="fn_OnlyNumber(this);" value="" onkeyup="chk(this,'EXAMTIME')" onblur="chk(this,'EXAMTIME')"/>&nbsp;분</div>
            </td>
        </tr>
        <tr>
            <th>활성/비활성</th>
            <td style="text-align:left;">
                <div class="item">
					<input name="MOCKISUSE" id="MOCKISUSE" class="i_radio" value="0" type="radio" <c:if test="${empty searchMap.EXAMPERIODTYPE or searchMap.EXAMPERIODTYPE == '0' }">checked="checked"</c:if> /><label for="EXAMPERIODTYPE1">비활성</label>
                    <input name="MOCKISUSE" id="MOCKISUSE" class="i_radio" value="1" type="radio" <c:if test="${empty searchMap.EXAMPERIODTYPE or searchMap.EXAMPERIODTYPE == '1' }">checked="checked"</c:if> /><label for="EXAMPERIODTYPE1">활성</label>
                </div>
            </td>
        </tr>        		
        <tr>
			<th>문제PDF파일</th>
			<td style="text-align:left;">
				<div class="item" id="fileControl">
					<input title="레이블 텍스트" type="file" name="uploadFile1"  id="uploadFile1" />
				</div>
			</td>
		</tr>
		<tr>
			<th>해설PDF파일</th>
			<td style="text-align:left;">
				<div class="item" id="fileControl">
					<input title="레이블 텍스트" type="file" name="uploadFile2" id="uploadFile2"  />
				</div>
			</td>
		</tr>

        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:checkParam();">저장</a></li>
        <li><a href="javascript:goList();">취소</a></li>
    </ul>
    <!--//버튼-->

    </div>
</form>
<!--//팝업-->