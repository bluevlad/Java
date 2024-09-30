<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
var sts = "${sts}";

var MOCKCODE = "${searchMap.MOCKCODE}";
var EXAMPERIOD = "${searchMap.EXAMPERIOD}";
var S_EXAMYEAR = "${searchMap.S_EXAMYEAR}";
var S_EXAMROUND = "${searchMap.S_EXAMROUND}";
var S_ISEXAMTYPEON = "${searchMap.S_ISEXAMTYPEON}";
var S_ISEXAMTYPEOFF = "${searchMap.S_ISEXAMTYPEOFF}";
var S_CLASSCODE = "${searchMap.S_CLASSCODE}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

window.onload = function () {
    if(EXAMPERIOD == "") {
        $('#EXAMPERIOD').val("0");
    }

    if(sts == "U") {
        getSubCODE('CLASSCODE', 'CLASSSERIESCODE', '<c:url value="/mouigosa/reg/subCode2.do"/>', 'U');
    }
}

$(function() {
    setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
    initDateFicker2("RECEIPTSTARTTIME1", "RECEIPTENDTIME1");
});

$(function() {
    setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
    initDateFicker2("EXAMSTARTTIME1", "EXAMENDTIME1");
});

function onOnlyNumber(obj) {
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
    } else {
        event.returnValue = false;
    }
}

//숫자 입력 폼
function chk(obj, target){
    var val = obj.value;
    if (val) {
        if (val.match(/^\d+$/gi) == null) {
            if(target == "OFFCLOSEPERSONNUMBER") {
                $('#OFFCLOSEPERSONNUMBER').val("");
                $('#OFFCLOSEPERSONNUMBER').focus();
                return;
            }

            if(target == "EXAMSTARTTIME2") {
                $('#EXAMSTARTTIME2').val("");
                $('#EXAMSTARTTIME2').focus();
                return;
            }

            if(target == "EXAMSTARTTIME3") {
                $('#EXAMSTARTTIME3').val("");
                $('#EXAMSTARTTIME3').focus();
                return;
            }

            if(target == "EXAMENDTIME2") {
                $('#EXAMENDTIME2').val("");
                $('#EXAMENDTIME2').focus();
                return;
            }

            if(target == "EXAMENDTIME3") {
                $('#EXAMENDTIME3').val("");
                $('#EXAMENDTIME3').focus();
                return;
            }

            if(target == "EXAMPERIOD") {
                $('#EXAMPERIOD').val("");
                $('#EXAMPERIOD').focus();
                return;
            }

            if(target == "EXAMTIME") {
                $('#EXAMTIME').val("");
                $('#EXAMTIME').focus();
                return;
            }

            if(target == "RECEIPTSTARTTIME2") {
                $('#RECEIPTSTARTTIME2').val("");
                $('#RECEIPTSTARTTIME2').focus();
                return;
            }

            if(target == "RECEIPTSTARTTIME3") {
                $('#RECEIPTSTARTTIME3').val("");
                $('#RECEIPTSTARTTIME3').focus();
                return;
            }

            if(target == "RECEIPTENDTIME2") {
                $('#RECEIPTENDTIME2').val("");
                $('#RECEIPTENDTIME2').focus();
                return;
            }

            if(target == "RECEIPTENDTIME3") {
                $('#RECEIPTENDTIME3').val("");
                $('#RECEIPTENDTIME3').focus();
                return;
            }

            if(target == "EXAMCOST") {
                $('#EXAMCOST').val("");
                $('#EXAMCOST').focus();
                return;
            }

            if(target == "DISCOUNTRATIO") {
                $('#DISCOUNTRATIO').val("");
                $('#DISCOUNTRATIO').focus();
                return;
            }

            if(target == "SALEAMOUNTS") {
                $('#SALEAMOUNTS').val("");
                $('#SALEAMOUNTS').focus();
                return;
            }

            if(target == "SUBJECTTYPEDIVISION") {
                $('#SUBJECTTYPEDIVISION').val("");
                $('#SUBJECTTYPEDIVISION').focus();
                return;
            }
        }
        else {
            if (val < 1) {
                /* if(target == "OFFCLOSEPERSONNUMBER") {
                    $('#OFFCLOSEPERSONNUMBER').val("");
                    $('#OFFCLOSEPERSONNUMBER').focus();
                    return;
                }  */
            }
        }
    }
}

