<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/libs/cheditor/cheditor.js" /></script>
<script type="text/javascript">
var editor = null;
var editor2 = null;
var editor3 = null;
var editor4 = null;
var editor5 = null;
var editor6 = null;
$(document).ready(function(){
    editor = new cheditor();              // 에디터 개체를 생성합니다.
    editor.config.editorHeight = '200px';     // 에디터 세로폭입니다.
    editor.config.editorWidth = '96%';        // 에디터 가로폭입니다.
    editor.inputForm = 'YPLAN';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
    editor.run();

    editor2 = new cheditor();              // 에디터 개체를 생성합니다.
    editor2.config.editorHeight = '200px';     // 에디터 세로폭입니다.
    editor2.config.editorWidth = '96%';        // 에디터 가로폭입니다.
    editor2.inputForm = 'OFF_YPLAN';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
    editor2.run();

    editor3 = new cheditor();              // 에디터 개체를 생성합니다.
    editor3.config.editorHeight = '200px';     // 에디터 세로폭입니다.
    editor3.config.editorWidth = '96%';        // 에디터 가로폭입니다.
    editor3.inputForm = 'LECINFO';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
    editor3.run();

    editor4 = new cheditor();              // 에디터 개체를 생성합니다.
    editor4.config.editorHeight = '200px';     // 에디터 세로폭입니다.
    editor4.config.editorWidth = '96%';        // 에디터 가로폭입니다.
    editor4.inputForm = 'OFF_LECINFO';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
    editor4.run();

    editor5 = new cheditor();              // 에디터 개체를 생성합니다.
    editor5.config.editorHeight = '200px';     // 에디터 세로폭입니다.
    editor5.config.editorWidth = '96%';        // 에디터 가로폭입니다.
    editor5.inputForm = 'PROF_HTML';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
    editor5.run();

    editor6 = new cheditor();              // 에디터 개체를 생성합니다.
    editor6.config.editorHeight = '200px';     // 에디터 세로폭입니다.
    editor6.config.editorWidth = '96%';        // 에디터 가로폭입니다.
    editor6.inputForm = 'OFF_PROF_HTML';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
    editor6.run();


    if($("input[name='CATEGORY_CODE']").length == $("input[name='CATEGORY_CODE']:checked").length){
        $("#allCheck").attr("checked", "checked");
    }
    $("#USER_PWD,#USER_PWD2").val(new String(Base64.decode($("#USER_PWD").val())));

    $("#allCheck").focus();

    $('input[type="checkbox"][name="TeacherOnOff_YN"]').click(function(){
        //클릭 이벤트 발생한 요소가 선택 상태일 경우
        //체크된 요소 확인후 복수개 선택되있을 경우 체크 해제
        if ($(this).prop('checked') && $('input[type="checkbox"][name="TeacherOnOff_YN"]:checked').size()>1) {
            $(this).prop('checked', false);
        	alert('두개 이상 선택할 수 없습니다.');
        }
    });
});
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="USER_ID" name="USER_ID" value="${view[0].USER_ID}">

    <h2>● 강의 관리 > <strong>강사관리</strong></h2>
    <table class="table01">
        <tr>
            <th width="10%">분류</th>
            <td colspan="3">
                <input type="checkbox" id="allCheck" name="allCheck" VALUE="" onclick="fn_CheckAll('allCheck', 'CATEGORY_CODE')"/>전체 &nbsp;
                <c:forEach items="${kindlist}" var="item" varStatus="loop">
                    <c:set var="CHECKEDSET" value="" />
                    <c:forEach items="${mkindlist}" var="mitem" varStatus="mloop">
                        <c:if test="${item.CODE eq mitem.CATEGORY_CODE}" >
                            <c:set var="CHECKEDSET" value="checked='checked'" />
                            <input type="hidden" name="ORI_CATEGORY_CODE" VALUE="${item.CODE }" />
                        </c:if>
                    </c:forEach>
                    <input type="checkbox" name="CATEGORY_CODE" VALUE="${item.CODE}" ${CHECKEDSET}/>${item.NAME} &nbsp;
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th width="10%">온오프구분</th>
            <td>
                <input type="checkbox" id="TeacherOnOff_YN" name="TeacherOnOff_YN" VALUE="3" <c:if test="${view[0].TeacherOnOff_YN == '3' }">checked="checked"</c:if>/>전체 &nbsp;
                <input type="checkbox" id="TeacherOnOff_YN" name="TeacherOnOff_YN" VALUE="1" <c:if test="${view[0].TeacherOnOff_YN == '1' }">checked="checked"</c:if>/>온라인 &nbsp;
                <input type="checkbox" id="TeacherOnOff_YN" name="TeacherOnOff_YN" VALUE="2" <c:if test="${view[0].TeacherOnOff_YN == '2' }">checked="checked"</c:if>/>오프라인 &nbsp;
            </td>
            <th width="10%">사이트구분</th>
            <td>
                <input type="radio" id="USER_POSITION" name="USER_POSITION" VALUE="PUB" <c:if test="${view[0].USER_POSITION == 'PUB' }">checked</c:if>/>공무원 &nbsp;
                <input type="radio" id="USER_POSITION" name="USER_POSITION" VALUE="COP" <c:if test="${view[0].USER_POSITION == 'COP' }">checked</c:if>/>경찰 &nbsp;
                <input type="radio" id="USER_POSITION" name="USER_POSITION" VALUE="GWJ" <c:if test="${view[0].USER_POSITION == 'GWJ' }">checked</c:if>/>광주 &nbsp;
                <input type="radio" id="USER_POSITION" name="USER_POSITION" VALUE="ALL" <c:if test="${view[0].USER_POSITION == 'ALL' }">checked</c:if>/>전체 &nbsp;
            </td>
        </tr>
        <tr>
            <th width="10%">회원아이디</th>
            <td colspan="3">
                ${view[0].USER_ID}
            </td>
        </tr>

        <tr>
            <th width="10%">비밀번호</th>
            <td>
                <input type="password" id="USER_PWD" name="USER_PWD" value="" size="20"  maxlength="20" title="비밀번호" style="width:28%;background:#FFECEC;"/>
            </td>
            <th width="10%">비밀번호확인</th>
            <td>
                <input type="password" id="USER_PWD2" name="USER_PWD2" value="" size="20"  maxlength="20" title="비밀번호확인" style="width:28%;background:#FFECEC;"/>
            </td>
        </tr>
        <tr>
            <th>성명</th>
            <td>
                <input type="text" id="USER_NM" name="USER_NM" value="${view[0].USER_NM}" size="20"  maxlength="25" title="성명" style="width:28%;background:#FFECEC;"/>
            </td>
            <th>별명</th>
            <td>
                <input type="text" id="USER_NICKNM" name="USER_NICKNM" value="${view[0].USER_NICKNM}" size="20"  maxlength="50" title="별명" style="width:18%;"/>
            </td>
        </tr>
        <tr>
            <th>생년월일</th>
            <td>
                <input type="text" id="BIRTH_DAY" name="BIRTH_DAY" value="${view[0].BIRTH_DAY}" size="20"  maxlength="8" title="생년월일" style="width:18%;"/>
            </td>
            <th>이메일</th>
            <td>
                <input type="text" id="EMAIL" name="EMAIL" value="${view[0].EMAIL}" size="20"  maxlength="80" title="이메일" style="width:28%;"/>
            </td>
        </tr>
        <tr>
            <th>입금계좌</th>
            <td>
                <input type="text" id="ACCOUNT" name="ACCOUNT" value="${view[0].ACCOUNT}" size="20"  maxlength="60" title="입금계좌" style="width:28%;"/>
            </td>
            <th>전화번호</th>
            <td>
                <input type="text" id="PHONE_NO" name="PHONE_NO" value="${view[0].PHONE_NO}" size="20"  maxlength="20" title="전화번호" style="width:18%;ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/>
            </td>
        </tr>
        <tr valign='middle'>
            <th>강사지급률<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <input type="text" id="PAYMENT" name="PAYMENT" value="${view[0].PAYMENT}" size="20"  maxlength="3" title="강사지급률" style="width:18%;background:#FFECEC;ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/>%
            </td>
            <th>강사지급률<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <input type="text" id="OFF_PAYMENT" name="OFF_PAYMENT" value="${view[0].OFF_PAYMENT}" size="20"  maxlength="3" title="강사지급률" style="width:18%;background:#FFECEC;ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/>%
            </td>
        </tr>



        <%-- #{TeacherOnOff_YN}, #{PRF_ONPIC1}, #{PRF_ONPIC2}, #{PRF_ONPIC3}, #{PRF_OFFPIC1}, #{PRF_OFFPIC2}, #{PRF_OFFPIC3} --%>
        <tr valign='middle'>
            <th>사진<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PRF_ONPIC1}" ><div style="padding-top:5px">사진(대) : <input type="file" id="PRF_ONPIC1" name="PRF_ONPIC1" title="사진(중)" /></div></c:if>
                <c:if test="${!empty view[0].PRF_ONPIC1}" >
                    <div style="padding-top:5px">
                        <div>사진(대) : <input type="file" id="PRF_ONPIC1" name="PRF_ONPIC1" title="사진(대)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_ONPIC1_DEL" name="PRF_ONPIC1_DEL"/></span><input type="hidden" id="PRF_ONPIC1_DELNM" name="PRF_ONPIC1_DELNM" value="${view[0].PRF_ONPIC1}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_ONPIC1}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].PRF_ONPIC2}" ><div style="padding-top:5px">사진(중) : <input type="file" id="PRF_ONPIC2" name="PRF_ONPIC2" title="사진(중)" /></div></c:if>
                <c:if test="${!empty view[0].PRF_ONPIC2}" >
                    <div style="padding-top:5px">
                        <div>사진(중) : <input type="file" id="PRF_ONPIC2" name="PRF_ONPIC2" title="사진(중)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_ONPIC2_DEL" name="PRF_ONPIC2_DEL"/></span><input type="hidden" id="PRF_ONPIC2_DELNM" name="PRF_ONPIC2_DELNM" value="${view[0].PRF_ONPIC2}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_ONPIC2}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].PRF_ONPIC3}" ><div style="padding-top:5px">사진(이벤트) : <input type="file" id="PRF_ONPIC3" name="PRF_ONPIC3" title="사진(이벤트)" /></div></c:if>
                <c:if test="${!empty view[0].PRF_ONPIC3}" >
                    <div style="padding-top:5px">
                        <div>사진(소) : <input type="file" id="PRF_ONPIC3" name="PRF_ONPIC3" title="사진(중)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_ONPIC3_DEL" name="PRF_ONPIC3_DEL"/></span><input type="hidden" id="PRF_ONPIC3_DELNM" name="PRF_ONPIC3_DELNM" value="${view[0].PRF_ONPIC3}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_ONPIC3}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
            </td>
            <th>사진<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PRF_OFFPIC1}" ><div style="padding-top:5px">사진(대) : <input type="file" id="PRF_OFFPIC1" name="PRF_OFFPIC1" title="사진(대)" /></div></c:if>
                <c:if test="${!empty view[0].PRF_OFFPIC1}" >
                    <div style="padding-top:5px">
                        <div>사진(대) : <input type="file" id="PRF_OFFPIC1" name="PRF_OFFPIC1" title="사진(대)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_OFFPIC1_DEL" name="PRF_OFFPIC1_DEL"/></span><input type="hidden" id="PRF_OFFPIC1_DELNM" name="PRF_OFFPIC1_DELNM" value="${view[0].PRF_OFFPIC1}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_OFFPIC1}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].PRF_OFFPIC2}" ><div style="padding-top:5px">사진(중) : <input type="file" id="PRF_OFFPIC2" name="PRF_OFFPIC2" title="사진(중)" /></div></c:if>
                <c:if test="${!empty view[0].PRF_OFFPIC2}" >
                    <div style="padding-top:5px">
                        <div>사진(중) : <input type="file" id="PRF_OFFPIC2" name="PRF_OFFPIC2" title="사진(중)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_OFFPIC2_DEL" name="PRF_OFFPIC2_DEL"/></span><input type="hidden" id="PRF_OFFPIC2_DELNM" name="PRF_OFFPIC2_DELNM" value="${view[0].PRF_OFFPIC2}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_OFFPIC2}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].PRF_OFFPIC3}" ><div style="padding-top:5px">사진(소) : <input type="file" id="PRF_OFFPIC3" name="PRF_OFFPIC3" title="사진(소)" /></div></c:if>
                <c:if test="${!empty view[0].PRF_OFFPIC3}" >
                    <div style="padding-top:5px">
                        <div>사진(소) : <input type="file" id="PRF_OFFPIC3" name="PRF_OFFPIC3" title="사진(소)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_OFFPIC3_DEL" name="PRF_OFFPIC3_DEL"/></span><input type="hidden" id="PRF_OFFPIC3_DELNM" name="PRF_OFFPIC3_DELNM" value="${view[0].PRF_OFFPIC3}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_OFFPIC3}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
            </td>
        </tr>
        <tr valign='middle'>
            <th>메인이미지<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PIC1}" ><div><input type="file" id="PIC1" name="PIC1" title="메인이미지" disabled="disabled"/></div></c:if>
                <c:if test="${!empty view[0].PIC1}" >
                    <div>
                        <div><input type="file" id="PIC1" name="PIC1" title="메인이미지" disabled="disabled"/> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC1_DEL" name="PIC1_DEL"/></span><input type="hidden" id="PIC1_DELNM" name="PIC1_DELNM" value="${view[0].PIC1}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC1}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <!--
                <c:if test="${empty view[0].PIC2}" ><div style="padding-top:5px">사진(중) : <input type="file" id="PIC2" name="PIC2" title="사진(중)" /></div></c:if>
                <c:if test="${!empty view[0].PIC2}" >
                    <div style="padding-top:5px">
                        <div>사진(중) : <input type="file" id="PIC2" name="PIC2" title="사진(중)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC2_DEL" name="PIC2_DEL"/></span><input type="hidden" id="PIC2_DELNM" name="PIC2_DELNM" value="${view[0].PIC2}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC2}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].PIC3}" ><div style="padding-top:5px">사진(대) : <input type="file" id="PIC3" name="PIC3" title="사진(대)" /></div></c:if>
                <c:if test="${!empty view[0].PIC3}" >
                    <div style="padding-top:5px">
                        <div>사진(대) : <input type="file" id="PIC3" name="PIC3" title="사진(대)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC3_DEL" name="PIC3_DEL"/></span><input type="hidden" id="PIC3_DELNM" name="PIC3_DELNM" value="${view[0].PIC3}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC3}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].PIC4}" ><div style="padding-top:5px">사진(이벤트) : <input type="file" id="PIC4" name="PIC4" title="사진(이벤트)" /></div></c:if>
                <c:if test="${!empty view[0].PIC4}" >
                    <div style="padding-top:5px">
                        <div>사진(이벤트) : <input type="file" id="PIC4" name="PIC4" title="사진(중)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC4_DEL" name="PIC4_DEL"/></span><input type="hidden" id="PIC4_DELNM" name="PIC4_DELNM" value="${view[0].PIC4}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC4}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                -->
            </td>
            <th>메인이미지<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].OFF_PIC1}" ><div><input type="file" id="OFF_PIC1" name="OFF_PIC1" title="메인이미지" disabled="disabled"/></div></c:if>
                <c:if test="${!empty view[0].OFF_PIC1}" >
                    <div>
                        <div><input type="file" id="OFF_PIC1" name="OFF_PIC1" title="메인이미지" disabled="disabled"/> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC1_DEL" name="OFF_PIC1_DEL"/></span><input type="hidden" id="OFF_PIC1_DELNM" name="OFF_PIC1_DELNM" value="${view[0].OFF_PIC1}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC1}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <!--
                <c:if test="${empty view[0].OFF_PIC2}" ><div style="padding-top:5px">사진(중) : <input type="file" id="OFF_PIC2" name="OFF_PIC2" title="사진(중)" /></div></c:if>
                <c:if test="${!empty view[0].OFF_PIC2}" >
                    <div style="padding-top:5px">
                        <div>사진(중) : <input type="file" id="OFF_PIC2" name="OFF_PIC2" title="사진(중)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC2_DEL" name="OFF_PIC2_DEL"/></span><input type="hidden" id="OFF_PIC2_DELNM" name="OFF_PIC2_DELNM" value="${view[0].OFF_PIC2}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC2}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].OFF_PIC3}" ><div style="padding-top:5px">사진(대) : <input type="file" id="OFF_PIC3" name="OFF_PIC3" title="사진(대)" /></div></c:if>
                <c:if test="${!empty view[0].OFF_PIC3}" >
                    <div style="padding-top:5px">
                        <div>사진(대) : <input type="file" id="OFF_PIC3" name="OFF_PIC3" title="사진(대)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC3_DEL" name="OFF_PIC3_DEL"/></span><input type="hidden" id="OFF_PIC3_DELNM" name="OFF_PIC3_DELNM" value="${view[0].OFF_PIC3}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC3}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].OFF_PIC4}" ><div style="padding-top:5px">사진(이벤트) : <input type="file" id="OFF_PIC4" name="OFF_PIC4" title="사진(이벤트)" /></div></c:if>
                <c:if test="${!empty view[0].OFF_PIC4}" >
                    <div style="padding-top:5px">
                        <div>사진(이벤트) : <input type="file" id="OFF_PIC4" name="OFF_PIC4" title="사진(이벤트)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC4_DEL" name="OFF_PIC4_DEL"/></span><input type="hidden" id="OFF_PIC4_DELNM" name="OFF_PIC4_DELNM" value="${view[0].OFF_PIC4}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC4}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${empty view[0].OFF_PIC5}" ><div style="padding-top:5px">사진(이벤트2) : <input type="file" id="OFF_PIC5" name="OFF_PIC5" title="사진(이벤트2)" /></div></c:if>
                <c:if test="${!empty view[0].OFF_PIC5}" >
                    <div style="padding-top:5px">
                        <div>사진(이벤트)2 : <input type="file" id="OFF_PIC5" name="OFF_PIC5" title="사진(이벤트2)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC5_DEL" name="OFF_PIC5_DEL"/></span><input type="hidden" id="OFF_PIC5_DELNM" name="OFF_PIC5_DELNM" value="${view[0].OFF_PIC5}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC5}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                -->
            </td>
        </tr>
        <tr valign='middle'>
            <th>서브이미지<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PIC2}" ><div><input type="file" id="PIC2" name="PIC2" title="서브이미지" /></div></c:if>
                <c:if test="${!empty view[0].PIC2}" >
                    <div>
                        <div><input type="file" id="PIC2" name="PIC2" title="서브이미지" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC2_DEL" name="PIC2_DEL"/></span><input type="hidden" id="PIC2_DELNM" name="PIC2_DELNM" value="${view[0].PIC2}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC2}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
            </td>
            <th>서브이미지<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].OFF_PIC2}" ><div><input type="file" id="OFF_PIC2" name="OFF_PIC2" title="서브이미지" /></div></c:if>
                <c:if test="${!empty view[0].OFF_PIC2}" >
                    <div>
                        <div><input type="file" id="OFF_PIC2" name="OFF_PIC2" title="서브이미지" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC2_DEL" name="OFF_PIC2_DEL"/></span><input type="hidden" id="OFF_PIC2_DELNM" name="OFF_PIC2_DELNM" value="${view[0].OFF_PIC2}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC2}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
            </td>
        </tr>
        <tr valign='middle'>
            <th>대표강의 등록<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <input type="text" id="ON_URL" name="ON_URL" value="${view[0].ON_URL}" size="20"  maxlength="200" title="대표강의" style="width:96%;"/>
            </td>
            <th>대표강의 등록<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <input type="text" id="OFF_URL" name="OFF_URL" value="${view[0].ON_URL}" size="20"  maxlength="200" title="대표강의" style="width:96%;"/>
            </td>
        </tr>
        <tr valign='middle'>
            <th>무료강좌 썸네일<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PIC6}" >
                <div><input type="file" id="PIC6" name="PIC6" title="무료강좌 썸네일" />
               <!--  &nbsp;썸네일크기 : <input type="text" id="PIC6X" name="PIC6X" value="" title="넓이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> X
                <input type="text" id="PIC6Y" name="PIC6Y" value="" title="높이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> -->
                </div>
                </c:if>
                <c:if test="${!empty view[0].PIC6}" >
                    <div>
                        <div><input type="file" id="PIC6" name="PIC6" title="무료강좌 썸네일" />
                        <!-- &nbsp;썸네일크기 : <input type="text" id="PIC6X" name="PIC6X" value="" title="넓이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> X
                        <input type="text" id="PIC6Y" name="PIC6Y" value="" title="높이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> -->
                        <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC6_DEL" name="PIC6_DEL"/></span><input type="hidden" id="PIC6_DELNM" name="PIC6_DELNM" value="${view[0].PIC6}"/>
                        </div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC6}"/>"></div>
                    </div>
                </c:if>
            </td>
            <th>무료강좌 썸네일<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].OFF_PIC6}" >
                <div><input type="file" id="OFF_PIC6" name="OFF_PIC6" title="무료강좌 썸네일" />
               <!--  &nbsp;썸네일크기 : <input type="text" id="OFF_PIC6X" name="OFF_PIC6X" value="" title="넓이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> X
                <input type="text" id="OFF_PIC6Y" name="OFF_PIC6Y" value="" title="높이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> -->
                </div>
                </c:if>
                <c:if test="${!empty view[0].OFF_PIC6}" >
                    <div>
                        <div><input type="file" id="OFF_PIC6" name="OFF_PIC6" title="무료강좌 썸네일" />
                        <!-- &nbsp;썸네일크기 : <input type="text" id="OFF_PIC6X" name="OFF_PIC6X" value="" title="넓이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> X
                        <input type="text" id="OFF_PIC6Y" name="OFF_PIC6Y" value="" title="높이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> -->
                        <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC6_DEL" name="OFF_PIC6_DEL"/></span><input type="hidden" id="OFF_PIC6_DELNM" name="OFF_PIC6_DELNM" value="${view[0].OFF_PIC6}"/>
                        </div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC6}"/>"></div>
                    </div>
                </c:if>
            </td>
        </tr>
        <tr valign='middle'>
            <th>자료실/질문게시판<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <input type="radio" id="BRD_YN" name="BRD_YN" class="i_check" value="Y" <c:if test="${view[0].BRD_YN=='Y'}">checked="checked"</c:if>><label for="a2">활성</label>&nbsp;&nbsp;
                <input type="radio" id="BRD_YN" name="BRD_YN" class="i_check" value="N" <c:if test="${view[0].BRD_YN=='N'}">checked="checked"</c:if> ><label for="a3">비활성</label>
            </td>
            <th>자료실/질문게시판<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <input type="radio" id="OFF_BRD_YN" name="OFF_BRD_YN" class="i_check" value="Y" <c:if test="${view[0].OFF_BRD_YN=='Y'}">checked="checked"</c:if> ><label for="a2">활성</label>&nbsp;&nbsp;
                <input type="radio" id="OFF_BRD_YN" name="OFF_BRD_YN" class="i_check" value="N" <c:if test="${view[0].OFF_BRD_YN=='N'}">checked="checked"</c:if> ><label for="a3">비활성</label>
            </td>
        </tr>
        <tr valign='middle'>
            <th>이벤트배너 등록<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PIC7}" ><div>이미지 : <input type="file" id="PIC7" name="PIC7" title="이벤트배너 등록" /></div></c:if>
                <c:if test="${!empty view[0].PIC7}" >
                    <div>
                        <div>이미지 : <input type="file" id="PIC7" name="PIC7" title="이벤트배너 등록" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC7_DEL" name="PIC7_DEL"/></span><input type="hidden" id="PIC7_DELNM" name="PIC7_DELNM" value="${view[0].PIC7}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC7}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <br/>URL : <input type="text" id="PIC7_TURL" name="PIC7_TURL" value="${view[0].PIC7_TURL}" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
            </td>
            <th>이벤트배너 등록<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].OFF_PIC7}" ><div>이미지 : <input type="file" id="OFF_PIC7" name="OFF_PIC7" title="이벤트배너 등록" /></div></c:if>
                <c:if test="${!empty view[0].OFF_PIC7}" >
                    <div>
                        <div>이미지 : <input type="file" id="OFF_PIC7" name="OFF_PIC7" title="이벤트배너 등록" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC7_DEL" name="OFF_PIC7_DEL"/></span><input type="hidden" id="OFF_PIC7_DELNM" name="OFF_PIC7_DELNM" value="${view[0].OFF_PIC7}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC7}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <br/>URL : <input type="text" id="OFF_PIC7_TURL" name="OFF_PIC7_TURL" value="${view[0].OFF_PIC7_TURL}" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
            </td>
        </tr>
        <tr valign='middle'>
            <th>스카이배너 등록<br/>(온라인)<br/><input type="button" id="addButton" onclick="javascript:addAttchFile('skyfileControl',7);" value="추가"></th>
            <td style="vertical-align:middle;">
                <div class="item" id="skyfileControl">
                <c:set var="indexo" value="0"/>
                <c:if test="${empty view[0].PIC8}" >
                    <div id="row1">이미지1 : <input type="file" id="PIC8" name="PIC8" title="스카이배너 등록" /></div>
                     <br/>URL1 : <input type="text" id="PIC8_TURL" name="PIC8_TURL" value="" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
                </c:if>
                <c:if test="${!empty view[0].PIC8}" >
                    <c:set var="indexo" value="${indexo+1}"/>
                    <div id="row${indexo}">
                        이미지1 : <input type="file" id="PIC8" name="PIC8" title="스카이배너 등록" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC8_DEL" name="PIC8_DEL"/></span><input type="hidden" id="PIC8_DELNM" name="PIC8_DELNM" value="${view[0].PIC8}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC8}"/>" width="150px" height="100px"></div>
                         <br/>URL1 : <input type="text" id="PIC8_TURL" name="PIC8_TURL" value="${view[0].PIC8_TURL}" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
                    </div>
                </c:if>
                <c:if test="${!empty view[0].PIC9}" >
                    <c:set var="indexo" value="${indexo+1}"/>
                    <br/><div id="row${indexo}">
                        이미지2 : <input type="file" id="PIC9" name="PIC9" title="스카이배너 등록" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC9_DEL" name="PIC9_DEL"/></span><input type="hidden" id="PIC9_DELNM" name="PIC9_DELNM" value="${view[0].PIC9}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC9}"/>" width="150px" height="100px"></div>
                        <br/>URL2 : <input type="text" id="PIC9_TURL" name="PIC9_TURL" value="${view[0].PIC9_TURL}" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
                    </div>
                </c:if>
                <c:if test="${!empty view[0].PIC10}" >
                    <c:set var="indexo" value="${indexo+1}"/>
                    <br/><div id="row${indexo}">
                        이미지3 : <input type="file" id="PIC10" name="PIC10" title="스카이배너 등록" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC10_DEL" name="PIC10_DEL"/></span><input type="hidden" id="PIC10_DELNM" name="PIC10_DELNM" value="${view[0].PIC10}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC10}"/>" width="150px" height="100px"></div>
                        <br/>URL3 : <input type="text" id="PIC10_TURL" name="PIC10_TURL" value="${view[0].PIC10_TURL}" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
                    </div>
                </c:if>
                </div>
            </td>
            <th>스카이배너 등록<br/>(오프라인)<br/><input type="button" id="addButton" onclick="javascript:addAttchFileF('skyfileControlF',7);" value="추가"></th>
            <td style="vertical-align:middle;">
                <div class="item" id="skyfileControlF">
                <c:set var="indexf" value="0"/>
                <c:if test="${empty view[0].OFF_PIC8}" >
                    <div id="rowf1">이미지1 : <input type="file" id="OFF_PIC8" name="OFF_PIC8" title="스카이배너 등록" /></div>
                    <br/>URL1 : <input type="text" id="OFF_PIC8_TURL" name="OFF_PIC8_TURL" value="" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
                </c:if>
                <c:if test="${!empty view[0].OFF_PIC8}" >
                    <c:set var="indexf" value="${indexf+1}"/>
                    <div id="rowf${indexf}">
                        이미지1 : <input type="file" id="OFF_PIC8" name="OFF_PIC8" title="스카이배너 등록" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC8_DEL" name="OFF_PIC8_DEL"/></span><input type="hidden" id="OFF_PIC8_DELNM" name="OFF_PIC8_DELNM" value="${view[0].OFF_PIC8}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC8}"/>" width="150px" height="100px"></div>
                        <br/>URL1 : <input type="text" id="OFF_PIC8_TURL" name="OFF_PIC8_TURL" value="${view[0].OFF_PIC8_TURL}" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
                    </div>
                </c:if>
                <c:if test="${!empty view[0].OFF_PIC9}" >
                    <c:set var="indexf" value="${indexf+1}"/>
                    <br/><div id="rowf${indexf}">
                        이미지2 : <input type="file" id="OFF_PIC9" name="OFF_PIC9" title="스카이배너 등록" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC9_DEL" name="OFF_PIC9_DEL"/></span><input type="hidden" id="OFF_PIC9_DELNM" name="OFF_PIC9_DELNM" value="${view[0].OFF_PIC9}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC9}"/>" width="150px" height="100px"></div>
                        <br/>URL2 : <input type="text" id="OFF_PIC9_TURL" name="OFF_PIC9_TURL" value="${view[0].OFF_PIC9_TURL}" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
                    </div>
                </c:if>
                <c:if test="${!empty view[0].OFF_PIC10}" >
                    <c:set var="indexf" value="${indexf+1}"/>
                    <br/><div id="rowf${indexf}">
                        이미지3 : <input type="file" id="OFF_PIC10" name="OFF_PIC10" title="스카이배너 등록" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC10_DEL" name="OFF_PIC10_DEL"/></span><input type="hidden" id="OFF_PIC10_DELNM" name="OFF_PIC10_DELNM" value="${view[0].OFF_PIC10}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC10}"/>" width="150px" height="100px"></div>
                         <br/>URL3 : <input type="text" id="OFF_PIC10_TURL" name="OFF_PIC10_TURL" value="${view[0].OFF_PIC10_TURL}" size="20"  maxlength="200" title="이벤트배너" style="width:90%;"/>
                    </div>
                </c:if>
                </div>
            </td>
        </tr>
        <tr valign='middle'>
            <th>교수님소개이미지<br/>(온라인)<br/><input type="button" id="addButton" onclick="javascript:addAttchFile('fileControl',2);" value="추가"></th>
            <td style="vertical-align:middle;">
                <div class="item" id="fileControl">
                <c:set var="indexo" value="0"/>
                <c:if test="${empty view[0].PIC3}" >
                    <div id="row1">이미지1 : <input type="file" id="PIC3" name="PIC3" title="교수님소개이미지" /></div>
                </c:if>
                <c:if test="${!empty view[0].PIC3}" >
                    <c:set var="indexo" value="${indexo+1}"/>
                    <div id="row${indexo}">
                        이미지1 : <input type="file" id="PIC3" name="PIC3" title="교수님소개이미지" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC3_DEL" name="PIC3_DEL"/></span><input type="hidden" id="PIC3_DELNM" name="PIC3_DELNM" value="${view[0].PIC3}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC3}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${!empty view[0].PIC4}" >
                    <c:set var="indexo" value="${indexo+1}"/>
                    <br/><div id="row${indexo}">
                        이미지2 : <input type="file" id="PIC4" name="PIC4" title="교수님소개이미지" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC4_DEL" name="PIC4_DEL"/></span><input type="hidden" id="PIC4_DELNM" name="PIC4_DELNM" value="${view[0].PIC4}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC4}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${!empty view[0].PIC5}" >
                    <c:set var="indexo" value="${indexo+1}"/>
                    <br/><div id="row${indexo}">
                        이미지3 : <input type="file" id="PIC5" name="PIC5" title="교수님소개이미지" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PIC5_DEL" name="PIC5_DEL"/></span><input type="hidden" id="PIC5_DELNM" name="PIC5_DELNM" value="${view[0].PIC5}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PIC5}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                </div>
            </td>
            <th>교수님소개이미지<br/>(오프라인)<br/><input type="button" id="addButton" onclick="javascript:addAttchFileF('fileControlF',2);" value="추가"></th>
            <td style="vertical-align:middle;">
                <div class="item" id="fileControlF">
                <c:set var="indexf" value="0"/>
                <c:if test="${empty view[0].OFF_PIC3}" >
                    <div id="rowf1">이미지1 : <input type="file" id="OFF_PIC3" name="OFF_PIC3" title="교수님소개이미지" /></div>
                </c:if>
                <c:if test="${!empty view[0].OFF_PIC3}" >
                    <c:set var="indexf" value="${indexf+1}"/>
                    <div id="rowf${indexf}">
                        이미지1 : <input type="file" id="OFF_PIC3" name="OFF_PIC3" title="교수님소개이미지" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC3_DEL" name="OFF_PIC3_DEL"/></span><input type="hidden" id="OFF_PIC3_DELNM" name="OFF_PIC3_DELNM" value="${view[0].OFF_PIC3}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC3}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${!empty view[0].OFF_PIC4}" >
                    <c:set var="indexf" value="${indexf+1}"/>
                    <br/><div id="rowf${indexf}">
                        이미지2 : <input type="file" id="OFF_PIC4" name="OFF_PIC4" title="교수님소개이미지" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC4_DEL" name="OFF_PIC4_DEL"/></span><input type="hidden" id="OFF_PIC4_DELNM" name="OFF_PIC4_DELNM" value="${view[0].OFF_PIC4}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC4}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                <c:if test="${!empty view[0].OFF_PIC5}" >
                    <c:set var="indexf" value="${indexf+1}"/>
                    <br/><div id="rowf${indexf}">
                        이미지3 : <input type="file" id="OFF_PIC5" name="OFF_PIC5" title="교수님소개이미지" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PIC5_DEL" name="OFF_PIC5_DEL"/></span><input type="hidden" id="OFF_PIC5_DELNM" name="OFF_PIC5_DELNM" value="${view[0].OFF_PIC5}"/>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PIC5}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                </div>
            </td>
        </tr>
        <tr valign='middle'>
            <th>리스트배너 등록<br/>(온라인)<br/></th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PRF_LISTONBANNER}" ><div style="padding-top:5px">이미지 : <input type="file" id="PRF_LISTONBANNER" name="PRF_LISTONBANNER" title="이미지" /></div></c:if>
                <c:if test="${!empty view[0].PRF_LISTONBANNER}" >
                    <div style="padding-top:5px">
                        <div>이미지 : <input type="file" id="PRF_LISTONBANNER" name="PRF_LISTONBANNER" title="사진(소)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_LISTONBANNER_DEL" name="PRF_LISTONBANNER_DEL"/></span><input type="hidden" id="PRF_LISTONBANNER_DELNM" name="PRF_LISTONBANNER_DELNM" value="${view[0].PRF_LISTONBANNER}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_LISTONBANNER}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                </div>
            </td>
            <th>리스트배너 등록<br/>(오프라인)<br/></th>
            <td style="vertical-align:middle;">
                <div class="item" id="fileControlF">
                <c:if test="${empty view[0].PRF_LISTOFFBANNER}" ><div style="padding-top:5px">이미지 : <input type="file" id="PRF_LISTOFFBANNER" name="PRF_LISTOFFBANNER" title="이미지" /></div></c:if>
                <c:if test="${!empty view[0].PRF_LISTOFFBANNER}" >
                    <div style="padding-top:5px">
                        <div>이미지 : <input type="file" id="PRF_LISTOFFBANNER" name="PRF_LISTOFFBANNER" title="사진(소)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_LISTOFFBANNER_DEL" name="PRF_LISTOFFBANNER_DEL"/></span><input type="hidden" id="PRF_LISTOFFBANNER_DELNM" name="PRF_LISTOFFBANNER_DELNM" value="${view[0].PRF_LISTOFFBANNER}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_LISTOFFBANNER}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                </div>
            </td>
        </tr>
        <tr valign='middle'>
            <th>약력<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="PROFILE" name="PROFILE" ROWS="5" style="width:96%;">${view[0].PROFILE}</textarea>
            </td>
            <th>약력<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="OFF_PROFILE" name="OFF_PROFILE" ROWS="5" style="width:96%;">${view[0].OFF_PROFILE}</textarea>
            </td>
        </tr>
        <tr valign='middle'>
            <th>타이틀<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <input type="text" id="TITLE" name="TITLE" value="${view[0].TITLE}" size="20"  maxlength="100" title="" style="width:96%;"/>
            </td>
            <th>타이틀<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <input type="text" id="OFF_TITLE" name="OFF_TITLE" value="${view[0].OFF_TITLE}" size="20"  maxlength="100" title="" style="width:96%;"/>
            </td>
        </tr>
        <tr>
            <th>주요저서<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="BOOK_LOG" name="BOOK_LOG" cols="50" rows="10" class="i_text" title="주요저서" style="width:96%;">${view[0].BOOK_LOG}</textarea>
            </td>
            <th>주요저서<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="OFF_BOOK_LOG" name="OFF_BOOK_LOG" cols="50" rows="10" class="i_text" title="주요저서" style="width:96%;">${view[0].OFF_BOOK_LOG}</textarea>
            </td>
        </tr>
        <tr valign='middle'>
            <th>연간강의일정<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="YPLAN" name="YPLAN" cols="50" rows="20" class="i_text" title="연간강의일정(온라인)" style="width:96%;">${view[0].YPLAN}</textarea>
            </td>
            <th>연간강의일정<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="OFF_YPLAN" name="OFF_YPLAN" cols="50" rows="20" class="i_text" title="연간강의일정(오프라인)" style="width:96%;">${view[0].OFF_YPLAN}</textarea>
            </td>
        </tr>
        <tr valign='middle'>
            <th>강의안내<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="LECINFO" name="LECINFO" cols="50" rows="20" class="i_text" title="강의안내(온라인)" style="width:96%;">${view[0].LECINFO}</textarea>
            </td>
            <th>강의안내<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="OFF_LECINFO" name="OFF_LECINFO" cols="50" rows="20" class="i_text" title="강의안내(오프라인)" style="width:96%;">${view[0].OFF_LECINFO}</textarea>
            </td>
        </tr>
        <tr valign='middle'>
            <th>교수소개 이미지<br/>(온라인)<br/></th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PROF_IMG}" ><div style="padding-top:5px">이미지 : <input type="file" id="PROF_IMG" name="PROF_IMG" title="이미지" /></div></c:if>
                <c:if test="${!empty view[0].PROF_IMG}" >
                    <div style="padding-top:5px">
                        <div>이미지 : <input type="file" id="PROF_IMG" name="PROF_IMG" title="사진(소)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PROF_IMG_DEL" name="PROF_IMG_DEL"/></span><input type="hidden" id="PROF_IMG_DELNM" name="PROF_IMG_DELNM" value="${view[0].PROF_IMG}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PROF_IMG}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                </div>
            </td>
            <th>교수소개 이미지<br/>(오프라인)<br/></th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].OFF_PROF_IMG}" ><div style="padding-top:5px">이미지 : <input type="file" id="OFF_PROF_IMG" name="OFF_PROF_IMG" title="이미지" /></div></c:if>
                <c:if test="${!empty view[0].OFF_PROF_IMG}" >
                    <div style="padding-top:5px">
                        <div>이미지 : <input type="file" id="OFF_PROF_IMG" name="OFF_PROF_IMG" title="사진(소)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="OFF_PROF_IMG_DEL" name="OFF_PROF_IMG_DEL"/></span><input type="hidden" id="OFF_PROF_IMG_DELNM" name="OFF_PROF_IMG_DELNM" value="${view[0].OFF_PROF_IMG}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].OFF_PROF_IMG}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
            </td>
        </tr>
        <tr valign='middle'>
            <th>교수소개 HTML<br/>(온라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="PROF_HTML" name="PROF_HTML" cols="50" rows="20" class="i_text" title="교수소개 HTML(온라인)" style="width:96%;">${view[0].PROF_HTML}</textarea>
            </td>
            <th>교수소개 HTML<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <textarea id="OFF_PROF_HTML" name="OFF_PROF_HTML" cols="50" rows="20" class="i_text" title="교수소개 HTML(오프라인)" style="width:96%;">${view[0].OFF_PROF_HTML}</textarea>
            </td>
        </tr>
        <tr valign='middle'>
            <th>메뉴상단 공통이미지<br/>(온라인)<br/></th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PRF_TOPONIMG}" ><div style="padding-top:5px">이미지 : <input type="file" id="PRF_TOPONIMG" name="PRF_TOPONIMG" title="이미지" /></div></c:if>
                <c:if test="${!empty view[0].PRF_TOPONIMG}" >
                    <div style="padding-top:5px">
                        <div>이미지 : <input type="file" id="PRF_TOPONIMG" name="PRF_TOPONIMG" title="사진(소)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_TOPONIMG_DEL" name="PRF_TOPONIMG_DEL"/></span><input type="hidden" id="PRF_TOPONIMG_DELNM" name="PRF_TOPONIMG_DELNM" value="${view[0].PRF_TOPONIMG}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_TOPONIMG}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
                </div>
            </td>
            <th>메뉴상단 공통이미지<br/>(오프라인)<br/></th>
            <td style="vertical-align:middle;">
                <c:if test="${empty view[0].PRF_TOPOFFIMG}" ><div style="padding-top:5px">이미지 : <input type="file" id="PRF_TOPOFFIMG" name="PRF_TOPOFFIMG" title="이미지" /></div></c:if>
                <c:if test="${!empty view[0].PRF_TOPOFFIMG}" >
                    <div style="padding-top:5px">
                        <div>이미지 : <input type="file" id="PRF_TOPOFFIMG" name="PRF_TOPOFFIMG" title="사진(소)" /> <span>기존파일삭제 : </span><span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="PRF_TOPOFFIMG_DEL" name="PRF_TOPOFFIMG_DEL"/></span><input type="hidden" id="PRF_TOPOFFIMG_DELNM" name="PRF_TOPOFFIMG_DELNM" value="${view[0].PRF_TOPOFFIMG}"/></div>
                        <div style="padding:5px 0 5px 0;"><img src="<c:url value="/imgFileView.do?path=${view[0].PRF_TOPOFFIMG}"/>" width="150px" height="100px"></div>
                    </div>
                </c:if>
            </td>
        </tr>
        <tr valign='middle'>
            <th>상태<br/> (온라인)</th>
            <td style="vertical-align:middle;">
                <input type="radio" id="ON_OPENYN" name="ON_OPENYN" class="i_check" value="Y" <c:if test="${view[0].ON_OPENYN=='Y'}">checked="checked"</c:if>><label for="a2">활성</label>&nbsp;&nbsp;
                <input type="radio" id="ON_OPENYN" name="ON_OPENYN" class="i_check" value="N" <c:if test="${view[0].ON_OPENYN=='N'}">checked="checked"</c:if> ><label for="a3">비활성</label>
            </td>
            <th>상태<br/>(오프라인)</th>
            <td style="vertical-align:middle;">
                <input type="radio" id="OFF_OPENYN" name="OFF_OPENYN" class="i_check" value="Y" <c:if test="${view[0].OFF_OPENYN=='Y'}">checked="checked"</c:if>><label for="a2">활성</label>&nbsp;&nbsp;
                <input type="radio" id="OFF_OPENYN" name="OFF_OPENYN" class="i_check" value="N" <c:if test="${view[0].OFF_OPENYN=='N'}">checked="checked"</c:if> ><label for="a3">비활성</label>
            </td>
        </tr>
        <tr>
            <th>질답게시판<br/> (온라인)</th>
            <td>
                <select id="PRF_BRD_ON" name="PRF_BRD_ON">
                    <option value="Y" <c:if test="${view[0].PRF_BRD_ON eq 'Y' }">selected="selected"</c:if>>활성</option>
                    <option value="N" <c:if test="${view[0].PRF_BRD_ON eq 'N' }">selected="selected"</c:if>>비활성</option>
                </select>
            </td>
            <th>질답게시판<br/>(오프라인)</th>
            <td>
                <select id="PRF_BRD_OF" name="PRF_BRD_OF">
                    <option value="Y" <c:if test="${view[0].PRF_BRD_OF eq 'Y' }">selected="selected"</c:if>>활성</option>
                    <option value="N" <c:if test="${view[0].PRF_BRD_OF eq 'N' }">selected="selected"</c:if>>비활성</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>과목(온라인)</th>
            <td>
                <table width="500px" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="text-align:center;">
                            <select id="ORG_SUBJECT_CD" name="ORG_SUBJECT_CD" size="10" multiple style="width:150px;">
                                <c:forEach items="${eonsubjectlist}" var="item" varStatus="loop">
                                    <option value="${item.SUBJECT_CD}">${item.SUBJECT_NM}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align:center;" width="100px" height="143px">
                            <span class="btn_pack medium"><button type="button" onclick="javascript:fn_AddSel();">&gt;&gt; 추가</button></span>
                            <br/>
                            <span class="btn_pack medium"><button type="button" onclick="javascript:fn_DelSel();">&lt;&lt; 삭제</button></span>
                        </td>
                        <td style="text-align:center;">
                            <select id="SUBJECT_CD" name="SUBJECT_CD" size="10" multiple style="width:150px;">
                                <c:forEach items="${monsubjectlist}" var="item" varStatus="loop">
                                    <option value="${item.SUBJECT_CD}">${item.SUBJECT_NM}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
            <th>과목(오프라인)</th>
            <td>
                <table width="500px" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="text-align:center;">
                            <select id="ORG_OFF_SUBJECT_CD" name="ORG_OFF_SUBJECT_CD" size="10" multiple style="width:150px;">
                                <c:forEach items="${eoffsubjectlist}" var="item" varStatus="loop">
                                    <option value="${item.SUBJECT_CD}">${item.SUBJECT_NM}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align:center;" width="100px" height="143px">
                            <span class="btn_pack medium"><button type="button" onclick="javascript:fn_Off_AddSel();">&gt;&gt; 추가</button></span>
                            <br/>
                            <span class="btn_pack medium"><button type="button" onclick="javascript:fn_Off_DelSel();">&lt;&lt; 삭제</button></span>
                        </td>
                        <td style="text-align:center;">
                            <select id="OFF_SUBJECT_CD" name="OFF_SUBJECT_CD" size="10" multiple style="width:150px;">
                                <c:forEach items="${moffsubjectlist}" var="item" varStatus="loop">
                                    <option value="${item.SUBJECT_CD}">${item.SUBJECT_NM}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Submit();">수정</a></li>
        <li><a href="javascript:fn_List();">목록</a></li>
        <li><a href="javascript:fn_Delete();">삭제</a></li>
    </ul>
    <!--//버튼-->

