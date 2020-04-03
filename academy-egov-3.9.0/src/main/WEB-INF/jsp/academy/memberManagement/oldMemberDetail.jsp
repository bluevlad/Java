<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SDATE" name="SDATE" value="${params.SDATE}"/>
<input type="hidden" id="EDATE" name="EDATE" value="${params.EDATE}"/>
<input type="hidden" id="USER_ID" name="USER_ID" value="${detail.USER_ID}">
<input type="hidden" id="SBJ_REQ" name="SBJ_REQ" value="${detail.SBJ_REQ }" />
<input type="hidden" id="F_CAT_CD" name="F_CAT_CD" value="${detail.F_CAT_CD }" />
<input type="hidden" id="F_AREA" name="F_AREA" value="${detail.F_AREA }" />
<input type="hidden" id="S_CAT_CD" name="S_CAT_CD" value="${detail.S_CAT_CD }" />
<input type="hidden" id="S_AREA" name="S_AREA" value="${detail.S_AREA }" />
<input type="hidden" id="EVENT_REQ" name="EVENT_REQ" value="${detail.EVENT_REQ }" />
<input type="hidden" id="SBJ_REQ_COUNT" name="SBJ_REQ_COUNT" value="${fn:length(fn:replace(detail.SBJ_REQ,',','')) }" />
<input type="hidden" id="USER_PWD" name="USER_PWD" value="${detail.USER_PWD}" />
<input type="hidden" id="OLD_Y" name="OLD_Y" value="Y" />

    <h2>● 회원관리 > <strong>이관회원조회</strong></h2> 
        <table class="table01">
        <tr>
            <th width="15%">회원아이디</th>
            <td colspan="3">${detail.USER_ID}</td>
        </tr>       
        <tr>
            <th width="15%">성명</th>
            <td colspan="3">
                <input type="text" id="USER_NM" name="USER_NM" value="${detail.USER_NM}" size="20"  maxlength="25" title="성명" style="width:28%;background:#FFECEC;"/>
            </td>
        </tr>       
        <tr>
            <th>생년월일</th>
            <td>
                <input type="text" id="BIRTH_DAY" name="BIRTH_DAY" value="${detail.BIRTH_DAY}" size="20"  maxlength="8" title="생년월일" style="width:50%;background:#FFECEC;"/>
            </td>
            <th>이메일</th>
            <td>
                <input type="text" id="EMAIL" name="EMAIL" value="${detail.EMAIL}" size="20"  maxlength="80" title="이메일" style="width:50%;background:#FFECEC;"/>
            </td>
        </tr>
        <tr>
            <th>핸드폰</th>
            <td>
                <input type="text" id="PHONE_NO" name="PHONE_NO" value="${detail.PHONE_NO}" size="20"  maxlength="20" title="핸드폰" style="width:50%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
            </td>
            <th>자택전화번호</th>
            <td>
                <input type="text" id="TEL_NO" name="TEL_NO" value="${detail.TEL_NO}" size="20"  maxlength="20" title="전화번호" style="width:50%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
            </td>
        </tr>
        <tr>
            <th>SMS/EMAIL 수신동의</th>
            <td colspan=3>
                <label for="ISOK_SMS">SMS수신</label>
                <select  id="ISOK_SMS" name="ISOK_SMS">
                    <option value="Y" <c:if test="${detail.ISOK_SMS eq 'Y'}">selected='selected'</c:if>>수신</option>
                    <option value="N" <c:if test="${detail.ISOK_SMS eq 'N'}">selected='selected'</c:if>>거부</option>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="ISOK_EMAIL">EMAIL수신</label>
                <select  id="ISOK_EMAIL" name="ISOK_EMAIL">
                    <option value="Y" <c:if test="${detail.ISOK_SMS eq 'Y'}">selected='selected'</c:if>>수신</option>
                    <option value="N" <c:if test="${detail.ISOK_SMS eq 'N'}">selected='selected'</c:if>>거부</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>자택주소</th>
            <td colspan=3>
                <input type="text" id="ZIP_CODE" name="ZIP_CODE" value="${detail.ZIP_CODE}"  readonly="readonly" size="7"  maxlength="7" title="우편번호" style="width:18%;ime-mode:disabled;"/>
                <input type="button" name="input" onClick="ZipSearch()" value="우편번호찾기">
                <div id="UI_ZIPCODE" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
                </div>
                <input type="text" id="ADDRESS1" name="ADDRESS1" value="${detail.ADDRESS1}"  readonly="readonly"  size="60"   title="자택주소" style="width:80%;ime-mode:disabled;"/>
                <input type="text" id="ADDRESS2" name="ADDRESS2" value="${detail.ADDRESS2}" size="60"   title="자택주소 상세" style="width:80%;ime-mode:disabled;"/>
            </td>
        </tr>
        <tr>
            <th>포인트</th>
            <td>
                <input type="text" id="USER_POINT" name="USER_POINT" value="${detail.USER_POINT}" size="20"  maxlength="20" title="포인트" style="width:18%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
                <input type="hidden" id="BEFORE_USER_POINT" name="BEFORE_USER_POINT" value="${detail.USER_POINT}" size="20"  maxlength="20" title="포인트" style="width:18%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
            </td>
            <th>상태</th>
            <td>
                <label for="ISUSE"></label>
                <select  id="ISUSE" name="ISUSE">
                    <option value="Y"  <c:if test="${detail.ISUSE == 'Y'}">selected</c:if>>활성</option>
                    <option value="N" <c:if test="${detail.ISUSE == 'N'}">selected</c:if>>비활성</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>가입경로</th>
            <td>
                <select id="JOIN_CHANNEL" name="JOIN_CHANNEL">
                    <option value="">선택하세요</option>
                <c:forEach items="${JOIN_CHANNELs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.JOIN_CHANNEL}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
            <th>직업</th>
            <td>
                <select id="JOB" name="JOB">
                    <option value="">선택하세요</option>
                <c:forEach items="${JOBs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.JOB}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>관심직렬</th>
            <td>
                <select id="CATEGORY_CODE" name="CATEGORY_CODE">
                    <option value="">선택하세요</option>
                <c:forEach items="${categoryList}" var="data" varStatus="status" >
                    <option value="${data.CODE}" <c:if test="${data.CODE eq detail.USER_CATEGORY_CODE}">selected='selected'</c:if>><c:out value='${data.NAME}'/></option>
                </c:forEach>
                </select>
            </td>
            <th>수험기간</th>
            <td>
                <select id="EXAM_REQ" name="EXAM_REQ">
                    <option value="">선택하세요</option>
                <c:forEach items="${EXAM_REQs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.EXAM_REQ}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>선호하는 선택과목</th>
            <td colspan=3>
                <select id="sbj_req_1" name="sbj_req_1" onchange="javascript:fn_sbj_req('1');">
                    <option value="">선택하세요</option>
                <c:forEach items="${SBJ_REQs}" var="data" varStatus="status" >
                    <c:set var="CHECKEDSET" value="" />
                    <c:if test="${data.CODE_CD eq fn:substring(fn:replace(detail.SBJ_REQ,',',''),0,1)}"><c:set var="CHECKEDSET" value="selected='selected'" /></c:if>
                    <option value="${data.CODE_CD}" ${CHECKEDSET}><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;
                <select id="sbj_req_2" name="sbj_req_2" onchange="javascript:fn_sbj_req('2');">
                    <option value="">선택하세요</option>
                <c:forEach items="${SBJ_REQs}" var="data" varStatus="status" >
                    <c:set var="CHECKEDSET" value="" />
                    <c:if test="${data.CODE_CD eq fn:substring(fn:replace(detail.SBJ_REQ,',',''),1,2)}"><c:set var="CHECKEDSET" value="selected='selected'" /></c:if>
                    <option value="${data.CODE_CD}" ${CHECKEDSET}><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>1차 관심직종 및 지역</th>
            <td colspan=3>
                <select id="f_cat_cd" name="f_cat_cd" onchange="javascript:fn_f_cat_cd(this);">
                    <option value="">선택하세요</option>
                <c:forEach items="${S_CAT_CDs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.F_CAT_CD}" >selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
                <p class="stxp">※ 1차 관심 직종의 선택가능한 지역을 모두 선택 해 주세요.</p>
                <ul class="iptList">
                <c:forEach items="${AREAs}" var="data" varStatus="status" >
                    <c:set var="CHECKEDSET" value="" />
                    <c:set var="x" value="0" />
                    <c:set var="y" value="2" />
                    <c:forEach var="i" begin="1" end="${fn:length(fn:replace(detail.F_AREA,',',''))/2}">
                        <c:if test="${data.CODE_VAL eq fn:substring(fn:replace(detail.F_AREA,',',''),x,y)}"><c:set var="CHECKEDSET" value="checked='checked'" /></c:if>
                        <c:set var="x" value="${x+2}" /><c:set var="y" value="${y+2}" />
                    </c:forEach>
                    <input type="checkbox" id="f_area_${data.CODE_CD}" name="f_area_${data.CODE_CD}" value="${data.CODE_CD}" onchange="javascript:fn_f_area(this)" ${CHECKEDSET}><label for="f_area_${data.CODE_CD}"><c:out value='${data.CODE_NM}'/></label>&nbsp;
                </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <th>2차 관심직종 및 지역</th>
            <td colspan=3>
                <select id="s_cat_cd" name="s_cat_cd" onchange="javascript:fn_s_cat_cd(this);">
                    <option value="">선택하세요</option>
                <c:forEach items="${S_CAT_CDs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.S_CAT_CD}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
                <p class="stxp">※ 2차 관심 직종의 선택가능한 지역을 모두 선택 해 주세요.</p>
                <ul class="iptList">
                <c:forEach items="${AREAs}" var="data" varStatus="status" >
                    <c:set var="x" value="0" />
                    <c:set var="y" value="2" />
                    <c:set var="CHECKEDSET" value="" />
                    <c:forEach var="i" begin="1" end="${fn:length(fn:replace(detail.S_AREA,',',''))/2}">
                        <c:if test="${data.CODE_VAL eq fn:substring(fn:replace(detail.S_AREA,',',''),x,y)}"><c:set var="CHECKEDSET" value="checked='checked'" /></c:if>
                        <c:set var="x" value="${x+2}" /><c:set var="y" value="${y+2}" />
                    </c:forEach>
                    <input type="checkbox" id="s_area_${data.CODE_CD}" name="s_area_${data.CODE_CD}" value="${data.CODE_CD}" onchange="javascript:fn_s_area(this)" ${CHECKEDSET}><label for="s_area_${data.CODE_CD}"><c:out value='${data.CODE_NM}'/></label>&nbsp;
                </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <th>관심정보</th>
            <td>
                <select id="INFO_REQ" name="INFO_REQ">
                    <option value="">선택하세요</option>
                <c:forEach items="${INFO_REQs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.INFO_REQ}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
            <th>선호이벤트</th>
            <td>
                <select id="event_req" name="event_req" onchange="javascript:fn_event_req(this);">
                    <option value="">선택하세요</option>
                <c:forEach items="${EVENT_REQs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.EVENT_REQ}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
                <c:if test="${detail.EVENT_REQ eq 'F' }">
                <div class="elts" id="event_req_etc" style="display: block"><label for="elts">기타 :</label><input type="text" id="event_req_etc_txt" name="event_req_etc_txt" value="${myInfoMap.EVENT_REQ_ETC }" class="ipt"></div>
                </c:if>
                <c:if test="${detail.EVENT_REQ ne 'F' }">
                <div class="elts" id="event_req_etc" style="display: none"><label for="elts">기타 :</label><input type="text" id="event_req_etc_txt" name="event_req_etc_txt" value="${myInfoMap.EVENT_REQ_ETC }" class="ipt"></div>
                </c:if>
            </td>
        </tr>
        <tr>
            <th>특이사항</th>
            <td colspan=3>
                <textarea id="REMARK" name="REMARK" ROWS="3" maxlength="4000" style="width:68%;">${detail.REMARK}</textarea>
            </td>
        </tr>
    </table>
    <!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:fn_Submit();">회원이동</a></li>
      <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
<script type="text/javascript">
//등록
function fn_Submit(){

	if(confirm("현회원 목록으로 이동하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/memberManagement/memberInsertProcess.do' />");
        $("#frm").submit();
    }
}

function fn_List(){
    $("#frm").attr("action", "<c:url value='/memberManagement/oldMemberList.do' />");
    $("#frm").submit();
}
</script>