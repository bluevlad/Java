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

		var formData = new FormData();

		formData.append("ITEMID", $("#ITEMID").val());
		formData.append("MOCKNAME", $("#MOCKNAME").val());
		formData.append("MOCKLECCODE", $("#MOCKLECCODE").val());
		formData.append("MOCKPRFCODE", $("#MOCKPRFCODE").val());
		formData.append("QUESTIONNUM", $("#QUESTIONNUM").val());
		formData.append("ORI_QUESTIONNUM", $("#ORI_QUESTIONNUM").val());
		formData.append("ENTRYNUM", $("#ENTRYNUM").val());
		formData.append("EXAMTIME", $("#EXAMTIME").val());
		formData.append("MOCKISUSE", $("input[name=MOCKISUSE]:checked").val());
		if($("#uploadFileNm1").val() != ''){
			formData.append("uploadFile1", "");
		}else{
			formData.append("uploadFile1", $("#uploadFile1")[0].files[0]);
		}
		
		if($("#uploadFileNm2").val() != ''){
			formData.append("uploadFile2", "");
		}else{
			formData.append("uploadFile2", $("#uploadFile2")[0].files[0]);
		}
       	
       
		$.ajax({
		     
		     type: "POST", 
		     url : '<c:url value="/mouigosa/reg/D_mouigosaRegistrationUpdate.do"/>',
		     data: formData,
		     processData: false,
     	     contentType: false,
		     async : false,
		     success: function(RES) {
		      if($.trim(RES)=="Y"){           
		        alert("수정 되었습니다.");
		        location.reload();
		        return;
		      }
		     },error: function(){
		      alert("수정 실패");
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

function deleteAttachFile(itemid ,fileno, flag){
	
	var checkparam = "ITEMID=" + itemid + "&FILENO=" + fileno + "&FLAG=" + flag ;

	$.ajax({
	  type: "GET", 
	  url : '<c:url value="/mouigosa/reg/D_AttachDeleteFile.do"/>?' + checkparam,
	  dataType: "text",  
	  async : false,
	  success: function(RES) {
	   if($.trim(RES)=="Y"){
	     alert("삭제되었습니다.");
	     location.reload();
	   }
	  },error: function(){
	   alert("삭제 실패");
	   return;
	  }
	 });
	
}

//등록
function addPopup(ITEMID,QNUM) {
    var go_url = '<c:url value="/mouigosa/reg/D_createQuestionMouigosa.pop"/>?'
                + 'TOP_MENU_ID=' + $("#TOP_MENU_ID").val()
                + '&MENU_ID=' + $("#MENU_ID").val()
                + '&MENUTYPE=' + $("#MENUTYPE").val()
                + '&L_MENU_NM=' + $("#L_MENU_NM").val()
                + '&ITEMID=' + ITEMID
                + '&QUESTIONNUMBER=' + QNUM 
                + '&ENTRYNUM=' + $("#ENTRYNUM").val();

        window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=782,height=382');

        //window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=780,height=597');

}

//수정
function editPopup(ITEMID,QNUM) {
    var go_url = '<c:url value="/mouigosa/reg/D_mouigosaQuestionUpd.pop"/>?'
                + 'TOP_MENU_ID=' + $("#TOP_MENU_ID").val()
                + '&MENU_ID=' + $("#MENU_ID").val()
                + '&MENUTYPE=' + $("#MENUTYPE").val()
                + '&L_MENU_NM=' + $("#L_MENU_NM").val()
                + '&ITEMID=' + ITEMID
                + '&QUESTIONNUMBER=' + QNUM 
                + '&ENTRYNUM=' + $("#ENTRYNUM").val();

        window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=790,height=715');

        //window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=778,height=602');
  
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
    <input type="hidden" id="ITEMID" name="ITEMID" value="${searchMap.ITEMID}">
    <input type="hidden" id="uploadFileNm1" name="uploadFileNm1" value="${searchMap2.FILENAME}">
    <input type="hidden" id="uploadFileNm2" name="uploadFileNm2" value="${searchMap3.FILENAME}">
    <input type="hidden" id="ORI_QUESTIONNUM" name="ORI_QUESTIONNUM" value="${searchMap.QUESTIONNUM}">

<div id="content">
    <h2>
        ● 모의고사등록 > <strong>동형모의고사 수정
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
                <input id="MOCKNAME" name="MOCKNAME" type="text" class="i_text must" style="width:320px;"  value="${searchMap.ITEMNM}"/>
                </div>
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>강의코드</th>
            <td style="text-align:left;">
                <div class="item">
                	<input id="MOCKLECCODE" name="MOCKLECCODE" type="text" class="i_text must" style="width:120px;"  value="${searchMap.LECCODE}"/>
                </div>
            </td>
        </tr>
        <tr>
            <th>강사코드</th>
            <td style="text-align:left;">
                <div class="item">
                	<input id="MOCKPRFCODE" name="MOCKPRFCODE" type="text" class="i_text must" style="width:120px;"  value="${searchMap.PROFCODE}"/>
                </div>
            </td>
        </tr>
        <tr>
            <th>문제수</th>
            <td style="text-align:left;">
                <div class="item">
                	<input id="QUESTIONNUM" name="QUESTIONNUM" type="text" class="i_text must" style="width:50px;"  onkeyup="fn_OnlyNumber(this);" value="${searchMap.QUESTIONNUM}"/>&nbsp;문항
                </div>
            </td>
        </tr>
        <tr>
            <th>문제보기수</th>
            <td style="text-align:left;">
                <div class="item">
                	<input id="ENTRYNUM" name="ENTRYNUM" type="text" class="i_text must" style="width:50px;"  onkeyup="fn_OnlyNumber(this);" value="${searchMap.ENTRYNUM}"/>&nbsp;개
                </div>
            </td>
        </tr>
        <tr>
            <th>시험응시시간</th>
            <td style="text-align:left;">
                <div class="item"><input id="EXAMTIME" name="EXAMTIME" type="text" alt="시험시간" class="i_text must number" style="width:3%;" maxlength="3"  onkeyup="fn_OnlyNumber(this);" value="${searchMap.EXAMTIME}" onkeyup="chk(this,'EXAMTIME')" onblur="chk(this,'EXAMTIME')"/>&nbsp;분</div>
            </td>
        </tr>
        <tr>
            <th>활성/비활성</th>
            <td style="text-align:left;">
                <div class="item">
					<input name="MOCKISUSE" id="MOCKISUSE" class="i_radio" value="0" type="radio" <c:if test="${searchMap.OPENSTATE == '0' }">checked="checked"</c:if> /><label for="EXAMPERIODTYPE1">비활성</label>
                    <input name="MOCKISUSE" id="MOCKISUSE" class="i_radio" value="1" type="radio" <c:if test="${searchMap.OPENSTATE == '1' }">checked="checked"</c:if> /><label for="EXAMPERIODTYPE1">활성</label>
                </div>
            </td>
        </tr>        		
        <tr>
			<th>문제PDF파일</th>
			<td style="text-align:left;">
				<div class="item" id="fileControl">
					<c:if test="${searchMap2.FILENAME ne '' }">
						<span style="cursor:pointer" id="fileThumbNail_${searchMap2.QUESTIONFID }" onclick="deleteAttachFile('${searchMap2.ITEMID}','${searchMap2.QUESTIONFID }', '0')">${searchMap2.FILENAME} - 삭제</span><br/>
					</c:if>
					<c:if test="${searchMap2.FILENAME eq '' }">
						<input title="레이블 텍스트" type="file" name="uploadFile1"  id="uploadFile1" />
					</c:if>
					
				</div>
			</td>
		</tr>
		<tr>
			<th>해설PDF파일</th>
			<td style="text-align:left;">
				<div class="item" id="fileControl">
					<c:if test="${searchMap3.FILENAME ne '' }">
                    	<span style="cursor:pointer" id="fileThumbNail_${searchMap3.ANSWERFID }" onclick="deleteAttachFile('${searchMap3.ITEMID}','${searchMap3.ANSWERFID }', '1')">${searchMap3.FILENAME} - 삭제</span><br/>
                	</c:if>
                	<c:if test="${searchMap3.FILENAME eq '' }">
                		<input title="레이블 텍스트" type="file" name="uploadFile2" id="uploadFile2"  />
                	</c:if>             
				</div>
			</td>
		</tr>

        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:checkParam();">수정</a></li>
        <li><a href="javascript:goList();">목록</a></li>
    </ul>
    <!--//버튼-->

    <!--테이블-->
    <table class="table02">
        <caption></caption>
        <colgroup>
        <col width="8%">
        <col width="20%">
        <col width="20%">
        <col width="8%">
        <col width="*">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">문제번호</th>
            <th scope="col">문제</th>
            <th scope="col">해설</th>
            <th scope="col">정답</th>
            <th scope="col">영역</th>
            <th scope="col">난이도</th>
            <th scope="col">저장</th>
        </tr>
        </thead>
        <tbody>
      <c:if test="${not empty poollist}">
        <c:forEach items="${poollist}" var="list" varStatus="status">
        <tr>
            <td>${list.QUESTIONNUMBER}</td>
            <td><a href="#" onclick="PrintPopup('${list.ITEMID}','${list.QUESTIONNUMBER}','1')">${searchMap.EXAMROUND}회 ${list.QUESTIONNUMBER}번</a></td>
            <td><a href="#" onclick="PrintPopup('${list.ITEMID}','${list.QUESTIONNUMBER}','2')">${searchMap.EXAMROUND}회 ${list.QUESTIONNUMBER}번</a></td>
            <td>${list.ANSWERNUMBER}</td>
            <td>${list.QUESTIONRANGENAME}</td>
            <td>${list.LEVELDIFFICULTYNAME}</td>
            <td>
            <c:if test="${list.ANSWERNUMBER eq null}"><span class="btn_pack small"><button type="button" onclick="addPopup('${list.ITEMID}','${list.QUESTIONNUMBER}')">등록</button></span></c:if>
            <c:if test="${list.ANSWERNUMBER ne null}"><span class="btn_pack small"><button type="button" onclick="editPopup('${list.ITEMID}','${list.QUESTIONNUMBER}')">수정</button></span></c:if>
            </td>
        </tr>
        </c:forEach>
      </c:if>
      <c:if test="${empty poollist}">
        <tr bgColor=#ffffff align=center>
            <td colspan="8">데이터가 존재하지 않습니다.</td>
        </tr>
      </c:if>
        </tbody>
    </table>
    
    </div>
</form>
<!--//팝업-->