</form>
</div>
<!--//content -->

<script type="text/javascript">
// All Checkbox Controller
function fn_CheckAll(id, name) {
    if($("#"+id).attr("checked") == "checked") {
        $("input[name="+name+"]").attr("checked", "checked");
    } else {
        $("input[name="+name+"]").removeAttr("checked");
    }
}

//온라인 과목삭제
function fn_DelSel() {
    for(var i=document.frm.SUBJECT_CD.options.length-1; i>=0; i--){
        if(document.frm.SUBJECT_CD.options[i].selected == true) {
        var newOption = new Option();
        newOption.value = document.frm.SUBJECT_CD.options[i].value;
        newOption.text = document.frm.SUBJECT_CD.options[i].text;
        document.frm.ORG_SUBJECT_CD.add(newOption);
        document.frm.SUBJECT_CD.remove(i);
        }
    }
}

//온라인 과목추가
function fn_AddSel() {
    for(var i=document.frm.ORG_SUBJECT_CD.options.length-1; i>=0; i--)
    if(document.frm.ORG_SUBJECT_CD.options[i].selected == true) {
        var newOption = new Option();
        newOption.value = document.frm.ORG_SUBJECT_CD.options[i].value;
        newOption.text = document.frm.ORG_SUBJECT_CD.options[i].text;
        document.frm.SUBJECT_CD.add(newOption);
        document.frm.ORG_SUBJECT_CD.remove(i);
    }

}

