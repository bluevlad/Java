<%--
* /manager/sysinfo/Sg_Menu_Mst_Add.jsp
* 메뉴관리 등록
* @author (Copyright Miraenet Co.,Ltd.)
* @email  @miraenet.com
* @version 1.0,2008/04/11
--%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<script language="JavaScript">
//중복확인 여부 
var isMenuIDCheck=0;

function checkNumber(input) {
    if(!isNumber(input)) {
        alert("☞ 숫자[0~9]만 사용하여 입력하십시요");
        setEmpty();
    }
}
function checkData(input) {
    if(!isAlphaNumDash(input)) {
        alert("☞ 영문 대문자[A~Z]만 사용하여 입력하십시요");
        setEmpty();
    }
}

function add(form) {
    var retVal=0;
    if(form.sys_cd.value=='') {
        alert("☞ 공통코드를 입력해주세요");
        form.sys_cd.focus();
    } else if(form.sys_nm.value=='') {
        alert("☞ 공통코드명을 입력해주세요");
        form.sys_nm.focus();
    } else if(form.code_val.value=='') {
        alert("☞ 설정코드를 입력해주세요");
        form.code_val.focus();
    } else if(form.code_nm.value=='') {
        alert("☞ 설정코드명을 입력해주세요");
        form.code_nm.focus();
    } else {
    	        
        if(form.code_id.value == ''){
        	form.code_id.value = form.sys_cd.value;
        }
        
        var dataString = $("#form").serialize();
        
        $.ajax({
            type : 'POST' , 
            url : '<c:url value="/adminManagementCode/passcodeInsertProcess.json"/>',
            data : dataString ,
            async : false,
            success : function(result){
                if(result.isInsert == 1) {
                    alert("등록 되었습니다.");
                    $("#form").attr("action", "<c:url value='/bannerManagement/bannerMgtList.do' />");
                    $("#form").submit();
                } else {
                    alert("등록이 안되었습니다.");
                }
            }
        });
    }
}

function fn_List(){
    $("#frm").attr("action", "<c:url value='/bannerManagement/bannerMgtList.do' />");
    $("#frm").submit();
}
</script>
</head>

<!--content -->
<div id="content">
 <h2>● 운영자관리 > <strong>배너관리</strong></h2>
 	<table class="table01">
        <form name=myform id="form" method=post  style="margin:0">
 		<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
		<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
		<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
		<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
		<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
		<input type="hidden" id="subCurrentPage" name="subCurrentPage" value="${params.subCurrentPage}">
		<input type="hidden" id="subPageRow" name="subPageRow" value="${params.subPageRow}">
        
        <input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
		<input type="hidden" id="SEARCHBANNERNO" name="SEARCHBANNERNO" value="${params.SEARCHBANNERNO}"/>
		<input type="hidden" id="SEARCHISUSE" name="SEARCHISUSE" value="${params.SEARCHISUSE}"/>
            <tr>
                <th width="10%">코드ID</th>
                <td class="tdLeft">
                ${params.CODE_ID}
                    <input type="hidden" name="code_id"  class="in_just" value="${params.CODE_ID}">
                    <!-- <input type=button class=btn04 value="중복확인" onClick="duplcheck(this.form)"> -->
                    <!-- <DIV ID='duplcheck' style='display: ; position: absolute;'>
                    <input type=button class=btn04 value="중복확인" onClick="duplcheck(this.form)">
                    </div> -->
                </td>
            </tr>
            <tr>
                <th width="10%">부모코드ID</th>
                <td class="tdLeft">
                 ${params.P_CODEID}<input type="hidden" name="p_codeid" size="60"   value="${params.P_CODEID}">
                </td>
            </tr>
            <tr>
                <th >순서</th>
                <td>${params.CODE_SEQ}<input type="hidden" name="code_seq" size="30"   value="${params.CODE_SEQ}"></td>
            </tr>
            <tr>
                <th height="25" >상태</th>
                <td class="tdLeft">
                    <input type=radio name="isuse" value="Y" checked >활성
                    <input type=radio name="isuse" value="N">비활성
                </td>
            </tr>
	        <tr>
	            <th>공통코드</th>
	            <td class="tdLeft"><input type="text" name="sys_cd" size="60"  value="${fn:replace(params.P_CODEID,'_ROOT','')}"></td>
	        </tr>
	        <tr>
	            <th>공통코드명</th>
	            <td class="tdLeft"><input type="text" name="sys_nm" size="60"  value="${params.SYS_NM}"></td>
	        </tr>        
	        <tr>
	            <th>설정코드</th>
	            <td class="tdLeft"><input type="text" name="code_val" size="60"  value=""></td>
	        </tr>       
	        <tr>
	            <th>설정코드명</th>
	            <td class="tdLeft"><input type="text" name="code_nm" size="60"  value=""></td>
	        </tr>               
            <tr>
                <th height="25" >코드설명</th>
               <td class="tdLeft">
               <textarea rows="10" cols="70" name="code_info" ></textarea>
                <br>(<span class="coun">※ 간략한 설명으로 20자이내 입력</span>)
                </td>
            </tr>
        	</form>
        </table>
         <ul class="boardBtns">
	          <li><a href="javascript:add(this.form);">신규배너등록</a></li>
	          <li><a href="javascript:fn_List();">목록</a></li>
	        </ul>
</div>