//비동기 직렬 가져오기
function getSubCODE(selectId, target, url, sts) {
    var CLASSCODE = "";

    if(sts == "I") {
        CLASSCODE = $("#"+selectId+" option:selected").val();
    }else{
        CLASSCODE = "${searchMap.CLASSCODE}";
    }
    if(CLASSCODE == "" || CLASSCODE == undefined) {
        return false;
    }

    $("#CLASSSERIESCODE").html('');

    var _url = url + '?CLASSCODE=' + CLASSCODE;
    var CLASSSERIESCODE = "${searchMap.CLASSSERIESCODE}";
    var array_data = CLASSSERIESCODE.split(",");

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa){
                    $("#"+target).append('<tr><td>' + mouigosa.NAME + '</td><td><div class="item"><input class="i_check" name="CLASSSERIESCODE"  value=' + mouigosa.CODE + ' type="checkbox" /></div></td></tr>');
                    for(var i=0; i<array_data.length; i++ ){
                        var temp = array_data[i];
                         if(mouigosa.CODE == temp){
                            jQuery("input[name=CLASSSERIESCODE]").eq(index).prop("checked",true);
                        }
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });

    var EXAMYEAR = $('#EXAMYEAR').val();
    var EXAMROUND = $('#EXAMROUND').val();

    $("#SUBJECTPERIOD1").html('');

    var url = '<c:url value="/mouigosa/reg/subCode3.do"/>';
    _url = url + '?CLASSCODE=' + CLASSCODE + '&EXAMYEAR=' + EXAMYEAR + '&EXAMROUND=' + EXAMROUND + '&MOCKCODE=' + MOCKCODE + '&sts=' + sts;
    var ITEMID_1 = "${searchMap.ITEMID_1}";
    var array_data2 = ITEMID_1.split(",");

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa2){
                    if(sts == "I") {
                        $("#SUBJECTPERIOD1").append('<tr><td>' + mouigosa2.SUBJECT_NM + '</td><td>' + mouigosa2.SUBJECTORDER + '</td><td>' + mouigosa2.PROF_NM + '</td><td><input type="text" id="SUBJECTPERIOD1" style="width:50%;" alt="교시" class="i_text must number" title="교시를 입력해 주세요" name="SUBJECTPERIOD1" value="1"/></td><td><div class="item"><input class="i_check" name="SUBJECT_CD_CK"  value=' + mouigosa2.ITEMID + ' type="checkbox" /></div></td><input type="hidden" name="ITEMID1" id="ITEMID1" value="' + mouigosa2.ITEMID + '"><input type="hidden" name="SUBJECT_CD1" id="SUBJECT_CD1" value="' + mouigosa2.SUBJECT_CD + '"><input type="hidden" name="SUBJECTORDER1" id="SUBJECTORDER1" value="' + mouigosa2.SUBJECTORDER + '">');
                    }else{
                        $("#SUBJECTPERIOD1").append('<tr><td>' + mouigosa2.SUBJECT_NM + '</td><td>' + mouigosa2.SUBJECTORDER + '</td><td>' + mouigosa2.PROF_NM + '</td><td><input type="text" id="SUBJECTPERIOD1" style="width:50%;" alt="교시" class="i_text must number" title="교시를 입력해 주세요" name="SUBJECTPERIOD1" value="' + mouigosa2.SUBJECTPERIOD + '"/></td><td><div class="item"><input class="i_check" name="SUBJECT_CD_CK"  value=' + mouigosa2.ITEMID + ' type="checkbox" /></div></td><input type="hidden" name="ITEMID1" id="ITEMID1" value="' + mouigosa2.ITEMID + '"><input type="hidden" name="SUBJECT_CD1" id="SUBJECT_CD1" value="' + mouigosa2.SUBJECT_CD + '"><input type="hidden" name="SUBJECTORDER1" id="SUBJECTORDER1" value="' + mouigosa2.SUBJECTORDER + '">');
                    }

                    for(var i=0; i<array_data2.length; i++ ){
                        var temp2 = array_data2[i];
                         if(mouigosa2.ITEMID == temp2){
                            jQuery("input[name=SUBJECT_CD_CK]").eq(index).prop("checked",true);
                         }
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });

    $("#SUBJECTPERIOD2").html('');

    var url = '<c:url value="/mouigosa/reg/subCode4.do"/>';
    _url = url + '?CLASSCODE=' + CLASSCODE + '&EXAMYEAR=' + EXAMYEAR + '&EXAMROUND=' + EXAMROUND + '&MOCKCODE=' + MOCKCODE + '&sts=' + sts;
    var ITEMID_2 = "${searchMap.ITEMID_2}";
    var array_data3 = ITEMID_2.split(",");

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa3){
                    if(sts == "I") {
                        $("#SUBJECTPERIOD2").append('<tr><td>' + mouigosa3.SUBJECT_NM + '</td><td>' + mouigosa3.SUBJECTORDER + '</td><td>' + mouigosa3.PROF_NM + '</td><td><input type="text" id="SUBJECTPERIOD2" style="width:50%;" alt="교시" class="i_text must number" title="교시를 입력해 주세요" name="SUBJECTPERIOD2" value="1"/></td><td><div class="item"><input class="i_check" name="SUBJECT_CD_CK2"  value=' + mouigosa3.ITEMID + ' type="checkbox" /></div></td><input type="hidden" name="ITEMID2" id="ITEMID2" value="' + mouigosa3.ITEMID + '"><input type="hidden" name="SUBJECT_CD2" id="SUBJECT_CD2" value="' + mouigosa3.SUBJECT_CD + '"><input type="hidden" name="SUBJECTORDER2" id="SUBJECTORDER2" value="' + mouigosa3.SUBJECTORDER + '">');
                    }else{
                        $("#SUBJECTPERIOD2").append('<tr><td>' + mouigosa3.SUBJECT_NM + '</td><td>' + mouigosa3.SUBJECTORDER + '</td><td>' + mouigosa3.PROF_NM + '</td><td><input type="text" id="SUBJECTPERIOD2" style="width:50%;" alt="교시" class="i_text must number" title="교시를 입력해 주세요" name="SUBJECTPERIOD2" value="' + mouigosa3.SUBJECTPERIOD + '"/></td><td><div class="item"><input class="i_check" name="SUBJECT_CD_CK2"  value=' + mouigosa3.ITEMID + ' type="checkbox" /></div></td><input type="hidden" name="ITEMID2" id="ITEMID2" value="' + mouigosa3.ITEMID + '"><input type="hidden" name="SUBJECT_CD2" id="SUBJECT_CD2" value="' + mouigosa3.SUBJECT_CD + '"><input type="hidden" name="SUBJECTORDER2" id="SUBJECTORDER2" value="' + mouigosa3.SUBJECTORDER + '">');
                    }
                    
                    for(var i=0; i<array_data3.length; i++ ){
                        var temp3 = array_data3[i];
                         if(mouigosa3.ITEMID == temp3){
                            jQuery("input[name=SUBJECT_CD_CK2]").eq(index).prop("checked",true);
                        }
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });
}