//오프라인 과목삭제
function fn_Off_DelSel() {
    for(var i=document.frm.OFF_SUBJECT_CD.options.length-1; i>=0; i--){
        if(document.frm.OFF_SUBJECT_CD.options[i].selected == true) {
        var newOption = new Option();
        newOption.value = document.frm.OFF_SUBJECT_CD.options[i].value;
        newOption.text = document.frm.OFF_SUBJECT_CD.options[i].text;
        document.frm.ORG_OFF_SUBJECT_CD.add(newOption);
        document.frm.OFF_SUBJECT_CD.remove(i);
        }
    }
}

//오프라인 과목추가
function fn_Off_AddSel() {
    for(var i=document.frm.ORG_OFF_SUBJECT_CD.options.length-1; i>=0; i--)
    if(document.frm.ORG_OFF_SUBJECT_CD.options[i].selected == true) {
        var newOption = new Option();
        newOption.value = document.frm.ORG_OFF_SUBJECT_CD.options[i].value;
        newOption.text = document.frm.ORG_OFF_SUBJECT_CD.options[i].text;
        document.frm.OFF_SUBJECT_CD.add(newOption);
        document.frm.ORG_OFF_SUBJECT_CD.remove(i);
    }

}

// 목록으로
function fn_List(){
    $("#frm").attr("action","<c:url value='/teacher/list.do' />");
    $("#frm").submit();
}

