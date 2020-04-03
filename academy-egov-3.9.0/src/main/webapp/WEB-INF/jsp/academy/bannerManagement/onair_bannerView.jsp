<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %><head>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("SUBJECT_OPEN_DATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	
	window.onload = function(){
		
		if("${view.ATTACH_TYPE1}" == 'M'){
			jQuery('#UPLOAD1').css("display", "none");
			jQuery('#ATTACH_FILE1').css("display", "none");
			
			jQuery('#LINK1').css("display", "block");
			jQuery('#ATTACH_MOVIE_LINK1').css("display", "block");
		}else if("${view.ATTACH_TYPE2}" == 'M'){
			jQuery('#UPLOAD2').css("display", "none");
			jQuery('#ATTACH_FILE2').css("display", "none");
			
			jQuery('#LINK2').css("display", "block");
			jQuery('#ATTACH_MOVIE_LINK2').css("display", "block");
		}

		setLecDate2();
	}
});

function addForm(){
    var addedFormDiv = document.getElementById("addedFormDiv");
    var cnt=parseInt($("#CNT").val());
    if(cnt >= 4){
    	alert("최대 3개까지 가능합니다.");
    }else{
        var str = "";
        str+="<input type=text name=TEXT"+cnt+ " id=TEXT"+cnt+" size=100% maxlength=45>&nbsp;&nbsp;<input type=button value=삭제 onclick=delForm()><br>";
        // 추가할 폼(에 들어갈 HTML)
        var addedDiv = document.createElement("div"); // 폼 생성
        addedDiv.id = "added_"+cnt; // 폼 Div에 ID 부여 (삭제를 위해)
        addedDiv.innerHTML  = str; // 폼 Div안에 HTML삽입
        addedFormDiv.appendChild(addedDiv); // 삽입할 DIV에 생성한 폼 삽입

        cnt++;
        document.frm.CNT.value=cnt;
        // 다음 페이지에 몇개의 폼을 넘기는지 전달하기 위해 히든 폼에 카운트 저장
    }

}

function delForm(){
	var cnt=parseInt($("#CNT").val());
    var addedFormDiv = document.getElementById("addedFormDiv");
    if(cnt >1){ // 현재 폼이 두개 이상이면
               var addedDiv = document.getElementById("added_"+(--cnt));
               // 마지막으로 생성된 폼의 ID를 통해 Div객체를 가져옴
               addedFormDiv.removeChild(addedDiv); // 폼 삭제 
    }else{ // 마지막 폼만 남아있다면
               document.baseForm.reset(); // 폼 내용 삭제
    }
    
    document.frm.CNT.value=cnt;
}

var total;
var karr;