//저장
function checkParam() {
    if(!formValidate("popFrm")) return;

    if(popFrm.ISEXAMTYPEON.checked == true){
        $('#ISEXAMTYPEON').val("1");
    }else{
        $('#ISEXAMTYPEON').val("0");
    }
    if(popFrm.ISEXAMTYPEOFF.checked == true){
        $('#ISEXAMTYPEOFF').val("1");
    }else{
        $('#ISEXAMTYPEOFF').val("0");
    }

    if(popFrm.EXAMPERIODTYPE1.checked == true){
        if ($('#EXAMSTARTTIME1').val() == "") {
            alert('시험일자를 입력해 주세요.');
            $('#EXAMSTARTTIME1').focus();
            return;
        }

        if ($('#EXAMSTARTTIME2').val() == "") {
            alert('시험일자 시를 입력해 주세요.');
            $('#EXAMSTARTTIME2').focus();
            return;
        }

        if ($('#EXAMSTARTTIME3').val() == "") {
            alert('시험일자 분을 입력해 주세요.');
            $('#EXAMSTARTTIME3').focus();
            return;
        }

        if ($('#EXAMENDTIME1').val() == "") {
            alert('시험일자를 입력해 주세요.');
            $('#EXAMENDTIME1').focus();
            return;
        }

        if ($('#EXAMENDTIME2').val() == "") {
            alert('시험일자 시를 입력해 주세요.');
            $('#EXAMENDTIME2').focus();
            return;
        }

        if ($('#EXAMENDTIME3').val() == "") {
            alert('시험일자 분을 입력해 주세요.');
            $('#EXAMENDTIME3').focus();
            return;
        }

        if($("#EXAMSTARTTIME1").val()!='' || $("#EXAMENDTIME1").val()!=''){
            if($("#EXAMSTARTTIME1").val()!='' && $("#EXAMENDTIME1").val()!=''){
                if(parseInt($("#EXAMENDTIME1").val().replace(/-/g,'')) < parseInt($("#EXAMSTARTTIME1").val().replace(/-/g,''))){
                    alert('시험일자 종료일은 시작일보다 큰 날짜를 선택하세요.');
                    return;
                }
            }
        }

        if($("#EXAMSTARTTIME2").val().length != 2) {
            alert('시험일자 시를 2자리로 입력해 주세요.');
            $('#EXAMSTARTTIME2').focus();
            return;
        }

        if(parseInt($("#EXAMSTARTTIME2").val()) > 23) {
            alert('시험일자 시를 23 이하로 입력해주세요.');
            $('#EXAMSTARTTIME2').focus();
            return;
        }

        if($("#EXAMSTARTTIME3").val().length != 2) {
            alert('시험일자 분을 2자리로 입력해 주세요.');
            $('#EXAMSTARTTIME3').focus();
            return;
        }

        if(parseInt($("#EXAMSTARTTIME3").val()) > 59) {
            alert('시험일자 분를 59 이하로 입력해주세요.');
            $('#EXAMSTARTTIME3').focus();
            return;
        }

        var EXAMSTARTTIME = $("#EXAMSTARTTIME1").val().replace(/-/g,'') + $('#EXAMSTARTTIME2').val() + $('#EXAMSTARTTIME3').val();
        $('#EXAMSTARTTIME').val(EXAMSTARTTIME);

        if($("#EXAMENDTIME2").val().length != 2) {
            alert('시험일자 시를 2자리로 입력해 주세요.');
            $('#EXAMENDTIME2').focus();
            return;
        }

        if(parseInt($("#EXAMENDTIME2").val()) > 23) {
            alert('시험일자 시를 23 이하로 입력해주세요.');
            $('#EXAMENDTIME2').focus();
            return;
        }

        if($("#EXAMENDTIME3").val().length != 2) {
            alert('시험일자 분을 2자리로 입력해 주세요.');
            $('#EXAMENDTIME3').focus();
            return;
        }

        if(parseInt($("#EXAMENDTIME3").val()) > 59) {
            alert('시험일자 분를 59 이하로 입력해주세요.');
            $('#EXAMENDTIME3').focus();
            return;
        }

        var EXAMENDTIME = $("#EXAMENDTIME1").val().replace(/-/g,'') + $('#EXAMENDTIME2').val() + $('#EXAMENDTIME3').val();
        $('#EXAMENDTIME').val(EXAMENDTIME);
    }else{
        if ($('#EXAMPERIOD').val() == "") {
            alert('시험기간을 입력해 주세요.');
            $('#EXAMPERIOD').focus();
            return;
        }
    }

    if($("#EXAMSTARTTIME1").val()!='' && $("#EXAMSTARTTIME2").val()!='' && $("#EXAMSTARTTIME3").val()!=''){

        if($("#EXAMSTARTTIME2").val().length != 2) {
            alert('시험일자 시를 2자리로 입력해 주세요.');
            $('#EXAMSTARTTIME2').focus();
            return;
        }

        if(parseInt($("#EXAMSTARTTIME2").val()) > 23) {
            alert('시험일자 시를 23 이하로 입력해주세요.');
            $('#EXAMSTARTTIME2').focus();
            return;
        }

        if($("#EXAMSTARTTIME3").val().length != 2) {
            alert('시험일자 분을 2자리로 입력해 주세요.');
            $('#EXAMSTARTTIME3').focus();
            return;
        }

        if(parseInt($("#EXAMSTARTTIME3").val()) > 59) {
            alert('시험일자 분를 59 이하로 입력해주세요.');
            $('#EXAMSTARTTIME3').focus();
            return;
        }

        var EXAMSTARTTIME = $("#EXAMSTARTTIME1").val().replace(/-/g,'') + $('#EXAMSTARTTIME2').val() + $('#EXAMSTARTTIME3').val();
        $('#EXAMSTARTTIME').val(EXAMSTARTTIME);
    }

    if($("#EXAMENDTIME1").val()!='' && $("#EXAMENDTIME2").val()!='' && $("#EXAMENDTIME3").val()!=''){

        if($("#EXAMENDTIME2").val().length != 2) {
            alert('시험일자 시를 2자리로 입력해 주세요.');
            $('#EXAMENDTIME2').focus();
            return;
        }

        if(parseInt($("#EXAMENDTIME2").val()) > 23) {
            alert('시험일자 시를 23 이하로 입력해주세요.');
            $('#EXAMENDTIME2').focus();
            return;
        }

        if($("#EXAMENDTIME3").val().length != 2) {
            alert('시험일자 분을 2자리로 입력해 주세요.');
            $('#EXAMENDTIME3').focus();
            return;
        }

        if(parseInt($("#EXAMENDTIME3").val()) > 59) {
            alert('시험일자 분를 59 이하로 입력해주세요.');
            $('#EXAMENDTIME3').focus();
            return;
        }

        var EXAMENDTIME = $("#EXAMENDTIME1").val().replace(/-/g,'') + $('#EXAMENDTIME2').val() + $('#EXAMENDTIME3').val();
        $('#EXAMENDTIME').val(EXAMENDTIME);
    }

     /* alert("ISEXAMTYPEON:"+$('#ISEXAMTYPEON').val() +"\n"+
    "ISEXAMTYPEOFF:"+$('#ISEXAMTYPEOFF').val()); */

    if ($('#ISEXAMTYPEOFF').val() == "1") {

        if ($('#OFFCLOSEPERSONNUMBER').val() == "") {
            alert('마감인원(OFF)를 입력해 주세요.');
            $('#OFFCLOSEPERSONNUMBER').focus();
            return;
        }

        if ($('#RECEIPTSTARTTIME1').val() == "") {
            alert('접수기간을 입력해 주세요.');
            $('#RECEIPTSTARTTIME1').focus();
            return;
        }

        if ($('#RECEIPTSTARTTIME2').val() == "") {
            alert('접수기간 시를 입력해 주세요.');
            $('#RECEIPTSTARTTIME2').focus();
            return;
        }

        if ($('#RECEIPTSTARTTIME3').val() == "") {
            alert('접수기간 분을 입력해 주세요.');
            $('#RECEIPTSTARTTIME3').focus();
            return;
        }

        if ($('#RECEIPTENDTIME1').val() == "") {
            alert('접수기간을 입력해 주세요.');
            $('#RECEIPTENDTIME1').focus();
            return;
        }

        if ($('#RECEIPTENDTIME2').val() == "") {
            alert('접수기간 시를 입력해 주세요.');
            $('#RECEIPTENDTIME2').focus();
            return;
        }

        if ($('#RECEIPTENDTIME3').val() == "") {
            alert('접수기간 분을 입력해 주세요.');
            $('#RECEIPTENDTIME3').focus();
            return;
        }

        if($("#RECEIPTSTARTTIME1").val()!='' || $("#RECEIPTENDTIME1").val()!=''){
            if($("#RECEIPTSTARTTIME1").val()!='' && $("#RECEIPTENDTIME1").val()!=''){
                if(parseInt($("#RECEIPTENDTIME1").val().replace(/-/g,'')) < parseInt($("#RECEIPTSTARTTIME1").val().replace(/-/g,''))){
                    alert('접수기간 종료일은 시작일보다 큰 날짜를 선택하세요.');
                    return;
                }
            }
        }

        if($("#RECEIPTSTARTTIME2").val().length != 2) {
            alert('접수기간 시를 2자리로 입력해 주세요.');
            $('#RECEIPTSTARTTIME2').focus();
            return;
        }

        if(parseInt($("#RECEIPTSTARTTIME2").val()) > 23) {
            alert('접수기간 시를 23 이하로 입력해주세요.');
            $('#RECEIPTSTARTTIME2').focus();
            return;
        }

        if($("#RECEIPTSTARTTIME3").val().length != 2) {
            alert('접수기간 분을 2자리로 입력해 주세요.');
            $('#RECEIPTSTARTTIME3').focus();
            return;
        }

        if(parseInt($("#RECEIPTSTARTTIME3").val()) > 59) {
            alert('접수기간 분를 59 이하로 입력해주세요.');
            $('#RECEIPTSTARTTIME3').focus();
            return;
        }

        var RECEIPTSTARTTIME = $("#RECEIPTSTARTTIME1").val().replace(/-/g,'') + $('#RECEIPTSTARTTIME2').val() + $('#RECEIPTSTARTTIME3').val();
        $('#RECEIPTSTARTTIME').val(RECEIPTSTARTTIME);

        if($("#RECEIPTENDTIME2").val().length != 2) {
            alert('접수기간 시를 2자리로 입력해 주세요.');
            $('#RECEIPTENDTIME2').focus();
            return;
        }

        if(parseInt($("#RECEIPTENDTIME2").val()) > 23) {
            alert('접수기간 시를 23 이하로 입력해주세요.');
            $('#RECEIPTENDTIME2').focus();
            return;
        }

        if($("#RECEIPTENDTIME3").val().length != 2) {
            alert('접수기간 분을 2자리로 입력해 주세요.');
            $('#RECEIPTENDTIME3').focus();
            return;
        }

        if(parseInt($("#RECEIPTENDTIME3").val()) > 59) {
            alert('접수기간 분를 59 이하로 입력해주세요.');
            $('#RECEIPTENDTIME3').focus();
            return;
        }

        var RECEIPTENDTIME = $("#RECEIPTENDTIME1").val().replace(/-/g,'') + $('#RECEIPTENDTIME2').val() + $('#RECEIPTENDTIME3').val();
        $('#RECEIPTENDTIME').val(RECEIPTENDTIME);
    }

    if($("#RECEIPTSTARTTIME1").val()!='' && $("#RECEIPTSTARTTIME2").val()!='' && $("#RECEIPTSTARTTIME3").val()!=''){
        if($("#RECEIPTSTARTTIME2").val().length != 2) {
            alert('접수기간 시를 2자리로 입력해 주세요.');
            $('#RECEIPTSTARTTIME2').focus();
            return;
        }

        if(parseInt($("#RECEIPTSTARTTIME2").val()) > 23) {
            alert('접수기간 시를 23 이하로 입력해주세요.');
            $('#RECEIPTSTARTTIME2').focus();
            return;
        }

        if($("#RECEIPTSTARTTIME3").val().length != 2) {
            alert('접수기간 분을 2자리로 입력해 주세요.');
            $('#RECEIPTSTARTTIME3').focus();
            return;
        }

        if(parseInt($("#RECEIPTSTARTTIME3").val()) > 59) {
            alert('접수기간 분를 59 이하로 입력해주세요.');
            $('#RECEIPTSTARTTIME3').focus();
            return;
        }

        var RECEIPTSTARTTIME = $("#RECEIPTSTARTTIME1").val().replace(/-/g,'') + $('#RECEIPTSTARTTIME2').val() + $('#RECEIPTSTARTTIME3').val();
        $('#RECEIPTSTARTTIME').val(RECEIPTSTARTTIME);
    }

    if($("#RECEIPTENDTIME1").val()!='' && $("#RECEIPTENDTIME2").val()!='' && $("#RECEIPTENDTIME3").val()!=''){
        if($("#RECEIPTENDTIME2").val().length != 2) {
            alert('접수기간 시를 2자리로 입력해 주세요.');
            $('#RECEIPTENDTIME2').focus();
            return;
        }

        if(parseInt($("#RECEIPTENDTIME2").val()) > 23) {
            alert('접수기간 시를 23 이하로 입력해주세요.');
            $('#RECEIPTENDTIME2').focus();
            return;
        }

        if($("#RECEIPTENDTIME3").val().length != 2) {
            alert('접수기간 분을 2자리로 입력해 주세요.');
            $('#RECEIPTENDTIME3').focus();
            return;
        }

        if(parseInt($("#RECEIPTENDTIME3").val()) > 59) {
            alert('접수기간 분를 59 이하로 입력해주세요.');
            $('#RECEIPTENDTIME3').focus();
            return;
        }

        var RECEIPTENDTIME = $("#RECEIPTENDTIME1").val().replace(/-/g,'') + $('#RECEIPTENDTIME2').val() + $('#RECEIPTENDTIME3').val();
        $('#RECEIPTENDTIME').val(RECEIPTENDTIME);
    }

    var tmp ="";
    $("input[name=CLASSSERIESCODE]:checked").each(function (index){
        tmp += $(this).val() + ",";
    });
    if(tmp == null || tmp == "" || tmp == undefined){
        alert("직렬을 선택해 주세요.");
        return;
    }
    $("#insertIds").val(tmp);
     /* alert("insertIds:"+$('#insertIds').val() +"\n"+
            "index:"+$('#insertIds').val().split(",").length);return; */

    var tmp2 ="";
    $("input[name=SUBJECT_CD_CK]:checked").each(function (index){
        tmp2 += $(this).val() + ",";
    });
    $("#deleteIds").val(tmp2);
    /* alert("deleteIds:"+$('#deleteIds').val() +"\n"+
            "index:"+$('#deleteIds').val().split(",").length); */

    var tmp3 ="";
    $("input[name=SUBJECT_CD_CK2]:checked").each(function (index){
        tmp3 += $(this).val() + ",";
    });
    $("#deleteIds2").val(tmp3);
    /* alert("deleteIds2:"+$('#deleteIds2').val() +"\n"+
            "index:"+$('#deleteIds2').val().split(",").length); */

    /* var obj;
    $("input[name=SUBJECT_CD1]").each(function (index){
        if(obj == $(this).val()) {
            alert("필수 과목을 중복하여 등록할 수 없습니다.");
            return;
        }

        obj = $(this).val();
    });

    var obj2;
    $("input[name=SUBJECT_CD2]").each(function (index){
        if(obj2 == $(this).val()) {
            alert("선택 과목을 중복하여 등록할 수 없습니다.");
            return;
        }

        obj2 = $(this).val();
    }); */

    if(sts == "I") {
        var _url = '<c:url value="/mouigosa/reg/registrationMax.do"/>';

        //모의고사 등록 max값
        $.ajax({
            type : "GET",
            url : _url,
            dataType : "json",
            async : false,
            success : function(json){
                if(json && json.length > 0) {
                    $(json).each(function(index, mouigosa){
                        $('#MOCKCODE').val(mouigosa.MOCKCODE);
                        $('#popFrm').attr('action','<c:url value="/mouigosa/reg/mouigosaRegistrationInsert.do"/>').submit();
                    });
                }
            }
        });
    }else{
        popFrm.submit();
        $('#popFrm').attr('action','<c:url value="/mouigosa/reg/mouigosaRegistrationUpdate.do"/>').submit();
    }
}