function addAttchFile(id,count){
    var divLength = $("#"+id+" div[id^='row']").length;
    var lastItemNo;
    var nNum;

    if(divLength >= 3){
        alert("최대 3개까지 가능합니다.");
        return;
    }

    if(divLength > 0){
        lastItemNo = $("#"+id+" div[id^='row']:last-child").attr('id').replace("row","");
        nNum = parseInt(lastItemNo) + 1;
    } else {
        nNum = 1;
    }
//     alert(nNum);
    var newitem = '<div id="row'+nNum+'">이미지'+nNum+' : <input type="file" name="PIC'+(nNum+count)+'" id="PIC'+(nNum+count)+'">';
    newitem += ' <input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow1(\'' + nNum +'\',\''+ id+'\');">';
    newitem += '<br/>URL'+count+' : <input type="text" id="PIC'+(nNum+count)+'_TURL" name="PIC'+(nNum+count)+'_TURL" value="" size="20"  maxlength="200" title="스카이배너" style="width:90%;"/></div>';
//     alert(newitem);
    $("#"+id).append(newitem);

}

function delRow(id, param){
    if(param == 'text') $("#rowText" + id).remove();
    else {
        $("#row" + id).remove();
    }
}

function delRow1(id, divid){
      $("#"+divid+" #row" + id).remove();
}
function addAttchFileF(id,count){
    var divLength = $("#"+id+" div[id^='rowf']").length;
    var lastItemNo;
    var nNum;

    if(divLength >= 3){
        alert("최대 3개까지 가능합니다.");
        return;
    }

    if(divLength > 0){
        lastItemNo = $("#"+id+" div[id^='rowf']:last-child").attr('id').replace("rowf","");
        nNum = parseInt(lastItemNo) + 1;
    } else {
        nNum = 1;
    }
    var newitem = '<div id="rowf'+nNum+'">이미지'+nNum+' : <input type="file" name="OFF_PIC'+(nNum+count)+'" id="OFF_PIC'+(nNum+count)+'">';
    newitem += ' <input type="button" name="delAttchButtonF" value="삭제" onclick="javascript:delRowF1(\'' + nNum +'\',\''+ id+'\');">';
    newitem += '<br/>URL'+count+' : <input type="text" id="OFF_PIC'+(nNum+count)+'_TURL" name="OFF_PIC'+(nNum+count)+'_TURL" value="" size="20"  maxlength="200" title="스카이배너" style="width:90%;"/></div>';
    $("#"+id).append(newitem);
}