function setLecDate2() {

	var result = "";
	total = 0;
	karr = 0;
	
	var f = document.frm;
	
	if($("#LEC_SCHEDULE").val() == ""){
		alert("먼저 강의회차를 입력하세요");
		return;
	}           
	if($("#SUBJECT_OPEN_DATE").val() == ""){
		alert("먼저 개강일자를 입력하세요");
		return;
	}

	var t = $(".weekcls:checked").length; 
	
	if(t<1) {
		alert('요일을 입력하세요.');
	} else {
		
		var Months = new Array(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		
		//lec_start_date 강의시작일
		var syy = $("#SUBJECT_OPEN_DATE").val().substr(0,4); //연도추출
		var smm = $("#SUBJECT_OPEN_DATE").val().substr(4,2); // 월추출
		
		var yy = parseInt(syy,10); // 연도2자리
		var mm = parseInt(smm,10); // 월2자리

		result += "<table border='0' cellpadding='0' cellspacing='0'><tr>";
		
		var ty = 0;
		var tm = 0;
		var i = 0;
		
		//lecture_amount 강의총회차
		while( parseInt($("#LEC_SCHEDULE").val(),10) > total) {
			ty = yy + parseInt((mm+i-1)/12);
			tm = Months[((mm+i)%12)];
			
			result += "<td style='padding:5px;' valign='top'>";
			result += Calendar2(ty,tm);
			result += "</td>";
			i++;
		}
		
		result += "</tr></table>";
		
		var ele = document.getElementById("cal_vw");
		ele.innerHTML = result;
		ele.style.display = "block";
	}
}

function Calendar2( Year, Month ) {

	var days = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	var weekDay = new Array("일", "월", "화", "수", "목", "금", "토");

	firstDay = new Date(Year,Month-1,1).getDay();
	
	days[1] = (((Year % 4 == 0) && (Year % 100 != 0)) || (Year % 400 == 0)) ? 29 : 28;
		
	var output_string = "";
	output_string += "<table border='0' cellpadding='0' cellspacing='0' width='246' class='tdTable' >";
	output_string += "<tr><td align='center' style='text-align:center;'><b>";
	output_string += Year + " / " + Month;
	output_string += "</b></td></tr>";
	output_string += "</table>";
	
	output_string += "<table border='0' cellpadding='0' cellspacing='1' bgcolor='#999999' class='tdTable' >";
	output_string += "<tr align='center' bgcolor='#dadada' height='25'> ";
	for (var dayNum= 0; dayNum <= 6; dayNum++) {
		output_string += "<td width='34'>" + weekDay[dayNum] + "</td>";
	}
	output_string += "</tr>";

	var kMonth = (Month < 10) ? "0"+Month : Month;
		
	var nDay = 1;
	
	for(var i=0; i<6; i++) {
		output_string += "<tr align='center'>";
		for(var j=0; j<7; j++) {
			tarr = i*7+j;
			
			if(firstDay <= tarr && days[Month-1] >= nDay ) {
				var kDay = (nDay < 10) ? "0"+nDay : nDay;
				
				var bg_color = "";
				var frm_value = "";
				
				<c:forEach items="${viewdatelist}" var="item" varStatus="loop">
					if( (Year +""+ kMonth +""+ kDay) == "${item.LEC_DATE}" ){
						bg_color ="yellow";
						frm_value = Year +""+ kMonth +""+ kDay;
						total++;								
					}
				</c:forEach>					
				if(frm_value == ""){
					bg_color ="#ffffff";
				}				
				
				output_string += "<td id='"+ (Year +""+ kMonth +""+ kDay) +"' style='background-color:"+ bg_color +";cursor:hand;cursor:pointer;' width='24' height='20' ";
				output_string += " onClick=\"javascript:chkDay('"+ parseInt((Year +""+ kMonth +""+ kDay),10)  +"', "+ karr +");\">";
				output_string += nDay
				output_string += "<input type='hidden' name='SAVDAY' value='"+ frm_value +"'>";
				output_string += "</td>";
				
				nDay++;
				karr++;
			} else {
				output_string += "<td bgcolor='#ffffff' width='24' height='20'>"+ "&nbsp;" +"</td>";
			}
		}
	}
	
	output_string += "</tr>";
	output_string += "</table>";
	
	return output_string;
	
}

function fn_List(){
    $("#frm").attr("action", "<c:url value='/bannerManagement/OnAir_Banner_Lst.do' />");
    $("#frm").submit();
}

//삭제
function fn_Delete(){

		if(confirm("정말 삭제하시겠습니까?")){
			if($("#REAL_FILE_NAME").val() != ""){
				$("#ATTACH_FILE_IMG1_DEL").val('Y');
				$("#ATTACH_FILENM_IMG1_DEL").val($("#REAL_FILE_NAME").val());
			}
			if($("#REAL_FILE_NAME2").val() != ""){
				$("#ATTACH_FILE_IMG2_DEL").val('Y');
				$("#ATTACH_FILENM_IMG2_DEL").val($("#REAL_FILE_NAME2").val());
			}
			$("#frm").attr("action","<c:url value='/bannerManagement/OnAir_delete.do' />");
			$("#frm").submit();
		}			
}

//등록
function fn_Submit(){
	    
    if($.trim($("#LEC_SCHEDULE").val())==""){
        alert("회차를 입력해주세요.");
        $("#LEC_SCHEDULE").focus();
        return;
    }
    
 	if($.trim($("#SUBJECT_OPEN_DATE").val()) == ""){
 		alert("게시일자를 입력하세요.");
 		$("#SUBJECT_OPEN_DATE").focus();
        return;
 	}

	var WEEKCHK = "";
	$(".weekcls").each(function(idx,el){		
		if($(this).attr("checked")=="checked"){
			WEEKCHK += $(this).val();
		}
		if($(this).attr("name") != "WEEK7"){
			WEEKCHK += ",";
		}
	});
 	
	if( $("#CLECS").val() != $("#LEC_SCHEDULE").val()  ){
 		alert("회차가 변경되었습니다. 적용버튼을 눌러 날짜를 확인해주세요");
 		return;
 	}
 	if( $("#COPENDATE").val() != $("#SUBJECT_OPEN_DATE").val() ){
 		alert("시작일자가 변경되었습니다. 적용버튼을 눌러 날짜를 확인해주세요");
 		return;
 	}
 	if( $("#CDATE").val() != WEEKCHK  ){
 		alert("강의요일이 변경되었습니다. 적용버튼을 눌러 날짜를 확인해주세요");
 		return;
 	}
 	
 	if($(':radio[name="TIME_TYPE"]:checked').val() == 'I'){ 		
 	
	 	if($("select[name=S_TIME_HH]").val() == '') {
	        alert("시작시간을 선택해 주세요.");
	        $("#S_TIME_HH").focus();
	        return;
	    }
	 	
	 	if($("select[name=S_TIME_MM]").val() == '') {
	        alert("시작시간을 선택해 주세요.");
	        $("#S_TIME_MM").focus();
	        return;
	    }
	 	
	 	if($("select[name=E_TIME_HH]").val() == '') {
	        alert("종료시간을 선택해 주세요.");
	        $("#E_TIME_HH").focus();
	        return;
	    }
	 	
	 	if($("select[name=E_TIME_MM]").val() == '') {
	        alert("종료시간을 선택해 주세요.");
	        $("#E_TIME_MM").focus();
	        return;
	    }
 	}
 	
 	if($.trim($("#SUBJECT_TITLE").val()) == ""){
 		alert("강좌명을 입력하세요.");
 		$("#SUBJECT_TITLE").focus();
        return;
 	}
 	
	var TEXT = "";
	
	for(var i=1; i<$("#CNT").val(); i++ ){
		if($.trim($("#TEXT"+i).val()) == ""){
	 		alert("상단 텍스트를 입력하세요.");
	 		$("#TEXT"+i).focus();
	        return;
	 	}else{
	 		
	 		TEXT = TEXT + $.trim($("#TEXT"+i).val()) + "/";
	 		$("#TOP_TEXT").val(TEXT);
	 	}
	}
 	
    if($("select[name=TEACHER_NM]").val() == '') {
        alert("교수명을 선택해 주세요.");
        $("#TEACHER_NM").focus();
        return;
    }
    
    if($.trim($("#TEACHER_ADVICE").val()) == ""){
 		alert("교수 한마디를 입력해주세요.");
 		$("#TEACHER_ADVICE").focus();
        return;
 	}
    
    if($(':radio[name="ATTACH_FILE_TYPE1"]:checked').val() == 'M'){
    	if($("#ATTACH_MOVIE_LINK1").val() == ''){
    		alert("영상링크 주소를 입력해주세요.");
    		$("#ATTACH_MOVIE_LINK1").focus();
    		return;
		}
    }
    
	if($(':radio[name="ATTACH_FILE_TYPE2"]:checked').val() == 'M'){
		if($("#ATTACH_MOVIE_LINK2").val() == ''){
    		alert("영상링크 주소를 입력해주세요.");
    		$("#ATTACH_MOVIE_LINK2").focus();
    		return;
		}
    	
    }
	
	if($("select[name=CLASSROOM]").val() == '') {
        alert("강의실을 선택해 주세요.");
        $("#CLASSROOM").focus();
        return;
    }
    
    if(confirm("ON-AIR 배너를 수정하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/bannerManagement/OnAir_update.do' />");
        $("#frm").submit();
    }
}

function setLecDate() {
	$("#COPENDATE").val($("#SUBJECT_OPEN_DATE").val());
	$("#CLECS").val($("#LEC_SCHEDULE").val());
	
	var WEEKCHK = "";
	$(".weekcls").each(function(idx,el){		
		if($(this).attr("checked")=="checked"){
			WEEKCHK += $(this).val();
		}
		if($(this).attr("name") != "WEEK7"){
			WEEKCHK += ",";
		}
	});
	$("#CDATE").val(WEEKCHK);
	
	var result = "";
	total = 0;
	karr = 0;
	
	var f = document.frm;
	
	if($("#LEC_SCHEDULE").val() == ""){
		alert("먼저 회차를 입력하세요");
		return;
	}           
	if($("#SUBJECT_OPEN_DATE").val() == ""){
		alert("먼저 시작일자를 입력하세요");
		return;
	}

	var t = $(".weekcls:checked").length; 
	
	if(t<1) {
		alert('요일을 입력하세요.');
	} else {

		var Months = new Array(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		
		//lec_start_date 강의시작일
		var syy = $("#SUBJECT_OPEN_DATE").val().substr(0,4); //연도추출
		var smm = $("#SUBJECT_OPEN_DATE").val().substr(4,2); // 월추출

		var yy = parseInt(syy,10); // 연도2자리
		var mm = parseInt(smm,10); // 월2자리

		result += "<table border='0' cellpadding='0' cellspacing='0'><tr>";
		
		var ty = 0;
		var tm = 0;
		var i = 0;

		//lecture_amount 강의총회차
		while( parseInt($("#LEC_SCHEDULE").val(),10) > total) {
			ty = yy + parseInt((mm+i-1)/12);
			tm = Months[((mm+i)%12)];
			
			result += "<td style='padding:5px;' valign='top'>";
			result += Calendar(ty,tm);
			result += "</td>";
			i++;
		}
		
		result += "</tr></table>";

		var ele = document.getElementById("cal_vw");
		ele.innerHTML = result;
		ele.style.display = "block";
	}
}

function Calendar( Year, Month ) {
	var days = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	var weekDay = new Array("일", "월", "화", "수", "목", "금", "토");

	firstDay = new Date(Year,Month-1,1).getDay();
	
	days[1] = (((Year % 4 == 0) && (Year % 100 != 0)) || (Year % 400 == 0)) ? 29 : 28;
		
	var output_string = "";
	output_string += "<table border='0' cellpadding='0' cellspacing='0' width='246' class='tdTable' >";
	output_string += "<tr><td align='center' style='text-align:center;'><b>";
	output_string += Year + " / " + Month;
	output_string += "</b></td></tr>";
	output_string += "</table>";
	
	output_string += "<table border='0' cellpadding='0' cellspacing='1' bgcolor='#999999' class='tdTable' >";
	output_string += "<tr align='center' bgcolor='#dadada' height='25'> ";
	for (var dayNum= 0; dayNum <= 6; dayNum++) {
		output_string += "<td width='34'>" + weekDay[dayNum] + "</td>";
	}
	output_string += "</tr>";

	var kMonth = (Month < 10) ? "0"+Month : Month;
		
	var nDay = 1;
	
	for(var i=0; i<6; i++) {
		output_string += "<tr align='center'>";
		for(var j=0; j<7; j++) {
			tarr = i*7+j;
			
			if(firstDay <= tarr && days[Month-1] >= nDay ) {
				var kDay = (nDay < 10) ? "0"+nDay : nDay;
				
				var bg_color = "";
				var frm_value = "";
				if (
					$(".weekcls")[j].checked && 
					parseInt($("#LEC_SCHEDULE").val(),10) > total &&
					parseInt((Year +""+ kMonth +""+ kDay),10)  >= parseInt($("#SUBJECT_OPEN_DATE").val(),10)
					) {
					bg_color ="yellow";
					frm_value = Year +""+ kMonth +""+ kDay;					
					total++;
					
				} else {
					bg_color ="#ffffff";
					frm_value = "";
				}
				output_string += "<td id='"+ (Year +""+ kMonth +""+ kDay) +"' style='background-color:"+ bg_color +";cursor:hand;cursor:pointer;' width='24' height='20' ";
				output_string += " onClick=\"javascript:chkDay('"+ parseInt((Year +""+ kMonth +""+ kDay),10)  +"', "+ karr +");\">";
				output_string += nDay
				output_string += "<input type='hidden' name='SAVDAY' value='"+ frm_value +"'>";
				output_string += "</td>";
				
				nDay++;
				karr++;
			} else {
				output_string += "<td bgcolor='#ffffff' width='24' height='20'>"+ "&nbsp;" +"</td>";
			}
		}
	}
	
	output_string += "</tr>";
	output_string += "</table>";
	
	return output_string;
	
}

function chkDay(clkDay, arr) {

	var f = document.frm;
	var ele = document.getElementById(clkDay);
	if( f.SAVDAY[arr].value.length == 0 ) {
		ele.style.backgroundColor = "yellow";
		f.SAVDAY[arr].value = clkDay;
		
	} else {
		ele.style.backgroundColor = "#ffffff";
		f.SAVDAY[arr].value = "";
	}
	
	var t = 0;
	for(var i=0; i<f.SAVDAY.length; i++) {
		if( f.SAVDAY[i].value.length > 0 ) {
			t++;
		}
	}
	$("#LEC_SCHEDULE").val(t);
}

function fn_upload_link(val, no){

		if(val == "I" && no == "1"){
			if($("#ATTACH_MOVIE_LINK1").val() != ""){
				if(confirm("영상링크가 초기화 됩니다. 변경 하시겠습니까?")){
					$("#ATTACH_MOVIE_LINK1").val('')
					jQuery('#UPLOAD1').css("display", "block");
					jQuery('#ATTACH_FILE1').css("display", "block");
					
					jQuery('#LINK1').css("display", "none");
					jQuery('#ATTACH_MOVIE_LINK1').css("display", "none");
				}else{
					$("input:radio[name='ATTACH_FILE_TYPE1']:radio[value='M']").attr("checked",true);
					$("input:radio[name='ATTACH_FILE_TYPE1']:radio[value='I']").attr("checked",false);
				}
			}else{
				jQuery('#UPLOAD1').css("display", "block");
				jQuery('#ATTACH_FILE1').css("display", "block");
				
				jQuery('#LINK1').css("display", "none");
				jQuery('#ATTACH_MOVIE_LINK1').css("display", "none");
			}

		}else if(val == "M" && no == "1"){
			if($("#REAL_FILE_NAME").val() != ""){
				if(confirm("이미지가 삭제 됩니다. 변경 하시겠습니까?")){
					
					$("#listThumImg1").html('');
					$("#ATTACH_FILE_IMG1_DEL").val('Y');
					$("#ATTACH_FILENM_IMG1_DEL").val($("#REAL_FILE_NAME").val());
					$("#FILE_PATH").val('');
					$("#FILE_NAME").val('');
					$("#REAL_FILE_NAME").val('');
					
					jQuery('#UPLOAD1').css("display", "none");
					jQuery('#ATTACH_FILE1').css("display", "none");
					
					jQuery('#LINK1').css("display", "block");
					jQuery('#ATTACH_MOVIE_LINK1').css("display", "block");
					
				}else{
					$("input:radio[name='ATTACH_FILE_TYPE1']:radio[value='I']").attr("checked",true);
					$("input:radio[name='ATTACH_FILE_TYPE1']:radio[value='M']").attr("checked",false);
				}

			}else{
				jQuery('#UPLOAD1').css("display", "none");
				jQuery('#ATTACH_FILE1').css("display", "none");
				
				jQuery('#LINK1').css("display", "block");
				jQuery('#ATTACH_MOVIE_LINK1').css("display", "block");
			}
			
		}else if(val == "I" && no == "2"){
			
			if($("#ATTACH_MOVIE_LINK2").val() != ""){
				if(confirm("영상링크가 초기화 됩니다. 변경 하시겠습니까?")){
					$("#ATTACH_MOVIE_LINK2").val('')
					jQuery('#UPLOAD2').css("display", "block");
					jQuery('#ATTACH_FILE2').css("display", "block");
					
					jQuery('#LINK2').css("display", "none");
					jQuery('#ATTACH_MOVIE_LINK2').css("display", "none");
				}else{
					$("input:radio[name='ATTACH_FILE_TYPE2']:radio[value='M']").attr("checked",true);
					$("input:radio[name='ATTACH_FILE_TYPE2']:radio[value='I']").attr("checked",false);
				}
			}else{
				jQuery('#UPLOAD2').css("display", "block");
				jQuery('#ATTACH_FILE2').css("display", "block");
				
				jQuery('#LINK2').css("display", "none");
				jQuery('#ATTACH_MOVIE_LINK2').css("display", "none");
			}
		
		}else if(val == "M" && no == "2"){
			
			if($("#REAL_FILE_NAME2").val() != ""){
				if(confirm("이미지가 삭제 됩니다. 변경 하시겠습니까?")){
					
					$("#listThumImg2").html('');
					$("#ATTACH_FILE_IMG2_DEL").val('Y');
					$("#ATTACH_FILENM_IMG2_DEL").val($("#REAL_FILE_NAME2").val());
					$("#FILE_PATH2").val('');
					$("#FILE_NAME2").val('');
					$("#REAL_FILE_NAME2").val('');
					
					jQuery('#UPLOAD2').css("display", "none");
					jQuery('#ATTACH_FILE2').css("display", "none");
					
					jQuery('#LINK2').css("display", "block");
					jQuery('#ATTACH_MOVIE_LINK2').css("display", "block");
					
				}else{
					$("input:radio[name='ATTACH_FILE_TYPE2']:radio[value='I']").attr("checked",true);
					$("input:radio[name='ATTACH_FILE_TYPE2']:radio[value='M']").attr("checked",false);
				}

			}else{
				jQuery('#UPLOAD2').css("display", "none");
				jQuery('#ATTACH_FILE2').css("display", "none");
				
				jQuery('#LINK2').css("display", "block");
				jQuery('#ATTACH_MOVIE_LINK2').css("display", "block");
			}
			
		}

}

function fn_timeChk(val){
	
	if(val == 'S'){
		$("#S_TIME_HH").val("").prop("selected", true); 
		$("#S_TIME_MM").val("").prop("selected", true);
		$("#E_TIME_HH").val("").prop("selected", true);
		$("#E_TIME_MM").val("").prop("selected", true);
		
		$("#S_TIME_HH").attr('disabled', 'disabled');
		$("#S_TIME_MM").attr('disabled', 'disabled');
		$("#E_TIME_HH").attr('disabled', 'disabled');
		$("#E_TIME_MM").attr('disabled', 'disabled');

	}else{
		
		$("#S_TIME_HH").removeAttr('disabled');
		$("#S_TIME_MM").removeAttr('disabled');
		$("#E_TIME_HH").removeAttr('disabled');
		$("#E_TIME_MM").removeAttr('disabled');
	}
}

function deleteImage(param, nm){
	if(param == 'listThumImg1'){
		$("#listThumImg1").html('');
		$("#ATTACH_FILE_IMG1_DEL").val('Y');
		$("#ATTACH_FILENM_IMG1_DEL").val(nm);
		$("#FILE_PATH").val('');
		$("#FILE_NAME").val('');
		$("#REAL_FILE_NAME").val('');
		
		jQuery('#UPLOAD1').css("display", "block");
		jQuery('#ATTACH_FILE1').css("display", "block");
	}else if(param == 'listThumImg2'){
		$("#listThumImg2").html('');
		$("#ATTACH_FILE_IMG2_DEL").val('Y');
		$("#ATTACH_FILENM_IMG2_DEL").val(nm);
		$("#FILE_PATH2").val('');
		$("#FILE_NAME2").val('');
		$("#REAL_FILE_NAME2").val('');
		
		jQuery('#UPLOAD2').css("display", "block");
		jQuery('#ATTACH_FILE2').css("display", "block");
	}
	
	alert('수정버튼을 눌러야 적용이 됩니다.');
}
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="COPENDATE" name="COPENDATE" value="${view.SUBJECT_OPEN_DATE }"/>
<input type="hidden" id="CLECS" name="CLECS" value="${view.LEC_SCHEDULE }"/>

<c:set var="WEEKCHK" value="" />
<c:if test="${view.WEEK1 eq 'Y' }"><c:set var="WEEKCHK" value="Y," /></c:if>
<c:if test="${view.WEEK1 ne 'Y' }"><c:set var="WEEKCHK" value="," /></c:if>

<c:if test="${view.WEEK2 eq 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}Y," /></c:if>
<c:if test="${view.WEEK2 ne 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}," /></c:if>

<c:if test="${view.WEEK3 eq 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}Y," /></c:if>
<c:if test="${view.WEEK3 ne 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}," /></c:if>

<c:if test="${view.WEEK4 eq 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}Y," /></c:if>
<c:if test="${view.WEEK4 ne 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}," /></c:if>

<c:if test="${view.WEEK5 eq 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}Y," /></c:if>
<c:if test="${view.WEEK5 ne 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}," /></c:if>

<c:if test="${view.WEEK6 eq 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}Y," /></c:if>
<c:if test="${view.WEEK6 ne 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}," /></c:if>

<c:if test="${view.WEEK7 eq 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}Y" /></c:if>
<c:if test="${view.WEEK7 ne 'Y' }"><c:set var="WEEKCHK" value="${WEEKCHK}" /></c:if>

<input type="hidden" id="CDATE" name="CDATE" value="${WEEKCHK}"/>
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}">
<input type="hidden" id="SEARCHMONTH" name="SEARCHMONTH" value="${params.SEARCHMONTH}">
<input type="hidden" id="SEARCHISUSE" name="SEARCHISUSE" value="${params.SEARCHISUSE}">
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}">
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}">
<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="ATTACH_FILE_IMG1_DEL" name="ATTACH_FILE_IMG1_DEL" value=""/>
<input type="hidden" id="ATTACH_FILE_IMG2_DEL" name="ATTACH_FILE_IMG2_DEL" value=""/>
<input type="hidden" id="ATTACH_FILENM_IMG1_DEL" name="ATTACH_FILENM_IMG1_DEL" value=""/>
<input type="hidden" id="ATTACH_FILENM_IMG2_DEL" name="ATTACH_FILENM_IMG2_DEL" value=""/>
<input type="hidden" id="SEQ" name="SEQ" value="${view.SEQ }"/>
<input type="hidden" id="FILE_PATH" name="FILE_PATH" value="${view.ATTACH_FILE1_PATH }"/>
<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="${view.ATTACH_FILE1_NM }"/>
<input type="hidden" id="REAL_FILE_NAME" name="REAL_FILE_NAME" value="${view.ATTACH_FILE1_NM }"/>
<input type="hidden" id="FILE_PATH2" name="FILE_PATH2" value="${view.ATTACH_FILE2_PATH }"/>
<input type="hidden" id="FILE_NAME2" name="FILE_NAME2" value="${view.ATTACH_FILE2_NM }"/>
<input type="hidden" id="REAL_FILE_NAME2" name="REAL_FILE_NAME2" value="${view.ATTACH_FILE2_NM}"/>