//취소
function goList() {
    $('#popFrm').attr('action','<c:url value="/mouigosa/reg/mouigosaRegistrationList.do"/>').submit();
}

function chkbox(){
    if(popFrm.ISEXAMTYPEON.checked == true){
        $('#ISEXAMTYPEON').val("1");
    }else{
        $('#ISEXAMTYPEON').val("0");
    }

    if(popFrm.ISEXAMTYPEOFF.checked == true){
        $('#ISEXAMTYPEOFF').val("1");
    }else{
        $('#ISEXAMTYPEOFF').val("0");
    }
}

//필수/선택과목 불러오기
function goRegistrationPop(SUBJECTTYPEDIVISION) {
    if ($('#CLASSCODE').val() == "") {
        alert('직급을 선택해 주세요.');
        $('#CLASSCODE').focus();
        return;
    }

    var CLASSCODE = $('#CLASSCODE').val();

    window.open('<c:url value="/mouigosa/reg/updateRegistrationOnlyMouigosa.pop"/>?SUBJECTTYPEDIVISION=' + SUBJECTTYPEDIVISION + '&CLASSCODE=' + CLASSCODE, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=795,height=950');

}

//필수/선택과목 불러오기 팝업 변수 받아오기
function goparam(ITEMID,SUBJECT_CD,SUBJECT_NM,USERNAME,SUBJECTTYPEDIVISION) {

    var SUBJECTORDER1 = $("[name='SUBJECTORDER1']").size() + 1;
    var SUBJECTORDER2 = $("[name='SUBJECTORDER2']").size() + 1;

    if(SUBJECTTYPEDIVISION == "1") {
        $("#SUBJECTPERIOD1").append('<tr><td>' + SUBJECT_NM + '</td><td>' + SUBJECTORDER1 + '</td><td>' + USERNAME + '</td><td><input type="text" id="SUBJECTPERIOD1" style="width:50%;" alt="교시" class="i_text must number" title="교시를 입력해 주세요" name="SUBJECTPERIOD1" value="1"/></td><td><div class="item"><input class="i_check" name="SUBJECT_CD_CK"  value=' + ITEMID + ' type="checkbox" /></div></td><input type="hidden" name="ITEMID1" id="ITEMID1" value="' + ITEMID + '"><input type="hidden" name="SUBJECT_CD1" id="SUBJECT_CD1" value="' + SUBJECT_CD + '"><input type="hidden" name="SUBJECTORDER1" id="SUBJECTORDER1" value="' + SUBJECTORDER1 + '">');
    }else{
        $("#SUBJECTPERIOD2").append('<tr><td>' + SUBJECT_NM + '</td><td>' + SUBJECTORDER2 + '</td><td>' + USERNAME + '</td><td><input type="text" id="SUBJECTPERIOD2" style="width:50%;" alt="교시" class="i_text must number" title="교시를 입력해 주세요" name="SUBJECTPERIOD2" value="1"/></td><td><div class="item"><input class="i_check" name="SUBJECT_CD_CK2"  value=' + ITEMID + ' type="checkbox" /></div></td><input type="hidden" name="ITEMID2" id="ITEMID2" value="' + ITEMID + '"><input type="hidden" name="SUBJECT_CD2" id="SUBJECT_CD2" value="' + SUBJECT_CD + '"><input type="hidden" name="SUBJECTORDER2" id="SUBJECTORDER2" value="' + SUBJECTORDER2 + '">');
    }

}
</script>
</head>

<form id="popFrm" name="popFrm" method="post">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}">

    <input type="hidden" id="MOCKCODE" name="MOCKCODE" value="${searchMap.MOCKCODE}" />
    <input type="hidden" id="RECEIPTSTARTTIME" name="RECEIPTSTARTTIME" value="" />
    <input type="hidden" id="RECEIPTENDTIME" name="RECEIPTENDTIME" value="" />
    <input type="hidden" id="EXAMSTARTTIME" name="EXAMSTARTTIME" value="" />
    <input type="hidden" id="EXAMENDTIME" name="EXAMENDTIME" value="" />
    <input type="hidden" id="insertIds" name="insertIds">
    <input type="hidden" id="deleteIds" name="deleteIds">
    <input type="hidden" id="deleteIds2" name="deleteIds2">
    <input type="hidden" id="USER_ID" name="USER_ID" value="${AdmUserInfo.USER_ID}" />

    <input type="hidden" id="S_EXAMYEAR" name="S_EXAMYEAR" value="${searchMap.S_EXAMYEAR}">
    <input type="hidden" id="S_EXAMROUND" name="S_EXAMROUND" value="${searchMap.S_EXAMROUND}">
    <input type="hidden" id="S_ISEXAMTYPEON" name="S_ISEXAMTYPEON" value="${searchMap.S_ISEXAMTYPEON}">
    <input type="hidden" id="S_ISEXAMTYPEOFF" name="S_ISEXAMTYPEOFF" value="${searchMap.S_ISEXAMTYPEOFF}">
    <input type="hidden" id="S_CLASSCODE" name="S_CLASSCODE" value="${searchMap.S_CLASSCODE}">