function delRowF1(id, divid){
	$("#"+divid+" #row" + id).remove();
}

function delRowF(id, param){
    if(param == 'text') $("#rowText" + id).remove();
    else {
        $("#rowf" + id).remove();
    }
}


// 숫자만 입력
function fn_OnlyNumber(event) {
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) {
        return;
    } else {
        alert("숫자만 입력 가능합니다.");
        return false;
    }
}

// 숫자만 입력
function removeChar(event) {
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
        return;
    else
        event.target.value = event.target.value.replace(/[^0-9]/g, "");
}

// 입력값의 바이트 길이를 리턴
function getByteLength(input) {
    var byteLength = 0;
    for (var inx = 0; inx < input.value.length; inx++) {
        var oneChar = escape(input.value.charAt(inx));
        if ( oneChar.length == 1 ) {
            byteLength ++;
        } else if (oneChar.indexOf("%u") != -1) {
            byteLength += 3;
        } else if (oneChar.indexOf("%") != -1) {
            byteLength += oneChar.length/3;
            byteLength += oneChar.length/3;
        }
    }
    return byteLength;
}

// 입력값의 바이트 길이가 주어진 길이보다 크면 false를 리턴
function checkLenth(input, len) {
    if(getByteLength(input) > len) {
        return false;
    } else true;

}