<c:choose><c:when test="${MENUTYPE eq 'OM_ROOT'}">
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="O"/>
</c:when><c:otherwise>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="F"/>
</c:otherwise></c:choose>
<input type="hidden" id="SCREEN_GUBUN" name="SCREEN_GUBUN" value=""/>
        <h2>● 사이트관리 > <strong>ON-AIR 수정</strong></h2>
        <p align="right">* 상단 텍스트는 여러개 입력시 / 로 구분해주세요</p><br>
        <table class="table01" >
            <tr>
                <th>회차</th>
            	<td class="tdLeft" colspan="6">
            		총 <input type="text" id="LEC_SCHEDULE" name="LEC_SCHEDULE" size="10%" value="${view.LEC_SCHEDULE }"> 회 강의입니다.
            	</td>
            </tr>
            <tr>
                <th>날짜</th>
                <td class="tdLeft" colspan="6">
					개시일자 <input type="text" id="SUBJECT_OPEN_DATE" name="SUBJECT_OPEN_DATE" value="${view.SUBJECT_OPEN_DATE }" style="background:#FFECEC;" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="WEEK1" value="Y" class="weekcls" <c:if test="${view.WEEK1 eq 'Y' }">checked="checked"</c:if> />일&nbsp;&nbsp;
					<input type="checkbox" name="WEEK2" value="Y" class="weekcls" <c:if test="${view.WEEK2 eq 'Y' }">checked="checked"</c:if> />월&nbsp;&nbsp;
					<input type="checkbox" name="WEEK3" value="Y" class="weekcls" <c:if test="${view.WEEK3 eq 'Y' }">checked="checked"</c:if> />화&nbsp;&nbsp;
					<input type="checkbox" name="WEEK4" value="Y" class="weekcls" <c:if test="${view.WEEK4 eq 'Y' }">checked="checked"</c:if> />수&nbsp;&nbsp;
					<input type="checkbox" name="WEEK5" value="Y" class="weekcls" <c:if test="${view.WEEK5 eq 'Y' }">checked="checked"</c:if> />목&nbsp;&nbsp; 
					<input type="checkbox" name="WEEK6" value="Y" class="weekcls" <c:if test="${view.WEEK6 eq 'Y' }">checked="checked"</c:if> />금&nbsp;&nbsp;
					<input type="checkbox" name="WEEK7" value="Y" class="weekcls" <c:if test="${view.WEEK7 eq 'Y' }">checked="checked"</c:if> />토&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="" type="button" value="적용" onClick="javascript:setLecDate();">
					<div id="cal_vw" style="width:800px;height:300px;margin-left:8px;display:none;margin-bottom:3px; overflow-x: scroll; "></div>
                </td>
            </tr>
            <!--직렬선택 /-->
            <tr>
                <th width="10%">시간</th>
                <td class="tdLeft" colspan="6">
                <input type="radio" id="TIME_TYPE" name="TIME_TYPE" value="S" onclick="fn_timeChk(this.value);"> 바로시작
                <input type="radio" id="TIME_TYPE" name="TIME_TYPE" value="I" onclick="fn_timeChk(this.value);" checked="checked"> 직접입력
                &nbsp;
                <select name="S_TIME_HH" id="S_TIME_HH">
                    <option value="">선택</option>                
                    <option value="00" <c:if test="${fn:substring(view.S_TIME,0,2) eq '00' }">selected="selected"</c:if>>00</option>
                    <option value="01" <c:if test="${fn:substring(view.S_TIME,0,2) eq '01' }">selected="selected"</c:if>>01</option>
                    <option value="02" <c:if test="${fn:substring(view.S_TIME,0,2) eq '02' }">selected="selected"</c:if>>02</option>
                    <option value="03" <c:if test="${fn:substring(view.S_TIME,0,2) eq '03' }">selected="selected"</c:if>>03</option>
                    <option value="04" <c:if test="${fn:substring(view.S_TIME,0,2) eq '04' }">selected="selected"</c:if>>04</option>
                    <option value="05" <c:if test="${fn:substring(view.S_TIME,0,2) eq '05' }">selected="selected"</c:if>>05</option>
                    <option value="06" <c:if test="${fn:substring(view.S_TIME,0,2) eq '06' }">selected="selected"</c:if>>06</option>
                    <option value="07" <c:if test="${fn:substring(view.S_TIME,0,2) eq '07' }">selected="selected"</c:if>>07</option>
                    <option value="08" <c:if test="${fn:substring(view.S_TIME,0,2) eq '08' }">selected="selected"</c:if>>08</option>
                    <option value="09" <c:if test="${fn:substring(view.S_TIME,0,2) eq '09' }">selected="selected"</c:if>>09</option>
                    <option value="10" <c:if test="${fn:substring(view.S_TIME,0,2) eq '10' }">selected="selected"</c:if>>10</option>
                    <option value="11" <c:if test="${fn:substring(view.S_TIME,0,2) eq '11' }">selected="selected"</c:if>>11</option>
                    <option value="12" <c:if test="${fn:substring(view.S_TIME,0,2) eq '12' }">selected="selected"</c:if>>12</option>
                    <option value="13" <c:if test="${fn:substring(view.S_TIME,0,2) eq '13' }">selected="selected"</c:if>>13</option>
                    <option value="14" <c:if test="${fn:substring(view.S_TIME,0,2) eq '14' }">selected="selected"</c:if>>14</option>
                    <option value="15" <c:if test="${fn:substring(view.S_TIME,0,2) eq '15' }">selected="selected"</c:if>>15</option>
                    <option value="16" <c:if test="${fn:substring(view.S_TIME,0,2) eq '16' }">selected="selected"</c:if>>16</option>
                    <option value="17" <c:if test="${fn:substring(view.S_TIME,0,2) eq '17' }">selected="selected"</c:if>>17</option>
                    <option value="18" <c:if test="${fn:substring(view.S_TIME,0,2) eq '18' }">selected="selected"</c:if>>18</option>
                    <option value="19" <c:if test="${fn:substring(view.S_TIME,0,2) eq '19' }">selected="selected"</c:if>>19</option>
                    <option value="20" <c:if test="${fn:substring(view.S_TIME,0,2) eq '20' }">selected="selected"</c:if>>20</option>
                    <option value="21" <c:if test="${fn:substring(view.S_TIME,0,2) eq '21' }">selected="selected"</c:if>>21</option>
                    <option value="22" <c:if test="${fn:substring(view.S_TIME,0,2) eq '22' }">selected="selected"</c:if>>22</option>
                    <option value="23" <c:if test="${fn:substring(view.S_TIME,0,2) eq '23' }">selected="selected"</c:if>>23</option>
                    <option value="24" <c:if test="${fn:substring(view.S_TIME,0,2) eq '24' }">selected="selected"</c:if>>24</option>
             </select>시
             &nbsp; : &nbsp;
                <select name="S_TIME_MM" id="S_TIME_MM">
                    <option value="">선택</option>                
                    <option value="00" <c:if test="${fn:substring(view.S_TIME,2,4) eq '00' }">selected="selected"</c:if>>00</option>
                    <option value="10" <c:if test="${fn:substring(view.S_TIME,2,4) eq '10' }">selected="selected"</c:if>>10</option>
                    <option value="20" <c:if test="${fn:substring(view.S_TIME,2,4) eq '20' }">selected="selected"</c:if>>20</option>
                    <option value="30" <c:if test="${fn:substring(view.S_TIME,2,4) eq '30' }">selected="selected"</c:if>>30</option>
                    <option value="40" <c:if test="${fn:substring(view.S_TIME,2,4) eq '40' }">selected="selected"</c:if>>40</option>
                    <option value="50" <c:if test="${fn:substring(view.S_TIME,2,4) eq '50' }">selected="selected"</c:if>>50</option>
             </select>분
             &nbsp; ~ &nbsp;          
             <select name="E_TIME_HH" id="E_TIME_HH">
                    <option value="">선택</option>                
                    <option value="00" <c:if test="${fn:substring(view.E_TIME,0,2) eq '00' }">selected="selected"</c:if>>00</option>
                    <option value="01" <c:if test="${fn:substring(view.E_TIME,0,2) eq '01' }">selected="selected"</c:if>>01</option>
                    <option value="02" <c:if test="${fn:substring(view.E_TIME,0,2) eq '02' }">selected="selected"</c:if>>02</option>
                    <option value="03" <c:if test="${fn:substring(view.E_TIME,0,2) eq '03' }">selected="selected"</c:if>>03</option>
                    <option value="04" <c:if test="${fn:substring(view.E_TIME,0,2) eq '04' }">selected="selected"</c:if>>04</option>
                    <option value="05" <c:if test="${fn:substring(view.E_TIME,0,2) eq '05' }">selected="selected"</c:if>>05</option>
                    <option value="06" <c:if test="${fn:substring(view.E_TIME,0,2) eq '06' }">selected="selected"</c:if>>06</option>
                    <option value="07" <c:if test="${fn:substring(view.E_TIME,0,2) eq '07' }">selected="selected"</c:if>>07</option>
                    <option value="08" <c:if test="${fn:substring(view.E_TIME,0,2) eq '08' }">selected="selected"</c:if>>08</option>
                    <option value="09" <c:if test="${fn:substring(view.E_TIME,0,2) eq '09' }">selected="selected"</c:if>>09</option>
                    <option value="10" <c:if test="${fn:substring(view.E_TIME,0,2) eq '10' }">selected="selected"</c:if>>10</option>
                    <option value="11" <c:if test="${fn:substring(view.E_TIME,0,2) eq '11' }">selected="selected"</c:if>>11</option>
                    <option value="12" <c:if test="${fn:substring(view.E_TIME,0,2) eq '12' }">selected="selected"</c:if>>12</option>
                    <option value="13" <c:if test="${fn:substring(view.E_TIME,0,2) eq '13' }">selected="selected"</c:if>>13</option>
                    <option value="14" <c:if test="${fn:substring(view.E_TIME,0,2) eq '14' }">selected="selected"</c:if>>14</option>
                    <option value="15" <c:if test="${fn:substring(view.E_TIME,0,2) eq '15' }">selected="selected"</c:if>>15</option>
                    <option value="16" <c:if test="${fn:substring(view.E_TIME,0,2) eq '16' }">selected="selected"</c:if>>16</option>
                    <option value="17" <c:if test="${fn:substring(view.E_TIME,0,2) eq '17' }">selected="selected"</c:if>>17</option>
                    <option value="18" <c:if test="${fn:substring(view.E_TIME,0,2) eq '18' }">selected="selected"</c:if>>18</option>
                    <option value="19" <c:if test="${fn:substring(view.E_TIME,0,2) eq '19' }">selected="selected"</c:if>>19</option>
                    <option value="20" <c:if test="${fn:substring(view.E_TIME,0,2) eq '20' }">selected="selected"</c:if>>20</option>
                    <option value="21" <c:if test="${fn:substring(view.E_TIME,0,2) eq '21' }">selected="selected"</c:if>>21</option>
                    <option value="22" <c:if test="${fn:substring(view.E_TIME,0,2) eq '22' }">selected="selected"</c:if>>22</option>
                    <option value="23" <c:if test="${fn:substring(view.E_TIME,0,2) eq '23' }">selected="selected"</c:if>>23</option>
                    <option value="24" <c:if test="${fn:substring(view.E_TIME,0,2) eq '24' }">selected="selected"</c:if>>24</option>
             </select>시
             &nbsp; : &nbsp;
             <select name="E_TIME_MM" id="E_TIME_MM">
                    <option value="">선택</option>                
                    <option value="00" <c:if test="${fn:substring(view.E_TIME,2,4) eq '00' }">selected="selected"</c:if>>00</option>
                    <option value="10" <c:if test="${fn:substring(view.E_TIME,2,4) eq '10' }">selected="selected"</c:if>>10</option>
                    <option value="20" <c:if test="${fn:substring(view.E_TIME,2,4) eq '20' }">selected="selected"</c:if>>20</option>
                    <option value="30" <c:if test="${fn:substring(view.E_TIME,2,4) eq '30' }">selected="selected"</c:if>>30</option>
                    <option value="40" <c:if test="${fn:substring(view.E_TIME,2,4) eq '40' }">selected="selected"</c:if>>40</option>
                    <option value="50" <c:if test="${fn:substring(view.E_TIME,2,4) eq '50' }">selected="selected"</c:if>>50</option>
             </select>분                               
                </td>
            </tr>
            
            <tr>
                <th width="10%">강좌명</th>
                <td>
                	<input type="text" id="SUBJECT_TITLE" name="SUBJECT_TITLE" size="50"  value="${view.SUBJECT_NM }">
                </td>
                <th width="10%">배너명</th>
                <td>
                	<input type="text" id="TAB_NM" name="TAB_NM" size="50"  value="${view.TAB_NM }">
                </td>
				<th>진행여부</th>
                	<td>
                		<select name="ISUSE" id="ISUSE">
                			<option value="Y"  <c:if test="${view.ISUSE eq 'Y' }">selected="selected"</c:if>>진행</option>
                			<%-- <option value="N"  <c:if test="${view.ISUSE eq 'N' }">selected="selected"</c:if>>예정</option> --%>
                			<option value="E"  <c:if test="${view.ISUSE eq 'E' }">selected="selected"</c:if>>종료</option>
                		</select>
                	</td>

            </tr>
            <tr>
                <th>상단 텍스트</th>
                <td colspan="6">
                <c:set var="TEXTARR" value="${fn:split(view.TOP_TEXT, '/')}"/>
                
                <input type="hidden" id="CNT" name="CNT" value=" ${fn:length(fn:split(view.TOP_TEXT, '/'))+1}"/>
				<div id="addedFormDiv" >
	                <c:forEach var="TXT" items="${TEXTARR}" varStatus="s">
	                	<div id="added_${s.count }">
	                		<input type="text" id="TEXT${s.count }" name="TEXT${s.count }" size="100%"  maxlength="45" value="${TXT }">&nbsp;&nbsp;<c:if test="${s.count == 1 }"><input type="button" value="추가" onclick="addForm()" /></c:if><c:if test="${s.count != 1 }"><input type="button" value="삭제" onclick="delForm()" /></c:if>
	                	</div>	
	                </c:forEach>
	                	<input type="hidden" id="TOP_TEXT" name="TOP_TEXT" size="200%" value="${view.TOP_TEXT }">
                </div>
                </td>
            </tr>
            <tr>
                <th>교수 한마디</th>
                <td class="tdLeft tdValign" colspan="6">
                	<select name="TEACHER_NM" id="TEACHER_NM">
	                    <option value="">교수선택</option>
	                    <c:forEach items="${TEACHER_NM}" var="data" varStatus="status" >
	                    	<option value="${data.USER_NM }" <c:if test="${data.USER_NM eq view.TEACHER_NM }">selected="selected"</c:if>>${data.USER_NM }</option>
	                    </c:forEach>                
             		</select>&nbsp;
					<input type="text" id="TEACHER_ADVICE" name="TEACHER_ADVICE" size="100" value="${view.TEACHER_ADVICE }">
                </td>
            </tr>
            <tr>
            	<th>강의실</th>
                	<td>
	                	<select name="CLASSROOM" id="CLASSROOM">
		                    <option value="">강의실선택</option>
		                    <c:forEach items="${CLASSROOM}" var="data" varStatus="status" >
		                    	<option value="${data.ONAIR_URL }" <c:if test="${data.ONAIR_URL eq view.ONAIR60_URL }">selected="selected"</c:if>>${data.CLASSROOM2 }</option>
		                    </c:forEach>                
	             		</select>&nbsp;
                	</td>
            </tr>
            <tr>
                <th>노출내용1</th>
                <td class="tdLeft tdValign" colspan="6">
                * 타입 : 
                  <input type="radio" name="ATTACH_FILE_TYPE1" id="ATTACH_FILE_TYPE1" value="I"  onclick="fn_upload_link(this.value,1);" <c:if test="${view.ATTACH_TYPE1 eq 'I' }">checked</c:if>><span class="txt03">이미지</span>
                  <input type="radio" name="ATTACH_FILE_TYPE1" id="ATTACH_FILE_TYPE1" value="M" onclick="fn_upload_link(this.value,1);" <c:if test="${view.ATTACH_TYPE1 eq 'M' }">checked</c:if>><span class="txt04">영상</span></span>
                  <br>
                <p id="UPLOAD1" name="UPLOAD1" <c:if test="${!empty view.ATTACH_FILE1_PATH}" >style="display: none;"</c:if>> * UPLOAD :  </p><input title="레이블 텍스트" type="file" name="ATTACH_FILE1" id="ATTACH_FILE1"  <c:if test="${!empty view.ATTACH_FILE1_PATH}" >style="display: none;"</c:if>/>
                <ul class="bannerFile" id="listThumImg1">
                <c:if test="${!empty view.ATTACH_FILE1_PATH}" >
					<li>
						${view.ATTACH_FILE1_NM}
						<a href="javascript:deleteImage('listThumImg1', '${view.ATTACH_FILE1_NM}');"> [삭제] </a>
					</li>
					<li><img src="<c:url value="/imgFileView.do?path=${view.ATTACH_FILE1_PATH}"/>" width="150" height="50"></li>
				</c:if>
				</ul>
                <p style="display: none;"id="LINK1" name="LINK1">* LINK : </p><input type="text" name="ATTACH_MOVIE_LINK1" id="ATTACH_MOVIE_LINK1" value="${view.ATTACH_MOVIE_LINK1 }" size="120%" style="display: none;"/>
                </td>
            </tr>
            <tr>
                <th>노출내용2</th>
                <td class="tdLeft tdValign" colspan="6">
                * 타입 : 
                  <input type="radio" name="ATTACH_FILE_TYPE2" id="ATTACH_FILE_TYPE2" value="I"  onclick="fn_upload_link(this.value,2);" <c:if test="${view.ATTACH_TYPE2 eq 'I' }">checked</c:if>><span class="txt03">이미지</span>                  
                  <input type="radio" name="ATTACH_FILE_TYPE2" id="ATTACH_FILE_TYPE2" value="M" onclick="fn_upload_link(this.value,2);" <c:if test="${view.ATTACH_TYPE2 eq 'M' }">checked</c:if>><span class="txt04">영상</span></span>
                  <br>
                <p id="UPLOAD2" name="UPLOAD2" <c:if test="${!empty view.ATTACH_FILE2_PATH}" >style="display: none;"</c:if>>* UPLOAD : </p><input title="레이블 텍스트" type="file" name="ATTACH_FILE2" id="ATTACH_FILE2"  <c:if test="${!empty view.ATTACH_FILE2_PATH}" >style="display: none;"</c:if>/>
                <ul class="bannerFile" id="listThumImg2">
                <c:if test="${!empty view.ATTACH_FILE2_PATH}" >
					<li>
						${view.ATTACH_FILE2_NM}
						<a href="javascript:deleteImage('listThumImg2', '${view.ATTACH_FILE2_NM}');"> [삭제] </a>
					</li>
					<li><img src="<c:url value="/imgFileView.do?path=${view.ATTACH_FILE2_PATH}"/>" width="150" height="50"></li>
				</c:if>
				</ul>
                <p style="display: none;" id="LINK2" name="LINK2">* LINK : </p><input type="text" name="ATTACH_MOVIE_LINK2" id="ATTACH_MOVIE_LINK2" value="${view.ATTACH_MOVIE_LINK2 }" size="120%" style="display: none;"/>
                </td>
            </tr>            
        </table>
        <!--//테이블--> 
    
        <!--버튼-->
        <ul class="boardBtns">
          <li><a href="javascript:fn_Submit();">수정</a></li>
          <li><a href="javascript:fn_Delete();">삭제</a></li>
          <li><a href="javascript:fn_List();">목록</a></li>
        </ul>
        <!--//버튼--> 
</form>
</div>
<!--//content --> 