<div id="content">
    <h2>
    <c:choose>
        <c:when test="${sts == 'I' }">● 모의고사등록 > <strong>모의고사 등록</strong></c:when>
        <c:when test="${sts == 'U' }">● 모의고사등록 > <strong>모의고사 수정</strong></c:when>
    </c:choose>
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
            <th>모의고사 명</th>
            <td>
                <div class="item">
                제목:
                <input id="MOCKNAME" name="MOCKNAME" type="text" class="i_text must" style="width:320px;" title="모의고사 명을 입력해 주세요" value="${searchMap.MOCKNAME}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                년도:
                <select id="EXAMYEAR" name="EXAMYEAR" class="must" >
                    <option value="2022"  <c:if test="${searchMap.EXAMYEAR == '2022'}">selected</c:if>>2022</option>
                    <option value="2023"  <c:if test="${searchMap.EXAMYEAR == '2023'}">selected</c:if>>2023</option>
                    <option value="2024"  <c:if test="${searchMap.EXAMYEAR == '2024'}">selected</c:if>>2024</option>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                      회차:
                <select id="EXAMROUND" name="EXAMROUND" class="must" >
                    <option value="1"  <c:if test="${searchMap.EXAMROUND == '1'}">selected</c:if>>1</option>
                    <option value="2"  <c:if test="${searchMap.EXAMROUND == '2'}">selected</c:if>>2</option>
                    <option value="3"  <c:if test="${searchMap.EXAMROUND == '3'}">selected</c:if>>3</option>
                    <option value="4"  <c:if test="${searchMap.EXAMROUND == '4'}">selected</c:if>>4</option>
                    <option value="5"  <c:if test="${searchMap.EXAMROUND == '5'}">selected</c:if>>5</option>
                    <option value="6"  <c:if test="${searchMap.EXAMROUND == '6'}">selected</c:if>>6</option>
                    <option value="7"  <c:if test="${searchMap.EXAMROUND == '7'}">selected</c:if>>7</option>
                    <option value="8"  <c:if test="${searchMap.EXAMROUND == '8'}">selected</c:if>>8</option>
                    <option value="9"  <c:if test="${searchMap.EXAMROUND == '9'}">selected</c:if>>9</option>
                    <option value="10"  <c:if test="${searchMap.EXAMROUND == '10'}">selected</c:if>>10</option>
                </select>
                </div>
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>응시형태</th>
            <td style="text-align:left;">
                <div class="item">
                <input id="ISEXAMTYPEON" name="ISEXAMTYPEON" class="i_checkbox" value="2" type="checkbox" <c:if test="${searchMap.ISEXAMTYPEON == '1' }">checked="checked"</c:if> onclick="chkbox()"/><label for="ISEXAMTYPEON">&nbsp;&nbsp;ON</label>
                <input id="ISEXAMTYPEOFF" name="ISEXAMTYPEOFF" class="i_checkbox" value="2" type="checkbox" <c:if test="${searchMap.ISEXAMTYPEOFF == '1' }">checked="checked"</c:if> onclick="chkbox()"/><label for="ISEXAMTYPEOFF">&nbsp;&nbsp;OFF</label>
                </div>
            </td>
        </tr>

        <tr>
            <th>마감인원(OFF)</th>
            <td style="text-align:left;">
                <div class="item"><input id="OFFCLOSEPERSONNUMBER" name="OFFCLOSEPERSONNUMBER" type="text" class="i_text" style="width:4%;" maxlength="4" value="${searchMap.OFFCLOSEPERSONNUMBER}" onkeyup="chk(this,'OFFCLOSEPERSONNUMBER')" onblur="chk(this,'OFFCLOSEPERSONNUMBER')"/></div>
            </td>
        </tr>

        <tr>
            <th>시험일자</th>
            <td style="text-align:left;">
                <div class="item">
                    <input id="EXAMSTARTTIME1" name="EXAMSTARTTIME1" type="text" maxlength="10" class="i_text" <c:if test="${!empty searchMap.EXAMSTARTTIME}">value="${fn:substring(searchMap.EXAMSTARTTIME, 0, 4)}-${fn:substring(searchMap.EXAMSTARTTIME, 4, 6)}-${fn:substring(searchMap.EXAMSTARTTIME, 6, 8)}"</c:if> readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
                    <input id="EXAMSTARTTIME2" name="EXAMSTARTTIME2" type="text" class="i_text" style="width:2%;" maxlength="2" value="${fn:substring(searchMap.EXAMSTARTTIME, 8, 10)}" onkeyup="chk(this,'EXAMSTARTTIME2')" onblur="chk(this,'EXAMSTARTTIME2')"/>시
                    <input id="EXAMSTARTTIME3" name="EXAMSTARTTIME3" type="text" class="i_text" style="width:2%;" maxlength="2" value="${fn:substring(searchMap.EXAMSTARTTIME, 10, 12)}" onkeyup="chk(this,'EXAMSTARTTIME3')" onblur="chk(this,'EXAMSTARTTIME3')"/>분~

                    <input id="EXAMENDTIME1" name="EXAMENDTIME1" type="text" maxlength="10" class="i_text" <c:if test="${!empty searchMap.EXAMENDTIME}">value="${fn:substring(searchMap.EXAMENDTIME, 0, 4)}-${fn:substring(searchMap.EXAMENDTIME, 4, 6)}-${fn:substring(searchMap.EXAMENDTIME, 6, 8)}"</c:if> readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
                    <input id="EXAMENDTIME2" name="EXAMENDTIME2" type="text" class="i_text" style="width:2%;" maxlength="2" value="${fn:substring(searchMap.EXAMENDTIME, 8, 10)}" onkeyup="chk(this,'EXAMENDTIME2')" onblur="chk(this,'EXAMENDTIME2')"/>시
                    <input id="EXAMENDTIME3" name="EXAMENDTIME3" type="text" class="i_text" style="width:2%;" maxlength="2" value="${fn:substring(searchMap.EXAMENDTIME, 10, 12)}" onkeyup="chk(this,'EXAMENDTIME3')" onblur="chk(this,'EXAMENDTIME3')"/>분
                    &nbsp;
                    <input name="EXAMPERIODTYPE" id="EXAMPERIODTYPE1" class="i_radio" value="0" type="radio" <c:if test="${empty searchMap.EXAMPERIODTYPE or searchMap.EXAMPERIODTYPE == '0' }">checked="checked"</c:if> /><label for="EXAMPERIODTYPE1">사용선택</label>
                </div>
            </td>
        </tr>

        <tr>
            <th>시험기간</th>
            <td style="text-align:left;">
                <div class="item">
                <input id="EXAMPERIOD" name="EXAMPERIOD" type="text" class="i_text" style="width:3%;" maxlength="3" value="${searchMap.EXAMPERIOD}" onkeyup="chk(this,'EXAMPERIOD')" onblur="chk(this,'EXAMPERIOD')"/>일
                &nbsp;
                <input name="EXAMPERIODTYPE" id="EXAMPERIODTYPE2" class="i_radio" value="1" type="radio" <c:if test="${searchMap.EXAMPERIODTYPE == '1' }">checked="checked"</c:if> /><label for="EXAMPERIODTYPE2">사용선택</label>
                </div>
            </td>
        </tr>

        <tr>
            <th>시험응시시간</th>
            <td style="text-align:left;">
                <div class="item"><input id="EXAMTIME" name="EXAMTIME" type="text" alt="시험시간" class="i_text must number" style="width:3%;" maxlength="3" title="시험응시시간을 입력해 주세요" value="${searchMap.EXAMTIME}" onkeyup="chk(this,'EXAMTIME')" onblur="chk(this,'EXAMTIME')"/>분</div>
            </td>
        </tr>

        <tr>
            <th>접수기간(OFF)</th>
            <td style="text-align:left;">
                <div class="item">
                    <input id="RECEIPTSTARTTIME1" name="RECEIPTSTARTTIME1" type="text" maxlength="10" class="i_text" <c:if test="${!empty searchMap.RECEIPTSTARTTIME}">value="${fn:substring(searchMap.RECEIPTSTARTTIME, 0, 4)}-${fn:substring(searchMap.RECEIPTSTARTTIME, 4, 6)}-${fn:substring(searchMap.RECEIPTSTARTTIME, 6, 8)}"</c:if> readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
                    <input id="RECEIPTSTARTTIME2" name="RECEIPTSTARTTIME2" type="text" class="i_text" style="width:2%;" maxlength="2" value="${fn:substring(searchMap.RECEIPTSTARTTIME, 8, 10)}" onkeyup="chk(this,'RECEIPTSTARTTIME2')" onblur="chk(this,'RECEIPTSTARTTIME2')"/>시
                    <input id="RECEIPTSTARTTIME3" name="RECEIPTSTARTTIME3" type="text" class="i_text" style="width:2%;" maxlength="2" value="${fn:substring(searchMap.RECEIPTSTARTTIME, 10, 12)}" onkeyup="chk(this,'RECEIPTSTARTTIME3')" onblur="chk(this,'RECEIPTSTARTTIME3')"/>분~

                    <input id="RECEIPTENDTIME1" name="RECEIPTENDTIME1" type="text" maxlength="10" class="i_text" <c:if test="${!empty searchMap.RECEIPTENDTIME}">value="${fn:substring(searchMap.RECEIPTENDTIME, 0, 4)}-${fn:substring(searchMap.RECEIPTENDTIME, 4, 6)}-${fn:substring(searchMap.RECEIPTENDTIME, 6, 8)}"</c:if> readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
                    <input id="RECEIPTENDTIME2" name="RECEIPTENDTIME2" type="text" class="i_text" style="width:2%;" maxlength="2" value="${fn:substring(searchMap.RECEIPTENDTIME, 8, 10)}" onkeyup="chk(this,'RECEIPTENDTIME2')" onblur="chk(this,'RECEIPTENDTIME2')"/>시
                    <input id="RECEIPTENDTIME3" name="RECEIPTENDTIME3" type="text" class="i_text" style="width:2%;" maxlength="2" value="${fn:substring(searchMap.RECEIPTENDTIME, 10, 12)}" onkeyup="chk(this,'RECEIPTENDTIME3')" onblur="chk(this,'RECEIPTENDTIME3')"/>분
                </div>
            </td>
        </tr>

        <tr>
            <th>직급/직렬</th>
            <td style="text-align:left;">
                <div class="item">
                    <select id="CLASSCODE" name="CLASSCODE" class="must"  title="직급을 선택해 주십시오." onchange="getSubCODE('CLASSCODE', 'CLASSSERIESCODE', '<c:url value="/mouigosa/reg/subCode2.do"/>', 'I')">
                        <option value="">직급 선택</option>
                    <c:forEach items="${registration_list}"  var="registration_list">
                        <option value="${registration_list.CODE }" <c:if test="${searchMap.CLASSCODE == registration_list.CODE}">selected</c:if>>${registration_list.NAME }</option>
                    </c:forEach>
                    </select>
                </div>
                <!--테이블-->
                <div class="item">
                     <div class="form_table" style="margin-top:10px; float:left; width:20%;">
                       <table class="tbl_type" border="1" cellspacing="0" summary="받은쪽지" style="min-width:100px;">
                         <caption>
                         </caption>
                         <colgroup>
                         <col width="">
                         <col width="30%">
                         </colgroup>
                         <thead>
                           <tr>
                             <th scope="col">직렬</th>
                             <th scope="col">
                                <div class="item">선택
                                  <input id="allCheck" class="i_check" value="" type="checkbox" onclick="checkAll('allCheck', 'CLASSSERIESCODE')" />
                                </div>
                              </th>
                           </tr>
                         </thead>
                         <tbody id="CLASSSERIESCODE">
                         </tbody>
                       </table>
                     </div>
                </div>
                 <!--//테이블-->
            </td>
        </tr>

        <tr>
            <th>필수/선택 과목</th>
            <td style="text-align:left;">
                 <!--테이블-->
                     <div class="form_table" style="margin-top:10px; float:left; width:45%;">
                       <table class="tbl_type" border="1" cellspacing="0" summary="받은쪽지">
                         <caption>
                         </caption>
                         <colgroup>
                         <col width="15%">
                         <col width="10%">
                         <col width="15%">
                         <col width="10%">
                         <col width="10%">
                         </colgroup>
                         <thead>
                           <tr>
                             <th scope="col">필수과목<br>(문제수)</th>
                             <th scope="col">순번</th>
                             <th scope="col">교수<br>(구분)</th>
                             <th scope="col">교시</th>
                             <th scope="col">
                                <div class="item">삭제
                                </div>
                              </th>
                           </tr>
                         </thead>
                         <tbody id="SUBJECTPERIOD1" style="background-color:#efefef">
                         <tr bgColor=#ffffff align=center>
                            <td colspan="5">직급을 선택해주세요.</td>
                        </tr>
                         </tbody>
                       </table>
                       <br>
                       <span class="btn_pack medium"><button type="button" onclick="goRegistrationPop('1')">필수과목 불러오기</button></span>
                     </div>
                 <!--//테이블-->
                 <!--테이블-->
                     <div class="form_table" style="margin-top:10px; float:left; width:45%;">
                       <table class="tbl_type" border="1" cellspacing="0" summary="받은쪽지">
                         <caption>
                         </caption>
                         <colgroup>
                         <col width="15%">
                         <col width="10%">
                         <col width="15%">
                         <col width="10%">
                         <col width="10%">
                         </colgroup>
                         <thead>
                           <tr>
                             <th scope="col">선택과목<br>(문제수)</th>
                             <th scope="col">순번</th>
                             <th scope="col">교수<br>(구분)</th>
                             <th scope="col">교시</th>
                             <th scope="col">
                                <div class="item">삭제
                                </div>
                              </th>
                           </tr>
                         </thead>
                         <tbody id="SUBJECTPERIOD2" style="background-color:#efefef">
                         <tr bgColor=#ffffff align=center>
                            <td colspan="5">직급을 선택해주세요.</td>
                        </tr>
                         </tbody>
                       </table>

                       <br>
                       <span class="btn_pack medium"><button type="button" onclick="goRegistrationPop('2')">선택과목 불러오기</button></span>
                     </div>
                 <!--//테이블-->
            </td>
        </tr>

        <tr>
            <th>응시료</th>
            <td style="text-align:left;">
                <div class="item">
                    <input id="EXAMCOST" name="EXAMCOST" type="text" alt="응시료 원가"  class="i_text must number" maxlength="7" title="응시료 원가를 입력해 주세요." value="${searchMap.EXAMCOST }" style="width:50px;" />원가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="DISCOUNTRATIO" name="DISCOUNTRATIO" type="text" alt="응시료 할인율"  class="i_text must number" maxlength="3" title="응시료 할인율을 입력해 주세요." value="${searchMap.DISCOUNTRATIO }" style="width:40px;" />할인율(%)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="SALEAMOUNTS" name="SALEAMOUNTS" type="text" alt="응시료 판매가"  class="i_text must number" maxlength="7" title="응시료 판매가를 입력해 주세요." value="${searchMap.SALEAMOUNTS }" style="width:50px;" />판매가
                </div>
            </td>
        </tr>

        <tr>
            <th>개설여부</th>
            <td style="text-align:left;">
                <div class="item">
                    <select id="USEFLAG" name="USEFLAG" class="must">
                        <option value="1"  <c:if test="${searchMap.USEFLAG == '1'}">selected</c:if>>활성</option>
                        <option value="0"  <c:if test="${searchMap.USEFLAG == '0'}">selected</c:if>>비활성</option>
                        <option value="3"  <c:if test="${searchMap.USEFLAG == '3'}">selected</c:if>>상시</option>
                        <option value="2"  <c:if test="${searchMap.USEFLAG == '2'}">selected</c:if>>마감</option>
                    </select>
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