// 수정
function fn_Submit(){
    $("#YPLAN").val(editor.outputBodyHTML());
    $("#OFF_YPLAN").val(editor2.outputBodyHTML());
    $("#LECINFO").val(editor3.outputBodyHTML());
    $("#OFF_LECINFO").val(editor4.outputBodyHTML());
    $("#PROF_HTML").val(editor5.outputBodyHTML());
    $("#OFF_PROF_HTML").val(editor6.outputBodyHTML());

//  if($.trim($("#USER_PWD").val())==""){
//      alert("비밀번호를 입력해주세요");
//      $("#USER_PWD").focus();
//      return;
//  }
//  if($.trim($("#USER_PWD2").val())==""){
//      alert("비밀번호 확인을 입력해주세요");
//      $("#USER_PWD2").focus();
//      return;
//  }
    if($.trim($("#USER_PWD").val())!=$.trim($("#USER_PWD2").val())){
        alert("비밀번호와 비밀번호확인 입력값이 틀립니다. 다시 확인해주세요");
        $("#USER_PWD").focus();
        return;
    }
    if($.trim($("#USER_NM").val())==""){
        alert("성명을 입력해주세요");
        $("#USER_NM").focus();
        return;
    }
    <c:if test="${MENUTYPE eq 'OM_ROOT'}" >
        if($.trim($("#PAYMENT").val())==""){
            alert("강사료 지급률을 입력해주세요");
            $("#PAYMENT").focus();
            return;
        }
    </c:if>
    <c:if test="${MENUTYPE ne 'OM_ROOT'}" >
        if($.trim($("#OFF_PAYMENT").val())==""){
            alert("강사료 지급률을 입력해주세요");
            $("#OFF_PAYMENT").focus();
            return;
        }
    </c:if>
    /*
    if($.trim($("#PROFILE").val())!=""){
        if(checkLenth(document.getElementById("PROFILE"),4000)==false){
            alert("☞ 영문 4000자,한글 1333자까지 입력이 가능합니다\n글자수가 너무 많습니다. 줄여주세요");
            return;
        }
    }
    */
    if(confirm("강사정보를 수정하시겠습니까?")){
        $("#SUBJECT_CD option").attr("selected","true");
        $("#OFF_SUBJECT_CD option").attr("selected","true");
//      $("#USER_PWD,#USER_PWD2").val(Base64.encode($("#USER_PWD").val()));
        $("#frm").attr("action","<c:url value='/teacher/update.do' />");
        $("#frm").submit();
    }
}

// 삭제
function fn_Delete(){
    if(confirm("정말 삭제하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/teacher/delete.do' />");
        $("#frm").submit();
    }
}
</script>
